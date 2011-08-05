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

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
Contact selContact = (Contact)request.getAttribute("user.selContact");

String facebook = selContact.getFacebookSn();
String mySpace = selContact.getMySpaceSn();
String twitter = selContact.getTwitterSn();
%>

<c:if test="<%= Validator.isNotNull(facebook) || Validator.isNotNull(mySpace) || Validator.isNotNull(twitter) %>">
	<h3><liferay-ui:message key="social-network" /></h3>

	<dl class="property-list">
		<c:if test="<%= Validator.isNotNull(facebook) %>">
			<dt>
				<liferay-ui:message key="facebook" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(facebook) %>
			</dd>
		</c:if>

		<c:if test="<%= Validator.isNotNull(mySpace) %>">
			<dt>
				<liferay-ui:message key="myspace" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(mySpace) %>
			</dd>
		</c:if>

		<c:if test="<%= Validator.isNotNull(twitter) %>">
			<dt>
				<liferay-ui:message key="twitter" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(twitter) %>
			</dd>
		</c:if>
	</dl>
</c:if>