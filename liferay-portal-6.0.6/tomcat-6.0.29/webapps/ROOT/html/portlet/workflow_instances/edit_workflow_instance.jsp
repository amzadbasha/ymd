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

<%@ include file="/html/portlet/workflow_instances/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

WorkflowInstance workflowInstance = (WorkflowInstance)request.getAttribute(WebKeys.WORKFLOW_INSTANCE);

Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();

long companyId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
long groupId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID));
String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

WorkflowHandler workflowHandler = WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

AssetRenderer assetRenderer = workflowHandler.getAssetRenderer(classPK);
AssetRendererFactory assetRendererFactory = workflowHandler.getAssetRendererFactory();

AssetEntry assetEntry = null;

if (assetRenderer != null) {
	assetEntry = AssetEntryLocalServiceUtil.getEntry(assetRendererFactory.getClassName(), assetRenderer.getClassPK());
}

String headerTitle = LanguageUtil.get(pageContext, workflowInstance.getWorkflowDefinitionName());

if (assetEntry != null) {
	headerTitle = headerTitle.concat(StringPool.COLON + StringPool.SPACE + assetEntry.getTitle());
}

PortletURL editPortletURL = workflowHandler.getURLEdit(classPK, (LiferayPortletRequest)renderRequest, (LiferayPortletResponse)renderResponse);

PortletURL viewFullContentURL = renderResponse.createRenderURL();

viewFullContentURL.setParameter("struts_action", "/workflow_tasks/view_content");
viewFullContentURL.setParameter("redirect", currentURL);
viewFullContentURL.setParameter("type", assetRendererFactory.getType());

if (assetEntry != null) {
	viewFullContentURL.setParameter("assetEntryId", String.valueOf(assetEntry.getEntryId()));
}
%>

<portlet:renderURL var="backURL">
	<portlet:param name="struts_action" value="/workflow_instances/view" />
</portlet:renderURL>

<liferay-ui:header
	backURL="<%= backURL.toString() %>"
	title="<%= headerTitle %>"
/>

<aui:layout>
	<aui:column columnWidth="<%= 75 %>" cssClass="lfr-asset-column lfr-asset-column-details" first="<%= true %>">
		<aui:layout>
			<aui:column columnWidth="60">
				<div class="lfr-asset-status">
					<aui:field-wrapper label="state">
						<%= LanguageUtil.get(pageContext, workflowInstance.getState()) %>
					</aui:field-wrapper>
				</div>
			</aui:column>

			<aui:column>
				<div class="lfr-asset-date">
					<aui:field-wrapper label="end-date">
						<%= (workflowInstance.getEndDate() == null) ? LanguageUtil.get(pageContext, "never") : dateFormatDateTime.format(workflowInstance.getEndDate()) %>
					</aui:field-wrapper>
				</div>
			</aui:column>
		</aui:layout>

		<liferay-ui:panel-container cssClass="task-panel-container" id="preview" extended="<%= true %>">

			<c:if test="<%= assetRenderer != null %>">
				<liferay-ui:panel defaultState="open" title='<%= LanguageUtil.format(pageContext, "preview-of-x", ResourceActionsUtil.getModelResource(locale, className)) %>'>
					<div class="task-content-actions">
						<liferay-ui:icon-list>
							<c:if test="<%= assetRenderer.hasViewPermission(permissionChecker) %>">
								<liferay-ui:icon image="view" method="get" url="<%= viewFullContentURL.toString() %>" />
							</c:if>

							<c:if test="<%= editPortletURL != null %>">

								<%
								editPortletURL.setWindowState(WindowState.MAXIMIZED);
								editPortletURL.setPortletMode(PortletMode.VIEW);

								editPortletURL.setParameter("redirect", currentURL);
								%>

								<c:choose>
									<c:when test="<%= assetRenderer.hasEditPermission(permissionChecker) %>">
										<liferay-ui:icon image="edit" method="get" url="<%= editPortletURL.toString() %>" />
									</c:when>
									<c:otherwise>
										<liferay-ui:icon-help message="please-assign-the-task-to-yourself-to-be-able-to-edit-the-content" />
									</c:otherwise>
								</c:choose>
							</c:if>
						</liferay-ui:icon-list>
					</div>

					<h3 class="task-content-title">
						<img src="<%= workflowHandler.getIconPath((LiferayPortletRequest)renderRequest) %>" alt="" /> <%= workflowHandler.getTitle(classPK) %>
					</h3>

					<%
					String path = workflowHandler.render(classPK, renderRequest, renderResponse, AssetRenderer.TEMPLATE_ABSTRACT);

					request.setAttribute(WebKeys.ASSET_RENDERER, assetRenderer);
					request.setAttribute(WebKeys.ASSET_PUBLISHER_ABSTRACT_LENGTH, 200);
					%>

					<c:choose>
						<c:when test="<%= path == null %>">
							<%= workflowHandler.getSummary(classPK) %>
						</c:when>
						<c:otherwise>
							<liferay-util:include page="<%= path %>" portletId="<%= assetRendererFactory.getPortletId() %>" />
						</c:otherwise>
					</c:choose>

					<%
					String selectionStyle = "manual";

					String[] metadataFields = new String[] {"author", "categories", "tags"};
					%>

					<div class="asset-metadata">
						<%@ include file="/html/portlet/asset_publisher/asset_metadata.jspf" %>
					</div>
				</liferay-ui:panel>
			</c:if>

			<%
			List<WorkflowTask> workflowTasks = null;

			if (portletName.equals(PortletKeys.WORKFLOW_DEFINITIONS)) {
				workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(company.getCompanyId(), null, workflowInstance.getWorkflowInstanceId(), null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			}
			else {
				workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(company.getCompanyId(), user.getUserId(), workflowInstance.getWorkflowInstanceId(), false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			}
			%>

			<c:if test="<%= !workflowTasks.isEmpty() %>">
				<liferay-ui:panel defaultState="open" title='<%= LanguageUtil.get(pageContext, "tasks") %>'>

					<%
					PortletURL portletURL = renderResponse.createRenderURL();
					%>

					<liferay-ui:search-container
						emptyResultsMessage="there-are-no-tasks"
						iteratorURL="<%= portletURL %>"
					>
						<liferay-ui:search-container-results
							results="<%= workflowTasks %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.portal.kernel.workflow.WorkflowTask"
							modelVar="workflowTask"
							stringKey="<%= true %>"
						>
							<liferay-ui:search-container-row-parameter
								name="workflowTask"
								value="<%= workflowTask %>"
							/>

							<liferay-ui:search-container-column-text
								buffer="buffer"
								name="task"
							>

								<%
								buffer.append("<span class=\"task-name\" id=\"");
								buffer.append(workflowTask.getWorkflowTaskId());
								buffer.append("\">");
								buffer.append(LanguageUtil.get(pageContext, workflowTask.getName()));
								buffer.append("</span>");
								%>

							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								buffer="buffer"
								name="due-date"
							>

								<%
								if (workflowTask.getDueDate() == null) {
									buffer.append(LanguageUtil.get(pageContext, "never"));
								}
								else {
									buffer.append(dateFormatDateTime.format(workflowTask.getDueDate()));
								}
								%>

							</liferay-ui:search-container-column-text>

							<liferay-ui:search-container-column-text
								name="completed"
								value='<%= workflowTask.isCompleted() ? LanguageUtil.get(pageContext, "yes") : LanguageUtil.get(pageContext, "no") %>'
							/>

							<liferay-ui:search-container-column-jsp
								align="right"
								path="/html/portlet/workflow_instances/workflow_task_action.jsp"
							/>
						</liferay-ui:search-container-row>
						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
				</liferay-ui:panel>
			</c:if>

			<liferay-ui:panel defaultState="closed" title='<%= LanguageUtil.get(pageContext, "activities") %>'>

				<%
				List<Integer> logTypes = new ArrayList<Integer>();

				logTypes.add(WorkflowLog.TASK_ASSIGN);
				logTypes.add(WorkflowLog.TASK_COMPLETION);
				logTypes.add(WorkflowLog.TASK_UPDATE);
				logTypes.add(WorkflowLog.TRANSITION);

				List<WorkflowLog> workflowLogs = WorkflowLogManagerUtil.getWorkflowLogsByWorkflowInstance(company.getCompanyId(), workflowInstance.getWorkflowInstanceId(), logTypes, QueryUtil.ALL_POS, QueryUtil.ALL_POS, WorkflowComparatorFactoryUtil.getLogCreateDateComparator(true));

				for (WorkflowLog workflowLog : workflowLogs) {
					Role curRole = null;
					User curUser = null;
					String actorName = null;

					if (workflowLog.getRoleId() != 0) {
						curRole = RoleLocalServiceUtil.getRole(workflowLog.getRoleId());
						actorName = curRole.getDescriptiveName();
					}
					else if (workflowLog.getUserId() != 0) {
						curUser = UserLocalServiceUtil.getUser(workflowLog.getUserId());
						actorName = curUser.getFullName();
					}
				%>

					<div class="task-activity task-type-<%= workflowLog.getType() %>">
						<div class="task-activity-date">
							<%= dateFormatDateTime.format(workflowLog.getCreateDate()) %>
						</div>

						<c:choose>
							<c:when test="<%= workflowLog.getType() == WorkflowLog.TASK_COMPLETION %>">
								<div>
									<%= LanguageUtil.format(pageContext, "x-completed-the-task-x", new Object[] {HtmlUtil.escape(actorName), workflowLog.getState()}) %>
								</div>
							</c:when>
							<c:when test="<%= workflowLog.getType() == WorkflowLog.TASK_UPDATE %>">
								<div>
									<%= LanguageUtil.format(pageContext, "x-updated-the-due-date", HtmlUtil.escape(actorName)) %>
								</div>
							</c:when>
							<c:when test="<%= workflowLog.getType() == WorkflowLog.TRANSITION %>">
								<div>
									<%= LanguageUtil.format(pageContext, "x-changed-the-state-from-x-to-x", new Object[] {HtmlUtil.escape(actorName), workflowLog.getPreviousState(), workflowLog.getState()}) %>
								</div>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="<%= (workflowLog.getPreviousUserId() == 0) && (curUser != null) %>">
										<div>
											<%= LanguageUtil.format(pageContext, curUser.isMale() ? "x-assigned-the-task-to-himself" : "x-assigned-the-task-to-herself", HtmlUtil.escape(curUser.getFullName())) %>
										</div>
									</c:when>
									<c:otherwise>

										<%
										String previousActorName = null;

										if (curRole == null) {
											previousActorName = PortalUtil.getUserName(workflowLog.getPreviousUserId(), StringPool.BLANK);
										%>

											<div>
												<%= LanguageUtil.format(pageContext, "task-assigned-to-x.-previous-assignee-was-x", new Object[] {actorName, HtmlUtil.escape(previousActorName)}) %>
											</div>

										<%
										}
										else {
											previousActorName = curRole.getDescriptiveName();
										}
										%>

										<div>
											<%= LanguageUtil.format(pageContext, "task-initially-assigned-to-the-x-role", new Object[] {actorName}) %>
										</div>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>

						<div>
							<%= workflowLog.getComment() %>
						</div>
					</div>

				<%
				}
				%>

			</liferay-ui:panel>

			<liferay-ui:panel title='<%= LanguageUtil.get(pageContext, "comments") %>'>
				<portlet:actionURL var="discussionURL">
					<portlet:param name="struts_action" value="/workflow_instances/edit_workflow_instance_discussion" />
				</portlet:actionURL>

				<liferay-ui:discussion
					className="<%= WorkflowInstance.class.getName() %>"
					classPK="<%= workflowInstance.getWorkflowInstanceId() %>"
					formAction="<%= discussionURL %>"
					formName="fm1"
					ratingsEnabled="<%= false %>"
					redirect="<%= currentURL %>"
					subject="<%= workflowInstance.getWorkflowDefinitionName() %>"
					userId="<%= user.getUserId() %>"
				/>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</aui:column>

	<aui:column columnWidth="<%= 25 %>" cssClass="lfr-asset-column lfr-asset-column-actions" last="<%= true %>">
		<div class="lfr-asset-summary">
			<liferay-ui:icon
				cssClass="lfr-asset-avatar"
				image='../file_system/large/task'
				message="download"
			/>

			<div class="lfr-asset-name">
				<%= workflowInstance.getWorkflowDefinitionName() %>
			</div>
		</div>

		<%
		request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
		%>

		<liferay-util:include page="/html/portlet/workflow_instances/workflow_instance_action.jsp" />
	</aui:column>
</aui:layout>

<%
PortalUtil.addPortletBreadcrumbEntry(request, headerTitle, currentURL);
%>