package org.ieeecs.service.presentation.controller;

import java.util.*;

import javax.portlet.*;

import org.springframework.web.portlet.ModelAndView;


public class ThemeServicesController extends BaseController {
	
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		try {			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("Home", model);

		return modelAndView;
	}

}
