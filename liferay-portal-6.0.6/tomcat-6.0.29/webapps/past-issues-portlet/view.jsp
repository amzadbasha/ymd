<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />
<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %>
<%@ page import="com.liferay.portal.model.Group" %>
<%@ page import="com.liferay.portal.service.GroupLocalServiceUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="java.util.*" %>

<%!
Group group=null;
Group frndURL=null;
String[] communityName;
String[] communityURL;
final String communityHome="http://localhost:8080/web";
int communityTotal;
int count;
%>
<%
List<Group> groups = GroupLocalServiceUtil.search(
        PortalUtil.getCompanyId(renderRequest), null, null,
        null, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
System.out.println("groups size="+groups.size());
communityTotal=groups.size();
communityName=new String[groups.size()];
communityURL=new String[groups.size()];
for (int i = 0; i < groups.size(); i++) {  
        group = (Group)groups.get(i);
		System.out.println("groups id="+group.getGroupId());
		frndURL=GroupLocalServiceUtil.getGroup(group.getGroupId());
        System.out.println("Friendly URL="+frndURL.getFriendlyURL());
		System.out.println("groups Name="+group.getDescriptiveName());
		communityName[i]=(String)group.getDescriptiveName();
		communityURL[i]=communityHome+String.valueOf(frndURL.getFriendlyURL());
		System.out.println("Friendly URL="+communityHome+String.valueOf(frndURL.getFriendlyURL()));
}
%>
<ul>
<%
for(count=0;count<communityName.length;count++){
%>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li bgcolor="#8BB381"><a href="<%=communityURL[count]%>"><%=communityName[count]%></a></li>
<%}%>
</ul>
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Showing <%=communityTotal%> results