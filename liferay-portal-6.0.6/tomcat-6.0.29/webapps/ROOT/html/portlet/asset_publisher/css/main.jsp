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

.portlet-asset-publisher .add-asset-selector {
	margin-bottom: 1em;
	overflow: auto;
}

.portlet-asset-publisher .asset-abstract .asset-content p {
	margin-bottom: 0;
}

.portlet-asset-publisher .asset-abstract .asset-description {
	clear: left;
}

.portlet-asset-publisher .asset-back-to {
	text-align: right;
}

.portlet-asset-publisher .asset-content .asset-description {
	clear: left;
	font-style: italic;
}

.portlet-asset-publisher .asset-content {
	clear: right;
	margin-bottom: 10px;
	margin-left: 25px;
	margin-right: 10px;
}

.portlet-asset-publisher .asset-flag {
	margin-top: 1.8em;
}

.portlet-asset-publisher .asset-full-content .asset-content {
	margin-right: 25px;
}

.portlet-asset-publisher .asset-full-content.show-asset-title .asset-content {
	margin-right: 10px;
}

.portlet-asset-publisher .asset-full-content.no-title .asset-user-actions{
	padding-bottom: 2em;
}

.portlet-asset-publisher .asset-edit {
	float: right;
	margin-left: 1em;
}

.portlet-asset-publisher .asset-metadata span {
	float: left;
}

.portlet-asset-publisher .asset-metadata .metadata-categories span {
	float: none;
}

.portlet-asset-publisher .asset-metadata {
	clear: both;
	margin-left: 25px;
	overflow: hidden;
}

.ie .portlet-asset-publisher .asset-metadata {
	height: 1%;
}

.portlet-asset-publisher .asset-more {
	clear: left;
	/*Amzad*/
	color: #5B677D;
	font-size: 10px;
	
}

.portlet-asset-publisher .asset-entries-group-label {
	background-color: #DDD;
	clear: both;
	padding: 3px;
}

.portlet-asset-publisher .asset-ratings {
	float: left;
}

.portlet-asset-publisher .asset-small-image {
	float: right;
	padding-left: 0.5em;
}

.portlet-asset-publisher .asset-title .asset-actions img {
	margin-left: 5px;
}

.portlet-asset-publisher .asset-actions {
	float: right;
	font-size: 11px;
	font-weight: normal;
	margin-bottom: 3px;
	margin-top: 0;
}

.portlet-asset-publisher .asset-title a {
	text-decoration: none;
	margin-left: 10px;
	
}

.portlet-asset-publisher .asset-title a:hover {
	text-decoration: underline;
}

.portlet-asset-publisher .asset-title {
	border-bottom: 1px solid #DDD;
	margin-bottom: 0.7em;
	margin-right: 8px;
	margin-top: 2em;
	padding-bottom: 1px;
	font-size: 12px;
}

.portlet-asset-publisher .asset-user-actions .export-actions, .portlet-asset-publisher .asset-user-actions .print-action, .portlet-asset-publisher .asset-user-actions .locale-actions {
	float: right;
}

.portlet-asset-publisher .asset-user-actions .locale-separator {
	border-right: 1px solid #CCC;
	float: right;
	margin-right: 1em;
	padding: 0.8em 0.5em;
}

.portlet-asset-publisher .asset-user-actions .print-action {
	margin: 0 1em;
}



.portlet-asset-publisher .edit-controls {
	margin-bottom: 20px;
}

.portlet-asset-publisher .final-separator {
	border: 0;
	margin-bottom: 30px;
}

.portlet-asset-publisher .lfr-meta-actions {
	margin-right: 1em;
	padding-top: 0;
}

.portlet-asset-publisher .metadata-author {
	background: url(<%= themeImagesPath %>/portlet/edit_guest.png) no-repeat 0 0;
	float: left;
	font-weight: bold;
	margin-right: 10px;
	padding-left: 25px;
}

.portlet-asset-publisher .metadata-entry {
	color: #999;
}

.portlet-asset-publisher .metadata-modified-date, .portlet-asset-publisher .metadata-create-date, .portlet-asset-publisher .metadata-publish-date, .portlet-asset-publisher .metadata-expiration-date {
	background: url(<%= themeImagesPath %>/common/date.png) no-repeat 0 0;
	color: #999;
	margin-bottom: 1em;
	padding-left: 25px;
}

.portlet-asset-publisher .metadata-priority {
	background: url(<%= themeImagesPath %>/common/top.png) no-repeat 0 20%;
	margin-right: 10px;
	padding-left: 25px;
}

.portlet-asset-publisher .metadata-view-count {
	margin-right: 10px;
}

.portlet-asset-publisher .separator {
	border-right: 1px solid #999;
	clear: both;
	margin: 25px 25px;
}

.portlet-asset-publisher .taglib-asset-categories-summary {
	float: left;
}

.portlet-asset-publisher .taglib-asset-tags-summary {
	float: left;
}

.portlet-asset-publisher .title-list .asset-actions {
	left: 10px;
	position: relative;
}

.portlet-asset-publisher .title-list .asset-metadata {
	padding: 0;
}

.portlet-asset-publisher .title-list a {
	float: left;
}

.portlet-asset-publisher .vertical-separator {
	border-right: 1px solid #999;
	float: left;
	margin: 0 10px;
	padding: 7px 0;
}

.portlet-asset-publisher li.title-list {
	clear: both;
	list-style: none;
	margin-bottom: 0.15em;
	margin-right: 8px;
	padding-bottom: 1px;
}

.portlet-asset-publisher ul.title-list {
	margin-left: 0;
}

.portlet-configuration .aui-form .queryRules fieldset {
	padding: 0;
}

.portlet-configuration .aui-form .queryRules fieldset legend {
	font-size: 1em;
	font-weight: normal;
	padding: 0;
}

.portlet-configuration .aui-form .lfr-form-row {
	background-color: #F3F3F3;
}

.portlet-configuration .aui-form .lfr-form-row:hover {
	background-color: #DFFCCB;
}

.portlet-configuration .aui-form .lfr-form-row .aui-field {
	line-height: 2;
}

.portlet-configuration .aui-form .lfr-form-row .aui-field.tags-selector, .aui-form .lfr-form-row .aui-field.categories-selector{
	clear: both;
	line-height: 1.5;
	margin-top: 5px;
	width: 100%;
}

.portlet-configuration .lfr-panel .lfr-panel-titlebar {
	margin-bottom: 0;
}

.portlet-configuration .lfr-panel-content {
	background-color: #F8F8F8;
	padding: 10px;
}

.portlet-configuration .aui-form .general-display-settings {
	margin-bottom: 1em;
}

.portlet-configuration .dl-file-icon {
	border-width: 0;
	margin-right: 0.3em;
	position: relative;
	text-align: left;
	top: 0.4em;
}

.portlet-configuration .add-asset-selector {
	overflow: auto;
}

.portlet-configuration .asset-search {
	margin-bottom: 1em;
}

.portlet-configuration .lfr-actions.select-existing-selector {
	margin: 0 0  1em 1em;
}