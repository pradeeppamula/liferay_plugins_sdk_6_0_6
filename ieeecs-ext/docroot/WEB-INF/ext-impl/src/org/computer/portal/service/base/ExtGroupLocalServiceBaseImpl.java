package org.computer.portal.service.base;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import org.computer.portal.model.ExtGroup;
import org.computer.portal.service.ExtGroupLocalService;
import org.computer.portal.service.ExtGroupService;
import org.computer.portal.service.ExtUserLocalService;
import org.computer.portal.service.ExtUserService;
import org.computer.portal.service.persistence.ExtGroupPersistence;
import org.computer.portal.service.persistence.ExtUserPersistence;

import java.util.List;


public abstract class ExtGroupLocalServiceBaseImpl
    implements ExtGroupLocalService {
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

    public ExtGroup addExtGroup(ExtGroup extGroup) throws SystemException {
        extGroup.setNew(true);

        return extGroupPersistence.update(extGroup, false);
    }

    public ExtGroup createExtGroup(long groupId) {
        return extGroupPersistence.create(groupId);
    }

    public void deleteExtGroup(long groupId)
        throws PortalException, SystemException {
        extGroupPersistence.remove(groupId);
    }

    public void deleteExtGroup(ExtGroup extGroup) throws SystemException {
        extGroupPersistence.remove(extGroup);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return extGroupPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return extGroupPersistence.findWithDynamicQuery(dynamicQuery, start, end);
    }

    public ExtGroup getExtGroup(long groupId)
        throws PortalException, SystemException {
        return extGroupPersistence.findByPrimaryKey(groupId);
    }

    public List<ExtGroup> getExtGroups(int start, int end)
        throws SystemException {
        return extGroupPersistence.findAll(start, end);
    }

    public int getExtGroupsCount() throws SystemException {
        return extGroupPersistence.countAll();
    }

    public ExtGroup updateExtGroup(ExtGroup extGroup) throws SystemException {
        extGroup.setNew(false);

        return extGroupPersistence.update(extGroup, true);
    }

    public ExtGroup updateExtGroup(ExtGroup extGroup, boolean merge)
        throws SystemException {
        extGroup.setNew(false);

        return extGroupPersistence.update(extGroup, merge);
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
