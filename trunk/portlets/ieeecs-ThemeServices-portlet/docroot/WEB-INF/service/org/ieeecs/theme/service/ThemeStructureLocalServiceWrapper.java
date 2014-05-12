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

/**
 * <p>
 * This class is a wrapper for {@link ThemeStructureLocalService}.
 * </p>
 *
 * @author    IEEECS
 * @see       ThemeStructureLocalService
 * @generated
 */
public class ThemeStructureLocalServiceWrapper
	implements ThemeStructureLocalService {
	public ThemeStructureLocalServiceWrapper(
		ThemeStructureLocalService themeStructureLocalService) {
		_themeStructureLocalService = themeStructureLocalService;
	}

	public java.util.Map<java.lang.String, java.lang.String> getThemeStructureSections(
		java.lang.String communityName, java.lang.String currentUrl) {
		return _themeStructureLocalService.getThemeStructureSections(communityName,
			currentUrl);
	}

	public ThemeStructureLocalService getWrappedThemeStructureLocalService() {
		return _themeStructureLocalService;
	}

	private ThemeStructureLocalService _themeStructureLocalService;
}