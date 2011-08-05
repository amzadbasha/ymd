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

<%@ include file="/html/portlet/translator/init.jsp" %>

<%
Translation translation = (Translation)request.getAttribute(WebKeys.TRANSLATOR_TRANSLATION);

if (translation == null) {
	translation = new Translation(PropsUtil.get(PropsKeys.TRANSLATOR_DEFAULT_LANGUAGES), StringPool.BLANK, StringPool.BLANK);
}
%>

<portlet:actionURL var="portletURL" />

<aui:form accept-charset="UTF-8" action="<%= portletURL %>" method="post" name="fm">
	<c:if test="<%= Validator.isNotNull(translation.getToText()) %>">
		<%= HtmlUtil.escape(translation.getToText()) %>
	</c:if>

	<aui:fieldset>
		<aui:input cssClass="lfr-textarea-container" label="" name="text" type="textarea" value="<%= translation.getFromText() %>" wrap="soft" />

		<aui:select label="" name="id">
			<aui:option label="en_zh" selected='<%= translation.getTranslationId().equals("en_zh") %>' />
			<aui:option label="en_zt" selected='<%= translation.getTranslationId().equals("en_zt") %>' />
			<aui:option label="en_nl" selected='<%= translation.getTranslationId().equals("en_nl") %>' />
			<aui:option label="en_fr" selected='<%= translation.getTranslationId().equals("en_fr") %>' />
			<aui:option label="en_de" selected='<%= translation.getTranslationId().equals("en_de") %>' />
			<aui:option label="en_it" selected='<%= translation.getTranslationId().equals("en_it") %>' />
			<aui:option label="en_ja" selected='<%= translation.getTranslationId().equals("en_ja") %>' />
			<aui:option label="en_ko" selected='<%= translation.getTranslationId().equals("en_ko") %>' />
			<aui:option label="en_pt" selected='<%= translation.getTranslationId().equals("en_pt") %>' />
			<aui:option label="en_es" selected='<%= translation.getTranslationId().equals("en_es") %>' />
			<aui:option label="zh_en" selected='<%= translation.getTranslationId().equals("zh_en") %>' />
			<aui:option label="zt_en" selected='<%= translation.getTranslationId().equals("zt_en") %>' />
			<aui:option label="nl_en" selected='<%= translation.getTranslationId().equals("nl_en") %>' />
			<aui:option label="fr_en" selected='<%= translation.getTranslationId().equals("fr_en") %>' />
			<aui:option label="fr_de" selected='<%= translation.getTranslationId().equals("fr_de") %>' />
			<aui:option label="de_en" selected='<%= translation.getTranslationId().equals("de_en") %>' />
			<aui:option label="de_fr" selected='<%= translation.getTranslationId().equals("de_fr") %>' />
			<aui:option label="it_en" selected='<%= translation.getTranslationId().equals("it_en") %>' />
			<aui:option label="ja_en" selected='<%= translation.getTranslationId().equals("ja_en") %>' />
			<aui:option label="ko_en" selected='<%= translation.getTranslationId().equals("ko_en") %>' />
			<aui:option label="pt_en" selected='<%= translation.getTranslationId().equals("pt_en") %>' />
			<aui:option label="ru_en" selected='<%= translation.getTranslationId().equals("ru_en") %>' />
			<aui:option label="es_en" selected='<%= translation.getTranslationId().equals("es_en") %>' />
		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="translate" />
	</aui:button-row>
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<aui:script>
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />text);
	</aui:script>
</c:if>