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

<%@ include file="/html/taglib/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_flags_page") + StringPool.UNDERLINE;

String className = (String)request.getAttribute("liferay-ui:flags:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:flags:classPK"));
String contentTitle = GetterUtil.getString((String)request.getAttribute("liferay-ui:flags:contentTitle"));
boolean label = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:flags:label"), true);
String message = GetterUtil.getString((String)request.getAttribute("liferay-ui:flags:message"), "flag[action]");
long reportedUserId = GetterUtil.getLong((String)request.getAttribute("liferay-ui:flags:reportedUserId"));
%>

<div class="taglib-flags" title="<liferay-ui:message key="<%= message %>" />">
	<liferay-ui:icon
		cssClass="<%= randomNamespace %>"
		image="../ratings/flagged_icon"
		imageHover="../ratings/flagged_icon_hover"
		label="<%= label %>"
		message="<%= message %>"
		url="javascript:;"
	/>
</div>

<c:choose>
	<c:when test="<%= PropsValues.FLAGS_GUEST_USERS_ENABLED || themeDisplay.isSignedIn() %>">
		<aui:script use="aui-dialog">
			var icon = A.one('.<%= randomNamespace %>');

			if (icon) {
				icon.on(
					'click',
					function() {
						var popup = new A.Dialog(
							{
								centered: true,
								destroyOnClose: true,
								draggable: true,
								modal: true,
								stack: true,
								title: '<liferay-ui:message key="report-inappropriate-content" />',
								width: 435
							}
						).render();

						popup.plug(
							A.Plugin.IO, {
								data: {
									className: '<%= className %>',
									classPK: '<%= classPK %>',
									contentTitle: '<%= HtmlUtil.escape(contentTitle) %>',
									contentURL: '<%= PortalUtil.getPortalURL(request) + currentURL %>',
									reportedUserId: '<%= reportedUserId %>'
								},
								uri: '<liferay-portlet:renderURL portletName="<%= PortletKeys.FLAGS %>" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><liferay-portlet:param name="struts_action" value="/flags/edit_entry" /></liferay-portlet:renderURL>'
							}
						);
					}
				);
			}
		</aui:script>
	</c:when>
	<c:otherwise>
		<div id="<%= randomNamespace %>signIn" style="display:none">
			<liferay-ui:message key="please-sign-in-to-flag-this-as-inappropriate" />
		</div>

		<aui:script use="aui-dialog">
			var icon = A.one('.<%= randomNamespace %>');

			if (icon) {
				icon.on(
					'click',
					function(event) {
						var popup = new A.Dialog(
							{
								bodyContent: A.one('#<%= randomNamespace %>signIn').html(),
								centered: true,
								destroyOnClose: true,
								title: '<liferay-ui:message key="report-inappropriate-content" />',
								modal: true,
								width: 500
							}
						).render();

						event.preventDefault();
					}
				);
			}
		</aui:script>
	</c:otherwise>
</c:choose>