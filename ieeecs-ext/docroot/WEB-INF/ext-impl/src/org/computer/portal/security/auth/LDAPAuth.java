/**
 * 
 */
package org.computer.portal.security.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.computer.portal.model.ExtUser;
import org.computer.portal.service.ExtUserLocalServiceUtil;
import org.computer.utils.Constants;
import org.computer.utils.HealthCheckFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PasswordExpiredException;
import com.liferay.portal.PortalException;
import com.liferay.portal.UserLockoutException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthSettingsUtil;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.security.auth.LDAPAuthResult;
import com.liferay.portal.security.ldap.LDAPSettingsUtil;
import com.liferay.portal.security.ldap.PortalLDAPImporterUtil;
import com.liferay.portal.security.ldap.PortalLDAPUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.admin.util.OmniadminUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

/**
 * @author dev
 *
 */
public class LDAPAuth implements Authenticator {
	
	private static String authenticationServiceURL;
	public static final String AUTH_METHOD_BIND = "bind";

	public static final String AUTH_METHOD_PASSWORD_COMPARE =
		"password-compare";

	public static final String RESULT_PASSWORD_EXP_WARNING =
		"2.16.840.1.113730.3.4.5";

	public static final String RESULT_PASSWORD_RESET =
		"2.16.840.1.113730.3.4.4";

	/* (non-Javadoc)
	 * @see com.liferay.portal.security.auth.Authenticator#authenticateByEmailAddress(long, java.lang.String, java.lang.String, java.util.Map, java.util.Map)
	 */
	public int authenticateByEmailAddress(long companyId, String emailAddress,
			String password, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap) throws AuthException {

		try {
			return authenticate(
				companyId, emailAddress, StringPool.BLANK, 0, password, headerMap);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new AuthException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.liferay.portal.security.auth.Authenticator#authenticateByScreenName(long, java.lang.String, java.lang.String, java.util.Map, java.util.Map)
	 */
	public int authenticateByScreenName(long companyId, String screenName,
			String password, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap) throws AuthException {
		
		try {
			return authenticate(
				companyId, StringPool.BLANK, screenName, 0, password,  headerMap);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new AuthException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.liferay.portal.security.auth.Authenticator#authenticateByUserId(long, long, java.lang.String, java.util.Map, java.util.Map)
	 */
	public int authenticateByUserId(long companyId, long userId,
			String password, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap) throws AuthException {
		
		try {
			return authenticate(
				companyId, StringPool.BLANK, StringPool.BLANK, userId,
				password,  headerMap);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new AuthException(e);
		}
	}
	
	protected int authenticate(
			long companyId, String emailAddress, String screenName, long userId,
			String password, Map<String, String[]> headerMap)
		throws Exception {

		if (!AuthSettingsUtil.isLDAPAuthEnabled(companyId)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Authenticator is not enabled");
			}

			return SUCCESS;
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Authenticator is enabled");
		}

		long[] ldapServerIds = StringUtil.split(
			PrefsPropsUtil.getString(companyId, "ldap.server.ids"), 0L);

		for (long ldapServerId : ldapServerIds) {
			int result = authenticate(
				companyId, ldapServerId, emailAddress, screenName, userId,
				password, headerMap);

			if (result == SUCCESS) {
				return result;
			}
		}

		for (int ldapServerId = 0;; ldapServerId++) {
			String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

			String providerUrl = PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_BASE_PROVIDER_URL + postfix);

			if (Validator.isNull(providerUrl)) {
				break;
			}

			int result = authenticate(
				companyId, ldapServerId, emailAddress, screenName, userId,
				password, headerMap);

			if (result == SUCCESS) {
				return result;
			}
		}

		return authenticateRequired(
			companyId, userId, emailAddress, screenName, true, FAILURE);
	}

	protected int authenticate(
			long companyId, long ldapServerId, String emailAddress, String screenName, long userId,
			String password, Map<String, String[]> headerMap)
		throws Exception {

		// Assume all will work.  If a failure occurs we will set a failure then.
		HealthCheckFactory.getHealthCheck("authentication").setHealthy();

		
		
		String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);
		
		long startTime = 0;
		if (_log.isDebugEnabled()) {
			startTime = System.currentTimeMillis();
		}

		LdapContext ctx = PortalLDAPUtil.getContext(
			ldapServerId, companyId);
		
		if (_log.isDebugEnabled()) {
			_log.debug("LDAP Context for LDAP server " + ldapServerId);
		}


		if (ctx == null) {
			_log.error("ctx is NULL - url is " );
			return Authenticator.FAILURE;
		}

		//  Process LDAP auth search filter

//		String filter = PortalLDAPUtil.getAuthSearchFilter(
//			companyId, emailAddress, screenName, String.valueOf(userId));

		try {
			
			String baseDN = PrefsPropsUtil.getString(
					companyId, PropsKeys.LDAP_BASE_DN + postfix);

				//  Process LDAP auth search filter

				String filter = LDAPSettingsUtil.getAuthSearchFilter(
					ldapServerId, companyId, emailAddress, screenName,
					String.valueOf(userId));
			
			SearchControls cons = new SearchControls(
				SearchControls.SUBTREE_SCOPE, 1, 0, null, false, false);

			NamingEnumeration<SearchResult> enu = ctx.search(
				baseDN, filter, cons);
			
			if (_log.isDebugEnabled()) {
				long endTime = System.currentTimeMillis();
				_log.debug(
					"[ First LDAP Connection for user " + screenName + " ] "  +
							(endTime - startTime) + " ms");
			}

			if (enu.hasMoreElements()) {
				if (_log.isDebugEnabled()) {
					_log.debug("Search filter returned at least one result");
				}

				SearchResult result = enu.nextElement();

//				String fullUserDN = PortalLDAPUtil.getNameInNamespace(
//					companyId, result);
//				
//				Attributes attrs = PortalLDAPUtil.getUserAttributes(
//					companyId, ctx, fullUserDN);
				
				String fullUserDN = PortalLDAPUtil.getNameInNamespace(
						ldapServerId, companyId, result);

				Attributes attrs = PortalLDAPUtil.getUserAttributes(
					ldapServerId, companyId, ctx, fullUserDN);

				if (_log.isDebugEnabled()) {
					startTime = System.currentTimeMillis();
				}
				LDAPAuthResult ldapAuthResult = authenticate(
					ctx, companyId, attrs, fullUserDN, password);
				if (_log.isDebugEnabled()) {
					long endTime = System.currentTimeMillis();
					_log.debug(
						"[ Second LDAP Connection for user " + screenName + " ] "  +
								(endTime - startTime) + " ms");
				}

//				if (!ldapAuthResult.isAuthenticated()) {
//					return Authenticator.FAILURE;
//				}
				
				// Process LDAP failure codes

				String errorMessage = ldapAuthResult.getErrorMessage();

				if (errorMessage != null) {
					if (errorMessage.indexOf(PrefsPropsUtil.getString(
							companyId, PropsKeys.LDAP_ERROR_USER_LOCKOUT))
								!= -1) {

						throw new UserLockoutException();
					}
					else if (errorMessage.indexOf(PrefsPropsUtil.getString(
						companyId, PropsKeys.LDAP_ERROR_PASSWORD_EXPIRED))
							!= -1) {

						throw new PasswordExpiredException();
					}
				}

				if (!ldapAuthResult.isAuthenticated() &&
					PropsValues.LDAP_IMPORT_USER_PASSWORD_ENABLED) {

					return FAILURE;
				}

				// Get user or create from LDAP
//				User user = addUser(companyId, screenName,password);
				
				// Get user or create from LDAP

				User user = PortalLDAPImporterUtil.importLDAPUser(
					ldapServerId, companyId, ctx, attrs, password);
				
				// Process LDAP success codes

				if (ldapAuthResult != null) {
					String resultCode = ldapAuthResult.getResponseControl();

					if (resultCode.equals(
							LDAPAuth.RESULT_PASSWORD_EXP_WARNING)) {

						UserLocalServiceUtil.updatePasswordReset(
							user.getUserId(), true);
					}
					else if (resultCode.equals(
								LDAPAuth.RESULT_PASSWORD_RESET)) {

						UserLocalServiceUtil.updatePasswordReset(
							user.getUserId(), true);
					}
				}
				
				// Get the user's access rights

				// Update the user's attributes
				updateUserAttributes(companyId, user, headerMap);
			}
			else {
				if (_log.isErrorEnabled()) {
					_log.error("Search filter did not return any results");
				}

				return Authenticator.FAILURE;
			}

			enu.close();
		}
		catch (Exception e) {
			_log.error("Problem accessing LDAP server: " + e.getMessage());
			_ldapErrorCount++;
			if (_ldapErrorCount >= LDAP_ERROR_MAX) {
				_log.error("***** Setting HealthCheck to failed. *****");
				HealthCheckFactory.getHealthCheck("authentication").setFailed(e.getClass().getName() + ": " + e.getMessage());
			}
			throw e;
		}
		finally {
			if (ctx != null) {
				ctx.close();
			}
		}

		return SUCCESS;
	}

	protected LDAPAuthResult authenticate(
			LdapContext ctx, long companyId, Attributes attrs, String userDN,
			String password)
		throws Exception {

		LDAPAuthResult ldapAuthResult = new LDAPAuthResult();

		InitialLdapContext innerCtx = null;
		
		try {
			Hashtable<String, Object> env =
				(Hashtable<String, Object>)ctx.getEnvironment();

			_log.debug("Bind using userDn '" + userDN + "' and password '********'");
			env.put(Context.SECURITY_PRINCIPAL, userDN);
			env.put(Context.SECURITY_CREDENTIALS, password);
			env.put(
				Context.REFERRAL,
				PrefsPropsUtil.getString(
					companyId, PropsKeys.LDAP_REFERRAL));

			// Do not use pooling because principal changes

			env.put("com.sun.jndi.ldap.connect.pool", "false");

			innerCtx = new InitialLdapContext(env, null);

			// Get LDAP bind results

			Control[] responseControls =  innerCtx.getResponseControls();

			_log.debug("Set authenticated to TRUE");
			ldapAuthResult.setAuthenticated(true);
			ldapAuthResult.setResponseControl(responseControls);
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error(
					"Failed to bind to the LDAP server with userDN "
						+ userDN + " and password ********" );
			}

			_log.error(
				"Failed to bind to the LDAP server: " + e.getMessage());

			ldapAuthResult.setAuthenticated(false);
			ldapAuthResult.setErrorMessage(e.getMessage());
		}
		finally {
			if (innerCtx != null) {
				innerCtx.close();
			}
		}

		return ldapAuthResult;
	}

	protected static User addUser(long companyId, String screenName,String password)
	throws PortalException, SystemException {

		try {
			String baseDN = PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_BASE_DN);
	
			LdapContext ctx = LocalLDAPUtil.getContext(companyId);
	
			if (ctx == null) {
				throw new SystemException("Failed to bind to the LDAP server");
			}
	
			String filter = PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_AUTH_SEARCH_FILTER);
	
			if (_log.isDebugEnabled()) {
				_log.debug("Search filter before transformation " + filter);
			}
	
			filter = StringUtil.replace(
				filter,
				new String[] {
					"@company_id@", "@email_address@", "@screen_name@"
				},
				new String[] {
					String.valueOf(companyId), StringPool.BLANK, screenName
				});
	
			if (_log.isDebugEnabled()) {
				_log.debug("Search filter after transformation " + filter);
			}
	
			SearchControls cons = new SearchControls(
				SearchControls.SUBTREE_SCOPE, 1, 0, null, false, false);
	
			NamingEnumeration<SearchResult> enu = ctx.search(
				baseDN, filter, cons);
	
			if (enu.hasMore()) {
				if (_log.isDebugEnabled()) {
					_log.debug("Search filter returned at least one result");
				}
	
				Binding binding = enu.next();
				
				if (_log.isDebugEnabled()) {
					_log.debug("First entry is " + binding.toString());
//					_log.debug("Get the LDAP attributes for " + binding.getNameInNamespace());
				}
	
				Attributes attrs = ctx.getAttributes(binding.getNameInNamespace());
	
				_log.debug("Import the attributes");
				try {
					return LocalLDAPUtil.importLDAPUser(
						companyId, ctx, attrs, password, false);
				} catch (Exception e) {
					_log.error("Error importing LDAP user -- " + e.getClass().getName() + ": " + e.getMessage());
					throw e;
				}
			}
			else {
				throw new NoSuchUserException(
					"User " + screenName + " was not found in the LDAP server");
			}
		}
		catch (Exception e) {
			_log.error("Problem accessing LDAP server ", e);
	
			throw new SystemException(
				"Problem accessign LDAP server " + e.getMessage());
		}
	}
	
	private static void updateUserAttributes(long companyId, User user, Map<String, String[]> headerMap)
	throws SystemException {
		
		UserPrivilages userPriv = new UserPrivilages();
		ArrayList<String> roles = new ArrayList<String>();
		String[] ipAddresses = headerMap.get("X-FORWARD-FOR");
		_log.info("user - " + user.getEmailAddress() + " ipaddress - " + ipAddresses);
		
		if(authenticationServiceURL==null)
		{
			authenticationServiceURL = PropsUtil.get("ieeecsAuthenticationServiceURl");
		}
		String urlString =authenticationServiceURL;
		urlString = urlString + "byUserIpaddress?userId=cscsdltest@gmail&ipAddress=2886729985";
		_log.info("URL -" + urlString);
		//String urlString = "http://localhost:8085/AuthorizationWebService/service/byUserIpaddress?userId=cscsdltest@gmail&ipAddress=2886729985";
		_log.info("New service code url - " + urlString);
		try {
			URL url = new URL(urlString);
			HttpURLConnection	conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			//_log.info("Buffered reader - " + br.readLine());
			String output = "";
			String line = null;
			System.out.println("Output from Server .... \n");

			while ((line = br.readLine()) != null) {
				output = output + line;
				_log.info("Buffered line - " + output);
			}
			
			conn.disconnect();
			
			Gson gson = new Gson();
			_log.info("Output - " + output);
			
			userPriv = gson.fromJson(output, UserPrivilages.class);
			if(userPriv != null && userPriv.getRoles().size() > 0) {
				roles = (ArrayList<String>) userPriv.getRoles();
				_log.info("roles - " + roles.size() + " - " + roles.get(0));
			} else {
				roles.add("co");
				roles.add("it");
			}
			//_log.info("roles " + userPriv);
			
		} catch (JsonSyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ExtUser extUser = null;
		try {
			extUser = ExtUserLocalServiceUtil.getExtUser(user.getUserId());
		} catch (Exception e) {}
		
		// If the extended user attributes do not exist, so create a new one
		if (extUser == null) {
			_log.debug("Extended user attributes were not found -- add them");
			// Create one
			extUser = ExtUserLocalServiceUtil.createExtUser(user.getUserId());
			if (userPriv.isMember()) {
				extUser.setCsMember(true);
				extUser.setCanCreateInstantCommunities(true);
			}
			else {
				extUser.setCsMember(false);
				extUser.setCanCreateInstantCommunities(false);
			}
		}
		// Else, we update the existing user
		else {
			_log.debug("Update the existing user attributes from -- " + extUser.toString());
			// Look for the case where they were not a member until this login
			if (!extUser.isCsMember() && userPriv.isMember()) {
				// Turn them into a member
				extUser.setCsMember(true);
				extUser.setCanCreateInstantCommunities(true);
			}
			// Check for membership expiration
			else if (extUser.isCsMember() && !userPriv.isMember()) {
				// Update their membership status.  For example, it may have expired
				extUser.setCsMember(false);
				extUser.setCanCreateInstantCommunities(false);
			}
		}
		
	
		
	
		
		// And save the updates
		_log.debug("User attributes now -- " + extUser.toString());
		ExtUserLocalServiceUtil.updateExtUser(extUser);
		
		
		
		if (userPriv.isCap())
			updateUserOrganization(companyId, user, userPriv.getCAPOrganization());

		try {
			ExpandoValueLocalServiceUtil.
			addValue(companyId,User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, Constants.IEEECS_ROLES, user.getUserId(), toStringArray(roles));
			 
			
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (ProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
		} catch (com.liferay.portal.kernel.exception.PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updateUserGroup(User user, String groupName, boolean isMember) {
		try {
			UserGroup csMemberGroup = UserGroupLocalServiceUtil.getUserGroup(user.getCompanyId(), groupName);
			long[] userIds = {user.getUserId()};
			if (isMember) {
				UserLocalServiceUtil.addUserGroupUsers(csMemberGroup.getUserGroupId(), userIds);
				_log.debug("Added " + user.getScreenName() + " to " + csMemberGroup.getName());
			}
			else {
				UserLocalServiceUtil.unsetUserGroupUsers(csMemberGroup.getUserGroupId(), userIds);
				_log.debug("Removed " + user.getScreenName() + " from " + csMemberGroup.getName());
			}
		} catch (Exception e) {
			_log.error("Unable to update " + user.getScreenName() + " to group '" + groupName + "'", e);
		}
	}
	
	public static void updateUserOrganization(long companyId, User user, String organizationName) {
		try {
			_log.debug("Add " + user.getScreenName() + " to " + organizationName);
			Organization org = OrganizationLocalServiceUtil.getOrganization(companyId, organizationName);
			UserUtil.addOrganization(user.getUserId(), org.getOrganizationId());
		} catch (Exception e) {
			_log.error("Unable to add user to organization '" + organizationName + "'", e);
		}
	}
	
	protected int authenticateOmniadmin(
			long companyId, String emailAddress, String screenName, long userId)
		throws Exception {

		// Only allow omniadmin if Liferay password checking is enabled

		if (PropsValues.AUTH_PIPELINE_ENABLE_LIFERAY_CHECK) {
			if (userId > 0) {
				if (OmniadminUtil.isOmniadmin(userId)) {
					return SUCCESS;
				}
			}
			else if (Validator.isNotNull(emailAddress)) {
				try {
					User user = UserLocalServiceUtil.getUserByEmailAddress(
						companyId, emailAddress);

					if (OmniadminUtil.isOmniadmin(user.getUserId())) {
						return SUCCESS;
					}
				}
				catch (NoSuchUserException nsue) {
				}
			}
			else if (Validator.isNotNull(screenName)) {
				try {
					User user = UserLocalServiceUtil.getUserByScreenName(
						companyId, screenName);

					if (OmniadminUtil.isOmniadmin(user.getUserId())) {
						return SUCCESS;
					}
				}
				catch (NoSuchUserException nsue) {
				}
			}
		}

		return FAILURE;
	}
	
	protected int authenticateRequired(
			long companyId, long userId, String emailAddress, String screenName,
			boolean allowOmniadmin, int failureCode)
		throws Exception {

		// Make exceptions for omniadmins so that if they break the LDAP
		// configuration, they can still login to fix the problem

		if (allowOmniadmin &&
			(authenticateOmniadmin(
				companyId, emailAddress, screenName, userId) == SUCCESS)) {

			return SUCCESS;
		}

		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.LDAP_AUTH_REQUIRED)) {

			return failureCode;
		}
		else {
			return SUCCESS;
		}
	}
	
	
	private static int 			_ldapErrorCount = 0;
	private final static int 	LDAP_ERROR_MAX = 4;
	
	private static Log _log = LogFactoryUtil.getLog(LDAPAuth.class);
	
	private static String[] toStringArray(List<String> input)
	{
		String[] output = new String[input.size()];
		for(int i=0; i < input.size();i++)
		{
			output[i] = input.get(i);
		}
		return output;
	}
	
}
