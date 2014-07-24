package org.computer.portal.service.persistence;

import com.liferay.portal.kernel.exception.SystemException;

public class ExtGroupUtil {
    private static ExtGroupPersistence _persistence;

    public static void cacheResult(org.computer.portal.model.ExtGroup extGroup) {
        getPersistence().cacheResult(extGroup);
    }

    public static void cacheResult(
        java.util.List<org.computer.portal.model.ExtGroup> extGroups) {
        getPersistence().cacheResult(extGroups);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static org.computer.portal.model.ExtGroup create(long groupId) {
        return getPersistence().create(groupId);
    }

    public static org.computer.portal.model.ExtGroup remove(long groupId)
        throws SystemException,
            org.computer.portal.NoSuchGroupException {
        return getPersistence().remove(groupId);
    }

    public static org.computer.portal.model.ExtGroup remove(
        org.computer.portal.model.ExtGroup extGroup)
        throws SystemException {
        return getPersistence().remove(extGroup);
    }

    /**
     * @deprecated Use <code>update(ExtGroup extGroup, boolean merge)</code>.
     */
    public static org.computer.portal.model.ExtGroup update(
        org.computer.portal.model.ExtGroup extGroup)
        throws SystemException {
        return getPersistence().update(extGroup);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                extGroup the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when extGroup is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static org.computer.portal.model.ExtGroup update(
        org.computer.portal.model.ExtGroup extGroup, boolean merge)
        throws SystemException {
        return getPersistence().update(extGroup, merge);
    }

    public static org.computer.portal.model.ExtGroup updateImpl(
        org.computer.portal.model.ExtGroup extGroup, boolean merge)
        throws SystemException {
        return getPersistence().updateImpl(extGroup, merge);
    }

    public static org.computer.portal.model.ExtGroup findByPrimaryKey(
        long groupId)
        throws SystemException,
            org.computer.portal.NoSuchGroupException {
        return getPersistence().findByPrimaryKey(groupId);
    }

    public static org.computer.portal.model.ExtGroup fetchByPrimaryKey(
        long groupId) throws SystemException {
        return getPersistence().fetchByPrimaryKey(groupId);
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

    public static java.util.List<org.computer.portal.model.ExtGroup> findAll()
        throws SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<org.computer.portal.model.ExtGroup> findAll(
        int start, int end) throws SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<org.computer.portal.model.ExtGroup> findAll(
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

    public static ExtGroupPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ExtGroupPersistence persistence) {
        _persistence = persistence;
    }
}
