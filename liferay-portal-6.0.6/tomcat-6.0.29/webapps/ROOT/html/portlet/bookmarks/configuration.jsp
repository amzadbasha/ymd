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

<%@ include file="/html/portlet/bookmarks/init.jsp" %>

<%
String strutsAction = "/bookmarks";

String redirect = ParamUtil.getString(request, "redirect");
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveConfiguration();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="rootFolderId" type="hidden" value="<%= rootFolderId %>" />
	<aui:input name="folderColumns" type="hidden" />
	<aui:input name="entryColumns" type="hidden" />

	<liferay-ui:error key="rootFolderId" message="please-enter-a-valid-root-folder" />

	<liferay-ui:panel-container extended="<%= true %>" id="bookmarksSettingsPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="foldersListingPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "folders-listing") %>'>
			<aui:fieldset>
				<aui:field-wrapper label="root-folder">
					<portlet:renderURL var="viewFolderURL">
						<portlet:param name="struts_action" value='<%= strutsAction + "/view" %>' />
						<portlet:param name="folderId" value="<%= String.valueOf(rootFolderId) %>" />
					</portlet:renderURL>

					<aui:a href="<%= viewFolderURL %>" id="rootFolderName"><%= rootFolderName %></aui:a>

					<aui:button name="openFolderSelectorButton" onClick='<%= renderResponse.getNamespace() + "openFolderSelector();" %>' type="button" value="select" />

					<aui:button disabled="<%= rootFolderId <= 0 %>" name="removeFolderButton" onClick='<%= renderResponse.getNamespace() + "removeFolder();" %>' value="remove" />
				</aui:field-wrapper>

				<aui:input inlineLabel="left" label="show-search" name="showFoldersSearch" type="checkbox" value="<%= showFoldersSearch %>" />

				<aui:input inlineLabel="left" name="showSubfolders" type="checkbox" value="<%= showSubfolders %>" />

				<aui:input name="foldersPerPage" size="2" type="text" value="<%= foldersPerPage %>" />

				<aui:field-wrapper label="show-columns">

					<%
					Set availableFolderColumns = SetUtil.fromArray(StringUtil.split(allFolderColumns));

					// Left list

					List leftList = new ArrayList();

					for (int i = 0; i < folderColumns.length; i++) {
						String folderColumn = folderColumns[i];

						leftList.add(new KeyValuePair(folderColumn, LanguageUtil.get(pageContext, folderColumn)));
					}

					// Right list

					List rightList = new ArrayList();

					Arrays.sort(folderColumns);

					Iterator itr = availableFolderColumns.iterator();

					while (itr.hasNext()) {
						String folderColumn = (String)itr.next();

						if (Arrays.binarySearch(folderColumns, folderColumn) < 0) {
							rightList.add(new KeyValuePair(folderColumn, LanguageUtil.get(pageContext, folderColumn)));
						}
					}

					rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
					%>

					<liferay-ui:input-move-boxes
						leftTitle="current"
						rightTitle="available"
						leftBoxName="currentFolderColumns"
						rightBoxName="availableFolderColumns"
						leftReorder="true"
						leftList="<%= leftList %>"
						rightList="<%= rightList %>"
					/>
				</aui:field-wrapper>
			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="bookmarksListingPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "bookmarks-listing") %>'>
			<aui:fieldset>
				<aui:input label="documents-per-page" name="entriesPerPage" size="2" type="text" value="<%= entriesPerPage %>" />

				<aui:field-wrapper label="show-columns">

					<%
					Set availableEntryColumns = SetUtil.fromArray(StringUtil.split(allEntryColumns));

					// Left list

					List leftList = new ArrayList();

					for (int i = 0; i < entryColumns.length; i++) {
						String entryColumn = entryColumns[i];

						leftList.add(new KeyValuePair(entryColumn, LanguageUtil.get(pageContext, entryColumn)));
					}

					// Right list

					List rightList = new ArrayList();

					Arrays.sort(entryColumns);

					Iterator itr = availableEntryColumns.iterator();

					while (itr.hasNext()) {
						String entryColumn = (String)itr.next();

						if (Arrays.binarySearch(entryColumns, entryColumn) < 0) {
							rightList.add(new KeyValuePair(entryColumn, LanguageUtil.get(pageContext, entryColumn)));
						}
					}

					rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
					%>

					<liferay-ui:input-move-boxes
						leftTitle="current"
						rightTitle="available"
						leftBoxName="currentEntryColumns"
						rightBoxName="availableEntryColumns"
						leftReorder="true"
						leftList="<%= leftList %>"
						rightList="<%= rightList %>"
					/>
				</aui:field-wrapper>
			</aui:fieldset>
		</liferay-ui:panel>
	</liferay-ui:panel-container>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />openFolderSelector() {
		var folderWindow = window.open('<liferay-portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" portletName="<%= portletResource %>"><portlet:param name="struts_action" value='<%= strutsAction + "/select_folder" %>' /></liferay-portlet:renderURL>', 'folder', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=830');

		folderWindow.focus();
	}

	function <portlet:namespace />removeFolder() {
		document.<portlet:namespace />fm.<portlet:namespace />rootFolderId.value = "<%=  BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID %>";

		var nameEl = document.getElementById("<portlet:namespace />rootFolderName");

		nameEl.href = "";
		nameEl.innerHTML = "";
	}

	function <%= PortalUtil.getPortletNamespace(portletResource) %>selectFolder(rootFolderId, rootFolderName) {
		document.<portlet:namespace />fm.<portlet:namespace />rootFolderId.value = rootFolderId;

		var nameEl = document.getElementById("<portlet:namespace />rootFolderName");

		nameEl.href = "<liferay-portlet:renderURL portletName="<%= portletResource %>"><portlet:param name="struts_action" value='<%= strutsAction + "/view" %>' /></liferay-portlet:renderURL>&<portlet:namespace />folderId=" + rootFolderId;
		nameEl.innerHTML = rootFolderName + "&nbsp;";
	}

	Liferay.provide(
		window,
		'<portlet:namespace />saveConfiguration',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace />folderColumns.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentFolderColumns);
			document.<portlet:namespace />fm.<portlet:namespace />entryColumns.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentEntryColumns);

			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>