package org.ieee.cnp.util;

import java.util.*;

import javax.portlet.PortletPreferences;


public class ContentAdvancedUtil {

	public static final String CONFIG = "CONFIG";
	public static final String MODE   = "DEACTIVATED";
	public static final String USERID = "";

	public static final String CSSBLOCK = "[]";
	public static final String HTMLBLOCK = "[]";
	public static final String JSBLOCKINTERNALPRE = "[]";
	public static final String JSBLOCKINTERNALPOST = "[]";
	public static final String JSBLOCKEXTERNALPRE = "";
	public static final String JSBLOCKEXTERNALPOST = "";
	public static final String SHOWINTRO = "ON";

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
	public static final String PORTLETHEIGHT            = "200";

	public static void putPortletPreferencesIntoModel(PortletPreferences prefs, Map<String,Object> model) {

		try {

			model.put("portletMode", prefs.getValue("portletMode", MODE));
			model.put("modifiedByUserId", prefs.getValue("modifiedByUserId", USERID));

			model.put("jsBlockExternalPre", prefs.getValue("jsBlockExternalPre", JSBLOCKEXTERNALPRE));
			model.put("jsBlockExternalPost", prefs.getValue("jsBlockExternalPost", JSBLOCKEXTERNALPOST));
			
			model.put("showIntro", prefs.getValue("showIntro", SHOWINTRO));

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
			model.put("portletHeight", prefs.getValue("portletHeight", PORTLETHEIGHT));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}