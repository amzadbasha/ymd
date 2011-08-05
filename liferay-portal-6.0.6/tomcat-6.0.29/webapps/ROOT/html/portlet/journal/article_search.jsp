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

<%@ include file="/html/portlet/journal/init.jsp" %>

<%
ArticleSearch searchContainer = (ArticleSearch)request.getAttribute("liferay-ui:search:searchContainer");

ArticleDisplayTerms displayTerms = (ArticleDisplayTerms)searchContainer.getDisplayTerms();
%>

<liferay-ui:search-toggle
	id="toggle_id_journal_article_search"
	displayTerms="<%= displayTerms %>"
	buttonLabel="search"
>
	<aui:fieldset>
		<aui:column>
			<aui:input label="id" name="<%= displayTerms.ARTICLE_ID %>" size="20" value="<%= displayTerms.getArticleId() %>" />

			<aui:input name="<%= displayTerms.CONTENT %>" size="20" type="text" value="<%= displayTerms.getContent() %>" />
		</aui:column>

		<aui:column>
			<aui:input name="<%= displayTerms.VERSION %>" size="20" type="text" value="<%= displayTerms.getVersionString() %>" />

			<aui:select name="<%= displayTerms.TYPE %>">
				<aui:option value=""></aui:option>

				<%
				for (int i = 0; i < JournalArticleConstants.TYPES.length; i++) {
				%>

					<aui:option label="<%= JournalArticleConstants.TYPES[i] %>" selected="<%= displayTerms.getType().equals(JournalArticleConstants.TYPES[i]) %>" />

				<%
				}
				%>

			</aui:select>
		</aui:column>

		<aui:column>
			<aui:input label="name" name="<%= displayTerms.TITLE %>" size="20" type="text" value="<%= displayTerms.getTitle() %>" />

			<c:choose>
				<c:when test="<%= portletName.equals(PortletKeys.JOURNAL) %>">
					<aui:select name="<%= displayTerms.STATUS %>">
						<aui:option value=""></aui:option>
						<aui:option label="draft" selected='<%= displayTerms.getStatus().equals("draft") %>' />
						<aui:option label="pending" selected='<%= displayTerms.getStatus().equals("pending") %>' />
						<aui:option label="approved" selected='<%= displayTerms.getStatus().equals("approved") %>' />
						<aui:option label="expired" selected='<%= displayTerms.getStatus().equals("expired") %>' />
					</aui:select>
				</c:when>
				<c:otherwise>

					<%
					List<Group> myPlaces = user.getMyPlaces();
					%>

					<aui:select label="my-places" name="<%= displayTerms.GROUP_ID %>">
						<aui:option label="global" selected="<%= displayTerms.getGroupId() == themeDisplay.getCompanyGroupId() %>" value="<%= themeDisplay.getCompanyGroupId() %>" />

						<%
						for (Group myPlace : myPlaces) {
							if (myPlace.hasStagingGroup()) {
								myPlace = myPlace.getStagingGroup();
							}
						%>

							<aui:option label='<%= myPlace.isUser() ? "my-community" : HtmlUtil.escape(myPlace.getDescriptiveName()) %>' selected="<%= displayTerms.getGroupId() == myPlace.getGroupId() %>" value="<%= myPlace.getGroupId() %>" />

						<%
						}
						%>

						<c:if test="<%= layout.hasScopeGroup() %>">
							<aui:option label='<%= LanguageUtil.get(pageContext,"current-page") + " (" + HtmlUtil.escape(layout.getName(locale)) + ")" %>' selected="<%= displayTerms.getGroupId() == layout.getScopeGroup().getGroupId() %>" value="<%= layout.getScopeGroup().getGroupId() %>" />
						</c:if>
					</aui:select>
				</c:otherwise>
			</c:choose>
		</aui:column>

		<aui:column>
			<aui:input name="<%= displayTerms.DESCRIPTION %>" size="20" type="text" value="<%= displayTerms.getDescription() %>" />
		</aui:column>
	</aui:fieldset>
</liferay-ui:search-toggle>

<%
boolean showAddArticleButtonButton = false;
boolean showPermissionsButton = false;
boolean showSubscribeLink = false;

if (portletName.equals(PortletKeys.JOURNAL)) {
	showAddArticleButtonButton = JournalPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE);
	showPermissionsButton = GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);
	showSubscribeLink = JournalPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE);
}
%>

<c:if test="<%= showAddArticleButtonButton || showPermissionsButton %>">
	<aui:button-row cssClass="add-permission-button-row">
		<c:if test="<%= showAddArticleButtonButton %>">
			<aui:button onClick='<%= renderResponse.getNamespace() + "addArticle();" %>' value="add-web-content" />
		</c:if>

		<c:if test="<%= showPermissionsButton %>">
			<liferay-security:permissionsURL
				modelResource="com.liferay.portlet.journal"
				modelResourceDescription="<%= HtmlUtil.escape(themeDisplay.getScopeGroupName()) %>"
				resourcePrimKey="<%= String.valueOf(scopeGroupId) %>"
				var="permissionsURL"
			/>

			<aui:button onClick="<%= permissionsURL %>" value="permissions" />
		</c:if>
	</aui:button-row>
</c:if>

<c:if test="<%= showSubscribeLink %>">
	<c:choose>
		<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(company.getCompanyId(), user.getUserId(), JournalArticle.class.getName(), scopeGroupId) %>">
			<portlet:actionURL var="unsubscribeURL">
				<portlet:param name="struts_action" value="/journal/edit_article" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNSUBSCRIBE %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:actionURL>

			<liferay-ui:icon cssClass="subscribe-link" image="unsubscribe" label="<%= true %>" url="<%= unsubscribeURL %>" />
		</c:when>
		<c:otherwise>
			<portlet:actionURL var="subscribeURL">
				<portlet:param name="struts_action" value="/journal/edit_article" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:actionURL>

			<liferay-ui:icon cssClass="subscribe-link" image="subscribe" label="<%= true %>" url="<%= subscribeURL %>" />
		</c:otherwise>
	</c:choose>
</c:if>

<c:if test="<%= Validator.isNotNull(displayTerms.getStructureId()) %>">
	<aui:input name="<%= displayTerms.STRUCTURE_ID %>" type="hidden" value="<%= displayTerms.getStructureId() %>" />

	<div class="portlet-msg-info">
		<liferay-ui:message key="filter-by-structure" />: <%= displayTerms.getStructureId() %><br />
	</div>
</c:if>

<c:if test="<%= Validator.isNotNull(displayTerms.getTemplateId()) %>">
	<aui:input name="<%= displayTerms.TEMPLATE_ID %>" type="hidden" value="<%= displayTerms.getTemplateId() %>" />

	<div class="portlet-msg-info">
		<liferay-ui:message key="filter-by-template" />: <%= displayTerms.getTemplateId() %><br />
	</div>
</c:if>

<aui:script>
	function <portlet:namespace />addArticle() {
		var url = '<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" portletName="<%= PortletKeys.JOURNAL %>"><portlet:param name="struts_action" value="/journal/edit_article" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="structureId" value="<%= displayTerms.getStructureId() %>" /><portlet:param name="templateId" value="<%= displayTerms.getTemplateId() %>" /></liferay-portlet:renderURL>';

		if (toggle_id_journal_article_searchcurClickValue == 'basic') {
			url += '&<portlet:namespace /><%= displayTerms.TITLE %>=' + document.<portlet:namespace />fm.<portlet:namespace /><%= displayTerms.KEYWORDS %>.value;

			submitForm(document.hrefFm, url);
		}
		else {
			document.<portlet:namespace />fm.method = 'post';
			submitForm(document.<portlet:namespace />fm, url);
		}
	}

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= displayTerms.ARTICLE_ID %>);
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= displayTerms.KEYWORDS %>);
	</c:if>
</aui:script>