/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.util
 * @created July 9, 2013
 * @description This class will represent the Choice Liferay Model
 */
package org.ieeecs.communities.util;

import org.apache.log4j.Logger;

import java.util.*;

import java.io.StringReader;

import javax.portlet.PortletPreferences;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.liferay.portlet.journal.model.JournalArticle;


public class HomepageAccountUtil {
    static final Logger LOGGER = Logger.getLogger(HomepageAccountUtil.class);

    public static final String DEFAULT_JOURNAL_ARTICLE_CONTENT = "<h2 class=\"text-center\">Welcome to MyHome</h2><p class=\"lead\">Gain access to exclusive webinars only in the topics you care about.</p>";
    public static final String JOURNAL_ARTICLE_CONTENT_NAME = "homepage-account-content";
	public static final String REQUEST_TYPE_NUM_OF_ARTICLES = "numOfArticles";
    public static final String REQUEST_TYPE_USER_PURCHASE_DATA = "LOAD_USER_PURCHASE_DATA";
    public static final String REQUEST_TYPE_UPDATE_USER_PURCHASE_DATA = "UPDATE_USER_PURCHASE_DATA";

	// the amount that meta data will live in the cache
	public static final Long CACHE_AGE_LIMIT = 604800L; // One week measured in SECONDS
	public static final String PORTLET_NAME = "portletName"; // TODO: <-move this to common
	// meta data constants
	public static final String META_PORTLET_NAME = "homepageAccount";
	public static final String META_START_DATE = "startDate";
	public static final String META_END_DATE = "endDate";
	public static final String META_CREATED_DATE = "createdDate";
	
	// configurations
	public static final String CONFIG = "CONFIG";
	public static final String MODE   = "DEACTIVATED";
	public static final String USERID = "";
	public static final String PORTLETBORDERCOLORTOP    = "CCCCCC";
	public static final String PORTLETBORDERCOLORRIGHT  = "CCCCCC";
	public static final String PORTLETBORDERCOLORBOTTOM = "CCCCCC";
	public static final String PORTLETBORDERCOLORLEFT   = "CCCCCC";
	public static final String PORTLETBORDERPIXELTOP    = "0";
	public static final String PORTLETBORDERPIXELRIGHT  = "0";
	public static final String PORTLETBORDERPIXELBOTTOM = "0";
	public static final String PORTLETBORDERPIXELLEFT   = "0";
	public static final String PORTLETBACKGROUNDCOLOR   = "FFFFFF";
	public static final String PORTLETTOPLEFTRADIUS     = "0";
	public static final String PORTLETBOTTOMLEFTRADIUS  = "0";
	public static final String PORTLETTOPRIGHTRADIUS    = "0";
	public static final String PORTLETBOTTOMRIGHTRADIUS = "0";
	public static final String INNERMARGINS        = "0px";

	/**
	 * This function will add the necessary preferences to the 
	 * model for the View.
	 * @param prefs
	 * @param model
	 */
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
		} catch (Exception e) {
            LOGGER.error("A problem occurred when putting the portlet preferences on the model.",  e);
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