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


public class CSDLPattern2Controller extends BaseController {

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
			String fileType      = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileType", DigitalLibraryUtil.FILETYPE));
			
			PackageBean issueBean = new PackageBean();
			String proceedingUrlPrefix = "";
			List<ContentBean> tableOfContentsList = new ArrayList<ContentBean>();

			try {
				
				idPrefix = idPrefix.toLowerCase().trim();
				putBreadcrumbForProceedingsInfoIntoModel(model, 
														 prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH), idPrefix, 
														 prefs.getValue("csdlListPage", DigitalLibraryUtil.CSDLLISTPAGE), 
														 prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE), 
														 prefs.getValue("proceedingsUrlSegment", DigitalLibraryUtil.PROCEEDINGSURLSEGMENT));
				
				populateTableOfContentsList(tableOfContentsList, DigitalLibraryUtil.PROCEEDINGS, idPrefix, year, 
											catalogNumber + "/" + volumeNumber, 
											prefs.getValue("csdlToCPage", DigitalLibraryUtil.CSDLTOCPAGE));

				ContentBean cbWithIssueInfo = tableOfContentsList.get(0);
				if ( null != cbWithIssueInfo ) {
					issueBean = cbWithIssueInfo.getPackageBean();
				}
				
				proceedingUrlPrefix = prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH) + "/" + 
									  prefs.getValue("proceedingsUrlSegment", DigitalLibraryUtil.PROCEEDINGSURLSEGMENT) + "/" + 
									  idPrefix + "/" + year + "/" + catalogNumber + "/" + volumeNumber;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.put("searchDatabases",getSearchDatabases("proceedings"));
			model.put("issueBean", issueBean);
			model.put("tableOfContentsList", tableOfContentsList);
			model.put("idPrefix", idPrefix);
			model.put("proceedingUrlPrefix", proceedingUrlPrefix);
			model.put("totalTimeMS", getTotalTime(startTiming) + " ms");

		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("Pattern2", model);

		return modelAndView;
	}

}