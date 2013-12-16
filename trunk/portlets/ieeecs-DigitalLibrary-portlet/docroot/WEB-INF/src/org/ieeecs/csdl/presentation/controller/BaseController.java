package org.ieeecs.csdl.presentation.controller;

import java.util.*;

import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.journal.model.JournalArticle;

import javax.portlet.*;

import javax.servlet.http.HttpServletRequest;

import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;

import org.ieeecs.csdl.bean.ContentBean;
import org.ieeecs.csdl.bean.IssueBean;
import org.ieeecs.csdl.bean.PublicationBean;
import org.ieeecs.csdl.util.DigitalLibraryUtil;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.ResourceAwareController;
import org.springframework.web.portlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;


@SuppressWarnings("deprecation")
public class BaseController extends SimpleFormController  implements ResourceAwareController {
	
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

	public static void putPortletPreferencesIntoModel(PortletPreferences prefs, Map<String,Object> model) {
		DigitalLibraryUtil.putPortletPreferencesIntoModel(prefs, model);
	}

	public static void setPorletMode(ThemeDisplay themeDisplay, PortletPreferences prefs, Map<String,Object> model) {		
		DigitalLibraryUtil.setPorletMode(themeDisplay, prefs, model);
	}
	
	public static JSONObject getJSONObject(JSONObject targetJSONObject, String attributeName) {
		return DigitalLibraryUtil.getJSONObject(targetJSONObject, attributeName);
	}
	
	public static JSONArray getJSONArray(JSONObject targetJSONObject, String attributeName) {
		return DigitalLibraryUtil.getJSONArray(targetJSONObject, attributeName);
	}	
	
	public static String getString(JSONObject targetJSONObject, String attributeName) {
		return DigitalLibraryUtil.getString(targetJSONObject, attributeName);
	}
	
	public static JSONObject jsonObjectFromDotNotation(JSONObject parentJSON, String dotNotation) {
		return DigitalLibraryUtil.jsonObjectFromDotNotation(parentJSON, dotNotation);
	}
	
	public static JSONArray jsonArrayFromDotNotation(JSONObject parentJSON, String dotNotation) {
		return DigitalLibraryUtil.jsonArrayFromDotNotation(parentJSON, dotNotation);
	}
	
	public static JSONObject byteArrayToJSONObject(byte[] responseBody) { 
		return DigitalLibraryUtil.byteArrayToJSONObject(responseBody);
	}
	
	public static void goHome(String csdlContextPathPrefix, String csdlIndexPage, Map<String,Object> model) {
		DigitalLibraryUtil.goHome(csdlContextPathPrefix, csdlIndexPage, model);
	}
	
	public static void getIssueOrTableOfContents(JSONArray packageArray, String csdlContextPathPrefix, String pubType, String idPrefix, 
												 String year, String issueNumber, String csdlIndexPage, Object targetObject) {
		DigitalLibraryUtil.getIssueOrTableOfContents(packageArray, csdlContextPathPrefix, pubType, idPrefix, year, issueNumber, csdlIndexPage, targetObject);
	}
		
	public static void populatePublication(Map<Integer,PublicationBean> publicationMap, String csdlBase, String pubType, String csdlContextPathPrefix, String csdlIndexPage) {
		DigitalLibraryUtil.populatePublication(publicationMap, csdlBase, pubType, csdlContextPathPrefix, csdlIndexPage);
	}
	
	public static ContentBean populateContentBean(JSONObject contentJSON) {
		return DigitalLibraryUtil.populateContentBean(contentJSON);
	}
	
	public static void populateTableOfContentsList(Object targetObject, String packageId) {
		DigitalLibraryUtil.populateTableOfContentsList(targetObject, packageId);
	}
	
	public static void populateTableOfContentsList(Object targetObject, String pubType, String idPrefix, String year, String urlSuffix, String tocPage) {
		DigitalLibraryUtil.populateTableOfContentsList(targetObject, pubType, idPrefix, year, urlSuffix, tocPage);
	}
	
	public static void getIssues(JSONObject csdlJSON, String csdlContextPathPrefix, String pubType, String idPrefix, String year, 
			                     String csdlIndexPage, String productId, Map<String,IssueBean> issueMap) {
		DigitalLibraryUtil.getIssues(csdlJSON, csdlContextPathPrefix, pubType, idPrefix, year, csdlIndexPage, productId, issueMap);
	}
	
	public static void getTableOfContents(JSONObject csdlJSON, String csdlContextPathPrefix, String pubType, String idPrefix, String year, String issueNumber, 
                                          String csdlIndexPage, List<ContentBean> tableOfContentsList) {
		DigitalLibraryUtil.getTableOfContents(csdlJSON, csdlContextPathPrefix, pubType, idPrefix, year, issueNumber, csdlIndexPage, tableOfContentsList);
	}
	
	public static ContentBean getAbstract(String[] segments) {
		return DigitalLibraryUtil.getAbstract(segments);
	}
	
	public static ContentBean getAbstract(String pubType, String idPrefix, String year, String issueNumber, String fileName, String fileDetail, String fileType) {
		return DigitalLibraryUtil.getAbstract(pubType, idPrefix, year, issueNumber, fileName, fileDetail, fileType);
	}
	
	public static void putBreadcrumbInfoIntoModel(Map<String,Object> model, JSONObject idPrefixDataJSON, String csdlContextPathPrefix, String csdlIndexPage, String issueNumberLabel, 
                                                  String pubType, String idPrefix, String year, String issueNumber) {		
		DigitalLibraryUtil.putBreadcrumbInfoIntoModel(model, idPrefixDataJSON, csdlContextPathPrefix, csdlIndexPage, issueNumberLabel, pubType, idPrefix, year, issueNumber);
	}
	
	public static void putBreadcrumbForProceedingsInfoIntoModel(Map<String,Object> model, String csdlContextPathPrefix, String idPrefix, String csdlListPage, 
																String csdlIndexPage, String proceedingsUrlSegment) {
		DigitalLibraryUtil.putBreadcrumbForProceedingsInfoIntoModel(model, csdlContextPathPrefix, idPrefix, csdlListPage, csdlIndexPage, proceedingsUrlSegment);
	}
	
	public static void loadEditableProperties(Properties prop) {
		DigitalLibraryUtil.loadEditableProperties(prop);
	}
	
	public static void populatePublicationsCache(Properties prop) {
		DigitalLibraryUtil.populatePublicationsCache(prop);
	}
	
	public static void populateUICache(Properties prop, String location) { 
		DigitalLibraryUtil.populateUICache(prop, location);
	}
	
	public static String generatePublicationJSON(String pubType, String defaultOutput) {
		return DigitalLibraryUtil.generatePublicationJSON(pubType, defaultOutput);
	}
	
	public static void initializeProperties() {
		DigitalLibraryUtil.initializeProperties();
	}
	
	public static void initializeCache() {
		DigitalLibraryUtil.initializeCache();
	}	
	
	public static String getArticleContent(JournalArticle article, String defaultContent) {
		return DigitalLibraryUtil.getArticleContent(article, defaultContent);
	}
	
	public  String getSearchDatabases(String... idPrefix) {
		return DigitalLibraryUtil.getSearchDatabases(idPrefix);
	}
	
	public ModelAndView handleResourceRequest(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		
		Map<String,Object> model = new HashMap<String,Object>();
		JSONObject output = new JSONObject();
		
		try {
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resourceResponse.setCharacterEncoding("UTF-8");		
		resourceResponse.setContentType("text/json");
		
		model.put("output", output.toString());
		ModelAndView modelAndView = new ModelAndView("Response", model);

	    return modelAndView;
	}
}