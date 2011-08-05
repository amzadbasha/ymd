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
Object bean = request.getAttribute("liferay-ui:write:bean");
String property = (String)request.getAttribute("liferay-ui:write:property");
%>

<c:choose>
	<c:when test="<%= bean instanceof Organization %>">

		<%
		Organization organization = (Organization)bean;
		%>

		<c:choose>
			<c:when test='<%= property.equals("country") %>'>

				<%
				Address address = organization.getAddress();

				Country country = address.getCountry();

				String countryName = country.getName();

				if (Validator.isNull(countryName)) {
					try {
						country = CountryServiceUtil.getCountry(organization.getCountryId());

						countryName = LanguageUtil.get(pageContext, country.getName());
					}
					catch (Exception e) {
					}
				}
				%>

				<%= countryName %>
			</c:when>
			<c:when test='<%= property.equals("region") %>'>

				<%
				Address address = organization.getAddress();

				Region region = address.getRegion();

				String regionName = region.getName();

				if (Validator.isNull(regionName)) {
					try {
						region = RegionServiceUtil.getRegion(organization.getRegionId());

						regionName = LanguageUtil.get(pageContext, region.getName());
					}
					catch (Exception e) {
					}
				}
				%>

				<%= regionName %>
			</c:when>
		</c:choose>
	</c:when>
	<c:when test="<%= bean instanceof User %>">

		<%
		User user2 = (User)bean;
		%>

		<c:choose>
			<c:when test='<%= property.equals("organizations") %>'>

				<%
				List<Organization> organizations = user2.getOrganizations();
				%>

				<%= HtmlUtil.escape(ListUtil.toString(organizations, "name", StringPool.COMMA_AND_SPACE)) %>
			</c:when>
		</c:choose>
	</c:when>
</c:choose>