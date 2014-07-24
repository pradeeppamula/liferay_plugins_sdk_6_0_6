package org.computer.portal.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import org.computer.portal.model.ExtUser;
import org.computer.portal.model.ExtUserModel;
import org.computer.portal.model.ExtUserSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ExtUserModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a model that represents the <code>ExtUser</code> table
 * in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see org.computer.portal.model.ExtUser
 * @see org.computer.portal.model.ExtUserModel
 * @see org.computer.portal.model.impl.ExtUserImpl
 *
 */
public class ExtUserModelImpl extends BaseModelImpl<ExtUser> implements ExtUserModel{
    public static final String TABLE_NAME = "ExtUser";
    public static final Object[][] TABLE_COLUMNS = {
            { "userId", new Integer(Types.BIGINT) },
            

            { "csMember", new Integer(Types.BOOLEAN) },
            

            { "staff", new Integer(Types.BOOLEAN) },
            

            { "canCreateInstantCommunities", new Integer(Types.BOOLEAN) },
            

            { "societies", new Integer(Types.VARCHAR) },
            

            { "publications", new Integer(Types.VARCHAR) }
        };
    public static final String TABLE_SQL_CREATE = "create table ExtUser (userId LONG not null primary key,csMember BOOLEAN,staff BOOLEAN,canCreateInstantCommunities BOOLEAN,societies VARCHAR(75) null,publications VARCHAR(75) null)";
    public static final String TABLE_SQL_DROP = "drop table ExtUser";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.entity.cache.enabled.org.computer.portal.model.ExtUser"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
                "value.object.finder.cache.enabled.org.computer.portal.model.ExtUser"),
            true);
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
                "lock.expiration.time.org.computer.portal.model.ExtUser"));
    private long _userId;
    private boolean _csMember;
    private boolean _staff;
    private boolean _canCreateInstantCommunities;
    private String _societies;
    private String _publications;
    private transient ExpandoBridge _expandoBridge;

    public ExtUserModelImpl() {
    }

    public static ExtUser toModel(ExtUserSoap soapModel) {
        ExtUser model = new ExtUserImpl();

        model.setUserId(soapModel.getUserId());
        model.setCsMember(soapModel.getCsMember());
        model.setStaff(soapModel.getStaff());
        model.setCanCreateInstantCommunities(soapModel.getCanCreateInstantCommunities());
        model.setSocieties(soapModel.getSocieties());
        model.setPublications(soapModel.getPublications());

        return model;
    }

    public static List<ExtUser> toModels(ExtUserSoap[] soapModels) {
        List<ExtUser> models = new ArrayList<ExtUser>(soapModels.length);

        for (ExtUserSoap soapModel : soapModels) {
            models.add(toModel(soapModel));
        }

        return models;
    }

    public long getPrimaryKey() {
        return _userId;
    }

    public void setPrimaryKey(long pk) {
        setUserId(pk);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_userId);
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;
    }

    public boolean getCsMember() {
        return _csMember;
    }

    public boolean isCsMember() {
        return _csMember;
    }

    public void setCsMember(boolean csMember) {
        _csMember = csMember;
    }

    public boolean getStaff() {
        return _staff;
    }

    public boolean isStaff() {
        return _staff;
    }

    public void setStaff(boolean staff) {
        _staff = staff;
    }

    public boolean getCanCreateInstantCommunities() {
        return _canCreateInstantCommunities;
    }

    public boolean isCanCreateInstantCommunities() {
        return _canCreateInstantCommunities;
    }

    public void setCanCreateInstantCommunities(
        boolean canCreateInstantCommunities) {
        _canCreateInstantCommunities = canCreateInstantCommunities;
    }

    public String getSocieties() {
        return GetterUtil.getString(_societies);
    }

    public void setSocieties(String societies) {
        _societies = societies;
    }

    public String getPublications() {
        return GetterUtil.getString(_publications);
    }

    public void setPublications(String publications) {
        _publications = publications;
    }

    public ExtUser toEscapedModel() {
        if (isEscapedModel()) {
            return (ExtUser) this;
        } else {
            ExtUser model = new ExtUserImpl();

            model.setNew(isNew());
            model.setEscapedModel(true);

            model.setUserId(getUserId());
            model.setCsMember(getCsMember());
            model.setStaff(getStaff());
            model.setCanCreateInstantCommunities(getCanCreateInstantCommunities());
            model.setSocieties(HtmlUtil.escape(getSocieties()));
            model.setPublications(HtmlUtil.escape(getPublications()));

            model = (ExtUser) Proxy.newProxyInstance(ExtUser.class.getClassLoader(),
                    new Class[] { ExtUser.class },
                    new ReadOnlyBeanHandler(model));

            return model;
        }
    }
    
    @Override
	public CacheModel<ExtUser> toCacheModel() {
    	ExtUserCacheModel extUserCacheModel = new ExtUserCacheModel();
    	
    	extUserCacheModel.userId = getUserId();
    	extUserCacheModel.csMember = isCsMember();
    	extUserCacheModel.canCreateInstantCommunities = getCanCreateInstantCommunities();
    	extUserCacheModel.staff = isStaff();
    	extUserCacheModel.societies = getSocieties();
    	extUserCacheModel.publications = getPublications();
    	
    	return extUserCacheModel;
    }

    public ExpandoBridge getExpandoBridge() {
        if (_expandoBridge == null) {
            _expandoBridge = new ExpandoBridgeImpl(0, ExtUser.class.getName(),
                    getPrimaryKey());
        }

        return _expandoBridge;
    }

    public Object clone() {
        ExtUserImpl clone = new ExtUserImpl();

        clone.setUserId(getUserId());
        clone.setCsMember(getCsMember());
        clone.setStaff(getStaff());
        clone.setCanCreateInstantCommunities(getCanCreateInstantCommunities());
        clone.setSocieties(getSocieties());
        clone.setPublications(getPublications());

        return clone;
    }

    public int compareTo(ExtUser extUser) {
        long pk = extUser.getPrimaryKey();

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

        ExtUser extUser = null;

        try {
            extUser = (ExtUser) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long pk = extUser.getPrimaryKey();

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

        sb.append("{userId=");
        sb.append(getUserId());
        sb.append(", csMember=");
        sb.append(getCsMember());
        sb.append(", staff=");
        sb.append(getStaff());
        sb.append(", canCreateInstantCommunities=");
        sb.append(getCanCreateInstantCommunities());
        sb.append(", societies=");
        sb.append(getSocieties());
        sb.append(", publications=");
        sb.append(getPublications());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<model><model-name>");
        sb.append("org.computer.portal.model.ExtUser");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>csMember</column-name><column-value><![CDATA[");
        sb.append(getCsMember());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>staff</column-name><column-value><![CDATA[");
        sb.append(getStaff());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>canCreateInstantCommunities</column-name><column-value><![CDATA[");
        sb.append(getCanCreateInstantCommunities());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>societies</column-name><column-value><![CDATA[");
        sb.append(getSocieties());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>publications</column-name><column-value><![CDATA[");
        sb.append(getPublications());
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
