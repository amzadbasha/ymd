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
 * This class is a wrapper for {@link KaleoNotificationRecipientLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationRecipientLocalService
 * @generated
 */
public class KaleoNotificationRecipientLocalServiceWrapper
	implements KaleoNotificationRecipientLocalService {
	public KaleoNotificationRecipientLocalServiceWrapper(
		KaleoNotificationRecipientLocalService kaleoNotificationRecipientLocalService) {
		_kaleoNotificationRecipientLocalService = kaleoNotificationRecipientLocalService;
	}

	/**
	* Adds the kaleo notification recipient to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipient the kaleo notification recipient to add
	* @return the kaleo notification recipient that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient addKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.addKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	/**
	* Creates a new kaleo notification recipient with the primary key. Does not add the kaleo notification recipient to the database.
	*
	* @param kaleoNotificationRecipientId the primary key for the new kaleo notification recipient
	* @return the new kaleo notification recipient
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient createKaleoNotificationRecipient(
		long kaleoNotificationRecipientId) {
		return _kaleoNotificationRecipientLocalService.createKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	/**
	* Deletes the kaleo notification recipient with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipientId the primary key of the kaleo notification recipient to delete
	* @throws PortalException if a kaleo notification recipient with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationRecipientLocalService.deleteKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	/**
	* Deletes the kaleo notification recipient from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipient the kaleo notification recipient to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationRecipientLocalService.deleteKaleoNotificationRecipient(kaleoNotificationRecipient);
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
		return _kaleoNotificationRecipientLocalService.dynamicQuery(dynamicQuery);
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
		return _kaleoNotificationRecipientLocalService.dynamicQuery(dynamicQuery,
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
		return _kaleoNotificationRecipientLocalService.dynamicQuery(dynamicQuery,
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
		return _kaleoNotificationRecipientLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the kaleo notification recipient with the primary key.
	*
	* @param kaleoNotificationRecipientId the primary key of the kaleo notification recipient to get
	* @return the kaleo notification recipient
	* @throws PortalException if a kaleo notification recipient with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient getKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.getKaleoNotificationRecipient(kaleoNotificationRecipientId);
	}

	/**
	* Gets a range of all the kaleo notification recipients.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo notification recipients to return
	* @param end the upper bound of the range of kaleo notification recipients to return (not inclusive)
	* @return the range of kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.getKaleoNotificationRecipients(start,
			end);
	}

	/**
	* Gets the number of kaleo notification recipients.
	*
	* @return the number of kaleo notification recipients
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoNotificationRecipientsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.getKaleoNotificationRecipientsCount();
	}

	/**
	* Updates the kaleo notification recipient in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipient the kaleo notification recipient to update
	* @return the kaleo notification recipient that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.updateKaleoNotificationRecipient(kaleoNotificationRecipient);
	}

	/**
	* Updates the kaleo notification recipient in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationRecipient the kaleo notification recipient to update
	* @param merge whether to merge the kaleo notification recipient with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo notification recipient that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.updateKaleoNotificationRecipient(kaleoNotificationRecipient,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient addKaleoNotificationRecipient(
		long kaleoDefinitionId, long kaleoNotificationId,
		com.liferay.portal.workflow.kaleo.definition.Recipient recipient,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.addKaleoNotificationRecipient(kaleoDefinitionId,
			kaleoNotificationId, recipient, serviceContext);
	}

	public void deleteCompanyKaleoNotificationRecipients(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationRecipientLocalService.deleteCompanyKaleoNotificationRecipients(companyId);
	}

	public void deleteKaleoDefinitionKaleoNotificationRecipients(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationRecipientLocalService.deleteKaleoDefinitionKaleoNotificationRecipients(kaleoDefinitionId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipientLocalService.getKaleoNotificationRecipients(kaleoNotificationId);
	}

	public KaleoNotificationRecipientLocalService getWrappedKaleoNotificationRecipientLocalService() {
		return _kaleoNotificationRecipientLocalService;
	}

	private KaleoNotificationRecipientLocalService _kaleoNotificationRecipientLocalService;
}