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

package com.liferay.wsrp.service;

import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The interface for the w s r p producer local service.
 *
 * <p>
 * Never modify or reference this interface directly. Always use {@link WSRPProducerLocalServiceUtil} to access the w s r p producer local service. Add custom service methods to {@link com.liferay.wsrp.service.impl.WSRPProducerLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducerLocalServiceUtil
 * @see com.liferay.wsrp.service.base.WSRPProducerLocalServiceBaseImpl
 * @see com.liferay.wsrp.service.impl.WSRPProducerLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WSRPProducerLocalService {
	/**
	* Adds the w s r p producer to the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer to add
	* @return the w s r p producer that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new w s r p producer with the primary key. Does not add the w s r p producer to the database.
	*
	* @param wsrpProducerId the primary key for the new w s r p producer
	* @return the new w s r p producer
	*/
	public com.liferay.wsrp.model.WSRPProducer createWSRPProducer(
		long wsrpProducerId);

	/**
	* Deletes the w s r p producer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducerId the primary key of the w s r p producer to delete
	* @throws PortalException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWSRPProducer(long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the w s r p producer from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

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
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the w s r p producer with the primary key.
	*
	* @param wsrpProducerId the primary key of the w s r p producer to get
	* @return the w s r p producer
	* @throws PortalException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.wsrp.model.WSRPProducer getWSRPProducer(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets a range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers to return
	* @param end the upper bound of the range of w s r p producers to return (not inclusive)
	* @return the range of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the number of w s r p producers.
	*
	* @return the number of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWSRPProducersCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the w s r p producer in the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer to update
	* @return the w s r p producer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the w s r p producer in the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer to update
	* @param merge whether to merge the w s r p producer with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the w s r p producer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(long userId,
		java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(long userId,
		long groupId, java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWSRPProducersCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		long wsrpProducerId, java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}