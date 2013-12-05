package org.ieeecs.csdl.presentation.controller;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.util.*;

import javax.portlet.*;

import org.ieeecs.csdl.util.DigitalLibraryUtil;
import org.springframework.web.portlet.ModelAndView;


public class DigitalLibraryController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String resourcesSection = "";

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			putPortletPreferencesIntoModel(prefs, model);			
			setPorletMode(themeDisplay, prefs, model);

			long startTiming = getTime();
			
			goHome(prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH), prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE), model);
			
			// Retrieve the "Resources" section, powered by a JournalArticle
			long groupId = themeDisplay.getScopeGroupId();
			JournalArticle article = JournalArticleLocalServiceUtil.getLatestArticle(groupId, prefs.getValue("resourceArticleId", DigitalLibraryUtil.RESOURCEARTICLEID));
			resourcesSection = getArticleContent(article, prefs.getValue("resourceDefaultText", DigitalLibraryUtil.RESOURCEDEFAULTTEXT));
	
			model.put("totalTimeMS", getTotalTime(startTiming) + " ms");		
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("resourcesSection", resourcesSection);
		ModelAndView modelAndView = new ModelAndView("Home", model);

		return modelAndView;
	}

}