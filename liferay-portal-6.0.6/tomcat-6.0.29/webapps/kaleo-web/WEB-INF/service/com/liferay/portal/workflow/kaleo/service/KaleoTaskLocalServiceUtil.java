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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the kaleo task local service. This utility wraps {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoTaskLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoTaskLocalServiceImpl
 * @generated
 */
public class KaleoTaskLocalServiceUtil {
	/**
	* Adds the kaleo task to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTask the kaleo task to add
	* @return the kaleo task that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask addKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoTask(kaleoTask);
	}

	/**
	* Creates a new kaleo task with the primary key. Does not add the kaleo task to the database.
	*
	* @param kaleoTaskId the primary key for the new kaleo task
	* @return the new kaleo task
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask createKaleoTask(
		long kaleoTaskId) {
		return getService().createKaleoTask(kaleoTaskId);
	}

	/**
	* Deletes the kaleo task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTaskId the primary key of the kaleo task to delete
	* @throws PortalException if a kaleo task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteKaleoTask(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoTask(kaleoTaskId);
	}

	/**
	* Deletes the kaleo task from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTask the kaleo task to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoTask(kaleoTask);
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
	* Gets the kaleo task with the primary key.
	*
	* @param kaleoTaskId the primary key of the kaleo task to get
	* @return the kaleo task
	* @throws PortalException if a kaleo task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask getKaleoTask(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTask(kaleoTaskId);
	}

	/**
	* Gets a range of all the kaleo tasks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo tasks to return
	* @param end the upper bound of the range of kaleo tasks to return (not inclusive)
	* @return the range of kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTask> getKaleoTasks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTasks(start, end);
	}

	/**
	* Gets the number of kaleo tasks.
	*
	* @return the number of kaleo tasks
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoTasksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoTasksCount();
	}

	/**
	* Updates the kaleo task in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTask the kaleo task to update
	* @return the kaleo task that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask updateKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoTask(kaleoTask);
	}

	/**
	* Updates the kaleo task in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoTask the kaleo task to update
	* @param merge whether to merge the kaleo task with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo task that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoTask updateKaleoTask(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoTask(kaleoTask, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTask addKaleoTask(
		long kaleoDefinitionId, long kaleoNodeId,
		com.liferay.portal.workflow.kaleo.definition.Task task,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoTask(kaleoDefinitionId, kaleoNodeId, task,
			serviceContext);
	}

	public static void deleteCompanyKaleoTasks(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoTasks(companyId);
	}

	public static void deleteKaleoDefinitionKaleoTasks(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoDefinitionKaleoTasks(kaleoDefinitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTask getKaleoNodeKaleoTask(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNodeKaleoTask(kaleoNodeId);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoTaskLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new KaleoTaskLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(KaleoTaskLocalService service) {
		_service = service;
	}

	private static KaleoTaskLocalService _service;
}