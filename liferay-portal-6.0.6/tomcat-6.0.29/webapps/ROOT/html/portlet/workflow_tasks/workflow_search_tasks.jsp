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

<%@ include file="/html/portlet/workflow_tasks/init.jsp" %>

<%
WorkflowTaskDisplayTerms displayTerms = new WorkflowTaskDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	id="toggle_id_workflow_task_search"
	displayTerms="<%= displayTerms %>"
	buttonLabel="search"
>

	<aui:fieldset>
		<aui:column>
			<aui:input name="<%= displayTerms.NAME %>" size="20" value="<%= displayTerms.getName() %>" />
		</aui:column>
		<aui:column>
			<aui:select name="<%= displayTerms.TYPE %>">

				<%
				String displayTermsType = displayTerms.getType();

				List<WorkflowHandler> workflowHhandlers = WorkflowHandlerRegistryUtil.getWorkflowHandlers();

				for (WorkflowHandler workflowHandler : workflowHhandlers) {
					String defaultWorkflowHandlerType = workflowHandler.getType(LocaleUtil.getDefault());
				%>

					<aui:option label="<%= workflowHandler.getType(locale) %>" selected="<%= displayTermsType.equals(defaultWorkflowHandlerType) %>" value="<%= defaultWorkflowHandlerType %>" />

				<%
				}
				%>

			</aui:select>
		</aui:column>
	</aui:fieldset>
</liferay-ui:search-toggle>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= displayTerms.NAME %>);
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= displayTerms.KEYWORDS %>);
	</aui:script>
</c:if>