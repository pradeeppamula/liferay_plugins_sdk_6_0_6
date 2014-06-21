package org.ieee.cnp.presentation.controller;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.ieee.cnp.util.ContentContainerUtil;
import org.ieee.common.constants.GlobalConstants;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.CSDLUtil;
import org.ieee.common.util.DateUtils;
import org.ieee.common.util.ParamUtil;

import org.springframework.web.portlet.ModelAndView;


public class ContentContainerController extends BaseController {
	
	private static final String defaultContent = "Please click on an Article or Blog post.";
	
	@SuppressWarnings("deprecation")
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String theContent = defaultContent;
		
		boolean isSocialShareActive = true;
		boolean isCommentingActive = false;
		boolean showSocialLoginBar = true;
		boolean showNewCommentSection = false;
		String urlTitle = "";
		String contentTitle = "";
		String pageTitle = "Computing Now";
		String displayDate = "";
		String groupId = "0";
		String type = "";
		String creator = "";
		String articleSource = "";
		String headerImagePath = "";
		String commentParentId = "";
		String commentParentType = "";
		String commentFullName = "";
		boolean peerReviewed = false;
		String relatedContentHTML = "";
		String multiMediaHTML = "";
		boolean fallbackJS = true;
		
		// -----------------------------------------------------------------------------------------------------------------
		// ThemeDisplay is used in various parts below.
		// -----------------------------------------------------------------------------------------------------------------
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		// We assume that the theme where this portlet resides doesn't contain all of the required Javascript libraries.
		// If it finds that the "complianceVersion" value is set in the theme's "liferay-look-and-feel.xml" file, then it contains all needed libraries.
		String complianceVersion = themeDisplay.getTheme().getSetting("complianceVersion");
		if ( null != complianceVersion && !"".equals(complianceVersion) ) {
			fallbackJS = false;
		}		
		
		try {
	
			// -----------------------------------------------------------------------------------------------------------------
			// Get the Content and its corresponding comments, based on the content type
			// -----------------------------------------------------------------------------------------------------------------
			urlTitle = ParamUtil.getStringParameter(renderRequest, "urlTitle");
			urlTitle = URLDecoder.decode(urlTitle);

			if ( null != urlTitle && !"".equals(urlTitle) ) {
				
				groupId = ParamUtil.getStringParameter(renderRequest, "g");
				type = ParamUtil.getStringParameter(renderRequest, "type");
				
				if ( !"".equals(groupId) && !"".equals(type) ) {
					
					// -------------------------------------------------------------
					// Articles (Webinar, Interview, Promo, Static, Video, etc)
					// -------------------------------------------------------------
					if ( "article".equalsIgnoreCase(type) ) {
						
						JournalArticle article = JournalArticleLocalServiceUtil.getLatestArticleByUrlTitle(new Long(groupId), urlTitle, 0);

						if (null != article) {
		
							articleSource = getArticleSource(article);
							headerImagePath = getHeaderImagePath(article);
							theContent = this.getContent(article);							
							contentTitle = article.getTitle();
							pageTitle = pageTitle + " | " + article.getTitle();
							displayDate = DateUtils.dateToString(article.getDisplayDate(), "MMM dd, yyyy HH:mm a").toUpperCase();
							commentParentId = article.getArticleId();
							commentParentType = ContentContainerUtil.ARTICLE;
							
							// Beautify the "author" (or creator) section, taking into account there might be multiple authors
							if ( article.getExpandoBridge().hasAttribute( "Creator" ) ) {
								if ( null != article.getExpandoBridge().getAttribute( "Creator" ) ) {
									creator = (String) article.getExpandoBridge().getAttribute( "Creator" );	
									creator = creator.trim().length() > 0 ? creator + " <br/> " : "";
								}
							}
							
							// Check to see if the article has been "peer reviewed" or not.									
							if ( article.getExpandoBridge().hasAttribute( "PeerReviewed" ) ) {
								if ( null != article.getExpandoBridge().getAttribute( "PeerReviewed" ) ) {
									peerReviewed = (Boolean) article.getExpandoBridge().getAttribute( "PeerReviewed" );
								}
							}	
							
							// Increment the "viewcount" for this content Asset Entry record
							AssetEntryLocalServiceUtil.incrementViewCounter(themeDisplay.getUserId(), "com.liferay.portlet.journal.model.JournalArticle", article.getResourcePrimKey());
							
							// Should the "Social Share" section be shown or not, (depends on how each article is setup via the Liferay Control Panel "Web Content")?
							if ( article.getExpandoBridge().hasAttribute( "IsSocialShareActive" ) ) {
								if ( null != article.getExpandoBridge().getAttribute( "IsSocialShareActive" ) ) {
									isSocialShareActive = (Boolean) article.getExpandoBridge().getAttribute( "IsSocialShareActive" );
								}
							}	
							
							// Should comments be turned off or not, (depends on how each article is setup via the Liferay Control Panel "Web Content")?
							if ( article.getExpandoBridge().hasAttribute( "IsCommentingActive" ) ) {
								if ( null != article.getExpandoBridge().getAttribute( "IsCommentingActive" ) ) {
									isCommentingActive = (Boolean) article.getExpandoBridge().getAttribute( "IsCommentingActive" );
								}
							}		
							
							// Are there any "Related Content" articles for this entry?
							if ( article.getExpandoBridge().hasAttribute( "RelatedContent" ) ) {
								if ( null != article.getExpandoBridge().getAttribute( "RelatedContent" ) ) {
									relatedContentHTML = (String) article.getExpandoBridge().getAttribute( "RelatedContent" );
								}
							}	
							
							// Are there any "MultiMedia" snippets for this entry?
							if ( article.getExpandoBridge().hasAttribute( "MultiMedia" ) ) {
								if ( null != article.getExpandoBridge().getAttribute( "MultiMedia" ) ) {
									multiMediaHTML = (String) article.getExpandoBridge().getAttribute( "MultiMedia" );
								}
							}							
							
						} else {	
							// Default 'theContent' to something nice for the user to see, as the Article wasn't found or has trouble being viewed
							theContent = defaultContent + " <span style='color: #FFFFFF'>(1)</span>";
						}
	
					// -------------------------------------------------------------
					// Blog Posts and Podcasts
					// -------------------------------------------------------------						
					} else if ( "blogpost".equalsIgnoreCase(type) || "podcast".equalsIgnoreCase(type) ) {
						
						BlogsEntry blogEntry = BlogsEntryLocalServiceUtil.getEntry(new Long(groupId), urlTitle);
						
						if ( null != blogEntry ) {

							theContent = blogEntry.getContent();
							contentTitle = blogEntry.getTitle();
							pageTitle = pageTitle + " | " + blogEntry.getTitle();
							displayDate = DateUtils.dateToString(blogEntry.getDisplayDate(), "MMM dd, yyyy HH:mm a").toUpperCase();
							commentParentId = (new Long(blogEntry.getEntryId())).toString();
							commentParentType = ContentContainerUtil.BLOG;
							
							// Beautify the "author" (or creator) section, taking into account there might be multiple authors							
							if ( blogEntry.getExpandoBridge().hasAttribute( "Creator" ) ) {
								if ( null != blogEntry.getExpandoBridge().getAttribute( "Creator" ) ) {
									creator = (String) blogEntry.getExpandoBridge().getAttribute( "Creator" );	
									creator = creator.trim().length() > 0 ? creator + " <br/> " : "";
								}
							}
							
							// Increment the "viewcount" for this content Asset Entry record
							AssetEntryLocalServiceUtil.incrementViewCounter(themeDisplay.getUserId(), "com.liferay.portlet.blogs.model.BlogsEntry", blogEntry.getEntryId());
							
							// Should the "Social Share" section be shown or not, (depends on how each blog post is setup via the Liferay Control Panel "Blogs")?
							if ( blogEntry.getExpandoBridge().hasAttribute( "IsSocialShareActive" ) ) {
								if ( null != blogEntry.getExpandoBridge().getAttribute( "IsSocialShareActive" ) ) {
									isSocialShareActive = (Boolean) blogEntry.getExpandoBridge().getAttribute( "IsSocialShareActive" );
								}
							}	
							
							// Should comments be turned off or not, (depends on how each blog post is setup via the Liferay Control Panel "Blogs")?
							if ( blogEntry.getExpandoBridge().hasAttribute( "IsCommentingActive" ) ) {
								if ( null != blogEntry.getExpandoBridge().getAttribute( "IsCommentingActive" ) ) {
									isCommentingActive = (Boolean) blogEntry.getExpandoBridge().getAttribute( "IsCommentingActive" );
								}
							}
							
						} else {							
							// Default 'theContent' to something nice for the user to see
							theContent = defaultContent + " <span style='color: #FFFFFF'>(2)</span>";
						}
						
					} else {
						// Default 'theContent' to something nice for the user to see
						theContent = defaultContent + " <span style='color: #FFFFFF'>(3)</span>";
					}
					
				} else {
					// Default 'theContent' to something nice for the user to see
					theContent = defaultContent + " <span style='color: #FFFFFF'>(4)</span>";
				}

			} else {				
				// Default 'theContent' to something nice for the user to see
				theContent = defaultContent + " <span style='color: #FFFFFF'>(5)</span>";				
			} // if ( null != urlTitle && !"".equals(urlTitle) ) {
			
		} catch (Exception e) {
			// Default 'theContent' to something nice for the user to see, as the Article wasn't found or has trouble being viewed
			theContent = defaultContent + " <span style='color: #FFFFFF'>(6)</span>";
			e.printStackTrace();
		}			
			
		try {
			
			// -----------------------------------------------------------------------------------------------------------------	
			// If there is a Connection Token in the URL, then the User is trying to login via the Social Login.
			// Using this Connection Token, get the User info from the API.  Create a new Session Map, if needed, to hold this 
			// information, and to show that the User has already logged in.
			// -----------------------------------------------------------------------------------------------------------------	
			Map<String,Object> smap = getSessionMap(renderRequest);			
			if ( null == smap ) {
				smap = ContentContainerUtil.populateSessionMap(renderRequest, themeDisplay);
			}
								
			// -----------------------------------------------------------------------------------------------------------------			
			// Check if the Session Map exists and if the User has logged in via Liferay or a Social service
			// If the User has logged in, regardless of the login source, they show the "New Comment" section and hide the 
			// "Social Login" section.
			// -----------------------------------------------------------------------------------------------------------------			
			if ( null != smap ) {

				boolean signedInLiferay = (Boolean) smap.get(GlobalConstants.SIGNEDINLIFERAY);
				boolean signedInSocial  = (Boolean) smap.get(GlobalConstants.SIGNEDINSOCIAL);

				if ( isCommentingActive ) {
					if ( signedInLiferay || signedInSocial ) {
						showSocialLoginBar = false;
						showNewCommentSection = true;	
					} else {
						showSocialLoginBar = true;
						showNewCommentSection = false;	
					}
				} else {
					showSocialLoginBar = false;
					showNewCommentSection = false;	
				}
				
				commentFullName = (String) smap.get(GlobalConstants.FULLNAME);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			
			// -----------------------------------------------------------------------------------------------------------------
			// Retrieve this portlet's Preferences
			// -----------------------------------------------------------------------------------------------------------------			
			PortletPreferences prefs = renderRequest.getPreferences();
			ContentContainerUtil.putPortletPreferencesIntoModel(prefs, model);
			
			// -----------------------------------------------------------------------------------------------------------------
			// If the current user has Deactivated this Portlet, we should still let them
			// see what they are doing.  Allow only this user who is controlling this portlet
			// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
			// until this admin user sets it to "Activated".
			// -----------------------------------------------------------------------------------------------------------------
			String currentUserId = new Long(themeDisplay.getUserId()).toString();
			String modifiedByUserId = prefs.getValue("modifiedByUserId", ContentContainerUtil.USERID);
			String currentMode = prefs.getValue("portletMode", ContentContainerUtil.MODE);			
			
			if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && currentMode.trim().equalsIgnoreCase(ContentContainerUtil.MODE) ) {
				model.put("portletMode", "PREVIEW");
			}	
			
			// -----------------------------------------------------------------------------------------------------------------
			// If we want to turn the comment system off for this container portlet, check if the value is "OFF", (overrides all
			// settings).
			// -----------------------------------------------------------------------------------------------------------------
			String commentSystem = prefs.getValue("commentSystem", ContentContainerUtil.COMMENTSYSTEM);
			if ( "OFF".equalsIgnoreCase(commentSystem) ) {
				showSocialLoginBar = false;
				showNewCommentSection = false;					
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("theContent", theContent);		
		model.put("isSocialShareActive", isSocialShareActive);
		model.put("showSocialLoginBar", showSocialLoginBar);
		model.put("showNewCommentSection", showNewCommentSection);		
		model.put("urlTitle", urlTitle);
		model.put("contentTitle", contentTitle);
		model.put("pageTitle", pageTitle);
		model.put("displayDate", displayDate);
		model.put("creator", creator);
		model.put("groupId", groupId);
		model.put("type", type);
		model.put("articleSource", articleSource);
		model.put("headerImagePath", headerImagePath);
		model.put("commentParentId", commentParentId);		
		model.put("commentParentType", commentParentType);
		model.put("commentFullName", commentFullName);	
		model.put("peerReviewed", peerReviewed);		
		model.put("relatedContentHTML", relatedContentHTML);
		model.put("multiMediaHTML", multiMediaHTML);
		model.put("fallbackJS", fallbackJS);		
		ModelAndView modelAndView = new ModelAndView("Home", model);

		return modelAndView;
	}	
	
	private static String getCSDLFile(String filePath) {
		
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
	
	private static Date stringToDate(String dateValue, String dateMask){

		SimpleDateFormat sdf = new SimpleDateFormat(dateMask);
	  	Date date = null;

		try{
		    date = sdf.parse(dateValue);
		} catch (ParseException e) {
		    e.printStackTrace();
		}

        return date;
	}
	
	private static String getArticleSource(JournalArticle article) {
		
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
	
	private static String getHeaderImagePath(JournalArticle article) {
		
		String headerImagePath = "";
		
		try {
		
			if ( article.getExpandoBridge().hasAttribute( "CSDLHeaderImage" ) ) {
				if ( null != article.getExpandoBridge().getAttribute( "CSDLHeaderImage" ) ) {
					headerImagePath = (String) article.getExpandoBridge().getAttribute( "CSDLHeaderImage" );
					headerImagePath = null == headerImagePath ? "" : headerImagePath.trim();
				}
			}	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return headerImagePath;
	}
	
	private static Date getCSDLExpirationDate(JournalArticle article) {
		
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
	
	private String createContentWithVM(List<DLFileEntry> abstractList, VelocityContext context) {
		
		String theContent = "";
				
		try {
			
			DLFileEntry fe = abstractList.get(0);

			InputStream vmInputStream = DLFileEntryLocalServiceUtil.getFileAsStream(fe.getCompanyId(), fe.getUserId(), fe.getGroupId(), fe.getFolderId(), fe.getName());									
			String abstractTemplate = IOUtils.toString(vmInputStream);
			
			StringResourceRepository repo = StringResourceLoader.getRepository();								
			repo.putStringResource("ABSTRACT_LANDING_PDF_TEMPLATE", abstractTemplate);
			VelocityEngine vEngine = getVelocityConfigurer().getVelocityEngine();
			Template vTemplate = vEngine.getTemplate("ABSTRACT_LANDING_PDF_TEMPLATE");
								
	        StringWriter swOut = new StringWriter();
	        vTemplate.merge(context, swOut);
	        theContent = swOut.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return theContent;
	}
	
	private String getContent(JournalArticle article) {
		
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
										theContent = CSDLUtil.getCSDLAbstract(fileId.trim(), "html", "CSDLAbstract", "txt", getVelocityConfigurer());
									} else {
										theContent = defaultContent;
									}
																		
								} else {
									theContent = defaultContent;
								}
								
							// If the CSDL Article has not expired, go to the CSDL and get the HTML copy of the Article
							// First, check if we're dealing with a PDF file, (ie. no HTML file exists), or an HTML file.
							// If we're dealing with a PDF, (which hopefully won't be very often), then generate a generic landing page with a link to the PDF
							// If we're dealing with an HTML file, then pull that file from the CSDL mounted drive.
							// If we're dealing with neither PDF or HTML, then just display what is stored in the Journal Article content.
							} else {
																				
								if ( csdl.toLowerCase().endsWith(".pdf") ) {
																						
									DynamicQuery abstractQuery = DynamicQueryFactoryUtil.forClass(DLFileEntry.class, PortalClassLoaderUtil.getClassLoader())
																		.add(PropertyFactoryUtil.forName("title").eq("CSDLAbstract_PDF.txt"));

									List<DLFileEntry> abstractList = DLFileEntryLocalServiceUtil.dynamicQuery(abstractQuery);
										
									if ( null != abstractList && abstractList.size() > 0 ) {
										
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
										theContent = this.createContentWithVM(abstractList, context);
					    
									}												
									
								} else if ( csdl.toLowerCase().endsWith(".htm") || csdl.toLowerCase().endsWith(".html") ){
									theContent = getCSDLFile(csdl);
								} else {
									theContent = ContentContainerUtil.getArticleContent(article);
								}
							
							} // if ( null == expirationDate || todaysDate.after(expirationDate) ) {	
							
						} catch (Exception e) {
							e.printStackTrace();
							theContent = ContentContainerUtil.getArticleContent(article);
						}
						
					} else {
						theContent = ContentContainerUtil.getArticleContent(article);										
					} // if ( null != csdl && !"".equals(csdl) && !csdl.toLowerCase().startsWith("auto") ) {
				} else {
					theContent = ContentContainerUtil.getArticleContent(article);
				} // if ( null != article.getExpandoBridge().getAttribute( "csdl" ) ) {
			} else {
				theContent = ContentContainerUtil.getArticleContent(article);
			} // if ( article.getExpandoBridge().hasAttribute( "csdl" ) ) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return theContent;
	}
		
}