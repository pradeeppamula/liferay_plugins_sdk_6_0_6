package org.computer.portal.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface ExtGroupPersistence extends BasePersistence {
    public void cacheResult(org.computer.portal.model.ExtGroup extGroup);

    public void cacheResult(
        java.util.List<org.computer.portal.model.ExtGroup> extGroups);

    public void clearCache();

    public org.computer.portal.model.ExtGroup create(long groupId);

    public org.computer.portal.model.ExtGroup remove(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.computer.portal.NoSuchGroupException;

    public org.computer.portal.model.ExtGroup remove(
        org.computer.portal.model.ExtGroup extGroup)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
     * @deprecated Use <code>update(ExtGroup extGroup, boolean merge)</code>.
     */
    public org.computer.portal.model.ExtGroup update(
        org.computer.portal.model.ExtGroup extGroup)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public org.computer.portal.model.ExtGroup update(
        org.computer.portal.model.ExtGroup extGroup, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    public org.computer.portal.model.ExtGroup updateImpl(
        org.computer.portal.model.ExtGroup extGroup, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    public org.computer.portal.model.ExtGroup findByPrimaryKey(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            org.computer.portal.NoSuchGroupException;

    public org.computer.portal.model.ExtGroup fetchByPrimaryKey(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.computer.portal.model.ExtGroup> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.computer.portal.model.ExtGroup> findAll(
        int start, int end) throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<org.computer.portal.model.ExtGroup> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void removeAll() throws com.liferay.portal.kernel.exception.SystemException;

    public int countAll() throws com.liferay.portal.kernel.exception.SystemException;
}
