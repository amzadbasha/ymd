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

<%@ include file="/html/portlet/journal_content/init.jsp" %>

<%
int cur = ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM);

String redirect = ParamUtil.getString(request, "redirect");

JournalArticle article = null;

String type = StringPool.BLANK;

try {
	if (Validator.isNotNull(articleId)) {
		article = JournalArticleLocalServiceUtil.getLatestArticle(groupId, articleId);

		groupId = article.getGroupId();
		type = article.getType();
	}
}
catch (NoSuchArticleException nsae) {
}

groupId = ParamUtil.getLong(request, "groupId", groupId);
type = ParamUtil.getString(request, "type", type);
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />
<liferay-portlet:renderURL portletConfiguration="true" varImpl="portletURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm1">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value='<%= portletURL.toString() + StringPool.AMPERSAND + renderResponse.getNamespace() + "cur=" + cur %>' />

	<liferay-ui:error exception="<%= NoSuchArticleException.class %>" message="the-web-content-could-not-be-found" />

	<div class="portlet-msg-info">
		<span class="displaying-help-message-holder <%= article == null ? StringPool.BLANK : "aui-helper-hidden" %>">
			<liferay-ui:message key="please-select-a-web-content-from-the-list-below" />
		</span>

		<span class="displaying-article-id-holder <%= article == null ? "aui-helper-hidden" : StringPool.BLANK %>">
			<liferay-ui:message key="displaying-content" />: <span class="displaying-article-id"><%= article != null ? HtmlUtil.escape(article.getTitle()) : StringPool.BLANK %></span>
		</span>
	</div>

	<c:if test="<%= article != null %>">

		<%
		String structureId = article.getStructureId();

		if (Validator.isNotNull(structureId)) {
			List templates = JournalTemplateLocalServiceUtil.getStructureTemplates(groupId, structureId);

			if (!templates.isEmpty()) {
				if (Validator.isNull(templateId)) {
					templateId = article.getTemplateId();
				}
		%>

				<aui:fieldset>
					<liferay-ui:message key="override-default-template" />

					<liferay-ui:table-iterator
						list="<%= templates %>"
						listType="com.liferay.portlet.journal.model.JournalTemplate"
						rowLength="3"
						rowPadding="30"
					>

						<%
						boolean templateChecked = false;

						if (templateId.equals(tableIteratorObj.getTemplateId())) {
							templateChecked = true;
						}

						if ((tableIteratorPos.intValue() == 0) && Validator.isNull(templateId)) {
							templateChecked = true;
						}
						%>

						<liferay-portlet:renderURL portletName="<%= PortletKeys.JOURNAL %>" var="editTemplateURL">
							<portlet:param name="struts_action" value="/journal/edit_template" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="groupId" value="<%= String.valueOf(tableIteratorObj.getGroupId()) %>" />
							<portlet:param name="templateId" value="<%= tableIteratorObj.getTemplateId() %>" />
						</liferay-portlet:renderURL>

						<liferay-util:buffer var="linkContent">
							<aui:a href="<%= editTemplateURL %>" id="tableIteratorObjName"><%= tableIteratorObj.getName() %></aui:a>
						</liferay-util:buffer>

						<aui:input checked="<%= templateChecked %>" inlineLabel="right" name="overideTemplateId" label="<%= linkContent %>" onChange='<%= "if (this.checked) {document." + renderResponse.getNamespace() + "fm." + renderResponse.getNamespace() + "templateId.value = this.value;}" %>' type="radio" value="<%= tableIteratorObj.getTemplateId() %>" />

						<c:if test="<%= tableIteratorObj.isSmallImage() %>">
							<br />

							<img border="0" hspace="0" src="<%= Validator.isNotNull(tableIteratorObj.getSmallImageURL()) ? tableIteratorObj.getSmallImageURL() : themeDisplay.getPathImage() + "/journal/template?img_id=" + tableIteratorObj.getSmallImageId() + "&t=" + ImageServletTokenUtil.getToken(tableIteratorObj.getSmallImageId()) %>" vspace="0" />
						</c:if>
					</liferay-ui:table-iterator>

					<br />
				</aui:fieldset>

		<%
			}
		}
		%>

	</c:if>

	<%
	DynamicRenderRequest dynamicRenderRequest = new DynamicRenderRequest(renderRequest);

	dynamicRenderRequest.setParameter("type", type);
	dynamicRenderRequest.setParameter("groupId", String.valueOf(groupId));

	ArticleSearch searchContainer = new ArticleSearch(dynamicRenderRequest, portletURL);
	%>

	<liferay-ui:search-form
		page="/html/portlet/journal/article_search.jsp"
		searchContainer="<%= searchContainer %>"
	>
		<liferay-ui:param name="groupId" value="<%= String.valueOf(groupId) %>" />
		<liferay-ui:param name="type" value="<%= type %>" />
	</liferay-ui:search-form>

	<br />

	<%
	OrderByComparator orderByComparator = JournalUtil.getArticleOrderByComparator(searchContainer.getOrderByCol(), searchContainer.getOrderByType());

	ArticleSearchTerms searchTerms = (ArticleSearchTerms)searchContainer.getSearchTerms();

	searchTerms.setVersion(-1);
	%>

	<%@ include file="/html/portlet/journal/article_search_results.jspf" %>

	<%
	List resultRows = searchContainer.getResultRows();

	for (int i = 0; i < results.size(); i++) {
		JournalArticle curArticle = (JournalArticle)results.get(i);

		curArticle = curArticle.toEscapedModel();

		ResultRow row = new ResultRow(null, curArticle.getArticleId() + EditArticleAction.VERSION_SEPARATOR + curArticle.getVersion(), i);

		StringBundler sb = new StringBundler(7);

		sb.append("javascript:");
		sb.append(renderResponse.getNamespace());
		sb.append("selectArticle('");
		sb.append(curArticle.getArticleId());
		sb.append("','");
		sb.append(curArticle.getTitle());
		sb.append("');");

		String rowHREF = sb.toString();

		// Article id

		row.addText(curArticle.getArticleId(), rowHREF);

		// Title

		row.addText(curArticle.getTitle(), rowHREF);

		// Version

		row.addText(String.valueOf(curArticle.getVersion()), rowHREF);

		// Modified date

		row.addText(dateFormatDate.format(curArticle.getModifiedDate()), rowHREF);

		// Display date

		row.addText(dateFormatDate.format(curArticle.getDisplayDate()), rowHREF);

		// Author

		row.addText(HtmlUtil.escape(PortalUtil.getUserName(curArticle.getUserId(), curArticle.getUserName())), rowHREF);

		// Add result row

		resultRows.add(row);
	}
	%>

	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
</aui:form>

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value='<%= portletURL.toString() + StringPool.AMPERSAND + renderResponse.getNamespace() + "cur" + cur %>' />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
	<aui:input name="articleId" type="hidden" value="<%= articleId %>" />
	<aui:input name="templateId" type="hidden" value="<%= templateId %>" />

	<aui:fieldset cssClass="aui-helper-hidden">
		<aui:field-wrapper label="portlet-id">
			<%= portletResource %>
		</aui:field-wrapper>
	</aui:fieldset>

	<br />

	<aui:fieldset>
		<aui:input inlineLabel="left" name="showAvailableLocales" type="checkbox" value="<%= showAvailableLocales %>" />

		<aui:field-wrapper helpMessage='<%= !openOfficeServerEnabled ? "enabling-openoffice-integration-provides-document-conversion-functionality" : StringPool.BLANK %>' label="enable-conversion-to">

			<%
			for (String conversion : conversions) {
			%>

				<aui:field-wrapper inlineField="<%= true %>" inlineLabel="left" label="<%= conversion.toUpperCase() %>">
					<input <%= ArrayUtil.contains(extensions, conversion) ? "checked": "" %> <%= !openOfficeServerEnabled ? "disabled" : "" %> name="<portlet:namespace />extensions" type="checkbox" value="<%= conversion %>" />
				</aui:field-wrapper>

			<%
			}
			%>

		</aui:field-wrapper>

		<aui:input inlineLabel="left" name="enablePrint" type="checkbox" value="<%= enablePrint %>" />

		<aui:input inlineLabel="left" name="enableRatings" type="checkbox" value="<%= enableRatings %>" />

		<c:if test="<%= PropsValues.JOURNAL_ARTICLE_COMMENTS_ENABLED %>">
			<aui:input inlineLabel="left" name="enableComments" type="checkbox" value="<%= enableComments %>" />

			<aui:input inlineLabel="left" name="enableCommentRatings" type="checkbox" value="<%= enableCommentRatings %>" />
		</c:if>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />selectArticle',
		function(articleId, articletTitle) {
			var A = AUI();

			document.<portlet:namespace />fm.<portlet:namespace />articleId.value = articleId;
			document.<portlet:namespace />fm.<portlet:namespace />templateId.value = "";

			A.one('.displaying-article-id-holder').show();
			A.one('.displaying-help-message-holder').hide();

			var displayArticleId = A.one('.displaying-article-id');

			displayArticleId.set('innerHTML', articletTitle + ' (<%= LanguageUtil.get(pageContext, "modified") %>)');
			displayArticleId.addClass('modified');
		},
		['aui-base']
	);
</aui:script>