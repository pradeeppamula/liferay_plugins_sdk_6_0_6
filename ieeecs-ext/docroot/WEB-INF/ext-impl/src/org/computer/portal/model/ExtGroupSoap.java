package org.computer.portal.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="ExtGroupSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>org.computer.portal.service.http.ExtGroupServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see org.computer.portal.service.http.ExtGroupServiceSoap
 *
 */
public class ExtGroupSoap implements Serializable {
    private long _groupId;
    private boolean _instantCommunity;
    private String _keywords;
    private String _modifierUserId;
    private Date _created;
    private Date _modified;

    public ExtGroupSoap() {
    }

    public static ExtGroupSoap toSoapModel(ExtGroup model) {
        ExtGroupSoap soapModel = new ExtGroupSoap();

        soapModel.setGroupId(model.getGroupId());
        soapModel.setInstantCommunity(model.getInstantCommunity());
        soapModel.setKeywords(model.getKeywords());
        soapModel.setModifierUserId(model.getModifierUserId());
        soapModel.setCreated(model.getCreated());
        soapModel.setModified(model.getModified());

        return soapModel;
    }

    public static ExtGroupSoap[] toSoapModels(ExtGroup[] models) {
        ExtGroupSoap[] soapModels = new ExtGroupSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ExtGroupSoap[][] toSoapModels(ExtGroup[][] models) {
        ExtGroupSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ExtGroupSoap[models.length][models[0].length];
        } else {
            soapModels = new ExtGroupSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ExtGroupSoap[] toSoapModels(List<ExtGroup> models) {
        List<ExtGroupSoap> soapModels = new ArrayList<ExtGroupSoap>(models.size());

        for (ExtGroup model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ExtGroupSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _groupId;
    }

    public void setPrimaryKey(long pk) {
        setGroupId(pk);
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
        return _keywords;
    }

    public void setKeywords(String keywords) {
        _keywords = keywords;
    }

    public String getModifierUserId() {
        return _modifierUserId;
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
}
