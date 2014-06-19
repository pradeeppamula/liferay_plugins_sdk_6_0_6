/**
 * @copyright 2014 IEEE
 * @package org.ieee.cnp.util
 * @created May 14, 2014
 * @description This class will load serve as a basic utility class for
 * the content advanced portlet
 */
package org.ieee.cnp.util;

import com.liferay.portal.kernel.util.ParamUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.ieee.cnp.bean.CustomPreferences;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSContentAdvancedUtil {
    public static final String SAVE_CONFIG = "SAVE_CONFIG";
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

    public static List<CustomPreferences> customPreferences = new ArrayList<CustomPreferences>() {
        {
            add(new CustomPreferences("portletMode", MODE));
            add(new CustomPreferences("modifiedByUserId", USERID));
            add(new CustomPreferences("showIntro", SHOWINTRO));
            add(new CustomPreferences("portletBorderColorTop", PORTLETBORDERCOLORTOP));
            add(new CustomPreferences("portletBorderColorRight", PORTLETBORDERCOLORRIGHT));
            add(new CustomPreferences("portletBorderColorBottom", PORTLETBORDERCOLORBOTTOM));
            add(new CustomPreferences("portletBorderColorLeft", PORTLETBORDERCOLORLEFT));
            add(new CustomPreferences("portletBorderPixelTop", PORTLETBORDERPIXELTOP));
            add(new CustomPreferences("portletBorderPixelRight", PORTLETBORDERPIXELRIGHT));
            add(new CustomPreferences("portletBorderPixelBottom", PORTLETBORDERPIXELBOTTOM));
            add(new CustomPreferences("portletBorderPixelLeft", PORTLETBORDERPIXELLEFT));
            add(new CustomPreferences("portletBackgroundColor",PORTLETBACKGROUNDCOLOR));
            add(new CustomPreferences("portletTopLeftRadius", PORTLETTOPLEFTRADIUS));
            add(new CustomPreferences("portletBottomLeftRadius", PORTLETBOTTOMLEFTRADIUS));
            add(new CustomPreferences("portletTopRightRadius",PORTLETTOPRIGHTRADIUS));
            add(new CustomPreferences("portletBottomRightRadius", PORTLETBOTTOMRIGHTRADIUS));
            add(new CustomPreferences("portletHeight",PORTLETHEIGHT));
            add(new CustomPreferences("cssBlock", CSSBLOCK));
            add(new CustomPreferences("htmlBlock", HTMLBLOCK));
            add(new CustomPreferences("jsBlockExternalPre", JSBLOCKEXTERNALPRE));
            add(new CustomPreferences("jsBlockExternalPost", JSBLOCKEXTERNALPOST));
            add(new CustomPreferences("jsBlockInternalPre", JSBLOCKINTERNALPRE));
            add(new CustomPreferences("jsBlockInternalPost", JSBLOCKINTERNALPOST));
        }
    };

    /**
     * This function will iterate over a list of custom preferences and
     * store them on a view model.
     * @param prefs
     * @param model
     */
	public static void putPortletPreferencesIntoModel(PortletPreferences prefs, Map<String,Object> model) {
		try {
            for (CustomPreferences customPref : CSContentAdvancedUtil.customPreferences) {
                model.put(customPref.getName(), prefs.getValue(customPref.getName(), customPref.getDefaultValue()));
            }
		} catch (Exception e) {
            e.printStackTrace();
        }
	}

    /**
     * Update the portlet preferences in the database.
     * @param request
     * @param modifiedByUserId
     * @param instanceId
     * @param isConfigureMode
     * @return
     * @throws Exception
     */
    public static boolean updatePortletData(ResourceRequest request, String modifiedByUserId, String instanceId, boolean isConfigureMode) throws Exception {
        boolean retVal = true;
        try {
            // grab the portlet preferences data json off the request
            PortletPreferences prefs = request.getPreferences();
            prefs.setValue("modifiedByUserId", modifiedByUserId);
            prefs.setValue("cssBlock",  ParamUtil.getString(request, "cssBlock_"+instanceId, CSContentAdvancedUtil.CSSBLOCK));
            prefs.setValue("htmlBlock", ParamUtil.getString(request, "htmlBlock_"+instanceId, CSContentAdvancedUtil.HTMLBLOCK));
            prefs.setValue("jsBlockInternalPre", ParamUtil.getString(request, "jsBlockInternalPre_"+instanceId, CSContentAdvancedUtil.JSBLOCKINTERNALPRE));
            prefs.setValue("jsBlockInternalPost",  ParamUtil.getString(request, "jsBlockInternalPost_"+instanceId, CSContentAdvancedUtil.JSBLOCKINTERNALPOST));
            prefs.setValue("jsBlockExternalPre",  ParamUtil.getString(request, "jsBlockExternalPre_"+instanceId, CSContentAdvancedUtil.JSBLOCKEXTERNALPRE));
            prefs.setValue("jsBlockExternalPost", ParamUtil.getString(request, "jsBlockExternalPost_"+instanceId, CSContentAdvancedUtil.JSBLOCKEXTERNALPOST));

            // if we are saving from the configure page, we can save all the preferences
            if(isConfigureMode) {
                prefs.setValue("portletMode", ParamUtil.getString(request, "portletMode_" + instanceId, CSContentAdvancedUtil.MODE));
                prefs.setValue("showIntro", ParamUtil.getString(request, "showIntro_" + instanceId, CSContentAdvancedUtil.SHOWINTRO).toUpperCase());
                prefs.setValue("portletBorderColorTop", ParamUtil.getString(request, "portletBorderColorTop_" + instanceId, CSContentAdvancedUtil.PORTLETBORDERCOLORTOP));
                prefs.setValue("portletBorderColorRight", ParamUtil.getString(request, "portletBorderColorRight_" + instanceId, CSContentAdvancedUtil.PORTLETBORDERCOLORRIGHT));
                prefs.setValue("portletBorderColorBottom", ParamUtil.getString(request, "portletBorderColorBottom_" + instanceId, CSContentAdvancedUtil.PORTLETBORDERCOLORBOTTOM));
                prefs.setValue("portletBorderColorLeft", ParamUtil.getString(request, "portletBorderColorLeft_" + instanceId, CSContentAdvancedUtil.PORTLETBORDERCOLORLEFT));
                prefs.setValue("portletBorderPixelTop", ParamUtil.getString(request, "portletBorderPixelTop_" + instanceId, CSContentAdvancedUtil.PORTLETBORDERPIXELTOP));
                prefs.setValue("portletBorderPixelRight", ParamUtil.getString(request, "portletBorderPixelRight_" + instanceId, CSContentAdvancedUtil.PORTLETBORDERPIXELRIGHT));
                prefs.setValue("portletBorderPixelBottom", ParamUtil.getString(request, "portletBorderPixelBottom_" + instanceId, CSContentAdvancedUtil.PORTLETBORDERPIXELBOTTOM));
                prefs.setValue("portletBorderPixelLeft", ParamUtil.getString(request, "portletBorderPixelLeft_" + instanceId, CSContentAdvancedUtil.PORTLETBORDERPIXELLEFT));
                prefs.setValue("portletBackgroundColor", ParamUtil.getString(request, "portletBackgroundColor_" + instanceId, CSContentAdvancedUtil.PORTLETBACKGROUNDCOLOR));
                prefs.setValue("portletTopLeftRadius", ParamUtil.getString(request, "portletTopLeftRadius_" + instanceId, CSContentAdvancedUtil.PORTLETTOPLEFTRADIUS));
                prefs.setValue("portletBottomLeftRadius", ParamUtil.getString(request, "portletBottomLeftRadius_" + instanceId, CSContentAdvancedUtil.PORTLETBOTTOMLEFTRADIUS));
                prefs.setValue("portletTopRightRadius", ParamUtil.getString(request, "portletTopRightRadius_" + instanceId, CSContentAdvancedUtil.PORTLETTOPRIGHTRADIUS));
                prefs.setValue("portletBottomRightRadius", ParamUtil.getString(request, "portletBottomRightRadius_" + instanceId, CSContentAdvancedUtil.PORTLETBOTTOMRIGHTRADIUS));
            }
            // save the portlet data
            prefs.store();
        } catch (Exception e) {
            retVal = false;
        }
        return retVal;
    }
}