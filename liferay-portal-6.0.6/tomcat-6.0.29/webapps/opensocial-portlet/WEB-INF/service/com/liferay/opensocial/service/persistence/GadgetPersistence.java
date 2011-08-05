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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.model.Gadget;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the gadget service.
 *
 * <p>
 * Never modify or reference this interface directly. Always use {@link GadgetUtil} to access the gadget persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GadgetPersistenceImpl
 * @see GadgetUtil
 * @generated
 */
public interface GadgetPersistence extends BasePersistence<Gadget> {
	/**
	* Caches the gadget in the entity cache if it is enabled.
	*
	* @param gadget the gadget to cache
	*/
	public void cacheResult(com.liferay.opensocial.model.Gadget gadget);

	/**
	* Caches the gadgets in the entity cache if it is enabled.
	*
	* @param gadgets the gadgets to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.opensocial.model.Gadget> gadgets);

	/**
	* Creates a new gadget with the primary key. Does not add the gadget to the database.
	*
	* @param gadgetId the primary key for the new gadget
	* @return the new gadget
	*/
	public com.liferay.opensocial.model.Gadget create(long gadgetId);

	/**
	* Removes the gadget with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gadgetId the primary key of the gadget to remove
	* @return the gadget that was removed
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.Gadget remove(long gadgetId)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.opensocial.model.Gadget updateImpl(
		com.liferay.opensocial.model.Gadget gadget, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the gadget with the primary key or throws a {@link com.liferay.opensocial.NoSuchGadgetException} if it could not be found.
	*
	* @param gadgetId the primary key of the gadget to find
	* @return the gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.Gadget findByPrimaryKey(long gadgetId)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the gadget with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gadgetId the primary key of the gadget to find
	* @return the gadget, or <code>null</code> if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.Gadget fetchByPrimaryKey(long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the gadgets where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.Gadget> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the gadgets where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of gadgets to return
	* @param end the upper bound of the range of gadgets to return (not inclusive)
	* @return the range of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.Gadget> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the gadgets where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of gadgets to return
	* @param end the upper bound of the range of gadgets to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.Gadget> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first gadget in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.Gadget findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last gadget in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.Gadget findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the gadgets before and after the current gadget in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param gadgetId the primary key of the current gadget
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.Gadget[] findByCompanyId_PrevAndNext(
		long gadgetId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the gadgets.
	*
	* @return the gadgets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.Gadget> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the gadgets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of gadgets to return
	* @param end the upper bound of the range of gadgets to return (not inclusive)
	* @return the range of gadgets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.Gadget> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the gadgets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of gadgets to return
	* @param end the upper bound of the range of gadgets to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of gadgets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.Gadget> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the gadgets where companyId = &#63; from the database.
	*
	* @param companyId the company id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the gadgets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the gadgets where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the number of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the gadgets.
	*
	* @return the number of gadgets
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}