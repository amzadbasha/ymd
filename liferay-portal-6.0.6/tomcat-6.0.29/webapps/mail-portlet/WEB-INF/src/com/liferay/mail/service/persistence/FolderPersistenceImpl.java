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

package com.liferay.mail.service.persistence;

import com.liferay.mail.NoSuchFolderException;
import com.liferay.mail.model.Folder;
import com.liferay.mail.model.impl.FolderImpl;
import com.liferay.mail.model.impl.FolderModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the folder service.
 *
 * <p>
 * Never modify or reference this class directly. Always use {@link FolderUtil} to access the folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FolderPersistence
 * @see FolderUtil
 * @generated
 */
public class FolderPersistenceImpl extends BasePersistenceImpl<Folder>
	implements FolderPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = FolderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_ACCOUNTID = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByAccountId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCOUNTID = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByAccountId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_A_F = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_ENTITY,
			"fetchByA_F",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_COUNT_BY_A_F = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByA_F",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	/**
	 * Caches the folder in the entity cache if it is enabled.
	 *
	 * @param folder the folder to cache
	 */
	public void cacheResult(Folder folder) {
		EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey(), folder);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
			new Object[] { new Long(folder.getAccountId()), folder.getFullName() },
			folder);
	}

	/**
	 * Caches the folders in the entity cache if it is enabled.
	 *
	 * @param folders the folders to cache
	 */
	public void cacheResult(List<Folder> folders) {
		for (Folder folder : folders) {
			if (EntityCacheUtil.getResult(
						FolderModelImpl.ENTITY_CACHE_ENABLED, FolderImpl.class,
						folder.getPrimaryKey(), this) == null) {
				cacheResult(folder);
			}
		}
	}

	/**
	 * Clears the cache for all folders.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		CacheRegistryUtil.clear(FolderImpl.class.getName());
		EntityCacheUtil.clearCache(FolderImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the folder.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(Folder folder) {
		EntityCacheUtil.removeResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey());

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_F,
			new Object[] { new Long(folder.getAccountId()), folder.getFullName() });
	}

	/**
	 * Creates a new folder with the primary key. Does not add the folder to the database.
	 *
	 * @param folderId the primary key for the new folder
	 * @return the new folder
	 */
	public Folder create(long folderId) {
		Folder folder = new FolderImpl();

		folder.setNew(true);
		folder.setPrimaryKey(folderId);

		return folder;
	}

	/**
	 * Removes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the folder to remove
	 * @return the folder that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param folderId the primary key of the folder to remove
	 * @return the folder that was removed
	 * @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder remove(long folderId)
		throws NoSuchFolderException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Folder folder = (Folder)session.get(FolderImpl.class,
					new Long(folderId));

			if (folder == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + folderId);
				}

				throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					folderId);
			}

			return remove(folder);
		}
		catch (NoSuchFolderException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Folder removeImpl(Folder folder) throws SystemException {
		folder = toUnwrappedModel(folder);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, folder);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		FolderModelImpl folderModelImpl = (FolderModelImpl)folder;

		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_F,
			new Object[] {
				new Long(folderModelImpl.getAccountId()),
				
			folderModelImpl.getFullName()
			});

		EntityCacheUtil.removeResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey());

		return folder;
	}

	public Folder updateImpl(com.liferay.mail.model.Folder folder, boolean merge)
		throws SystemException {
		folder = toUnwrappedModel(folder);

		boolean isNew = folder.isNew();

		FolderModelImpl folderModelImpl = (FolderModelImpl)folder;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, folder, merge);

			folder.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
			FolderImpl.class, folder.getPrimaryKey(), folder);

		if (!isNew &&
				((folder.getAccountId() != folderModelImpl.getOriginalAccountId()) ||
				!Validator.equals(folder.getFullName(),
					folderModelImpl.getOriginalFullName()))) {
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_F,
				new Object[] {
					new Long(folderModelImpl.getOriginalAccountId()),
					
				folderModelImpl.getOriginalFullName()
				});
		}

		if (isNew ||
				((folder.getAccountId() != folderModelImpl.getOriginalAccountId()) ||
				!Validator.equals(folder.getFullName(),
					folderModelImpl.getOriginalFullName()))) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
				new Object[] {
					new Long(folder.getAccountId()),
					
				folder.getFullName()
				}, folder);
		}

		return folder;
	}

	protected Folder toUnwrappedModel(Folder folder) {
		if (folder instanceof FolderImpl) {
			return folder;
		}

		FolderImpl folderImpl = new FolderImpl();

		folderImpl.setNew(folder.isNew());
		folderImpl.setPrimaryKey(folder.getPrimaryKey());

		folderImpl.setFolderId(folder.getFolderId());
		folderImpl.setCompanyId(folder.getCompanyId());
		folderImpl.setUserId(folder.getUserId());
		folderImpl.setUserName(folder.getUserName());
		folderImpl.setCreateDate(folder.getCreateDate());
		folderImpl.setModifiedDate(folder.getModifiedDate());
		folderImpl.setAccountId(folder.getAccountId());
		folderImpl.setFullName(folder.getFullName());
		folderImpl.setDisplayName(folder.getDisplayName());
		folderImpl.setRemoteMessageCount(folder.getRemoteMessageCount());

		return folderImpl;
	}

	/**
	 * Finds the folder with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the folder to find
	 * @return the folder
	 * @throws com.liferay.portal.NoSuchModelException if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the folder with the primary key or throws a {@link com.liferay.mail.NoSuchFolderException} if it could not be found.
	 *
	 * @param folderId the primary key of the folder to find
	 * @return the folder
	 * @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder findByPrimaryKey(long folderId)
		throws NoSuchFolderException, SystemException {
		Folder folder = fetchByPrimaryKey(folderId);

		if (folder == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + folderId);
			}

			throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				folderId);
		}

		return folder;
	}

	/**
	 * Finds the folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the folder to find
	 * @return the folder, or <code>null</code> if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param folderId the primary key of the folder to find
	 * @return the folder, or <code>null</code> if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder fetchByPrimaryKey(long folderId) throws SystemException {
		Folder folder = (Folder)EntityCacheUtil.getResult(FolderModelImpl.ENTITY_CACHE_ENABLED,
				FolderImpl.class, folderId, this);

		if (folder == null) {
			Session session = null;

			try {
				session = openSession();

				folder = (Folder)session.get(FolderImpl.class,
						new Long(folderId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (folder != null) {
					cacheResult(folder);
				}

				closeSession(session);
			}
		}

		return folder;
	}

	/**
	 * Finds all the folders where accountId = &#63;.
	 *
	 * @param accountId the account id to search with
	 * @return the matching folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findByAccountId(long accountId)
		throws SystemException {
		return findByAccountId(accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the folders where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountId the account id to search with
	 * @param start the lower bound of the range of folders to return
	 * @param end the upper bound of the range of folders to return (not inclusive)
	 * @return the range of matching folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findByAccountId(long accountId, int start, int end)
		throws SystemException {
		return findByAccountId(accountId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the folders where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountId the account id to search with
	 * @param start the lower bound of the range of folders to return
	 * @param end the upper bound of the range of folders to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of matching folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findByAccountId(long accountId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				accountId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Folder> list = (List<Folder>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_ACCOUNTID,
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

			query.append(_SQL_SELECT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(FolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				list = (List<Folder>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_ACCOUNTID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_ACCOUNTID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first folder in the ordered set where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountId the account id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the first matching folder
	 * @throws com.liferay.mail.NoSuchFolderException if a matching folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder findByAccountId_First(long accountId,
		OrderByComparator orderByComparator)
		throws NoSuchFolderException, SystemException {
		List<Folder> list = findByAccountId(accountId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountId=");
			msg.append(accountId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFolderException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last folder in the ordered set where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param accountId the account id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the last matching folder
	 * @throws com.liferay.mail.NoSuchFolderException if a matching folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder findByAccountId_Last(long accountId,
		OrderByComparator orderByComparator)
		throws NoSuchFolderException, SystemException {
		int count = countByAccountId(accountId);

		List<Folder> list = findByAccountId(accountId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountId=");
			msg.append(accountId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFolderException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the folders before and after the current folder in the ordered set where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param folderId the primary key of the current folder
	 * @param accountId the account id to search with
	 * @param orderByComparator the comparator to order the set by
	 * @return the previous, current, and next folder
	 * @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder[] findByAccountId_PrevAndNext(long folderId, long accountId,
		OrderByComparator orderByComparator)
		throws NoSuchFolderException, SystemException {
		Folder folder = findByPrimaryKey(folderId);

		Session session = null;

		try {
			session = openSession();

			Folder[] array = new FolderImpl[3];

			array[0] = getByAccountId_PrevAndNext(session, folder, accountId,
					orderByComparator, true);

			array[1] = folder;

			array[2] = getByAccountId_PrevAndNext(session, folder, accountId,
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

	protected Folder getByAccountId_PrevAndNext(Session session, Folder folder,
		long accountId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FOLDER_WHERE);

		query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

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
			query.append(FolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(accountId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(folder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Folder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds the folder where accountId = &#63; and fullName = &#63; or throws a {@link com.liferay.mail.NoSuchFolderException} if it could not be found.
	 *
	 * @param accountId the account id to search with
	 * @param fullName the full name to search with
	 * @return the matching folder
	 * @throws com.liferay.mail.NoSuchFolderException if a matching folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder findByA_F(long accountId, String fullName)
		throws NoSuchFolderException, SystemException {
		Folder folder = fetchByA_F(accountId, fullName);

		if (folder == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountId=");
			msg.append(accountId);

			msg.append(", fullName=");
			msg.append(fullName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFolderException(msg.toString());
		}

		return folder;
	}

	/**
	 * Finds the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountId the account id to search with
	 * @param fullName the full name to search with
	 * @return the matching folder, or <code>null</code> if a matching folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder fetchByA_F(long accountId, String fullName)
		throws SystemException {
		return fetchByA_F(accountId, fullName, true);
	}

	/**
	 * Finds the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountId the account id to search with
	 * @param fullName the full name to search with
	 * @return the matching folder, or <code>null</code> if a matching folder could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Folder fetchByA_F(long accountId, String fullName,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { accountId, fullName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_A_F,
					finderArgs, this);
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_A_F_ACCOUNTID_2);

			if (fullName == null) {
				query.append(_FINDER_COLUMN_A_F_FULLNAME_1);
			}
			else {
				if (fullName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_A_F_FULLNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_A_F_FULLNAME_2);
				}
			}

			query.append(FolderModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				if (fullName != null) {
					qPos.add(fullName);
				}

				List<Folder> list = q.list();

				result = list;

				Folder folder = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
						finderArgs, list);
				}
				else {
					folder = list.get(0);

					cacheResult(folder);

					if ((folder.getAccountId() != accountId) ||
							(folder.getFullName() == null) ||
							!folder.getFullName().equals(fullName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_A_F,
							finderArgs, folder);
					}
				}

				return folder;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_A_F,
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
				return (Folder)result;
			}
		}
	}

	/**
	 * Finds all the folders.
	 *
	 * @return the folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of folders to return
	 * @param end the upper bound of the range of folders to return (not inclusive)
	 * @return the range of folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of folders to return
	 * @param end the upper bound of the range of folders to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by
	 * @return the ordered range of folders
	 * @throws SystemException if a system exception occurred
	 */
	public List<Folder> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Folder> list = (List<Folder>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_FOLDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FOLDER.concat(FolderModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Folder>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Folder>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the folders where accountId = &#63; from the database.
	 *
	 * @param accountId the account id to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByAccountId(long accountId) throws SystemException {
		for (Folder folder : findByAccountId(accountId)) {
			remove(folder);
		}
	}

	/**
	 * Removes the folder where accountId = &#63; and fullName = &#63; from the database.
	 *
	 * @param accountId the account id to search with
	 * @param fullName the full name to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByA_F(long accountId, String fullName)
		throws NoSuchFolderException, SystemException {
		Folder folder = findByA_F(accountId, fullName);

		remove(folder);
	}

	/**
	 * Removes all the folders from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Folder folder : findAll()) {
			remove(folder);
		}
	}

	/**
	 * Counts all the folders where accountId = &#63;.
	 *
	 * @param accountId the account id to search with
	 * @return the number of matching folders
	 * @throws SystemException if a system exception occurred
	 */
	public int countByAccountId(long accountId) throws SystemException {
		Object[] finderArgs = new Object[] { accountId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCOUNTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the folders where accountId = &#63; and fullName = &#63;.
	 *
	 * @param accountId the account id to search with
	 * @param fullName the full name to search with
	 * @return the number of matching folders
	 * @throws SystemException if a system exception occurred
	 */
	public int countByA_F(long accountId, String fullName)
		throws SystemException {
		Object[] finderArgs = new Object[] { accountId, fullName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_A_F,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FOLDER_WHERE);

			query.append(_FINDER_COLUMN_A_F_ACCOUNTID_2);

			if (fullName == null) {
				query.append(_FINDER_COLUMN_A_F_FULLNAME_1);
			}
			else {
				if (fullName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_A_F_FULLNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_A_F_FULLNAME_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountId);

				if (fullName != null) {
					qPos.add(fullName);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_A_F, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Counts all the folders.
	 *
	 * @return the number of folders
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

				Query q = session.createQuery(_SQL_COUNT_FOLDER);

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
	 * Initializes the folder persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.mail.model.Folder")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Folder>> listenersList = new ArrayList<ModelListener<Folder>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Folder>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(FolderImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = AccountPersistence.class)
	protected AccountPersistence accountPersistence;
	@BeanReference(type = AttachmentPersistence.class)
	protected AttachmentPersistence attachmentPersistence;
	@BeanReference(type = FolderPersistence.class)
	protected FolderPersistence folderPersistence;
	@BeanReference(type = MessagePersistence.class)
	protected MessagePersistence messagePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_FOLDER = "SELECT folder FROM Folder folder";
	private static final String _SQL_SELECT_FOLDER_WHERE = "SELECT folder FROM Folder folder WHERE ";
	private static final String _SQL_COUNT_FOLDER = "SELECT COUNT(folder) FROM Folder folder";
	private static final String _SQL_COUNT_FOLDER_WHERE = "SELECT COUNT(folder) FROM Folder folder WHERE ";
	private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 = "folder.accountId = ?";
	private static final String _FINDER_COLUMN_A_F_ACCOUNTID_2 = "folder.accountId = ? AND ";
	private static final String _FINDER_COLUMN_A_F_FULLNAME_1 = "folder.fullName IS NULL";
	private static final String _FINDER_COLUMN_A_F_FULLNAME_2 = "folder.fullName = ?";
	private static final String _FINDER_COLUMN_A_F_FULLNAME_3 = "(folder.fullName IS NULL OR folder.fullName = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "folder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Folder exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Folder exists with the key {";
	private static Log _log = LogFactoryUtil.getLog(FolderPersistenceImpl.class);
}