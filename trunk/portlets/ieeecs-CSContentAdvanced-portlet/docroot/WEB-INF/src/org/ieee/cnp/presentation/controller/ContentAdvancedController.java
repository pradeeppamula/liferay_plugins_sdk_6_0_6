package org.ieee.cnp.presentation.controller;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieee.cnp.util.ContentAdvancedUtil;
import org.ieee.common.json.JSONArray;
import org.ieee.common.presentation.controller.BaseController;

import org.springframework.web.portlet.ModelAndView;


public class ContentAdvancedController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
		boolean fallbackJS = true;

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			ContentAdvancedUtil.putPortletPreferencesIntoModel(prefs, model);
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();
			
			// We assume that the theme where this portlet resides doesn't contain all of the required Javascript libraries.
			// If it finds that the "complianceVersion" value is set in the theme's "liferay-look-and-feel.xml" file, then it contains all needed libraries.
			String complianceVersion = themeDisplay.getTheme().getSetting("complianceVersion");
			if ( null != complianceVersion && !"".equals(complianceVersion) ) {
				fallbackJS = false;
			}

			// -----------------------------------------------------------------------------------------------------------------
			// The various blocks of code (CSS and Javascript) will be populated here, prepared for Ace editor viewing
			// -----------------------------------------------------------------------------------------------------------------
			String cssPref = prefs.getValue("cssBlock", ContentAdvancedUtil.CSSBLOCK);
			JSONArray cssBlockJSONArray = new JSONArray(cssPref);
			String cssBlock = "";

			for ( int cssI = 0; cssI < cssBlockJSONArray.length(); cssI++ ) {
				String line = (String) cssBlockJSONArray.get(cssI);
				cssBlock = cssBlock + line + "\r\n";
			}

			String htmlPref = prefs.getValue("htmlBlock", ContentAdvancedUtil.HTMLBLOCK);
			JSONArray htmlBlockJSONArray = new JSONArray(htmlPref);
			String htmlBlock = "";

			for ( int htmlI = 0; htmlI < htmlBlockJSONArray.length(); htmlI++ ) {
				String line = (String) htmlBlockJSONArray.get(htmlI);
				htmlBlock = htmlBlock + line + "\r\n";
			}

			String jsInternalPrePref = prefs.getValue("jsBlockInternalPre", ContentAdvancedUtil.JSBLOCKINTERNALPRE);
			JSONArray jsBlockInternalPreJSONArray = new JSONArray(jsInternalPrePref);
			String jsBlockInternalPre = "";

			for ( int jsIPI = 0; jsIPI < jsBlockInternalPreJSONArray.length(); jsIPI++ ) {
				String line = (String) jsBlockInternalPreJSONArray.get(jsIPI);
				jsBlockInternalPre = jsBlockInternalPre + line + "\r\n";
			}

			String jsInternalPostPref = prefs.getValue("jsBlockInternalPost", ContentAdvancedUtil.JSBLOCKINTERNALPOST);
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
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", ContentAdvancedUtil.USERID);
			String currentMode = prefs.getValue("portletMode", ContentAdvancedUtil.MODE);

			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && currentMode.trim().equalsIgnoreCase(ContentAdvancedUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("id", instanceId);
		model.put("fallbackJS", fallbackJS);
		ModelAndView modelAndView = new ModelAndView("Home", model);

		return modelAndView;
	}

}