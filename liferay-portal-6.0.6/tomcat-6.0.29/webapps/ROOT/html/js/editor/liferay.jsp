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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.liferay.portal.kernel.servlet.BrowserSnifferUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%
String panel = ParamUtil.get(request, "panel", (BrowserSnifferUtil.isIe(request) && (BrowserSnifferUtil.getMajorVersion(request) >= 5.5)) ? DEFAULT_PANEL_IE : DEFAULT_PANEL_MOZILLA);
String initMethod = ParamUtil.get(request, "initMethod", DEFAULT_INIT_METHOD);
String onChangeMethod = ParamUtil.getString(request, "onChangeMethod");
boolean disableControl = ParamUtil.get(request, "disable_control", false);
boolean disableRightClick = ParamUtil.get(request, "disable_right_click", false);
boolean pasteText = ParamUtil.get(request, "paste_text", false);
%>

<html>

<head>
	<title>Editor</title>

	<style type="text/css">
		.button {
			border-bottom: #E5E4E8 1px solid;
			border-left: #E5E4E8 1px solid;
			border-right: #E5E4E8 1px solid;
			border-top: #E5E4E8 1px solid;
		}

		.button-down {
			border-bottom: 1px solid buttonhighlight;
			border-left: 1px solid buttonshadow;
			border-right: 1px solid buttonhighlight;
			border-top: 1px solid buttonshadow;
		}

		.button-hover {
			border-bottom: 1px solid buttonshadow;
			border-left: 1px solid buttonhighlight;
			border-right: 1px solid buttonshadow;
			border-top: 1px solid buttonhighlight;
		}

		.separator {
			border-bottom: 1px solid buttonhighlight;
			border-left: 1px solid buttonshadow;
			border-right: 1px solid buttonhighlight;
			border-top: 1px solid buttonshadow;
			font-size: 12px;
			width: 2px;
		}
	</style>

	<script type="text/javascript">
		var win = window;
		var Liferay = win.Liferay;
		var themeDisplay = win.themeDisplay;

		while (!Liferay) {
			if (!win.opener && win.parent == win) {
				break;
			}

			win = win.opener || win.parent;

			Liferay = win.Liferay;
			themeDisplay = win.themeDisplay;
		}

		// Public description

		var publicDescription = new Editor();

		function Editor() {
			this.init = init;
			this.reset = reset;
			this.focus = focus;
			this.getHTML = getHTML;
			this.setHTML = setHTML;
			this.getText = getText;
		}

		var originalText;

		function init(value) {
			setHTML(value);
			originalText = value;

			// How do we get this to work in Mozilla too?

			<%
			if (disableControl) {
			%>

				document.getElementById("textArea").contentWindow.document.onkeydown = disableControl;

			<%
			}

			if (disableRightClick) {
			%>

				document.getElementById("textArea").contentWindow.document.onmousedown = disableRightClick;

			<%
			}
			%>

		}

		function reset() {
			setHTML(originalText);
		}

		function focus() {
			document.getElementById("textArea").contentWindow.focus();
		}

		function getHTML() {
			if (source) {
				switchMode();
			}

			return formatHTML(document.getElementById("textArea").contentWindow.document.body.innerHTML);
		}

		var htmlValue;

		function setHTML(value) {
			if (document.getElementById("textArea").contentWindow.document.body == null) {
				htmlValue = value;
				setTimeout("setHTML(htmlValue)", 0);
			}
			else {
				if (Liferay.Browser.isIe()) {

					// The IE rich text editor gives two breaks instead of one
					// when a user hits enter. Wrapping the HTML with div tags
					// will make it so the enter key will add one break instead
					// of two breaks. This workaround does not work when the
					// user switches from HTML to text mode and back. This is
					// also nasty because subsequent updates to the generated
					// HTML will add another set of div tags.

					if (!Liferay.Util.startsWith(value, "<DIV>") && !Liferay.Util.endsWith(value, "</DIV>")) {
						value = "<DIV>" + value + "</DIV>";
					}
				}

				document.getElementById("textArea").contentWindow.document.body.innerHTML = value;
			}
		}

		function getText() {
			if (source) {
				switchMode();
			}

			if (Liferay.Browser.isIe()) {
				return document.getElementById("textArea").contentWindow.document.body.innerText;
			}
			else {
				var temp = document.getElementById("textArea").contentWindow.document.body.ownerDocument.createRange();
				temp.selectNodeContents(document.getElementById("textArea").contentWindow.document.body);

				return temp.toString();
			}
		}

		// Private methods

		function buttonDown(button) {
			button.className = "button-down";
		}

		function buttonOut(button) {
			button.className = "button";
		}

		function buttonOver(button) {
			button.className = "button-hover";
		}

		function checkTab(box) {
			if ((document.all) && (event.keyCode == 9)) {
				box.selection = document.selection.createRange();
				setTimeout("exec(\"indent\");", 0);
			}
		}

		function disableControl() {
			var textAreaEl = document.getElementById("textArea");

			if (textAreaEl.contentWindow.event.ctrlKey) {
				var keyCode = textAreaEl.contentWindow.event.keyCode;

				if (keyCode == 88) {
					alert("CTRL-X is disabled. Please use the cut button.");
				}
				else if (keyCode == 67) {
					alert("CTRL-C is disabled. Please use the copy button.");
				}
				else if (keyCode == 86) {
					alert("CTRL-V is disabled. Please use the paste button.");
				}

				return false;
			}
			else {
				return true;
			}
		}

		function disableRightClick() {
			var textAreaEvent = document.getElementById("textArea").contentWindow.event;

			if (textAreaEvent.button == 2 || textAreaEvent.button == 3) {
				alert('Right click is disabled.');

				return false;
			}
			else {
				return true;
			}
		}

		function exec(cmd, opt) {
			if (source) {
				switchMode();
			}

			if (Liferay.Browser.isIe()) {
				/*if (cmd == "createLink") {
					var selUrl = prompt("URL:", "http://");

					document.getElementById("textArea").contentWindow.document.execCommand("unlink", false, null);
					document.getElementById("textArea").contentWindow.document.execCommand("createLink", false, selUrl);
				}*/

				<%
				if (pasteText) {
				%>

					if (cmd == "paste") {
						var clipboardText = window.clipboardData.getData('Text');

						insertHTML(clipboardText);
					}
					else {
						document.getElementById("textArea").contentWindow.document.execCommand(cmd, "", opt);
						document.getElementById("textArea").contentWindow.document.body.focus();
					}

				<%
				}
				else {
				%>

					document.getElementById("textArea").contentWindow.document.execCommand(cmd, "", opt);
					document.getElementById("textArea").contentWindow.document.body.focus();

				<%
				}
				%>

			}
			else {
				try {
					if (cmd == "createLink") {
						var selUrl = prompt("URL:", "");

						document.getElementById("textArea").contentWindow.document.execCommand("createLink", false, selUrl);
					}
					else {
						document.getElementById("textArea").contentWindow.document.execCommand(cmd, false, opt);
					}
				}
				catch (e) {
					if (cmd == "cut" || cmd == "copy" || cmd == "paste") {
						alert(
							"Your browser does not allow programatic access to cut, copy, and paste functions. Please use the standard key combinations: CTRL-X for cut, CTRL-C for copy, and CTRL-V for paste.");
					}
				}
			}

			document.getElementById("textArea").contentWindow.focus();
		}

		function initEditor() {
			document.getElementById("textArea").contentWindow.document.designMode = "on";
			init(parent.<%= initMethod %>());

			setInterval("onChangeCallback()", 300);
		}

		function formatHTML(html) {

			// Remove all entries that start with href="http://location.host
			// where location.host is a JavaScript variable denoting the current
			// host. This allows for relative paths using the WYSIWYG editor.

			html = formatHTMLLocation(html, "href");
			html = formatHTMLLocation(html, "src");

			return html;
		}

		function formatHTMLLocation(html, tag) {
			html = formatHTMLLocations(html, tag + "=\"", "http");
			html = formatHTMLLocations(html, tag + "='", "http");

			html = formatHTMLLocations(html, tag + "=\"", "https");
			html = formatHTMLLocations(html, tag + "='", "https");

			return html;
		}

		function formatHTMLLocations(html, tag, protocol) {
			var localhostUrl = tag + protocol + "://" + location.host;

			while (html.indexOf(localhostUrl) >= 0) {
				html = html.replace(localhostUrl, tag);
			}

			localhostUrl = tag + "<%= request.getContextPath() %>/html/common/@"

			while (html.indexOf(localhostUrl) >= 0) {
				html = html.replace(localhostUrl, tag + "@");
			}

			return html;
		}

		function insertHTML(newHTML) {
			document.getElementById("textArea").contentWindow.focus();

			if (Liferay.Browser.isIe()) {
				document.selection.createRange().pasteHTML(newHTML);
			}
			else {
				var html = document.getElementById("textArea").contentWindow.document.body.ownerDocument.createRange();
				html.selectNodeContents(document.getElementById("textArea").contentWindow.document.body);
				document.getElementById("textArea").contentWindow.document.body.innerHTML = html.toString() + newHTML;
			}
		}

		var spellCheckWindow;

		function openSpellCheck() {
			if (spellCheckWindow != null) {
				spellCheckWindow.close();
			}

			spellCheckWindow = window.open("", "spell_check", "height=480px,status=yes,width=640px");

			//spellCheckWindow.document.write("<font face=\"MS Sans Serif\" size=\"2\"><strong>Loading spell checker...</strong></font>");

			document.spell_check_fm.text.value = getHTML();
			document.spell_check_fm.submit();
		}

		var colorWindow;
		var colorType;

		function openForeColor() {
			colorType = "foreColor";

			openColorWindow();
		}

		function openBackColor() {
			if (Liferay.Browser.isIe()) {
				colorType = "backColor";
			}
			else {
				colorType = "hiliteColor";
			}

			openColorWindow();
		}

		function openColorWindow() {
			if (colorWindow != null) {
				colorWindow.close();
			}

			colorWindow = window.open("liferay/color.html", "color", "height=190px,width=230px");
		}

		function chooseColor(color) {
			exec(colorType, color);

			document.getElementById("textArea").contentWindow.focus();
		}

		var pictureWindow;

		function openPicture() {
			if (pictureWindow != null) {
				pictureWindow.close();
			}

			pictureWindow = window.open("liferay/picture.html", "picture", "height=200px,width=460px");
		}

		var tableWindow;

		function openTable() {
			if (tableWindow != null) {
				tableWindow.close();
			}

			tableWindow = window.open("liferay/table.html", "table", "height=250px,width=460px");
		}

		var emotionWindow;

		function openEmotion() {
			if (emotionWindow != null) {
				emotionWindow.close();
			}

			emotionWindow = window.open("liferay/emotion.jsp", "emotion", "height=100px,width=125px");
		}

		var characterMapWindow;

		function openCharacterMap() {
			if (characterMapWindow != null) {
				characterMapWindow.close();
			}

			characterMapWindow = window.open("liferay/character_map.html", "character_map", "height=160px,width=210px");
		}

		var source = false;

		function switchMode() {
			source = !source;

			if (source) {
				if (Liferay.Browser.isIe()) {
					var temp = formatHTML(document.getElementById("textArea").contentWindow.document.body.innerHTML);
					document.getElementById("textArea").contentWindow.document.body.innerText = temp;
				}
				else {
					var temp = document.createTextNode(document.getElementById("textArea").contentWindow.document.body.innerHTML);
					document.getElementById("textArea").contentWindow.document.body.innerHTML = "";
					document.getElementById("textArea").contentWindow.document.body.appendChild(temp);
				}
			}
			else {
				if (Liferay.Browser.isIe()) {
					var temp = document.getElementById("textArea").contentWindow.document.body.innerText;
					document.getElementById("textArea").contentWindow.document.body.innerHTML = temp;
				}
				else {
					var temp = document.getElementById("textArea").contentWindow.document.body.ownerDocument.createRange();
					temp.selectNodeContents(document.getElementById("textArea").contentWindow.document.body);
					document.getElementById("textArea").contentWindow.document.body.innerHTML = temp.toString();
				}
			}

			document.getElementById("textArea").contentWindow.focus();
		}

		function onChangeCallback() {

			<%
			if (Validator.isNotNull(onChangeMethod)) {
			%>

				if (getHTML().replace(/\s/g,"") != originalText.replace(/\s/g,"")) {
					try {
						parent.<%= HtmlUtil.escape(onChangeMethod) %>(getText());
					}
					catch (e) {
					}
				}

			<%
			}
			%>

		}
	</script>
</head>

<body leftmargin="0" marginheight="0" marginwidth="0" rightmargin="0" topmargin="0" onLoad="initEditor();">

<table bgcolor="#E5E4E8" cellpadding="0" cellspacing="0" height="100%" width="100%">
<tr>
	<td>

		<%
		String[] rows = StringUtil.split(panel, ",break,");

		for (int i = 0; i < rows.length; i++) {
			String[] cols = StringUtil.split(rows[i]);
		%>

			<table cellpadding="1" cellspacing="0">
			<tr>

				<%
				for (int j = 0; j < cols.length; j++) {
					String curCol = cols[j];
				%>

					<c:choose>
						<c:when test='<%= curCol.equalsIgnoreCase("separator") %>'>
							<td><img border="0" height="1" hspace="0" src="liferay/images/spacer.gif" vspace="0" width="2"><span class="separator"></span><img border="0" height="1" hspace="0" src="liferay/images/spacer.gif" vspace="0" width="2"></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("cut") %>'>
							<td><div class="button" onClick="exec('cut');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Cut" border="0" height="18" hspace="0" src="liferay/images/cut.gif" title="Cut" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("copy") %>'>
							<td><div class="button" onClick="exec('copy');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Copy" border="0" height="18" hspace="0" src="liferay/images/copy.gif" title="Copy" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("paste") %>'>
							<td><div class="button" onClick="exec('paste');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Paste" border="0" height="18" hspace="0" src="liferay/images/paste.gif" title="Paste" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("undo") %>'>
							<td><div class="button" onClick="exec('undo');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Undo" border="0" height="18" hspace="0" src="liferay/images/undo.gif" title="Undo" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("redo") %>'>
							<td><div class="button" onClick="exec('redo');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Redo" border="0" height="18" hspace="0" src="liferay/images/redo.gif" title="Redo" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("bold") %>'>
							<td><div class="button" onClick="exec('bold');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Bold" border="0" height="18" hspace="0" src="liferay/images/bold.gif" title="Bold" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("italic") %>'>
							<td><div class="button" onClick="exec('italic');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Italic" border="0" height="18" hspace="0" src="liferay/images/italic.gif" title="Italic" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("underline") %>'>
							<td><div class="button" onClick="exec('underline');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Underline" border="0" height="18" hspace="0" src="liferay/images/underline.gif" title="Underline" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("strikethrough") %>'>
							<td><div class="button" onClick="exec('strikethrough');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Strike" border="0" height="18" hspace="0" src="liferay/images/strike.gif" title="Strike" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("subscript") %>'>
							<td><div class="button" onClick="exec('subscript');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Subscript" border="0" height="18" hspace="0" src="liferay/images/subscript.gif" title="Subscript" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("superscript") %>'>
							<td><div class="button" onClick="exec('superscript');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Superscript" border="0" height="18" hspace="0" src="liferay/images/superscript.gif" title="Superscript" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("outdent") %>'>
							<td><div class="button" onClick="exec('outdent');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Decrease Indent" border="0" height="18" hspace="0" src="liferay/images/decrease_indent.gif" title="Decrease Indent" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("indent") %>'>
							<td><div class="button" onClick="exec('indent');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Increase Indent" border="0" height="18" hspace="0" src="liferay/images/increase_indent.gif" title="Increase Indent" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("justifyleft") %>'>
							<td><div class="button" onClick="exec('justifyleft');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Align Left" border="0" height="18" hspace="0" src="liferay/images/justify_left.gif" title="Align Left" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("justifycenter") %>'>
							<td><div class="button" onClick="exec('justifycenter');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Center" border="0" height="18" hspace="0" src="liferay/images/justify_center.gif" title="Center" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("justifyright") %>'>
							<td><div class="button" onClick="exec('justifyright');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Align Right" border="0" height="18" hspace="0" src="liferay/images/justify_right.gif" title="Align Right" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("insertorderedlist") %>'>
							<td><div class="button" onClick="exec('insertorderedlist');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Numbering" border="0" height="18" hspace="0" src="liferay/images/insert_ordered_list.gif" title="Numbering" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("insertunorderedlist") %>'>
							<td><div class="button" onClick="exec('insertunorderedlist');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Bullets" border="0" height="18" hspace="0" src="liferay/images/insert_unordered_list.gif" title="Bullets" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("spellcheck") %>'>
							<td><div class="button" onClick="openSpellCheck();" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Bullets" border="0" height="18" hspace="0" src="liferay/images/spell_check.gif" title="Spell Check" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("fontname") %>'>
							<td>
								<select onChange="exec('fontname', this[this.selectedIndex].value);">
									<option value="Arial">Arial</option>
									<option value="Comic Sans MS">Comic Sans MS</option>
									<option value="Courier New">Courier New</option>
									<option value="Tahoma">Tahoma</option>
									<option selected value="Times New Roman">Times New Roman</option>
									<option value="Verdana">Verdana</option>
									<option value="Wingdings">Wingdings</option>
								</select>
							</td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("fontsize") %>'>
							<td>
								<select onChange="exec('fontsize', this[this.selectedIndex].value);">
									<option value="1">1</option>
									<option value="2">2</option>
									<option selected value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
								</select>
							</td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("forecolor") %>'>
							<td><div class="button" onClick="openForeColor();" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Foreground Color" border="0" height="18" hspace="0" src="liferay/images/fg_color.gif" title="Foreground Color" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("backcolor") %>'>
							<td><div class="button" onClick="openBackColor();" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Background Color" border="0" height="18" hspace="0" src="liferay/images/bg_color.gif" title="Background Color" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("link") %>'>
							<td><div class="button" onClick="exec('createLink');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Hyperlink" border="0" height="18" hspace="0" src="liferay/images/hyperlink.gif" title="Hyperlink" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("picture") %>'>
							<td><div class="button" onClick="openPicture();" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Image" border="0" height="18" hspace="0" src="liferay/images/picture.gif" title="Image" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("horizontalrule") %>'>
							<td><div class="button" onClick="exec('insertHorizontalRule');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Horizontal Rule" border="0" height="18" hspace="0" src="liferay/images/hr.gif" title="Horizontal Rule" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("table") %>'>
							<td><div class="button" onClick="openTable();" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Table" border="0" height="18" hspace="0" src="liferay/images/table.gif" title="Table" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("emotion") %>'>
							<td><div class="button" onClick="openEmotion();" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Emotion" border="0" height="18" hspace="0" src="liferay/images/emotion.gif" title="Emotion" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("charactermap") %>'>
							<td><div class="button" onClick="openCharacterMap();" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Character Map" border="0" height="18" hspace="0" src="liferay/images/character_map.gif" title="Character Map" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("selectall") %>'>
							<td><div class="button" onClick="exec('selectAll');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Select All" border="0" height="18" hspace="0" src="liferay/images/select_all.gif" title="Select All" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("removeformat") %>'>
							<td><div class="button" onClick="exec('removeFormat');" onMouseDown="buttonDown(this);" onMouseOut="buttonOut(this);" onMouseOver="buttonOver(this);"><img align="absmiddle" alt="Remove Formatting" border="0" height="18" hspace="0" src="liferay/images/remove_formatting.gif" title="Remove Formatting" vspace="0" width="18"></div></td>
						</c:when>

						<c:when test='<%= curCol.equalsIgnoreCase("source") %>'>
							<td valign="top"><font face="MS Sans Serif" size="1">Source </font><input type="checkbox" onClick="switchMode();"></td>
						</c:when>
					</c:choose>

				<%
				}
				%>

			</tr>
			</table>

		<%
		}
		%>

	</td>
</tr>
<tr>
	<td bgcolor="#FFFFFF" height="100%">
		<iframe height="100%" id="textArea" name="textArea" src="../../common/null.html" width="100%"></iframe>
	</td>
</tr>
</table>

<form action="liferay/spell_check.jsp" method="post" name="spell_check_fm" target="spell_check">
<input name="text" type="hidden" value="">
</form>

</body>

</html>

<%!
public static final String DEFAULT_PANEL_IE = "cut,copy,paste,undo,redo,separator,bold,italic,underline,separator,strikethrough,subscript,superscript,separator,outdent,indent,justifyleft,justifycenter,justifyright,separator,insertorderedlist,insertunorderedlist,separator,spellcheck,break,fontname,fontsize,separator,forecolor,backcolor,separator,link,picture,horizontalrule,separator,table,emotion,charactermap,separator,selectall,removeformat,separator,source";

public static final String DEFAULT_PANEL_MOZILLA = "cut,copy,paste,undo,redo,separator,bold,italic,underline,separator,strikethrough,subscript,superscript,separator,outdent,indent,justifyleft,justifycenter,justifyright,separator,insertorderedlist,insertunorderedlist,separator,spellcheck,break,fontname,fontsize,separator,forecolor,backcolor,separator,link,picture,horizontalrule,separator,table,emotion,charactermap,separator,source";

public static final String DEFAULT_INIT_METHOD = "initEditor";
%>