/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created July 9, 2013
 * @description This class will ...
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.ieee.common.json.XML;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.ParamUtil;
import org.ieeecs.communities.mongo.MongoConfigUtil;
import org.ieeecs.communities.mongo.MongoException;
import org.ieeecs.communities.mongo.MongoHandler;
import org.ieeecs.communities.util.HomepageContentUtil;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomepageContentController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(HomepageContentController.class);
    // TODO: move to CSDL Constants
    public static final String CSDL_ROOT_PATH = "http://www.computer.org/digitallibrary/";
    public static final String CSDL_URL_ENDPOINT_CONTENT_DOI = "content/description/doi/";

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
     * This atomic helper method will retrieve the purchase data based on
     * the user's credentials.
     * TODO: Move this to common CSDL Handler
     *
     * @param userId
     * @return String
     * @throws Exception
     */
    private String getPurchaseData(long userId) throws Exception {
        String retVal = "{}";
        try {
            // build up the query document
            BasicDBObject query = new BasicDBObject("user_id", userId);

            // execute the query against the collection
            List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PURCHASE, query);
            // if there is a result, grab the first one
            if(results != null && results.size() > 0) {
                retVal = results.get(0).toString();
            }
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the purchase data.",  e);
        }
        return retVal;
    }

    /**
     * This function will load up the article data from the REST Endpoint
     * based on the passed in cid
     * @param cid
     * @return String
     */
    private String loadArticleContent(String cid) {
        // build the URL for the article count REST endpoint
        String url = CSDL_ROOT_PATH + CSDL_URL_ENDPOINT_CONTENT_DOI;
        String fullURL = url + cid;
        fullURL = fullURL.replace(" ", "%20");
        return this.retrieveContentData(fullURL);
    }

    /**
     * Atomic helper method that will retrieve the content data from the passed in
     * url and return it in JSON form to the callee.
     *
     * @param url
     * @return   String
     */
    private String retrieveContentData(String url) {
        HttpClient client = new HttpClient();
        String retVal = "";
        // create the GET method for the URL
        GetMethod method = new GetMethod(url);
        try {
            // execute the get method for the content endpoint
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("There was a problem accessing the content with url endpoint: " + url);
            } else {
                // grab the response body
                byte[] responseBody = method.getResponseBody();

                // since the CDSL returns as XML, we need to convert it to json
                org.ieee.common.json.JSONObject responseJSON = XML.toJSONObject(new String(responseBody));
                // build article JSON in atomic method
                retVal = responseJSON.toString();
            }
        } catch (Exception e) {
            LOGGER.error("An exception occurred when retrieving the content data.", e);
        } finally {
            method.releaseConnection();
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
            String requestType = com.liferay.portal.kernel.util.ParamUtil.getString(request, "requestType_" + instanceId, "");

            // determine which functionality to use based on the request type
            if (requestType.equalsIgnoreCase(HomepageContentUtil.REQUEST_TYPE_LOAD_ARTICLE_CONTENT)) {
                // grab the units JSON off of the request
                String cid = com.liferay.portal.kernel.util.ParamUtil.getString(request, "cid_" + instanceId, "");

                // use the CSDL Util to retrieve the actual subscription content metadata
                model.put("response", this.loadArticleContent(cid));
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred when handling the resource request.", e);
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
        boolean isAuthenticated = false;

        try {
            // first grab the theme display for the portlet
            themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            // set if the user is signed in or not
            isAuthenticated = themeDisplay.isSignedIn();
            // grab the preferences from the request
            prefs = renderRequest.getPreferences();
            // add the default preferences to the model used in the view
            HomepageContentUtil.putPortletPreferencesIntoModel(prefs, model);

            // grab the instance id of this portlet
            instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

            // -----------------------------------------------------------------------------------------------------------------
            // If the current user has Deactivated this Portlet, we should still let them
            // see what they are doing.  Allow only the user who is controlling this portlet
            // configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
            // until this current "owner" user sets it to "Activated".
            // -----------------------------------------------------------------------------------------------------------------
            String currentUserId = new Long(themeDisplay.getUserId()).toString();
            String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepageContentUtil.USERID);
            String currentMode = prefs.getValue("portletMode", HomepageContentUtil.MODE);

            // if the current user is the modifying user, then put the model in "preview" mode
            if (currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) &&
                    currentMode.trim().equalsIgnoreCase(HomepageContentUtil.MODE)) {
                model.put("portletMode", "PREVIEW");
            }

            if(isAuthenticated) {
                // add teh user's purchase data to the request since they are authenticated
                model.put("userPurchaseData", this.getPurchaseData(themeDisplay.getUserId()));
            }

            // get the content type so we can know how the UI will render
            String type = ParamUtil.getStringParameter(renderRequest, "type");
            type = (type != null && !("".equalsIgnoreCase(type))) ? type.toLowerCase() : "generic";
            model.put("type", type);

            // get the unique identifier for the content
            String id = ParamUtil.getStringParameter(renderRequest, "cid");
            model.put("cid", id);

            // add the url for Elastic Search
            String esURL = PropsUtil.get(HomepageContentUtil.PROPERTY_ES_URL);
            model.put("elasticSearchURL", esURL);
        } catch (Exception e) {
            //  gracefully handle exception and put on model
            model.put("error", "A problem has occurred.  Please reload the page or contact help@computer.org.");
            LOGGER.error("An error occurred when handling the render request.", e);
        }

        // create the model for the View and add the model attributes to it
        model.put("isAuthenticated", isAuthenticated);
        model.put("id", instanceId);
        modelAndView = new ModelAndView("Home", model);
        return modelAndView;
    }
}