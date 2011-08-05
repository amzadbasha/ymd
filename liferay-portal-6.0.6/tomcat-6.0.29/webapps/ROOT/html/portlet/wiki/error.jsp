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

<%@ include file="/html/portlet/wiki/init.jsp" %>

<liferay-ui:header
	backURL="javascript:history.go(-1);"
	title="error"
/>

<liferay-ui:error exception="<%= DuplicateFileException.class %>" message="please-enter-a-file-with-a-valid-file-name" />
<liferay-ui:error exception="<%= NoSuchNodeException.class %>" message="please-enter-a-valid-page-title" />

<c:if test="<%= SessionErrors.contains(renderRequest, NoSuchPageException.class.getName()) %>">

	<%
	String nodeId = ParamUtil.getString(request, "nodeId");
	String title = ParamUtil.getString(request, "title");

	WikiNode node = null;

	if (Validator.isNull(nodeId) || nodeId.equals("0")) {
		node = (WikiNode)request.getAttribute(WebKeys.WIKI_NODE);

		if (node != null) {
			nodeId = String.valueOf(node.getNodeId());
		}
	}

	boolean hasDraftPage = false;

	if (node != null) {
		hasDraftPage = WikiPageLocalServiceUtil.hasDraftPage(node.getNodeId(), title);
	}

	PortletURL searchURL = renderResponse.createRenderURL();

	searchURL.setParameter("struts_action", "/wiki/search");
	searchURL.setParameter("redirect", currentURL);
	searchURL.setParameter("nodeId", nodeId);
	searchURL.setParameter("keywords", title);

	PortletURL editPageURL = renderResponse.createRenderURL();

	editPageURL.setParameter("struts_action", "/wiki/edit_page");
	editPageURL.setParameter("redirect", currentURL);
	editPageURL.setParameter("nodeId", nodeId);
	editPageURL.setParameter("title", title);
	%>

	<c:choose>
		<c:when test="<%= hasDraftPage %>">

			<%
			WikiPage draftPage = WikiPageLocalServiceUtil.getDraftPage(node.getNodeId(), title);

			boolean editableDraft = true;

			if (permissionChecker.isCompanyAdmin() || permissionChecker.isCommunityAdmin(scopeGroupId) || (draftPage.getUserId() == user.getUserId())) {
				editableDraft = true;
			}
			%>

			<c:choose>
				<c:when test="<%= editableDraft %>">
					<div class="portlet-msg-info">
						<liferay-ui:message key="this-page-has-an-associated-draft-that-is-not-yet-published" />
					</div>

					<div>
						<input onclick="location.href = '<%= editPageURL.toString() %>'" type="button" value="<%= LanguageUtil.get(pageContext, "edit-draft") %>" />
					</div>
				</c:when>
				<c:otherwise>
					<div class="portlet-msg-info">
						<liferay-ui:message key="this-page-has-already-been-started-by-another-author" />
					</div>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<div class="portlet-msg-info">
				<liferay-ui:message key="this-page-is-empty.-use-the-buttons-below-to-create-it-or-to-search-for-the-words-in-the-title" />
			</div>

			<div>
				<input onclick="location.href = '<%= searchURL.toString() %>'" type="button" value="<%= LanguageUtil.format(pageContext, "search-for-x", title) %>" />

				<input onclick="location.href = '<%= editPageURL.toString() %>'" type="button" value="<%= LanguageUtil.format(pageContext, "create-page-x", title) %>" />
			</div>
		</c:otherwise>
	</c:choose>
</c:if>

<liferay-ui:error exception="<%= PageTitleException.class %>" message="please-enter-a-valid-page-title" />
<liferay-ui:error exception="<%= PrincipalException.class %>" message="you-do-not-have-the-required-permissions" />