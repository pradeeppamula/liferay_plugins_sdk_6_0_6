package org.computer.portal.service.persistence;

import com.liferay.portal.kernel.exception.SystemException;

public class ExtUserUtil {
    private static ExtUserPersistence _persistence;

    public static void cacheResult(org.computer.portal.model.ExtUser extUser) {
        getPersistence().cacheResult(extUser);
    }

    public static void cacheResult(
        java.util.List<org.computer.portal.model.ExtUser> extUsers) {
        getPersistence().cacheResult(extUsers);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static org.computer.portal.model.ExtUser create(long userId) {
        return getPersistence().create(userId);
    }

    public static org.computer.portal.model.ExtUser remove(long userId)
        throws SystemException,
            org.computer.portal.NoSuchUserException {
        return getPersistence().remove(userId);
    }

    public static org.computer.portal.model.ExtUser remove(
        org.computer.portal.model.ExtUser extUser)
        throws SystemException {
        return getPersistence().remove(extUser);
    }

    /**
     * @deprecated Use <code>update(ExtUser extUser, boolean merge)</code>.
     */
    public static org.computer.portal.model.ExtUser update(
        org.computer.portal.model.ExtUser extUser)
        throws SystemException {
        return getPersistence().update(extUser);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                extUser the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when extUser is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static org.computer.portal.model.ExtUser update(
        org.computer.portal.model.ExtUser extUser, boolean merge)
        throws SystemException {
        return getPersistence().update(extUser, merge);
    }

    public static org.computer.portal.model.ExtUser updateImpl(
        org.computer.portal.model.ExtUser extUser, boolean merge)
        throws SystemException {
        return getPersistence().updateImpl(extUser, merge);
    }

    public static org.computer.portal.model.ExtUser findByPrimaryKey(
        long userId)
        throws SystemException,
            org.computer.portal.NoSuchUserException {
        return getPersistence().findByPrimaryKey(userId);
    }

    public static org.computer.portal.model.ExtUser fetchByPrimaryKey(
        long userId) throws SystemException {
        return getPersistence().fetchByPrimaryKey(userId);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    public static java.util.List<org.computer.portal.model.ExtUser> findAll()
        throws SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<org.computer.portal.model.ExtUser> findAll(
        int start, int end) throws SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<org.computer.portal.model.ExtUser> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeAll() throws SystemException {
        getPersistence().removeAll();
    }

    public static int countAll() throws SystemException {
        return getPersistence().countAll();
    }

    public static ExtUserPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ExtUserPersistence persistence) {
        _persistence = persistence;
    }
}
