package org.ieee.common.util;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import org.apache.commons.io.IOUtils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

import org.ieee.common.bean.CreatorBean;
import org.ieee.common.bean.ReferenceBean;
import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;
import org.ieee.common.json.XML;

import org.ieee.common.rest.v1.beans.content.Csdlresponse;

import org.springframework.web.servlet.view.velocity.VelocityConfigurer;


public class CSDLUtil {
	
	public static final String CSDLROOTPATH = "http://www.computer.org/digitallibrary/content/description";	

	
	public static String getCSDLAbstract(String fileID, String type, String fileName, String fileExt, VelocityConfigurer velocityConfigurer) {
		
		String output = "";
		
		try {
			
			HttpClient client = new HttpClient();
			fileID = URLDecoder.decode(fileID,"UTF-8");
			fileID = fileID.trim();
			String url = generateURL(fileID, CSDLROOTPATH);
			
			if ( "".equals(url.trim()) ) {							
				output = "<div class=\"abstractErrorMessage\">This article was not found in the Digital Library database.<br/><br/>Please try again later, or contact us at 'webmaster@computer.org' and we'll fix the issue as soon as possible.</div>";							
			} else {
				
				GetMethod method = new GetMethod(url);
				
				try {
					
					int statusCode = client.executeMethod(method);
					if (statusCode != HttpStatus.SC_OK) {
						output = "<div class=\"abstractErrorMessage\">It looks like we're having issues accessing the Digital Library database for this article. (Status=" + statusCode + ")<br/><br/>Please try again later, or contact us at 'webmaster@computer.org' and we'll fix the issue as soon as possible.</div>";
					} else {
						
						byte[] responseBody = method.getResponseBody();
						
						if ( "XML".equalsIgnoreCase(type) ) {
							output = new String(responseBody);
						} else if ( "JSON".equalsIgnoreCase(type) ) {
							JSONObject jsonObject = XML.toJSONObject(new String(responseBody));
							output = jsonObject.toString();
						} else if ( "HTML".equalsIgnoreCase(type) ) {
							
							String xmlData = new String(responseBody);
							
							VelocityContext context = new VelocityContext();
							JAXBContext jaxbContext = JAXBContext.newInstance(Csdlresponse.class);
							Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
							Csdlresponse csdlresponse = (Csdlresponse) jaxbUnmarshaller.unmarshal( new StreamSource( new StringReader( xmlData ) ));													
							context.put("csdlResponse", csdlresponse);
					
							output = createContentWithVM("ABSTRACT_LANDING_TEMPLATE", fileName+"."+fileExt.toLowerCase(), context, velocityConfigurer);
						
						} else {
							output = "<div class=\"abstractErrorMessage\">It looks like we're having issues accessing the Digital Library database for this article. (Type=" + type + ")<br/><br/>Please try again later, or contact us at 'webmaster@computer.org' and we'll fix the issue as soon as possible.</div>";
						} // if ( "XML".equalsIgnoreCase(type) ) {
					} // if (statusCode != HttpStatus.SC_OK) {
				
				} catch (Exception e) {
					output = "<div class=\"abstractErrorMessage\">It looks like we're having issues accessing the Digital Library database for this article. (ACSDL)<br/><br/>Please try again later, or contact us at 'webmaster@computer.org' and we'll fix the issue as soon as possible.</div>";
					e.printStackTrace();
				} finally {
					method.releaseConnection();
				}
	
			} // if ( "".equals(url.trim()) ) {	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}
	
	public static String generateURL(String fileID, String csdlPath) {
		
		String url = "";

		try {
			
			String type = fileID.substring(0,1);
			
			if ( type.equalsIgnoreCase("m") ) {
				
				String category = fileID.substring(1,3);
				String year = fileID.substring(3,7);
				String month = fileID.substring(7,9);
				url = csdlPath + "/url/mags/" + category + "/" + year + "/" + month + "/" + fileID + "abs.htm";
				
			} else if ( type.equalsIgnoreCase("t") ) {
				
				String category = fileID.substring(1,3);
				String year = fileID.substring(3,7);
				String month = fileID.substring(7,9);
				url = csdlPath + "/url/trans/" + category + "/" + year + "/" + month + "/" + fileID + "abs.htm";

			}
			
		} catch (Exception e) {
			url = "";
			e.printStackTrace();
		}
		
		return url;						
	}
	
	public static String prepareTitle(String title) {
		
		String finalTitle = "";
		
		try {
			
			finalTitle = title.replaceAll("&amp;amp;#x2014;", "-");					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalTitle;
	}
	
	public static List<CreatorBean> getCreatorList(JSONObject contentList) {
		
		List<CreatorBean> creatorBeanList = new ArrayList<CreatorBean>();
		
		try { 
			
			JSONObject creatorList = contentList.getJSONObject("creatorlist");	
			
			if ( creatorList.length() > 0 ) {
			
				String jsonClassName = creatorList.get("creator").getClass().getName();
				
				if ( "org.ieee.common.json.JSONArray".equalsIgnoreCase(jsonClassName) ) {
					
					JSONArray creatorArray = creatorList.getJSONArray("creator");
					
					for ( int i = 0; i < creatorArray.length(); i++ ){					
						JSONObject creator = creatorArray.getJSONObject(i);
						creatorBeanList.add( populateCreatorBean(creator) );
					}
					
				} else if ( "org.ieee.common.json.JSONObject".equalsIgnoreCase(jsonClassName) ) {				
					JSONObject creator = creatorList.getJSONObject("creator");
					creatorBeanList.add( populateCreatorBean(creator) );				
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return creatorBeanList;
	}
	
	public static List<ReferenceBean> getReferenceList(JSONObject contentList) {
		
		List<ReferenceBean> referenceBeanList = new ArrayList<ReferenceBean>();
		
		try { 
			
			JSONObject referenceList = contentList.getJSONObject("referencelist");
			
			if ( referenceList.length() > 0 ) {
			
				String jsonClassName = referenceList.get("reference").getClass().getName();
				
				if ( "org.ieee.common.json.JSONArray".equalsIgnoreCase(jsonClassName) ) {
					
					JSONArray referenceArray = referenceList.getJSONArray("reference");
					
					for ( int i = 0; i < referenceArray.length(); i++ ){					
						JSONObject reference = referenceArray.getJSONObject(i);
						referenceBeanList.add( populateReferenceBean(reference) );
					}
					
				} else if ( "org.ieee.common.json.JSONObject".equalsIgnoreCase(jsonClassName) ) {				
					JSONObject reference = referenceList.getJSONObject("reference");
					referenceBeanList.add( populateReferenceBean(reference) );				
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return referenceBeanList;
	}
	
		
	@SuppressWarnings("unchecked")
	public static String createContentWithVM(String resourceName, String fileName, VelocityContext context, VelocityConfigurer velocityConfigurer) {
		
		String theContent = "";
				
		try {
			
			DynamicQuery contentItemQuery = DynamicQueryFactoryUtil.forClass(DLFileEntry.class, PortalClassLoaderUtil.getClassLoader())
															.add(PropertyFactoryUtil.forName("title").eq(fileName));

			List<DLFileEntry> fileEntryList = DLFileEntryLocalServiceUtil.dynamicQuery(contentItemQuery);
			
			if ( null != fileEntryList && fileEntryList.size() > 0 ) {
				
				DLFileEntry fe = fileEntryList.get(0);

				InputStream vmInputStream = DLFileEntryLocalServiceUtil.getFileAsStream(fe.getCompanyId(), fe.getUserId(), fe.getGroupId(), fe.getFolderId(), fe.getName());									
				String abstractTemplate = IOUtils.toString(vmInputStream);
				
				StringResourceRepository repo = StringResourceLoader.getRepository();								
				repo.putStringResource(resourceName, abstractTemplate);
				VelocityEngine vEngine = velocityConfigurer.getVelocityEngine();
				Template vTemplate = vEngine.getTemplate(resourceName);
									
		        StringWriter swOut = new StringWriter();
		        vTemplate.merge(context, swOut);
		        theContent = swOut.toString();				
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return theContent;
	}
	
	public static CreatorBean populateCreatorBean(JSONObject creator) {
		
		CreatorBean cb = new CreatorBean();
		
		try { 
			
			cb.setGivenName(creator.getString("givenname"));
			cb.setRole(creator.getString("role"));
			cb.setSurname(creator.getString("surname"));
			
			String sponsor = creator.getString("sponsor");
			sponsor = null == sponsor || "".equals(sponsor.trim()) ? "" : ", " + sponsor;			
			cb.setSponsor(sponsor);
		
		} catch (Exception e) {
			e.printStackTrace();							
		} 
			
		return cb;		
	}
	
	public static ReferenceBean populateReferenceBean(JSONObject reference) {
		
		ReferenceBean rb = new ReferenceBean();
		
		try { 
			
			rb.setReferenceId(reference.getString("referenceid"));
			rb.setDisplay(reference.getString("display"));

		} catch (Exception e) {
			e.printStackTrace();							
		} 
			
		return rb;		
	}

}