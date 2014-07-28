package org.computer.portal.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * <a href="ExtUserSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>org.computer.portal.service.http.ExtUserServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see org.computer.portal.service.http.ExtUserServiceSoap
 *
 */
public class ExtUserSoap implements Serializable {
    private long _userId;
    private boolean _csMember;
    private boolean _staff;
    private boolean _canCreateInstantCommunities;
    private String _societies;
    private String _publications;

    public ExtUserSoap() {
    }

    public static ExtUserSoap toSoapModel(ExtUser model) {
        ExtUserSoap soapModel = new ExtUserSoap();

        soapModel.setUserId(model.getUserId());
        soapModel.setCsMember(model.getCsMember());
        soapModel.setStaff(model.getStaff());
        soapModel.setCanCreateInstantCommunities(model.getCanCreateInstantCommunities());
        soapModel.setSocieties(model.getSocieties());
        soapModel.setPublications(model.getPublications());

        return soapModel;
    }

    public static ExtUserSoap[] toSoapModels(ExtUser[] models) {
        ExtUserSoap[] soapModels = new ExtUserSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ExtUserSoap[][] toSoapModels(ExtUser[][] models) {
        ExtUserSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ExtUserSoap[models.length][models[0].length];
        } else {
            soapModels = new ExtUserSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ExtUserSoap[] toSoapModels(List<ExtUser> models) {
        List<ExtUserSoap> soapModels = new ArrayList<ExtUserSoap>(models.size());

        for (ExtUser model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ExtUserSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _userId;
    }

    public void setPrimaryKey(long pk) {
        setUserId(pk);
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
        return _societies;
    }

    public void setSocieties(String societies) {
        _societies = societies;
    }

    public String getPublications() {
        return _publications;
    }

    public void setPublications(String publications) {
        _publications = publications;
    }
}
