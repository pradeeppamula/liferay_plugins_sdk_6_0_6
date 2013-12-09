package org.ieeecs.communities.util;

import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

import org.ieeecs.communties.bean.QuestionBean;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portlet.polls.model.PollsChoice;
import com.liferay.portlet.polls.model.PollsQuestion;
import com.liferay.portlet.polls.model.PollsVote;
import com.liferay.portlet.polls.service.PollsVoteLocalServiceUtil;

public class CommunityPollUtil {

	// define the community poll configuration defaults
	public static final String CONFIG 					= "CONFIG";
	public static final String VOTE 					= "VOTE";
	public static final String MODE   					= "DEACTIVATED";
	public static final String USERID 					= "";
	public static final String SHOWINTRO 				= "ON";
	public static final String PORTLETBORDERCOLORTOP    = "CCCCCC";
	public static final String PORTLETBORDERCOLORRIGHT  = "CCCCCC";
	public static final String PORTLETBORDERCOLORBOTTOM = "CCCCCC";
	public static final String PORTLETBORDERCOLORLEFT   = "CCCCCC";
    public static final String POLL_FONT_COLOR       = "444444";
	public static final String PORTLETBORDERPIXELTOP    = "1";
	public static final String PORTLETBORDERPIXELRIGHT  = "1";
	public static final String PORTLETBORDERPIXELBOTTOM = "1";
	public static final String PORTLETBORDERPIXELLEFT   = "1";
	public static final String PORTLETBACKGROUNDCOLOR   = "CCCCCC";
	public static final String PORTLETTOPLEFTRADIUS     = "0";
	public static final String PORTLETBOTTOMLEFTRADIUS  = "0";
	public static final String PORTLETTOPRIGHTRADIUS    = "0";
	public static final String PORTLETBOTTOMRIGHTRADIUS = "0";
	public static final String INNERMARGINS        		= "20px";
	public static final String DEFAULTCATEGORYNAME 		= "Display";
	public static final String SHOW_VOTE_TOTAL 			= "TRUE";
	public static final String CAN_VOTE					= "FALSE";
	public static final String POLL_BAR_COLOR			= "5BC0DE";
	public static final String POLL_FONT_SIZE 			= "14";
	public static final String POLL_QUESTION_SIZE 		= "16";
	public static final String MAX_NUM_OF_CHOICES		= "25";
	
	/*
	 * This variable is needed to maintain the Liferay auto name property 
	 * indexing that occurs when a new choice is create through the control
	 * panel.
	 */
	public static final String[] choiceNames = {"a", "b", "c", "d", "e", "f", "g", "h", "i", 
		"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", 
		"{", "|", "}", "~"};
	
	/*
	 * NOTE: This property will determine the number of votes per user on 
	 * any given poll.  As of now (06.26.13), Liferay only allows
	 * one vote per userid.  For the future we need to look into
	 * if there is any way to configure this with Liferay.
	 */
	public static final int MAX_NUM_OF_VOTES_PER_USER   = 1;

	/**
	 * This function will add the necessary preferences to the 
	 * model for the View.
	 * @param prefs
	 * @param model
	 */
	public static void putPortletPreferencesIntoModel(PortletPreferences prefs, Map<String,Object> model) {
		try {
			model.put("portletMode", prefs.getValue("portletMode", MODE));
			model.put("modifiedByUserId", prefs.getValue("modifiedByUserId", USERID));
			model.put("showIntro", prefs.getValue("showIntro", SHOWINTRO));
			model.put("portletBorderColorTop", prefs.getValue("portletBorderColorTop", PORTLETBORDERCOLORTOP));
			model.put("portletBorderColorRight", prefs.getValue("portletBorderColorRight", PORTLETBORDERCOLORRIGHT));
			model.put("portletBorderColorBottom", prefs.getValue("portletBorderColorBottom", PORTLETBORDERCOLORBOTTOM));
			model.put("portletBorderColorLeft", prefs.getValue("portletBorderColorLeft", PORTLETBORDERCOLORLEFT));
			model.put("portletBorderPixelTop", prefs.getValue("portletBorderPixelTop", PORTLETBORDERPIXELTOP));
			model.put("portletBorderPixelRight", prefs.getValue("portletBorderPixelRight", PORTLETBORDERPIXELRIGHT));
			model.put("portletBorderPixelBottom", prefs.getValue("portletBorderPixelBottom", PORTLETBORDERPIXELBOTTOM));
			model.put("portletBorderPixelLeft", prefs.getValue("portletBorderPixelLeft", PORTLETBORDERPIXELLEFT));
			model.put("portletBackgroundColor", prefs.getValue("portletBackgroundColor", PORTLETBACKGROUNDCOLOR));
			model.put("portletTopLeftRadius", prefs.getValue("portletTopLeftRadius", PORTLETTOPLEFTRADIUS));
			model.put("portletBottomLeftRadius", prefs.getValue("portletBottomLeftRadius", PORTLETBOTTOMLEFTRADIUS));
			model.put("portletTopRightRadius", prefs.getValue("portletTopRightRadius", PORTLETTOPRIGHTRADIUS));
			model.put("portletBottomRightRadius", prefs.getValue("portletBottomRightRadius", PORTLETBOTTOMRIGHTRADIUS));
			model.put("innerMargins", prefs.getValue("innerMargins", INNERMARGINS));
			model.put("defaultCategoryName", prefs.getValue("defaultCategoryName", DEFAULTCATEGORYNAME));
			model.put("showVoteTotal", prefs.getValue("showVoteTotal", SHOW_VOTE_TOTAL));
			model.put("pollBarColor", prefs.getValue("pollBarColor", POLL_BAR_COLOR));
			model.put("pollFontSize", prefs.getValue("pollFontSize", POLL_FONT_SIZE));
			model.put("pollQuestionSize", prefs.getValue("pollQuestionSize", POLL_QUESTION_SIZE));
			model.put("maxNumberOfChoices", prefs.getValue("maxNumberOfChoices", MAX_NUM_OF_CHOICES));
            model.put("pollFontColor", prefs.getValue("pollFontColor", POLL_FONT_COLOR));
		} catch (Exception e) {
			// TODO: logging?
			e.printStackTrace();
		}
	}
	
	/**
	 * This helper method will parse out the content based on the passed in 
	 * node name from the xml parameter.
	 * @param modelXML
	 * @param uniqueNodeName
	 * @return retVal
	 * @throws Exception
	 */
	public static String parseContentFromXML(String modelXML, String uniqueNodeName) throws Exception {
		String retVal = "";
		try {
			// first check to see if the string is actually xml
			if(stringIsXML(modelXML)) {
				SAXBuilder parser = new SAXBuilder();
				Document document = parser.build(new StringReader(modelXML));
				XPath contentXPath = XPath.newInstance("//"+uniqueNodeName);
				List<?> contentList = contentXPath.selectNodes(document);					
				if ((null != contentList) && (contentList.size() > 0)) {
					Element contentElement = (Element)contentList.get(0);
					retVal = contentElement.getText();
				}	
			} else { // return back the original string
				retVal = modelXML;
			}
		} catch (Exception e) {
			throw new Exception("There was an exception when parsing content from xml: " + e);
		}
		return retVal;
	}	
	
	/**
	 * Helper method to determine if the string is xml
	 * @param xml
	 * @return boolean
	 */
	public static Boolean stringIsXML(String xml) {
		Boolean retVal = false;
		if(xml != null) {
			retVal = xml.toLowerCase().contains("<?xml");
		}
		return retVal;
	}
	
	/**
	 * Helper method that will determine if the user
	 * can vote on the poll referenced by the questionId parameter.
	 * @param poll
	 * @param userId
	 * @return Boolean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Boolean userCanVote(QuestionBean poll, String userId) throws Exception {
		Boolean retVal = false;
		try {
			Date expiration = poll.getExpirationDate();
            // grab the number of votes that the user has for the passed in question
            DynamicQuery voteQuery = DynamicQueryFactoryUtil.forClass(PollsVote.class, PortalClassLoaderUtil.getClassLoader())
                    .add(PropertyFactoryUtil.forName("questionId").eq( Long.parseLong(poll.getQuestionId()) ))
                    .add(PropertyFactoryUtil.forName("userId").eq( Long.parseLong(userId) ));

            List<PollsVote> votes = PollsVoteLocalServiceUtil.dynamicQuery(voteQuery);
            /**
             *  if the poll is expired, the user cannot vote.
             *  If the expiration date of the poll is null,
             *  then that means it does not expire
             */
            if (expiration == null || expiration.after(new Date())) {
                if(votes != null) {
                    retVal = votes.size() < MAX_NUM_OF_VOTES_PER_USER;
                }
            }
		} catch(Exception e) {
			throw new Exception("There was an exception when determining if the user can vote: " + e);
		}
		return retVal;
	}
	
	/**
	 * Since we need to maintain the way Liferay generates order PollsChoice
	 * names, we will have this helper function to manually get the next name
	 * for the new PollsChoice.
	 * @param question
	 * @return String
	 */
	public static String getNextChoiceName(PollsQuestion question) {
		String retVal = "";
		if(question != null) {
			try {
				List<PollsChoice> choices = question.getChoices();
			
				// grab the newest choice, and check its name
				PollsChoice newestChoice = choices.get(choices.size()-1);
				
				// determine what the next name will be for the new choice
				String currName = newestChoice.getName();
				
				// iterate over the list of possible choice names, to find the match
				int max = Integer.parseInt(MAX_NUM_OF_CHOICES);
				for(int idx=0; idx < max; idx++) {
					if(choiceNames[idx].equalsIgnoreCase(currName)) {
						retVal = choiceNames[idx+1];
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}
}
