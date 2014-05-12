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

package org.ieeecs.theme.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the theme structure local service. This utility wraps {@link org.ieeecs.theme.service.impl.ThemeStructureLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link org.ieeecs.theme.service.impl.ThemeStructureLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author IEEECS
 * @see ThemeStructureLocalService
 * @see org.ieeecs.theme.service.base.ThemeStructureLocalServiceBaseImpl
 * @see org.ieeecs.theme.service.impl.ThemeStructureLocalServiceImpl
 * @generated
 */
public class ThemeStructureLocalServiceUtil {
	public static java.util.Map<java.lang.String, java.lang.String> getThemeStructureSections(
		java.lang.String communityName, java.lang.String currentUrl) {
		return getService().getThemeStructureSections(communityName, currentUrl);
	}

	public static void clearService() {
		_service = null;
	}

	public static ThemeStructureLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					ThemeStructureLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new ThemeStructureLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(ThemeStructureLocalService service) {
		_service = service;
	}

	private static ThemeStructureLocalService _service;
}