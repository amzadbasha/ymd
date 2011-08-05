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

<%@ include file="/html/portlet/document_library/init.jsp" %>

<aui:form method="post" name="fm">
	<liferay-ui:header
		title="communities"
	/>

	<%
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_action", "/document_library/select_group");

	GroupSearch searchContainer = new GroupSearch(renderRequest, portletURL);
	%>

	<liferay-ui:search-form
		page="/html/portlet/enterprise_admin/group_search.jsp"
		searchContainer="<%= searchContainer %>"
	/>

	<div class="separator"><!-- --></div>

	<%
	GroupSearchTerms searchTerms = (GroupSearchTerms)searchContainer.getSearchTerms();

	List<Group> myPlaces = user.getMyPlaces();

	int total = myPlaces.size();

	searchContainer.setTotal(total);

	List<Group> results = myPlaces;

	searchContainer.setResults(results);

	List resultRows = searchContainer.getResultRows();

	for (int i = 0; i < results.size(); i++) {
		Group group = results.get(i);

		ResultRow row = new ResultRow(group, group.getGroupId(), i);

		String groupName = HtmlUtil.escape(group.getDescriptiveName());

		if (group.isUser()) {
			groupName = LanguageUtil.get(pageContext, "my-community");
		}

		StringBundler sb = new StringBundler(7);

		sb.append("javascript:opener.");
		sb.append(renderResponse.getNamespace());
		sb.append("selectGroup('");
		sb.append(group.getGroupId());
		sb.append("', '");
		sb.append(UnicodeFormatter.toString(groupName));
		sb.append("'); window.close();");

		String rowHREF = sb.toString();

		// Name

		row.addText(groupName, rowHREF);

		// Type

		row.addText(LanguageUtil.get(pageContext, group.getTypeLabel()), rowHREF);

		// Add result row

		resultRows.add(row);
	}
	%>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
</aui:form>