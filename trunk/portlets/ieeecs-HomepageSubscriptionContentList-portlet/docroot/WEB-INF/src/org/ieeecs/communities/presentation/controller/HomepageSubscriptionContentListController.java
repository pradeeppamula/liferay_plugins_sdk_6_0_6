/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created July 9, 2013
 * @description This class will ...
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.ieee.common.json.XML;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.DateUtils;
import org.ieeecs.communities.mongo.MongoConfigUtil;
import org.ieeecs.communities.mongo.MongoException;
import org.ieeecs.communities.mongo.MongoHandler;
import org.ieeecs.communities.util.DateUtil;
import org.ieeecs.communities.util.HomepageSubscriptionContentListUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.util.*;

public class HomepageSubscriptionContentListController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(HomepageSubscriptionContentListController.class);

    // BEGIN CODE THAT NEEDS TO BE MOVED
    // TODO: move to CSDL Constants
    public static final String CSDL_ROOT_PATH = "http://www.computer.org/digitallibrary/";
    public static final String CSDL_URL_ENDPOINT_CONTENT_DOI = "content/description/doi/";
    public static final String CSDL_ES_ROOT_SEARCH_PATH = "/content/_search";

    /**
     * Helper method that will clean up all necessary resources
     * when the bean is destroyed.
     * @throws Exception
     */
    public void cleanUp() throws Exception {
        // close all mongo connections
        MongoHandler.getInstance().close();
    }

    // subscription content types
    public enum ContentType {
        ARTICLE("article"),
        WEBINAR("webinar"),
        ESSENTIALSET("essentialset"),
        READYNOTE("readynote");
        public final String type;

        private ContentType(final String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public static ContentType getContentType(String contentType) {
            ContentType retVal = ContentType.ARTICLE;
            try {
                retVal = ContentType.valueOf(contentType.toUpperCase());
            } catch (Exception e) {
                LOGGER.warn("An error occurred when getting the subscription content type: " + ExceptionUtils.getRootCauseMessage(e));
            }
            return retVal;
        }
    }

    /**
     * This method will parse out the JSON that represents the subscriber's
     * units and put them into a Map to be returned.
     * @param unitsJSON
     * @return   HashMap<String, List<String>>
     */
    private HashMap<String, List<String>> parseUnitsJSON(String unitsJSON) {
        HashMap<String, List<String>> retVal = new HashMap<String, List<String>>();
        // first convert the string json into an JSON object
        try {
            ArrayList<JSONObject> units = (ArrayList<JSONObject>) new JSONParser().parse(unitsJSON);
            ArrayList<String> articles = null;
            ArrayList<String> webinars = null;
            ArrayList<String> essentialset = null;
            ArrayList<String> readynote = null;
            for (JSONObject unit : units) {
                // get the type of the unit
                String contentType = (String) unit.get("type");
                String id = (String) unit.get("contentId");
                if ("csdl_article".equalsIgnoreCase(contentType)) {
                    if (articles == null) {
                        articles = new ArrayList<String>();
                    }
                    // add the id to the articles
                    articles.add(id);
                } else if ("webinars".equalsIgnoreCase(contentType)) {
                    if (webinars == null) {
                        webinars = new ArrayList<String>();
                    }
                    // add the id to the webinars
                    webinars.add(id);
                } else if ("essentialset".equalsIgnoreCase(contentType)) {
                    if (essentialset == null) {
                        essentialset = new ArrayList<String>();
                    }
                    // add the id to the essentialset
                    essentialset.add(id);
                } else if ("readynote".equalsIgnoreCase(contentType)) {
                    if (readynote == null) {
                        readynote = new ArrayList<String>();
                    }
                    // add the id to the readynote
                    readynote.add(id);
                }
            }
            // Add the specific subscription content to be returned if there are ids for the content type
            if (articles != null && articles.size() > 0) {
                retVal.put(ContentType.ARTICLE.getType(), articles);
            }
            if (webinars != null && webinars.size() > 0) {
                retVal.put(ContentType.WEBINAR.getType(), webinars);
            }
            if (essentialset != null && essentialset.size() > 0) {
                retVal.put(ContentType.ESSENTIALSET.getType(), essentialset);
            }
            if (readynote != null && readynote.size() > 0) {
                retVal.put(ContentType.READYNOTE.getType(), readynote);
            }
        } catch (ParseException e) {
            LOGGER.error("An exception occurred when parsing out the units JSON: "  + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal;
    }

    /**
     * Atomic helper method that will build up the JSON string that will
     * be returned back to the client
     * @param json
     * @param contentType
     * @param contentJSON
     * @param webinarJSON
     * @return String retVal
     */
    private String buildContentJSON(String json, ContentType contentType, org.ieee.common.json.JSONObject contentJSON, String webinarJSON) {
        StringBuilder retVal = null;
        try {
            switch (contentType) {
                case ARTICLE:
                // csdlresponse
                org.ieee.common.json.JSONObject csdlResponse = contentJSON.getJSONObject("csdlresponse");
                if (csdlResponse != null) {
                    // contentlist
                    org.ieee.common.json.JSONObject contentList = csdlResponse.getJSONObject("contentlist");
                    if (contentList != null) {
                        retVal = new StringBuilder(json);
                        // append the content type
                        retVal.append("{\"contentType\": \"");
                        retVal.append(contentType.getType());
                        retVal.append("\", \"content\":");
                        // now append the actual content
                        retVal.append(contentList.toString());
                        retVal.append("}");
                    }
                }
                    break;
                case WEBINAR:
                    retVal = new StringBuilder(json);
                    // append the content type
                    retVal.append("{\"contentType\": \"");
                    retVal.append(contentType.getType());
                    retVal.append("\", \"content\":");
                    // now append the actual content
                    retVal.append(webinarJSON);
                    retVal.append("}");
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("An exception occurred when building the content JSON:"  + ExceptionUtils.getRootCauseMessage(e));
        }
        return (retVal != null) ? retVal.toString() : json;
    }

    /**
     * Atomic helper method that will retrieve the content data from the passed in
     * url and return it in JSON form to the callee.
     *
     * @param contentType
     * @param client
     * @param url
     * @return   String
     */
    private String retrieveContentData(ContentType contentType, HttpClient client, String url) {
        String retVal = "";
        // create the GET method for the URL
        GetMethod method = new GetMethod(url);
        try {
            // execute the get method for the content endpoint
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("There was a problem accessing the Digital Library for contentType: " + contentType + ", with url endpoint: " + url);
            } else {
                // grab the response body
                byte[] responseBody = method.getResponseBody();

                // since the CDSL returns as XML, we need to convert it to json
                org.ieee.common.json.JSONObject responseJSON = XML.toJSONObject(new String(responseBody));
                // build article JSON in atomic method
                retVal = this.buildContentJSON(retVal, contentType, responseJSON, null);
            }
        } catch (Exception e) {
            LOGGER.error("An exception occurred when retrieving the content data: "  + ExceptionUtils.getRootCauseMessage(e));
        } finally {
            method.releaseConnection();
        }
        return retVal;
    }

    /**
     * Atomic helper method that will retrieve the webinar Data
     *
     * @param client
     * @param sku
     * @return   String
     */
    private String retrieveWebinarData(ContentType contentType, HttpClient client, String sku) {
        String retVal = "";
        PostMethod method = null;

        try {
            // create the POST method for the URL
            String esURL = PropsUtil.get(HomepageSubscriptionContentListUtil.PROPERTY_ES_URL);
            method = new PostMethod(esURL + CSDL_ES_ROOT_SEARCH_PATH);
            // Build up the query
            String requestJSONQuery = "{\"query\":{\"multi_match\":{\"fields\":[\"sku\"],\"query\":\""+sku+"\",\"type\":\"prefix\" }}}";
            StringRequestEntity requestEntity = new StringRequestEntity(requestJSONQuery,"application/json","UTF-8");
            method.setRequestEntity(requestEntity);

            // execute the get method for the content endpoint
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("There was a problem accessing the webinar data with endpoint: " + esURL + CSDL_ES_ROOT_SEARCH_PATH + ", with request JSON: " + requestJSONQuery);
            } else {
                // grab the response body
                byte[] responseBody = method.getResponseBody();
                retVal = this.buildContentJSON(retVal, contentType, null, new String(responseBody));
            }
        } catch (Exception e) {
            LOGGER.error("An exception occurred when retrieving the webinar data: " + ExceptionUtils.getRootCauseMessage(e));
        } finally {
            try {
                method.releaseConnection();
            } catch (Exception ee) {} // digest
        }
        return retVal;
    }

    /**
     * This method will hit the necessary CSDL RESTful endpoints for retrieving
     * the user's subscribed content based on the passed in subscription content
     * identifiers.  The content could be csdl articles, webinars, essentialset, and/or
     * readynote.  This could also change down the road and more types of content
     * could be presented and handled here.
     * <p/>
     * TODO: Move to CSDL "Handler" or "Util"
     *
     * @param userId
     * @param unitsJSON
     * @return retVal
     */
    public String getUserSubscriptionContent(String userId, String unitsJSON) {
        String retVal = "[";
        HashMap<String, List<String>> units = null;
        HttpClient client = new HttpClient();
        boolean foundAtLeastOneResult = false;
        try {
            // retrieve and read in the JSON for the user account information
            if (unitsJSON != null && (!unitsJSON.equalsIgnoreCase("") && !unitsJSON.equalsIgnoreCase("{}"))) {

                // parse out the JSON for the units into one big hashmap
                units = this.parseUnitsJSON(unitsJSON);
                // if there are units that need to be retreived
                if (units != null && units.size() > 0) {
                    // iterate over each of the unit types
                    Iterator it = units.entrySet().iterator();
                    while (it.hasNext()) {
                        String url = null;
                        Map.Entry pairs = (Map.Entry) it.next();
                        ContentType contentType = ContentType.getContentType((String) pairs.getKey());
                        // load the content based on the content type
                        switch (contentType) {
                            case ARTICLE:
                                // build the URL for the article count REST endpoint
                                url = CSDL_ROOT_PATH + CSDL_URL_ENDPOINT_CONTENT_DOI;
                                // grab the list of identifiers for the specific content type
                                List<String> contentIdentifiers = (ArrayList) pairs.getValue();
                                // iterate over each of the identifiers to retrieve their data
                                for (String id : contentIdentifiers) {
                                    String fullURL = url + "/" + id;
                                    fullURL = fullURL.replace(" ", "%20");
                                    String contentJSON = this.retrieveContentData(contentType, client, fullURL);
                                    if (contentJSON != null && !contentJSON.equalsIgnoreCase("")) {
                                        if (foundAtLeastOneResult) {
                                            retVal += "," + contentJSON;
                                        } else {
                                            retVal += contentJSON;
                                            foundAtLeastOneResult = true;
                                        }
                                    }
                                }
                                break;
                            case ESSENTIALSET:
                                break;
                            case READYNOTE:
                                break;
                            case WEBINAR:
                                // grab the list of identifiers for the specific content type
                                List<String> skus = (ArrayList) pairs.getValue();
                                // iterate over each of the skus to retrieve the matching webinar
                                for (String sku : skus) {
                                    String contentJSON = this.retrieveWebinarData(contentType, client, sku);
                                    if (contentJSON != null && !contentJSON.equalsIgnoreCase("")) {
                                        if (foundAtLeastOneResult) {
                                            retVal += "," + contentJSON;
                                        } else {
                                            retVal += contentJSON;
                                            foundAtLeastOneResult = true;
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred when retrieving the user subscription content: "  + ExceptionUtils.getRootCauseMessage(e));
        }
        retVal += "]";
        return retVal;
    }

    /**
     * This atomic helper method will retrieve the purchase data based on
     * the user's credentials.
     * TODO: Move this to common CSDL Handler
     *
     * @param userId
     * @return String
     * @throws Exception
     */
    private String getPurchaseData(int userId) throws Exception {
        String retVal = "{}";
        try {
            // build up the query document
            BasicDBObject query = new BasicDBObject("user_id", userId);

            // execute the query against the collection
            List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PURCHASE, query);
            // if there is a result, grab the first one
            if (results != null && results.size() > 0) {
                retVal = results.get(0).toString();
            }
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("An error occurred when retrieving the user purchase data: "  + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal;
    }

    /**
     * This method will load the number of articles in the CSDL based on what
     * the user has in their bundle.
     *
     * @param request
     * @param response
     */
    @Override
    public ModelAndView handleResourceRequest(ResourceRequest request,
                                              ResourceResponse response) throws Exception {
        ModelAndView modelAndView = null;
        ThemeDisplay themeDisplay = null;
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            // grab the ThemeDisplay that contains all needed information on the user
            themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
            int cropHere = portletId.indexOf("_INSTANCE_");
            String instanceId = "_" + portletId.substring(cropHere + 10);
            String currentUserId = new Long(themeDisplay.getUserId()).toString();

            // grab the request type from the request
            String requestType = ParamUtil.getString(request, "requestType_" + instanceId, "");

            // determine which functionality to use based on the request type
            if (requestType.equalsIgnoreCase(HomepageSubscriptionContentListUtil.REQUEST_TYPE_USER_SUBSCRIPTION_CONTENT)) {
                // grab the units JSON off of the request
                String unitsJSON = ParamUtil.getString(request, "units_" + instanceId, "");

                // use the CSDL Util to retrieve the actual subscription content metadata
                model.put("response", this.getUserSubscriptionContent(currentUserId, unitsJSON));
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred when handling the resource request: " + ExceptionUtils.getRootCauseMessage(e));
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        // specify which JSP to render the response to
        modelAndView = new ModelAndView("Response", model);
        return modelAndView;
    }

    /**
     * This method is called before the view of the portlet is rendered.  It will
     * perform any necessary setup processes.
     *
     * @param renderRequest
     * @param renderResponse
     */
    protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        Map<String, Object> model = new HashMap<String, Object>();
        String instanceId = "";
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
            HomepageSubscriptionContentListUtil.putPortletPreferencesIntoModel(prefs, model);
            // grab the instance id of this portlet
            instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

            // -----------------------------------------------------------------------------------------------------------------
            // If the current user has Deactivated this Portlet, we should still let them
            // see what they are doing.  Allow only the user who is controlling this portlet
            // configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
            // until this current "owner" user sets it to "Activated".
            // -----------------------------------------------------------------------------------------------------------------
            String currentUserId = new Long(themeDisplay.getUserId()).toString();
            String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepageSubscriptionContentListUtil.USERID);
            String currentMode = prefs.getValue("portletMode", HomepageSubscriptionContentListUtil.MODE);

            // if the current user is the modifying user, then put the model in "preview" mode
            if (currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) &&
                    currentMode.trim().equalsIgnoreCase(HomepageSubscriptionContentListUtil.MODE)) {
                model.put("portletMode", "PREVIEW");
            }
            // grab the user's purchase data and return to the client
            String json = this.getPurchaseData(Integer.parseInt(currentUserId));
            // now parse the json for the data needed in the model
            JSONObject purchaseJSON = (JSONObject) new JSONParser().parse(json);
            String units = "[{}]";
            if (purchaseJSON != null) {
                // filter out expired webinars
                purchaseJSON = this.filterOutExpiredWebinars(purchaseJSON);

                // populate the articleDOIs list
                JSONObject unitsJSON = (JSONObject) purchaseJSON.get("units");
                if (unitsJSON != null) {
                    units = unitsJSON.toJSONString();
                }
            }
            model.put("units", units);

            // add the url for Elastic Search
            String esURL = PropsUtil.get(HomepageSubscriptionContentListUtil.PROPERTY_ES_URL);
            model.put("elasticSearchURL", esURL);
        } catch (Exception e) {
            //  gracefully handle exception and put on model
            model.put("error", "A problem has occurred.  Please reload the page or contact help@computer.org.");
            LOGGER.error("An error occurred when handling the render request: "  + ExceptionUtils.getRootCauseMessage(e));
        }

        // create the model for the View and add the model attributes to it
        model.put("id", instanceId);
        model.put("isSignedIn", isSignedIn);
        modelAndView = new ModelAndView("Home", model);
        return modelAndView;
    }

    /**
     * Atomic helper function that will check the webinar bundles,
     * and remove the skus of the bundles that are expired.
     *
     * @param purchaseJSON
     * @return JSONObject
     */
    private JSONObject filterOutExpiredWebinars(JSONObject purchaseJSON) {
        try {
            Date today = new Date();
            List<String> expiredSkus = new ArrayList();
            // first grab the bundle object
            JSONObject bundle = (JSONObject) purchaseJSON.get("bundle");

            // next get the webinars list
            JSONArray webinarsBundle = (JSONArray) bundle.get("webinar");
            // iterate over each webinar bundle
            for(Object webinar : webinarsBundle) {
                // get the expiration date of the current webinar bundle
                String expirationDate = (String)((JSONObject)webinar).get("expiration_date");
                Date expiration = DateUtil.toUTCDate(expirationDate);
                // check the webinars object for bundles with an expiration date before today
                if(today.after(expiration)) {
                    // if there is a webinars bundle with an expiration before today, build a list of its skus
                    JSONArray selectedWebinars =  (JSONArray)((JSONObject)webinar).get("selected_items");
                    for(Object item : selectedWebinars) {
                        expiredSkus.add(((JSONObject)item).get("sku").toString());
                    }
                }
            }

            // get the units object
            JSONObject units = (JSONObject) purchaseJSON.get("units");
            // get the webinar skus from the units
            JSONArray webinarUnits = (JSONArray)units.get("webinars");
            // iterate over the expired skus, and check the units webinar skus for a match
            for(String expiredSku : expiredSkus) {
                // if a match is found remove it from the units
                if(webinarUnits.contains(expiredSku)) {
                    webinarUnits.remove(expiredSku);
                }
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred when filtering out the expired webinars: " + ExceptionUtils.getRootCauseMessage(e));
        }
        return purchaseJSON;
    }
}