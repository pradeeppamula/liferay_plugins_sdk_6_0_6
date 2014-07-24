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
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.GroupImpl;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import org.computer.portal.NoSuchUserException;
import org.computer.portal.model.ExtUser;
import org.computer.portal.model.impl.ExtUserImpl;
import org.computer.portal.model.impl.ExtUserModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ExtUserPersistenceImpl extends BasePersistenceImpl
    implements ExtUserPersistence {
    public static final String FINDER_CLASS_NAME_ENTITY = ExtUserImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
        ".List";
    public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ExtUserModelImpl.ENTITY_CACHE_ENABLED,
            ExtUserModelImpl.FINDER_CACHE_ENABLED, ExtUser.class, FINDER_CLASS_NAME_LIST,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExtUserModelImpl.ENTITY_CACHE_ENABLED,
            ExtUserModelImpl.FINDER_CACHE_ENABLED, ExtUser.class, FINDER_CLASS_NAME_LIST,
            "countAll", new String[0]);
    private static Log _log = LogFactoryUtil.getLog(ExtUserPersistenceImpl.class);
    @BeanReference(name = "org.computer.portal.service.persistence.ExtGroupPersistence.impl")
    protected org.computer.portal.service.persistence.ExtGroupPersistence extGroupPersistence;
    @BeanReference(name = "org.computer.portal.service.persistence.ExtUserPersistence.impl")
    protected org.computer.portal.service.persistence.ExtUserPersistence extUserPersistence;
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;

    public void cacheResult(ExtUser extUser) {
        EntityCacheUtil.putResult(ExtUserModelImpl.ENTITY_CACHE_ENABLED,
            ExtUserImpl.class, extUser.getPrimaryKey(), extUser);
    }

    public void cacheResult(List<ExtUser> extUsers) {
        for (ExtUser extUser : extUsers) {
            if (EntityCacheUtil.getResult(
                        ExtUserModelImpl.ENTITY_CACHE_ENABLED,
                        ExtUserImpl.class, extUser.getPrimaryKey(), this) == null) {
                cacheResult(extUser);
            }
        }
    }

    public void clearCache() {
    	if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExtUserImpl.class.getName());
		}
        EntityCacheUtil.clearCache(ExtUserImpl.class.getName());
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
    }

    public ExtUser create(long userId) {
        ExtUser extUser = new ExtUserImpl();

        extUser.setNew(true);
        extUser.setPrimaryKey(userId);

        return extUser;
    }

    public ExtUser remove(long userId)
        throws NoSuchUserException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ExtUser extUser = (ExtUser) session.get(ExtUserImpl.class,
                    new Long(userId));

            if (extUser == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn("No ExtUser exists with the primary key " +
                        userId);
                }

                throw new NoSuchUserException(
                    "No ExtUser exists with the primary key " + userId);
            }

            return remove(extUser);
        } catch (NoSuchUserException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    public ExtUser remove(ExtUser extUser) throws SystemException {
        for (ModelListener<ExtUser> listener : listeners) {
            listener.onBeforeRemove(extUser);
        }

        extUser = removeImpl(extUser);

        for (ModelListener<ExtUser> listener : listeners) {
            listener.onAfterRemove(extUser);
        }

        return extUser;
    }

    protected ExtUser removeImpl(ExtUser extUser) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            if (extUser.isCachedModel() || BatchSessionUtil.isEnabled()) {
                Object staleObject = session.get(ExtUserImpl.class,
                        extUser.getPrimaryKeyObj());

                if (staleObject != null) {
                    session.evict(staleObject);
                }
            }

            session.delete(extUser);

            session.flush();
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.removeResult(ExtUserModelImpl.ENTITY_CACHE_ENABLED,
            ExtUserImpl.class, extUser.getPrimaryKey());

        return extUser;
    }

    /**
     * @deprecated Use <code>update(ExtUser extUser, boolean merge)</code>.
     */
    public ExtUser update(ExtUser extUser) throws SystemException {
        if (_log.isWarnEnabled()) {
            _log.warn(
                "Using the deprecated update(ExtUser extUser) method. Use update(ExtUser extUser, boolean merge) instead.");
        }

        return update(extUser, false);
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
    public ExtUser update(ExtUser extUser, boolean merge)
        throws SystemException {
        boolean isNew = extUser.isNew();

        for (ModelListener<ExtUser> listener : listeners) {
            if (isNew) {
                listener.onBeforeCreate(extUser);
            } else {
                listener.onBeforeUpdate(extUser);
            }
        }

        extUser = updateImpl(extUser, merge);

        for (ModelListener<ExtUser> listener : listeners) {
            if (isNew) {
                listener.onAfterCreate(extUser);
            } else {
                listener.onAfterUpdate(extUser);
            }
        }

        return extUser;
    }

    public ExtUser updateImpl(org.computer.portal.model.ExtUser extUser,
        boolean merge) throws SystemException {
        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, extUser, merge);

            extUser.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

        EntityCacheUtil.putResult(ExtUserModelImpl.ENTITY_CACHE_ENABLED,
            ExtUserImpl.class, extUser.getPrimaryKey(), extUser);

        return extUser;
    }

    public ExtUser findByPrimaryKey(long userId)
        throws NoSuchUserException, SystemException {
        ExtUser extUser = fetchByPrimaryKey(userId);

        if (extUser == null) {
            if (_log.isWarnEnabled()) {
                _log.warn("No ExtUser exists with the primary key " + userId);
            }

            throw new NoSuchUserException(
                "No ExtUser exists with the primary key " + userId);
        }

        return extUser;
    }

    public ExtUser fetchByPrimaryKey(long userId) throws SystemException {
        ExtUser extUser = (ExtUser) EntityCacheUtil.getResult(ExtUserModelImpl.ENTITY_CACHE_ENABLED,
                ExtUserImpl.class, userId, this);
        
        if (extUser == null) {
            Session session = null;

            try {
                session = openSession();

                extUser = (ExtUser) session.get(ExtUserImpl.class,
                        new Long(userId));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (extUser != null) {
                    cacheResult(extUser);
                }

                closeSession(session);
            }
        }

        return extUser;
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

    public List<ExtUser> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    public List<ExtUser> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    public List<ExtUser> findAll(int start, int end, OrderByComparator obc)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                String.valueOf(start), String.valueOf(end), String.valueOf(obc)
            };

        List<ExtUser> list = (List<ExtUser>) FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                StringBuilder query = new StringBuilder();

                query.append("FROM org.computer.portal.model.ExtUser ");

                if (obc != null) {
                    query.append("ORDER BY ");
                    query.append(obc.getOrderBy());
                }

                Query q = session.createQuery(query.toString());

                if (obc == null) {
                    list = (List<ExtUser>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ExtUser>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    list = new ArrayList<ExtUser>();
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

                closeSession(session);
            }
        }

        return list;
    }

    public void removeAll() throws SystemException {
        for (ExtUser extUser : findAll()) {
            remove(extUser);
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
                        "SELECT COUNT(*) FROM org.computer.portal.model.ExtUser");

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
                        "value.object.listener.org.computer.portal.model.ExtUser")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ExtUser>> listenersList = new ArrayList<ModelListener<ExtUser>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ExtUser>) Class.forName(
                            listenerClassName).newInstance());
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }
}
