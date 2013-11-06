/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created July 9, 2013
 * @description This class will represent the Choice Liferay Model
 */
package org.ieeecs.communities.presentation.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.util.PropsUtil;
import org.apache.log4j.Logger;
import org.ieee.common.presentation.controller.BaseController;
import org.ieeecs.communities.util.HomepageSuggestedArticleUtil;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;


public class HomepageSuggestedArticleController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(HomepageSuggestedArticleController.class);
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
		boolean isSignedIn = false;
		
		try {
			// first grab the theme display for the portlet			
			themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			// set if the user is signed in or not
			isSignedIn = themeDisplay.isSignedIn();
			// grab the preferences from the request
			prefs = renderRequest.getPreferences();
			// add the default preferences to the model used in the view
			HomepageSuggestedArticleUtil.putPortletPreferencesIntoModel(prefs, model);
			// grab the instance id of this portlet
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

			// -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only the user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this current "owner" user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepageSuggestedArticleUtil.USERID);
			String currentMode = prefs.getValue("portletMode", HomepageSuggestedArticleUtil.MODE);

			// if the current user is the modifying user, then put the model in "preview" mode
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && 
					currentMode.trim().equalsIgnoreCase(HomepageSuggestedArticleUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}

            // add the url for Elastic Search
            String esURL = PropsUtil.get(HomepageSuggestedArticleUtil.PROPERTY_ES_URL);
            model.put("elasticSearchURL", esURL);
		} catch (Exception e) {
			// gracefully handle exception and put on model
			model.put("error", "A problem has occurred.  Please reload the page or contact help@computer.org.");
            LOGGER.error("An error occurred when handling the render request.", e);
		}

		// create the model for the View and add the model attributes to it
		model.put("id", instanceId);
		model.put("isSignedIn", isSignedIn);
		modelAndView = new ModelAndView("Home", model);
		return modelAndView;
	}
	
	/**
	 * This method will handle all the ajax request to the portlet.
	 * @param request
	 * @param response
	 */
	@Override
	public ModelAndView handleResourceRequest(ResourceRequest request,
			ResourceResponse response) throws Exception {
		ModelAndView modelAndView = null;
		Map<String,Object> model = new HashMap<String,Object>();
		 try{                
		       
			// grab the ThemeDisplay that contains all needed information on the user
			String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
			int cropHere = portletId.indexOf("_INSTANCE_");
			String instanceId = "_" + portletId.substring(cropHere+10);

			// grab the request type from the request
			String requestType = ParamUtil.getString(request, "requestType_"+instanceId, "");

			// determine which functionality to use based on the request type
			if (requestType.equalsIgnoreCase(HomepageSuggestedArticleUtil.REQUEST_TYPE_LOAD_SUGGESTED_ARTICLE_DATA)) {
				// grab the number of articles that we should return
				String limit = ParamUtil.getString(request, "limit_"+instanceId, "");
				limit = (limit != null && !"".equalsIgnoreCase(limit)) ? limit : HomepageSuggestedArticleUtil.SUGGESTED_ARTICLE_DEFAULT_LIMIT;
				String json = this.getSuggestedArticles(Integer.parseInt(limit));
				model.put("response", json);
			}
			response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/json");
		} catch (Exception e) {
             LOGGER.error("An error occurred when handling the resource request.", e);
		}
		
		// specify which JSP to render the response to
		modelAndView = new ModelAndView("Response", model);
	    return modelAndView;
	}
	
	/**
	 * This function will retrieve the suggested articles based on the 
	 * type of articles the current user has in their bundle.  If they
	 * do not have any articles, we just grab some randoms.
	 * // TODO: pull list from CSDL service? 
	 * @param limit
	 * @return retVal the json representation of the articles
	 */
	private String getSuggestedArticles(int limit) {
		
		// NOTE: this is just temp dummy code
		Random generator2 = new Random( 19580427 );
		StringBuilder retVal = new StringBuilder("[");
		String title = "";
		String[] imageURLs = new String[10];
		imageURLs[0] = "https://si0.twimg.com/profile_images/2176846885/-5-1_normal.jpeg";
		imageURLs[1] = "https://si0.twimg.com/profile_images/2798485853/6430472036d3340610e6c78fd8004c6f_normal.png";
		imageURLs[2] = "http://www.bulldogz.com/chrishell/images/black-lace-2-a.jpg";
		imageURLs[3] = "http://images.fanpop.com/images/image_uploads/marvel-comics-marvel-comics-301822_1600_1200.jpg";
		imageURLs[4] = "http://images2.fanpop.com/images/photos/3900000/Captain-America-marvel-comics-3979574-1024-768.jpg";
		imageURLs[5] = "http://images.fanpop.com/images/image_uploads/Space-space-584336_1600_1200.jpg";
		imageURLs[6] = "http://younxt.files.wordpress.com/2010/04/fifa_world_cup_trophy_1_1600x1200.jpg";
		imageURLs[7] = "http://vafoodbanks.org/wp-content/uploads/2012/06/fresh_food.jpg";
		imageURLs[8] = "http://media.blizinski.pl/images/blog/code-color-1.png";
		imageURLs[9] = "http://media.bestofmicro.com/microsoft-xbox-one-e3-2013,M-X-388329-13.jpg";
		// for now just build up a fake list
		for(int idx = 0; idx < limit; idx++) {
			title = "The Title of the article will be here and we will make sure it is great. " + System.currentTimeMillis();
			if(idx>0) {
				retVal.append(",");
			}
			retVal.append("{");
			retVal.append("\"title\":\""+title+"\",");
			retVal.append("\"imageURL\":\""+imageURLs[generator2.nextInt( 10 )]+"\"}");
		}
		
		retVal.append("]");
		return retVal.toString();
	}
}
