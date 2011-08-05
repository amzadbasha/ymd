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

<%@ include file="/html/taglib/ui/asset_categories_navigation/init.jsp" %>

<%
boolean hidePortletWhenEmpty = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:asset-tags-navigation:hidePortletWhenEmpty"));
long[] vocabularyIds = (long[])request.getAttribute("liferay-ui:asset-tags-navigation:vocabularyIds");

long categoryId = ParamUtil.getLong(request, "categoryId");

List<AssetVocabulary> vocabularies = null;

if (vocabularyIds == null) {
	vocabularies = AssetVocabularyServiceUtil.getGroupsVocabularies(new long[] {scopeGroupId, themeDisplay.getCompanyGroupId()});
}
else {
	vocabularies = new ArrayList<AssetVocabulary>();

	for (long vocabularyId : vocabularyIds) {
		try {
			vocabularies.add(AssetVocabularyServiceUtil.getVocabulary(vocabularyId));
		}
		catch (NoSuchVocabularyException nsve) {
		}
	}
}

PortletURL portletURL = renderResponse.createRenderURL();
%>

<liferay-ui:panel-container cssClass="taglib-asset-categories-navigation" extended="<%= true %>" id='<%= namespace + "taglibAssetCategoriesNavigationPanel" %>' persistState="<%= true %>">

	<%
	for (int i = 0; i < vocabularies.size(); i++) {
		AssetVocabulary vocabulary = vocabularies.get(i);

		String vocabularyNavigation = _buildVocabularyNavigation(vocabulary, categoryId, portletURL);

		if (Validator.isNotNull(vocabularyNavigation)) {
			hidePortletWhenEmpty = false;
	%>

			<liferay-ui:panel collapsible="<%= false %>" extended="<%= true %>" persistState="<%= true %>" title="<%= vocabulary.getName() %>">
				<%= vocabularyNavigation %>
			</liferay-ui:panel>

	<%
		}
	}
	%>

</liferay-ui:panel-container>

<%
if (hidePortletWhenEmpty) {
	renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
%>

	<div class="portlet-msg-info">
		<liferay-ui:message key="there-are-no-categories" />
	</div>

<%
}
%>

<aui:script use="aui-tree-view">
	var treeViews = A.all('#<%= namespace %>taglibAssetCategoriesNavigationPanel .lfr-asset-category-list-container');

	treeViews.each(
		function(treeEl) {
			new A.TreeView(
				{
					boundingBox: treeEl,
					contentBox: treeEl.one('.lfr-asset-category-list'),
					type: 'normal'
				}
			).render();
		}
	);
</aui:script>

<%!
private void _buildCategoriesNavigation(List<AssetCategory> categories, long curCategoryId, PortletURL portletURL, StringBundler sb) throws Exception {
	for (AssetCategory category : categories) {
		long categoryId = category.getCategoryId();
		String name = category.getName();

		List<AssetCategory> categoriesChildren = AssetCategoryServiceUtil.getChildCategories(category.getCategoryId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		sb.append("<li><span>");

		if (categoryId == curCategoryId) {
			sb.append("<strong>");
			sb.append(name);
			sb.append("</strong>");
		}
		else {
			portletURL.setParameter("categoryId", String.valueOf(categoryId));

			sb.append("<a href=\"");
			sb.append(portletURL.toString());
			sb.append("\">");
			sb.append(name);
			sb.append("</a>");
		}

		sb.append("</span>");

		if (!categoriesChildren.isEmpty()) {
			sb.append("<ul>");

			_buildCategoriesNavigation(categoriesChildren, curCategoryId, portletURL, sb);

			sb.append("</ul>");
		}

		sb.append("</li>");
	}
}

private String _buildVocabularyNavigation(AssetVocabulary vocabulary, long categoryId, PortletURL portletURL) throws Exception {
	List<AssetCategory> categories = AssetCategoryServiceUtil.getVocabularyRootCategories(vocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

	if (categories.isEmpty()) {
		return null;
	}

	StringBundler sb = new StringBundler();

	sb.append("<div class=\"lfr-asset-category-list-container\"><ul class=\"lfr-asset-category-list\">");

	_buildCategoriesNavigation(categories, categoryId, portletURL, sb);

	sb.append("</ul></div>");

	return sb.toString();
}
%>