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

<%@ include file="/html/portlet/journal/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String originalRedirect = ParamUtil.getString(request, "originalRedirect", StringPool.BLANK);

if (originalRedirect.equals(StringPool.BLANK)) {
	originalRedirect = redirect;
}
else {
	redirect = originalRedirect;
}

JournalTemplate template = (JournalTemplate)request.getAttribute(WebKeys.JOURNAL_TEMPLATE);

long groupId = BeanParamUtil.getLong(template, request, "groupId", scopeGroupId);

Group group = GroupLocalServiceUtil.getGroup(groupId);

String templateId = BeanParamUtil.getString(template, request, "templateId");
String newTemplateId = ParamUtil.getString(request, "newTemplateId");

String structureId = BeanParamUtil.getString(template, request, "structureId");

String structureName = StringPool.BLANK;

if (Validator.isNotNull(structureId)) {
	try {
		JournalStructure structure = JournalStructureLocalServiceUtil.getStructure(groupId, structureId);

		structureName = structure.getName();
	}
	catch (NoSuchStructureException nsse) {
	}
}

String xslContent = request.getParameter("xslContent");

String xsl = xslContent;

if (xslContent != null) {
	xsl = JS.decodeURIComponent(xsl);
}
else {
	xsl = BeanParamUtil.getString(template, request, "xsl");
}

String langType = BeanParamUtil.getString(template, request, "langType", JournalTemplateConstants.LANG_TYPE_VM);

boolean cacheable = BeanParamUtil.getBoolean(template, request, "cacheable");

if (template == null) {
	cacheable = true;
}

boolean smallImage = BeanParamUtil.getBoolean(template, request, "smallImage");
String smallImageURL = BeanParamUtil.getString(template, request, "smallImageURL");
%>

<aui:form method="post" name="fm2">
	<input name="xslContent" type="hidden" value="" />
	<input name="formatXsl" type="hidden" value="" />
	<input name="langType" type="hidden" value="" />
</aui:form>

<portlet:actionURL var="editTemplateURL">
	<portlet:param name="struts_action" value="/journal/edit_template" />
</portlet:actionURL>

<aui:form action="<%= editTemplateURL %>" enctype="multipart/form-data" method="post" name="fm1" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveTemplate();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="originalRedirect" type="hidden" value="<%= originalRedirect %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
	<aui:input name="templateId" type="hidden" value="<%= templateId %>" />
	<aui:input name="xslContent" type="hidden" value="<%= JS.encodeURIComponent(xsl) %>" />
	<aui:input name="saveAndContinue" type="hidden" />

	<liferay-ui:header
		backURL="<%= redirect %>"
		title='<%= (template != null) ? template.getName() : "new-template" %>'
	/>

	<liferay-ui:error exception="<%= DuplicateTemplateIdException.class %>" message="please-enter-a-unique-id" />
	<liferay-ui:error exception="<%= TemplateDescriptionException.class %>" message="please-enter-a-valid-description" />
	<liferay-ui:error exception="<%= TemplateIdException.class %>" message="please-enter-a-valid-id" />
	<liferay-ui:error exception="<%= TemplateNameException.class %>" message="please-enter-a-valid-name" />

	<liferay-ui:error exception="<%= TemplateSmallImageNameException.class %>">

		<%
		String[] imageExtensions = PrefsPropsUtil.getStringArray(PropsKeys.JOURNAL_IMAGE_EXTENSIONS, ",");
		%>

		<liferay-ui:message key="image-names-must-end-with-one-of-the-following-extensions" /> <%= StringUtil.merge(imageExtensions, StringPool.COMMA) %>.
	</liferay-ui:error>

	<liferay-ui:error exception="<%= TemplateSmallImageSizeException.class %>" message="please-enter-a-file-with-a-valid-file-size" />
	<liferay-ui:error exception="<%= TemplateXslException.class %>" message="please-enter-a-valid-script-template" />

	<aui:model-context bean="<%= template %>" model="<%= JournalTemplate.class %>" />

	<aui:fieldset>
		<c:choose>
			<c:when test="<%= template == null %>">
				<c:choose>
					<c:when test="<%= PropsValues.JOURNAL_TEMPLATE_FORCE_AUTOGENERATE_ID %>">
						<aui:input name="newTemplateId" type="hidden" />
						<aui:input name="autoTemplateId" type="hidden" value="<%= true %>" />
					</c:when>
					<c:otherwise>
						<aui:input cssClass="lfr-input-text-container" label="id" name="newTemplateId" field="templateId" fieldParam="newTemplateId" value="<%= newTemplateId %>" />

						<aui:input label="autogenerate-id" name="autoTemplateId" type="checkbox" />
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<aui:field-wrapper label="id">
					<%= templateId %>
				</aui:field-wrapper>
			</c:otherwise>
		</c:choose>

		<aui:input cssClass="lfr-input-text-container" name="name" />

		<aui:input cssClass="lfr-textarea-container" name="description" />

		<aui:input helpMessage="journal-template-cacheable-help" inlineLabel="right" name="cacheable" value="<%= new Boolean(cacheable) %>" />

		<c:if test="<%= template != null %>">
			<aui:field-wrapper label="url">
				<liferay-ui:input-resource url='<%= themeDisplay.getPortalURL() + themeDisplay.getPathMain() + "/journal/get_template?groupId=" + groupId + "&templateId=" + templateId %>' />
			</aui:field-wrapper>

			<c:if test="<%= portletDisplay.isWebDAVEnabled() %>">
				<aui:field-wrapper label="webdav-url">
					<liferay-ui:input-resource url='<%= themeDisplay.getPortalURL() + "/tunnel-web/secure/webdav" + group.getFriendlyURL() + "/journal/Templates/" + templateId %>' />
				</aui:field-wrapper>
			</c:if>
		</c:if>

		<aui:field-wrapper label="structure">
			<aui:input name="structureId" type="hidden" value="<%= structureId %>" />

			<c:choose>
				<c:when test="<%= (template == null) || (Validator.isNotNull(structureId)) %>">
					<portlet:renderURL var="editStructureURL">
						<portlet:param name="struts_action" value="/journal/edit_structure" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
						<portlet:param name="structureId" value="<%= structureId %>" />
					</portlet:renderURL>

					<aui:a href="<%= editStructureURL %>" label="<%= structureName %>" id="structureName" />
				</c:when>
				<c:otherwise>
					<aui:a href="" id="structureName" />
				</c:otherwise>
			</c:choose>

			<c:if test="<%= (template == null) || (Validator.isNull(template.getStructureId())) %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "openStructureSelector();" %>'  type="button" value="select" />

				<aui:button disabled="<%= Validator.isNull(structureId) %>" name="removeStructureButton" onClick='<%= renderResponse.getNamespace() + "removeStructure();" %>' type="button" value="remove" />
			</c:if>
		</aui:field-wrapper>

		<aui:select label="language-type" name="langType">

			<%
			for (int i = 0; i < JournalTemplateConstants.LANG_TYPES.length; i++) {
			%>

				<aui:option label="<%= JournalTemplateConstants.LANG_TYPES[i].toUpperCase() %>" selected="<%= langType.equals(JournalTemplateConstants.LANG_TYPES[i]) %>" value="<%= JournalTemplateConstants.LANG_TYPES[i] %>" />

			<%
			}
			%>

		</aui:select>

		<aui:field-wrapper label="script">
			<aui:input label="" name="xsl" type="file" />

			<aui:button name="editorButton" type="button" value="launch-editor" />

			<c:if test="<%= template != null %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "downloadTemplateContent();" %>' type="button" value="download" />
			</c:if>
		</aui:field-wrapper>

		<aui:input inlineLabel="right" label="format-script" name="formatXsl" type="checkbox" />

		<aui:input cssClass="lfr-input-text-container" label="small-image-url" name="smallImageURL" />

		<span style="font-size: xx-small;">-- <%= LanguageUtil.get(pageContext, "or").toUpperCase() %> --</span>

		<aui:input cssClass="lfr-input-text-container" label="small-image" name="smallFile" type="file" />

		<aui:input inlineLabel="right" name="smallImage"  />

		<c:if test="<%= template == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= JournalTemplate.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button onClick='<%= renderResponse.getNamespace() + "saveAndContinueTemplate();" %>' type="button" value="save-and-continue" />

		<aui:button onClick="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />downloadTemplateContent() {
		document.<portlet:namespace />fm2.action = "<%= themeDisplay.getPathMain() %>/journal/get_template_content";
		document.<portlet:namespace />fm2.target = "_self";
		document.<portlet:namespace />fm2.xslContent.value = document.<portlet:namespace />fm1.<portlet:namespace />xslContent.value;
		document.<portlet:namespace />fm2.formatXsl.value = document.<portlet:namespace />fm1.<portlet:namespace />formatXsl.value;
		document.<portlet:namespace />fm2.langType.value = document.<portlet:namespace />fm1.<portlet:namespace />langType.value;
		document.<portlet:namespace />fm2.submit();
	}

	function <portlet:namespace />openStructureSelector() {
		var structureWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/journal/select_structure" /><portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" /></portlet:renderURL>', 'structure', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');

		structureWindow.focus();
	}

	function <portlet:namespace />removeStructure() {
		document.<portlet:namespace />fm1.<portlet:namespace />structureId.value = "";

		var nameEl = document.getElementById("<portlet:namespace />structureName");

		nameEl.href = "#";
		nameEl.innerHTML = "";

		document.getElementById("<portlet:namespace />removeStructureButton").disabled = true;
	}

	function <portlet:namespace />saveAndContinueTemplate() {
		document.<portlet:namespace />fm1.<portlet:namespace />saveAndContinue.value = "1";
		<portlet:namespace />saveTemplate();
	}

	function <portlet:namespace />saveTemplate() {
		document.<portlet:namespace />fm1.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (template == null) ? Constants.ADD : Constants.UPDATE %>";

		<c:if test="<%= template == null %>">
			document.<portlet:namespace />fm1.<portlet:namespace />templateId.value = document.<portlet:namespace />fm1.<portlet:namespace />newTemplateId.value;
		</c:if>

		submitForm(document.<portlet:namespace />fm1);
	}

	function <portlet:namespace />selectStructure(structureId, structureName) {
		document.<portlet:namespace />fm1.<portlet:namespace />structureId.value = structureId;

		var nameEl = document.getElementById("<portlet:namespace />structureName");

		nameEl.href = "<portlet:renderURL><portlet:param name="struts_action" value="/journal/edit_structure" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" /></portlet:renderURL>&<portlet:namespace />structureId=" + structureId;
		nameEl.innerHTML = structureName + "&nbsp;";

		document.getElementById("<portlet:namespace />removeStructureButton").disabled = false;
	}

	Liferay.Util.disableToggleBoxes('<portlet:namespace />autoTemplateIdCheckbox','<portlet:namespace />newTemplateId', true);

	Liferay.Util.inlineEditor(
		{
			button: '#<portlet:namespace />editorButton',
			textarea: '<portlet:namespace />xslContent',
			url: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="struts_action" value="/journal/edit_template_xsl" /></portlet:renderURL>&<portlet:namespace />langType=' + document.<portlet:namespace />fm1.<portlet:namespace />langType.value
		}
	);

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		<c:choose>
			<c:when test="<%= PropsValues.JOURNAL_TEMPLATE_FORCE_AUTOGENERATE_ID %>">
				Liferay.Util.focusFormField(document.<portlet:namespace />fm1.<portlet:namespace />name);
			</c:when>
			<c:otherwise>
				Liferay.Util.focusFormField(document.<portlet:namespace />fm1.<portlet:namespace /><%= (template == null) ? "newTemplateId" : "name" %>);
			</c:otherwise>
		</c:choose>
	</c:if>
</aui:script>