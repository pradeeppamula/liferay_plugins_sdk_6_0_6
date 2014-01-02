/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created October 1, 2013
 * @description This class will function as the action handler for the About portlet.
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
import com.mongodb.MongoException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.ieee.common.presentation.controller.BaseController;
import org.ieeecs.communities.mongo.MongoConfigUtil;
import org.ieeecs.communities.mongo.MongoHandler;
import org.ieeecs.communities.util.HomepageAboutUtil;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomepageAboutController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(HomepageAboutController.class);

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
			HomepageAboutUtil.putPortletPreferencesIntoModel(prefs, model);
			// grab the instance id of this portlet
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

			// -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only the user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this current "owner" user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", HomepageAboutUtil.USERID);
			String currentMode = prefs.getValue("portletMode", HomepageAboutUtil.MODE);

			// if the current user is the modifying user, then put the model in "preview" mode
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && 
					currentMode.trim().equalsIgnoreCase(HomepageAboutUtil.MODE) ) {
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
			model.put("error", "A problem has occurred.  Please reload the page or contact help@computer.org.");
            LOGGER.error("A problem occurred when rendering the portlet: "  + ExceptionUtils.getRootCauseMessage(e));
		}

		// create the model for the View and add the model attributes to it
		model.put("id", instanceId);
		model.put("isSignedIn", isSignedIn);
        model.put("canInlineEdit", canInlineEdit);
		modelAndView = new ModelAndView("Home", model);
		return modelAndView;
	}

    /**
     * This method will load the number of articles in the CSDL based on the
     * passed in start and end time on the request.
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
            // first grab the theme display for the portlet
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
            int cropHere = portletId.indexOf("_INSTANCE_");
            String instanceId = "_" + portletId.substring(cropHere+10);

            // grab the request type from the request
            String requestType = ParamUtil.getString(request, "requestType_" + instanceId, "");

            // determine which functionality to use based on the request type
            if ( HomepageAboutUtil.REQUEST_TYPE_USER_PURCHASE_DATA.equalsIgnoreCase(requestType)) {
                // grab the user's purchase data
                model.put("response",  this.getPurchaseData(themeDisplay.getUserId()));
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json");
        } catch (Exception e) {
            LOGGER.error("A problem occurred when handling a resource request: "  + ExceptionUtils.getRootCauseMessage(e));
        }

        // specify which JSP to render the response to
        modelAndView = new ModelAndView("Response", model);
        return modelAndView;
    }

    /**
     * This atomic helper method will retrieve the purchase data based on
     * the user's credentials.
     * TODO: Move this to common CSDL Handler
     *
     * @param userId
     * @return String
     * @throws Exception
     */
    private String getPurchaseData(long userId) throws Exception {
        String retVal = "{}";
        try {
            // build up the query document
            BasicDBObject query = new BasicDBObject("user_id", userId);

            // execute the query against the collection
            List<DBObject> results = MongoHandler.getInstance().find(MongoConfigUtil.Collection.PURCHASE, query);
            // if there is a result, grab the first one
            if(results != null && results.size() > 0) {
                retVal = results.get(0).toString();
            }
        } catch (MongoException me) {
            throw me;
        } catch (Exception e) {
            LOGGER.error("A problem occurred when retrieving the purchase data: "  + ExceptionUtils.getRootCauseMessage(e));
        }
        return retVal;
    }
}
