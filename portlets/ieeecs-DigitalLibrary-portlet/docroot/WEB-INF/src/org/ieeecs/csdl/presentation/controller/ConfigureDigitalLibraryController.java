package org.ieeecs.csdl.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.*;

import org.ieeecs.csdl.util.DigitalLibraryUtil;
import org.ieee.common.presentation.controller.BaseController;

import org.springframework.web.portlet.ModelAndView;


public class ConfigureDigitalLibraryController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			DigitalLibraryUtil.putPortletPreferencesIntoModel(prefs, model);
			instanceId = themeDisplay.getPortletDisplay().getId();

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

			if ( source.equalsIgnoreCase(DigitalLibraryUtil.CONFIG) ) {

				String portletMode = ParamUtil.getString(actionRequest, "portletMode", DigitalLibraryUtil.MODE);
				String modifiedByUserId = new Long(themeDisplay.getUserId()).toString();

				String portletBorderColorTop = ParamUtil.getString(actionRequest, "portletBorderColorTop", DigitalLibraryUtil.PORTLETBORDERCOLORTOP);
				String portletBorderColorRight = ParamUtil.getString(actionRequest, "portletBorderColorRight", DigitalLibraryUtil.PORTLETBORDERCOLORRIGHT);
				String portletBorderColorBottom = ParamUtil.getString(actionRequest, "portletBorderColorBottom", DigitalLibraryUtil.PORTLETBORDERCOLORBOTTOM);
				String portletBorderColorLeft = ParamUtil.getString(actionRequest, "portletBorderColorLeft", DigitalLibraryUtil.PORTLETBORDERCOLORLEFT);
				String portletBorderPixelTop = ParamUtil.getString(actionRequest, "portletBorderPixelTop", DigitalLibraryUtil.PORTLETBORDERPIXELTOP);
				String portletBorderPixelRight = ParamUtil.getString(actionRequest, "portletBorderPixelRight", DigitalLibraryUtil.PORTLETBORDERPIXELRIGHT);
				String portletBorderPixelBottom = ParamUtil.getString(actionRequest, "portletBorderPixelBottom", DigitalLibraryUtil.PORTLETBORDERPIXELBOTTOM);
				String portletBorderPixelLeft = ParamUtil.getString(actionRequest, "portletBorderPixelLeft", DigitalLibraryUtil.PORTLETBORDERPIXELLEFT);
				String portletBackgroundColor = ParamUtil.getString(actionRequest, "portletBackgroundColor", DigitalLibraryUtil.PORTLETBACKGROUNDCOLOR);
				String portletTopLeftRadius = ParamUtil.getString(actionRequest, "portletTopLeftRadius", DigitalLibraryUtil.PORTLETTOPLEFTRADIUS);
				String portletBottomLeftRadius = ParamUtil.getString(actionRequest, "portletBottomLeftRadius", DigitalLibraryUtil.PORTLETBOTTOMLEFTRADIUS);
				String portletTopRightRadius = ParamUtil.getString(actionRequest, "portletTopRightRadius", DigitalLibraryUtil.PORTLETTOPRIGHTRADIUS);
				String portletBottomRightRadius = ParamUtil.getString(actionRequest, "portletBottomRightRadius", DigitalLibraryUtil.PORTLETBOTTOMRIGHTRADIUS);
				
				String innerMargins = ParamUtil.getString(actionRequest, "innerMargins", DigitalLibraryUtil.INNERMARGINS);
				String restAPI = ParamUtil.getString(actionRequest, "restAPI", DigitalLibraryUtil.RESTAPI);
				String homePageFeaturedSliderDelay = ParamUtil.getString(actionRequest, "homePageFeaturedSliderDelay", DigitalLibraryUtil.HOMEPAGEFEATUREDSLIDERDELAY);
								
				String mags = ParamUtil.getString(actionRequest, "mags", DigitalLibraryUtil.MAGS);
				String trans = ParamUtil.getString(actionRequest, "trans", DigitalLibraryUtil.TRANS);
				String letters = ParamUtil.getString(actionRequest, "letters", DigitalLibraryUtil.LETTERS);
				String proceedings = ParamUtil.getString(actionRequest, "proceedings", DigitalLibraryUtil.PROCEEDINGS);
				String prePrints = ParamUtil.getString(actionRequest, "prePrints", DigitalLibraryUtil.PREPRINTS);
				String rapidPosts = ParamUtil.getString(actionRequest, "rapidPosts", DigitalLibraryUtil.RAPIDPOSTS);
				
				String csdlHeaderTitle = ParamUtil.getString(actionRequest, "csdlHeaderTitle", DigitalLibraryUtil.CSDLHEADERTITLE);
				String csdlHeaderIntroduction = ParamUtil.getString(actionRequest, "csdlHeaderIntroduction", DigitalLibraryUtil.CSDLHEADERINTRODUCTION);
				String csdlHeaderIntroductionImagePath = ParamUtil.getString(actionRequest, "csdlHeaderIntroductionImagePath", DigitalLibraryUtil.CSDLHEADERINTRODUCTIONIMAGEPATH);
				String subscriptionBlurb = ParamUtil.getString(actionRequest, "subscriptionBlurb", DigitalLibraryUtil.SUBSCRIPTIONBLURB);
				String csdlSearchTitle = ParamUtil.getString(actionRequest, "csdlSearchTitle", DigitalLibraryUtil.CSDLSEARCHTITLE);
				String magazinesDescription = ParamUtil.getString(actionRequest, "magazinesDescription", DigitalLibraryUtil.MAGAZINESDESCRIPTION);
				String transactionsDescription = ParamUtil.getString(actionRequest, "transactionsDescription", DigitalLibraryUtil.TRANSACTIONSDESCRIPTION);
				String lettersDescription = ParamUtil.getString(actionRequest, "lettersDescription", DigitalLibraryUtil.LETTERSDESCRIPTION);
				String proceedingsDescription = ParamUtil.getString(actionRequest, "proceedingsDescription", DigitalLibraryUtil.PROCEEDINGSDESCRIPTION);
				String prePrintsDescription = ParamUtil.getString(actionRequest, "prePrintsDescription", DigitalLibraryUtil.PREPRINTSDESCRIPTION);
				String rapidPostsDescription = ParamUtil.getString(actionRequest, "rapidPostsDescription", DigitalLibraryUtil.RAPIDPOSTSDESCRIPTION);
				String magazineLabel = ParamUtil.getString(actionRequest, "magazineLabel", DigitalLibraryUtil.MAGAZINELABEL);
				String transactionLabel = ParamUtil.getString(actionRequest, "transactionLabel", DigitalLibraryUtil.TRANSACTIONLABEL);
				String letterLabel = ParamUtil.getString(actionRequest, "letterLabel", DigitalLibraryUtil.LETTERLABEL);
				String proceedingLabel = ParamUtil.getString(actionRequest, "proceedingLabel", DigitalLibraryUtil.PROCEEDINGLABEL);
				String resourceLabel = ParamUtil.getString(actionRequest, "resourceLabel", DigitalLibraryUtil.RESOURCELABEL);
				String resourceArticleId = ParamUtil.getString(actionRequest, "resourceArticleId", DigitalLibraryUtil.RESOURCEARTICLEID);
				String resourceDefaultText = ParamUtil.getString(actionRequest, "resourceDefaultText", DigitalLibraryUtil.RESOURCEDEFAULTTEXT);
				String proceedingsUrlSegment = ParamUtil.getString(actionRequest, "proceedingsUrlSegment", DigitalLibraryUtil.PROCEEDINGSURLSEGMENT);
				String volumeAbbrev = ParamUtil.getString(actionRequest, "volumeAbbrev", DigitalLibraryUtil.VOLUMEABBREV);
				String issueAbbrev = ParamUtil.getString(actionRequest, "issueAbbrev", DigitalLibraryUtil.ISSUEABBREV);
				String issueNumberLabel = ParamUtil.getString(actionRequest, "issueNumberLabel", DigitalLibraryUtil.ISSUENUMBERLABEL);
				String prePrintsLabel = ParamUtil.getString(actionRequest, "prePrintsLabel", DigitalLibraryUtil.PREPRINTSLABEL);
				String rapidPostsLabel = ParamUtil.getString(actionRequest, "rapidPostsLabel", DigitalLibraryUtil.RAPIDPOSTSLABEL);
				String csdlHomeLabel = ParamUtil.getString(actionRequest, "csdlHomeLabel", DigitalLibraryUtil.CSDLHOMELABEL);
				String volumeAndIssueLabel = ParamUtil.getString(actionRequest, "volumeAndIssueLabel", DigitalLibraryUtil.VOLUMEANDISSUELABEL);	
				String tableOfContentsLabel = ParamUtil.getString(actionRequest, "tableOfContentsLabel", DigitalLibraryUtil.TABLEOFCONTENTSLABEL);				
				
				String pubType = ParamUtil.getString(actionRequest, "pubType", DigitalLibraryUtil.PUBTYPE);
				String idPrefix = ParamUtil.getString(actionRequest, "idPrefix", DigitalLibraryUtil.IDPREFIX);
				String fileName = ParamUtil.getString(actionRequest, "fileName", DigitalLibraryUtil.FILENAME);
				String fileDetail = ParamUtil.getString(actionRequest, "fileDetail", DigitalLibraryUtil.FILEDETAIL);
				String fileType = ParamUtil.getString(actionRequest, "fileType", DigitalLibraryUtil.FILETYPE);
				String year = ParamUtil.getString(actionRequest, "year", DigitalLibraryUtil.YEAR);
				String catalogNumber = ParamUtil.getString(actionRequest, "catalogNumber", DigitalLibraryUtil.CATALOGNUMBER);
				String volumeNumber = ParamUtil.getString(actionRequest, "volumeNumber", DigitalLibraryUtil.VOLUMENUMBER);
				String issueNumber = ParamUtil.getString(actionRequest, "issueNumber", DigitalLibraryUtil.ISSUENUMBER);
				
				String csdlContextPath = ParamUtil.getString(actionRequest, "csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH);
				String csdlIndexPage = ParamUtil.getString(actionRequest, "csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE);
				String csdlListPage = ParamUtil.getString(actionRequest, "csdlListPage", DigitalLibraryUtil.CSDLLISTPAGE);
				String csdlToCPage = ParamUtil.getString(actionRequest, "csdlToCPage", DigitalLibraryUtil.CSDLTOCPAGE);
				String prePrintsUrl = ParamUtil.getString(actionRequest, "prePrintsUrl", DigitalLibraryUtil.PREPRINTSURL);
				String rapidPostsUrl = ParamUtil.getString(actionRequest, "rapidPostsUrl", DigitalLibraryUtil.RAPIDPOSTSURL);
				String doiUrlPrefix = ParamUtil.getString(actionRequest, "doiUrlPrefix", DigitalLibraryUtil.DOIURLPREFIX);
				
				String prePrintsYear = ParamUtil.getString(actionRequest, "prePrintsYear", DigitalLibraryUtil.PREPRINTSYEAR);
				String prePrintsIssueNumber = ParamUtil.getString(actionRequest, "prePrintsIssueNumber", DigitalLibraryUtil.PREPRINTSISSUENUMBER);
				String rapidPostsYear = ParamUtil.getString(actionRequest, "rapidPostsYear", DigitalLibraryUtil.RAPIDPOSTSYEAR);
				String rapidPostsIssueNumber = ParamUtil.getString(actionRequest, "rapidPostsIssueNumber", DigitalLibraryUtil.RAPIDPOSTSISSUENUMBER);
				
				String buttonAbstract = ParamUtil.getString(actionRequest, "buttonAbstract", DigitalLibraryUtil.BUTTONABSTRACT);
				String buttonFullText = ParamUtil.getString(actionRequest, "buttonFullText", DigitalLibraryUtil.BUTTONFULLTEXT);
				String buttonBuy = ParamUtil.getString(actionRequest, "buttonBuy", DigitalLibraryUtil.BUTTONBUY);
				String buttonPDF = ParamUtil.getString(actionRequest, "buttonPDF", DigitalLibraryUtil.BUTTONPDF);
				String buttonHTML = ParamUtil.getString(actionRequest, "buttonHTML", DigitalLibraryUtil.BUTTONHTML);
				String buttonXPLORE = ParamUtil.getString(actionRequest, "buttonXPLORE", DigitalLibraryUtil.BUTTONXPLORE);
				String buttonOpenUrl = ParamUtil.getString(actionRequest, "buttonOpenUrl", DigitalLibraryUtil.BUTTONOPENURL);

				PortletPreferences prefs = actionRequest.getPreferences();
				prefs.setValue("portletMode", portletMode);
				prefs.setValue("modifiedByUserId", modifiedByUserId);

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
				
				prefs.setValue("innerMargins", innerMargins);
				prefs.setValue("restAPI", restAPI);
				prefs.setValue("homePageFeaturedSliderDelay", homePageFeaturedSliderDelay);
								
				prefs.setValue("mags", mags);
				prefs.setValue("trans", trans);
				prefs.setValue("letters", letters);
				prefs.setValue("proceedings", proceedings);
				prefs.setValue("prePrints", prePrints);
				prefs.setValue("rapidPosts", rapidPosts);
				
				prefs.setValue("csdlHeaderTitle", csdlHeaderTitle);
				prefs.setValue("csdlHeaderIntroduction", csdlHeaderIntroduction);
				prefs.setValue("csdlHeaderIntroductionImagePath", csdlHeaderIntroductionImagePath);
				prefs.setValue("subscriptionBlurb", subscriptionBlurb);
				prefs.setValue("csdlSearchTitle", csdlSearchTitle);
				prefs.setValue("magazinesDescription", magazinesDescription);
				prefs.setValue("transactionsDescription", transactionsDescription);
				prefs.setValue("lettersDescription", lettersDescription);
				prefs.setValue("proceedingsDescription", proceedingsDescription);
				prefs.setValue("prePrintsDescription", prePrintsDescription);
				prefs.setValue("rapidPostsDescription", rapidPostsDescription);
				prefs.setValue("magazineLabel", magazineLabel);
				prefs.setValue("transactionLabel", transactionLabel);
				prefs.setValue("letterLabel", letterLabel);
				prefs.setValue("proceedingLabel", proceedingLabel);
				prefs.setValue("resourceLabel", resourceLabel);
				prefs.setValue("resourceArticleId", resourceArticleId);
				prefs.setValue("resourceDefaultText", resourceDefaultText);
				prefs.setValue("proceedingsUrlSegment", proceedingsUrlSegment);
				prefs.setValue("volumeAbbrev", volumeAbbrev);
				prefs.setValue("issueAbbrev", issueAbbrev);
				prefs.setValue("issueNumberLabel", issueNumberLabel);
				prefs.setValue("prePrintsLabel", prePrintsLabel);
				prefs.setValue("rapidPostsLabel", rapidPostsLabel);
				prefs.setValue("csdlHomeLabel", csdlHomeLabel);
				prefs.setValue("volumeAndIssueLabel", volumeAndIssueLabel);
				prefs.setValue("tableOfContentsLabel", tableOfContentsLabel);				
								
				prefs.setValue("pubType", pubType);
				prefs.setValue("idPrefix", idPrefix);
				prefs.setValue("fileName", fileName);
				prefs.setValue("fileDetail", fileDetail);
				prefs.setValue("fileType", fileType);
				prefs.setValue("year", year);
				prefs.setValue("catalogNumber", catalogNumber);
				prefs.setValue("volumeNumber", volumeNumber);
				prefs.setValue("issueNumber", issueNumber);
				
				prefs.setValue("csdlContextPath", csdlContextPath);
				prefs.setValue("csdlIndexPage", csdlIndexPage);
				prefs.setValue("csdlListPage", csdlListPage);
				prefs.setValue("csdlToCPage", csdlToCPage);
				prefs.setValue("prePrintsUrl", prePrintsUrl);
				prefs.setValue("rapidPostsUrl", rapidPostsUrl);
				prefs.setValue("doiUrlPrefix", doiUrlPrefix);
				
				prefs.setValue("prePrintsYear", prePrintsYear);
				prefs.setValue("prePrintsIssueNumber", prePrintsIssueNumber);
				prefs.setValue("rapidPostsYear", rapidPostsYear);
				prefs.setValue("rapidPostsIssueNumber", rapidPostsIssueNumber);
				
				prefs.setValue("buttonAbstract", buttonAbstract);
				prefs.setValue("buttonFullText", buttonFullText);
				prefs.setValue("buttonBuy", buttonBuy);
				prefs.setValue("buttonPDF", buttonPDF);
				prefs.setValue("buttonHTML", buttonHTML);
				prefs.setValue("buttonXPLORE", buttonXPLORE);
				prefs.setValue("buttonOpenUrl", buttonOpenUrl);

				prefs.store();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
