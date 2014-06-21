package org.ieee.cnp.presentation.controller;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.CSDLUtil;
import org.ieee.common.util.ParamUtil;

import org.springframework.web.portlet.ModelAndView;


public class CSDLAbstractController extends BaseController {

	private static final String STARTDELIM  = "~~~~~~";
	private static final String ENDDELIM    = "^^^^^^";

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		String output = "";

		try {
	
			String fileID   = ParamUtil.getStringParameter(renderRequest, "id");
			String type     = ParamUtil.getStringParameter(renderRequest, "type");
			String fileName = ParamUtil.getStringParameter(renderRequest, "fileName");
			String fileExt  = ParamUtil.getStringParameter(renderRequest, "fileExt");
			
			if ( null != fileID && !"".equals(fileID) ) {
				output = CSDLUtil.getCSDLAbstract(fileID, type, fileName, fileExt, getVelocityConfigurer());
			}
					
		} catch (Exception e) {
			output = "There was an error in retrieving the Digital Library content.";
			e.printStackTrace();
		}

		model.put("output", STARTDELIM + output + ENDDELIM);
		ModelAndView modelAndView = new ModelAndView("Output", model);

		return modelAndView;
	}

}
