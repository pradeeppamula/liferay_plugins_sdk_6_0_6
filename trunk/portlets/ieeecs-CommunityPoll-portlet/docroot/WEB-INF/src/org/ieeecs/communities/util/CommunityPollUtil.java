package org.ieeecs.communities.util;

import java.io.StringReader;
import java.util.*;

import javax.portlet.PortletPreferences;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.liferay.portlet.journal.model.JournalArticle;


public class CommunityPollUtil {

	public static final String CONFIG = "CONFIG";
	public static final String MODE   = "DEACTIVATED";
	public static final String USERID = "";

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
	public static final String PORTLETBOTTOMLEFTRADIUS  = "0";
	public static final String PORTLETTOPRIGHTRADIUS    = "0";
	public static final String PORTLETBOTTOMRIGHTRADIUS = "0";
	
	public static final String INNERMARGINS        = "20px";
	public static final String RESTAPI             = "/portal/web/CS-Communities-Help/rest/-/api/";
	public static final String DEFAULTCATEGORYNAME = "Display";
	public static final String RESOURCEPRIMKEY     = "0";	

	
	public static void putPortletPreferencesIntoModel(PortletPreferences prefs, Map<String,Object> model) {

		try {

			model.put("portletMode", prefs.getValue("portletMode", MODE));
			model.put("modifiedByUserId", prefs.getValue("modifiedByUserId", USERID));

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
			
			model.put("innerMargins", prefs.getValue("innerMargins", INNERMARGINS));
			model.put("restAPI", prefs.getValue("restAPI", RESTAPI));
			model.put("defaultCategoryName", prefs.getValue("defaultCategoryName", DEFAULTCATEGORYNAME));
			model.put("resourcePrimKey", prefs.getValue("resourcePrimKey", RESOURCEPRIMKEY));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getArticleContent(JournalArticle article, String defaultContent) {
		
		String content = "";
		
		try {
			
			Date todaysDate = new Date();
			Date expirationDate = null == article.getExpirationDate() ? 
								  new Date(System.currentTimeMillis() + 630720000000L) : 
								  article.getExpirationDate();										
														
			if ( todaysDate.after(expirationDate) ) {				
				content = defaultContent;		
			} else {
				SAXBuilder parser = new SAXBuilder();
				Document document = parser.build(new StringReader(article.getContent()));
				XPath contentXPath = XPath.newInstance("/root/static-content");
				List<?> contentList = contentXPath.selectNodes(document);					

				if ((null != contentList) && (contentList.size() > 0)) {
					Element contentElement = (Element)contentList.get(0);
					content = contentElement.getText();
				}
			} 
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}
}
