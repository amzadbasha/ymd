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

/**
 * <p>
 * This class is a wrapper for {@link MeetupsEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MeetupsEntryLocalService
 * @generated
 */
public class MeetupsEntryLocalServiceWrapper implements MeetupsEntryLocalService {
	public MeetupsEntryLocalServiceWrapper(
		MeetupsEntryLocalService meetupsEntryLocalService) {
		_meetupsEntryLocalService = meetupsEntryLocalService;
	}

	/**
	* Adds the meetups entry to the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsEntry the meetups entry to add
	* @return the meetups entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry addMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.addMeetupsEntry(meetupsEntry);
	}

	/**
	* Creates a new meetups entry with the primary key. Does not add the meetups entry to the database.
	*
	* @param meetupsEntryId the primary key for the new meetups entry
	* @return the new meetups entry
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry createMeetupsEntry(
		long meetupsEntryId) {
		return _meetupsEntryLocalService.createMeetupsEntry(meetupsEntryId);
	}

	/**
	* Deletes the meetups entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsEntryId the primary key of the meetups entry to delete
	* @throws PortalException if a meetups entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMeetupsEntry(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_meetupsEntryLocalService.deleteMeetupsEntry(meetupsEntryId);
	}

	/**
	* Deletes the meetups entry from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsEntry the meetups entry to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		_meetupsEntryLocalService.deleteMeetupsEntry(meetupsEntry);
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
		return _meetupsEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _meetupsEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _meetupsEntryLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _meetupsEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the meetups entry with the primary key.
	*
	* @param meetupsEntryId the primary key of the meetups entry to get
	* @return the meetups entry
	* @throws PortalException if a meetups entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry getMeetupsEntry(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntry(meetupsEntryId);
	}

	/**
	* Gets a range of all the meetups entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of meetups entries to return
	* @param end the upper bound of the range of meetups entries to return (not inclusive)
	* @return the range of meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntries(start, end);
	}

	/**
	* Gets the number of meetups entries.
	*
	* @return the number of meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public int getMeetupsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntriesCount();
	}

	/**
	* Updates the meetups entry in the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsEntry the meetups entry to update
	* @return the meetups entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.updateMeetupsEntry(meetupsEntry);
	}

	/**
	* Updates the meetups entry in the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsEntry the meetups entry to update
	* @param merge whether to merge the meetups entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the meetups entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.updateMeetupsEntry(meetupsEntry, merge);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry addMeetupsEntry(
		long userId, java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
		int totalAttendees, int maxAttendees, double price, byte[] thumbnail)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.addMeetupsEntry(userId, title,
			description, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, totalAttendees,
			maxAttendees, price, thumbnail);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntriesByCompany(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntriesByCompany(companyId);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntriesByUser(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntriesByUser(userId);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		long userId, long meetupsEntryId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int totalAttendees, int maxAttendees, double price,
		byte[] thumbnail)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.updateMeetupsEntry(userId,
			meetupsEntryId, title, description, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute,
			totalAttendees, maxAttendees, price, thumbnail);
	}

	public MeetupsEntryLocalService getWrappedMeetupsEntryLocalService() {
		return _meetupsEntryLocalService;
	}

	private MeetupsEntryLocalService _meetupsEntryLocalService;
}