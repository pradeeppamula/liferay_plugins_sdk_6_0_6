package org.ieeecs.service.presentation.controller;

import java.util.*;

import com.liferay.portal.util.PortalUtil;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

import javax.portlet.*;

import javax.servlet.http.HttpServletRequest;

import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;

import org.ieeecs.service.util.ThemeServicesUtil;

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
	
	public static long getTime() {
		return (new Date()).getTime();
	}
	
	public static long getTotalTime(long startTimeMS) {
		return (new Date()).getTime() - startTimeMS;
	}

	public static JSONObject getJSONObject(JSONObject targetJSONObject, String attributeName) {
		return ThemeServicesUtil.getJSONObject(targetJSONObject, attributeName);
	}
	
	public static BasicDBObject getBasicDBObject(BasicDBObject targetBasicDBObject, String attributeName) {
		return ThemeServicesUtil.getBasicDBObject(targetBasicDBObject, attributeName);
	}
	
	public static JSONArray getJSONArray(JSONObject targetJSONObject, String attributeName) {
		return ThemeServicesUtil.getJSONArray(targetJSONObject, attributeName);
	}	
	
	public static BasicDBList getBasicDBList(BasicDBObject targetBasicDBObject, String attributeName) {
		return ThemeServicesUtil.getBasicDBList(targetBasicDBObject, attributeName);
	}		
	
	public static String getString(JSONObject targetJSONObject, String attributeName) {
		return ThemeServicesUtil.getString(targetJSONObject, attributeName);
	}
	
	public static String getString(BasicDBObject targetBasicDBObject, String attributeName) {
		return ThemeServicesUtil.getString(targetBasicDBObject, attributeName);
	}
	
	public static JSONObject jsonObjectFromDotNotation(JSONObject parentJSON, String dotNotation) {
		return ThemeServicesUtil.jsonObjectFromDotNotation(parentJSON, dotNotation);
	}
	
	public static BasicDBObject jsonObjectFromDotNotation(BasicDBObject parentBasicDBObject, String dotNotation) {
		return ThemeServicesUtil.jsonObjectFromDotNotation(parentBasicDBObject, dotNotation);
	}
	
	public static JSONArray jsonArrayFromDotNotation(JSONObject parentJSON, String dotNotation) {
		return ThemeServicesUtil.jsonArrayFromDotNotation(parentJSON, dotNotation);
	}
	
	public static BasicDBList jsonArrayFromDotNotation(BasicDBObject parentBasicDBObject, String dotNotation) {
		return ThemeServicesUtil.jsonArrayFromDotNotation(parentBasicDBObject, dotNotation);
	}
	
	public static JSONObject byteArrayToJSONObject(byte[] responseBody) { 
		return ThemeServicesUtil.byteArrayToJSONObject(responseBody);
	}
		
}
