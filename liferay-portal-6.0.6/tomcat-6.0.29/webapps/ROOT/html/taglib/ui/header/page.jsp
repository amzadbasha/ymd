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

<%@ include file="/html/taglib/ui/header/init.jsp" %>

<%
if (Validator.isNotNull(backURL) && !backURL.equals("javascript:history.go(-1);")) {
	backURL = HtmlUtil.escape(HtmlUtil.escapeHREF(PortalUtil.escapeRedirect(backURL)));
}
%>

<div class="taglib-header <%= (cssClass != null) ? cssClass : "" %>">
	<c:if test="<%= Validator.isNotNull(backURL) %>">
		<span class="header-back-to">
			<a href="<%= backURL %>" id="<%= namespace %>TabsBack"><%= Validator.isNotNull(backLabel) ? backLabel : "&laquo;" + LanguageUtil.get(pageContext, "back") %></a>
		</span>
	</c:if>

	<h1 class="header-title">
		<span>
			<c:choose>
				<c:when test="<%= escapeXml %>">
					<%= HtmlUtil.escape(LanguageUtil.get(pageContext, title)) %>
				</c:when>
				<c:otherwise>
					<%= LanguageUtil.get(pageContext, title) %>
				</c:otherwise>
			</c:choose>
		</span>
	</h1>
</div>