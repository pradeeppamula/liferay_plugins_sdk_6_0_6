/**
 * @copyright 2014 IEEE
 * @package org.ieeecs.communities.util
 * @created July 30, 2014
 * @description This class will load serve as a basic utility class for
 * the featured subcontent portlet
 */
package org.ieeecs.communities.util;

import com.liferay.portal.kernel.util.ParamUtil;
import org.ieeecs.communities.bean.CustomPreferences;

import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSFeaturedSubContentUtil {
    public static final String SAVE_CONFIG = "SAVE_CONFIG";
    public static final String EMPTY_JSON = "{}";
	public static final String MODE   = "DEACTIVATED";
	public static final String USERID = "";
    public static final String NUMBER_OF_ITEMS = "1";

    public static List<CustomPreferences> customPreferences = new ArrayList<CustomPreferences>() {
        {
            add(new CustomPreferences("modifiedByUserId", USERID));
            add(new CustomPreferences("numberOfItems", NUMBER_OF_ITEMS));
            add(new CustomPreferences("item1", EMPTY_JSON));
            add(new CustomPreferences("item2", EMPTY_JSON));
            add(new CustomPreferences("item3", EMPTY_JSON));
            add(new CustomPreferences("item4", EMPTY_JSON));
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
            for (CustomPreferences customPref : CSFeaturedSubContentUtil.customPreferences) {
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
     * @return boolean
     * @throws Exception
     */
    public static boolean updatePortletData(ResourceRequest request, String modifiedByUserId) throws Exception {
        boolean retVal = true;
        try {
            // grab the portlet preferences data json off the request
            PortletPreferences prefs = request.getPreferences();
            prefs.setValue("modifiedByUserId", modifiedByUserId);
            prefs.setValue("numberOfItems", ParamUtil.getString(request, "numberOfItems", CSFeaturedSubContentUtil.NUMBER_OF_ITEMS));
            prefs.setValue("item1", ParamUtil.getString(request, "item1", EMPTY_JSON));
            prefs.setValue("item2", ParamUtil.getString(request, "item2", EMPTY_JSON));
            prefs.setValue("item3", ParamUtil.getString(request, "item3", EMPTY_JSON));
            prefs.setValue("item4", ParamUtil.getString(request, "item4", EMPTY_JSON));

            // save the portlet data
            prefs.store();
        } catch (Exception e) {
            retVal = false;
        }
        return retVal;
    }
}