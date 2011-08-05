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

<h3><liferay-ui:message key="summary" /></h3>

<%
Role role = (Role)request.getAttribute("edit_role_permissions.jsp-role");

PortletURL permissionsSummaryURL = renderResponse.createRenderURL();

permissionsSummaryURL.setParameter("struts_action", "/enterprise_admin/edit_role_permissions");
permissionsSummaryURL.setParameter(Constants.CMD, Constants.VIEW);
permissionsSummaryURL.setParameter("tabs1", "roles");
permissionsSummaryURL.setParameter("roleId", String.valueOf(role.getRoleId()));

List<String> headerNames = new ArrayList<String>();

headerNames.add("resource-set");
headerNames.add("resource");
headerNames.add("action");

if (role.getType() == RoleConstants.TYPE_REGULAR) {
	headerNames.add("scope");
}

headerNames.add(StringPool.BLANK);

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, permissionsSummaryURL, headerNames, "this-role-does-not-have-any-permissions");

List<Permission> permissions = null;

if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) {
	permissions = new ArrayList<Permission>();

	int[] scopes = new int[0];

	if (role.getType() == RoleConstants.TYPE_REGULAR) {
		scopes = new int[] {ResourceConstants.SCOPE_COMPANY, ResourceConstants.SCOPE_GROUP};
	}
	else if ((role.getType() == RoleConstants.TYPE_COMMUNITY) || (role.getType() == RoleConstants.TYPE_ORGANIZATION)) {
		scopes = new int[] {ResourceConstants.SCOPE_GROUP_TEMPLATE};
	}

	List<ResourcePermission> resourcePermissions = ResourcePermissionLocalServiceUtil.getRoleResourcePermissions(role.getRoleId(), scopes, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

	for (ResourcePermission resourcePermission : resourcePermissions) {
		List<ResourceAction> resourceActions = ResourceActionLocalServiceUtil.getResourceActions(resourcePermission.getName());

		for (ResourceAction resourceAction : resourceActions) {
			if (ResourcePermissionLocalServiceUtil.hasActionId(resourcePermission, resourceAction)) {
				Permission permission = new PermissionImpl();

				permission.setName(resourcePermission.getName());
				permission.setScope(resourcePermission.getScope());
				permission.setPrimKey(resourcePermission.getPrimKey());
				permission.setActionId(resourceAction.getActionId());

				permissions.add(permission);
			}
		}
	}
}
else {
	permissions = PermissionLocalServiceUtil.getRolePermissions(role.getRoleId());
}

List<PermissionDisplay> permissionsDisplay = new ArrayList<PermissionDisplay>(permissions.size());

for (int i = 0; i < permissions.size(); i++) {
	Permission permission = permissions.get(i);

	Resource resource = null;

	if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) {
		resource = new ResourceImpl();

		resource.setCompanyId(themeDisplay.getCompanyId());
		resource.setName(permission.getName());
		resource.setScope(permission.getScope());
		resource.setPrimKey(permission.getPrimKey());

		if (permission.getScope() == ResourceConstants.SCOPE_INDIVIDUAL) {
			continue;
		}
	}
	else {
		resource = ResourceLocalServiceUtil.getResource(permission.getResourceId());

		ResourceCode resourceCode = ResourceCodeLocalServiceUtil.getResourceCode(resource.getCodeId());

		if (resourceCode.getScope() == ResourceConstants.SCOPE_INDIVIDUAL) {
			continue;
		}
	}

	String curPortletName = null;
	String curPortletLabel = null;
	String curModelName = null;
	String curModelLabel = null;
	String actionId = permission.getActionId();
	String actionLabel = ResourceActionsUtil.getAction(pageContext, actionId);

	if (PortletLocalServiceUtil.hasPortlet(company.getCompanyId(), resource.getName())) {
		curPortletName = resource.getName();
		curModelName = StringPool.BLANK;
		curModelLabel = StringPool.BLANK;
	}
	else {
		curModelName = resource.getName();
		curModelLabel = ResourceActionsUtil.getModelResource(pageContext, curModelName);

		List portletResources = ResourceActionsUtil.getModelPortletResources(curModelName);

		if (!portletResources.isEmpty()) {
			curPortletName = (String)portletResources.get(0);
		}
	}

	if (curPortletName == null) {
		continue;
	}

	Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), curPortletName);

	if (portlet.getPortletId().equals(PortletKeys.PORTAL)) {
		curPortletLabel = LanguageUtil.get(pageContext, "general");
	}
	else {
		curPortletLabel = PortalUtil.getPortletTitle(portlet, application, locale);
	}

	PermissionDisplay permissionDisplay = new PermissionDisplay(permission, resource, curPortletName, curPortletLabel, curModelName, curModelLabel, actionId, actionLabel);

	if (!permissionsDisplay.contains(permissionDisplay)) {
		permissionsDisplay.add(permissionDisplay);
	}
}

permissionsDisplay = ListUtil.sort(permissionsDisplay);

int total = permissionsDisplay.size();

searchContainer.setTotal(total);

List results = ListUtil.subList(permissionsDisplay, searchContainer.getStart(), searchContainer.getEnd());

searchContainer.setResults(results);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	PermissionDisplay permissionDisplay = (PermissionDisplay)results.get(i);

	Permission permission = permissionDisplay.getPermission();
	Resource resource = permissionDisplay.getResource();
	String curResource = resource.getName();
	String curPortletName = permissionDisplay.getPortletName();
	String curPortletLabel = permissionDisplay.getPortletLabel();
	String curModelLabel = permissionDisplay.getModelLabel();
	String actionId = permissionDisplay.getActionId();
	String actionLabel = permissionDisplay.getActionLabel();

	ResultRow row = new ResultRow(new Object[] {permission, role}, actionId, i);

	List groups = Collections.EMPTY_LIST;

	int scope;

	if (role.getType() == RoleConstants.TYPE_REGULAR) {
		LinkedHashMap groupParams = new LinkedHashMap();

		List rolePermissions = new ArrayList();

		rolePermissions.add(curResource);
		rolePermissions.add(new Integer(ResourceConstants.SCOPE_GROUP));
		rolePermissions.add(actionId);
		rolePermissions.add(new Long(role.getRoleId()));

		groupParams.put("rolePermissions", rolePermissions);

		groups = GroupLocalServiceUtil.search(company.getCompanyId(), null, null, groupParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		if (groups.isEmpty()) {
			scope = ResourceConstants.SCOPE_COMPANY;
		}
		else {
			scope = ResourceConstants.SCOPE_GROUP;
		}
	}
	else {
		scope = ResourceConstants.SCOPE_GROUP_TEMPLATE;
	}

	boolean selected = false;

	if (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) {
		selected = ResourcePermissionLocalServiceUtil.hasScopeResourcePermission(company.getCompanyId(), curResource, scope, role.getRoleId(), actionId);
	}
	else {
		selected = PermissionLocalServiceUtil.hasRolePermission(role.getRoleId(), company.getCompanyId(), curResource, scope, actionId);
	}

	if (!selected) {
		continue;
	}

	PortletURL editPermissionsURL = renderResponse.createRenderURL();

	editPermissionsURL.setParameter("struts_action", "/enterprise_admin/edit_role_permissions");
	editPermissionsURL.setParameter(Constants.CMD, Constants.EDIT);
	editPermissionsURL.setParameter("tabs1", "roles");
	editPermissionsURL.setParameter("roleId", String.valueOf(role.getRoleId()));
	editPermissionsURL.setParameter("redirect", permissionsSummaryURL.toString());
	editPermissionsURL.setParameter("portletResource", curPortletName);

	if (curPortletName.equals(PortletKeys.PORTAL) || curPortletName.equals(curResource)) {
		editPermissionsURL.setParameter("showModelResources", "0");
	}
	else {
		editPermissionsURL.setParameter("showModelResources", "1");
	}

	row.addText(curPortletLabel, editPermissionsURL);
	row.addText(curModelLabel);
	row.addText(actionLabel);

	if (scope == ResourceConstants.SCOPE_COMPANY) {
		row.addText(LanguageUtil.get(pageContext, "portal"));
	}
	else if (scope == ResourceConstants.SCOPE_GROUP_TEMPLATE) {
	}
	else if (scope == ResourceConstants.SCOPE_GROUP) {
		StringBundler sb = new StringBundler(groups.size() * 3 - 2);

		for (int j = 0; j < groups.size(); j++) {
			Group group = (Group)groups.get(j);

			sb.append(group.getDescriptiveName());

			if (j < groups.size() - 1) {
				sb.append(StringPool.COMMA);
				sb.append(StringPool.SPACE);
			}
		}

		row.addText(sb.toString());
	}

	// Action

	row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/enterprise_admin/permission_action.jsp");

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />