package org.computer.portal.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="ExtGroupLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>org.computer.portal.service.impl.ExtGroupLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see org.computer.portal.service.ExtGroupLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ExtGroupLocalService {
    public org.computer.portal.model.ExtGroup addExtGroup(
        org.computer.portal.model.ExtGroup extGroup)
        throws SystemException;

    public org.computer.portal.model.ExtGroup createExtGroup(long groupId);

    public void deleteExtGroup(long groupId)
        throws SystemException, PortalException;

    public void deleteExtGroup(org.computer.portal.model.ExtGroup extGroup)
        throws SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.computer.portal.model.ExtGroup getExtGroup(long groupId)
        throws SystemException, PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.computer.portal.model.ExtGroup> getExtGroups(
        int start, int end) throws SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getExtGroupsCount() throws SystemException;

    public org.computer.portal.model.ExtGroup updateExtGroup(
        org.computer.portal.model.ExtGroup extGroup)
        throws SystemException;

    public org.computer.portal.model.ExtGroup updateExtGroup(
        org.computer.portal.model.ExtGroup extGroup, boolean merge)
        throws SystemException;
}
