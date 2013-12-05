package org.ieeecs.csdl.presentation.controller;


import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieeecs.csdl.util.DigitalLibraryUtil;

import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.ParamUtil;


import org.springframework.web.portlet.ModelAndView;


public class CSDLPattern5Controller extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();


		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			DigitalLibraryUtil.putPortletPreferencesIntoModel(prefs, model);

			// -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only this user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this admin user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", DigitalLibraryUtil.USERID);
			String currentMode = prefs.getValue("portletMode", DigitalLibraryUtil.MODE);

			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && currentMode.trim().equalsIgnoreCase(DigitalLibraryUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}

			String pubType       = ParamUtil.getStringParameter(renderRequest, prefs.getValue("pubType", DigitalLibraryUtil.PUBTYPE));
			String idPrefix      = ParamUtil.getStringParameter(renderRequest, prefs.getValue("idPrefix", DigitalLibraryUtil.IDPREFIX));
			String year     	 = ParamUtil.getStringParameter(renderRequest, prefs.getValue("year", DigitalLibraryUtil.YEAR));
			String catalogNumber = ParamUtil.getStringParameter(renderRequest, prefs.getValue("catalogNumber", DigitalLibraryUtil.CATALOGNUMBER));
			String volumeNumber  = ParamUtil.getStringParameter(renderRequest, prefs.getValue("volumeNumber", DigitalLibraryUtil.VOLUMENUMBER));
			String issueNumber   = ParamUtil.getStringParameter(renderRequest, prefs.getValue("issueNumber", DigitalLibraryUtil.ISSUENUMBER));
			String fileName      = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileName", DigitalLibraryUtil.FILENAME));
			String fileDetail    = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileDetail", DigitalLibraryUtil.FILEDETAIL));
			String fileType      = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileType", DigitalLibraryUtil.FILETYPE));			

		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("Home", model);

		return modelAndView;
	}

}