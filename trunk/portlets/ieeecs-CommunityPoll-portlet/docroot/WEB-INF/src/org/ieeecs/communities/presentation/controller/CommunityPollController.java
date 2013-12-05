package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import com.liferay.portlet.polls.model.PollsQuestion;
import com.liferay.portlet.polls.service.PollsQuestionLocalServiceUtil;

import java.util.*;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieeecs.communities.util.CommunityPollUtil;
import org.ieee.common.presentation.controller.BaseController;

import org.springframework.web.portlet.ModelAndView;


public class CommunityPollController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
//		String pollQuestion = "";
		String groupId = "";
		boolean canInlineEdit = false;

		try {
			
			// first grab the theme display for the portlet
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			// grab the preferences from the request
			PortletPreferences prefs = renderRequest.getPreferences();
			// add the preferences to the model used in the view
			CommunityPollUtil.putPortletPreferencesIntoModel(prefs, model);
			// grab the instance id 
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

			// -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only this user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this admin user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", CommunityPollUtil.USERID);
			String currentMode = prefs.getValue("portletMode", CommunityPollUtil.MODE);

			// if the current user is the modifying user, then put the model in "preview" mode
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && 
					currentMode.trim().equalsIgnoreCase(CommunityPollUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}
			
			// grab the group id so we can use it as necessary in the View
			groupId = Long.toString(themeDisplay.getScopeGroupId());
			
			// Grab the polls question based on the resource question id
			
			List<PollsQuestion> listOfPolls = PollsQuestionLocalServiceUtil.getQuestions(new Long(groupId));
			
			for (int index = 0; index < listOfPolls.size(); index++ ) {
				
				// Get choices per Poll Qa.
				
				// new Question object
				// populate new Q object with Choice
				 // stuff the Q object into an ArrayList
				
			}
			
			
			
			
//			PollsQuestion question = PollsQuestionLocalServiceUtil.getPollsQuestion(new Long(resourcePrimKey));
//			pollQuestion = (question.getTitle() != null) ? question.getTitle() : "Empty Title";
			
			// TODO: next we will grab the choices from the poll question
			
			// -----------------------------------------------------------------------------------------------------------------
			// This will be moved in the next few versions to the "base" controller class.
			// -----------------------------------------------------------------------------------------------------------------
			User currentUser        = themeDisplay.getUser();
			boolean isSignedIn      = themeDisplay.isSignedIn();
			boolean isMemberOfGroup = UserLocalServiceUtil.hasGroupUser(new Long(groupId), currentUser.getUserId());			
			boolean isLiferayAdmin  = renderRequest.isUserInRole("Administrator") ? true : false;
			
			// determine based on the roles, etc. if the user can inline edit the portal
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
			// if the user is a liferay admin they can edit the portal
			if ( isLiferayAdmin ) {
				canInlineEdit = true;
			}
		} catch (Exception e) {
			// TODO: logging?
			e.printStackTrace();
		}

		// add the necessary data to the model to be sent to the view
		model.put("id", instanceId);
		model.put("groupId", groupId);		
//		model.put("question", pollQuestion);
		model.put("canInlineEdit", canInlineEdit);

		// create the model for the View and add the model attributes to it
		ModelAndView modelAndView = new ModelAndView("Home", model);
		return modelAndView;
	}

}
