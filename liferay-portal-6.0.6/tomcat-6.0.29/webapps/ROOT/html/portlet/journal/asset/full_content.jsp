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
AssetRendererFactory assetRendererFactory = (AssetRendererFactory)request.getAttribute(WebKeys.ASSET_RENDERER_FACTORY);

JournalArticle article = (JournalArticle)request.getAttribute(WebKeys.JOURNAL_ARTICLE);
JournalArticleResource articleResource = JournalArticleResourceLocalServiceUtil.getArticleResource(article.getResourcePrimKey());

String templateId = (String)request.getAttribute(WebKeys.JOURNAL_TEMPLATE_ID);
String languageId = LanguageUtil.getLanguageId(request);
int articlePage = ParamUtil.getInteger(request, "page", 1);
String xmlRequest = PortletRequestUtil.toXML(renderRequest, renderResponse);

JournalArticleDisplay articleDisplay = null;

if (article.isApproved()) {
	articleDisplay = JournalContentUtil.getDisplay(articleResource.getGroupId(), articleResource.getArticleId(), templateId, null, languageId, themeDisplay, articlePage, xmlRequest);
}
else {
	articleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(article, null, null, languageId, 1, null, themeDisplay);
}
%>

<div class="journal-content-article">
	<%= articleDisplay.getContent() %>
</div>

<c:if test="<%= articleDisplay.isPaginate() %>">

	<%
	String pageRedirect = ParamUtil.getString(request, "redirect");

	if (Validator.isNull(pageRedirect)) {
		pageRedirect = currentURL;
	}

	int cur = ParamUtil.getInteger(request, "cur");

	PortletURL articlePageURL = renderResponse.createRenderURL();

	articlePageURL.setParameter("struts_action", "/asset_publisher/view_content");
	articlePageURL.setParameter("type", assetRendererFactory.getType());
	articlePageURL.setParameter("redirect", pageRedirect);
	articlePageURL.setParameter("urlTitle", articleDisplay.getUrlTitle());
	articlePageURL.setParameter("cur", String.valueOf(cur));
	%>

	<br />

	<liferay-ui:page-iterator
		cur="<%= articleDisplay.getCurrentPage() %>"
		curParam="page"
		delta="<%= 1 %>"
		maxPages="<%= 25 %>"
		total="<%= articleDisplay.getNumberOfPages() %>"
		type="article"
		url="<%= articlePageURL.toString() %>"
	/>

	<br />
</c:if>