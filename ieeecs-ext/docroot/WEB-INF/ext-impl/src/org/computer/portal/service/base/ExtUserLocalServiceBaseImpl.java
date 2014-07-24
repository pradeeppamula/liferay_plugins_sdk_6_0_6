package org.computer.portal.service.base;


import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

import org.computer.portal.model.ExtUser;
import org.computer.portal.service.ExtGroupLocalService;
import org.computer.portal.service.ExtGroupService;
import org.computer.portal.service.ExtUserLocalService;
import org.computer.portal.service.ExtUserService;
import org.computer.portal.service.persistence.ExtGroupPersistence;
import org.computer.portal.service.persistence.ExtUserPersistence;

import java.util.List;


public abstract class ExtUserLocalServiceBaseImpl implements ExtUserLocalService {
    @BeanReference(name = "org.computer.portal.service.ExtGroupLocalService.impl")
    protected ExtGroupLocalService extGroupLocalService;
    @BeanReference(name = "org.computer.portal.service.ExtGroupService.impl")
    protected ExtGroupService extGroupService;
    @BeanReference(name = "org.computer.portal.service.persistence.ExtGroupPersistence.impl")
    protected ExtGroupPersistence extGroupPersistence;
    @BeanReference(name = "org.computer.portal.service.ExtUserLocalService.impl")
    protected ExtUserLocalService extUserLocalService;
    @BeanReference(name = "org.computer.portal.service.ExtUserService.impl")
    protected ExtUserService extUserService;
    @BeanReference(name = "org.computer.portal.service.persistence.ExtUserPersistence.impl")
    protected ExtUserPersistence extUserPersistence;

    public ExtUser addExtUser(ExtUser extUser) throws SystemException {
        extUser.setNew(true);

        return extUserPersistence.update(extUser, false);
    }

    public ExtUser createExtUser(long userId) {
        return extUserPersistence.create(userId);
    }

    public void deleteExtUser(long userId)
        throws PortalException, SystemException {
        extUserPersistence.remove(userId);
    }

    public void deleteExtUser(ExtUser extUser) throws SystemException {
        extUserPersistence.remove(extUser);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return extUserPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return extUserPersistence.findWithDynamicQuery(dynamicQuery, start, end);
    }

    public ExtUser getExtUser(long userId)
        throws PortalException, SystemException {
        return extUserPersistence.findByPrimaryKey(userId);
    }

    public List<ExtUser> getExtUsers(int start, int end)
        throws SystemException {
        return extUserPersistence.findAll(start, end);
    }

    public int getExtUsersCount() throws SystemException {
        return extUserPersistence.countAll();
    }

    public ExtUser updateExtUser(ExtUser extUser) throws SystemException {
        extUser.setNew(false);

        return extUserPersistence.update(extUser, true);
    }

    public ExtUser updateExtUser(ExtUser extUser, boolean merge)
        throws SystemException {
        extUser.setNew(false);

        return extUserPersistence.update(extUser, merge);
    }

    public ExtGroupLocalService getExtGroupLocalService() {
        return extGroupLocalService;
    }

    public void setExtGroupLocalService(
        ExtGroupLocalService extGroupLocalService) {
        this.extGroupLocalService = extGroupLocalService;
    }

    public ExtGroupService getExtGroupService() {
        return extGroupService;
    }

    public void setExtGroupService(ExtGroupService extGroupService) {
        this.extGroupService = extGroupService;
    }

    public ExtGroupPersistence getExtGroupPersistence() {
        return extGroupPersistence;
    }

    public void setExtGroupPersistence(ExtGroupPersistence extGroupPersistence) {
        this.extGroupPersistence = extGroupPersistence;
    }

    public ExtUserLocalService getExtUserLocalService() {
        return extUserLocalService;
    }

    public void setExtUserLocalService(ExtUserLocalService extUserLocalService) {
        this.extUserLocalService = extUserLocalService;
    }

    public ExtUserService getExtUserService() {
        return extUserService;
    }

    public void setExtUserService(ExtUserService extUserService) {
        this.extUserService = extUserService;
    }

    public ExtUserPersistence getExtUserPersistence() {
        return extUserPersistence;
    }

    public void setExtUserPersistence(ExtUserPersistence extUserPersistence) {
        this.extUserPersistence = extUserPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
