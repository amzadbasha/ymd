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

<%@ include file="/html/portlet/message_boards/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "general");

String redirect = ParamUtil.getString(request, "redirect");

String emailFromName = ParamUtil.getString(request, "emailFromName", MBUtil.getEmailFromName(preferences));
String emailFromAddress = ParamUtil.getString(request, "emailFromAddress", MBUtil.getEmailFromAddress(preferences));

String emailMessageAddedSubjectPrefix = ParamUtil.getString(request, "emailMessageAddedSubjectPrefix", MBUtil.getEmailMessageAddedSubjectPrefix(preferences));
String emailMessageAddedBody = ParamUtil.getString(request, "emailMessageAddedBody", MBUtil.getEmailMessageAddedBody(preferences));
String emailMessageAddedSignature = ParamUtil.getString(request, "emailMessageAddedSignature", MBUtil.getEmailMessageAddedSignature(preferences));

String emailMessageUpdatedSubjectPrefix = ParamUtil.getString(request, "emailMessageUpdatedSubjectPrefix", MBUtil.getEmailMessageUpdatedSubjectPrefix(preferences));
String emailMessageUpdatedBody = ParamUtil.getString(request, "emailMessageUpdatedBody", MBUtil.getEmailMessageUpdatedBody(preferences));
String emailMessageUpdatedSignature = ParamUtil.getString(request, "emailMessageUpdatedSignature", MBUtil.getEmailMessageUpdatedSignature(preferences));

String bodyEditorParam = StringPool.BLANK;
String bodyEditorContent = StringPool.BLANK;
String signatureEditorParam = StringPool.BLANK;
String signatureEditorContent = StringPool.BLANK;

if (tabs2.equals("message-added-email")) {
	bodyEditorParam = "emailMessageAddedBody";
	bodyEditorContent = emailMessageAddedBody;
	signatureEditorParam = "emailMessageAddedSignature";
	signatureEditorContent = emailMessageAddedSignature;
}
else if (tabs2.equals("message-updated-email")) {
	bodyEditorParam = "emailMessageUpdatedBody";
	bodyEditorContent = emailMessageUpdatedBody;
	signatureEditorParam = "emailMessageUpdatedSignature";
	signatureEditorContent = emailMessageUpdatedSignature;
}
%>

<liferay-portlet:renderURL var="portletURL" portletConfiguration="true">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveConfiguration();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:tabs
		names="general,email-from,message-added-email,message-updated-email,thread-priorities,user-ranks,rss"
		param="tabs2"
		url="<%= portletURL %>"
	/>

	<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
	<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />
	<liferay-ui:error key="emailMessageAddedBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailMessageAddedSignature" message="please-enter-a-valid-signature" />
	<liferay-ui:error key="emailMessageAddedSubjectPrefix" message="please-enter-a-valid-subject" />
	<liferay-ui:error key="emailMessageUpdatedBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailMessageUpdatedSignature" message="please-enter-a-valid-signature" />
	<liferay-ui:error key="emailMessageUpdatedSubjectPrefix" message="please-enter-a-valid-subject" />

	<c:choose>
		<c:when test='<%= tabs2.equals("general") %>'>
			<aui:fieldset>
				<aui:input inlineLabel="left" name="allowAnonymousPosting" type="checkbox" value="<%= MBUtil.isAllowAnonymousPosting(preferences) %>" />

				<aui:input inlineLabel="left" name="enableFlags" type="checkbox" value="<%= enableFlags %>" />

				<aui:input inlineLabel="left" name="enableRatings" type="checkbox" value="<%= enableRatings %>" />
			</aui:fieldset>
		</c:when>
		<c:when test='<%= tabs2.equals("email-from") %>'>
			<aui:fieldset>
				<aui:input cssClass="lfr-input-text-container" label="name" name="emailFromName" value="<%= emailFromName %>" />

				<aui:input cssClass="lfr-input-text-container" label="address" name="emailFromAddress" value="<%= emailFromAddress %>" />

				<aui:input inlineLabel="left" label="html-format" name="emailHtmlFormat" type="checkbox" value="<%= MBUtil.getEmailHtmlFormat(preferences) %>" />
			</aui:fieldset>

			<div class="definition-of-terms">
				<h4><liferay-ui:message key="definition-of-terms" /></h4>

				<dl>
					<dt>
						[$COMPANY_ID$]
					</dt>
					<dd>
						<liferay-ui:message key="the-company-id-associated-with-the-message-board" />
					</dd>
					<dt>
						[$COMPANY_MX$]
					</dt>
					<dd>
						<liferay-ui:message key="the-company-mx-associated-with-the-message-board" />
					</dd>
					<dt>
						[$COMPANY_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-company-name-associated-with-the-message-board" />
					</dd>
					<dt>
						[$COMMUNITY_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-community-name-associated-with-the-message-board" />
					</dd>
					<dt>
						[$MAILING_LIST_ADDRESS$]
					</dt>
					<dd>
						<liferay-ui:message key="the-email-address-of-the-mailing-list" />
					</dd>
					<dt>
						[$MESSAGE_USER_ADDRESS$]
					</dt>
					<dd>
						<liferay-ui:message key="the-email-address-of-the-user-who-added-the-message" />
					</dd>
					<dt>
						[$MESSAGE_USER_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-user-who-added-the-message" />
					</dd>
					<dt>
						[$PORTLET_NAME$]
					</dt>
					<dd>
						<%= PortalUtil.getPortletTitle(renderResponse) %>
					</dd>
				</dl>
			</div>
		</c:when>
		<c:when test='<%= tabs2.startsWith("message-") %>'>
			<aui:fieldset>
				<c:choose>
					<c:when test='<%= tabs2.equals("message-added-email") %>'>
						<aui:input inlineLabel="left" label="enabled" name="emailMessageAddedEnabled" type="checkbox" value="<%= MBUtil.getEmailMessageAddedEnabled(preferences) %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("message-updated-email") %>'>
						<aui:input inlineLabel="left" label="enabled" name="emailMessageUpdatedEnabled" type="checkbox" value="<%= MBUtil.getEmailMessageUpdatedEnabled(preferences) %>" />
					</c:when>
				</c:choose>

				<c:choose>
					<c:when test='<%= tabs2.equals("message-added-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject-prefix" name="emailMessageAddedSubjectPrefix" value="<%= emailMessageAddedSubjectPrefix %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("message-updated-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject-prefix" name="emailMessageUpdatedSubjectPrefix" value="<%= emailMessageUpdatedSubjectPrefix %>" />
					</c:when>
				</c:choose>

				<aui:input cssClass="lfr-textarea-container" label="body" name="<%= bodyEditorParam %>" type="textarea" value="<%= bodyEditorContent %>" warp="soft" />

				<aui:input cssClass="lfr-textarea-container" label="signature" name="<%= signatureEditorParam %>" type="textarea" value="<%= signatureEditorContent %>" wrap="soft" />
			</aui:fieldset>

			<div class="definition-of-terms">
				<h4><liferay-ui:message key="definition-of-terms" /></h4>

				<dl>
					<dt>
						[$CATEGORY_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-category-in-which-the-message-has-been-posted" />
					</dd>
					<dt>
						[$COMPANY_ID$]
					</dt>
					<dd>
						<liferay-ui:message key="the-company-id-associated-with-the-message-board" />
					</dd>
					<dt>
						[$COMPANY_MX$]
					</dt>
					<dd>
						<liferay-ui:message key="the-company-mx-associated-with-the-message-board" />
					</dd>
					<dt>
						[$COMPANY_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-company-name-associated-with-the-message-board" />
					</dd>
					<dt>
						[$COMMUNITY_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-community-name-associated-with-the-message-board" />
					</dd>
					<dt>
						[$FROM_ADDRESS$]
					</dt>
					<dd>
						<%= HtmlUtil.escape(emailFromAddress) %>
					</dd>
					<dt>
						[$FROM_NAME$]
					</dt>
					<dd>
						<%= HtmlUtil.escape(emailFromName) %>
					</dd>
					<dt>
						[$MAILING_LIST_ADDRESS$]
					</dt>
					<dd>
						<liferay-ui:message key="the-email-address-of-the-mailing-list" />
					</dd>
					<dt>
						[$MESSAGE_BODY$]
					</dt>
					<dd>
						<liferay-ui:message key="the-message-body" />
					</dd>
					<dt>
						[$MESSAGE_ID$]
					</dt>
					<dd>
						<liferay-ui:message key="the-message-id" />
					</dd>
					<dt>
						[$MESSAGE_SUBJECT$]
					</dt>
					<dd>
						<liferay-ui:message key="the-message-subject" />
					</dd>
					<dt>
						[$MESSAGE_USER_ADDRESS$]
					</dt>
					<dd>
						<liferay-ui:message key="the-email-address-of-the-user-who-added-the-message" />
					</dd>
					<dt>
						[$MESSAGE_USER_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-user-who-added-the-message" />
					</dd>
					<dt>
						[$PORTAL_URL$]
					</dt>
					<dd>
						<%= company.getVirtualHost() %>
					</dd>
					<dt>
						[$PORTLET_NAME$]
					</dt>
					<dd>
						<%= PortalUtil.getPortletTitle(renderResponse) %>
					</dd>
					<dt>
						[$TO_ADDRESS$]
					</dt>
					<dd>
						<liferay-ui:message key="the-address-of-the-email-recipient" />
					</dd>
					<dt>
						[$TO_NAME$]
					</dt>
					<dd>
						<liferay-ui:message key="the-name-of-the-email-recipient" />
					</dd>
				</dl>
			</div>
		</c:when>
		<c:when test='<%= tabs2.equals("thread-priorities") %>'>
			<div class="portlet-msg-info">
				<liferay-ui:message key="enter-the-name,-image,-and-priority-level-in-descending-order" />
			</div>

			<br /><br />

			<table class="lfr-table">
			<tr>
				<td>
					<aui:field-wrapper label="default-language">
						<%= defaultLocale.getDisplayName(defaultLocale) %>
					</aui:field-wrapper>
				</td>
				<td>
					<aui:select label="localized-language" name="languageId" onClick='<%= renderResponse.getNamespace() + "updateLanguage();" %>' showEmptyOption="<%= true %>">

						<%
						for (int i = 0; i < locales.length; i++) {
							if (locales[i].equals(defaultLocale)) {
								continue;
							}
						%>

							<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

						<%
						}
						%>

					</aui:select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<br />
				</td>
			</tr>
			<tr>
				<td>
					<table class="lfr-table">
					<tr>
						<td class="lfr-label">
							<liferay-ui:message key="name" />
						</td>
						<td class="lfr-label">
							<liferay-ui:message key="image" />
						</td>
						<td class="lfr-label">
							<liferay-ui:message key="priority" />
						</td>
					</tr>

					<%
					priorities = LocalizationUtil.getPreferencesValues(preferences, "priorities", defaultLanguageId);

					for (int i = 0; i < 10; i++) {
						String name = StringPool.BLANK;
						String image = StringPool.BLANK;
						String value = StringPool.BLANK;

						if (priorities.length > i) {
							String[] priority = StringUtil.split(priorities[i]);

							try {
								name = priority[0];
								image = priority[1];
								value = priority[2];
							}
							catch (Exception e) {
							}

							if (Validator.isNull(name) && Validator.isNull(image)) {
								value = StringPool.BLANK;
							}
						}
					%>

						<tr>
							<td>
								<aui:input label="" name='<%= "priorityName" + i + "_" + defaultLanguageId %>' size="15" value="<%= name %>" />
							</td>
							<td>
								<aui:input label="" name='<%= "priorityImage" + i + "_" + defaultLanguageId %>' size="40" value="<%= image %>" />
							</td>
							<td>
								<aui:input label="" name='<%= "priorityValue" + i + "_" + defaultLanguageId %>' size="4" value="<%= value %>" />
							</td>
						</tr>

					<%
					}
					%>

					</table>
				</td>
				<td>
					<table id="<portlet:namespace />localized-priorities-table" class='<%= (currentLocale.equals(defaultLocale) ? "aui-helper-hidden" : "") + " lfr-table" %>'>
					<tr>
						<td class="lfr-label">
							<liferay-ui:message key="name" />
						</td>
						<td class="lfr-label">
							<liferay-ui:message key="image" />
						</td>
						<td class="lfr-label">
							<liferay-ui:message key="priority" />
						</td>
					</tr>

					<%
					for (int i = 0; i < 10; i++) {
					%>

						<tr>
							<td>
								<aui:input label="" name='<%= "priorityName" + i + "_temp" %>' onChange='<%= renderResponse.getNamespace() + "onChanged();" %>' size="15" />
							</td>
							<td>
								<aui:input label="" name='<%= "priorityImage" + i + "_temp" %>' onChange='<%= renderResponse.getNamespace() + "onChanged();" %>' size="40" />
							</td>
							<td>
								<aui:input label="" name='<%= "priorityValue" + i + "_temp" %>' onChange='<%= renderResponse.getNamespace() + "onChanged();" %>' size="4" />
							</td>
						</tr>

					<%
					}
					%>

					</table>

					<%
					for (int i = 0; i < locales.length; i++) {
						if (locales[i].equals(defaultLocale)) {
							continue;
						}

						String[] tempPriorities = LocalizationUtil.getPreferencesValues(preferences, "priorities", LocaleUtil.toLanguageId(locales[i]));

						for (int j = 0; j < 10; j++) {
							String name = StringPool.BLANK;
							String image = StringPool.BLANK;
							String value = StringPool.BLANK;

							if (tempPriorities.length > j) {
								String[] priority = StringUtil.split(tempPriorities[j]);

								try {
									name = priority[0];
									image = priority[1];
									value = priority[2];
								}
								catch (Exception e) {
								}

								if (Validator.isNull(name) && Validator.isNull(image)) {
									value = StringPool.BLANK;
								}
							}

					%>

							<aui:input name='<%= "priorityName" + j + "_" + LocaleUtil.toLanguageId(locales[i]) %>' type="hidden" value="<%= name %>" />
							<aui:input name='<%= "priorityImage" + j + "_" + LocaleUtil.toLanguageId(locales[i]) %>' type="hidden" value="<%= image %>" />
							<aui:input name='<%= "priorityValue" + j + "_" + LocaleUtil.toLanguageId(locales[i]) %>' type="hidden" value="<%= value %>" />

					<%
						}
					}
					%>

				</td>
			</tr>
			</table>

			<br />

			<aui:script>
				var changed = false;
				var lastLanguageId = "<%= currentLanguageId %>";

				function <portlet:namespace />onChanged() {
					changed = true;
				}

				Liferay.provide(
					window,
					'<portlet:namespace />updateLanguage',
					function() {
						var A = AUI();

						if (lastLanguageId != '<%= defaultLanguageId %>') {
							if (changed) {
								for (var i = 0; i < 10; i++) {
									var priorityName = A.one('#<portlet:namespace />priorityName' + i + '_temp').val();
									var priorityImage = A.one('#<portlet:namespace />priorityImage' + i + '_temp').val();
									var priorityValue = A.one('#<portlet:namespace />priorityValue' + i + '_temp').val();

									A.one('#<portlet:namespace />priorityName' + i + '_' + lastLanguageId).val(priorityName);
									A.one('#<portlet:namespace />priorityImage' + i + '_' + lastLanguageId).val(priorityImage);
									A.one('#<portlet:namespace />priorityValue' + i + '_' + lastLanguageId).val(priorityValue);
								}

								changed = false;
							}
						}

						var selLanguageId = A.one(document.<portlet:namespace />fm.<portlet:namespace />languageId).val();

						var localizedPriorityTable = A.one('#<portlet:namespace />localized-priorities-table');

						if (selLanguageId) {
							<portlet:namespace />updateLanguageTemps(selLanguageId);

							localizedPriorityTable.show();
						}
						else {
							localizedPriorityTable.hide();
						}

						lastLanguageId = selLanguageId;
					},
					['aui-base']
				);

				Liferay.provide(
					window,
					'<portlet:namespace />updateLanguageTemps',
					function(lang) {
						var A = AUI();

						if (lang != '<%= defaultLanguageId %>') {
							for (var i = 0; i < 10; i++) {
								var defaultName = A.one('#<portlet:namespace />priorityName' + i + '_' + '<%= defaultLanguageId %>').val();
								var defaultImage = A.one('#<portlet:namespace />priorityImage' + i + '_' + '<%= defaultLanguageId %>').val();
								var defaultValue = A.one('#<portlet:namespace />priorityValue' + i + '_' + '<%= defaultLanguageId %>').val();

								var priorityName = A.one('#<portlet:namespace />priorityName' + i + '_' + lang).val();
								var priorityImage = A.one('#<portlet:namespace />priorityImage' + i + '_' + lang).val();
								var priorityValue = A.one('#<portlet:namespace />priorityValue' + i + '_' + lang).val();

								var name = priorityName || defaultName;
								var image = priorityImage || defaultImage;
								var value = priorityValue || defaultValue;

								A.one('#<portlet:namespace />priorityName' + i + '_temp').val(name);
								A.one('#<portlet:namespace />priorityImage' + i + '_temp').val(image);
								A.one('#<portlet:namespace />priorityValue' + i + '_temp').val(value);
							}
						}
					},
					['aui-base']
				);

				<portlet:namespace />updateLanguageTemps(lastLanguageId);
			</aui:script>
		</c:when>
		<c:when test='<%= tabs2.equals("user-ranks") %>'>
			<div class="portlet-msg-info">
				<liferay-ui:message key="enter-rank-and-minimum-post-pairs-per-line" />
			</div>

			<aui:fieldset>
				<table class="lfr-table">
				<tr>
					<td class="lfr-label">
						<aui:field-wrapper label="default-language">
							<%= defaultLocale.getDisplayName(defaultLocale) %>
						</aui:field-wrapper>
					</td>
					<td class="lfr-label">
						<aui:select label="localized-language" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateLanguage();" %>' showEmptyOption="<%= true %>">

							<%
							for (int i = 0; i < locales.length; i++) {
								if (locales[i].equals(defaultLocale)) {
									continue;
								}
							%>

								<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= currentLanguageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

							<%
							}
							%>

						</aui:select>
					</td>
				</tr>
				<tr>
					<td>
						<aui:input cssClass="lfr-textarea-container" label="" name='<%= "ranks_" + defaultLanguageId %>' type="textarea" value='<%= StringUtil.merge(LocalizationUtil.getPreferencesValues(preferences, "ranks", defaultLanguageId), StringPool.NEW_LINE) %>' />
					</td>
					<td>

						<%
						for (int i = 0; i < locales.length; i++) {
							if (locales[i].equals(defaultLocale)) {
								continue;
							}
						%>

							<aui:input name='<%= "ranks_" + LocaleUtil.toLanguageId(locales[i]) %>' type="hidden" value='<%= StringUtil.merge(LocalizationUtil.getPreferencesValues(preferences, "ranks", LocaleUtil.toLanguageId(locales[i]), false), StringPool.NEW_LINE) %>' />

						<%
						}
						%>

						<aui:input cssClass="lfr-textarea-container" label="" name="ranks_temp" onChange='<%= renderResponse.getNamespace() + "onRanksChanged();" %>' type="textarea" />
					</td>
				</tr>
				</table>
			</aui:fieldset>

			<aui:script>
				var ranksChanged = false;
				var lastLanguageId = '<%= currentLanguageId %>';

				function <portlet:namespace />onRanksChanged() {
					ranksChanged = true;
				}

				Liferay.provide(
					window,
					'<portlet:namespace />updateLanguage',
					function() {
						var A = AUI();

						if (lastLanguageId != '<%= defaultLanguageId %>') {
							if (ranksChanged) {
								var ranksValue = A.one('#<portlet:namespace />ranks_temp').val();

								if (ranksValue == null) {
									ranksValue = '';
								}

								A.one('#<portlet:namespace />ranks_' + lastLanguageId).val(ranksValue);

								ranksChanged = false;
							}
						}

						var selLanguageId = A.one(document.<portlet:namespace />fm.<portlet:namespace />languageId).val();

						var ranksTemp = A.one('#<portlet:namespace />ranks_temp');

						if (selLanguageId) {
							<portlet:namespace />updateLanguageTemps(selLanguageId);

							ranksTemp.show();
						}
						else {
							ranksTemp.hide();
						}

						lastLanguageId = selLanguageId;
					},
					['aui-base']
				);

				Liferay.provide(
					window,
					'<portlet:namespace />updateLanguageTemps',
					function(lang) {
						var A = AUI();

						if (lang != '<%= defaultLanguageId %>') {
							var ranksValue = A.one('#<portlet:namespace />ranks_' + lang).val();
							var defaultRanksValue = A.one('#<portlet:namespace />ranks_<%= defaultLanguageId %>').val();

							var value = ranksValue || defaultRanksValue;

							A.one('#<portlet:namespace />ranks_temp').val(value);
						}
					},
					['aui-base']
				);

				<portlet:namespace />updateLanguageTemps(lastLanguageId);
			</aui:script>
		</c:when>
		<c:when test='<%= tabs2.equals("rss") %>'>
			<aui:fieldset>
				<aui:select label="maximum-items-to-display" name="rssDelta">
					<aui:option label="1" selected="<%= rssDelta == 1 %>" />
					<aui:option label="2" selected="<%= rssDelta == 2 %>" />
					<aui:option label="3" selected="<%= rssDelta == 3 %>" />
					<aui:option label="4" selected="<%= rssDelta == 4 %>" />
					<aui:option label="5" selected="<%= rssDelta == 5 %>" />
					<aui:option label="10" selected="<%= rssDelta == 10 %>" />
					<aui:option label="15" selected="<%= rssDelta == 15 %>" />
					<aui:option label="20" selected="<%= rssDelta == 20 %>" />
					<aui:option label="25" selected="<%= rssDelta == 25 %>" />
					<aui:option label="30" selected="<%= rssDelta == 30 %>" />
					<aui:option label="40" selected="<%= rssDelta == 40 %>" />
					<aui:option label="50" selected="<%= rssDelta == 50 %>" />
					<aui:option label="60" selected="<%= rssDelta == 60 %>" />
					<aui:option label="70" selected="<%= rssDelta == 70 %>" />
					<aui:option label="80" selected="<%= rssDelta == 80 %>" />
					<aui:option label="90" selected="<%= rssDelta == 90 %>" />
					<aui:option label="100" selected="<%= rssDelta == 100 %>" />
				</aui:select>

				<aui:select label="display-style" name="rssDisplayStyle">
					<aui:option label="full-content" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_FULL_CONTENT) %>" value="<%= RSSUtil.DISPLAY_STYLE_FULL_CONTENT %>" />
					<aui:option label="abstract" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_ABSTRACT) %>" value="<%= RSSUtil.DISPLAY_STYLE_ABSTRACT %>" />
					<aui:option label="title" selected="<%= rssDisplayStyle.equals(RSSUtil.DISPLAY_STYLE_TITLE) %>" value="<%= RSSUtil.DISPLAY_STYLE_TITLE %>" />
				</aui:select>

				<aui:select label="format" name="rssFormat">
					<aui:option label="RSS 1.0" selected='<%= rssFormat.equals("rss10") %>' value="rss10" />
					<aui:option label="RSS 2.0" selected='<%= rssFormat.equals("rss20") %>' value="rss20" />
					<aui:option label="Atom10" selected='<%= rssFormat.equals("atom10") %>' value="atom10" />
				</aui:select>
			</aui:fieldset>
		</c:when>
	</c:choose>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveConfiguration() {
		<c:if test='<%= tabs2.equals("user-ranks") || tabs2.equals("thread-priorities") %>'>
			<portlet:namespace />updateLanguage();
		</c:if>

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>

<%!
public static final String EDITOR_WYSIWYG_IMPL_KEY = "editor.wysiwyg.portal-web.docroot.html.portlet.message_boards.edit_configuration.jsp";
%>