/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskAssignmentInstanceModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo task assignment instance service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link KaleoTaskAssignmentInstanceUtil} to access the kaleo task assignment instance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentInstancePersistence
 * @see KaleoTaskAssignmentInstanceUtil
 * @generated
 */
public class KaleoTaskAssignmentInstancePersistenceImpl
	extends BasePersistenceImpl<KaleoTaskAssignmentInstance>
	implements KaleoTaskAssignmentInstancePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoTaskAssignmentInstanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoDefinitionId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOINSTANCEID = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByKaleoInstanceId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOTASKINSTANCETOKENID = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findBykaleoTaskInstanceTokenId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countBykaleoTaskInstanceTokenId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the kaleo task assignment instance in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskAssignmentInstance the kaleo task assignment instance to cache
	 */
	public void cacheResult(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		EntityCacheUtil.putResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			kaleoTaskAssignmentInstance.getPrimaryKey(),
			kaleoTaskAssignmentInstance);
	}

	/**
	 * Caches the kaleo task assignment instances in the entity cache if it is enabled.
	 *
	 * @param kaleoTaskAssignmentInstances the kaleo task assignment instances to cache
	 */
	public void cacheResult(
		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances) {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : kaleoTaskAssignmentInstances) {
			if (EntityCacheUtil.getResult(
						KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
						KaleoTaskAssignmentInstanceImpl.class,
						kaleoTaskAssignmentInstance.getPrimaryKey(), this) == null) {
				cacheResult(kaleoTaskAssignmentInstance);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo task assignment instances.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(KaleoTaskAssignmentInstanceImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoTaskAssignmentInstanceImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo task assignment instance.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		EntityCacheUtil.removeResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			kaleoTaskAssignmentInstance.getPrimaryKey());
	}

	/**
	 * Creates a new kaleo task assignment instance with the primary key. Does not add the kaleo task assignment instance to the database.
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key for the new kaleo task assignment instance
	 * @return the new kaleo task assignment instance
	 */
	public KaleoTaskAssignmentInstance create(
		long kaleoTaskAssignmentInstanceId) {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = new KaleoTaskAssignmentInstanceImpl();

		kaleoTaskAssignmentInstance.setNew(true);
		kaleoTaskAssignmentInstance.setPrimaryKey(kaleoTaskAssignmentInstanceId);

		return kaleoTaskAssignmentInstance;
	}

	/**
	 * Removes the kaleo task assignment instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment instance to remove
	 * @return the kaleo task assignment instance that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo task assignment instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance to remove
	 * @return the kaleo task assignment instance that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance remove(
		long kaleoTaskAssignmentInstanceId)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = (KaleoTaskAssignmentInstance)session.get(KaleoTaskAssignmentInstanceImpl.class,
					new Long(kaleoTaskAssignmentInstanceId));

			if (kaleoTaskAssignmentInstance == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoTaskAssignmentInstanceId);
				}

				throw new NoSuchTaskAssignmentInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskAssignmentInstanceId);
			}

			return remove(kaleoTaskAssignmentInstance);
		}
		catch (NoSuchTaskAssignmentInstanceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignmentInstance removeImpl(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws SystemException {
		kaleoTaskAssignmentInstance = toUnwrappedModel(kaleoTaskAssignmentInstance);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoTaskAssignmentInstance);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			kaleoTaskAssignmentInstance.getPrimaryKey());

		return kaleoTaskAssignmentInstance;
	}

	public KaleoTaskAssignmentInstance updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		boolean merge) throws SystemException {
		kaleoTaskAssignmentInstance = toUnwrappedModel(kaleoTaskAssignmentInstance);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoTaskAssignmentInstance, merge);

			kaleoTaskAssignmentInstance.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
			KaleoTaskAssignmentInstanceImpl.class,
			kaleoTaskAssignmentInstance.getPrimaryKey(),
			kaleoTaskAssignmentInstance);

		return kaleoTaskAssignmentInstance;
	}

	protected KaleoTaskAssignmentInstance toUnwrappedModel(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		if (kaleoTaskAssignmentInstance instanceof KaleoTaskAssignmentInstanceImpl) {
			return kaleoTaskAssignmentInstance;
		}

		KaleoTaskAssignmentInstanceImpl kaleoTaskAssignmentInstanceImpl = new KaleoTaskAssignmentInstanceImpl();

		kaleoTaskAssignmentInstanceImpl.setNew(kaleoTaskAssignmentInstance.isNew());
		kaleoTaskAssignmentInstanceImpl.setPrimaryKey(kaleoTaskAssignmentInstance.getPrimaryKey());

		kaleoTaskAssignmentInstanceImpl.setKaleoTaskAssignmentInstanceId(kaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId());
		kaleoTaskAssignmentInstanceImpl.setGroupId(kaleoTaskAssignmentInstance.getGroupId());
		kaleoTaskAssignmentInstanceImpl.setCompanyId(kaleoTaskAssignmentInstance.getCompanyId());
		kaleoTaskAssignmentInstanceImpl.setUserId(kaleoTaskAssignmentInstance.getUserId());
		kaleoTaskAssignmentInstanceImpl.setUserName(kaleoTaskAssignmentInstance.getUserName());
		kaleoTaskAssignmentInstanceImpl.setCreateDate(kaleoTaskAssignmentInstance.getCreateDate());
		kaleoTaskAssignmentInstanceImpl.setModifiedDate(kaleoTaskAssignmentInstance.getModifiedDate());
		kaleoTaskAssignmentInstanceImpl.setKaleoDefinitionId(kaleoTaskAssignmentInstance.getKaleoDefinitionId());
		kaleoTaskAssignmentInstanceImpl.setKaleoInstanceId(kaleoTaskAssignmentInstance.getKaleoInstanceId());
		kaleoTaskAssignmentInstanceImpl.setKaleoInstanceTokenId(kaleoTaskAssignmentInstance.getKaleoInstanceTokenId());
		kaleoTaskAssignmentInstanceImpl.setKaleoTaskInstanceTokenId(kaleoTaskAssignmentInstance.getKaleoTaskInstanceTokenId());
		kaleoTaskAssignmentInstanceImpl.setKaleoTaskId(kaleoTaskAssignmentInstance.getKaleoTaskId());
		kaleoTaskAssignmentInstanceImpl.setKaleoTaskName(kaleoTaskAssignmentInstance.getKaleoTaskName());
		kaleoTaskAssignmentInstanceImpl.setAssigneeClassName(kaleoTaskAssignmentInstance.getAssigneeClassName());
		kaleoTaskAssignmentInstanceImpl.setAssigneeClassPK(kaleoTaskAssignmentInstance.getAssigneeClassPK());
		kaleoTaskAssignmentInstanceImpl.setCompleted(kaleoTaskAssignmentInstance.isCompleted());
		kaleoTaskAssignmentInstanceImpl.setCompletionDate(kaleoTaskAssignmentInstance.getCompletionDate());

		return kaleoTaskAssignmentInstanceImpl;
	}

	/**
	 * Finds the kaleo task assignment instance with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment instance to find
	 * @return the kaleo task assignment instance
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the kaleo task assignment instance with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException} if it could not be found.
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance to find
	 * @return the kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByPrimaryKey(
		long kaleoTaskAssignmentInstanceId)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = fetchByPrimaryKey(kaleoTaskAssignmentInstanceId);

		if (kaleoTaskAssignmentInstance == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoTaskAssignmentInstanceId);
			}

			throw new NoSuchTaskAssignmentInstanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoTaskAssignmentInstanceId);
		}

		return kaleoTaskAssignmentInstance;
	}

	/**
	 * Finds the kaleo task assignment instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo task assignment instance to find
	 * @return the kaleo task assignment instance, or <code>null</code> if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance fetchByPrimaryKey(
		Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the kaleo task assignment instance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance to find
	 * @return the kaleo task assignment instance, or <code>null</code> if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance fetchByPrimaryKey(
		long kaleoTaskAssignmentInstanceId) throws SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = (KaleoTaskAssignmentInstance)EntityCacheUtil.getResult(KaleoTaskAssignmentInstanceModelImpl.ENTITY_CACHE_ENABLED,
				KaleoTaskAssignmentInstanceImpl.class,
				kaleoTaskAssignmentInstanceId, this);

		if (kaleoTaskAssignmentInstance == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoTaskAssignmentInstance = (KaleoTaskAssignmentInstance)session.get(KaleoTaskAssignmentInstanceImpl.class,
						new Long(kaleoTaskAssignmentInstanceId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoTaskAssignmentInstance != null) {
					cacheResult(kaleoTaskAssignmentInstance);
				}

				closeSession(session);
			}
		}

		return kaleoTaskAssignmentInstance;
	}

	/**
	 * Finds all the kaleo task assignment instances where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the kaleo task assignment instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of kaleo task assignment instances to return
	 * @param end the upper bound of the range of kaleo task assignment instances to return (not inclusive)
	 * @return the range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo task assignment instances where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of kaleo task assignment instances to return
	 * @param end the upper bound of the range of kaleo task assignment instances to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskAssignmentInstance> list = (List<KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo task assignment instance in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		List<KaleoTaskAssignmentInstance> list = findByCompanyId(companyId, 0,
				1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo task assignment instance in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoTaskAssignmentInstance> list = findByCompanyId(companyId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance[] findByCompanyId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = findByPrimaryKey(kaleoTaskAssignmentInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignmentInstance[] array = new KaleoTaskAssignmentInstanceImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, companyId, orderByComparator,
					true);

			array[1] = kaleoTaskAssignmentInstance;

			array[2] = getByCompanyId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, companyId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignmentInstance getByCompanyId_PrevAndNext(
		Session session,
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		long companyId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskAssignmentInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignmentInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @return the matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param start the lower bound of the range of kaleo task assignment instances to return
	 * @param end the upper bound of the range of kaleo task assignment instances to return (not inclusive)
	 * @return the range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param start the lower bound of the range of kaleo task assignment instances to return
	 * @param end the upper bound of the range of kaleo task assignment instances to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskAssignmentInstance> list = (List<KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByKaleoDefinitionId_First(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		List<KaleoTaskAssignmentInstance> list = findByKaleoDefinitionId(kaleoDefinitionId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByKaleoDefinitionId_Last(
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoTaskAssignmentInstance> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = findByPrimaryKey(kaleoTaskAssignmentInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignmentInstance[] array = new KaleoTaskAssignmentInstanceImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoDefinitionId,
					orderByComparator, true);

			array[1] = kaleoTaskAssignmentInstance;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoDefinitionId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignmentInstance getByKaleoDefinitionId_PrevAndNext(
		Session session,
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		long kaleoDefinitionId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskAssignmentInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignmentInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @return the matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @param start the lower bound of the range of kaleo task assignment instances to return
	 * @param end the upper bound of the range of kaleo task assignment instances to return (not inclusive)
	 * @return the range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @param start the lower bound of the range of kaleo task assignment instances to return
	 * @param end the upper bound of the range of kaleo task assignment instances to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskAssignmentInstance> list = (List<KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByKaleoInstanceId_First(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		List<KaleoTaskAssignmentInstance> list = findByKaleoInstanceId(kaleoInstanceId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findByKaleoInstanceId_Last(
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		List<KaleoTaskAssignmentInstance> list = findByKaleoInstanceId(kaleoInstanceId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = findByPrimaryKey(kaleoTaskAssignmentInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignmentInstance[] array = new KaleoTaskAssignmentInstanceImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoInstanceId,
					orderByComparator, true);

			array[1] = kaleoTaskAssignmentInstance;

			array[2] = getByKaleoInstanceId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoInstanceId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignmentInstance getByKaleoInstanceId_PrevAndNext(
		Session session,
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		long kaleoInstanceId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskAssignmentInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignmentInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @return the matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId) throws SystemException {
		return findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @param start the lower bound of the range of kaleo task assignment instances to return
	 * @param end the upper bound of the range of kaleo task assignment instances to return (not inclusive)
	 * @return the range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end)
		throws SystemException {
		return findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId, start,
			end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @param start the lower bound of the range of kaleo task assignment instances to return
	 * @param end the upper bound of the range of kaleo task assignment instances to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoTaskInstanceTokenId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskAssignmentInstance> list = (List<KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOTASKINSTANCETOKENID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KALEOTASKINSTANCETOKENID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KALEOTASKINSTANCETOKENID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findBykaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		List<KaleoTaskAssignmentInstance> list = findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskInstanceTokenId=");
			msg.append(kaleoTaskInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a matching kaleo task assignment instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance findBykaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		int count = countBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);

		List<KaleoTaskAssignmentInstance> list = findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskInstanceTokenId=");
			msg.append(kaleoTaskInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskAssignmentInstanceException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo task assignment instances before and after the current kaleo task assignment instance in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskAssignmentInstanceId the primary key of the current kaleo task assignment instance
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo task assignment instance
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException if a kaleo task assignment instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoTaskAssignmentInstance[] findBykaleoTaskInstanceTokenId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoTaskInstanceTokenId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskAssignmentInstanceException, SystemException {
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance = findByPrimaryKey(kaleoTaskAssignmentInstanceId);

		Session session = null;

		try {
			session = openSession();

			KaleoTaskAssignmentInstance[] array = new KaleoTaskAssignmentInstanceImpl[3];

			array[0] = getBykaleoTaskInstanceTokenId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoTaskInstanceTokenId,
					orderByComparator, true);

			array[1] = kaleoTaskAssignmentInstance;

			array[2] = getBykaleoTaskInstanceTokenId_PrevAndNext(session,
					kaleoTaskAssignmentInstance, kaleoTaskInstanceTokenId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoTaskAssignmentInstance getBykaleoTaskInstanceTokenId_PrevAndNext(
		Session session,
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		long kaleoTaskInstanceTokenId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

		query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoTaskInstanceTokenId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoTaskAssignmentInstance);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoTaskAssignmentInstance> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo task assignment instances.
	 *
	 * @return the kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findAll()
		throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo task assignment instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignment instances to return
	 * @param end the upper bound of the range of kaleo task assignment instances to return (not inclusive)
	 * @return the range of kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo task assignment instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo task assignment instances to return
	 * @param end the upper bound of the range of kaleo task assignment instances to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoTaskAssignmentInstance> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoTaskAssignmentInstance> list = (List<KaleoTaskAssignmentInstance>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE.concat(KaleoTaskAssignmentInstanceModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoTaskAssignmentInstance>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the kaleo task assignment instances where companyId = &#63; from the database.
	 *
	 * @param companyId the company id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : findByCompanyId(
				companyId)) {
			remove(kaleoTaskAssignmentInstance);
		}
	}

	/**
	 * Removes all the kaleo task assignment instances where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : findByKaleoDefinitionId(
				kaleoDefinitionId)) {
			remove(kaleoTaskAssignmentInstance);
		}
	}

	/**
	 * Removes all the kaleo task assignment instances where kaleoInstanceId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : findByKaleoInstanceId(
				kaleoInstanceId)) {
			remove(kaleoTaskAssignmentInstance);
		}
	}

	/**
	 * Removes all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63; from the database.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBykaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws SystemException {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : findBykaleoTaskInstanceTokenId(
				kaleoTaskInstanceTokenId)) {
			remove(kaleoTaskAssignmentInstance);
		}
	}

	/**
	 * Removes all the kaleo task assignment instances from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance : findAll()) {
			remove(kaleoTaskAssignmentInstance);
		}
	}

	/**
	 * Counts all the kaleo task assignment instances where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the number of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo task assignment instances where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @return the number of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo task assignment instances where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @return the number of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo task assignment instances where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @return the number of matching kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countBykaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoTaskInstanceTokenId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE_WHERE);

			query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo task assignment instances.
	 *
	 * @return the number of kaleo task assignment instances
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
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

	/**
	 * Initializes the kaleo task assignment instance persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoTaskAssignmentInstance>> listenersList = new ArrayList<ModelListener<KaleoTaskAssignmentInstance>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoTaskAssignmentInstance>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoTaskAssignmentInstanceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = KaleoActionPersistence.class)
	protected KaleoActionPersistence kaleoActionPersistence;
	@BeanReference(type = KaleoDefinitionPersistence.class)
	protected KaleoDefinitionPersistence kaleoDefinitionPersistence;
	@BeanReference(type = KaleoInstancePersistence.class)
	protected KaleoInstancePersistence kaleoInstancePersistence;
	@BeanReference(type = KaleoInstanceTokenPersistence.class)
	protected KaleoInstanceTokenPersistence kaleoInstanceTokenPersistence;
	@BeanReference(type = KaleoLogPersistence.class)
	protected KaleoLogPersistence kaleoLogPersistence;
	@BeanReference(type = KaleoNodePersistence.class)
	protected KaleoNodePersistence kaleoNodePersistence;
	@BeanReference(type = KaleoNotificationPersistence.class)
	protected KaleoNotificationPersistence kaleoNotificationPersistence;
	@BeanReference(type = KaleoNotificationRecipientPersistence.class)
	protected KaleoNotificationRecipientPersistence kaleoNotificationRecipientPersistence;
	@BeanReference(type = KaleoTaskPersistence.class)
	protected KaleoTaskPersistence kaleoTaskPersistence;
	@BeanReference(type = KaleoTaskAssignmentPersistence.class)
	protected KaleoTaskAssignmentPersistence kaleoTaskAssignmentPersistence;
	@BeanReference(type = KaleoTaskAssignmentInstancePersistence.class)
	protected KaleoTaskAssignmentInstancePersistence kaleoTaskAssignmentInstancePersistence;
	@BeanReference(type = KaleoTaskInstanceTokenPersistence.class)
	protected KaleoTaskInstanceTokenPersistence kaleoTaskInstanceTokenPersistence;
	@BeanReference(type = KaleoTransitionPersistence.class)
	protected KaleoTransitionPersistence kaleoTransitionPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE = "SELECT kaleoTaskAssignmentInstance FROM KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance";
	private static final String _SQL_SELECT_KALEOTASKASSIGNMENTINSTANCE_WHERE = "SELECT kaleoTaskAssignmentInstance FROM KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance WHERE ";
	private static final String _SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE = "SELECT COUNT(kaleoTaskAssignmentInstance) FROM KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance";
	private static final String _SQL_COUNT_KALEOTASKASSIGNMENTINSTANCE_WHERE = "SELECT COUNT(kaleoTaskAssignmentInstance) FROM KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoTaskAssignmentInstance.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoTaskAssignmentInstance.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2 =
		"kaleoTaskAssignmentInstance.kaleoInstanceId = ?";
	private static final String _FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2 =
		"kaleoTaskAssignmentInstance.kaleoTaskInstanceTokenId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoTaskAssignmentInstance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoTaskAssignmentInstance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoTaskAssignmentInstance exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoTaskAssignmentInstancePersistenceImpl.class);
}