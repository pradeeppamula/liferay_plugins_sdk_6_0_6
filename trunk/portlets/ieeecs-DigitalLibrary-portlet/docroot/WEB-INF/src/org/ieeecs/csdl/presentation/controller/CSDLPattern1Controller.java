package org.ieeecs.csdl.presentation.controller;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.*;

import javax.portlet.*;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;
import org.ieee.common.util.ParamUtil;

import org.ieeecs.csdl.bean.PackageBean;
import org.ieeecs.csdl.bean.ProductBean;
import org.ieeecs.csdl.util.DigitalLibraryUtil;

import org.springframework.web.portlet.ModelAndView;


public class CSDLPattern1Controller extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			putPortletPreferencesIntoModel(prefs, model);			
			setPorletMode(themeDisplay, prefs, model);
			
			long startTiming = getTime();

			String idPrefix = ParamUtil.getStringParameter(renderRequest, prefs.getValue("idPrefix", DigitalLibraryUtil.IDPREFIX));
			String fileName = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileName", DigitalLibraryUtil.FILENAME));
			String fileType = ParamUtil.getStringParameter(renderRequest, prefs.getValue("fileType", DigitalLibraryUtil.FILETYPE));
			
			Map<String,ProductBean> proceedingsMap = new TreeMap<String,ProductBean>();

			try {
				
				idPrefix = idPrefix.toLowerCase().trim();				
				putBreadcrumbForProceedingsInfoIntoModel(model, 
														 prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH), idPrefix, 
														 prefs.getValue("csdlListPage", DigitalLibraryUtil.CSDLLISTPAGE), 
														 prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE), 
														 prefs.getValue("proceedingsUrlSegment", DigitalLibraryUtil.PROCEEDINGSURLSEGMENT));
				
				if ( (  
						(idPrefix.length() == 1 && "abcdefghijklmnopqrstuvwxyz".indexOf(idPrefix) > -1) || "0-9".equals(idPrefix)
					 )
					 && ("list".equalsIgnoreCase(fileName)) ) {

						HttpClient client = new HttpClient();				
						String url = DigitalLibraryUtil.csdlProperties.getProperty("product.list.productType.Proceeding") + idPrefix;			
						GetMethod method = new GetMethod(url);				
						int statusCode = client.executeMethod(method);
						
						if ( statusCode >= 400 && statusCode < 500 ) {
							
							
						} else if ( statusCode >= 500 ) {
							
							
						} else {
							
							JSONObject jsonResponse = byteArrayToJSONObject(method.getResponseBody());
							JSONArray product = jsonArrayFromDotNotation(jsonResponse, "csdlresponse.productlist.product");
							
							if ( null != product && product.length() > 0 ) {
		
								for ( int index = 0; index < product.length(); index++ ) {
									
									ProductBean pb = new ProductBean();
									JSONObject productJSON = product.getJSONObject(index);	
									pb.setTitle( getString(productJSON, "title") );
									pb.setPrice( getString(productJSON, "price") );
									pb.setUpdateTime( getString(productJSON, "updatetime") );
									pb.setProductId( getString(productJSON, "productid") );
									pb.setDescription( getString(productJSON, "description") );
									pb.setSubject( getString(productJSON, "subject") );
									pb.setProductType( getString(productJSON, "producttype") );
									
									JSONArray keyValuePairs = jsonArrayFromDotNotation(productJSON, "productmetadata.value");			
									pb.populateMetadataMap(keyValuePairs);
									
									String key = pb.getMetadataMap().get("abbrev");
									String key2nd = pb.getMetadataMap().get("ieeeprefix");
									key2nd = null == key2nd ? "XXXX" : key2nd;
									key = null == key ? key2nd.toUpperCase() : key.toUpperCase();
									
									proceedingsMap.put(key, pb);
									
								}
							}						
						} // if ( statusCode >= 400 && statusCode < 500 ) {
					
				} else if ( "index".equalsIgnoreCase(fileName) ) {
					
					Map<String, List<PackageBean>> proceedingsYearMap = new TreeMap<String, List<PackageBean>>(Collections.reverseOrder());
					ProductBean productBean = new ProductBean();
					
					HttpClient client = new HttpClient();				
					String url = DigitalLibraryUtil.csdlProperties.getProperty("package.product.list.metadata.idprefix") + idPrefix;			
					GetMethod method = new GetMethod(url);				
					int statusCode = client.executeMethod(method);
					
					if ( statusCode >= 400 && statusCode < 500 ) {
						
						
					} else if ( statusCode >= 500 ) {
						
						
					} else {
						
						JSONObject jsonResponse = byteArrayToJSONObject(method.getResponseBody()); 
						JSONArray packageArray = jsonArrayFromDotNotation(jsonResponse, "csdlresponse.packagelist.package");
						
						if ( null != packageArray && packageArray.length() > 0 ) {
							for ( int index = 0; index < packageArray.length(); index++ ) {
								
								JSONObject packageJSON = packageArray.getJSONObject(index);
		
								// Same Start
								String packageId = getString(packageJSON, "packageid");
								
								PackageBean pb = new PackageBean();
								pb.setPackageId( packageId );
								pb.setPackageType( getString(packageJSON, "packagetype") );
								pb.setTitle( getString(packageJSON, "title") );
								pb.setPrice( getString(packageJSON, "price") );
								pb.setUpdateTime( getString(packageJSON, "updatetime") );
								pb.setDescription( getString(packageJSON, "description") );
								pb.setSubTitle( getString(packageJSON, "subtitle") );
								pb.setMediaTypes( getString(packageJSON, "mediatypes") );
								pb.setPublicationDate( getString(packageJSON, "publicationdate") );
								pb.setPublisher( getString(packageJSON, "publisher") );
								
								JSONArray keyValuePairs = jsonArrayFromDotNotation(packageJSON, "packagemetadata.value");			
								pb.populateMetadataMap(keyValuePairs);								
								
								String year = getString(packageJSON, "publicationdate");
								if ( year.length() > 4 ) {
									year = year.substring(0, 4);	
								}
								
								String catalog = pb.getMetadataMap().get("catalog");
								catalog = null == catalog ? "" : catalog;
								String volumeNumber = pb.getMetadataMap().get("volumeNumber");
								volumeNumber = null == volumeNumber ? "" : volumeNumber;
								volumeNumber = volumeNumber.length() == 1 ? "0"+volumeNumber : volumeNumber;
								pb.setUrl(prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH) + "/" + 
											prefs.getValue("proceedingsUrlSegment", DigitalLibraryUtil.PROCEEDINGSURLSEGMENT) + "/" + idPrefix + "/" + 
											year + "/" + catalog + "/" + volumeNumber + "/" + prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE) );							
								
								if ( index == 0 ) {
									JSONObject productJSON = jsonObjectFromDotNotation(packageJSON, "productlist.product");
									productBean.setTitle( getString(productJSON, "title") );
									productBean.setPrice( getString(productJSON, "price") );
									productBean.setUpdateTime( getString(productJSON, "updatetime") );
									productBean.setProductId( getString(productJSON, "productid") );
									productBean.setDescription( getString(productJSON, "description") );
									productBean.setSubject( getString(productJSON, "subject") );
									productBean.setProductType( getString(productJSON, "producttype") );
									
									JSONArray productBeanKeyValuePairs = jsonArrayFromDotNotation(productJSON, "productmetadata.value");			
									productBean.populateMetadataMap(productBeanKeyValuePairs);
								}
								
								List<PackageBean> currentPackageBeanList = proceedingsYearMap.get(year);
								
								if ( null == currentPackageBeanList ) {
									currentPackageBeanList = new ArrayList<PackageBean>();
									currentPackageBeanList.add(pb);
									proceedingsYearMap.put(year,  currentPackageBeanList);
								} else {
									currentPackageBeanList.add(pb);
								}
								// Same End
			
							} // for ( int index = 0; index < packageArray.length(); index++ ) {
							
						}  else {
							
							JSONObject packageJSON = jsonObjectFromDotNotation(jsonResponse, "csdlresponse.packagelist.package");
							
							if ( null != packageJSON ) {
								
								// Same Start
								String packageId = getString(packageJSON, "packageid");
								
								PackageBean pb = new PackageBean();
								pb.setPackageId( packageId );
								pb.setPackageType( getString(packageJSON, "packagetype") );
								pb.setTitle( getString(packageJSON, "title") );
								pb.setPrice( getString(packageJSON, "price") );
								pb.setUpdateTime( getString(packageJSON, "updatetime") );
								pb.setDescription( getString(packageJSON, "description") );
								pb.setSubTitle( getString(packageJSON, "subtitle") );
								pb.setMediaTypes( getString(packageJSON, "mediatypes") );
								pb.setPublicationDate( getString(packageJSON, "publicationdate") );
								pb.setPublisher( getString(packageJSON, "publisher") );
								
								JSONArray keyValuePairs = jsonArrayFromDotNotation(packageJSON, "packagemetadata.value");			
								pb.populateMetadataMap(keyValuePairs);								
								
								String year = getString(packageJSON, "publicationdate");
								if ( year.length() > 4 ) {
									year = year.substring(0, 4);	
								}
								
								String catalog = pb.getMetadataMap().get("catalog");
								catalog = null == catalog ? "" : catalog;
								String volumeNumber = pb.getMetadataMap().get("volumeNumber");
								volumeNumber = null == volumeNumber ? "" : volumeNumber;
								volumeNumber = volumeNumber.length() == 1 ? "0"+volumeNumber : volumeNumber;
								pb.setUrl(prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH) + "/" + 
											prefs.getValue("proceedingsUrlSegment", DigitalLibraryUtil.PROCEEDINGSURLSEGMENT) + "/" + idPrefix + "/" + 
											year + "/" + catalog + "/" + volumeNumber + "/" + prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE) );
								
								JSONObject productJSON = jsonObjectFromDotNotation(packageJSON, "productlist.product");
								productBean.setTitle( getString(productJSON, "title") );
								productBean.setPrice( getString(productJSON, "price") );
								productBean.setUpdateTime( getString(productJSON, "updatetime") );
								productBean.setProductId( getString(productJSON, "productid") );
								productBean.setDescription( getString(productJSON, "description") );
								productBean.setSubject( getString(productJSON, "subject") );
								productBean.setProductType( getString(productJSON, "producttype") );
								
								JSONArray productBeanKeyValuePairs = jsonArrayFromDotNotation(productJSON, "productmetadata.value");			
								productBean.populateMetadataMap(productBeanKeyValuePairs);
								
								List<PackageBean> currentPackageBeanList = proceedingsYearMap.get(year);
								
								if ( null == currentPackageBeanList ) {
									currentPackageBeanList = new ArrayList<PackageBean>();
									currentPackageBeanList.add(pb);
									proceedingsYearMap.put(year,  currentPackageBeanList);
								} else {
									currentPackageBeanList.add(pb);
								}
								// Same End
								
							} // if ( null != packageJSON ) {							
						} // if ( null != packageArray && packageArray.length() > 0 ) {	
						
						// Product Bean
						
					} // if ( statusCode >= 400 && statusCode < 500 ) {
					
					model.put("idPrefix", idPrefix.toUpperCase());			
					model.put("proceedingsYearMap", proceedingsYearMap);
					model.put("productBean", productBean);
					model.put("totalTimeMS", getTotalTime(startTiming) + " ms");
					ModelAndView modelAndView = new ModelAndView("Pattern1B", model);
					return modelAndView;
			
				} else {
					
					goHome(prefs.getValue("csdlContextPath", DigitalLibraryUtil.CSDLCONTEXTPATH), prefs.getValue("csdlIndexPage", DigitalLibraryUtil.CSDLINDEXPAGE), model);	
					model.put("totalTimeMS", getTotalTime(startTiming) + " ms");
					ModelAndView modelAndView = new ModelAndView("Home", model);
					return modelAndView;
					
				} // if ( (idPrefix.length() == 1 && "abcdefghijklmnopqrstuvwxyz".indexOf(idPrefix) > -1) || ("0-9".equals(idPrefix)) ) {		
	
			} catch (Exception e) {
				e.printStackTrace();
			}	

			model.put("idPrefix", idPrefix.toUpperCase());
			model.put("proceedingsMap", proceedingsMap);			
			model.put("totalTimeMS", getTotalTime(startTiming) + " ms");

		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("Pattern1A", model);

		return modelAndView;
	}	
	
}