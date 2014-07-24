package org.computer.portal.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import org.computer.portal.model.ExtGroup;
import org.computer.portal.model.ExtGroupSoap;
import org.computer.portal.model.ExtUser;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="ExtGroupModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ExtGroup</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see org.computer.portal.model.ExtGroup
 * @see org.computer.portal.model.ExtGroupModel
 * @see org.computer.portal.model.impl.ExtGroupImpl
 *
 */
public class ExtGroupModelImpl extends BaseModelImpl<ExtGroup> {
    public static final String TABLE_NAME = "ExtGroup";
    public static final Object[][] TABLE_COLUMNS = {
            { "groupId", new Integer(Types.BIGINT) },
            
           { "instantCommunity", new Integer(Types.BOOLEAN) },
            

            { "keywords", new Integer(Types.VARCHAR) },
            

            { "modifierUserId", new Integer(Types.VARCHAR) },
            

            { "created", new Integer(Types.TIMESTAMP) },
            

            { "modified", new Integer(Types.TIMESTAMP) }
        };
    public static final String TABLE_SQL_CREATE = "create table ExtGroup (groupId LONG not null primary key, instantCommunity BOOLEAN,keywords VARCHAR(75) null,modifierUserId VARCHAR(75) null,created DATE null,modified DATE null)";
    public static final String TABLE_SQL_DROP = "drop table ExtGroup";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.org.computer.portal.model.ExtGroup"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.org.computer.portal.model.ExtGroup"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.org.computer.portal.model.ExtGroup"));
    private long _groupId;
    private boolean _instantCommunity;
    private String _keywords;
    private String _modifierUserId;
    private Date _created;
    private Date _modified;
    private transient ExpandoBridge _expandoBridge;

    public ExtGroupModelImpl() {
    }

    public static ExtGroup toModel(ExtGroupSoap soapModel) {
        ExtGroup model = new ExtGroupImpl();

        model.setGroupId(soapModel.getGroupId());
        model.setInstantCommunity(soapModel.getInstantCommunity());
        model.setKeywords(soapModel.getKeywords());
        model.setModifierUserId(soapModel.getModifierUserId());
        model.setCreated(soapModel.getCreated());
        model.setModified(soapModel.getModified());

        return model;
    }

    public static List<ExtGroup> toModels(ExtGroupSoap[] soapModels) {
        List<ExtGroup> models = new ArrayList<ExtGroup>(soapModels.length);

        for (ExtGroupSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public long getPrimaryKey() {
        return _groupId;
    }

    public void setPrimaryKey(long pk) {
        setGroupId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_groupId);
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public boolean getInstantCommunity() {
        return _instantCommunity;
    }

    public boolean isInstantCommunity() {
        return _instantCommunity;
    }

    public void setInstantCommunity(boolean instantCommunity) {
        _instantCommunity = instantCommunity;
    }

    public String getKeywords() {
        return GetterUtil.getString(_keywords);
    }

    public void setKeywords(String keywords) {
        _keywords = keywords;
    }

    public String getModifierUserId() {
        return GetterUtil.getString(_modifierUserId);
    }

    public void setModifierUserId(String modifierUserId) {
        _modifierUserId = modifierUserId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getModified() {
        return _modified;
    }

    public void setModified(Date modified) {
        _modified = modified;
    }

    public ExtGroup toEscapedModel() {
        if (isEscapedModel()) {
            return (ExtGroup) this;
        } else {
            ExtGroup model = new ExtGroupImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setGroupId(getGroupId());
            model.setInstantCommunity(getInstantCommunity());
            model.setKeywords(HtmlUtil.escape(getKeywords()));
            model.setModifierUserId(HtmlUtil.escape(getModifierUserId()));
            model.setCreated(getCreated());
            model.setModified(getModified());

            model = (ExtGroup) Proxy.newProxyInstance(ExtGroup.class.getClassLoader(),
                    new Class[] { ExtGroup.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }

    public ExpandoBridge getExpandoBridge() {
        if (_expandoBridge == null) {
            _expandoBridge = new ExpandoBridgeImpl(0, ExtGroup.class.getName(),
                    getPrimaryKey());
        }

        return _expandoBridge;
    }

    public Object clone() {
        ExtGroupImpl clone = new ExtGroupImpl();

        clone.setGroupId(getGroupId());
        clone.setInstantCommunity(getInstantCommunity());
        clone.setKeywords(getKeywords());
        clone.setModifierUserId(getModifierUserId());
        clone.setCreated(getCreated());
        clone.setModified(getModified());

        return clone;
    }

    public int compareTo(ExtGroup extGroup) {
        long pk = extGroup.getPrimaryKey();

        if (getPrimaryKey() < pk) {
            return -1;
        } else if (getPrimaryKey() > pk) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        ExtGroup extGroup = null;

        try {
            extGroup = (ExtGroup) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long pk = extGroup.getPrimaryKey();

        if (getPrimaryKey() == pk) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (int) getPrimaryKey();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{groupId=");
        sb.append(getGroupId());
        sb.append(", instantCommunity=");
        sb.append(getInstantCommunity());
        sb.append(", keywords=");
        sb.append(getKeywords());
        sb.append(", modifierUserId=");
        sb.append(getModifierUserId());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", modified=");
        sb.append(getModified());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("org.computer.portal.model.ExtGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>instantCommunity</column-name><column-value><![CDATA[");
        sb.append(getInstantCommunity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>keywords</column-name><column-value><![CDATA[");
        sb.append(getKeywords());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifierUserId</column-name><column-value><![CDATA[");
        sb.append(getModifierUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modified</column-name><column-value><![CDATA[");
        sb.append(getModified());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return ExtUser.class;
	}

	public String getModelClassName() {
		return ExtUser.class.getName();
	}
}
