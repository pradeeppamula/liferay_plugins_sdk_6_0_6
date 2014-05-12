/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package org.ieeecs.theme.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import org.ieeecs.theme.service.MetaTagLocalService;
import org.ieeecs.theme.service.MetaTagService;
import org.ieeecs.theme.service.ThemeStructureLocalService;
import org.ieeecs.theme.service.ThemeStructureService;

import javax.sql.DataSource;

/**
 * The base implementation of the theme structure local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link org.ieeecs.theme.service.impl.ThemeStructureLocalServiceImpl}.
 * </p>
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link org.ieeecs.theme.service.ThemeStructureLocalServiceUtil} to access the theme structure local service.
 * </p>
 *
 * @author IEEECS
 * @see org.ieeecs.theme.service.impl.ThemeStructureLocalServiceImpl
 * @see org.ieeecs.theme.service.ThemeStructureLocalServiceUtil
 * @generated
 */
public abstract class ThemeStructureLocalServiceBaseImpl
	implements ThemeStructureLocalService {
	/**
	 * Gets the meta tag local service.
	 *
	 * @return the meta tag local service
	 */
	public MetaTagLocalService getMetaTagLocalService() {
		return metaTagLocalService;
	}

	/**
	 * Sets the meta tag local service.
	 *
	 * @param metaTagLocalService the meta tag local service
	 */
	public void setMetaTagLocalService(MetaTagLocalService metaTagLocalService) {
		this.metaTagLocalService = metaTagLocalService;
	}

	/**
	 * Gets the meta tag remote service.
	 *
	 * @return the meta tag remote service
	 */
	public MetaTagService getMetaTagService() {
		return metaTagService;
	}

	/**
	 * Sets the meta tag remote service.
	 *
	 * @param metaTagService the meta tag remote service
	 */
	public void setMetaTagService(MetaTagService metaTagService) {
		this.metaTagService = metaTagService;
	}

	/**
	 * Gets the theme structure local service.
	 *
	 * @return the theme structure local service
	 */
	public ThemeStructureLocalService getThemeStructureLocalService() {
		return themeStructureLocalService;
	}

	/**
	 * Sets the theme structure local service.
	 *
	 * @param themeStructureLocalService the theme structure local service
	 */
	public void setThemeStructureLocalService(
		ThemeStructureLocalService themeStructureLocalService) {
		this.themeStructureLocalService = themeStructureLocalService;
	}

	/**
	 * Gets the theme structure remote service.
	 *
	 * @return the theme structure remote service
	 */
	public ThemeStructureService getThemeStructureService() {
		return themeStructureService;
	}

	/**
	 * Sets the theme structure remote service.
	 *
	 * @param themeStructureService the theme structure remote service
	 */
	public void setThemeStructureService(
		ThemeStructureService themeStructureService) {
		this.themeStructureService = themeStructureService;
	}

	/**
	 * Gets the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Gets the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Gets the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Gets the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Gets the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Gets the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Gets the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query to perform
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = MetaTagLocalService.class)
	protected MetaTagLocalService metaTagLocalService;
	@BeanReference(type = MetaTagService.class)
	protected MetaTagService metaTagService;
	@BeanReference(type = ThemeStructureLocalService.class)
	protected ThemeStructureLocalService themeStructureLocalService;
	@BeanReference(type = ThemeStructureService.class)
	protected ThemeStructureService themeStructureService;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}