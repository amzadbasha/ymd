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

<%@ include file="/html/portal/init.jsp" %>

<liferay-portlet:runtime portletName="<%= PortletKeys.TAGS_COMPILER %>" />

<%
boolean layoutMaximized = layoutTypePortlet.hasStateMax();

if (!layoutMaximized) {
	String themeId = theme.getThemeId();

	String layoutTemplateId = layoutTypePortlet.getLayoutTemplateId();

	if (Validator.isNull(layoutTemplateId)) {
		layoutTemplateId = PropsValues.DEFAULT_LAYOUT_TEMPLATE_ID;
	}

	LayoutTemplate layoutTemplate = LayoutTemplateLocalServiceUtil.getLayoutTemplate(layoutTemplateId, false, theme.getThemeId());

	if (layoutTemplate != null) {
		themeId = layoutTemplate.getThemeId();
	}

	String velocityTemplateId = themeId + LayoutTemplateConstants.CUSTOM_SEPARATOR + layoutTypePortlet.getLayoutTemplateId();
	String velocityTemplateContent = LayoutTemplateLocalServiceUtil.getWapContent(layoutTypePortlet.getLayoutTemplateId(), false, theme.getThemeId());

	RuntimePortletUtil.processTemplate(application, request, response, pageContext, out, velocityTemplateId, velocityTemplateContent);
}
else {
	String velocityTemplateId = null;
	String velocityTemplateContent = null;

	if (themeDisplay.isStateExclusive()) {
		velocityTemplateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "exclusive";
		velocityTemplateContent = LayoutTemplateLocalServiceUtil.getWapContent("exclusive", true, theme.getThemeId());
	}
	else {
		velocityTemplateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "max";
		velocityTemplateContent = LayoutTemplateLocalServiceUtil.getWapContent("max", true, theme.getThemeId());
	}

	RuntimePortletUtil.processTemplate(application, request, response, pageContext, out, StringUtil.split(layoutTypePortlet.getStateMax())[0], velocityTemplateId, velocityTemplateContent);
}
%>