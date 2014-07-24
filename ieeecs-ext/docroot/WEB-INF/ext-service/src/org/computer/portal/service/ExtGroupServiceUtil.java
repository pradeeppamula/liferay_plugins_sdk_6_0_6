package org.computer.portal.service;


/**
 * <a href="ExtGroupServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>org.computer.portal.service.ExtGroupService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see org.computer.portal.service.ExtGroupService
 *
 */
public class ExtGroupServiceUtil {
    private static ExtGroupService _service;

    public static ExtGroupService getService() {
        if (_service == null) {
            throw new RuntimeException("ExtGroupService is not set");
        }

        return _service;
    }

    public void setService(ExtGroupService service) {
        _service = service;
    }
}
