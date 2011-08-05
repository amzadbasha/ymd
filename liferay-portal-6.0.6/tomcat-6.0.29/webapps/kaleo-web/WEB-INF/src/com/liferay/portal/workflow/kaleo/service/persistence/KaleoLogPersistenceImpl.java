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
import com.liferay.portal.workflow.kaleo.NoSuchLogException;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoLogImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoLogModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo log service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link KaleoLogUtil} to access the kaleo log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoLogPersistence
 * @see KaleoLogUtil
 * @generated
 */
public class KaleoLogPersistenceImpl extends BasePersistenceImpl<KaleoLog>
	implements KaleoLogPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoLogImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEODEFINITIONID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKaleoDefinitionId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEODEFINITIONID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKaleoDefinitionId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOINSTANCEID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKaleoInstanceId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOINSTANCEID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKaleoInstanceId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KALEOTASKINSTANCETOKENID = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKaleoTaskInstanceTokenId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID =
		new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKaleoTaskInstanceTokenId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KITI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKITI_T",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKITI_T",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_KITI_KNI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByKITI_KNI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_KITI_KNI_T = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByKITI_KNI_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the kaleo log in the entity cache if it is enabled.
	 *
	 * @param kaleoLog the kaleo log to cache
	 */
	public void cacheResult(KaleoLog kaleoLog) {
		EntityCacheUtil.putResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogImpl.class, kaleoLog.getPrimaryKey(), kaleoLog);
	}

	/**
	 * Caches the kaleo logs in the entity cache if it is enabled.
	 *
	 * @param kaleoLogs the kaleo logs to cache
	 */
	public void cacheResult(List<KaleoLog> kaleoLogs) {
		for (KaleoLog kaleoLog : kaleoLogs) {
			if (EntityCacheUtil.getResult(
						KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
						KaleoLogImpl.class, kaleoLog.getPrimaryKey(), this) == null) {
				cacheResult(kaleoLog);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo logs.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(KaleoLogImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoLogImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo log.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(KaleoLog kaleoLog) {
		EntityCacheUtil.removeResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogImpl.class, kaleoLog.getPrimaryKey());
	}

	/**
	 * Creates a new kaleo log with the primary key. Does not add the kaleo log to the database.
	 *
	 * @param kaleoLogId the primary key for the new kaleo log
	 * @return the new kaleo log
	 */
	public KaleoLog create(long kaleoLogId) {
		KaleoLog kaleoLog = new KaleoLogImpl();

		kaleoLog.setNew(true);
		kaleoLog.setPrimaryKey(kaleoLogId);

		return kaleoLog;
	}

	/**
	 * Removes the kaleo log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo log to remove
	 * @return the kaleo log that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoLogId the primary key of the kaleo log to remove
	 * @return the kaleo log that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog remove(long kaleoLogId)
		throws NoSuchLogException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoLog kaleoLog = (KaleoLog)session.get(KaleoLogImpl.class,
					new Long(kaleoLogId));

			if (kaleoLog == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoLogId);
				}

				throw new NoSuchLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoLogId);
			}

			return remove(kaleoLog);
		}
		catch (NoSuchLogException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoLog removeImpl(KaleoLog kaleoLog) throws SystemException {
		kaleoLog = toUnwrappedModel(kaleoLog);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoLog);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogImpl.class, kaleoLog.getPrimaryKey());

		return kaleoLog;
	}

	public KaleoLog updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog, boolean merge)
		throws SystemException {
		kaleoLog = toUnwrappedModel(kaleoLog);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoLog, merge);

			kaleoLog.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
			KaleoLogImpl.class, kaleoLog.getPrimaryKey(), kaleoLog);

		return kaleoLog;
	}

	protected KaleoLog toUnwrappedModel(KaleoLog kaleoLog) {
		if (kaleoLog instanceof KaleoLogImpl) {
			return kaleoLog;
		}

		KaleoLogImpl kaleoLogImpl = new KaleoLogImpl();

		kaleoLogImpl.setNew(kaleoLog.isNew());
		kaleoLogImpl.setPrimaryKey(kaleoLog.getPrimaryKey());

		kaleoLogImpl.setKaleoLogId(kaleoLog.getKaleoLogId());
		kaleoLogImpl.setGroupId(kaleoLog.getGroupId());
		kaleoLogImpl.setCompanyId(kaleoLog.getCompanyId());
		kaleoLogImpl.setUserId(kaleoLog.getUserId());
		kaleoLogImpl.setUserName(kaleoLog.getUserName());
		kaleoLogImpl.setCreateDate(kaleoLog.getCreateDate());
		kaleoLogImpl.setModifiedDate(kaleoLog.getModifiedDate());
		kaleoLogImpl.setKaleoDefinitionId(kaleoLog.getKaleoDefinitionId());
		kaleoLogImpl.setKaleoInstanceId(kaleoLog.getKaleoInstanceId());
		kaleoLogImpl.setKaleoInstanceTokenId(kaleoLog.getKaleoInstanceTokenId());
		kaleoLogImpl.setKaleoTaskInstanceTokenId(kaleoLog.getKaleoTaskInstanceTokenId());
		kaleoLogImpl.setKaleoNodeId(kaleoLog.getKaleoNodeId());
		kaleoLogImpl.setKaleoNodeName(kaleoLog.getKaleoNodeName());
		kaleoLogImpl.setTerminalKaleoNode(kaleoLog.isTerminalKaleoNode());
		kaleoLogImpl.setKaleoActionId(kaleoLog.getKaleoActionId());
		kaleoLogImpl.setKaleoActionName(kaleoLog.getKaleoActionName());
		kaleoLogImpl.setKaleoActionDescription(kaleoLog.getKaleoActionDescription());
		kaleoLogImpl.setPreviousKaleoNodeId(kaleoLog.getPreviousKaleoNodeId());
		kaleoLogImpl.setPreviousKaleoNodeName(kaleoLog.getPreviousKaleoNodeName());
		kaleoLogImpl.setPreviousAssigneeClassName(kaleoLog.getPreviousAssigneeClassName());
		kaleoLogImpl.setPreviousAssigneeClassPK(kaleoLog.getPreviousAssigneeClassPK());
		kaleoLogImpl.setCurrentAssigneeClassName(kaleoLog.getCurrentAssigneeClassName());
		kaleoLogImpl.setCurrentAssigneeClassPK(kaleoLog.getCurrentAssigneeClassPK());
		kaleoLogImpl.setType(kaleoLog.getType());
		kaleoLogImpl.setComment(kaleoLog.getComment());
		kaleoLogImpl.setStartDate(kaleoLog.getStartDate());
		kaleoLogImpl.setEndDate(kaleoLog.getEndDate());
		kaleoLogImpl.setDuration(kaleoLog.getDuration());
		kaleoLogImpl.setWorkflowContext(kaleoLog.getWorkflowContext());

		return kaleoLogImpl;
	}

	/**
	 * Finds the kaleo log with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo log to find
	 * @return the kaleo log
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the kaleo log with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchLogException} if it could not be found.
	 *
	 * @param kaleoLogId the primary key of the kaleo log to find
	 * @return the kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByPrimaryKey(long kaleoLogId)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = fetchByPrimaryKey(kaleoLogId);

		if (kaleoLog == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoLogId);
			}

			throw new NoSuchLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoLogId);
		}

		return kaleoLog;
	}

	/**
	 * Finds the kaleo log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo log to find
	 * @return the kaleo log, or <code>null</code> if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the kaleo log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoLogId the primary key of the kaleo log to find
	 * @return the kaleo log, or <code>null</code> if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog fetchByPrimaryKey(long kaleoLogId)
		throws SystemException {
		KaleoLog kaleoLog = (KaleoLog)EntityCacheUtil.getResult(KaleoLogModelImpl.ENTITY_CACHE_ENABLED,
				KaleoLogImpl.class, kaleoLogId, this);

		if (kaleoLog == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoLog = (KaleoLog)session.get(KaleoLogImpl.class,
						new Long(kaleoLogId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoLog != null) {
					cacheResult(kaleoLog);
				}

				closeSession(session);
			}
		}

		return kaleoLog;
	}

	/**
	 * Finds all the kaleo logs where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the kaleo logs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @return the range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo logs where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Finds the first kaleo log in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<KaleoLog> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo log in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoLog> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo logs before and after the current kaleo log in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog[] findByCompanyId_PrevAndNext(long kaleoLogId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoLog, companyId,
					orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoLog, companyId,
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

	protected KaleoLog getByCompanyId_PrevAndNext(Session session,
		KaleoLog kaleoLog, long companyId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo logs where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @return the matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo logs where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @return the range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end) throws SystemException {
		return findByKaleoDefinitionId(kaleoDefinitionId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo logs where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKaleoDefinitionId(long kaleoDefinitionId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoDefinitionId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEODEFINITIONID,
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

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoDefinitionId);

				list = (List<KaleoLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Finds the first kaleo log in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByKaleoDefinitionId_First(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<KaleoLog> list = findByKaleoDefinitionId(kaleoDefinitionId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo log in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByKaleoDefinitionId_Last(long kaleoDefinitionId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByKaleoDefinitionId(kaleoDefinitionId);

		List<KaleoLog> list = findByKaleoDefinitionId(kaleoDefinitionId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoDefinitionId=");
			msg.append(kaleoDefinitionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo logs before and after the current kaleo log in the ordered set where kaleoDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog[] findByKaleoDefinitionId_PrevAndNext(long kaleoLogId,
		long kaleoDefinitionId, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByKaleoDefinitionId_PrevAndNext(session, kaleoLog,
					kaleoDefinitionId, orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByKaleoDefinitionId_PrevAndNext(session, kaleoLog,
					kaleoDefinitionId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoLog getByKaleoDefinitionId_PrevAndNext(Session session,
		KaleoLog kaleoLog, long kaleoDefinitionId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoDefinitionId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo logs where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @return the matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo logs where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @return the range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKaleoInstanceId(long kaleoInstanceId,
		int start, int end) throws SystemException {
		return findByKaleoInstanceId(kaleoInstanceId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo logs where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKaleoInstanceId(long kaleoInstanceId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOINSTANCEID,
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

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceId);

				list = (List<KaleoLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Finds the first kaleo log in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByKaleoInstanceId_First(long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<KaleoLog> list = findByKaleoInstanceId(kaleoInstanceId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo log in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByKaleoInstanceId_Last(long kaleoInstanceId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByKaleoInstanceId(kaleoInstanceId);

		List<KaleoLog> list = findByKaleoInstanceId(kaleoInstanceId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceId=");
			msg.append(kaleoInstanceId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo logs before and after the current kaleo log in the ordered set where kaleoInstanceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog[] findByKaleoInstanceId_PrevAndNext(long kaleoLogId,
		long kaleoInstanceId, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByKaleoInstanceId_PrevAndNext(session, kaleoLog,
					kaleoInstanceId, orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByKaleoInstanceId_PrevAndNext(session, kaleoLog,
					kaleoInstanceId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoLog getByKaleoInstanceId_PrevAndNext(Session session,
		KaleoLog kaleoLog, long kaleoInstanceId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo logs where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @return the matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId) throws SystemException {
		return findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo logs where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @return the range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end)
		throws SystemException {
		return findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId, start,
			end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo logs where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoTaskInstanceTokenId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KALEOTASKINSTANCETOKENID,
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

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoTaskInstanceTokenId);

				list = (List<KaleoLog>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Finds the first kaleo log in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByKaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<KaleoLog> list = findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
				0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskInstanceTokenId=");
			msg.append(kaleoTaskInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo log in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByKaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);

		List<KaleoLog> list = findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoTaskInstanceTokenId=");
			msg.append(kaleoTaskInstanceTokenId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo logs before and after the current kaleo log in the ordered set where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog[] findByKaleoTaskInstanceTokenId_PrevAndNext(
		long kaleoLogId, long kaleoTaskInstanceTokenId,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByKaleoTaskInstanceTokenId_PrevAndNext(session,
					kaleoLog, kaleoTaskInstanceTokenId, orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByKaleoTaskInstanceTokenId_PrevAndNext(session,
					kaleoLog, kaleoTaskInstanceTokenId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoLog getByKaleoTaskInstanceTokenId_PrevAndNext(
		Session session, KaleoLog kaleoLog, long kaleoTaskInstanceTokenId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoTaskInstanceTokenId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo logs where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param type the type to search with
	 * @return the matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKITI_T(long kaleoInstanceTokenId, String type)
		throws SystemException {
		return findByKITI_T(kaleoInstanceTokenId, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo logs where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param type the type to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @return the range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKITI_T(long kaleoInstanceTokenId, String type,
		int start, int end) throws SystemException {
		return findByKITI_T(kaleoInstanceTokenId, type, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo logs where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param type the type to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKITI_T(long kaleoInstanceTokenId, String type,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceTokenId, type,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KITI_T,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2);

			if (type == null) {
				query.append(_FINDER_COLUMN_KITI_T_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KITI_T_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_KITI_T_TYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				if (type != null) {
					qPos.add(type);
				}

				list = (List<KaleoLog>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KITI_T,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KITI_T,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param type the type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByKITI_T_First(long kaleoInstanceTokenId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<KaleoLog> list = findByKITI_T(kaleoInstanceTokenId, type, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param type the type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByKITI_T_Last(long kaleoInstanceTokenId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByKITI_T(kaleoInstanceTokenId, type);

		List<KaleoLog> list = findByKITI_T(kaleoInstanceTokenId, type,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo logs before and after the current kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param type the type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog[] findByKITI_T_PrevAndNext(long kaleoLogId,
		long kaleoInstanceTokenId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByKITI_T_PrevAndNext(session, kaleoLog,
					kaleoInstanceTokenId, type, orderByComparator, true);

			array[1] = kaleoLog;

			array[2] = getByKITI_T_PrevAndNext(session, kaleoLog,
					kaleoInstanceTokenId, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoLog getByKITI_T_PrevAndNext(Session session,
		KaleoLog kaleoLog, long kaleoInstanceTokenId, String type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

		query.append(_FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2);

		if (type == null) {
			query.append(_FINDER_COLUMN_KITI_T_TYPE_1);
		}
		else {
			if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KITI_T_TYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_KITI_T_TYPE_2);
			}
		}

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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceTokenId);

		if (type != null) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo logs where kaleoInstanceTokenId = &#63; and kaleoNodeId = &#63; and type = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param type the type to search with
	 * @return the matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKITI_KNI_T(long kaleoInstanceTokenId,
		long kaleoNodeId, String type) throws SystemException {
		return findByKITI_KNI_T(kaleoInstanceTokenId, kaleoNodeId, type,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo logs where kaleoInstanceTokenId = &#63; and kaleoNodeId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param type the type to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @return the range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKITI_KNI_T(long kaleoInstanceTokenId,
		long kaleoNodeId, String type, int start, int end)
		throws SystemException {
		return findByKITI_KNI_T(kaleoInstanceTokenId, kaleoNodeId, type, start,
			end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo logs where kaleoInstanceTokenId = &#63; and kaleoNodeId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param type the type to search with
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findByKITI_KNI_T(long kaleoInstanceTokenId,
		long kaleoNodeId, String type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceTokenId, kaleoNodeId, type,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_KITI_KNI_T,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KITI_KNI_T_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_KNI_T_KALEONODEID_2);

			if (type == null) {
				query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(kaleoNodeId);

				if (type != null) {
					qPos.add(type);
				}

				list = (List<KaleoLog>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_KITI_KNI_T,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_KITI_KNI_T,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and kaleoNodeId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param type the type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByKITI_KNI_T_First(long kaleoInstanceTokenId,
		long kaleoNodeId, String type, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		List<KaleoLog> list = findByKITI_KNI_T(kaleoInstanceTokenId,
				kaleoNodeId, type, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and kaleoNodeId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param type the type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a matching kaleo log could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog findByKITI_KNI_T_Last(long kaleoInstanceTokenId,
		long kaleoNodeId, String type, OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		int count = countByKITI_KNI_T(kaleoInstanceTokenId, kaleoNodeId, type);

		List<KaleoLog> list = findByKITI_KNI_T(kaleoInstanceTokenId,
				kaleoNodeId, type, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoInstanceTokenId=");
			msg.append(kaleoInstanceTokenId);

			msg.append(", kaleoNodeId=");
			msg.append(kaleoNodeId);

			msg.append(", type=");
			msg.append(type);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLogException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo logs before and after the current kaleo log in the ordered set where kaleoInstanceTokenId = &#63; and kaleoNodeId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoLogId the primary key of the current kaleo log
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param type the type to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo log
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchLogException if a kaleo log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoLog[] findByKITI_KNI_T_PrevAndNext(long kaleoLogId,
		long kaleoInstanceTokenId, long kaleoNodeId, String type,
		OrderByComparator orderByComparator)
		throws NoSuchLogException, SystemException {
		KaleoLog kaleoLog = findByPrimaryKey(kaleoLogId);

		Session session = null;

		try {
			session = openSession();

			KaleoLog[] array = new KaleoLogImpl[3];

			array[0] = getByKITI_KNI_T_PrevAndNext(session, kaleoLog,
					kaleoInstanceTokenId, kaleoNodeId, type, orderByComparator,
					true);

			array[1] = kaleoLog;

			array[2] = getByKITI_KNI_T_PrevAndNext(session, kaleoLog,
					kaleoInstanceTokenId, kaleoNodeId, type, orderByComparator,
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

	protected KaleoLog getByKITI_KNI_T_PrevAndNext(Session session,
		KaleoLog kaleoLog, long kaleoInstanceTokenId, long kaleoNodeId,
		String type, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOLOG_WHERE);

		query.append(_FINDER_COLUMN_KITI_KNI_T_KALEOINSTANCETOKENID_2);

		query.append(_FINDER_COLUMN_KITI_KNI_T_KALEONODEID_2);

		if (type == null) {
			query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_1);
		}
		else {
			if (type.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_2);
			}
		}

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
			query.append(KaleoLogModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoInstanceTokenId);

		qPos.add(kaleoNodeId);

		if (type != null) {
			qPos.add(type);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoLog);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoLog> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo logs.
	 *
	 * @return the kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @return the range of kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo logs to return
	 * @param end the upper bound of the range of kaleo logs to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoLog> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoLog> list = (List<KaleoLog>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOLOG.concat(KaleoLogModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoLog>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the kaleo logs where companyId = &#63; from the database.
	 *
	 * @param companyId the company id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoLog kaleoLog : findByCompanyId(companyId)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Removes all the kaleo logs where kaleoDefinitionId = &#63; from the database.
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		for (KaleoLog kaleoLog : findByKaleoDefinitionId(kaleoDefinitionId)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Removes all the kaleo logs where kaleoInstanceId = &#63; from the database.
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		for (KaleoLog kaleoLog : findByKaleoInstanceId(kaleoInstanceId)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Removes all the kaleo logs where kaleoTaskInstanceTokenId = &#63; from the database.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws SystemException {
		for (KaleoLog kaleoLog : findByKaleoTaskInstanceTokenId(
				kaleoTaskInstanceTokenId)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Removes all the kaleo logs where kaleoInstanceTokenId = &#63; and type = &#63; from the database.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param type the type to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKITI_T(long kaleoInstanceTokenId, String type)
		throws SystemException {
		for (KaleoLog kaleoLog : findByKITI_T(kaleoInstanceTokenId, type)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Removes all the kaleo logs where kaleoInstanceTokenId = &#63; and kaleoNodeId = &#63; and type = &#63; from the database.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param type the type to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByKITI_KNI_T(long kaleoInstanceTokenId, long kaleoNodeId,
		String type) throws SystemException {
		for (KaleoLog kaleoLog : findByKITI_KNI_T(kaleoInstanceTokenId,
				kaleoNodeId, type)) {
			remove(kaleoLog);
		}
	}

	/**
	 * Removes all the kaleo logs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoLog kaleoLog : findAll()) {
			remove(kaleoLog);
		}
	}

	/**
	 * Counts all the kaleo logs where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the number of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

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
	 * Counts all the kaleo logs where kaleoDefinitionId = &#63;.
	 *
	 * @param kaleoDefinitionId the kaleo definition id to search with
	 * @return the number of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoDefinitionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEODEFINITIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

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
	 * Counts all the kaleo logs where kaleoInstanceId = &#63;.
	 *
	 * @param kaleoInstanceId the kaleo instance id to search with
	 * @return the number of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOINSTANCEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

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
	 * Counts all the kaleo logs where kaleoTaskInstanceTokenId = &#63;.
	 *
	 * @param kaleoTaskInstanceTokenId the kaleo task instance token id to search with
	 * @return the number of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoTaskInstanceTokenId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KALEOTASKINSTANCETOKENID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

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
	 * Counts all the kaleo logs where kaleoInstanceTokenId = &#63; and type = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param type the type to search with
	 * @return the number of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKITI_T(long kaleoInstanceTokenId, String type)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoInstanceTokenId, type };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KITI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2);

			if (type == null) {
				query.append(_FINDER_COLUMN_KITI_T_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KITI_T_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_KITI_T_TYPE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				if (type != null) {
					qPos.add(type);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KITI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo logs where kaleoInstanceTokenId = &#63; and kaleoNodeId = &#63; and type = &#63;.
	 *
	 * @param kaleoInstanceTokenId the kaleo instance token id to search with
	 * @param kaleoNodeId the kaleo node id to search with
	 * @param type the type to search with
	 * @return the number of matching kaleo logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByKITI_KNI_T(long kaleoInstanceTokenId, long kaleoNodeId,
		String type) throws SystemException {
		Object[] finderArgs = new Object[] {
				kaleoInstanceTokenId, kaleoNodeId, type
			};

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_KITI_KNI_T,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEOLOG_WHERE);

			query.append(_FINDER_COLUMN_KITI_KNI_T_KALEOINSTANCETOKENID_2);

			query.append(_FINDER_COLUMN_KITI_KNI_T_KALEONODEID_2);

			if (type == null) {
				query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_1);
			}
			else {
				if (type.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_KITI_KNI_T_TYPE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoInstanceTokenId);

				qPos.add(kaleoNodeId);

				if (type != null) {
					qPos.add(type);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KITI_KNI_T,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo logs.
	 *
	 * @return the number of kaleo logs
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

				Query q = session.createQuery(_SQL_COUNT_KALEOLOG);

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
	 * Initializes the kaleo log persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoLog")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoLog>> listenersList = new ArrayList<ModelListener<KaleoLog>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoLog>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoLogImpl.class.getName());
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
	private static final String _SQL_SELECT_KALEOLOG = "SELECT kaleoLog FROM KaleoLog kaleoLog";
	private static final String _SQL_SELECT_KALEOLOG_WHERE = "SELECT kaleoLog FROM KaleoLog kaleoLog WHERE ";
	private static final String _SQL_COUNT_KALEOLOG = "SELECT COUNT(kaleoLog) FROM KaleoLog kaleoLog";
	private static final String _SQL_COUNT_KALEOLOG_WHERE = "SELECT COUNT(kaleoLog) FROM KaleoLog kaleoLog WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoLog.companyId = ?";
	private static final String _FINDER_COLUMN_KALEODEFINITIONID_KALEODEFINITIONID_2 =
		"kaleoLog.kaleoDefinitionId = ?";
	private static final String _FINDER_COLUMN_KALEOINSTANCEID_KALEOINSTANCEID_2 =
		"kaleoLog.kaleoInstanceId = ?";
	private static final String _FINDER_COLUMN_KALEOTASKINSTANCETOKENID_KALEOTASKINSTANCETOKENID_2 =
		"kaleoLog.kaleoTaskInstanceTokenId = ?";
	private static final String _FINDER_COLUMN_KITI_T_KALEOINSTANCETOKENID_2 = "kaleoLog.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_T_TYPE_1 = "kaleoLog.type IS NULL";
	private static final String _FINDER_COLUMN_KITI_T_TYPE_2 = "kaleoLog.type = ?";
	private static final String _FINDER_COLUMN_KITI_T_TYPE_3 = "(kaleoLog.type IS NULL OR kaleoLog.type = ?)";
	private static final String _FINDER_COLUMN_KITI_KNI_T_KALEOINSTANCETOKENID_2 =
		"kaleoLog.kaleoInstanceTokenId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_KNI_T_KALEONODEID_2 = "kaleoLog.kaleoNodeId = ? AND ";
	private static final String _FINDER_COLUMN_KITI_KNI_T_TYPE_1 = "kaleoLog.type IS NULL";
	private static final String _FINDER_COLUMN_KITI_KNI_T_TYPE_2 = "kaleoLog.type = ?";
	private static final String _FINDER_COLUMN_KITI_KNI_T_TYPE_3 = "(kaleoLog.type IS NULL OR kaleoLog.type = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoLog exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoLog exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoLogPersistenceImpl.class);
}