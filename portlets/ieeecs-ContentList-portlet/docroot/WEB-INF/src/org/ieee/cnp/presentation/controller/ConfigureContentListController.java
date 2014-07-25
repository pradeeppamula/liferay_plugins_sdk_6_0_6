package org.ieee.cnp.presentation.controller;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;

import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.bookmarks.util.comparator.EntryNameComparator;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import javax.portlet.*;

import org.apache.commons.lang.StringUtils;

import org.ieee.cnp.bean.CategoryBean;
import org.ieee.cnp.bean.GroupBean;
import org.ieee.cnp.util.ContentListUtil;

import org.ieee.common.constants.GlobalConstants;
import org.ieee.common.presentation.controller.BaseController;

import org.springframework.web.portlet.ModelAndView;


public class ConfigureContentListController extends BaseController {

	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();	
		Map<String, GroupBean> groupMap = new TreeMap<String, GroupBean>();
		Map<String, GroupBean> communityMap = new TreeMap<String, GroupBean>();
		Map<String, CategoryBean> channelMap = new TreeMap<String, CategoryBean>();
		Map<String, Map<String,CategoryBean>> vocabularyMap = new TreeMap<String, Map<String,CategoryBean>>();
		
		
		Map<String, CategoryBean> subCategoriesMap = new TreeMap<String, CategoryBean>();
		
		
		String instanceId = "";
		boolean fallbackJS = true;
		
		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			// We assume that the theme where this portlet resides doesn't contain all of the required Javascript libraries.
			// If it finds that the "complianceVersion" value is set in the theme's "liferay-look-and-feel.xml" file, then it contains all needed libraries.
			String complianceVersion = themeDisplay.getTheme().getSetting("complianceVersion");
			if ( null != complianceVersion && !"".equals(complianceVersion) ) {
				fallbackJS = false;
			}			
			
			// Now continue with the Portlet preferences settings and other parameters needed to configure this list.
			PortletPreferences prefs = renderRequest.getPreferences();
			ContentListUtil.putPortletPreferencesIntoModel(prefs, model);			
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();	

			populateMaps(themeDisplay.getCompanyGroupId(), themeDisplay.getScopeGroupId(), 
						 prefs.getValue("propertiesFile", ContentListUtil.PROPERTIESFILE), communityMap, channelMap, vocabularyMap, subCategoriesMap);
						
			String contentListData = prefs.getValue("contentListData", ContentListUtil.CONTENTLISTDATA);
			contentListData = "".equals(contentListData.trim()) ? "[]" : contentListData;
			model.put("contentListData", contentListData);
			
			String cssBlock = prefs.getValue("cssBlock", ContentListUtil.CSSBLOCK);
			String htmlBlock = prefs.getValue("htmlBlock", ContentListUtil.HTMLBLOCK);
			
			model.put("cssBlock", cssBlock);
			model.put("htmlBlock", htmlBlock);
						
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("id", instanceId);
		model.put("contentItemMap", GlobalConstants.contentItemMap);
		model.put("groupMap", groupMap);
		model.put("communityMap", communityMap);
		model.put("channelMap", channelMap);
		
		model.put("subCategoriesMap", subCategoriesMap);
		
		
		model.put("vocabularyMap", vocabularyMap);
		model.put("fallbackJS", fallbackJS);		
		ModelAndView modelAndView = new ModelAndView("Configure", model);

		return modelAndView;
	}

	protected void handleActionRequestInternal(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			String portletId = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
			int cropHere = portletId.indexOf("_INSTANCE_");
			String instanceId = "_" + portletId.substring(cropHere+10);
			
			// The "source" attribute is not stored and is used solely to determine where the Request came from.
			// When a person opens up the Portlet "config/edit" window, then decides to go to the Liferay Control Panel, and then returns
			// to the Portlet "config/edit" screen, then this "handleActionRequestInternal" method is fired.   I guess the Liferay "Return to Full Page"
			// link within the Control Panel is an ActionURL instead of a RenderURL.   The "source" will be null, and therefore empty, if the 
			// person returns to the "config/edit" screen from the Control Panel.
			String source = ParamUtil.getString(actionRequest, "source"+instanceId, "");
			
			if ( source.equalsIgnoreCase(ContentListUtil.CONFIG) ) {
				
				String portletMode = ParamUtil.getString(actionRequest, "portletMode"+instanceId, ContentListUtil.MODE);
				String modifiedByUserId = new Long(themeDisplay.getUserId()).toString();
				
				// The Group/Category values will come from the "actionRequest" as (for example):
				//		[ 
				//			{"uiId":"1345428490345VGCcwIiM","groupId":"16228","contentBit":7,"categories":["17256"],"description":"ComputingNow (Articles, Blog Posts, Podcasts) in 'Cloud'"},
				//			...
				//			...
				//		] 			
				//
				String contentListData = ParamUtil.getString(actionRequest, "contentListData"+instanceId, ContentListUtil.CONTENTLISTDATA);		
				
				String cssBlock  = ParamUtil.getString(actionRequest, "cssBlock"+instanceId, ContentListUtil.CSSBLOCK);
				String htmlBlock = ParamUtil.getString(actionRequest, "htmlBlock"+instanceId, ContentListUtil.HTMLBLOCK);
							
				String uiLayout          = ParamUtil.getString(actionRequest, "uiLayout"+instanceId, ContentListUtil.UILAYOUT);
				String perPage           = ParamUtil.getString(actionRequest, "perPage"+instanceId, ContentListUtil.PERPAGE);
				String pagination        = ParamUtil.getString(actionRequest, "pagination"+instanceId, ContentListUtil.PAGINATION);
				String titleVisible      = ParamUtil.getString(actionRequest, "titleVisible"+instanceId, ContentListUtil.TITLEVISIBLE);
				String titleOfList       = ParamUtil.getString(actionRequest, "titleOfList"+instanceId, ContentListUtil.TITLEOFLIST);
				String titleTopMargin    = ParamUtil.getString(actionRequest, "titleTopMargin"+instanceId, ContentListUtil.TITLETOPMARGIN);
				String titleBottomMargin = ParamUtil.getString(actionRequest, "titleBottomMargin"+instanceId, ContentListUtil.TITLEBOTTOMMARGIN);
				String titleColor        = ParamUtil.getString(actionRequest, "titleColor"+instanceId, ContentListUtil.TITLECOLOR);
				String titleFont         = ParamUtil.getString(actionRequest, "titleFont"+instanceId, ContentListUtil.TITLEFONT);				
				String filtering         = ParamUtil.getString(actionRequest, "filtering"+instanceId, ContentListUtil.FILTERING);
				String scrollDuration    = ParamUtil.getString(actionRequest, "scrollDuration"+instanceId, ContentListUtil.SCROLLDURATION);
				String initialChunk      = ParamUtil.getString(actionRequest, "initialChunk"+instanceId, ContentListUtil.INITIALCHUNK);
				String asyncChunk        = ParamUtil.getString(actionRequest, "asyncChunk"+instanceId, ContentListUtil.ASYNCCHUNK);
				String recordsToPull     = ParamUtil.getString(actionRequest, "recordsToPull"+instanceId, ContentListUtil.RECORDSTOPULL);
				String nextText          = ParamUtil.getString(actionRequest, "nextText"+instanceId, ContentListUtil.NEXTTEXT);
				String prevText          = ParamUtil.getString(actionRequest, "prevText"+instanceId, ContentListUtil.PREVTEXT);		
				String searchInputText        = ParamUtil.getString(actionRequest, "searchInputText"+instanceId, ContentListUtil.SEARCHINPUTTEXT);
	
				String titleBorderColor       = ParamUtil.getString(actionRequest, "titleBorderColor"+instanceId, ContentListUtil.TITLEBORDERCOLOR);
				String titleBackgroundColor   = ParamUtil.getString(actionRequest, "titleBackgroundColor"+instanceId, ContentListUtil.TITLEBACKGRCOLOR);
	
				String titleTopLeftRadius     = ParamUtil.getString(actionRequest, "titleTopLeftRadius"+instanceId, ContentListUtil.TITLETOPLEFTRAD);
				String titleBottomLeftRadius  = ParamUtil.getString(actionRequest, "titleBottomLeftRadius"+instanceId, ContentListUtil.TITLEBOTLEFTRAD);
				String titleTopRightRadius    = ParamUtil.getString(actionRequest, "titleTopRightRadius"+instanceId, ContentListUtil.TITLETOPRGHTRAD);
				String titleBottomRightRadius = ParamUtil.getString(actionRequest, "titleBottomRightRadius"+instanceId, ContentListUtil.TITLEBOTRGHTRAD);
	
				String paginationBorderColor     = ParamUtil.getString(actionRequest, "paginationBorderColor"+instanceId, ContentListUtil.PAGINATIONBORDERCOLOR);
				String paginationBackgroundColor = ParamUtil.getString(actionRequest, "paginationBackgroundColor"+instanceId, ContentListUtil.PAGINATIONBACKGRCOLOR);
	
				String previousTopLeftRadius     = ParamUtil.getString(actionRequest, "previousTopLeftRadius"+instanceId, ContentListUtil.PREVIOUSTOPLEFTRAD);
				String previousBottomLeftRadius  = ParamUtil.getString(actionRequest, "previousBottomLeftRadius"+instanceId, ContentListUtil.PREVIOUSBOTLEFTRAD);
				String previousTopRightRadius    = ParamUtil.getString(actionRequest, "previousTopRightRadius"+instanceId, ContentListUtil.PREVIOUSTOPRGHTRAD);
				String previousBottomRightRadius = ParamUtil.getString(actionRequest, "previousBottomRightRadius"+instanceId, ContentListUtil.PREVIOUSBOTRGHTRAD);	
	
				String nextTopLeftRadius     = ParamUtil.getString(actionRequest, "nextTopLeftRadius"+instanceId, ContentListUtil.NEXTTOPLEFTRAD);
				String nextBottomLeftRadius  = ParamUtil.getString(actionRequest, "nextBottomLeftRadius"+instanceId, ContentListUtil.NEXTBOTLEFTRAD);
				String nextTopRightRadius    = ParamUtil.getString(actionRequest, "nextTopRightRadius"+instanceId, ContentListUtil.NEXTTOPRGHTRAD);
				String nextBottomRightRadius = ParamUtil.getString(actionRequest, "nextBottomRightRadius"+instanceId, ContentListUtil.NEXTBOTRGHTRAD);	
	
				String paginationWidth = ParamUtil.getString(actionRequest, "paginationWidth"+instanceId, ContentListUtil.PAGINATIONWIDTH);
				String paginationHeight = ParamUtil.getString(actionRequest, "paginationHeight"+instanceId, ContentListUtil.PAGINATIONHEIGHT);
				String paginationOffset = ParamUtil.getString(actionRequest, "paginationOffset"+instanceId, ContentListUtil.PAGINATIONOFFSET);
	
				String portletBorderColorTop = ParamUtil.getString(actionRequest, "portletBorderColorTop"+instanceId, ContentListUtil.PORTLETBORDERCOLORTOP);
				String portletBorderColorRight = ParamUtil.getString(actionRequest, "portletBorderColorRight"+instanceId, ContentListUtil.PORTLETBORDERCOLORRIGHT);
				String portletBorderColorBottom = ParamUtil.getString(actionRequest, "portletBorderColorBottom"+instanceId, ContentListUtil.PORTLETBORDERCOLORBOTTOM);
				String portletBorderColorLeft = ParamUtil.getString(actionRequest, "portletBorderColorLeft"+instanceId, ContentListUtil.PORTLETBORDERCOLORLEFT);			
				String portletBorderPixelTop = ParamUtil.getString(actionRequest, "portletBorderPixelTop"+instanceId, ContentListUtil.PORTLETBORDERPIXELTOP);
				String portletBorderPixelRight = ParamUtil.getString(actionRequest, "portletBorderPixelRight"+instanceId, ContentListUtil.PORTLETBORDERPIXELRIGHT);
				String portletBorderPixelBottom = ParamUtil.getString(actionRequest, "portletBorderPixelBottom"+instanceId, ContentListUtil.PORTLETBORDERPIXELBOTTOM);
				String portletBorderPixelLeft = ParamUtil.getString(actionRequest, "portletBorderPixelLeft"+instanceId, ContentListUtil.PORTLETBORDERPIXELLEFT);						
				String portletBackgroundColor = ParamUtil.getString(actionRequest, "portletBackgroundColor"+instanceId, ContentListUtil.PORTLETBACKGROUNDCOLOR);
				String portletTopLeftRadius = ParamUtil.getString(actionRequest, "portletTopLeftRadius"+instanceId, ContentListUtil.PORTLETTOPLEFTRADIUS);
				String portletBottomLeftRadius = ParamUtil.getString(actionRequest, "portletBottomLeftRadius"+instanceId, ContentListUtil.PORTLETBOTTOMLEFTRADIUS);
				String portletTopRightRadius = ParamUtil.getString(actionRequest, "portletTopRightRadius"+instanceId, ContentListUtil.PORTLETTOPRIGHTRADIUS);
				String portletBottomRightRadius = ParamUtil.getString(actionRequest, "portletBottomRightRadius"+instanceId, ContentListUtil.PORTLETBOTTOMRIGHTRADIUS);
				
				String portletContainerHeight = ParamUtil.getString(actionRequest, "portletContainerHeight"+instanceId, ContentListUtil.PORTLETCONTAINERHEIGHT);
				String portletContainerItemCSS = ParamUtil.getString(actionRequest, "portletContainerItemCSS"+instanceId, ContentListUtil.PORTLETCONTAINERITEMCSS);
	
				String pageMode = ParamUtil.getString(actionRequest, "pageMode"+instanceId, ContentListUtil.PAGEMODE);
				String pageModeTarget = ParamUtil.getString(actionRequest, "pageModeTarget"+instanceId, ContentListUtil.PAGEMODETARGET);
				String urlTargetName = ParamUtil.getString(actionRequest, "urlTargetName"+instanceId, ContentListUtil.URLTARGETNAME);
				String channelVocabularyName = ParamUtil.getString(actionRequest, "channelVocabularyName"+instanceId, ContentListUtil.CHANNELVOCABULARYNAME);
				String contentTypeVocabularyName = ParamUtil.getString(actionRequest, "contentTypeVocabularyName"+instanceId, ContentListUtil.CONTENTTYPEVOCABULARYNAME);
				
				
				String subCategoriesVocabularyName = ParamUtil.getString(actionRequest, "subCategoriesVocabularyName"+instanceId, ContentListUtil.SUBCATEGORIESVOCABULARYNAME);
				
				
				
				String restAPI = ParamUtil.getString(actionRequest, "restAPI"+instanceId, ContentListUtil.RESTAPI);
				String propertiesFile = ParamUtil.getString(actionRequest, "propertiesFile"+instanceId, ContentListUtil.PROPERTIESFILE);
				String publicServletMapping = ParamUtil.getString(actionRequest, "publicServletMapping"+instanceId, ContentListUtil.PUBLICSERVLETMAPPING);
				
				String articleImagePath = ParamUtil.getString(actionRequest, "articleImagePath"+instanceId, ContentListUtil.ARTICLEIMAGEPATH);
				String blogImagePath = ParamUtil.getString(actionRequest, "blogImagePath"+instanceId, ContentListUtil.BLOGIMAGEPATH);
				String supplement = ParamUtil.getString(actionRequest, "supplement"+instanceId, ContentListUtil.SUPPLEMENT);
				
				String defaultImagePath = ParamUtil.getString(actionRequest, "defaultImagePath"+instanceId, ContentListUtil.DEFAULTIMAGEPATH);
				String displayDateFormat = ParamUtil.getString(actionRequest, "displayDateFormat"+instanceId, ContentListUtil.DISPLAYDATEFORMAT);
												
				String showIntro = ParamUtil.getString(actionRequest, "showIntro"+instanceId, ContentListUtil.SHOWINTRO);
	
				PortletPreferences prefs = actionRequest.getPreferences();
				prefs.setValue("portletMode", portletMode);
				prefs.setValue("modifiedByUserId", modifiedByUserId);				
				
				prefs.setValue("cssBlock", cssBlock);
				prefs.setValue("htmlBlock", htmlBlock);
				
				prefs.setValue("contentListData", contentListData);
				prefs.setValue("uiLayout", uiLayout);
				prefs.setValue("perPage", perPage);
				prefs.setValue("pagination", pagination);
				prefs.setValue("titleVisible", titleVisible);
				prefs.setValue("titleOfList", titleOfList);
				prefs.setValue("titleTopMargin", titleTopMargin);
				prefs.setValue("titleBottomMargin", titleBottomMargin);
				prefs.setValue("titleColor", titleColor);
				prefs.setValue("titleFont", titleFont);
				prefs.setValue("filtering", filtering);
				prefs.setValue("scrollDuration", scrollDuration);
				prefs.setValue("initialChunk", initialChunk);
				prefs.setValue("asyncChunk", asyncChunk);
				prefs.setValue("recordsToPull", recordsToPull);
				prefs.setValue("nextText", nextText);
				prefs.setValue("prevText", prevText);	
				prefs.setValue("searchInputText", searchInputText);
	
				prefs.setValue("titleBorderColor", titleBorderColor);
				prefs.setValue("titleBackgroundColor", titleBackgroundColor);
	
				prefs.setValue("titleTopLeftRadius", titleTopLeftRadius);
				prefs.setValue("titleBottomLeftRadius", titleBottomLeftRadius);			
				prefs.setValue("titleTopRightRadius", titleTopRightRadius);
				prefs.setValue("titleBottomRightRadius", titleBottomRightRadius);
	
				prefs.setValue("paginationBorderColor", paginationBorderColor);
				prefs.setValue("paginationBackgroundColor", paginationBackgroundColor);
	
				prefs.setValue("previousTopLeftRadius", previousTopLeftRadius);
				prefs.setValue("previousBottomLeftRadius", previousBottomLeftRadius);			
				prefs.setValue("previousTopRightRadius", previousTopRightRadius);
				prefs.setValue("previousBottomRightRadius", previousBottomRightRadius);
	
				prefs.setValue("nextTopLeftRadius", nextTopLeftRadius);
				prefs.setValue("nextBottomLeftRadius", nextBottomLeftRadius);			
				prefs.setValue("nextTopRightRadius", nextTopRightRadius);
				prefs.setValue("nextBottomRightRadius", nextBottomRightRadius);
	
				prefs.setValue("paginationWidth", paginationWidth);
				prefs.setValue("paginationHeight", paginationHeight);
				prefs.setValue("paginationOffset", paginationOffset);
	
				prefs.setValue("portletBorderColorTop", portletBorderColorTop);
				prefs.setValue("portletBorderColorRight", portletBorderColorRight);
				prefs.setValue("portletBorderColorBottom", portletBorderColorBottom);
				prefs.setValue("portletBorderColorLeft", portletBorderColorLeft);			
				prefs.setValue("portletBorderPixelTop", portletBorderPixelTop);
				prefs.setValue("portletBorderPixelRight", portletBorderPixelRight);
				prefs.setValue("portletBorderPixelBottom", portletBorderPixelBottom);
				prefs.setValue("portletBorderPixelLeft", portletBorderPixelLeft);			
				prefs.setValue("portletBackgroundColor", portletBackgroundColor);
				prefs.setValue("portletTopLeftRadius", portletTopLeftRadius);
				prefs.setValue("portletBottomLeftRadius", portletBottomLeftRadius);
				prefs.setValue("portletTopRightRadius", portletTopRightRadius);
				prefs.setValue("portletBottomRightRadius", portletBottomRightRadius);
				
				prefs.setValue("portletContainerHeight", portletContainerHeight);
				prefs.setValue("portletContainerItemCSS", portletContainerItemCSS);
	
				prefs.setValue("pageMode", pageMode);
				prefs.setValue("pageModeTarget", pageModeTarget);
				prefs.setValue("urlTargetName", urlTargetName);
				prefs.setValue("channelVocabularyName", channelVocabularyName);
				prefs.setValue("contentTypeVocabularyName", contentTypeVocabularyName);
				
				
				
				prefs.setValue("subCategoriesVocabularyName", subCategoriesVocabularyName);
				
				
				
				prefs.setValue("restAPI", restAPI);
				prefs.setValue("propertiesFile", propertiesFile);
				prefs.setValue("publicServletMapping", publicServletMapping);
				
				prefs.setValue("articleImagePath", articleImagePath);
				prefs.setValue("blogImagePath", blogImagePath);
				prefs.setValue("supplement", supplement);
				
				prefs.setValue("defaultImagePath", defaultImagePath);
				prefs.setValue("displayDateFormat", displayDateFormat);
				
			
				
				prefs.setValue("showIntro", showIntro.toUpperCase());
	
				prefs.store(); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void populateMaps(long globalGroupId, long groupId, String propertyFile, Map<String, GroupBean> communityMap, Map<String, CategoryBean> channelMap,
								Map<String, Map<String,CategoryBean>> vocabularyMap,Map<String, CategoryBean> subCategoriesMap) {

		try {

			// ------------------------------------------------------------------
			// Read this micro-site/site Properites map, stored within the Liferay
			// Document Library.  Populate the Community, Channel, and Vocabulary
			// Maps accordingly.
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
						String values = keyValuePair[1];
						
						// ------------------------------------------------------------------
						// Get our Communities for this micro-site/site
						// Ignore comment (#) and empty lines
						// ------------------------------------------------------------------
						if ( key.equalsIgnoreCase("communities") ) {
							
							String[] groupIdArray = values.trim().split(",",-1);							
							Long[] groupIdLongArray = new Long[groupIdArray.length];

							for ( int i = 0; i < groupIdArray.length; i++ ) {
								groupIdLongArray[i] = new Long(groupIdArray[i].trim());
							}
							
							DynamicQuery groupQuery = DynamicQueryFactoryUtil.forClass(Group.class, PortalClassLoaderUtil.getClassLoader())
															.add(PropertyFactoryUtil.forName("groupId").in(groupIdLongArray));					

							List<Group> groupList = GroupLocalServiceUtil.dynamicQuery(groupQuery);

							if ( null != groupList && groupList.size() > 0 ) {

								for ( Group group : groupList ) {					
									GroupBean gb = new GroupBean();
									gb.setFriendlyUrl( group.getFriendlyURL() );
									gb.setGroupId( group.getGroupId() );
									gb.setName( group.getName() );

									if ( group.getGroupId() == groupId ) {
										gb.setSelected("selected");
									}

									communityMap.put( group.getName(), gb );
								}
							}
							
						// ------------------------------------------------------------------
						// Get the Categories for the "Channel" Vocabulary
						// Ignore comment (#) and empty lines	
						// ------------------------------------------------------------------
						} else if ( key.equalsIgnoreCase("channels") ) {
							
							OrderByComparator obc = new EntryNameComparator(true);
							AssetVocabulary channelVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroupId, values.trim()) ; 														
							List<AssetCategory> categoryList = AssetCategoryLocalServiceUtil.getVocabularyCategories(channelVocabulary.getVocabularyId(), 
																				QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);
							
							if ( null != categoryList && categoryList.size() > 0 ) {
								
								for ( AssetCategory category : categoryList ) {									
									CategoryBean cb = new CategoryBean();
									cb.setCategoryId( category.getCategoryId() );
									cb.setName( category.getName() );									
									channelMap.put(category.getName(), cb);
								}								
							}
						
						// ------------------------------------------------------------------
						// Get the remaining Categories for the Content Type, Specific, Theme, 
						// and any other Vocabularies used in populating and filtering this list.
						// Ignore comment (#) and empty lines	
						// ------------------------------------------------------------------							
						} else if ( key.equalsIgnoreCase("vocabularies") ) {
							
							String[] vocabularyArray = values.trim().split(",",-1);
							
							if ( vocabularyArray.length > 0 ) {
								
								OrderByComparator obc = new EntryNameComparator(true);
								
								for ( int index = 0; index < vocabularyArray.length; index++ ) {
									
									String currentVocabularyName = vocabularyArray[index].trim();
									AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroupId, currentVocabularyName) ;
									List<AssetCategory> categoryList = AssetCategoryLocalServiceUtil.getVocabularyCategories(vocabulary.getVocabularyId(), 
																			QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);									
									
									if ( null != categoryList && categoryList.size() > 0 ) {
										
										Map<String, CategoryBean> currentVocabularyMap = new TreeMap<String, CategoryBean>();
										
										for ( AssetCategory category : categoryList ) {											
											CategoryBean cb = new CategoryBean();
											cb.setCategoryId( category.getCategoryId() );
											cb.setName( category.getName() );											
											currentVocabularyMap.put(category.getName(), cb);
										}	
										
										vocabularyMap.put(currentVocabularyName, currentVocabularyMap);
									}									
								}								
							}							
						}
					
					} // if ( !StringUtils.startsWith(line, "#") && !"".equals(line.trim()) ) {
				} // while ((line = br.readLine()) != null)   {	
				is.close();				
			} // if ( null != fileEntryList && fileEntryList.size() > 0 ) {	
			
			
			
			// ------------------------------------------------------------------
			// Get the Categories for the Sub Categories Vocabulary 
		
			// ------------------------------------------------------------------	
			
			OrderByComparator obc = new EntryNameComparator(true);
			AssetVocabulary channelVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(globalGroupId, ContentListUtil.SUBCATEGORIESVOCABULARYNAME) ; 														
			List<AssetCategory> categoryList = AssetCategoryLocalServiceUtil.getVocabularyCategories(channelVocabulary.getVocabularyId(), 
																QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);
			
			if ( null != categoryList && categoryList.size() > 0 ) {
				
				for ( AssetCategory category : categoryList ) {									
					CategoryBean cb = new CategoryBean();
					cb.setCategoryId( category.getCategoryId() );
					cb.setName( category.getName() );									
					subCategoriesMap.put(category.getName(), cb);
				}								
			}
			

			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}