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

<%@ include file="/html/common/init.jsp" %>

<portlet:defineObjects />

<tiles:useAttribute id="tilesPortletContent" name="portlet_content" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="tilesPortletDecorate" name="portlet_decorate" classname="java.lang.String" ignore="true" />
<tiles:useAttribute id="tilesPortletPadding" name="portlet_padding" classname="java.lang.String" ignore="true" />

<%
Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

PortletPreferences portletSetup = PortletPreferencesFactoryUtil.getLayoutPortletSetup(layout, portletDisplay.getId());

RenderResponseImpl renderResponseImpl = (RenderResponseImpl)PortletResponseImpl.getPortletResponseImpl(renderResponse);

// Portlet decorate

boolean tilesPortletDecorateBoolean = GetterUtil.getBoolean(tilesPortletDecorate, true);

boolean portletDecorateDefault = false;

if (tilesPortletDecorateBoolean) {
	portletDecorateDefault = GetterUtil.getBoolean(theme.getSetting("portlet-setup-show-borders-default"), PropsValues.THEME_PORTLET_DECORATE_DEFAULT);
}

boolean portletDecorate = GetterUtil.getBoolean(portletSetup.getValue("portlet-setup-show-borders", String.valueOf(portletDecorateDefault)));

Boolean portletDecorateObj = (Boolean)renderRequest.getAttribute(WebKeys.PORTLET_DECORATE);

if (portletDecorateObj != null) {
	portletDecorate = portletDecorateObj.booleanValue();

	request.removeAttribute(WebKeys.PORTLET_DECORATE);
}

// Portlet title

String portletTitle = PortletConfigurationUtil.getPortletTitle(portletSetup, themeDisplay.getLanguageId());

if (portletDisplay.isAccess() && portletDisplay.isActive()) {
	if (Validator.isNull(portletTitle)) {
		portletTitle = renderResponseImpl.getTitle();
	}
}

ResourceBundle resourceBundle = portletConfig.getResourceBundle(locale);

if (Validator.isNull(portletTitle)) {
	portletTitle = resourceBundle.getString(JavaConstants.JAVAX_PORTLET_TITLE);
}

portletDisplay.setTitle(portletTitle);

// Portlet description

String portletDescription = StringPool.BLANK;

try {
	portletDescription = resourceBundle.getString(JavaConstants.JAVAX_PORTLET_DESCRIPTION);
}
catch (MissingResourceException mre) {
}

if (Validator.isNull(portletDescription)) {
	portletDescription = PortalUtil.getPortletDescription(portlet.getPortletId(), locale);
}

portletDisplay.setDescription(portletDescription);

Group group = layout.getGroup();

boolean wsrp = ParamUtil.getBoolean(request, "wsrp");
%>

<c:choose>
	<c:when test="<%= wsrp %>">
		<liferay-wsrp-portlet>
			<%@ include file="/html/common/themes/portlet_content_wrapper.jspf" %>
		</liferay-wsrp-portlet>
	</c:when>
	<c:when test="<%= themeDisplay.isFacebook() %>">
		<%@ include file="/html/common/themes/portlet_facebook.jspf" %>
	</c:when>
	<c:when test="<%= themeDisplay.isStateExclusive() %>">
		<%@ include file="/html/common/themes/portlet_content_wrapper.jspf" %>
	</c:when>
	<c:when test="<%= themeDisplay.isStatePopUp() %>">
		<div class="portlet-body">
			<c:if test="<%= Validator.isNotNull(tilesPortletContent) %>">
				<c:if test='<%= !tilesPortletContent.endsWith("/error.jsp") %>'>
					<%@ include file="/html/common/themes/portlet_messages.jspf" %>
				</c:if>

				<liferay-util:include page="<%= StrutsUtil.TEXT_HTML_DIR + tilesPortletContent %>" />
			</c:if>

			<c:if test="<%= Validator.isNull(tilesPortletContent) %>">

				<%
				pageContext.getOut().print(renderRequest.getAttribute(WebKeys.PORTLET_CONTENT));
				%>

			</c:if>
		</div>
	</c:when>
	<c:otherwise>

		<%
		Boolean renderPortletResource = (Boolean)request.getAttribute(WebKeys.RENDER_PORTLET_RESOURCE);

		boolean runtimePortlet = (renderPortletResource != null) && renderPortletResource.booleanValue();

		boolean freeformPortlet = themeDisplay.isFreeformLayout() && !runtimePortlet && !layoutTypePortlet.hasStateMax();

		String containerStyles = StringPool.BLANK;

		if (freeformPortlet) {
			Properties freeformStyleProps = PropertiesUtil.load(portletSetup.getValue("portlet-freeform-styles", StringPool.BLANK));

			containerStyles = "style=\"height: ".concat(GetterUtil.getString(freeformStyleProps.getProperty("height"), "300px")).concat("; overflow: auto;\"");
		}
		else {
			containerStyles = "style=\"\"";
		}
		%>

		<c:choose>
			<c:when test="<%= portletDecorate %>">
				<liferay-theme:wrap-portlet page="portlet.jsp">
					<div class="<%= portletDisplay.isStateMin() ? "aui-helper-hidden" : "" %> portlet-content-container" <%= containerStyles %>>
						<%@ include file="/html/common/themes/portlet_content_wrapper.jspf" %>
					</div>
				</liferay-theme:wrap-portlet>
			</c:when>
			<c:otherwise>
				<div class="portlet-borderless-container" <%= containerStyles %>>
					<c:if test="<%= (tilesPortletDecorateBoolean && portletDisplay.isShowConfigurationIcon()) || portletDisplay.isShowBackIcon() %>">
						<div class="portlet-borderless-bar">
							<c:if test="<%= tilesPortletDecorateBoolean && portletDisplay.isShowConfigurationIcon() %>">
								<span class="portlet-title-default"><%= portletDisplay.getTitle() %></span>

								<span class="portlet-actions">
									<span class="portlet-action portlet-options">
										<span class="portlet-action-separator">-</span>

										<liferay-portlet:icon-options />
									</span>

									<c:if test="<%= portletDisplay.isShowCloseIcon() %>">
										<span class="portlet-action portlet-close">
											<span class="portlet-action-separator">-</span>

											<a href="<%= portletDisplay.getURLClose() %>" title="<liferay-ui:message key="close" />"><liferay-ui:message key="close" /></a>
										</span>
									</c:if>
								</span>
							</c:if>

							<c:if test="<%= portletDisplay.isShowBackIcon() %>">
								<span class="portlet-action portlet-back">
									<span class="portlet-action-separator">-</span>

									<a href="<%= portletDisplay.getURLBack() %>" title="<liferay-ui:message key="back" />"><liferay-ui:message key="back" /></a>
								</span>
							</c:if>
						</div>
					</c:if>

					<%@ include file="/html/common/themes/portlet_content_wrapper.jspf" %>
				</div>

				<c:if test="<%= freeformPortlet %>">
					<div class="portlet-resize-container">
						<div class="portlet-resize-handle"></div>
					</div>
				</c:if>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>