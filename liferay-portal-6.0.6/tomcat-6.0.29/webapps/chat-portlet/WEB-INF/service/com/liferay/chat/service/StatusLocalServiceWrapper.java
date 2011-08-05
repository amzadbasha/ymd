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

package com.liferay.chat.service;

/**
 * <p>
 * This class is a wrapper for {@link StatusLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       StatusLocalService
 * @generated
 */
public class StatusLocalServiceWrapper implements StatusLocalService {
	public StatusLocalServiceWrapper(StatusLocalService statusLocalService) {
		_statusLocalService = statusLocalService;
	}

	/**
	* Adds the status to the database. Also notifies the appropriate model listeners.
	*
	* @param status the status to add
	* @return the status that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status addStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.addStatus(status);
	}

	/**
	* Creates a new status with the primary key. Does not add the status to the database.
	*
	* @param statusId the primary key for the new status
	* @return the new status
	*/
	public com.liferay.chat.model.Status createStatus(long statusId) {
		return _statusLocalService.createStatus(statusId);
	}

	/**
	* Deletes the status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statusId the primary key of the status to delete
	* @throws PortalException if a status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_statusLocalService.deleteStatus(statusId);
	}

	/**
	* Deletes the status from the database. Also notifies the appropriate model listeners.
	*
	* @param status the status to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteStatus(com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		_statusLocalService.deleteStatus(status);
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
		return _statusLocalService.dynamicQuery(dynamicQuery);
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
		return _statusLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _statusLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _statusLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the status with the primary key.
	*
	* @param statusId the primary key of the status to get
	* @return the status
	* @throws PortalException if a status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status getStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getStatus(statusId);
	}

	/**
	* Gets a range of all the statuses.
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
	public java.util.List<com.liferay.chat.model.Status> getStatuses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getStatuses(start, end);
	}

	/**
	* Gets the number of statuses.
	*
	* @return the number of statuses
	* @throws SystemException if a system exception occurred
	*/
	public int getStatusesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getStatusesCount();
	}

	/**
	* Updates the status in the database. Also notifies the appropriate model listeners.
	*
	* @param status the status to update
	* @return the status that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.updateStatus(status);
	}

	/**
	* Updates the status in the database. Also notifies the appropriate model listeners.
	*
	* @param status the status to update
	* @param merge whether to merge the status with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the status that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.updateStatus(status, merge);
	}

	public java.util.List<java.lang.Object[]> getAllStatuses(long companyId,
		long userId, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getAllStatuses(companyId, userId,
			modifiedDate, start, end);
	}

	public java.util.List<java.lang.Object[]> getGroupStatuses(long userId,
		long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getGroupStatuses(userId, modifiedDate,
			start, end);
	}

	public java.util.List<java.lang.Object[]> getSocialStatuses(long userId,
		int type, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getSocialStatuses(userId, type,
			modifiedDate, start, end);
	}

	public com.liferay.chat.model.Status getUserStatus(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.getUserStatus(userId);
	}

	public com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.updateStatus(userId, modifiedDate);
	}

	public com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate, int online, int awake,
		java.lang.String activePanelId, java.lang.String message, int playSound)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _statusLocalService.updateStatus(userId, modifiedDate, online,
			awake, activePanelId, message, playSound);
	}

	public StatusLocalService getWrappedStatusLocalService() {
		return _statusLocalService;
	}

	private StatusLocalService _statusLocalService;
}