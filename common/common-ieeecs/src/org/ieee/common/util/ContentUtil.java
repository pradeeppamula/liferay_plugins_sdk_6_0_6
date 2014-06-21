package org.ieee.common.util;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.servlet.ImageServletTokenUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Image;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.bookmarks.util.comparator.EntryNameComparator;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.io.*;
import java.net.URLEncoder;

import java.util.*;

import org.apache.commons.lang.StringUtils;

import org.ieee.common.bean.AssetPrimaryKeysBean;
import org.ieee.common.bean.CommunityContentBean;
import org.ieee.common.bean.ContentBean;
import org.ieee.common.constants.GlobalConstants;
import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;


public class ContentUtil {

	public static final String URLFILTER = "[#;\\/*?\"<>|&']";
	
	public static Map<String,String> defaultCommunityProperties = new HashMap<String,String>();
	public static Map<String,String> classNameMap = new HashMap<String,String>();

	static {
		defaultCommunityProperties.put("communities","");
		defaultCommunityProperties.put("channels","No Channels");
		defaultCommunityProperties.put("vocabularies","Content Types, Specific");
		defaultCommunityProperties.put("urlTargetName","content");
		defaultCommunityProperties.put("channelVocabularyName","No Channels");
		defaultCommunityProperties.put("contentTypeVocabularyName","Content Types");
		defaultCommunityProperties.put("publicServletMapping","/portal/web");
		defaultCommunityProperties.put("articleImagePath","/portal/image/image_gallery");
		defaultCommunityProperties.put("blogImagePath","/cms/Computer.org/ComputingNow/homepage/blog/logos");
		defaultCommunityProperties.put("supplement","");
		defaultCommunityProperties.put("defaultImagePath","");
		defaultCommunityProperties.put("displayDateFormat","EEEE, MMM d, yyyy");	

		classNameMap.put("Article", "com.liferay.portlet.journal.model.JournalArticle");
		classNameMap.put("Blog", "com.liferay.portlet.blogs.model.BlogsEntry");
	}


	/**
	 * @param companyGroupId
	 * @param start
	 * @param end
	 * @param contentListData
	 * @param urlTargetName
	 * @param channelVocabularyName
	 * @param contentTypeVocabularyName
	 * @param publicServletMapping
	 * @param articleImagePath
	 * @param blogImagePath
	 * @param supplement
	 * @param defaultImagePath
	 * @param displayDateFormat
	 * @return
	 */
	public static List<ContentBean> getContentList(long companyGroupId, Integer start, Integer end, String contentListData, String urlTargetName, 
			String channelVocabularyName, String contentTypeVocabularyName, String publicServletMapping, String articleImagePath, String blogImagePath,
			String supplement, String defaultImagePath, String displayDateFormat) {

		List<ContentBean> contentBeanList = new ArrayList<ContentBean>();

		try {

			List<AssetEntry> assetEntryList = getAssetsByCategory(start, end, contentListData);

			contentBeanList = getContentList(companyGroupId, assetEntryList, urlTargetName, 
					channelVocabularyName, contentTypeVocabularyName, publicServletMapping, articleImagePath, blogImagePath,
					supplement, defaultImagePath, displayDateFormat);

		} catch (Exception e) {
			e.printStackTrace();
		}			

		return contentBeanList;
	}

	/**
	 * @param companyGroupId
	 * @param assetEntryList
	 * @param urlTargetName
	 * @param channelVocabularyName
	 * @param contentTypeVocabularyName
	 * @param publicServletMapping
	 * @param articleImagePath
	 * @param blogImagePath
	 * @param supplement
	 * @param defaultImagePath
	 * @param displayDateFormat
	 * @return
	 */
	public static List<ContentBean> getContentList(long companyGroupId, List<AssetEntry> assetEntryList, String urlTargetName, 
			String channelVocabularyName, String contentTypeVocabularyName, String publicServletMapping, String articleImagePath, String blogImagePath,
			String supplement, String defaultImagePath, String displayDateFormat) {

		List<ContentBean> contentBeanList = new ArrayList<ContentBean>();

		try {

			if ( null != assetEntryList && assetEntryList.size() > 0 ) {

				Collection<Object> webContentIdArray = new ArrayList<Object>();
				Collection<Object> blogContentIdArray = new ArrayList<Object>();
				Map<Long,AssetPrimaryKeysBean> primaryKeyMap = new HashMap<Long,AssetPrimaryKeysBean>();

				for ( AssetEntry currentEntry : assetEntryList ) {

					// Since Liferay doesn't provide a better way to relate 
					//			Asset Entries <==> JournalArticles/Blogs/Etc <==> Categories 
					// we'll make a reference map to hold all of those Primary Keys and their relations.
					Long contentId = currentEntry.getClassPK();
					AssetPrimaryKeysBean apkb = new AssetPrimaryKeysBean();
					apkb.setEntryId(currentEntry.getEntryId());
					apkb.setGroupId(currentEntry.getGroupId());
					apkb.setCompanyId(currentEntry.getCompanyId());
					apkb.setUserId(currentEntry.getUserId());
					apkb.setContentId( contentId );
					apkb.setCategoryList(currentEntry.getCategories());
					primaryKeyMap.put( contentId, apkb);

					if ( currentEntry.getClassNameId() == ClassNameLocalServiceUtil.getClassNameId("com.liferay.portlet.journal.model.JournalArticle") ) {
						webContentIdArray.add(currentEntry.getClassPK());
					}

					if ( currentEntry.getClassNameId() == ClassNameLocalServiceUtil.getClassNameId("com.liferay.portlet.blogs.model.BlogsEntry") ) {
						blogContentIdArray.add(currentEntry.getClassPK());
					}					
				}	

				if ( null != webContentIdArray && webContentIdArray.size() > 0 ) {

					DynamicQuery query = DynamicQueryFactoryUtil.forClass(JournalArticle.class, PortalClassLoaderUtil.getClassLoader())
							.add(PropertyFactoryUtil.forName("resourcePrimKey").in(webContentIdArray));

					@SuppressWarnings("unchecked")
					List<JournalArticle> articleList = JournalArticleLocalServiceUtil.dynamicQuery(query);

					contentBeanList.addAll( populateContentBeanListWithArticles( companyGroupId, articleList, primaryKeyMap, 
							urlTargetName, channelVocabularyName, contentTypeVocabularyName, publicServletMapping, articleImagePath, defaultImagePath, displayDateFormat ));
				}

				if ( null != blogContentIdArray && blogContentIdArray.size() > 0 ) {

					DynamicQuery query = DynamicQueryFactoryUtil.forClass(BlogsEntry.class, PortalClassLoaderUtil.getClassLoader())
							.add(PropertyFactoryUtil.forName("entryId").in(blogContentIdArray));

					@SuppressWarnings("unchecked")
					List<BlogsEntry> blogEntryList = BlogsEntryLocalServiceUtil.dynamicQuery(query);

					contentBeanList.addAll( populateContentBeanListWithBlogs( companyGroupId, blogEntryList, primaryKeyMap, 
							urlTargetName, channelVocabularyName, contentTypeVocabularyName, publicServletMapping, blogImagePath, displayDateFormat ));
				}				
			} // if ( null != assetEntryList && assetEntryList.size() > 0 ) {

		} catch (Exception e) {
			e.printStackTrace();
		}			

		try {

			if ( null != supplement && !"".equals(supplement) ) {

				supplement = "[" + supplement + "]";
				JSONArray supplementArray = new JSONArray(supplement);

				if ( null != supplementArray && supplementArray.length() > 0 ) {

					List<ContentBean> supplementBeanList = new ArrayList<ContentBean>();

					for ( int index = 0; index < supplementArray.length(); index++ )  {

						JSONObject current = supplementArray.getJSONObject(index);
						String imageUrl    = current.has("imageUrl") ? current.getString("imageUrl") : "";
						String title       = current.has("title") ? current.getString("title") : "";
						String dateTime    = current.has("dateTime") ? current.getString("dateTime") : "";
						String description = current.has("description") ? current.getString("description") : "";
						String url         = current.has("url") ? current.getString("url") : "";
						String subType     = current.has("subType") ? current.getString("subType") : "";
						String target      = current.has("target") ? current.getString("target") : "";
						String channel     = current.has("channel") ? current.getString("channel") : "";
						boolean peerReviewed = current.has("peerReviewed") ? current.getBoolean("peerReviewed") : false;

						Date dateTimeSQL = DateUtils.stringToDate(dateTime, "yyyy-MM-dd HH:mm");

						ContentBean contentBean = new ContentBean();
						contentBean.setImagePath( imageUrl );
						contentBean.setTitle( title );
						contentBean.setDateTime( DateUtils.dateToString(dateTimeSQL, "EEEE, MMM d, yyyy") );
						contentBean.setDateTimeMS( dateTimeSQL.getTime() );
						contentBean.setDescription( description ) ;
						contentBean.setUrl( url );
						contentBean.setSubType( subType );
						contentBean.setType( "supplement" );
						contentBean.setTarget( target );
						contentBean.setChannel( channel );
						contentBean.setPeerReviewed(peerReviewed);
						supplementBeanList.add(contentBean);
					}

					contentBeanList.addAll(supplementBeanList);
				}							
			} // if ( null != supplement && !"".equals(supplement) ) {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return contentBeanList;
	}

	/**
	 * @param companyGroupId
	 * @param start
	 * @param end
	 * @param contentListData
	 * @param urlTargetName
	 * @param publicServletMapping
	 * @param articleImagePath
	 * @param displayDateFormat
	 * @param defaultImagePath
	 * @param contentTypeVocabularyName
	 * @return
	 */
	public static List<CommunityContentBean> getCommunityContentList(long companyGroupId, Integer start, Integer end, String contentListData, String urlTargetName, String contentTypeVocabularyName, 
															String publicServletMapping, String articleImagePath, String displayDateFormat, String defaultImagePath) {

		List<CommunityContentBean> contentBeanList = new ArrayList<CommunityContentBean>();

		try {

			List<AssetEntry> assetEntryList = getAssetsByCategory(start, end, contentListData);

			contentBeanList = getCommunityContentList(companyGroupId, assetEntryList, urlTargetName, contentTypeVocabularyName, publicServletMapping, articleImagePath, displayDateFormat, 
												      defaultImagePath);

		} catch (Exception e) {
			e.printStackTrace();
		}			

		return contentBeanList;
	}

	/**
	 * @param companyGroupId
	 * @param assetEntryList
	 * @param urlTargetName
	 * @param publicServletMapping
	 * @param articleImagePath
	 * @param displayDateFormat
	 * @param defaultImagePath
	 * @param contentTypeVocabularyName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<CommunityContentBean> getCommunityContentList(long companyGroupId, List<AssetEntry> assetEntryList, String urlTargetName, String contentTypeVocabularyName, 
															String publicServletMapping, String articleImagePath, String displayDateFormat, 
															String defaultImagePath) {

		List<CommunityContentBean> contentBeanList = new ArrayList<CommunityContentBean>();

		try {

			if ( null != assetEntryList && assetEntryList.size() > 0 ) {

				Collection<Object> webContentIdArray = new ArrayList<Object>();
				Map<Long,AssetPrimaryKeysBean> primaryKeyMap = new HashMap<Long,AssetPrimaryKeysBean>();

				for ( AssetEntry currentEntry : assetEntryList ) {

					// Since Liferay doesn't provide a better way to relate 
					//			Asset Entries <==> JournalArticles/Blogs/Etc <==> Categories 
					// we'll make a reference map to hold all of those Primary Keys and their relations.
					Long contentId = currentEntry.getClassPK();
					AssetPrimaryKeysBean apkb = new AssetPrimaryKeysBean();
					apkb.setEntryId(currentEntry.getEntryId());
					apkb.setGroupId(currentEntry.getGroupId());
					apkb.setCompanyId(currentEntry.getCompanyId());
					apkb.setUserId(currentEntry.getUserId());
					apkb.setContentId( contentId );
					apkb.setCategoryList(currentEntry.getCategories());
					primaryKeyMap.put( contentId, apkb);

					if ( currentEntry.getClassNameId() == ClassNameLocalServiceUtil.getClassNameId("com.liferay.portlet.journal.model.JournalArticle") ) {
						webContentIdArray.add(currentEntry.getClassPK());
					}				
				}	

				if ( null != webContentIdArray && webContentIdArray.size() > 0 ) {

					DynamicQuery query = DynamicQueryFactoryUtil.forClass(JournalArticle.class, PortalClassLoaderUtil.getClassLoader())
																.add(PropertyFactoryUtil.forName("resourcePrimKey").in(webContentIdArray));

					List<JournalArticle> articleList = JournalArticleLocalServiceUtil.dynamicQuery(query);

					contentBeanList.addAll( populateCommunityContentBeanListWithArticles( companyGroupId, articleList, primaryKeyMap, 
																			urlTargetName, contentTypeVocabularyName, publicServletMapping, articleImagePath, displayDateFormat, 
																			defaultImagePath ));
				}
			
			} // if ( null != assetEntryList && assetEntryList.size() > 0 ) {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return contentBeanList;
	}	

	/**
	 * @param start
	 * @param end
	 * @param contentListData
	 * @return
	 */
	public static List<AssetEntry> getAssetsByCategory(Integer start, Integer end, String contentListData) {

		List<AssetEntry> assetEntryList = new ArrayList<AssetEntry>();

		try {

			JSONArray contentListDataArray = new JSONArray(contentListData);
			int numberOfPairs = contentListDataArray.length();

			for (int index = 0; index < numberOfPairs; index++) {

				List<AssetEntry> holderList = new ArrayList<AssetEntry>();

				JSONObject data = contentListDataArray.getJSONObject(index);

				// Group
				String groupId = data.getString("groupId");
				long[] groupIds = {new Long(groupId)};

				// Type(s)
				String contentBit = data.getString("contentBit");
				long[] classNames = GlobalConstants.getClassNameIds(new Integer(contentBit));

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
				assetEntryList.addAll(holderList);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return assetEntryList;
	}
	
	public static Map<String,String> getAllCategoriesForThisCommunityAllowableByThisPortlet(long communityGroupId, String contentListData) {
		
		Map<String,String> communityCategoryMap = new TreeMap<String,String>();
		
		try {
			
			JSONArray contentListDataArray = new JSONArray(contentListData);
			int numberOfPairs = contentListDataArray.length();

			for (int index = 0; index < numberOfPairs; index++) {
				
				JSONObject data = contentListDataArray.getJSONObject(index);

				Long groupId = data.getLong("groupId");
				
				if ( groupId == communityGroupId ) {
					
					JSONArray categoryJSONArray = data.getJSONArray("categories");

					for ( int x = 0; x < categoryJSONArray.length(); x++ ) {
						String categoryId = (String) categoryJSONArray.get(x);
						AssetCategory ac = AssetCategoryLocalServiceUtil.getAssetCategory(new Long(categoryId));
						if ( null != ac ) {
							communityCategoryMap.put(ac.getName().trim().toUpperCase(), categoryId);
						}
					}	
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return communityCategoryMap;
	}

	/**
	 * @param jsonData
	 * @return
	 */
	public static List<AssetEntry> getAssetsByJSONData(JSONObject jsonData) {

		List<AssetEntry> assetEntryList = new ArrayList<AssetEntry>();

		try {

			JSONArray groupIdArray     = jsonData.has("groupIds") ? jsonData.getJSONArray("groupIds") : new JSONArray("[]");
			JSONArray classNameIdArray = jsonData.has("classNameIds") ? jsonData.getJSONArray("classNameIds") : new JSONArray("[]");
			JSONArray categoryIdArray  = jsonData.has("categoryIds") ? jsonData.getJSONArray("categoryIds") : new JSONArray("[]");

			if ( groupIdArray.length() == 0 && classNameIdArray.length() == 0 && categoryIdArray.length() == 0 ) {
				groupIdArray     = jsonData.has("defaultGroupIds") ? jsonData.getJSONArray("defaultGroupIds") : new JSONArray("[]");
				classNameIdArray = jsonData.has("defaultClassNameIds") ? jsonData.getJSONArray("defaultClassNameIds") : new JSONArray("[]");
				categoryIdArray  = jsonData.has("defaultCategoryIds") ? jsonData.getJSONArray("defaultCategoryIds") : new JSONArray("[]");
			} 

			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

			if ( groupIdArray.length() > 0 ) {					
				try {
					long[] groupIds = new long[groupIdArray.length()];					
					for ( int index = 0; index < groupIdArray.length(); index++ ) {						
						Long currentId = (Long) groupIdArray.get(index);
						groupIds[index] = currentId;						
					}		
					assetEntryQuery.setGroupIds( groupIds );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if ( classNameIdArray.length() > 0 ) {					
				try {
					long[] classNameIds = new long[classNameIdArray.length()];					
					for ( int index = 0; index < classNameIdArray.length(); index++ ) {						
						Integer currentId = (Integer) classNameIdArray.get(index);
						classNameIds[index] = new Long(currentId);						
					}		
					assetEntryQuery.setClassNameIds( classNameIds );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	

			if ( categoryIdArray.length() > 0 ) {					
				try {
					long[] categoryIds = new long[categoryIdArray.length()];					
					for ( int index = 0; index < categoryIdArray.length(); index++ ) {						
						Long currentId = (Long) categoryIdArray.get(index);
						categoryIds[index] = currentId;						
					}		
					assetEntryQuery.setAllCategoryIds( categoryIds );
				} catch (Exception e) {
					e.printStackTrace();
				}
			}				

			assetEntryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return assetEntryList;
	}

	/**
	 * @param companyGroupId
	 * @param articleList
	 * @param primaryKeyMap
	 * @param urlTargetName
	 * @param channelVocabularyName
	 * @param contentTypeVocabularyName
	 * @param publicServletMapping
	 * @param articleImagePath
	 * @param defaultImagePath
	 * @param displayDateFormat
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static List<ContentBean> populateContentBeanListWithArticles(long companyGroupId, List<JournalArticle> articleList, 
			Map<Long,AssetPrimaryKeysBean> primaryKeyMap, String urlTargetName, String channelVocabularyName, String contentTypeVocabularyName,
			String publicServletMapping, String articleImagePath, String defaultImagePath, String displayDateFormat) {

		List<ContentBean> contentBeanList = new ArrayList<ContentBean>();

		try {

			if ( null != articleList && articleList.size() > 0 ) {			

				for ( JournalArticle currentArticle : articleList ) {

					boolean isLatestVersion = JournalArticleLocalServiceUtil.isLatestVersion( currentArticle.getGroupId(), currentArticle.getArticleId(), currentArticle.getVersion() );

					// *******************
					// Is it the latest?
					// *******************
					if ( isLatestVersion ) {

						Date currentDate = new Date();
						Date displayDate = currentArticle.getDisplayDate();

						if ( null != displayDate ) {

							Long currentDateMS = currentDate.getTime();
							Long displayDateMS = displayDate.getTime();
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

										ContentBean contentBean = new ContentBean();

										// Content placeholder Image
										String smallImagePath = getPlaceholderImage(currentArticle, articleImagePath, defaultImagePath);					

										// Construct the URL that will be used to view this content
										// Group    = community url
										// Category = The Channel/Page
										// Type     = article/webinar/static
										// 
										// Will result in /Group_CommunityName/Channel_PageName/article  (or whatever type the content is)

										String urlToContent = "";
										String subType = "";
										String channel = "";									
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
												urlTitle = URLEncoder.encode(urlTitle);
												if ( !"".equals(categoryUrl) ) {
													urlToContent = publicServletMapping + groupUrl + "/" + categoryUrl + "/" + urlTargetName + "?g=" + groupId + "&type=" + type + "&urlTitle=" + urlTitle;	
												} else {
													urlToContent = publicServletMapping + groupUrl + "/" + urlTargetName + "?g=" + groupId + "&type=" + type + "&urlTitle=" + urlTitle;	
												}
											}

										} // if ( null != apkb ) {

										// Check to see if the article has been "peer reviewed" or not.		
										boolean peerReviewed = false;
										if ( currentArticle.getExpandoBridge().hasAttribute( "PeerReviewed" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "PeerReviewed" ) ) {
												peerReviewed = (Boolean) currentArticle.getExpandoBridge().getAttribute( "PeerReviewed" );
											}
										}
										
										// Get the Sub-Categoy values for this content object	
										String subCategories = "";
										if ( currentArticle.getExpandoBridge().hasAttribute( "SubCategories" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "SubCategories" ) ) {
												subCategories = (String) currentArticle.getExpandoBridge().getAttribute( "SubCategories" );
											}
										}										

										// Should this content open up in a new browser or exist in the current?
										String customURLTarget = "_self";
										if ( currentArticle.getExpandoBridge().hasAttribute( "CustomURLTarget" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "CustomURLTarget" ) ) {
												String[] customURLTargetValue = (String[]) currentArticle.getExpandoBridge().getAttribute( "CustomURLTarget" );

												if ( customURLTargetValue.length > 1 ) {
													customURLTarget = "_self";
												} else {
													customURLTarget = customURLTargetValue[0];
													if ( customURLTarget.indexOf("_self") > -1 ) {
														customURLTarget = "_self";
													} else if ( customURLTarget.indexOf("_blank") > -1 ) {

														if ( customURLTarget.indexOf("New Window") > -1 ) {  // Before this custom field was set, handle old values.
															customURLTarget = "_self";
														} else {
															customURLTarget = "_blank";
														}													

													} else {
														customURLTarget = "_self";
													}	
												}
											}
										}

										// Now that the URL has been constructed, populate the Content Bean and add to the List.
										contentBean.setImagePath( smallImagePath );
										contentBean.setGroupId( currentArticle.getGroupId() );
										contentBean.setUrl( urlToContent );
										contentBean.setUrlTitle( currentArticle.getUrlTitle() );
										contentBean.setId( currentArticle.getId() );
										contentBean.setTitle( currentArticle.getTitle() );
										contentBean.setDescription( currentArticle.getDescription() );
										contentBean.setDateTime( DateUtils.dateToString(currentArticle.getDisplayDate(), displayDateFormat) );
										contentBean.setDateTimeMS( currentArticle.getDisplayDate().getTime() );
										contentBean.setType( currentArticle.getType().toLowerCase() );
										contentBean.setSubType( subType );
										contentBean.setChannel(channel);
										contentBean.setPeerReviewed(peerReviewed);
										contentBean.setTarget(customURLTarget);	
										contentBean.setSubCategories(subCategories);
										contentBeanList.add(contentBean);

									} // if ( type.equalsIgnoreCase("article") ) {
								} // if ( currentStatus == 0 ) {
							} // if ( currentDateMS >= displayDateMS && currentDateMS < expirationDateMS ) {
						} // if ( null != displayDate ) {
					} // if ( isLatestVersion ) {
				}
			}			

		} catch (Exception e) {
			e.printStackTrace();
		}		

		return contentBeanList;
	}

	/**
	 * @param companyGroupId
	 * @param articleList
	 * @param primaryKeyMap
	 * @param urlTargetName
	 * @param publicServletMapping
	 * @param articleImagePath
	 * @param displayDateFormat
	 * @param defaultImagePath
	 * @param contentTypeVocabularyName
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static List<CommunityContentBean> populateCommunityContentBeanListWithArticles(long companyGroupId, List<JournalArticle> articleList, 
			Map<Long,AssetPrimaryKeysBean> primaryKeyMap, String urlTargetName, String contentTypeVocabularyName, String publicServletMapping, String articleImagePath, String displayDateFormat, 
			String defaultImagePath) {

		List<CommunityContentBean> contentBeanList = new ArrayList<CommunityContentBean>();

		try {

			if ( null != articleList && articleList.size() > 0 ) {			

				for ( JournalArticle currentArticle : articleList ) {

					boolean isLatestVersion = JournalArticleLocalServiceUtil.isLatestVersion( currentArticle.getGroupId(), currentArticle.getArticleId(), currentArticle.getVersion() );

					// *******************
					// Is it the latest?
					// *******************
					if ( isLatestVersion ) {

						Date currentDate = new Date();
						Date displayDate = currentArticle.getDisplayDate();

						if ( null != displayDate ) {

							Long currentDateMS  = currentDate.getTime();
							Long displayDateMS  = displayDate.getTime();
							Date expirationDate = currentArticle.getExpirationDate();
							Long expirationDateTimeMS = null == expirationDate ? 9999999999999L : currentArticle.getExpirationDate().getTime();

							// *****************************************
							// Can we display it and has it expired?
							// *****************************************					
							if ( currentDateMS >= displayDateMS && currentDateMS < expirationDateTimeMS ) {

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

										CommunityContentBean contentBean = new CommunityContentBean();

										// Content placeholder Image
										String smallImagePath = getPlaceholderImage(currentArticle, articleImagePath, defaultImagePath);					

										// Construct the URL that will be used to view this content
										String urlToContent = "";
										String subType      = "";
										String allSubTypes  = "";
										String categories   = "";									
										long resourcePrimaryKey = currentArticle.getResourcePrimKey();
										AssetPrimaryKeysBean apkb = primaryKeyMap.get(resourcePrimaryKey);

										if ( null != apkb ) {

											// Group/Community Friendly URL part
											long groupId = apkb.getGroupId();
											Group group = GroupLocalServiceUtil.getGroup(groupId);
											String groupUrl = group.getFriendlyURL();
										
											List<AssetCategory> categoryList = apkb.getCategoryList();							

											if ( null != categoryList && categoryList.size() > 0 ) {

												// We'll need to distinguish each asset by its content subtype, (Interview, Webinar, Promo, etc), for display in the UI
												AssetVocabulary apkbSubTypeVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId, contentTypeVocabularyName);

												if ( null != apkbSubTypeVocabulary ) {
													for ( AssetCategory ac : categoryList ) {

														long currentVocabId = ac.getVocabularyId();

														if ( currentVocabId == apkbSubTypeVocabulary.getVocabularyId() && "".equals(subType) ) {
															subType = ac.getName().toUpperCase();
														}	
														
														allSubTypes = allSubTypes + ac.getName().toUpperCase() + ",";
														categories = categories + ac.getCategoryId() + ",";
													}
												}
												allSubTypes = !"".equals(allSubTypes) ? allSubTypes.substring(0, allSubTypes.length()-1) : "";
												categories  = !"".equals(categories)  ? categories.substring(0, categories.length()-1)   : "";
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
												urlTitle = URLEncoder.encode(urlTitle);
												urlToContent = publicServletMapping + groupUrl + "/" + urlTargetName + "?g=" + groupId + "&type=" + type + "&urlTitle=" + urlTitle;													
											}

										} // if ( null != apkb ) {

										// Check to see if the article has been "peer reviewed" or not.		
										boolean peerReviewed = false;
										if ( currentArticle.getExpandoBridge().hasAttribute( "PeerReviewed" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "PeerReviewed" ) ) {
												peerReviewed = (Boolean) currentArticle.getExpandoBridge().getAttribute( "PeerReviewed" );
											}
										}

										// Should this content open up in a new browser or exist in the current?
										String customURLTarget = "_self";
										if ( currentArticle.getExpandoBridge().hasAttribute( "CustomURLTarget" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "CustomURLTarget" ) ) {
												String[] customURLTargetValue = (String[]) currentArticle.getExpandoBridge().getAttribute( "CustomURLTarget" );

												if ( customURLTargetValue.length > 1 ) {
													customURLTarget = "_self";
												} else {
													customURLTarget = customURLTargetValue[0];
													if ( customURLTarget.indexOf("_self") > -1 ) {
														customURLTarget = "_self";
													} else if ( customURLTarget.indexOf("_blank") > -1 ) {

														if ( customURLTarget.indexOf("New Window") > -1 ) {  // Before this custom field was set, handle old values.
															customURLTarget = "_self";
														} else {
															customURLTarget = "_blank";
														}													

													} else {
														customURLTarget = "_self";
													}	
												}
											}
										}
										
										// Retrieve the Creator(s)		
										String creator = "";
										if ( currentArticle.getExpandoBridge().hasAttribute( "Creator" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "Creator" ) ) {
												creator = (String) currentArticle.getExpandoBridge().getAttribute( "Creator" );
											}
										}
										
										// Are comments allowed for this content?	
										boolean comments = true;
										if ( currentArticle.getExpandoBridge().hasAttribute( "IsCommentingActive" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "IsCommentingActive" ) ) {
												comments = (Boolean) currentArticle.getExpandoBridge().getAttribute( "IsCommentingActive" );
											}
										}	
										
										// Is Social Sharing allowed for this content?	
										boolean social = true;
										if ( currentArticle.getExpandoBridge().hasAttribute( "IsSocialShareActive" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "IsSocialShareActive" ) ) {
												social = (Boolean) currentArticle.getExpandoBridge().getAttribute( "IsSocialShareActive" );
											}
										}	
										
										// Retrieve the Multimedia content.		
										String multiMedia = "";
										if ( currentArticle.getExpandoBridge().hasAttribute( "MultiMedia" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "MultiMedia" ) ) {
												multiMedia = (String) currentArticle.getExpandoBridge().getAttribute( "MultiMedia" );
											}
										}	
										
										// Retrieve the Related Content.		
										String relatedContent = "";
										if ( currentArticle.getExpandoBridge().hasAttribute( "RelatedContent" ) ) {
											if ( null != currentArticle.getExpandoBridge().getAttribute( "RelatedContent" ) ) {
												relatedContent = (String) currentArticle.getExpandoBridge().getAttribute( "RelatedContent" );
											}
										}											

										// Now that the URL has been constructed, populate the Content Bean and add to the List.
										contentBean.setImagePath( smallImagePath );
										contentBean.setResourcePrimKey( currentArticle.getResourcePrimKey() );
										contentBean.setGroupId( currentArticle.getGroupId() );
										contentBean.setUrl( urlToContent );
										contentBean.setUrlTitle( currentArticle.getUrlTitle() );
										contentBean.setId( currentArticle.getId() );
										contentBean.setTitle( currentArticle.getTitle() );
										contentBean.setDescription( currentArticle.getDescription() );
										contentBean.setDateTime( DateUtils.dateToString(currentArticle.getDisplayDate(), displayDateFormat) );
										contentBean.setDateTimeMS( currentArticle.getDisplayDate().getTime() );
										contentBean.setType( currentArticle.getType().toLowerCase() );
										contentBean.setSubType( subType );
										contentBean.setAllSubTypes( allSubTypes );
										contentBean.setChannel( "" );
										contentBean.setPeerReviewed( peerReviewed );
										contentBean.setTarget( customURLTarget );	
										contentBean.setExpirationDateTime( null == expirationDate ? "" : DateUtils.dateToString(expirationDate, displayDateFormat)  );
										contentBean.setExpirationDateTimeMS( expirationDateTimeMS );
										contentBean.setCreator( creator );
										contentBean.setComments( comments );
										contentBean.setSocial( social );
										contentBean.setMultiMedia( multiMedia );
										contentBean.setRelatedContent( relatedContent );
										contentBean.setCategories(categories);
										contentBeanList.add( contentBean );

									} // if ( type.equalsIgnoreCase("article") ) {
								} // if ( currentStatus == 0 ) {
							} // if ( currentDateMS >= displayDateMS && currentDateMS < expirationDateTimeMS ) {
						} // if ( null != displayDate ) {
					} // if ( isLatestVersion ) {
				}
			}			

		} catch (Exception e) {
			e.printStackTrace();
		}		

		return contentBeanList;
	}
	
	/**
	 * @param companyGroupId
	 * @param blogEntryList
	 * @param primaryKeyMap
	 * @param urlTargetName
	 * @param channelVocabularyName
	 * @param contentTypeVocabularyName
	 * @param publicServletMapping
	 * @param blogImagePath
	 * @param displayDateFormat
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private static List<ContentBean> populateContentBeanListWithBlogs(long companyGroupId, List<BlogsEntry> blogEntryList, 
			Map<Long,AssetPrimaryKeysBean> primaryKeyMap, String urlTargetName, String channelVocabularyName, String contentTypeVocabularyName,
			String publicServletMapping, String blogImagePath, String displayDateFormat) {

		List<ContentBean> contentBeanList = new ArrayList<ContentBean>();

		try {

			if ( null != blogEntryList && blogEntryList.size() > 0 ) {	

				for ( BlogsEntry currentEntry : blogEntryList ) {

					// Retrieve whether we're dealing with a Blog Post or Podcast
					String[] types = new String[] {};
					String type = "";

					if ( currentEntry.getExpandoBridge().hasAttribute( "BlogType" ) ) {
						if ( null != currentEntry.getExpandoBridge().getAttribute( "BlogType" ) ) {
							types = (String[]) currentEntry.getExpandoBridge().getAttribute( "BlogType" );	
							type = null != types && types.length > 0 ? types[0].toLowerCase() : "";
						}
					}	
					
					// Get the Sub-Categoy values for this content object	
					String subCategories = "";
					if ( currentEntry.getExpandoBridge().hasAttribute( "SubCategories" ) ) {
						if ( null != currentEntry.getExpandoBridge().getAttribute( "SubCategories" ) ) {
							subCategories = (String) currentEntry.getExpandoBridge().getAttribute( "SubCategories" );
						}
					}						

					// Construct the URL that will be used to view this content
					// Group    = community url
					// Category = The Channel/Page
					// Type     = article/webinar/static
					// 
					// Will result in /Group_CommunityName/Channel_PageName/article  (or whatever type the content is)

					String urlToContent = "";
					String subType = "".equals(type) ? "BLOGPOST" : type.toUpperCase();
					String channel = "";
					long blogEntryId = currentEntry.getEntryId();
					AssetPrimaryKeysBean apkb = primaryKeyMap.get(blogEntryId);

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
						List<AssetCategory> categoryList = apkb.getCategoryList();							

						if ( null != categoryList && categoryList.size() > 0 ) {

							AssetVocabulary apkbChannelVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId, channelVocabularyName) ;							

							// We'll also need to distinguish each asset by its content subtype, (Interview, Webinar, Promo, etc), for display in the UI
							AssetVocabulary apkbSubTypeVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(companyGroupId, contentTypeVocabularyName);

							if ( null != apkbChannelVocabulary ) {
								for ( AssetCategory ac : categoryList ) {

									long currentVocabId = ac.getVocabularyId();

									if ( currentVocabId == apkbChannelVocabulary.getVocabularyId() ) {
										channel = ac.getName().toUpperCase();	
									}

									if ( currentVocabId == apkbSubTypeVocabulary.getVocabularyId() && "".equals(subType) ) {
										subType = ac.getName();
										subType = subType.toUpperCase();
									}										
								}
							}
						}

						// Title of Content, Friendly URL part
						String urlTitle = currentEntry.getUrlTitle();
						urlTitle = URLEncoder.encode(urlTitle);
						urlToContent = publicServletMapping + groupUrl + "/" + urlTargetName + "?g=" + groupId + "&type=" + type + "&urlTitle=" + urlTitle;	

					} // if ( null != apkb ) {

					// Now that the URL has been constructed, populate the Content Bean and add to the List.
					ContentBean contentBean = new ContentBean();
					contentBean.setUrl( urlToContent );
					contentBean.setImagePath(blogImagePath + "/" + currentEntry.getGroupId() + ".png");
					contentBean.setUrlTitle( currentEntry.getUrlTitle() );
					contentBean.setId( currentEntry.getEntryId() );
					contentBean.setTitle( currentEntry.getTitle() );					
					contentBean.setDateTime( DateUtils.dateToString(currentEntry.getDisplayDate(), displayDateFormat) );
					contentBean.setDateTimeMS( currentEntry.getDisplayDate().getTime() );
					contentBean.setType( type );
					contentBean.setSubType( subType );
					contentBean.setGroupId( currentEntry.getGroupId() );
					contentBean.setChannel(channel);
					contentBean.setTarget("_self");
					contentBean.setSubCategories(subCategories);

					if ( currentEntry.getExpandoBridge().hasAttribute( "Description" ) ) {
						if ( null != currentEntry.getExpandoBridge().getAttribute( "Description" ) ) {
							String description = (String) currentEntry.getExpandoBridge().getAttribute( "Description" );
							contentBean.setDescription( description );
						} else {
							contentBean.setDescription( "Please enter a Description." );
						}
					} else {
						contentBean.setDescription( "Please enter a Description for this Blog Entry." );
					}				

					contentBeanList.add(contentBean);	

				} // for ( BlogsEntry currentEntry : blogEntryList ) {
			} // if ( null != blogEntryList && blogEntryList.size() > 0 ) {		

		} catch (Exception e) {
			e.printStackTrace();
		}		

		return contentBeanList;
	}	

	/**
	 * @param groupId
	 * @return
	 */
	public static long[] getCategoryIdsByGroupId(long groupId) {

		long[] categoryIds = new long[0];

		try {

			DynamicQuery categoryQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class, PortalClassLoaderUtil.getClassLoader())
					.add(PropertyFactoryUtil.forName("groupId").eq(groupId));

			@SuppressWarnings("unchecked")
			List<AssetCategory> assetCategoryList = AssetCategoryLocalServiceUtil.dynamicQuery(categoryQuery);

			if ( null != assetCategoryList && assetCategoryList.size() > 0 ) {

				categoryIds = new long[assetCategoryList.size()];

				for ( int index = 0; index < assetCategoryList.size(); index++ ) {
					long categoryId = assetCategoryList.get(index).getCategoryId();
					categoryIds[index] = categoryId;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoryIds;
	}

	/**
	 * @param fileName
	 * @return
	 */
	public static String getCommunityListFromDL(String fileName) {

		String output = "";

		try {

			DynamicQuery contentItemQuery = DynamicQueryFactoryUtil.forClass(DLFileEntry.class, PortalClassLoaderUtil.getClassLoader())
					.add(PropertyFactoryUtil.forName("title").eq(fileName));

			@SuppressWarnings("unchecked")
			List<DLFileEntry> fileEntryList = DLFileEntryLocalServiceUtil.dynamicQuery(contentItemQuery);

			if ( null != fileEntryList && fileEntryList.size() > 0 ) {

				DLFileEntry fe = fileEntryList.get(0);

				InputStream is = DLFileEntryLocalServiceUtil.getFileAsStream(fe.getCompanyId(), fe.getUserId(), fe.getGroupId(), fe.getFolderId(), fe.getName());

				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line;
				while ((line = br.readLine()) != null)   {	

					if ( !StringUtils.startsWith(line, "#")) {						
						output = line;
						break;
					}
				}
				is.close();				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}	

		return output;		
	}

	/**
	 * @param currentArticle
	 * @param articleImagePath
	 * @param defaultImagePath
	 * @return
	 */
	public static String getPlaceholderImage(JournalArticle currentArticle, String articleImagePath, String defaultImagePath) {

		String smallImagePath = "";

		try {

			String smallImageUrl = currentArticle.getSmallImageURL();

			if ( null == smallImageUrl || "".equals(smallImageUrl) ) {
				long smallImageId = currentArticle.getSmallImageId();					
				String smallImageToken = ImageServletTokenUtil.getToken(smallImageId);																					
				Image fileSystemImage = ImageLocalServiceUtil.getImage(smallImageId);

				if ( null == fileSystemImage ) {
					smallImagePath = defaultImagePath;
				} else {
					smallImagePath = articleImagePath + "?img_id=" + smallImageId + "&t=" + smallImageToken;
				}

			} else {
				smallImagePath = smallImageUrl;
			}

		} catch (Exception e) {
			smallImagePath = defaultImagePath;
			e.printStackTrace();
		}

		return smallImagePath;
	}

	/**
	 * @param propertyFile
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> getCommunityProperties(String propertyFile) {

		Map<String,String> communityPropertiesMap = new HashMap<String,String>();

		try {

			if ( null == propertyFile || "".equals(propertyFile) ) {

				communityPropertiesMap.put("communities", defaultCommunityProperties.get("communities"));
				communityPropertiesMap.put("channels", defaultCommunityProperties.get("channels"));
				communityPropertiesMap.put("vocabularies", defaultCommunityProperties.get("vocabularies"));
				communityPropertiesMap.put("urlTargetName", defaultCommunityProperties.get("urlTargetName"));
				communityPropertiesMap.put("channelVocabularyName", defaultCommunityProperties.get("channelVocabularyName"));
				communityPropertiesMap.put("contentTypeVocabularyName", defaultCommunityProperties.get("contentTypeVocabularyName"));
				communityPropertiesMap.put("publicServletMapping", defaultCommunityProperties.get("publicServletMapping"));
				communityPropertiesMap.put("articleImagePath", defaultCommunityProperties.get("articleImagePath"));
				communityPropertiesMap.put("blogImagePath", defaultCommunityProperties.get("blogImagePath"));
				communityPropertiesMap.put("supplement", defaultCommunityProperties.get("supplement"));
				communityPropertiesMap.put("defaultImagePath", defaultCommunityProperties.get("defaultImagePath"));
				communityPropertiesMap.put("displayDateFormat", defaultCommunityProperties.get("displayDateFormat"));				

			} else {

				// -----------------------------------------------------------------------------------------------
				// Read this micro-site/site Properites map, stored within the Liferay Document Library.  
				// -----------------------------------------------------------------------------------------------
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
							value = null == value || "".equals(value) ? defaultCommunityProperties.get(key) : value;
							communityPropertiesMap.put(key, value);
						}
					}
					is.close();				
				} // if ( null != fileEntryList && fileEntryList.size() > 0 ) {				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return communityPropertiesMap;
	}

	/**
	 * @return
	 */
	public static String getValidClassNameIds() {

		String classNameIds = "";

		try {

			Iterator<String> it = classNameMap.keySet().iterator();
			while (it.hasNext()) {

				String key = (String) it.next();
				String value = classNameMap.get(key);
				long classNameId = ClassNameLocalServiceUtil.getClassNameId(value);
				classNameIds = classNameIds + "," + classNameId;
			}
			classNameIds = classNameIds.substring(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return classNameIds;		
	}

	/**
	 * @param groupId
	 * @param vocabularyNames
	 * @return
	 */
	public static String getValidCategoryIds(long groupId, String vocabularyNames) {

		String categoryIds = "";

		try {

			String[] vocabularies = vocabularyNames.split(",");			
			if ( vocabularies.length > 0 ) {

				for ( int index = 0; index < vocabularies.length; index++ ) {

					String currentVocabName = vocabularies[index];
					AssetVocabulary currentAssetVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(groupId, currentVocabName.trim());
					OrderByComparator obc = new EntryNameComparator(true);
					List<AssetCategory> assetCategoryList = AssetCategoryLocalServiceUtil.getVocabularyCategories(currentAssetVocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

					if ( null != assetCategoryList && assetCategoryList.size() > 0 ) {
						for ( AssetCategory assetCategory : assetCategoryList ) {
							categoryIds = categoryIds + "," + assetCategory.getCategoryId();
						}
					}

				}
				categoryIds = categoryIds.substring(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoryIds;		
	}	
	
	/**
	 * @param title
	 * @return
	 */
	public static String prepareUrlTitle(String title) {
		
		String finalUrlTitle = "";
		
		try {
			
			finalUrlTitle = title.replaceAll(" ", "-");
			finalUrlTitle = finalUrlTitle.replaceAll(URLFILTER, "");			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return finalUrlTitle;
	}

}