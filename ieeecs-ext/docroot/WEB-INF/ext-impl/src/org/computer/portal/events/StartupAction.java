/**
 * 
 */
package org.computer.portal.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;


import org.computer.utils.Constants;
/**
 * @author williamberks
 *
 */
public class StartupAction extends SimpleAction {

	/* (non-Javadoc)
	 * @see com.liferay.portal.kernel.events.SimpleAction#run(java.lang.String[])
	 */
	@Override
	public void run(String[] ids) throws ActionException {
		_log.error("run");
		try {
			this.doRun(GetterUtil.getLong(ids[0]));
		} catch (Exception e) {
			throw new ActionException(e);
		}
	}
	
	protected void doRun(long companyId) throws Exception {
		_log.error("doRun(" + companyId + ")");
		this.setupExpando(companyId);
		this.setupOrganizations(companyId);
	}
	
	protected void setupExpando(long companyId) throws Exception {
		ExpandoTable table = this.getTable(companyId);
		
		// Add the columns
		for (Column column : Column.values()) {
			this.addColumn(table, column.getName(), column.getType());
		}
	}
	
	protected void setupOrganizations(long companyId) throws Exception {
		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);
		for (Organizations organization : Organizations.values()) {
			this.setupOrganization(companyId, defaultUserId, organization.getName(), organization.getDescription());
		}
	}
	
	
	private ExpandoTable getTable(long companyId) throws Exception {
		_log.error("getTable");
		ExpandoTable table = null;
		
		// Create the table
		try {
			table = ExpandoTableLocalServiceUtil.addTable(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
			_log.error("table added");
		} catch (DuplicateTableNameException e) {
			table = ExpandoTableLocalServiceUtil.getTable(companyId, User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		return table;
	}
	
	private void addColumn(ExpandoTable table, String columnName, int type) throws Exception {
		try {
			_log.error("addColumn(" + columnName + ")");
			ExpandoColumnLocalServiceUtil.addColumn(table.getTableId(), columnName, type);
		} catch (DuplicateColumnNameException e) {
			_log.error("column alreay exists");
		}
	}
	
	private void setupOrganization(long companyId, long userId, String organizationName, String description) {
		try {
			int statusId = GetterUtil.getInteger(PortalUtil.getPortalProperties().getProperty("sql.data.com.liferay.portal.model.ListType.organization.status"));
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setAddCommunityPermissions(true);
			serviceContext.setAddGuestPermissions(true);
			Long orginizationId = OrganizationLocalServiceUtil.getOrganizationId(companyId, organizationName);
			
			Organization organization = null;
			if(orginizationId!=null && orginizationId !=0)
			{
				organization = OrganizationLocalServiceUtil.getOrganization(orginizationId);
			}
			if(organization==null)
			{
				OrganizationLocalServiceUtil.addOrganization(userId, 
						                                     OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 
						                                     organizationName, 
						                                     OrganizationConstants.TYPE_REGULAR_ORGANIZATION, 
						                                     true, 
						                                     0, 
						                                     0, 
						                                     statusId, 
						                                     description, 
						                                     serviceContext);
				_log.info("Organization " + organizationName + " created");
			}
			else
			{
				_log.info("Organization " + organizationName + " found");
			}
			
		} catch (Exception e) {
			_log.info(e);
		}
	}
	
	private void setupUserGroup(long companyId, long userId, String userGroupName, String description) {
		try {
			UserGroup userGroup = UserGroupLocalServiceUtil.getUserGroup(companyId, userGroupName);
			if(userGroup==null)
			{
				UserGroupLocalServiceUtil.addUserGroup(userId, companyId, userGroupName, description);
			}
		} catch (Exception e) {
			_log.info(e);
		}
	}
	
	private static enum Column {
		Role(Constants.IEEECS_ROLES,ExpandoColumnConstants.STRING_ARRAY),
		SafariUserName(Constants.SAFARI_USER_NAME, ExpandoColumnConstants.STRING ),
		SafariAccountId(Constants.SAFARI_ACCOUNT_ID, ExpandoColumnConstants.STRING ),
		SafariUserId(Constants.SAFARI_USER_ID, ExpandoColumnConstants.STRING ),
		SafariPremUserName(Constants.SAFARI_PREM_USER_NAME, ExpandoColumnConstants.STRING ),
		SafariPremAccountId(Constants.SAFARI_PREM_ACCOUNT_ID, ExpandoColumnConstants.STRING ),
		SafariPremUserId(Constants.SAFARI_PREM_USER_ID, ExpandoColumnConstants.STRING );
		
		private final String name;
		private final int type;
		private Column(String name, int type) {
			this.name = name;
			this.type = type;
		}
		public String getName() {
			return this.name;
		}
		public int getType() {
			return this.type;
		}
	}
	
	private static enum Organizations {
		NGIS("NGIS", "Corporate Affiliate Partner");
	
		private final String name;
		private final String description;
		private Organizations(String name, String description) {
			this.name = name;
			this.description = description;
		}

		public String getName() {
			return this.name;
		}
		public String getDescription() {
			return this.description;
		}
	}
	
	private static enum UserGroups {
		CSMember("CS Member", "Active members of the Computer Society"),
		ICOwner("IC Owner", "Able to create and own Instant Communities"),
		Staff("Staff", "IEEECS Staff"),
		CAP("CAP", "Corporate Affiliate Partner");
	
		private final String name;
		private final String description;
		private UserGroups(String name, String description) {
			this.name = name;
			this.description = description;
		}

		public String getName() {
			return this.name;
		}
		public String getDescription() {
			return this.description;
		}
	}
	
	private final static Log _log = LogFactoryUtil.getLog(StartupAction.class);

}
