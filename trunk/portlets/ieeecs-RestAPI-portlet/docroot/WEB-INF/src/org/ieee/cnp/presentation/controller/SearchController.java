package org.ieee.cnp.presentation.controller;

import java.util.*;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieee.common.bean.ContentBean;
import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.ContentUtil;
import org.ieee.common.util.ParamUtil;

import org.springframework.web.portlet.ModelAndView;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetEntry;


public class SearchController extends BaseController {

	private static final String STARTDELIM  = "~~~~~~";
	private static final String ENDDELIM    = "^^^^^^";
	private static final String QUERY       = "query";
	private static final String BUILD       = "build";
	

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		String output = "";

		try {
			
			String process = ParamUtil.getStringParameter(renderRequest, "process");
			
			if ( null != process && !"".equals(process) ) {
			
				String data = ParamUtil.getStringParameter(renderRequest, "data");
				
				if ( null != data && !"".equals(data) ) {
					
					if ( process.equalsIgnoreCase(QUERY) ) {
						
						JSONObject jsonData = new JSONObject(data);
						String configFile = jsonData.has("file") ? jsonData.getString("file") : "";
						Map<String,String> communityPropertiesMap = ContentUtil.getCommunityProperties(configFile);
						
						ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
												
						long companyGroupId              = themeDisplay.getCompanyGroupId();
						String communities               = null == communityPropertiesMap.get("communities") ? ContentUtil.defaultCommunityProperties.get("communities") : communityPropertiesMap.get("communities");
						String channels                  = null == communityPropertiesMap.get("channels") ? ContentUtil.defaultCommunityProperties.get("channels") : communityPropertiesMap.get("channels");
						String vocabularies              = null == communityPropertiesMap.get("vocabularies") ? ContentUtil.defaultCommunityProperties.get("vocabularies") : communityPropertiesMap.get("vocabularies");
						String urlTargetName             = null == communityPropertiesMap.get("urlTargetName") ? ContentUtil.defaultCommunityProperties.get("urlTargetName") : communityPropertiesMap.get("urlTargetName");
						String channelVocabularyName     = null == communityPropertiesMap.get("channelVocabularyName") ? ContentUtil.defaultCommunityProperties.get("channelVocabularyName") : communityPropertiesMap.get("channelVocabularyName");
						String contentTypeVocabularyName = null == communityPropertiesMap.get("contentTypeVocabularyName") ? ContentUtil.defaultCommunityProperties.get("contentTypeVocabularyName") : communityPropertiesMap.get("contentTypeVocabularyName");
						String publicServletMapping      = null == communityPropertiesMap.get("publicServletMapping") ? ContentUtil.defaultCommunityProperties.get("publicServletMapping") : communityPropertiesMap.get("publicServletMapping");
						String articleImagePath          = null == communityPropertiesMap.get("articleImagePath") ? ContentUtil.defaultCommunityProperties.get("articleImagePath") : communityPropertiesMap.get("articleImagePath");
						String blogImagePath             = null == communityPropertiesMap.get("blogImagePath") ? ContentUtil.defaultCommunityProperties.get("blogImagePath") : communityPropertiesMap.get("blogImagePath");						
						String defaultImagePath          = null == communityPropertiesMap.get("defaultImagePath") ? ContentUtil.defaultCommunityProperties.get("defaultImagePath") : communityPropertiesMap.get("defaultImagePath");
						String displayDateFormat         = null == communityPropertiesMap.get("displayDateFormat") ? ContentUtil.defaultCommunityProperties.get("displayDateFormat") : communityPropertiesMap.get("displayDateFormat");
						
						// Not needed for Search, but required for the method call. Leaving it here for future use.
						String supplement = null == communityPropertiesMap.get("urlTargetName") ? ContentUtil.defaultCommunityProperties.get("urlTargetName") : communityPropertiesMap.get("urlTargetName");
						
						// Set the 'default' values in case there are no search parameters passed in, (ie. if the User just put a keyword in the "Search" text box and hit "Search").
						JSONArray defaultGroupIds     = new JSONArray("[" + communities + "]");
						JSONArray defaultClassNameIds = new JSONArray("[" + ContentUtil.getValidClassNameIds() + "]");
						JSONArray defaultCategoryIds  = new JSONArray("[" + ContentUtil.getValidCategoryIds(companyGroupId, channels + "," + vocabularies) + "]");
						jsonData.put("defaultGroupIds", defaultGroupIds);
						jsonData.put("defaultClassNameIds", defaultClassNameIds);
						jsonData.put("defaultCategoryIds", defaultCategoryIds);
						
						List<AssetEntry> assetEntryList   = ContentUtil.getAssetsByJSONData(jsonData);
						List<ContentBean> contentBeanList = ContentUtil.getContentList(companyGroupId, assetEntryList, urlTargetName, channelVocabularyName, contentTypeVocabularyName, publicServletMapping, 
																	                   articleImagePath, blogImagePath, supplement, defaultImagePath, displayDateFormat);
						
						
						

						
					} else if ( process.equalsIgnoreCase(BUILD) ) {
						
						JSONObject jsonData = new JSONObject(data);
						String configFile          = jsonData.has("file") ? jsonData.getString("file") : "";
						
					}
	
				}
			}

		} catch (Exception e) {
			output = "{'status':'error'}";
			e.printStackTrace();
		}

		model.put("output", STARTDELIM + output + ENDDELIM);
		ModelAndView modelAndView = new ModelAndView("Output", model);

		return modelAndView;
	}
}