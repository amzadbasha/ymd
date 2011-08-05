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

<%@ include file="/html/portlet/enterprise_admin/init.jsp" %>

<%
String className = (String)request.getAttribute("addresses.className");
long classPK = (Long)request.getAttribute("addresses.classPK");

List<Address> addresses = Collections.EMPTY_LIST;

int[] addressesIndexes = null;

String addressesIndexesParam = ParamUtil.getString(request, "addressesIndexes");

if (Validator.isNotNull(addressesIndexesParam)) {
	addresses = new ArrayList<Address>();

	addressesIndexes = StringUtil.split(addressesIndexesParam, 0);

	for (int addressesIndex : addressesIndexes) {
		addresses.add(new AddressImpl());
	}
}
else {
	if (classPK > 0) {
		addresses = AddressServiceUtil.getAddresses(className, classPK);

		addressesIndexes = new int[addresses.size()];

		for (int i = 0; i < addresses.size() ; i++) {
			addressesIndexes[i] = i;
		}
	}

	if (addresses.isEmpty()) {
		addresses = new ArrayList<Address>();

		addresses.add(new AddressImpl());

		addressesIndexes = new int[] {0};
	}

	if (addressesIndexes == null) {
		addressesIndexes = new int[0];
	}
}
%>

<liferay-ui:error-marker key="errorSection" value="addresses" />

<h3><liferay-ui:message key="addresses" /></h3>

<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />
<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-zip" />
<liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />
<liferay-ui:error key="<%= NoSuchListTypeException.class.getName() + className + ListTypeConstants.ADDRESS %>" message="please-select-a-type" />
<liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />

<aui:fieldset>

	<%
	for (int i = 0; i < addressesIndexes.length; i++) {
		int addressesIndex = addressesIndexes[i];

		Address address = addresses.get(i);

		long countryId = ParamUtil.getLong(request, "addressCountryId" + addressesIndex, address.getCountryId());
		long regionId = ParamUtil.getLong(request, "addressRegionId" + addressesIndex, address.getRegionId());
	%>

		<aui:model-context bean="<%= address %>" model="<%= Address.class %>" />

		<div class="lfr-form-row">
			<div class="row-fields">
				<aui:column>
					<aui:input name='<%= "addressId" + addressesIndex %>' type="hidden" value="<%= address.getAddressId() %>" />

					<aui:input fieldParam='<%= "addressStreet1_" + addressesIndex %>' name="street1" />

					<aui:input fieldParam='<%= "addressStreet2_" + addressesIndex %>' name="street2" />

					<aui:input fieldParam='<%= "addressStreet3_" + addressesIndex %>' name="street3" />

					<aui:select label="country" name='<%= "addressCountryId" + addressesIndex %>' />

					<aui:select label="region" name='<%= "addressRegionId" + addressesIndex %>' />
				</aui:column>

				<aui:column>
					<aui:select label="type" name='<%= "addressTypeId" + addressesIndex %>' listType="<%= className + ListTypeConstants.ADDRESS %>" />

					<aui:input name="zip" fieldParam='<%= "addressZip" + addressesIndex %>' />

					<aui:input name="city" fieldParam='<%= "addressCity" + addressesIndex %>' />

					<aui:input checked="<%= address.isPrimary() %>" cssClass="primary-ctrl" id='<%= "addressPrimary" + addressesIndex %>' inlineLabel="left" label="primary" name="addressPrimary" type="radio" value="<%= addressesIndex %>" />

					<aui:input cssClass="mailing-ctrl" fieldParam='<%= "addressMailing" + addressesIndex %>' inlineLabel="left" name="mailing" />
				</aui:column>
			</div>
		</div>

		<aui:script use="liferay-dynamic-select">
			new Liferay.DynamicSelect(
				[
					{
						select: '<portlet:namespace />addressCountryId<%= addressesIndex %>',
						selectData: Liferay.Address.getCountries,
						selectDesc: 'name',
						selectId: 'countryId',
						selectVal: '<%= countryId %>'
					},
					{
						select: '<portlet:namespace />addressRegionId<%= addressesIndex %>',
						selectData: Liferay.Address.getRegions,
						selectDesc: 'name',
						selectId: 'regionId',
						selectVal: '<%= regionId %>'
					}
				]
			);
		</aui:script>

	<%
	}
	%>

</aui:fieldset>

<aui:script use="liferay-auto-fields,liferay-dynamic-select">
	Liferay.once(
		'enterpriseAdmin:revealaddresses',
		function() {
			var addresses = new Liferay.AutoFields(
				{
					contentBox: '#addresses > fieldset',
					fieldIndexes: '<portlet:namespace />addressesIndexes',
					on: {
						'clone': function(event) {
							var row = event.row;
							var guid = event.guid;

							var dynamicSelects = row.one('select[data-componentType=dynamic_select]');

							if (dynamicSelects) {
								dynamicSelects.detach('change');
							}

							new Liferay.DynamicSelect(
								[
									{
										select: '<portlet:namespace />addressCountryId' + guid,
										selectData: Liferay.Address.getCountries,
										selectDesc: 'name',
										selectId: 'countryId',
										selectVal: ''
									},
									{
										select: '<portlet:namespace />addressRegionId' + guid,
										selectData: Liferay.Address.getRegions,
										selectDesc: 'name',
										selectId: 'regionId',
										selectVal: ''
									}
								]
							);
						}
					}
				}
			).render();
		}
	);
</aui:script>