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
JournalArticleDisplay articleDisplay = (JournalArticleDisplay)request.getAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY);

boolean print = ParamUtil.getString(request, "viewMode").equals(Constants.PRINT);

if (articleDisplay != null) {
	AssetEntryServiceUtil.incrementViewCounter(JournalArticle.class.getName(), articleDisplay.getResourcePrimKey());
}
%>

<c:choose>
	<c:when test="<%= themeDisplay.isStateExclusive() %>">

		<%
		RuntimeLogic portletLogic = new PortletLogic(application, request, response, renderRequest, renderResponse);
		RuntimeLogic actionURLLogic = new ActionURLLogic(renderResponse);
		RuntimeLogic renderURLLogic = new RenderURLLogic(renderResponse);

		String content = articleDisplay.getContent();

		content = RuntimePortletUtil.processXML(request, content, portletLogic);
		content = RuntimePortletUtil.processXML(request, content, actionURLLogic);
		content = RuntimePortletUtil.processXML(request, content, renderURLLogic);
		%>

		<%= content %>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="<%= articleDisplay != null %>">

				<%
				RuntimeLogic portletLogic = new PortletLogic(application, request, response, renderRequest, renderResponse);
				RuntimeLogic actionURLLogic = new ActionURLLogic(renderResponse);
				RuntimeLogic renderURLLogic = new RenderURLLogic(renderResponse);

				String content = articleDisplay.getContent();

				content = RuntimePortletUtil.processXML(request, content, portletLogic);
				content = RuntimePortletUtil.processXML(request, content, actionURLLogic);
				content = RuntimePortletUtil.processXML(request, content, renderURLLogic);

				PortletURL portletURL = renderResponse.createRenderURL();
				%>

				<c:if test="<%= enableConversions || enablePrint || (showAvailableLocales && (articleDisplay.getAvailableLocales().length > 1)) %>">
					<div class="user-actions">
						<c:if test="<%= enablePrint %>">
							<c:choose>
								<c:when test="<%= print %>">
									<div class="print-action">
										<liferay-ui:icon
											image="print"
											label="<%= true %>"
											message='<%= LanguageUtil.format(pageContext, "print-x-x", new Object[] {"aui-helper-hidden-accessible", articleDisplay.getTitle()}) %>'
											url="javascript:print();"
										/>
									</div>

									<aui:script>
										print();
									</aui:script>
								</c:when>
								<c:otherwise>

									<%
									PortletURL printPageURL = renderResponse.createRenderURL();

									printPageURL.setWindowState(LiferayWindowState.POP_UP);

									printPageURL.setParameter("struts_action", "/journal_content/view");
									printPageURL.setParameter("groupId", String.valueOf(articleDisplay.getGroupId()));
									printPageURL.setParameter("articleId", articleDisplay.getArticleId());
									printPageURL.setParameter("viewMode", Constants.PRINT);
									%>

									<div class="print-action">
										<liferay-ui:icon
											image="print"
											label="<%= true %>"
											message='<%= LanguageUtil.format(pageContext, "print-x-x", new Object[] {"aui-helper-hidden-accessible", articleDisplay.getTitle()}) %>'
											url='<%= "javascript:" + renderResponse.getNamespace() + "printPage();" %>'
										/>
									</div>

									<aui:script>
										function <portlet:namespace />printPage() {
											window.open('<%= printPageURL %>', '', "directories=0,height=480,left=80,location=1,menubar=1,resizable=1,scrollbars=yes,status=0,toolbar=0,top=180,width=640");
										}
									</aui:script>
								</c:otherwise>
							</c:choose>
						</c:if>

						<c:if test="<%= enableConversions && !print %>">

							<%
							PortletURL exportArticleURL = renderResponse.createActionURL();

							exportArticleURL.setWindowState(LiferayWindowState.EXCLUSIVE);

							exportArticleURL.setParameter("struts_action", "/journal_content/export_article");
							exportArticleURL.setParameter("groupId", String.valueOf(articleDisplay.getGroupId()));
							exportArticleURL.setParameter("articleId", articleDisplay.getArticleId());
							%>

							<div class="export-actions">
								<liferay-ui:icon-list>

									<%
									for (String extension : extensions) {
										exportArticleURL.setParameter("targetExtension", extension);
									%>

										<liferay-ui:icon
											image='<%= "../file_system/small/" + extension %>'
											label="<%= true %>"
											message='<%= LanguageUtil.format(pageContext, "x-convert-x-to-x", new Object[] {"aui-helper-hidden-accessible", articleDisplay.getTitle(), extension.toUpperCase()}) %>'
											url="<%= exportArticleURL.toString() %>"
											method="get"
										/>

									<%
									}
									%>

								</liferay-ui:icon-list>
							</div>
						</c:if>

						<c:if test="<%= showAvailableLocales && !print %>">

							<%
							String[] availableLocales = articleDisplay.getAvailableLocales();
							%>

							<c:if test="<%= availableLocales.length > 1 %>">
								<c:if test="<%= enableConversions || enablePrint %>">
									<div class="locale-separator"> </div>
								</c:if>

								<div class="locale-actions">
									<liferay-ui:language languageIds="<%= availableLocales %>" displayStyle="<%= 0 %>" />
								</div>
							</c:if>
						</c:if>
					</div>
				</c:if>

				<div class="journal-content-article" id="article_<%= articleDisplay.getCompanyId() %>_<%= articleDisplay.getGroupId() %>_<%= articleDisplay.getArticleId() %>_<%= articleDisplay.getVersion() %>">
					<%= content %>
				</div>

				<c:if test="<%= articleDisplay.isPaginate() %>">
					<liferay-ui:page-iterator
						cur="<%= articleDisplay.getCurrentPage() %>"
						curParam='<%= "page" %>'
						delta="<%= 1 %>"
						maxPages="<%= 25 %>"
						total="<%= articleDisplay.getNumberOfPages() %>"
						type="article"
						url="<%= portletURL.toString() %>"
					/>

					<br />
				</c:if>
			</c:when>
			<c:otherwise>

				<%
				renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
				%>

				<br />

				<c:choose>
					<c:when test="<%= Validator.isNull(articleId) %>">
						<div class="portlet-msg-info">
							<liferay-ui:message key="select-existing-web-content-or-add-some-web-content-to-be-displayed-in-this-portlet" />
						</div>
					</c:when>
					<c:otherwise>

						<%
						JournalArticle article = null;

						try {
							article = JournalArticleLocalServiceUtil.getLatestArticle(scopeGroupId, articleId, WorkflowConstants.STATUS_ANY);

							if (article.isExpired()) {
						%>

								<div class="portlet-msg-alert">
									<%= LanguageUtil.format(pageContext, "x-is-expired", article.getTitle()) %>
								</div>

						<%
							}
							else if (!article.isApproved()) {
						%>

								<c:choose>
									<c:when test="<%= JournalArticlePermission.contains(permissionChecker, article, ActionKeys.UPDATE) %>">
										<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL" portletName="<%= PortletKeys.JOURNAL %>">
											<portlet:param name="struts_action" value="/journal/edit_article" />
											<portlet:param name="redirect" value="<%= currentURL %>" />
											<portlet:param name="referringPortletResource" value="<%= PortletKeys.JOURNAL_CONTENT %>" />
											<portlet:param name="groupId" value="<%= String.valueOf(article.getGroupId()) %>" />
											<portlet:param name="articleId" value="<%= article.getArticleId() %>" />
											<portlet:param name="version" value="<%= String.valueOf(article.getVersion()) %>" />
										</liferay-portlet:renderURL>

										<div class="portlet-msg-alert">
											<a href="<%= editURL %>">
												<%= LanguageUtil.format(pageContext, "x-is-not-approved", article.getTitle()) %>
											</a>
										</div>
								</c:when>
								<c:otherwise>
									<div class="portlet-msg-alert">
										<%= LanguageUtil.format(pageContext, "x-is-not-approved", article.getTitle()) %>
									</div>
								</c:otherwise>
							</c:choose>

						<%
							}
						}
						catch (NoSuchArticleException nsae) {
						%>

							<div class="portlet-msg-error">
								<%= LanguageUtil.get(pageContext, "the-selected-web-content-no-longer-exists") %>
							</div>

						<%
						}
						%>

					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>

		<%
		Group stageableGroup = themeDisplay.getScopeGroup();

		if (themeDisplay.getScopeGroup().isLayout()) {
			stageableGroup = layout.getGroup();
		}

		JournalTemplate template = null;

		if (articleDisplay != null && Validator.isNotNull(articleDisplay.getTemplateId())) {
			try {
				template = JournalTemplateLocalServiceUtil.getTemplate(articleDisplay.getGroupId(), articleDisplay.getTemplateId());
			}
			catch (NoSuchTemplateException nste) {
				template = JournalTemplateLocalServiceUtil.getTemplate(themeDisplay.getCompanyGroupId(), articleDisplay.getTemplateId());
			}
		}

		boolean staged = stageableGroup.hasStagingGroup();

		boolean showEditArticleIcon = (articleDisplay != null) && JournalArticlePermission.contains(permissionChecker, articleDisplay.getGroupId(), articleDisplay.getArticleId(), ActionKeys.UPDATE);
		boolean showEditTemplateIcon = (template != null) && JournalTemplatePermission.contains(permissionChecker, template.getGroupId(), template.getTemplateId(), ActionKeys.UPDATE);
		boolean showSelectArticleIcon = PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION);
		boolean showAddArticleIcon = PortletPermissionUtil.contains(permissionChecker, plid, portletDisplay.getId(), ActionKeys.CONFIGURATION) && JournalPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ARTICLE);
		boolean showIconsActions = themeDisplay.isSignedIn() && ((showEditArticleIcon || showEditTemplateIcon || showSelectArticleIcon || showAddArticleIcon) && !staged);
		%>

		<c:if test="<%= showIconsActions && !print %>">
			<div class="lfr-meta-actions icons-container">
				<div class="icon-actions">
					<c:if test="<%= showEditArticleIcon %>">
						<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL" portletName="<%= PortletKeys.JOURNAL %>">
							<portlet:param name="struts_action" value="/journal/edit_article" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="referringPortletResource" value="<%= PortletKeys.JOURNAL_CONTENT %>" />
							<portlet:param name="groupId" value="<%= String.valueOf(articleDisplay.getGroupId()) %>" />
							<portlet:param name="articleId" value="<%= articleDisplay.getArticleId() %>" />
							<portlet:param name="version" value="<%= String.valueOf(articleDisplay.getVersion()) %>" />
						</liferay-portlet:renderURL>

						<liferay-ui:icon
							image="edit"
							message="edit-web-content"
							url="<%= editURL %>"
						/>
					</c:if>

					<c:if test="<%= showEditTemplateIcon %>">
						<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editTemplateURL" portletName="<%= PortletKeys.JOURNAL %>">
							<portlet:param name="struts_action" value="/journal/edit_template" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="referringPortletResource" value="<%= PortletKeys.JOURNAL_CONTENT %>" />
							<portlet:param name="groupId" value="<%= String.valueOf(template.getGroupId()) %>" />
							<portlet:param name="templateId" value="<%= template.getTemplateId() %>" />
						</liferay-portlet:renderURL>

						<liferay-ui:icon
							image="../file_system/small/xml"
							message="edit-template"
							url="<%= editTemplateURL %>"
						/>
					</c:if>

					<c:if test="<%= showSelectArticleIcon %>">
						<liferay-ui:icon
							cssClass="portlet-configuration"
							image="configuration"
							message="select-web-content"
							method="get"
							url="<%= portletDisplay.getURLConfiguration() %>"
						/>
					</c:if>

					<c:if test="<%= showAddArticleIcon %>">
						<liferay-portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="addArticleURL" portletName="<%= PortletKeys.JOURNAL %>">
							<portlet:param name="struts_action" value="/journal/edit_article" />
							<portlet:param name="portletResource" value="<%= portletDisplay.getId() %>" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="referringPortletResource" value="<%= PortletKeys.JOURNAL_CONTENT %>" />
						</liferay-portlet:renderURL>

						<liferay-ui:icon
							image="add_article"
							message="add-web-content"
							url="<%= addArticleURL %>"
						/>
					</c:if>
				</div>
			</div>
		</c:if>

		<c:if test="<%= (articleDisplay != null) %>">
			<c:if test="<%= enableRatings %>">
				<div class="taglib-ratings-wrapper">
					<liferay-ui:ratings
						className="<%= JournalArticle.class.getName() %>"
						classPK="<%= articleDisplay.getResourcePrimKey() %>"
					/>
				</div>
			</c:if>

			<c:if test="<%= enableComments %>">

				<%
				int discussionMessagesCount = MBMessageLocalServiceUtil.getDiscussionMessagesCount(PortalUtil.getClassNameId(JournalArticle.class.getName()), articleDisplay.getResourcePrimKey(), WorkflowConstants.STATUS_APPROVED);
				%>

				<c:if test="<%= discussionMessagesCount > 0 %>">
					<liferay-ui:header
						title="comments"
					/>
				</c:if>

				<portlet:actionURL var="discussionURL">
					<portlet:param name="struts_action" value="/journal_content/edit_article_discussion" />
				</portlet:actionURL>

				<liferay-ui:discussion
					className="<%= JournalArticle.class.getName() %>"
					classPK="<%= articleDisplay.getResourcePrimKey() %>"
					formAction="<%= discussionURL %>"
					ratingsEnabled="<%= enableCommentRatings %>"
					redirect="<%= currentURL %>"
					subject="<%= articleDisplay.getTitle() %>"
					userId="<%= articleDisplay.getUserId() %>"
				/>
			</c:if>
		</c:if>
	</c:otherwise>
</c:choose>