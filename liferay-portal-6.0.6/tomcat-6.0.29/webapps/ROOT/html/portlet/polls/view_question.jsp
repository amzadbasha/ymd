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

<%@ include file="/html/portlet/polls/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

PollsQuestion question = (PollsQuestion)request.getAttribute(WebKeys.POLLS_QUESTION);

question = question.toEscapedModel();

List choices = PollsChoiceLocalServiceUtil.getChoices(question.getQuestionId());

boolean hasVoted = PollsUtil.hasVoted(request, question.getQuestionId());

boolean viewResults = ParamUtil.getBoolean(request, "viewResults", false);

if (viewResults && !PollsQuestionPermission.contains(permissionChecker, question, ActionKeys.UPDATE)) {
	viewResults = false;
}
%>

<portlet:actionURL var="viewQuestionActionURL">
	<portlet:param name="struts_action" value="/polls/view_question" />
</portlet:actionURL>

<aui:form action="<%= viewQuestionActionURL %>" method="post" name="fm">
	<portlet:renderURL var="viewQuestionRenderURL">
		<portlet:param name="struts_action" value="/polls/view_question" />
		<portlet:param name="questionId" value="<%= String.valueOf(question.getQuestionId()) %>" />
	</portlet:renderURL>

	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>" />
	<aui:input name="redirect" type="hidden" value="<%= viewQuestionRenderURL %>" />
	<aui:input name="questionId" type="hidden" value="<%= question.getQuestionId() %>" />

	<liferay-ui:error exception="<%= DuplicateVoteException.class %>" message="you-may-only-vote-once" />
	<liferay-ui:error exception="<%= NoSuchChoiceException.class %>" message="please-select-an-option" />

	<aui:fieldset>
 		<liferay-ui:header
			backURL="<%= redirect %>"
			title="<%= question.getTitle(locale) %>"
		/>

		<span style="font-size: x-small;">
			<%= HtmlUtil.escape(question.getDescription(locale)) %>
		</span>

		<br /><br />

		<c:choose>
			<c:when test='<%= !viewResults && !question.isExpired() && !hasVoted && PollsQuestionPermission.contains(permissionChecker, question, ActionKeys.ADD_VOTE) %>'>
				<aui:field-wrapper>

					<%
					Iterator itr = choices.iterator();

					while (itr.hasNext()) {
						PollsChoice choice = (PollsChoice)itr.next();

						choice = choice.toEscapedModel();
					%>

						<aui:input inlineLabel="left" label='<%= "<strong>" + choice.getName() + ".</strong> " + HtmlUtil.escape(choice.getDescription(locale)) %>' name="choiceId" type="radio" value="<%= choice.getChoiceId() %>" />

					<%
					}
					%>

				</aui:field-wrapper>

				<c:if test="<%= PollsQuestionPermission.contains(permissionChecker, question, ActionKeys.UPDATE) %>">
					<portlet:renderURL var="viewResultsURL">
						<portlet:param name="struts_action" value="/polls/view_question" />
						<portlet:param name="redirect" value="<%= redirect %>" />
						<portlet:param name="questionId" value="<%= String.valueOf(question.getQuestionId()) %>" />
						<portlet:param name="viewResults" value="1" />
					</portlet:renderURL>

					<liferay-ui:icon
						image="view"
						label="<%= true %>"
						message="view-results"
						url="<%= viewResultsURL %>"
					/>
				</c:if>

				<aui:button-row>
					<aui:button type="submit" value="vote" />

					<aui:button onClick="<%= redirect %>" type="cancel" />
				</aui:button-row>

				<%
				PortalUtil.addPortletBreadcrumbEntry(request, question.getTitle(locale), currentURL);
				%>

			</c:when>
			<c:otherwise>
				<%@ include file="/html/portlet/polls/view_question_results.jspf" %>

				<portlet:renderURL var="viewQuestionURL">
					<portlet:param name="struts_action" value="/polls/view_question" />
					<portlet:param name="redirect" value="<%= redirect %>" />
					<portlet:param name="questionId" value="<%= String.valueOf(question.getQuestionId()) %>" />
				</portlet:renderURL>

				<aui:button-row>
					<c:choose>
						<c:when test="<%= !question.isExpired() && !hasVoted && PollsQuestionPermission.contains(permissionChecker, question, ActionKeys.ADD_VOTE) %>">
							<aui:button onClick="<%= viewQuestionURL %>" value="back-to-vote" />
						</c:when>
					</c:choose>
				</aui:button-row>

				<%
				PortalUtil.addPortletBreadcrumbEntry(request, question.getTitle(locale), viewQuestionURL.toString());
				PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "results"), currentURL);
				%>

			</c:otherwise>
		</c:choose>
	</aui:fieldset>
</aui:form>