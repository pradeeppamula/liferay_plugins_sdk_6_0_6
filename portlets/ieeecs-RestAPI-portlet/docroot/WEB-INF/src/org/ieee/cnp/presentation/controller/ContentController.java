package org.ieee.cnp.presentation.controller;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.io.StringReader;
import java.net.URLDecoder;
import java.util.*;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.web.portlet.ModelAndView;

import org.ieee.cnp.util.RestAPIUtil;

import org.ieee.common.bean.ContentBean;
import org.ieee.common.json.JSONObject;
import org.ieee.common.presentation.controller.BaseController;
import org.ieee.common.util.ContentUtil;
import org.ieee.common.util.DateUtils;
import org.ieee.common.util.ParamUtil;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;


public class ContentController extends BaseController {

	private static final String STARTDELIM  = "~~~~~~";
	private static final String ENDDELIM    = "^^^^^^";
	private static final String ARTICLE     = "ARTICLE";
	private static final String BLOGPOST    = "BLOGPOST";
	private static final String PODCAST     = "PODCAST";
	private static final String CONTENTLIST = "CONTENTLIST";
	private static final String UPSERT      = "UPSERT";
	private static final String FULL        = "FULL";
	private static final String CONTENT     = "CONTENT";
	
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();

		String output = "";

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String contentType = ParamUtil.getStringParameter(renderRequest, "contentType");
			
			if ( contentType.toUpperCase().equalsIgnoreCase(ARTICLE) ) {	
				output = getArticleContent(renderRequest);
			} else if ( contentType.toUpperCase().equalsIgnoreCase(BLOGPOST) || contentType.toUpperCase().equalsIgnoreCase(PODCAST) ) {
				output = getBlogContent(renderRequest);
			} else if ( contentType.toUpperCase().equalsIgnoreCase(CONTENTLIST) ) {
				
				List<ContentBean> contentList = new ArrayList<ContentBean>();				
				
				long companyGroupId = themeDisplay.getCompanyGroupId();
				Integer start       = ParamUtil.getIntegerParameter(renderRequest, "start");
				Integer end         = ParamUtil.getIntegerParameter(renderRequest, "end");
				String portletId    = ParamUtil.getStringParameter(renderRequest, "id");
				
				DynamicQuery prefsQuery = DynamicQueryFactoryUtil.forClass(PortletPreferences.class, PortalClassLoaderUtil.getClassLoader())
																		.add(PropertyFactoryUtil.forName("portletId").like("%" + portletId + "%"));
				
				List<PortletPreferences> prefsList = PortletPreferencesLocalServiceUtil.dynamicQuery(prefsQuery);
		
				if ( null != prefsList && prefsList.size() > 0 ) {
					
					PortletPreferences pp = prefsList.get(0);
					String prefsXML = pp.getPreferences();
					
					String urlTargetName             = "";			
					String contentListData           = "";				
					String channelVocabularyName     = "";
					String contentTypeVocabularyName = "";
					String publicServletMapping      = "";
					String articleImagePath          = "";
					String blogImagePath             = "";
					String supplement                = "";
					String defaultImagePath          = "";
					String displayDateFormat         = "";

					// Ah, if someone finds a better way to do this, I'll buy them lunch... 
					// I can't find a way to pinpoint-traverse the XML via the XPath/JDOM, 
					// therefore I resort to the ugly "IF/ELSE" below for each element value.
					SAXBuilder parser = new SAXBuilder();
					Document document = parser.build(new StringReader(prefsXML));
					XPath contentXPath = XPath.newInstance("/portlet-preferences/preference");
					List preferenceList = contentXPath.selectNodes(document);
					
					if ((null != preferenceList) && (preferenceList.size() > 0)) {
						
						for ( int index = 0; index < preferenceList.size(); index++ ) {
							
							Element preferenceElement = (Element) preferenceList.get(index);
							Element nameElement  = preferenceElement.getChild("name");
							Element valueElement = preferenceElement.getChild("value");
							String name = nameElement.getText();
							String value = valueElement.getText();
							
							if ( name.equalsIgnoreCase("urlTargetName") ) {
								urlTargetName = value;
							} else if ( name.equalsIgnoreCase("contentListData") ) {
								contentListData = value;
							} else if ( name.equalsIgnoreCase("channelVocabularyName") ) {
								channelVocabularyName = value;
							} else if ( name.equalsIgnoreCase("contentTypeVocabularyName") ) {
								contentTypeVocabularyName = value;
							} else if ( name.equalsIgnoreCase("publicServletMapping") ) {
								publicServletMapping = value;
							} else if ( name.equalsIgnoreCase("articleImagePath") ) {
								articleImagePath = value;
							} else if ( name.equalsIgnoreCase("blogImagePath") ) {
								blogImagePath = value;
							} else if ( name.equalsIgnoreCase("supplement") ) {
								supplement = value;
								supplement = supplement.replaceAll("\\[\\$NEW_LINE\\$\\]", "");
							} else if ( name.equalsIgnoreCase("defaultImagePath") ) {
								defaultImagePath = value;
							} else if ( name.equalsIgnoreCase("displayDateFormat") ) {
								displayDateFormat = value;
							}
						}	
						
						contentList = ContentUtil.getContentList(companyGroupId, start, end, contentListData, 
																	urlTargetName, channelVocabularyName, contentTypeVocabularyName, 
																	publicServletMapping, articleImagePath, blogImagePath, supplement, defaultImagePath, displayDateFormat);

						output = JSONFactoryUtil.serialize( contentList );	
						
					} else {
						output = "{'status':'error', 'message':'Preferences for Content List " + portletId + " is empty.'}";
					} // if ((null != preferenceList) && (preferenceList.size() > 0)) {
					
				} else {
					output = "{'status':'error', 'message':'Preferences for Content List " + portletId + " not found.'}";
				} // if ( null != prefsList && prefsList.size() > 0 ) {
				
			} else if ( contentType.toUpperCase().equalsIgnoreCase(UPSERT) ) {
				
				String groupId         = ParamUtil.getStringParameter(renderRequest, "groupId");
				String resourcePrimKey = ParamUtil.getStringParameter(renderRequest, "resourcePrimKey");
				String portletType     = ParamUtil.getStringParameter(renderRequest, "portletType");
								
				User currentUser        = themeDisplay.getUser();				
				boolean isSignedIn      = themeDisplay.isSignedIn();
				boolean isMemberOfGroup = UserLocalServiceUtil.hasGroupUser(new Long(groupId), currentUser.getUserId());
				boolean hasValidRole    = renderRequest.isUserInRole("Administrator") ? true : false;
				
				if ( isSignedIn && isMemberOfGroup ) {

					if ( !hasValidRole ) {
						
						List<Role> userGroupRoles = RoleLocalServiceUtil.getUserGroupRoles(currentUser.getUserId(), new Long(groupId));
						
						if ( null != userGroupRoles && userGroupRoles.size() > 0 ) {
							for ( int index = 0; index < userGroupRoles.size(); index++ ) {
								Role currentRole = userGroupRoles.get(index);
								String currentRoleName = currentRole.getName();								
								if ( "Community Owner".equals(currentRoleName) || 
									 "Community Administrator".equals(currentRoleName) || 
									 "Community ControlPanel".equals(currentRoleName) || 
									 "Community Editor".equals(currentRoleName) ) {
									hasValidRole = true;									
								}	
							}	
						}
					}
					
					if ( hasValidRole ) {
						
						if ( FULL.equalsIgnoreCase(portletType) ) {

							// Various values from the UI form.
							String title 		  = URLDecoder.decode( ParamUtil.getStringParameter(renderRequest, "title") ,"UTF-8");
							String description 	  = URLDecoder.decode( ParamUtil.getStringParameter(renderRequest, "description") ,"UTF-8");
							String creator 		  = URLDecoder.decode( ParamUtil.getStringParameter(renderRequest, "creator") ,"UTF-8");
							String multiMedia 	  = URLDecoder.decode( ParamUtil.getStringParameter(renderRequest, "multiMedia") ,"UTF-8");
							String relatedContent = URLDecoder.decode( ParamUtil.getStringParameter(renderRequest, "relatedContent") ,"UTF-8");
							String comments 	  = ParamUtil.getStringParameter(renderRequest, "comments");
							String social 		  = ParamUtil.getStringParameter(renderRequest, "social");
							String peerReviewed	  = ParamUtil.getStringParameter(renderRequest, "peerReviewed");
							String imagePath 	  = ParamUtil.getStringParameter(renderRequest, "imagePath");
							String categories 	  = ParamUtil.getStringParameter(renderRequest, "categories");
							String dateTime		        = ParamUtil.getStringParameter(renderRequest, "dateTime");
							String expirationDateTime   = ParamUtil.getStringParameter(renderRequest, "expirationDateTime");
							String dateTimeHM		    = ParamUtil.getStringParameter(renderRequest, "dateTimeHM");
							String expirationDateTimeHM = ParamUtil.getStringParameter(renderRequest, "expirationDateTimeHM");							
													
							// The content
							String content = URLDecoder.decode( ParamUtil.getStringParameter(renderRequest, "content") ,"UTF-8");
							content = "<?xml version='1.0' encoding='UTF-8'?><root><static-content><![CDATA[" + content + "]]></static-content></root>";
							
							// Display and, if needed, Expire dates
							Date displayDate = DateUtils.stringToDate(dateTime.trim() + " " + dateTimeHM.trim(), "MM/dd/yyyy hh:mm a");
							Date expireDate = null;
							if ( !"".equals(expirationDateTime.trim()) ) {
								expireDate = DateUtils.stringToDate(expirationDateTime.trim() + " " + expirationDateTimeHM.trim(), "MM/dd/yyyy hh:mm a");
							}
							
							// The URL Title
							String urlTitle = RestAPIUtil.prepareUrlTitle(title);
							urlTitle = urlTitle.toLowerCase();	
							
							// The Category Ids
							long[] categoryIds = new long[0];
							String[] categoryArray = categories.split(",");
							if ( null != categoryArray && categoryArray.length > 0 ) {
								categoryIds = new long[categoryArray.length];
								for ( int index = 0; index < categoryArray.length; index++ ) {
									categoryIds[index] = new Long(categoryArray[index]);	
								}
							}
							
							// The Expando (Custom Field) values
							Map<String,String> expandoValueMap = new HashMap<String,String>();
							expandoValueMap.put("creator", creator);
							expandoValueMap.put("multiMedia", multiMedia);
							expandoValueMap.put("relatedContent", relatedContent);
							expandoValueMap.put("comments", comments);
							expandoValueMap.put("social", social);
							expandoValueMap.put("peerReviewed", peerReviewed);
							
							if ( "".equals(resourcePrimKey) || "0".equals(resourcePrimKey) ) {						
								RestAPIUtil.createNewEntry(new Long(groupId), themeDisplay, displayDate, expireDate, title, urlTitle, description, categoryIds, imagePath, content, expandoValueMap);	
							} else {						
								RestAPIUtil.updateEntry(new Long(groupId), themeDisplay, new Long(resourcePrimKey), displayDate, expireDate, title, urlTitle, description, categoryIds, imagePath, content, expandoValueMap);						
							}
							
							output = "{'status':'success', 'resourcePrimKey':" + resourcePrimKey + "}";
							
						} else if ( CONTENT.equalsIgnoreCase(portletType) ) {
							
							String originalContent = ParamUtil.getStringParameter(renderRequest, "content");
							String content = originalContent;
							content = URLDecoder.decode(content,"UTF-8");
							content = "<?xml version='1.0' encoding='UTF-8'?><root><static-content><![CDATA[" + content + "]]></static-content></root>";
							
							String title = ParamUtil.getStringParameter(renderRequest, "title");
							title = URLDecoder.decode(title,"UTF-8");
							
							String description = ParamUtil.getStringParameter(renderRequest, "description");
							description = URLDecoder.decode(description,"UTF-8");
														
							if ( "".equals(resourcePrimKey) || "0".equals(resourcePrimKey) ) {
								
							} else {
								JournalArticle article = JournalArticleLocalServiceUtil.getLatestArticle(new Long(resourcePrimKey), 0);
								article.setContent(content);
								
								if ( null != title && !"".equals(title.trim()) ) {
									article.setTitle(title);
								}
								
								if ( null != description && !"".equals(description.trim()) ) {
									article.setDescription(description);
								}								
				
								article = JournalArticleLocalServiceUtil.updateJournalArticle(article);
								
								output = "{'status':'success', 'resourcePrimKey':" + resourcePrimKey + "}";
							}

						} // if ( FULL.equalsIgnoreCase(portletType) ) {
					}// if ( hasValidRole ) {
				} // if ( isSignedIn && isMemberOfGroup ) {

			} else {
				output = "{'status':'error', 'message':'Content type not found or set.'}";
			} // if ( contentType.toUpperCase().equalsIgnoreCase(ARTICLE) ) {	
		
		} catch (Exception e) {
			output = "{'status':'error', 'message':'There is a general error with the ajax call for the content.  Please look at the log files.'}";
			e.printStackTrace();
		}

		model.put("output", STARTDELIM + output + ENDDELIM);
		ModelAndView modelAndView = new ModelAndView("Output", model);

		return modelAndView;
	}

	private String getArticleContent(RenderRequest renderRequest) {
		
		String output = "";
		
		try {
			
			// -----------------------------------------------------------------------------------------------------------------
			// Get the Article content
			// -----------------------------------------------------------------------------------------------------------------
			String urlTitle = ParamUtil.getStringParameter(renderRequest, "urlTitle");
			JSONObject theContent = new JSONObject();

			if ( null != urlTitle && !"".equals(urlTitle) ) {
				
				String groupId = ParamUtil.getStringParameter(renderRequest, "groupId");

				JournalArticle article = JournalArticleLocalServiceUtil.getLatestArticleByUrlTitle(new Long(groupId), urlTitle, 0);

				if (null != article) {

					String articleSource = RestAPIUtil.getArticleSource(article);
					String headerImagePath = RestAPIUtil.getHeaderImagePath(article);
					String articleContent = RestAPIUtil.getContent(article, getVelocityConfigurer());
					String contentTitle = article.getTitle();
					String pageTitle = "Computing Now | " + article.getTitle();
					String displayDate = DateUtils.dateToString(article.getDisplayDate(), "MMM dd, yyyy HH:mm a").toUpperCase();
					String commentParentId = article.getArticleId();
					String commentParentType = RestAPIUtil.ARTICLE;
					
					String creator = "";
					if ( article.getExpandoBridge().hasAttribute( "Creator" ) ) {
						if ( null != article.getExpandoBridge().getAttribute( "Creator" ) ) {
							creator = (String) article.getExpandoBridge().getAttribute( "Creator" );	
							creator = creator.trim().length() > 0 ? creator + " <br/> " : "";
						}
					}
					
					// Check to see if the article has been "peer reviewed" or not.		
					boolean peerReviewed = false;
					if ( article.getExpandoBridge().hasAttribute( "PeerReviewed" ) ) {
						if ( null != article.getExpandoBridge().getAttribute( "PeerReviewed" ) ) {
							peerReviewed = (Boolean) article.getExpandoBridge().getAttribute( "PeerReviewed" );
						}
					}
					
					// Increment the "viewcount" for this content Asset Entry record
					AssetEntryLocalServiceUtil.incrementViewCounter(article.getUserId(), "com.liferay.portlet.journal.model.JournalArticle", article.getResourcePrimKey());					
					
					theContent.put("body", articleContent);
					theContent.put("urlTitle", urlTitle);
					theContent.put("title", contentTitle);
					theContent.put("pageTitle", pageTitle);
					theContent.put("displayDate", displayDate );
					theContent.put("creator", creator);
					theContent.put("articleSource", articleSource);
					theContent.put("headerImagePath", headerImagePath);
					theContent.put("commentParentId", commentParentId);
					theContent.put("commentParentType", commentParentType);
					theContent.put("peerReviewed", peerReviewed);
					theContent.put("status", "success");

				} // if (null != article) {			
			} // if ( null != urlTitle && !"".equals(urlTitle) ) {
			
			output = theContent.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return output;		
	}
	
	private static String getBlogContent(RenderRequest renderRequest) {
		
		String output = "";
		
		try {
			
			// -----------------------------------------------------------------------------------------------------------------
			// Get the Blog content
			// -----------------------------------------------------------------------------------------------------------------
			String urlTitle = ParamUtil.getStringParameter(renderRequest, "urlTitle");
			JSONObject theContent = new JSONObject();

			if ( null != urlTitle && !"".equals(urlTitle) ) {
				
				String groupId = ParamUtil.getStringParameter(renderRequest, "groupId");

				BlogsEntry blogEntry = BlogsEntryLocalServiceUtil.getEntry(new Long(groupId), urlTitle);

				if (null != blogEntry) {
					
					theContent.put("body", blogEntry.getContent());
					theContent.put("urlTitle", urlTitle);
					theContent.put("title", blogEntry.getTitle());
					theContent.put("displayDate", DateUtils.dateToString(blogEntry.getDisplayDate(), "MMM dd, yyyy HH:mm a").toUpperCase() );
					theContent.put("commentParentId", blogEntry.getEntryId());
					theContent.put("commentParentType", RestAPIUtil.BLOG);
					theContent.put("pageTitle", blogEntry.getTitle());

					String creator = "";
					if ( blogEntry.getExpandoBridge().hasAttribute( "Creator" ) ) {
						if ( null != blogEntry.getExpandoBridge().getAttribute( "Creator" ) ) {							
							creator = (String) blogEntry.getExpandoBridge().getAttribute( "Creator" );	
							creator = creator.trim().length() > 0 ? creator + " <br/> " : "";
							
						}
					}
					
					// Increment the "viewcount" for this content Asset Entry record
					AssetEntryLocalServiceUtil.incrementViewCounter(blogEntry.getUserId(), "com.liferay.portlet.blogs.model.BlogsEntry", blogEntry.getEntryId());
		
					theContent.put("creator", creator);
					theContent.put("status", "success");

				} // if (null != article) {				
			} // if ( null != urlTitle && !"".equals(urlTitle) ) {
			
			output = theContent.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return output;		
	}	
	
}