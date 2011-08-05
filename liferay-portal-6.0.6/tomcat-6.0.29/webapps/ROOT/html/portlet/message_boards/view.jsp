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
String topLink = ParamUtil.getString(request, "topLink", "message-boards-home");

String redirect = ParamUtil.getString(request, "redirect");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = MBUtil.getCategoryId(request, category);

MBCategoryDisplay categoryDisplay = new MBCategoryDisplayImpl(scopeGroupId, categoryId);

Set<Long> categorySubscriptionClassPKs = null;
Set<Long> threadSubscriptionClassPKs = null;

if (themeDisplay.isSignedIn()) {
	List<Subscription> categorySubscriptions = SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), MBCategory.class.getName());

	categorySubscriptionClassPKs = new HashSet<Long>(categorySubscriptions.size());

	for (Subscription subscription : categorySubscriptions) {
		categorySubscriptionClassPKs.add(subscription.getClassPK());
	}

	threadSubscriptionClassPKs = new HashSet<Long>();

	List<Subscription> threadSubscriptions = SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), MBThread.class.getName());

	threadSubscriptionClassPKs = new HashSet<Long>(threadSubscriptions.size());

	for (Subscription subscription : threadSubscriptions) {
		threadSubscriptionClassPKs.add(subscription.getClassPK());
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/message_boards/view");
portletURL.setParameter("topLink", topLink);
portletURL.setParameter("mbCategoryId", String.valueOf(categoryId));

request.setAttribute("view.jsp-viewCategory", Boolean.TRUE.toString());
%>

<liferay-util:include page="/html/portlet/message_boards/top_links.jsp" />

<c:choose>
	<c:when test='<%= topLink.equals("message-boards-home") %>'>
		<c:if test="<%= category == null %>">
			<div class="category-subscriptions">
				<div class="category-subscription-types">
					<liferay-ui:icon
						image="rss"
						label="<%= true %>"
						method="get"
						target="_blank"
						url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathMain() + "/message_boards/rss?p_l_id=" + plid + "&mbCategoryId=" + scopeGroupId + rssURLParams %>'
					/>

					<c:if test="<%= MBPermission.contains(permissionChecker, scopeGroupId, ActionKeys.SUBSCRIBE) %>">
						<c:choose>
							<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), MBCategory.class.getName(), scopeGroupId) %>">
								<portlet:actionURL var="unsubscribeURL">
									<portlet:param name="struts_action" value="/message_boards/edit_category" />
									<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNSUBSCRIBE %>" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="mbCategoryId" value="<%= String.valueOf(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) %>" />
								</portlet:actionURL>

								<liferay-ui:icon
									image="unsubscribe"
									label="<%= true %>"
									url="<%= unsubscribeURL %>"
								/>
							</c:when>
							<c:otherwise>
								<portlet:actionURL var="subscribeURL">
									<portlet:param name="struts_action" value="/message_boards/edit_category" />
									<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE %>" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="mbCategoryId" value="<%= String.valueOf(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) %>" />
								</portlet:actionURL>

								<liferay-ui:icon
									image="subscribe"
									label="<%= true %>"
									url="<%= subscribeURL %>"
								/>
							</c:otherwise>
						</c:choose>
					</c:if>
				</div>
			</div>
		</c:if>

		<%
		boolean showAddCategoryButton = MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_CATEGORY);
		boolean showAddMessageButton = MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_MESSAGE);
		boolean showPermissionsButton = GroupPermissionUtil.contains(permissionChecker, scopeGroupId, ActionKeys.PERMISSIONS);

		if (showAddMessageButton && !themeDisplay.isSignedIn()) {
			if (!allowAnonymousPosting) {
				showAddMessageButton = false;
			}
		}
		%>

		<c:if test="<%= showAddCategoryButton || showAddMessageButton || showPermissionsButton %>">
			<div class="category-buttons">
				<c:if test="<%= showAddCategoryButton %>">
					<portlet:renderURL var="editCategoryURL">
						<portlet:param name="struts_action" value="/message_boards/edit_category" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="parentCategoryId" value="<%= String.valueOf(categoryId) %>" />
					</portlet:renderURL>

					<aui:button onClick='<%= editCategoryURL %>' value='<%= (category == null) ? "add-category" : "add-subcategory" %>' />
				</c:if>

				<c:if test="<%= showAddMessageButton %>">
					<portlet:renderURL var="editMessageURL">
						<portlet:param name="struts_action" value="/message_boards/edit_message" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="mbCategoryId" value="<%= String.valueOf(categoryId) %>" />
					</portlet:renderURL>

					<aui:button onClick='<%= editMessageURL %>' value="post-new-thread" />
				</c:if>

				<c:if test="<%= showPermissionsButton %>">

					<%
					String modelResource = "com.liferay.portlet.messageboards";
					String modelResourceDescription = themeDisplay.getScopeGroupName();
					String resourcePrimKey = String.valueOf(scopeGroupId);

					if (category != null) {
						modelResource = MBCategory.class.getName();
						modelResourceDescription = category.getName();
						resourcePrimKey = String.valueOf(category.getCategoryId());
					}
					%>

					<liferay-security:permissionsURL
						modelResource="<%= modelResource %>"
						modelResourceDescription="<%= HtmlUtil.escape(modelResourceDescription) %>"
						resourcePrimKey="<%= resourcePrimKey %>"
						var="permissionsURL"
					/>

					<aui:button onClick="<%= permissionsURL %>" value="permissions" />
				</c:if>
			</div>
		</c:if>

		<c:if test="<%= category != null %>">

			<%
			long parentCategoryId = category.getParentCategoryId();
			String parentCategoryName = LanguageUtil.get(pageContext, "message-boards-home");

			if (!category.isRoot()) {
				MBCategory parentCategory = MBCategoryLocalServiceUtil.getCategory(parentCategoryId);

				parentCategoryId = parentCategory.getCategoryId();
				parentCategoryName = parentCategory.getName();
			}
			%>

			<portlet:renderURL var="backURL">
				<portlet:param name="struts_action" value="/message_boards/view" />
				<portlet:param name="mbCategoryId" value="<%= String.valueOf(parentCategoryId) %>" />
			</portlet:renderURL>

			<liferay-ui:header
				title="<%= category.getName() %>"
				backLabel='<%= "&laquo; " + LanguageUtil.format(pageContext, "back-to-x", HtmlUtil.escape(parentCategoryName)) %>'
				backURL="<%= backURL.toString() %>"
			/>
		</c:if>

		<liferay-ui:panel-container cssClass="message-boards-panels" extended="<%= false %>" id="messageBoardsPanelContainer" persistState="<%= true %>">

			<%
			int categoriesCount = MBCategoryServiceUtil.getCategoriesCount(scopeGroupId, categoryId);
			%>

			<c:if test="<%= categoriesCount > 0 %>">
				<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="messageBoardsCategoriesPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, (category != null) ? "subcategories" : "categories") %>'>
					<liferay-ui:search-container
						curParam="cur1"
						deltaConfigurable="<%= false %>"
						headerNames="category,categories,threads,posts"
						iteratorURL="<%= portletURL %>"
					>
						<liferay-ui:search-container-results
							results="<%= MBCategoryServiceUtil.getCategories(scopeGroupId, categoryId, searchContainer.getStart(), searchContainer.getEnd()) %>"
							total="<%= categoriesCount %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.portlet.messageboards.model.MBCategory"
							escapedModel="<%= true %>"
							keyProperty="categoryId"
							modelVar="curCategory"
						>
							<liferay-ui:search-container-row-parameter name="categorySubscriptionClassPKs" value="<%= categorySubscriptionClassPKs %>" />

							<liferay-portlet:renderURL varImpl="rowURL">
								<portlet:param name="struts_action" value="/message_boards/view" />
								<portlet:param name="mbCategoryId" value="<%= String.valueOf(curCategory.getCategoryId()) %>" />
							</liferay-portlet:renderURL>

							<%@ include file="/html/portlet/message_boards/category_columns.jspf" %>
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
				</liferay-ui:panel>
			</c:if>

			<liferay-ui:panel collapsible="<%= true %>" cssClass="threads-panel" extended="<%= true %>" id="messageBoardsThreadsPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "threads") %>'>
				<liferay-ui:search-container
					curParam="cur2"
					emptyResultsMessage="there-are-no-threads-in-this-category"
					headerNames="thread,flag,started-by,posts,views,last-post"
					iteratorURL="<%= portletURL %>"
				>
					<liferay-ui:search-container-results
						results="<%= MBThreadServiceUtil.getThreads(scopeGroupId, categoryId, WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(), searchContainer.getEnd()) %>"
						total="<%= MBThreadServiceUtil.getThreadsCount(scopeGroupId, categoryId, WorkflowConstants.STATUS_APPROVED) %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.portlet.messageboards.model.MBThread"
						keyProperty="threadId"
						modelVar="thread"
					>

						<%
						MBMessage message = MBMessageLocalServiceUtil.getMessage(thread.getRootMessageId());

						message = message.toEscapedModel();

						boolean readThread = MBMessageFlagLocalServiceUtil.hasReadFlag(themeDisplay.getUserId(), thread);

						row.setBold(!readThread);
						row.setObject(new Object[] {message, threadSubscriptionClassPKs});
						row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));
						%>

						<liferay-portlet:renderURL varImpl="rowURL">
							<portlet:param name="struts_action" value="/message_boards/view_message" />
							<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
						</liferay-portlet:renderURL>

						<liferay-ui:search-container-column-text
							buffer="buffer"
							href="<%= rowURL %>"
							name="thread"
						>

							<%
							String[] threadPriority = MBUtil.getThreadPriority(preferences, themeDisplay.getLanguageId(), thread.getPriority(), themeDisplay);

							if ((threadPriority != null) && (thread.getPriority() > 0)) {
								buffer.append("<img class=\"thread-priority\" alt=\"");
								buffer.append(threadPriority[0]);
								buffer.append("\" src=\"");
								buffer.append(threadPriority[1]);
								buffer.append("\" title=\"");
								buffer.append(threadPriority[0]);
								buffer.append("\" />");
							}

							if (thread.isLocked()) {
								buffer.append("<img class=\"thread-priority\" alt=\"");
								buffer.append(LanguageUtil.get(pageContext, "thread-locked"));
								buffer.append("\" src=\"");
								buffer.append(themeDisplay.getPathThemeImages() + "/common/lock.png");
								buffer.append("\" title=\"");
								buffer.append(LanguageUtil.get(pageContext, "thread-locked"));
								buffer.append("\" />");
							}

							buffer.append(message.getSubject());
							%>

						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							buffer="buffer"
							href="<%= rowURL %>"
							name="flag"
						>

							<%
							if (MBMessageFlagLocalServiceUtil.hasAnswerFlag(message.getMessageId())) {
								buffer.append(LanguageUtil.get(pageContext, "resolved"));
							}
							else if (MBMessageFlagLocalServiceUtil.hasQuestionFlag(message.getMessageId())) {
								buffer.append(LanguageUtil.get(pageContext, "waiting-for-an-answer"));
							}
							%>

						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							href="<%= rowURL %>"
							name="started-by"
							value='<%= message.isAnonymous() ? LanguageUtil.get(pageContext, "anonymous") : HtmlUtil.escape(PortalUtil.getUserName(message.getUserId(), message.getUserName())) %>'
						/>

						<liferay-ui:search-container-column-text
							href="<%= rowURL %>"
							name="posts"
							value="<%= String.valueOf(thread.getMessageCount()) %>"
						/>

						<liferay-ui:search-container-column-text
							href="<%= rowURL %>"
							name="views"
							value="<%= String.valueOf(thread.getViewCount()) %>"
						/>

						<liferay-ui:search-container-column-text
							buffer="buffer"
							href="<%= rowURL %>"
							name="last-post"
						>

							<%
							if (thread.getLastPostDate() == null) {
								buffer.append(LanguageUtil.get(pageContext, "none"));
							}
							else {
								buffer.append(LanguageUtil.get(pageContext, "date"));
								buffer.append(": ");
								buffer.append(dateFormatDateTime.format(thread.getLastPostDate()));

								String lastPostByUserName = HtmlUtil.escape(PortalUtil.getUserName(thread.getLastPostByUserId(), StringPool.BLANK));

								if (Validator.isNotNull(lastPostByUserName)) {
									buffer.append("<br />");
									buffer.append(LanguageUtil.get(pageContext, "by"));
									buffer.append(": ");
									buffer.append(lastPostByUserName);
								}
							}
							%>

						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-jsp
							align="right"
							path="/html/portlet/message_boards/message_action.jsp"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
			</liferay-ui:panel>
		</liferay-ui:panel-container>

		<%
		if (category != null) {
			PortalUtil.setPageSubtitle(category.getName(), request);
			PortalUtil.setPageDescription(category.getDescription(), request);

			MBUtil.addPortletBreadcrumbEntries(category, request, renderResponse);
		}
		%>

	</c:when>
	<c:when test='<%= topLink.equals("my-posts") || topLink.equals("my-subscriptions") || topLink.equals("recent-posts") %>'>

		<%
		long groupThreadsUserId = ParamUtil.getLong(request, "groupThreadsUserId");

		if ((topLink.equals("my-posts") || topLink.equals("my-subscriptions")) && themeDisplay.isSignedIn()) {
			groupThreadsUserId = user.getUserId();
		}

		if (groupThreadsUserId > 0) {
			portletURL.setParameter("groupThreadsUserId", String.valueOf(groupThreadsUserId));
		}
		%>

		<c:if test='<%= topLink.equals("recent-posts") && (groupThreadsUserId > 0) %>'>
			<div class="portlet-msg-info">
				<liferay-ui:message key="filter-by-user" />: <%= HtmlUtil.escape(PortalUtil.getUserName(groupThreadsUserId, StringPool.BLANK)) %>
			</div>
		</c:if>

		<c:if test='<%= topLink.equals("my-subscriptions") %>'>
			<liferay-ui:search-container
				curParam="cur1"
				deltaConfigurable="<%= false %>"
				emptyResultsMessage="you-are-not-subscribed-to-any-categories"
				headerNames="category,categories,threads,posts"
				iteratorURL="<%= portletURL %>"
			>
				<liferay-ui:search-container-results
					results="<%= MBCategoryServiceUtil.getSubscribedCategories(scopeGroupId, user.getUserId(), searchContainer.getStart(), searchContainer.getEnd()) %>"
					total="<%= MBCategoryServiceUtil.getSubscribedCategoriesCount(scopeGroupId, user.getUserId()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.portlet.messageboards.model.MBCategory"
					escapedModel="<%= true %>"
					keyProperty="categoryId"
					modelVar="curCategory"
				>
					<liferay-ui:search-container-row-parameter name="categorySubscriptionClassPKs" value="<%= categorySubscriptionClassPKs %>" />

					<liferay-portlet:renderURL varImpl="rowURL">
						<portlet:param name="struts_action" value="/message_boards/view" />
						<portlet:param name="mbCategoryId" value="<%= String.valueOf(curCategory.getCategoryId()) %>" />
					</liferay-portlet:renderURL>

					<%@ include file="/html/portlet/message_boards/subscribed_category_columns.jspf" %>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator type="more" />
			</liferay-ui:search-container>
		</c:if>

		<liferay-ui:search-container
			headerNames="thread,started-by,posts,views,last-post"
			iteratorURL="<%= portletURL %>"
		>

			<%
			String emptyResultsMessage = null;

			if (topLink.equals("my-posts")) {
				emptyResultsMessage = "you-do-not-have-any-posts";
			}
			else if (topLink.equals("my-subscriptions")) {
				emptyResultsMessage = "you-are-not-subscribed-to-any-threads";
			}
			else if (topLink.equals("recent-posts")) {
				emptyResultsMessage = "there-are-no-recent-posts";
			}

			searchContainer.setEmptyResultsMessage(emptyResultsMessage);
			%>

			<liferay-ui:search-container-results>

				<%
				if (topLink.equals("my-posts")) {
					results = MBThreadServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_ANY, searchContainer.getStart(), searchContainer.getEnd());
					total = MBThreadServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_ANY);
				}
				else if (topLink.equals("my-subscriptions")) {
					results = MBThreadServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, true, searchContainer.getStart(), searchContainer.getEnd());
					total = MBThreadServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, true);
				}
				else if (topLink.equals("recent-posts")) {
					results = MBThreadServiceUtil.getGroupThreads(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, false, false, searchContainer.getStart(), searchContainer.getEnd());
					total = MBThreadServiceUtil.getGroupThreadsCount(scopeGroupId, groupThreadsUserId, WorkflowConstants.STATUS_APPROVED, false, false);
				}

				pageContext.setAttribute("results", results);
				pageContext.setAttribute("total", total);
				%>

			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.portlet.messageboards.model.MBThread"
				keyProperty="threadId"
				modelVar="thread"
			>

				<%
				MBMessage message = MBMessageLocalServiceUtil.getMessage(thread.getRootMessageId());

				message = message.toEscapedModel();

				boolean readThread = MBMessageFlagLocalServiceUtil.hasReadFlag(themeDisplay.getUserId(), thread);

				row.setBold(!readThread);
				row.setObject(new Object[] {message, threadSubscriptionClassPKs});
				row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));
				%>

				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="struts_action" value="/message_boards/view_message" />
					<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					buffer="buffer"
					href="<%= rowURL %>"
					name="thread"
				>

					<%
					String[] threadPriority = MBUtil.getThreadPriority(preferences, themeDisplay.getLanguageId(), thread.getPriority(), themeDisplay);

					if ((threadPriority != null) && (thread.getPriority() > 0)) {
						buffer.append("<img class=\"thread-priority\" alt=\"");
						buffer.append(threadPriority[0]);
						buffer.append("\" src=\"");
						buffer.append(threadPriority[1]);
						buffer.append("\" title=\"");
						buffer.append(threadPriority[0]);
						buffer.append("\" />");
					}

					buffer.append(message.getSubject());
					%>

				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="started-by"
					value='<%= message.isAnonymous() ? LanguageUtil.get(pageContext, "anonymous") : HtmlUtil.escape(PortalUtil.getUserName(message.getUserId(), message.getUserName())) %>'
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="posts"
					value="<%= String.valueOf(thread.getMessageCount()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="views"
					value="<%= String.valueOf(thread.getViewCount()) %>"
				/>

				<liferay-ui:search-container-column-text
					buffer="buffer"
					href="<%= rowURL %>"
					name="last-post"
				>

					<%
					if (thread.getLastPostDate() == null) {
						buffer.append(LanguageUtil.get(pageContext, "none"));
					}
					else {
						buffer.append(LanguageUtil.get(pageContext, "date"));
						buffer.append(": ");
						buffer.append(dateFormatDateTime.format(thread.getLastPostDate()));

						String lastPostByUserName = HtmlUtil.escape(PortalUtil.getUserName(thread.getLastPostByUserId(), StringPool.BLANK));

						if (Validator.isNotNull(lastPostByUserName)) {
							buffer.append("<br />");
							buffer.append(LanguageUtil.get(pageContext, "by"));
							buffer.append(": ");
							buffer.append(lastPostByUserName);
						}
					}
					%>

				</liferay-ui:search-container-column-text>

				<c:if test='<%= topLink.equals("my-posts") %>'>
					<liferay-ui:search-container-column-text
						href="<%= rowURL %>"
						name="status"
						value="<%= LanguageUtil.get(pageContext, WorkflowConstants.toLabel(message.getStatus())) %>"
					/>
				</c:if>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/html/portlet/message_boards/message_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>

		<c:if test='<%= topLink.equals("recent-posts") %>'>

			<%
			String rssURL = themeDisplay.getPortalURL() + themeDisplay.getPathMain() + "/message_boards/rss?p_l_id=" + plid + "&groupId=" + scopeGroupId;

			if (groupThreadsUserId > 0) {
				rssURL += "&userId=" + groupThreadsUserId;
			}

			rssURL += rssURLParams;
			%>

			<br />

			<table class="lfr-table">
			<tr>
				<td>
					<liferay-ui:icon
						image="rss"
						label="<%= true %>"
						message="subscribe-to-recent-posts"
						method="get"
						target="_blank"
						url="<%= rssURL %>"
					/>
				</td>
			</tr>
			</table>
		</c:if>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(topLink, StringPool.UNDERLINE, StringPool.DASH)), request);
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, TextFormatter.format(topLink, TextFormatter.O)), portletURL.toString());
		%>

	</c:when>
	<c:when test='<%= topLink.equals("statistics") %>'>
		<liferay-ui:panel-container cssClass="statistics-panel" extended="<%= false %>" id="messageBoardsStatisticsPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" cssClass="statistics-panel-content" extended="<%= true %>" id="messageBoardsGeneralStatisticsPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "general") %>'>
				<dl>
					<dt>
						<liferay-ui:message key="num-of-categories" />:
					</dt>
					<dd>
						<%= numberFormat.format(categoryDisplay.getAllCategoriesCount()) %>
					</dd>
					<dt>
						<liferay-ui:message key="num-of-posts" />:
					</dt>
					<dd>
						<%= numberFormat.format(MBMessageLocalServiceUtil.getGroupMessagesCount(scopeGroupId, WorkflowConstants.STATUS_APPROVED)) %>
					</dd>
					<dt>
						<liferay-ui:message key="num-of-participants" />:
					</dt>
					<dd>
						<%= numberFormat.format(MBStatsUserLocalServiceUtil.getStatsUsersByGroupIdCount(scopeGroupId)) %>
					</dd>
				</dl>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" cssClass="statistics-panel-content" extended="<%= true %>" id="messageBoardsTopPostersPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "top-posters") %>'>
				<liferay-ui:search-container
					emptyResultsMessage="there-are-no-top-posters"
					iteratorURL="<%= portletURL %>"
				>
					<liferay-ui:search-container-results
						results="<%= MBStatsUserLocalServiceUtil.getStatsUsersByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
						total="<%= MBStatsUserLocalServiceUtil.getStatsUsersByGroupIdCount(scopeGroupId) %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.portlet.messageboards.model.MBStatsUser"
						keyProperty="statsUserId"
						modelVar="statsUser"
					>
						<liferay-ui:search-container-column-jsp
							path="/html/portlet/message_boards/top_posters_user_display.jsp"
						/>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
			</liferay-ui:panel>
		</liferay-ui:panel-container>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(topLink, StringPool.UNDERLINE, StringPool.DASH)), request);
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, TextFormatter.format(topLink, TextFormatter.O)), portletURL.toString());
		%>

	</c:when>
	<c:when test='<%= topLink.equals("banned-users") %>'>
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-banned-users"
			headerNames="banned-user,banned-by,ban-date"
			iteratorURL="<%= portletURL %>"
		>
			<liferay-ui:search-container-results
				results="<%= MBBanLocalServiceUtil.getBans(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
				total="<%= MBBanLocalServiceUtil.getBansCount(scopeGroupId) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portlet.messageboards.model.MBBan"
				keyProperty="banId"
				modelVar="ban"
			>
				<liferay-ui:search-container-column-text
					name="banned-user"
					value="<%= HtmlUtil.escape(PortalUtil.getUserName(ban.getBanUserId(), StringPool.BLANK)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="banned-by"
					value="<%= HtmlUtil.escape(PortalUtil.getUserName(ban.getUserId(), StringPool.BLANK)) %>"
				/>

				<liferay-ui:search-container-column-text
					name="ban-date"
					value="<%= dateFormatDateTime.format(ban.getCreateDate()) %>"
				/>

				<c:if test="<%= PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL > 0 %>">
					<liferay-ui:search-container-column-text
						name="unban-date"
						value="<%= dateFormatDateTime.format(MBUtil.getUnbanDate(ban, PropsValues.MESSAGE_BOARDS_EXPIRE_BAN_INTERVAL)) %>"
					/>
				</c:if>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/html/portlet/message_boards/ban_user_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>

		<%
		PortalUtil.setPageSubtitle(LanguageUtil.get(pageContext, StringUtil.replace(topLink, StringPool.UNDERLINE, StringPool.DASH)), request);
		PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, TextFormatter.format(topLink, TextFormatter.O)), portletURL.toString());
		%>

	</c:when>
</c:choose>