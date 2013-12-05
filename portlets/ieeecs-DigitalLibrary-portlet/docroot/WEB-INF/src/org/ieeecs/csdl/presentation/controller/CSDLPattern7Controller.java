package org.ieeecs.csdl.presentation.controller;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.*;

import org.ieeecs.csdl.bean.ContentBean;
import org.ieeecs.csdl.bean.IssueBean;
import org.ieeecs.csdl.bean.PackageBean;
import org.ieeecs.csdl.util.DigitalLibraryUtil;

import org.ieee.common.json.JSONObject;
import org.ieee.common.util.ParamUtil;

import org.springframework.web.portlet.ModelAndView;


public class CSDLPattern7Controller extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();		
		String outputJSP = "Pattern7";

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			putPortletPreferencesIntoModel(prefs, model);			
			setPorletMode(themeDisplay, prefs, model);
			
			String csdlBase = DigitalLibraryUtil.CSDLBASE;
			long startTiming = getTime();
			
			String pubType  = ParamUtil.getStringParameter(renderRequest, prefs.getValue("pubType", DigitalLibraryUtil.PUBTYPE));
			String idPrefix = ParamUtil.getStringParameter(renderRequest, prefs.getValue("idPrefix", DigitalLibraryUtil.IDPREFIX));
			String year     = ParamUtil.getStringParameter(renderRequest, prefs.getValue("year", DigitalLibraryUtil.YEAR));
			String fileName = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileName", DigitalLibraryUtil.FILENAME));
			String fileType = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileType", DigitalLibraryUtil.FILETYPE));
			
			Map<String,IssueBean> issueMap = new TreeMap<String,IssueBean>(Collections.reverseOrder());
			PackageBean issueBean = new PackageBean();
			List<ContentBean> tableOfContentsList = new ArrayList<ContentBean>();			

			try {
				
				JSONObject idPrefixDataJSON = jsonObjectFromDotNotation(new JSONObject(csdlBase), pubType + "." + idPrefix);					
				putBreadcrumbInfoIntoModel(model, idPrefixDataJSON, 
										   prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH), 
										   prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE), 
										   prefs.getValue("issueNumberLabel", DigitalLibraryUtil.ISSUENUMBERLABEL), 
										   pubType, idPrefix, year, "");
				
				if ( !prefs.getValue("prePrintsUrl", DigitalLibraryUtil.PREPRINTSURL).equalsIgnoreCase(year) && !prefs.getValue("rapidPostsUrl", DigitalLibraryUtil.RAPIDPOSTSURL).equalsIgnoreCase(year) ) {

					String productId = idPrefixDataJSON.getString("productId");					
					getIssues(new JSONObject(csdlBase), prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH), pubType, idPrefix, year, 
							  prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE), productId, issueMap);
					
				} else if ( prefs.getValue("prePrintsUrl", DigitalLibraryUtil.PREPRINTSURL).equalsIgnoreCase(year) ) {
					
					populateTableOfContentsList(tableOfContentsList, pubType, idPrefix, 
											    prefs.getValue("prePrintsYear", DigitalLibraryUtil.PREPRINTSYEAR), 
											    prefs.getValue("prePrintsIssueNumber", DigitalLibraryUtil.PREPRINTSISSUENUMBER), 
											    prefs.getValue("csdlToCPage", DigitalLibraryUtil.CSDLTOCPAGE));					
					
				} else if ( prefs.getValue("rapidPostsUrl", DigitalLibraryUtil.RAPIDPOSTSURL).equalsIgnoreCase(year) ) {
					
					populateTableOfContentsList(tableOfContentsList, pubType, idPrefix, 
												prefs.getValue("rapidPostsYear", DigitalLibraryUtil.RAPIDPOSTSYEAR), 
												prefs.getValue("rapidPostsIssueNumber", DigitalLibraryUtil.RAPIDPOSTSISSUENUMBER), 
												prefs.getValue("csdlToCPage", DigitalLibraryUtil.CSDLTOCPAGE));
					
				}		
				
				if ( prefs.getValue("prePrintsUrl", DigitalLibraryUtil.PREPRINTSURL).equalsIgnoreCase(year) || prefs.getValue("rapidPostsUrl", DigitalLibraryUtil.RAPIDPOSTSURL).equalsIgnoreCase(year) ) {
					
					ContentBean cbWithIssueInfo = tableOfContentsList.get(0);
					if ( null != cbWithIssueInfo ) {
						issueBean = cbWithIssueInfo.getPackageBean();
					}	
					outputJSP = "Pattern8";					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.put("issueMap", issueMap);
			model.put("issueBean", issueBean);
			model.put("tableOfContentsList", tableOfContentsList);				
			model.put("idPrefix", idPrefix);
			model.put("totalTimeMS", getTotalTime(startTiming) + " ms");

		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView(outputJSP, model);

		return modelAndView;
	}

}