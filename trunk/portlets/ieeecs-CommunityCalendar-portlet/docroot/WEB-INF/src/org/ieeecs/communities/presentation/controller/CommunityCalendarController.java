package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieeecs.communities.bean.CalendarBean;
import org.ieeecs.communities.util.CommunityCalendarUtil;
import org.ieee.common.presentation.controller.BaseController;

import org.springframework.web.portlet.ModelAndView;


public class CommunityCalendarController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
		String communityCalendarJSON = "";
		
		try {
			
			// Retrieve the Preferences for this portlet 
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			CommunityCalendarUtil.putPortletPreferencesIntoModel(prefs, model);			
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();
						
			Integer start              = new Integer(0);
			Integer end                = new Integer( prefs.getValue("recordsToPull", CommunityCalendarUtil.RECORDSTOPULL) );
			String urlTargetName       = prefs.getValue("urlTargetName", CommunityCalendarUtil.URLTARGETNAME);			
			String communityCalendarData = prefs.getValue("communityCalendarData", CommunityCalendarUtil.COMMUNITYCALENDARDATA);
			long companyGroupId        = themeDisplay.getCompanyGroupId();
			String dataFeed            = prefs.getValue("dataFeed", CommunityCalendarUtil.DATAFEED);
			String dataFeedType        = prefs.getValue("dataFeedType", CommunityCalendarUtil.DATAFEEDTYPE);
			String channelVocabularyName     = prefs.getValue("channelVocabularyName", CommunityCalendarUtil.CHANNELVOCABULARYNAME);
			String contentTypeVocabularyName = prefs.getValue("contentTypeVocabularyName", CommunityCalendarUtil.CONTENTTYPEVOCABULARYNAME);
			String publicServletMapping      = prefs.getValue("publicServletMapping", CommunityCalendarUtil.PUBLICSERVLETMAPPING);			
					
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
			
			List<CalendarBean> calendarBeanList = new ArrayList<CalendarBean>();
			calendarBeanList = CommunityCalendarUtil.getContentList(companyGroupId, start, end, communityCalendarData, urlTargetName, channelVocabularyName, contentTypeVocabularyName, publicServletMapping);
			
			// Read the external data feed, if required.
			if ( !"".equals(dataFeed) ) {
				
				String externalEventBackgroundColor = prefs.getValue("externalEventBackgroundColor", CommunityCalendarUtil.EXTERNALEVENTBACKGROUNDCOLOR);
				String externalEventBorderColor = prefs.getValue("externalEventBorderColor", CommunityCalendarUtil.EXTERNALEVENTBORDERCOLOR);
				String externalEventTextColor = prefs.getValue("externalEventTextColor", CommunityCalendarUtil.EXTERNALEVENTTEXTCOLOR);	
				
				// File-based
				if ( CommunityCalendarUtil.DATAFEEDTYPE.equalsIgnoreCase(dataFeedType) ) {
					
					calendarBeanList.addAll( CommunityCalendarUtil.readDataFeed(dataFeed, externalEventBackgroundColor, externalEventBorderColor, externalEventTextColor, dataFeedType) );
					
				// URL-based
				} else {
					
					if ( !dataFeed.startsWith("http://") ) {
						dataFeed = "http://" + dataFeed.trim();
					}
					calendarBeanList.addAll( CommunityCalendarUtil.readDataFeed(dataFeed, externalEventBackgroundColor, externalEventBorderColor, externalEventTextColor, dataFeedType) );
					
				}	
			}
	
			// Now transform the List into some JSON.
			communityCalendarJSON = JSONFactoryUtil.serialize( calendarBeanList );

			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only this user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this admin user sets it to "Activated".
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", CommunityCalendarUtil.USERID);
			String currentMode = prefs.getValue("portletMode", CommunityCalendarUtil.MODE);
			
			
			//adding the calendarData into model so that legend can be shown on the portlet			
			model.put("calendarDataForLegend", communityCalendarData);
			

			
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && currentMode.trim().equalsIgnoreCase(CommunityCalendarUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("id", instanceId);
		model.put("communityCalendarJSON", communityCalendarJSON);
		ModelAndView modelAndView = new ModelAndView("Home", model);

		return modelAndView;
	}

}
