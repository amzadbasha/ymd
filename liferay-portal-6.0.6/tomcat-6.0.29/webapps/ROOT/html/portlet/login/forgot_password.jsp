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

<%@ include file="/html/portlet/login/init.jsp" %>

<%
User user2 = (User)request.getAttribute(ForgotPasswordAction.class.getName());

if (Validator.isNull(authType)) {
	authType = company.getAuthType();
}
%>

<portlet:actionURL var="forgotPasswordURL">
	<portlet:param name="saveLastPath" value="0" />
	<portlet:param name="struts_action" value="/login/forgot_password" />
</portlet:actionURL>

<aui:form action="<%= forgotPasswordURL %>" method="post" name="fm">
	<portlet:renderURL var="redirectURL" />

	<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />

	<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="text-verification-failed" />

	<liferay-ui:error exception="<%= NoSuchUserException.class %>" message='<%= "the-" + TextFormatter.format(authType, TextFormatter.K) + "-you-requested-is-not-registered-in-our-database" %>' />
	<liferay-ui:error exception="<%= RequiredReminderQueryException.class %>" message="you-have-not-configured-a-reminder-query" />
	<liferay-ui:error exception="<%= SendPasswordException.class %>" message="your-password-can-only-be-sent-to-an-external-email-address" />
	<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />
	<liferay-ui:error exception="<%= UserReminderQueryException.class %>" message="your-answer-does-not-match-what-is-in-our-database" />

	<aui:fieldset>
		<c:choose>
			<c:when test="<%= user2 == null %>">

				<%
				String loginParameter = null;
				String loginLabel = null;

				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					loginParameter = "emailAddress";
					loginLabel = "email-address";
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
					loginParameter = "screenName";
					loginLabel = "screen-name";
				}
				else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
					loginParameter = "userId";
					loginLabel = "id";
				}

				String loginValue = ParamUtil.getString(request, loginParameter);
				%>

				<aui:input name="step" type="hidden" value="1" />

				<aui:input label="<%= loginLabel %>" name="<%= loginParameter %>" size="30" type="text" value="<%= loginValue %>" />

				<c:if test="<%= PropsValues.CAPTCHA_CHECK_PORTAL_SEND_PASSWORD && !PropsValues.USERS_REMINDER_QUERIES_ENABLED %>">
					<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="captchaURL">
						<portlet:param name="struts_action" value="/login/captcha" />
					</portlet:actionURL>

					<liferay-ui:captcha url="<%= captchaURL %>" />
				</c:if>

				<aui:button-row>
					<aui:button type="submit" value='<%= PropsValues.USERS_REMINDER_QUERIES_ENABLED ? "next" : "send-new-password" %>' />
				</aui:button-row>

			</c:when>
			<c:when test="<%= (user2 != null) && Validator.isNotNull(user2.getEmailAddress()) %>">
				<aui:input name="step" type="hidden" value="2" />
				<aui:input name="emailAddress" type="hidden" value="<%= user2.getEmailAddress() %>" />

				<c:if test="<%= Validator.isNotNull(user2.getReminderQueryQuestion()) && Validator.isNotNull(user2.getReminderQueryAnswer()) %>">
					<div class="portlet-msg-info">
						<%= LanguageUtil.format(pageContext, "a-new-password-will-be-sent-to-x-if-you-can-correctly-answer-the-following-question", user2.getEmailAddress()) %>
					</div>

					<aui:input label="<%= user2.getReminderQueryQuestion() %>" name="answer" type="text" />
				</c:if>

				<c:choose>
					<c:when test="<%= PropsValues.USERS_REMINDER_QUERIES_REQUIRED && !user2.hasReminderQuery() %>">
						<div class="portlet-msg-info">
							<liferay-ui:message key="the-password-cannot-be-reset-because-you-have-not-configured-a-reminder-query" />
						</div>
					</c:when>
					<c:otherwise>
						<c:if test="<%= PropsValues.CAPTCHA_CHECK_PORTAL_SEND_PASSWORD %>">
							<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="captchaURL">
								<portlet:param name="struts_action" value="/login/captcha" />
							</portlet:actionURL>

							<liferay-ui:captcha url="<%= captchaURL %>" />
						</c:if>

						<aui:button-row>
							<aui:button type="submit" value='<%= company.isSendPasswordResetLink() ? "send-password-reset-link" : "send-new-password" %>' />
						</aui:button-row>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<div class="portlet-msg-alert">
					<liferay-ui:message key="the-system-cannot-send-you-a-new-password-because-you-have-not-provided-an-email-address" />
				</div>
			</c:otherwise>
		</c:choose>
	</aui:fieldset>
</aui:form>

<liferay-util:include page="/html/portlet/login/navigation.jsp" />

<aui:script>
	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= (user2 == null) ? "emailAddress" : "answer" %>);
</aui:script>