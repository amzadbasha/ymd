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

package com.liferay.chat.service.persistence;

import com.liferay.chat.model.Status;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the status service.
 *
 * <p>
 * Never modify or reference this interface directly. Always use {@link StatusUtil} to access the status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StatusPersistenceImpl
 * @see StatusUtil
 * @generated
 */
public interface StatusPersistence extends BasePersistence<Status> {
	/**
	* Caches the status in the entity cache if it is enabled.
	*
	* @param status the status to cache
	*/
	public void cacheResult(com.liferay.chat.model.Status status);

	/**
	* Caches the statuses in the entity cache if it is enabled.
	*
	* @param statuses the statuses to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.chat.model.Status> statuses);

	/**
	* Creates a new status with the primary key. Does not add the status to the database.
	*
	* @param statusId the primary key for the new status
	* @return the new status
	*/
	public com.liferay.chat.model.Status create(long statusId);

	/**
	* Removes the status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statusId the primary key of the status to remove
	* @return the status that was removed
	* @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status remove(long statusId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Status updateImpl(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the status with the primary key or throws a {@link com.liferay.chat.NoSuchStatusException} if it could not be found.
	*
	* @param statusId the primary key of the status to find
	* @return the status
	* @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status findByPrimaryKey(long statusId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the status with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param statusId the primary key of the status to find
	* @return the status, or <code>null</code> if a status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status fetchByPrimaryKey(long statusId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the status where userId = &#63; or throws a {@link com.liferay.chat.NoSuchStatusException} if it could not be found.
	*
	* @param userId the user id to search with
	* @return the matching status
	* @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status findByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the status where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user id to search with
	* @return the matching status, or <code>null</code> if a matching status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status fetchByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the status where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user id to search with
	* @return the matching status, or <code>null</code> if a matching status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status fetchByUserId(long userId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the statuses where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date to search with
	* @return the matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the statuses where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date to search with
	* @param start the lower bound of the range of statuses to return
	* @param end the upper bound of the range of statuses to return (not inclusive)
	* @return the range of matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the statuses where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date to search with
	* @param start the lower bound of the range of statuses to return
	* @param end the upper bound of the range of statuses to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first status in the ordered set where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching status
	* @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status findByModifiedDate_First(
		long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last status in the ordered set where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching status
	* @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status findByModifiedDate_Last(
		long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the statuses before and after the current status in the ordered set where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param statusId the primary key of the current status
	* @param modifiedDate the modified date to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next status
	* @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status[] findByModifiedDate_PrevAndNext(
		long statusId, long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the statuses where online = &#63;.
	*
	* @param online the online to search with
	* @return the matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the statuses where online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param online the online to search with
	* @param start the lower bound of the range of statuses to return
	* @param end the upper bound of the range of statuses to return (not inclusive)
	* @return the range of matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the statuses where online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param online the online to search with
	* @param start the lower bound of the range of statuses to return
	* @param end the upper bound of the range of statuses to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first status in the ordered set where online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param online the online to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching status
	* @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status findByOnline_First(boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last status in the ordered set where online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param online the online to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching status
	* @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status findByOnline_Last(boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the statuses before and after the current status in the ordered set where online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param statusId the primary key of the current status
	* @param online the online to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next status
	* @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status[] findByOnline_PrevAndNext(
		long statusId, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date to search with
	* @param online the online to search with
	* @return the matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date to search with
	* @param online the online to search with
	* @param start the lower bound of the range of statuses to return
	* @param end the upper bound of the range of statuses to return (not inclusive)
	* @return the range of matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date to search with
	* @param online the online to search with
	* @param start the lower bound of the range of statuses to return
	* @param end the upper bound of the range of statuses to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date to search with
	* @param online the online to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching status
	* @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status findByM_O_First(long modifiedDate,
		boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param modifiedDate the modified date to search with
	* @param online the online to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching status
	* @throws com.liferay.chat.NoSuchStatusException if a matching status could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status findByM_O_Last(long modifiedDate,
		boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the statuses before and after the current status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param statusId the primary key of the current status
	* @param modifiedDate the modified date to search with
	* @param online the online to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next status
	* @throws com.liferay.chat.NoSuchStatusException if a status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status[] findByM_O_PrevAndNext(
		long statusId, long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the statuses.
	*
	* @return the statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of statuses to return
	* @param end the upper bound of the range of statuses to return (not inclusive)
	* @return the range of statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of statuses to return
	* @param end the upper bound of the range of statuses to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of statuses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Status> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the status where userId = &#63; from the database.
	*
	* @param userId the user id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the statuses where modifiedDate = &#63; from the database.
	*
	* @param modifiedDate the modified date to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByModifiedDate(long modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the statuses where online = &#63; from the database.
	*
	* @param online the online to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOnline(boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the statuses where modifiedDate = &#63; and online = &#63; from the database.
	*
	* @param modifiedDate the modified date to search with
	* @param online the online to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByM_O(long modifiedDate, boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the statuses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the statuses where userId = &#63;.
	*
	* @param userId the user id to search with
	* @return the number of matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the statuses where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date to search with
	* @return the number of matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public int countByModifiedDate(long modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the statuses where online = &#63;.
	*
	* @param online the online to search with
	* @return the number of matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public int countByOnline(boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date to search with
	* @param online the online to search with
	* @return the number of matching statuses
	* @throws SystemException if a system exception occurred
	*/
	public int countByM_O(long modifiedDate, boolean online)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the statuses.
	*
	* @return the number of statuses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}