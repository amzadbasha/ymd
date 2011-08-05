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

long questionId = BeanParamUtil.getLong(question, request, "questionId");

boolean neverExpire = ParamUtil.getBoolean(request, "neverExpire", true);

Calendar expirationDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

expirationDate.add(Calendar.MONTH, 1);

if (question != null) {
	if (question.getExpirationDate() != null) {
		neverExpire = false;

		expirationDate.setTime(question.getExpirationDate());
	}
}

List choices = new ArrayList();

int oldChoicesCount = 0;

if (question != null) {
	choices = PollsChoiceLocalServiceUtil.getChoices(questionId);

	oldChoicesCount = choices.size();
}

int choicesCount = ParamUtil.getInteger(request, "choicesCount", choices.size());

if (choicesCount < 2) {
	choicesCount = 2;
}

int choiceName = ParamUtil.getInteger(request, "choiceName");

boolean deleteChoice = false;

if (choiceName > 0) {
	deleteChoice = true;
}
%>

<portlet:actionURL var="editQuestionURL">
	<portlet:param name="struts_action" value="/polls/edit_question" />
</portlet:actionURL>

<aui:form action="<%= editQuestionURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveQuestion();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="questionId" type="hidden" value="<%= questionId %>" />
	<aui:input name="choicesCount" type="hidden" value="<%= choicesCount %>" />
	<aui:input name="choiceName" type="hidden" value="" />

	<liferay-ui:header
		backURL="<%= redirect %>"
		title='<%= (question != null) ? question.getTitle(locale) : "new-question" %>'
	/>

	<liferay-ui:error exception="<%= QuestionChoiceException.class %>" message="please-enter-valid-choices" />
	<liferay-ui:error exception="<%= QuestionDescriptionException.class %>" message="please-enter-a-valid-description" />
	<liferay-ui:error exception="<%= QuestionExpirationDateException.class %>" message="please-enter-a-valid-expiration-date" />
	<liferay-ui:error exception="<%= QuestionTitleException.class %>" message="please-enter-a-valid-title" />

	<aui:model-context bean="<%= question %>" model="<%= PollsQuestion.class %>" />

	<aui:fieldset>
		<aui:input name="title" />

		<aui:input name="description" />

		<aui:input disabled="<%= neverExpire %>" name="expirationDate" value="<%= expirationDate %>" />

		<%
		String taglibNeverExpireOnClick = renderResponse.getNamespace() + "disableInputDate('expirationDate', this.checked);";
		%>

		<aui:input inlineLabel="left" name="neverExpire" onClick="<%= taglibNeverExpireOnClick %>" type="checkbox" value="<%= neverExpire %>" />

		<aui:field-wrapper label="choices">

			<%
			for (int i = 1; i <= choicesCount; i++) {
				char c = (char)(96 + i);

				PollsChoice choice = null;

				String paramName = null;

				if (deleteChoice && (i >= choiceName)) {
					paramName = EditQuestionAction.CHOICE_DESCRIPTION_PREFIX + ((char)(96 + i + 1));
				}
				else {
					paramName = EditQuestionAction.CHOICE_DESCRIPTION_PREFIX + c;
				}

				if (question != null && (i - 1 < choices.size())) {
					choice = (PollsChoice)choices.get(i - 1);
				}
			%>

				<div class="choice <%= (i == choicesCount) ? "last-choice" : StringPool.BLANK %>">
					<aui:model-context bean="<%= choice %>" model="<%= PollsChoice.class %>" />

					<aui:input name="<%= EditQuestionAction.CHOICE_NAME_PREFIX + c %>" type="hidden" value="<%= c %>" />

					<aui:input fieldParam="<%= paramName %>" label="<%= c + StringPool.PERIOD %>" name="description" />

					<c:if test="<%= (((question == null) && (choicesCount > 2)) || ((question != null) && (choicesCount > oldChoicesCount))) && (i == choicesCount) %>">
						<aui:button onClick='<%= renderResponse.getNamespace() + "deletePollChoice(" + i + ");" %>' value="delete" />
					</c:if>
				</div>

			<%
			}
			%>

			<aui:button cssClass="add-choice" onClick='<%= renderResponse.getNamespace() + "addPollChoice();" %>' value="add-choice" />
		</aui:field-wrapper>

		<c:if test="<%= question == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= PollsQuestion.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button onClick="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />addPollChoice() {
		<portlet:actionURL var="addPollChoiceURL">
			<portlet:param name="struts_action" value="/polls/edit_question" />
			<portlet:param name="<%= EditQuestionAction.CHOICE_DESCRIPTION_PREFIX + (char)(96 + choicesCount + 1) %>" value="" />
		</portlet:actionURL>

		document.<portlet:namespace />fm.<portlet:namespace />choicesCount.value = '<%= choicesCount + 1 %>';
		submitForm(document.<portlet:namespace />fm, '<%= addPollChoiceURL %>');
	}

	function <portlet:namespace />deletePollChoice(choiceName) {
		document.<portlet:namespace />fm.<portlet:namespace />choicesCount.value = '<%= choicesCount - 1 %>';
		document.<portlet:namespace />fm.<portlet:namespace />choiceName.value = '<%= choiceName %>';
		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />saveQuestion() {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = "<%= (question == null) ? Constants.ADD : Constants.UPDATE %>";
		submitForm(document.<portlet:namespace />fm);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />disableInputDate',
		function(date, checked) {
			var A = AUI();

			document.<portlet:namespace />fm["<portlet:namespace />" + date + "Hour"].disabled = checked;
			document.<portlet:namespace />fm["<portlet:namespace />" + date + "Minute"].disabled = checked;
			document.<portlet:namespace />fm["<portlet:namespace />" + date + "AmPm"].disabled = checked;

			var calendarWidgetId = document.<portlet:namespace />fm["<portlet:namespace />" + date + "Month"].getAttribute('data-auiComponentID');

			var calendarWidget = A.Component.getById(calendarWidgetId);

			if (calendarWidget) {
				calendarWidget.set('disabled', checked);
			}
		},
		['aui-base']
	);

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />title);
	</c:if>
</aui:script>

<%
if (question != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, question.getTitle(locale), null);
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "add-question"), currentURL);
}
%>