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

<%@ include file="/html/taglib/init.jsp" %>

<%@ page import="com.liferay.portlet.journal.model.JournalArticleDisplay" %>
<%@ page import="com.liferay.portlet.journal.model.JournalArticleResource" %>
<%@ page import="com.liferay.portlet.journal.service.JournalArticleResourceLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.journalcontent.util.JournalContentUtil" %>

<%
long articleResourcePrimKey = GetterUtil.getLong((String)request.getAttribute("liferay-ui:journal-article:articleResourcePrimKey"));
long groupId = GetterUtil.getLong((String)request.getAttribute("liferay-ui:journal-article:groupId"));
String articleId = GetterUtil.getString((String)request.getAttribute("liferay-ui:journal-article:articleId"));
String templateId = GetterUtil.getString((String)request.getAttribute("liferay-ui:journal-article:templateId"));
String languageId = GetterUtil.getString((String)request.getAttribute("liferay-ui:journal-article:languageId"), LanguageUtil.getLanguageId(request));
int articlePage = GetterUtil.getInteger((String)request.getAttribute("liferay-ui:journal-article:articlePage"));

String xmlRequest = GetterUtil.getString((String)request.getAttribute("liferay-ui:journal-article:xmlRequest"));

if (Validator.isNull(xmlRequest) && (portletRequest != null) && (portletResponse != null)) {
	xmlRequest = PortletRequestUtil.toXML(portletRequest, portletResponse);
}

boolean showTitle = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:journal-article:showTitle"));
boolean showAvailableLocales = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:journal-article:showAvailableLocales"));

if (articleResourcePrimKey > 0) {
	JournalArticleResource articleResource = JournalArticleResourceLocalServiceUtil.getArticleResource(articleResourcePrimKey);

	groupId = articleResource.getGroupId();
	articleId = articleResource.getArticleId();
}

JournalArticleDisplay articleDisplay = JournalContentUtil.getDisplay(groupId, articleId, templateId, null, languageId, themeDisplay, articlePage, xmlRequest);
%>

<c:if test="<%= articleDisplay != null %>">
	<c:if test="<%= showTitle %>">
		<h3 class="journal-content-title"><%= articleDisplay.getTitle() %></h3>
	</c:if>

	<c:if test="<%= showAvailableLocales %>">

		<%
		String[] availableLocales = articleDisplay.getAvailableLocales();
		%>

		<c:if test="<%= availableLocales.length > 1 %>">
			<div>
				<br />

				<liferay-ui:language languageIds="<%= availableLocales %>" displayStyle="<%= 0 %>" />
			</div>
		</c:if>
	</c:if>

	<%= articleDisplay.getContent() %>
</c:if>