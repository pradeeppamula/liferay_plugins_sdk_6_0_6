/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.presentation.controller
 * @created October 1, 2013
 * @description This class will function as the configuration handler for the About portlet.
 */
package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.*;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.ieeecs.communities.util.HomepageAboutUtil;
import org.ieee.common.presentation.controller.BaseController;

import org.springframework.web.portlet.ModelAndView;

public class ConfigureHomepageAboutController extends BaseController {
    static final Logger LOGGER = Logger.getLogger(ConfigureHomepageAboutController.class);
	/**
	 * This method is called with the view for this portlet is about to be rendered.  It will
	 * perform all of the necessary setup processes for the portlet.
	 * @param renderRequest
	 * @param renderResponse
	 */
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
		ThemeDisplay themeDisplay = null;
		PortletPreferences prefs = null;
		try {			
			// first grab the theme display for the portlet			
			themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			// grab the preferences from the request
			prefs = renderRequest.getPreferences();
			// add the default preferences to the model used in the view
			HomepageAboutUtil.putPortletPreferencesIntoModel(prefs, model);
			// grab the instance id of this portlet
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();
		} catch (Exception e) {
            LOGGER.error("A problem occurred when rendering the request: "  + ExceptionUtils.getRootCauseMessage(e));
		}

		model.put("id", instanceId);
		ModelAndView modelAndView = new ModelAndView("Configure", model);
		return modelAndView;
	}

	/**
	 * This method will handle the saving of the portlet preferences based on the user's 
	 * specifications.  
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
			String source = ParamUtil.getString(actionRequest, "source"+instanceId, "");

			if ( source.equalsIgnoreCase(HomepageAboutUtil.CONFIG) ) {

				String portletMode = ParamUtil.getString(actionRequest, "portletMode"+instanceId, HomepageAboutUtil.MODE);
				String modifiedByUserId = new Long(themeDisplay.getUserId()).toString();

				String portletBorderColorTop = ParamUtil.getString(actionRequest, "portletBorderColorTop"+instanceId, HomepageAboutUtil.PORTLETBORDERCOLORTOP);
				String portletBorderColorRight = ParamUtil.getString(actionRequest, "portletBorderColorRight"+instanceId, HomepageAboutUtil.PORTLETBORDERCOLORRIGHT);
				String portletBorderColorBottom = ParamUtil.getString(actionRequest, "portletBorderColorBottom"+instanceId, HomepageAboutUtil.PORTLETBORDERCOLORBOTTOM);
				String portletBorderColorLeft = ParamUtil.getString(actionRequest, "portletBorderColorLeft"+instanceId, HomepageAboutUtil.PORTLETBORDERCOLORLEFT);
				String portletBorderPixelTop = ParamUtil.getString(actionRequest, "portletBorderPixelTop"+instanceId, HomepageAboutUtil.PORTLETBORDERPIXELTOP);
				String portletBorderPixelRight = ParamUtil.getString(actionRequest, "portletBorderPixelRight"+instanceId, HomepageAboutUtil.PORTLETBORDERPIXELRIGHT);
				String portletBorderPixelBottom = ParamUtil.getString(actionRequest, "portletBorderPixelBottom"+instanceId, HomepageAboutUtil.PORTLETBORDERPIXELBOTTOM);
				String portletBorderPixelLeft = ParamUtil.getString(actionRequest, "portletBorderPixelLeft"+instanceId, HomepageAboutUtil.PORTLETBORDERPIXELLEFT);
				String portletBackgroundColor = ParamUtil.getString(actionRequest, "portletBackgroundColor"+instanceId, HomepageAboutUtil.PORTLETBACKGROUNDCOLOR);
				String portletTopLeftRadius = ParamUtil.getString(actionRequest, "portletTopLeftRadius"+instanceId, HomepageAboutUtil.PORTLETTOPLEFTRADIUS);
				String portletBottomLeftRadius = ParamUtil.getString(actionRequest, "portletBottomLeftRadius"+instanceId, HomepageAboutUtil.PORTLETBOTTOMLEFTRADIUS);
				String portletTopRightRadius = ParamUtil.getString(actionRequest, "portletTopRightRadius"+instanceId, HomepageAboutUtil.PORTLETTOPRIGHTRADIUS);
				String portletBottomRightRadius = ParamUtil.getString(actionRequest, "portletBottomRightRadius"+instanceId, HomepageAboutUtil.PORTLETBOTTOMRIGHTRADIUS);
				
				String innerMargins = ParamUtil.getString(actionRequest, "innerMargins"+instanceId, HomepageAboutUtil.INNERMARGINS);

				PortletPreferences prefs = actionRequest.getPreferences();
				prefs.setValue("portletMode", portletMode);
				prefs.setValue("modifiedByUserId", modifiedByUserId);

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
				prefs.setValue("innerMargins", innerMargins);
				prefs.store();
			}

		} catch (Exception e) {
            LOGGER.error("A problem occurred when saving the portlet preferences: "  + ExceptionUtils.getRootCauseMessage(e));
		}
	}
}
