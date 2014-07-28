package org.computer.portal.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ExtUserModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ExtUser</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see org.computer.portal.model.ExtUser
 * @see org.computer.portal.model.impl.ExtUserImpl
 * @see org.computer.portal.model.impl.ExtUserModelImpl
 *
 */
public interface ExtUserModel extends BaseModel<ExtUser> {
    public long getPrimaryKey();

    public void setPrimaryKey(long pk);

    public long getUserId();

    public void setUserId(long userId);

    public boolean getCsMember();

    public boolean isCsMember();

    public void setCsMember(boolean csMember);

    public boolean getStaff();

    public boolean isStaff();

    public void setStaff(boolean staff);

    public boolean getCanCreateInstantCommunities();

    public boolean isCanCreateInstantCommunities();

    public void setCanCreateInstantCommunities(
        boolean canCreateInstantCommunities);

    public String getSocieties();

    public void setSocieties(String societies);

    public String getPublications();

    public void setPublications(String publications);

    public ExtUser toEscapedModel();
}
