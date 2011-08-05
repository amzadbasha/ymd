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

<%@ include file="/html/portlet/language/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveConfiguration();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<aui:fieldset label="languages">
		<aui:input name="languageIds" type="hidden" />

		<%
		Set availableLanguageIdsSet = SetUtil.fromArray(availableLanguageIds);

		// Left list

		List leftList = new ArrayList();

		for (int i = 0; i < languageIds.length; i++) {
			String languageId = languageIds[i];

			leftList.add(new KeyValuePair(languageId, LocaleUtil.fromLanguageId(languageId).getDisplayName(locale)));
		}

		// Right list

		List rightList = new ArrayList();

		Arrays.sort(languageIds);

		Iterator itr = availableLanguageIdsSet.iterator();

		while (itr.hasNext()) {
			String languageId = (String)itr.next();

			if (Arrays.binarySearch(languageIds, languageId) < 0) {
				rightList.add(new KeyValuePair(languageId, LocaleUtil.fromLanguageId(languageId).getDisplayName(locale)));
			}
		}

		rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
		%>

		<liferay-ui:input-move-boxes
			leftTitle="current"
			rightTitle="available"
			leftBoxName="currentLanguageIds"
			rightBoxName="availableLanguageIds"
			leftReorder="true"
			leftList="<%= leftList %>"
			rightList="<%= rightList %>"
		/>
	</aui:fieldset>

	<aui:fieldset>
		<aui:select name="displayStyle">
			<aui:option label="icon" selected="<%= displayStyle == LanguageTag.LIST_ICON %>" value="<%= LanguageTag.LIST_ICON %>" />
			<aui:option label="long-text" selected="<%= displayStyle == LanguageTag.LIST_LONG_TEXT %>" value="<%= LanguageTag.LIST_LONG_TEXT %>" />
			<aui:option label="short-text" selected="<%= displayStyle == LanguageTag.LIST_SHORT_TEXT %>" value="<%= LanguageTag.LIST_SHORT_TEXT %>" />
			<aui:option label="select-box" selected="<%= displayStyle == LanguageTag.SELECT_BOX %>" value="<%= LanguageTag.SELECT_BOX %>" />
		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />saveConfiguration',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace />languageIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentLanguageIds);

			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>