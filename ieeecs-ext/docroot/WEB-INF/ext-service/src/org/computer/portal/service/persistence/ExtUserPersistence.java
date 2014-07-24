package org.computer.portal.service.persistence;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;


public interface ExtUserPersistence extends BasePersistence {
    public void cacheResult(org.computer.portal.model.ExtUser extUser);

    public void cacheResult(
        java.util.List<org.computer.portal.model.ExtUser> extUsers);

    public void clearCache();

    public org.computer.portal.model.ExtUser create(long userId);

    public org.computer.portal.model.ExtUser remove(long userId)
        throws SystemException,
            org.computer.portal.NoSuchUserException;

    public org.computer.portal.model.ExtUser remove(
        org.computer.portal.model.ExtUser extUser)
        throws SystemException;

    /**
     * @deprecated Use <code>update(ExtUser extUser, boolean merge)</code>.
     */
    public org.computer.portal.model.ExtUser update(
        org.computer.portal.model.ExtUser extUser)
        throws SystemException;

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
    public org.computer.portal.model.ExtUser update(
        org.computer.portal.model.ExtUser extUser, boolean merge)
        throws SystemException;

    public org.computer.portal.model.ExtUser updateImpl(
        org.computer.portal.model.ExtUser extUser, boolean merge)
        throws SystemException;

    public org.computer.portal.model.ExtUser findByPrimaryKey(long userId)
        throws SystemException,
            org.computer.portal.NoSuchUserException;

    public org.computer.portal.model.ExtUser fetchByPrimaryKey(long userId)
        throws SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws SystemException;

    public java.util.List<org.computer.portal.model.ExtUser> findAll()
        throws SystemException;

    public java.util.List<org.computer.portal.model.ExtUser> findAll(
        int start, int end) throws SystemException;

    public java.util.List<org.computer.portal.model.ExtUser> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws SystemException;

    public void removeAll() throws SystemException;

    public int countAll() throws SystemException;
}
