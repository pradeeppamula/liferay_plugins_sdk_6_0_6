package org.ieee.common.presentation.controller;

import com.liferay.portal.util.PortalUtil;

import java.util.Map;

import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

import org.ieee.common.constants.GlobalConstants;
import org.springframework.web.portlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;


@SuppressWarnings("deprecation")
public class BaseController extends SimpleFormController {

	private VelocityConfigurer velocityConfigurer;

	public void setVelocityConfigurer(VelocityConfigurer velocityConfigurer) {
		this.velocityConfigurer = velocityConfigurer;
	}
	
	public VelocityConfigurer getVelocityConfigurer() {
		return velocityConfigurer;
	}

	public HttpServletRequest getServletRequest(RenderRequest request) {
		return PortalUtil.getHttpServletRequest(request);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getSessionMap(RenderRequest request) {
		return (Map<String,Object>) getServletRequest(request).getSession().getAttribute(GlobalConstants.SESSIONMAP);
	}
}