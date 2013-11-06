/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.util
 * @created October 1, 2013
 * @description This class will function as the utility class for the purchase bundle portlet
 */
package org.ieeecs.communities.util;

import org.apache.log4j.Logger;

import javax.portlet.PortletPreferences;
import java.util.Map;

public class HomepagePurchaseUtil {
    static final Logger LOGGER = Logger.getLogger(HomepagePurchaseUtil.class);
    // email related properties
    public static final String EMAIL_FILE_PATH          = "/WEB-INF/content";
    public static final String EMAIL_FILE_NAME          = "purchase_email_template.html";
    public static final String EMAIL_TO                 = "jcruz@computer.org";
    //public static final String EMAIL_TO                 = "trainingpartners@computer.org";
    public static final String EMAIL_FROM               = "noreply.ieeecs@gmail.com";
//    public static final String EMAIL_USER               = "demo1.ieeecs@gmail.com";
    public static final String EMAIL_USER               = "itcore.ieeecs@gmail.com";
//    public static final String EMAIL_PASSWORD           = "Pa55word123";
    public static final String EMAIL_PASSWORD           = "0utsideTheB0x";
    public static final String EMAIL_HOST               = "imap.gmail.com";
    public static final String EMAIL_NAME_TOKEN         = "EMAIL_NAME_TOKEN";
    public static final String EMAIL_TITLE_TOKEN        = "EMAIL_TITLE_TOKEN";
    public static final String EMAIL_COMPANY_TOKEN      = "EMAIL_COMPANY_TOKEN";
    public static final String EMAIL_PHONE_TOKEN        = "EMAIL_PHONE_TOKEN";
    public static final String EMAIL_EMAIL_TOKEN        = "EMAIL_EMAIL_TOKEN";
    public static final String EMAIL_BUNDLE_TYPE_TOKEN  = "EMAIL_BUNDLE_TYPE_TOKEN";

    // meta data constants
	public static final String META_PORTLET_NAME = "homepagePurchase";
    public static final String REQUEST_TYPE_PURCHASE_BUNDLE = "PURCHASE_BUNDLE";
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
	public static final String INNERMARGINS        = "0px";;

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
}