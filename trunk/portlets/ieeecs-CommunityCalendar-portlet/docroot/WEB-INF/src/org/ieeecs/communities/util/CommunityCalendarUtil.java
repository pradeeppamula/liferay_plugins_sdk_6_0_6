package org.ieeecs.communities.util;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.portlet.PortletPreferences;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import org.ieeecs.communities.bean.AssetCalendarBean;
import org.ieeecs.communities.bean.AssetPrimaryKeysBean;
import org.ieeecs.communities.bean.CalendarBean;
import org.ieee.common.constants.GlobalConstants;
import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;
import org.ieee.common.util.DateUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;


public class CommunityCalendarUtil {
	
	public static final String CONFIG = "CONFIG";
	public static final String MODE   = "DEACTIVATED";
	public static final String USERID = "";
	public static final String CUSTOMEVENTCSS = "customEventCell";
	
	public static final String COMMUNITYCALENDARDATA = "";
	public static final String TITLEVISIBLE      = "NO";
	public static final String TITLEOFCALENDAR   = "";
	public static final String TITLETOPMARGIN    = "0";
	public static final String TITLEBOTTOMMARGIN = "0";
	public static final String TITLECOLOR        = "000000";
	public static final String TITLEFONT         = "bold 16px arial, helvetica, sans-serif";
	public static final String RECORDSTOPULL     = "200";
	public static final String MODALTOPOFFSET    = "226";

	public static final String TITLEBORDERCOLOR  = "FFCC33";
	public static final String TITLEBACKGRCOLOR  = "FFCC33";
	public static final String TITLETOPLEFTRAD   = "5";
	public static final String TITLEBOTLEFTRAD   = "0";
	public static final String TITLETOPRGHTRAD   = "5";
	public static final String TITLEBOTRGHTRAD   = "0";

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
	public static final String PORTLETBOTTOMLEFTRADIUS  = "5";
	public static final String PORTLETTOPRIGHTRADIUS    = "0";
	public static final String PORTLETBOTTOMRIGHTRADIUS = "5";

	public static final String URLTARGETNAME             = "content";
	public static final String CHANNELVOCABULARYNAME     = "Computing Now Channels";
	public static final String CONTENTTYPEVOCABULARYNAME = "Content Types";
	public static final String RESTAPI                   = "/portal/web/computingnow/rest/-/api/";
	public static final String PUBLICSERVLETMAPPING      = "/portal/web";
		
	public static final String DATAFEED                     = "";
	public static final String DATAFEEDTYPE                 = "FILE";
	public static final String EXTERNALEVENTBACKGROUNDCOLOR = "CCCCCC";
	public static final String EXTERNALEVENTBORDERCOLOR     = "CCCCCC";
	public static final String EXTERNALEVENTTEXTCOLOR       = "000000";
	
	public static final String SLOTMINUTES = "15";
	public static final String ASPECTRATIO = "1.35";
	public static final String HIGHLIGHTBG = "EEEEEE";
	public static final String EVENTCSS    = "padding: 5px; font: 12px arial,helvetica,sans-serif;";
	
	public static final String FILTERING       = "NO";
	public static final String SEARCHINPUTTEXT = "Search this Calendar";
	public static final String BACKTOTEXT      = "Back to the Calendar";
	
	public static final String SHOWINTRO = "ON";

	
	public static void putPortletPreferencesIntoModel(PortletPreferences prefs, Map<String,Object> model) {

		try {

			model.put("portletMode", prefs.getValue("portletMode", MODE));
			model.put("modifiedByUserId", prefs.getValue("modifiedByUserId", USERID));
			
			model.put("contentCalendarData", prefs.getValue("contentCalendarData", COMMUNITYCALENDARDATA));
			model.put("titleVisible", prefs.getValue("titleVisible", TITLEVISIBLE));
			model.put("titleOfCalendar", prefs.getValue("titleOfCalendar", TITLEOFCALENDAR));
			model.put("titleTopMargin", prefs.getValue("titleTopMargin", TITLETOPMARGIN));
			model.put("titleBottomMargin", prefs.getValue("titleBottomMargin", TITLEBOTTOMMARGIN));
			model.put("titleColor", prefs.getValue("titleColor", TITLECOLOR));
			model.put("titleFont", prefs.getValue("titleFont", TITLEFONT));
			model.put("recordsToPull", prefs.getValue("recordsToPull", RECORDSTOPULL));
			model.put("modalTopOffset", prefs.getValue("modalTopOffset", MODALTOPOFFSET));

			model.put("titleBorderColor", prefs.getValue("titleBorderColor", TITLEBORDERCOLOR));
			model.put("titleBackgroundColor", prefs.getValue("titleBackgroundColor", TITLEBACKGRCOLOR));

			model.put("titleTopLeftRadius", prefs.getValue("titleTopLeftRadius", TITLETOPLEFTRAD));
			model.put("titleBottomLeftRadius", prefs.getValue("titleBottomLeftRadius", TITLEBOTLEFTRAD));
			model.put("titleTopRightRadius", prefs.getValue("titleTopRightRadius", TITLETOPRGHTRAD));
			model.put("titleBottomRightRadius", prefs.getValue("titleBottomRightRadius", TITLEBOTRGHTRAD));

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

			model.put("urlTargetName", prefs.getValue("urlTargetName", URLTARGETNAME));
			model.put("channelVocabularyName", prefs.getValue("channelVocabularyName", CHANNELVOCABULARYNAME));
			model.put("contentTypeVocabularyName", prefs.getValue("contentTypeVocabularyName", CONTENTTYPEVOCABULARYNAME));
			model.put("restAPI", prefs.getValue("restAPI", RESTAPI));
			model.put("publicServletMapping", prefs.getValue("publicServletMapping", PUBLICSERVLETMAPPING));
			
			model.put("dataFeed", prefs.getValue("dataFeed", DATAFEED));
			model.put("dataFeedType", prefs.getValue("dataFeedType", DATAFEEDTYPE));
			model.put("externalEventBackgroundColor", prefs.getValue("externalEventBackgroundColor", EXTERNALEVENTBACKGROUNDCOLOR));
			model.put("externalEventBorderColor", prefs.getValue("externalEventBorderColor", EXTERNALEVENTBORDERCOLOR));
			model.put("externalEventTextColor", prefs.getValue("externalEventTextColor", EXTERNALEVENTTEXTCOLOR));			
			
			model.put("slotMinutes", prefs.getValue("slotMinutes", SLOTMINUTES));
			model.put("aspectRatio", prefs.getValue("aspectRatio", ASPECTRATIO));
			model.put("highlightBackgroundColor", prefs.getValue("highlightBackgroundColor", HIGHLIGHTBG));
			model.put("eventCSS", prefs.getValue("eventCSS", EVENTCSS));
			
			model.put("filtering", prefs.getValue("filtering", FILTERING));
			model.put("searchInputText", prefs.getValue("searchInputText", SEARCHINPUTTEXT));
			model.put("backToText", prefs.getValue("backToText", BACKTOTEXT));
			
			model.put("showIntro", prefs.getValue("showIntro", SHOWINTRO));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static List<CalendarBean> getContentList(long companyGroupId, Integer start, Integer end, String contentCalendarData, String urlTargetName, 
							String channelVocabularyName, String contentTypeVocabularyName, String publicServletMapping) {

		List<CalendarBean> calendarBeanList = new ArrayList<CalendarBean>();

		try {

			List<AssetCalendarBean> assetCalendarList = getAssetsByCategory(start, end, contentCalendarData);

			if ( null != assetCalendarList && assetCalendarList.size() > 0 ) {

				Collection<Object> webContentIdArray = new ArrayList<Object>();
				Map<Long,AssetPrimaryKeysBean> primaryKeyMap = new HashMap<Long,AssetPrimaryKeysBean>();
				
				for ( AssetCalendarBean acb : assetCalendarList ) {
					
					String bgColor = acb.getBackgroundColor();
					String borderColor = acb.getBorderColor();
					String textColor = acb.getTextColor();
					List<AssetEntry> assetEntryList = acb.getAssetEntryList();
					
					if ( null != assetEntryList && assetEntryList.size() > 0 ) {
						
						for ( AssetEntry currentEntry : assetEntryList ) {
							
							// Since Liferay doesn't provide a better way to relate 
							//			Asset Entries <==> JournalArticles <==> Categories 
							// we'll make a reference map to hold all of those Primary Keys and their relations.
							Long contentId = currentEntry.getClassPK();
							AssetPrimaryKeysBean apkb = new AssetPrimaryKeysBean();
							apkb.setEntryId(currentEntry.getEntryId());
							apkb.setGroupId(currentEntry.getGroupId());
							apkb.setCompanyId(currentEntry.getCompanyId());
							apkb.setUserId(currentEntry.getUserId());
							apkb.setContentId( contentId );
							apkb.setCategoryList(currentEntry.getCategories());
							apkb.setBackgroundColor(bgColor);
							apkb.setBorderColor(borderColor);
							apkb.setTextColor(textColor);
							primaryKeyMap.put( contentId, apkb );
			
							if ( currentEntry.getClassNameId() == ClassNameLocalServiceUtil.getClassNameId("com.liferay.portlet.journal.model.JournalArticle") ) {
								webContentIdArray.add(currentEntry.getClassPK());
							}		
							
						} // for ( AssetEntry currentEntry : assetEntryList ) {
					} // if ( null != assetEntryList && assetEntryList.size() > 0 ) {
				} // for ( AssetCalendarBean acb : assetCalendarList ) {
			
				if ( null != webContentIdArray && webContentIdArray.size() > 0 ) {

					DynamicQuery query = DynamicQueryFactoryUtil.forClass(JournalArticle.class, PortalClassLoaderUtil.getClassLoader())
																			.add(PropertyFactoryUtil.forName("resourcePrimKey").in(webContentIdArray));

					List<JournalArticle> articleList = JournalArticleLocalServiceUtil.dynamicQuery(query);
					
					calendarBeanList.addAll( populateCalendarBeanListWithArticles( companyGroupId, articleList, primaryKeyMap, 
												urlTargetName, channelVocabularyName, contentTypeVocabularyName, publicServletMapping ));
				}			
			} // if ( null != assetCalendarList && assetCalendarList.size() > 0 ) {
				
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
		return calendarBeanList;
	}

	public static List<AssetCalendarBean> getAssetsByCategory(Integer start, Integer end, String contentCalendarData) {

		List<AssetCalendarBean> assetCalendarList = new ArrayList<AssetCalendarBean>();

		try {
			
			if ( "".equals(contentCalendarData) ) {
				contentCalendarData = "[]";
			}

			JSONArray contentCalendarDataArray = new JSONArray(contentCalendarData);
			int numberOfPairs = contentCalendarDataArray.length();
			
			for (int index = 0; index < numberOfPairs; index++) {
				
				List<AssetEntry> holderList = new ArrayList<AssetEntry>();
				
				JSONObject data = contentCalendarDataArray.getJSONObject(index);
				
				// Group
				String groupId = data.getString("groupId");
				long[] groupIds = {new Long(groupId)};
				
				// Type(s)
				String contentBit = data.getString("contentBit");
				long[] classNames = GlobalConstants.getClassNameIds(new Integer(contentBit));
				
				// Background, Border, and Text colors
				String bgColor = data.getString("bgColor");
				String borderColor = data.getString("borderColor");
				String textColor = data.getString("textColor");
				
				// Categories
				JSONArray categoryJSONArray = data.getJSONArray("categories");
				long[] categoryIds = new long[categoryJSONArray.length()];
								
				for ( int x = 0; x < categoryJSONArray.length(); x++ ) {
					String categoryId = (String) categoryJSONArray.get(x);
					categoryIds[x] = new Long(categoryId);
				}
				
				// Build the Asset Query
				AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
				assetEntryQuery.setGroupIds( groupIds );
				assetEntryQuery.setClassNameIds( classNames );							
				assetEntryQuery.setStart(start);
				assetEntryQuery.setEnd(end);	
								
				if ( null != categoryIds && categoryIds.length > 0 ) {
					assetEntryQuery.setAllCategoryIds( categoryIds );
				}	
				
				holderList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
				
				AssetCalendarBean acb = new AssetCalendarBean();
				acb.setBackgroundColor(bgColor);
				acb.setBorderColor(borderColor);
				acb.setTextColor(textColor);
				acb.setAssetEntryList(holderList);
					
				assetCalendarList.add(acb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return assetCalendarList;
	}

	private static List<CalendarBean> populateCalendarBeanListWithArticles(long companyGroupId, List<JournalArticle> articleList, 
					Map<Long,AssetPrimaryKeysBean> primaryKeyMap, String urlTargetName, String channelVocabularyName, String contentTypeVocabularyName,
					String publicServletMapping) {

		List<CalendarBean> calendarBeanList = new ArrayList<CalendarBean>();

		try {

			if ( null != articleList && articleList.size() > 0 ) {			

				for ( JournalArticle currentArticle : articleList ) {

					boolean isLatestVersion = JournalArticleLocalServiceUtil.isLatestVersion( currentArticle.getGroupId(), currentArticle.getArticleId(), currentArticle.getVersion() );
					
					// *******************
					// Is it the latest?
					// *******************
					if ( isLatestVersion ) {
						
						Date currentDate = new Date();
						Long currentDateMS = currentDate.getTime();
						Long displayDateMS = currentArticle.getDisplayDate().getTime();
						Long expirationDateMS = null == currentArticle.getExpirationDate() ? 9999999999999L : currentArticle.getExpirationDate().getTime();
						
						// *****************************************
						// Can we display it and has it expired?
						// *****************************************					
						if ( currentDateMS >= displayDateMS && currentDateMS < expirationDateMS ) {
				
							long currentStatus = currentArticle.getStatus();
												
							// **********************
							// Just show Published.
							// **********************							
							if ( currentStatus == 0 ) {
								
								// *************************************************************************
								// Only Article type, (not General, Press Release, News, etc.)
								// *************************************************************************
								String type = currentArticle.getType().toLowerCase();
								
								if ( type.equalsIgnoreCase("article") ) {
						
									CalendarBean calendarBean = new CalendarBean();
			
									// Construct the URL that will be used to view this content
									// Group    = community url
									// Category = The Channel/Page
									// Type     = article/webinar/static
									// 
									// Will result in /Group_CommunityName/Channel_PageName/article  (or whatever type the content is)
									
									String urlToContent = "";
									String subType = "";
									String channel = "";
									String bgColor = "CCCCCC";
									String borderColor = "CCCCCC";
									String textColor = "000000";
									String message = "";
									long resourcePrimaryKey = currentArticle.getResourcePrimKey();
									AssetPrimaryKeysBean apkb = primaryKeyMap.get(resourcePrimaryKey);
									
									if ( null != apkb ) {
						
										// Group/Community Friendly URL part
										long groupId = apkb.getGroupId();
										Group group = GroupLocalServiceUtil.getGroup(groupId);
										String groupUrl = group.getFriendlyURL();
										
										// Category/Channel Friendly URL part, but first get the Channels Category Vocabulary ID.
										// If we don't get the specific Vocabulary ID for just the "Channels" Categories, then we might return a Category name that doesn't belong to a page.
										// For the "Channels" Categories, we are making sure that every "Channels" Categories has a corresponding Liferay "page".
										// All other categories are used solely as a sub-content type.  
										// (ie.  Article - Webinar, Article - Interview, Article - Promo, etc.  where the "Webinar, Interview, Promo" would be three examples of a Category.)							
										String categoryUrl = "";											
										List<AssetCategory> categoryList = apkb.getCategoryList();							
										
										if ( null != categoryList && categoryList.size() > 0 ) {
											
											AssetVocabulary apkbChannelVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId, channelVocabularyName);
										
											// We'll also need to distinguish each asset by its content subtype, (Interview, Webinar, Promo, etc), for display in the UI
											AssetVocabulary apkbSubTypeVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId, contentTypeVocabularyName);
											
											if ( null != apkbChannelVocabulary ) {
												for ( AssetCategory ac : categoryList ) {
													
													long currentVocabId = ac.getVocabularyId();
													
													if ( currentVocabId == apkbChannelVocabulary.getVocabularyId() ) {
														channel = ac.getName().toUpperCase();
														categoryUrl = ac.getName().toLowerCase();
														categoryUrl.replaceAll(" ", "-");	
													}
													
													if ( currentVocabId == apkbSubTypeVocabulary.getVocabularyId() && "".equals(subType) ) {
														subType = ac.getName();
														subType = subType.toUpperCase();
													}										
												}
											}
										}	
										
										// Check to see if a "custom" URL has been set for this Article.
										// If there is a "custom" URL, then we'll not need the dynamically created one.
										boolean needURL = true;
										if ( currentArticle.getExpandoBridge().hasAttribute( "CustomURL" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "CustomURL" ) ) {												
												String customURL = (String) currentArticle.getExpandoBridge().getAttribute( "CustomURL" );	
												if ( null != customURL && !"".equals(customURL) ) {
													urlToContent = customURL;
													needURL = false;											
												}							
											}
										}
										
										if ( needURL ) {
											// Title of Content, Friendly URL part
											String urlTitle = currentArticle.getUrlTitle();	
											if ( !"".equals(categoryUrl) ) {
												urlToContent = publicServletMapping + groupUrl + "/" + categoryUrl + "/" + urlTargetName + "?g=" + groupId + "&type=" + type + "&urlTitle=" + urlTitle;	
											} else {
												urlToContent = publicServletMapping + groupUrl + "/" + urlTargetName + "?g=" + groupId + "&type=" + type + "&urlTitle=" + urlTitle;	
											}
										}
						
										// Get the Background, Border, and Text colors for this Article (Event)
										bgColor = apkb.getBackgroundColor();
										borderColor = apkb.getBorderColor();
										textColor = apkb.getTextColor();
										
									} // if ( null != apkb ) {
									
									Date eventStartDate = getEventStartDate(currentArticle);
									Date eventEndDate = getEventEndDate(currentArticle);
									
									// Warn if there is no Start date, or the Start date is not formatted correctly.
									if ( null == eventStartDate ) {
										bgColor = "CC0000";
										borderColor = "CC0000";
										textColor = "FFFFFF";
										message = "<u>There might be start/end date issues with this entry</u>: ";
									}
									
									// If the dates are okay, then continue.  Otherwise, place them in the "today" cell (for the admin to located easily).
									eventStartDate = null == eventStartDate ? new Date() : eventStartDate;
									eventEndDate = null == eventEndDate ? new Date() : eventEndDate;
									int utcOffset = TimeZone.getDefault().inDaylightTime( eventStartDate ) ? 7*60*60 : 8*60*60;
									
									// Now let's get the Event Location
									String eventLocation = "";
									if ( currentArticle.getExpandoBridge().hasAttribute( "EventLocation" ) ) {
										if ( null != currentArticle.getExpandoBridge().getAttribute( "EventLocation" ) ) {	
											eventLocation = (String) currentArticle.getExpandoBridge().getAttribute( "EventLocation" );											
										}
									}									
									
									Calendar c = Calendar.getInstance();
									c.setTime(eventStartDate);
									calendarBean.setEventMonth( c.get(Calendar.MONTH) );
									calendarBean.setEventYear( c.get(Calendar.YEAR) );
									
									calendarBean.setStart( eventStartDate.getTime()/1000 + utcOffset );
									calendarBean.setEventStartDateTime( DateUtils.dateToString(eventStartDate, DateUtils.DEFAULT_DATEFORMAT) );
									calendarBean.setEventStartDateTimeMS( eventStartDate.getTime() );
									calendarBean.setEventStartDateTimeS( eventStartDate.getTime()/1000 );
									
									calendarBean.setEnd( eventEndDate.getTime()/1000 + utcOffset );
									calendarBean.setEventEndDateTime( DateUtils.dateToString(eventEndDate, DateUtils.DEFAULT_DATEFORMAT) );
									calendarBean.setEventEndDateTimeMS( eventEndDate.getTime() );
									calendarBean.setEventEndDateTimeS( eventEndDate.getTime()/1000 );
						
									if ( null != eventStartDate && null != eventEndDate ) {
										
										long startTimeMS = eventStartDate.getTime();
										long endTimeMS = eventEndDate.getTime();
										long totalTimeMS = endTimeMS - startTimeMS;
										long oneDay = 8 * 60 * 60 * 1000;										
										if ( totalTimeMS > oneDay ) {
											calendarBean.setAllDay(true);
										}										
										
									} else {
										calendarBean.setAllDay(false);
									}
								
									// Now that the URL has been constructed, populate the Content Bean and add to the List.
									calendarBean.setGroupId( currentArticle.getGroupId() );
									calendarBean.setUrl( urlToContent );
									calendarBean.setUrlTitle( currentArticle.getUrlTitle() );
									calendarBean.setId( currentArticle.getId() );
									calendarBean.setTitle( currentArticle.getTitle() );
									calendarBean.setDescription( currentArticle.getDescription() );
									calendarBean.setDateTime( DateUtils.dateToString(currentArticle.getDisplayDate(), "EEEE, MMM d, yyyy") );
									calendarBean.setDateTimeMS( currentArticle.getDisplayDate().getTime() );
									calendarBean.setDateTimeS( currentArticle.getDisplayDate().getTime()/1000 );
									calendarBean.setType( currentArticle.getType().toLowerCase() );
									calendarBean.setSubType( subType );
									calendarBean.setChannel(channel);
									calendarBean.setBackgroundColor("#"+bgColor);
									calendarBean.setBorderColor("#"+borderColor);
									calendarBean.setTextColor("#"+textColor);
									calendarBean.setClassName(CUSTOMEVENTCSS);
									calendarBean.setMessage(message);
									calendarBean.setEventLocation(eventLocation);
			
									calendarBeanList.add(calendarBean);
						
								} // if ( type.equalsIgnoreCase("article") ) {
							} // if ( currentStatus == 0 ) {
						} // if ( currentDateMS >= displayDateMS && currentDateMS < expirationDateMS ) {						
					} // if ( isLatestVersion ) {
				}
			}			

		} catch (Exception e) {
			e.printStackTrace();
		}		

		return calendarBeanList;
	}
	
	private static Date getEventStartDate(JournalArticle article) {
		
		Date eventStartDate = null;
		
		try {
			
			if ( article.getExpandoBridge().hasAttribute( "EventStartDateTime" ) ) {
				if ( null != article.getExpandoBridge().getAttribute( "EventStartDateTime" ) ) {						
					String eventStart = (String) article.getExpandoBridge().getAttribute( "EventStartDateTime" );
					eventStartDate = stringToDate(eventStart);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return eventStartDate;
	}
	
	private static Date getEventEndDate(JournalArticle article) {
		
		Date eventEndDate = null;
		
		try {
			
			if ( article.getExpandoBridge().hasAttribute( "EventEndDateTime" ) ) {
				if ( null != article.getExpandoBridge().getAttribute( "EventEndDateTime" ) ) {						
					String eventEnd = (String) article.getExpandoBridge().getAttribute( "EventEndDateTime" );
					eventEndDate = stringToDate(eventEnd);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return eventEndDate;
	}
	
	public static Date stringToDate(String dateValue){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	  	Date date = null;

		try{
		    date = sdf.parse(dateValue);
		} catch (ParseException e) {
		    e.printStackTrace();
		}

        return date;
	}
	
	public static List<CalendarBean> readDataFeed(String dataFeedPath, String externalBgColor, String externalBorderColor, String externalTextColor, String dataFeedType) {
		
		List<CalendarBean> calendarBeanList = new ArrayList<CalendarBean>();

		try {
		
			String content = "";
			
			// File-based
			if ( CommunityCalendarUtil.DATAFEEDTYPE.equalsIgnoreCase(dataFeedType) ) {
				
				String sCurrentLine;				 
				BufferedReader br = new BufferedReader(new FileReader(dataFeedPath));
	 
				while ((sCurrentLine = br.readLine()) != null) {
					content = content + sCurrentLine;
				}
				content = content.replaceAll("&", "&amp;");

			// URL-based
			} else {
				
				HttpClient client = new HttpClient();
				GetMethod method = new GetMethod(dataFeedPath);
				client.executeMethod(method);

				byte[] responseBody = method.getResponseBody();
				String responseString = new String(responseBody);
				responseString = responseString.replaceAll("&", "&amp;");
							
				SAXBuilder parser = new SAXBuilder();
				Document document = parser.build(new StringReader(responseString));
				XPath contentXPath = XPath.newInstance("/html/body");
				Element contentElement = (Element) contentXPath.selectSingleNode(document);
				content = "[" + contentElement.getText().trim() + "]";
				
			}

			content = content.replaceAll("\n", " ");
			content = content.replaceAll("\r", "");
			
			JSONArray jsonArray = new JSONArray(content);
			
			if ( null != jsonArray && jsonArray.length() > 0 ) {
				
				for ( int index = 0; index < jsonArray.length(); index++ ) {
					
					JSONObject event = jsonArray.getJSONObject(index);
					
					String eventStartDateTime = event.getString("eventStartDateTime");
					String eventEndDateTime   = event.getString("eventEndDateTime");
					String url                = event.getString("url");
					String title              = event.getString("title");
					String description        = event.getString("description");
					String eventLocation      = event.getString("eventLocation");
					
					String[] eventStartDateArray = eventStartDateTime.split("-",-1);
					String[] eventEndDateArray   = eventEndDateTime.split("-",-1);
					
					String eventStartMonth = eventStartDateArray[1].startsWith("0") ? eventStartDateArray[1] : "0" + eventStartDateArray[1];
					String eventStartDay   = eventStartDateArray[2].startsWith("0") ? eventStartDateArray[2] : "0" + eventStartDateArray[2];					
					String eventStartDateTimeFormatted= "";
					if ( eventStartDateTime.indexOf(":") > -1 ) {
						eventStartDateTimeFormatted = eventStartDateArray[0] + "-" + eventStartMonth + "-" + eventStartDay;
					} else {
						eventStartDateTimeFormatted = eventStartDateArray[0] + "-" + eventStartMonth + "-" + eventStartDay + " 00:00";
					}
					
					String eventEndMonth = eventEndDateArray[1].startsWith("0") ? eventEndDateArray[1] : "0" + eventEndDateArray[1];
					String eventEndDay   = eventEndDateArray[2].startsWith("0") ? eventEndDateArray[2] : "0" + eventEndDateArray[2];					
					String eventEndDateTimeFormatted= "";
					if ( eventEndDateTime.indexOf(":") > -1 ) {
						eventEndDateTimeFormatted = eventEndDateArray[0] + "-" + eventEndMonth + "-" + eventEndDay;
					} else {
						eventEndDateTimeFormatted = eventEndDateArray[0] + "-" + eventEndMonth + "-" + eventEndDay + " 00:00";
					}
					
					Date eventStartDate = stringToDate(eventStartDateTimeFormatted);
					Date eventEndDate   = stringToDate(eventEndDateTimeFormatted);
					
					int utcOffset = TimeZone.getDefault().inDaylightTime( eventStartDate ) ? 7*60*60 : 8*60*60;
					Calendar c = Calendar.getInstance();
					c.setTime( eventStartDate );
			
					CalendarBean calendarBean = new CalendarBean();
					calendarBean.setEventMonth( c.get(Calendar.MONTH) );
					calendarBean.setEventYear( c.get(Calendar.YEAR) );
					
					calendarBean.setStart( eventStartDate.getTime()/1000 + utcOffset );
					calendarBean.setEventStartDateTime( DateUtils.dateToString(eventStartDate, DateUtils.DEFAULT_DATEFORMAT) );
					calendarBean.setEventStartDateTimeMS( eventStartDate.getTime() + utcOffset*1000 );
					calendarBean.setEventStartDateTimeS( eventStartDate.getTime()/1000 + utcOffset );
					
					calendarBean.setEnd( eventEndDate.getTime()/1000 + utcOffset );
					calendarBean.setEventEndDateTime( DateUtils.dateToString(eventEndDate, DateUtils.DEFAULT_DATEFORMAT) );
					calendarBean.setEventEndDateTimeMS( eventEndDate.getTime() + utcOffset*1000 );
					calendarBean.setEventEndDateTimeS( eventEndDate.getTime()/1000 + utcOffset );
					
					if ( null != eventStartDate && null != eventEndDate ) {
						
						long startTimeMS = eventStartDate.getTime();
						long endTimeMS = eventEndDate.getTime();
						long totalTimeMS = endTimeMS - startTimeMS;
						long oneDay = 8 * 60 * 60 * 1000;										
						if ( totalTimeMS > oneDay ) {
							calendarBean.setAllDay(true);
						}										
						
					} else {
						calendarBean.setAllDay(false);
					}
					
					if ( !url.startsWith("http") && !"".equals(url.trim()) ) {
						url = "http://" + url;
					}
					
					// Populate the bean with necessary event information.
					calendarBean.setUrl( url );
					calendarBean.setId( index );
					calendarBean.setTitle( title );
					calendarBean.setDescription( description );
					calendarBean.setClassName(CUSTOMEVENTCSS);
					calendarBean.setEventLocation(eventLocation);
					calendarBean.setBackgroundColor("#"+externalBgColor);
					calendarBean.setBorderColor("#"+externalBorderColor);
					calendarBean.setTextColor("#"+externalTextColor);
	
					// Fill out remaining attributes that aren't necessarily needed by the external event data feed.
					calendarBean.setGroupId( 0 );  // The conferences are from an external source and don't originate from a Group.
					calendarBean.setUrlTitle( "" );
					calendarBean.setDateTime( DateUtils.dateToString(eventStartDate, "EEEE, MMM d, yyyy") );
					calendarBean.setDateTimeMS( eventStartDate.getTime() );
					calendarBean.setDateTimeS( eventStartDate.getTime()/1000 );
					calendarBean.setType( "" );
					calendarBean.setSubType( "" );
					calendarBean.setChannel( "" );
					calendarBean.setMessage("");
					
					calendarBeanList.add(calendarBean);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return calendarBeanList;
	}
}
