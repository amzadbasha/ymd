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
Contact selContact = (Contact)request.getAttribute("user.selContact");
%>

<h3><liferay-ui:message key="social-network" /></h3>

<c:choose>
	<c:when test="<%= selContact != null %>">
		<aui:model-context bean="<%= selContact %>" model="<%= Contact.class %>" />

		<aui:fieldset>
			<div class="social-network">
				<aui:input label="facebook" name="facebookSn" />

				<img alt="<liferay-ui:message key="facebook" />" src="<%= themeDisplay.getPathThemeImages() %>/enterprise_admin/facebook.jpg" />
			</div>

			<div class="social-network">
				<aui:input label="myspace" name="mySpaceSn" />

				<img alt="<liferay-ui:message key="myspace" />" src="<%= themeDisplay.getPathThemeImages() %>/enterprise_admin/myspace.jpg" />

			</div>

			<div class="social-network">
				<aui:input label="twitter" name="twitterSn" />

				<img alt="<liferay-ui:message key="twitter" />" class="social-network-logo" src="<%= themeDisplay.getPathThemeImages() %>/enterprise_admin/twitter.jpg" />
			</div>
		</aui:fieldset>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-info">
			<liferay-ui:message key="this-section-will-be-editable-after-creating-the-user" />
		</div>
	</c:otherwise>
</c:choose>