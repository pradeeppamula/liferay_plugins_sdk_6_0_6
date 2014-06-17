package org.ieee.cnp.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.ieee.cnp.util.CSContentAdvancedUtil;
import org.ieee.common.json.JSONArray;
import org.ieee.common.presentation.controller.BaseController;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CSContentAdvancedController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(CSContentAdvancedController.class);
    private String instanceId = "";

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
		boolean fallbackJS = true;
        boolean canInlineEdit = false;
        String groupId = "";

		try {
            ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
            instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();

            // grab any preferences off the request and store onto the model
            PortletPreferences prefs = renderRequest.getPreferences();
            CSContentAdvancedUtil.putPortletPreferencesIntoModel(prefs, model);
			
			// We assume that the theme where this portlet resides doesn't contain all of the required Javascript libraries.
			// If it finds that the "complianceVersion" value is set in the theme's "liferay-look-and-feel.xml" file, then it contains all needed libraries.
			String complianceVersion = themeDisplay.getTheme().getSetting("complianceVersion");
			if ( null != complianceVersion && !"".equals(complianceVersion) ) {
				fallbackJS = false;
			}

			// -----------------------------------------------------------------------------------------------------------------
			// The various blocks of code (CSS and Javascript) will be populated here, prepared for Ace editor viewing
			// -----------------------------------------------------------------------------------------------------------------
			String cssPref = prefs.getValue("cssBlock", CSContentAdvancedUtil.CSSBLOCK);
			JSONArray cssBlockJSONArray = new JSONArray(cssPref);
			String cssBlock = "";

			for ( int cssI = 0; cssI < cssBlockJSONArray.length(); cssI++ ) {
				String line = (String) cssBlockJSONArray.get(cssI);
				cssBlock = cssBlock + line + "\r\n";
			}

			String htmlPref = prefs.getValue("htmlBlock", CSContentAdvancedUtil.HTMLBLOCK);
			JSONArray htmlBlockJSONArray = new JSONArray(htmlPref);
			String htmlBlock = "";

			for ( int htmlI = 0; htmlI < htmlBlockJSONArray.length(); htmlI++ ) {
				String line = (String) htmlBlockJSONArray.get(htmlI);
				htmlBlock = htmlBlock + line + "\r\n";
			}

			String jsInternalPrePref = prefs.getValue("jsBlockInternalPre", CSContentAdvancedUtil.JSBLOCKINTERNALPRE);
			JSONArray jsBlockInternalPreJSONArray = new JSONArray(jsInternalPrePref);
			String jsBlockInternalPre = "";

			for ( int jsIPI = 0; jsIPI < jsBlockInternalPreJSONArray.length(); jsIPI++ ) {
				String line = (String) jsBlockInternalPreJSONArray.get(jsIPI);
				jsBlockInternalPre = jsBlockInternalPre + line + "\r\n";
			}

			String jsInternalPostPref = prefs.getValue("jsBlockInternalPost", CSContentAdvancedUtil.JSBLOCKINTERNALPOST);
			JSONArray jsBlockInternalPostJSONArray = new JSONArray(jsInternalPostPref);
			String jsBlockInternalPost = "";

			for ( int jsI = 0; jsI < jsBlockInternalPostJSONArray.length(); jsI++ ) {
				String line = (String) jsBlockInternalPostJSONArray.get(jsI);
				jsBlockInternalPost = jsBlockInternalPost + line + "\r\n";
			}

			model.put("cssBlock", cssBlock);
			model.put("htmlBlock", htmlBlock);
			model.put("jsBlockInternalPre", jsBlockInternalPre);
			model.put("jsBlockInternalPost", jsBlockInternalPost);

			// -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only this user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this admin user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
            User currentUser = themeDisplay.getUser();
            String currentUserId = new Long(currentUser.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", CSContentAdvancedUtil.USERID);
			String currentMode = prefs.getValue("portletMode", CSContentAdvancedUtil.MODE);
            // set the group id
            groupId = Long.toString(themeDisplay.getScopeGroupId());
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && currentMode.trim().equalsIgnoreCase(CSContentAdvancedUtil.MODE) ) {
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
            model.put("error", "There was a problem loading your account information.  Please reload the page or contact help@computer.org.");
            LOGGER.error("A problem occurred when handling a request: " + ExceptionUtils.getRootCauseMessage(e));
		}

		model.put("id", instanceId);
		model.put("fallbackJS", fallbackJS);
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
            }

            // grab the request type from the request
            String requestType = ParamUtil.getString(request, "requestType_" + instanceId, "");

            // determine which functionality to use based on the request type
            if (CSContentAdvancedUtil.SAVE_CONFIG.equalsIgnoreCase(requestType)) {

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
}