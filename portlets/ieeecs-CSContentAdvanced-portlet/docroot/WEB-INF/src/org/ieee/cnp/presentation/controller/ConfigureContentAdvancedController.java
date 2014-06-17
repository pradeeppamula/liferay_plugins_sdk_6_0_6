package org.ieee.cnp.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.*;

import org.ieee.cnp.util.ContentAdvancedUtil;
import org.ieee.common.presentation.controller.BaseController;

import org.springframework.web.portlet.ModelAndView;


public class ConfigureContentAdvancedController extends BaseController {

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
			String cssBlock = prefs.getValue("cssBlock", ContentAdvancedUtil.CSSBLOCK);
			String htmlBlock = prefs.getValue("htmlBlock", ContentAdvancedUtil.HTMLBLOCK);
			String jsBlockInternalPre = prefs.getValue("jsBlockInternalPre", ContentAdvancedUtil.JSBLOCKINTERNALPRE);
			String jsBlockInternalPost = prefs.getValue("jsBlockInternalPost", ContentAdvancedUtil.JSBLOCKINTERNALPOST);

			model.put("cssBlock", cssBlock);
			model.put("htmlBlock", htmlBlock);
			model.put("jsBlockInternalPre", jsBlockInternalPre);
			model.put("jsBlockInternalPost", jsBlockInternalPost);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("id", instanceId);
		model.put("fallbackJS", fallbackJS);
		ModelAndView modelAndView = new ModelAndView("Configure", model);

		return modelAndView;
	}

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
			String source = ParamUtil.getString(actionRequest, "source"+instanceId, "");

			if ( source.equalsIgnoreCase(ContentAdvancedUtil.CONFIG) ) {

				String portletMode = ParamUtil.getString(actionRequest, "portletMode"+instanceId, ContentAdvancedUtil.MODE);
				String modifiedByUserId = new Long(themeDisplay.getUserId()).toString();

				String cssBlock    = ParamUtil.getString(actionRequest, "cssBlock"+instanceId, ContentAdvancedUtil.CSSBLOCK);
				String htmlBlock   = ParamUtil.getString(actionRequest, "htmlBlock"+instanceId, ContentAdvancedUtil.HTMLBLOCK);
				String jsBlockInternalPre  = ParamUtil.getString(actionRequest, "jsBlockInternalPre"+instanceId, ContentAdvancedUtil.JSBLOCKINTERNALPRE);
				String jsBlockInternalPost = ParamUtil.getString(actionRequest, "jsBlockInternalPost"+instanceId, ContentAdvancedUtil.JSBLOCKINTERNALPOST);
				String jsBlockExternalPre  = ParamUtil.getString(actionRequest, "jsBlockExternalPre"+instanceId, ContentAdvancedUtil.JSBLOCKEXTERNALPRE);
				String jsBlockExternalPost = ParamUtil.getString(actionRequest, "jsBlockExternalPost"+instanceId, ContentAdvancedUtil.JSBLOCKEXTERNALPOST);
				
				String showIntro = ParamUtil.getString(actionRequest, "showIntro"+instanceId, ContentAdvancedUtil.SHOWINTRO);

				String portletBorderColorTop = ParamUtil.getString(actionRequest, "portletBorderColorTop"+instanceId, ContentAdvancedUtil.PORTLETBORDERCOLORTOP);
				String portletBorderColorRight = ParamUtil.getString(actionRequest, "portletBorderColorRight"+instanceId, ContentAdvancedUtil.PORTLETBORDERCOLORRIGHT);
				String portletBorderColorBottom = ParamUtil.getString(actionRequest, "portletBorderColorBottom"+instanceId, ContentAdvancedUtil.PORTLETBORDERCOLORBOTTOM);
				String portletBorderColorLeft = ParamUtil.getString(actionRequest, "portletBorderColorLeft"+instanceId, ContentAdvancedUtil.PORTLETBORDERCOLORLEFT);
				String portletBorderPixelTop = ParamUtil.getString(actionRequest, "portletBorderPixelTop"+instanceId, ContentAdvancedUtil.PORTLETBORDERPIXELTOP);
				String portletBorderPixelRight = ParamUtil.getString(actionRequest, "portletBorderPixelRight"+instanceId, ContentAdvancedUtil.PORTLETBORDERPIXELRIGHT);
				String portletBorderPixelBottom = ParamUtil.getString(actionRequest, "portletBorderPixelBottom"+instanceId, ContentAdvancedUtil.PORTLETBORDERPIXELBOTTOM);
				String portletBorderPixelLeft = ParamUtil.getString(actionRequest, "portletBorderPixelLeft"+instanceId, ContentAdvancedUtil.PORTLETBORDERPIXELLEFT);
				String portletBackgroundColor = ParamUtil.getString(actionRequest, "portletBackgroundColor"+instanceId, ContentAdvancedUtil.PORTLETBACKGROUNDCOLOR);
				String portletTopLeftRadius = ParamUtil.getString(actionRequest, "portletTopLeftRadius"+instanceId, ContentAdvancedUtil.PORTLETTOPLEFTRADIUS);
				String portletBottomLeftRadius = ParamUtil.getString(actionRequest, "portletBottomLeftRadius"+instanceId, ContentAdvancedUtil.PORTLETBOTTOMLEFTRADIUS);
				String portletTopRightRadius = ParamUtil.getString(actionRequest, "portletTopRightRadius"+instanceId, ContentAdvancedUtil.PORTLETTOPRIGHTRADIUS);
				String portletBottomRightRadius = ParamUtil.getString(actionRequest, "portletBottomRightRadius"+instanceId, ContentAdvancedUtil.PORTLETBOTTOMRIGHTRADIUS);
				String portletHeight = ParamUtil.getString(actionRequest, "portletHeight"+instanceId, ContentAdvancedUtil.PORTLETHEIGHT);

				PortletPreferences prefs = actionRequest.getPreferences();
				prefs.setValue("portletMode", portletMode);
				prefs.setValue("modifiedByUserId", modifiedByUserId);

				prefs.setValue("cssBlock", cssBlock);
				prefs.setValue("htmlBlock", htmlBlock);
				prefs.setValue("jsBlockInternalPre", jsBlockInternalPre);
				prefs.setValue("jsBlockInternalPost", jsBlockInternalPost);
				prefs.setValue("jsBlockExternalPre", jsBlockExternalPre);
				prefs.setValue("jsBlockExternalPost", jsBlockExternalPost);
				
				prefs.setValue("showIntro", showIntro.toUpperCase());

				prefs.setValue("portletBorderColorTop", portletBorderColorTop);
				prefs.setValue("portletBorderColorRight", portletBorderColorRight);
				prefs.setValue("portletBorderColorBottom", portletBorderColorBottom);
				prefs.setValue("portletBorderColorLeft", portletBorderColorLeft);
				prefs.setValue("portletBorderPixelTop", portletBorderPixelTop);
				prefs.setValue("portletBorderPixelRight", portletBorderPixelRight);
				prefs.setValue("portletBorderPixelBottom", portletBorderPixelBottom);
				prefs.setValue("portletBorderPixelLeft", portletBorderPixelLeft);
				prefs.setValue("portletBackgroundColor", portletBackgroundColor);
				prefs.setValue("portletTopLeftRadius", portletTopLeftRadius);
				prefs.setValue("portletBottomLeftRadius", portletBottomLeftRadius);
				prefs.setValue("portletTopRightRadius", portletTopRightRadius);
				prefs.setValue("portletBottomRightRadius", portletBottomRightRadius);
				prefs.setValue("portletHeight", portletHeight);

				prefs.store();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}