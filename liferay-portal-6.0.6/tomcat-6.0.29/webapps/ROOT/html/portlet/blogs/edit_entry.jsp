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

<%@ include file="/html/portlet/blogs/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String referringPortletResource = ParamUtil.getString(request, "referringPortletResource");

BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);

long entryId = BeanParamUtil.getLong(entry, request, "entryId");

String content = BeanParamUtil.getString(entry, request, "content");

Calendar displayDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

if (entry != null) {
	if (entry.getDisplayDate() != null) {
		displayDate.setTime(entry.getDisplayDate());
	}
}

boolean allowPingbacks = PropsValues.BLOGS_PINGBACK_ENABLED && BeanParamUtil.getBoolean(entry, request, "allowPingbacks", true);
boolean allowTrackbacks = PropsValues.BLOGS_TRACKBACK_ENABLED && BeanParamUtil.getBoolean(entry, request, "allowTrackbacks", true);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (entry != null) ? entry.getTitle() : "new-blog-entry" %>'
/>

<portlet:actionURL var="editEntryURL">
	<portlet:param name="struts_action" value="/blogs/edit_entry" />
</portlet:actionURL>

<aui:form action="<%= editEntryURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveEntry(false);" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="referringPortletResource" type="hidden" value="<%= referringPortletResource %>" />
	<aui:input name="entryId" type="hidden" value="<%= entryId %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_PUBLISH %>" />

	<liferay-ui:error exception="<%= EntryTitleException.class %>" message="please-enter-a-valid-title" />
	<liferay-ui:asset-tags-error />

	<aui:model-context bean="<%= entry %>" model="<%= BlogsEntry.class %>" />

	<c:if test="<%= (entry == null) || !entry.isApproved() %>">
		<div class="save-status" id="<portlet:namespace />saveStatus"></div>
	</c:if>

	<c:if test="<%= entry != null %>">
		<aui:workflow-status id="<%= String.valueOf(entry.getEntryId()) %>" status="<%= entry.getStatus() %>" />
	</c:if>

	<aui:fieldset>
		<aui:input name="title" />

		<aui:input name="displayDate" value="<%= displayDate %>" />

		<aui:field-wrapper label="content">
			<liferay-ui:input-editor editorImpl="<%= EDITOR_WYSIWYG_IMPL_KEY %>" />

			<aui:input name="content" type="hidden" />
		</aui:field-wrapper>

		<liferay-ui:custom-attributes-available className="<%= BlogsEntry.class.getName() %>">
			<liferay-ui:custom-attribute-list
				className="<%= BlogsEntry.class.getName() %>"
				classPK="<%= (entry != null) ? entry.getEntryId() : 0 %>"
				editable="<%= true %>"
				label="<%= true %>"
			/>
		</liferay-ui:custom-attributes-available>

		<c:if test="<%= PropsValues.BLOGS_PINGBACK_ENABLED %>">
			<aui:input helpMessage="to-allow-pingbacks,-please-also-ensure-the-entry's-guest-view-permission-is-enabled" inlineLabel="left" label="allow-pingbacks" name="allowPingbacks" value="<%= allowPingbacks %>" />
		</c:if>

		<c:if test="<%= PropsValues.BLOGS_TRACKBACK_ENABLED %>">
			<aui:input helpMessage="to-allow-trackbacks,-please-also-ensure-the-entry's-guest-view-permission-is-enabled" inlineLabel="left" label="allow-trackbacks" name="allowTrackbacks" value="<%= allowTrackbacks %>" />

			<aui:input label="trackbacks-to-send" name="trackbacks" />

			<c:if test="<%= (entry != null) && Validator.isNotNull(entry.getTrackbacks()) %>">
				<aui:field-wrapper name="trackbacks-already-sent">

					<%
					for (String trackback : StringUtil.split(entry.getTrackbacks())) {
					%>

						<%= HtmlUtil.escape(trackback) %><br />

					<%
					}
					%>

				</aui:field-wrapper>
			</c:if>
		</c:if>

		<aui:input name="categories" type="assetCategories" />

		<aui:input name="tags" type="assetTags" />

		<c:if test="<%= (entry == null) || (entry.getStatus() == WorkflowConstants.STATUS_DRAFT) %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= BlogsEntry.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<%
		boolean pending = false;

		if (entry != null) {
			pending = entry.isPending();
		}
		%>

		<c:if test="<%= pending %>">
			<div class="portlet-msg-info">
				<liferay-ui:message key="there-is-a-publication-workflow-in-process" />
			</div>
		</c:if>

		<aui:button-row>

			<%
			String saveButtonLabel = "save";

			if ((entry == null) || entry.isDraft() || entry.isApproved()) {
				saveButtonLabel = "save-as-draft";
			}

			String publishButtonLabel = "publish";

			if (WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(themeDisplay.getCompanyId(), scopeGroupId, BlogsEntry.class.getName())) {
				publishButtonLabel = "submit-for-publication";
			}
			%>

			<c:if test="<%= (entry != null) && entry.isApproved() && WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(entry.getCompanyId(), entry.getGroupId(), BlogsEntry.class.getName()) %>">
				<div class="portlet-msg-info">
					<%= LanguageUtil.format(pageContext, "this-x-is-approved.-publishing-these-changes-will-cause-it-to-be-unpublished-and-go-through-the-approval-process-again", ResourceActionsUtil.getModelResource(locale, BlogsEntry.class.getName())) %>
				</div>
			</c:if>

			<aui:button name="saveButton" onClick='<%= renderResponse.getNamespace() + "saveEntry(true);" %>' type="button" value="<%= saveButtonLabel %>" />

			<aui:button disabled="<%= pending %>" name="publishButton" type="submit" value="<%= publishButtonLabel %>" />

			<aui:button name="cancelButton" onClick="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	var <portlet:namespace />saveDraftIntervalId = null;
	var <portlet:namespace />oldTitle = null;
	var <portlet:namespace />oldContent = null;

	function <portlet:namespace />clearSaveDraftIntervalId() {
		if (<portlet:namespace />saveDraftIntervalId != null) {
			clearInterval(<portlet:namespace />saveDraftIntervalId);
		}
	}

	function <portlet:namespace />getSuggestionsContent() {
		var content = '';

		content += document.<portlet:namespace />fm.<portlet:namespace />title.value + ' ';
		content += window.<portlet:namespace />editor.getHTML();

		return content;
	}

	function <portlet:namespace />initEditor() {
		return "<%= UnicodeFormatter.toString(content) %>";
	}

	Liferay.provide(
		window,
		'<portlet:namespace />saveEntry',
		function(draft) {
			var A = AUI();

			var title = document.<portlet:namespace />fm.<portlet:namespace />title.value;
			var content = window.<portlet:namespace />editor.getHTML();

			var publishButton = A.one('#<portlet:namespace />publishButton');
			var cancelButton = A.one('#<portlet:namespace />cancelButton');

			var saveStatus = A.one('#<portlet:namespace />saveStatus');
			var saveText = '<%= UnicodeLanguageUtil.format(pageContext, ((entry != null) && entry.isPending()) ? "entry-saved-at-x" : "draft-saved-at-x", "[TIME]", false) %>';

			if (draft) {
				if ((title == '') || (content == '')) {
					return;
				}

				if ((<portlet:namespace />oldTitle == title) &&
					(<portlet:namespace />oldContent == content)) {

					return;
				}

				<portlet:namespace />oldTitle = title;
				<portlet:namespace />oldContent = content;

				var url = '<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/blogs/edit_entry" /></portlet:actionURL>';

				A.io.request(
					url,
					{
						data: {
							<portlet:namespace />assetTagNames: document.<portlet:namespace />fm.<portlet:namespace />assetTagNames.value,
							<portlet:namespace /><%= Constants.CMD %>: '<%= Constants.ADD %>',
							<portlet:namespace />content: content,
							<portlet:namespace />displayDateAmPm: document.<portlet:namespace />fm.<portlet:namespace />displayDateAmPm.value,
							<portlet:namespace />displayDateDay: document.<portlet:namespace />fm.<portlet:namespace />displayDateDay.value,
							<portlet:namespace />displayDateHour: document.<portlet:namespace />fm.<portlet:namespace />displayDateHour.value,
							<portlet:namespace />displayDateMinute: document.<portlet:namespace />fm.<portlet:namespace />displayDateMinute.value,
							<portlet:namespace />displayDateMonth: document.<portlet:namespace />fm.<portlet:namespace />displayDateMonth.value,
							<portlet:namespace />displayDateYear: document.<portlet:namespace />fm.<portlet:namespace />displayDateYear.value,
							<portlet:namespace />entryId: document.<portlet:namespace />fm.<portlet:namespace />entryId.value,
							<portlet:namespace />redirect: document.<portlet:namespace />fm.<portlet:namespace />redirect.value,
							<portlet:namespace />referringPortletResource: document.<portlet:namespace />fm.<portlet:namespace />referringPortletResource.value,
							<portlet:namespace />title: title,
							<portlet:namespace />workflowAction: <%= WorkflowConstants.ACTION_SAVE_DRAFT %>
						},
						dataType: 'json',
						on: {
							failure: function() {
								if (saveStatus) {
									saveStatus.set('className', 'save-status portlet-msg-error');
									saveStatus.html('<%= UnicodeLanguageUtil.get(pageContext, "could-not-save-draft-to-the-server") %>');
								}
							},
							start: function() {
								if (publishButton) {
									publishButton.attr('disabled', true);
								}

								if (saveStatus) {
									saveStatus.set('className', 'save-status portlet-msg-info pending');
									saveStatus.html('<%= UnicodeLanguageUtil.get(pageContext, "saving-draft") %>');
								}
							},
							success: function(event, id, obj) {
								var instance = this;

								var message = instance.get('responseData');

								document.<portlet:namespace />fm.<portlet:namespace />entryId.value = message.entryId;
								document.<portlet:namespace />fm.<portlet:namespace />redirect.value = message.redirect;

								if (publishButton) {
									publishButton.attr('disabled', false);
								}

								var tabs1BackButton = A.one('#<portlet:namespace />tabs1TabsBack');

								if (tabs1BackButton) {
									tabs1BackButton.attr('href', message.redirect);
								}

								if (cancelButton) {
									cancelButton.detach('click');

									cancelButton.on(
										'click',
										function() {
											location.href = message.redirect;
										}
									);
								}

								var now = saveText.replace(/\[TIME\]/gim, (new Date()).toString());

								if (saveStatus) {
									saveStatus.set('className', 'save-status portlet-msg-success');
									saveStatus.html(now);
								}
							}
						}
					}
				);
			}
			else {
				<portlet:namespace />clearSaveDraftIntervalId();

				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (entry == null) ? Constants.ADD : Constants.UPDATE %>";
				document.<portlet:namespace />fm.<portlet:namespace />content.value = content;
				document.<portlet:namespace />fm.<portlet:namespace />workflowAction.value = <%= WorkflowConstants.ACTION_PUBLISH %>;
				submitForm(document.<portlet:namespace />fm);
			}
		},
		['aui-io']
	);

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />title);
	</c:if>
</aui:script>

<aui:script use="aui-base">
	var cancelButton = A.one('#<portlet:namespace />cancelButton');

	if (cancelButton) {
		cancelButton.on(
			'click',
			function() {
				<portlet:namespace />clearSaveDraftIntervalId();

				location.href = '<%= UnicodeFormatter.toString(redirect) %>';
			}
		);
	}

	<c:if test="<%= (entry == null) || (entry.getStatus() == WorkflowConstants.STATUS_DRAFT) %>">
		<portlet:namespace />saveDraftIntervalId = setInterval('<portlet:namespace />saveEntry(true)', 30000);
		<portlet:namespace />oldTitle = document.<portlet:namespace />fm.<portlet:namespace />title.value;
		<portlet:namespace />oldContent = <portlet:namespace />initEditor();
	</c:if>
</aui:script>

<%
if (entry != null) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("struts_action", "/blogs/view_entry");
	portletURL.setParameter("entryId", String.valueOf(entry.getEntryId()));

	PortalUtil.addPortletBreadcrumbEntry(request, entry.getTitle(), portletURL.toString());
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-entry"), currentURL);
}
%>

<%!
public static final String EDITOR_WYSIWYG_IMPL_KEY = "editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit_entry.jsp";
%>