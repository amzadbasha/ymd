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

<%@ include file="/html/portlet/portlet_configuration/init.jsp" %>

<c:choose>
	<c:when test="<%= (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 5) || (PropsValues.PERMISSIONS_USER_CHECK_ALGORITHM == 6) %>">
		<liferay-util:include page="/html/portlet/portlet_configuration/edit_permissions_algorithm_5_to_6.jsp" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portlet/portlet_configuration/edit_permissions_algorithm_1_to_4.jsp" />
	</c:otherwise>
</c:choose>