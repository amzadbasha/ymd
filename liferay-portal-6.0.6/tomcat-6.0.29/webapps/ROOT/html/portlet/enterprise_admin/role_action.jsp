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

<%@ include file="/html/portlet/enterprise_admin/init.jsp" %>

<%
RoleSearch searchContainer = (RoleSearch)request.getAttribute("liferay-ui:search:searchContainer");

String redirect = searchContainer.getIteratorURL().toString();

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Role role = (Role)row.getObject();

String name = role.getName();

boolean unassignableRole = false;

if (name.equals(RoleConstants.GUEST) || name.equals(RoleConstants.OWNER) || name.equals(RoleConstants.USER)) {
	unassignableRole = true;
}
%>

<liferay-ui:icon-menu>
	<c:if test="<%= RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="struts_action" value="/enterprise_admin/edit_role" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="roleId" value="<%= String.valueOf(role.getRoleId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= !unassignableRole && RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Role.class.getName() %>"
			modelResourceDescription="<%= role.getTitle(locale) %>"
			resourcePrimKey="<%= String.valueOf(role.getRoleId()) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
	</c:if>

	<c:if test="<%= !name.equals(RoleConstants.ADMINISTRATOR) && !name.equals(RoleConstants.COMMUNITY_ADMINISTRATOR) && !name.equals(RoleConstants.COMMUNITY_OWNER) && !name.equals(RoleConstants.ORGANIZATION_ADMINISTRATOR) && !name.equals(RoleConstants.ORGANIZATION_OWNER) && !name.equals(RoleConstants.OWNER) && RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.DEFINE_PERMISSIONS) %>">
		<portlet:renderURL var="editRolePermissionsURL">
			<portlet:param name="struts_action" value="/enterprise_admin/edit_role_permissions" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.VIEW %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="roleId" value="<%= String.valueOf(role.getRoleId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="define_permissions" url="<%= editRolePermissionsURL %>" />
	</c:if>

	<c:if test="<%= !unassignableRole && (role.getType() == RoleConstants.TYPE_REGULAR) && RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.ASSIGN_MEMBERS) %>">
		<portlet:renderURL var="assignMembersURL">
			<portlet:param name="struts_action" value="/enterprise_admin/edit_role_assignments" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="roleId" value="<%= String.valueOf(role.getRoleId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="assign" message="assign-members" url="<%= assignMembersURL %>" />
	</c:if>

	<c:if test="<%= !unassignableRole && (role.getType() == RoleConstants.TYPE_REGULAR) && RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.VIEW) %>">
		<portlet:renderURL var="viewUsersURL">
			<portlet:param name="struts_action" value="/enterprise_admin/view" />
			<portlet:param name="tabs1" value="users" />
			<portlet:param name="viewUsersRedirect" value="<%= currentURL %>" />
			<portlet:param name="roleId" value="<%= String.valueOf(role.getRoleId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon image="view_users" message="view-users" method="get" url="<%= viewUsersURL %>" />
	</c:if>

	<c:if test="<%= !PortalUtil.isSystemRole(name) && RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.DELETE) %>">
		<portlet:actionURL var="deleteURL">
			<portlet:param name="struts_action" value="/enterprise_admin/edit_role" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="roleId" value="<%= String.valueOf(role.getRoleId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL %>" />
	</c:if>
</liferay-ui:icon-menu>