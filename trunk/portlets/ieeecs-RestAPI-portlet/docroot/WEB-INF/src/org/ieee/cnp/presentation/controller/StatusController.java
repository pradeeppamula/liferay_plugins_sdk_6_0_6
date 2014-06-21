package org.ieee.cnp.presentation.controller;

import com.liferay.portal.util.PortalUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.ParamUtil;

import org.springframework.web.portlet.ModelAndView;


public class StatusController extends BaseController {

	private static final String STARTDELIM  = "~~~~~~";
	private static final String ENDDELIM    = "^^^^^^";
	private static final String SERVER      = "server";
	

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		String output = "";

		try {
			
			String process = ParamUtil.getStringParameter(renderRequest, "process");
			
			if ( SERVER.equalsIgnoreCase(process) ) {
				
				String start = ParamUtil.getStringParameter(renderRequest, "start");
				long startMS = 0L;
				
				if ( null == start || "".equals(start) || "0".equals(start) ) {
					startMS = (new Date()).getTime();
				} else {
					startMS = new Long(start);
				}
				
				String computerAddress = PortalUtil.getComputerAddress();
				String computerName = PortalUtil.getComputerName();				
				String portalURL = PortalUtil.getPortalURL(renderRequest);
				
				Date uptime = PortalUtil.getUptime();
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				sdf.setTimeZone(TimeZone.getTimeZone( "America/Los_Angeles" ));
				String upDate = sdf.format(uptime);
				String current = sdf.format(new Date());
				
				long uptimeDiff = (System.currentTimeMillis() - uptime.getTime());	
				String uptimeFormatted = String.format("%d hour(s), %d minute(s), %d second(s)",
											uptimeDiff/(1000*60*60), (uptimeDiff%(1000*60*60))/(1000*60), ((uptimeDiff%(1000*60*60))%(1000*60))/1000);
				
				/* Also can access the Portal Properties if we needed:   Properties portalProperties = PortalUtil.getPortalProperties(); */
			
				output = "{\"machineIP\":\"" + computerAddress + 
						"\", \"machineName\":\"" + computerName + 
						"\", \"url\":\"" + portalURL + 
						"\", \"started\":\"" + upDate + 
						"\", \"current\":\"" + current + 
						"\", \"uptime\":\"" + uptimeFormatted + 
						"\", \"uptimeMS\":\"" + uptimeDiff + 
						"\", \"startMS\":" + startMS + "}";

			}
	
		} catch (Exception e) {
			output = "{'status':'error'}";
			e.printStackTrace();
		}

		model.put("output", STARTDELIM + output + ENDDELIM);
		ModelAndView modelAndView = new ModelAndView("Output", model);

		return modelAndView;
	}
}