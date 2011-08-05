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
long ldapServerId = ParamUtil.getLong(request, "ldapServerId", 0);

String postfix = LDAPSettingsUtil.getPropertyPostfix(ldapServerId);

String baseProviderURL = ParamUtil.getString(request, "baseProviderURL");
String baseDN = ParamUtil.getString(request, "baseDN");
String principal = ParamUtil.getString(request, "principal");

String credentials = ParamUtil.getString(request, "credentials");

if (credentials.equals(Portal.TEMP_OBFUSCATION_VALUE)) {
	credentials = PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_SECURITY_CREDENTIALS + postfix);
}

LdapContext ldapContext = PortalLDAPUtil.getContext(themeDisplay.getCompanyId(), baseProviderURL, principal, credentials);

if (ldapContext == null) {
%>

	<liferay-ui:message key="liferay-has-failed-to-connect-to-the-ldap-server" />

<%
	return;
}

if (Validator.isNull(ParamUtil.getString(request, "userMappingScreenName")) ||
	Validator.isNull(ParamUtil.getString(request, "userMappingPassword")) ||
	Validator.isNull(ParamUtil.getString(request, "userMappingEmailAddress")) ||
	Validator.isNull(ParamUtil.getString(request, "userMappingFirstName")) ||
	Validator.isNull(ParamUtil.getString(request, "userMappingLastName"))) {
%>

	<liferay-ui:message key="please-map-each-of-the-user-properties-screen-name,-password,-email-address,-first-name,-and-last-name-to-an-ldap-attribute" />

<%
	return;
}

String userFilter = ParamUtil.getString(request, "importUserSearchFilter");

List<SearchResult> searchResults = new ArrayList<SearchResult>();

PortalLDAPUtil.getUsers(themeDisplay.getCompanyId(), ldapContext, new byte[0], 20, baseDN, userFilter, searchResults);

String userMappingsParams =
	"screenName=" + ParamUtil.getString(request, "userMappingScreenName") +
	"\npassword=" + ParamUtil.getString(request, "userMappingPassword") +
	"\nemailAddress=" + ParamUtil.getString(request, "userMappingEmailAddress") +
	"\nfullName=" + ParamUtil.getString(request, "userMappingFullName") +
	"\nfirstName=" + ParamUtil.getString(request, "userMappingFirstName") +
	"\nlastName=" + ParamUtil.getString(request, "userMappingLastName") +
	"\njobTitle=" + ParamUtil.getString(request, "userMappingJobTitle") +
	"\ngroup=" + ParamUtil.getString(request, "userMappingGroup");

Properties userMappings = PropertiesUtil.load(userMappingsParams);
%>

<liferay-ui:message key="test-ldap-users" />

<br /><br />

<liferay-ui:message key="a-subset-of-users-has-been-displayed-for-you-to-review" />

<br /><br />

<table class="lfr-table">

<%
boolean showMissingAttributeMessage = false;

int counter = 0;

for (SearchResult searchResult : searchResults) {
	Attributes attributes = searchResult.getAttributes();

	String screenName = LDAPUtil.getAttributeValue(attributes, userMappings.getProperty("screenName")).toLowerCase();
	String password = LDAPUtil.getAttributeValue(attributes, userMappings.getProperty("password")).toLowerCase();
	String emailAddress = LDAPUtil.getAttributeValue(attributes, userMappings.getProperty("emailAddress"));
	String firstName = LDAPUtil.getAttributeValue(attributes, userMappings.getProperty("firstName"));
	String lastName = LDAPUtil.getAttributeValue(attributes, userMappings.getProperty("lastName"));
	String jobTitle = LDAPUtil.getAttributeValue(attributes, userMappings.getProperty("jobTitle"));
	Attribute attribute = attributes.get(userMappings.getProperty("group"));

	if (Validator.isNull(screenName) || Validator.isNull(password) || Validator.isNull(emailAddress) || Validator.isNull(firstName) || Validator.isNull(lastName)) {
		showMissingAttributeMessage = true;
	}

	if (counter == 0) {
%>

		<tr>
			<th>
				#
			</th>
			<th>
				<liferay-ui:message key="screen-name" />
			</th>
			<th>
				<liferay-ui:message key="email-address" />
			</th>
			<th>
				<liferay-ui:message key="first-name" />
			</th>
			<th>
				<liferay-ui:message key="last-name" />
			</th>
			<th>
				<liferay-ui:message key="password" />
			</th>
			<th>
				<liferay-ui:message key="job-title" />
			</th>
			<th>
				<liferay-ui:message key="group" />
			</th>
		</tr>

<%
	}

	counter++;
%>

	<tr>
		<td>
			<%= counter %>
		</td>
		<td>
			<%= screenName %>
		</td>
		<td>
			<%= emailAddress %>
		</td>
		<td>
			<%= firstName %>
		</td>
		<td>
			<%= lastName %>
		</td>
		<td>
			<c:if test="<%= Validator.isNotNull(password) %>">
				********
			</c:if>
		</td>
		<td>
			<%= jobTitle %>
		</td>
		<td>
			<%= (attribute == null) ? "0" : String.valueOf(attribute.size()) %>
		</td>
	</tr>

<%
}

if (counter == 0) {
%>

	<tr>
		<td colspan="7">
			<liferay-ui:message key="no-users-were-found" />
		</td>
	</tr>

<%
}
%>

</table>

<%
if (showMissingAttributeMessage) {
%>

	<div class="portlet-msg-info">
		<liferay-ui:message key="the-above-results-include-users-which-are-missing-the-required-attributes-(screen-name,-password,-email-address,-first-name,-and-last-name).-these-users-will-not-be-imported-until-these-attributes-are-filled-in" />
	</div>

<%
}

if (ldapContext != null) {
	ldapContext.close();
}
%>