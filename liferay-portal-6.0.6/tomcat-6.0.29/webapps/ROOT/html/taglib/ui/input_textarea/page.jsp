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
String cssClass = GetterUtil.getString((String)request.getAttribute("liferay-ui:input-textarea:cssClass"));
String param = (String)request.getAttribute("liferay-ui:input-textarea:param");
String defaultValue = (String)request.getAttribute("liferay-ui:input-textarea:defaultValue");
boolean disabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:input-textarea:disabled"));

String value = ParamUtil.getString(request, param, defaultValue);
%>

<textarea class="lfr-textarea <%= cssClass %>" <%= disabled ? "disabled=\"disabled\"" : "" %> id="<%= namespace %><%= param %>" name="<%= namespace %><%= param %>" wrap="soft" onKeyDown="Liferay.Util.disableEsc();"><%= value %></textarea>

<aui:script use="aui-char-counter">
	new A.CharCounter(
		{
			input: '#<%= namespace %><%= param %>',
			maxLength: <%= ModelHintsConstants.TEXTAREA_MAX_LENGTH %>
		}
	);
</aui:script>