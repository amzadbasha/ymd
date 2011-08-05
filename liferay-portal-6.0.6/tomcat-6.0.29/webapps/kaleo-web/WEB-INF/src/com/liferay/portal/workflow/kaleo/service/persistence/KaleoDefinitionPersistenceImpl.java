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
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.NoSuchDefinitionException;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl;
import com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo definition service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link KaleoDefinitionUtil} to access the kaleo definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoDefinitionPersistence
 * @see KaleoDefinitionUtil
 * @generated
 */
public class KaleoDefinitionPersistenceImpl extends BasePersistenceImpl<KaleoDefinition>
	implements KaleoDefinitionPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_N = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_N",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_N",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_A = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_A = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_C_N_V = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_V = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_BY_C_N_A = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_A = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_N_A",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the kaleo definition in the entity cache if it is enabled.
	 *
	 * @param kaleoDefinition the kaleo definition to cache
	 */
	public void cacheResult(KaleoDefinition kaleoDefinition) {
		EntityCacheUtil.putResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionImpl.class, kaleoDefinition.getPrimaryKey(),
			kaleoDefinition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
			new Object[] {
				new Long(kaleoDefinition.getCompanyId()),
				
			kaleoDefinition.getName(), new Integer(kaleoDefinition.getVersion())
			}, kaleoDefinition);
	}

	/**
	 * Caches the kaleo definitions in the entity cache if it is enabled.
	 *
	 * @param kaleoDefinitions the kaleo definitions to cache
	 */
	public void cacheResult(List<KaleoDefinition> kaleoDefinitions) {
		for (KaleoDefinition kaleoDefinition : kaleoDefinitions) {
			if (EntityCacheUtil.getResult(
						KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoDefinitionImpl.class,
						kaleoDefinition.getPrimaryKey(), this) == null) {
				cacheResult(kaleoDefinition);
			}
		}
	}

	/**
	 * Clears the cache for all kaleo definitions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(KaleoDefinitionImpl.class.getName());
		EntityCacheUtil.clearCache(KaleoDefinitionImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the kaleo definition.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(KaleoDefinition kaleoDefinition) {
		EntityCacheUtil.removeResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionImpl.class, kaleoDefinition.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V,
			new Object[] {
				new Long(kaleoDefinition.getCompanyId()),
				
			kaleoDefinition.getName(), new Integer(kaleoDefinition.getVersion())
			});
	}

	/**
	 * Creates a new kaleo definition with the primary key. Does not add the kaleo definition to the database.
	 *
	 * @param kaleoDefinitionId the primary key for the new kaleo definition
	 * @return the new kaleo definition
	 */
	public KaleoDefinition create(long kaleoDefinitionId) {
		KaleoDefinition kaleoDefinition = new KaleoDefinitionImpl();

		kaleoDefinition.setNew(true);
		kaleoDefinition.setPrimaryKey(kaleoDefinitionId);

		return kaleoDefinition;
	}

	/**
	 * Removes the kaleo definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo definition to remove
	 * @return the kaleo definition that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the kaleo definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition to remove
	 * @return the kaleo definition that was removed
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition remove(long kaleoDefinitionId)
		throws NoSuchDefinitionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoDefinition kaleoDefinition = (KaleoDefinition)session.get(KaleoDefinitionImpl.class,
					new Long(kaleoDefinitionId));

			if (kaleoDefinition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						kaleoDefinitionId);
				}

				throw new NoSuchDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					kaleoDefinitionId);
			}

			return remove(kaleoDefinition);
		}
		catch (NoSuchDefinitionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDefinition removeImpl(KaleoDefinition kaleoDefinition)
		throws SystemException {
		kaleoDefinition = toUnwrappedModel(kaleoDefinition);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, kaleoDefinition);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		KaleoDefinitionModelImpl kaleoDefinitionModelImpl = (KaleoDefinitionModelImpl)kaleoDefinition;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V,
			new Object[] {
				new Long(kaleoDefinitionModelImpl.getCompanyId()),
				
			kaleoDefinitionModelImpl.getName(),
				new Integer(kaleoDefinitionModelImpl.getVersion())
			});

		EntityCacheUtil.removeResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionImpl.class, kaleoDefinition.getPrimaryKey());

		return kaleoDefinition;
	}

	public KaleoDefinition updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition,
		boolean merge) throws SystemException {
		kaleoDefinition = toUnwrappedModel(kaleoDefinition);

		boolean isNew = kaleoDefinition.isNew();

		KaleoDefinitionModelImpl kaleoDefinitionModelImpl = (KaleoDefinitionModelImpl)kaleoDefinition;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, kaleoDefinition, merge);

			kaleoDefinition.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDefinitionImpl.class, kaleoDefinition.getPrimaryKey(),
			kaleoDefinition);

		if (!isNew &&
				((kaleoDefinition.getCompanyId() != kaleoDefinitionModelImpl.getOriginalCompanyId()) ||
				!Validator.equals(kaleoDefinition.getName(),
					kaleoDefinitionModelImpl.getOriginalName()) ||
				(kaleoDefinition.getVersion() != kaleoDefinitionModelImpl.getOriginalVersion()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V,
				new Object[] {
					new Long(kaleoDefinitionModelImpl.getOriginalCompanyId()),
					
				kaleoDefinitionModelImpl.getOriginalName(),
					new Integer(kaleoDefinitionModelImpl.getOriginalVersion())
				});
		}

		if (isNew ||
				((kaleoDefinition.getCompanyId() != kaleoDefinitionModelImpl.getOriginalCompanyId()) ||
				!Validator.equals(kaleoDefinition.getName(),
					kaleoDefinitionModelImpl.getOriginalName()) ||
				(kaleoDefinition.getVersion() != kaleoDefinitionModelImpl.getOriginalVersion()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
				new Object[] {
					new Long(kaleoDefinition.getCompanyId()),
					
				kaleoDefinition.getName(),
					new Integer(kaleoDefinition.getVersion())
				}, kaleoDefinition);
		}

		return kaleoDefinition;
	}

	protected KaleoDefinition toUnwrappedModel(KaleoDefinition kaleoDefinition) {
		if (kaleoDefinition instanceof KaleoDefinitionImpl) {
			return kaleoDefinition;
		}

		KaleoDefinitionImpl kaleoDefinitionImpl = new KaleoDefinitionImpl();

		kaleoDefinitionImpl.setNew(kaleoDefinition.isNew());
		kaleoDefinitionImpl.setPrimaryKey(kaleoDefinition.getPrimaryKey());

		kaleoDefinitionImpl.setKaleoDefinitionId(kaleoDefinition.getKaleoDefinitionId());
		kaleoDefinitionImpl.setGroupId(kaleoDefinition.getGroupId());
		kaleoDefinitionImpl.setCompanyId(kaleoDefinition.getCompanyId());
		kaleoDefinitionImpl.setUserId(kaleoDefinition.getUserId());
		kaleoDefinitionImpl.setUserName(kaleoDefinition.getUserName());
		kaleoDefinitionImpl.setCreateDate(kaleoDefinition.getCreateDate());
		kaleoDefinitionImpl.setModifiedDate(kaleoDefinition.getModifiedDate());
		kaleoDefinitionImpl.setName(kaleoDefinition.getName());
		kaleoDefinitionImpl.setTitle(kaleoDefinition.getTitle());
		kaleoDefinitionImpl.setDescription(kaleoDefinition.getDescription());
		kaleoDefinitionImpl.setVersion(kaleoDefinition.getVersion());
		kaleoDefinitionImpl.setActive(kaleoDefinition.isActive());
		kaleoDefinitionImpl.setStartKaleoNodeId(kaleoDefinition.getStartKaleoNodeId());

		return kaleoDefinitionImpl;
	}

	/**
	 * Finds the kaleo definition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo definition to find
	 * @return the kaleo definition
	 * @throws com.liferay.portal.NoSuchModelException if a kaleo definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the kaleo definition with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchDefinitionException} if it could not be found.
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition to find
	 * @return the kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByPrimaryKey(long kaleoDefinitionId)
		throws NoSuchDefinitionException, SystemException {
		KaleoDefinition kaleoDefinition = fetchByPrimaryKey(kaleoDefinitionId);

		if (kaleoDefinition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + kaleoDefinitionId);
			}

			throw new NoSuchDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				kaleoDefinitionId);
		}

		return kaleoDefinition;
	}

	/**
	 * Finds the kaleo definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo definition to find
	 * @return the kaleo definition, or <code>null</code> if a kaleo definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the kaleo definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoDefinitionId the primary key of the kaleo definition to find
	 * @return the kaleo definition, or <code>null</code> if a kaleo definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition fetchByPrimaryKey(long kaleoDefinitionId)
		throws SystemException {
		KaleoDefinition kaleoDefinition = (KaleoDefinition)EntityCacheUtil.getResult(KaleoDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoDefinitionImpl.class, kaleoDefinitionId, this);

		if (kaleoDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoDefinition = (KaleoDefinition)session.get(KaleoDefinitionImpl.class,
						new Long(kaleoDefinitionId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (kaleoDefinition != null) {
					cacheResult(kaleoDefinition);
				}

				closeSession(session);
			}
		}

		return kaleoDefinition;
	}

	/**
	 * Finds all the kaleo definitions where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the kaleo definitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @return the range of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo definitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoDefinition> list = (List<KaleoDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
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

			query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<KaleoDefinition>)QueryUtil.list(q, getDialect(),
						start, end);
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
	 * Finds the first kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		List<KaleoDefinition> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		int count = countByCompanyId(companyId);

		List<KaleoDefinition> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition[] findByCompanyId_PrevAndNext(
		long kaleoDefinitionId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		KaleoDefinition kaleoDefinition = findByPrimaryKey(kaleoDefinitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDefinition[] array = new KaleoDefinitionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, kaleoDefinition,
					companyId, orderByComparator, true);

			array[1] = kaleoDefinition;

			array[2] = getByCompanyId_PrevAndNext(session, kaleoDefinition,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDefinition getByCompanyId_PrevAndNext(Session session,
		KaleoDefinition kaleoDefinition, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

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
			query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo definitions where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @return the matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByC_N(long companyId, String name)
		throws SystemException {
		return findByC_N(companyId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the kaleo definitions where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @return the range of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByC_N(long companyId, String name,
		int start, int end) throws SystemException {
		return findByC_N(companyId, name, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo definitions where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByC_N(long companyId, String name,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, name,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoDefinition> list = (List<KaleoDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_N,
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

			query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_N_NAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				list = (List<KaleoDefinition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_N,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_N,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByC_N_First(long companyId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		List<KaleoDefinition> list = findByC_N(companyId, name, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByC_N_Last(long companyId, String name,
		OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		int count = countByC_N(companyId, name);

		List<KaleoDefinition> list = findByC_N(companyId, name, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition[] findByC_N_PrevAndNext(long kaleoDefinitionId,
		long companyId, String name, OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		KaleoDefinition kaleoDefinition = findByPrimaryKey(kaleoDefinitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDefinition[] array = new KaleoDefinitionImpl[3];

			array[0] = getByC_N_PrevAndNext(session, kaleoDefinition,
					companyId, name, orderByComparator, true);

			array[1] = kaleoDefinition;

			array[2] = getByC_N_PrevAndNext(session, kaleoDefinition,
					companyId, name, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDefinition getByC_N_PrevAndNext(Session session,
		KaleoDefinition kaleoDefinition, long companyId, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

		query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_C_N_NAME_2);
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
			query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (name != null) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @param active the active to search with
	 * @return the matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByC_A(long companyId, boolean active)
		throws SystemException {
		return findByC_A(companyId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param active the active to search with
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @return the range of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByC_A(long companyId, boolean active,
		int start, int end) throws SystemException {
		return findByC_A(companyId, active, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param active the active to search with
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByC_A(long companyId, boolean active,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, active,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoDefinition> list = (List<KaleoDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_A,
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

			query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(active);

				list = (List<KaleoDefinition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_A,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_A,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param active the active to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByC_A_First(long companyId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		List<KaleoDefinition> list = findByC_A(companyId, active, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param active the active to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByC_A_Last(long companyId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		int count = countByC_A(companyId, active);

		List<KaleoDefinition> list = findByC_A(companyId, active, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company id to search with
	 * @param active the active to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition[] findByC_A_PrevAndNext(long kaleoDefinitionId,
		long companyId, boolean active, OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		KaleoDefinition kaleoDefinition = findByPrimaryKey(kaleoDefinitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDefinition[] array = new KaleoDefinitionImpl[3];

			array[0] = getByC_A_PrevAndNext(session, kaleoDefinition,
					companyId, active, orderByComparator, true);

			array[1] = kaleoDefinition;

			array[2] = getByC_A_PrevAndNext(session, kaleoDefinition,
					companyId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDefinition getByC_A_PrevAndNext(Session session,
		KaleoDefinition kaleoDefinition, long companyId, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

		query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

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
			query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchDefinitionException} if it could not be found.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param version the version to search with
	 * @return the matching kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByC_N_V(long companyId, String name, int version)
		throws NoSuchDefinitionException, SystemException {
		KaleoDefinition kaleoDefinition = fetchByC_N_V(companyId, name, version);

		if (kaleoDefinition == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", version=");
			msg.append(version);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDefinitionException(msg.toString());
		}

		return kaleoDefinition;
	}

	/**
	 * Finds the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param version the version to search with
	 * @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition fetchByC_N_V(long companyId, String name, int version)
		throws SystemException {
		return fetchByC_N_V(companyId, name, version, true);
	}

	/**
	 * Finds the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param version the version to search with
	 * @return the matching kaleo definition, or <code>null</code> if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition fetchByC_N_V(long companyId, String name,
		int version, boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, name, version };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_N_V,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_V_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_V_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_N_V_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_N_V_NAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_N_V_VERSION_2);

			query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(version);

				List<KaleoDefinition> list = q.list();

				result = list;

				KaleoDefinition kaleoDefinition = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
						finderArgs, list);
				}
				else {
					kaleoDefinition = list.get(0);

					cacheResult(kaleoDefinition);

					if ((kaleoDefinition.getCompanyId() != companyId) ||
							(kaleoDefinition.getName() == null) ||
							!kaleoDefinition.getName().equals(name) ||
							(kaleoDefinition.getVersion() != version)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V,
							finderArgs, kaleoDefinition);
					}
				}

				return kaleoDefinition;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (KaleoDefinition)result;
			}
		}
	}

	/**
	 * Finds all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param active the active to search with
	 * @return the matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByC_N_A(long companyId, String name,
		boolean active) throws SystemException {
		return findByC_N_A(companyId, name, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param active the active to search with
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @return the range of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByC_N_A(long companyId, String name,
		boolean active, int start, int end) throws SystemException {
		return findByC_N_A(companyId, name, active, start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param active the active to search with
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findByC_N_A(long companyId, String name,
		boolean active, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, name, active,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoDefinition> list = (List<KaleoDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_N_A,
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

			query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_A_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_A_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_N_A_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_N_A_NAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_N_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(active);

				list = (List<KaleoDefinition>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_N_A,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_N_A,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param active the active to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByC_N_A_First(long companyId, String name,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		List<KaleoDefinition> list = findByC_N_A(companyId, name, active, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param active the active to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a matching kaleo definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition findByC_N_A_Last(long companyId, String name,
		boolean active, OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		int count = countByC_N_A(companyId, name, active);

		List<KaleoDefinition> list = findByC_N_A(companyId, name, active,
				count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchDefinitionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the kaleo definitions before and after the current kaleo definition in the ordered set where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param kaleoDefinitionId the primary key of the current kaleo definition
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param active the active to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next kaleo definition
	 * @throws com.liferay.portal.workflow.kaleo.NoSuchDefinitionException if a kaleo definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public KaleoDefinition[] findByC_N_A_PrevAndNext(long kaleoDefinitionId,
		long companyId, String name, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchDefinitionException, SystemException {
		KaleoDefinition kaleoDefinition = findByPrimaryKey(kaleoDefinitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDefinition[] array = new KaleoDefinitionImpl[3];

			array[0] = getByC_N_A_PrevAndNext(session, kaleoDefinition,
					companyId, name, active, orderByComparator, true);

			array[1] = kaleoDefinition;

			array[2] = getByC_N_A_PrevAndNext(session, kaleoDefinition,
					companyId, name, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDefinition getByC_N_A_PrevAndNext(Session session,
		KaleoDefinition kaleoDefinition, long companyId, String name,
		boolean active, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEODEFINITION_WHERE);

		query.append(_FINDER_COLUMN_C_N_A_COMPANYID_2);

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_A_NAME_1);
		}
		else {
			if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_A_NAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_C_N_A_NAME_2);
			}
		}

		query.append(_FINDER_COLUMN_C_N_A_ACTIVE_2);

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
			query.append(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (name != null) {
			qPos.add(name);
		}

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(kaleoDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the kaleo definitions.
	 *
	 * @return the kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the kaleo definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @return the range of kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the kaleo definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public List<KaleoDefinition> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<KaleoDefinition> list = (List<KaleoDefinition>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEODEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEODEFINITION.concat(KaleoDefinitionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<KaleoDefinition>)QueryUtil.list(q,
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
	 * Removes all the kaleo definitions where companyId = &#63; from the database.
	 *
	 * @param companyId the company id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoDefinition kaleoDefinition : findByCompanyId(companyId)) {
			remove(kaleoDefinition);
		}
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; and name = &#63; from the database.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_N(long companyId, String name)
		throws SystemException {
		for (KaleoDefinition kaleoDefinition : findByC_N(companyId, name)) {
			remove(kaleoDefinition);
		}
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company id to search with
	 * @param active the active to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_A(long companyId, boolean active)
		throws SystemException {
		for (KaleoDefinition kaleoDefinition : findByC_A(companyId, active)) {
			remove(kaleoDefinition);
		}
	}

	/**
	 * Removes the kaleo definition where companyId = &#63; and name = &#63; and version = &#63; from the database.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param version the version to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_N_V(long companyId, String name, int version)
		throws NoSuchDefinitionException, SystemException {
		KaleoDefinition kaleoDefinition = findByC_N_V(companyId, name, version);

		remove(kaleoDefinition);
	}

	/**
	 * Removes all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param active the active to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_N_A(long companyId, String name, boolean active)
		throws SystemException {
		for (KaleoDefinition kaleoDefinition : findByC_N_A(companyId, name,
				active)) {
			remove(kaleoDefinition);
		}
	}

	/**
	 * Removes all the kaleo definitions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (KaleoDefinition kaleoDefinition : findAll()) {
			remove(kaleoDefinition);
		}
	}

	/**
	 * Counts all the kaleo definitions where companyId = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @return the number of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEODEFINITION_WHERE);

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
	 * Counts all the kaleo definitions where companyId = &#63; and name = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @return the number of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_N(long companyId, String name)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, name };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_N,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_N_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_N_NAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo definitions where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @param active the active to search with
	 * @return the number of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_A(long companyId, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, active };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_A, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo definitions where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param version the version to search with
	 * @return the number of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_N_V(long companyId, String name, int version)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, name, version };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_N_V,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_V_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_V_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_N_V_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_N_V_NAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_N_V_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(version);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N_V,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo definitions where companyId = &#63; and name = &#63; and active = &#63;.
	 *
	 * @param companyId the company id to search with
	 * @param name the name to search with
	 * @param active the active to search with
	 * @return the number of matching kaleo definitions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_N_A(long companyId, String name, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, name, active };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_N_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEODEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_A_COMPANYID_2);

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_A_NAME_1);
			}
			else {
				if (name.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_N_A_NAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_C_N_A_NAME_2);
				}
			}

			query.append(_FINDER_COLUMN_C_N_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (name != null) {
					qPos.add(name);
				}

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N_A,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the kaleo definitions.
	 *
	 * @return the number of kaleo definitions
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

				Query q = session.createQuery(_SQL_COUNT_KALEODEFINITION);

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
	 * Gets all the kaleo nodes associated with the kaleo definition.
	 *
	 * @param pk the primary key of the kaleo definition to get the associated kaleo nodes for
	 * @return the kaleo nodes associated with the kaleo definition
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoNode> getKaleoNodes(
		long pk) throws SystemException {
		return getKaleoNodes(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Gets a range of all the kaleo nodes associated with the kaleo definition.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo definition to get the associated kaleo nodes for
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @return the range of kaleo nodes associated with the kaleo definition
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoNode> getKaleoNodes(
		long pk, int start, int end) throws SystemException {
		return getKaleoNodes(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_KALEONODES = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoNodePersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoNodes",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	/**
	 * Gets an ordered range of all the kaleo nodes associated with the kaleo definition.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param pk the primary key of the kaleo definition to get the associated kaleo nodes for
	 * @param start the lower bound of the range of kaleo definitions to return
	 * @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of kaleo nodes associated with the kaleo definition
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.liferay.portal.workflow.kaleo.model.KaleoNode> getKaleoNodes(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				pk, String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<com.liferay.portal.workflow.kaleo.model.KaleoNode> list = (List<com.liferay.portal.workflow.kaleo.model.KaleoNode>)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEONODES,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETKALEONODES.concat(ORDER_BY_CLAUSE)
											.concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETKALEONODES.concat(com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("KaleoNode",
					com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.liferay.portal.workflow.kaleo.model.KaleoNode>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_KALEONODES,
						finderArgs);
				}
				else {
					kaleoNodePersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_KALEONODES,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_KALEONODES_SIZE = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoNodePersistenceImpl.FINDER_CLASS_NAME_LIST,
			"getKaleoNodesSize", new String[] { Long.class.getName() });

	/**
	 * Gets the number of kaleo nodes associated with the kaleo definition.
	 *
	 * @param pk the primary key of the kaleo definition to get the number of associated kaleo nodes for
	 * @return the number of kaleo nodes associated with the kaleo definition
	 * @throws SystemException if a system exception occurred
	 */
	public int getKaleoNodesSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_KALEONODES_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETKALEONODESSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_KALEONODES_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_KALEONODE = new FinderPath(com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl.ENTITY_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl.FINDER_CACHE_ENABLED,
			com.liferay.portal.workflow.kaleo.service.persistence.KaleoNodePersistenceImpl.FINDER_CLASS_NAME_LIST,
			"containsKaleoNode",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Determines if the kaleo node is associated with the kaleo definition.
	 *
	 * @param pk the primary key of the kaleo definition
	 * @param kaleoNodePK the primary key of the kaleo node
	 * @return <code>true</code> if the kaleo node is associated with the kaleo definition; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsKaleoNode(long pk, long kaleoNodePK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, kaleoNodePK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_KALEONODE,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsKaleoNode.contains(pk,
							kaleoNodePK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_KALEONODE,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Determines if the kaleo definition has any kaleo nodes associated with it.
	 *
	 * @param pk the primary key of the kaleo definition to check for associations with kaleo nodes
	 * @return <code>true</code> if the kaleo definition has any kaleo nodes associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsKaleoNodes(long pk) throws SystemException {
		if (getKaleoNodesSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Initializes the kaleo definition persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.model.KaleoDefinition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoDefinition>> listenersList = new ArrayList<ModelListener<KaleoDefinition>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoDefinition>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsKaleoNode = new ContainsKaleoNode(this);
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KaleoDefinitionImpl.class.getName());
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
	protected ContainsKaleoNode containsKaleoNode;

	protected class ContainsKaleoNode {
		protected ContainsKaleoNode(
			KaleoDefinitionPersistenceImpl persistenceImpl) {
			super();

			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSKALEONODE,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long kaleoDefinitionId, long kaleoNodeId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(kaleoDefinitionId), new Long(kaleoNodeId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	private static final String _SQL_SELECT_KALEODEFINITION = "SELECT kaleoDefinition FROM KaleoDefinition kaleoDefinition";
	private static final String _SQL_SELECT_KALEODEFINITION_WHERE = "SELECT kaleoDefinition FROM KaleoDefinition kaleoDefinition WHERE ";
	private static final String _SQL_COUNT_KALEODEFINITION = "SELECT COUNT(kaleoDefinition) FROM KaleoDefinition kaleoDefinition";
	private static final String _SQL_COUNT_KALEODEFINITION_WHERE = "SELECT COUNT(kaleoDefinition) FROM KaleoDefinition kaleoDefinition WHERE ";
	private static final String _SQL_GETKALEONODES = "SELECT {KaleoNode.*} FROM KaleoNode INNER JOIN KaleoDefinition ON (KaleoDefinition.kaleoDefinitionId = KaleoNode.kaleoDefinitionId) WHERE (KaleoDefinition.kaleoDefinitionId = ?)";
	private static final String _SQL_GETKALEONODESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM KaleoNode WHERE kaleoDefinitionId = ?";
	private static final String _SQL_CONTAINSKALEONODE = "SELECT COUNT(*) AS COUNT_VALUE FROM KaleoNode WHERE kaleoDefinitionId = ? AND kaleoNodeId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoDefinition.companyId = ?";
	private static final String _FINDER_COLUMN_C_N_COMPANYID_2 = "kaleoDefinition.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_NAME_1 = "kaleoDefinition.name IS NULL";
	private static final String _FINDER_COLUMN_C_N_NAME_2 = "kaleoDefinition.name = ?";
	private static final String _FINDER_COLUMN_C_N_NAME_3 = "(kaleoDefinition.name IS NULL OR kaleoDefinition.name = ?)";
	private static final String _FINDER_COLUMN_C_A_COMPANYID_2 = "kaleoDefinition.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_A_ACTIVE_2 = "kaleoDefinition.active = ?";
	private static final String _FINDER_COLUMN_C_N_V_COMPANYID_2 = "kaleoDefinition.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_V_NAME_1 = "kaleoDefinition.name IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_V_NAME_2 = "kaleoDefinition.name = ? AND ";
	private static final String _FINDER_COLUMN_C_N_V_NAME_3 = "(kaleoDefinition.name IS NULL OR kaleoDefinition.name = ?) AND ";
	private static final String _FINDER_COLUMN_C_N_V_VERSION_2 = "kaleoDefinition.version = ?";
	private static final String _FINDER_COLUMN_C_N_A_COMPANYID_2 = "kaleoDefinition.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_A_NAME_1 = "kaleoDefinition.name IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_A_NAME_2 = "kaleoDefinition.name = ? AND ";
	private static final String _FINDER_COLUMN_C_N_A_NAME_3 = "(kaleoDefinition.name IS NULL OR kaleoDefinition.name = ?) AND ";
	private static final String _FINDER_COLUMN_C_N_A_ACTIVE_2 = "kaleoDefinition.active = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoDefinition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoDefinition exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(KaleoDefinitionPersistenceImpl.class);
}