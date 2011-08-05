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

<%@ include file="/html/taglib/ui/captcha/init.jsp" %>

<noscript>
	<iframe frameborder="0" height="300" src="<%= PropsValues.CAPTCHA_ENGINE_RECAPTCHA_URL_SCRIPT %><%= PrefsPropsUtil.getString(PropsKeys.CAPTCHA_ENGINE_RECAPTCHA_KEY_PUBLIC, PropsValues.CAPTCHA_ENGINE_RECAPTCHA_KEY_PUBLIC) %>" width="500"></iframe><br />

	<textarea cols="40" name="recaptcha_challenge_field" rows="3"></textarea>

	<input name="recaptcha_response_field" type="hidden" value="manual_challenge" />
</noscript>

<script src="<%= PropsValues.CAPTCHA_ENGINE_RECAPTCHA_URL_SCRIPT %><%= PrefsPropsUtil.getString(PropsKeys.CAPTCHA_ENGINE_RECAPTCHA_KEY_PUBLIC, PropsValues.CAPTCHA_ENGINE_RECAPTCHA_KEY_PUBLIC) %>" type="text/javascript"></script>

<aui:script position="inline">
	var RecaptchaOptions = {
		lang : '<%= locale.getLanguage() %>',
		theme : 'white'
	};
</aui:script>