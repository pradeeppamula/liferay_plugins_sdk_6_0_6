/**
 * @copyright 2013 IEEE
 * @package org.ieeecs.communities.util
 * @created October 1, 2013
 * @description This class will function as the utility class for the BundleOrgAdmin bundle portlet
 */
package org.ieeecs.communities.util;

import org.apache.log4j.Logger;

import javax.portlet.PortletPreferences;
import java.util.Map;

public class HomepageBundleOrgAdminUtil {
    static final Logger LOGGER = Logger.getLogger(HomepageBundleOrgAdminUtil.class);
    public static final String[] VALID_FILE_EXTENSIONS = {"txt", "csv"};
    // meta data constants
	public static final String META_PORTLET_NAME = "homepageBundleOrgAdmin";
    public static final String REQUEST_TYPE_LOAD_ORGANIZATIONS = "LOAD_ORGANIZATIONS";
    public static final String REQUEST_TYPE_LOAD_USERS = "LOAD_USERS";
    public static final String REQUEST_TYPE_REMOVE_USER = "REMOVE_USER";
    public static final String REQUEST_TYPE_UPDATE_ORGANIZATION = "UPDATE_ORGANIZATION";
    public static final String REQUEST_TYPE_SAVE_ORGANIZATION_BUNDLES = "SAVE_ORGANIZATION_BUNDLES";
    public static final String REQUEST_TYPE_ADD_ORGANIZATION = "ADD_ORGANIZATION";
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
}