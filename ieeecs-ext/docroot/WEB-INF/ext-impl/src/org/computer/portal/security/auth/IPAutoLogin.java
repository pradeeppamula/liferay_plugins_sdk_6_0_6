/**
 * 
 */
package org.computer.portal.security.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.computer.accessLogger.UserData;
import org.computer.authentication.AuthenticatedUser;
import org.computer.authentication.Authenticator;
import org.computer.authentication.IPAddress;
import org.computer.utils.Base64;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AutoLogin;
import com.liferay.portal.security.auth.AutoLoginException;
import com.liferay.portal.util.PortalUtil;

/**
 * @author dev
 *
 */
public class IPAutoLogin implements AutoLogin {

	public String[] login(HttpServletRequest request,
			HttpServletResponse response) throws AutoLoginException {
		
		String[] credentials = null;
		HttpSession ses = request.getSession();
		
		long companyId = 0;
		try {
			companyId = PortalUtil.getCompanyId(request);
		} catch (Exception e) {
			_log.debug(e, e);
		}
		long userId = 0;
		try {
			userId = PortalUtil.getUserId(request);
		} catch (Exception e) {
			_log.debug(e, e);
		}
		User user = null;
		try {
			user = PortalUtil.getUser(request);
		} catch (Exception e) {
			_log.debug(e, e);
		}
		_log.debug("companyId = " + companyId);
		_log.debug("userId = " + userId);
		_log.debug("user = " + user);
		
		IPAddress ipAddress = new IPAddress(request);
		
		AuthenticatedUser authenticatedUser = null;
		
		try {
			if (user != null) {
				_log.debug("Create the authenticated user with screenName '" + user.getScreenName() + "'");
				authenticatedUser = Authenticator.authenticatedUser(ipAddress, user.getScreenName());
			}
			else {
				_log.debug("Create the authenticated user using just the IP address " + ipAddress.toString());
				authenticatedUser = Authenticator.authenticate(ipAddress);
			}

			_log.debug("Save the authenticatedUser for " + (user != null? user.getScreenName() : "") + ": " + authenticatedUser.getIPAddress().toString() + " in the session");
			ses.setAttribute("USER_" + AuthenticatedUser.USER, authenticatedUser);
			ses.setAttribute(AuthenticatedUser.USER, authenticatedUser);
			ses.setAttribute("USER_ENC_" + AuthenticatedUser.USER, Base64.encodeObject(authenticatedUser));
			
			// Create user data for logging
			UserData userData = new UserData();
			userData.setUserName(authenticatedUser.getName());
			userData.setIpAddress(authenticatedUser.getIPAddress());
			userData.setInstitution(authenticatedUser.getInstitutionName());
			userData.setReferrer(request.getHeader("referer"));
			userData.setUserAgent(request.getHeader("user-agent"));
			userData.setLoggedIn(user != null);
			
			_log.debug("Save UserData for " + userData.getUserName() + " in the session");
			ses.setAttribute("USER_loggingData", Base64.encodeObject(userData));
			
			userData.addToResponse(response, "computer.org");
		} catch (Exception e) {
			_log.error(e, e);
		}
		
//		try {
//			long companyId = PortalUtil.getCompanyId(request);
//			_log.error("Request parameters:");
//			Enumeration<String> names = request.getParameterNames();
//			while (names.hasMoreElements()) {
//				String name = names.nextElement();
//				_log.error(name + " = " + request.getParameter(name));
//			}
//		} catch (Exception e) {
//			_log.error(e, e);
//		}
		
		return credentials;
	}
	
	private static Log _log = LogFactoryUtil.getLog(IPAutoLogin.class);

}
