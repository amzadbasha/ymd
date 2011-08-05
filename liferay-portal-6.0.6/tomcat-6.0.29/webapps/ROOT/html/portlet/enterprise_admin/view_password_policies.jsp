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
PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");

boolean passwordPolicyEnabled = LDAPSettingsUtil.isPasswordPolicyEnabled(company.getCompanyId());
%>

<c:if test="<%= passwordPolicyEnabled %>">
	<div class="portlet-msg-info">
		<liferay-ui:message key="you-are-using-ldaps-password-policy" />
	</div>
</c:if>

<liferay-util:include page="/html/portlet/enterprise_admin/password_policy/toolbar.jsp">
	<liferay-util:param name="toolbarItem" value="view-all" />
</liferay-util:include>

<%
PasswordPolicySearch searchContainer = new PasswordPolicySearch(renderRequest, portletURL);

List headerNames = searchContainer.getHeaderNames();

headerNames.add(StringPool.BLANK);
%>

<c:if test="<%= !passwordPolicyEnabled %>">
	<liferay-ui:search-form
		page="/html/portlet/enterprise_admin/password_policy_search.jsp"
		searchContainer="<%= searchContainer %>"
	/>
</c:if>

<c:if test="<%= !passwordPolicyEnabled && windowState.equals(WindowState.MAXIMIZED) %>">

	<%
	PasswordPolicySearchTerms searchTerms = (PasswordPolicySearchTerms)searchContainer.getSearchTerms();

	int total = PasswordPolicyLocalServiceUtil.searchCount(company.getCompanyId(), searchTerms.getName());

	searchContainer.setTotal(total);

	List results = PasswordPolicyLocalServiceUtil.search(company.getCompanyId(), searchTerms.getName(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

	searchContainer.setResults(results);

	PortletURL passwordPoliciesRedirectURL = PortletURLUtil.clone(portletURL, renderResponse);

	passwordPoliciesRedirectURL.setParameter(searchContainer.getCurParam(), String.valueOf(searchContainer.getCur()));
	%>

	<aui:input name="passwordPoliciesRedirect" type="hidden" value="<%= passwordPoliciesRedirectURL.toString() %>" />

	<div class="separator"><!-- --></div>

	<%
	List resultRows = searchContainer.getResultRows();

	for (int i = 0; i < results.size(); i++) {
		PasswordPolicy passwordPolicy = (PasswordPolicy)results.get(i);

		ResultRow row = new ResultRow(passwordPolicy, passwordPolicy.getPasswordPolicyId(), i);

		PortletURL rowURL = renderResponse.createRenderURL();

		rowURL.setParameter("struts_action", "/enterprise_admin/edit_password_policy");
		rowURL.setParameter("redirect", searchContainer.getIteratorURL().toString());
		rowURL.setParameter("passwordPolicyId", String.valueOf(passwordPolicy.getPasswordPolicyId()));

		// Name

		row.addText(HtmlUtil.escape(passwordPolicy.getName()), rowURL);

		// Description

		row.addText(HtmlUtil.escape(passwordPolicy.getDescription()), rowURL);

		// Action

		row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/enterprise_admin/password_policy_action.jsp");

		// Add result row

		resultRows.add(row);
	}
	%>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
</c:if>