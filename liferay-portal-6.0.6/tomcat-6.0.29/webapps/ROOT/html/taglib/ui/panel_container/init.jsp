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
boolean accordion = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:panel-container:accordion"));
String cssClass = (String)request.getAttribute("liferay-ui:panel-container:cssClass");
Boolean extended = (Boolean)request.getAttribute("liferay-ui:panel-container:extended");
String id = (String)request.getAttribute("liferay-ui:panel-container:id");
boolean persistState = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:panel-container:persistState"));
%>