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

import com.liferay.mail.model.Folder;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the folder service.
 *
 * <p>
 * Never modify or reference this interface directly. Always use {@link FolderUtil} to access the folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FolderPersistenceImpl
 * @see FolderUtil
 * @generated
 */
public interface FolderPersistence extends BasePersistence<Folder> {
	/**
	* Caches the folder in the entity cache if it is enabled.
	*
	* @param folder the folder to cache
	*/
	public void cacheResult(com.liferay.mail.model.Folder folder);

	/**
	* Caches the folders in the entity cache if it is enabled.
	*
	* @param folders the folders to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.mail.model.Folder> folders);

	/**
	* Creates a new folder with the primary key. Does not add the folder to the database.
	*
	* @param folderId the primary key for the new folder
	* @return the new folder
	*/
	public com.liferay.mail.model.Folder create(long folderId);

	/**
	* Removes the folder with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param folderId the primary key of the folder to remove
	* @return the folder that was removed
	* @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Folder remove(long folderId)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Folder updateImpl(
		com.liferay.mail.model.Folder folder, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the folder with the primary key or throws a {@link com.liferay.mail.NoSuchFolderException} if it could not be found.
	*
	* @param folderId the primary key of the folder to find
	* @return the folder
	* @throws com.liferay.mail.NoSuchFolderException if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Folder findByPrimaryKey(long folderId)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the folder with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param folderId the primary key of the folder to find
	* @return the folder, or <code>null</code> if a folder with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Folder fetchByPrimaryKey(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the folders where accountId = &#63;.
	*
	* @param accountId the account id to search with
	* @return the matching folders
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.mail.model.Folder findByAccountId_First(long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.mail.model.Folder findByAccountId_Last(long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.mail.model.Folder[] findByAccountId_PrevAndNext(
		long folderId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the folder where accountId = &#63; and fullName = &#63; or throws a {@link com.liferay.mail.NoSuchFolderException} if it could not be found.
	*
	* @param accountId the account id to search with
	* @param fullName the full name to search with
	* @return the matching folder
	* @throws com.liferay.mail.NoSuchFolderException if a matching folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Folder findByA_F(long accountId,
		java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountId the account id to search with
	* @param fullName the full name to search with
	* @return the matching folder, or <code>null</code> if a matching folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Folder fetchByA_F(long accountId,
		java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the folder where accountId = &#63; and fullName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountId the account id to search with
	* @param fullName the full name to search with
	* @return the matching folder, or <code>null</code> if a matching folder could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Folder fetchByA_F(long accountId,
		java.lang.String fullName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the folders.
	*
	* @return the folders
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Folder> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.mail.model.Folder> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.mail.model.Folder> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the folders where accountId = &#63; from the database.
	*
	* @param accountId the account id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountId(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the folder where accountId = &#63; and fullName = &#63; from the database.
	*
	* @param accountId the account id to search with
	* @param fullName the full name to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the folders from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the folders where accountId = &#63;.
	*
	* @param accountId the account id to search with
	* @return the number of matching folders
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountId(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the folders where accountId = &#63; and fullName = &#63;.
	*
	* @param accountId the account id to search with
	* @param fullName the full name to search with
	* @return the number of matching folders
	* @throws SystemException if a system exception occurred
	*/
	public int countByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the folders.
	*
	* @return the number of folders
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}