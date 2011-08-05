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
 * This class is a wrapper for {@link KaleoDefinitionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoDefinitionLocalService
 * @generated
 */
public class KaleoDefinitionLocalServiceWrapper
	implements KaleoDefinitionLocalService {
	public KaleoDefinitionLocalServiceWrapper(
		KaleoDefinitionLocalService kaleoDefinitionLocalService) {
		_kaleoDefinitionLocalService = kaleoDefinitionLocalService;
	}

	/**
	* Adds the kaleo definition to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinition the kaleo definition to add
	* @return the kaleo definition that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition addKaleoDefinition(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.addKaleoDefinition(kaleoDefinition);
	}

	/**
	* Creates a new kaleo definition with the primary key. Does not add the kaleo definition to the database.
	*
	* @param kaleoDefinitionId the primary key for the new kaleo definition
	* @return the new kaleo definition
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition createKaleoDefinition(
		long kaleoDefinitionId) {
		return _kaleoDefinitionLocalService.createKaleoDefinition(kaleoDefinitionId);
	}

	/**
	* Deletes the kaleo definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinitionId the primary key of the kaleo definition to delete
	* @throws PortalException if a kaleo definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoDefinition(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoDefinitionLocalService.deleteKaleoDefinition(kaleoDefinitionId);
	}

	/**
	* Deletes the kaleo definition from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinition the kaleo definition to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoDefinition(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoDefinitionLocalService.deleteKaleoDefinition(kaleoDefinition);
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
		return _kaleoDefinitionLocalService.dynamicQuery(dynamicQuery);
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
		return _kaleoDefinitionLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _kaleoDefinitionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _kaleoDefinitionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the kaleo definition with the primary key.
	*
	* @param kaleoDefinitionId the primary key of the kaleo definition to get
	* @return the kaleo definition
	* @throws PortalException if a kaleo definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition getKaleoDefinition(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinition(kaleoDefinitionId);
	}

	/**
	* Gets a range of all the kaleo definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo definitions to return
	* @param end the upper bound of the range of kaleo definitions to return (not inclusive)
	* @return the range of kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> getKaleoDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinitions(start, end);
	}

	/**
	* Gets the number of kaleo definitions.
	*
	* @return the number of kaleo definitions
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinitionsCount();
	}

	/**
	* Updates the kaleo definition in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinition the kaleo definition to update
	* @return the kaleo definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition updateKaleoDefinition(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.updateKaleoDefinition(kaleoDefinition);
	}

	/**
	* Updates the kaleo definition in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDefinition the kaleo definition to update
	* @param merge whether to merge the kaleo definition with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition updateKaleoDefinition(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.updateKaleoDefinition(kaleoDefinition,
			merge);
	}

	public void activateKaleoDefinition(long kaleoDefinitionId,
		long startKaleoNodeId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoDefinitionLocalService.activateKaleoDefinition(kaleoDefinitionId,
			startKaleoNodeId, serviceContext);
	}

	public void activateKaleoDefinition(long kaleoDefinitionId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoDefinitionLocalService.activateKaleoDefinition(kaleoDefinitionId,
			serviceContext);
	}

	public void activateKaleoDefinition(java.lang.String name, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoDefinitionLocalService.activateKaleoDefinition(name, version,
			serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition addKaleoDefinition(
		java.lang.String name, java.lang.String title,
		java.lang.String description, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.addKaleoDefinition(name, title,
			description, version, serviceContext);
	}

	public void deactivateKaleoDefinition(java.lang.String name, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoDefinitionLocalService.deactivateKaleoDefinition(name, version,
			serviceContext);
	}

	public void deleteCompanyKaleoDefinitions(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoDefinitionLocalService.deleteCompanyKaleoDefinitions(companyId);
	}

	public void deleteKaleoDefinition(java.lang.String name, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoDefinitionLocalService.deleteKaleoDefinition(name, version,
			serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition getKaleoDefinition(
		java.lang.String name, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinition(name, version,
			serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> getKaleoDefinitions(
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinitions(active, start,
			end, orderByComparator, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> getKaleoDefinitions(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinitions(start, end,
			orderByComparator, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> getKaleoDefinitions(
		java.lang.String name, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinitions(name, active,
			start, end, orderByComparator, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> getKaleoDefinitions(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinitions(name, start,
			end, orderByComparator, serviceContext);
	}

	public int getKaleoDefinitionsCount(boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinitionsCount(active,
			serviceContext);
	}

	public int getKaleoDefinitionsCount(
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinitionsCount(serviceContext);
	}

	public int getKaleoDefinitionsCount(java.lang.String name, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinitionsCount(name,
			active, serviceContext);
	}

	public int getKaleoDefinitionsCount(java.lang.String name,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.getKaleoDefinitionsCount(name,
			serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition incrementKaleoDefinition(
		java.lang.String name, java.lang.String title,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.incrementKaleoDefinition(name,
			title, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition updateTitle(
		java.lang.String name, int version, java.lang.String title,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinitionLocalService.updateTitle(name, version, title,
			serviceContext);
	}

	public KaleoDefinitionLocalService getWrappedKaleoDefinitionLocalService() {
		return _kaleoDefinitionLocalService;
	}

	private KaleoDefinitionLocalService _kaleoDefinitionLocalService;
}