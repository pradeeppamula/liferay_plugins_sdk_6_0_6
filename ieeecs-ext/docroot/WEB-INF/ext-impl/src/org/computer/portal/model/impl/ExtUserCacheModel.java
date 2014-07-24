package org.computer.portal.model.impl;

/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */


import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;



import org.computer.portal.model.ExtUser;

/**
 * The cache model class for representing User in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see User
 * @generated
 */
public class ExtUserCacheModel implements CacheModel<ExtUser> {
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

        sb.append("{userId=");
        sb.append(userId);
        sb.append(", csMember=");
        sb.append(csMember);
        sb.append(", staff=");
        sb.append(staff);
        sb.append(", canCreateInstantCommunities=");
        sb.append(canCreateInstantCommunities);
        sb.append(", societies=");
        sb.append(societies);
        sb.append(", publications=");
        sb.append(publications);
        sb.append("}");

        return sb.toString();
	}

	public ExtUser toEntityModel() {
		ExtUserImpl extUserImpl = new ExtUserImpl();

		
		extUserImpl.setUserId(userId);
		extUserImpl.setCsMember(csMember);
		extUserImpl.setStaff(staff);
		extUserImpl.setCanCreateInstantCommunities(canCreateInstantCommunities);

		
		if (societies == null) {
			extUserImpl.setSocieties(StringPool.BLANK);
		}
		else {
			extUserImpl.setSocieties(societies);
		}

		if (publications == null) {
			extUserImpl.setPublications(StringPool.BLANK);
		}
		else {
			extUserImpl.setPublications(publications);
		}

		
		return extUserImpl;
	}

	public long userId;
    public boolean csMember;
    public boolean staff;
    public boolean canCreateInstantCommunities;
    public String societies;
    public String publications;
}