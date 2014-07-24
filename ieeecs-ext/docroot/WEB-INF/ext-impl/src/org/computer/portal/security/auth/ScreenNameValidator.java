/**
 * 
 */
package org.computer.portal.security.auth;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.DefaultScreenNameValidator;

/**
 * Override the default Liferay ScreenNameValidator since theirs
 * does not allow names which contain an underscore.
 * 
 * @author wberks
 *
 */
public class ScreenNameValidator extends DefaultScreenNameValidator {

	public boolean validate(long companyId, String screenName) {
		if (Validator.isNull(screenName) ||
//				Validator.isNumber(screenName) ||
//				Validator.isEmailAddress(screenName) ||
				(screenName.equalsIgnoreCase(CYRUS)) ||
				(screenName.equalsIgnoreCase(POSTFIX)) ||
				(screenName.indexOf(StringPool.SLASH) != -1))
//				(screenName.indexOf(StringPool.UNDERLINE) != -1))
		{
			
				return false;
			}
			else {
				return true;
			}
	}
	
}
