package org.ieee.cnp.util;

import java.util.*;

import javax.portlet.PortletPreferences;


public class ContentListUtil {
	
	public static final String CONFIG = "CONFIG";
	public static final String MODE   = "DEACTIVATED";
	public static final String USERID = "";
	
	public static final String CSSBLOCK  = "[]";
	public static final String HTMLBLOCK = "[]";	

	public static final String CONTENTLISTDATA   = "[]";
	public static final String UILAYOUT          = "standard";
	public static final String PERPAGE           = "5";
	public static final String PAGINATION        = "YES";
	public static final String TITLEVISIBLE      = "NO";
	public static final String TITLEOFLIST       = "";
	public static final String TITLETOPMARGIN    = "0";
	public static final String TITLEBOTTOMMARGIN = "0";
	public static final String TITLECOLOR        = "000000";
	public static final String TITLEFONT         = "bold 16px arial, helvetica, sans-serif";
	public static final String FILTERING         = "YES";
	public static final String SCROLLDURATION    = "500";
	public static final String INITIALCHUNK      = "50";
	public static final String ASYNCCHUNK        = "200";
	public static final String RECORDSTOPULL     = "200";
	public static final String PREVTEXT          = "PREV";
	public static final String NEXTTEXT          = "NEXT";
	public static final String SEARCHINPUTTEXT   = "Search this list";

	public static final String TITLEBORDERCOLOR  = "FFCC33";
	public static final String TITLEBACKGRCOLOR  = "FFCC33";
	public static final String TITLETOPLEFTRAD   = "5";
	public static final String TITLEBOTLEFTRAD   = "0";
	public static final String TITLETOPRGHTRAD   = "5";
	public static final String TITLEBOTRGHTRAD   = "0";

	public static final String PAGINATIONBORDERCOLOR  = "CCCCCC";
	public static final String PAGINATIONBACKGRCOLOR  = "CCCCCC";

	public static final String PREVIOUSTOPLEFTRAD   = "5";
	public static final String PREVIOUSBOTLEFTRAD   = "5";
	public static final String PREVIOUSTOPRGHTRAD   = "0";
	public static final String PREVIOUSBOTRGHTRAD   = "0";

	public static final String NEXTTOPLEFTRAD   = "0";
	public static final String NEXTBOTLEFTRAD   = "0";
	public static final String NEXTTOPRGHTRAD   = "5";
	public static final String NEXTBOTRGHTRAD   = "5";

	public static final String PAGINATIONWIDTH  = "60";
	public static final String PAGINATIONHEIGHT = "22";
	public static final String PAGINATIONOFFSET = "6";

	public static final String PORTLETBORDERCOLORTOP    = "CCCCCC";
	public static final String PORTLETBORDERCOLORRIGHT  = "CCCCCC";
	public static final String PORTLETBORDERCOLORBOTTOM = "CCCCCC";
	public static final String PORTLETBORDERCOLORLEFT   = "CCCCCC";
	public static final String PORTLETBORDERPIXELTOP    = "1";
	public static final String PORTLETBORDERPIXELRIGHT  = "1";
	public static final String PORTLETBORDERPIXELBOTTOM = "1";
	public static final String PORTLETBORDERPIXELLEFT   = "1";	
	public static final String PORTLETBACKGROUNDCOLOR   = "FFFFFF";
	public static final String PORTLETTOPLEFTRADIUS     = "0";
	public static final String PORTLETBOTTOMLEFTRADIUS  = "5";
	public static final String PORTLETTOPRIGHTRADIUS    = "0";
	public static final String PORTLETBOTTOMRIGHTRADIUS = "5";
	
	public static final String PORTLETCONTAINERHEIGHT   = "800";
	public static final String PORTLETCONTAINERITEMCSS  = "border-top: 1px solid #BBBBBB;";

	public static final String PAGEMODE                  = "new";
	public static final String PAGEMODETARGET            = "";
	public static final String URLTARGETNAME             = "content";
	public static final String CHANNELVOCABULARYNAME     = "Computing Now Channels";
	public static final String CONTENTTYPEVOCABULARYNAME = "Content Types";
	public static final String RESTAPI                   = "/portal/web/computingnow/rest/-/api/";
	public static final String PROPERTIESFILE			 = "ComputingNow.txt";
	public static final String PUBLICSERVLETMAPPING      = "/portal/web";
	
	public static final String ARTICLEIMAGEPATH  = "/portal/image/image_gallery";
	public static final String BLOGIMAGEPATH     = "/cms/Computer.org/ComputingNow/homepage/blog/logos";
	public static final String SUPPLEMENT        = "";
	public static final String DEFAULTIMAGEPATH  = "/documents/16228/4e75d1a3-0a5e-4911-8107-d68b0ca232c6";
	public static final String DISPLAYDATEFORMAT = "EEEE, MMM d, yyyy";
	
	public static final String SUBCATEGORIES = "";
	
	public static final String SHOWINTRO = "ON";

	
	public static void putPortletPreferencesIntoModel(PortletPreferences prefs, Map<String,Object> model) {

		try {

			model.put("portletMode", prefs.getValue("portletMode", MODE));
			model.put("modifiedByUserId", prefs.getValue("modifiedByUserId", USERID));
			
			model.put("contentListData", prefs.getValue("contentListData", CONTENTLISTDATA));
			model.put("uiLayout", prefs.getValue("uiLayout", UILAYOUT));
			model.put("perPage", prefs.getValue("perPage", PERPAGE));
			model.put("pagination", prefs.getValue("pagination", PAGINATION));
			model.put("titleVisible", prefs.getValue("titleVisible", TITLEVISIBLE));
			model.put("titleOfList", prefs.getValue("titleOfList", TITLEOFLIST));
			model.put("titleTopMargin", prefs.getValue("titleTopMargin", TITLETOPMARGIN));
			model.put("titleBottomMargin", prefs.getValue("titleBottomMargin", TITLEBOTTOMMARGIN));
			model.put("titleColor", prefs.getValue("titleColor", TITLECOLOR));
			model.put("titleFont", prefs.getValue("titleFont", TITLEFONT));
			model.put("filtering", prefs.getValue("filtering", FILTERING));
			model.put("scrollDuration", prefs.getValue("scrollDuration", SCROLLDURATION));
			model.put("initialChunk", prefs.getValue("initialChunk", INITIALCHUNK));
			model.put("asyncChunk", prefs.getValue("asyncChunk", ASYNCCHUNK));
			model.put("recordsToPull", prefs.getValue("recordsToPull", RECORDSTOPULL));
			model.put("nextText", prefs.getValue("nextText", NEXTTEXT));
			model.put("prevText", prefs.getValue("prevText", PREVTEXT));
			model.put("searchInputText", prefs.getValue("searchInputText", SEARCHINPUTTEXT));

			model.put("titleBorderColor", prefs.getValue("titleBorderColor", TITLEBORDERCOLOR));
			model.put("titleBackgroundColor", prefs.getValue("titleBackgroundColor", TITLEBACKGRCOLOR));

			model.put("titleTopLeftRadius", prefs.getValue("titleTopLeftRadius", TITLETOPLEFTRAD));
			model.put("titleBottomLeftRadius", prefs.getValue("titleBottomLeftRadius", TITLEBOTLEFTRAD));
			model.put("titleTopRightRadius", prefs.getValue("titleTopRightRadius", TITLETOPRGHTRAD));
			model.put("titleBottomRightRadius", prefs.getValue("titleBottomRightRadius", TITLEBOTRGHTRAD));

			model.put("paginationBorderColor", prefs.getValue("paginationBorderColor", PAGINATIONBORDERCOLOR));
			model.put("paginationBackgroundColor", prefs.getValue("paginationBackgroundColor", PAGINATIONBACKGRCOLOR));	

			model.put("previousTopLeftRadius", prefs.getValue("previousTopLeftRadius", PREVIOUSTOPLEFTRAD));
			model.put("previousBottomLeftRadius", prefs.getValue("previousBottomLeftRadius", PREVIOUSBOTLEFTRAD));
			model.put("previousTopRightRadius", prefs.getValue("previousTopRightRadius", PREVIOUSTOPRGHTRAD));
			model.put("previousBottomRightRadius", prefs.getValue("previousBottomRightRadius", PREVIOUSBOTRGHTRAD));

			model.put("nextTopLeftRadius", prefs.getValue("nextTopLeftRadius", NEXTTOPLEFTRAD));
			model.put("nextBottomLeftRadius", prefs.getValue("nextBottomLeftRadius", NEXTBOTLEFTRAD));
			model.put("nextTopRightRadius", prefs.getValue("nextTopRightRadius", NEXTTOPRGHTRAD));
			model.put("nextBottomRightRadius", prefs.getValue("nextBottomRightRadius", NEXTBOTRGHTRAD));

			model.put("paginationWidth", prefs.getValue("paginationWidth", PAGINATIONWIDTH));
			model.put("paginationHeight", prefs.getValue("paginationHeight", PAGINATIONHEIGHT));
			model.put("paginationOffset", prefs.getValue("paginationOffset", PAGINATIONOFFSET));

			model.put("portletBorderColorTop", prefs.getValue("portletBorderColorTop", PORTLETBORDERCOLORTOP));
			model.put("portletBorderColorRight", prefs.getValue("portletBorderColorRight", PORTLETBORDERCOLORRIGHT));
			model.put("portletBorderColorBottom", prefs.getValue("portletBorderColorBottom", PORTLETBORDERCOLORBOTTOM));
			model.put("portletBorderColorLeft", prefs.getValue("portletBorderColorLeft", PORTLETBORDERCOLORLEFT));			
			model.put("portletBorderPixelTop", prefs.getValue("portletBorderPixelTop", PORTLETBORDERPIXELTOP));
			model.put("portletBorderPixelRight", prefs.getValue("portletBorderPixelRight", PORTLETBORDERPIXELRIGHT));
			model.put("portletBorderPixelBottom", prefs.getValue("portletBorderPixelBottom", PORTLETBORDERPIXELBOTTOM));
			model.put("portletBorderPixelLeft", prefs.getValue("portletBorderPixelLeft", PORTLETBORDERPIXELLEFT));			
			model.put("portletBackgroundColor", prefs.getValue("portletBackgroundColor", PORTLETBACKGROUNDCOLOR));
			model.put("portletTopLeftRadius", prefs.getValue("portletTopLeftRadius", PORTLETTOPLEFTRADIUS));
			model.put("portletBottomLeftRadius", prefs.getValue("portletBottomLeftRadius", PORTLETBOTTOMLEFTRADIUS));
			model.put("portletTopRightRadius", prefs.getValue("portletTopRightRadius", PORTLETTOPRIGHTRADIUS));
			model.put("portletBottomRightRadius", prefs.getValue("portletBottomRightRadius", PORTLETBOTTOMRIGHTRADIUS));
			
			model.put("portletContainerHeight", prefs.getValue("portletContainerHeight", PORTLETCONTAINERHEIGHT));
			model.put("portletContainerItemCSS", prefs.getValue("portletContainerItemCSS", PORTLETCONTAINERITEMCSS));

			model.put("pageMode", prefs.getValue("pageMode", PAGEMODE));
			model.put("pageModeTarget", prefs.getValue("pageModeTarget", PAGEMODETARGET));
			model.put("urlTargetName", prefs.getValue("urlTargetName", URLTARGETNAME));
			model.put("channelVocabularyName", prefs.getValue("channelVocabularyName", CHANNELVOCABULARYNAME));
			model.put("contentTypeVocabularyName", prefs.getValue("contentTypeVocabularyName", CONTENTTYPEVOCABULARYNAME));
			model.put("restAPI", prefs.getValue("restAPI", RESTAPI));
			model.put("propertiesFile", prefs.getValue("propertiesFile", PROPERTIESFILE));
			model.put("publicServletMapping", prefs.getValue("publicServletMapping", PUBLICSERVLETMAPPING));
			
			model.put("articleImagePath", prefs.getValue("articleImagePath", ARTICLEIMAGEPATH));
			model.put("blogImagePath", prefs.getValue("blogImagePath", BLOGIMAGEPATH));
			model.put("supplement", prefs.getValue("supplement", SUPPLEMENT));
			
			model.put("defaultImagePath", prefs.getValue("defaultImagePath", DEFAULTIMAGEPATH));
			model.put("displayDateFormat", prefs.getValue("displayDateFormat", DISPLAYDATEFORMAT));
			
			model.put("subCategories", prefs.getValue("subCategories", SUBCATEGORIES));
			
			model.put("showIntro", prefs.getValue("showIntro", SHOWINTRO));

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}