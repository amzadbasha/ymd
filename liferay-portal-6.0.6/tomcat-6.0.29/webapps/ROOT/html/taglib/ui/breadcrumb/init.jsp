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

<%
Layout selLayout = (Layout)request.getAttribute("liferay-ui:breadcrumb:selLayout");

if (selLayout == null) {
	selLayout = layout;
}

String selLayoutParam = (String)request.getAttribute("liferay-ui:breadcrumb:selLayoutParam");
PortletURL portletURL = (PortletURL)request.getAttribute("liferay-ui:breadcrumb:portletURL");

int displayStyle = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:breadcrumb:displayStyle"));

if (displayStyle == 0) {
	displayStyle = 1;
}

boolean showGuestGroup = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:breadcrumb:showGuestGroup"));
boolean showParentGroups = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:breadcrumb:showParentGroups"));
boolean showLayout = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:breadcrumb:showLayout"));
boolean showPortletBreadcrumb = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:breadcrumb:showPortletBreadcrumb"));
%>

<%!
private void _buildGuestGroupBreadcrumb(ThemeDisplay themeDisplay, StringBundler sb) throws Exception {
	Group group = GroupLocalServiceUtil.getGroup(themeDisplay.getCompanyId(), GroupConstants.GUEST);

	if (group.getPublicLayoutsPageCount() > 0) {
		LayoutSet layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(group.getGroupId(), false);

		String layoutSetFriendlyURL = PortalUtil.getLayoutSetFriendlyURL(layoutSet, themeDisplay);

		sb.append("<li><span><a href=\"");
		sb.append(layoutSetFriendlyURL);
		sb.append("\">");
		sb.append(HtmlUtil.escape(themeDisplay.getAccount().getName()));
		sb.append("</a></span></li>");
	}
}

private void _buildParentGroupsBreadcrumb(LayoutSet layoutSet, PortletURL portletURL, ThemeDisplay themeDisplay, StringBundler sb) throws Exception {
	Group group = layoutSet.getGroup();

	if (group.isOrganization()) {
		Organization organization = OrganizationLocalServiceUtil.getOrganization(group.getOrganizationId());

		Organization parentOrganization = organization.getParentOrganization();

		if (parentOrganization != null) {
			Group parentGroup = parentOrganization.getGroup();

			LayoutSet parentLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(parentGroup.getGroupId(), layoutSet.isPrivateLayout());

			_buildParentGroupsBreadcrumb(parentLayoutSet, portletURL, themeDisplay, sb);
		}
	}
	else if (group.isUser()) {
		User groupUser = UserLocalServiceUtil.getUser(group.getClassPK());

		List<Organization> organizations = OrganizationLocalServiceUtil.getUserOrganizations(groupUser.getUserId(), true);

		if (!organizations.isEmpty()) {
			Organization organization = organizations.get(0);

			Group parentGroup = organization.getGroup();

			LayoutSet parentLayoutSet = LayoutSetLocalServiceUtil.getLayoutSet(parentGroup.getGroupId(), layoutSet.isPrivateLayout());

			_buildParentGroupsBreadcrumb(parentLayoutSet, portletURL, themeDisplay, sb);
		}
	}

	int layoutsPageCount = 0;

	if (layoutSet.isPrivateLayout()) {
		layoutsPageCount = group.getPrivateLayoutsPageCount();
	}
	else {
		layoutsPageCount = group.getPublicLayoutsPageCount();
	}

	if ((layoutsPageCount > 0) && !group.getName().equals(GroupConstants.GUEST)) {
		String layoutSetFriendlyURL = PortalUtil.getLayoutSetFriendlyURL(layoutSet, themeDisplay);

		sb.append("<li><span><a href=\"");
		sb.append(layoutSetFriendlyURL);
		sb.append("\">");
		sb.append(HtmlUtil.escape(group.getDescriptiveName()));
		sb.append("</a></span></li>");
	}
}

private void _buildPortletBreadcrumb(HttpServletRequest request, StringBundler sb) throws Exception {
	List<KeyValuePair> portletBreadcrumbList = PortalUtil.getPortletBreadcrumbList(request);

	if (portletBreadcrumbList == null) {
		return;
	}

	for (KeyValuePair kvp : portletBreadcrumbList) {
		String breadcrumbText = kvp.getKey();
		String breadcrumbURL = kvp.getValue();

		sb.append("<li><span>");

		if (Validator.isNotNull(breadcrumbURL)) {
			sb.append("<a href=\"");
			sb.append(HtmlUtil.escape(breadcrumbURL));
			sb.append("\">");
		}

		sb.append(HtmlUtil.escape(breadcrumbText));

		if (Validator.isNotNull(breadcrumbURL)) {
			sb.append("</a>");
		}

		sb.append("</span></li>");
	}
}
%>