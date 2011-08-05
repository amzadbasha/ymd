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

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
List<Organization> organizations = (List<Organization>)request.getAttribute("user.organizations");

String className = (String)request.getAttribute("addresses.className");
long classPK = (Long)request.getAttribute("addresses.classPK");

List<Address> personalAddresses = Collections.EMPTY_LIST;
List<Address> organizationAddresses = new ArrayList<Address>();

if (classPK > 0) {
	personalAddresses = AddressServiceUtil.getAddresses(className, classPK);
}

for (int i = 0; i < organizations.size(); i++) {
	organizationAddresses.addAll(AddressServiceUtil.getAddresses(Organization.class.getName(), organizations.get(i).getOrganizationId()));
}
%>

<c:if test="<%= !personalAddresses.isEmpty() || !organizationAddresses.isEmpty() %>">
	<h3><liferay-ui:message key="address" /></h3>

	<c:if test="<%= !organizationAddresses.isEmpty() %>">
		<div>
			<h4><liferay-ui:message key="organization-address" /></h4>

			<ul class="property-list">

			<%
			for (Address address: organizationAddresses) {
				String street1 = address.getStreet1();
				String street2 = address.getStreet2();
				String street3 = address.getStreet3();

				String zipCode = address.getZip();
				String city = address.getCity();

				String mailingName = LanguageUtil.get(pageContext, address.getType().getName());
			%>

				<li class="<%= address.isPrimary() ? "primary" : "" %>">
					<em class="mailing-name"><%= mailingName %></em>

					<c:if test="<%= Validator.isNotNull(street1) %>">
						<%= HtmlUtil.escape(street1) %><br />
					</c:if>

					<c:if test="<%= Validator.isNotNull(street2) %>">
						<%= HtmlUtil.escape(street2) %><br />
					</c:if>

					<c:if test="<%= Validator.isNotNull(street3) %>">
						<%= HtmlUtil.escape(street3) %><br />
					</c:if>

					<c:if test="<%= Validator.isNotNull(zipCode) %>">
						<%= zipCode %>,
					</c:if>

					<c:if test="<%= Validator.isNotNull(city) %>">
						<%= HtmlUtil.escape(city) %>
					</c:if>

					<c:if test="<%= address.isMailing() %>">(<liferay-ui:message key="mailing" />)</c:if>
				</li>

			<%
			}
			%>

			</ul>
		</div>
	</c:if>

	<c:if test="<%= !personalAddresses.isEmpty() %>">
		<div>
			<h4><liferay-ui:message key="personal-address" /></h4>

			<ul class="property-list">

			<%
			for (Address address: personalAddresses) {
				String street1 = address.getStreet1();
				String street2 = address.getStreet2();
				String street3 = address.getStreet3();

				String zipCode = address.getZip();
				String city = address.getCity();

				String mailingName = LanguageUtil.get(pageContext, address.getType().getName());
			%>

				<li class="<%= address.isPrimary() ? "primary" : "" %>">
					<em class="mailing-name"><%= mailingName %></em>
					<c:if test="<%= Validator.isNotNull(street1) %>">
						<%= HtmlUtil.escape(street1) %><br />
					</c:if>

					<c:if test="<%= Validator.isNotNull(street2) %>">
						<%= HtmlUtil.escape(street2) %><br />
					</c:if>

					<c:if test="<%= Validator.isNotNull(street3) %>">
						<%= HtmlUtil.escape(street3) %><br />
					</c:if>

					<c:if test="<%= Validator.isNotNull(zipCode) %>">
						<%= zipCode %>,
					</c:if>

					<c:if test="<%= Validator.isNotNull(city) %>">
						<%= HtmlUtil.escape(city) %>
					</c:if>

					<c:if test="<%= address.isMailing() %>">(<liferay-ui:message key="mailing" />)</c:if>
				</li>

			<%
			}
			%>

			</ul>
		</div>
	</c:if>
</c:if>