package org.ieee.common.util;

import com.liferay.portal.util.PortalUtil;

import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import org.springframework.web.portlet.bind.PortletRequestUtils;


public class ParamUtil {

	public static String getStringParameter(RenderRequest renderRequest, String paramName) {
		
		String param = "";

		try {
			
			param = getUrlStringParameter(renderRequest, paramName);
			
			if ( null == param || "".equals(param) ) {
				param = PortletRequestUtils.getStringParameter(renderRequest, paramName); 
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null == param ? "" : param;
	}
	
	public static Integer getIntegerParameter(RenderRequest renderRequest, String paramName) {
		
		Integer param = new Integer(0);
		
		try {
			
			String p = getStringParameter(renderRequest, paramName);
			param = "".equals(p) || !StringUtils.isNumeric(p) ? new Integer(0) : new Integer(p);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return param; 
	}
	
	public static Integer getIntegerParameterZeroOrGreater(RenderRequest renderRequest, String paramName) {
		
		Integer param = getIntegerParameter(renderRequest, paramName);
		return param < 0 ? new Integer(0) : param; 
	}	
	
	public static String getUrlStringParameter(RenderRequest renderRequest, String paramName) {
		
		String param = "";
		
		try {
			
			HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(renderRequest);
			param = PortalUtil.getOriginalServletRequest(servletRequest).getParameter(paramName);
			
			if ( null == param ) {
				param = "";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return param; 
	}
	
	public static Integer getUrlIntegerParameter(RenderRequest renderRequest, String paramName) {
		
		Integer param = new Integer(0);
		
		try {
			
			String p = getUrlStringParameter(renderRequest, paramName);
			param = "".equals(p) || !StringUtils.isNumeric(p) ? new Integer(0) : new Integer(p);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return param; 
	}	
	
	
}