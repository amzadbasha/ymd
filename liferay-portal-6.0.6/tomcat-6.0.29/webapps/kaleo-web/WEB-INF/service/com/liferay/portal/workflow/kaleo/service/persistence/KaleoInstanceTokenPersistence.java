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

import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;

/**
 * The persistence interface for the kaleo instance token service.
 *
 * <p>
 * Never modify or reference this interface directly. Always use {@link KaleoInstanceTokenUtil} to access the kaleo instance token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceTokenPersistenceImpl
 * @see KaleoInstanceTokenUtil
 * @generated
 */
public interface KaleoInstanceTokenPersistence extends BasePersistence<KaleoInstanceToken> {
	/**
	* Caches the kaleo instance token in the entity cache if it is enabled.
	*
	* @param kaleoInstanceToken the kaleo instance token to cache
	*/
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken);

	/**
	* Caches the kaleo instance tokens in the entity cache if it is enabled.
	*
	* @param kaleoInstanceTokens the kaleo instance tokens to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> kaleoInstanceTokens);

	/**
	* Creates a new kaleo instance token with the primary key. Does not add the kaleo instance token to the database.
	*
	* @param kaleoInstanceTokenId the primary key for the new kaleo instance token
	* @return the new kaleo instance token
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken create(
		long kaleoInstanceTokenId);

	/**
	* Removes the kaleo instance token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token to remove
	* @return the kaleo instance token that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken remove(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the kaleo instance token with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException} if it could not be found.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token to find
	* @return the kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByPrimaryKey(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the kaleo instance token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoInstanceTokenId the primary key of the kaleo instance token to find
	* @return the kaleo instance token, or <code>null</code> if a kaleo instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken fetchByPrimaryKey(
		long kaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the kaleo instance tokens where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the kaleo instance tokens where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @return the range of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the kaleo instance tokens where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first kaleo instance token in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the last kaleo instance token in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByCompanyId_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @return the matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @return the range of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the last kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the kaleo instance tokens before and after the current kaleo instance token in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds all the kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance id to search with
	* @return the matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance id to search with
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @return the range of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance id to search with
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the last kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceId the kaleo instance id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the kaleo instance tokens before and after the current kaleo instance token in the ordered set where kaleoInstanceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param kaleoInstanceId the kaleo instance id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByKaleoInstanceId_PrevAndNext(
		long kaleoInstanceTokenId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @return the matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI(
		long companyId, long parentKaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @return the range of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI(
		long companyId, long parentKaleoInstanceTokenId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI(
		long companyId, long parentKaleoInstanceTokenId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_First(
		long companyId, long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_Last(
		long companyId, long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByC_PKITI_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param completionDate the completion date to search with
	* @return the matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI_CD(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param completionDate the completion date to search with
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @return the range of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI_CD(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param completionDate the completion date to search with
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findByC_PKITI_CD(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param completionDate the completion date to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_CD_First(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the last kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param completionDate the completion date to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a matching kaleo instance token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken findByC_PKITI_CD_Last(
		long companyId, long parentKaleoInstanceTokenId,
		java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds the kaleo instance tokens before and after the current kaleo instance token in the ordered set where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoInstanceTokenId the primary key of the current kaleo instance token
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param completionDate the completion date to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next kaleo instance token
	* @throws com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException if a kaleo instance token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken[] findByC_PKITI_CD_PrevAndNext(
		long kaleoInstanceTokenId, long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchInstanceTokenException;

	/**
	* Finds all the kaleo instance tokens.
	*
	* @return the kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the kaleo instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @return the range of kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the kaleo instance tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo instance tokens to return
	* @param end the upper bound of the range of kaleo instance tokens to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo instance tokens where companyId = &#63; from the database.
	*
	* @param companyId the company id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo instance tokens where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo instance tokens where kaleoInstanceId = &#63; from the database.
	*
	* @param kaleoInstanceId the kaleo instance id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; from the database.
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_PKITI(long companyId, long parentKaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63; from the database.
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param completionDate the completion date to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the kaleo instance tokens from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the kaleo instance tokens where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the number of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the kaleo instance tokens where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @return the number of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the kaleo instance tokens where kaleoInstanceId = &#63;.
	*
	* @param kaleoInstanceId the kaleo instance id to search with
	* @return the number of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63;.
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @return the number of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_PKITI(long companyId, long parentKaleoInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the kaleo instance tokens where companyId = &#63; and parentKaleoInstanceTokenId = &#63; and completionDate = &#63;.
	*
	* @param companyId the company id to search with
	* @param parentKaleoInstanceTokenId the parent kaleo instance token id to search with
	* @param completionDate the completion date to search with
	* @return the number of matching kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_PKITI_CD(long companyId,
		long parentKaleoInstanceTokenId, java.util.Date completionDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the kaleo instance tokens.
	*
	* @return the number of kaleo instance tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}