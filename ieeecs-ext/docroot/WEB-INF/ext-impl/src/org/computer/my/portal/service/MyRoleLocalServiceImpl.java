package org.computer.my.portal.service;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.impl.RoleLocalServiceImpl;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

public class MyRoleLocalServiceImpl extends RoleLocalServiceImpl {

	/* (non-Javadoc)
	 * @see com.liferay.portal.service.impl.RoleLocalServiceImpl#hasUserRole(long, long, java.lang.String, boolean)
	 */
	@Override
	public boolean hasUserRole(long userId, long companyId, String name,
			boolean inherited) throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return  "mango".equals(name) || super.hasUserRole(userId, companyId, name, inherited);
	}
	
	/*check if roles list contains the role passed in */
	private boolean checkRole(long userId, String name) {
		List <String> roles = null; 
		try {
			roles = (List<String>) ExpandoValueLocalServiceUtil.getValue(User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME, "role", userId);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(roles != null) {
			return roles.contains(name);
		} else {
			return false;
		}
	}

}
