package org.ieee.cnp.util;

import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.journal.model.JournalArticle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpSession;

import org.ieee.common.constants.GlobalConstants;
import org.ieee.common.json.JSONArray;
import org.ieee.common.json.JSONObject;
import org.ieee.common.util.ParamUtil;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;


public class ContentContainerUtil {
	
	public static final String ARTICLE = "ARTICLE";
	public static final String BLOG    = "BLOG";
	public static final String SUCCESS = "SUCCESS";
	public static final String LIFERAY = "LIFERAY";
	
	public static final String CONFIG = "CONFIG";
	public static final String MODE   = "DEACTIVATED";
	public static final String USERID = "";	

	public static final String TARGETNAME            = "ContentContainerPortlet";
	public static final String CONTAINERHEIGHTOFFSET = "0";
	public static final String PAGINATION            = "NO";
	public static final String SCROLLDURATION        = "500";
	public static final String RESTAPI               = "/portal/web/computingnow/rest/-/api/";
	public static final String COMMENTSYSTEM         = "ON";

	public static final String FRSTTEXT = "FIRST";
	public static final String PREVTEXT = "PREV";
	public static final String NEXTTEXT = "NEXT";
	public static final String LASTTEXT = "LAST";

	public static final String PAGINATIONBORDERCOLOR  = "CCCCCC";
	public static final String PAGINATIONBACKGRCOLOR  = "CCCCCC";
	
	public static final String FIRSTTOPLEFTRAD = "5";
	public static final String FIRSTBOTLEFTRAD = "5";
	public static final String FIRSTTOPRGHTRAD = "0";
	public static final String FIRSTBOTRGHTRAD = "0";
	
	public static final String PREVIOUSTOPLEFTRAD = "0";
	public static final String PREVIOUSBOTLEFTRAD = "0";
	public static final String PREVIOUSTOPRGHTRAD = "0";
	public static final String PREVIOUSBOTRGHTRAD = "0";
	
	public static final String NEXTTOPLEFTRAD = "0";
	public static final String NEXTBOTLEFTRAD = "0";
	public static final String NEXTTOPRGHTRAD = "0";
	public static final String NEXTBOTRGHTRAD = "0";
	
	public static final String LASTTOPLEFTRAD = "0";
	public static final String LASTBOTLEFTRAD = "0";
	public static final String LASTTOPRGHTRAD = "5";
	public static final String LASTBOTRGHTRAD = "5";
	
	public static final String PAGINATIONWIDTH  = "60";
	public static final String PAGINATIONHEIGHT = "22";
	public static final String PAGINATIONOFFSET = "6";
	
	public static final String PORTLETBORDERCOLORTOP    = "CCCCCC";
	public static final String PORTLETBORDERCOLORRIGHT  = "CCCCCC";
	public static final String PORTLETBORDERCOLORBOTTOM = "CCCCCC";
	public static final String PORTLETBORDERCOLORLEFT   = "CCCCCC";
	public static final String PORTLETBORDERPIXELTOP    = "1";
	public static final String PORTLETBORDERPIXELRIGHT  = "1";
	public static final String PORTLETBORDERPIXELBOTTOM = "1";
	public static final String PORTLETBORDERPIXELLEFT   = "1";	
	public static final String PORTLETBACKGROUNDCOLOR   = "FFFFFF";
	public static final String PORTLETTOPLEFTRADIUS     = "10";
	public static final String PORTLETBOTTOMLEFTRADIUS  = "10";
	public static final String PORTLETTOPRIGHTRADIUS    = "10";
	public static final String PORTLETBOTTOMRIGHTRADIUS = "10";	
	
	public static final String NUMBEREDBORDERCOLOR  = "CCCCCC";
	public static final String NUMBEREDBACKGRCOLOR  = "CCCCCC";
	public static final String NUMBEREDWIDTH  = "18";
	public static final String NUMBEREDHEIGHT = "22";
	public static final String NUMBEREDOFFSET = "6";
	public static final String NUMBEREDTOPLEFTRAD = "0";
	public static final String NUMBEREDBOTLEFTRAD = "0";
	public static final String NUMBEREDTOPRGHTRAD = "0";
	public static final String NUMBEREDBOTRGHTRAD = "0";	
	
	public static final String SHOWINTRO = "ON";
	
	
	public static void putPortletPreferencesIntoModel(PortletPreferences prefs, Map<String,Object> model) {
		
		try {

			model.put("portletMode", prefs.getValue("portletMode", MODE));
			model.put("modifiedByUserId", prefs.getValue("modifiedByUserId", USERID));
			
			model.put("targetName", prefs.getValue("targetName", TARGETNAME));
			model.put("containerHeightOffset", prefs.getValue("containerHeightOffset", CONTAINERHEIGHTOFFSET));
			model.put("pagination", prefs.getValue("pagination", PAGINATION));
			model.put("scrollDuration", prefs.getValue("scrollDuration", SCROLLDURATION));
			model.put("restAPI", prefs.getValue("restAPI", RESTAPI));
			model.put("commentSystem", prefs.getValue("commentSystem", COMMENTSYSTEM));

			model.put("frstText", prefs.getValue("frstText", FRSTTEXT));
			model.put("nextText", prefs.getValue("nextText", NEXTTEXT));
			model.put("prevText", prefs.getValue("prevText", PREVTEXT));
			model.put("lastText", prefs.getValue("lastText", LASTTEXT));

			model.put("paginationBorderColor", prefs.getValue("paginationBorderColor", PAGINATIONBORDERCOLOR));
			model.put("paginationBackgroundColor", prefs.getValue("paginationBackgroundColor", PAGINATIONBACKGRCOLOR));	
			
			model.put("firstTopLeftRadius", prefs.getValue("firstTopLeftRadius", FIRSTTOPLEFTRAD));
			model.put("firstBottomLeftRadius", prefs.getValue("firstBottomLeftRadius", FIRSTBOTLEFTRAD));
			model.put("firstTopRightRadius", prefs.getValue("firstTopRightRadius", FIRSTTOPRGHTRAD));
			model.put("firstBottomRightRadius", prefs.getValue("firstBottomRightRadius", FIRSTBOTRGHTRAD));
			
			model.put("previousTopLeftRadius", prefs.getValue("previousTopLeftRadius", PREVIOUSTOPLEFTRAD));
			model.put("previousBottomLeftRadius", prefs.getValue("previousBottomLeftRadius", PREVIOUSBOTLEFTRAD));
			model.put("previousTopRightRadius", prefs.getValue("previousTopRightRadius", PREVIOUSTOPRGHTRAD));
			model.put("previousBottomRightRadius", prefs.getValue("previousBottomRightRadius", PREVIOUSBOTRGHTRAD));
			
			model.put("nextTopLeftRadius", prefs.getValue("nextTopLeftRadius", NEXTTOPLEFTRAD));
			model.put("nextBottomLeftRadius", prefs.getValue("nextBottomLeftRadius", NEXTBOTLEFTRAD));
			model.put("nextTopRightRadius", prefs.getValue("nextTopRightRadius", NEXTTOPRGHTRAD));
			model.put("nextBottomRightRadius", prefs.getValue("nextBottomRightRadius", NEXTBOTRGHTRAD));
			
			model.put("lastTopLeftRadius", prefs.getValue("lastTopLeftRadius", LASTTOPLEFTRAD));
			model.put("lastBottomLeftRadius", prefs.getValue("lastBottomLeftRadius", LASTBOTLEFTRAD));
			model.put("lastTopRightRadius", prefs.getValue("lastTopRightRadius", LASTTOPRGHTRAD));
			model.put("lastBottomRightRadius", prefs.getValue("lastBottomRightRadius", LASTBOTRGHTRAD));

			model.put("paginationWidth", prefs.getValue("paginationWidth", PAGINATIONWIDTH));
			model.put("paginationHeight", prefs.getValue("paginationHeight", PAGINATIONHEIGHT));
			model.put("paginationOffset", prefs.getValue("paginationOffset", PAGINATIONOFFSET));
			
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
			
			model.put("numberedBorderColor", prefs.getValue("numberedBorderColor", NUMBEREDBORDERCOLOR));
			model.put("numberedBackgroundColor", prefs.getValue("numberedBackgroundColor", NUMBEREDBACKGRCOLOR));	
			model.put("numberedWidth", prefs.getValue("numberedWidth", NUMBEREDWIDTH));
			model.put("numberedHeight", prefs.getValue("numberedHeight", NUMBEREDHEIGHT));
			model.put("numberedOffset", prefs.getValue("numberedOffset", NUMBEREDOFFSET));
			model.put("numberedTopLeftRadius", prefs.getValue("numberedTopLeftRadius", NUMBEREDTOPLEFTRAD));
			model.put("numberedBottomLeftRadius", prefs.getValue("numberedBottomLeftRadius", NUMBEREDBOTLEFTRAD));
			model.put("numberedTopRightRadius", prefs.getValue("numberedTopRightRadius", NUMBEREDTOPRGHTRAD));
			model.put("numberedBottomRightRadius", prefs.getValue("numberedBottomRightRadius", NUMBEREDBOTRGHTRAD));
			
			model.put("showIntro", prefs.getValue("showIntro", SHOWINTRO));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static String getArticleContent(JournalArticle article) {
		
		String content = "";
		
		try {
			
			// Retrieve the Article					
			SAXBuilder parser = new SAXBuilder();
			Document document = parser.build(new StringReader(article.getContent()));
			XPath contentXPath = XPath.newInstance("/root/static-content");
			List contentList = contentXPath.selectNodes(document);					

			if ((null != contentList) && (contentList.size() > 0)) {
				Element contentElement = (Element)contentList.get(0);
				content = contentElement.getText();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return content;
	}
	
	public static String connectWithAPI(String connectionToken) {

		String result = "";

		try {

			URL url = new URL("https://computersociety.api.oneall.com/connections/" + connectionToken + ".json");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			String userpassword = "e3e07b9b-c1fb-4f6e-971f-63ee18a75027:1bbd70de-2723-4441-981b-4d6471f10ad6";
			String encodedAuthorization = Base64.encode( userpassword.getBytes() );
			connection.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
			connection.setDoOutput(true);
			connection.setReadTimeout(10000);
			connection.connect();
			connection.getInputStream();

			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;

			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}

			result = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static JSONObject resultToJSON(String result) {

		JSONObject jsonObject = new JSONObject();

		try {			
			jsonObject = new JSONObject(result);			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	public static String getStatus(JSONObject apiResult) {

		String apiStatus = "unknown";

		try {

			JSONObject response = apiResult.getJSONObject("response");
			JSONObject request = response.getJSONObject("request");				
			JSONObject status = request.getJSONObject("status");
			apiStatus = status.getString("flag");

		} catch(Exception e) {
			e.printStackTrace();
		}

		return apiStatus;
	}
	
	public static String getFullName(JSONObject apiResult) {

		String fullName = "";

		try {

			JSONObject response = apiResult.getJSONObject("response");
			JSONObject result = response.getJSONObject("result");				
			JSONObject data = result.getJSONObject("data");
			JSONObject user = data.getJSONObject("user");
			JSONObject identity = user.getJSONObject("identity");
			JSONObject name = identity.getJSONObject("name");
			fullName = name.getString("formatted");
			
			if ( null == fullName || "".equals(fullName.trim()) ) {
				fullName = identity.getString("displayName");
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return fullName;
	}	
	
	public static String getProvider(JSONObject apiResult) {

		String provider = "";

		try {

			JSONObject response = apiResult.getJSONObject("response");
			JSONObject result = response.getJSONObject("result");				
			JSONObject data = result.getJSONObject("data");
			JSONObject user = data.getJSONObject("user");
			JSONObject identity = user.getJSONObject("identity");
			provider = identity.getString("provider");
			provider = provider.toUpperCase();

		} catch(Exception e) {
			e.printStackTrace();
		}

		return provider;
	}	
	
	public static String getEmailAddress(JSONObject apiResult) {

		String emailAddress = "";

		try {

			JSONObject response = apiResult.getJSONObject("response");
			JSONObject result = response.getJSONObject("result");				
			JSONObject data = result.getJSONObject("data");
			JSONObject user = data.getJSONObject("user");
			JSONObject identity = user.getJSONObject("identity");
			JSONArray emails = identity.getJSONArray("emails");
			
			if ( null != emails && emails.length() > 0 ) {				
				JSONObject emailAddressObject = emails.getJSONObject(0);
				emailAddress = emailAddressObject.getString("value");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return emailAddress;
	}	
	
	public static Map<String,Object> populateSessionMap(RenderRequest renderRequest, ThemeDisplay themeDisplay) {
		
		Map<String,Object> smap = null;
		
		try {

			String connectionToken = ParamUtil.getStringParameter(renderRequest, "connection_token");			
			
			// Has the user been verified via a Social Signin?
			if ( !"".equals(connectionToken) ) {

				String apiResult = connectWithAPI(connectionToken);
				JSONObject apiJSON = resultToJSON(apiResult);

				if ( null != apiJSON ) {

					String status = getStatus(apiJSON);

					if ( status.equalsIgnoreCase(SUCCESS) ) {

						String fullName = getFullName(apiJSON);
						String provider = getProvider(apiJSON);	
						String emailAddress = getEmailAddress(apiJSON);
						
						// Does a SessionBean already exist for this User?
						// Perhaps they either logged in via Liferay, or want to log in via another Social service.
						// If not, create a new Session Map and populate.
						// If so, overwrite the existing Session Map with the new Social data.

						if ( null == smap ) {						
							smap = new HashMap<String,Object>();
							smap.put(GlobalConstants.FULLNAME, fullName);
							smap.put(GlobalConstants.EMAILADDRESS, emailAddress);
							smap.put(GlobalConstants.AUTHFROM, provider);
							smap.put(GlobalConstants.SIGNEDINSOCIAL, new Boolean(true));
							smap.put(GlobalConstants.SIGNEDINLIFERAY, new Boolean(false));
							HttpSession httpSession = PortalUtil.getHttpServletRequest(renderRequest).getSession();
							httpSession.setAttribute(GlobalConstants.SESSIONMAP, smap);						
						} else {						
							smap.put(GlobalConstants.FULLNAME, fullName);
							smap.put(GlobalConstants.EMAILADDRESS, emailAddress);
							smap.put(GlobalConstants.AUTHFROM, provider);
							smap.put(GlobalConstants.SIGNEDINSOCIAL, new Boolean(true));
							smap.put(GlobalConstants.SIGNEDINLIFERAY, new Boolean(false));
						}		
					} // if ( status.equalsIgnoreCase("SUCCESS") ) {
				} // if ( null != apiJSON ) {
			} // if ( !"".equals(connectionToken) ) {			
			
			
			// Has the user logged in via Computer.org (Liferay)?
			// We'll also override some settings that might've been set with the Social Signin, as we'll assume the Liferay account is more accurate.
			if ( themeDisplay.isSignedIn() ) {
				
				if ( null == smap ) {						
					smap = new HashMap<String,Object>();
					smap.put(GlobalConstants.FULLNAME, themeDisplay.getUser().getFullName());
					smap.put(GlobalConstants.EMAILADDRESS, themeDisplay.getUser().getEmailAddress());
					smap.put(GlobalConstants.AUTHFROM, LIFERAY);
					smap.put(GlobalConstants.SIGNEDINSOCIAL, new Boolean(false));
					smap.put(GlobalConstants.SIGNEDINLIFERAY, new Boolean(true));
					HttpSession httpSession = PortalUtil.getHttpServletRequest(renderRequest).getSession();
					httpSession.setAttribute(GlobalConstants.SESSIONMAP, smap);						
				} else {						
					smap.put(GlobalConstants.FULLNAME, themeDisplay.getUser().getFullName());
					smap.put(GlobalConstants.EMAILADDRESS, themeDisplay.getUser().getEmailAddress());
					smap.put(GlobalConstants.AUTHFROM, LIFERAY);
					smap.put(GlobalConstants.SIGNEDINSOCIAL, new Boolean(false));
					smap.put(GlobalConstants.SIGNEDINLIFERAY, new Boolean(true));
				}				
			
			} // if ( themeDisplay.isSignedIn() ) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return smap;
	}	
	
}