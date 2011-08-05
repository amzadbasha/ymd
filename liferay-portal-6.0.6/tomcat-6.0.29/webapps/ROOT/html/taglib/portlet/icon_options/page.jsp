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

<liferay-ui:icon-menu
	align="auto"
	cssClass="portlet-options"
	icon=""
	message="options"
	showArrow="<%= false %>"
	showWhenSingleIcon="<%= true %>"
>
	<liferay-portlet:icon-refresh />

	<liferay-portlet:icon-portlet-css />

	<liferay-portlet:icon-configuration />

	<liferay-portlet:icon-edit />

	<liferay-portlet:icon-edit-defaults />

	<liferay-portlet:icon-edit-guest />

	<liferay-portlet:icon-export-import />

	<liferay-portlet:icon-help />

	<liferay-portlet:icon-print />

	<%
	Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);
	%>

	<c:if test="<%= portlet != null %>">

		<%
		PortletPreferences portletSetup = PortletPreferencesFactoryUtil.getLayoutPortletSetup(layout, portletDisplay.getId());

		boolean widgetShowAddAppLink = GetterUtil.getBoolean(portletSetup.getValue("lfr-widget-show-add-app-link", null), PropsValues.THEME_PORTLET_SHARING_DEFAULT);

		String facebookAPIKey = portletSetup.getValue("lfr-facebook-api-key", StringPool.BLANK);
		String facebookCanvasPageURL = portletSetup.getValue("lfr-facebook-canvas-page-url", StringPool.BLANK);
		boolean facebookShowAddAppLink = GetterUtil.getBoolean(portletSetup.getValue("lfr-facebook-show-add-app-link", null), true);

		if (Validator.isNull(facebookCanvasPageURL) || Validator.isNull(facebookAPIKey)) {
			facebookShowAddAppLink = false;
		}

		boolean iGoogleShowAddAppLink = GetterUtil.getBoolean(portletSetup.getValue("lfr-igoogle-show-add-app-link", StringPool.BLANK));
		boolean netvibesShowAddAppLinks = GetterUtil.getBoolean(portletSetup.getValue("lfr-netvibes-show-add-app-link", StringPool.BLANK));
		boolean appShowShareWithFriendsLink = GetterUtil.getBoolean(portletSetup.getValue("lfr-app-show-share-with-friends-link", StringPool.BLANK));
		%>

		<c:if test="<%= widgetShowAddAppLink || facebookShowAddAppLink || iGoogleShowAddAppLink || netvibesShowAddAppLinks || appShowShareWithFriendsLink %>">
			<c:if test="<%= widgetShowAddAppLink %>">

				<%
				String widgetHREF = "javascript:Liferay.PortletSharing.showWidgetInfo('" + PortalUtil.getWidgetURL(portlet, themeDisplay) + "');";
				%>

				<liferay-ui:icon
					cssClass='<%= portletDisplay.getNamespace() + "expose-as-widget" %>'
					image="../dock/add_content"
					label="<%= true %>"
					message="add-to-any-website"
					url="<%= widgetHREF %>"
				/>
			</c:if>

			<c:if test="<%= facebookShowAddAppLink %>">
				<liferay-ui:icon
					image="../social_bookmarks/facebook"
					label="<%= true %>"
					message="add-to-facebook"
					method="get"
					url='<%= "http://www.facebook.com/add.php?api_key=" + facebookAPIKey + "&ref=pd" %>'
				/>
			</c:if>

			<c:if test="<%= iGoogleShowAddAppLink %>">

				<%
				String googleGadgetHREF = "http://fusion.google.com/add?source=atgs&moduleurl=" + PortalUtil.getGoogleGadgetURL(portlet, themeDisplay);
				%>

				<liferay-ui:icon
					cssClass='<%= portletDisplay.getNamespace() + "expose-as-widget" %>'
					image="../dock/add_content"
					label="<%= true %>"
					message="add-to-igoogle"
					url="<%= googleGadgetHREF %>"
				/>
			</c:if>

			<c:if test="<%= netvibesShowAddAppLinks %>">

				<%
				String netvibesHREF = "javascript:Liferay.PortletSharing.showNetvibesInfo('" + PortalUtil.getNetvibesURL(portlet, themeDisplay) + "');";
				%>

				<liferay-ui:icon
					image="../dock/add_content"
					label="<%= true %>"
					message="add-to-netvibes"
					method="get"
					url="<%= netvibesHREF %>"
				/>
			</c:if>

			<c:if test="<%= appShowShareWithFriendsLink %>">
				<liferay-ui:icon
					image="share"
					label="<%= true %>"
					message="share-this-application-with-friends"
					method="get"
					url="javascript:;"
				/>
			</c:if>
		</c:if>
	</c:if>
</liferay-ui:icon-menu>