package org.ieeecs.csdl.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.*;

import javax.portlet.PortletPreferences;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.math.NumberUtils;

import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;
import org.ieee.common.json.XML;

import org.ieeecs.csdl.action.StartupCSDLAction;
import org.ieeecs.csdl.bean.AssociatedContentBean;
import org.ieeecs.csdl.bean.ContentBean;
import org.ieeecs.csdl.bean.CreatorBean;
import org.ieeecs.csdl.bean.IssueBean;
import org.ieeecs.csdl.bean.PackageBean;
import org.ieeecs.csdl.bean.PublicationBean;
import org.ieeecs.csdl.bean.ReferenceBean;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.journal.model.JournalArticle;


public class DigitalLibraryUtil {

	public static final String CONFIG = "CONFIG";
	public static final String MODE   = "DEACTIVATED";
	public static final String USERID = "";

	public static final String PORTLETBORDERCOLORTOP    = "CCCCCC";
	public static final String PORTLETBORDERCOLORRIGHT  = "CCCCCC";
	public static final String PORTLETBORDERCOLORBOTTOM = "CCCCCC";
	public static final String PORTLETBORDERCOLORLEFT   = "CCCCCC";
	public static final String PORTLETBORDERPIXELTOP    = "1";
	public static final String PORTLETBORDERPIXELRIGHT  = "1";
	public static final String PORTLETBORDERPIXELBOTTOM = "1";
	public static final String PORTLETBORDERPIXELLEFT   = "1";
	public static final String PORTLETBACKGROUNDCOLOR   = "FFFFFF";
	public static final String PORTLETTOPLEFTRADIUS     = "0";
	public static final String PORTLETBOTTOMLEFTRADIUS  = "0";
	public static final String PORTLETTOPRIGHTRADIUS    = "0";
	public static final String PORTLETBOTTOMRIGHTRADIUS = "0";
	
	public static final String INNERMARGINS                = "20px";
	public static final String RESTAPI                     = "/portal/web/CS-Communities-Help/rest/-/api/";
	public static final String HOMEPAGEFEATUREDSLIDERDELAY = "1000";
	
	public static final String MAGS        = "mags";
	public static final String TRANS       = "trans";
	public static final String LETTERS     = "letters";
	public static final String PROCEEDINGS = "proceedings";
	public static final String PREPRINTS   = "PrePrints";
	public static final String RAPIDPOSTS  = "RapidPosts";
	
	public static final String CSDLHEADERTITLE                 = "IEEE COMPUTER SOCIETY DIGITAL LIBRARY";
	public static final String CSDLHEADERINTRODUCTION          = "This is a short paragraph describing what the CSDL is and how it will benefit our visitors/members.";
	public static final String CSDLHEADERINTRODUCTIONIMAGEPATH = "/ieeecs-DigitalLibrary-portlet/images/CSDL_IntroGraphic.jpg";
	public static final String SUBSCRIPTIONBLURB               = "Short sentence on why a person should subscribe.";
	public static final String CSDLSEARCHTITLE                 = "Search the Digital Library";
	public static final String MAGAZINESDESCRIPTION            = "This is the description for the Magazine publications section. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	public static final String TRANSACTIONSDESCRIPTION         = "This is the description for the Transaction publications section. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	public static final String LETTERSDESCRIPTION              = "This is the description for the Letter publications section. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	public static final String PROCEEDINGSDESCRIPTION          = "This is the description for the Proceeding publications section. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	public static final String PREPRINTSDESCRIPTION            = "This is the description for the PrePrint section. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	public static final String RAPIDPOSTSDESCRIPTION           = "This is the description for the RapidPost section. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
	public static final String MAGAZINELABEL                   = "Magazines";
	public static final String TRANSACTIONLABEL                = "Transactions";
	public static final String LETTERLABEL                     = "Letters";
	public static final String PROCEEDINGLABEL                 = "Proceedings";
	public static final String RESOURCELABEL                   = "Resources";
	public static final String RESOURCEARTICLEID               = "17101";
	public static final String RESOURCEDEFAULTTEXT             = "Please enter your Resource links here.";
	public static final String PROCEEDINGSURLSEGMENT           = "proceedings";
	public static final String VOLUMEABBREV                    = " vol.";
	public static final String ISSUEABBREV                     = " no.";
	public static final String ISSUENUMBERLABEL                = "Issue No. ";	
	public static final String PREPRINTSLABEL                  = "PrePrints";
	public static final String RAPIDPOSTSLABEL                 = "RapidPosts";
	public static final String CSDLHOMELABEL                   = "CSDL Home";
	public static final String VOLUMEANDISSUELABEL             = "SELECT YEAR & ISSUE";
	public static final String TABLEOFCONTENTSLABEL            = "TABLE OF CONTENTS";
	
	public static final String PUBTYPE       = "pubType";	
	public static final String IDPREFIX      = "idPrefix";
	public static final String FILENAME      = "fileName";
	public static final String FILEDETAIL    = "fileDetail";
	public static final String FILETYPE      = "fileType";
	public static final String YEAR          = "year";
	public static final String CATALOGNUMBER = "catalogNumber";
	public static final String VOLUMENUMBER  = "volumeNumber";
	public static final String ISSUENUMBER   = "issueNumber";
	
	public static final String CSDLCONTEXTPATH = "/portal/web/guest/csdl/-/csdl";
	public static final String CSDLINDEXPAGE   = "index/html";
	public static final String CSDLLISTPAGE    = "list/html";
	public static final String CSDLTOCPAGE     = "toc.htm";
	public static final String PREPRINTSURL    = "preprint";
	public static final String RAPIDPOSTSURL   = "rapidpost";
	public static final String DOIURLPREFIX    = "http://doi.ieeecomputersociety.org/";	
	
	public static final String PREPRINTSYEAR         = "5555";
	public static final String PREPRINTSISSUENUMBER  = "01";	
	public static final String RAPIDPOSTSYEAR        = "5555";
	public static final String RAPIDPOSTSISSUENUMBER = "02";
	
	public static final String BUTTONABSTRACT = "ABSTRACT";
	public static final String BUTTONFULLTEXT = "FULL TEXT";
	public static final String BUTTONBUY      = "BUY";
	public static final String BUTTONPDF      = "PDF";
	public static final String BUTTONHTML     = "HTML";
	public static final String BUTTONXPLORE   = "IEEE XPLORE";
	public static final String BUTTONOPENURL  = "OPEN URL";
	
	public static Properties csdlProperties;
	public static final String CSDLPROPERTIESFILE = "csdl.properties";
	
	public static String CSDLBASE = "";
	
	public static final String PUBLICATIONS_KEY = "csdl.cache.publications.location";
	public static final String MAGAZINES_KEY    = "csdl.cache.magazines.location";
	public static final String TRANSACTIONS_KEY = "csdl.cache.transactions.location";
	public static final String LETTERS_KEY      = "csdl.cache.letters.location";
	public static final String PROCEEDINGS_KEY  = "csdl.cache.proceedings.location";

	public static JSONObject magsUICache        = null;
	public static JSONObject transUICache       = null;
	public static JSONObject lettersUICache     = null;
	public static JSONObject proceedingsUICache = null;
	

	public static void putPortletPreferencesIntoModel(PortletPreferences prefs, Map<String,Object> model) {

		try {

			model.put("portletMode", prefs.getValue("portletMode", MODE));
			model.put("modifiedByUserId", prefs.getValue("modifiedByUserId", USERID));

			model.put("portletBorderColorTop", prefs.getValue("portletBorderColorTop", PORTLETBORDERCOLORTOP));
			model.put("portletBorderColorRight", prefs.getValue("portletBorderColorRight", PORTLETBORDERCOLORRIGHT));
			model.put("portletBorderColorBottom", prefs.getValue("portletBorderColorBottom", PORTLETBORDERCOLORBOTTOM));
			model.put("portletBorderColorLeft", prefs.getValue("portletBorderColorLeft", PORTLETBORDERCOLORLEFT));
			model.put("portletBorderPixelTop", prefs.getValue("portletBorderPixelTop", PORTLETBORDERPIXELTOP));
			model.put("portletBorderPixelRight", prefs.getValue("portletBorderPixelRight", PORTLETBORDERPIXELRIGHT));
			model.put("portletBorderPixelBottom", prefs.getValue("portletBorderPixelBottom", PORTLETBORDERPIXELBOTTOM));
			model.put("portletBorderPixelLeft", prefs.getValue("portletBorderPixelLeft", PORTLETBORDERPIXELLEFT));
			model.put("portletBackgroundColor", prefs.getValue("portletBackgroundColor", PORTLETBACKGROUNDCOLOR));
			model.put("portletTopLeftRadius", prefs.getValue("portletTopLeftRadius", PORTLETTOPLEFTRADIUS));
			model.put("portletBottomLeftRadius", prefs.getValue("portletBottomLeftRadius", PORTLETBOTTOMLEFTRADIUS));
			model.put("portletTopRightRadius", prefs.getValue("portletTopRightRadius", PORTLETTOPRIGHTRADIUS));
			model.put("portletBottomRightRadius", prefs.getValue("portletBottomRightRadius", PORTLETBOTTOMRIGHTRADIUS));
			
			model.put("innerMargins", prefs.getValue("innerMargins", INNERMARGINS));
			model.put("restAPI", prefs.getValue("restAPI", RESTAPI));
			model.put("homePageFeaturedSliderDelay", prefs.getValue("homePageFeaturedSliderDelay", HOMEPAGEFEATUREDSLIDERDELAY));
						
			model.put("mags", prefs.getValue("mags", MAGS));
			model.put("trans", prefs.getValue("trans", TRANS));
			model.put("letters", prefs.getValue("letters", LETTERS));
			model.put("proceedings", prefs.getValue("proceedings", PROCEEDINGS));
			model.put("prePrints", prefs.getValue("prePrints", PREPRINTS));
			model.put("rapidPosts", prefs.getValue("rapidPosts", RAPIDPOSTS));
			
			model.put("csdlHeaderTitle", prefs.getValue("csdlHeaderTitle", CSDLHEADERTITLE));
			model.put("csdlHeaderIntroduction", prefs.getValue("csdlHeaderIntroduction", CSDLHEADERINTRODUCTION));
			model.put("csdlHeaderIntroductionImagePath", prefs.getValue("csdlHeaderIntroductionImagePath", CSDLHEADERINTRODUCTIONIMAGEPATH));
			model.put("subscriptionBlurb", prefs.getValue("subscriptionBlurb", SUBSCRIPTIONBLURB));
			model.put("csdlSearchTitle", prefs.getValue("csdlSearchTitle", CSDLSEARCHTITLE));
			model.put("magazinesDescription", prefs.getValue("magazinesDescription", MAGAZINESDESCRIPTION));
			model.put("transactionsDescription", prefs.getValue("transactionsDescription", TRANSACTIONSDESCRIPTION));
			model.put("lettersDescription", prefs.getValue("lettersDescription", LETTERSDESCRIPTION));
			model.put("proceedingsDescription", prefs.getValue("proceedingsDescription", PROCEEDINGSDESCRIPTION));
			model.put("prePrintsDescription", prefs.getValue("prePrintsDescription", PREPRINTSDESCRIPTION));
			model.put("rapidPostsDescription", prefs.getValue("rapidPostsDescription", RAPIDPOSTSDESCRIPTION));
			model.put("magazineLabel", prefs.getValue("magazineLabel", MAGAZINELABEL));
			model.put("transactionLabel", prefs.getValue("transactionLabel", TRANSACTIONLABEL));
			model.put("letterLabel", prefs.getValue("letterLabel", LETTERLABEL));
			model.put("proceedingLabel", prefs.getValue("proceedingLabel", PROCEEDINGLABEL));
			model.put("resourceLabel", prefs.getValue("resourceLabel", RESOURCELABEL));
			model.put("resourceArticleId", prefs.getValue("resourceArticleId", RESOURCEARTICLEID));
			model.put("resourceDefaultText", prefs.getValue("resourceDefaultText", RESOURCEDEFAULTTEXT));
			model.put("proceedingsUrlSegment", prefs.getValue("proceedingsUrlSegment", PROCEEDINGSURLSEGMENT));
			model.put("volumeAbbrev", prefs.getValue("volumeAbbrev", VOLUMEABBREV));
			model.put("issueAbbrev", prefs.getValue("issueAbbrev", ISSUEABBREV));
			model.put("issueNumberLabel", prefs.getValue("issueNumberLabel", ISSUENUMBERLABEL));
			model.put("prePrintsLabel", prefs.getValue("prePrintsLabel", PREPRINTSLABEL));
			model.put("rapidPostsLabel", prefs.getValue("rapidPostsLabel", RAPIDPOSTSLABEL));
			model.put("csdlHomeLabel", prefs.getValue("csdlHomeLabel", CSDLHOMELABEL));
			model.put("volumeAndIssueLabel", prefs.getValue("volumeAndIssueLabel", VOLUMEANDISSUELABEL));
			model.put("tableOfContentsLabel", prefs.getValue("tableOfContentsLabel", TABLEOFCONTENTSLABEL));			
			
			model.put("pubType", prefs.getValue("pubType", PUBTYPE));
			model.put("idPrefix", prefs.getValue("idPrefix", IDPREFIX));
			model.put("fileName", prefs.getValue("fileName", FILENAME));
			model.put("fileDetail", prefs.getValue("fileDetail", FILEDETAIL));
			model.put("fileType", prefs.getValue("fileType", FILETYPE));
			model.put("year", prefs.getValue("year", YEAR));
			model.put("catalogNumber", prefs.getValue("catalogNumber", CATALOGNUMBER));
			model.put("volumeNumber", prefs.getValue("volumeNumber", VOLUMENUMBER));
			model.put("issueNumber", prefs.getValue("issueNumber", ISSUENUMBER));
			
			model.put("csdlContextPath", prefs.getValue("csdlContextPath", CSDLCONTEXTPATH));
			model.put("csdlContextPathPrefix", prefs.getValue("csdlContextPath", CSDLCONTEXTPATH));
			model.put("csdlIndexPage", prefs.getValue("csdlIndexPage", CSDLINDEXPAGE));
			model.put("csdlListPage", prefs.getValue("csdlListPage", CSDLLISTPAGE));
			model.put("csdlToCPage", prefs.getValue("csdlToCPage", CSDLTOCPAGE));
			model.put("prePrintsUrl", prefs.getValue("prePrintsUrl", PREPRINTSURL));
			model.put("rapidPostsUrl", prefs.getValue("rapidPostsUrl", RAPIDPOSTSURL));
			model.put("doiUrlPrefix", prefs.getValue("doiUrlPrefix", DOIURLPREFIX));
			
			model.put("prePrintsYear", prefs.getValue("prePrintsYear", PREPRINTSYEAR));
			model.put("prePrintsIssueNumber", prefs.getValue("prePrintsIssueNumber", PREPRINTSISSUENUMBER));
			model.put("rapidPostsYear", prefs.getValue("rapidPostsYear", RAPIDPOSTSYEAR));
			model.put("rapidPostsIssueNumber", prefs.getValue("rapidPostsIssueNumber", RAPIDPOSTSISSUENUMBER));
			
			model.put("buttonAbstract", prefs.getValue("buttonAbstract", BUTTONABSTRACT));
			model.put("buttonFullText", prefs.getValue("buttonFullText", BUTTONFULLTEXT));
			model.put("buttonBuy", prefs.getValue("buttonBuy", BUTTONBUY));
			model.put("buttonPDF", prefs.getValue("buttonPDF", BUTTONPDF));
			model.put("buttonHTML", prefs.getValue("buttonHTML", BUTTONHTML));
			model.put("buttonXPLORE", prefs.getValue("buttonXPLORE", BUTTONXPLORE));
			model.put("buttonOpenUrl", prefs.getValue("buttonOpenUrl", BUTTONOPENURL));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void putBreadcrumbInfoIntoModel(Map<String,Object> model, JSONObject idPrefixDataJSON, String csdlContextPathPrefix, String csdlIndexPage, String issueNumberLabel, 
			                                      String pubType, String idPrefix, String year, String issueNumber) {
		
		try {
			
			String publicationName = idPrefixDataJSON.getString("display");
			String publicationUrl  = csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + csdlIndexPage;
			String volumeName      = year;
			String volumeUrl       = csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + year + "/" + csdlIndexPage;
			String issueUrlPrefix  = "";
			String issueUrl        = "";
			String issueName       = issueNumberLabel + issueNumber + " - ";
			
			if ( !"".equals(issueNumber.trim()) ) {			
				issueUrlPrefix = csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + year + "/" + issueNumber;
				issueUrl       = csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + year + "/" + issueNumber + "/" + csdlIndexPage;				
			} else {
				issueUrlPrefix = csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + year;
				issueUrl       = csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + year + "/" + csdlIndexPage;	
			}
						
			model.put("bc_publicationType", pubType);
			model.put("bc_publicationPrefix", idPrefix);
			model.put("bc_publicationName", publicationName);
			model.put("bc_publicationUrl", publicationUrl);
			model.put("bc_volumeName", volumeName);;
			model.put("bc_volumeUrl", volumeUrl);;
			model.put("bc_issueUrlPrefix", issueUrlPrefix);
			model.put("bc_issueUrl", issueUrl);
			model.put("bc_issueName", issueName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void putBreadcrumbForProceedingsInfoIntoModel(Map<String,Object> model, String csdlContextPathPrefix, String idPrefix, String csdlListPage, 
																String csdlIndexPage, String proceedingsUrlSegment) {

		try {
			
			String proceedingAlphaNum    = "";
			String proceedingAlphaNumUrl = "";
			String proceedingAbbrev      = "";
			String proceedingAbbrevUrl   = "";			
			
			if ( ( idPrefix.length() == 1 && "abcdefghijklmnopqrstuvwxyz".indexOf(idPrefix) > -1 ) || "0-9".equals(idPrefix) ) {
				proceedingAlphaNum    = idPrefix.toUpperCase();
				proceedingAlphaNumUrl = csdlContextPathPrefix + "/" + proceedingsUrlSegment + "/" + idPrefix.toLowerCase() + "/" + csdlListPage;
			} else if ( idPrefix.length() > 1 && !"0-9".equals(idPrefix) ) {
				proceedingAlphaNum    = idPrefix.substring(0,1).toUpperCase();
				proceedingAlphaNumUrl = csdlContextPathPrefix + "/" + proceedingsUrlSegment + "/" + idPrefix.substring(0,1).toLowerCase() + "/" + csdlListPage;
				proceedingAbbrev      = idPrefix.toUpperCase();
				proceedingAbbrevUrl   = csdlContextPathPrefix + "/" + proceedingsUrlSegment + "/" + idPrefix.toLowerCase() + "/" + csdlIndexPage;
			}

			model.put("bc_proceedingAlphaNum", proceedingAlphaNum);
			model.put("bc_proceedingAlphaNumUrl", proceedingAlphaNumUrl);
			model.put("bc_proceedingAbbrev", proceedingAbbrev);;
			model.put("bc_proceedingAbbrevUrl", proceedingAbbrevUrl);;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static void setPorletMode(ThemeDisplay themeDisplay, PortletPreferences prefs, Map<String,Object> model) {
		
		// -----------------------------------------------------------------------------------------------------------------
		// If the current user has Deactivated this Portlet, we should still let them
		// see what they are doing.  Allow only this user who is controlling this portlet
		// configuration to see the Portlet in "preview" mode.  No other users will see the Portlet
		// until this admin user sets it to "Activated".
		// -----------------------------------------------------------------------------------------------------------------
		String currentUserId = new Long(themeDisplay.getUserId()).toString();
		String modifiedByUserId = prefs.getValue("modifiedByUserId", USERID);
		String currentMode = prefs.getValue("portletMode", MODE);

		if ( currentUserId.trim().equalsIgnoreCase(modifiedByUserId.trim()) && currentMode.trim().equalsIgnoreCase(MODE) ) {
			model.put("portletMode", "PREVIEW");
		}
	}
	
	public static long getTime() {
		return (new Date()).getTime();
	}
	
	public static long getTotalTime(long startTimeMS) {
		return (new Date()).getTime() - startTimeMS;
	}
	
	public static JSONObject getJSONObject(JSONObject targetJSONObject, String attributeName) {
		
		JSONObject resultingJSONObject = null;
		
		try {			
			resultingJSONObject = null != targetJSONObject && targetJSONObject.has(attributeName) ? targetJSONObject.getJSONObject(attributeName) : null;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultingJSONObject;
	}
	
	public static JSONArray getJSONArray(JSONObject targetJSONObject, String attributeName) {
		
		JSONArray resultingJSONArray = null;
		
		try {			
			resultingJSONArray = null != targetJSONObject && targetJSONObject.has(attributeName) ? targetJSONObject.getJSONArray(attributeName) : null;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultingJSONArray;
	}	
	
	public static String getString(JSONObject targetJSONObject, String attributeName) {
		
		String resultingString = "";
		
		try {			
			resultingString = null != targetJSONObject && targetJSONObject.has(attributeName) ? targetJSONObject.getString(attributeName).trim() : "";			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultingString;
	}
	
	public static JSONObject jsonObjectFromDotNotation(JSONObject parentJSON, String dotNotation) {
		
		JSONObject resultingJSONObject = null;
		JSONObject processJSONObject = parentJSON;
		
		try {
		
			if ( null != dotNotation && !"".equals(dotNotation) ) {
				
				String[] nodes = dotNotation.split("\\.");
				if ( null != nodes && nodes.length > 0 ) {
					for ( int index = 0; index < nodes.length; index++ ) {
						String currentNode = nodes[index];
						processJSONObject = getJSONObject(processJSONObject, currentNode);
					}
					
					resultingJSONObject = null != processJSONObject ? processJSONObject : resultingJSONObject;
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultingJSONObject;
	}
	
	public static JSONArray jsonArrayFromDotNotation(JSONObject parentJSON, String dotNotation) {
		
		JSONArray resultingJSONArray = null;
		JSONObject processJSONObject = parentJSON;
		
		try {
		
			if ( null != dotNotation && !"".equals(dotNotation) ) {
				
				String[] nodes = dotNotation.split("\\.");
				if ( null != nodes && nodes.length > 0 ) {
					for ( int index = 0; index < nodes.length; index++ ) {
						String currentNode = nodes[index];
						if ( index < nodes.length - 1 ) {
							processJSONObject = getJSONObject(processJSONObject, currentNode);
						} else {
							if ( processJSONObject.get(currentNode) instanceof JSONArray ) {
								resultingJSONArray = getJSONArray(processJSONObject, currentNode);
							}
						}
					}
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultingJSONArray;
	}
	
	public static JSONObject byteArrayToJSONObject(byte[] responseBody) {
		
		JSONObject jsonResponse = null;
		
		try {
			if ( null != responseBody ) {
				jsonResponse = XML.toJSONObject(new String(responseBody));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return jsonResponse;
	}
	
	public static void goHome(String csdlContextPathPrefix, String csdlIndexPage, Map<String,Object> model) {
	
		try {
			
			Map<Integer,PublicationBean> magazineMap    = new TreeMap<Integer,PublicationBean>();
			Map<Integer,PublicationBean> transactionMap = new TreeMap<Integer,PublicationBean>();
			Map<Integer,PublicationBean> letterMap      = new TreeMap<Integer,PublicationBean>();
			
			try {

				populatePublication(magazineMap, DigitalLibraryUtil.CSDLBASE, DigitalLibraryUtil.MAGS, csdlContextPathPrefix, csdlIndexPage);
				populatePublication(transactionMap, DigitalLibraryUtil.CSDLBASE, DigitalLibraryUtil.TRANS, csdlContextPathPrefix, csdlIndexPage);
				populatePublication(letterMap, DigitalLibraryUtil.CSDLBASE, DigitalLibraryUtil.LETTERS, csdlContextPathPrefix, csdlIndexPage);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.put("magazineMap", magazineMap);
			model.put("transactionMap", transactionMap);
			model.put("letterMap", letterMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void getIssueOrTableOfContents(JSONArray packageArray, String csdlContextPathPrefix, String pubType, String idPrefix, String year, String issueNumber, 
			                                     String csdlIndexPage, Object targetObject) {

		try {
			
			if ( null != packageArray && packageArray.length() > 0 ) {
				for ( int index = 0; index < packageArray.length(); index++ ) {
					
					JSONObject currentPackage = packageArray.getJSONObject(index);
					String publicationDate = getString(currentPackage, "publicationdate");
					String publicationYear = publicationDate.trim().substring(0,4);
					
					if ( year.equals(publicationYear) ) {
						
						String title = getString(currentPackage, "title");								
						JSONObject packagemetadata = getJSONObject(currentPackage, "packagemetadata");
						JSONArray keyValuePairs = getJSONArray(packagemetadata, "value");
						
						if ( null != keyValuePairs && keyValuePairs.length() > 0 ) {
							for ( int valIndex = 0; valIndex < keyValuePairs.length(); valIndex++ ) {
								JSONObject contentNamePair = keyValuePairs.getJSONObject(valIndex);
								String currentName = getString(contentNamePair, "name");
								
								if ( "issueNumber".equalsIgnoreCase(currentName) ) {
									String currentIssueNumber = getString(contentNamePair, "content");
									currentIssueNumber = currentIssueNumber.length() == 1 ? "0"+currentIssueNumber : currentIssueNumber;

									if ( targetObject instanceof Map<?,?> ) {
		
										IssueBean ib = new IssueBean();
										ib.setYear(year);
										ib.setIssueNumber(currentIssueNumber);											
										ib.setUrl( csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + year + "/" + currentIssueNumber + "/" + csdlIndexPage);
										ib.setTitle(title);
										((TreeMap<String,IssueBean>) targetObject).put(publicationDate, ib);	
																		
									} else if ( targetObject instanceof List<?> ) {
				
										if ( currentIssueNumber.equals(issueNumber) ) {											
											String packageId = getString(currentPackage, "packageid");										
											populateTableOfContentsList(targetObject, packageId);
										} 
									} 
								} 
							} // for ( int valIndex = 0; valIndex < keyValuePairs.length(); valIndex++ ) {
						} // if ( null != keyValuePairs && keyValuePairs.length() > 0 ) {
					} // if ( year.equals(publicationYear) ) {
				} // for ( int index = 0; index < packageArray.length(); index++ ) {
			} // if ( null != packageArray && packageArray.length() > 0 ) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void populatePublication(Map<Integer,PublicationBean> publicationMap, String csdlBase, String pubType, String csdlContextPathPrefix, String csdlIndexPage) {
		
		try {
			
			JSONObject csdlDataJSON = new JSONObject(csdlBase);
			JSONObject pubDataJSON = csdlDataJSON.getJSONObject(pubType);
			
			Iterator<String> magIterator = pubDataJSON.keys();
			while ( magIterator.hasNext() ) {
			
				String idPrefix = (String) magIterator.next();
				JSONObject currentJSON = pubDataJSON.getJSONObject(idPrefix);
				Integer listOrder = new Integer(currentJSON.getString("listOrder"));
				
				PublicationBean ptb = new PublicationBean();
				ptb.setPubType( pubType );
				ptb.setIdPrefix( idPrefix );				
				ptb.setStartYear( currentJSON.getString("startYear") );
				ptb.setEndYear( currentJSON.getString("endYear") );
				ptb.setStartVolume( currentJSON.getString("startVolume") );
				ptb.setRapidPosts( new Boolean(currentJSON.getString("rapidPosts")) );
				ptb.setPrePrints( new Boolean(currentJSON.getString("prePrints")) );
				ptb.setProductId( currentJSON.getString("productId") );
				ptb.setDisplay( currentJSON.getString("display") );
				ptb.setListOrder( listOrder );
				
				if ( currentJSON.has("url") ) {
					ptb.setUrl( currentJSON.getString("url") );
				} else {
					ptb.setUrl( csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + csdlIndexPage);
				}

				publicationMap.put(listOrder, ptb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ContentBean populateContentBean(JSONObject contentJSON) {
		
		ContentBean cb = new ContentBean();
		
		try {
			
			cb.setMediaFormats( getString(contentJSON, "mediaformats") );
			cb.setSummary( getString(contentJSON, "summary") );
			cb.setKeywords( getString(contentJSON, "keywords") );
			cb.setUpdateTime( getString(contentJSON, "updatetime") );
			cb.setContentId( getString(contentJSON, "contentid") );
			cb.setDefaultPrice( getString(contentJSON, "defaultprice") );
			cb.setCopyright( getString(contentJSON, "copyright") );
			cb.setPublicationDate( getString(contentJSON, "publicationdate") );
			cb.setContentType( getString(contentJSON, "contenttype") );
			cb.setUri( getString(contentJSON, "uri") );
			cb.setTitle( getString(contentJSON, "title") );			
			cb.setXplore( getString(contentJSON, "xplore") );
			cb.setSubTitle( getString(contentJSON, "subtitle") );
			cb.setLength( getString(contentJSON, "length") );
			cb.setFilename( getString(contentJSON, "filename") );
			cb.setPeerReview( getString(contentJSON, "peerreview") );
			cb.setDoi( getString(contentJSON, "doi") );
			
			String price = getString(contentJSON, "price");
			price = price.startsWith("0.") ? "0" : price;
			cb.setPrice( price.trim() );
			
			populateContentBeanReferenceList(contentJSON, cb);
			populateContentBeanAssociatedContentList(contentJSON, cb);
			populateContentBeanCreatorList(contentJSON, cb);

			JSONObject categoryJSON = getJSONObject(contentJSON, "categorylist");
			String categoryTitle = getString(categoryJSON, "title");														
			cb.setCategoryTitle(categoryTitle);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cb;
	}
	
	public static void populateContentBeanReferenceList(JSONObject contentJSON, ContentBean cb) {
		
		try {
		
			if ( contentJSON.has("referencelist") ) {
				
				JSONObject referenceList = getJSONObject(contentJSON, "referencelist");
				
				if ( referenceList.has("reference") ) {
					
					if ( referenceList.get("reference") instanceof JSONObject ) {
						
						JSONObject reference = getJSONObject(referenceList, "reference");
						putReferenceInReferenceMap(reference, cb);
						
					} else if ( referenceList.get("reference") instanceof JSONArray ) {
					
						JSONArray reference = getJSONArray(referenceList, "reference");
						if ( null != reference && reference.length() > 0 ) {
							for ( int refIndex = 0; refIndex < reference.length(); refIndex++ ) {
								JSONObject referenceJSON = reference.getJSONObject(refIndex);
								putReferenceInReferenceMap(referenceJSON, cb);
							}
						}	
					}					
				} // if ( referenceList.has("reference") ) {
			} // if ( contentJSON.has("referencelist") ) {
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void putReferenceInReferenceMap(JSONObject reference, ContentBean cb) {
	
		try {
			
			String referenceId = getString(reference, "referenceid");
			String display     = getString(reference, "display");			
			cb.putReferenceInReferenceMap(referenceId, new ReferenceBean(referenceId, StringEscapeUtils.unescapeHtml(display)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void populateContentBeanAssociatedContentList(JSONObject contentJSON, ContentBean cb) {
		
		try {
		
			if ( contentJSON.has("associatedcontentlist") ) {
				
				JSONObject associatedContentList = getJSONObject(contentJSON, "associatedcontentlist");
				
				if ( associatedContentList.has("associatedcontent") ) {
					
					if ( associatedContentList.get("associatedcontent") instanceof JSONObject ) {
						
						JSONObject associatedContent = getJSONObject(associatedContentList, "associatedcontent");
						addAssociatedContentBeanToAssociatedContentList(associatedContent, cb);
						
					} else if ( associatedContentList.get("associatedcontent") instanceof JSONArray ) {
					
						JSONArray associatedContent = getJSONArray(associatedContentList, "associatedcontent");
						if ( null != associatedContent && associatedContent.length() > 0 ) {
							for ( int refIndex = 0; refIndex < associatedContent.length(); refIndex++ ) {
								JSONObject associatedContentJSON = associatedContent.getJSONObject(refIndex);
								addAssociatedContentBeanToAssociatedContentList(associatedContentJSON, cb);
							}
						}	
					}					
				} // if ( associatedcontentlist.has("associatedcontent") ) {
			} // if ( contentJSON.has("associatedcontentlist") ) {
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addAssociatedContentBeanToAssociatedContentList(JSONObject associatedContent, ContentBean cb) {
		
		try {
			
			AssociatedContentBean acb = new AssociatedContentBean();
			acb.setPrice( getString(associatedContent, "price") );
			acb.setLink( getString(associatedContent, "link") );
			acb.setLinkString( getString(associatedContent, "linkstring") );
			acb.setLinkType( getString(associatedContent, "linktype") );
			acb.setDisplayString( getString(associatedContent, "displaystring") );
			acb.setMediaType( getString(associatedContent, "mediatype") );
			acb.setAssociatedContentId( getString(associatedContent, "associatedcontentid") );
			cb.addAssociatedContentBeanToAssociatedContentList(acb);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void populateContentBeanCreatorList(JSONObject contentJSON, ContentBean cb) {
		
		try {
		
			if ( contentJSON.has("creatorlist") ) {
			
				JSONObject creatorList = getJSONObject(contentJSON, "creatorlist");	
				
				if ( creatorList.has("creator") ) {
					
					if ( creatorList.get("creator") instanceof JSONObject ) {
						
						JSONObject creatorJSON = getJSONObject(creatorList, "creator");
						addCreatorToCreatorList(creatorJSON, cb);
						
					} else if ( creatorList.get("creator") instanceof JSONArray ) {
						
						JSONArray creator = getJSONArray(creatorList, "creator");
						if ( null != creator && creator.length() > 0 ) {
							for ( int creatorIndex = 0; creatorIndex < creator.length(); creatorIndex++ ) {
								JSONObject creatorJSON = creator.getJSONObject(creatorIndex);
								addCreatorToCreatorList(creatorJSON, cb);
							}
						}
					}					
				} // if ( creatorList.has("creator") ) {
			} // if ( contentJSON.has("creatorlist") ) {
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addCreatorToCreatorList(JSONObject creator, ContentBean cb) {
		
		try {
			
			String givenName = getString(creator, "givenname");
			String role      = getString(creator, "role");
			String surname   = getString(creator, "surname");
			String sponsor   = getString(creator, "sponsor");
			cb.addCreatorToCreatorList( new CreatorBean(givenName, surname, role, sponsor) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void populateTableOfContentsList(Object targetObject, String packageId) {
		
		try {

			HttpClient client = new HttpClient();								
			GetMethod method = new GetMethod(DigitalLibraryUtil.csdlProperties.getProperty("content.list") + packageId);			
			int statusCode = client.executeMethod(method);
			
			JSONObject jsonResponse = byteArrayToJSONObject(method.getResponseBody());
			JSONArray contentlistArray = jsonArrayFromDotNotation(jsonResponse, "csdlresponse.contentlist");
			JSONArray contentArray = (JSONArray) contentlistArray.get(0);
		
			if ( null != contentArray && contentArray.length() > 0 ) {
				
				PackageBean pb = new PackageBean();
				
				for ( int contentIndex = 0; contentIndex < contentArray.length(); contentIndex++ ) {
			
					JSONObject contentJSON = contentArray.getJSONObject(contentIndex);													
					ContentBean cb = populateContentBean(contentJSON);
					
					// The Package (Issue) information comes with every Content object, but we only need to pull
					// it once.  Once we have a populated Package object, we'll use that same object for all
					// Content objects.  Why?  Might save a few 10's or 100's of milliseconds.
					if ( contentIndex == 0 ) {
						pb = getIssue(contentJSON);
					}
					cb.setPackageBean(pb);
					
					((List<ContentBean>) targetObject).add(cb);			
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void populateTableOfContentsList(Object targetObject, String pubType, String idPrefix, String year, String urlSuffix, String tocPage) {
		
		try {

			HttpClient client = new HttpClient();								
			GetMethod method = new GetMethod(DigitalLibraryUtil.csdlProperties.getProperty("content.description.url") + pubType + "/" + idPrefix + "/" + year + "/" + urlSuffix + "/" + tocPage);			
			int statusCode = client.executeMethod(method);
			
			JSONObject jsonResponse = byteArrayToJSONObject(method.getResponseBody());
			JSONArray contentlistArray = jsonArrayFromDotNotation(jsonResponse, "csdlresponse.contentlist");
			JSONArray contentArray = (JSONArray) contentlistArray.get(0);
		
			if ( null != contentArray && contentArray.length() > 0 ) {
				
				PackageBean pb = new PackageBean();
				
				for ( int contentIndex = 0; contentIndex < contentArray.length(); contentIndex++ ) {
			
					JSONObject contentJSON = contentArray.getJSONObject(contentIndex);													
					ContentBean cb = populateContentBean(contentJSON);
					
					// The Package (Issue) information comes with every Content object, but we only need to pull
					// it once.  Once we have a populated Package object, we'll use that same object for all
					// Content objects.  Why?  Might save a few 10's or 100's of milliseconds.
					if ( contentIndex == 0 ) {
						pb = getIssue(contentJSON);
					}
					cb.setPackageBean(pb);
					
					((List<ContentBean>) targetObject).add(cb);			
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PackageBean populatePackageBean(JSONObject packageJSON) {
	
		PackageBean pb = new PackageBean();
		
		try {
			
			pb.setPackageId( getString(packageJSON, "packageid") );
			pb.setPackageType( getString(packageJSON, "packagetype") );
			pb.setTitle( getString(packageJSON, "title") );
			pb.setPrice( getString(packageJSON, "price") );
			pb.setUpdateTime( getString(packageJSON, "updatetime") );
			pb.setDescription( getString(packageJSON, "description") );
			pb.setSubTitle( getString(packageJSON, "subtitle") );
			pb.setMediaTypes( getString(packageJSON, "mediatypes") );
			pb.setPublicationDate( getString(packageJSON, "publicationdate") );
			pb.setPublisher( getString(packageJSON, "publisher") );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pb;
	}
	
	public static void getIssues(JSONObject csdlJSON, String csdlContextPathPrefix, String pubType, String idPrefix, String year, 
								 String csdlIndexPage, String productId, Map<String,IssueBean> issueMap) {
		
		try {
		
			boolean cacheIsNull = false;
			
			if ( MAGS.equalsIgnoreCase(pubType) ) {
				
				if ( null != magsUICache ) {
					
					String key = pubType + "/" + idPrefix + "/" + year;
					JSONObject allIssuesJSON = magsUICache.getJSONObject(key);
					
					Iterator issueIterator = allIssuesJSON.keys();
					while ( issueIterator.hasNext() ) {
						
						String issueNumber = (String) issueIterator.next();
						JSONObject valueJSON = allIssuesJSON.getJSONObject(issueNumber);
						String title = valueJSON.getString("t");

						IssueBean ib = new IssueBean();
						ib.setYear(year);
						ib.setIssueNumber(issueNumber);	
						ib.setUrl( csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + year + "/" + issueNumber + "/" + csdlIndexPage);
						ib.setTitle(title);
						issueMap.put(issueNumber, ib);
						
					}

				} else {
					cacheIsNull = true;						
				}
				
			} else if ( TRANS.equalsIgnoreCase(pubType) ) {
				
				if ( null != transUICache ) {
					
					String key = pubType + "/" + idPrefix + "/" + year;
					JSONObject allIssuesJSON = transUICache.getJSONObject(key);
					
					Iterator issueIterator = allIssuesJSON.keys();
					while ( issueIterator.hasNext() ) {
						
						String issueNumber = (String) issueIterator.next();
						JSONObject valueJSON = allIssuesJSON.getJSONObject(issueNumber);
						String title = valueJSON.getString("t");

						IssueBean ib = new IssueBean();
						ib.setYear(year);
						ib.setIssueNumber(issueNumber);	
						ib.setUrl( csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + year + "/" + issueNumber + "/" + csdlIndexPage);
						ib.setTitle(title);
						issueMap.put(issueNumber, ib);
						
					}

				} else {
					cacheIsNull = true;						
				}
				
			} else if ( LETTERS.equalsIgnoreCase(pubType) ) {
			
				if ( null != lettersUICache ) {
					
					String key = pubType + "/" + idPrefix + "/" + year;
					JSONObject allIssuesJSON = lettersUICache.getJSONObject(key);
					
					Iterator issueIterator = allIssuesJSON.keys();
					while ( issueIterator.hasNext() ) {
						
						String issueNumber = (String) issueIterator.next();
						JSONObject valueJSON = allIssuesJSON.getJSONObject(issueNumber);
						String title = valueJSON.getString("t");

						IssueBean ib = new IssueBean();
						ib.setYear(year);
						ib.setIssueNumber(issueNumber);	
						ib.setUrl( csdlContextPathPrefix + "/" + pubType + "/" + idPrefix + "/" + year + "/" + issueNumber + "/" + csdlIndexPage);
						ib.setTitle(title);
						issueMap.put(issueNumber, ib);
						
					}

				} else {
					cacheIsNull = true;						
				}
			
			} else {
				
				// TO DO
				
				
			} // if ( MAGS.equalsIgnoreCase(pubType) ) {
				

			if ( cacheIsNull ) {

				HttpClient client = new HttpClient();
				GetMethod method = new GetMethod(DigitalLibraryUtil.csdlProperties.getProperty("package.list") + productId);
				int statusCode = client.executeMethod(method);
				
				if ( statusCode >= 400 && statusCode < 500 ) {
					
					
				} else if ( statusCode >= 500 ) {
					
					
				} else {
					
					JSONObject jsonResponse = byteArrayToJSONObject(method.getResponseBody()); 
					JSONArray packageArray = jsonArrayFromDotNotation(jsonResponse, "csdlresponse.packagelist.package");					
					getIssueOrTableOfContents(packageArray, csdlContextPathPrefix, pubType, idPrefix, year, "", csdlIndexPage, issueMap);

				}
			} // if ( cacheIsNull ) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static PackageBean getIssue(JSONObject contentJSON) {
	
		PackageBean pb = new PackageBean();
		
		try {

			JSONObject packageJSON = jsonObjectFromDotNotation(contentJSON, "packagelist.package");
			pb = populatePackageBean(packageJSON);
			
			JSONArray keyValuePairs = jsonArrayFromDotNotation(packageJSON, "packagemetadata.value");			
			pb.populateMetadataMap(keyValuePairs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pb;
	}
	
	public static PackageBean getIssue(JSONObject contentJSON, String dateMask) {
		
		PackageBean pb = new PackageBean();
		
		try {

			JSONObject packageJSON = jsonObjectFromDotNotation(contentJSON, "packagelist.package");
			pb = populatePackageBean(packageJSON);
			
			JSONArray keyValuePairs = jsonArrayFromDotNotation(packageJSON, "packagemetadata.value");			
			pb.populateMetadataMap(keyValuePairs, dateMask);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pb;
	}
	
	public static void getTableOfContents(JSONObject csdlJSON, String csdlContextPathPrefix, String pubType, String idPrefix, String year, String issueNumber, 
			                              String csdlIndexPage, List<ContentBean> tableOfContentsList) {
		
		try {
			
			boolean cacheIsNull = false;
		
			if ( MAGS.equalsIgnoreCase(pubType) ) {
				
				if ( null != magsUICache ) {
					
					// Pull the Package ID from the cache.
					String key = pubType + "/" + idPrefix + "/" + year;
					JSONObject allIssuesJSON = magsUICache.getJSONObject(key);
					JSONObject issueJSON = getJSONObject(allIssuesJSON, issueNumber);
					String packageId = getString(issueJSON, "id");
					populateTableOfContentsList(tableOfContentsList, packageId);
					
				} else {
					cacheIsNull = true;						
				}		
				
			} else if ( TRANS.equalsIgnoreCase(pubType) ) {
				
				if ( null != transUICache ) {
					
					// Pull the Package ID from the cache.
					String key = pubType + "/" + idPrefix + "/" + year;
					JSONObject allIssuesJSON = transUICache.getJSONObject(key);
					JSONObject issueJSON = getJSONObject(allIssuesJSON, issueNumber);
					String packageId = getString(issueJSON, "id");
					populateTableOfContentsList(tableOfContentsList, packageId);
					
				} else {
					cacheIsNull = true;						
				}	
				
			} else if ( LETTERS.equalsIgnoreCase(pubType) ) {
				
				if ( null != lettersUICache ) {
					
					// Pull the Package ID from the cache.
					String key = pubType + "/" + idPrefix + "/" + year;
					JSONObject allIssuesJSON = lettersUICache.getJSONObject(key);
					JSONObject issueJSON = getJSONObject(allIssuesJSON, issueNumber);
					String packageId = getString(issueJSON, "id");
					populateTableOfContentsList(tableOfContentsList, packageId);
					
				} else {
					cacheIsNull = true;						
				}	
				
			} else {
				
				
			} // if ( MAGS.equalsIgnoreCase(pubType) ) {
			
			if ( cacheIsNull ) {			
		
				HttpClient client = new HttpClient();
				
				JSONObject idPrefixDataJSON = jsonObjectFromDotNotation(csdlJSON, pubType + "." + idPrefix);
				
				String productId = idPrefixDataJSON.getString("productId");
							
				GetMethod method = new GetMethod(DigitalLibraryUtil.csdlProperties.getProperty("package.list") + productId);
				
				int statusCode = client.executeMethod(method);
				
				if ( statusCode >= 400 && statusCode < 500 ) {
					
					
				} else if ( statusCode >= 500 ) {
					
					
				} else {
					
					JSONObject jsonResponse = byteArrayToJSONObject(method.getResponseBody()); 
					JSONArray packageArray = jsonArrayFromDotNotation(jsonResponse, "csdlresponse.packagelist.package");
					getIssueOrTableOfContents(packageArray, csdlContextPathPrefix, pubType, idPrefix, year, issueNumber, csdlIndexPage, tableOfContentsList);

				}
				
			} // if ( cacheIsNull ) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public static ContentBean getAbstract(String[] segments) {
		
		ContentBean abstractBean = new ContentBean();
		int numberSegments = segments.length;
		
		if ( numberSegments == 7 ) {
			
			if ( "abs".equalsIgnoreCase(segments[5]) ) {
				
				String pubType     = segments[0];
				String idPrefix    = segments[1];
				String year        = segments[2];
				String issueNumber = segments[3];
				String fileName    = segments[4];
				String fileDetail  = segments[5];
				String fileType    = segments[6];
				abstractBean = getAbstract(pubType, idPrefix, year, issueNumber, fileName, fileDetail, fileType);
			}					
		}
		
		return abstractBean;
	}
	
	public static ContentBean getAbstract(String pubType, String idPrefix, String year, String issueNumber, String fileName, String fileDetail, String fileType) {
	
		ContentBean abstractBean = new ContentBean();
		
		try {
			
			HttpClient client = new HttpClient();				
			String url = DigitalLibraryUtil.csdlProperties.getProperty("content.description.url") + pubType + "/" + idPrefix + "/" + year + "/" + issueNumber + "/" + fileName + fileDetail + "." + fileType;			
			GetMethod method = new GetMethod(url);				
			int statusCode = client.executeMethod(method);
			
			if ( statusCode >= 400 && statusCode < 500 ) {
				
				
			} else if ( statusCode >= 500 ) {
				
				
			} else {
				
				JSONObject jsonResponse = byteArrayToJSONObject(method.getResponseBody());					
				JSONObject contentList = jsonObjectFromDotNotation(jsonResponse, "csdlresponse.contentlist");
				abstractBean = populateContentBean(contentList);
				
				if ( issueNumber.indexOf("/") == -1 ) {
					abstractBean.setPackageBean( getIssue(contentList) );
				} else {
					abstractBean.setPackageBean( getIssue(contentList) );
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return abstractBean;
	}

	public static String[] getPageRange(String pageRange) {
		
		String[] firstAndLast = {"", ""};
		
		try {
			
			pageRange = pageRange.trim();
			
			if ( "Online Only".equalsIgnoreCase(pageRange) || "OnlineOnly".equalsIgnoreCase(pageRange) ) {
				firstAndLast[0] = "Online Only";
				firstAndLast[1] = "Online Only";				
			} else if ( "Not-in-print".equalsIgnoreCase(pageRange) || "Notinprint".equalsIgnoreCase(pageRange) ) {
				firstAndLast[0] = "Not-in-print";
				firstAndLast[1] = "Not-in-print";	
			} else {
				
				if ( NumberUtils.isDigits(pageRange) ) {
					firstAndLast[0] = pageRange;
					firstAndLast[1] = pageRange;					
				} else {
					
					if ( pageRange.indexOf("-") > -1 && pageRange.indexOf(",") == -1 ) {
						
						String[] pageRangeArray = pageRange.split("-");
						if ( pageRangeArray.length == 2 ) {
							firstAndLast[0] = pageRangeArray[0];
							firstAndLast[1] = pageRangeArray[1];
						} else if ( pageRangeArray.length == 1 ) {
							firstAndLast[0] = pageRangeArray[0];
							firstAndLast[1] = pageRangeArray[0];
						}

					} else if ( pageRange.indexOf("-") == -1 && pageRange.indexOf(",") > -1 ) {
						
						String[] pageRangeArray = pageRange.split(",");
						firstAndLast[0] = pageRangeArray[0];
						firstAndLast[1] = pageRangeArray[0];
						
					} else if ( pageRange.indexOf("-") > -1 && pageRange.indexOf(",") > -1 ) {
						String[] pageRangeArrayComma = pageRange.split(",");
						String pageRangeHyphen = pageRangeArrayComma[0];
						String[] pageRangeArrayHyphen = pageRangeHyphen.split("-");
						
						if ( pageRangeArrayHyphen.length == 2 ) {
							firstAndLast[0] = pageRangeArrayHyphen[0];
							firstAndLast[1] = pageRangeArrayHyphen[1];
						} else if ( pageRangeArrayHyphen.length == 1 ) {
							firstAndLast[0] = pageRangeArrayHyphen[0];
							firstAndLast[1] = pageRangeArrayHyphen[0];
						}
					}					
				}	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return firstAndLast;
	}
	
	public static void loadEditableProperties(Properties prop) {	
		
		FileReader csdlFileReader = null;
 
		try {

			// Read the Digital Library "configurable" properties from a set location.
			// If the properties can't be obtained from that location, then set the default properties found in 
			// the file that exists on the class path.
			csdlFileReader = new FileReader( prop.getProperty("csdl.settings.location") );			
			DigitalLibraryUtil.csdlProperties = new Properties();
			
			if ( null != csdlFileReader ) {	
				
				DigitalLibraryUtil.csdlProperties.load(csdlFileReader);		
				
				if ( null == DigitalLibraryUtil.csdlProperties ) {
					DigitalLibraryUtil.csdlProperties.load( StartupCSDLAction.class.getClassLoader().getResourceAsStream(CSDLPROPERTIESFILE) );
				}
				
			} else {
				DigitalLibraryUtil.csdlProperties.load( StartupCSDLAction.class.getClassLoader().getResourceAsStream(CSDLPROPERTIESFILE) );
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (csdlFileReader != null) {
					csdlFileReader.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void populatePublicationsCache(Properties prop) {	
		
		BufferedReader br = null;
 
		try {
			 
			String currentLine = "";
			String json = "";
			br = new BufferedReader(new FileReader( prop.getProperty(PUBLICATIONS_KEY) ));
 
			while ((currentLine = br.readLine()) != null) {
				json = json + currentLine.trim();		
			}
			
			DigitalLibraryUtil.CSDLBASE = json;
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void populateUICache(Properties prop, String location) {
		
		BufferedReader br = null;
		 
		try {
			 
			String currentLine = "";
			String json = "";
			br = new BufferedReader(new FileReader( prop.getProperty(location) ));
 
			while ((currentLine = br.readLine()) != null) {
				json = json + currentLine.trim();		
			}
			
			if ( MAGAZINES_KEY.equalsIgnoreCase(location) ) {
				DigitalLibraryUtil.magsUICache = new JSONObject(json);
			} else if ( TRANSACTIONS_KEY.equalsIgnoreCase(location) ) {
				DigitalLibraryUtil.transUICache = new JSONObject(json);
			} else if ( LETTERS_KEY.equalsIgnoreCase(location) ) {
				DigitalLibraryUtil.lettersUICache = new JSONObject(json);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static String generatePublicationJSON(String pubType, String defaultOutput) {
		
		String output = defaultOutput;
		
		try {
			
			long startTimeMS = getTime();
			
			HttpClient client = new HttpClient();
			
			JSONObject jsonObjectHolder = new JSONObject();
			JSONObject csdlDataJSON = new JSONObject(DigitalLibraryUtil.CSDLBASE);
			JSONObject publicationsDataJSON = csdlDataJSON.getJSONObject(pubType);
			
			Iterator<String> publicationsIterator = publicationsDataJSON.keys();
			while ( publicationsIterator.hasNext() ) {
			
				String idPrefix = (String) publicationsIterator.next();
				JSONObject nextPublication = publicationsDataJSON.getJSONObject(idPrefix);
				
				int productId = nextPublication.getInt("productId");
				
				GetMethod method = new GetMethod(DigitalLibraryUtil.csdlProperties.getProperty("package.list") + productId);					
				client.executeMethod(method);
				
				JSONObject jsonResponse = byteArrayToJSONObject(method.getResponseBody()); 
				JSONArray packageArray = jsonArrayFromDotNotation(jsonResponse, "csdlresponse.packagelist.package");
		
				if ( null != packageArray && packageArray.length() > 0 ) {
					for ( int index = 0; index < packageArray.length(); index++ ) {
						
						JSONObject currentPackage = packageArray.getJSONObject(index);
						String publicationDate = DigitalLibraryUtil.getString(currentPackage, "publicationdate");
						String publicationYear = publicationDate.trim().substring(0,4);
						String title           = DigitalLibraryUtil.getString(currentPackage, "title");
						String packageId       = DigitalLibraryUtil.getString(currentPackage, "packageid");
						String issueNumber     = "9999";
						
						JSONArray packageMetadataKeyValuePairs = jsonArrayFromDotNotation(currentPackage, "packagemetadata.value");
						
						if ( null != packageMetadataKeyValuePairs && packageMetadataKeyValuePairs.length() > 0 ) {
							for ( int valIndex = 0; valIndex < packageMetadataKeyValuePairs.length(); valIndex++ ) {
								
								JSONObject contentNamePair = packageMetadataKeyValuePairs.getJSONObject(valIndex);
								String currentName    = DigitalLibraryUtil.getString(contentNamePair, "name");
								
								if ( "issueNumber".equalsIgnoreCase(currentName) ) {
									String currentIssueNumber = DigitalLibraryUtil.getString(contentNamePair, "content");
									currentIssueNumber = currentIssueNumber.length() == 1 ? "0"+currentIssueNumber : currentIssueNumber;
									issueNumber = currentIssueNumber;									
								} 									
							} 
						} 

						String currentKey = pubType + "/" + idPrefix + "/" + publicationYear;
						
						JSONObject packageInfoJSON = new JSONObject();
						packageInfoJSON.put("t",title);
						packageInfoJSON.put("id", packageId);
						
						if ( jsonObjectHolder.has(currentKey) ) {								
							JSONObject currentPublication = jsonObjectHolder.getJSONObject(currentKey);
							currentPublication.put(issueNumber, packageInfoJSON);
						} else {
							JSONObject publicationJSON = new JSONObject();
							publicationJSON.put(issueNumber, packageInfoJSON);
							jsonObjectHolder.put(currentKey, publicationJSON);
						}

					} // for ( int index = 0; index < packageArray.length(); index++ ) {
				} // if ( null != packageArray && packageArray.length() > 0 ) {
			} // while ( publicationsIterator.hasNext() ) {
			
			output = jsonObjectHolder.length() + " Entries<br/>in " + getTotalTime(startTimeMS) + " ms<br/>File Size approximately " + 
			         jsonObjectHolder.toString().length() + " bytes<br/><br/>" + jsonObjectHolder.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	public static void initializeProperties() {
		
		try {
			
			// Load the properties from the class path.
			// These properties will contain the locations for the "cache" JSON files that power the Digital Library portlet.
			// Note that there is no cache for the Proceedings.  We'll see if we need it, but for right now it uses straight CSDL REST API calls.
			Properties prop = new Properties();
			prop.load( StartupCSDLAction.class.getClassLoader().getResourceAsStream(CSDLPROPERTIESFILE) );
			
			DigitalLibraryUtil.loadEditableProperties(prop);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void initializeCache() {
		
		try {
			
			// Load the properties from the class path.
			// These properties will contain the locations for the "cache" JSON files that power the Digital Library portlet.
			// Note that there is no cache for the Proceedings.  We'll see if we need it, but for right now it uses straight CSDL REST API calls.
			Properties prop = new Properties();
			prop.load( StartupCSDLAction.class.getClassLoader().getResourceAsStream(CSDLPROPERTIESFILE) );

			DigitalLibraryUtil.populatePublicationsCache(prop);
			
			DigitalLibraryUtil.populateUICache(prop, DigitalLibraryUtil.MAGAZINES_KEY);
			DigitalLibraryUtil.populateUICache(prop, DigitalLibraryUtil.TRANSACTIONS_KEY);
			DigitalLibraryUtil.populateUICache(prop, DigitalLibraryUtil.LETTERS_KEY);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
	public static String getArticleContent(JournalArticle article, String defaultContent) {
		
		String content = "";
		
		try {									
														
			if ( null == article ) {				
				content = defaultContent;		
			} else {
				SAXBuilder parser = new SAXBuilder();
				Document document = parser.build(new StringReader(article.getContent()));
				XPath contentXPath = XPath.newInstance("/root/static-content");
				List<?> contentList = contentXPath.selectNodes(document);					

				if ((null != contentList) && (contentList.size() > 0)) {
					Element contentElement = (Element)contentList.get(0);
					content = contentElement.getText();
				}
			} 
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}
}