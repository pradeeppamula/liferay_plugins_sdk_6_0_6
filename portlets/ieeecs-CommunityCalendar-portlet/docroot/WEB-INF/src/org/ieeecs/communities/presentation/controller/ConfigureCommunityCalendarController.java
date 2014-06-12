package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.bookmarks.util.comparator.EntryNameComparator;

import java.util.*;

import javax.portlet.*;

import org.ieeecs.communities.bean.CategoryBean;
import org.ieeecs.communities.bean.GroupBean;
import org.ieeecs.communities.util.CommunityCalendarUtil;
import org.ieee.common.constants.GlobalConstants;
import org.ieee.common.presentation.controller.BaseController;

import org.springframework.web.portlet.ModelAndView;


public class ConfigureCommunityCalendarController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();	
		Map<String, GroupBean> groupMap = new TreeMap<String, GroupBean>();
		Map<String, CategoryBean> channelMap = new TreeMap<String, CategoryBean>();
		Map<String, Map<String,CategoryBean>> vocabularyMap = new TreeMap<String, Map<String,CategoryBean>>();		
		String instanceId = "";
		String communityId = "";
		String communityName = "";

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			CommunityCalendarUtil.putPortletPreferencesIntoModel(prefs, model);
			instanceId = themeDisplay.getPortletDisplay().getId();
			
			String channelVocabularyName = prefs.getValue("channelVocabularyName", CommunityCalendarUtil.CHANNELVOCABULARYNAME);
			String contentTypeVocabularyName = prefs.getValue("contentTypeVocabularyName", CommunityCalendarUtil.CONTENTTYPEVOCABULARYNAME);
			
			populateChannelMap(themeDisplay.getCompanyGroupId(), channelMap, channelVocabularyName);
			populateContentTypeMap(themeDisplay.getCompanyGroupId(), vocabularyMap, contentTypeVocabularyName);
						
			// The "communityCalendarData" is the existing settings for this calendar, (configured by a user/admin who setup the portlet).
			String communityCalendarData = prefs.getValue("communityCalendarData", CommunityCalendarUtil.COMMUNITYCALENDARDATA);
			communityCalendarData = "".equals(communityCalendarData.trim()) ? "[]" : communityCalendarData;
			model.put("communityCalendarData", communityCalendarData);
			
			communityId = (new Long(themeDisplay.getScopeGroupId())).toString();
			communityName = themeDisplay.getScopeGroupName();				
						
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("id", instanceId);
		model.put("contentItemMap", GlobalConstants.contentItemMap);
		model.put("groupMap", groupMap);
		model.put("channelMap", channelMap);
		model.put("vocabularyMap", vocabularyMap);
		model.put("communityId", communityId);
		model.put("communityName", communityName);
		ModelAndView modelAndView = new ModelAndView("Configure", model);

		return modelAndView;
	}

	protected void handleActionRequestInternal(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
			// The "source" attribute is not stored and is used solely to determine where the Request came from.
			// When a person opens up the Portlet "config/edit" window, then decides to go to the Liferay Control Panel, and then returns
			// to the Portlet "config/edit" screen, then this "handleActionRequestInternal" method is fired.   I guess the Liferay "Return to Full Page"
			// link within the Control Panel is an ActionURL instead of a RenderURL.   The "source" will be null, and therefore empty, if the 
			// person returns to the "config/edit" screen from the Control Panel.
			String source = ParamUtil.getString(actionRequest, "source", "");
			
			if ( source.equalsIgnoreCase(CommunityCalendarUtil.CONFIG) ) {
				
				String portletMode = ParamUtil.getString(actionRequest, "portletMode", CommunityCalendarUtil.MODE);
				String modifiedByUserId = new Long(themeDisplay.getUserId()).toString();
				
				// ******************************************************************************************************
				// Note:
				//
				// The Group/Category values will come from the "actionRequest" as (for example):
				//		[ 
				//			{"uiId":"1345428490345VGCcwIiM","groupId":"16228","contentBit":7,"categories":["17256"],"bgColor":"CCCCCC","borderColor":"CCCCCC","textColor":"000000",
				//           "description":"ComputingNow (Articles, Blog Posts, Podcasts) in 'Cloud'   <<with the Background, Border, and Title colors here>>   "},
				//			...
				//			...
				//		] 			
				//
				// ******************************************************************************************************
				String communityCalendarData = ParamUtil.getString(actionRequest, "communityCalendarData", CommunityCalendarUtil.COMMUNITYCALENDARDATA);		
				 
				String titleVisible      = ParamUtil.getString(actionRequest, "titleVisible", CommunityCalendarUtil.TITLEVISIBLE);
				String titleOfCalendar   = ParamUtil.getString(actionRequest, "titleOfCalendar", CommunityCalendarUtil.TITLEOFCALENDAR);
				String titleTopMargin    = ParamUtil.getString(actionRequest, "titleTopMargin", CommunityCalendarUtil.TITLETOPMARGIN);
				String titleBottomMargin = ParamUtil.getString(actionRequest, "titleBottomMargin", CommunityCalendarUtil.TITLEBOTTOMMARGIN);
				String titleColor        = ParamUtil.getString(actionRequest, "titleColor", CommunityCalendarUtil.TITLECOLOR);
				String titleFont         = ParamUtil.getString(actionRequest, "titleFont", CommunityCalendarUtil.TITLEFONT);				
				String recordsToPull     = ParamUtil.getString(actionRequest, "recordsToPull", CommunityCalendarUtil.RECORDSTOPULL);
				String modalTopOffset    = ParamUtil.getString(actionRequest, "modalTopOffset", CommunityCalendarUtil.MODALTOPOFFSET);				
	
				String titleBorderColor       = ParamUtil.getString(actionRequest, "titleBorderColor", CommunityCalendarUtil.TITLEBORDERCOLOR);
				String titleBackgroundColor   = ParamUtil.getString(actionRequest, "titleBackgroundColor", CommunityCalendarUtil.TITLEBACKGRCOLOR);
	
				String titleTopLeftRadius     = ParamUtil.getString(actionRequest, "titleTopLeftRadius", CommunityCalendarUtil.TITLETOPLEFTRAD);
				String titleBottomLeftRadius  = ParamUtil.getString(actionRequest, "titleBottomLeftRadius", CommunityCalendarUtil.TITLEBOTLEFTRAD);
				String titleTopRightRadius    = ParamUtil.getString(actionRequest, "titleTopRightRadius", CommunityCalendarUtil.TITLETOPRGHTRAD);
				String titleBottomRightRadius = ParamUtil.getString(actionRequest, "titleBottomRightRadius", CommunityCalendarUtil.TITLEBOTRGHTRAD);
	
				String portletBorderColorTop = ParamUtil.getString(actionRequest, "portletBorderColorTop", CommunityCalendarUtil.PORTLETBORDERCOLORTOP);
				String portletBorderColorRight = ParamUtil.getString(actionRequest, "portletBorderColorRight", CommunityCalendarUtil.PORTLETBORDERCOLORRIGHT);
				String portletBorderColorBottom = ParamUtil.getString(actionRequest, "portletBorderColorBottom", CommunityCalendarUtil.PORTLETBORDERCOLORBOTTOM);
				String portletBorderColorLeft = ParamUtil.getString(actionRequest, "portletBorderColorLeft", CommunityCalendarUtil.PORTLETBORDERCOLORLEFT);			
				String portletBorderPixelTop = ParamUtil.getString(actionRequest, "portletBorderPixelTop", CommunityCalendarUtil.PORTLETBORDERPIXELTOP);
				String portletBorderPixelRight = ParamUtil.getString(actionRequest, "portletBorderPixelRight", CommunityCalendarUtil.PORTLETBORDERPIXELRIGHT);
				String portletBorderPixelBottom = ParamUtil.getString(actionRequest, "portletBorderPixelBottom", CommunityCalendarUtil.PORTLETBORDERPIXELBOTTOM);
				String portletBorderPixelLeft = ParamUtil.getString(actionRequest, "portletBorderPixelLeft", CommunityCalendarUtil.PORTLETBORDERPIXELLEFT);						
				String portletBackgroundColor = ParamUtil.getString(actionRequest, "portletBackgroundColor", CommunityCalendarUtil.PORTLETBACKGROUNDCOLOR);
				String portletTopLeftRadius = ParamUtil.getString(actionRequest, "portletTopLeftRadius", CommunityCalendarUtil.PORTLETTOPLEFTRADIUS);
				String portletBottomLeftRadius = ParamUtil.getString(actionRequest, "portletBottomLeftRadius", CommunityCalendarUtil.PORTLETBOTTOMLEFTRADIUS);
				String portletTopRightRadius = ParamUtil.getString(actionRequest, "portletTopRightRadius", CommunityCalendarUtil.PORTLETTOPRIGHTRADIUS);
				String portletBottomRightRadius = ParamUtil.getString(actionRequest, "portletBottomRightRadius", CommunityCalendarUtil.PORTLETBOTTOMRIGHTRADIUS);
	
				String urlTargetName = ParamUtil.getString(actionRequest, "urlTargetName", CommunityCalendarUtil.URLTARGETNAME);
				String channelVocabularyName = ParamUtil.getString(actionRequest, "channelVocabularyName", CommunityCalendarUtil.CHANNELVOCABULARYNAME);
				String contentTypeVocabularyName = ParamUtil.getString(actionRequest, "contentTypeVocabularyName", CommunityCalendarUtil.CONTENTTYPEVOCABULARYNAME);
				String restAPI = ParamUtil.getString(actionRequest, "restAPI", CommunityCalendarUtil.RESTAPI);
				String publicServletMapping = ParamUtil.getString(actionRequest, "publicServletMapping", CommunityCalendarUtil.PUBLICSERVLETMAPPING);
				
				String dataFeed = ParamUtil.getString(actionRequest, "dataFeed", CommunityCalendarUtil.DATAFEED);
				String dataFeedType = ParamUtil.getString(actionRequest, "dataFeedType", CommunityCalendarUtil.DATAFEEDTYPE);
				String externalEventBackgroundColor = ParamUtil.getString(actionRequest, "externalEventBackgroundColor", CommunityCalendarUtil.EXTERNALEVENTBACKGROUNDCOLOR);
				String externalEventBorderColor = ParamUtil.getString(actionRequest, "externalEventBorderColor", CommunityCalendarUtil.EXTERNALEVENTBORDERCOLOR);
				String externalEventTextColor = ParamUtil.getString(actionRequest, "externalEventTextColor", CommunityCalendarUtil.EXTERNALEVENTTEXTCOLOR);
				
				String slotMinutes = ParamUtil.getString(actionRequest, "slotMinutes", CommunityCalendarUtil.SLOTMINUTES);
				String aspectRatio = ParamUtil.getString(actionRequest, "aspectRatio", CommunityCalendarUtil.ASPECTRATIO);
				String highlightBackgroundColor = ParamUtil.getString(actionRequest, "highlightBackgroundColor", CommunityCalendarUtil.HIGHLIGHTBG);
				String eventCSS = ParamUtil.getString(actionRequest, "eventCSS", CommunityCalendarUtil.EVENTCSS);
				
				String filtering       = ParamUtil.getString(actionRequest, "filtering", CommunityCalendarUtil.FILTERING);
				String searchInputText = ParamUtil.getString(actionRequest, "searchInputText", CommunityCalendarUtil.SEARCHINPUTTEXT);
				String backToText      = ParamUtil.getString(actionRequest, "backToText", CommunityCalendarUtil.BACKTOTEXT);
				
				String showIntro = ParamUtil.getString(actionRequest, "showIntro", CommunityCalendarUtil.SHOWINTRO);
	
				PortletPreferences prefs = actionRequest.getPreferences();
				prefs.setValue("portletMode", portletMode);
				prefs.setValue("modifiedByUserId", modifiedByUserId);				
				
				prefs.setValue("communityCalendarData", communityCalendarData);
				prefs.setValue("titleVisible", titleVisible);
				prefs.setValue("titleOfCalendar", titleOfCalendar);
				prefs.setValue("titleTopMargin", titleTopMargin);
				prefs.setValue("titleBottomMargin", titleBottomMargin);
				prefs.setValue("titleColor", titleColor);
				prefs.setValue("titleFont", titleFont);
				prefs.setValue("recordsToPull", recordsToPull);
				prefs.setValue("modalTopOffset", modalTopOffset);				
	
				prefs.setValue("titleBorderColor", titleBorderColor);
				prefs.setValue("titleBackgroundColor", titleBackgroundColor);
	
				prefs.setValue("titleTopLeftRadius", titleTopLeftRadius);
				prefs.setValue("titleBottomLeftRadius", titleBottomLeftRadius);			
				prefs.setValue("titleTopRightRadius", titleTopRightRadius);
				prefs.setValue("titleBottomRightRadius", titleBottomRightRadius);
	
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

				prefs.setValue("urlTargetName", urlTargetName);
				prefs.setValue("channelVocabularyName", channelVocabularyName);
				prefs.setValue("contentTypeVocabularyName", contentTypeVocabularyName);
				prefs.setValue("restAPI", restAPI);
				prefs.setValue("publicServletMapping", publicServletMapping);
				
				prefs.setValue("dataFeed", dataFeed);
				prefs.setValue("dataFeedType", dataFeedType);
				prefs.setValue("externalEventBackgroundColor", externalEventBackgroundColor);
				prefs.setValue("externalEventBorderColor", externalEventBorderColor);
				prefs.setValue("externalEventTextColor", externalEventTextColor);
				
				prefs.setValue("slotMinutes", slotMinutes);
				prefs.setValue("aspectRatio", aspectRatio);
				prefs.setValue("highlightBackgroundColor", highlightBackgroundColor);
				prefs.setValue("eventCSS", eventCSS);
				
				prefs.setValue("filtering", filtering);
				prefs.setValue("searchInputText", searchInputText);
				prefs.setValue("backToText", backToText);
				
				prefs.setValue("showIntro", showIntro.toUpperCase());
	
				prefs.store(); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void populateChannelMap(long globalGroupId, Map<String, CategoryBean> channelMap, String channelVocabularyName) {
		
		try {
			
			OrderByComparator obc = new EntryNameComparator(true);
			AssetVocabulary channelVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroupId, channelVocabularyName) ; 														
			List<AssetCategory> categoryList = AssetCategoryLocalServiceUtil.getVocabularyCategories(channelVocabulary.getVocabularyId(), 
																QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);
			
			if ( null != categoryList && categoryList.size() > 0 ) {
				
				for ( AssetCategory category : categoryList ) {									
					CategoryBean cb = new CategoryBean();
					cb.setCategoryId( category.getCategoryId() );
					cb.setName( category.getName() );									
					channelMap.put(category.getName(), cb);
				}								
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void populateContentTypeMap(long globalGroupId, Map<String, Map<String,CategoryBean>> vocabularyMap, String contentTypeVocabularyName) {
		
		try {
			
			String[] vocabularyArray = contentTypeVocabularyName.trim().split(",",-1);
			
			if ( vocabularyArray.length > 0 ) {
				
				OrderByComparator obc = new EntryNameComparator(true);
				
				for ( int index = 0; index < vocabularyArray.length; index++ ) {
					
					String currentVocabularyName = vocabularyArray[index].trim();
					AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroupId, currentVocabularyName) ;
					List<AssetCategory> categoryList = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabulary.getVocabularyId(), 
															QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);									
					
					if ( null != categoryList && categoryList.size() > 0 ) {
						
						Map<String, CategoryBean> currentVocabularyMap = new TreeMap<String, CategoryBean>();
						
						for ( AssetCategory category : categoryList ) {											
							CategoryBean cb = new CategoryBean();
							cb.setCategoryId( category.getCategoryId() );
							cb.setName( category.getName() );											
							currentVocabularyMap.put(category.getName(), cb);
						}	
						
						vocabularyMap.put(currentVocabularyName, currentVocabularyMap);
					}									
				}								
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
