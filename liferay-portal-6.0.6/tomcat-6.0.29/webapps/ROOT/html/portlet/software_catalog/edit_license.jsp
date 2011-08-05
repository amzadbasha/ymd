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

<%@ include file="/html/portlet/software_catalog/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

SCLicense license = (SCLicense)request.getAttribute(WebKeys.SOFTWARE_CATALOG_LICENSE);

long licenseId = BeanParamUtil.getLong(license, request, "licenseId");
%>

<form action="<portlet:actionURL><portlet:param name="struts_action" value="/software_catalog/edit_license" /></portlet:actionURL>" method="post" name="<portlet:namespace />fm" onSubmit="<portlet:namespace />saveLicense(); return false;">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escapeAttribute(redirect) %>" />
<input name="<portlet:namespace />licenseId" type="hidden" value="<%= licenseId %>" />

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (license != null) ? license.getName() : "new-license" %>'
/>

<liferay-ui:error exception="<%= LicenseNameException.class %>" message="please-enter-a-valid-name" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="name" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= SCLicense.class %>" bean="<%= license %>" field="name" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="url" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= SCLicense.class %>" bean="<%= license %>" field="url" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="open-source" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= SCLicense.class %>" bean="<%= license %>" field="openSource" defaultValue="<%= Boolean.TRUE %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="active" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= SCLicense.class %>" bean="<%= license %>" field="active" defaultValue="<%= Boolean.TRUE %>" />
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="recommended" />
	</td>
	<td>
		<liferay-ui:input-field model="<%= SCLicense.class %>" bean="<%= license %>" field="recommended" defaultValue="<%= Boolean.TRUE %>" />
	</td>
</tr>
</table>

<br />

<input type="submit" value="<liferay-ui:message key="save" />" />

<input type="button" value="<liferay-ui:message key="cancel" />" onClick="location.href = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" />

</form>

<aui:script>
	function <portlet:namespace />saveLicense() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (license == null) ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</c:if>
</aui:script>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/software_catalog/view");
portletURL.setParameter("tabs1", "licenses");

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "licenses"), portletURL.toString());

if (license != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, license.getName(), null);
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-license"), currentURL);
}
%>