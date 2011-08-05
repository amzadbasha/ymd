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

<%@ page import="com.liferay.portlet.messageboards.util.BBCodeUtil" %>

<%
for (int i = 0; i < BBCodeUtil.EMOTICONS.length; i++) {
	String image = StringUtil.replace(BBCodeUtil.EMOTICONS[i][0], "@theme_images_path@", themeDisplay.getPathThemeImages());
%>

	<a class="lfr-button emoticon" emoticonCode="<%= BBCodeUtil.EMOTICONS[i][1] %>"><%= image %></a>

<%
}
%>