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

<%@ include file="/html/portlet/asset_publisher/init.jsp" %>

<%
if (mergeUrlTags) {
	String[] compilerTagNames = (String[])request.getAttribute(WebKeys.TAGS_COMPILER_ENTRIES);

	Set<String> layoutTagNames = AssetUtil.getLayoutTagNames(request);

	if (!layoutTagNames.isEmpty()) {
		compilerTagNames = ArrayUtil.append(compilerTagNames, layoutTagNames.toArray(new String[layoutTagNames.size()]));
	}

	String titleEntry = null;

	if ((compilerTagNames != null) && (compilerTagNames.length > 0)) {
		String[] newAssetTagNames = ArrayUtil.append(allAssetTagNames, compilerTagNames);

		allAssetTagNames = newAssetTagNames;

		titleEntry = compilerTagNames[compilerTagNames.length - 1];
	}

	String portletTitle = HtmlUtil.unescape(portletDisplay.getTitle());

	portletTitle = AssetUtil.substituteTagPropertyVariables(scopeGroupId, titleEntry, portletTitle);

	renderResponse.setTitle(portletTitle);
}

allAssetTagNames = ArrayUtil.distinct(allAssetTagNames, new StringComparator());

for (String curAssetTagName : allAssetTagNames) {
	try {
		AssetTag assetTag = AssetTagLocalServiceUtil.getTag(scopeGroupId, curAssetTagName);

		AssetTagProperty journalTemplateIdProperty = AssetTagPropertyLocalServiceUtil.getTagProperty(assetTag.getTagId(), "journal-template-id");

		String journalTemplateId = journalTemplateIdProperty.getValue();

		request.setAttribute(WebKeys.JOURNAL_TEMPLATE_ID, journalTemplateId);

		break;
	}
	catch (NoSuchTagException nste) {
	}
	catch (NoSuchTagPropertyException nstpe) {
	}
}

if (enableTagBasedNavigation && selectionStyle.equals("manual") && ((assetEntryQuery.getAllCategoryIds().length > 0) || (assetEntryQuery.getAllTagIds().length > 0))) {
	selectionStyle = "dynamic";
}

Group group = themeDisplay.getScopeGroup();
%>

<c:if test="<%= (group != null) && (!group.hasStagingGroup() || group.isStagingGroup()) %>">
	<aui:form name="fm">
		<div class="add-asset-selector">
			<%@ include file="/html/portlet/asset_publisher/add_asset.jspf" %>
		</div>
	</aui:form>
</c:if>

<%
PortletURL portletURL = renderResponse.createRenderURL();

SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, delta, portletURL, null, null);

if (!paginationType.equals("none")) {
	searchContainer.setDelta(delta);
	searchContainer.setDeltaConfigurable(false);
}
%>

<c:if test='<%= (assetCategoryId > 0) && selectionStyle.equals("dynamic") %>'>
	<h1 class="asset-categorization-title">
		<%= LanguageUtil.format(pageContext, "content-with-x-x", new String[] {assetVocabularyName, assetCategoryName}) %>
	</h1>

	<%
	AssetUtil.addPortletBreadcrumbEntries(assetCategoryId, request, portletURL);
	%>

</c:if>

<c:if test='<%= Validator.isNotNull(assetTagName) && selectionStyle.equals("dynamic") %>'>
	<h1 class="asset-categorization-title">
		<%= HtmlUtil.escape(LanguageUtil.format(pageContext, "content-with-tag-x", assetTagName)) %>
	</h1>

	<%
	PortalUtil.addPortletBreadcrumbEntry(request, assetTagName, currentURL);
	%>

</c:if>

<c:choose>
	<c:when test='<%= selectionStyle.equals("dynamic") %>'>
		<%@ include file="/html/portlet/asset_publisher/view_dynamic_list.jspf" %>
	</c:when>
	<c:when test='<%= selectionStyle.equals("manual") %>'>
		<%@ include file="/html/portlet/asset_publisher/view_manual.jspf" %>
	</c:when>
</c:choose>

<c:if test='<%= !paginationType.equals("none") && (searchContainer.getTotal() > searchContainer.getResults().size()) %>'>
	<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" type="<%= paginationType %>" />
</c:if>

<c:if test="<%= enableRSS %>">
	<portlet:resourceURL var="rssURL">
		<portlet:param name="struts_action" value="/asset_publisher/rss" />
	</portlet:resourceURL>

	<div class="subscribe">
		<liferay-ui:icon
			image="rss"
			label="<%= true %>"
			method="get"
			target="_blank"
			url="<%= rssURL %>"
		/>
	</div>

	<liferay-util:html-top>
		<link href="<%= HtmlUtil.escape(rssURL) %>" rel="alternate" title="RSS" type="application/rss+xml" />
	</liferay-util:html-top>
</c:if>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portlet.asset_publisher.view.jsp");
%>