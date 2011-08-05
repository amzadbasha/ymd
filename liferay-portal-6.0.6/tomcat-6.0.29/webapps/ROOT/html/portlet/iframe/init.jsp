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

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portlet.iframe.util.IFrameUtil" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String src = preferences.getValue("src", StringPool.BLANK);
boolean relative = GetterUtil.getBoolean(preferences.getValue("relative", StringPool.BLANK));

boolean auth = GetterUtil.getBoolean(preferences.getValue("auth", StringPool.BLANK));
String authType = preferences.getValue("auth-type", StringPool.BLANK);
String formMethod = preferences.getValue("form-method", StringPool.BLANK);
String userName = preferences.getValue("user-name", StringPool.BLANK);
String userNameField = preferences.getValue("user-name-field", StringPool.BLANK);
String password = preferences.getValue("password", StringPool.BLANK);
String passwordField = preferences.getValue("password-field", StringPool.BLANK);
String hiddenVariables = preferences.getValue("hidden-variables", StringPool.BLANK);
boolean resizeAutomatically = GetterUtil.getBoolean(preferences.getValue("resize-automatically", StringPool.TRUE));
String heightMaximized = GetterUtil.getString(preferences.getValue("height-maximized", "600"));
String heightNormal = GetterUtil.getString(preferences.getValue("height-normal", "300"));
String width = GetterUtil.getString(preferences.getValue("width", "100%"));

String alt = preferences.getValue("alt", StringPool.BLANK);
String border = preferences.getValue("border", "0");
String bordercolor = preferences.getValue("bordercolor", "#000000");
String frameborder = preferences.getValue("frameborder", "0");
String hspace = preferences.getValue("hspace", "0");
String longdesc = preferences.getValue("longdesc", StringPool.BLANK);
String scrolling = preferences.getValue("scrolling", "auto");
String vspace = preferences.getValue("vspace", "0");

List<String> iframeVariables = new ArrayList<String>();

Enumeration<String> enu = request.getParameterNames();

while (enu.hasMoreElements()) {
	String name = enu.nextElement();

	if (name.startsWith(_IFRAME_PREFIX)) {
		iframeVariables.add(name.substring(_IFRAME_PREFIX.length()).concat(StringPool.EQUAL).concat(request.getParameter(name)));
	}
}
%>

<%!
private static final String _IFRAME_PREFIX = "iframe_";
%>