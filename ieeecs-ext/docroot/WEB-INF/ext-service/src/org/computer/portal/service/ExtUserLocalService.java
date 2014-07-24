package org.computer.portal.service;


import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;



/**
 * <a href="ExtUserLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>org.computer.portal.service.impl.ExtUserLocalServiceImpl</code>.
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
 * @see org.computer.portal.service.ExtUserLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ExtUserLocalService {
    public org.computer.portal.model.ExtUser addExtUser(
        org.computer.portal.model.ExtUser extUser)
        throws SystemException;

    public org.computer.portal.model.ExtUser createExtUser(long userId);

    public void deleteExtUser(long userId)
        throws SystemException, PortalException;

    public void deleteExtUser(org.computer.portal.model.ExtUser extUser)
        throws SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public org.computer.portal.model.ExtUser getExtUser(long userId)
        throws SystemException, PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<org.computer.portal.model.ExtUser> getExtUsers(
        int start, int end) throws SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getExtUsersCount() throws SystemException;

    public org.computer.portal.model.ExtUser updateExtUser(
        org.computer.portal.model.ExtUser extUser)
        throws SystemException;

    public org.computer.portal.model.ExtUser updateExtUser(
        org.computer.portal.model.ExtUser extUser, boolean merge)
        throws SystemException;
}
