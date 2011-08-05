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

<%@ include file="/html/portlet/layout_prototypes/init.jsp" %>

<%
String keywords = ParamUtil.getString(request, "keywords");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/layout_prototypes/view");
%>

<liferay-util:include page="/html/portlet/layout_prototypes/toolbar.jsp">
	<liferay-util:param name="toolbarItem" value="view-all" />
</liferay-util:include>

<aui:form action="<%= portletURL.toString() %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />

	<liferay-ui:search-container
		searchContainer='<%= new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, null, LanguageUtil.get(pageContext, "no-page-templates-were-found")) %>'
		headerNames="name"
	>
		<aui:input name="deleteLayoutPrototypesIds" type="hidden" />

		<liferay-ui:search-container-results
			results="<%= LayoutPrototypeLocalServiceUtil.search(company.getCompanyId(), null, searchContainer.getStart(), searchContainer.getEnd(), null) %>"
			total="<%= LayoutPrototypeLocalServiceUtil.searchCount(company.getCompanyId(), null) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.LayoutPrototype"
			escapedModel="<%= true %>"
			keyProperty="layoutPrototypeId"
			modelVar="layoutPrototype"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="struts_action" value="/layout_prototypes/edit_layout_prototype" />
				<portlet:param name="redirect" value="<%= searchContainer.getIteratorURL().toString() %>" />
				<portlet:param name="backURL" value="<%= searchContainer.getIteratorURL().toString() %>" />
				<portlet:param name="layoutPrototypeId" value="<%= String.valueOf(layoutPrototype.getLayoutPrototypeId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				orderable="<%= true %>"
				value="<%= HtmlUtil.escape(layoutPrototype.getName(locale)) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="active"
			>
				<%= LanguageUtil.get(pageContext, layoutPrototype.isActive()? "yes" : "no") %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/html/portlet/layout_prototypes/layout_prototype_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>