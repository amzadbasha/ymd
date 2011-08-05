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

package com.liferay.socialnetworking.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the meetups registration local service. This utility wraps {@link com.liferay.socialnetworking.service.impl.MeetupsRegistrationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.socialnetworking.service.impl.MeetupsRegistrationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistrationLocalService
 * @see com.liferay.socialnetworking.service.base.MeetupsRegistrationLocalServiceBaseImpl
 * @see com.liferay.socialnetworking.service.impl.MeetupsRegistrationLocalServiceImpl
 * @generated
 */
public class MeetupsRegistrationLocalServiceUtil {
	/**
	* Adds the meetups registration to the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistration the meetups registration to add
	* @return the meetups registration that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration addMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addMeetupsRegistration(meetupsRegistration);
	}

	/**
	* Creates a new meetups registration with the primary key. Does not add the meetups registration to the database.
	*
	* @param meetupsRegistrationId the primary key for the new meetups registration
	* @return the new meetups registration
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration createMeetupsRegistration(
		long meetupsRegistrationId) {
		return getService().createMeetupsRegistration(meetupsRegistrationId);
	}

	/**
	* Deletes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration to delete
	* @throws PortalException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteMeetupsRegistration(long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteMeetupsRegistration(meetupsRegistrationId);
	}

	/**
	* Deletes the meetups registration from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistration the meetups registration to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteMeetupsRegistration(meetupsRegistration);
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
	* Gets the meetups registration with the primary key.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration to get
	* @return the meetups registration
	* @throws PortalException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration getMeetupsRegistration(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMeetupsRegistration(meetupsRegistrationId);
	}

	/**
	* Gets a range of all the meetups registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of meetups registrations to return
	* @param end the upper bound of the range of meetups registrations to return (not inclusive)
	* @return the range of meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> getMeetupsRegistrations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMeetupsRegistrations(start, end);
	}

	/**
	* Gets the number of meetups registrations.
	*
	* @return the number of meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	public static int getMeetupsRegistrationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMeetupsRegistrationsCount();
	}

	/**
	* Updates the meetups registration in the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistration the meetups registration to update
	* @return the meetups registration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration updateMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateMeetupsRegistration(meetupsRegistration);
	}

	/**
	* Updates the meetups registration in the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistration the meetups registration to update
	* @param merge whether to merge the meetups registration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the meetups registration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.MeetupsRegistration updateMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateMeetupsRegistration(meetupsRegistration, merge);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> getMeetupsRegistrations(
		long meetupsEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getMeetupsRegistrations(meetupsEntryId, status, start, end);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration getMeetupsRegistration(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMeetupsRegistration(userId, meetupsEntryId);
	}

	public static int getMeetupsRegistrationsCount(long meetupsEntryId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMeetupsRegistrationsCount(meetupsEntryId, status);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration updateMeetupsRegistration(
		long userId, long meetupsEntryId, int status, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateMeetupsRegistration(userId, meetupsEntryId, status,
			comments);
	}

	public static void clearService() {
		_service = null;
	}

	public static MeetupsRegistrationLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					MeetupsRegistrationLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new MeetupsRegistrationLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(MeetupsRegistrationLocalService service) {
		_service = service;
	}

	private static MeetupsRegistrationLocalService _service;
}