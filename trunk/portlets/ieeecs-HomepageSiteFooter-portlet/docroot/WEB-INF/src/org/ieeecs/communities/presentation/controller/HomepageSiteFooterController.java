/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created July 11, 2013
 * @description This class will ..
 */
package org.ieeecs.communities.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.ieee.common.presentation.controller.BaseController;
import org.ieeecs.communities.util.HomepageSiteFooterUtil;
import org.springframework.web.portlet.ModelAndView;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;


public class HomepageSiteFooterController extends BaseController  {
    static final Logger LOGGER = Logger.getLogger(HomepageSiteFooterController.class);
	/**
	 * This method is called before the view of the portlet is rendered.  It will 
	 * perform any necessary setup processes.
	 * @param renderRequest
	 * @param renderResponse
	 */
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
		ModelAndView modelAndView = null;
		ThemeDisplay themeDisplay = null;
		PortletPreferences prefs = null;
		boolean canInlineEdit = false;
		String groupId    = "";
		boolean isSignedIn = false;
		
		try {
			// first grab the theme display for the portlet			
			themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			// set the group id
			groupId = Long.toString(themeDisplay.getScopeGroupId());
			// grab the preferences from the request
			prefs = renderRequest.getPreferences();
			// set if the user is signed in or not
			isSignedIn      = themeDisplay.isSignedIn();
			// add the default preferences to the model used in the view
			HomepageSiteFooterUtil.putPortletPreferencesIntoModel(prefs, model);
			// grab the instance id of this portlet
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

			// -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only the user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this current "owner" user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepageSiteFooterUtil.USERID);
			String currentMode = prefs.getValue("portletMode", HomepageSiteFooterUtil.MODE);

			// if the current user is the modifying user, then put the model in "preview" mode
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && 
					currentMode.trim().equalsIgnoreCase(HomepageSiteFooterUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}
			// -----------------------------------------------------------------------------------------------------------------
			// This will be moved in the next few versions to the "base" controller class.
			// -----------------------------------------------------------------------------------------------------------------
			User currentUser        = themeDisplay.getUser();
			
			boolean isMemberOfGroup = UserLocalServiceUtil.hasGroupUser(new Long(groupId), currentUser.getUserId());			
			boolean isLiferayAdmin  = renderRequest.isUserInRole("Administrator") ? true : false;
			
			if ( isSignedIn && isMemberOfGroup && !isLiferayAdmin ) {
				List<Role> userGroupRoles = RoleLocalServiceUtil.getUserGroupRoles(currentUser.getUserId(), new Long(groupId));
				if ( null != userGroupRoles && userGroupRoles.size() > 0 ) {
					for ( int index = 0; index < userGroupRoles.size(); index++ ) {
						Role currentRole = userGroupRoles.get(index);
						String currentRoleName = currentRole.getName();								
						if ( "Community Owner".equals(currentRoleName) || 
							 "Community Administrator".equals(currentRoleName) || 
							 "Community ControlPanel".equals(currentRoleName) || 
							 "Community Editor".equals(currentRoleName) ) {
							canInlineEdit = true;									
						}	
					}	
				}
					
			}
			if ( isLiferayAdmin ) {
				canInlineEdit = true;
			}
		} catch (Exception e) {
			// TODO: gracefully handle exception and put on model
			model.put("error", "A problem has occurred.  Please reload the page or contact help@computer.org.");
			// TODO: log stacktrace
            LOGGER.error("A problem occurred when rendering the portlet: "  + ExceptionUtils.getRootCauseMessage(e));
		}

		// create the model for the View and add the model attributes to it
		model.put("id", instanceId);
		model.put("isSignedIn", isSignedIn);
		modelAndView = new ModelAndView("Home", model);
		return modelAndView;
	}
}
