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

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DLFileEntry fileEntry = null;
DLFileShortcut fileShortcut = null;

boolean view = false;

if (row != null) {
	Object result = row.getObject();

	if (result instanceof AssetEntry) {
		AssetEntry assetEntry = (AssetEntry)result;

		if (assetEntry.getClassName().equals(DLFileEntry.class.getName())) {
			fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(assetEntry.getClassPK());
		}
		else {
			fileShortcut = DLFileShortcutLocalServiceUtil.getFileShortcut(assetEntry.getClassPK());
		}
	}
	else if (result instanceof DLFileEntry) {
		fileEntry = (DLFileEntry)result;
	}
	else {
		fileShortcut = (DLFileShortcut)result;
	}
}
else {
	if (request.getAttribute("view_file_entry.jsp-fileEntry") != null) {
		fileEntry = (DLFileEntry)request.getAttribute("view_file_entry.jsp-fileEntry");
	}
	else {
		fileShortcut = (DLFileShortcut)request.getAttribute("view_file_shortcut.jsp-fileShortcut");
	}

	view = true;
}

long folderId = 0;

if (fileEntry != null) {
	folderId = fileEntry.getFolderId();
}
else if (fileShortcut != null) {
	folderId = fileShortcut.getFolderId();
}

PortletURL viewFolderURL = renderResponse.createRenderURL();

viewFolderURL.setParameter("struts_action", "/document_library/view");
viewFolderURL.setParameter("folderId", String.valueOf(folderId));
%>

<liferay-ui:icon-menu showExpanded="<%= view %>" showWhenSingleIcon="<%= view %>">
	<c:choose>
		<c:when test="<%= fileEntry != null %>">
			<c:if test="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.VIEW) %>">
				<liferay-ui:icon
					image="download"
					message='<%= LanguageUtil.get(pageContext, "download") + " (" + TextFormatter.formatKB(fileEntry.getSize(), locale) + "k)" %>'
					url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + StringPool.SLASH + fileEntry.getFolderId() + StringPool.SLASH + HttpUtil.encodeURL(HtmlUtil.unescape(fileEntry.getTitle())) %>'
				/>
			</c:if>

			<%@ include file="/html/portlet/document_library/file_entry_action_open_document.jspf" %>

			<c:if test="<%= showActions && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
				<portlet:renderURL var="editURL">
					<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
					<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="edit"
					url="<%= editURL %>"
				/>
			</c:if>

			<c:if test="<%= showActions && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
				<portlet:renderURL var="moveURL">
					<portlet:param name="struts_action" value="/document_library/move_file_entry" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
					<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="submit"
					message="move"
					url="<%= moveURL %>"
				/>
			</c:if>

			<c:if test="<%= showActions %>">
				<%@ include file="/html/portlet/document_library/file_entry_action_lock.jspf" %>
			</c:if>

			<c:if test="<%= showActions && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL
					modelResource="<%= DLFileEntry.class.getName() %>"
					modelResourceDescription="<%= HtmlUtil.unescape(fileEntry.getTitle()) %>"
					resourcePrimKey="<%= String.valueOf(fileEntry.getFileEntryId()) %>"
					var="permissionsURL"
				/>

				<liferay-ui:icon
					image="permissions"
					url="<%= permissionsURL %>"
				/>
			</c:if>

			<c:if test="<%= !view && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) %>">
				<portlet:actionURL var="deleteURL">
					<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
					<portlet:param name="redirect" value="<%= viewFolderURL.toString() %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(fileEntry.getFolderId()) %>" />
					<portlet:param name="name" value="<%= HtmlUtil.unescape(fileEntry.getName()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete
					url="<%= deleteURL %>"
				/>
			</c:if>
		</c:when>
		<c:otherwise>

			<%
			fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileShortcut.getGroupId(), fileShortcut.getToFolderId(), HtmlUtil.unescape(fileShortcut.getToName()));
			%>

			<c:if test="<%= DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.VIEW) %>">
				<liferay-ui:icon
					image="download"
					message='<%= LanguageUtil.get(pageContext, "download") + " (" + TextFormatter.formatKB(fileEntry.getSize(), locale) + "k)" %>'
					url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/documents/" + themeDisplay.getScopeGroupId() + StringPool.SLASH + fileEntry.getFolderId() + StringPool.SLASH + HttpUtil.encodeURL(HtmlUtil.unescape(fileEntry.getTitle())) %>'
				/>
			</c:if>

			<%@ include file="/html/portlet/document_library/file_entry_action_open_document.jspf" %>

			<c:if test="<%= !view && DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.VIEW) %>">
				<portlet:renderURL var="viewShortcutURL">
					<portlet:param name="struts_action" value="/document_library/view_file_shortcut" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="fileShortcutId" value="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="view"
					url="<%= viewShortcutURL %>"
				/>
			</c:if>

			<c:if test="<%= view && DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>">
				<portlet:renderURL var="viewOriginalFileURL">
					<portlet:param name="struts_action" value="/document_library/view_file_entry" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(fileShortcut.getToFolderId()) %>" />
					<portlet:param name="name" value="<%= HtmlUtil.unescape(fileShortcut.getToName()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="view"
					message="view-original-file"
					url="<%= viewOriginalFileURL %>"
				/>
			</c:if>

			<c:if test="<%= showActions %>">
				<%@ include file="/html/portlet/document_library/file_entry_action_lock.jspf" %>
			</c:if>

			<c:if test="<%= showActions && DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.UPDATE) %>">
				<portlet:renderURL var="editShortcutURL">
					<portlet:param name="struts_action" value="/document_library/edit_file_shortcut" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="fileShortcutId" value="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					image="edit"
					url="<%= editShortcutURL %>"
				/>
			</c:if>

			<c:if test="<%= showActions && DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.PERMISSIONS) %>">
				<liferay-security:permissionsURL
					modelResource="<%= DLFileShortcut.class.getName() %>"
					modelResourceDescription="<%= fileEntry.getTitle() %>"
					resourcePrimKey="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>"
					var="shortcutPermissionsURL"
				/>

				<liferay-ui:icon
					image="permissions"
					url="<%= shortcutPermissionsURL %>"
				/>
			</c:if>

			<c:if test="<%= showActions && DLFileShortcutPermission.contains(permissionChecker, fileShortcut, ActionKeys.DELETE) %>">
				<portlet:actionURL var="deleteShortcutURL">
					<portlet:param name="struts_action" value="/document_library/edit_file_shortcut" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="fileShortcutId" value="<%= String.valueOf(fileShortcut.getFileShortcutId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete
					url="<%= deleteShortcutURL %>"
				/>
			</c:if>
		</c:otherwise>
	</c:choose>
</liferay-ui:icon-menu>