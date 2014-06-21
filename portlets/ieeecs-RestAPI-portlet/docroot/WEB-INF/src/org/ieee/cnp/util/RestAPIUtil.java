package org.ieee.cnp.util;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleResource;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalArticleResourceLocalServiceUtil;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;

import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;
import org.ieee.common.util.CSDLUtil;
import org.ieee.common.util.ExpandoUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import org.springframework.web.servlet.view.velocity.VelocityConfigurer;


public class RestAPIUtil {
	
	public static final String ARTICLE = "ARTICLE";
	public static final String BLOG    = "BLOG";
	public static final String SUCCESS = "SUCCESS";
	public static final String LIFERAY = "LIFERAY";
	
	public static final String CSDLROOTPATH = "http://www.computer.org/digitallibrary/content/description";
	public static final String URLFILTER    = "[\\.#;\\/*?\"<>|&']";
	
	public static Map<String,String> mailingListMap = null;
	

	public static String getArticleContent(JournalArticle article) {
		
		String content = "";
		
		try {
			
			// Retrieve the Article					
			SAXBuilder parser = new SAXBuilder();
			Document document = parser.build(new StringReader(article.getContent()));
			XPath contentXPath = XPath.newInstance("/root/static-content");
			List<?> contentList = contentXPath.selectNodes(document);					

			if ((null != contentList) && (contentList.size() > 0)) {
				Element contentElement = (Element) contentList.get(0);
				content = contentElement.getText();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}
	
	
	public static String getArticleSource(JournalArticle article) {
		
		String articleSource = "";
		
		try {
			
			if ( article.getExpandoBridge().hasAttribute( "csdl" ) ) {
				if ( null != article.getExpandoBridge().getAttribute( "csdl" ) ) {
					
					String csdl = (String) article.getExpandoBridge().getAttribute( "csdl" );
					csdl = csdl.trim();
					
					if ( null != csdl && !"".equals(csdl) && !csdl.toLowerCase().startsWith("auto") ) {						
						articleSource = "CSDL";	
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return articleSource;
	}
	
	public static String getHeaderImagePath(JournalArticle article) {
		
		String headerImagePath = "";
		
		try {
		
			if ( article.getExpandoBridge().hasAttribute( "CSDLHeaderImage" ) ) {
				if ( null != article.getExpandoBridge().getAttribute( "CSDLHeaderImage" ) ) {
					headerImagePath = (String) article.getExpandoBridge().getAttribute( "CSDLHeaderImage" );
				}
			}	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return headerImagePath;
	}	
	
	public static Date getCSDLExpirationDate(JournalArticle article) {
		
		Date expirationDate = null;
		
		try {
			
			if ( article.getExpandoBridge().hasAttribute( "CSDLExpirationDate" ) ) {
				if ( null != article.getExpandoBridge().getAttribute( "CSDLExpirationDate" ) ) {
					String csdlExpirationDate = (String) article.getExpandoBridge().getAttribute( "CSDLExpirationDate" );
					if ( !"".equals(csdlExpirationDate.trim()) ) {
						expirationDate = stringToDate(csdlExpirationDate, "MM/dd/yyyy HH:mm");
					}
				}
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return expirationDate;
	}	
	
	public static String getContent(JournalArticle article, VelocityConfigurer velocityConfigurer) {
		
		String theContent = "";
		
		try {
			
			if ( article.getExpandoBridge().hasAttribute( "csdl" ) ) {
				if ( null != article.getExpandoBridge().getAttribute( "csdl" ) ) {
					
					String csdl = (String) article.getExpandoBridge().getAttribute( "csdl" );
					csdl = csdl.trim();
					
					if ( null != csdl && !"".equals(csdl) && !csdl.toLowerCase().startsWith("auto") ) {
						
						try {
							
							Date todaysDate = new Date();
							Date expirationDate = getCSDLExpirationDate(article);										
																		
							// If the CSDL Article has expired, then show the Landing Page (Subscription)
							if ( null == expirationDate || todaysDate.after(expirationDate) ) {
								
								String[] idParts = csdl.split("/");
								if ( idParts.length > 1 ) {
									
									String filename = idParts[idParts.length-1];
									String[] fileParts = filename.split("\\.");
									
									if ( fileParts.length >= 1 ) {
										String fileId = fileParts[0];										
										theContent = CSDLUtil.getCSDLAbstract(fileId.trim(), "html", "CSDLAbstract", "txt", velocityConfigurer);
									} else {
										theContent = "Please click on an Article or Blog post.";
									}
																		
								} else {
									theContent = "Please click on an Article or Blog post.";
								}
							
							// If the CSDL Article has not expired, go to the CSDL and get the HTML copy of the Article
							// First, check if we're dealing with a PDF file, (ie. no HTML file exists), or an HTML file.
							// If we're dealing with a PDF, (which hopefully won't be very often), then generate a generic landing page with a link to the PDF
							// If we're dealing with an HTML file, then pull that file from the CSDL mounted drive.
							// If we're dealing with neither PDF or HTML, then just display what is stored in the Journal Article content.
							} else {
																				
								if ( csdl.toLowerCase().endsWith(".pdf") ) {
												
									String title = article.getTitle();
									String description = article.getDescription();
									
									String creators = "";
									if ( article.getExpandoBridge().hasAttribute( "Creator" ) ) {
										if ( null != article.getExpandoBridge().getAttribute( "Creator" ) ) {
											creators = (String) article.getExpandoBridge().getAttribute( "Creator" );
										}
									}															

									VelocityContext context = new VelocityContext();
									context.put("title", title);
									context.put("creators", creators);
									context.put("description", description);
									context.put("csdl", csdl);
									
									theContent = CSDLUtil.createContentWithVM("ABSTRACT_LANDING_PDF_TEMPLATE", "CSDLAbstract_PDF.txt", context, velocityConfigurer);					    											
									
								} else if ( csdl.toLowerCase().endsWith(".htm") || csdl.toLowerCase().endsWith(".html") ){
									theContent = getCSDLFile(csdl);
								} else {
									theContent = RestAPIUtil.getArticleContent(article);
								}
							
							} // if ( null == expirationDate || todaysDate.after(expirationDate) ) {	
							
						} catch (Exception e) {
							e.printStackTrace();
							theContent = RestAPIUtil.getArticleContent(article);
						}
						
					} else {
						theContent = RestAPIUtil.getArticleContent(article);										
					} // if ( null != csdl && !"".equals(csdl) && !csdl.toLowerCase().startsWith("auto") ) {
				} else {
					theContent = RestAPIUtil.getArticleContent(article);
				} // if ( null != article.getExpandoBridge().getAttribute( "csdl" ) ) {
			} else {
				theContent = RestAPIUtil.getArticleContent(article);
			} // if ( article.getExpandoBridge().hasAttribute( "csdl" ) ) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return theContent;
	}	
	
	public static Date stringToDate(String dateValue, String dateMask){

		SimpleDateFormat sdf = new SimpleDateFormat(dateMask);
	  	Date date = null;

		try{
		    date = sdf.parse(dateValue);
		} catch (ParseException e) {
		    e.printStackTrace();
		}

        return date;
	}	
	
	public static String getCSDLFile(String filePath) {
		
		StringBuilder sb = new StringBuilder();
		
		try { 
			
			FileInputStream fstream = new FileInputStream(filePath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			boolean readFromFile = false;
			
			while ((line = br.readLine()) != null)   {
				
				if ( readFromFile &&  line.indexOf("</body>") == -1 ) {
					sb.append(line);
				}				
				
				if ( line.indexOf("<body") > -1 ) {
					readFromFile = true;
				} else if ( line.indexOf("</body>") > -1 ) {
					readFromFile = false;
				}				
			}
		
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public static String getCreators(JSONObject contentList) {
		
		String creators = "";
		
		try { 
			
			JSONObject creatorList = contentList.getJSONObject("creatorlist");			
			String jsonClassName = creatorList.get("creator").getClass().getName();
			
			if ( "org.ieee.common.json.JSONArray".equalsIgnoreCase(jsonClassName) ) {
				
				JSONArray creatorArray = creatorList.getJSONArray("creator");
				
				for ( int i = 0; i < creatorArray.length(); i++ ){
					JSONObject creator = creatorArray.getJSONObject(i);
					creators += addCreator(creator);			
				}
				
			} else if ( "org.ieee.common.json.JSONObject".equalsIgnoreCase(jsonClassName) ) {				
				JSONObject creator = creatorList.getJSONObject("creator");
				creators += addCreator(creator);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return creators;
	}
	
	public static String addCreator(JSONObject creator) {
		
		String creatorOutput = "";
		
		try {
			
			String givenName = creator.getString("givenname");
			String surName = creator.getString("surname");
			String sponsor = creator.getString("sponsor");
			
			givenName = null == givenName || "".equals(givenName.trim()) ? "" : givenName.trim();
			surName = null == surName || "".equals(surName.trim()) ? "" : surName.trim();
			sponsor = null == sponsor || "".equals(sponsor.trim()) ? "" : sponsor.trim();
			
			if ( !"".equals(givenName) ) {
				creatorOutput += givenName;
			}
			if ( !"".equals(surName) ) {
				creatorOutput += " ";
				creatorOutput += surName;
			}
			if ( !"".equals(sponsor) ) {
				if ( !"".equals(givenName) && !"".equals(surName) ) {
					creatorOutput += ", ";
				}
				creatorOutput += sponsor;
				creatorOutput += "<br/>";
			}	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return creatorOutput;
	}
	
	public static boolean fileExists(String filePath) {
		
		boolean fileExists = false;
		
		try { 
			@SuppressWarnings("unused")
			FileInputStream fstream = new FileInputStream(filePath);
			fileExists = true;
		} catch (FileNotFoundException fnfe) {
			fileExists = false;							
		} catch (Exception e) {
			fileExists = false;
			e.printStackTrace();							
		} 
		
		return fileExists;
	}
	
	public static String prepareUrlTitle(String title) {
		
		String finalUrlTitle = "";
		
		try {
	
			finalUrlTitle = title.replaceAll(" ", "-");
			finalUrlTitle = title.replaceAll("/", "-");
			finalUrlTitle = finalUrlTitle.replaceAll("[^a-zA-Z0-9/]" , "-");
			finalUrlTitle = finalUrlTitle.replaceAll("(-)\\1+" , "-");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalUrlTitle;
	}
	
	public static void createNewEntry(long groupId, ThemeDisplay themeDisplay, Date displayDate, Date expireDate, String title, String urlTitle, String description, long[] categoryIds, String thumbnailImagePath,
									  String content, Map<String,String> expandoValueMap) {
		
		try {
		
			// ===============================================
			// Create the various ID values needed
			// ===============================================									
			long nextEntryId = CounterLocalServiceUtil.increment();
			long nextResourceId = CounterLocalServiceUtil.increment();
			long nextArticleId = CounterLocalServiceUtil.increment();
			long nextJournalId = CounterLocalServiceUtil.increment();
			
			if ( nextEntryId > 0 && nextResourceId > 0 && nextArticleId > 0 && nextJournalId > 0 && null != themeDisplay && 
				 !"".equals(title.trim()) &&
				 !"".equals(description.trim()) &&
				 !"".equals(urlTitle.trim()) ) {
			
				if ( null == displayDate ) {
					displayDate = new Date();
				}
				
				if ( null == thumbnailImagePath ) {
					thumbnailImagePath = "";
				}
				
				// ===============================================
				// Create and save the new Asset Entry record
				// ===============================================										
				AssetEntry ae = AssetEntryLocalServiceUtil.createAssetEntry(nextEntryId);
				ae.setGroupId( groupId );
				ae.setCompanyId( themeDisplay.getCompanyId() );
				ae.setUserId( themeDisplay.getUserId() );
				ae.setUserName( themeDisplay.getUser().getFullName() );
				ae.setCreateDate( displayDate );
				ae.setModifiedDate( displayDate );
				ae.setClassNameId( ClassNameLocalServiceUtil.getClassNameId("com.liferay.portlet.journal.model.JournalArticle") );
				ae.setClassPK( nextResourceId );
				ae.setClassUuid( PortalUUIDUtil.generate() );
				ae.setVisible( true );
				ae.setPublishDate( displayDate );
				ae.setMimeType( "text/html" );
				ae.setTitle( title );
				ae.setDescription( description );
				ae.setSummary( description );	
				
				if ( null != expireDate ) { 
					ae.setExpirationDate(expireDate); 
				}
					
				ae = AssetEntryLocalServiceUtil.updateAssetEntry(ae);									
				ae = AssetEntryLocalServiceUtil.updateEntry(themeDisplay.getUserId(), 
															groupId, 
													        "com.liferay.portlet.journal.model.JournalArticle", 
													        nextResourceId, 
													        categoryIds, 
													        null);			
				
				// ===============================================
				// Create and save the new Journal Resource record
				// ===============================================																			
				JournalArticleResource resource = JournalArticleResourceLocalServiceUtil.createJournalArticleResource(nextResourceId);
				resource.setGroupId( groupId );
				resource.setArticleId( (new Long(nextArticleId)).toString() );
				resource = JournalArticleResourceLocalServiceUtil.updateJournalArticleResource(resource);
				
				// ===============================================
				// Create and save the new Journal Article record
				// ===============================================	
				JournalArticle ja = JournalArticleLocalServiceUtil.createJournalArticle(nextJournalId);	
				ja.setResourcePrimKey( nextResourceId );
				ja.setGroupId( groupId );
				ja.setCompanyId( themeDisplay.getCompanyId() );
				ja.setUserId( themeDisplay.getUserId() );
				ja.setUserName( themeDisplay.getUser().getFullName() );
				ja.setCreateDate( displayDate );
				ja.setModifiedDate( displayDate );
				ja.setArticleId( (new Long(nextArticleId)).toString() );
				ja.setTitle( title );
				ja.setUrlTitle( urlTitle );
				ja.setDescription( description );
				ja.setType( "article" );
				ja.setDisplayDate( displayDate );
				ja.setIndexable( true );
				ja.setSmallImage( false );
				ja.setSmallImageURL( thumbnailImagePath );
				ja.setStatus( 0 );
				ja.setStatusByUserId( themeDisplay.getUserId() );
				ja.setStatusByUserName( themeDisplay.getUser().getFullName() );
				ja.setStatusDate( displayDate );
				
				BigDecimal nextVersion = new BigDecimal("1").setScale(0, BigDecimal.ROUND_UP);
				ja.setVersion( nextVersion.doubleValue() );				
				
				if ( null != expireDate ) { 
					ja.setExpirationDate(expireDate); 
				}
				
				ExpandoUtil.setExpandoValue(ja, expandoValueMap.get("creator"), "Creator");
				ExpandoUtil.setExpandoValue(ja, new Boolean( expandoValueMap.get("peerReviewed") ), "PeerReviewed");
				ExpandoUtil.setExpandoValue(ja, expandoValueMap.get("multiMedia"), "MultiMedia");
				ExpandoUtil.setExpandoValue(ja, expandoValueMap.get("relatedContent"), "RelatedContent");
				ExpandoUtil.setExpandoValue(ja, new Boolean( expandoValueMap.get("comments") ), "IsCommentingActive");
				ExpandoUtil.setExpandoValue(ja, new Boolean( expandoValueMap.get("social") ), "IsSocialShareActive");				
	
				ja.setContent(content);
				ja = JournalArticleLocalServiceUtil.updateJournalArticle(ja);		
				
			} // if ( nextEntryId > 0 && nextResourceId > 0 && nextArticleId > 0 && nextJournalId > 0 ...
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateEntry(long groupId, ThemeDisplay themeDisplay, long resourcePrimKey, Date displayDate, Date expireDate, String title, String urlTitle, String description, 
								   long[] categoryIds, String thumbnailImagePath, String content, Map<String,String> expandoValueMap) {

		try {
			
			Date rightNow = new Date();
			long nextJournalId = CounterLocalServiceUtil.increment();
			
			if ( nextJournalId > 0 && null != themeDisplay && 
					 !"".equals(title.trim()) &&
					 !"".equals(description.trim()) &&
					 !"".equals(urlTitle.trim()) &&
					 resourcePrimKey > 0 ) {			
				
				if ( null == displayDate ) {
					displayDate = new Date();
				}
				
				if ( null == thumbnailImagePath ) {
					thumbnailImagePath = "";
				}				
			
				// ===============================================
				// Update the Asset Entry record
				// ===============================================	
				AssetEntry ae = AssetEntryLocalServiceUtil.getEntry("com.liferay.portlet.journal.model.JournalArticle", resourcePrimKey);
				ae.setModifiedDate( rightNow );
				if ( null != expireDate ) { 
					ae.setExpirationDate(expireDate); 
				}
				ae.setPublishDate( displayDate );
				ae.setTitle( title );
				ae.setDescription( description );
				ae.setSummary( description );
				ae = AssetEntryLocalServiceUtil.updateAssetEntry(ae);
				ae = AssetEntryLocalServiceUtil.updateEntry(themeDisplay.getUserId(), 
															groupId, 
													        "com.liferay.portlet.journal.model.JournalArticle", 
													        resourcePrimKey, 
													        categoryIds, 
													        null);				
				
				// ===============================================
				// Update the Journal Article record
				// ===============================================	
				JournalArticle pastArticle = JournalArticleLocalServiceUtil.getLatestArticle(resourcePrimKey);
				
				JournalArticle ja = JournalArticleLocalServiceUtil.createJournalArticle(nextJournalId);	
				ja.setResourcePrimKey( resourcePrimKey );
				ja.setGroupId( groupId );
				ja.setCompanyId( themeDisplay.getCompanyId() );
				ja.setUserId( themeDisplay.getUserId() );
				ja.setUserName( themeDisplay.getUser().getFullName() );
				ja.setCreateDate( displayDate );
				ja.setModifiedDate( rightNow );
				ja.setArticleId( pastArticle.getArticleId() );				
				ja.setTitle( title );
				ja.setUrlTitle( urlTitle );
				ja.setDescription( description );
				ja.setType( "article" );
				ja.setDisplayDate( displayDate );
				ja.setIndexable( true );
				ja.setSmallImage( false );
				ja.setSmallImageURL( thumbnailImagePath );
				ja.setStatus( 0 );
				ja.setStatusByUserId( themeDisplay.getUserId() );
				ja.setStatusByUserName( themeDisplay.getUser().getFullName() );
				ja.setStatusDate( displayDate );	
				
				BigDecimal currentVersion = new BigDecimal(pastArticle.getVersion()).setScale(1, BigDecimal.ROUND_UP);
				BigDecimal incrementVersion = new BigDecimal("0.1").setScale(1, BigDecimal.ROUND_UP);
				BigDecimal nextVersion = currentVersion.add(incrementVersion);
				ja.setVersion( nextVersion.doubleValue() );
				
				if ( null != expireDate ) { 
					ja.setExpirationDate(expireDate); 
				}
				
				ExpandoUtil.setExpandoValue(ja, expandoValueMap.get("creator"), "Creator");
				ExpandoUtil.setExpandoValue(ja, new Boolean( expandoValueMap.get("peerReviewed") ), "PeerReviewed");
				ExpandoUtil.setExpandoValue(ja, expandoValueMap.get("multiMedia"), "MultiMedia");
				ExpandoUtil.setExpandoValue(ja, expandoValueMap.get("relatedContent"), "RelatedContent");
				ExpandoUtil.setExpandoValue(ja, new Boolean( expandoValueMap.get("comments") ), "IsCommentingActive");
				ExpandoUtil.setExpandoValue(ja, new Boolean( expandoValueMap.get("social") ), "IsSocialShareActive");			
				
				ja.setContent(content);
				ja = JournalArticleLocalServiceUtil.updateJournalArticle(ja);
			
			} // if ( nextJournalId > 0 && null != themeDisplay &&  ...
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}