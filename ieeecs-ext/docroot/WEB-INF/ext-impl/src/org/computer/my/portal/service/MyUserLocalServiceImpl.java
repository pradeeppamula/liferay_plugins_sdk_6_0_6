package org.computer.my.portal.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.naming.Binding;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapContext;

import com.liferay.portal.ContactFirstNameException;
import com.liferay.portal.ContactLastNameException;
import com.liferay.portal.DuplicateUserScreenNameException;
import com.liferay.portal.GroupFriendlyURLException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.ReservedUserEmailAddressException;
import com.liferay.portal.ReservedUserScreenNameException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.UserIdException;
import com.liferay.portal.UserPasswordException;
import com.liferay.portal.UserScreenNameException;
import com.liferay.portal.UserSmsException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.InstancePool;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.LayoutImpl;
import com.liferay.portal.security.auth.AuthPipeline;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.security.auth.ScreenNameValidator;
import com.liferay.portal.security.ldap.LDAPSettingsUtil;
import com.liferay.portal.security.ldap.Modifications;
import com.liferay.portal.security.ldap.PortalLDAPUtil;
import com.liferay.portal.security.pwd.PwdAuthenticator;
import com.liferay.portal.security.pwd.PwdEncryptor;
import com.liferay.portal.security.pwd.PwdToolkitUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.service.impl.UserLocalServiceImpl;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class MyUserLocalServiceImpl extends  com.liferay.portal.service.impl.UserLocalServiceImpl {

	protected int authenticate(
			long companyId, String login, String password, String authType,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap,
			Map<String, Object> resultsMap)
		throws PortalException, SystemException {
		
		_log.debug("In Customized UserLocalServiceImpl");
		if (PropsValues.AUTH_LOGIN_DISABLED) {
			return Authenticator.FAILURE;
		}
		
		login = login.trim();                        //		login = login.trim().toLowerCase();
		
		long userId = GetterUtil.getLong(login);

		// User input validation

		if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
			if (Validator.isNull(login)) {
				throw new UserEmailAddressException();
			}
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			if (Validator.isNull(login)) {
				throw new UserScreenNameException();
			}
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			if (Validator.isNull(login)) {
				throw new UserIdException();
			}
		}

		if (Validator.isNull(password)) {
			throw new UserPasswordException(
				UserPasswordException.PASSWORD_INVALID);
		}

		int authResult = Authenticator.FAILURE;

		// Pre-authentication pipeline

		if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
			authResult = AuthPipeline.authenticateByEmailAddress(
				PropsKeys.AUTH_PIPELINE_PRE, companyId, login, password,
				headerMap, parameterMap);
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			authResult = AuthPipeline.authenticateByScreenName(
				PropsKeys.AUTH_PIPELINE_PRE, companyId, login, password,
				headerMap, parameterMap);
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			authResult = AuthPipeline.authenticateByUserId(
				PropsKeys.AUTH_PIPELINE_PRE, companyId, userId, password,
				headerMap, parameterMap);
		}

		// Get user

		User user = null;
		
		try {
			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				user = userPersistence.findByC_EA(companyId, login);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				// liferay normalises the screen names when saving them so we have to 
				// look for the normalised version of the screen name in the database
//				String normLogin = FriendlyURLNormalizer.normalize(login);
				String normLogin = getScreenName(login);
				user = userPersistence.findByC_SN(companyId, normLogin);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				user = userPersistence.findByC_U(
					companyId, GetterUtil.getLong(login));
			}
		}
		catch (NoSuchUserException nsue) {
			return Authenticator.DNE;
		}

		if (user.isDefaultUser()) {
			if (_log.isInfoEnabled()) {
				_log.info("Authentication is disabled for the default user");
			}

			return Authenticator.DNE;
		}
		else if (!user.isActive()) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Authentication is disabled for inactive user " +
						user.getUserId());
			}

			return Authenticator.FAILURE;
		}

		if (!user.isPasswordEncrypted()) {
			user.setPassword(PwdEncryptor.encrypt(user.getPassword()));
			user.setPasswordEncrypted(true);

			userPersistence.update(user, false);
		}

		// Check password policy to see if the is account locked out or if the
		// password is expired

		checkLockout(user);

		checkPasswordExpired(user);

		// Authenticate against the User_ table

		if (authResult == Authenticator.SUCCESS) {
			if (PropsValues.AUTH_PIPELINE_ENABLE_LIFERAY_CHECK) {
				boolean authenticated = PwdAuthenticator.authenticate(
					login, password, user.getPassword());

				if (authenticated) {
					authResult = Authenticator.SUCCESS;
				}
				else {
					authResult = Authenticator.FAILURE;
				}
			}
		}

		// Post-authentication pipeline

		if (authResult == Authenticator.SUCCESS) {
			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				authResult = AuthPipeline.authenticateByEmailAddress(
					PropsKeys.AUTH_PIPELINE_POST, companyId, login, password,
					headerMap, parameterMap);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				authResult = AuthPipeline.authenticateByScreenName(
					PropsKeys.AUTH_PIPELINE_POST, companyId, login, password,
					headerMap, parameterMap);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				authResult = AuthPipeline.authenticateByUserId(
					PropsKeys.AUTH_PIPELINE_POST, companyId, userId, password,
					headerMap, parameterMap);
			}
		}

		if (authResult == Authenticator.SUCCESS) {
			if (resultsMap != null) {
				resultsMap.put("userId", user.getUserId());
			}

			// Update digest

			boolean updateDigest = true;

			if (PropsValues.AUTH_PIPELINE_ENABLE_LIFERAY_CHECK) {
				if (Validator.isNotNull(user.getDigest())) {
					updateDigest = false;
				}
			}

			if (updateDigest) {
				String digest = user.getDigest(password);

				user.setDigest(digest);

				userPersistence.update(user, false);
			}
		}

		// Execute code triggered by authentication failure

		if (authResult == Authenticator.FAILURE) {
			try {
				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					AuthPipeline.onFailureByEmailAddress(
						PropsKeys.AUTH_FAILURE, companyId, login, headerMap,
						parameterMap);
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
					AuthPipeline.onFailureByScreenName(
						PropsKeys.AUTH_FAILURE, companyId, login, headerMap,
						parameterMap);
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
					AuthPipeline.onFailureByUserId(
						PropsKeys.AUTH_FAILURE, companyId, userId, headerMap,
						parameterMap);
				}

				// Let LDAP handle max failure event

				if (!LDAPSettingsUtil.isPasswordPolicyEnabled(
						user.getCompanyId())) {

					PasswordPolicy passwordPolicy = user.getPasswordPolicy();

					int failedLoginAttempts = user.getFailedLoginAttempts();
					int maxFailures = passwordPolicy.getMaxFailure();

					if ((failedLoginAttempts >= maxFailures) &&
						(maxFailures != 0)) {

						if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
							AuthPipeline.onMaxFailuresByEmailAddress(
								PropsKeys.AUTH_MAX_FAILURES, companyId, login,
								headerMap, parameterMap);
						}
						else if (authType.equals(
									CompanyConstants.AUTH_TYPE_SN)) {

							AuthPipeline.onMaxFailuresByScreenName(
								PropsKeys.AUTH_MAX_FAILURES, companyId, login,
								headerMap, parameterMap);
						}
						else if (authType.equals(
									CompanyConstants.AUTH_TYPE_ID)) {

							AuthPipeline.onMaxFailuresByUserId(
								PropsKeys.AUTH_MAX_FAILURES, companyId, userId,
								headerMap, parameterMap);
						}
					}
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return authResult;
	}

	public User updateEmailAddress(
			long userId, String password, String emailAddress1,
			String emailAddress2)
		throws PortalException, SystemException {

		emailAddress1 = emailAddress1.trim().toLowerCase();
		emailAddress2 = emailAddress2.trim().toLowerCase();

		if (!emailAddress1.equals(emailAddress2)) {
			throw new UserEmailAddressException();
		}

		User user = userPersistence.findByPrimaryKey(userId);

		validateEmailAddress(user.getCompanyId(), emailAddress1);
		validateEmailAddress(user.getCompanyId(), emailAddress2);

		if (!user.getEmailAddress().equalsIgnoreCase(emailAddress1)) {
			if (userPersistence.fetchByC_EA(
					user.getCompanyId(), emailAddress1) != null) {

// duplicates are allowed -- wtberks
//				throw new DuplicateUserEmailAddressException();
			}
		}

		setEmailAddress(
			user, password, user.getFirstName(), user.getMiddleName(),
			user.getLastName(), emailAddress1);

		userPersistence.update(user, false);

		return user;
	}

	public User updateScreenName(long userId, String screenName)
		throws PortalException, SystemException {
		_log.debug("updateScreenName(" + screenName + ")");
		// User

		User user = userPersistence.findByPrimaryKey(userId);

		screenName = getScreenName(screenName);

		validateScreenName(user.getCompanyId(), userId, screenName);
		
		// BEGIN - this code is copied from 6.0.12
		if (!user.getScreenName().equalsIgnoreCase(screenName)) {
			user.setDigest(StringPool.BLANK);
		}
		// END

		_log.debug("call user.setScreenName(" + screenName + ")");
		user.setScreenName(screenName);

		userPersistence.update(user, false);
		
		_log.debug("After update screenName = '" + user.getScreenName() + "'");

		// Group

		Group group = groupLocalService.getUserGroup(
			user.getCompanyId(), userId);

		group.setFriendlyURL(StringPool.SLASH + screenName);

		groupPersistence.update(group, false);
		return user;
	}
	
	public User getUserByScreenName(long companyId, String screenName)
			throws PortalException, SystemException {

			screenName = getScreenName(screenName);
			
			if (_log.isDebugEnabled()) {
				_log.debug("Getting user by screen name: " + screenName);
			}

			return userPersistence.findByC_SN(companyId, screenName);
	}

//	Commenting out this method. We should use the parent's normalization method
//	protected String getScreenName(String screenName) {
//		if (_log.isDebugEnabled()) {
//			_log.debug("getScreenName(" + screenName + ") - Normalizing screenName '" + screenName + "' to '" + StringUtil.trim(screenName) + "'");
//		}
//		return StringUtil.trim(screenName);
//	}

	protected void validate(
			long userId, String screenName, String emailAddress,
			String firstName, String lastName, String smsSn)
	throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		if (!user.getScreenName().equalsIgnoreCase(screenName)) {
			_log.error("ScreenName  for userId " + userId + " has changed.  DB value = '" + user.getScreenName() +"' whereas LDAP value = '" + screenName + "'");
			validateScreenName(user.getCompanyId(), userId, screenName);
		}

		validateEmailAddress(user.getCompanyId(), emailAddress);

		if (!user.isDefaultUser()) {

			if (Validator.isNull(firstName)) {
				throw new ContactFirstNameException();
			}
			else if (Validator.isNull(lastName)) {
				throw new ContactLastNameException();
			}
		}

		if (Validator.isNotNull(smsSn) && !Validator.isEmailAddress(smsSn)) {
			throw new UserSmsException();
		}
	}

	protected void validate(
			long companyId, long userId, boolean autoPassword, String password1,
			String password2, boolean autoScreenName, String screenName,
			String emailAddress, String firstName, String lastName,
			long[] organizationIds)
		throws PortalException, SystemException {

		if (!autoScreenName) {
			validateScreenName(companyId, userId, screenName);
		}

		if (!autoPassword) {
			PasswordPolicy passwordPolicy =
				passwordPolicyLocalService.getDefaultPasswordPolicy(companyId);

			PwdToolkitUtil.validate(
				companyId, 0, password1, password2, passwordPolicy);
		}

		validateEmailAddress(companyId, emailAddress);

// duplicates are ok -- wtberks
//		if (Validator.isNotNull(emailAddress)) {
//			User user = userPersistence.fetchByC_EA(companyId, emailAddress);
//
//			if (user != null) {
//				throw new DuplicateUserEmailAddressException();
//			}
//		}

		if (Validator.isNull(firstName)) {
			throw new ContactFirstNameException();
		}
		else if (Validator.isNull(lastName)) {
			throw new ContactLastNameException();
		}
	}
	
	protected void validate(
			long userId, String screenName, String emailAddress,
			String firstName, String middleName, String lastName, String smsSn)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		if (!user.getScreenName().equalsIgnoreCase(screenName)) {
			validateScreenName(user.getCompanyId(), userId, screenName);
		}

		validateEmailAddress(user.getCompanyId(), emailAddress);

		if (!user.isDefaultUser()) {
//			duplicate emails are ok during the update
//			if (Validator.isNotNull(emailAddress) &&
//				!user.getEmailAddress().equalsIgnoreCase(emailAddress)) {
//
//				if (userPersistence.fetchByC_EA(
//						user.getCompanyId(), emailAddress) != null) {
//
//					throw new DuplicateUserEmailAddressException();
//				}
//			}

			validateFullName(
				user.getCompanyId(), firstName, middleName, lastName);
		}

		if (Validator.isNotNull(smsSn) && !Validator.isEmailAddress(smsSn)) {
			throw new UserSmsException();
		}
	}
	
	protected void validateEmailAddress(long companyId, String emailAddress)
		throws PortalException, SystemException {

		if (Validator.isNull(emailAddress) &&
			!PropsValues.USERS_EMAIL_ADDRESS_REQUIRED) {

			return;
		}

// allow stupid email names, too -- wtberks
//		if (!Validator.isEmailAddress(emailAddress) ||
//			emailAddress.startsWith("root@") ||
//			emailAddress.startsWith("postmaster@")) {
		if (!Validator.isEmailAddress(emailAddress)) {
			throw new UserEmailAddressException();
		}

		String[] reservedEmailAddresses = PrefsPropsUtil.getStringArray(
			companyId, PropsKeys.ADMIN_RESERVED_EMAIL_ADDRESSES,
			StringPool.NEW_LINE, PropsValues.ADMIN_RESERVED_EMAIL_ADDRESSES);

		for (int i = 0; i < reservedEmailAddresses.length; i++) {
			if (emailAddress.equalsIgnoreCase(reservedEmailAddresses[i])) {
				throw new ReservedUserEmailAddressException();
			}
		}
	}
	protected void validateScreenName(
			long companyId, long userId, String screenName)
		throws PortalException, SystemException {

		if (Validator.isNull(screenName)) {
			throw new UserScreenNameException();
		}

		ScreenNameValidator screenNameValidator =
			(ScreenNameValidator)InstancePool.get(
				PropsValues.USERS_SCREEN_NAME_VALIDATOR);

		if (screenNameValidator != null) {
			if (!screenNameValidator.validate(companyId, screenName)) {
				throw new UserScreenNameException();
			}
		}

// wtberks - We do not care if the user's screen name is numeric
//		if (Validator.isNumber(screenName) &&
//			!screenName.equals(String.valueOf(userId))) {
//
//			throw new UserScreenNameException();
//		}

// we also allow email addresses as a screen name (yuck!)
//
		for (char c : screenName.toCharArray()) {
			if ((!Validator.isChar(c)) && (!Validator.isDigit(c)) &&
				(c != CharPool.DASH) && (c != CharPool.PERIOD) &&
				(c != CharPool.UNDERLINE) && (c != CharPool.AT) && (c != CharPool.PLUS)) {

				_log.error("ERROR - screen name contained the invalid character '" + c + "'");
				throw new UserScreenNameException();
			}
		}

		String[] anonymousNames = PrincipalBean.ANONYMOUS_NAMES;

		for (int i = 0; i < anonymousNames.length; i++) {
			if (screenName.equalsIgnoreCase(anonymousNames[i])) {
				throw new UserScreenNameException();
			}
		}

		User user = userPersistence.fetchByC_SN(companyId, screenName);

		if (user != null) {
			throw new DuplicateUserScreenNameException();
		}

		String friendlyURL = StringPool.SLASH + screenName.replace(CharPool.AT, CharPool.DASH);

		Group group = groupPersistence.fetchByC_F(companyId, friendlyURL);

		if (group != null) {
				throw new DuplicateUserScreenNameException();
		}

		int exceptionType = LayoutImpl.validateFriendlyURL(friendlyURL);

		if (exceptionType != -1) {
			throw new UserScreenNameException(
				new GroupFriendlyURLException(exceptionType));
		}

		String[] reservedScreenNames = PrefsPropsUtil.getStringArray(
			companyId, PropsKeys.ADMIN_RESERVED_SCREEN_NAMES,
			StringPool.NEW_LINE, PropsValues.ADMIN_RESERVED_SCREEN_NAMES);

		for (int i = 0; i < reservedScreenNames.length; i++) {
			if (screenName.equalsIgnoreCase(reservedScreenNames[i])) {
				throw new ReservedUserScreenNameException();
			}
		}
	}

	@Override
	public User updatePassword(
			long userId, String password1, String password2,
			boolean passwordReset)
		throws PortalException, SystemException {

		return updatePassword(
			userId, password1, password2, passwordReset, false);
	}

	@Override
	public User updatePassword(
			long userId, String password1, String password2,
			boolean passwordReset, boolean silentUpdate)
		throws PortalException, SystemException {

		_log.debug("#### updatePassword ####");
		User user = userPersistence.findByPrimaryKey(userId);

		// Use silentUpdate so that imported user passwords are not exported,
		// tracked, or validated

		if (!silentUpdate) {
			_log.debug("### validate password ###");
			validatePassword(user.getCompanyId(), userId, password1, password2);
		}

		String oldEncPwd = user.getPassword();

		if (!user.isPasswordEncrypted()) {
			oldEncPwd = PwdEncryptor.encrypt(user.getPassword());
			_log.debug("### old password = '" + oldEncPwd + "' ###");
		}

		String newEncPwd = PwdEncryptor.encrypt(password1);

		if (user.hasCompanyMx()) {
			mailService.updatePassword(user.getCompanyId(), userId, password1);
		}

		user.setPassword(newEncPwd);
		user.setPasswordUnencrypted(password1);
		user.setPasswordEncrypted(true);
		user.setPasswordReset(passwordReset);
		user.setPasswordModifiedDate(new Date());
		user.setGraceLoginCount(0);

		if (!silentUpdate) {
			_log.debug("### set password modified to true ###");
			user.setPasswordModified(true);
		}

		try {
			userPersistence.update(user, false);
		}
		catch (Exception e) {
			_log.error(e);
		}


		boolean isCap = false;
		try {
			ExpandoValue val = ExpandoValueLocalServiceUtil.getValue(User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, "isCap", user.getUserId());
			isCap = (val == null ? Boolean.FALSE : val.getBoolean());			
		} catch (Exception e) {
			_log.error(e);
		}
		if (isCap) {
			try {
				this.exportToLDAP(user);
			} catch (Exception e) {
				_log.error("Unable to export CAP member to LDAP", e);
			}
		}
		else
			_log.debug("### User " + user.getUserId() + " is not a CAP member ###");

		return user;
	}

	private void exportToLDAP(User user) throws Exception {
		long companyId = user.getCompanyId();

		long ldapServerId = PortalLDAPUtil.getLdapServerId(
			companyId, user.getScreenName(), user.getEmailAddress());

		LdapContext ctx = PortalLDAPUtil.getContext(
			ldapServerId, companyId);


		try {
			if (ctx == null) {
				return;
			}
			_log.debug("### export to LDAP ###");
//			Properties userMappings = PortalLDAPUtil.getUserMappings(companyId);
			Properties userMappings = LDAPSettingsUtil.getUserMappings(ldapServerId, companyId);
			Binding binding = PortalLDAPUtil.getUser(
					ldapServerId, user.getCompanyId(), user.getScreenName(), user.getEmailAddress());
			String name = StringPool.BLANK;

			// Modify existing LDAP user record

			name = PortalLDAPUtil.getNameInNamespace(ldapServerId, companyId, binding);

			Modifications mods = Modifications.getInstance();

//			mods.addItem(
//				userMappings.getProperty("firstName"), user.getFirstName());
//			mods.addItem(
//				userMappings.getProperty("lastName"), user.getLastName());
//
//			String fullNameMapping = userMappings.getProperty("fullName");
			
//			if (Validator.isNotNull(fullNameMapping)) {
//				mods.addItem(fullNameMapping, user.getFullName());
//			}

			mods.addItem(
				userMappings.getProperty("password"),
				user.getPasswordUnencrypted());

//			if (Validator.isNotNull(user.getEmailAddress())) {
//				mods.addItem(
//					userMappings.getProperty("emailAddress"),
//					user.getEmailAddress());
//			}
//
//			String jobTitleMapping = userMappings.getProperty("jobTitle");
//
//			if (Validator.isNotNull(jobTitleMapping)) {
//				mods.addItem(
//					jobTitleMapping, user.getContact().getJobTitle());
//			}

			ModificationItem[] modItems = mods.getItems();

			_log.debug("### modifyAttributes ###");
			for (ModificationItem item : modItems)
				_log.debug("#### item: '" + item.toString() + "' ####");

			ctx.modifyAttributes(name, modItems);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			if (ctx != null) {
				ctx.close();
			}
		}
	}

	@Override
	public void sendPassword(
			long companyId, String emailAddress, String remoteAddr,
			String remoteHost, String userAgent, String fromName,
			String fromAddress, String subject, String body, ServiceContext serviceContext)
		throws PortalException, SystemException {

		_log.debug("*** sendPassword ***");
		try {
			doSendPassword(
				companyId, emailAddress, remoteAddr, remoteHost, userAgent,
				fromName, fromAddress, subject, body, serviceContext);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	@Override
	protected void doSendPassword(
			long companyId, String emailAddress, String remoteAddr,
			String remoteHost, String userAgent, String fromName,
			String fromAddress, String subject, String body, ServiceContext serviceContext)
		throws IOException, PortalException, SystemException {

//		if (!PrefsPropsUtil.getBoolean(
//				companyId, PropsKeys.COMPANY_SECURITY_SEND_PASSWORD) ||
//			!PrefsPropsUtil.getBoolean(
//				companyId, PropsKeys.ADMIN_EMAIL_PASSWORD_SENT_ENABLED)) {
//
//			return;
//		}

		_log.debug("*** doSendPassword ***");
		emailAddress = emailAddress.trim().toLowerCase();

		if (!Validator.isEmailAddress(emailAddress)) {
			throw new UserEmailAddressException();
		}

		Company company = companyPersistence.findByPrimaryKey(companyId);

		User user = userPersistence.findByC_EA(companyId, emailAddress);

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		/*if (user.hasCompanyMx()) {
			throw new SendPasswordException();
		}*/

		_log.debug("create a new, temporary password");
		String newPassword = PwdToolkitUtil.generate(passwordPolicy);;
		_log.debug("new password = '" + newPassword + "'");
		
		user.setPassword(PwdEncryptor.encrypt(newPassword));
		user.setPasswordUnencrypted(newPassword);
		user.setPasswordEncrypted(true);
		user.setPasswordReset(true);
		user.setPasswordModified(true);
		user.setPasswordModifiedDate(new Date());

		userPersistence.update(user, false);
		
		try {
			exportToLDAP(user);
		} catch (Exception e) {
			throw new PortalException(e);
		}

//		user.setPasswordModified(false);

		if (Validator.isNull(fromName)) {
			fromName = PrefsPropsUtil.getString(
				companyId, PropsKeys.ADMIN_EMAIL_FROM_NAME);
		}

		if (Validator.isNull(fromAddress)) {
			fromAddress = PrefsPropsUtil.getString(
				companyId, PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
		}

		String toName = user.getFullName();
		String toAddress = user.getEmailAddress();

		if (Validator.isNull(subject)) {
			subject = PrefsPropsUtil.getContent(
				companyId, PropsKeys.ADMIN_EMAIL_PASSWORD_SENT_SUBJECT);
		}

		if (Validator.isNull(body)) {
			body = PrefsPropsUtil.getContent(
				companyId, PropsKeys.ADMIN_EMAIL_PASSWORD_SENT_BODY);
		}

		subject = StringUtil.replace(
			subject,
			new String[] {
				"[$FROM_ADDRESS$]",
				"[$FROM_NAME$]",
				"[$PORTAL_URL$]",
				"[$REMOTE_ADDRESS$]",
				"[$REMOTE_HOST$]",
				"[$TO_ADDRESS$]",
				"[$TO_NAME$]",
				"[$USER_AGENT$]",
				"[$USER_ID$]",
				"[$USER_PASSWORD$]",
				"[$USER_SCREENNAME$]"
			},
			new String[] {
				fromAddress,
				fromName,
				company.getVirtualHost(),
				remoteAddr,
				remoteHost,
				toAddress,
				toName,
				HtmlUtil.escape(userAgent),
				String.valueOf(user.getUserId()),
				newPassword,
				user.getScreenName()
			});

		body = StringUtil.replace(
			body,
			new String[] {
				"[$FROM_ADDRESS$]",
				"[$FROM_NAME$]",
				"[$PORTAL_URL$]",
				"[$REMOTE_ADDRESS$]",
				"[$REMOTE_HOST$]",
				"[$TO_ADDRESS$]",
				"[$TO_NAME$]",
				"[$USER_AGENT$]",
				"[$USER_ID$]",
				"[$USER_PASSWORD$]",
				"[$USER_SCREENNAME$]",
				"[$IEEE_HOSTNAME$]",
			},
			new String[] {
				fromAddress,
				fromName,
				company.getVirtualHost(),
				remoteAddr,
				remoteHost,
				toAddress,
				toName,
				HtmlUtil.escape(userAgent),
				String.valueOf(user.getUserId()),
				newPassword,
				user.getScreenName(),
				PropsUtil.get("www.computer.org")
			});
		
		InternetAddress from = new InternetAddress(fromAddress, fromName);

		InternetAddress to = new InternetAddress(toAddress, toName);
		
		_log.debug("from = " + from);
		_log.debug("to = " + to);
		_log.debug("subject = " + subject);
		_log.debug("body\n" + body);

		MailMessage message = new MailMessage(from, to, subject, body, true);

		mailService.sendEmail(message);
	}

	
	@Override
	public boolean isPasswordExpired(User user) throws PortalException,
			SystemException {
		if (LDAPSettingsUtil.isPasswordPolicyEnabled(
				user.getCompanyId()) && user.getPasswordPolicy()!=null) {
			
			return super.isPasswordExpired(user);
		}
		return false;
	}


	private static Log _log = LogFactoryUtil.getLog(UserLocalServiceImpl.class);



}
