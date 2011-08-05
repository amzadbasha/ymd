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
String className = (String)request.getAttribute("websites.className");
long classPK = (Long)request.getAttribute("websites.classPK");

List<Website> websites = Collections.EMPTY_LIST;

int[] websitesIndexes = null;

String websitesIndexesParam = ParamUtil.getString(request, "websitesIndexes");

if (Validator.isNotNull(websitesIndexesParam)) {
	websites = new ArrayList<Website>();

	websitesIndexes = StringUtil.split(websitesIndexesParam, 0);

	for (int websitesIndex : websitesIndexes) {
		websites.add(new WebsiteImpl());
	}
}
else {
	if (classPK > 0) {
		websites = WebsiteServiceUtil.getWebsites(className, classPK);

		websitesIndexes = new int[websites.size()];

		for (int i = 0; i < websites.size(); i++) {
			websitesIndexes[i] = i;
		}
	}

	if (websites.isEmpty()) {
		websites = new ArrayList<Website>();

		websites.add(new WebsiteImpl());

		websitesIndexes = new int[] {0};
	}

	if (websitesIndexes == null) {
		websitesIndexes = new int[0];
	}
}
%>

<liferay-ui:error-marker key="errorSection" value="websites" />

<h3><liferay-ui:message key="websites" /></h3>

<liferay-ui:error key="<%= NoSuchListTypeException.class.getName() + className + ListTypeConstants.WEBSITE %>" message="please-select-a-type" />
<liferay-ui:error exception="<%= WebsiteURLException.class %>" message="please-enter-a-valid-url" />

<aui:fieldset>

	<%
	for (int i = 0; i < websitesIndexes.length; i++) {
		int websitesIndex = websitesIndexes[i];

		Website website = websites.get(i);
	%>

		<aui:model-context bean="<%= website %>" model="<%= Website.class %>" />

		<div class="lfr-form-row lfr-form-row-inline">
			<div class="row-fields">

				<aui:input name='<%= "websiteId" + websitesIndex %>' type="hidden" />

				<aui:input fieldParam='<%= "websiteUrl" + websitesIndex %>' name="url" />

				<aui:select label="type" name='<%= "websiteTypeId" + websitesIndex %>' listType="<%= className + ListTypeConstants.WEBSITE %>" />

				<aui:input checked="<%= website.isPrimary() %>" cssClass="primary-ctrl" id='<%= "websitePrimary" + websitesIndex %>' inlineLabel="left" label="primary" name="websitePrimary" type="radio" value="<%= websitesIndex %>" />
			</div>
		</div>

	<%
	}
	%>

</aui:fieldset>

<aui:script use="liferay-auto-fields">
	Liferay.once(
		'enterpriseAdmin:revealwebsites',
		function() {
			new Liferay.AutoFields(
				{
					contentBox: '#websites > fieldset',
					fieldIndexes: '<portlet:namespace />websitesIndexes'
				}
			).render();
		}
	);
</aui:script>