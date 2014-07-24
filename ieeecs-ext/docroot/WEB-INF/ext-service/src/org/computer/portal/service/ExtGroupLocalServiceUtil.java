package org.computer.portal.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;


/**
 * <a href="ExtGroupLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>org.computer.portal.service.ExtGroupLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see org.computer.portal.service.ExtGroupLocalService
 *
 */
public class ExtGroupLocalServiceUtil {
    private static ExtGroupLocalService _service;

    public static org.computer.portal.model.ExtGroup addExtGroup(
        org.computer.portal.model.ExtGroup extGroup)
        throws SystemException {
        return getService().addExtGroup(extGroup);
    }

    public static org.computer.portal.model.ExtGroup createExtGroup(
        long groupId) {
        return getService().createExtGroup(groupId);
    }

    public static void deleteExtGroup(long groupId)
        throws PortalException,
            SystemException {
        getService().deleteExtGroup(groupId);
    }

    public static void deleteExtGroup(
        org.computer.portal.model.ExtGroup extGroup)
        throws SystemException {
        getService().deleteExtGroup(extGroup);
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

    public static org.computer.portal.model.ExtGroup getExtGroup(long groupId)
        throws PortalException,
            SystemException {
        return getService().getExtGroup(groupId);
    }

    public static java.util.List<org.computer.portal.model.ExtGroup> getExtGroups(
        int start, int end) throws SystemException {
        return getService().getExtGroups(start, end);
    }

    public static int getExtGroupsCount()
        throws SystemException {
        return getService().getExtGroupsCount();
    }

    public static org.computer.portal.model.ExtGroup updateExtGroup(
        org.computer.portal.model.ExtGroup extGroup)
        throws SystemException {
        return getService().updateExtGroup(extGroup);
    }

    public static org.computer.portal.model.ExtGroup updateExtGroup(
        org.computer.portal.model.ExtGroup extGroup, boolean merge)
        throws SystemException {
        return getService().updateExtGroup(extGroup, merge);
    }

    public static ExtGroupLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ExtGroupLocalService is not set");
        }

        return _service;
    }

    public void setService(ExtGroupLocalService service) {
        _service = service;
    }
}
