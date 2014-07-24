/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.computer.portal.security.auth;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.NoSuchUserGroupException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.LogUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.model.impl.CompanyImpl;
import com.liferay.portal.security.auth.FullNameGenerator;
import com.liferay.portal.security.auth.FullNameGeneratorFactory;
import com.liferay.portal.security.ldap.AttributesTransformer;
import com.liferay.portal.security.ldap.AttributesTransformerFactory;
import com.liferay.portal.security.ldap.LDAPSettingsUtil;
import com.liferay.portal.security.ldap.PortalLDAPUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.ldap.LDAPUtil;

/**
 * Taken from PortalLDAPUtil -- wberks
 * 
 * <a href="LocalLDAPUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author William Berks
 *
 */
public class LocalLDAPUtil {

	public static final String IMPORT_BY_USER = "user";

	public static final String IMPORT_BY_GROUP = "group";

	public static Attributes getAttributes(
			LdapContext ctx, String fullDistinguishedName)
		throws Exception {

		String[] attrIds = {
			"creatorsName", "createTimestamp", "modifiersName",
			"modifyTimestamp"
		};

		Attributes attrs = ctx.getAttributes(fullDistinguishedName);

		NamingEnumeration<? extends Attribute> enu = ctx.getAttributes(
			fullDistinguishedName, attrIds).getAll();

		while (enu.hasMore()) {
			attrs.put(enu.next());
		}

		return attrs;
	}

	public static LdapContext getContext(long companyId) throws Exception {
		String baseProviderURL = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_BASE_PROVIDER_URL);
		String pricipal = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_SECURITY_PRINCIPAL);
		String credentials = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_SECURITY_CREDENTIALS);

		return getContext(companyId, baseProviderURL, pricipal, credentials);
	}

	public static LdapContext getContext(
			long companyId, String providerURL, String pricipal,
			String credentials)
		throws Exception {

		Properties env = new Properties();

		env.put(
			Context.INITIAL_CONTEXT_FACTORY,
			PrefsPropsUtil.getString(
				companyId, PropsKeys.LDAP_FACTORY_INITIAL));
		env.put(Context.PROVIDER_URL, providerURL);
		env.put(Context.SECURITY_PRINCIPAL, pricipal);
		env.put(Context.SECURITY_CREDENTIALS, credentials);

		LogUtil.debug(_log, env);

		LdapContext ctx = null;

		try {
			ctx = new InitialLdapContext(env, null);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Failed to bind to the LDAP server");
			}

			if (_log.isErrorEnabled()) {
				_log.error(e);
			}
		}

		return ctx;
	}

	public static Properties getGroupMappings(long companyId)
		throws Exception {

		Properties groupMappings = PropertiesUtil.load(
			PrefsPropsUtil.getString(companyId, PropsKeys.LDAP_GROUP_MAPPINGS));

		LogUtil.debug(_log, groupMappings);

		return groupMappings;
	}

	public static NamingEnumeration<SearchResult> getGroups(
			long companyId, LdapContext ctx, int maxResults)
		throws Exception {

		String baseDN = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_BASE_DN);
		String groupFilter = PrefsPropsUtil.getString(
			companyId, PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER);

		return getGroups(companyId, ctx, maxResults, baseDN, groupFilter);
	}

	public static NamingEnumeration<SearchResult> getGroups(
			long companyId, LdapContext ctx, int maxResults, String baseDN,
			String groupFilter)
		throws Exception {

		SearchControls cons = new SearchControls(
			SearchControls.SUBTREE_SCOPE, maxResults, 0, null, false, false);

		return ctx.search(baseDN, groupFilter, cons);
	}

	public static UserGroup importLDAPGroup(
			long companyId, LdapContext ctx, Attributes attrs,
			boolean importGroupMembership)
		throws Exception {

		AttributesTransformer attrsTransformer =
			AttributesTransformerFactory.getInstance();

		attrs = attrsTransformer.transformGroup(attrs);

		Properties groupMappings = getGroupMappings(companyId);

		LogUtil.debug(_log, groupMappings);

		String groupName = LDAPUtil.getAttributeValue(
			attrs, groupMappings.getProperty("groupName")).toLowerCase();
		String description = LDAPUtil.getAttributeValue(
			attrs, groupMappings.getProperty("description"));

		// Get or create user group

		UserGroup userGroup = null;

		try {
			userGroup = UserGroupLocalServiceUtil.getUserGroup(
				companyId, groupName);

			UserGroupLocalServiceUtil.updateUserGroup(
				companyId, userGroup.getUserGroupId(), groupName, description);
		}
		catch (NoSuchUserGroupException nsuge) {
			if (_log.isErrorEnabled()) {
				_log.error("Adding user group to portal " + groupName);
			}

			long defaultUserId = UserLocalServiceUtil.getDefaultUserId(
				companyId);

			try {
				userGroup = UserGroupLocalServiceUtil.addUserGroup(
					defaultUserId, companyId, groupName, description);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Could not create user group " + groupName);
				}

				if (_log.isErrorEnabled()) {
					_log.error(e, e);
				}
			}
		}

		// Import users and membership

		if (importGroupMembership && (userGroup != null)) {
			Attribute attr = attrs.get(groupMappings.getProperty("user"));

			if (attr != null){
				_importUsersAndMembershipFromLDAPGroup(
					companyId, ctx, userGroup.getUserGroupId(), attr);
			}
		}

		return userGroup;
	}

	public static User importLDAPUser(
			long companyId, LdapContext ctx, Attributes attrs, String password,
			boolean importGroupMembership)
		throws Exception {
		_log.error("importLDAPUser (ieeecs)");

		AttributesTransformer attrsTransformer =
			AttributesTransformerFactory.getInstance();

		attrs = attrsTransformer.transformUser(attrs);

//		Properties userMappings = PortalLDAPUtil.getUserMappings(companyId);
		
		long[] ldapServerIds = StringUtil.split(
				PrefsPropsUtil.getString(companyId, "ldap.server.ids"), 0L);
		
		long ldapServerId = ldapServerIds[0];
		
		Properties userMappings = LDAPSettingsUtil.getUserMappings(
				ldapServerId, companyId);

		LogUtil.debug(_log, userMappings);

		_log.error("Get default user for company " + companyId);
		User defaultUser = UserLocalServiceUtil.getDefaultUser(companyId);

		boolean autoPassword = false;
		boolean updatePassword = true;

		if (password.equals(StringPool.BLANK)) {
			autoPassword = true;
			updatePassword = false;
		}

		long creatorUserId = 0;
		boolean passwordReset = false;
		boolean autoScreenName = false;
//		String screenName = LDAPUtil.getAttributeValue(
//				attrs, userMappings.getProperty("screenName")).toLowerCase();
		String screenName = LDAPUtil.getAttributeValue(
				attrs, userMappings.getProperty("screenName"));
		// The screen names from LDAP may have spaces within them that cause Liferay lots of 
		// headaches, so normalize the screen name
		screenName = normalize(screenName);
		
		String emailAddress = LDAPUtil.getAttributeValue(
			attrs, userMappings.getProperty("emailAddress"));
		// *****************************************************************************
		// Note: We are going to use the openId as the holder of the user's CID.
		// In our system, the screen name is based on the UID and the user can change
		// their screen name/UID.  However they cannot change the CID.  We need to use
		// the CID as the map between the LDAP data and the Liferay data.  If the user
		// changes their screen name, we need to update Liferay.
		// *****************************************************************************
		_log.error(PropsKeys.LDAP_USER_MAPPINGS + " = '" + PrefsPropsUtil.getString(companyId, PropsKeys.LDAP_USER_MAPPINGS) + "'");
		_log.error("LDAP attribute mappings");
		_log.error("openId maps to '" + userMappings.getProperty("openId") + "'");
		_log.error("firstName maps to '" + userMappings.getProperty("firstName") + "'");
		_log.error("lastName maps to '" + userMappings.getProperty("lastName") + "'");
		_log.error("fullName maps to '" + userMappings.getProperty("fullName") + "'");
		String openId = LDAPUtil.getAttributeValue(
			attrs, userMappings.getProperty("openId"));
		Locale locale = defaultUser.getLocale();
		String firstName = LDAPUtil.getAttributeValue(
			attrs, userMappings.getProperty("firstName"));
		String middleName = StringPool.BLANK;
		String lastName = LDAPUtil.getAttributeValue(
			attrs, userMappings.getProperty("lastName"));
		String fullName = LDAPUtil.getAttributeValue(
			attrs, userMappings.getProperty("fullName"));

		if (Validator.isNull(firstName) || Validator.isNull(lastName)) {

//			String[] names = LDAPUtil.splitFullName(fullName);
			
			FullNameGenerator fullNameGenerator =
					FullNameGeneratorFactory.getInstance();

			String[] names = fullNameGenerator.splitFullName(fullName);

			firstName = names[0];
			middleName = names[1];
			lastName = names[2];
		}

		int prefixId = 0;
		int suffixId = 0;
		boolean male = true;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = LDAPUtil.getAttributeValue(
			attrs, userMappings.getProperty("jobTitle"));
		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		List<UserGroupRole> userGroupRoles = null;
		long[] userGroupIds = null;
		boolean sendEmail = false;
		ServiceContext serviceContext = new ServiceContext();

		if (_log.isErrorEnabled()) {
			_log.error(
				"Screen name " + screenName + " and email address " +
					emailAddress);
		}
		
		// Some accounts do not have an email address (such as University accounts)
		// If the email is not available
		if (Validator.isNull(emailAddress)) {
			// Create one for them
			emailAddress = LDAPUtil.getAttributeValue(attrs, "cid") + "@autogenerated-address.com";
			_log.info("Null email address for " + screenName + ", generated the address " + emailAddress);
		}
		// Sometimes they put more than one email address into the LDAP field
		if (emailAddress.indexOf('@') != emailAddress.lastIndexOf('@')) {
			// Use the first one
			emailAddress.trim();
			int sepIndex = emailAddress.indexOf(';');
			if (sepIndex == -1)
				sepIndex = emailAddress.indexOf(',');
			if (sepIndex == -1)
				sepIndex = emailAddress.indexOf(' ');
			if (sepIndex == -1) // we could not determine where the fist address ended
				// Make up an address
				emailAddress = LDAPUtil.getAttributeValue(attrs, "cid") + "@autogenerated-address.com";
			else
				emailAddress = emailAddress.substring(0, sepIndex).trim();
		}

		if (Validator.isNull(screenName) || Validator.isNull(emailAddress)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Cannot add user because screen name and email address " +
						"are required");
			}

			return null;
		}

		User user = null;

		try {
			_log.error("Find user using CID '" + openId + "'");
			// Find corresponding portal user
			user = UserLocalServiceUtil.getUserByOpenId(companyId, openId);
			
			// Skip if is default user

			if (user.isDefaultUser()) {
				return user;
			}

			Contact contact = user.getContact();

			Calendar birthdayCal = CalendarFactoryUtil.getCalendar();

			birthdayCal.setTime(contact.getBirthday());

			birthdayMonth = birthdayCal.get(Calendar.MONTH);
			birthdayDay = birthdayCal.get(Calendar.DATE);
			birthdayYear = birthdayCal.get(Calendar.YEAR);

			// User exists so update user information

			if (updatePassword) {
				ExpandoValue val = ExpandoValueLocalServiceUtil.getValue(/*companyId,*/ User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, "isCap", user.getUserId());
				boolean isCap = (val == null ? Boolean.FALSE : val.getBoolean());
				passwordReset = isCap?user.isPasswordReset():passwordReset;
				user = UserLocalServiceUtil.updatePassword(
					user.getUserId(), password, password, passwordReset,
					true);
			}
			
			_log.error("call UserLocalServiceUtil.updateUser for userId '" + user.getUserId() + "' and screenName '" + screenName + "'");
			user = UserLocalServiceUtil.updateUser(
					user.getUserId(), password, StringPool.BLANK, StringPool.BLANK,
					user.isPasswordReset(), user.getReminderQueryQuestion(),
					user.getReminderQueryAnswer(), screenName, emailAddress, 0L, openId,
					user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(),
					user.getComments(), firstName, middleName, lastName,
					contact.getPrefixId(), contact.getSuffixId(), contact.getMale(),
					birthdayMonth, birthdayDay, birthdayYear, contact.getSmsSn(),
					contact.getAimSn(), contact.getFacebookSn(), contact.getIcqSn(),
					contact.getJabberSn(), contact.getMsnSn(),
					contact.getMySpaceSn(), contact.getSkypeSn(),
					contact.getTwitterSn(), contact.getYmSn(), jobTitle, groupIds,
					organizationIds, roleIds, userGroupRoles, userGroupIds,
					serviceContext);
			_log.error("After update, user screen name = '" + user.getScreenName() + "'");

		}
		catch (NoSuchUserException nsue) {

			// User does not exist so create
			_log.error("Searching for the user using the CID failed");
		}

		if (user == null) {
			// ************************************************************************
			// The user may already exist based upon the old method of screen name/UID.
			// We need to search for the user using their screen name and then verify
			// that the entry found is in fact the correct one.  If it is, we can then
			// store the CID into the openId field and 'fix' the entry.
			// ************************************************************************
			try {
				_log.error("Search for the user using their screenName '" + screenName + "'");
				user = UserLocalServiceUtil.getUserByScreenName(companyId, screenName);
				
				// See if the CID / openId is already set.  If it is, then this entry 
				// has already been 'claimed' and is not really our user.
				if (Validator.isNull(user.getOpenId())) {
					// OK, so the openId field is not set and the screen name matches,
					// so we can grab this user.
					
					Contact contact = user.getContact();

					Calendar birthdayCal = CalendarFactoryUtil.getCalendar();

					birthdayCal.setTime(contact.getBirthday());

					birthdayMonth = birthdayCal.get(Calendar.MONTH);
					birthdayDay = birthdayCal.get(Calendar.DATE);
					birthdayYear = birthdayCal.get(Calendar.YEAR);

					// User exists so update user information

					if (updatePassword) {
						user = UserLocalServiceUtil.updatePassword(
							user.getUserId(), password, password, passwordReset,
							true);
					}
					
					_log.error("Correcting " + screenName + " to use CID value " + openId);
					
					user = UserLocalServiceUtil.updateUser(
							user.getUserId(), password, StringPool.BLANK, StringPool.BLANK,
							user.isPasswordReset(), user.getReminderQueryQuestion(),
							user.getReminderQueryAnswer(), screenName, emailAddress, 0L, openId,
							user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(),
							user.getComments(), firstName, middleName, lastName,
							contact.getPrefixId(), contact.getSuffixId(), contact.getMale(),
							birthdayMonth, birthdayDay, birthdayYear, contact.getSmsSn(),
							contact.getAimSn(), contact.getFacebookSn(), contact.getIcqSn(),
							contact.getJabberSn(), contact.getMsnSn(),
							contact.getMySpaceSn(), contact.getSkypeSn(),
							contact.getTwitterSn(), contact.getYmSn(), jobTitle, groupIds,
							organizationIds, roleIds, userGroupRoles, userGroupIds,
							serviceContext);
				}
			} catch (NoSuchUserException e) {
				// They do not already exists under the screen name
				_log.error("User with screen name '" + screenName + "' was not found.");
			}
		}
		
//		if (user == null) {
//			// ************************************************************************
//			// Try again with a lower cased screen
//			// ************************************************************************
//			try {
//				_log.error("Search for the user using their screenName '" + screenName.toLowerCase() + "'");
//				user = UserLocalServiceUtil.getUserByScreenName(companyId, screenName.toLowerCase());
//				
//				// See if the CID / openId is already set.  If it is, then this entry 
//				// has already been 'claimed' and is not really our user.
//				if (Validator.isNull(user.getOpenId())) {
//					// OK, so the openId field is not set and the screen name matches,
//					// so we can grab this user.
//					
//					Contact contact = user.getContact();
//
//					Calendar birthdayCal = CalendarFactoryUtil.getCalendar();
//
//					birthdayCal.setTime(contact.getBirthday());
//
//					birthdayMonth = birthdayCal.get(Calendar.MONTH);
//					birthdayDay = birthdayCal.get(Calendar.DATE);
//					birthdayYear = birthdayCal.get(Calendar.YEAR);
//
//					// User exists so update user information
//
//					if (updatePassword) {
//						user = UserLocalServiceUtil.updatePassword(
//							user.getUserId(), password, password, passwordReset,
//							true);
//					}
//					
//					_log.error("Correcting " + screenName + " to use CID value " + openId);
//					
//					user = UserLocalServiceUtil.updateUser(
//							user.getUserId(), password, StringPool.BLANK, StringPool.BLANK,
//							user.isPasswordReset(), user.getReminderQueryQuestion(),
//							user.getReminderQueryAnswer(), screenName, emailAddress, openId,
//							user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(),
//							user.getComments(), firstName, middleName, lastName,
//							contact.getPrefixId(), contact.getSuffixId(), contact.getMale(),
//							birthdayMonth, birthdayDay, birthdayYear, contact.getSmsSn(),
//							contact.getAimSn(), contact.getFacebookSn(), contact.getIcqSn(),
//							contact.getJabberSn(), contact.getMsnSn(),
//							contact.getMySpaceSn(), contact.getSkypeSn(),
//							contact.getTwitterSn(), contact.getYmSn(), jobTitle, groupIds,
//							organizationIds, roleIds, userGroupRoles, userGroupIds,
//							serviceContext);
//				}
//			} catch (NoSuchUserException e) {
//				// They do not already exists under the screen name
//				_log.error("User with screen name '" + screenName.toLowerCase() + "' was not found.");
//			}
//		}
		
		// If we still have not been able to find the user, create one
		if (user == null) {
			try {
				_log.error("Adding " + screenName + " with CID value " + openId);

				user = UserLocalServiceUtil.addUser(
						creatorUserId, companyId, autoPassword, password, password,
						autoScreenName, screenName, emailAddress, 0L, openId, locale,     // facebook id 0L
						firstName, middleName, lastName, prefixId, suffixId, male,
						birthdayMonth, birthdayDay, birthdayYear, jobTitle,
						groupIds, organizationIds, roleIds, userGroupIds, sendEmail,
						serviceContext);
			}
			catch (Exception e){
				_log.error(
					"Problem adding user with screen name " + screenName +
						" and email address " + emailAddress,
					e);
			}
		}

		// Import user groups and membership

		if (importGroupMembership && (user != null)) {
			String userMappingsGroup = userMappings.getProperty("group");

			if (userMappingsGroup != null) {
				Attribute attr = attrs.get(userMappingsGroup);

				if (attr != null){
					_importGroupsAndMembershipFromLDAPUser(
						companyId, ctx, user.getUserId(), attr);
				}
			}
		}

		return user;
	}
	
	private static String normalize(String screenName) {
		return screenName.trim().replace(" ", "_");
	}

//	public static boolean isAuthEnabled(long companyId)
//		throws PortalException, SystemException {
//
//		if (PrefsPropsUtil.getBoolean(
//				companyId, PropsUtil.LDAP_AUTH_ENABLED,
//				PropsValues.LDAP_AUTH_ENABLED)) {
//
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//
//	public static boolean isExportEnabled(long companyId)
//		throws PortalException, SystemException {
//
//		if (PrefsPropsUtil.getBoolean(
//				companyId, PropsUtil.LDAP_EXPORT_ENABLED,
//				PropsValues.LDAP_EXPORT_ENABLED)) {
//
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//
//	public static boolean isImportEnabled(long companyId)
//		throws PortalException, SystemException {
//
//		if (PrefsPropsUtil.getBoolean(
//				companyId, PropsUtil.LDAP_IMPORT_ENABLED,
//				PropsValues.LDAP_IMPORT_ENABLED)) {
//
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//
//	public static boolean isImportOnStartup(long companyId)
//		throws PortalException, SystemException {
//
//		if (PrefsPropsUtil.getBoolean(
//				companyId, PropsUtil.LDAP_IMPORT_ON_STARTUP)) {
//
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//
//	public static boolean isNtlmEnabled(long companyId)
//		throws PortalException, SystemException {
//
//		if (!isAuthEnabled(companyId)) {
//			return false;
//		}
//
//		if (PrefsPropsUtil.getBoolean(companyId, PropsUtil.NTLM_AUTH_ENABLED)) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//
//	public static boolean isPasswordPolicyEnabled(long companyId)
//		throws PortalException, SystemException {
//
//		if (PrefsPropsUtil.getBoolean(
//				companyId, PropsUtil.LDAP_PASSWORD_POLICY_ENABLED,
//				PropsValues.LDAP_PASSWORD_POLICY_ENABLED)) {
//
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//
	private static void _importGroupsAndMembershipFromLDAPUser(
			long companyId, LdapContext ctx, long userId, Attribute attr)
		throws Exception {

		// Remove all user group membership from user

		UserGroupLocalServiceUtil.clearUserUserGroups(userId);

		for (int i = 0; i < attr.size(); i++) {

			// Find group in LDAP

			String fullGroupDN = (String)attr.get(i);

			Attributes groupAttrs = null;

			try {
				groupAttrs = getAttributes(ctx, fullGroupDN);
			}
			catch (NameNotFoundException nnfe) {
				_log.error(
					"LDAP group not found with fullGroupDN " + fullGroupDN);

				_log.error(nnfe, nnfe);

				continue;
			}

			UserGroup userGroup = importLDAPGroup(
				companyId, ctx, groupAttrs, false);

			// Add user to user group

			if (userGroup != null) {
				if (_log.isErrorEnabled()) {
					_log.error(
						"Adding " + userId + " to group " +
							userGroup.getUserGroupId());
				}

				UserLocalServiceUtil.addUserGroupUsers(
					userGroup.getUserGroupId(), new long[] {userId});
			}
		}
	}

	private static void _importUsersAndMembershipFromLDAPGroup(
			long companyId, LdapContext ctx, long userGroupId, Attribute attr)
		throws Exception {

		// Remove all user membership from user group

		UserLocalServiceUtil.clearUserGroupUsers(userGroupId);

		for (int i = 0; i < attr.size(); i++) {

			// Find user in LDAP

			String fullUserDN = (String)attr.get(i);

			Attributes userAttrs = null;

			try {
				userAttrs = getAttributes(ctx, fullUserDN);
			}
			catch (NameNotFoundException nnfe) {
				_log.error(
					"LDAP user not found with fullUserDN " + fullUserDN);

				_log.error(nnfe, nnfe);

				continue;
			}

			User user = importLDAPUser(
				companyId, ctx, userAttrs, StringPool.BLANK, false);

			// Add user to user group

			if (user != null) {
				if (_log.isErrorEnabled()) {
					_log.error(
						"Adding " + user.getUserId() + " to group " +
							userGroupId);
				}

				UserLocalServiceUtil.addUserGroupUsers(
					userGroupId, new long[] {user.getUserId()});
			}
		}
	}
	
	private static Log _log = LogFactoryUtil.getLog(LocalLDAPUtil.class);

}
