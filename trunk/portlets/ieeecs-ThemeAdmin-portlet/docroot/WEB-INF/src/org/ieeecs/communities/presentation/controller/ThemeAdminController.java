/**
 * @copyright 2014 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created May 14, 2014
 * @description This class will load up the admin app, maintaining the correct
 * ACLs for the app usage.
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.ieee.common.presentation.controller.BaseController;
import org.springframework.web.portlet.ModelAndView;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;


public class ThemeAdminController extends BaseController {
    static final Logger LOGGER = Logger.getLogger(ThemeAdminController.class);

    /**
	 * This method is called before the view of the portlet is rendered.  It will 
	 * perform any necessary setup processes.
	 * @param renderRequest
	 * @param renderResponse
	 */
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
        String groupId = "";
		ModelAndView modelAndView = null;
		ThemeDisplay themeDisplay = null;
        boolean hasAccess = false;
        boolean isAuthenticated = false;

		try {
			// first grab the theme display for the portlet			
			themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			// grab the instance id of this portlet
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

            // set the group id
            groupId = Long.toString(themeDisplay.getScopeGroupId());
            // -----------------------------------------------------------------------------------------------------------------
            // This will be moved in the next few versions to the "base" controller class.
            // -----------------------------------------------------------------------------------------------------------------
            User currentUser = themeDisplay.getUser();
            isAuthenticated = themeDisplay.isSignedIn();
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
                            hasAccess = true;
                        }
                    }
                }

            }

            if (isLiferayAdmin) {
                hasAccess = true;
            }
		} catch (Exception e) {
			model.put("error", "A problem has occurred.  Please reload the page or contact support@computer.org.");
            LOGGER.error("A problem occurred when rendering the portlet: " + ExceptionUtils.getRootCauseMessage(e));
		}

        model.put("hasAccess", hasAccess);
        model.put("isAuthenticated", isAuthenticated);
		// create the model for the View and add the model attributes to it
		modelAndView = new ModelAndView("Home", model);
		return modelAndView;
	}
}