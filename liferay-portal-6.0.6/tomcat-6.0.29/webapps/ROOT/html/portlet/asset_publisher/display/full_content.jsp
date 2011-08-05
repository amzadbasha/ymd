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
String redirect = ParamUtil.getString(request, "redirect");

List results = (List)request.getAttribute("view.jsp-results");

int assetEntryIndex = ((Integer)request.getAttribute("view.jsp-assetEntryIndex")).intValue();

AssetEntry assetEntry = (AssetEntry)request.getAttribute("view.jsp-assetEntry");
AssetRendererFactory assetRendererFactory = (AssetRendererFactory)request.getAttribute("view.jsp-assetRendererFactory");
AssetRenderer assetRenderer = (AssetRenderer)request.getAttribute("view.jsp-assetRenderer");

String title = (String)request.getAttribute("view.jsp-title");

boolean show = ((Boolean)request.getAttribute("view.jsp-show")).booleanValue();
boolean print = ((Boolean)request.getAttribute("view.jsp-print")).booleanValue();

request.setAttribute("view.jsp-showIconLabel", true);
%>

<c:if test="<%= assetRenderer.hasViewPermission(permissionChecker) %>">
	<c:if test="<%= showAssetTitle %>">
		<liferay-ui:header
			backURL="<%= print ? null : redirect %>"
			title="<%= title %>"
		/>
	</c:if>

	<div class="asset-full-content <%= showAssetTitle ? "show-asset-title" : "no-title" %>">
		<c:if test="<%= !print %>">
			<liferay-util:include page="/html/portlet/asset_publisher/asset_actions.jsp" />
		</c:if>

		<c:if test="<%= (enableConversions && assetRenderer.isConvertible()) || (enablePrint && assetRenderer.isPrintable()) || (showAvailableLocales && assetRenderer.isLocalizable()) %>">
			<div class="asset-user-actions">
				<c:if test="<%= enablePrint %>">
					<div class="print-action">
						<%@ include file="/html/portlet/asset_publisher/asset_print.jspf" %>
					</div>
				</c:if>

				<c:if test="<%= (enableConversions && assetRenderer.isConvertible()) && !print %>">

					<%
					String languageId = LanguageUtil.getLanguageId(request);

					PortletURL exportAssetURL = assetRenderer.getURLExport((LiferayPortletRequest)renderRequest, (LiferayPortletResponse)renderResponse);
					%>

					<div class="export-actions">
						<%@ include file="/html/portlet/asset_publisher/asset_export.jspf" %>
					</div>
				</c:if>
				<c:if test="<%= (showAvailableLocales && assetRenderer.isLocalizable()) && !print %>">

					<%
					String languageId = LanguageUtil.getLanguageId(request);

					String[] availableLocales = assetRenderer.getAvailableLocales();
					%>

					<c:if test="<%= availableLocales.length > 1 %>">
						<c:if test="<%= enableConversions || enablePrint %>">
							<div class="locale-separator"> </div>
						</c:if>

						<div class="locale-actions">
							<liferay-ui:language languageIds="<%= availableLocales %>" displayStyle="<%= 0 %>" />
						</div>
					</c:if>
				</c:if>
			</div>
		</c:if>

		<%
		AssetEntryServiceUtil.incrementViewCounter(assetEntry.getClassName(), assetEntry.getClassPK());

		if (showContextLink) {
			if (PortalUtil.getPlidFromPortletId(assetRenderer.getGroupId(), assetRendererFactory.getPortletId()) == 0) {
				showContextLink = false;
			}
		}

		PortletURL viewFullContentURL = renderResponse.createRenderURL();

		viewFullContentURL.setParameter("struts_action", "/asset_publisher/view_content");
		viewFullContentURL.setParameter("type", assetRendererFactory.getType());

		if (Validator.isNotNull(assetRenderer.getUrlTitle())) {
			if (assetRenderer.getGroupId() != scopeGroupId) {
				viewFullContentURL.setParameter("groupId", String.valueOf(assetRenderer.getGroupId()));
			}

			viewFullContentURL.setParameter("urlTitle", assetRenderer.getUrlTitle());
		}

		String viewFullContent = viewFullContentURL.toString();

		viewFullContent = HttpUtil.setParameter(viewFullContent, "redirect", currentURL);
		%>

		<div class="asset-content">

			<%
			String path = assetRenderer.render(renderRequest, renderResponse, AssetRenderer.TEMPLATE_FULL_CONTENT);

			request.setAttribute(WebKeys.ASSET_RENDERER_FACTORY, assetRendererFactory);
			request.setAttribute(WebKeys.ASSET_RENDERER, assetRenderer);
			%>

			<liferay-util:include page="<%= path %>" portletId="<%= assetRendererFactory.getPortletId() %>" />

			<c:if test="<%= enableFlags %>">
				<div class="asset-flag">
					<liferay-ui:flags
						className="<%= assetEntry.getClassName() %>"
						classPK="<%= assetEntry.getClassPK() %>"
						contentTitle="<%= assetRenderer.getTitle() %>"
						reportedUserId="<%= assetRenderer.getUserId() %>"
					/>
				</div>
			</c:if>

			<c:if test="<%= enableRatings %>">
			<div class="asset-ratings">
				<liferay-ui:ratings
						className="<%= assetEntry.getClassName() %>"
						classPK="<%= assetEntry.getClassPK() %>"
					/>
				</div>
			</c:if>

			<c:if test="<%= showContextLink && !print && assetEntry.isVisible() %>">
				<div class="asset-more">
					<a href="<%= assetRenderer.getURLViewInContext((LiferayPortletRequest)renderRequest, (LiferayPortletResponse)renderResponse, viewFullContentURL.toString()) %>"><liferay-ui:message key="<%= assetRenderer.getViewInContextMessage() %>" /> &raquo;</a>
				</div>
			</c:if>

			<c:if test="<%= Validator.isNotNull(assetRenderer.getDiscussionPath()) && enableComments %>">
				<br />

				<portlet:actionURL var="discussionURL">
					<portlet:param name="struts_action" value='<%= "/asset_publisher/" + assetRenderer.getDiscussionPath() %>' />
				</portlet:actionURL>

				<liferay-ui:discussion
					className="<%= assetEntry.getClassName() %>"
					classPK="<%= assetEntry.getClassPK() %>"
					formAction="<%= discussionURL %>"
					formName='<%= "fm" + assetEntry.getClassPK() %>'
					ratingsEnabled="<%= enableCommentRatings %>"
					redirect="<%= currentURL %>"
					subject="<%= assetRenderer.getTitle() %>"
					userId="<%= assetRenderer.getUserId() %>"
				/>
			</c:if>
		</div>

		<c:if test="<%= show %>">
			<div class="asset-metadata">
				<%@ include file="/html/portlet/asset_publisher/asset_metadata.jspf" %>
			</div>
		</c:if>
	</div>

	<c:choose>
		<c:when test="<%= !showAssetTitle && ((assetEntryIndex + 1) < results.size()) %>">
			<div class="separator"><!-- --></div>
		</c:when>
		<c:when test="<%= (assetEntryIndex + 1) == results.size() %>">
			<div class="final-separator"><!-- --></div>
		</c:when>
	</c:choose>
</c:if>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portlet.asset_publisher.display_full_content.jsp");
%>