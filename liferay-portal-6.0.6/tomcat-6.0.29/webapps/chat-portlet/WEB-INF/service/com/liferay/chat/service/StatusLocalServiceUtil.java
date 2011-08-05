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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the status local service. This utility wraps {@link com.liferay.chat.service.impl.StatusLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.chat.service.impl.StatusLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StatusLocalService
 * @see com.liferay.chat.service.base.StatusLocalServiceBaseImpl
 * @see com.liferay.chat.service.impl.StatusLocalServiceImpl
 * @generated
 */
public class StatusLocalServiceUtil {
	/**
	* Adds the status to the database. Also notifies the appropriate model listeners.
	*
	* @param status the status to add
	* @return the status that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Status addStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addStatus(status);
	}

	/**
	* Creates a new status with the primary key. Does not add the status to the database.
	*
	* @param statusId the primary key for the new status
	* @return the new status
	*/
	public static com.liferay.chat.model.Status createStatus(long statusId) {
		return getService().createStatus(statusId);
	}

	/**
	* Deletes the status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statusId the primary key of the status to delete
	* @throws PortalException if a status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteStatus(statusId);
	}

	/**
	* Deletes the status from the database. Also notifies the appropriate model listeners.
	*
	* @param status the status to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteStatus(com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteStatus(status);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the status with the primary key.
	*
	* @param statusId the primary key of the status to get
	* @return the status
	* @throws PortalException if a status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Status getStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getStatus(statusId);
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
	public static java.util.List<com.liferay.chat.model.Status> getStatuses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getStatuses(start, end);
	}

	/**
	* Gets the number of statuses.
	*
	* @return the number of statuses
	* @throws SystemException if a system exception occurred
	*/
	public static int getStatusesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getStatusesCount();
	}

	/**
	* Updates the status in the database. Also notifies the appropriate model listeners.
	*
	* @param status the status to update
	* @return the status that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStatus(status);
	}

	/**
	* Updates the status in the database. Also notifies the appropriate model listeners.
	*
	* @param status the status to update
	* @param merge whether to merge the status with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the status that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStatus(status, merge);
	}

	public static java.util.List<java.lang.Object[]> getAllStatuses(
		long companyId, long userId, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAllStatuses(companyId, userId, modifiedDate, start, end);
	}

	public static java.util.List<java.lang.Object[]> getGroupStatuses(
		long userId, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupStatuses(userId, modifiedDate, start, end);
	}

	public static java.util.List<java.lang.Object[]> getSocialStatuses(
		long userId, int type, long modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSocialStatuses(userId, type, modifiedDate, start, end);
	}

	public static com.liferay.chat.model.Status getUserStatus(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserStatus(userId);
	}

	public static com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStatus(userId, modifiedDate);
	}

	public static com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate, int online, int awake,
		java.lang.String activePanelId, java.lang.String message, int playSound)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, modifiedDate, online, awake,
			activePanelId, message, playSound);
	}

	public static void clearService() {
		_service = null;
	}

	public static StatusLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					StatusLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new StatusLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(StatusLocalService service) {
		_service = service;
	}

	private static StatusLocalService _service;
}