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
 * This class is a wrapper for {@link KaleoTaskAssignmentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentLocalService
 * @generated
 */
public class KaleoTaskAssignmentLocalServiceWrapper
	implements KaleoTaskAssignmentLocalService {
	public KaleoTaskAssignmentLocalServiceWrapper(
		KaleoTaskAssignmentLocalService kaleoTaskAssignmentLocalService) {
		_kaleoTaskAssignmentLocalService = kaleoTaskAssignmentLocalService;
	}

	/**
	* Adds the kaleo task assignment to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignment the kaleo task assignment to add
	* @return the kaleo task assignment that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment addKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.addKaleoTaskAssignment(kaleoTaskAssignment);
	}

	/**
	* Creates a new kaleo task assignment with the primary key. Does not add the kaleo task assignment to the database.
	*
	* @param kaleoTaskAssignmentId the primary key for the new kaleo task assignment
	* @return the new kaleo task assignment
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment createKaleoTaskAssignment(
		long kaleoTaskAssignmentId) {
		return _kaleoTaskAssignmentLocalService.createKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	/**
	* Deletes the kaleo task assignment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignmentId the primary key of the kaleo task assignment to delete
	* @throws PortalException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoTaskAssignment(long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentLocalService.deleteKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	/**
	* Deletes the kaleo task assignment from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignment the kaleo task assignment to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentLocalService.deleteKaleoTaskAssignment(kaleoTaskAssignment);
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
		return _kaleoTaskAssignmentLocalService.dynamicQuery(dynamicQuery);
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
		return _kaleoTaskAssignmentLocalService.dynamicQuery(dynamicQuery,
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
		return _kaleoTaskAssignmentLocalService.dynamicQuery(dynamicQuery,
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
		return _kaleoTaskAssignmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the kaleo task assignment with the primary key.
	*
	* @param kaleoTaskAssignmentId the primary key of the kaleo task assignment to get
	* @return the kaleo task assignment
	* @throws PortalException if a kaleo task assignment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment getKaleoTaskAssignment(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignment(kaleoTaskAssignmentId);
	}

	/**
	* Gets a range of all the kaleo task assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo task assignments to return
	* @param end the upper bound of the range of kaleo task assignments to return (not inclusive)
	* @return the range of kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(start,
			end);
	}

	/**
	* Gets the number of kaleo task assignments.
	*
	* @return the number of kaleo task assignments
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoTaskAssignmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignmentsCount();
	}

	/**
	* Updates the kaleo task assignment in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignment the kaleo task assignment to update
	* @return the kaleo task assignment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.updateKaleoTaskAssignment(kaleoTaskAssignment);
	}

	/**
	* Updates the kaleo task assignment in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskAssignment the kaleo task assignment to update
	* @param merge whether to merge the kaleo task assignment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo task assignment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateKaleoTaskAssignment(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.updateKaleoTaskAssignment(kaleoTaskAssignment,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment addKaleoTaskAssignment(
		long kaleoDefinitionId, long kaleoNodeId, long kaleoTaskId,
		com.liferay.portal.workflow.kaleo.definition.Assignment assignment,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.addKaleoTaskAssignment(kaleoDefinitionId,
			kaleoNodeId, kaleoTaskId, assignment, serviceContext);
	}

	public void deleteCompanyKaleoTaskAssignments(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentLocalService.deleteCompanyKaleoTaskAssignments(companyId);
	}

	public void deleteKaleoDefinitionKaleoTaskAssignments(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignmentLocalService.deleteKaleoDefinitionKaleoTaskAssignments(kaleoDefinitionId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(kaleoTaskId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments(
		java.lang.String assigneeClassName, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignments(assigneeClassName,
			kaleoTaskId);
	}

	public int getKaleoTaskAssignmentsCount(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignmentsCount(kaleoTaskId);
	}

	public int getKaleoTaskAssignmentsCount(
		java.lang.String assigneeClassName, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignmentLocalService.getKaleoTaskAssignmentsCount(assigneeClassName,
			kaleoTaskId);
	}

	public KaleoTaskAssignmentLocalService getWrappedKaleoTaskAssignmentLocalService() {
		return _kaleoTaskAssignmentLocalService;
	}

	private KaleoTaskAssignmentLocalService _kaleoTaskAssignmentLocalService;
}