/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created July 11, 2013
 * @description This class will handle the server side functionality for the featured
 * content portlet.
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.log4j.Logger;
import org.ieee.common.presentation.controller.BaseController;
import org.ieeecs.communities.mongo.MongoConfigUtil;
import org.ieeecs.communities.mongo.MongoException;
import org.ieeecs.communities.mongo.MongoHandler;
import org.ieeecs.communities.util.HomepageFeaturedContentUtil;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomepageFeaturedContentController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(HomepageFeaturedContentController.class);

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
            if (results != null && results.size() > 0) {
                retVal = results.get(0);
            }
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the portlet data",  e);
        }
        return retVal;
    }

    /**
     * The method will save the json to the specified portlet meta data
     * in mongo db.
     *
     * @param portletName
     * @param json
     * @throws Exception
     */
    public void savePortletMetaData(String portletName, String json) throws Exception {
        try {
            BasicDBObject metaData = MongoHandler.getInstance().jsonToDBObject(json);
            BasicDBObject query = new BasicDBObject("portletName", portletName);
            MongoHandler.getInstance().update(MongoConfigUtil.Collection.PORTLET_META_DATA, query, metaData);
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when handling a request",  e);
        }
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
        String groupId = "";
        String instanceId = "";
        ModelAndView modelAndView = null;
        ThemeDisplay themeDisplay = null;
        PortletPreferences prefs = null;
        boolean canInlineEdit = false;
        boolean isSignedIn = false;

        try {
            // first grab the theme display for the portlet
            themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            // set the group id
            groupId = Long.toString(themeDisplay.getScopeGroupId());
            // set if the user is signed in or not
            isSignedIn = themeDisplay.isSignedIn();
            // grab the preferences from the request
            prefs = renderRequest.getPreferences();
            // add the default preferences to the model used in the view
            HomepageFeaturedContentUtil.putPortletPreferencesIntoModel(prefs, model);
            // grab the instance id of this portlet
            instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

            // -----------------------------------------------------------------------------------------------------------------
            // If the current user has Deactivated this Portlet, we should still let them
            // see what they are doing.  Allow only the user who is controlling this portlet
            // configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
            // until this current "owner" user sets it to "Activated".
            // -----------------------------------------------------------------------------------------------------------------
            String currentUserId = new Long(themeDisplay.getUserId()).toString();
            String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepageFeaturedContentUtil.USERID);
            String currentMode = prefs.getValue("portletMode", HomepageFeaturedContentUtil.MODE);

            // if the current user is the modifying user, then put the model in "preview" mode
            if (currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) &&
                    currentMode.trim().equalsIgnoreCase(HomepageFeaturedContentUtil.MODE)) {
                model.put("portletMode", "PREVIEW");
            }

            // -----------------------------------------------------------------------------------------------------------------
            // This will be moved in the next few versions to the "base" controller class.
            // -----------------------------------------------------------------------------------------------------------------
            User currentUser = themeDisplay.getUser();
            isSignedIn = themeDisplay.isSignedIn();
            boolean isMemberOfGroup = UserLocalServiceUtil.hasGroupUser(new Long(groupId), currentUser.getUserId());
            boolean isLiferayAdmin = renderRequest.isUserInRole("Administrator") ? true : false;

            if (isSignedIn && isMemberOfGroup && !isLiferayAdmin) {
                List<Role> userGroupRoles = RoleLocalServiceUtil.getUserGroupRoles(currentUser.getUserId(), new Long(groupId));
                if (null != userGroupRoles && userGroupRoles.size() > 0) {
                    for (int index = 0; index < userGroupRoles.size(); index++) {
                        Role currentRole = userGroupRoles.get(index);
                        String currentRoleName = currentRole.getName();
                        if ("Community Owner".equals(currentRoleName) ||
                                "Community Administrator".equals(currentRoleName) ||
                                "Community ControlPanel".equals(currentRoleName) ||
                                "Community Editor".equals(currentRoleName)) {
                            canInlineEdit = true;
                        }
                    }
                }

            }
            if (isLiferayAdmin) {
                canInlineEdit = true;
            }
        } catch (Exception e) {
            //gracefully handle exception and put on model
            model.put("error", "A problem has occurred.  Please reload the page or contact help@computer.org.");
            LOGGER.error("A problem occurred when rendering the portlet", e);
        }

        // create the model for the View and add the model attributes to it
        model.put("id", instanceId);
        model.put("canInlineEdit", canInlineEdit);
        model.put("isSignedIn", isSignedIn);
        modelAndView = new ModelAndView("Home", model);
        return modelAndView;
    }

    /**
     * This method will handle saving the configurations to the
     * data cache for this portlet.
     *
     * @param request
     * @param response
     */
    @Override
    public ModelAndView handleResourceRequest(ResourceRequest request,
                                              ResourceResponse response) throws Exception {
        ModelAndView modelAndView = null;
        Map<String, Object> model = new HashMap<String, Object>();
        try {

            // grab the ThemeDisplay that contains all needed information on the user
            String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
            int cropHere = portletId.indexOf("_INSTANCE_");
            String instanceId = "_" + portletId.substring(cropHere + 10);

            // grab the request type from the request
            String requestType = ParamUtil.getString(request, "requestType_" + instanceId, "");

            // determine which functionality to use based on the request type
            if (requestType.equalsIgnoreCase(HomepageFeaturedContentUtil.REQUEST_TYPE_LOAD_CONTENT)) {
                DBObject results = getPortletMetaData(HomepageFeaturedContentUtil.META_PORTLET_NAME);
                // use the CSDL Util to retrieve the number of articles for the request date range
                model.put("response", results.toString());
            } else if (requestType.equalsIgnoreCase(HomepageFeaturedContentUtil.REQUEST_TYPE_SAVE_CONTENT)) {
                String metaJSON = ParamUtil.getString(request, "meta_" + instanceId, "");
                savePortletMetaData(HomepageFeaturedContentUtil.META_PORTLET_NAME, metaJSON);
                model.put("response", "[{}]");
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json");
        } catch (Exception e) {
            LOGGER.error("A problem occurred when handling a request",  e);
        }

        // specify which JSP to render the response to
        modelAndView = new ModelAndView("Response", model);
        return modelAndView;
    }
}
