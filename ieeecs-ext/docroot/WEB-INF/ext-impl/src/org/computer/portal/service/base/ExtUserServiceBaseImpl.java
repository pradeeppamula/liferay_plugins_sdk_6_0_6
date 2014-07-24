package org.computer.portal.service.base;


import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;

import org.computer.portal.service.ExtGroupLocalService;
import org.computer.portal.service.ExtGroupService;
import org.computer.portal.service.ExtUserLocalService;
import org.computer.portal.service.ExtUserService;
import org.computer.portal.service.persistence.ExtGroupPersistence;
import org.computer.portal.service.persistence.ExtGroupPersistenceImpl;
import org.computer.portal.service.persistence.ExtUserPersistence;
import org.computer.portal.service.persistence.ExtUserPersistenceImpl;


public abstract class ExtUserServiceBaseImpl extends PrincipalBean
    implements ExtUserService {
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
