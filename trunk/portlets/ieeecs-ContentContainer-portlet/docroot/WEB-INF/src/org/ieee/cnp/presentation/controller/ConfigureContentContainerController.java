package org.ieee.cnp.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.*;

import org.ieee.cnp.util.ContentContainerUtil;
import org.ieee.common.presentation.controller.BaseController;

import org.springframework.web.portlet.ModelAndView;


public class ConfigureContentContainerController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
		
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			instanceId = themeDisplay.getPortletDisplay().getId();
			
			PortletPreferences prefs = renderRequest.getPreferences();
			ContentContainerUtil.putPortletPreferencesIntoModel(prefs, model);			

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("id", instanceId);
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
			
			if ( source.equalsIgnoreCase(ContentContainerUtil.CONFIG) ) {
				
				String portletMode = ParamUtil.getString(actionRequest, "portletMode", ContentContainerUtil.MODE);
				String modifiedByUserId = new Long(themeDisplay.getUserId()).toString();
			 
				String targetName = ParamUtil.getString(actionRequest, "targetName", ContentContainerUtil.TARGETNAME);	
				String containerHeightOffset = ParamUtil.getString(actionRequest, "containerHeightOffset", ContentContainerUtil.CONTAINERHEIGHTOFFSET);
				String pagination        = ParamUtil.getString(actionRequest, "pagination", ContentContainerUtil.PAGINATION);
				String scrollDuration    = ParamUtil.getString(actionRequest, "scrollDuration", ContentContainerUtil.SCROLLDURATION);
				String restAPI           = ParamUtil.getString(actionRequest, "restAPI", ContentContainerUtil.RESTAPI);
				String commentSystem     = ParamUtil.getString(actionRequest, "commentSystem", ContentContainerUtil.COMMENTSYSTEM);
	
				String frstText = ParamUtil.getString(actionRequest, "frstText", ContentContainerUtil.FRSTTEXT);
				String nextText = ParamUtil.getString(actionRequest, "nextText", ContentContainerUtil.NEXTTEXT);
				String prevText = ParamUtil.getString(actionRequest, "prevText", ContentContainerUtil.PREVTEXT);
				String lastText = ParamUtil.getString(actionRequest, "lastText", ContentContainerUtil.LASTTEXT);		
	
				String paginationBorderColor     = ParamUtil.getString(actionRequest, "paginationBorderColor", ContentContainerUtil.PAGINATIONBORDERCOLOR);
				String paginationBackgroundColor = ParamUtil.getString(actionRequest, "paginationBackgroundColor", ContentContainerUtil.PAGINATIONBACKGRCOLOR);
				
				String firstTopLeftRadius     = ParamUtil.getString(actionRequest, "firstTopLeftRadius", ContentContainerUtil.FIRSTTOPLEFTRAD);
				String firstBottomLeftRadius  = ParamUtil.getString(actionRequest, "firstBottomLeftRadius", ContentContainerUtil.FIRSTBOTLEFTRAD);
				String firstTopRightRadius    = ParamUtil.getString(actionRequest, "firstTopRightRadius", ContentContainerUtil.FIRSTTOPRGHTRAD);
				String firstBottomRightRadius = ParamUtil.getString(actionRequest, "firstBottomRightRadius", ContentContainerUtil.FIRSTBOTRGHTRAD);	
				
				String previousTopLeftRadius     = ParamUtil.getString(actionRequest, "previousTopLeftRadius", ContentContainerUtil.PREVIOUSTOPLEFTRAD);
				String previousBottomLeftRadius  = ParamUtil.getString(actionRequest, "previousBottomLeftRadius", ContentContainerUtil.PREVIOUSBOTLEFTRAD);
				String previousTopRightRadius    = ParamUtil.getString(actionRequest, "previousTopRightRadius", ContentContainerUtil.PREVIOUSTOPRGHTRAD);
				String previousBottomRightRadius = ParamUtil.getString(actionRequest, "previousBottomRightRadius", ContentContainerUtil.PREVIOUSBOTRGHTRAD);	
				
				String nextTopLeftRadius     = ParamUtil.getString(actionRequest, "nextTopLeftRadius", ContentContainerUtil.NEXTTOPLEFTRAD);
				String nextBottomLeftRadius  = ParamUtil.getString(actionRequest, "nextBottomLeftRadius", ContentContainerUtil.NEXTBOTLEFTRAD);
				String nextTopRightRadius    = ParamUtil.getString(actionRequest, "nextTopRightRadius", ContentContainerUtil.NEXTTOPRGHTRAD);
				String nextBottomRightRadius = ParamUtil.getString(actionRequest, "nextBottomRightRadius", ContentContainerUtil.NEXTBOTRGHTRAD);	
				
				String lastTopLeftRadius     = ParamUtil.getString(actionRequest, "lastTopLeftRadius", ContentContainerUtil.LASTTOPLEFTRAD);
				String lastBottomLeftRadius  = ParamUtil.getString(actionRequest, "lastBottomLeftRadius", ContentContainerUtil.LASTBOTLEFTRAD);
				String lastTopRightRadius    = ParamUtil.getString(actionRequest, "lastTopRightRadius", ContentContainerUtil.LASTTOPRGHTRAD);
				String lastBottomRightRadius = ParamUtil.getString(actionRequest, "lastBottomRightRadius", ContentContainerUtil.LASTBOTRGHTRAD);
				
				String paginationWidth = ParamUtil.getString(actionRequest, "paginationWidth", ContentContainerUtil.PAGINATIONWIDTH);
				String paginationHeight = ParamUtil.getString(actionRequest, "paginationHeight", ContentContainerUtil.PAGINATIONHEIGHT);
				String paginationOffset = ParamUtil.getString(actionRequest, "paginationOffset", ContentContainerUtil.PAGINATIONOFFSET);
	
				String portletBorderColorTop = ParamUtil.getString(actionRequest, "portletBorderColorTop", ContentContainerUtil.PORTLETBORDERCOLORTOP);
				String portletBorderColorRight = ParamUtil.getString(actionRequest, "portletBorderColorRight", ContentContainerUtil.PORTLETBORDERCOLORRIGHT);
				String portletBorderColorBottom = ParamUtil.getString(actionRequest, "portletBorderColorBottom", ContentContainerUtil.PORTLETBORDERCOLORBOTTOM);
				String portletBorderColorLeft = ParamUtil.getString(actionRequest, "portletBorderColorLeft", ContentContainerUtil.PORTLETBORDERCOLORLEFT);			
				String portletBorderPixelTop = ParamUtil.getString(actionRequest, "portletBorderPixelTop", ContentContainerUtil.PORTLETBORDERPIXELTOP);
				String portletBorderPixelRight = ParamUtil.getString(actionRequest, "portletBorderPixelRight", ContentContainerUtil.PORTLETBORDERPIXELRIGHT);
				String portletBorderPixelBottom = ParamUtil.getString(actionRequest, "portletBorderPixelBottom", ContentContainerUtil.PORTLETBORDERPIXELBOTTOM);
				String portletBorderPixelLeft = ParamUtil.getString(actionRequest, "portletBorderPixelLeft", ContentContainerUtil.PORTLETBORDERPIXELLEFT);						
				String portletBackgroundColor = ParamUtil.getString(actionRequest, "portletBackgroundColor", ContentContainerUtil.PORTLETBACKGROUNDCOLOR);
				String portletTopLeftRadius = ParamUtil.getString(actionRequest, "portletTopLeftRadius", ContentContainerUtil.PORTLETTOPLEFTRADIUS);
				String portletBottomLeftRadius = ParamUtil.getString(actionRequest, "portletBottomLeftRadius", ContentContainerUtil.PORTLETBOTTOMLEFTRADIUS);
				String portletTopRightRadius = ParamUtil.getString(actionRequest, "portletTopRightRadius", ContentContainerUtil.PORTLETTOPRIGHTRADIUS);
				String portletBottomRightRadius = ParamUtil.getString(actionRequest, "portletBottomRightRadius", ContentContainerUtil.PORTLETBOTTOMRIGHTRADIUS);			
		
				String numberedBorderColor = ParamUtil.getString(actionRequest, "numberedBorderColor", ContentContainerUtil.NUMBEREDBORDERCOLOR);
				String numberedBackgroundColor = ParamUtil.getString(actionRequest, "numberedBackgroundColor", ContentContainerUtil.NUMBEREDBACKGRCOLOR);
				String numberedWidth = ParamUtil.getString(actionRequest, "numberedWidth", ContentContainerUtil.NUMBEREDWIDTH);
				String numberedHeight = ParamUtil.getString(actionRequest, "numberedHeight", ContentContainerUtil.NUMBEREDHEIGHT);			
				String numberedOffset = ParamUtil.getString(actionRequest, "numberedOffset", ContentContainerUtil.NUMBEREDOFFSET);
				String numberedTopLeftRadius = ParamUtil.getString(actionRequest, "numberedTopLeftRadius", ContentContainerUtil.NUMBEREDTOPLEFTRAD);
				String numberedBottomLeftRadius = ParamUtil.getString(actionRequest, "numberedBottomLeftRadius", ContentContainerUtil.NUMBEREDBOTLEFTRAD);
				String numberedTopRightRadius = ParamUtil.getString(actionRequest, "numberedTopRightRadius", ContentContainerUtil.NUMBEREDTOPRGHTRAD);						
				String numberedBottomRightRadius = ParamUtil.getString(actionRequest, "numberedBottomRightRadius", ContentContainerUtil.NUMBEREDBOTRGHTRAD);
				
				String showIntro = ParamUtil.getString(actionRequest, "showIntro", ContentContainerUtil.SHOWINTRO);
				
				PortletPreferences prefs = actionRequest.getPreferences();
				prefs.setValue("portletMode", portletMode);
				prefs.setValue("modifiedByUserId", modifiedByUserId);	
				
				prefs.setValue("targetName", targetName);
				prefs.setValue("containerHeightOffset", containerHeightOffset);
				prefs.setValue("pagination", pagination);
				prefs.setValue("scrollDuration", scrollDuration);
				prefs.setValue("restAPI", restAPI);
				prefs.setValue("commentSystem", commentSystem);
	
				prefs.setValue("frstText", frstText);
				prefs.setValue("nextText", nextText);
				prefs.setValue("prevText", prevText);
				prefs.setValue("lastText", lastText);	
							
				prefs.setValue("paginationBorderColor", paginationBorderColor);
				prefs.setValue("paginationBackgroundColor", paginationBackgroundColor);
				
				prefs.setValue("firstTopLeftRadius", firstTopLeftRadius);
				prefs.setValue("firstBottomLeftRadius", firstBottomLeftRadius);			
				prefs.setValue("firstTopRightRadius", firstTopRightRadius);
				prefs.setValue("firstBottomRightRadius", firstBottomRightRadius);
				
				prefs.setValue("previousTopLeftRadius", previousTopLeftRadius);
				prefs.setValue("previousBottomLeftRadius", previousBottomLeftRadius);			
				prefs.setValue("previousTopRightRadius", previousTopRightRadius);
				prefs.setValue("previousBottomRightRadius", previousBottomRightRadius);
				
				prefs.setValue("nextTopLeftRadius", nextTopLeftRadius);
				prefs.setValue("nextBottomLeftRadius", nextBottomLeftRadius);			
				prefs.setValue("nextTopRightRadius", nextTopRightRadius);
				prefs.setValue("nextBottomRightRadius", nextBottomRightRadius);
				
				prefs.setValue("lastTopLeftRadius", lastTopLeftRadius);
				prefs.setValue("lastBottomLeftRadius", lastBottomLeftRadius);			
				prefs.setValue("lastTopRightRadius", lastTopRightRadius);
				prefs.setValue("lastBottomRightRadius", lastBottomRightRadius);
				
				prefs.setValue("paginationWidth", paginationWidth);
				prefs.setValue("paginationHeight", paginationHeight);
				prefs.setValue("paginationOffset", paginationOffset);			
				
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
				
				prefs.setValue("numberedBorderColor", numberedBorderColor);
				prefs.setValue("numberedBackgroundColor", numberedBackgroundColor);
				prefs.setValue("numberedWidth", numberedWidth);
				prefs.setValue("numberedHeight", numberedHeight);			
				prefs.setValue("numberedOffset", numberedOffset);
				prefs.setValue("numberedTopLeftRadius", numberedTopLeftRadius);
				prefs.setValue("numberedBottomLeftRadius", numberedBottomLeftRadius);
				prefs.setValue("numberedTopRightRadius", numberedTopRightRadius);
				prefs.setValue("numberedBottomRightRadius", numberedBottomRightRadius);
				
				prefs.setValue("showIntro", showIntro.toUpperCase());
				
				prefs.store(); 		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}