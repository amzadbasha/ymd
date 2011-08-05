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

<%@ include file="/html/taglib/init.jsp" %>

<%@ page import="com.liferay.portlet.social.model.SocialActivity" %>
<%@ page import="com.liferay.portlet.social.model.SocialActivityFeedEntry" %>
<%@ page import="com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil" %>
<%@ page import="com.liferay.portlet.social.service.SocialActivityLocalServiceUtil" %>

<%@ page import="com.liferay.util.RSSUtil" %>

<%@ page import="com.sun.syndication.feed.synd.SyndContent" %>
<%@ page import="com.sun.syndication.feed.synd.SyndContentImpl" %>
<%@ page import="com.sun.syndication.feed.synd.SyndEntry" %>
<%@ page import="com.sun.syndication.feed.synd.SyndEntryImpl" %>
<%@ page import="com.sun.syndication.feed.synd.SyndFeed" %>
<%@ page import="com.sun.syndication.feed.synd.SyndFeedImpl" %>

<%
String className = (String)request.getAttribute("liferay-ui:social-activities:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-ui:social-activities:classPK"));
List<SocialActivity> activities = (List<SocialActivity>)request.getAttribute("liferay-ui:social-activities:activities");
boolean feedEnabled = GetterUtil.getBoolean((String)request.getAttribute("liferay-ui:social-activities:feedEnabled"));
String feedTitle = (String)request.getAttribute("liferay-ui:social-activities:feedTitle");
String feedLink = (String)request.getAttribute("liferay-ui:social-activities:feedLink");
String feedLinkMessage = (String)request.getAttribute("liferay-ui:social-activities:feedLinkMessage");

if (activities == null) {
	activities = SocialActivityLocalServiceUtil.getActivities(0, className, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
}

Format dateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("MMMM d", locale, timeZone);
Format timeFormatDate = FastDateFormatFactoryUtil.getTime(locale, timeZone);
%>

<c:choose>
	<c:when test="<%= themeDisplay.isStateExclusive() %>">

		<%
		SyndFeed syndFeed = new SyndFeedImpl();

		syndFeed.setFeedType(RSSUtil.DEFAULT_FEED_TYPE);
		syndFeed.setLink(feedLink);
		syndFeed.setTitle(feedTitle);
		syndFeed.setDescription(feedTitle);

		List<SyndEntry> entries = new ArrayList<SyndEntry>();

		syndFeed.setEntries(entries);

		for (SocialActivity activity : activities) {
			SocialActivityFeedEntry activityFeedEntry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, themeDisplay);

			if (activityFeedEntry == null) {
				continue;
			}

			SyndEntry syndEntry = new SyndEntryImpl();

			syndEntry.setTitle(HtmlUtil.extractText(activityFeedEntry.getTitle()));

			if (Validator.isNotNull(activityFeedEntry.getLink())) {
				syndEntry.setLink(activityFeedEntry.getLink());
			}

			syndEntry.setPublishedDate(new Date(activity.getCreateDate()));

			SyndContent syndContent = new SyndContentImpl();

			syndContent.setType(RSSUtil.DEFAULT_ENTRY_TYPE);
			syndContent.setValue(activityFeedEntry.getBody());

			syndEntry.setDescription(syndContent);

			entries.add(syndEntry);
		}

		String feedXML = StringPool.BLANK;

		try {
			feedXML = RSSUtil.export(syndFeed);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		response.setContentType(ContentTypes.TEXT_XML_UTF8);
		%>

		<%= feedXML %>
	</c:when>
	<c:otherwise>
		<div class="taglib-social-activities">
			<table>

			<%
			boolean hasActivities = false;

			boolean firstDaySeparator = true;

			Date now = new Date();

			int daysBetween = -1;

			for (SocialActivity activity : activities) {
				SocialActivityFeedEntry activityFeedEntry = SocialActivityInterpreterLocalServiceUtil.interpret(activity, themeDisplay);

				if (activityFeedEntry == null) {
					continue;
				}

				if (!hasActivities) {
					hasActivities = true;
				}

				Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), activityFeedEntry.getPortletId());

				int curDaysBetween = DateUtil.getDaysBetween(new Date(activity.getCreateDate()), now, timeZone);
			%>

				<c:if test="<%= curDaysBetween > daysBetween %>">

					<%
					daysBetween = curDaysBetween;
					%>

					<tr>
						<td class="day-separator" colspan="2">
							<c:choose>
								<c:when test="<%= curDaysBetween == 0 %>">
									<liferay-ui:message key="today" />
								</c:when>
								<c:when test="<%= curDaysBetween == 1 %>">
									<liferay-ui:message key="yesterday" />
								</c:when>
								<c:otherwise>
									<%= dateFormatDate.format(activity.getCreateDate()) %>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:if>

				<tr>
					<td class="portlet-icon">
						<liferay-portlet:icon-portlet portlet="<%= portlet %>" />
					</td>
					<td class="activity-data">
						<div class="activity-title">
							<%= activityFeedEntry.getTitle() %>
						</div>
						<div class="activity-body">
							<span class="time"><%= timeFormatDate.format(activity.getCreateDate()) %></span>

							<%= activityFeedEntry.getBody() %>
						</div>
					</td>
				</tr>

			<%
			}
			%>

			</table>

			<c:if test="<%= !hasActivities %>">
				<liferay-ui:message key="there-are-no-recent-activities" />
			</c:if>
		</div>

		<c:if test="<%= feedEnabled && !activities.isEmpty() %>">
			<div class="separator"><!-- --></div>

			<liferay-ui:icon
				image="rss"
				label="<%= true %>"
				message="<%= feedLinkMessage %>"
				method="get"
				target="_blank"
				url="<%= feedLink %>"
			/>
		</c:if>
	</c:otherwise>
</c:choose>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.taglib.ui.social_activities.page.jsp");
%>