<%
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
%>

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portlet.blogs.model.BlogsEntry" %>
<%@ page import="com.liferay.portlet.blogs.model.BlogsStatsUser" %>
<%@ page import="com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.blogs.service.BlogsStatsUserLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.blogs.util.comparator.StatsUserLastPostDateComparator" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String selectionMethod = preferences.getValue("selection-method", "users");
long organizationId = GetterUtil.getLong(preferences.getValue("organization-id", "0"));
String displayStyle = preferences.getValue("display-style", "user-name-and-image");
int max = GetterUtil.getInteger(preferences.getValue("max", "10"));

if (organizationId == 0) {
	Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);

	if (group.isOrganization()) {
		organizationId = group.getOrganizationId();
	}
}

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);
%>