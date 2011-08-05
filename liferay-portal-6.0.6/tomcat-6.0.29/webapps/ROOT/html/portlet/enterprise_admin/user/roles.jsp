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
User selUser = (User)request.getAttribute("user.selUser");
List<Group> groups = (List<Group>)request.getAttribute("user.groups");
List<Organization> organizations = (List<Organization>)request.getAttribute("user.organizations");
Long[] organizationIds = EnterpriseAdminUtil.getOrganizationIds(organizations);
List<Role> roles = (List<Role>)request.getAttribute("user.roles");
List<UserGroupRole> communityRoles = (List<UserGroupRole>)request.getAttribute("user.communityRoles");
List<UserGroupRole> organizationRoles = (List<UserGroupRole>)request.getAttribute("user.organizationRoles");
List<Group> allGroups = (List<Group>)request.getAttribute("user.allGroups");

List <UserGroupRole> userGroupRoles = new ArrayList<UserGroupRole>();

userGroupRoles.addAll(communityRoles);
userGroupRoles.addAll(organizationRoles);
%>

<liferay-util:buffer var="removeRoleIcon">
	<liferay-ui:icon
		image="unlink"
		label="<%= true %>"
		message="remove"
	/>
</liferay-util:buffer>

<aui:input name="groupRolesRoleIds" type="hidden" value='<%= ListUtil.toString(userGroupRoles, "roleId") %>' />
<aui:input name="groupRolesGroupIds" type="hidden" value='<%= ListUtil.toString(userGroupRoles, "groupId") %>' />

<h3><liferay-ui:message key="regular-roles" /></h3>

<liferay-ui:search-container
	id='<%= renderResponse.getNamespace() + "rolesSearchContainer" %>'
	headerNames="title"
>
	<liferay-ui:search-container-results
		results="<%= roles %>"
		total="<%= roles.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Role"
		keyProperty="roleId"
		modelVar="role"
	>
		<liferay-util:param name="className" value="<%= EnterpriseAdminUtil.getCssClassName(role) %>" />
		<liferay-util:param name="classHoverName" value="<%= EnterpriseAdminUtil.getCssClassName(role) %>" />

		<liferay-ui:search-container-column-text
			name="title"
			value="<%= HtmlUtil.escape(role.getTitle(locale)) %>"
		/>

		<c:if test="<%= !portletName.equals(PortletKeys.MY_ACCOUNT) %>">
			<liferay-ui:search-container-column-text>
				<a class="modify-link" data-rowId="<%= role.getRoleId() %>" href="javascript:;"><%= removeRoleIcon %></a>
			</liferay-ui:search-container-column-text>
		</c:if>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>

<c:if test="<%= !portletName.equals(PortletKeys.MY_ACCOUNT) %>">
	<liferay-ui:icon
		cssClass="modify-link"
		image="add"
		label="<%= true %>"
		message="select"
		url='<%= "javascript:" + renderResponse.getNamespace() + "openRegularRoleSelector();" %>'
	/>
</c:if>

<br /><br />

<h3><liferay-ui:message key="inherited-roles" /></h3>

<liferay-ui:search-container
	id='<%= renderResponse.getNamespace() + "inheritedRolesSearchContainer" %>'
	headerNames="title"
>
	<liferay-ui:search-container-results
		results="<%= allGroups %>"
		total="<%= allGroups.size() %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Group"
		keyProperty="groupId"
		modelVar="group"
	>

		<%
		List<Role> groupRoles = RoleLocalServiceUtil.getGroupRoles(group.getGroupId());

		if (!groupRoles.isEmpty()) {
			Role groupRole = groupRoles.get(0);
		%>

			<liferay-util:param name="className" value="<%= EnterpriseAdminUtil.getCssClassName(groupRole) %>" />
			<liferay-util:param name="classHoverName" value="<%= EnterpriseAdminUtil.getCssClassName(groupRole) %>" />

			<liferay-ui:search-container-column-text
				name="group"
				value="<%= HtmlUtil.escape(group.getDescriptiveName()) %>"
			/>
			<liferay-ui:search-container-column-text
				name="title"
				value='<%= HtmlUtil.escape(ListUtil.toString(groupRoles, "name")) %>'
			/>

		<%
		}
		%>

	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>

<br /><br />

<h3><liferay-ui:message key="organization-roles" /></h3>

<c:choose>
	<c:when test="<%= organizations.isEmpty() %>">
		<liferay-ui:message key="this-user-does-not-belong-to-an-organization-to-which-an-organization-role-can-be-assigned" />
	</c:when>
	<c:otherwise>
		<liferay-ui:search-container
			id='<%= renderResponse.getNamespace() + "organizationRolesSearchContainer" %>'
			headerNames="title,organization,"
		>
			<liferay-ui:search-container-results
				results="<%= organizationRoles %>"
				total="<%= organizationRoles.size() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.UserGroupRole"
				keyProperty="roleId"
				modelVar="userGroupRole"
			>
				<liferay-util:param name="className" value="<%= EnterpriseAdminUtil.getCssClassName(userGroupRole.getRole()) %>" />
				<liferay-util:param name="classHoverName" value="<%= EnterpriseAdminUtil.getCssClassName(userGroupRole.getRole()) %>" />

				<liferay-ui:search-container-column-text
					name="title"
					value="<%= HtmlUtil.escape(userGroupRole.getRole().getTitle(locale)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="organization"
					value="<%= HtmlUtil.escape(userGroupRole.getGroup().getDescriptiveName()) %>"
				/>

				<c:if test="<%= !portletName.equals(PortletKeys.MY_ACCOUNT) %>">
					<liferay-ui:search-container-column-text>
						<a class="modify-link" data-groupId="<%= userGroupRole.getGroupId() %>" data-rowId="<%= userGroupRole.getRoleId() %>" href="javascript:;"><%= removeRoleIcon %></a>
					</liferay-ui:search-container-column-text>
				</c:if>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>

		<c:if test="<%= !portletName.equals(PortletKeys.MY_ACCOUNT) %>">
			<liferay-ui:icon
				cssClass="modify-link"
				image="add"
				label="<%= true %>"
				message="select"
				url='<%= "javascript:" + renderResponse.getNamespace() + "openOrganizationRoleSelector();" %>'
			/>
		</c:if>

		<aui:script use="liferay-search-container">
			var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />organizationRolesSearchContainer');

			searchContainer.get('contentBox').delegate(
				'click',
				function(event) {
					var link = event.currentTarget;
					var tr = link.ancestor('tr');

					var rowId = link.getAttribute('data-rowId');
					var groupId =link.getAttribute('data-groupId');

					searchContainer.deleteRow(tr, rowId);

					<portlet:namespace />deleteGroupRole(rowId, groupId);

					<portlet:namespace />trackChanges();
				},
				'.modify-link'
			);
		</aui:script>
	</c:otherwise>
</c:choose>

<br /><br />

<h3><liferay-ui:message key="community-roles" /></h3>

<c:choose>
	<c:when test="<%= groups.isEmpty() %>">
		<liferay-ui:message key="this-user-does-not-belong-to-a-community-to-which-a-community-role-can-be-assigned" />
	</c:when>
	<c:otherwise>
		<liferay-ui:search-container
			id='<%= renderResponse.getNamespace() + "communityRolesSearchContainer" %>'
			headerNames="title,community,"
		>
			<liferay-ui:search-container-results
				results="<%= communityRoles %>"
				total="<%= communityRoles.size() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.UserGroupRole"
				keyProperty="roleId"
				modelVar="userGroupRole"
			>
				<liferay-util:param name="className" value="<%= EnterpriseAdminUtil.getCssClassName(userGroupRole.getRole()) %>" />
				<liferay-util:param name="classHoverName" value="<%= EnterpriseAdminUtil.getCssClassName(userGroupRole.getRole()) %>" />

				<liferay-ui:search-container-column-text
					name="title"
					value="<%= HtmlUtil.escape(userGroupRole.getRole().getTitle(locale)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="community"
					value="<%= HtmlUtil.escape(userGroupRole.getGroup().getDescriptiveName()) %>"
				/>

				<c:if test="<%= !portletName.equals(PortletKeys.MY_ACCOUNT) %>">
					<liferay-ui:search-container-column-text>
						<a class="modify-link" data-groupId="<%= userGroupRole.getGroupId() %>" data-rowId="<%= userGroupRole.getRoleId() %>" href="javascript:;"><%= removeRoleIcon %></a>
					</liferay-ui:search-container-column-text>
				</c:if>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>

		<c:if test="<%= !portletName.equals(PortletKeys.MY_ACCOUNT) %>">
			<liferay-ui:icon
				cssClass="modify-link"
				image="add"
				label="<%= true %>"
				message="select"
				url='<%= "javascript:" + renderResponse.getNamespace() + "openCommunityRoleSelector();" %>'
			/>
		</c:if>

		<aui:script use="liferay-search-container">
			var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />communityRolesSearchContainer');

			searchContainer.get('contentBox').delegate(
				'click',
				function(event) {
					var link = event.currentTarget;
					var tr = link.ancestor('tr');

					var rowId = link.getAttribute('data-rowId');
					var groupId =link.getAttribute('data-groupId');

					searchContainer.deleteRow(tr, rowId);

					<portlet:namespace />deleteGroupRole(rowId, groupId);
				},
				'.modify-link'
			);
		</aui:script>
	</c:otherwise>
</c:choose>

<aui:script>
	var <portlet:namespace />groupRolesGroupIds = ['<%= ListUtil.toString(userGroupRoles, "groupId", "', '") %>'];
	var <portlet:namespace />groupRolesRoleIds = ['<%= ListUtil.toString(userGroupRoles, "roleId", "', '") %>'];

	function <portlet:namespace />deleteGroupRole(roleId, groupId) {
		for (var i = 0; i < <portlet:namespace />groupRolesRoleIds.length; i++) {
			if ((<portlet:namespace />groupRolesRoleIds[i] == roleId) && (<portlet:namespace />groupRolesGroupIds[i] == groupId)) {
				 <portlet:namespace />groupRolesGroupIds.splice(i, 1);
				 <portlet:namespace />groupRolesRoleIds.splice(i, 1);

				break;
			}
		}

		document.<portlet:namespace />fm.<portlet:namespace />groupRolesGroupIds.value = <portlet:namespace />groupRolesGroupIds.join(',');
		document.<portlet:namespace />fm.<portlet:namespace />groupRolesRoleIds.value = <portlet:namespace />groupRolesRoleIds.join(',');
	}

	function <portlet:namespace />openCommunityRoleSelector() {
		<portlet:namespace />openRoleSelector('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/enterprise_admin/select_community_role" /><portlet:param name="step" value="1" /><portlet:param name="userId" value='<%= (selUser == null) ? "0" : String.valueOf(selUser.getUserId()) %>' /></portlet:renderURL>');
	}

	function <portlet:namespace />openOrganizationRoleSelector() {
		<portlet:namespace />openRoleSelector('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/enterprise_admin/select_organization_role" /><portlet:param name="step" value="1" /><portlet:param name="organizationIds" value="<%= StringUtil.merge(organizationIds) %>" /></portlet:renderURL>');
	}

	function <portlet:namespace />openRegularRoleSelector() {
		<portlet:namespace />openRoleSelector('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/enterprise_admin/select_regular_role" /></portlet:renderURL>');
	}

	function <portlet:namespace />openRoleSelector(url) {
		var roleWindow = window.open(url, 'role', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');

		roleWindow.focus();
	}

	Liferay.provide(
		window,
		'<portlet:namespace />selectRole',
		function(roleId, name, searchContainer, groupName, groupId) {
			var A = AUI();

			var searchContainerName = '<portlet:namespace />' + searchContainer + 'SearchContainer';

			searchContainer = Liferay.SearchContainer.get(searchContainerName);

			var rowColumns = [];

			rowColumns.push(name);

			if (groupName) {
				rowColumns.push(groupName);
			}

			if (groupId) {
				rowColumns.push('<a class="modify-link" data-groupId="' + groupId + '" data-rowId="' + roleId + '" href="javascript:;"><%= UnicodeFormatter.toString(removeRoleIcon) %></a>');

				<portlet:namespace />groupRolesRoleIds.push(roleId);
				<portlet:namespace />groupRolesGroupIds.push(groupId);

				document.<portlet:namespace />fm.<portlet:namespace />groupRolesRoleIds.value = <portlet:namespace />groupRolesRoleIds.join(',');
				document.<portlet:namespace />fm.<portlet:namespace />groupRolesGroupIds.value = <portlet:namespace />groupRolesGroupIds.join(',');
			}
			else {
				rowColumns.push('<a class="modify-link" data-rowId="' + roleId + '" href="javascript:;"><%= UnicodeFormatter.toString(removeRoleIcon) %></a>');
			}

			searchContainer.addRow(rowColumns, roleId);
			searchContainer.updateDataStore();

			<portlet:namespace />trackChanges();
		},
		['liferay-search-container']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />trackChanges',
		function() {
			var A = AUI();

			A.fire(
				'enterpriseAdmin:trackChanges',
				A.one('.selected .modify-link')
			);
		},
		['aui-base']
	);
</aui:script>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />rolesSearchContainer');

	searchContainer.get('contentBox').delegate(
		'click',
		function(event) {
			var link = event.currentTarget;
			var tr = link.ancestor('tr');

			searchContainer.deleteRow(tr, link.getAttribute('data-rowId'));

			<portlet:namespace />trackChanges();
		},
		'.modify-link'
	);
</aui:script>