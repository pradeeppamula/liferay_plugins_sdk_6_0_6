package org.computer.portal.security.ldap;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.ScreenNameGenerator;
import com.liferay.portal.security.auth.ScreenNameGeneratorFactory;
import com.liferay.portal.security.ldap.LDAPUser;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.ldap.LDAPUtil;

public class PortalLDAPImporterImpl extends com.liferay.portal.security.ldap.PortalLDAPImporterImpl {
	
	@Override
	protected User getUser(long companyId, LDAPUser ldapUser)
			throws Exception {

			User user = null;

			try {
				String authType = PrefsPropsUtil.getString(
					companyId, PropsKeys.COMPANY_SECURITY_AUTH_TYPE,
					PropsValues.COMPANY_SECURITY_AUTH_TYPE);

				if (authType.equals(CompanyConstants.AUTH_TYPE_SN) &&
					!ldapUser.isAutoScreenName()) {

//					user = UserLocalServiceUtil.getUserByScreenName(
//						companyId, ldapUser.getScreenName());
					_log.debug("Looking up by cid/openId: " + ldapUser.getOpenId());
					try {
						user = UserLocalServiceUtil.getUserByOpenId(
								companyId, ldapUser.getOpenId());
					}
					catch (NoSuchUserException ns) {
						// if open id is not set in the db, lookup by screen name
						user = UserLocalServiceUtil.getUserByScreenName(
									companyId, ldapUser.getScreenName());
					}
				}
				else {
					user = UserLocalServiceUtil.getUserByEmailAddress(
						companyId, ldapUser.getEmailAddress());
				}
			}
			catch (NoSuchUserException nsue) {
			}

			return user;
		}
	protected User updateUser(
			long companyId, LDAPUser ldapUser, User user, String password,
			String modifiedDate)
		throws Exception {


		Date ldapUserModifiedDate = null;

		try {
			if (Validator.isNull(modifiedDate)) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"LDAP entry never modified, skipping user " +
							user.getEmailAddress());
				}

				return user;
			}
			else {
				ldapUserModifiedDate = LDAPUtil.parseDate(modifiedDate);
			}

			if (ldapUserModifiedDate.equals(user.getModifiedDate()) &&
				ldapUser.isAutoPassword()) {

				if (_log.isDebugEnabled()) {
					_log.debug(
						"User is already synchronized, skipping user " +
							user.getEmailAddress());
				}

				return user;
			}
		}
		catch (ParseException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to parse LDAP modify timestamp " + modifiedDate,
					pe);
			}
		}

		Contact contact = user.getContact();

		Set<String> ldapIgnoreAttributes = SetUtil.fromArray(
			PropsValues.LDAP_USER_IGNORE_ATTRIBUTES);

		for (String attribute : ldapIgnoreAttributes) {
			Object value = BeanPropertiesUtil.getObjectSilent(user, attribute);

			if (value == null) {
				value = BeanPropertiesUtil.getObjectSilent(contact, attribute);
			}

			if (value != null) {
				BeanPropertiesUtil.setProperty(ldapUser, attribute, value);
			}
		}
		boolean passwordReset = ldapUser.isPasswordReset();

		if (PrefsPropsUtil.getBoolean(
				companyId, PropsKeys.LDAP_EXPORT_ENABLED,
				PropsValues.LDAP_EXPORT_ENABLED)) {

			passwordReset = user.isPasswordReset();
		}

		if (Validator.isNull(ldapUser.getScreenName())) {
			ldapUser.setAutoScreenName(true);
		}

		if (ldapUser.isAutoScreenName()) {
			ScreenNameGenerator screenNameGenerator =
				ScreenNameGeneratorFactory.getInstance();

			ldapUser.setScreenName(
				screenNameGenerator.generate(
					companyId, user.getUserId(), ldapUser.getEmailAddress()));
		}

		Calendar birthdayCal = CalendarFactoryUtil.getCalendar();

		birthdayCal.setTime(user.getContact().getBirthday());

		int birthdayMonth = birthdayCal.get(Calendar.MONTH);
		int birthdayDay = birthdayCal.get(Calendar.DAY_OF_MONTH);
		int birthdayYear = birthdayCal.get(Calendar.YEAR);

		if (PropsValues.LDAP_IMPORT_USER_PASSWORD_ENABLED) {
			UserLocalServiceUtil.updatePassword(
				user.getUserId(), password, password, passwordReset, true);
		}



		user = UserLocalServiceUtil.updateUser(
			user.getUserId(), password, StringPool.BLANK, StringPool.BLANK,
			passwordReset, ldapUser.getReminderQueryQuestion(),
			ldapUser.getReminderQueryAnswer(), ldapUser.getScreenName(),
			ldapUser.getEmailAddress(), ldapUser.getFacebookId(),
			ldapUser.getOpenId(), ldapUser.getLanguageId(),
			ldapUser.getTimeZoneId(), ldapUser.getGreeting(),
			ldapUser.getComments(), ldapUser.getFirstName(),
			ldapUser.getMiddleName(), ldapUser.getLastName(),
			ldapUser.getPrefixId(), ldapUser.getSuffixId(), ldapUser.isMale(),
			birthdayMonth, birthdayDay, birthdayYear, ldapUser.getSmsSn(),
			ldapUser.getAimSn(), ldapUser.getFacebookSn(), ldapUser.getIcqSn(),
			ldapUser.getJabberSn(), ldapUser.getMsnSn(),
			ldapUser.getMySpaceSn(), ldapUser.getSkypeSn(),
			ldapUser.getTwitterSn(), ldapUser.getYmSn(), ldapUser.getJobTitle(),
			ldapUser.getGroupIds(), ldapUser.getOrganizationIds(),
			ldapUser.getRoleIds(), ldapUser.getUserGroupRoles(),
			ldapUser.getUserGroupIds(), ldapUser.getServiceContext());

		if (ldapUserModifiedDate != null) {
			user = UserLocalServiceUtil.updateModifiedDate(
				user.getUserId(), ldapUserModifiedDate);
		}

		return user;
			
	}	
	private static Log _log = LogFactoryUtil.getLog(
			PortalLDAPImporterImpl.class);

}
