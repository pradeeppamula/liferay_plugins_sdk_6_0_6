/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created July 9, 2013
 * @description This class will ...
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.ParamUtil;
import org.ieeecs.communities.util.HomepageContentListUtil;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomepageContentListController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(HomepageContentListController.class);

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
            // grab the request type from the request
            String requestType = com.liferay.portal.kernel.util.ParamUtil.getString(request, "requestType_" + instanceId, "");

            // determine which functionality to use based on the request type
            if (requestType.equalsIgnoreCase(HomepageContentListUtil.REQUEST_TYPE_LOAD_SUGGESTED_GROUP_DATA)) {
                // grab the number of articles that we should return
                String limit = com.liferay.portal.kernel.util.ParamUtil.getString(request, "limit_" + instanceId, "");
                limit = (limit != null && !"".equalsIgnoreCase(limit)) ? limit : HomepageContentListUtil.SUGGESTED_GROUP_DEFAULT_LIMIT;
                String json = this.getSuggestedGroups(Integer.parseInt(limit), themeDisplay.getUserId());
                model.put("response", json);
            } else if (requestType.equalsIgnoreCase(HomepageContentListUtil.REQUEST_TYPE_JOIN_GROUP)) {
                // grab the group id from the request
                String groupId = com.liferay.portal.kernel.util.ParamUtil.getString(request, "groupId_" + instanceId, "");
                // join the user to the group
                boolean success = this.joinGroup(Long.parseLong(groupId), themeDisplay.getUserId());
                int status = (success) ? 200 : 500;
                model.put("response", status);
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
        String loadingMetaDataJSON = "";
        try {
            // first grab the theme display for the portlet
            themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            // set if the user is signed in or not
            isSignedIn = themeDisplay.isSignedIn();
            // grab the preferences from the request
            prefs = renderRequest.getPreferences();
            // add the default preferences to the model used in the view
            HomepageContentListUtil.putPortletPreferencesIntoModel(prefs, model);

            // grab the instance id of this portlet
            instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

            // get the content type so we can know how the UI will render
            String type = ParamUtil.getStringParameter(renderRequest, "type");
            type = (type != null && !("".equalsIgnoreCase(type))) ? type.toLowerCase() : "generic";

            // grab the keywords off the request
            String keywords = ParamUtil.getStringParameter(renderRequest, "keywords");

            // build the loading json
            loadingMetaDataJSON = "[{ contentType: \""+ type+"\", keywords: \""+keywords+"\"}]";

            // -----------------------------------------------------------------------------------------------------------------
            // If the current user has Deactivated this Portlet, we should still let them
            // see what they are doing.  Allow only the user who is controlling this portlet
            // configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
            // until this current "owner" user sets it to "Activated".
            // -----------------------------------------------------------------------------------------------------------------
            String currentUserId = new Long(themeDisplay.getUserId()).toString();
            String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepageContentListUtil.USERID);
            String currentMode = prefs.getValue("portletMode", HomepageContentListUtil.MODE);

            // if the current user is the modifying user, then put the model in "preview" mode
            if (currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) &&
                    currentMode.trim().equalsIgnoreCase(HomepageContentListUtil.MODE)) {
                model.put("portletMode", "PREVIEW");
            }
            // add the url for Elastic Search
            String esURL = PropsUtil.get(HomepageContentListUtil.PROPERTY_ES_URL);
            model.put("elasticSearchURL", esURL);
        } catch (Exception e) {
            //  gracefully handle exception and put on model
            model.put("error", "A problem has occurred.  Please reload the page or contact help@computer.org.");
            LOGGER.error("An error occurred when handling the render request: " + ExceptionUtils.getRootCauseMessage(e));
        }

        // create the model for the View and add the model attributes to it
        model.put("loadingMetaData", loadingMetaDataJSON);
        model.put("id", instanceId);
        model.put("isSignedIn", isSignedIn);
        modelAndView = new ModelAndView("Home", model);
        return modelAndView;
    }

    /**
     * This function will add the user to the group
     * @param groupId
     * @param userId
     * @return boolean
     */
    private boolean joinGroup(long groupId, long userId) {
        boolean retVal = false;
        try {
            long[] groupIds = new long[1];
            groupIds[0] = groupId;
            GroupLocalServiceUtil.addUserGroups(userId, groupIds);
            retVal = true;
        } catch (Exception e) {
            LOGGER.error("There was an exception thrown when adding the user with id: " + userId + " to group id: " + groupId + ", exception: " + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal;
    }

    /**
     * This function will retrieve some groups that the
     * user has not joined already.
     * @param limit
     * @return retVal the json representation of the groups
     */
    private String getSuggestedGroups(int limit, long userId) {
        StringBuilder retVal = new StringBuilder("[");
        try {
            // first get all the user's groups
            List<Group> userGroups = GroupLocalServiceUtil.getUserGroups(userId);
            // build a query to grab all the open groups
            DynamicQuery groupQuery = DynamicQueryFactoryUtil.forClass(Group.class, PortalClassLoaderUtil.getClassLoader())
                    .add(PropertyFactoryUtil.forName("type").eq(GroupConstants.TYPE_COMMUNITY_OPEN));

            @SuppressWarnings("unchecked")
            List<Group> allGroups = GroupLocalServiceUtil.dynamicQuery(groupQuery);

            /* now iterate over the all groups and filter out the ones that the
             * current user belongs to.
             */
            boolean firstIteration = true;
            for (Group group : allGroups)  {
                if(userGroups.contains(group)) {
                    continue;
                }
                if(firstIteration) {
                    firstIteration = false;
                } else {
                    retVal.append(",");
                }
                retVal.append("{");
                retVal.append("\"name\":\""+group.getName()+"\",");
                retVal.append("\"description\":\""+group.getDescription()+ "\",");
                retVal.append("\"groupId\":\""+group.getGroupId()+"\"}");
            }

            retVal.append("]");
        } catch (Exception e) {
            LOGGER.error("There was a problem retrieving the groups for user id " + userId + ": " + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal.toString();
    }
}