/**
 * @copyright 2014 IEEE
 * @package org.ieeecs.communities.bean
 * @created June 30, 2014
 * @description This class will handle all the rendering, and requests
 * for the breadcrumbs portlet.
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.ieee.common.presentation.controller.BaseController;
import org.ieeecs.communities.util.CSBreadcrumbsUtil;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSBreadcrumbsController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(CSBreadcrumbsController.class);
    private String instanceId = "";
    private String modifiedByUserId = "";

    /**
     * This function will render the view to show the portlet's content.  The function
     * will load up any necessary data and return it to the client via the model.
     * @param renderRequest
     * @param renderResponse
     * @return ModelAndView
     * @throws Exception
     */
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
        boolean canInlineEdit = false;
        String groupId = "";
        String community = "";

		try {
            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();
            community = themeDisplay.getScopeGroup().getFriendlyURL();
            community = (community != null && !"".equals(community)) ? community.replace("/","") : "";
            // grab any preferences off the request and store onto the model
            PortletPreferences prefs = renderRequest.getPreferences();
            CSBreadcrumbsUtil.putPortletPreferencesIntoModel(prefs, model);

            // build and set the breadcrumbs for this page
            model.put("breadcrumbs", this.buildBreadcrumbsJson(renderRequest, themeDisplay));

            // -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only this user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this admin user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
            User currentUser = themeDisplay.getUser();
            String currentUserId = new Long(currentUser.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", CSBreadcrumbsUtil.USERID);
			String currentMode = prefs.getValue("portletMode", CSBreadcrumbsUtil.MODE);
            // set the group id
            groupId = Long.toString(themeDisplay.getScopeGroupId());
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && currentMode.trim().equalsIgnoreCase(CSBreadcrumbsUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}

            boolean isMemberOfGroup = UserLocalServiceUtil.hasGroupUser(new Long(groupId), currentUser.getUserId());
            boolean isLiferayAdmin = renderRequest.isUserInRole("Administrator") ? true : false;

            if (themeDisplay.isSignedIn() && isMemberOfGroup && !isLiferayAdmin) {
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
            // gracefully handle exception and put on model
            model.put("error", "There was a problem rendering the page.  Please reload the page or contact help@computer.org.");
            LOGGER.error("A problem occurred when handling a request: " + ExceptionUtils.getRootCauseMessage(e));
		}

        model.put("community", community);
		model.put("id", instanceId);
        model.put("canInlineEdit", canInlineEdit);
		ModelAndView modelAndView = new ModelAndView("Home", model);
		return modelAndView;
	}

    /**
     * This method will handle any AJAX resource requests from the
     * Home view.  This will be mostly used for inline editing requests.
     * @param request
     * @param response
     * @return ModelAndView
     */
    @Override
    public ModelAndView handleResourceRequest(ResourceRequest request,
                                              ResourceResponse response) throws Exception {
        ModelAndView modelAndView = null;
        Map<String,Object> model = new HashMap<String,Object>();
        try{
            // grab the ThemeDisplay that contains all needed information on the user
            // first grab the theme display for the portlet
            if("".equals(this.instanceId)) {
                ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
                String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
                int cropHere = portletId.indexOf("_INSTANCE_");
                instanceId = "_" + portletId.substring(cropHere + 10);
                modifiedByUserId = new Long(themeDisplay.getUserId()).toString();
            }

            // grab the request type from the request
            String requestType = ParamUtil.getString(request, "requestType_" + instanceId, "");

            // determine which functionality to use based on the request type
            if (CSBreadcrumbsUtil.SAVE_CONFIG.equalsIgnoreCase(requestType)) {
                // update the data
                if(CSBreadcrumbsUtil.updatePortletData(request, modifiedByUserId, instanceId)) {
                    model.put("response", 200);
                } else {
                    model.put("response", 500);
                }
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json");
        } catch (Exception e) {
            LOGGER.error("A problem occurred when handling a resource request: "   + ExceptionUtils.getRootCauseMessage(e));
        }

        // specify which JSP to render the response to
        modelAndView = new ModelAndView("Response", model);
        return modelAndView;
    }

    /**
     * Atomic helper function that will build up the breadcrumb for the
     * side nav based on the current community and current page
     *
     * @param themeDisplay
     * @return String
     */
    private String buildBreadcrumbsJson(RenderRequest request, ThemeDisplay themeDisplay) {
        StringBuilder breadcrumbsJSON = new StringBuilder("[");
        try {
            // get the current community page and build its JSON
            Layout layout = themeDisplay.getLayout();
            breadcrumbsJSON.append("{\"title\":\"");
            breadcrumbsJSON.append(layout.getName(themeDisplay.getLocale()).toLowerCase());
            breadcrumbsJSON.append("\",\"url\":\"");
            breadcrumbsJSON.append(layout.getRegularURL(PortalUtil.getHttpServletRequest(request)));
            breadcrumbsJSON.append("\",\"active\": true");
            breadcrumbsJSON.append("}");

            // get the ancestors of the current page
            List<Layout> ancestors = layout.getAncestors();

            // build up the JSON for each ancestor layout
            for(Layout ancestorLayout: ancestors) {
                breadcrumbsJSON.append(",");
                breadcrumbsJSON.append("{\"title\":\"");
                breadcrumbsJSON.append(ancestorLayout.getName(themeDisplay.getLocale()).toLowerCase());
                breadcrumbsJSON.append("\",\"url\":\"");
                breadcrumbsJSON.append(ancestorLayout.getRegularURL(PortalUtil.getHttpServletRequest(request)));
                breadcrumbsJSON.append("\",\"active\": false");
                breadcrumbsJSON.append("}");
            }
        } catch (Exception e) {
            LOGGER.error("There was a problem building the breadcrumbs JSON: " + ExceptionUtils.getRootCauseMessage(e));
            breadcrumbsJSON = new StringBuilder("[");
        }
        breadcrumbsJSON.append("]");
        return breadcrumbsJSON.toString();
    }
}