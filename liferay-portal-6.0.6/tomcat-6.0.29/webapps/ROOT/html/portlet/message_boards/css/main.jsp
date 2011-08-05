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

<%@ include file="/html/portlet/css_init.jsp" %>

.portlet-message-boards .category-buttons {
	clear: both;
	margin-bottom: 1em;
}

.portlet-message-boards .category-subscriptions {
	clear: both;
	overflow: hidden;
}

.portlet-message-boards .category-subscription-types {
	float: right;
}

.portlet-message-boards .code {
	background: #fff;
	border: 1px solid #777;
	font-family: monospace;
	overflow-x: auto;
	white-space: pre;
}

.ie6 .portlet-message-boards .code {
	width: 100%;
}

.portlet-message-boards .code-lines {
	border-right: 1px solid #ccc;
	color: #000;
	margin-right: 5px;
	padding: 0px 5px 0px 5px;
}

.portlet-message-boards .quote {
	background: #fff url(<%= themeImagesPath %>/message_boards/quoteleft.png) left top no-repeat;
	border: 1px solid #777;
	padding: 5px 0px 0px 5px;
}

.portlet-message-boards .quote-content {
	background: transparent url(<%= themeImagesPath %>/message_boards/quoteright.png) right bottom no-repeat;
	padding: 5px 30px 10px 30px;
}

.portlet-message-boards .quote-title {
	font-weight: bold;
	padding: 5px 0px 5px 0px;
}

.portlet-message-boards .title {
	border-bottom: 1px solid #ccc;
	font-size: large;
	font-weight: normal;
	padding: 5px;
}

.portlet-message-boards #messageBoardsPanelContainer {
	border-width: 0;
}

.portlet-message-boards .message-container {
	border: 1px solid #ccc;
	margin: 5px 0 0 0;
}

.ie .portlet-message-boards .message-container {
	width: 100%;
}

.portlet-message-boards .message-container table {
	border-collapse: collapse;
	table-layout: fixed;
}

.portlet-message-boards .message-container td {
	border: none;
}

.portlet-message-boards .thread-top {
	border-bottom: 1px solid #ccc;
	padding: 3px 5px;
}

.portlet-message-boards .thread-bottom {
	padding: 3px 5px;
	vertical-align: bottom;
}

.portlet-message-boards .taglib-ratings.thumbs .total-rating {
	padding: 0 5px 0 10px;
}

td.user-info {
	border-right: 1px solid #ccc;
	width: 150px;
}

.portlet-message-boards .subject {
	float: left;
}

.portlet-message-boards .edit-controls {
	float: right;
}

.portlet-message-boards .edit-controls li {
	float: left;
	margin-right: 10px;
}

.portlet-message-boards .thread-body {
	overflow: hidden;
	padding: 15px;
}

.ie .portlet-message-boards .message-container .thread-body table {
	table-layout: auto;
}

.portlet-message-boards .message-container .user-info {
	border-right: 1px solid #ccc;
	padding: 5px;
}

.portlet-message-boards .clear {
	clear: both;
}

.portlet-message-boards .taglib-custom-attributes-list {
	margin-bottom: 1em;
}

.portlet-message-boards .taglib-custom-attributes-list label {
	display: block;
	font-weight: bold;
}

.portlet-message-boards .message-boards-panels {
	border-width: 0;
}

.portlet-message-boards .statistics-panel {
	border-width: 0;
}

.portlet-message-boards .statistics-panel-content {
	margin: 1em;
}

.portlet-message-boards .statistics-panel-content dt{
	float: left;
	font-weight: bold;
	margin-right: 1em;
}

.portlet-message-boards .toggle_id_message_boards_view_message_thread {
	border: 1px solid #ccc;
	margin: 5px 0 0 0;
	width: 100%;
}

.portlet-message-boards .thread-controls {
	border: 1px solid #ccc;
	margin-bottom: 5px;
	padding: 3px 5px;
}

.portlet-message-boards .thread-navigation {
	float: left;
}

.portlet-message-boards .thread-actions {
	float: right;
}

.portlet-message-boards .thread-user-rank {
	display: block;
}

.portlet-message-boards .thread-priority {
	border-width: 0;
	float: left;
}

.portlet-message-boards .thread-view, .portlet-message-boards .message-container .thread-message-view {
	border-collapse: collapse;
	padding: 0;
	width: 100%;
}

.portlet-message-boards .emoticons {
	border: 1px solid #ccc;
	margin-left: 10px;
}

.portlet-message-boards .tree {
	vertical-align: middle;
}

.portlet-message-boards .message-scroll {
	margin: 5px 0 0 0;
}

.portlet-message-boards .lfr-textarea.message-edit {
	height: 100%;
	width: 100%;
}

.portlet-message-boards .lfr-textarea.message-edit textarea {
	height: 378px;
	min-height: 100%;
	width: 99.5%;
}

.portlet-message-boards .subcategories {
	text-decoration: underline;
}

.portlet-message-boards .taglib-flags {
	float: left;
	margin-left: 20px;
}

.portlet-message-boards .threads-panel {
	margin-top: 2em;
}

.portlet-message-boards .top-links .category-search {
	float: right;
	margin: 0 0 0.5em 0.5em;
}