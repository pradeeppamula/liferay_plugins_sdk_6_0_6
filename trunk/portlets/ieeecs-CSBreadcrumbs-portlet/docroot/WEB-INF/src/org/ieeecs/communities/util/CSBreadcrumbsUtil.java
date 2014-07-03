/**
 * @copyright 2014 IEEE
 * @package org.ieeecs.communities.util
 * @created June 30, 2014
 * @description This class will load serve as a basic utility class for
 * the breadcrumbs portlet
 */
package org.ieeecs.communities.util;

import com.liferay.portal.kernel.util.ParamUtil;
import org.ieeecs.communities.bean.CustomPreferences;

import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSBreadcrumbsUtil {
    public static final String SAVE_CONFIG = "SAVE_CONFIG";
	public static final String MODE   = "DEACTIVATED";
	public static final String USERID = "";

    public static List<CustomPreferences> customPreferences = new ArrayList<CustomPreferences>() {
        {
            add(new CustomPreferences("modifiedByUserId", USERID));
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
            for (CustomPreferences customPref : CSBreadcrumbsUtil.customPreferences) {
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
     * @return boolean
     * @throws Exception
     */
    public static boolean updatePortletData(ResourceRequest request, String modifiedByUserId, String instanceId) throws Exception {
        boolean retVal = true;
        try {
            // grab the portlet preferences data json off the request
            PortletPreferences prefs = request.getPreferences();
            prefs.setValue("modifiedByUserId", modifiedByUserId);
            // save the portlet data
            prefs.store();
        } catch (Exception e) {
            retVal = false;
        }
        return retVal;
    }
}