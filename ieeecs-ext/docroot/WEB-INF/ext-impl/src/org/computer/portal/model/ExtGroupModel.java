package org.computer.portal.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="ExtGroupModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ExtGroup</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see org.computer.portal.model.ExtGroup
 * @see org.computer.portal.model.impl.ExtGroupImpl
 * @see org.computer.portal.model.impl.ExtGroupModelImpl
 *
 */
public interface ExtGroupModel extends BaseModel<ExtGroup> {
    public long getPrimaryKey();

    public void setPrimaryKey(long pk);

    public long getGroupId();

    public void setGroupId(long groupId);

    public boolean getInstantCommunity();

    public boolean isInstantCommunity();

    public void setInstantCommunity(boolean instantCommunity);

    public String getKeywords();

    public void setKeywords(String keywords);

    public String getModifierUserId();

    public void setModifierUserId(String modifierUserId);

    public Date getCreated();

    public void setCreated(Date created);

    public Date getModified();

    public void setModified(Date modified);

    public ExtGroup toEscapedModel();
}
