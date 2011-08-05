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

package com.liferay.portal.workflow.kaleo.service;

/**
 * <p>
 * This class is a wrapper for {@link KaleoTaskAssignmentInstanceLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentInstanceLocalService
 * @generated
 */
public class KaleoTaskAssignmentInstanceLocalServiceWrapper
	implements KaleoTaskAssignmentInstanceLocalService {
	public KaleoTaskAssignmentInstanceLocalServiceWrapper(
		KaleoTaskAssignmentInstanceLocalService kaleoTaskAssignmentInstanceLocalService) {
		_kaleoTaskAssignmentInstanceLocalService = kaleoTaskAssignmentInstanceLocalService;
	}

	/**
	* Adds the kaleo task assignment instance to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstance the kaleo task assignment instance to add
	* @return the kaleo task assignment instance that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.addKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance);
	}

	/**
	* Creates a new kaleo task assignment instance with the primary key. Does not add the kaleo task assignment instance to the database.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key for the new kaleo task assignment instance
	* @return the new kaleo task assignment instance
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance createKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId) {
		return _kaleoTaskAssignmentInstanceLocalService.createKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Deletes the kaleo task assignment instance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance to delete
	* @throws PortalException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentInstanceLocalService.deleteKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Deletes the kaleo task assignment instance from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstance the kaleo task assignment instance to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentInstanceLocalService.deleteKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the kaleo task assignment instance with the primary key.
	*
	* @param kaleoTaskAssignmentInstanceId the primary key of the kaleo task assignment instance to get
	* @return the kaleo task assignment instance
	* @throws PortalException if a kaleo task assignment instance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance getKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.getKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Gets a range of all the kaleo task assignment instances.
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
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.getKaleoTaskAssignmentInstances(start,
			end);
	}

	/**
	* Gets the number of kaleo task assignment instances.
	*
	* @return the number of kaleo task assignment instances
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoTaskAssignmentInstancesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.getKaleoTaskAssignmentInstancesCount();
	}

	/**
	* Updates the kaleo task assignment instance in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstance the kaleo task assignment instance to update
	* @return the kaleo task assignment instance that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance updateKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.updateKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance);
	}

	/**
	* Updates the kaleo task assignment instance in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentInstance the kaleo task assignment instance to update
	* @param merge whether to merge the kaleo task assignment instance with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo task assignment instance that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance updateKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.updateKaleoTaskAssignmentInstance(kaleoTaskAssignmentInstance,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.addKaleoTaskAssignmentInstance(kaleoTaskInstanceToken,
			assigneeClassName, assigneeClassPK, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> addTaskAssignmentInstances(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.addTaskAssignmentInstances(kaleoTaskInstanceToken,
			kaleoTaskAssignments, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance assignKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.assignKaleoTaskAssignmentInstance(kaleoTaskInstanceToken,
			assigneeClassName, assigneeClassPK, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance completeKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.completeKaleoTaskInstanceToken(kaleoTaskInstanceTokenId,
			serviceContext);
	}

	public void deleteCompanyKaleoTaskAssignmentInstances(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentInstanceLocalService.deleteCompanyKaleoTaskAssignmentInstances(companyId);
	}

	public void deleteKaleoDefinitionKaleoTaskAssignmentInstances(
		long kaleoDefintionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentInstanceLocalService.deleteKaleoDefinitionKaleoTaskAssignmentInstances(kaleoDefintionId);
	}

	public void deleteKaleoInstanceKaleoTaskAssignmentInstances(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentInstanceLocalService.deleteKaleoInstanceKaleoTaskAssignmentInstances(kaleoInstanceId);
	}

	public void deleteKaleoTaskAssignmentInstances(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentInstanceLocalService.deleteKaleoTaskAssignmentInstances(kaleoTaskInstanceToken);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentInstanceLocalService.getKaleoTaskAssignmentInstances(kaleoTaskInstanceTokenId);
	}

	public KaleoTaskAssignmentInstanceLocalService getWrappedKaleoTaskAssignmentInstanceLocalService() {
		return _kaleoTaskAssignmentInstanceLocalService;
	}

	private KaleoTaskAssignmentInstanceLocalService _kaleoTaskAssignmentInstanceLocalService;
}