package org.ieeecs.csdl.presentation.controller;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieeecs.csdl.bean.ContentBean;
import org.ieeecs.csdl.bean.PackageBean;
import org.ieeecs.csdl.util.DigitalLibraryUtil;

import org.ieee.common.util.ParamUtil;


import org.springframework.web.portlet.ModelAndView;


public class CSDLPattern3Controller extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();


		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			putPortletPreferencesIntoModel(prefs, model);			
			setPorletMode(themeDisplay, prefs, model);

			long startTiming = getTime();
	
			String idPrefix      = ParamUtil.getStringParameter(renderRequest, prefs.getValue("idPrefix", DigitalLibraryUtil.IDPREFIX));
			String year     	 = ParamUtil.getStringParameter(renderRequest, prefs.getValue("year", DigitalLibraryUtil.YEAR));
			String catalogNumber = ParamUtil.getStringParameter(renderRequest, prefs.getValue("catalogNumber", DigitalLibraryUtil.CATALOGNUMBER));
			String volumeNumber  = ParamUtil.getStringParameter(renderRequest, prefs.getValue("volumeNumber", DigitalLibraryUtil.VOLUMENUMBER));			
			String fileName      = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileName", DigitalLibraryUtil.FILENAME));
			String fileDetail    = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileDetail", DigitalLibraryUtil.FILEDETAIL));
			String fileType      = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileType", DigitalLibraryUtil.FILETYPE));			
			
			PackageBean issueBean = new PackageBean();
			ContentBean abstractContent = new ContentBean();
			
			try {	
				
				idPrefix = idPrefix.toLowerCase().trim();
				putBreadcrumbForProceedingsInfoIntoModel(model, 
														 prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH), idPrefix, 
														 prefs.getValue("csdlListPage", DigitalLibraryUtil.CSDLLISTPAGE), 
														 prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE), 
														 prefs.getValue("proceedingsUrlSegment", DigitalLibraryUtil.PROCEEDINGSURLSEGMENT));

				abstractContent = getAbstract("proceedings", idPrefix, year, catalogNumber+"/"+volumeNumber, fileName, fileDetail, fileType);
				issueBean = abstractContent.getPackageBean();
		
	
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.put("issueBean", issueBean);
			model.put("abstractContent", abstractContent);
			model.put("idPrefix", idPrefix);
			model.put("totalTimeMS", getTotalTime(startTiming) + " ms");

		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("Pattern3", model);

		return modelAndView;
	}
	
}