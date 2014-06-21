package org.ieee.cnp.presentation.controller;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalArticleResourceLocalServiceUtil;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.portlet.ModelAndView;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

import org.ieee.cnp.util.RestAPIUtil;
import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;
import org.ieee.common.json.XML;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.CSDLUtil;
import org.ieee.common.util.DateUtils;
import org.ieee.common.util.ParamUtil;


public class AutoCSDLController extends BaseController {

	private static final String STARTDELIM  = "~~~~~~";
	private static final String ENDDELIM    = "^^^^^^";
	private static final String LOOKUPCSDL  = "lookup";
	private static final String IMPORTCSDL  = "import";
	private static final String FILEMOUNT   = "/mnt/filer11/restricted/csdl/";
	private static final String FILESEP     = "/";
	private static final String URLFILTER   = "[;\\/*?\"<>|&']";
	private static final String FILEEXTHTM	= ".htm";
	private static final String FILEEXTPDF	= ".pdf";
	private static final String HTML        = "HTML";
	private static final String PDF			= "PDF";
	private static final String UNKNOWN		= "UNKNOWN";

	private String fileMount     = "";
	private String fileSeparator = "";
	private String urlFilter     = "";
	
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		String output = "";

		try {

			String process = ParamUtil.getStringParameter(renderRequest, "process");

			// =============================================================
			// Process to handle the initial CSDL file lookups.
			// =============================================================
			if ( process.equalsIgnoreCase(LOOKUPCSDL) ) {
								
				String csdlRootPath  = ParamUtil.getStringParameter(renderRequest, "csdlRootPath");
				String propertiesFile  = ParamUtil.getStringParameter(renderRequest, "propertiesFile");
				getFileSettings(propertiesFile);					
				output = processLookupRequest(renderRequest, csdlRootPath, fileSeparator, fileMount);

			// =============================================================
			// Process to handle the final CSDL entry into the Liferay Web
			// Content system.
			// =============================================================
			} else if ( process.equalsIgnoreCase(IMPORTCSDL) ) {

				String csdlRootPath = ParamUtil.getStringParameter(renderRequest, "csdlRootPath");
				String vmFileName   = ParamUtil.getStringParameter(renderRequest, "csdlAbstract");
				String csdlCategory = ParamUtil.getStringParameter(renderRequest, "csdlCategory");
								
				ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				output = "{'status':'unknown', 'message':''}";

				if ( themeDisplay.isSignedIn() ) {

					String fileID = ParamUtil.getStringParameter(renderRequest, "id");
					String index  = ParamUtil.getStringParameter(renderRequest, "index");	
					
					if ( null != fileID && !"".equals(fileID) ) {
							
						HttpClient client = new HttpClient();
						fileID = URLDecoder.decode(fileID,"UTF-8");
						fileID = fileID.trim();						
						String url = generateURL(fileID, csdlRootPath);
												
						if ( "".equals(url.trim()) ) {							
							output = "{'status':'error', 'message':'\"" + fileID + "\" not found.'}";							
						} else {
							
							GetMethod method = new GetMethod(url);
							
							try {

								client.executeMethod(method);

								byte[] responseBody = method.getResponseBody();
								JSONObject jsonObject = XML.toJSONObject(new String(responseBody));
								JSONObject csdlResponse = jsonObject.getJSONObject("csdlresponse");
								JSONObject contentList  = csdlResponse.getJSONObject("contentlist");								
								
								String title = contentList.getString("title");
								title = CSDLUtil.prepareTitle(title);
								String urlTitle = prepareUrlTitle(title);
								urlTitle = urlTitle.toLowerCase();								
								String doi = contentList.getString("doi");																
								String description = contentList.getString("summary");
								description = description.replaceAll("\"", "'");
								String price = contentList.getString("price");
							
								// ===============================================
								// Channels (Categories)
								// If the entry type is "W" (What's New) or 
								// "T" (Theme), then we'll add this entry to the 
								// "What's New" category as well.
								// ===============================================
								String categories = ParamUtil.getStringParameter(renderRequest, "channels");
								String entryType = ParamUtil.getStringParameter(renderRequest, "entryType");
								JSONArray categoryJSONArray = new JSONArray(categories);
								ArrayList<Long> categoryArrayList = new ArrayList<Long>();
								
								if ( null != categoryJSONArray && categoryJSONArray.length() > 0 ) {									
									for ( int catIndex = 0; catIndex < categoryJSONArray.length(); catIndex++) {
										String currentCategoryId = categoryJSONArray.getString(catIndex);
										categoryArrayList.add(new Long(currentCategoryId));
									}
								}
								
								if ( "W".equalsIgnoreCase(entryType) || "T".equalsIgnoreCase(entryType) ) {
									
									DynamicQuery assetCategoryQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class, PortalClassLoaderUtil.getClassLoader())
																		.add(PropertyFactoryUtil.forName("name").eq(csdlCategory));

									List<AssetCategory> acList = AssetCategoryLocalServiceUtil.dynamicQuery(assetCategoryQuery);
									
									if ( null != acList && acList.size() > 0 ) {
										categoryArrayList.add(new Long(acList.get(0).getCategoryId()));							
									}									
								}
								
								long[] categoryIds = ArrayUtils.toPrimitive(categoryArrayList.toArray(new Long[0]), 0L);
								
								// ===============================================
								// Extract CSDL Display and Expiration Dates
								// ===============================================
								String displayExpirationDate = ParamUtil.getStringParameter(renderRequest, "displayExpirationDate");
								displayExpirationDate = null == displayExpirationDate ? "" : displayExpirationDate;
								String[] dateArray = displayExpirationDate.split("-",-1);
								Date csdlDisplayDate = new Date();
								Date csdlExpirationDate = null;								
								if ( dateArray.length == 1 ) {
									csdlDisplayDate = DateUtils.stringToDate(dateArray[0].trim(), false);
								} else if ( dateArray.length == 2 ) {
									csdlDisplayDate = DateUtils.stringToDate(dateArray[0].trim(), false);
									csdlExpirationDate = DateUtils.stringToDate(dateArray[1].trim(), false);
								}
								csdlDisplayDate = null == csdlDisplayDate ? new Date() : csdlDisplayDate;
								
								// ===============================================
								// Creator(s) of the entry
								// ===============================================
								String creators = RestAPIUtil.getCreators(contentList);
																
								// ===============================================
								// Process the Images for this entry
								// ===============================================
								String imagePath = ParamUtil.getStringParameter(renderRequest, "thumbnail");
								String thumbnailImage = ParamUtil.getStringParameter(renderRequest, "thumbnailImage");
								String headerImage = ParamUtil.getStringParameter(renderRequest, "headerImage");								
								imagePath = null == imagePath ? "" : imagePath;
								thumbnailImage = null == thumbnailImage ? "" : thumbnailImage;
								headerImage = null == headerImage ? "" : headerImage;
								String thumbnailImagePath = "";
								String headerImagePath = "";
								
								if ( imagePath.endsWith("/") ) {
									thumbnailImagePath = imagePath + thumbnailImage;
									headerImagePath = imagePath + headerImage;
								} else {
									thumbnailImagePath = imagePath + "/" + thumbnailImage;
									headerImagePath = imagePath + "/" + headerImage;
								}
								
								// ===============================================
								// Find the Velocity Template to use for the 
								// "expired" CSDL Content Abstract Landing page.
								// Then create the Journal Article, (along with all
								// the necessary records in various other Liferay
								// tables).
								// ===============================================
								DynamicQuery contentItemQuery = DynamicQueryFactoryUtil.forClass(DLFileEntry.class, PortalClassLoaderUtil.getClassLoader())
																	.add(PropertyFactoryUtil.forName("title").eq(vmFileName));

								List<DLFileEntry> fileEntryList = DLFileEntryLocalServiceUtil.dynamicQuery(contentItemQuery);
																
								if ( null != fileEntryList && fileEntryList.size() > 0 ) {
								
									// ===============================================
									// Create the HTML using Velocity
									// ===============================================
									DLFileEntry fe = fileEntryList.get(0);

									InputStream vmInputStream = DLFileEntryLocalServiceUtil.getFileAsStream(fe.getCompanyId(), fe.getUserId(), fe.getGroupId(), fe.getFolderId(), fe.getName());									
									String abstractTemplate = IOUtils.toString(vmInputStream);
									
									StringResourceRepository repo = StringResourceLoader.getRepository();								
									repo.putStringResource("ABSTRACT_LANDING_TEMPLATE", abstractTemplate);
									VelocityEngine vEngine = getVelocityConfigurer().getVelocityEngine();
									Template vTemplate = vEngine.getTemplate("ABSTRACT_LANDING_TEMPLATE");
									
									VelocityContext context = new VelocityContext();
									context.put("title", title);
									context.put("doi", doi);
									context.put("price", price);
																
							        StringWriter swOut = new StringWriter();
							        vTemplate.merge(context, swOut);
							        String finalHTML = swOut.toString();	
							        finalHTML = "<?xml version='1.0' encoding='UTF-8'?><root><static-content><![CDATA[" + finalHTML + "]]></static-content></root>";
									
									// ===============================================
									// Create the various ID values needed
									// ===============================================									
									long nextEntryId = CounterLocalServiceUtil.increment();
									long nextResourceId = CounterLocalServiceUtil.increment();
									long nextArticleId = CounterLocalServiceUtil.increment();
									long nextJournalId = CounterLocalServiceUtil.increment();																					
									
									// ===============================================
									// Create and save the new Asset Entry record
									// ===============================================										
									AssetEntry ae = AssetEntryLocalServiceUtil.createAssetEntry(nextEntryId);
									ae.setGroupId( themeDisplay.getScopeGroupId() );
									ae.setCompanyId( themeDisplay.getCompanyId() );
									ae.setUserId( themeDisplay.getUserId() );
									ae.setUserName( themeDisplay.getUser().getFullName() );
									ae.setCreateDate( csdlDisplayDate );
									ae.setModifiedDate( csdlDisplayDate );
									ae.setClassNameId( ClassNameLocalServiceUtil.getClassNameId("com.liferay.portlet.journal.model.JournalArticle") );
									ae.setClassPK( nextResourceId );
									ae.setClassUuid( PortalUUIDUtil.generate() );
									ae.setVisible( true );
									ae.setPublishDate( csdlDisplayDate );
									ae.setMimeType( "text/html" );
									ae.setTitle( title );
									ae.setDescription( description );
									ae.setSummary( description );									
									ae = AssetEntryLocalServiceUtil.updateAssetEntry(ae);									
									ae = AssetEntryLocalServiceUtil.updateEntry(themeDisplay.getUserId(), 
																				themeDisplay.getScopeGroupId(), 
																		        "com.liferay.portlet.journal.model.JournalArticle", 
																		        nextResourceId, 
																		        categoryIds, 
																		        null);									
									
									// ===============================================
									// Create and save the new Journal Resource record
									// ===============================================																			
									JournalArticleResource resource = JournalArticleResourceLocalServiceUtil.createJournalArticleResource(nextResourceId);
									resource.setGroupId( themeDisplay.getScopeGroupId() );
									resource.setArticleId( (new Long(nextArticleId)).toString() );
									resource = JournalArticleResourceLocalServiceUtil.updateJournalArticleResource(resource);
									
									// ===============================================
									// Create and save the new Journal Article record
									// ===============================================	
									JournalArticle ja = JournalArticleLocalServiceUtil.createJournalArticle(nextJournalId);	
									ja.setResourcePrimKey( nextResourceId );
									ja.setGroupId( themeDisplay.getScopeGroupId() );
									ja.setCompanyId( themeDisplay.getCompanyId() );
									ja.setUserId( themeDisplay.getUserId() );
									ja.setUserName( themeDisplay.getUser().getFullName() );
									ja.setCreateDate( csdlDisplayDate );
									ja.setModifiedDate( csdlDisplayDate );
									ja.setArticleId( (new Long(nextArticleId)).toString() );
									ja.setVersion( 0 );
									ja.setTitle( title );
									ja.setUrlTitle( urlTitle );
									ja.setDescription( description );
									ja.setType( "article" );
									ja.setDisplayDate( csdlDisplayDate );
									ja.setIndexable( true );
									ja.setSmallImage( false );
									ja.setSmallImageURL( thumbnailImagePath );
									ja.setStatus( 0 );
									ja.setStatusByUserId( themeDisplay.getUserId() );
									ja.setStatusByUserName( themeDisplay.getUser().getFullName() );
									ja.setStatusDate( csdlDisplayDate );
									
									// CSDL Expiration Date
									if ( ja.getExpandoBridge().hasAttribute( "CSDLExpirationDate" ) && null != csdlExpirationDate ) {
										ja.getExpandoBridge().setAttribute( "CSDLExpirationDate", DateUtils.dateToString(csdlExpirationDate, "MM/dd/yyyy HH:mm") );
									}
									
									// CSDL Header Image
									if ( ja.getExpandoBridge().hasAttribute( "CSDLHeaderImage" ) ) {
										ja.getExpandoBridge().setAttribute( "CSDLHeaderImage", headerImagePath );
									}
									
									// Creator
									if ( ja.getExpandoBridge().hasAttribute( "Creator" ) ) {
										ja.getExpandoBridge().setAttribute( "Creator", creators );
									}
									
									// Peer Reviewed
									if ( ja.getExpandoBridge().hasAttribute( "PeerReviewed" ) ) {
										ja.getExpandoBridge().setAttribute( "PeerReviewed", true );
									}									
									
									// CSDL 
									if ( ja.getExpandoBridge().hasAttribute( "csdl" ) ) {
										String fileType = ParamUtil.getStringParameter(renderRequest, "fileType");
										String fileNumber = ParamUtil.getStringParameter(renderRequest, "fileNumber");
										if ( "PDF".equalsIgnoreCase(fileType) ) {
											String csdlPath = imagePath + "/" + fileNumber + FILEEXTPDF;
											ja.getExpandoBridge().setAttribute( "csdl", csdlPath );
										} else {
											String csdlPath = ParamUtil.getStringParameter(renderRequest, "fileLocation");
											ja.getExpandoBridge().setAttribute( "csdl", csdlPath );
										}										
									}
									
									// CSDL DOI
									if ( ja.getExpandoBridge().hasAttribute( "CSDL_DOI" ) ) {
										ja.getExpandoBridge().setAttribute( "CSDL_DOI", doi );
									}									
																	
									ja.setContent(finalHTML);
									ja = JournalArticleLocalServiceUtil.updateJournalArticle(ja);									
																					  									
									output = "{'status':'success', 'message':'Successful import.', 'groupId':'" + themeDisplay.getScopeGroupId() + "', 'index':'" + index + "', 'urlTitle':'" + urlTitle + "'}";
									
								} else {
									output = "{'status':'error', 'message':'Cannot find the Velocity Template file required (" + vmFileName + ").'}";
								} // if ( null != fileEntryList && fileEntryList.size() > 0 ) {
								
							} catch (Exception e) {
								output = "{'status':'error', 'message':'Error with the AUTOCSDL.'}";
								e.printStackTrace();
							} finally {
								method.releaseConnection();
							}
							
						} // if ( "".equals(url.trim()) ) {
			
					} else {
						output = "{'status':'error', 'message':'Please enter the File ID.'}";
					} // if ( null != fileID && !"".equals(fileID) ) {
					
				} else {
					output = "{'status':'error', 'message':'Authentication required.'}";
				} // if ( themeDisplay.isSignedIn() ) {

			}

		} catch (Exception e) {
			output = "{'status':'error', 'message':'Error with the AUTOCSDL.'}";
			e.printStackTrace();
		}

		model.put("output", STARTDELIM + output + ENDDELIM);
		ModelAndView modelAndView = new ModelAndView("Output", model);

		return modelAndView;
	}
	
	private String generateURL(String fileID, String csdlPath) {
		
		String url = "";
		
		if ( fileID.indexOf("__") > -1 ) {							
			fileID = fileID.replaceAll("__", "/");	
			fileID = fileID.replaceAll("-", ".");
			url = csdlPath + "/doi/" + fileID;
		} else {
			
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
		}
		
		return url;						
	}
	
	private String validFileAndRecordFound(String index, String fileID, String fileSeparator, String fileMount, String message, String status, JSONObject jsonObject) {
		
		String output = "";
		
		try {
			
			JSONObject csdlResponse = jsonObject.getJSONObject("csdlresponse");
			JSONObject contentList  = csdlResponse.getJSONObject("contentlist");
			
			String title = contentList.getString("title");
			title = CSDLUtil.prepareTitle(title);
			String creators = RestAPIUtil.getCreators(contentList);			
			String doi = contentList.getString("doi");
			String fileName = contentList.getString("filename");
			String articleLength = contentList.getString("length");
			String uri = contentList.getString("uri");
			String duplicatesMessage = "";
			
			String description = contentList.getString("summary");
			description = description.replaceAll("\"", "'");
						
			if ( "\\".equals(fileSeparator) ) {
				uri = uri.replace("/", "\\\\");
			}
			
			String filePath = "";
			String fileType = "";
			
			if ( RestAPIUtil.fileExists(fileMount + uri + fileName + FILEEXTHTM) ) {
				fileType = HTML;
				filePath = fileMount + uri + fileName + FILEEXTHTM;
			} else {
				if ( RestAPIUtil.fileExists(fileMount + uri + fileName + FILEEXTPDF) ) {
					fileType = PDF;
					filePath = fileMount + uri + fileName + FILEEXTPDF;
				} else {
					fileType = UNKNOWN;
				}
			}		
			
			// Has this CSDL entry already been processed in the past?
			DynamicQuery expandoQuery = DynamicQueryFactoryUtil.forClass(ExpandoValue.class, PortalClassLoaderUtil.getClassLoader())
											.add(PropertyFactoryUtil.forName("data").eq(doi));
			List<ExpandoValue> doiList = ExpandoValueLocalServiceUtil.dynamicQuery(expandoQuery);
			
			if ( null != doiList && doiList.size() > 0 ) {				
				int numberOfTimesImported = doiList.size();
				ExpandoValue ev = doiList.get(0);
				long journalArticleId = ev.getClassPK();
				
				try {
					JournalArticle dupArticle = JournalArticleLocalServiceUtil.getArticle(journalArticleId);
					String dupProcessedOn = DateUtils.dateToString(dupArticle.getCreateDate(), "MM/dd/yyyy");
					duplicatesMessage = "Found " + numberOfTimesImported + " duplicate(s) of this record.<br/>First processed on " + dupProcessedOn + ".";
				} catch (Exception e) {
					// Wish the Liferay API had an "exists()" method in "JournalArticleLocalServiceUtil".
					duplicatesMessage = "Found " + numberOfTimesImported + " duplicate(s) of this record.";
				}
			}
						
			StringBuilder sb = new StringBuilder("{\"status\":\"").append(status).append("\", ");
			sb.append("\"index\":").append(index).append(", ");
			sb.append("\"message\":\"").append(message).append("\", ");
			sb.append("\"file_number\":\"").append(fileName).append("\", ");
			sb.append("\"doi\":\"").append(doi).append("\", ");
			sb.append("\"title\":\"").append(title).append("\", ");
			sb.append("\"regular_abstract\":\"").append(description).append("\", ");
			sb.append("\"pp\":\"").append(articleLength).append("\", ");
			sb.append("\"fileLocation\":\"").append(filePath).append("\", ");
			sb.append("\"fileType\":\"").append(fileType).append("\", ");
			sb.append("\"duplicatesMessage\":\"").append(duplicatesMessage).append("\", ");
			sb.append("\"creators\":\"").append(creators).append("\" ");
			sb.append("}");									
			output = sb.toString();
			
		} catch (Exception e) {
			output = "{'status':'error', 'message':'CSDL XML missing (?)<br/>or \"" + convertFileID(fileID) + "\" not found.'}";
			e.printStackTrace();
		}
				
		return output;
	}
	
	private String validFileButNoRecords(String index, String fileID) {
		
		String output = "";
		
		try {
			StringBuilder sb = new StringBuilder("{\"status\":\"error\", ");
			sb.append("\"index\":").append(index).append(", ");
			sb.append("\"message\":\"0 Records found for <b>" + fileID + "</b>.\", ");
			sb.append("\"file_number\":\"\", ");
			sb.append("\"doi\":\"\", ");
			sb.append("\"title\":\"\", ");
			sb.append("\"regular_abstract\":\"\", ");
			sb.append("\"pp\":\"\"");
			sb.append("\"fileLocation\":\"\", ");
			sb.append("\"duplicatesMessage\":\"\", ");
			sb.append("\"fileType\":\"\", ");
			sb.append("}");	
		} catch (Exception e) {
			e.printStackTrace();			
		}
			
		return output;
	}

	private String convertFileID(String fileID) {
		
		String convertedFileID = "";
		
		try {			
			if ( fileID.indexOf("__") > -1 ) {							
				fileID = fileID.replaceAll("__", "/");	
				fileID = fileID.replaceAll("-", ".");
				convertedFileID = fileID;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return convertedFileID;
	}
	
	private String processLookupRequest(RenderRequest renderRequest, String csdlPath, String fileSeparator, String fileMount) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String output = "{'status':'unknown', 'message':''}";

		if ( themeDisplay.isSignedIn() ) {
			
			try {

				String fileID = ParamUtil.getStringParameter(renderRequest, "id");
				String index  = ParamUtil.getStringParameter(renderRequest, "index");
	
				if ( null != fileID && !"".equals(fileID) ) {
	
					HttpClient client = new HttpClient();
					fileID = URLDecoder.decode(fileID,"UTF-8");
					fileID = fileID.trim();
					String url = generateURL(fileID, csdlPath);
											
					if ( "".equals(url.trim()) ) {							
						output = "{'status':'error', 'message':'\"" + fileID + "\" not found.'}";							
					} else {
						
						GetMethod method = new GetMethod(url);
						
						try {
	
							int statusCode = client.executeMethod(method);
							String status = "success";
							String message = "Successful lookup.";
							if (statusCode != HttpStatus.SC_OK) {
								status = "error";
								message = "HTTP Method failed: " + method.getStatusLine();
							}
	
							byte[] responseBody = method.getResponseBody();
							JSONObject jsonObject = XML.toJSONObject(new String(responseBody));
					
							if ( null != jsonObject ) {  
								output = validFileAndRecordFound(index, fileID, fileSeparator, fileMount, message, status, jsonObject);  
							} else {  								
								output = validFileButNoRecords(index, fileID);								  
							} 	
						
						} catch (Exception e) {
							output = "{'status':'error', 'message':'Error with the AUTOCSDL.'}";
							e.printStackTrace();
						} finally {
							method.releaseConnection();
						}
						
					} // if ( "".equals(url.trim()) ) {
		
				} else {
					output = "{'status':'error', 'message':'Please enter the File ID.'}";
				} // if ( null != fileID && !"".equals(fileID) ) {

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			output = "{'status':'error', 'message':'Authentication required.'}";
		} // if ( themeDisplay.isSignedIn() ) {		
		
		return output;
	}
	
	private String prepareUrlTitle(String title) {
		
		String finalUrlTitle = "";
		
		try {
			
			finalUrlTitle = title.replaceAll(" ", "-");
			finalUrlTitle = finalUrlTitle.replaceAll(urlFilter, "");			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalUrlTitle;
	}
	
	private void getFileSettings(String propertyFile) {

		try {

			// ------------------------------------------------------------------
			// Read this micro-site/site Properites map, stored within the Liferay
			// Document Library.  
			// ------------------------------------------------------------------
			DynamicQuery contentItemQuery = DynamicQueryFactoryUtil.forClass(DLFileEntry.class, PortalClassLoaderUtil.getClassLoader())
												.add(PropertyFactoryUtil.forName("title").eq(propertyFile));

			List<DLFileEntry> fileEntryList = DLFileEntryLocalServiceUtil.dynamicQuery(contentItemQuery);

			if ( null != fileEntryList && fileEntryList.size() > 0 ) {

				DLFileEntry fe = fileEntryList.get(0);

				InputStream is = DLFileEntryLocalServiceUtil.getFileAsStream(fe.getCompanyId(), fe.getUserId(), fe.getGroupId(), fe.getFolderId(), fe.getName());

				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line = br.readLine()) != null) {	

					if ( !StringUtils.startsWith(line, "#") && !"".equals(line.trim()) ) {	

						String[] keyValuePair = line.split("=",-1);
						String key = keyValuePair[0];
						String value = keyValuePair[1];

						if ( key.equalsIgnoreCase("Auto-CSDL.filemount") ) {							
							fileMount = null == value || "".equals(value) ? FILEMOUNT : value;
						} else if ( key.equalsIgnoreCase("Auto-CSDL.fileseparator") ) {	
							fileSeparator = null == value || "".equals(value) ? FILESEP : value;
						} else if ( key.equalsIgnoreCase("Auto-CSDL.urlfilter") ) {	
							urlFilter = null == value || "".equals(value) ? URLFILTER : value;
						}
						
					} // if ( !StringUtils.startsWith(line, "#") && !"".equals(line.trim()) ) {
				} // while ((line = br.readLine()) != null)   {	
				is.close();				
			} // if ( null != fileEntryList && fileEntryList.size() > 0 ) {			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}