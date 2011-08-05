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
String tabs1 = ParamUtil.getString(request, "tabs1", "web-content");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/journal/view");
portletURL.setParameter("tabs1", tabs1);
%>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />

	<liferay-ui:tabs
		names="web-content,structures,templates,feeds,recent"
		url="<%= portletURL.toString() %>"
	/>

	<c:choose>
		<c:when test='<%= tabs1.equals("web-content") %>'>
			<aui:input name="groupId" type="hidden" />
			<aui:input name="deleteArticleIds" type="hidden" />
			<aui:input name="expireArticleIds" type="hidden" />

			<%
			ArticleSearch searchContainer = new ArticleSearch(renderRequest, portletURL);

			List headerNames = searchContainer.getHeaderNames();

			headerNames.add(3, "status");
			headerNames.add(StringPool.BLANK);

			searchContainer.setRowChecker(new RowChecker(renderResponse));
			%>

			<liferay-ui:search-form
				page="/html/portlet/journal/article_search.jsp"
				searchContainer="<%= searchContainer %>"
			/>

			<%
			ArticleSearchTerms searchTerms = (ArticleSearchTerms)searchContainer.getSearchTerms();

			searchTerms.setVersion(-1);
			%>

			<%@ include file="/html/portlet/journal/article_search_results.jspf" %>

			<div class="separator article-separator"><!-- --></div>

			<c:if test="<%= !results.isEmpty() %>">
				<aui:button-row>
					<aui:button onClick='<%= renderResponse.getNamespace() + "expireArticles();" %>' value="expire" />

					<aui:button onClick='<%= renderResponse.getNamespace() + "deleteArticles();" %>' value="delete" />
				</aui:button-row>

				<br /><br />
			</c:if>

			<%
			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				JournalArticle article = (JournalArticle)results.get(i);

				article = article.toEscapedModel();

				ResultRow row = new ResultRow(article, article.getArticleId() + EditArticleAction.VERSION_SEPARATOR + article.getVersion(), i);

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("struts_action", "/journal/edit_article");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("groupId", String.valueOf(article.getGroupId()));
				rowURL.setParameter("articleId", article.getArticleId());
				rowURL.setParameter("version", String.valueOf(article.getVersion()));

				// Article id

				row.addText(article.getArticleId(), rowURL);

				// Title

				row.addText(article.getTitle(), rowURL);

				// Version

				row.addText(String.valueOf(article.getVersion()), rowURL);

				// Status

				row.addText(LanguageUtil.get(pageContext, WorkflowConstants.toLabel(article.getStatus())), rowURL);

				// Modified date

				row.addText(dateFormatDateTime.format(article.getModifiedDate()), rowURL);

				// Display date

				row.addText(dateFormatDateTime.format(article.getDisplayDate()), rowURL);

				// Author

				row.addText(HtmlUtil.escape(PortalUtil.getUserName(article.getUserId(), article.getUserName())), rowURL);

				// Action

				row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/journal/article_action.jsp");

				// Add result row

				resultRows.add(row);
			}
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("structures") %>'>
			<aui:input name="groupId" type="hidden" />
			<aui:input name="deleteStructureIds" type="hidden" />

			<liferay-ui:error exception="<%= RequiredStructureException.class %>" message="required-structures-could-not-be-deleted" />

			<%
			StructureSearch searchContainer = new StructureSearch(renderRequest, portletURL);

			List headerNames = searchContainer.getHeaderNames();

			headerNames.add(StringPool.BLANK);

			searchContainer.setRowChecker(new RowChecker(renderResponse));
			%>

			<liferay-ui:search-form
				page="/html/portlet/journal/structure_search.jsp"
				searchContainer="<%= searchContainer %>"
			/>

			<%
			StructureSearchTerms searchTerms = (StructureSearchTerms)searchContainer.getSearchTerms();
			%>

			<%@ include file="/html/portlet/journal/structure_search_results.jspf" %>

			<div class="separator"><!-- --></div>

			<aui:button onClick='<%= renderResponse.getNamespace() + "deleteStructures();" %>' value="delete" />

			<br /><br />

			<%
			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				JournalStructure structure = (JournalStructure)results.get(i);

				structure = structure.toEscapedModel();

				ResultRow row = new ResultRow(structure, structure.getStructureId(), i);

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("struts_action", "/journal/edit_structure");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("groupId", String.valueOf(structure.getGroupId()));
				rowURL.setParameter("structureId", structure.getStructureId());

				// Structure id

				row.addText(structure.getStructureId(), rowURL);

				// Name and description

				if (Validator.isNotNull(structure.getDescription())) {
					row.addText(structure.getName().concat("<br />").concat(structure.getDescription()), rowURL);
				}
				else {
					row.addText(structure.getName(), rowURL);
				}

				// Action

				row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/journal/structure_action.jsp");

				// Add result row

				resultRows.add(row);
			}
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("templates") %>'>
			<aui:input name="groupId" type="hidden" />
			<aui:input name="deleteTemplateIds" type="hidden" />

			<liferay-ui:error exception="<%= RequiredTemplateException.class %>" message="required-templates-could-not-be-deleted" />

			<%
			TemplateSearch searchContainer = new TemplateSearch(renderRequest, portletURL);

			List headerNames = searchContainer.getHeaderNames();

			headerNames.add(StringPool.BLANK);

			searchContainer.setRowChecker(new RowChecker(renderResponse));
			%>

			<liferay-ui:search-form
				page="/html/portlet/journal/template_search.jsp"
				searchContainer="<%= searchContainer %>"
			/>

			<%
			TemplateSearchTerms searchTerms = (TemplateSearchTerms)searchContainer.getSearchTerms();

			searchTerms.setStructureIdComparator(StringPool.EQUAL);
			%>

			<%@ include file="/html/portlet/journal/template_search_results.jspf" %>

			<div class="separator"><!-- --></div>

			<aui:button onClick='<%= renderResponse.getNamespace() + "deleteTemplates();" %>' value="delete" />

			<br /><br />

			<%
			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				JournalTemplate template = (JournalTemplate)results.get(i);

				template = template.toEscapedModel();

				ResultRow row = new ResultRow(template, template.getTemplateId(), i);

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("struts_action", "/journal/edit_template");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("groupId", String.valueOf(template.getGroupId()));
				rowURL.setParameter("templateId", template.getTemplateId());

				row.setParameter("rowHREF", rowURL.toString());

				// Template id

				row.addText(template.getTemplateId(), rowURL);

				// Name, description, and image

				row.addJSP("/html/portlet/journal/template_description.jsp");

				// Action

				row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/journal/template_action.jsp");

				// Add result row

				resultRows.add(row);
			}
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("feeds") %>'>
			<aui:input name="groupId" type="hidden" />
			<aui:input name="deleteFeedIds" type="hidden" />

			<%
			FeedSearch searchContainer = new FeedSearch(renderRequest, portletURL);

			List headerNames = searchContainer.getHeaderNames();

			headerNames.add(StringPool.BLANK);

			searchContainer.setRowChecker(new RowChecker(renderResponse));
			%>

			<liferay-ui:search-form
				page="/html/portlet/journal/feed_search.jsp"
				searchContainer="<%= searchContainer %>"
			/>

			<%
			FeedSearchTerms searchTerms = (FeedSearchTerms)searchContainer.getSearchTerms();
			%>

			<%@ include file="/html/portlet/journal/feed_search_results.jspf" %>

			<div class="separator"><!-- --></div>

			<aui:button onClick='<%= renderResponse.getNamespace() + "deleteFeeds();" %>' value="delete" />

			<br /><br />

			<%
			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				JournalFeed feed = (JournalFeed)results.get(i);

				feed = feed.toEscapedModel();

				ResultRow row = new ResultRow(feed, feed.getFeedId(), i);

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("struts_action", "/journal/edit_feed");
				rowURL.setParameter("redirect", currentURL);
				rowURL.setParameter("groupId", String.valueOf(feed.getGroupId()));
				rowURL.setParameter("feedId", feed.getFeedId());

				row.setParameter("rowHREF", rowURL.toString());

				// Feed id

				row.addText(feed.getFeedId(), rowURL);

				// Name and description

				if (Validator.isNotNull(feed.getDescription())) {
					row.addText(feed.getName().concat("<br />").concat(feed.getDescription()), rowURL);
				}
				else {
					row.addText(feed.getName(), rowURL);
				}

				// Action

				row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/journal/feed_action.jsp");

				// Add result row

				resultRows.add(row);
			}
			%>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("recent") %>'>
			<%= LanguageUtil.format(pageContext, "this-page-displays-the-last-x-web-content,-structures,-and-templates-that-you-accessed", String.valueOf(JournalUtil.MAX_STACK_SIZE), false) %>

			<br /><br />

			<table class="lfr-table" width="100%">
			<tr>
				<td class="lfr-top" width="33%">
					<table border="0" cellpadding="4" cellspacing="0" width="100%">
					<tr class="portlet-section-header results-header" style="font-size: x-small; font-weight: bold;">
						<td colspan="2">
							<%= LanguageUtil.format(pageContext, "last-x-web-content", String.valueOf(JournalUtil.MAX_STACK_SIZE), false) %>
						</td>
					</tr>

					<%
					Stack recentArticles = JournalUtil.getRecentArticles(renderRequest);

					int recentArticlesSize = recentArticles.size();

					for (int i = recentArticlesSize - 1; i >= 0; i--) {
						JournalArticle article = (JournalArticle)recentArticles.get(i);

						article = article.toEscapedModel();

						String className = "portlet-section-body results-row";
						String classHoverName = "portlet-section-body-hover results-row hover";

						if (MathUtil.isEven(i)) {
							className = "portlet-section-alternate results-row alt";
							classHoverName = "portlet-section-alternate-hover results-row alt hover";
						}
					%>

						<portlet:renderURL var="editArticleURL">
							<portlet:param name="struts_action" value="/journal/edit_article" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="groupId" value="<%= String.valueOf(article.getGroupId()) %>" />
							<portlet:param name="articleId" value="<%= article.getArticleId() %>" />
							<portlet:param name="version" value="<%= String.valueOf(article.getVersion()) %>" />
						</portlet:renderURL>

						<tr class="<%= className %>" style="font-size: x-small;" onMouseEnter="this.className = '<%= classHoverName %>';" onMouseLeave="this.className = '<%= className %>';">
							<td>
								<aui:a href="<%= editArticleURL %>"><%= article.getArticleId() %></aui:a>
							</td>
							<td>
								<aui:a href="<%= editArticleURL %>"><%= article.getTitle() %></aui:a>
							</td>
						</tr>

					<%
					}
					%>

					</table>
				</td>
				<td class="lfr-top" width="33%">
					<table border="0" cellpadding="4" cellspacing="0" width="100%">
					<tr class="portlet-section-header results-header" style="font-size: x-small; font-weight: bold;">
						<td colspan="2">
							<%= LanguageUtil.format(pageContext, "last-x-structures", String.valueOf(JournalUtil.MAX_STACK_SIZE), false) %>
						</td>
					</tr>

					<%
					Stack recentStructures = JournalUtil.getRecentStructures(renderRequest);

					int recentStructuresSize = recentStructures.size();

					for (int i = recentStructuresSize - 1; i >= 0; i--) {
						JournalStructure structure = (JournalStructure)recentStructures.get(i);

						structure = structure.toEscapedModel();

						String className = "portlet-section-body results-row";
						String classHoverName = "portlet-section-body-hover results-row hover";

						if (MathUtil.isEven(i)) {
							className = "portlet-section-alternate results-row alt";
							classHoverName = "portlet-section-alternate-hover results-row alt hover";
						}
					%>

						<portlet:renderURL var="editStructureURL">
							<portlet:param name="struts_action" value="/journal/edit_structure" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="groupId" value="<%= String.valueOf(structure.getGroupId()) %>" />
							<portlet:param name="structureId" value="<%= structure.getStructureId() %>" />
						</portlet:renderURL>

						<tr class="<%= className %>" style="font-size: x-small;" onMouseEnter="this.className = '<%= classHoverName %>';" onMouseLeave="this.className = '<%= className %>';">
							<td>
								<aui:a href="<%= editStructureURL %>"><%= structure.getId() %></aui:a>
							</td>
							<td>
								<aui:a href="<%= editStructureURL %>"><%= structure.getName() %></aui:a>
							</td>
						</tr>

					<%
					}
					%>

					</table>
				</td>
				<td class="lfr-top" width="33%">
					<table border="0" cellpadding="4" cellspacing="0" width="100%">
					<tr class="portlet-section-header results-header" style="font-size: x-small; font-weight: bold;">
						<td colspan="2">
							<%= LanguageUtil.format(pageContext, "last-x-templates", String.valueOf(JournalUtil.MAX_STACK_SIZE), false) %>
						</td>
					</tr>

					<%
					Stack recentTemplates = JournalUtil.getRecentTemplates(renderRequest);

					int recentTemplatesSize = recentTemplates.size();

					for (int i = recentTemplatesSize - 1; i >= 0; i--) {
						JournalTemplate template = (JournalTemplate)recentTemplates.get(i);

						template = template.toEscapedModel();

						String className = "portlet-section-body results-row";
						String classHoverName = "portlet-section-body-hover results-row hover";

						if (MathUtil.isEven(recentTemplatesSize - i - 1)) {
							className = "portlet-section-alternate results-row alt";
							classHoverName = "portlet-section-alternate-hover results-row alt hover";
						}
					%>

						<portlet:renderURL var="editTemplateURL">
							<portlet:param name="struts_action" value="/journal/edit_template" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="groupId" value="<%= String.valueOf(template.getGroupId()) %>" />
							<portlet:param name="templateId" value="<%= template.getTemplateId() %>" />
						</portlet:renderURL>

						<tr class="<%= className %>" style="font-size: x-small;" onMouseEnter="this.className = '<%= classHoverName %>';" onMouseLeave="this.className = '<%= className %>';">
							<td>
								<aui:a href="<%= editTemplateURL %>"><%= template.getId() %></aui:a>
							</td>
							<td>
								<aui:a href="<%= editTemplateURL %>"><%= template.getName() %></aui:a>
							</td>
						</tr>

					<%
					}
					%>

					</table>
				</td>
			</tr>
			</table>
		</c:when>
	</c:choose>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />deleteArticles',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-web-content") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
				document.<portlet:namespace />fm.<portlet:namespace />groupId.value = "<%= scopeGroupId %>";
				document.<portlet:namespace />fm.<portlet:namespace />deleteArticleIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/journal/edit_article" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />deleteFeeds',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-feeds") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
				document.<portlet:namespace />fm.<portlet:namespace />groupId.value = "<%= scopeGroupId %>";
				document.<portlet:namespace />fm.<portlet:namespace />deleteFeedIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/journal/edit_feed" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />deleteStructures',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-structures") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
				document.<portlet:namespace />fm.<portlet:namespace />groupId.value = "<%= scopeGroupId %>";
				document.<portlet:namespace />fm.<portlet:namespace />deleteStructureIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/journal/edit_structure" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />deleteTemplates',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-templates") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.DELETE %>";
				document.<portlet:namespace />fm.<portlet:namespace />groupId.value = "<%= scopeGroupId %>";
				document.<portlet:namespace />fm.<portlet:namespace />deleteTemplateIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/journal/edit_template" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />expireArticles',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-expire-the-selected-web-content") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= Constants.EXPIRE %>";
				document.<portlet:namespace />fm.<portlet:namespace />groupId.value = "<%= scopeGroupId %>";
				document.<portlet:namespace />fm.<portlet:namespace />expireArticleIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL><portlet:param name="struts_action" value="/journal/edit_article" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>