package org.ieeecs.communities.presentation.controller;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.WebKeys;

import com.liferay.portal.theme.ThemeDisplay;

import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;

import java.util.*;

import javax.portlet.*;

import org.ieeecs.communities.util.CommunityPollUtil;
import org.ieee.common.constants.GlobalConstants;
import org.ieee.common.presentation.controller.BaseController;

import org.springframework.web.portlet.ModelAndView;


public class ConfigureCommunityPollController extends BaseController {

	@SuppressWarnings("unchecked")
	protected ModelAndView handleRenderRequestInternal(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

		Map<String,Object> model = new HashMap<String,Object>();
		String instanceId = "";
		Map<String,String> entryMap = new HashMap<String,String>();

		try {

			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			PortletPreferences prefs = renderRequest.getPreferences();
			CommunityPollUtil.putPortletPreferencesIntoModel(prefs, model);
			instanceId = "_" + themeDisplay.getPortletDisplay().getInstanceId();
			
			// Get a list of the web content entries that can be displayed by this portlet.
			// Only a select few, (default "Display" category type), will be allowed to be viewable in this portlet.
			long[] classNames = GlobalConstants.getClassNameIds(new Integer("1"));
			long[] groupIds = {new Long( themeDisplay.getScopeGroupId() )};
			long[] categoryIds = new long[0];
			
			// Get all the entries for a particular Category, (default is "Display").
			String defaultCategoryName = prefs.getValue("defaultCategoryName", CommunityPollUtil.DEFAULTCATEGORYNAME);
			DynamicQuery categoryQuery = DynamicQueryFactoryUtil.forClass(AssetCategory.class, PortalClassLoaderUtil.getClassLoader())
																.add(PropertyFactoryUtil.forName("groupId").eq( themeDisplay.getCompanyGroupId() ))
																.add(PropertyFactoryUtil.forName("name").eq( defaultCategoryName ));			
			
			List<AssetCategory> assetCategoryList = AssetCategoryLocalServiceUtil.dynamicQuery(categoryQuery);
			
			if ( null != assetCategoryList && assetCategoryList.size() > 0 ) {	
				categoryIds = new long[1];
				AssetCategory ac = assetCategoryList.get(0);
				categoryIds[0] = ac.getCategoryId(); 
			}
			
			// Build the Asset Query
			AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
			assetEntryQuery.setGroupIds( groupIds );
			assetEntryQuery.setClassNameIds( classNames );	
			assetEntryQuery.setAllCategoryIds( categoryIds );
			
			List<AssetEntry> entryList = new ArrayList<AssetEntry>();
			entryList = AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);	
			
			// Add the AssetEntry "classPK", (which is the JournalArticle "resourcePrimKey" value), and the Title to the "entryMap".
			if ( null != entryList && entryList.size() > 0 ) {
				for ( int index = 0; index < entryList.size(); index++ ) {					
					AssetEntry ae = entryList.get(index);
					entryMap.put(new Long(ae.getClassPK()).toString(), ae.getTitle());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.put("id", instanceId);
		model.put("entryMap", entryMap);
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

			if ( source.equalsIgnoreCase(CommunityPollUtil.CONFIG) ) {

				String portletMode = ParamUtil.getString(actionRequest, "portletMode"+instanceId, CommunityPollUtil.MODE);
				String modifiedByUserId = new Long(themeDisplay.getUserId()).toString();

				String portletBorderColorTop = ParamUtil.getString(actionRequest, "portletBorderColorTop"+instanceId, CommunityPollUtil.PORTLETBORDERCOLORTOP);
				String portletBorderColorRight = ParamUtil.getString(actionRequest, "portletBorderColorRight"+instanceId, CommunityPollUtil.PORTLETBORDERCOLORRIGHT);
				String portletBorderColorBottom = ParamUtil.getString(actionRequest, "portletBorderColorBottom"+instanceId, CommunityPollUtil.PORTLETBORDERCOLORBOTTOM);
				String portletBorderColorLeft = ParamUtil.getString(actionRequest, "portletBorderColorLeft"+instanceId, CommunityPollUtil.PORTLETBORDERCOLORLEFT);
				String portletBorderPixelTop = ParamUtil.getString(actionRequest, "portletBorderPixelTop"+instanceId, CommunityPollUtil.PORTLETBORDERPIXELTOP);
				String portletBorderPixelRight = ParamUtil.getString(actionRequest, "portletBorderPixelRight"+instanceId, CommunityPollUtil.PORTLETBORDERPIXELRIGHT);
				String portletBorderPixelBottom = ParamUtil.getString(actionRequest, "portletBorderPixelBottom"+instanceId, CommunityPollUtil.PORTLETBORDERPIXELBOTTOM);
				String portletBorderPixelLeft = ParamUtil.getString(actionRequest, "portletBorderPixelLeft"+instanceId, CommunityPollUtil.PORTLETBORDERPIXELLEFT);
				String portletBackgroundColor = ParamUtil.getString(actionRequest, "portletBackgroundColor"+instanceId, CommunityPollUtil.PORTLETBACKGROUNDCOLOR);
				String portletTopLeftRadius = ParamUtil.getString(actionRequest, "portletTopLeftRadius"+instanceId, CommunityPollUtil.PORTLETTOPLEFTRADIUS);
				String portletBottomLeftRadius = ParamUtil.getString(actionRequest, "portletBottomLeftRadius"+instanceId, CommunityPollUtil.PORTLETBOTTOMLEFTRADIUS);
				String portletTopRightRadius = ParamUtil.getString(actionRequest, "portletTopRightRadius"+instanceId, CommunityPollUtil.PORTLETTOPRIGHTRADIUS);
				String portletBottomRightRadius = ParamUtil.getString(actionRequest, "portletBottomRightRadius"+instanceId, CommunityPollUtil.PORTLETBOTTOMRIGHTRADIUS);
				
				String innerMargins = ParamUtil.getString(actionRequest, "innerMargins"+instanceId, CommunityPollUtil.INNERMARGINS);
				String restAPI = ParamUtil.getString(actionRequest, "restAPI"+instanceId, CommunityPollUtil.RESTAPI);
				String defaultCategoryName = ParamUtil.getString(actionRequest, "defaultCategoryName"+instanceId, CommunityPollUtil.DEFAULTCATEGORYNAME);
				String resourcePrimKey = ParamUtil.getString(actionRequest, "resourcePrimKey"+instanceId, CommunityPollUtil.RESOURCEPRIMKEY);

				PortletPreferences prefs = actionRequest.getPreferences();
				prefs.setValue("portletMode", portletMode);
				prefs.setValue("modifiedByUserId", modifiedByUserId);

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
				
				prefs.setValue("innerMargins", innerMargins);
				prefs.setValue("restAPI", restAPI);
				prefs.setValue("defaultCategoryName", defaultCategoryName);
				prefs.setValue("resourcePrimKey", resourcePrimKey);

				prefs.store();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
