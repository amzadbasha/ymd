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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

PasswordPolicy passwordPolicy = (PasswordPolicy)request.getAttribute(WebKeys.PASSWORD_POLICY);

long passwordPolicyId = BeanParamUtil.getLong(passwordPolicy, request, "passwordPolicyId");

boolean defaultPolicy = BeanParamUtil.getBoolean(passwordPolicy, request, "defaultPolicy");
long minAge = BeanParamUtil.getLong(passwordPolicy, request, "minAge");
int historyCount = BeanParamUtil.getInteger(passwordPolicy, request, "historyCount");
long maxAge = BeanParamUtil.getLong(passwordPolicy, request, "maxAge");
long warningTime = BeanParamUtil.getLong(passwordPolicy, request, "warningTime");
long resetFailureCount = BeanParamUtil.getLong(passwordPolicy, request, "resetFailureCount");
boolean requireUnlock = BeanParamUtil.getBoolean(passwordPolicy, request, "requireUnlock");
long lockoutDuration = BeanParamUtil.getLong(passwordPolicy, request, "lockoutDuration");
long resetTicketMaxAge = BeanParamUtil.getLong(passwordPolicy, request, "resetTicketMaxAge");
%>

<liferay-util:include page="/html/portlet/enterprise_admin/password_policy/toolbar.jsp">
	<liferay-util:param name="toolbarItem" value='<%= (passwordPolicy == null) ? "add" : "view-all" %>' />
</liferay-util:include>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (passwordPolicy == null) ? "new-password-policy" : passwordPolicy.getName() %>'
/>

<portlet:actionURL var="editPasswordPolicyURL">
	<portlet:param name="struts_action" value="/enterprise_admin/edit_password_policy" />
</portlet:actionURL>

<aui:form action="<%= editPasswordPolicyURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (passwordPolicy == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="passwordPolicyId" type="hidden" value="<%= passwordPolicyId %>" />

	<liferay-ui:error exception="<%= DuplicatePasswordPolicyException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= PasswordPolicyNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= passwordPolicy %>" model="<%= PasswordPolicy.class %>" />

	<liferay-ui:panel-container extended="<%= true %>" id="passwordPolicyPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="passwordPolicyGeneralPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "general") %>'>
			<aui:fieldset>
				<aui:input disabled="<%= defaultPolicy %>" name="name" />

				<aui:input name="description" />

				<aui:input helpMessage="changeable-help" inlineLabel="left" name="changeable" />

				<div id="<portlet:namespace />changeableSettings">
					<aui:input helpMessage="change-required-help" inlineLabel="left" name="changeRequired" />

					<aui:select helpMessage="minimum-age-help" label="minimum-age" name="minAge">
						<aui:option label="none" selected="<%= (minAge == 0) %>" value="0" />

						<%
						for (int i = 0; i < 15; i++) {
						%>

							<aui:option label="<%= LanguageUtil.getTimeDescription(pageContext, _DURATIONS[i] * 1000) %>" selected="<%= (minAge == _DURATIONS[i]) %>" value="<%= _DURATIONS[i] %>" />

						<%
						}
						%>

					</aui:select>
				</div>

				<aui:select helpMessage="reset-ticket-max-age-help" label="reset-ticket-max-age" name="resetTicketMaxAge">
					<aui:option label="eternal" selected="<%= (resetTicketMaxAge == 0) %>" value="0" />

					<%
					for (int i = 0; i < 15; i++) {
					%>

						<aui:option label="<%= LanguageUtil.getTimeDescription(pageContext, _DURATIONS[i] * 1000) %>" selected="<%= (resetTicketMaxAge == _DURATIONS[i]) %>" value="<%= _DURATIONS[i] %>" />

					<%
					}
					%>

				</aui:select>
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="passwordPolicySyntaxPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "password-syntax-checking") %>'>
			<aui:fieldset>

				<aui:input helpMessage="syntax-checking-enabled-help" inlineLabel="left" label="syntax-checking-enabled" name="checkSyntax" />

				<div id="<portlet:namespace />syntaxSettings">
					<aui:input helpMessage="allow-dictionary-words-help" inlineLabel="left" name="allowDictionaryWords" />

					<aui:input helpMessage="minimum-alpha-numeric-help" label="minimum-alpha-numeric" name="minAlphanumeric" />

					<aui:input helpMessage="minimum-length-help" label="minimum-length" name="minLength" />

					<aui:input helpMessage="minimum-lower-case-help" label="minimum-lower-case" name="minLowerCase" />

					<aui:input helpMessage="minimum-numbers-help" label="minimum-numbers" name="minNumbers" />

					<aui:input helpMessage="minimum-symbols-help" label="minimum-symbols" name="minSymbols" />

					<aui:input helpMessage="minimum-upper-case-help" label="minimum-upper-case" name="minUpperCase" />
				</div>
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="passwordPolicyHistoryPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "password-history") %>'>
			<aui:fieldset>

				<aui:input helpMessage="history-enabled-help" inlineLabel="left" label="history-enabled" name="history" />

				<div id="<portlet:namespace />historySettings">
					<aui:select helpMessage="history-count-help" name="historyCount">

						<%
						for (int i = 2; i < 25; i++) {
						%>

							<aui:option label="<%= i %>" selected="<%= (historyCount == i) %>" />

						<%
						}
						%>

					</aui:select>
				</div>
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="passwordPolicyExpirationPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "password-expiration") %>'>
			<aui:fieldset>

				<aui:input helpMessage="expiration-enabled-help" inlineLabel="left" label="expiration-enabled" name="expireable" />

				<div id="<portlet:namespace />expirationSettings">
					<aui:select helpMessage="maximum-age-help" label="maximum-age" name="maxAge">

						<%
						for (int i = 15; i < _DURATIONS.length; i++) {
						%>

							<aui:option label="<%= LanguageUtil.getTimeDescription(pageContext, _DURATIONS[i] * 1000) %>" selected="<%= (maxAge == _DURATIONS[i]) %>" value="<%= _DURATIONS[i] %>" />

						<%
						}
						%>

					</aui:select>

					<aui:select helpMessage="warning-time-help" name="warningTime">

						<%
						for (int i = 7; i < 16; i++) {
						%>

							<aui:option label="<%= LanguageUtil.getTimeDescription(pageContext, _DURATIONS[i] * 1000) %>" value="<%= _DURATIONS[i] %>" selected="<%= (warningTime == _DURATIONS[i]) %>" />

						<%
						}
						%>

					</aui:select>

					<aui:input helpMessage="grace-limit-help" name="graceLimit" />
				</div>
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="passwordPolicyLockoutPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "lockout") %>'>
			<aui:fieldset>
				<aui:input helpMessage="lockout-enabled-help" inlineLabel="left" label="lockout-enabled" name="lockout" />

				<div id="<portlet:namespace />lockoutSettings">
					<aui:input helpMessage="maximum-failure-help" label="maximum-failure" name="maxFailure" />

					<aui:select helpMessage="reset-failure-count-help" name="resetFailureCount">

						<%
						for (int i = 0; i < 15; i++) {
						%>

							<aui:option label="<%= LanguageUtil.getTimeDescription(pageContext, _DURATIONS[i] * 1000) %>" selected="<%= (resetFailureCount == _DURATIONS[i]) %>" value="<%= _DURATIONS[i] %>" />

						<%
						}
						%>

					</aui:select>

					<aui:select helpMessage="lockout-duration-help" name="lockoutDuration">
						<aui:option label="until-unlocked-by-an-administrator" selected="<%= (requireUnlock) %>" value="0" />

						<%
						for (int i = 0; i < 15; i++) {
						%>

							<aui:option label="<%= LanguageUtil.getTimeDescription(pageContext, _DURATIONS[i] * 1000) %>" selected="<%= (!requireUnlock && (lockoutDuration == _DURATIONS[i])) %>" value="<%= _DURATIONS[i] %>" />

						<%
						}
						%>

					</aui:select>
				</div>
			</aui:fieldset>
		</liferay-ui:panel>
	</liferay-ui:panel-container>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= defaultPolicy ? "description" : "name" %>);
	</c:if>

	Liferay.Util.toggleBoxes('<portlet:namespace />changeableCheckbox', '<portlet:namespace />changeableSettings');
	Liferay.Util.toggleBoxes('<portlet:namespace />checkSyntaxCheckbox', '<portlet:namespace />syntaxSettings');
	Liferay.Util.toggleBoxes('<portlet:namespace />historyCheckbox', '<portlet:namespace />historySettings');
	Liferay.Util.toggleBoxes('<portlet:namespace />expireableCheckbox', '<portlet:namespace />expirationSettings');
	Liferay.Util.toggleBoxes('<portlet:namespace />lockoutCheckbox', '<portlet:namespace />lockoutSettings');
</aui:script>

<%
if (passwordPolicy != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, passwordPolicy.getName(), null);
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-user"), currentURL);
}
%>

<%!
private static final long[] _DURATIONS = {300, 600, 1800, 3600, 7200, 10800, 21600, 43200, 86400, 172800, 259200, 345600, 432000, 518400, 604800, 1209600, 1814400, 2419200, 4838400, 7862400, 15724800, 31449600};
%>