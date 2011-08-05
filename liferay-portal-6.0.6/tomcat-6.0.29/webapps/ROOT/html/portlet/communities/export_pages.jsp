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
String cmd = ParamUtil.getString(request, "cmd", "publish_to_live");

String tabs1 = ParamUtil.getString(request, "tabs1", "public-pages");

String pagesRedirect = ParamUtil.getString(request, "pagesRedirect");

boolean selectPages = ParamUtil.getBoolean(request, "selectPages");
boolean schedule = ParamUtil.getBoolean(request, "schedule");

Group selGroup = (Group)request.getAttribute(WebKeys.GROUP);

Group liveGroup = null;
Group stagingGroup = null;

int pagesCount = 0;

if (selGroup.isStagingGroup()) {
	liveGroup = selGroup.getLiveGroup();
	stagingGroup = selGroup;
}
else {
	liveGroup = selGroup;

	if (selGroup.hasStagingGroup()) {
		stagingGroup = selGroup.getStagingGroup();
	}
}

long selGroupId = selGroup.getGroupId();

long liveGroupId = liveGroup.getGroupId();

long stagingGroupId = 0;

if (stagingGroup != null) {
	stagingGroupId = stagingGroup.getGroupId();
}

boolean localPublishing = true;

if (liveGroup.isStaged()) {
	if (liveGroup.isStagedRemotely()) {
		localPublishing = false;
	}
}
else if (cmd.equals("publish_to_remote")) {
	localPublishing = false;
}

String treeKey = "liveLayoutsTree";

if (liveGroup.isStaged()) {
	if (!liveGroup.isStagedRemotely()) {
		treeKey = "stageLayoutsTree";
	}
	else {
		treeKey = "remoteLayoutsTree";
	}
}

treeKey = treeKey + selGroupId;

String publishActionKey = "copy";

if (liveGroup.isStaged()) {
	publishActionKey = "publish";
}
else if (cmd.equals("publish_to_remote")) {
	publishActionKey = "publish";
}

long selPlid = ParamUtil.getLong(request, "selPlid", LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

Layout selLayout = null;

try {
	selLayout = LayoutLocalServiceUtil.getLayout(selPlid);

	if (selLayout.isPrivateLayout()) {
		tabs1 = "private-pages";
	}
}
catch (NoSuchLayoutException nsle) {
}

long[] selectedPlids = new long[0];

if (selPlid > 0) {
	selectedPlids = new long[] {selPlid};
}
else {
	selectedPlids = GetterUtil.getLongValues(StringUtil.split(SessionTreeJSClicks.getOpenNodes(request, treeKey + "SelectedNode"), ","));
}

List results = new ArrayList();

for (int i = 0; i < selectedPlids.length; i++) {
	try {
		results.add(LayoutLocalServiceUtil.getLayout(selectedPlids[i]));
	}
	catch (NoSuchLayoutException nsle) {
	}
}

boolean privateLayout = tabs1.equals("private-pages");

if (privateLayout) {
	pagesCount = selGroup.getPrivateLayoutsPageCount();
}
else {
	pagesCount = selGroup.getPublicLayoutsPageCount();
}

UnicodeProperties groupTypeSettings = selGroup.getTypeSettingsProperties();
UnicodeProperties liveGroupTypeSettings = liveGroup.getTypeSettingsProperties();

Organization organization = null;
User user2 = null;

if (liveGroup.isOrganization()) {
	organization = OrganizationLocalServiceUtil.getOrganization(liveGroup.getOrganizationId());
}
else if (liveGroup.isUser()) {
	user2 = UserLocalServiceUtil.getUserById(liveGroup.getClassPK());
}

String rootNodeName = liveGroup.getDescriptiveName();

if (liveGroup.isOrganization()) {
	rootNodeName = organization.getName();
}
else if (liveGroup.isUser()) {
	rootNodeName = user2.getFullName();
}

LayoutLister layoutLister = new LayoutLister();

LayoutView layoutView = layoutLister.getLayoutView(selGroupId, privateLayout, rootNodeName, locale);

List layoutList = layoutView.getList();

PortletURL portletURL = renderResponse.createActionURL();

long proposalId = ParamUtil.getLong(request, "proposalId");

if (proposalId > 0) {
	cmd = Constants.PUBLISH;

	portletURL.setParameter("struts_action", "/communities/edit_proposal");
	portletURL.setParameter("groupId", String.valueOf(liveGroupId));
	portletURL.setParameter("proposalId", String.valueOf(proposalId));
}
else {
	if (selGroup.isStaged() && selGroup.isStagedRemotely()) {
		cmd = "publish_to_remote";
	}

	portletURL.setParameter("struts_action", "/communities/edit_pages");
	portletURL.setParameter("groupId", String.valueOf(liveGroupId));
	portletURL.setParameter("private", String.valueOf(privateLayout));
}

PortletURL selectURL = renderResponse.createRenderURL();

selectURL.setWindowState(LiferayWindowState.EXCLUSIVE);
selectURL.setParameter("struts_action", "/communities/export_pages");
selectURL.setParameter(Constants.CMD, cmd);
selectURL.setParameter("pagesRedirect", pagesRedirect);
selectURL.setParameter("groupId", String.valueOf(selGroupId));
selectURL.setParameter("selectPages", String.valueOf(!selectPages));
selectURL.setParameter("schedule", String.valueOf(schedule));

request.setAttribute("edit_pages.jsp-groupId", new Long(selGroupId));
request.setAttribute("edit_pages.jsp-selPlid", new Long(selPlid));
request.setAttribute("edit_pages.jsp-privateLayout", new Boolean(privateLayout));

request.setAttribute("edit_pages.jsp-rootNodeName", rootNodeName);

request.setAttribute("edit_pages.jsp-layoutList", layoutList);

request.setAttribute("edit_pages.jsp-portletURL", portletURL);

response.setHeader("Ajax-ID", request.getHeader("Ajax-ID"));
%>

<style type="text/css">
	#<portlet:namespace />pane th.col-3 {
		text-align: left;
		width: 74%;
	}

	#<portlet:namespace />pane td.col-1 {
		padding-top: 5px;
	}

	#<portlet:namespace />exportPagesFm .export-pages-panel-container {
		margin: 1em 0;
	}

	#<portlet:namespace />exportPagesFm .export-pages-panel-container .lfr-panel-content{
		padding: 1em;
	}

	#<portlet:namespace />exportPagesFm .selected-pages-option .aui-field-content {
		display: inline;
	}

	#<portlet:namespace />pane {
		border: 1px solid #CCC;
		padding: 5px;
	}
</style>

<aui:form action='<%= portletURL.toString() + "&etag=0" %>' method="post" name="exportPagesFm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "refreshDialog();" %>' >
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= cmd %>" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="pagesRedirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="stagingGroupId" type="hidden" value="<%= stagingGroupId %>" />

	<liferay-ui:error exception="<%= RemoteExportException.class %>">

		<%
		RemoteExportException ree = (RemoteExportException)errorException;
		%>

		<c:if test="<%= ree.getType() == RemoteExportException.BAD_CONNECTION %>">
			<liferay-ui:message arguments="<%= ree.getURL() %>" key="there-was-a-bad-connection-with-the-remote-server-at-x" />
		</c:if>

		<c:if test="<%= ree.getType() == RemoteExportException.NO_GROUP %>">

			<%
			String groupType = LanguageUtil.get(pageContext, (liveGroup.isOrganization()? "organization" : "community"));
			%>

			<liferay-ui:message arguments="<%= new Object[] {groupType, ree.getGroupId()} %>" key="no-group-exists-on-the-remote-server-with-group-id-x" />
		</c:if>

		<c:if test="<%= ree.getType() == RemoteExportException.NO_LAYOUTS %>">
			<liferay-ui:message key="there-are-no-layouts-in-the-exported-data" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= RemoteOptionsException.class %>">

		<%
		RemoteOptionsException roe = (RemoteOptionsException)errorException;
		%>

		<c:if test="<%= roe.getType() == RemoteOptionsException.REMOTE_ADDRESS %>">
			<liferay-ui:message arguments="<%= roe.getRemoteAddress() %>" key="the-remote-address-x-is-not-valid" />
		</c:if>

		<c:if test="<%= roe.getType() == RemoteOptionsException.REMOTE_GROUP_ID %>">
			<liferay-ui:message arguments="<%= roe.getRemoteGroupId() %>" key="the-remote-group-id-x-is-not-valid" />
		</c:if>

		<c:if test="<%= roe.getType() == RemoteOptionsException.REMOTE_PORT %>">
			<liferay-ui:message arguments="<%= roe.getRemotePort() %>" key="the-remote-port-x-is-not-valid" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= SystemException.class %>">

		<%
		SystemException se = (SystemException)errorException;
		%>

		<liferay-ui:message key="<%= se.getMessage() %>" />
	</liferay-ui:error>

	<c:choose>
		<c:when test="<%= selectPages %>">
			<div id="<portlet:namespace />pane">
				<liferay-util:include page="/html/portlet/communities/tree_js.jsp">
					<liferay-util:param name="selectableTree" value="1" />
					<liferay-util:param name="treeId" value="<%= treeKey %>" />
				</liferay-util:include>
			</div>

			<aui:button-row>

				<%
				String taglibOnClick = "AUI().DialogManager.refreshByChild('#" + renderResponse.getNamespace() + "exportPagesFm');";
				%>

				<aui:button onClick="<%= taglibOnClick %>" value="select" />
			</aui:button-row>
		</c:when>
		<c:otherwise>
			<c:if test="<%= schedule %>">
				<div class="lfr-portlet-toolbar">
					<span class="lfr-toolbar-button view-button">
						<aui:a href='javascript:;' label="view-all" />
					</span>

					<span class="lfr-toolbar-button add-button current">
						<aui:a href='javascript:;' label="add" />
					</span>
				</div>

				<div class="aui-helper-hidden" id="<portlet:namespace />publishedEvents">
					<liferay-ui:header
						title="scheduled-events"
					/>

					<div id="<portlet:namespace />scheduledPublishEventsDiv"></div>
				</div>


			</c:if>

			<div id="<portlet:namespace />publishOptions">
				<c:if test="<%= schedule %>">
					<liferay-ui:header
						title="new-event"
					/>

					<aui:input label="title" name="description" type="text" />
				</c:if>

				<liferay-ui:panel-container cssClass="export-pages-panel-container" extended="<%= true %>" persistState="<%= true %>">
					<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "pages") %>'>
						<%@ include file="/html/portlet/communities/export_pages_select_pages.jspf" %>
					</liferay-ui:panel>

					<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= true %>" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "options") %>'>
						<%@ include file="/html/portlet/communities/export_pages_options.jspf" %>
					</liferay-ui:panel>

					<c:if test="<%= !localPublishing %>">
						<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= true %>" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "remote-live-connection-settings") %>'>
							<%@ include file="/html/portlet/communities/export_pages_remote_options.jspf" %>
						</liferay-ui:panel>
					</c:if>

					<c:if test="<%= proposalId <= 0 && schedule %>">
						<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "schedule") %>'>
							<%@ include file="/html/portlet/communities/export_pages_scheduler.jspf" %>
						</liferay-ui:panel>
					</c:if>
				</liferay-ui:panel-container>

				<c:choose>
					<c:when test="<%= schedule %>">
						<aui:button-row>
							<aui:button name="addButton" value="add-event" />
						</aui:button-row>
					</c:when>
					<c:otherwise>
						<aui:button-row>
							<aui:button name="publishBtn" type="submit" value="<%= publishActionKey %>" />
						</aui:button-row>
					</c:otherwise>
				</c:choose>
			</div>
		</c:otherwise>
	</c:choose>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />refreshDialog',
		function() {
			var A = AUI();

			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-" + publishActionKey + "-these-pages") %>')) {
				var dialog = A.DialogManager.findByChild('#<portlet:namespace />exportPagesFm');

				if (dialog) {
					dialog.io.set('uri', '<%= portletURL.toString() + "&etag=0" %>');

					dialog.io.set(
						'form',
						{
							id: '<portlet:namespace />exportPagesFm'
						}
					);

					dialog.io.detach('success');

					dialog.io.after(
						'success',
						function(event){
							dialog.close();
						}
					);

					dialog.io.start();
				}
			}
		},
		['aui-dialog']
	);
</aui:script>

<aui:script use="aui-base,aui-dialog">
	var dialog = A.DialogManager.findByChild('#<portlet:namespace />exportPagesFm');

	if (dialog) {
		dialog.io.set('uri', '<%= selectURL %>');

		<c:if test="<%= schedule %>">
			var toolbarViewButton = A.one('#<portlet:namespace />exportPagesFm .view-button');
			var toolbarAddButton = A.one('#<portlet:namespace />exportPagesFm .add-button');
			var addEventButton = A.one('#<portlet:namespace />addButton');

			var allEvents = A.one('#<portlet:namespace />publishedEvents');
			var publishOptions = A.one('#<portlet:namespace />publishOptions');

			var viewEvents = function() {
				toolbarAddButton.removeClass('current');
				toolbarViewButton.addClass('current');

				allEvents.show();
				publishOptions.hide();
			};

			addEventButton.on(
				'click',
				function(event) {
					<portlet:namespace />schedulePublishEvent();

					viewEvents();
				}
			);

			toolbarAddButton.one('a').on(
				'click',
				function(event) {
					toolbarAddButton.addClass('current');
					toolbarViewButton.removeClass('current');

					allEvents.hide();
					publishOptions.show();
				}
			);

			toolbarViewButton.one('a').on(
				'click',
				function(event) {
					viewEvents();
				}
			);
		</c:if>
	}
</aui:script>