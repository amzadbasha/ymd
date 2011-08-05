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

<%@ include file="/html/portlet/document_library/init.jsp" %>

<c:choose>
	<c:when test="<%= showTabs && portletName.equals(PortletKeys.DOCUMENT_LIBRARY) %>">

		<%
		String topLink = ParamUtil.getString(request, "topLink", "documents-home");

		long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

		boolean viewFolder = GetterUtil.getBoolean((String)request.getAttribute("view.jsp-viewFolder"));

		boolean useAssetEntryQuery = GetterUtil.getBoolean((String)request.getAttribute("view.jsp-useAssetEntryQuery"));

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("categoryId", StringPool.BLANK);
		portletURL.setParameter("tag", StringPool.BLANK);
		%>

		<div class="top-links-container">
			<div class="top-links">
				<div class="top-links-navigation">

					<%
					portletURL.setParameter("topLink", "documents-home");
					%>

					<liferay-ui:icon
						cssClass="top-link"
						image="../aui/home"
						label="<%= true %>"
						message="documents-home"
						url='<%= (topLink.equals("documents-home") && folderId == 0 && viewFolder && !useAssetEntryQuery) ? StringPool.BLANK : portletURL.toString() %>'
					/>


					<%
					portletURL.setParameter("topLink", "recent-documents");
					%>

					<liferay-ui:icon
						cssClass='<%= "top-link" + (themeDisplay.isSignedIn() ? StringPool.BLANK : " last") %>'
						image="../aui/clock"
						label="<%= true %>"
						message="recent-documents"
						url='<%= (topLink.equals("recent-documents") && !useAssetEntryQuery) ? StringPool.BLANK : portletURL.toString() %>'
					/>

					<c:if test="<%= themeDisplay.isSignedIn() %>">

						<%
						portletURL.setParameter("topLink", "my-documents");
						%>

						<liferay-ui:icon
							cssClass="top-link last"
							image="../aui/person"
							label="<%= true %>"
							message="my-documents"
							url='<%= (topLink.equals("my-documents") && !useAssetEntryQuery) ? StringPool.BLANK : portletURL.toString() %>'
						/>
					</c:if>
				</div>

				<liferay-portlet:renderURL varImpl="searchURL">
					<portlet:param name="struts_action" value="/document_library/search" />
				</liferay-portlet:renderURL>

				<c:if test="<%= showFoldersSearch %>">
					<div class="folder-search">
						<aui:form action="<%= searchURL %>" method="get" name="searchFm">
							<liferay-portlet:renderURLParams varImpl="searchURL" />
							<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
							<aui:input name="breadcrumbsFolderId" type="hidden" value="<%= folderId %>" />
							<aui:input name="searchFolderIds" type="hidden" value="<%= folderId %>" />

							<span class="aui-search-bar">
								<aui:input id="keywords1" inlineField="<%= true %>" label="" name="keywords" size="30" title="search-documents" type="text" />

								<aui:button type="submit" value="search" />
							</span>
						</aui:form>
					</div>

					<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
						<aui:script>
							Liferay.Util.focusFormField(document.<portlet:namespace />searchFm.<portlet:namespace />keywords);
						</aui:script>
					</c:if>
				</c:if>
			</div>
		</div>
	</c:when>
	<c:when test="<%= showTabs && portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) %>">

		<%
		long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));
		%>

		<div class="top-links-container">
			<div class="top-links">
				<liferay-portlet:renderURL varImpl="searchURL">
					<portlet:param name="struts_action" value="/document_library/search" />
				</liferay-portlet:renderURL>

				<c:if test="<%= showFoldersSearch %>">
					<div class="folder-search">
						<aui:form action="<%= searchURL %>" method="get" name="searchFm">
							<liferay-portlet:renderURLParams varImpl="searchURL" />
							<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
							<aui:input name="breadcrumbsFolderId" type="hidden" value="<%= folderId %>" />
							<aui:input name="searchFolderIds" type="hidden" value="<%= folderId %>" />

							<span class="aui-search-bar">
								<aui:input id="keywords1" inlineField="<%= true %>" label="" name="keywords" size="30" title="search-documents" type="text" />

								<aui:button type="submit" value="search" />
							</span>
						</aui:form>
					</div>

					<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
						<aui:script>
							Liferay.Util.focusFormField(document.<portlet:namespace />searchFm.<portlet:namespace />keywords);
						</aui:script>
					</c:if>
				</c:if>
			</div>
		</div>
	</c:when>
	<c:when test="<%= showTabs && showSubfolders %>">
		<liferay-ui:header
			title="documents-home"
		/>
	</c:when>
</c:choose>