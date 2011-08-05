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

package com.liferay.wsrp.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import com.liferay.wsrp.model.WSRPConsumerPortlet;

import java.util.List;

/**
 * The persistence utility for the w s r p consumer portlet service. This utility wraps {@link WSRPConsumerPortletPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortletPersistence
 * @see WSRPConsumerPortletPersistenceImpl
 * @generated
 */
public class WSRPConsumerPortletUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(WSRPConsumerPortlet wsrpConsumerPortlet) {
		getPersistence().clearCache(wsrpConsumerPortlet);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WSRPConsumerPortlet> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WSRPConsumerPortlet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WSRPConsumerPortlet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static WSRPConsumerPortlet remove(
		WSRPConsumerPortlet wsrpConsumerPortlet) throws SystemException {
		return getPersistence().remove(wsrpConsumerPortlet);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static WSRPConsumerPortlet update(
		WSRPConsumerPortlet wsrpConsumerPortlet, boolean merge)
		throws SystemException {
		return getPersistence().update(wsrpConsumerPortlet, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static WSRPConsumerPortlet update(
		WSRPConsumerPortlet wsrpConsumerPortlet, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(wsrpConsumerPortlet, merge, serviceContext);
	}

	/**
	* Caches the w s r p consumer portlet in the entity cache if it is enabled.
	*
	* @param wsrpConsumerPortlet the w s r p consumer portlet to cache
	*/
	public static void cacheResult(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet) {
		getPersistence().cacheResult(wsrpConsumerPortlet);
	}

	/**
	* Caches the w s r p consumer portlets in the entity cache if it is enabled.
	*
	* @param wsrpConsumerPortlets the w s r p consumer portlets to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> wsrpConsumerPortlets) {
		getPersistence().cacheResult(wsrpConsumerPortlets);
	}

	/**
	* Creates a new w s r p consumer portlet with the primary key. Does not add the w s r p consumer portlet to the database.
	*
	* @param wsrpConsumerPortletId the primary key for the new w s r p consumer portlet
	* @return the new w s r p consumer portlet
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet create(
		long wsrpConsumerPortletId) {
		return getPersistence().create(wsrpConsumerPortletId);
	}

	/**
	* Removes the w s r p consumer portlet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet to remove
	* @return the w s r p consumer portlet that was removed
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet remove(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence().remove(wsrpConsumerPortletId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateImpl(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(wsrpConsumerPortlet, merge);
	}

	/**
	* Finds the w s r p consumer portlet with the primary key or throws a {@link com.liferay.wsrp.NoSuchConsumerPortletException} if it could not be found.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet to find
	* @return the w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByPrimaryKey(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence().findByPrimaryKey(wsrpConsumerPortletId);
	}

	/**
	* Finds the w s r p consumer portlet with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet to find
	* @return the w s r p consumer portlet, or <code>null</code> if a w s r p consumer portlet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet fetchByPrimaryKey(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(wsrpConsumerPortletId);
	}

	/**
	* Finds all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @return the matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByWsrpConsumerId(wsrpConsumerId);
	}

	/**
	* Finds a range of all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @param start the lower bound of the range of w s r p consumer portlets to return
	* @param end the upper bound of the range of w s r p consumer portlets to return (not inclusive)
	* @return the range of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByWsrpConsumerId(wsrpConsumerId, start, end);
	}

	/**
	* Finds an ordered range of all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @param start the lower bound of the range of w s r p consumer portlets to return
	* @param end the upper bound of the range of w s r p consumer portlets to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByWsrpConsumerId(wsrpConsumerId, start, end,
			orderByComparator);
	}

	/**
	* Finds the first w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByWsrpConsumerId_First(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence()
				   .findByWsrpConsumerId_First(wsrpConsumerId, orderByComparator);
	}

	/**
	* Finds the last w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByWsrpConsumerId_Last(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence()
				   .findByWsrpConsumerId_Last(wsrpConsumerId, orderByComparator);
	}

	/**
	* Finds the w s r p consumer portlets before and after the current w s r p consumer portlet in the ordered set where wsrpConsumerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wsrpConsumerPortletId the primary key of the current w s r p consumer portlet
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a w s r p consumer portlet with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet[] findByWsrpConsumerId_PrevAndNext(
		long wsrpConsumerPortletId, long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence()
				   .findByWsrpConsumerId_PrevAndNext(wsrpConsumerPortletId,
			wsrpConsumerId, orderByComparator);
	}

	/**
	* Finds the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or throws a {@link com.liferay.wsrp.NoSuchConsumerPortletException} if it could not be found.
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @param portletHandle the portlet handle to search with
	* @return the matching w s r p consumer portlet
	* @throws com.liferay.wsrp.NoSuchConsumerPortletException if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByW_P(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence().findByW_P(wsrpConsumerId, portletHandle);
	}

	/**
	* Finds the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @param portletHandle the portlet handle to search with
	* @return the matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet fetchByW_P(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByW_P(wsrpConsumerId, portletHandle);
	}

	/**
	* Finds the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @param portletHandle the portlet handle to search with
	* @return the matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet fetchByW_P(
		long wsrpConsumerId, java.lang.String portletHandle,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByW_P(wsrpConsumerId, portletHandle, retrieveFromCache);
	}

	/**
	* Finds all the w s r p consumer portlets.
	*
	* @return the w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the w s r p consumer portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumer portlets to return
	* @param end the upper bound of the range of w s r p consumer portlets to return (not inclusive)
	* @return the range of w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the w s r p consumer portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumer portlets to return
	* @param end the upper bound of the range of w s r p consumer portlets to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the w s r p consumer portlets where wsrpConsumerId = &#63; from the database.
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByWsrpConsumerId(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByWsrpConsumerId(wsrpConsumerId);
	}

	/**
	* Removes the w s r p consumer portlet where wsrpConsumerId = &#63; and portletHandle = &#63; from the database.
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @param portletHandle the portlet handle to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByW_P(long wsrpConsumerId,
		java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		getPersistence().removeByW_P(wsrpConsumerId, portletHandle);
	}

	/**
	* Removes all the w s r p consumer portlets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the w s r p consumer portlets where wsrpConsumerId = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @return the number of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByWsrpConsumerId(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByWsrpConsumerId(wsrpConsumerId);
	}

	/**
	* Counts all the w s r p consumer portlets where wsrpConsumerId = &#63; and portletHandle = &#63;.
	*
	* @param wsrpConsumerId the wsrp consumer id to search with
	* @param portletHandle the portlet handle to search with
	* @return the number of matching w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByW_P(long wsrpConsumerId,
		java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByW_P(wsrpConsumerId, portletHandle);
	}

	/**
	* Counts all the w s r p consumer portlets.
	*
	* @return the number of w s r p consumer portlets
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static WSRPConsumerPortletPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WSRPConsumerPortletPersistence)PortletBeanLocatorUtil.locate(com.liferay.wsrp.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					WSRPConsumerPortletPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(WSRPConsumerPortletPersistence persistence) {
		_persistence = persistence;
	}

	private static WSRPConsumerPortletPersistence _persistence;
}