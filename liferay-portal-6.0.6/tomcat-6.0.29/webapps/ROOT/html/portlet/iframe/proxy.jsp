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

<%@ include file="/html/portlet/iframe/init.jsp" %>

<%
List<String> hiddenVariablesList = ListUtil.toList(StringUtil.split(hiddenVariables, StringPool.SEMICOLON));

hiddenVariablesList.addAll(iframeVariables);
%>

<html dir="<liferay-ui:message key="lang.dir" />">

<head>
	<meta content="no-cache" http-equiv="Cache-Control" />
	<meta content="no-cache" http-equiv="Pragma" />
	<meta content="0" http-equiv="Expires" />
</head>

<body onLoad="setTimeout('document.fm.submit()', 100);">

<form action="<%= src %>" method="<%= formMethod %>" name="fm">

<%
for (String hiddenVariable : hiddenVariablesList) {
	String hiddenKey = StringPool.BLANK;
	String hiddenValue = StringPool.BLANK;

	int pos = hiddenVariable.indexOf(StringPool.EQUAL);

	if (pos != -1) {
		hiddenKey = hiddenVariable.substring(0, pos);
		hiddenValue = hiddenVariable.substring(pos + 1, hiddenVariable.length());
	}
%>

	<input name="<%= hiddenKey %>" type="hidden" value="<%= hiddenValue %>" />

<%
}

if (Validator.isNull(userNameField)) {
	int pos = userName.indexOf(StringPool.EQUAL);

	if (pos != -1) {
		String fieldValuePair = userName;

		userNameField = fieldValuePair.substring(0, pos);
		userName = fieldValuePair.substring(pos + 1);

		preferences.setValue("user-name", userName);
		preferences.setValue("user-name-field", userNameField);

		preferences.store();
	}
}

if (Validator.isNotNull(userNameField)) {
	userName = IFrameUtil.getUserName(renderRequest, userName);
}
%>

<input name="<%= userNameField %>" type="hidden" value="<%= userName %>" />

<%
if (Validator.isNull(passwordField)) {
	int pos = password.indexOf(StringPool.EQUAL);

	if (pos != -1) {
		String fieldValuePair = password;

		passwordField = fieldValuePair.substring(0, pos);
		password = fieldValuePair.substring(pos + 1);

		preferences.setValue("password", password);
		preferences.setValue("password-field", passwordField);

		preferences.store();
	}
}

if (Validator.isNotNull(passwordField)) {
	password = IFrameUtil.getPassword(renderRequest, password);
}
%>

<input name="<%= passwordField %>" type="hidden" value="<%= password %>" />

</form>

</body>

</html>