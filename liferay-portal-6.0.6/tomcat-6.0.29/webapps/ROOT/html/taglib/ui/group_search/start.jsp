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

<%@ include file="/html/taglib/init.jsp" %>

<%@ page import="com.liferay.portlet.enterpriseadmin.search.GroupSearch" %>
<%@ page import="com.liferay.portlet.enterpriseadmin.search.GroupSearchTerms" %>

<portlet:defineObjects />

<%
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-ui:group-search:portletURL");
RowChecker rowChecker = (RowChecker)request.getAttribute("liferay-ui:group-search:rowChecker");
LinkedHashMap groupParams = (LinkedHashMap)request.getAttribute("liferay-ui:group-search:groupParams");

GroupSearch searchContainer = new GroupSearch(renderRequest, portletURL);

request.setAttribute(WebKeys.SEARCH_CONTAINER, searchContainer);

searchContainer.setRowChecker(rowChecker);
%>

<liferay-ui:search-form
	page="/html/portlet/enterprise_admin/group_search.jsp"
	searchContainer="<%= searchContainer %>"
/>

<%
GroupSearchTerms searchTerms = (GroupSearchTerms)searchContainer.getSearchTerms();

List<Group> results = null;
int total = 0;
%>

<%@ include file="/html/portlet/enterprise_admin/group_search_results.jspf" %>

<%
searchContainer.setResults(results);
searchContainer.setTotal(total);
%>