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

<%@ include file="/html/portlet/init.jsp" %>

<%@ page import="com.liferay.portlet.asset.model.AssetVocabulary" %>
<%@ page import="com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.asset.service.AssetVocabularyServiceUtil" %>

<%
PortletPreferences preferences = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

List<AssetVocabulary> vocabularies = AssetVocabularyServiceUtil.getGroupsVocabularies(new long[] {scopeGroupId, themeDisplay.getCompanyGroupId()});

long[] availableAssetVocabularyIds = new long[vocabularies.size()];

for (int i = 0; i < vocabularies.size(); i++) {
	AssetVocabulary vocabulary = vocabularies.get(i);

	availableAssetVocabularyIds[i] = vocabulary.getVocabularyId();
}

boolean allAssetVocabularies = GetterUtil.getBoolean(preferences.getValue("all-asset-vocabularies", Boolean.TRUE.toString()));

long[] assetVocabularyIds = availableAssetVocabularyIds;

if (!allAssetVocabularies && preferences.getValues("asset-vocabulary-ids", null) != null) {
	assetVocabularyIds = GetterUtil.getLongValues(preferences.getValues("asset-vocabulary-ids", null));
}
%>