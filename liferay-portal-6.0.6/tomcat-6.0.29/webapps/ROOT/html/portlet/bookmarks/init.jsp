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

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.search.Document" %>
<%@ page import="com.liferay.portal.kernel.search.Field" %>
<%@ page import="com.liferay.portal.kernel.search.Hits" %>
<%@ page import="com.liferay.portal.kernel.search.Indexer" %>
<%@ page import="com.liferay.portal.kernel.search.IndexerRegistryUtil" %>
<%@ page import="com.liferay.portal.kernel.search.SearchContext" %>
<%@ page import="com.liferay.portal.kernel.search.SearchContextFactory" %>
<%@ page import="com.liferay.portlet.asset.model.AssetCategory" %>
<%@ page import="com.liferay.portlet.asset.model.AssetEntry" %>
<%@ page import="com.liferay.portlet.asset.model.AssetVocabulary" %>
<%@ page import="com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.service.AssetEntryServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.service.persistence.AssetEntryQuery" %>
<%@ page import="com.liferay.portlet.bookmarks.EntryURLException" %>
<%@ page import="com.liferay.portlet.bookmarks.FolderNameException" %>
<%@ page import="com.liferay.portlet.bookmarks.NoSuchEntryException" %>
<%@ page import="com.liferay.portlet.bookmarks.NoSuchFolderException" %>
<%@ page import="com.liferay.portlet.bookmarks.model.BookmarksEntry" %>
<%@ page import="com.liferay.portlet.bookmarks.model.BookmarksFolder" %>
<%@ page import="com.liferay.portlet.bookmarks.model.BookmarksFolderConstants" %>
<%@ page import="com.liferay.portlet.bookmarks.service.BookmarksEntryLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.bookmarks.service.permission.BookmarksEntryPermission" %>
<%@ page import="com.liferay.portlet.bookmarks.service.permission.BookmarksFolderPermission" %>
<%@ page import="com.liferay.portlet.bookmarks.util.BookmarksUtil" %>

<%
PortalPreferences portalPrefs = PortletPreferencesFactoryUtil.getPortalPreferences(request);

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);

PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}
else if (layout.isTypeControlPanel()) {
	preferences = PortletPreferencesLocalServiceUtil.getPreferences(themeDisplay.getCompanyId(), scopeGroupId, PortletKeys.PREFS_OWNER_TYPE_GROUP, 0, PortletKeys.BOOKMARKS, null);
}

BookmarksFolder rootFolder = null;

long rootFolderId = PrefsParamUtil.getLong(preferences, request, "rootFolderId", BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);
String rootFolderName = StringPool.BLANK;

if (rootFolderId != BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
	try {
		rootFolder = BookmarksFolderLocalServiceUtil.getFolder(rootFolderId);

		rootFolderName = rootFolder.getName();
	}
	catch (NoSuchFolderException nsfe) {
	}
}

boolean showFoldersSearch = PrefsParamUtil.getBoolean(preferences, request, "showFoldersSearch", true);
boolean showSubfolders = PrefsParamUtil.getBoolean(preferences, request, "showSubfolders", true);
int foldersPerPage = PrefsParamUtil.getInteger(preferences, request, "foldersPerPage", SearchContainer.DEFAULT_DELTA);

String defaultFolderColumns = "folder,num-of-folders,num-of-entries";

String portletId = portletDisplay.getId();

if (portletId.equals(PortletKeys.PORTLET_CONFIGURATION)) {
	portletId = portletResource;
}

if (portletId.equals(PortletKeys.BOOKMARKS)) {
	defaultFolderColumns += ",action";
}

String allFolderColumns = defaultFolderColumns;

String[] folderColumns = StringUtil.split(PrefsParamUtil.getString(preferences, request, "folderColumns", defaultFolderColumns));

if (!portletId.equals(PortletKeys.BOOKMARKS)) {
	folderColumns = ArrayUtil.remove(folderColumns, "action");
}

int entriesPerPage = PrefsParamUtil.getInteger(preferences, request, "entriesPerPage", SearchContainer.DEFAULT_DELTA);

String defaultEntryColumns = "name,url,visits,modified-date";

if (portletId.equals(PortletKeys.BOOKMARKS)) {
	defaultEntryColumns += ",action";
}

String allEntryColumns = defaultEntryColumns;

String[] entryColumns = StringUtil.split(PrefsParamUtil.getString(preferences, request, "entryColumns", defaultEntryColumns));

if (!portletId.equals(PortletKeys.BOOKMARKS)) {
	entryColumns = ArrayUtil.remove(entryColumns, "action");
}
%>

<%@ include file="/html/portlet/bookmarks/init-ext.jsp" %>