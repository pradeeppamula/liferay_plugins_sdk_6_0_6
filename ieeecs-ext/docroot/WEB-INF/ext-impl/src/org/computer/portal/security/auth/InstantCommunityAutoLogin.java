package org.computer.portal.security.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.security.auth.AutoLogin;
import com.liferay.portal.security.auth.AutoLoginException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.CookieKeys;
import com.liferay.portal.util.PortalUtil;

public class InstantCommunityAutoLogin implements AutoLogin {

	boolean enabled = "true".equalsIgnoreCase(System.getProperty("instant-communities", "false"));
	public String[] login(HttpServletRequest request,HttpServletResponse response) throws AutoLoginException {
		String[] credentials = null;
		if(_log.isDebugEnabled()){System.out.println("enabled is "+enabled);}
		if(enabled)
		{
			credentials = getCredentials(request, response);			
		}
		if(_log.isDebugEnabled()){System.out.println("enabled is "+credentials);}
		return credentials;
	}
	
	private String[] getCredentials(HttpServletRequest request,HttpServletResponse response) throws AutoLoginException 
	{
		try {
			String[] credentials = null;
			
			
			Company company = PortalUtil.getCompany(request);
			String autoUserId = CookieKeys.getCookie(request, CookieKeys.ID);
			String autoPassword = CookieKeys.getCookie(	request, CookieKeys.PASSWORD);
			/*String ieeeUser = CookieKeys.getCookie(request,"UserLogging2");*/
			if (Validator.isNotNull(autoUserId) &&
				Validator.isNotNull(autoPassword)/* &&
				Validator.isNotNull(userLogging2)*/) {


				KeyValuePair kvp = null;

				if (company.isAutoLogin()) {
					kvp = UserLocalServiceUtil.decryptUserId(
						company.getCompanyId(), autoUserId, autoPassword);

					credentials = new String[3];

					credentials[0] = kvp.getKey();
					credentials[1] = kvp.getValue();
					credentials[2] = Boolean.FALSE.toString();
				}
			}

			return credentials;
		}
		catch (Exception e) {
			e.printStackTrace();
			_log.warn(e, e);
			throw new AutoLoginException(e);
		}
		

	}
	
	

	private static Log _log = LogFactoryUtil.getLog(InstantCommunityAutoLogin.class);
	
}