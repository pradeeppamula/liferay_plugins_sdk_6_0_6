package org.ieeecs.csdl.presentation.controller;

import java.util.*;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieeecs.csdl.util.DigitalLibraryUtil;

import org.ieee.common.util.ParamUtil;


import org.springframework.web.portlet.ModelAndView;


public class GenerateJSONStructureController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		String pubType = ParamUtil.getStringParameter(renderRequest, "pubType");
		String output = "<b>Can't Generate JSON Cache File</b>:<br/><br/>Incorrect publication type (case-sensitive) or you don't have access to this functionality. Sorry.";
		
		try {
			
			PortletPreferences prefs = renderRequest.getPreferences();
			
			if ( DigitalLibraryUtil.MAGS.equals(pubType) ||
				 DigitalLibraryUtil.TRANS.equals(pubType) ||
				 DigitalLibraryUtil.LETTERS.equals(pubType) ) {
				
				output = generatePublicationJSON(pubType, output);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		model.put("output", output);
		
		ModelAndView modelAndView = new ModelAndView("Response", model);

		return modelAndView;
	}
	
}