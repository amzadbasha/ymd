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

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
String viewUsersRedirect = ParamUtil.getString(request, "viewUsersRedirect");

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");

if (Validator.isNotNull(viewUsersRedirect)) {
	portletURL.setParameter("viewUsersRedirect", viewUsersRedirect);
}
%>

<c:if test="<%= Validator.isNotNull(viewUsersRedirect) %>">
	<aui:input name="viewUsersRedirect" type="hidden" value="<%= viewUsersRedirect %>" />
</c:if>

<liferay-ui:search-container
	searchContainer="<%= new UserSearch(renderRequest, portletURL) %>"
>
	<aui:input name="usersRedirect" type="hidden" value="<%= portletURL.toString() %>" />

	<%
	UserSearchTerms searchTerms = (UserSearchTerms)searchContainer.getSearchTerms();

	long organizationId = searchTerms.getOrganizationId();
	long userGroupId = searchTerms.getUserGroupId();

	Organization organization = null;

	if ((organizationId > 0)) {
		try {
			organization = OrganizationLocalServiceUtil.getOrganization(organizationId);
		}
		catch (NoSuchOrganizationException nsoe) {
		}
	}

	UserGroup userGroup = null;

	if (userGroupId > 0) {
		try {
			userGroup = UserGroupLocalServiceUtil.getUserGroup(userGroupId);
		}
		catch (NoSuchUserGroupException nsuge) {
		}
	}
	%>

	<c:if test="<%= organization != null %>">
		<aui:input name="<%= UserDisplayTerms.ORGANIZATION_ID %>" type="hidden" value="<%= organization.getOrganizationId() %>" />

		<h3><%= HtmlUtil.escape(LanguageUtil.format(pageContext, "users-of-x", organization.getName())) %></h3>
	</c:if>

	<c:if test="<%= userGroup != null %>">
		<aui:input name="<%= UserDisplayTerms.USER_GROUP_ID %>" type="hidden" value="<%= userGroup.getUserGroupId() %>" />

		<h3><%= LanguageUtil.format(pageContext, "users-of-x", HtmlUtil.escape(userGroup.getName())) %></h3>
	</c:if>

	<liferay-ui:search-form
		page="/html/portlet/directory/user_search.jsp"
	/>

	<%
	LinkedHashMap userParams = new LinkedHashMap();

	if (organizationId > 0) {
		userParams.put("usersOrgs", new Long(organizationId));
	}

	if (userGroupId > 0) {
		userParams.put("usersUserGroups", new Long(userGroupId));
	}
	%>

	<liferay-ui:search-container-results>
		<c:choose>
			<c:when test="<%= PropsValues.USERS_SEARCH_WITH_INDEX %>">
				<%@ include file="/html/portlet/enterprise_admin/user_search_results_index.jspf" %>
			</c:when>
			<c:otherwise>
				<%@ include file="/html/portlet/enterprise_admin/user_search_results_database.jspf" %>
			</c:otherwise>
		</c:choose>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="user2"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="struts_action" value="/directory/view_user" />
			<portlet:param name="tabs1" value="<%= HtmlUtil.escape(tabs1) %>" />
			<portlet:param name="redirect" value="<%= searchContainer.getIteratorURL().toString() %>" />
			<portlet:param name="p_u_i_d" value="<%= String.valueOf(user2.getUserId()) %>" />
		</liferay-portlet:renderURL>

		<%@ include file="/html/portlet/directory/user/search_columns.jspf" %>
	</liferay-ui:search-container-row>

	<c:if test="<%= (organization != null) || (userGroup != null) %>">
		<br />
	</c:if>

	<c:if test="<%= organization != null %>">
		<aui:input name="<%= UserDisplayTerms.ORGANIZATION_ID %>" type="hidden" value="<%= organization.getOrganizationId() %>" />

		<liferay-ui:message key="filter-by-organization" />: <%= HtmlUtil.escape(organization.getName()) %><br />
	</c:if>

	<c:if test="<%= userGroup != null %>">
		<aui:input name="<%= UserDisplayTerms.USER_GROUP_ID %>" type="hidden" value="<%= userGroup.getUserGroupId() %>" />

		<liferay-ui:message key="filter-by-user-group" />: <%= HtmlUtil.escape(userGroup.getName()) %><br />
	</c:if>

	<div class="separator"><!-- --></div>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>