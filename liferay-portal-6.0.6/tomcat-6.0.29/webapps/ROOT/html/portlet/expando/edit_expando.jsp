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

<%@ include file="/html/portlet/expando/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String modelResource = ParamUtil.getString(request, "modelResource");
String modelResourceName = ResourceActionsUtil.getModelResource(pageContext, modelResource);

ExpandoColumn column = (ExpandoColumn)request.getAttribute(WebKeys.EXPANDO_COLUMN);

long columnId = BeanParamUtil.getLong(column, request, "columnId");
int type = BeanParamUtil.getInteger(column, request, "type");

ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(company.getCompanyId(), modelResource);

UnicodeProperties properties = new UnicodeProperties(true);
Serializable defaultValue = null;

if (column != null) {
	properties = expandoBridge.getAttributeProperties(column.getName());
	defaultValue = expandoBridge.getAttributeDefault(column.getName());
}

boolean propertyHidden = GetterUtil.getBoolean(properties.get(ExpandoColumnConstants.PROPERTY_HIDDEN));
boolean propertyVisibleWithUpdatePermission = GetterUtil.getBoolean(properties.get(ExpandoColumnConstants.PROPERTY_VISIBLE_WITH_UPDATE_PERMISSION));
boolean propertySelection = GetterUtil.getBoolean(properties.get(ExpandoColumnConstants.PROPERTY_SELECTION));
boolean propertyIndexable = GetterUtil.getBoolean(properties.get(ExpandoBridgeIndexer.INDEXABLE));
boolean propertySecret = GetterUtil.getBoolean(properties.get(ExpandoColumnConstants.PROPERTY_SECRET));
int propertyHeight = GetterUtil.getInteger(properties.get(ExpandoColumnConstants.PROPERTY_HEIGHT));
int propertyWidth = GetterUtil.getInteger(properties.get(ExpandoColumnConstants.PROPERTY_WIDTH));

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/expando/view_attributes");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("modelResource", modelResource);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= modelResourceName + ": " + ((column == null) ? LanguageUtil.get(locale, "new-custom-field") : column.getName()) %>'
/>

<portlet:actionURL var="editExpandoURL">
	<portlet:param name="struts_action" value="/expando/edit_expando" />
</portlet:actionURL>

<aui:form action="<%= editExpandoURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveExpando();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="columnId" type="hidden" value="<%= columnId %>" />
	<aui:input name="modelResource" type="hidden" value="<%= modelResource %>" />

	<liferay-ui:error exception="<%= ColumnNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= ColumnTypeException.class %>" message="please-select-a-valid-type" />
	<liferay-ui:error exception="<%= DuplicateColumnNameException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= ValueDataException.class %>" message="please-enter-a-valid-value" />

	<aui:model-context bean="<%= column %>" model="<%= ExpandoColumn.class %>" />

	<aui:fieldset>
		<c:choose>
			<c:when test="<%= column != null %>">
				<aui:field-wrapper helpMessage="custom-field-key-help" label="key">
					<aui:input name="name" type="hidden" value="<%= column.getName() %>" />

					<%= HtmlUtil.escape(column.getName()) %>
				</aui:field-wrapper>
			</c:when>
			<c:otherwise>
				<aui:input helpMessage="custom-field-key-help" label="key" name="name" />
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="<%= column != null %>">
				<aui:field-wrapper label="type">
					<aui:input name="type" type="hidden" value="<%= type %>" />

					<liferay-ui:message key="<%= ExpandoColumnConstants.getTypeLabel(type) %>" />
				</aui:field-wrapper>
			</c:when>
			<c:otherwise>
				<aui:select helpMessage="custom-field-type-help" name="type">
					<optgroup label="<liferay-ui:message key="presets" />">
						<aui:option label="selection-of-integer-values" value="PresetSelectionIntegerArray()" />
						<aui:option label="selection-of-decimal-values" value="PresetSelectionDoubleArray()" />
						<aui:option label="selection-of-text-values" value="PresetSelectionStringArray()" />
						<aui:option label="text-box" value="PresetTextBox()" />
						<aui:option label="text-box-indexed" value="PresetTextBoxIndexed()" />
						<aui:option label="text-field-secret" value="PresetTextFieldSecret()" />
						<aui:option label="text-field-indexed" selected="<%= true %>" value="PresetTextFieldIndexed()" />
					</optgroup>
					<optgroup label="<liferay-ui:message key="primitives" />">

						<%
						for (int curType : ExpandoColumnConstants.TYPES) {
							if ((curType == ExpandoColumnConstants.BOOLEAN_ARRAY) || (curType == ExpandoColumnConstants.DATE_ARRAY)) {
								continue;
							}
						%>

							<aui:option label="<%= ExpandoColumnConstants.getTypeLabel(curType) %>" value="<%= curType %>" />

						<%
						}
						%>

					</optgroup>
				</aui:select>
			</c:otherwise>
		</c:choose>

		<c:if test="<%= column != null %>">
			<c:choose>
				<c:when test="<%= type == ExpandoColumnConstants.BOOLEAN %>">

					<%
					boolean curValue = ((Boolean)defaultValue).booleanValue();
					%>

					<aui:select name="defaultValue">
						<aui:option label="true" selected="<%= curValue %>" value="1" />
						<aui:option label="false" selected="<%= !curValue %>" value="0" />
					</aui:select>
				</c:when>
				<c:when test="<%= type == ExpandoColumnConstants.BOOLEAN_ARRAY %>">
				</c:when>
				<c:when test="<%= type == ExpandoColumnConstants.DATE %>">

					<%
					Calendar defaultValueDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

					if (defaultValue != null) {
						defaultValueDate.setTime((Date)defaultValue);
					}
					%>

					<aui:field-wrapper label="default-value">
						<liferay-ui:input-date
							dayValue="<%= defaultValueDate.get(Calendar.DATE) %>"
							dayParam="defaultValueDay"
							disabled="<%= false %>"
							firstDayOfWeek="<%= defaultValueDate.getFirstDayOfWeek() - 1 %>"
							monthParam="defaultValueMonth"
							monthValue="<%= defaultValueDate.get(Calendar.MONTH) %>"
							yearParam="defaultValueYear"
							yearValue="<%= defaultValueDate.get(Calendar.YEAR) %>"
							yearRangeStart="<%= defaultValueDate.get(Calendar.YEAR) - 100 %>"
							yearRangeEnd="<%= defaultValueDate.get(Calendar.YEAR) + 100 %>"
						/>

						&nbsp;

						<liferay-ui:input-time
							hourParam="defaultValueHour"
							hourValue="<%= defaultValueDate.get(Calendar.HOUR) %>"
							minuteParam="defaultValueMinute"
							minuteValue="<%= defaultValueDate.get(Calendar.MINUTE) %>"
							minuteInterval="<%= 1 %>"
							amPmParam="defaultValueAmPm"
							amPmValue="<%= defaultValueDate.get(Calendar.AM_PM) %>"
							disabled="<%= false %>"
						/>
					</aui:field-wrapper>
				</c:when>
				<c:when test="<%= type == ExpandoColumnConstants.DATE_ARRAY %>">
				</c:when>
				<c:when test="<%= type == ExpandoColumnConstants.DOUBLE_ARRAY %>">
					<aui:input cssClass="lfr-textarea-container" name="defaultValue" type="textarea" value="<%= StringUtil.merge((double[])defaultValue, StringPool.NEW_LINE) %>" />
				</c:when>
				<c:when test="<%= type == ExpandoColumnConstants.FLOAT_ARRAY %>">
					<aui:input cssClass="lfr-textarea-container" name="defaultValue" type="textarea" value="<%= StringUtil.merge((float[])defaultValue, StringPool.NEW_LINE) %>" />
				</c:when>
				<c:when test="<%= type == ExpandoColumnConstants.INTEGER_ARRAY %>">
					<aui:input cssClass="lfr-textarea-container" name="defaultValue" type="textarea" value="<%= StringUtil.merge((int[])defaultValue, StringPool.NEW_LINE) %>" />
				</c:when>
				<c:when test="<%= type == ExpandoColumnConstants.LONG_ARRAY %>">
					<aui:input cssClass="lfr-textarea-container" name="defaultValue" type="textarea" value="<%= StringUtil.merge((long[])defaultValue, StringPool.NEW_LINE) %>" />
				</c:when>
				<c:when test="<%= type == ExpandoColumnConstants.SHORT_ARRAY %>">
					<aui:input cssClass="lfr-textarea-container" name="defaultValue" type="textarea" value="<%= StringUtil.merge((short[])defaultValue, StringPool.NEW_LINE) %>" />
				</c:when>
				<c:when test="<%= type == ExpandoColumnConstants.STRING_ARRAY %>">
					<aui:input cssClass="lfr-textarea-container" name="defaultValue" type="textarea" value="<%= StringUtil.merge((String[])defaultValue, StringPool.NEW_LINE) %>" />
				</c:when>
				<c:otherwise>
					<aui:input cssClass="lfr-input-text-container" name="defaultValue" type="text" value="<%= String.valueOf(defaultValue) %>" />
				</c:otherwise>
			</c:choose>
		</c:if>
	</aui:fieldset>

	<c:if test="<%= column != null %>">
		<aui:fieldset label="properties">
			<aui:input name="PropertyName--hidden--" type="hidden" value="hidden" />

			<aui:select helpMessage="custom-field-hidden-help" label="hidden" name="Property--hidden--">
				<aui:option label="true" selected="<%= propertyHidden %>" value="1" />
				<aui:option label="false" selected="<%= !propertyHidden %>" value="0" />
			</aui:select>

			<aui:input name="PropertyName--visible-with-update-permission--" type="hidden" value="visible-with-update-permission" />

			<aui:select helpMessage="custom-field-visible-with-update-permission-help" label="visible-with-update-permission" name="Property--visible-with-update-permission--">
				<aui:option label="true" selected="<%= propertyVisibleWithUpdatePermission %>" value="1" />
				<aui:option label="false" selected="<%= !propertyVisibleWithUpdatePermission %>" value="0" />
			</aui:select>

			<aui:input name="PropertyName--indexable--" type="hidden" value="indexable" />

			<aui:select helpMessage="custom-field-indexable-help" label="searchable" name="Property--indexable--">
				<aui:option label="true" selected="<%= propertyIndexable %>" value="1" />
				<aui:option label="false" selected="<%= !propertyIndexable %>" value="0" />
			</aui:select>

			<c:if test="<%= (type == ExpandoColumnConstants.DOUBLE_ARRAY) || (type == ExpandoColumnConstants.FLOAT_ARRAY) || (type == ExpandoColumnConstants.INTEGER_ARRAY) || (type == ExpandoColumnConstants.LONG_ARRAY) || (type == ExpandoColumnConstants.SHORT_ARRAY) || (type == ExpandoColumnConstants.STRING_ARRAY) %>">
				<aui:input name="PropertyName--selection--" type="hidden" value="selection" />

				<aui:select helpMessage="custom-field-selection-help" label="selection" name="Property--selection--">
					<aui:option label="true" selected="<%= propertySelection %>" value="1" />
					<aui:option label="false" selected="<%= !propertySelection %>" value="0" />
				</aui:select>
			</c:if>

			<c:if test="<%= type == ExpandoColumnConstants.STRING %>">
				<aui:input name="PropertyName--secret--" type="hidden" value="secret" />

				<aui:select helpMessage="custom-field-secret-help" label="secret" name="Property--secret--">
					<aui:option label="true" selected="<%= propertySecret %>" value="1" />
					<aui:option label="false" selected="<%= !propertySecret %>" value="0" />
				</aui:select>

				<aui:input name="PropertyName--height--" type="hidden" value="height" />

				<aui:input cssClass="lfr-input-text short-input-text" helpMessage="custom-field-height-help" label="height" name="Property--height--" type="text" value="<%= propertyHeight %>" />

				<aui:input name="PropertyName--width--" type="hidden" value="width" />

				<aui:input cssClass="lfr-input-text short-input-text" helpMessage="custom-field-height-help" label="width" name="Property--width--" type="text" value="<%= propertyWidth %>" />
			</c:if>
		</aui:fieldset>
	</c:if>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveExpando(options) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (column == null) ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) && (column == null) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</c:if>
</aui:script>

<%
PortalUtil.addPortletBreadcrumbEntry(request, modelResourceName, portletURL.toString());

if (column != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, column.getName(), null);
}

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, ((column == null) ? "add-attribute" : "edit")), currentURL);
%>