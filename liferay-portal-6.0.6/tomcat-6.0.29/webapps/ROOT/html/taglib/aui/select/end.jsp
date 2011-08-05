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
boolean changesContext = GetterUtil.getBoolean((String)request.getAttribute("aui:select:changesContext"));
String helpMessage = GetterUtil.getString((String)request.getAttribute("aui:select:helpMessage"));
String id = namespace + GetterUtil.getString((String)request.getAttribute("aui:select:id"));
String inlineLabel = GetterUtil.getString((String)request.getAttribute("aui:select:inlineLabel"));
String label = GetterUtil.getString((String)request.getAttribute("aui:select:label"));
String suffix = GetterUtil.getString((String)request.getAttribute("aui:select:suffix"));
%>

			</select>
		</span>

		<c:if test="<%= Validator.isNotNull(suffix) %>">
			<span class="aui-suffix">
				<liferay-ui:message key="<%= suffix %>" />
			</span>
		</c:if>

		<c:if test='<%= inlineLabel.equals("right") %>'>
			<label <%= _buildLabel(inlineLabel, true, id) %>>
				<liferay-ui:message key="<%= label %>" />

				<c:if test="<%= Validator.isNotNull(helpMessage) %>">
					<liferay-ui:icon-help message="<%= helpMessage %>" />
				</c:if>

				<c:if test="<%= changesContext %>">
					<span class="aui-helper-hidden-accessible"><liferay-ui:message key="changing-the-value-of-this-field-will-reload-the-page" />)</span>
				</c:if>
			</label>
		</c:if>
	</span>
</span>