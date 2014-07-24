package org.computer.portal.security.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class CredentialsFactory {
	
	public static String COOKIE_NAME = "IEEECS_CRED";
	
	public static String[] getCredentials(HttpServletRequest request) {
		return getCredentials(request, COOKIE_NAME);
	}
	
	public static String[] getCredentials(HttpServletRequest request, String cookieName) {
		if ((cookieName == null) || (cookieName.trim().length() < 1)) return null;
		Cookie cookies[] = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookieName.equals(cookies[i].getName())) {
				return getCredentials(cookies[i]);
			}
		}
		if (_log.isDebugEnabled()) {
			_log.debug("Cookie '" + cookieName + "' was not found");
		}
		return null;
	}
	
	public static String[] getCredentials(Cookie cookie) {
		if (cookie == null) return null;
		String[] credentials = fromCookieValue(cookie.getValue());
		if (credentials == null)
			_log.debug("Credentials were NOT found");
		else
			_log.debug("Credentials found:\n" + credentials[0] + "\n"+ credentials[1] + "\n"+ credentials[2]); 
		return credentials;
	}
	
	public static void addCredentials(HttpServletResponse response, String[] credentials) {
		addCredentials(response, COOKIE_NAME, credentials);
	}
	
	public static void addCredentials(HttpServletResponse response, String cookieName, String[] credentials) {
		if ((credentials != null) && (credentials.length == 3) && (credentials[0].trim().length() > 0)) {
//			if (_log.isDebugEnabled())
//				_log.debug("Add cookie '" + cookieName + "' with value '" + toCookieValue(credentials) + "' to the response");
			Cookie cookie = new Cookie(cookieName, toCookieValue(credentials));
			cookie.setDomain("computer.org");
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}
	
	public static void deleteCredentials(HttpServletResponse response) {
		deleteCredentials(response, COOKIE_NAME);
	}
	
	public static void deleteCredentials(HttpServletResponse response, String cookieName) {
		_log.debug("Delete the cookie " + cookieName + "'");
		Cookie cookie = new Cookie(cookieName, "");
		cookie.setDomain("computer.org");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	private static String toCookieValue(String[] credentials) {
		if ((credentials == null) || (credentials.length != 3)) return "";
		String newVal = credentials[0] + ":" + credentials[1] + ":" + credentials[2];
//		_log.debug("toCookieString = '" + newVal + "'");
		return newVal;
	}
	
	private static String[] fromCookieValue(String value) {
		_log.debug("Split '" + value + "'");
		String[] values = value.split(":");
		if (_log.isDebugEnabled()) {
			if ((values == null) || (values.length < 1)) 
				_log.debug("Unable to split string");
			else 
				for (int i = 0; i < values.length; i++)
					_log.debug(i + " = '" + values[i] + "'");
		}
		return values;
	}
	
	private static Log _log = LogFactoryUtil.getLog(CredentialsFactory.class);
}