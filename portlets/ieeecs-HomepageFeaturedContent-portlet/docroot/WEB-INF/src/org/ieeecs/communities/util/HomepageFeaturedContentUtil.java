/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.util
 * @created July 11, 2013
 * @description This class will serve as the utilities class for the Featured Content Portlet.
 */
package org.ieeecs.communities.util;

import java.util.*;

import javax.portlet.PortletPreferences;

public class HomepageFeaturedContentUtil {


	// meta data constants
	public static final String META_PORTLET_NAME = "homepageFeaturedContent";

	public static final String REQUEST_TYPE_LOAD_CONTENT = "loadContent";
	public static final String REQUEST_TYPE_SAVE_CONTENT = "saveContent";
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
			// TODO: handle error gracefully
			e.printStackTrace();
		}
	}
	
}