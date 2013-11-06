/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created July 9, 2013
 * @description This class will represent the Choice Liferay Model
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.ieee.common.json.XML;
import org.ieee.common.presentation.controller.BaseController;
import org.ieeecs.communities.mongo.MongoConfigUtil;
import org.ieeecs.communities.mongo.MongoException;
import org.ieeecs.communities.mongo.MongoHandler;
import org.ieeecs.communities.util.DateUtil;
import org.ieeecs.communities.util.HomepageAccountUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomepageAccountController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(HomepageAccountController.class);
	// TODO: move to CSDL Constants
	public static final String CSDL_ROOT_PATH = "http://www.computer.org/digitallibrary/";
	public static final String CSDL_URL_ENDPOINT_CONTENT_DESCRIPTION = "content/description";
	public static final String CSDL_URL_ENDPOINT_CONTENT_LIST_PUBLICATIONDATE = "content/list/publicationDate/";

    /**
     * Helper method that will clean up all necessary resources
     * when the bean is destroyed.
     * @throws Exception
     */
    public void cleanUp() throws Exception {
        // close all mongo connections
        MongoHandler.getInstance().close();
    }

	/**
	 * Atomic helper method that will save the homepage account meta data to the 
	 * cache
	 * @param metaData
	 * @param startDate
	 * @param endDate
	 * @param numberOfArticles
	 * @throws Exception
	 */
	private void savePortletMetaData(BasicDBObject metaData,String startDate, String endDate, String numberOfArticles) throws Exception {
		BasicDBObject meta = new BasicDBObject();
		// if there was already meta data, we just need to update the data
		if(metaData != null) {
            // create the query object for the update
            BasicDBObject query = new BasicDBObject();
            query.put(HomepageAccountUtil.PORTLET_NAME, HomepageAccountUtil.META_PORTLET_NAME);
            // update the metaData values
            metaData.put(HomepageAccountUtil.META_START_DATE, startDate);
            metaData.put(HomepageAccountUtil.META_END_DATE, endDate);
            metaData.put(HomepageAccountUtil.REQUEST_TYPE_NUM_OF_ARTICLES, numberOfArticles);
            metaData.put(HomepageAccountUtil.META_CREATED_DATE, DateUtil.now());
			MongoHandler.getInstance().update(MongoConfigUtil.Collection.PORTLET_META_DATA, query, metaData);
		} else {
            // we'll create a new homepage account document
            meta.put(HomepageAccountUtil.PORTLET_NAME, HomepageAccountUtil.META_PORTLET_NAME);
            meta.put(HomepageAccountUtil.META_START_DATE, startDate);
            meta.put(HomepageAccountUtil.META_END_DATE, endDate);
            meta.put(HomepageAccountUtil.REQUEST_TYPE_NUM_OF_ARTICLES, numberOfArticles);
            meta.put(HomepageAccountUtil.META_CREATED_DATE, DateUtil.now());
            MongoHandler.getInstance().insert(MongoConfigUtil.Collection.PORTLET_META_DATA, meta);
        }
	}
	
	/**
	 * This method will hit the CSDL RESTful endpoint for the articles within 
	 * the specified date range.
	 * @param startDate
	 * @param endDate
	 * @return retVal
	 */
	public  String getArticlesCount(String startDate, String endDate) {
		String retVal = "";
		HttpClient client = new HttpClient();
		GetMethod method = null;
		BasicDBObject metaData = null;
		try {
			// first check the portlet meta data cache to see if there is data that is not stale
			metaData = (BasicDBObject) this.getPortletMetaData(HomepageAccountUtil.META_PORTLET_NAME);
			if(metaDataIsStale(metaData, HomepageAccountUtil.META_CREATED_DATE, HomepageAccountUtil.CACHE_AGE_LIMIT)) {
				String url = CSDL_ROOT_PATH + CSDL_URL_ENDPOINT_CONTENT_LIST_PUBLICATIONDATE  + startDate + "/"+endDate;
				url = url.replace(" ", "%20");
				// create the GET method for the URL 
				method = new GetMethod(url);
				// grab the REST status code returned
				int statusCode = client.executeMethod(method);
				if (statusCode != HttpStatus.SC_OK) {
					retVal = "{ \"statusCode\":"+statusCode+", \"message\": \"There was a problem accessing the Digital Library.\"}";
				} else {
					// grab the response body
					byte[] responseBody = method.getResponseBody();
					// since the CDSL returns as XML, we need to convert it to json
					org.ieee.common.json.JSONObject jsonObject = XML.toJSONObject(new String(responseBody));
					org.ieee.common.json.JSONObject resJSON = jsonObject.getJSONObject("csdlresponse");
					org.ieee.common.json.JSONObject statusJSON = resJSON.getJSONObject("status");
					String numberOfArticles = (String)statusJSON.get("hits");
					retVal = "{\"numOfArticles\":\""+numberOfArticles+"\"}";
					
					// save the meta data to mongo
					this.savePortletMetaData(metaData, startDate, endDate, numberOfArticles);
				}
			} else {
				retVal = "{\"numOfArticles\":\""+metaData.get(HomepageAccountUtil.REQUEST_TYPE_NUM_OF_ARTICLES).toString()+"\"}";
			}
		} catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the articles count.",  e);
		} finally {
			if(method != null) {
				method.releaseConnection();
			}
		}
		return retVal;
	}
	
	/**
	 * This method is called before the view of the portlet is rendered.  It will 
	 * perform any necessary setup processes.
	 * @param renderRequest
	 * @param renderResponse
	 */
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
		JSONObject jsonObject = null;
		ModelAndView modelAndView = null;
		ThemeDisplay themeDisplay = null;
		PortletPreferences prefs = null;
		boolean isSignedIn = false;
		
		try {
			// first grab the theme display for the portlet			
			themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			// set if the user is signed in or not
			isSignedIn = themeDisplay.isSignedIn();
			// grab the preferences from the request
			prefs = renderRequest.getPreferences();
			// add the default preferences to the model used in the view
			HomepageAccountUtil.putPortletPreferencesIntoModel(prefs, model);
			// grab the instance id of this portlet
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();
			// set the id and firstname on the model for the view
			model.put("id", instanceId);
			model.put("firstName", themeDisplay.getUser().getFirstName());

			// -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only the user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this current "owner" user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepageAccountUtil.USERID);
			String currentMode = prefs.getValue("portletMode", HomepageAccountUtil.MODE);

			// if the current user is the modifying user, then put the model in "preview" mode
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && 
					currentMode.trim().equalsIgnoreCase(HomepageAccountUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}
			
			// retrieve and read in the JSON for the account view
			String json = this.getPurchaseData(themeDisplay.getUserId(), themeDisplay.getUser().getEmailAddress());
			// now parse the json for the data needed in the model
			jsonObject = (JSONObject)new JSONParser().parse(json);	
			// set default values on the model
			model.put("accountArticles", "-");
			model.put("availableArticles", "-");		
			model = this.hydrateModelWithAccountJSON(model, jsonObject);
		} catch (Exception e) {
			// gracefully handle exception and put on model
			model.put("error", "There was a problem loading your account information.  Please reload the page or contact help@computer.org.");
            LOGGER.error("A problem occurred when handling a request",  e);
		}

		// create the model for the View and add the model attributes to it
		model.put("isSignedIn", isSignedIn);
		modelAndView = new ModelAndView("Home", model);
		return modelAndView;
	}

	/**
	 * This method will load the number of articles in the CSDL based on the
	 * passed in start and end time on the request.
	 * @param request
	 * @param response
	 */
	@Override
	public ModelAndView handleResourceRequest(ResourceRequest request,
			ResourceResponse response) throws Exception {
		ModelAndView modelAndView = null;
		Map<String,Object> model = new HashMap<String,Object>();
		 try{                
		       
			// grab the ThemeDisplay that contains all needed information on the user
             // first grab the theme display for the portlet
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
			int cropHere = portletId.indexOf("_INSTANCE_");
			String instanceId = "_" + portletId.substring(cropHere+10);

			// grab the request type from the request
			String requestType = ParamUtil.getString(request, "requestType_"+instanceId, "");

			// determine which functionality to use based on the request type
			if ( requestType.equalsIgnoreCase(HomepageAccountUtil.REQUEST_TYPE_NUM_OF_ARTICLES) ) {
				// grab the start and end dates for the request
				String startDate = ParamUtil.getString(request, "startDate_"+instanceId, "");
				String endDate = ParamUtil.getString(request, "endDate_"+instanceId, "");
				// use the CSDL Util to retrieve the number of articles for the request date range
				model.put("response", this.getArticlesCount(startDate, endDate));
			} else if ( HomepageAccountUtil.REQUEST_TYPE_USER_PURCHASE_DATA.equalsIgnoreCase(requestType)) {
                // grab the user's purchase data
                model.put("response",  this.getPurchaseData(themeDisplay.getUserId(), themeDisplay.getUser().getEmailAddress()));
            } else if (HomepageAccountUtil.REQUEST_TYPE_UPDATE_USER_PURCHASE_DATA.equalsIgnoreCase(requestType)) {
                // grab the purchase data json off the request
                String purchaseJSON  = ParamUtil.getString(request, "purchaseData_"+instanceId, "");
                // update the user's purchase data
                if(this.updatePurchaseData(purchaseJSON, themeDisplay.getUserId(), null)) {
                    model.put("response", 200);
                } else {
                    model.put("response", 500);
                }
            }
			response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/json");
		} catch (Exception e) {
             LOGGER.error("A problem occurred when handling a resource request",  e);
		}
		
		// specify which JSP to render the response to
		modelAndView = new ModelAndView("Response", model);
	    return modelAndView;
	}
	
	/**
	 * This function will add the necessary JSON data to the model for the view.
	 * 
	 * @param model
	 * @param jsonObject
	 * @return model
	 * @throws Exception
	 */
	private Map<String,Object> hydrateModelWithAccountJSON(Map<String,Object> model, JSONObject jsonObject) throws Exception {
		try {
			// first determine the user's bundle size
			JSONObject bundle = (JSONObject)jsonObject.get("bundle");
			if(bundle != null) {
				JSONArray csdlArticles = (JSONArray) bundle.get("csdl_article");
				int totalBundleSize = 0;
				// if there are some articles we can add to the bundle size
				if(csdlArticles != null) {
					int size = csdlArticles.size();
				    for (int i = 0; i < size; i++) {
				        JSONObject articleUnit = (JSONObject) csdlArticles.get(i);
				        Long numOfItems = (Long)articleUnit.get("number_of_items");
				        totalBundleSize += numOfItems;
				    }
				}
			    
			    // now determine how many units are used in their bundle
			    JSONObject units = (JSONObject)jsonObject.get("units");
			    if(units != null) {
			    	JSONArray csdlSubscriptions = (JSONArray) units.get("csdl_article");
			    	if(csdlSubscriptions != null) {
				    	int totalCSDLSubscriptions = csdlSubscriptions.size();
		
					    // now we can determine the available number of subscriptions 
					    int availableArticles = totalBundleSize - totalCSDLSubscriptions;
						model.put("accountArticles", String.valueOf(totalCSDLSubscriptions));
						model.put("availableArticles", String.valueOf(availableArticles));
			    	}
			    }
			}
		} catch (Exception e) {
            LOGGER.error("A problem occurred when hydrating JSON into a map.",  e);
		}
		return model;
	}
	
	/**
	 * This atomic helper method will retrieve the purchase data based on 
	 * the user's credentials.
	 * TODO: Move this to common CSDL Handler
	 * 
	 * @param userId
     * @param email
	 * @return String
	 * @throws Exception 
	 */
	private String getPurchaseData(long userId, String email) throws Exception {
		String retVal = "{}";
		try {
			// build up the query document
			BasicDBObject query = new BasicDBObject("user_id", userId);
			
			// execute the query against the collection
			List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PURCHASE, query);

            /**
             * If the user does not have any bundle purchase information based on their id, this
             * means that they are a new user.  Since a new organization's users are added to
             * the system by the unique email addresses, we need to search against their Liferay
             * email for their purchase data.
             *
             * If the user is then found, we will then update their purchase data with their userid
             * to be used in the future.
             *
             * If the user is NOT found.  At this point they will NOT have any purchase data.  Is
             * this what we want?
             */
			// if there is a result, grab the first one
			if(results != null && results.size() > 0) {
				retVal = results.get(0).toString();
			} else {
                // now grab the purchase data by the email address
                query = new BasicDBObject("email", email);

                // execute the query against the collection
                results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PURCHASE, query);
                if(results != null && results.size() > 0) {

                    // put the user's user id on the purchase object for future logins
                    DBObject purchaseData = results.get(0);
                    purchaseData.put("user_id", userId);
                    // update the purchase data in mongo
                    this.updatePurchaseData(purchaseData.toString(), userId, email);
                    retVal = purchaseData.toString();
                }
            }
		} catch (MongoException me) {
			throw me;
		} catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the purchase data.",  e);
		}
		return retVal;
	}

    /**
     * This method will update the user's purchase data in the datasource based
     * on the passed in user id
     * @param purchaseJSON
     * @param userId
     * @param email
     * @return  boolean
     * @throws Exception
     */
    private boolean updatePurchaseData(String purchaseJSON, long userId, String email) throws Exception {
        boolean retVal = false;
        try {
            // build up the query document
            BasicDBObject query = null;
            if(email != null) {
                query = new BasicDBObject("email", email);
            } else {
                query = new BasicDBObject("user_id", userId);
            }
            // convert the json to a DBobject so we can save in mongo
            BasicDBObject purchaseData = MongoHandler.getInstance().jsonToDBObject(purchaseJSON);

            // execute the query against the collection
            MongoHandler.getInstance().update(MongoConfigUtil.Collection.PURCHASE, query, purchaseData);
            retVal = true;
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when updating the purchase data for user with id: " + userId,  e);
        }
        return retVal;
    }
	
	/**
	 * This atomic helper method will retrieve the portlet meta data based on 
	 * the passed in portlet name.
	 * TODO: Move this to common CSDL Handler
	 * 
	 * @param portletName
	 * @return DBObject
	 * @throws Exception 
	 */
	public DBObject getPortletMetaData(String portletName) throws Exception {
		DBObject retVal = null;
		try {
			// build up the query document
			BasicDBObject query = new BasicDBObject("portletName", portletName);
			
			// execute the query against the collection
			List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PORTLET_META_DATA, query);
			// if there is a result, grab the first one
			if(results != null && results.size() > 0) {
				retVal = results.get(0);
			}
		} catch (MongoException me) {
			throw me;
		} catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the portlet data.",  e);
		}
		return retVal;
	}
	
	/**
	 * Helper method that will determine if the meta data is stale based on the 
	 * portlet's meta data age limit
	 * TODO: Move to common bundle code
	 * 
	 * For each of the portlets we assume three things:
	 * 1.  That the attribute key is on the top level of the JSON object
	 * 2.  That the date is stored as a UTC string in the DB
	 * 3.  That the age limit will be measured in SECONDS
	 * 
	 * @param metaData
	 * @param attributeKey
	 * @param ageLimit
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean metaDataIsStale(BasicDBObject metaData, String attributeKey, long ageLimit) throws Exception {
		boolean retVal = true;
		if(metaData != null) {
			// if it is not stale, use that data
			String created = (String) metaData.get(attributeKey);
			if(created != null && !"".equalsIgnoreCase(created)) {
				// convert to date and compare against the current data
				Date now = new Date();
				Date metaCreated = DateUtil.toUTCDate(created);
				long age = (now.getTime()-metaCreated.getTime())/1000;
				retVal = age > ageLimit;
			}
		} 
		return retVal;
	}
}
