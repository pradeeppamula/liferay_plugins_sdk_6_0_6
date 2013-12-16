package org.ieeecs.csdl.presentation.controller;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.*;

import org.ieeecs.csdl.bean.VolumeBean;
import org.ieeecs.csdl.util.DigitalLibraryUtil;

import org.ieee.common.json.JSONObject;
import org.ieee.common.util.ParamUtil;

import org.springframework.web.portlet.ModelAndView;


public class CSDLPattern6Controller extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			putPortletPreferencesIntoModel(prefs, model);			
			setPorletMode(themeDisplay, prefs, model);
	
			String csdlBase = DigitalLibraryUtil.CSDLBASE;
			long startTiming = getTime();
			
			String pubType  = ParamUtil.getStringParameter(renderRequest, prefs.getValue("pubType", DigitalLibraryUtil.PUBTYPE));
			String idPrefix = ParamUtil.getStringParameter(renderRequest, prefs.getValue("idPrefix", DigitalLibraryUtil.IDPREFIX));
			String fileName = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileName", DigitalLibraryUtil.FILENAME));
			String fileType = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileType", DigitalLibraryUtil.FILETYPE));			

			Map<String,VolumeBean> volumeMap = new TreeMap<String,VolumeBean>(Collections.reverseOrder());
			boolean prePrints = false;
			boolean rapidPosts = false;
			
			try {			
					
				JSONObject idPrefixDataJSON = jsonObjectFromDotNotation(new JSONObject(csdlBase), pubType + "." + idPrefix);				
				putBreadcrumbInfoIntoModel(model, idPrefixDataJSON, 
										   prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH), 
										   prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE), 
										   prefs.getValue("issueNumberLabel", DigitalLibraryUtil.ISSUENUMBERLABEL), 
										   pubType, idPrefix, "", "");
				
				int startYear			= idPrefixDataJSON.getInt("startYear");
				int endYear				= idPrefixDataJSON.getInt("endYear") == 0 ? Calendar.getInstance().get(Calendar.YEAR) : idPrefixDataJSON.getInt("endYear");
				int startVolume			= idPrefixDataJSON.getInt("startVolume");
				int currentVolumeNumber	= startVolume;				
				prePrints 		        = idPrefixDataJSON.getBoolean("prePrints");
				rapidPosts 		        = idPrefixDataJSON.getBoolean("rapidPosts");				
	
				for ( int currentYear = startYear; currentYear <= endYear; currentYear++ ) {
					VolumeBean vb = new VolumeBean();
					vb.setPrePrint(false);
					vb.setVolumeNumber(currentVolumeNumber > 9 ? ""+currentVolumeNumber : "&nbsp;"+currentVolumeNumber);
					vb.setYear(currentYear+"");
					vb.setUrl( prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH) + "/" + 
					           pubType + "/" + idPrefix + "/" + currentYear + "/" + prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE));
					volumeMap.put(currentYear + prefs.getValue("volumeAbbrev", DigitalLibraryUtil.VOLUMEABBREV) + currentVolumeNumber, vb);
					currentVolumeNumber++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}	
			model.put("searchDatabases",getSearchDatabases(idPrefix));
			model.put("volumeMap", volumeMap);
			model.put("prePrints", prePrints);
			model.put("rapidPosts", rapidPosts);
			model.put("idPrefix", idPrefix);
			model.put("totalTimeMS", getTotalTime(startTiming) + " ms");

		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("Pattern6", model);

		return modelAndView;
	}

}