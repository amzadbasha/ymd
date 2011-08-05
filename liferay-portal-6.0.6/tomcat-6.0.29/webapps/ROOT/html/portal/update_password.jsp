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

<%
PasswordPolicy passwordPolicy = user.getPasswordPolicy();

String ticket = ParamUtil.getString(request, "ticket");
%>

<aui:form action='<%= themeDisplay.getPathMain() + "/portal/update_password" %>' method="post" name="fm">
	<aui:input name="p_l_id" type="hidden" value="<%= layout.getPlid() %>" />
	<aui:input name="p_auth" type="hidden" value="<%= AuthTokenUtil.getToken(request) %>" />
	<aui:input name="doAsUserId" type="hidden" value="<%= themeDisplay.getDoAsUserId() %>" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="<%= WebKeys.REFERER %>" type="hidden" value='<%= themeDisplay.getPathMain() + "?doAsUserId=" + themeDisplay.getDoAsUserId() %>' />
	<aui:input name="ticket" type="hidden" value="<%= ticket %>" />

	<div class="portlet-msg-info">
		<liferay-ui:message key="please-set-a-new-password" />
	</div>

	<c:if test="<%= SessionErrors.contains(request, UserPasswordException.class.getName()) %>">

		<%
		UserPasswordException upe = (UserPasswordException)SessionErrors.get(request, UserPasswordException.class.getName());
		%>

		<div class="portlet-msg-error">
			<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_ALREADY_USED %>">
				<liferay-ui:message key="that-password-has-already-been-used-please-enter-in-a-different-password" />
			</c:if>

			<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_CONTAINS_TRIVIAL_WORDS %>">
				<liferay-ui:message key="that-password-uses-common-words-please-enter-in-a-password-that-is-harder-to-guess-i-e-contains-a-mix-of-numbers-and-letters" />
			</c:if>

			<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_INVALID %>">
				<liferay-ui:message key="that-password-is-invalid-please-enter-in-a-different-password" />
			</c:if>

			<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_LENGTH %>">
				<%= LanguageUtil.format(pageContext, "that-password-is-too-short-or-too-long-please-make-sure-your-password-is-between-x-and-512-characters", String.valueOf(passwordPolicy.getMinLength()), false) %>
			</c:if>

			<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_NOT_CHANGEABLE %>">
				<liferay-ui:message key="your-password-cannot-be-changed" />
			</c:if>

			<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_SAME_AS_CURRENT %>">
				<liferay-ui:message key="your-new-password-cannot-be-the-same-as-your-old-password-please-enter-in-a-different-password" />
			</c:if>

			<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_TOO_TRIVIAL %>">
				<liferay-ui:message key="that-password-is-too-trivial" />
			</c:if>

			<c:if test="<%= upe.getType() == UserPasswordException.PASSWORD_TOO_YOUNG %>">
				<%= LanguageUtil.format(pageContext, "you-cannot-change-your-password-yet-please-wait-at-least-x-before-changing-your-password-again", LanguageUtil.getTimeDescription(pageContext, passwordPolicy.getMinAge() * 1000), false) %>
			</c:if>

			<c:if test="<%= upe.getType() == UserPasswordException.PASSWORDS_DO_NOT_MATCH %>">
				<liferay-ui:message key="the-passwords-you-entered-do-not-match-each-other-please-re-enter-your-password" />
			</c:if>
		</div>
	</c:if>

	<aui:fieldset label="new-password">
		<aui:input class="lfr-input-text-container" label="password" name="password1" type="password" />

		<aui:input class="lfr-input-text-container" label="enter-again" name="password2" type="password" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.Util.focusFormField(document.fm.password1);
</aui:script>