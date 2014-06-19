/**
 * @copyright 2014 IEEE
 * @package org.ieee.cnp.presentation.controller
 * @created May 14, 2014
 * @description This class will handle any requests for configuring
 * the content advanced portlet
 */
package org.ieee.cnp.presentation.controller;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.ieee.cnp.util.CSContentAdvancedUtil;
import org.ieee.common.presentation.controller.BaseController;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;

import javax.portlet.*;
import java.util.HashMap;
import java.util.Map;

public class CSConfigureContentAdvancedController extends BaseController implements ResourceAwareController {
    static final Logger LOGGER = Logger.getLogger(CSContentAdvancedController.class);
    private String instanceId = "";
    private String modifiedByUserId = "";

    /**
     * This function will render the view to configure the portlet.  The function
     * will load up any necessary data and return it to the client via the model.
     * @param renderRequest
     * @param renderResponse
     * @return  ModelAndView
     * @throws Exception
     */
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		boolean fallbackJS = true;
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();
            modifiedByUserId = new Long(themeDisplay.getUserId()).toString();

            // load up all the preferences onto the model (CSS,HTML,JS, etc.)
            CSContentAdvancedUtil.putPortletPreferencesIntoModel(renderRequest.getPreferences(), model);

            // We assume that the theme where this portlet resides doesn't contain all of the required Javascript libraries.
			// If it finds that the "complianceVersion" value is set in the theme's "liferay-look-and-feel.xml" file, then it contains all needed libraries.
			String complianceVersion = themeDisplay.getTheme().getSetting("complianceVersion");
			if ( null != complianceVersion && !"".equals(complianceVersion) ) {
				fallbackJS = false;
			}

		} catch (Exception e) {
            // gracefully handle exception and put on model
            model.put("error", "There was a problem loading your account information.  Please reload the page or contact help@computer.org.");
            LOGGER.error("A problem occurred when handling a request: " + ExceptionUtils.getRootCauseMessage(e));
		}
		model.put("id", instanceId);
		model.put("fallbackJS", fallbackJS);
		return new ModelAndView("Configure", model);
	}

    /**
     * This method will handle any AJAX resource requests from the
     * Home view.
     * @param request
     * @param response
     * @return ModelAndView
     */
    @Override
    public ModelAndView handleResourceRequest(ResourceRequest request,
                                              ResourceResponse response) throws Exception {
        ModelAndView modelAndView = null;
        Map<String,Object> model = new HashMap<String,Object>();
        try{

            // grab the ThemeDisplay that contains all needed information on the user
            // first grab the theme display for the portlet
            if("".equals(this.instanceId)) {
                ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
                String portletId = (String) request.getAttribute(WebKeys.PORTLET_ID);
                int cropHere = portletId.indexOf("_INSTANCE_");
                instanceId = "_" + portletId.substring(cropHere + 10);
                modifiedByUserId = new Long(themeDisplay.getUserId()).toString();
            }

            // grab the request type from the request
            String requestType = ParamUtil.getString(request, "requestType_" + instanceId, "");

            // determine which functionality to use based on the request type
            if (CSContentAdvancedUtil.SAVE_CONFIG.equalsIgnoreCase(requestType)) {
                // update the data
                if(CSContentAdvancedUtil.updatePortletData(request, modifiedByUserId, instanceId, true)) {
                    model.put("response", 200);
                } else {
                    model.put("response", 500);
                }
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/json");
        } catch (Exception e) {
            LOGGER.error("A problem occurred when handling a resource request: "   + ExceptionUtils.getRootCauseMessage(e));
        }

        // specify which JSP to render the response to
        modelAndView = new ModelAndView("Response", model);
        return modelAndView;
    }
}