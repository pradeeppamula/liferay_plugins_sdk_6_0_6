package org.computer.portal.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;


/**
 * <a href="ExtUserLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>org.computer.portal.service.ExtUserLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see org.computer.portal.service.ExtUserLocalService
 *
 */
public class ExtUserLocalServiceUtil {
    private static ExtUserLocalService _service;

    public static org.computer.portal.model.ExtUser addExtUser(
        org.computer.portal.model.ExtUser extUser)
        throws SystemException {
        return getService().addExtUser(extUser);
    }

    public static org.computer.portal.model.ExtUser createExtUser(long userId) {
        return getService().createExtUser(userId);
    }

    public static void deleteExtUser(long userId)
        throws PortalException,
            SystemException {
        getService().deleteExtUser(userId);
    }

    public static void deleteExtUser(org.computer.portal.model.ExtUser extUser)
        throws SystemException {
        getService().deleteExtUser(extUser);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static org.computer.portal.model.ExtUser getExtUser(long userId)
        throws PortalException,
            SystemException {
        return getService().getExtUser(userId);
    }

    public static java.util.List<org.computer.portal.model.ExtUser> getExtUsers(
        int start, int end) throws SystemException {
        return getService().getExtUsers(start, end);
    }

    public static int getExtUsersCount()
        throws SystemException {
        return getService().getExtUsersCount();
    }

    public static org.computer.portal.model.ExtUser updateExtUser(
        org.computer.portal.model.ExtUser extUser)
        throws SystemException {
        return getService().updateExtUser(extUser);
    }

    public static org.computer.portal.model.ExtUser updateExtUser(
        org.computer.portal.model.ExtUser extUser, boolean merge)
        throws SystemException {
        return getService().updateExtUser(extUser, merge);
    }

    public static ExtUserLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ExtUserLocalService is not set");
        }

        return _service;
    }

    public void setService(ExtUserLocalService service) {
        _service = service;
    }
}
