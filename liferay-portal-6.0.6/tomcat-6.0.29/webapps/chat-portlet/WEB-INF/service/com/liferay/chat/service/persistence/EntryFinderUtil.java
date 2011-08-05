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

package com.liferay.chat.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryFinderUtil {
	public static java.util.List<com.liferay.chat.model.Entry> findByEmptyContent(
		long fromUserId, long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByEmptyContent(fromUserId, toUserId, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByNew(
		long userId, long createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByNew(userId, createDate, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByOld(
		long createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByOld(createDate, start, end);
	}

	public static EntryFinder getFinder() {
		if (_finder == null) {
			_finder = (EntryFinder)PortletBeanLocatorUtil.locate(com.liferay.chat.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					EntryFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(EntryFinder finder) {
		_finder = finder;
	}

	private static EntryFinder _finder;
}