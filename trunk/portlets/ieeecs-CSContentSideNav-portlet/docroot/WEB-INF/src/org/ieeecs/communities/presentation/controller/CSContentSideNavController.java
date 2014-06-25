/**
 * @copyright 2014 IEEE
 * @package org.ieeecs.communities.bean
 * @created June 18, 2014
 * @description This class will handle all the rendering, and requests
 * for the side navigation portlet.
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.NavItem;
import com.liferay.portal.theme.RequestVars;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.ieee.common.presentation.controller.BaseController;
import org.ieeecs.communities.util.CSContentSideNavUtil;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CSContentSideNavController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(CSContentSideNavController.class);
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
            CSContentSideNavUtil.putPortletPreferencesIntoModel(prefs, model);

            // build and set the links for this page
            model.put("links", this.buildLinksJson(renderRequest, prefs, themeDisplay));

            // -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only this user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this admin user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
            User currentUser = themeDisplay.getUser();
            String currentUserId = new Long(currentUser.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", CSContentSideNavUtil.USERID);
			String currentMode = prefs.getValue("portletMode", CSContentSideNavUtil.MODE);
            // set the group id
            groupId = Long.toString(themeDisplay.getScopeGroupId());
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && currentMode.trim().equalsIgnoreCase(CSContentSideNavUtil.MODE) ) {
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
            if (CSContentSideNavUtil.SAVE_CONFIG.equalsIgnoreCase(requestType)) {
                // update the data
                if(CSContentSideNavUtil.updatePortletData(request, modifiedByUserId, instanceId)) {
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
     * Atomic helper function that will build up the links for the
     * side nav based on the current community and current page
     * @param renderRequest
     * @param prefs
     * @param themeDisplay
     * @return String
     */
    private String buildLinksJson(RenderRequest renderRequest, PortletPreferences prefs,  ThemeDisplay themeDisplay) {
        StringBuilder linksJSON = new StringBuilder("[");
        try {
            // get the current community page
            Layout layout = themeDisplay.getLayout();

            // get all the community pages
            List<Layout> layouts = themeDisplay.getLayouts();
            // get active page name
            String title = layout.getName(themeDisplay.getLocale()).toLowerCase();
            RequestVars requestVars = new RequestVars(PortalUtil.getHttpServletRequest(renderRequest), themeDisplay, layout.getAncestorPlid(), layout.getAncestorLayoutId());

            // determine if we are not showing all the links for the community
            String showAllCommunityLinks = prefs.getValue("showAllCommunityLinks", CSContentSideNavUtil.SHOW_ALL_COMMUNITY_LINKS);
            if(!"yes".equalsIgnoreCase(showAllCommunityLinks)) {
                layouts = this.filterPages(layouts, layout);
            }

            // now retrieve all the navigation links for the list of pages in the community
            List<NavItem> topNavItems = NavItem.fromLayouts(requestVars, layouts);
            String showChildLinks = prefs.getValue("showChildLinks", CSContentSideNavUtil.SHOW_CHILD_LINKS);

            boolean firstItem = true;
            // iterate over the top level pages for the current community?
            for (NavItem topNavItem : topNavItems) {
                if (topNavItem.hasChildren()) {
                    // now build up all the child links
                    for (NavItem subSectionItem : topNavItem.getChildren()) {
                        if(firstItem) {
                            firstItem = false;
                        } else {
                            linksJSON.append(",");
                        }
                        linksJSON.append("{\"title\":\"");
                        linksJSON.append(subSectionItem.getName());
                        linksJSON.append("\",\"url\":\"");
                        linksJSON.append(subSectionItem.getURL());
                        // check to see if this is the active page so we can set the style
                        if(title.equalsIgnoreCase(subSectionItem.getName())) {
                            linksJSON.append("\",\"active\": true");
                        } else {
                            linksJSON.append("\",\"active\": false");
                        }
                        // add the sub section item to the json
                        if (showChildLinks != null && "yes".equalsIgnoreCase(showChildLinks) && subSectionItem.hasChildren()) {
                            // build the child links up
                            linksJSON = this.buildChildLinks(subSectionItem, title, linksJSON);
                        }
                        linksJSON.append("}");
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("There was a problem building the links JSON: " + ExceptionUtils.getRootCauseMessage(e));
            linksJSON = new StringBuilder("[");
        }
        linksJSON.append("]");
        return linksJSON.toString();
    }

    /**
     * Helper function that will add any child links to the parent JSON
     *
     * @param subSectionItem
     * @param title
     * @param linksJSON
     * @return StringBuilder
     * @throws Exception
     */
    private StringBuilder buildChildLinks(NavItem subSectionItem, String title, StringBuilder linksJSON) throws Exception {
        boolean firstChildItem = true;
        linksJSON.append(",\"childLinks\": [");
        for (NavItem subSectionItemChild : subSectionItem.getChildren()) {
            if(firstChildItem) {
                firstChildItem = false;
            } else {
                linksJSON.append(",");
            }
            linksJSON.append("{\"title\":\"");
            linksJSON.append(subSectionItemChild.getName());
            linksJSON.append("\",\"url\":\"");
            linksJSON.append(subSectionItemChild.getURL());
            // check to see if this is the active page so we can set the style
            if(title.equalsIgnoreCase(subSectionItemChild.getName())) {
                linksJSON.append("\",\"active\": true");
            } else {
                linksJSON.append("\",\"active\": false");
            }
            linksJSON.append("}");
        }
        linksJSON.append("]");
        return linksJSON;
    }

    /**
     * Atomic helper function that will remove all the layouts (pages)
     * from the list that do not match the passed in layout (or in the case of
     * a descendant the parent layout id).  This
     * is if the user wants to only show sub links that are children of the
     * current page.
     *
     * @param layouts
     * @param layout
     * @return List<Layout>
     * @throws Exception
     */
    private List<Layout> filterPages(List<Layout> layouts, Layout layout) throws Exception {
        Layout retVal = null;
        for (Layout currLayout : layouts) {
            if (layout.getLayoutId() == currLayout.getLayoutId() ||
                    layout.getParentLayoutId() == currLayout.getLayoutId()) {
                retVal = currLayout;
                break;
            }
        }
        // if we found a match, remove everything and just add the matching layout
        if (retVal != null) {
            layouts.clear();
            layouts.add(retVal);
        }
        return layouts;
    }
}