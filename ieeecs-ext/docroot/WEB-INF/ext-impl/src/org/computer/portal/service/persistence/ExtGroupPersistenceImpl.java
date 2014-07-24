package org.computer.portal.service.persistence;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.computer.portal.NoSuchGroupException;
import org.computer.portal.model.ExtGroup;
import org.computer.portal.model.impl.ExtGroupImpl;
import org.computer.portal.model.impl.ExtGroupModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ExtGroupPersistenceImpl extends BasePersistenceImpl
    implements ExtGroupPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ExtGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ExtGroupModelImpl.ENTITY_CACHE_ENABLED,
            ExtGroupModelImpl.FINDER_CACHE_ENABLED, ExtGroupImpl.class, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExtGroupModelImpl.ENTITY_CACHE_ENABLED,
            ExtGroupModelImpl.FINDER_CACHE_ENABLED, ExtGroupImpl.class, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ExtGroupPersistenceImpl.class);
    @BeanReference(name = "org.computer.portal.service.persistence.ExtGroupPersistence.impl")
    protected org.computer.portal.service.persistence.ExtGroupPersistence extGroupPersistence;
    @BeanReference(name = "org.computer.portal.service.persistence.ExtUserPersistence.impl")
    protected org.computer.portal.service.persistence.ExtUserPersistence extUserPersistence;

    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
    
    public void cacheResult(ExtGroup extGroup) {
        EntityCacheUtil.putResult(ExtGroupModelImpl.ENTITY_CACHE_ENABLED,
            ExtGroupImpl.class, extGroup.getPrimaryKey(), extGroup);
    }

    public void cacheResult(List<ExtGroup> extGroups) {
        for (ExtGroup extGroup : extGroups) {
            if (EntityCacheUtil.getResult(
                        ExtGroupModelImpl.ENTITY_CACHE_ENABLED,
                        ExtGroupImpl.class, extGroup.getPrimaryKey(), this) == null) {
                cacheResult(extGroup);
            }
        }
    }

    public void clearCache() {
    	if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExtGroupImpl.class.getName());
		}
        EntityCacheUtil.clearCache(ExtGroupImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ExtGroup create(long groupId) {
        ExtGroup extGroup = new ExtGroupImpl();

        extGroup.setNew(true);
        extGroup.setPrimaryKey(groupId);

        return extGroup;
    }

    public ExtGroup remove(long groupId)
        throws NoSuchGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ExtGroup extGroup = (ExtGroup) session.get(ExtGroupImpl.class,
                    new Long(groupId));

            if (extGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No ExtGroup exists with the primary key " +
                        groupId);
                }

                throw new NoSuchGroupException(
                    "No ExtGroup exists with the primary key " + groupId);
            }

            return remove(extGroup);
        } catch (NoSuchGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ExtGroup remove(ExtGroup extGroup) throws SystemException {
        for (ModelListener<ExtGroup> listener : listeners) {
            listener.onBeforeRemove(extGroup);
        }

        extGroup = removeImpl(extGroup);

        for (ModelListener<ExtGroup> listener : listeners) {
            listener.onAfterRemove(extGroup);
        }

        return extGroup;
    }

    protected ExtGroup removeImpl(ExtGroup extGroup) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (extGroup.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ExtGroupImpl.class,
                        extGroup.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(extGroup);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ExtGroupModelImpl.ENTITY_CACHE_ENABLED,
            ExtGroupImpl.class, extGroup.getPrimaryKey());

        return extGroup;
    }

    /**
     * @deprecated Use <code>update(ExtGroup extGroup, boolean merge)</code>.
     */
    public ExtGroup update(ExtGroup extGroup) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ExtGroup extGroup) method. Use update(ExtGroup extGroup, boolean merge) instead.");
        }

        return update(extGroup, false);
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
    public ExtGroup update(ExtGroup extGroup, boolean merge)
        throws SystemException {
        boolean isNew = extGroup.isNew();

        for (ModelListener<ExtGroup> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(extGroup);
            } else {
                listener.onBeforeUpdate(extGroup);
            }
        }

        extGroup = updateImpl(extGroup, merge);

        for (ModelListener<ExtGroup> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(extGroup);
            } else {
                listener.onAfterUpdate(extGroup);
            }
        }

        return extGroup;
    }

    public ExtGroup updateImpl(org.computer.portal.model.ExtGroup extGroup,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, extGroup, merge);

            extGroup.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ExtGroupModelImpl.ENTITY_CACHE_ENABLED,
            ExtGroupImpl.class, extGroup.getPrimaryKey(), extGroup);

        return extGroup;
    }

    public ExtGroup findByPrimaryKey(long groupId)
        throws NoSuchGroupException, SystemException {
        ExtGroup extGroup = fetchByPrimaryKey(groupId);

        if (extGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ExtGroup exists with the primary key " + groupId);
            }

            throw new NoSuchGroupException(
                "No ExtGroup exists with the primary key " + groupId);
        }

        return extGroup;
    }

    public ExtGroup fetchByPrimaryKey(long groupId) throws SystemException {
        ExtGroup extGroup = (ExtGroup) EntityCacheUtil.getResult(ExtGroupModelImpl.ENTITY_CACHE_ENABLED,
                ExtGroupImpl.class, groupId, this);

        if (extGroup == null) {
            Session session = null;

            try {
                session = openSession();

                extGroup = (ExtGroup) session.get(ExtGroupImpl.class,
                        new Long(groupId));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (extGroup != null) {
                    cacheResult(extGroup);
                }

                closeSession(session);
            }
        }

        return extGroup;
    }

    public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        Session session = null;

        try {
            session = openSession();

            dynamicQuery.compile(session);

            return dynamicQuery.list();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            dynamicQuery.setLimit(start, end);

            dynamicQuery.compile(session);

            return dynamicQuery.list();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public List<ExtGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ExtGroup> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<ExtGroup> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ExtGroup> list = (List<ExtGroup>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM org.computer.portal.model.ExtGroup ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ExtGroup>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ExtGroup>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ExtGroup>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (ExtGroup extGroup : findAll()) {
            remove(extGroup);
        }
    }

    public int countAll() throws SystemException {
        Object[] finderArgs = new Object[0];

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(
                        "SELECT COUNT(*) FROM org.computer.portal.model.ExtGroup");

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
                    count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.portal.util.PropsUtil.get(
                        "value.object.listener.org.computer.portal.model.ExtGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ExtGroup>> listenersList = new ArrayList<ModelListener<ExtGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ExtGroup>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
