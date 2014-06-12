package org.ieee.cnp.presentation.controller;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieee.cnp.util.ContentListUtil;

import org.ieee.common.bean.ContentBean;
import org.ieee.common.json.JSONArray;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.ContentUtil;

import org.springframework.web.portlet.ModelAndView;


public class ContentListController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
		String initialContentListJSON = "";
		int initialChunk = 0;
		int asyncChunk   = 0;
		int totalRecords = 0;
		boolean fallbackJS = true;
		List<ContentBean> contentList = new ArrayList<ContentBean>();
		
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			// We assume that the theme where this portlet resides doesn't contain all of the required Javascript libraries.
			// If it finds that the "complianceVersion" value is set in the theme's "liferay-look-and-feel.xml" file, then it contains all needed libraries.
			String complianceVersion = themeDisplay.getTheme().getSetting("complianceVersion");
			if ( null != complianceVersion && !"".equals(complianceVersion) ) {
				fallbackJS = false;
			}

			// Now continue with the Portlet preferences settings and other parameters needed to display this list.
			PortletPreferences prefs = renderRequest.getPreferences();
			ContentListUtil.putPortletPreferencesIntoModel(prefs, model);			
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();
						
			initialChunk           = new Integer( prefs.getValue("initialChunk", ContentListUtil.INITIALCHUNK) );
			asyncChunk             = new Integer( prefs.getValue("asyncChunk", ContentListUtil.ASYNCCHUNK) );
			totalRecords           = new Integer( prefs.getValue("recordsToPull", ContentListUtil.RECORDSTOPULL) );
			Integer start          = new Integer(0);
			Integer end            = initialChunk;
			String urlTargetName   = prefs.getValue("urlTargetName", ContentListUtil.URLTARGETNAME);			
			String contentListData = prefs.getValue("contentListData", ContentListUtil.CONTENTLISTDATA);
			long companyGroupId    = themeDisplay.getCompanyGroupId();
			String channelVocabularyName     = prefs.getValue("channelVocabularyName", ContentListUtil.CHANNELVOCABULARYNAME);
			String contentTypeVocabularyName = prefs.getValue("contentTypeVocabularyName", ContentListUtil.CONTENTTYPEVOCABULARYNAME);
			String publicServletMapping      = prefs.getValue("publicServletMapping", ContentListUtil.PUBLICSERVLETMAPPING);
			String articleImagePath          = prefs.getValue("articleImagePath", ContentListUtil.ARTICLEIMAGEPATH);
			String blogImagePath             = prefs.getValue("blogImagePath", ContentListUtil.BLOGIMAGEPATH);
			String supplement                = prefs.getValue("supplement", ContentListUtil.SUPPLEMENT);
			String defaultImagePath          = prefs.getValue("defaultImagePath", ContentListUtil.DEFAULTIMAGEPATH);
			String displayDateFormat         = prefs.getValue("displayDateFormat", ContentListUtil.DISPLAYDATEFORMAT);
						
			// The Group/Category values will come from the "actionRequest" as (for example):
			//		[ 
			//			{"uiId":"1345428490345VGCcwIiM","groupId":"16228","contentBit":7,"categories":["17256"],"description":"ComputingNow (Articles, Blog Posts, Podcasts) in 'Cloud'"},
			//			...
			//			...
			//		] 			
			//

			contentList = ContentUtil.getContentList(companyGroupId, start, end, contentListData, 
														urlTargetName, channelVocabularyName, contentTypeVocabularyName, 
														publicServletMapping, articleImagePath, blogImagePath, supplement, defaultImagePath, displayDateFormat);
			initialContentListJSON = JSONFactoryUtil.serialize( contentList );

			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only this user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this admin user sets it to "Activated".
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", ContentListUtil.USERID);
			String currentMode = prefs.getValue("portletMode", ContentListUtil.MODE);
			
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && currentMode.trim().equalsIgnoreCase(ContentListUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}
						
			// If a Custom template is used, get the CSS/HTML that has been entered for it.
			
			String cssPref = prefs.getValue("cssBlock", ContentListUtil.CSSBLOCK);
			JSONArray cssBlockJSONArray = new JSONArray(cssPref);
			String cssBlock = "";
			
			for ( int cssI = 0; cssI < cssBlockJSONArray.length(); cssI++ ) {
				String line = (String) cssBlockJSONArray.get(cssI);
				cssBlock = cssBlock + line + "\r\n";
			}			
			
			String htmlPref = prefs.getValue("htmlBlock", ContentListUtil.HTMLBLOCK);
			JSONArray htmlBlockJSONArray = new JSONArray(htmlPref);
			String htmlBlock = "";
			
			for ( int htmlI = 0; htmlI < htmlBlockJSONArray.length(); htmlI++ ) {
				String line = (String) htmlBlockJSONArray.get(htmlI);
				htmlBlock = htmlBlock + line + "\r\n";
			}
			
			model.put("cssBlock", cssBlock);
			model.put("htmlBlock", htmlBlock);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("id", instanceId);
		model.put("initialContentListJSON", initialContentListJSON);
		model.put("contentList", contentList);	
		model.put("initialChunk", initialChunk);
		model.put("asyncChunk", asyncChunk);
		model.put("totalRecords", totalRecords);
		model.put("fallbackJS", fallbackJS);		
		ModelAndView modelAndView = new ModelAndView("Home", model);

		return modelAndView;
	}

}