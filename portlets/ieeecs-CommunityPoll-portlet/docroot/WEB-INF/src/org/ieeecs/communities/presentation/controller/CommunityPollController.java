package org.ieeecs.communities.presentation.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieee.common.presentation.controller.BaseController;
import org.ieeecs.communities.util.CommunityPollUtil;
import org.ieeecs.communties.bean.ChoiceBean;
import org.ieeecs.communties.bean.QuestionBean;
import org.springframework.web.portlet.ModelAndView;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.polls.model.PollsChoice;
import com.liferay.portlet.polls.model.PollsQuestion;
import com.liferay.portlet.polls.model.PollsVote;
import com.liferay.portlet.polls.service.PollsChoiceLocalServiceUtil;
import com.liferay.portlet.polls.service.PollsQuestionLocalServiceUtil;
import com.liferay.portlet.polls.service.PollsVoteLocalServiceUtil;


public class CommunityPollController extends BaseController {

	/**
	 * This method is called before the view of the poll portlet is rendered.  It will 
	 * perform any necessary setup processes.
	 * @param renderRequest
	 * @param renderResponse
	 */
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
		String groupId = "";
		QuestionBean currentPoll = new QuestionBean();
		List<QuestionBean> groupPolls = new ArrayList<QuestionBean>();
		boolean canInlineEdit = false;
		boolean userCanVote = false;
		
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
			
			// Grab the polls question based on the groupid
			List<PollsQuestion> questions = PollsQuestionLocalServiceUtil.getQuestions(new Long(groupId));
			
			// build up the list of question beans based on the group
			groupPolls = this.hydrateGroupPolls(questions, groupPolls);
			
			// sort the list of polls by their created date
			Collections.sort(groupPolls);
			
			// pull out the newest poll
			currentPoll = groupPolls.remove(0);
			
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
			
			// determine if the user can vote
			userCanVote = CommunityPollUtil.userCanVote(currentPoll, currentUserId);
		} catch (Exception e) {
			// TODO: logging?
			e.printStackTrace();
		}

		// add the necessary data to the model to be sent to the view
		model.put("id", instanceId);
		model.put("groupId", groupId);
		model.put("currentPoll", currentPoll);
		model.put("canInlineEdit", canInlineEdit);
		model.put("canVote", userCanVote);
		
		//model.put("archivePolls", groupPolls);

		// create the model for the View and add the model attributes to it
		ModelAndView modelAndView = new ModelAndView("Home", model);
		return modelAndView;
	}
	
	/**
	 * This method will handle any actions submitted on the view of the poll portlet.
	 * @param actionRequest
	 * @param actionResponse
	 */
	protected void handleActionRequestInternal(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portletId = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
			int cropHere = portletId.indexOf("_INSTANCE_");
			String instanceId = "_" + portletId.substring(cropHere+10);

			// The "source" attribute is not stored and is used solely to determine where the Request came from.
			// When a person opens up the Portlet "config/edit" window, then decides to go to the Liferay Control Panel, and then returns
			// to the Portlet "config/edit" screen, then this "handleActionRequestInternal" method is fired.   I guess the Liferay "Return to Full Page"
			// link within the Control Panel is an ActionURL instead of a RenderURL.   The "source" will be null, and therefore empty, if the
			// person returns to the "config/edit" screen from the Control Panel.
			String source = ParamUtil.getString(actionRequest, "source-"+instanceId, "");

			if (source.equalsIgnoreCase(CommunityPollUtil.VOTE)) {
				// grab the question id
				String questionId = ParamUtil.getString(actionRequest, "question-"+instanceId, "");
				String choiceId = ParamUtil.getString(actionRequest, "option-radios-"+instanceId, "");
				// determine which choice was selected
				createNewVote(themeDisplay, choiceId, questionId);
				// Grab and save any necessary preferences after the vote has been saved.
				PortletPreferences prefs = actionRequest.getPreferences();
				prefs.setValue("canVote", "FALSE");
				prefs.store();
			} else if (source.equalsIgnoreCase(CommunityPollUtil.CONFIG)) {
				// grab the question id
				String questionId = ParamUtil.getString(actionRequest, "question-"+instanceId, "");
				PollsQuestion question = PollsQuestionLocalServiceUtil.getPollsQuestion(Long.parseLong(questionId));
				// update the question in the db
				String questionText = ParamUtil.getString(actionRequest, "input-question-"+questionId+"-"+instanceId, "");
				question.setTitle(questionText);
				PollsQuestionLocalServiceUtil.updatePollsQuestion(question);
				
				// grab the choices and update/remove as necessary
				List<PollsChoice> choices = question.getChoices();
				// iterate over the question choices and save them if they are in the request
				for(int idx = 0; idx < choices.size(); idx++) {
					PollsChoice choice = choices.get(idx);
					// check to see if the choice is in the request
					String choiceText = ParamUtil.getString(actionRequest, "input-choice-"+String.valueOf(choice.getChoiceId())+"-"+instanceId, "");
					
					// the choice data was passed in the request so we know to update it
					if(choiceText != null && !"".equalsIgnoreCase(choiceText)) {
						choice.setDescription(choiceText);
						PollsChoiceLocalServiceUtil.updatePollsChoice(choice);
					} else {						
						// grab all the votes associated with this choice and delete them
						DynamicQuery voteQuery = DynamicQueryFactoryUtil.forClass(PollsVote.class, PortalClassLoaderUtil.getClassLoader())
								.add(PropertyFactoryUtil.forName("choiceId").eq(choice.getChoiceId()));			
						@SuppressWarnings("unchecked")
						List<PollsVote> votes = PollsVoteLocalServiceUtil.dynamicQuery(voteQuery);
						if(votes != null && votes.size() >0) {
							for(int x=0; x< votes.size(); x++) {
								PollsVoteLocalServiceUtil.deletePollsVote(votes.get(x));
							}
						}
						
						// we know the choice was removed, so let's remove it from the db
						PollsChoiceLocalServiceUtil.deletePollsChoice(choice);
					}
				}
				
				// create any new choices for question in db
				boolean hasNewChoices = true;
				int x = 1;
				do {
					// check to see if a new choice is in the request
					String choiceText = ParamUtil.getString(actionRequest, "input-choice-"+String.valueOf(x)+"-"+instanceId, "");
					if ("".equalsIgnoreCase(choiceText)) {
						hasNewChoices = false;
					} else {
						// create a new choice for this question
						createNewChoice(choiceText, question);
					}
					x++;
				} while (hasNewChoices);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This helper method will create a new choice for the poll in the db
	 * @param choiceText
	 * @param questionId
	 * @throws Exception
	 */
	private void createNewChoice(String choiceText, PollsQuestion question) throws Exception {
		try {
			long nextChoiceId = CounterLocalServiceUtil.increment();
			// create the poll choice and save into the db
		    PollsChoice choice = PollsChoiceLocalServiceUtil.createPollsChoice(nextChoiceId);
		    choice.setQuestionId(question.getQuestionId());
		    choice.setName(CommunityPollUtil.getNextChoiceName(question));
		    choice.setDescription(choiceText);
		    PollsChoiceLocalServiceUtil.updatePollsChoice(choice);
		} catch (Exception e) {
			throw new Exception("There was a problem creating a poll choice: " + e);
		}
	}
	
	/**
	 * This method will simply create a new vote based on the user's choice 
	 * @param themeDisplay
	 * @param choiceId
	 * @param questionId
	 * @throws Exception
	 */
	private void createNewVote(ThemeDisplay themeDisplay, String choiceId, String questionId) throws Exception {
		try {
			long nextVoteId = CounterLocalServiceUtil.increment();
			// create the poll vote and save into the db
		    PollsVote vote = PollsVoteLocalServiceUtil.createPollsVote(nextVoteId);
		    vote.setChoiceId(Long.parseLong(choiceId));
		    vote.setQuestionId(Long.parseLong(questionId));
		    vote.setUserId( themeDisplay.getUserId() );
		    vote.setVoteDate( new Date() );
			PollsVoteLocalServiceUtil.updatePollsVote(vote);
		} catch (Exception e) {
			throw new Exception("There was a problem creating a poll vote: " + e);
		}
	}
			
	
	/**
	 * This method will take the data from the LifeRay specific poll models, and 
	 * put their data into the custom POJOs.  This will make it easier to access the
	 * poll related data on the front end using JSTL.
	 * @param questions
	 * @param archivePolls
	 * @return List<QuestionBean>
	 */
	private List<QuestionBean> hydrateGroupPolls(List<PollsQuestion> questions, List<QuestionBean> groupPolls) {
		try {
			// iterate over the questions building question beans for the View
			for(int idx = 0; idx < questions.size(); idx++) {
				PollsQuestion currPollsQuestion = questions.get(idx);
				// build up the question bean
				QuestionBean currQuestionBean = new QuestionBean();
				currQuestionBean.setQuestionId(String.valueOf(currPollsQuestion.getQuestionId()));
				currQuestionBean.setQuestion(CommunityPollUtil.parseContentFromXML(currPollsQuestion.getTitle(), "Title"));
				currQuestionBean.setNumberOfVotes(currPollsQuestion.getVotesCount());
				currQuestionBean.setCreatedDate(currPollsQuestion.getCreateDate());
				currQuestionBean.setExpirationDate(currPollsQuestion.getExpirationDate());
				List<PollsChoice> currPollsChoices = currPollsQuestion.getChoices();
				// build up the choices 
				for(int y = 0; y < currPollsChoices.size(); y++ ) {
					PollsChoice currChoice = currPollsChoices.get(y);
					ChoiceBean currChoiceBean = new ChoiceBean();
					currChoiceBean.setChoiceId(String.valueOf(currChoice.getChoiceId()));
					currChoiceBean.setQuestionId(String.valueOf(currPollsQuestion.getQuestionId()));
					currChoiceBean.setDescription(CommunityPollUtil.parseContentFromXML(currChoice.getDescription(), "Description"));
					currChoiceBean.setNumberOfVotes(currChoice.getVotesCount());
					// now add it to the list of the question bean's choices
					currQuestionBean.getChoices().add(currChoiceBean);
				}
				// finally add the new question bean to the list of questions for the view
				groupPolls.add(currQuestionBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupPolls;
	}
}
