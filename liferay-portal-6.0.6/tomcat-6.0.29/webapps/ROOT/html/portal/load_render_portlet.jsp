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

<%@ include file="/html/portal/init.jsp" %>

<%
Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

String portletId = portlet.getPortletId();
%>

<c:choose>
	<c:when test="<%= portlet.getRenderWeight() >= 1 %>">
		[$TEMPLATE_PORTLET_<%= portletId %>$]
	</c:when>
	<c:otherwise>

		<%
		portletDisplay.setId(portletId);
		portletDisplay.setNamespace(PortalUtil.getPortletNamespace(portletId));

		String url = PortletURLUtil.getRefreshURL(request, themeDisplay);
		%>

		<div class="loading-animation" id="p_load<%= portletDisplay.getNamespace() %>"></div>

		<aui:script use="aui-base">
			var ns = '<%= portletDisplay.getNamespace() %>';

			Liferay.Portlet.addHTML(
				{
					onComplete: function(portlet, portletId) {
						portlet.refreshURL = '<%= url %>';
					},
					placeHolder: A.one('#p_load' + ns),
					url: '<%= url %>'
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>