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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;

import java.util.List;

/**
 * The persistence utility for the kaleo action service. This utility wraps {@link KaleoActionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
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
 * @see KaleoActionPersistence
 * @see KaleoActionPersistenceImpl
 * @generated
 */
public class KaleoActionUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(KaleoAction kaleoAction) {
		getPersistence().clearCache(kaleoAction);
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
	public static List<KaleoAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoAction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoAction remove(KaleoAction kaleoAction)
		throws SystemException {
		return getPersistence().remove(kaleoAction);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoAction update(KaleoAction kaleoAction, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoAction, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoAction update(KaleoAction kaleoAction, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kaleoAction, merge, serviceContext);
	}

	/**
	* Caches the kaleo action in the entity cache if it is enabled.
	*
	* @param kaleoAction the kaleo action to cache
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction) {
		getPersistence().cacheResult(kaleoAction);
	}

	/**
	* Caches the kaleo actions in the entity cache if it is enabled.
	*
	* @param kaleoActions the kaleo actions to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> kaleoActions) {
		getPersistence().cacheResult(kaleoActions);
	}

	/**
	* Creates a new kaleo action with the primary key. Does not add the kaleo action to the database.
	*
	* @param kaleoActionId the primary key for the new kaleo action
	* @return the new kaleo action
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction create(
		long kaleoActionId) {
		return getPersistence().create(kaleoActionId);
	}

	/**
	* Removes the kaleo action with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoActionId the primary key of the kaleo action to remove
	* @return the kaleo action that was removed
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a kaleo action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction remove(
		long kaleoActionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence().remove(kaleoActionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoAction updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoAction, merge);
	}

	/**
	* Finds the kaleo action with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.NoSuchActionException} if it could not be found.
	*
	* @param kaleoActionId the primary key of the kaleo action to find
	* @return the kaleo action
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a kaleo action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction findByPrimaryKey(
		long kaleoActionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence().findByPrimaryKey(kaleoActionId);
	}

	/**
	* Finds the kaleo action with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoActionId the primary key of the kaleo action to find
	* @return the kaleo action, or <code>null</code> if a kaleo action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction fetchByPrimaryKey(
		long kaleoActionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoActionId);
	}

	/**
	* Finds all the kaleo actions where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Finds a range of all the kaleo actions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of kaleo actions to return
	* @param end the upper bound of the range of kaleo actions to return (not inclusive)
	* @return the range of matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Finds an ordered range of all the kaleo actions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of kaleo actions to return
	* @param end the upper bound of the range of kaleo actions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Finds the first kaleo action in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching kaleo action
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Finds the last kaleo action in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching kaleo action
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Finds the kaleo actions before and after the current kaleo action in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoActionId the primary key of the current kaleo action
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next kaleo action
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a kaleo action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction[] findByCompanyId_PrevAndNext(
		long kaleoActionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoActionId, companyId,
			orderByComparator);
	}

	/**
	* Finds all the kaleo actions where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @return the matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Finds a range of all the kaleo actions where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param start the lower bound of the range of kaleo actions to return
	* @param end the upper bound of the range of kaleo actions to return (not inclusive)
	* @return the range of matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	/**
	* Finds an ordered range of all the kaleo actions where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param start the lower bound of the range of kaleo actions to return
	* @param end the upper bound of the range of kaleo actions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	/**
	* Finds the first kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching kaleo action
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Finds the last kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching kaleo action
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	/**
	* Finds the kaleo actions before and after the current kaleo action in the ordered set where kaleoDefinitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoActionId the primary key of the current kaleo action
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next kaleo action
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a kaleo action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoActionId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoActionId,
			kaleoDefinitionId, orderByComparator);
	}

	/**
	* Finds all the kaleo actions where kaleoNodeId = &#63; and executionType = &#63;.
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @param executionType the execution type to search with
	* @return the matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findByKNI_ET(
		long kaleoNodeId, java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKNI_ET(kaleoNodeId, executionType);
	}

	/**
	* Finds a range of all the kaleo actions where kaleoNodeId = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @param executionType the execution type to search with
	* @param start the lower bound of the range of kaleo actions to return
	* @param end the upper bound of the range of kaleo actions to return (not inclusive)
	* @return the range of matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findByKNI_ET(
		long kaleoNodeId, java.lang.String executionType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKNI_ET(kaleoNodeId, executionType, start, end);
	}

	/**
	* Finds an ordered range of all the kaleo actions where kaleoNodeId = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @param executionType the execution type to search with
	* @param start the lower bound of the range of kaleo actions to return
	* @param end the upper bound of the range of kaleo actions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findByKNI_ET(
		long kaleoNodeId, java.lang.String executionType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKNI_ET(kaleoNodeId, executionType, start, end,
			orderByComparator);
	}

	/**
	* Finds the first kaleo action in the ordered set where kaleoNodeId = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @param executionType the execution type to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching kaleo action
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction findByKNI_ET_First(
		long kaleoNodeId, java.lang.String executionType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence()
				   .findByKNI_ET_First(kaleoNodeId, executionType,
			orderByComparator);
	}

	/**
	* Finds the last kaleo action in the ordered set where kaleoNodeId = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @param executionType the execution type to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching kaleo action
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a matching kaleo action could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction findByKNI_ET_Last(
		long kaleoNodeId, java.lang.String executionType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence()
				   .findByKNI_ET_Last(kaleoNodeId, executionType,
			orderByComparator);
	}

	/**
	* Finds the kaleo actions before and after the current kaleo action in the ordered set where kaleoNodeId = &#63; and executionType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kaleoActionId the primary key of the current kaleo action
	* @param kaleoNodeId the kaleo node id to search with
	* @param executionType the execution type to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next kaleo action
	* @throws com.liferay.portal.workflow.kaleo.NoSuchActionException if a kaleo action with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoAction[] findByKNI_ET_PrevAndNext(
		long kaleoActionId, long kaleoNodeId, java.lang.String executionType,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchActionException {
		return getPersistence()
				   .findByKNI_ET_PrevAndNext(kaleoActionId, kaleoNodeId,
			executionType, orderByComparator);
	}

	/**
	* Finds all the kaleo actions.
	*
	* @return the kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the kaleo actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo actions to return
	* @param end the upper bound of the range of kaleo actions to return (not inclusive)
	* @return the range of kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the kaleo actions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo actions to return
	* @param end the upper bound of the range of kaleo actions to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoAction> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo actions where companyId = &#63; from the database.
	*
	* @param companyId the company id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Removes all the kaleo actions where kaleoDefinitionId = &#63; from the database.
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Removes all the kaleo actions where kaleoNodeId = &#63; and executionType = &#63; from the database.
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @param executionType the execution type to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKNI_ET(long kaleoNodeId,
		java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKNI_ET(kaleoNodeId, executionType);
	}

	/**
	* Removes all the kaleo actions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the kaleo actions where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the number of matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Counts all the kaleo actions where kaleoDefinitionId = &#63;.
	*
	* @param kaleoDefinitionId the kaleo definition id to search with
	* @return the number of matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Counts all the kaleo actions where kaleoNodeId = &#63; and executionType = &#63;.
	*
	* @param kaleoNodeId the kaleo node id to search with
	* @param executionType the execution type to search with
	* @return the number of matching kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKNI_ET(long kaleoNodeId,
		java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKNI_ET(kaleoNodeId, executionType);
	}

	/**
	* Counts all the kaleo actions.
	*
	* @return the number of kaleo actions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoActionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoActionPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoActionPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(KaleoActionPersistence persistence) {
		_persistence = persistence;
	}

	private static KaleoActionPersistence _persistence;
}