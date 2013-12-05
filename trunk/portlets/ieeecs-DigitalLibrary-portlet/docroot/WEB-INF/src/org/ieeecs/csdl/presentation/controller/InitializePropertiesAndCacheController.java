package org.ieeecs.csdl.presentation.controller;

import java.util.*;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.ieeecs.csdl.util.DigitalLibraryUtil;

import org.springframework.web.portlet.ModelAndView;


public class InitializePropertiesAndCacheController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String output = "<b>Can't Initialize the Properties</b>:<br/><br/>You don't have access to this functionality. Sorry.";

		try {
			
			PortletPreferences prefs = renderRequest.getPreferences();
			
			DigitalLibraryUtil.initializeProperties();
			DigitalLibraryUtil.initializeCache();
			output = "Initialization of the Properties is complete.";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.put("output", output);

		ModelAndView modelAndView = new ModelAndView("Response", model);

		return modelAndView;
	}
	
}