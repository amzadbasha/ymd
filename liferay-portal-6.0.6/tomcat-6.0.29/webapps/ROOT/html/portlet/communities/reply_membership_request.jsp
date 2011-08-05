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

<%@ include file="/html/portlet/communities/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ActionUtil.getGroup(request);

Group group = (Group)request.getAttribute(WebKeys.GROUP);

long groupId = BeanParamUtil.getLong(group, request, "groupId");

String friendlyURL = BeanParamUtil.getString(group, request, "friendlyURL");

ActionUtil.getMembershipRequest(request);

MembershipRequest membershipRequest = (MembershipRequest)request.getAttribute(WebKeys.MEMBERSHIP_REQUEST);
%>

<portlet:actionURL var="replyMembershipRequestURL">
	<portlet:param name="struts_action" value="/communities/reply_membership_request" />
</portlet:actionURL>

<aui:form action="<%= replyMembershipRequestURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
	<aui:input name="membershipRequestId" type="hidden" value="<%= membershipRequest.getMembershipRequestId() %>" />

	<liferay-ui:header
		backURL="<%= redirect %>"
		title="community"
	/>

	<liferay-ui:error exception="<%= DuplicateGroupException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= GroupNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= MembershipRequestCommentsException.class %>" message="please-enter-valid-comments" />
	<liferay-ui:error exception="<%= RequiredGroupException.class %>" message="old-group-name-is-a-required-system-group" />

	<aui:model-context bean="<%= membershipRequest %>" model="<%= MembershipRequest.class %>" />

	<aui:fieldset>
		<aui:field-wrapper label="community">
			<%= group.getDescriptiveName() %>
		</aui:field-wrapper>

		<c:if test="<%= Validator.isNotNull(group.getDescription()) %>">
			<aui:field-wrapper label="description">
				<%= group.getDescription() %>
			</aui:field-wrapper>
		</c:if>

		<aui:field-wrapper label="user-name">
			<%= HtmlUtil.escape(PortalUtil.getUserName(membershipRequest.getUserId(), StringPool.BLANK)) %>
		</aui:field-wrapper>

		<aui:field-wrapper label="user-comments">
			<%= membershipRequest.getComments() %>
		</aui:field-wrapper>

		<aui:select label="status" name="statusId">
			<aui:option label="approve" value="<%= MembershipRequestConstants.STATUS_APPROVED %>" />
			<aui:option label="deny" value="<%= MembershipRequestConstants.STATUS_DENIED %>" />
		</aui:select>

		<aui:input name="replyComments" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />statusId);
	</aui:script>
</c:if>