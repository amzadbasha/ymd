package org.apache.jsp.html.portal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.model.ModelHintsConstants;

public final class css_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(57);
    _jspx_dependants.add("/html/portlet/css_init.jsp");
    _jspx_dependants.add("/html/portal/aui/css.jspf");
    _jspx_dependants.add("/html/portal/css/portal/generic_portal.jspf");
    _jspx_dependants.add("/html/portal/css/portal/add_content.jspf");
    _jspx_dependants.add("/html/portal/css/portal/flyout.jspf");
    _jspx_dependants.add("/html/portal/css/portal/panel_component.jspf");
    _jspx_dependants.add("/html/portal/css/portal/panel_pagination.jspf");
    _jspx_dependants.add("/html/portal/css/portal/position_helper.jspf");
    _jspx_dependants.add("/html/portal/css/portal/auto_row.jspf");
    _jspx_dependants.add("/html/portal/css/portal/undo_manager.jspf");
    _jspx_dependants.add("/html/portal/css/portal/panel_page.jspf");
    _jspx_dependants.add("/html/portal/css/portal/javascript_template.jspf");
    _jspx_dependants.add("/html/portal/css/portal/layout_templates.jspf");
    _jspx_dependants.add("/html/portal/css/portal/portlet_item.jspf");
    _jspx_dependants.add("/html/portal/css/portal/portlet_css_editor.jspf");
    _jspx_dependants.add("/html/portal/css/portal/portlet_toolbar.jspf");
    _jspx_dependants.add("/html/portal/css/portal/toolbar.jspf");
    _jspx_dependants.add("/html/portal/css/portal/tree.jspf");
    _jspx_dependants.add("/html/portal/css/portal/uploader.jspf");
    _jspx_dependants.add("/html/portal/css/portal/page_settings.jspf");
    _jspx_dependants.add("/html/portal/css/portal/notifications.jspf");
    _jspx_dependants.add("/html/portal/css/portal/asset_category_selector.jspf");
    _jspx_dependants.add("/html/portal/css/portal/asset_column.jspf");
    _jspx_dependants.add("/html/portal/css/portal/tag_selector.jspf");
    _jspx_dependants.add("/html/portal/css/portal/generic_portlet.jspf");
    _jspx_dependants.add("/html/portal/css/portal/forms.jspf");
    _jspx_dependants.add("/html/portal/css/portal/separator.jspf");
    _jspx_dependants.add("/html/portal/css/portal/top_links.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/asset_categories_summary.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/calendar.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/captcha.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/diff.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/diff_html.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/discussion.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/flags.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/header.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/icon.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/icon_help.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/icon_list.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/icon_menu.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/input_localized.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/input_move_boxes.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/input_repeat.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/language.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/preview.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/ratings.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/search_iterator.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/search_toggle.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/social_activities.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/social_bookmarks.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/tags_summary.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/user_display.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/webdav.jspf");
    _jspx_dependants.add("/html/portal/css/taglib/workflow_status.jspf");
    _jspx_dependants.add("/html/portal/css/portal/openid.jspf");
    _jspx_dependants.add("/html/portal/css/portal/accessibility.jspf");
    _jspx_dependants.add("/WEB-INF/tld/liferay-util.tld");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


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

      out.write('\n');
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

long companyId = PortalUtil.getCompanyId(request);

String themeId = ParamUtil.getString(request, "themeId");

Theme theme = ThemeLocalServiceUtil.getTheme(companyId, themeId, false);

String themeContextPath = PortalUtil.getPathContext();

if (theme.isWARFile()) {
	themeContextPath = theme.getContextPath();
}

String cdnHost = PortalUtil.getCDNHost(request.isSecure());

String themeImagesPath = cdnHost + themeContextPath + theme.getImagesPath();

response.addHeader(HttpHeaders.CONTENT_TYPE, ContentTypes.TEXT_CSS);

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("/* ---------- Browser normalization ---------- */\n");
      out.write("\n");
      out.write("body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form, fieldset, input, textarea, p, blockquote, th, td {\n");
      out.write("\tmargin: 0;\n");
      out.write("\tpadding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write("table {\n");
      out.write("\tborder-collapse: collapse;\n");
      out.write("\tborder-spacing: 0;\n");
      out.write("\tclear: both;\n");
      out.write("}\n");
      out.write("\n");
      out.write("fieldset, img {\n");
      out.write("\tborder-width: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write("address, caption, cite, code, dfn, em, strong, th, var {\n");
      out.write("\tfont-style: normal;\n");
      out.write("\tfont-weight: normal;\n");
      out.write("}\n");
      out.write("\n");
      out.write("caption, th {\n");
      out.write("\ttext-align: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write("q:before, q:after {\n");
      out.write("\tcontent: '';\n");
      out.write("}\n");
      out.write("\n");
      out.write("abbr, acronym {\n");
      out.write("\tborder: 0;\n");
      out.write("\tborder-bottom: 1px dotted #000;\n");
      out.write("\tcursor: help;\n");
      out.write("}\n");
      out.write("\n");
      out.write("blockquote, ul, ol, dl {\n");
      out.write("\tmargin: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("ol, ul, dl {\n");
      out.write("\tmargin-left: 2em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("ol {\n");
      out.write("\tlist-style: decimal outside;\n");
      out.write("}\n");
      out.write("\n");
      out.write("ul {\n");
      out.write("\tlist-style: disc outside;\n");
      out.write("}\n");
      out.write("\n");
      out.write("ul ul {\n");
      out.write("\tlist-style-type: circle;\n");
      out.write("}\n");
      out.write("\n");
      out.write("ol ol {\n");
      out.write("\tlist-style-type: upper-roman;\n");
      out.write("}\n");
      out.write("\n");
      out.write("dl dd {\n");
      out.write("\tmargin-left: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("th {\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write("caption {\n");
      out.write("\tmargin-bottom: 0.5em;\n");
      out.write("\ttext-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write("p {\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* ---------- Headers ---------- */\n");
      out.write("\n");
      out.write("h1 {\n");
      out.write("\tfont-size: 1.8em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("h2 {\n");
      out.write("\tfont-size: 1.6em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("h3 {\n");
      out.write("\tfont-size: 1.4em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("h1, h2, h3 {\n");
      out.write("\tmargin: 1em 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write("h4, h5, h6 {\n");
      out.write("\tfont-size: 1.1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("h1, h2, h3, h4, h5, h6, strong {\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* ---------- Text formatting ---------- */\n");
      out.write("\n");
      out.write("b, strong {\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write("i, em {\n");
      out.write("\tfont-style: italic;\n");
      out.write("}\n");
      out.write("\n");
      out.write("code, pre {\n");
      out.write("\tfont-family: \"Courier New\",Courier,monospace;\n");
      out.write("}\n");
      out.write("\n");
      out.write("pre {\n");
      out.write("\twhite-space: pre;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".important {\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* ---------- Separator ---------- */\n");
      out.write("\n");
      out.write("hr, .separator {\n");
      out.write("\tborder-top: 1px dotted #BFBFBF;\n");
      out.write("}\n");
      out.write("/* ---------- Interaction styles ---------- */\n");
      out.write("\n");
      out.write(".aui-state-default,\n");
      out.write(".aui-state-active,\n");
      out.write(".aui-state-hover {\n");
      out.write("\tbackground: #eee;\n");
      out.write("\tborder: 1px solid #ccc;\n");
      out.write("\tborder-color: #bbb #999 #999 #bbb;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-state-active {\n");
      out.write("\tbackground: #efefef;\n");
      out.write("\tborder-color: #333;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-state-hover {\n");
      out.write("\tbackground: #ddd;\n");
      out.write("\tborder-color: #999 #bbb #bbb #999;\n");
      out.write("\tcursor: pointer;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-state-disabled {\n");
      out.write("\tcursor: default !important;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-state-highlight {\n");
      out.write("\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-state-error {\n");
      out.write("\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-state-error-text {\n");
      out.write("\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-priority-primary {\n");
      out.write("\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-priority-secondary {\n");
      out.write("\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-html-template {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-helper-hidden {\n");
      out.write("\tdisplay: none !important;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-helper-hidden-accessible,\n");
      out.write(".aui-overlay-hidden,\n");
      out.write(".aui-overlaycontext-hidden,\n");
      out.write(".aui-overlaymask-hidden,\n");
      out.write(".aui-calendar-hidden,\n");
      out.write(".aui-date-picker-hidden,\n");
      out.write(".aui-datepicker-hidden,\n");
      out.write(".aui-colorpicker-hidden,\n");
      out.write(".aui-tooltip-hidden,\n");
      out.write(".aui-image-viewer-hidden,\n");
      out.write(".aui-image-gallery-hidden {\n");
      out.write("\tclip: rect(0 0 0 0);\n");
      out.write("\tposition: absolute;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-helper-reset {\n");
      out.write("\tmargin: 0;\n");
      out.write("\tpadding: 0;\n");
      out.write("\toutline: 0;\n");
      out.write("\ttext-decoration: none;\n");
      out.write("\tfont-size: 100%;\n");
      out.write("\tlist-style: none;\n");
      out.write("\tborder: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-helper-clearfix:after {\n");
      out.write("\tclear: both;\n");
      out.write("\tcontent: \"\";\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-helper-clearfix {\n");
      out.write("\t/* IE7/IE6 */\n");
      out.write("\tzoom: 1;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-helper-zfix {\n");
      out.write("\twidth: 100%;\n");
      out.write("\theight: 100%;\n");
      out.write("\ttop: 0;\n");
      out.write("\tleft: 0;\n");
      out.write("\tposition: absolute;\n");
      out.write("\topacity: 0;\n");
      out.write("\n");
      out.write("\t/* IE8 */\n");
      out.write("\t-ms-filter: alpha(opacity=0);\n");
      out.write("\t/* IE7/IE8(quirks) */\n");
      out.write("\tfilter: alpha(opacity=0);\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* unselectable */\n");
      out.write("\n");
      out.write(".aui-helper-unselectable, .aui-helper-unselectable * {\n");
      out.write("   -moz-user-select: none;\n");
      out.write("   -khtml-user-select: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* scroll lock */\n");
      out.write(".aui-helper-scroll-lock {\n");
      out.write("\theight: 100%;\n");
      out.write("\tmargin: 0;\n");
      out.write("\toverflow: hidden;\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/*\n");
      out.write("The rules below set the base font size and background color. These are best overwritten inside of custom.css.\n");
      out.write("*/\n");
      out.write("\n");
      out.write("body {\n");
      out.write("\tbackground-color: #FFF;\n");
      out.write("\tfont: 62.5%/1.6em Arial,Helvetica,Verdana,sans-serif;\n");
      out.write("}\n");
      out.write(".aui-layout {\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .aui-layout-content,\n");
      out.write(".ie7 .aui-layout-content {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ltr .aui-column,\n");
      out.write(".rtl .aui-column-last {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".rtl .aui-column,\n");
      out.write(".ltr .aui-column-last {\n");
      out.write("\tfloat: right;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ltr .aui-column-last {\n");
      out.write("\tmargin-left: -5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".rtl .aui-column-last {\n");
      out.write("\tmargin-right: -5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-layout-content:after {\n");
      out.write("\tclear: both;\n");
      out.write("\tcontent: \"\";\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-layout-content {\n");
      out.write("\t/* IE7/IE6 */\n");
      out.write("\tzoom: 1;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-16 {\n");
      out.write("\twidth: 6.25%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-12 {\n");
      out.write("\twidth: 8.334%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-10,\n");
      out.write(".aui-w10 {\n");
      out.write("\twidth: 10%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-9 {\n");
      out.write("\twidth: 11.112%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-8 {\n");
      out.write("\twidth: 12.5%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-7 {\n");
      out.write("\twidth: 14.286%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w15 {\n");
      out.write("\twidth: 15%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-6 {\n");
      out.write("\twidth: 16.667%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w3-16 {\n");
      out.write("\twidth: 18.75%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-5,\n");
      out.write(".aui-w20 {\n");
      out.write("\twidth: 20%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w2-9 {\n");
      out.write("\twidth: 22.223%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-4,\n");
      out.write(".aui-w25 {\n");
      out.write("\twidth: 25%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w2-7 {\n");
      out.write("\twidth: 28.572%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w3-10,\n");
      out.write(".aui-w30 {\n");
      out.write("\twidth: 30%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w5-16 {\n");
      out.write("\twidth: 31.25%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-3,\n");
      out.write(".aui-w33 {\n");
      out.write("\t/*width: 33.334%;*/\n");
      out.write("         width: 20%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w35 {\n");
      out.write("\twidth: 35%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w3-8 {\n");
      out.write("\twidth: 37.5%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w38 {\n");
      out.write("\twidth: 38.2%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w2-5,\n");
      out.write(".aui-w40 {\n");
      out.write("\twidth: 40%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w5-12 {\n");
      out.write("\twidth: 41.667%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w7-16 {\n");
      out.write("\twidth: 43.75%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w4-9 {\n");
      out.write("\twidth: 44.445%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w45 {\n");
      out.write("\twidth: 45%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w3-7 {\n");
      out.write("\twidth: 42.858%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w1-2,\n");
      out.write(".aui-w50 {\n");
      out.write("\twidth: 50%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w55 {\n");
      out.write("\twidth: 55%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w5-9 {\n");
      out.write("\twidth: 55.556%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w9-16 {\n");
      out.write("\twidth: 56.25%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w4-7 {\n");
      out.write("\twidth: 57.143%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w7-12 {\n");
      out.write("\twidth: 58.334%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w3-5,\n");
      out.write(".aui-w60 {\n");
      out.write("\twidth: 60%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w5-8 {\n");
      out.write("\twidth: 62.5%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w62 {\n");
      out.write("\twidth: 61.8%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w65 {\n");
      out.write("\twidth: 65%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w2-3,\n");
      out.write(".aui-w66 {\n");
      out.write("\twidth: 66.667%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w11-16 {\n");
      out.write("\twidth: 68.75%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w7-10,\n");
      out.write(".aui-w70 {\n");
      out.write("\twidth: 70%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w5-7 {\n");
      out.write("\twidth: 71.429%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w3-4,\n");
      out.write(".aui-w75 {\n");
      out.write("\twidth: 75%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w7-9 {\n");
      out.write("\twidth: 77.778%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w4-5,\n");
      out.write(".aui-w80 {\n");
      out.write("\twidth: 80%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w13-16 {\n");
      out.write("\twidth: 81.25%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w5-6 {\n");
      out.write("\twidth: 83.334%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w85 {\n");
      out.write("\twidth: 85%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w6-7 {\n");
      out.write("\twidth: 85.715%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w7-8 {\n");
      out.write("\twidth: 87.5%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w9-10,\n");
      out.write(".aui-w90 {\n");
      out.write("\twidth: 90%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w11-12 {\n");
      out.write("\twidth: 91.667%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w15-16 {\n");
      out.write("\twidth: 93.75%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w95 {\n");
      out.write("\twidth: 95%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-w100 {\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".webkit .aui-w1-12 {\n");
      out.write("\twidth: 8.345%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".webkit .aui-w11-12 {\n");
      out.write("\twidth: 91.675%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".webkit .aui-w1-6 {\n");
      out.write("\twidth: 16.675%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".webkit .aui-w5-6 {\n");
      out.write("\twidth: 83.345%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".webkit .aui-w5-12 {\n");
      out.write("\twidth: 41.675%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".webkit .aui-w7-12 {\n");
      out.write("\twidth: 58.345%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".webkit .aui-w1-3,\n");
      out.write(".webkit .aui-w33 {\n");
      out.write("\t/*width: 33.345%;*/\n");
      out.write("        width: 20%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".webkit .aui-w2-3,\n");
      out.write(".webkit .aui-w66 {\n");
      out.write("\twidth: 66.675%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-column-content,\n");
      out.write(".aui-column-content-center {\n");
      out.write("\tpadding: 0 0.5em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ltr .aui-column-content-first,\n");
      out.write(".rtl .aui-column-content-last {\n");
      out.write("\tpadding-left: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".rtl .aui-column-content-first,\n");
      out.write(".ltr .aui-column-content-last {\n");
      out.write("\tpadding-right: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6.ltr .aui-column-first,\n");
      out.write(".ie6.ltr .aui-column-content-first,\n");
      out.write(".ie6.rtl .aui-column-last,\n");
      out.write(".ie6.rtl .aui-column-content-last {\n");
      out.write("\tdisplay: inline;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .aui-layout-content .aui-column-content-center,\n");
      out.write(".ie6 .aui-layout-content .aui-column-content-first,\n");
      out.write(".ie6 .aui-layout-content .aui-column-content-last {\n");
      out.write("\tword-wrap: break-word;\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie body {\n");
      out.write("\tposition: relative;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie5 body,\n");
      out.write(".ie6 body {\n");
      out.write("\tposition: static;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".floatbox {\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write(".aui-form {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-fieldset-legend {\n");
      out.write("\tfont-size: 1.2em;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-labels-top {\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-labels-left {\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-labels-right {\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-labels-inline .aui-field-label,\n");
      out.write(".aui-field-labels-inline .aui-field-input,\n");
      out.write(".aui-field-labels-inline .aui-field-hint {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-labels-inline .aui-field-label,\n");
      out.write(".aui-field-labels-inline .aui-field-input-text {\n");
      out.write("\twidth: 45%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-labels-inline .aui-field-label {\n");
      out.write("\tmargin: 0.3em 2% 0 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-labels-right .aui-field-label {\n");
      out.write("\ttext-align: right;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-labels-inline .aui-field-hint {\n");
      out.write("\tdisplay: block;\n");
      out.write("\tfloat: none;\n");
      out.write("\tclear: both;\n");
      out.write("\tmargin-left: 47%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-label,\n");
      out.write(".aui-field-labels-top .aui-field-label {\n");
      out.write("\tdisplay: block;\n");
      out.write("\tfloat: none;\n");
      out.write("\ttext-align: left;\n");
      out.write("\tmargin: 0;\n");
      out.write("\twidth: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-input,\n");
      out.write(".aui-field-labels-top .aui-field-input {\n");
      out.write("\tfloat: none;\n");
      out.write("\twidth: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-hint,\n");
      out.write(".aui-field-labels-top .aui-field-hint {\n");
      out.write("\tline-height: 1;\n");
      out.write("\tclear: none;\n");
      out.write("\tfloat: right;\n");
      out.write("\twidth: 45%;\n");
      out.write("\tmargin: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-hint-icon {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-inline {\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-label-inline {\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("\tmargin: 0.4em 0.4em 0 0\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-inline .aui-field-content,\n");
      out.write(".aui-field-inline .aui-field-label {\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-inline .aui-field-label {\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-group {\n");
      out.write("\tfloat: left;\n");
      out.write("\twidth: 53%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-choice-label {\n");
      out.write("\tline-height: 1;\n");
      out.write("\twidth: 89%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-input-choice {\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("\tmargin: 0 0.4em 0 0;\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-choice-label,\n");
      out.write(".aui-field-input-choice {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-row {\n");
      out.write("\tdisplay: block;\n");
      out.write("\tclear: both;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-row:after {\n");
      out.write("\tclear: both;\n");
      out.write("\tcontent: \"\";\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-row {\n");
      out.write("\t/* IE7/IE6 */\n");
      out.write("\tzoom: 1;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-button-holder {\n");
      out.write("\toverflow: hidden;\n");
      out.write("\tdisplay: block;\n");
      out.write("\tclear: both;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-button-input {\n");
      out.write("\tmargin: 0;\n");
      out.write("\tline-height: 1.3;\n");
      out.write("\ttext-decoration: none;\n");
      out.write("\tcursor: pointer;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tpadding: 5px 10px 6px 7px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* IE7 and below */\n");
      out.write(".aui-button-input {\n");
      out.write("\t*padding: 4px 10px 3px 7px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-content:after,\n");
      out.write(".aui-button-holder:after, {\n");
      out.write("\tclear: both;\n");
      out.write("\tcontent: \" \";\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 0;\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-content,\n");
      out.write(".aui-button-holder {\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* start commented backslash hack \\*/\n");
      out.write("* html .aui-field-content,\n");
      out.write("* html .aui-button-holder {\n");
      out.write("\theight: 1%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-content,\n");
      out.write(".aui-button-holder {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("/* close commented backslash hack */\n");
      out.write("\n");
      out.write(".aui-field-label {\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-choice-label {\n");
      out.write("\tfont-weight: normal;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-hint {\n");
      out.write("\tcolor: #777;\n");
      out.write("\tfont-size: 0.9em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-choice-label,\n");
      out.write(".aui-field-hint {\n");
      out.write("\tline-height: 1.2;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field-element-left {\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Fields used for form validation */\n");
      out.write("\n");
      out.write(".aui-field.aui-has-error .aui-field-content {\n");
      out.write("\tborder-bottom: 1px solid #EF4A4A;\n");
      out.write("\tbackground-color: #FF9F9F;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field.aui-has-warn .aui-field-content {\n");
      out.write("\tborder-bottom: 1px solid #FFC45F;\n");
      out.write("\tbackground-color: #FFFABF;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-field .aui-message-holder {\n");
      out.write("\tcolor: #FF0000;\n");
      out.write("\tdisplay: block;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-status-failure {\n");
      out.write("\tborder:  1px solid #EF4A4A;\n");
      out.write("}\n");
      out.write(".aui-icon {\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/aui/icon_sprite.png) no-repeat 0 0;\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 16px;\n");
      out.write("\toverflow: hidden;\n");
      out.write("\ttext-indent: -99999em;\n");
      out.write("\twidth: 16px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-carat-1-t {\n");
      out.write("\tbackground-position: 0 0; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-carat-1-tr {\n");
      out.write("\tbackground-position: -16px 0; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-carat-1-r {\n");
      out.write("\tbackground-position: -32px 0; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-carat-1-br {\n");
      out.write("\tbackground-position: -48px 0; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-carat-1-b {\n");
      out.write("\tbackground-position: -64px 0; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-carat-1-bl {\n");
      out.write("\tbackground-position: -80px 0; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-carat-1-l {\n");
      out.write("\tbackground-position: -96px 0; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-carat-1-tl {\n");
      out.write("\tbackground-position: -112px 0; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-carat-2-t-b {\n");
      out.write("\tbackground-position: -128px 0; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-carat-2-r-l {\n");
      out.write("\tbackground-position: -144px 0; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-triangle-1-t {\n");
      out.write("\tbackground-position: 0 -16px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-triangle-1-tr {\n");
      out.write("\tbackground-position: -16px -16px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-triangle-1-r {\n");
      out.write("\tbackground-position: -32px -16px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-triangle-1-br {\n");
      out.write("\tbackground-position: -48px -16px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-triangle-1-b {\n");
      out.write("\tbackground-position: -64px -16px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-triangle-1-bl {\n");
      out.write("\tbackground-position: -80px -16px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-triangle-1-l {\n");
      out.write("\tbackground-position: -96px -16px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-triangle-1-tl {\n");
      out.write("\tbackground-position: -112px -16px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-triangle-2-t-b {\n");
      out.write("\tbackground-position: -128px -16px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-triangle-2-r-l {\n");
      out.write("\tbackground-position: -144px -16px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-1-t {\n");
      out.write("\tbackground-position: 0 -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-1-tr {\n");
      out.write("\tbackground-position: -16px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-1-r {\n");
      out.write("\tbackground-position: -32px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-1-br {\n");
      out.write("\tbackground-position: -48px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-1-b {\n");
      out.write("\tbackground-position: -64px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-1-bl {\n");
      out.write("\tbackground-position: -80px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-1-l {\n");
      out.write("\tbackground-position: -96px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-1-tl {\n");
      out.write("\tbackground-position: -112px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-2-t-b {\n");
      out.write("\tbackground-position: -128px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-2-tr-bl {\n");
      out.write("\tbackground-position: -144px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-2-r-l {\n");
      out.write("\tbackground-position: -160px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-2-br-tl {\n");
      out.write("\tbackground-position: -176px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowstop-1-t {\n");
      out.write("\tbackground-position: -192px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowstop-1-r {\n");
      out.write("\tbackground-position: -208px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowstop-1-b {\n");
      out.write("\tbackground-position: -224px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowstop-1-l {\n");
      out.write("\tbackground-position: -240px -32px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-1-t {\n");
      out.write("\tbackground-position: 0 -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-1-tr {\n");
      out.write("\tbackground-position: -16px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-1-r {\n");
      out.write("\tbackground-position: -32px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-1-br {\n");
      out.write("\tbackground-position: -48px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-1-b {\n");
      out.write("\tbackground-position: -64px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-1-bl {\n");
      out.write("\tbackground-position: -80px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-1-l {\n");
      out.write("\tbackground-position: -96px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-1-tl {\n");
      out.write("\tbackground-position: -112px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-2-t-b {\n");
      out.write("\tbackground-position: -128px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-2-tr-bl {\n");
      out.write("\tbackground-position: -144px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-2-r-l {\n");
      out.write("\tbackground-position: -160px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthick-2-br-tl {\n");
      out.write("\tbackground-position: -176px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthickstop-1-t {\n");
      out.write("\tbackground-position: -192px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthickstop-1-r {\n");
      out.write("\tbackground-position: -208px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthickstop-1-b {\n");
      out.write("\tbackground-position: -224px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowthickstop-1-l {\n");
      out.write("\tbackground-position: -240px -48px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowreturnthick-1-l {\n");
      out.write("\tbackground-position: 0 -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowreturnthick-1-t {\n");
      out.write("\tbackground-position: -16px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowreturnthick-1-r {\n");
      out.write("\tbackground-position: -32px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowreturnthick-1-b {\n");
      out.write("\tbackground-position: -48px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowreturn-1-l {\n");
      out.write("\tbackground-position: -64px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowreturn-1-t {\n");
      out.write("\tbackground-position: -80px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowreturn-1-r {\n");
      out.write("\tbackground-position: -96px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowreturn-1-b {\n");
      out.write("\tbackground-position: -112px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowrefresh-1-l {\n");
      out.write("\tbackground-position: -128px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowrefresh-1-t {\n");
      out.write("\tbackground-position: -144px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowrefresh-1-r {\n");
      out.write("\tbackground-position: -160px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrowrefresh-1-b {\n");
      out.write("\tbackground-position: -176px -64px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-4 {\n");
      out.write("\tbackground-position: 0 -80px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-arrow-4-diag {\n");
      out.write("\tbackground-position: -16px -80px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-extlink {\n");
      out.write("\tbackground-position: -32px -80px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-newwin {\n");
      out.write("\tbackground-position: -48px -80px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-refresh {\n");
      out.write("\tbackground-position: -64px -80px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-shuffle {\n");
      out.write("\tbackground-position: -80px -80px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-transfer-r-l {\n");
      out.write("\tbackground-position: -96px -80px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-transferthick-r-l {\n");
      out.write("\tbackground-position: -112px -80px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-folder-collapsed {\n");
      out.write("\tbackground-position: 0 -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-folder-open {\n");
      out.write("\tbackground-position: -16px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-document {\n");
      out.write("\tbackground-position: -32px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-document-b {\n");
      out.write("\tbackground-position: -48px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-note {\n");
      out.write("\tbackground-position: -64px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-mail-closed {\n");
      out.write("\tbackground-position: -80px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-mail-open {\n");
      out.write("\tbackground-position: -96px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-suitcase {\n");
      out.write("\tbackground-position: -112px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-comment {\n");
      out.write("\tbackground-position: -128px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-person {\n");
      out.write("\tbackground-position: -144px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-print {\n");
      out.write("\tbackground-position: -160px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-trash {\n");
      out.write("\tbackground-position: -176px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-locked {\n");
      out.write("\tbackground-position: -192px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-unlocked {\n");
      out.write("\tbackground-position: -208px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-bookmark {\n");
      out.write("\tbackground-position: -224px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-tag {\n");
      out.write("\tbackground-position: -240px -96px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-home {\n");
      out.write("\tbackground-position: 0 -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-flag {\n");
      out.write("\tbackground-position: -16px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-calendar {\n");
      out.write("\tbackground-position: -32px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-cart {\n");
      out.write("\tbackground-position: -48px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-pencil {\n");
      out.write("\tbackground-position: -64px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-clock {\n");
      out.write("\tbackground-position: -80px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-disk {\n");
      out.write("\tbackground-position: -96px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-calculator {\n");
      out.write("\tbackground-position: -112px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-zoomin {\n");
      out.write("\tbackground-position: -128px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-zoomout {\n");
      out.write("\tbackground-position: -144px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-search {\n");
      out.write("\tbackground-position: -160px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-wrench {\n");
      out.write("\tbackground-position: -176px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-gear {\n");
      out.write("\tbackground-position: -192px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-heart {\n");
      out.write("\tbackground-position: -208px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-star {\n");
      out.write("\tbackground-position: -224px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-link {\n");
      out.write("\tbackground-position: -240px -112px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-cancel {\n");
      out.write("\tbackground-position: 0 -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-plus {\n");
      out.write("\tbackground-position: -16px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-plusthick {\n");
      out.write("\tbackground-position: -32px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-minus {\n");
      out.write("\tbackground-position: -48px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-minusthick {\n");
      out.write("\tbackground-position: -64px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-close {\n");
      out.write("\tbackground-position: -80px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-closethick {\n");
      out.write("\tbackground-position: -96px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-key {\n");
      out.write("\tbackground-position: -112px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-lightbulb {\n");
      out.write("\tbackground-position: -128px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-scissors {\n");
      out.write("\tbackground-position: -144px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-clipboard {\n");
      out.write("\tbackground-position: -160px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-copy {\n");
      out.write("\tbackground-position: -176px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-contact {\n");
      out.write("\tbackground-position: -192px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-image {\n");
      out.write("\tbackground-position: -208px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-video {\n");
      out.write("\tbackground-position: -224px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-script {\n");
      out.write("\tbackground-position: -240px -128px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-alert {\n");
      out.write("\tbackground-position: 0 -144px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-info {\n");
      out.write("\tbackground-position: -16px -144px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-notice {\n");
      out.write("\tbackground-position: -32px -144px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-help {\n");
      out.write("\tbackground-position: -48px -144px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-check {\n");
      out.write("\tbackground-position: -64px -144px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-bullet {\n");
      out.write("\tbackground-position: -80px -144px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-radio-off {\n");
      out.write("\tbackground-position: -96px -144px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-radio-on {\n");
      out.write("\tbackground-position: -112px -144px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-pin-l {\n");
      out.write("\tbackground-position: -128px -144px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-pin-b {\n");
      out.write("\tbackground-position: -144px -144px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-play {\n");
      out.write("\tbackground-position: 0 -160px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-pause {\n");
      out.write("\tbackground-position: -16px -160px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-seek-next {\n");
      out.write("\tbackground-position: -32px -160px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-seek-prev {\n");
      out.write("\tbackground-position: -48px -160px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-seek-end {\n");
      out.write("\tbackground-position: -64px -160px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-seek-first {\n");
      out.write("\tbackground-position: -80px -160px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-stop {\n");
      out.write("\tbackground-position: -96px -160px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-eject {\n");
      out.write("\tbackground-position: -112px -160px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-volume-off {\n");
      out.write("\tbackground-position: -128px -160px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-volume-on {\n");
      out.write("\tbackground-position: -144px -160px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-power {\n");
      out.write("\tbackground-position: 0 -176px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-signal-diag {\n");
      out.write("\tbackground-position: -16px -176px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-signal {\n");
      out.write("\tbackground-position: -32px -176px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-battery-0 {\n");
      out.write("\tbackground-position: -48px -176px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-battery-1 {\n");
      out.write("\tbackground-position: -64px -176px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-battery-2 {\n");
      out.write("\tbackground-position: -80px -176px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-battery-3 {\n");
      out.write("\tbackground-position: -96px -176px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-plus {\n");
      out.write("\tbackground-position: 0 -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-minus {\n");
      out.write("\tbackground-position: -16px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-close {\n");
      out.write("\tbackground-position: -32px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-triangle-r {\n");
      out.write("\tbackground-position: -48px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-triangle-b {\n");
      out.write("\tbackground-position: -64px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-triangle-l {\n");
      out.write("\tbackground-position: -80px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-triangle-t {\n");
      out.write("\tbackground-position: -96px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-arrow-r {\n");
      out.write("\tbackground-position: -112px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-arrow-b {\n");
      out.write("\tbackground-position: -128px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-arrow-l {\n");
      out.write("\tbackground-position: -144px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-arrow-t {\n");
      out.write("\tbackground-position: -160px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-zoomin {\n");
      out.write("\tbackground-position: -176px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-zoomout {\n");
      out.write("\tbackground-position: -192px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circle-check {\n");
      out.write("\tbackground-position: -208px -192px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circlesmall-plus {\n");
      out.write("\tbackground-position: 0 -208px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circlesmall-minus {\n");
      out.write("\tbackground-position: -16px -208px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-circlesmall-close {\n");
      out.write("\tbackground-position: -32px -208px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-squaresmall-plus {\n");
      out.write("\tbackground-position: -48px -208px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-squaresmall-minus {\n");
      out.write("\tbackground-position: -64px -208px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-squaresmall-close {\n");
      out.write("\tbackground-position: -80px -208px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-grip-dotted-vertical {\n");
      out.write("\tbackground-position: 0 -224px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-grip-dotted-horizontal {\n");
      out.write("\tbackground-position: -16px -224px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-grip-solid-vertical {\n");
      out.write("\tbackground-position: -32px -224px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-grip-solid-horizontal {\n");
      out.write("\tbackground-position: -48px -224px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-gripsmall-diagonal-br {\n");
      out.write("\tbackground-position: -64px -224px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-grip-diagonal-br {\n");
      out.write("\tbackground-position: -80px -224px; \n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-icon-loading {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/aui/loading_indicator.gif) no-repeat 0 0;\n");
      out.write("\twidth: 16px;\n");
      out.write("\theight: 16px;\n");
      out.write("}\n");
      out.write(".aui-widget {\n");
      out.write("}\n");
      out.write("\n");
      out.write("/*.aui-widget-hidden {\n");
      out.write("\tdisplay: none;\n");
      out.write("}*/\n");
      out.write("\n");
      out.write(".aui-widget-content {\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-widget-hd {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-widget-bd {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-widget-ft {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-widget-close {\n");
      out.write("\tfloat: right;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-widget-stacked .aui-widget-shim {\n");
      out.write("\tborder: 0;\n");
      out.write("\theight: 100%;\n");
      out.write("\tleft: 0;\n");
      out.write("\tmargin: 0;\n");
      out.write("\topacity: 0;\n");
      out.write("\tpadding: 0;\n");
      out.write("\tposition: absolute;\n");
      out.write("\ttop: 0;\n");
      out.write("\twidth: 100%;\n");
      out.write("\tz-index: -1;\n");
      out.write("\n");
      out.write("\t/* IE8 */\n");
      out.write("\t-ms-filter: alpha(opacity=0);\n");
      out.write("\t/* IE7/IE8(quirks) and below */\n");
      out.write("\tfilter: alpha(opacity=0);\n");
      out.write("\t/* IE6 */\n");
      out.write("\t_width:0;\n");
      out.write("\t_height:0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-widget-content-expanded {\n");
      out.write("\t-moz-box-sizing: border-box;\n");
      out.write("\t-ms-box-sizing: border-box;\n");
      out.write("\t-webkit-box-sizing: border-box;\n");
      out.write("\tbox-sizing: border-box;\n");
      out.write("\n");
      out.write("\theight: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-overlay {\n");
      out.write("\tposition: absolute;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-widget-tmp-forcesize {\n");
      out.write("\toverflow: hidden !important;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-widget-stacked .aui-widget-shim {\n");
      out.write("\tborder-width: 0;\n");
      out.write("\theight: 100%;\n");
      out.write("\tleft: 0px;\n");
      out.write("\tmargin: 0;\n");
      out.write("\topacity: 0;\n");
      out.write("\tpadding: 0;\n");
      out.write("\tposition: absolute;\n");
      out.write("\ttop: 0px;\n");
      out.write("\twidth: 100%;\n");
      out.write("\tz-index: -1;\n");
      out.write("\n");
      out.write("\t/* IE */\n");
      out.write("\t-ms-filter: alpha(opacity=0);\n");
      out.write("\tfilter: alpha(opacity=0);\n");
      out.write("\n");
      out.write("\t/* IE6 */\n");
      out.write("\t_width:0;\n");
      out.write("\t_height:0;\n");
      out.write("}\n");
      out.write(".aui-autocomplete-results-content,\n");
      out.write(".aui-calendar-content,\n");
      out.write(".aui-colorpicker-content,\n");
      out.write(".aui-dialog-content,\n");
      out.write(".aui-overlaycontext-content {\n");
      out.write("\tbackground: #fff;\n");
      out.write("}\n");
      out.write("/*\n");
      out.write("Add your classname to the list below in order to have your element\n");
      out.write("recieve a gracefully degrading chrome enhancement such as a drop \n");
      out.write("shadow or rounded corners.\n");
      out.write("*/\n");
      out.write("\n");
      out.write("/* ---------- CSS3 shadows ---------- */\n");
      out.write(".aui-dialog-content,\n");
      out.write(".aui-date-picker-content,\n");
      out.write(".aui-overlaycontextpanel-content,\n");
      out.write(".aui-tooltip-content,\n");
      out.write(".aui-colorpicker-content,\n");
      out.write(".aui-tree-drag-helper-content {\n");
      out.write("\t-moz-box-shadow: 4px 4px 14px #777;\n");
      out.write("\t-webkit-box-shadow: 4px 4px 14px #777;\n");
      out.write("\tbox-shadow: 4px 4px 14px #777;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* No shadow */\n");
      out.write("\n");
      out.write(".aui-null-selector {\n");
      out.write("\t-moz-box-shadow: none;\n");
      out.write("\t-webkit-box-shadow: none;\n");
      out.write("\tbox-shadow: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* ---------- Rounded corners ---------- */\n");
      out.write("\n");
      out.write("/* All corners */\n");
      out.write("\n");
      out.write(".aui-state-default,\n");
      out.write(".aui-state-active,\n");
      out.write(".aui-state-hover,\n");
      out.write(".aui-calendar-content,\n");
      out.write(".aui-colorpicker-content,\n");
      out.write(".aui-colorpicker-panel-content,\n");
      out.write(".aui-image-gallery-paginator .aui-image-gallery-paginator-thumb,\n");
      out.write(".aui-image-viewer-loading .aui-image-viewer-bd,\n");
      out.write(".aui-loadingmask-message,\n");
      out.write(".aui-loadingmask-message-content,\n");
      out.write(".aui-progress-bar-content,\n");
      out.write(".aui-progress-bar-status {\n");
      out.write("\t-moz-border-radius: 4px;\n");
      out.write("\t-webkit-border-radius: 4px;\n");
      out.write("\tborder-radius: 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-carousel menu {\n");
      out.write("\t-moz-border-radius: 5px;\n");
      out.write("\t-webkit-border-radius: 5px;\n");
      out.write("\tborder-radius: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-carousel menu a {\n");
      out.write("\t-moz-border-radius: 9px;\n");
      out.write("\t-webkit-border-radius: 9px;\n");
      out.write("\tborder-radius: 9px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Large corners */\n");
      out.write(".aui-textboxlistentry,\n");
      out.write(".aui-textboxlistentry-close {\n");
      out.write("\t-moz-border-radius: 14px;\n");
      out.write("\t-webkit-border-radius: 14px;\n");
      out.write("\tborder-radius: 14px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write("/* No corners */\n");
      out.write("\n");
      out.write(".aui-dialog .aui-widget-hd,\n");
      out.write(".aui-editable-hover,\n");
      out.write(".aui-tab,\n");
      out.write(".aui-overlaycontextpanel-pointer.aui-state-default,\n");
      out.write(".aui-toolbar-item-content,\n");
      out.write(".aui-toolbar-item,\n");
      out.write(".aui-toolbar-vertical .aui-toolbar-item {\n");
      out.write("\t-moz-border-radius: 0;\n");
      out.write("\t-webkit-border-radius: 0;\n");
      out.write("\tborder-radius: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Left corners */\n");
      out.write("\n");
      out.write(".aui-toolbar-first {\n");
      out.write("\t-moz-border-radius-topleft: 4px;\n");
      out.write("\t-moz-border-radius-bottomleft: 4px;\n");
      out.write("\t-webkit-border-top-left-radius: 4px;\n");
      out.write("\t-webkit-border-bottom-left-radius: 4px;\n");
      out.write("\tborder-top-left-radius: 4px;\n");
      out.write("\tborder-bottom-left-radius: 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Right corners */\n");
      out.write("\n");
      out.write(".aui-toolbar-last {\n");
      out.write("\t-moz-border-radius-topright: 4px;\n");
      out.write("\t-moz-border-radius-bottomright: 4px;\n");
      out.write("\t-webkit-border-top-right-radius: 4px;\n");
      out.write("\t-webkit-border-bottom-right-radius: 4px;\n");
      out.write("\tborder-top-right-radius: 4px;\n");
      out.write("\tborder-bottom-right-radius: 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Top left */\n");
      out.write("\n");
      out.write(".aui-image-viewer-bd,\n");
      out.write(".aui-tab,\n");
      out.write(".aui-toolbar-vertical .aui-toolbar-first {\n");
      out.write("\t-moz-border-radius-topleft: 4px;\n");
      out.write("\t-webkit-border-top-left-radius: 4px;\n");
      out.write("\tborder-top-left-radius: 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Top right */\n");
      out.write("\n");
      out.write(".aui-image-viewer-bd,\n");
      out.write(".aui-tab,\n");
      out.write(".aui-toolbar-vertical .aui-toolbar-first {\n");
      out.write("\t-moz-border-radius-topright: 4px;\n");
      out.write("\t-webkit-border-top-right-radius: 4px;\n");
      out.write("\tborder-top-right-radius: 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Bottom left */\n");
      out.write("\n");
      out.write(".aui-image-viewer-content .aui-widget-ft,\n");
      out.write(".aui-toolbar-vertical .aui-toolbar-last {\n");
      out.write("\t-moz-border-radius-bottomleft: 4px;\n");
      out.write("\t-webkit-border-bottom-left-radius: 4px;\n");
      out.write("\tborder-bottom-left-radius: 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("/* Bottom right */\n");
      out.write("\n");
      out.write(".aui-image-viewer-content .aui-widget-ft,\n");
      out.write(".aui-toolbar-vertical .aui-toolbar-last {\n");
      out.write("\t-moz-border-radius-bottomright: 4px;\n");
      out.write("\t-webkit-border-bottom-right-radius: 4px;\n");
      out.write("\tborder-bottom-right-radius: 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-autocomplete-trigger{cursor:pointer;display:inline-block;float:none;}.aui-autocomplete-list-item{border:1px solid #fff;list-style:none;margin:0;padding:0 3px;}.aui-autocomplete-results-content{border:1px solid #98c0f4;height:100%;overflow-y:auto;}.aui-autocomplete-selected{background:#dfe8f6;border-color:#a3bae9;}\n");
      out.write(".aui-buttonitem-content{font-size:1em;line-height:130%;padding:4px 5px 3px 4px;margin:2px;white-space:nowrap;width:auto;overflow:visible;*padding:2px 5px 2px 4px;*width:1;}.aui-buttonitem-icon{display:inline-block;margin-top:-3px;vertical-align:middle;*text-indent:0;*margin-top:1px;}.aui-buttonitem-label{line-height:1em;display:inline-block;*line-height:1.4em;}.aui-buttonitem-label{padding:0 5px;}.aui-buttonitem-icon-only{font-size:0;height:16px;width:16px;overflow:hidden;padding:3px;box-sizing:content-box;-moz-box-sizing:content-box;-webkit-box-sizing:content-box;*height:24px;*width:24px;}.aui-buttonitem-icon-only .aui-buttonitem-icon{margin-top:0;*margin-top:-1px;*margin-left:-1px;}.gecko .aui-buttonitem-icon-only .aui-buttonitem-icon{margin-top:-3px;}\n");
      out.write(".aui-calendar{width:200px;}.aui-calendar-content{border:1px solid #ddd;padding:3px;}.aui-calendar-hd{padding:5px;position:relative;}.aui-calendar-hd .aui-icon{position:absolute;top:5px;}.aui-calendar-hd .aui-icon-circle-triangle-l{left:2px;}.aui-calendar-hd .aui-icon-circle-triangle-r{right:2px;}.aui-calendar-title{font-weight:bold;line-height:16px;margin:0 15px;text-align:center;}.aui-calendar-week{float:left;text-align:center;width:27px;}.aui-calendar-day{color:#000;float:left;margin:0 0 3px 3px;text-align:center;text-decoration:none;width:22px;}.aui-calendar-day-blank{float:left;height:16px;margin:0 0 3px 3px;text-align:center;width:24px;_width:23px;}.aui-calendar-day-padding-end,.aui-calendar-day-padding-start{border:1px dashed #aaa;opacity:.50;-ms-filter:alpha(opacity=50);filter:alpha(opacity=50);}.aui-calendar-day-hidden{display:none;}.aui-calendar-disabled{border-color:#C8C9CA #9E9E9E #9E9E9E #C8C9CA;border-style:solid;border-width:1px;opacity:.30;-ms-filter:alpha(opacity=30);filter:alpha(opacity=30);}.aui-calendar-link{text-decoration:none;}.aui-calendar-link-today{float:left;}.aui-calendar-link-none{float:right;}\n");
      out.write(".aui-carousel{position:relative;}.aui-carousel-item{position:absolute;z-index:1;}.aui-carousel-item-transition{z-index:2;}.aui-carousel-item-active{z-index:3;}.aui-carousel menu{background:#000;bottom:6px;position:absolute;list-style:none;margin:0;padding:3px;right:6px;z-index:4;}.aui-carousel menu li,.aui-carousel menu a{float:left;}.aui-carousel-menu-item{background:#474A4B;cursor:pointer;display:block;overflow:hidden;text-indent:-999999px;}.aui-carousel-menu-next,.aui-carousel-menu-play,.aui-carousel-menu-pause,.aui-carousel-menu-prev{height:18px;width:18px;margin:0 3px;}.aui-carousel-menu-next{background-image:url(");
      out.print( themeImagesPath );
      out.write("/aui/next.png);}.aui-carousel-menu-play{background-image:url(");
      out.print( themeImagesPath );
      out.write("/aui/play.png);}.aui-carousel-menu-pause{background-image:url(");
      out.print( themeImagesPath );
      out.write("/aui/pause.png);}.aui-carousel-menu-prev{background-image:url(");
      out.print( themeImagesPath );
      out.write("/aui/prev.png);}.aui-carousel-menu-index{height:9px;margin:4px 3px;width:9px;}.aui-carousel-menu-active{background:#FFF;}\n");
      out.write(".aui-colorpicker{width:315px;}.aui-colorpicker-container{height:192px;position:relative;}.aui-colorpicker-canvas{background:#f00 url(");
      out.print( themeImagesPath );
      out.write("/aui/picker_bg.png);background-color:#f00;height:184px;left:5px;outline:0 none;position:absolute;top:5px;width:184px;}.aui-colorpicker-controls{height:115px;left:226px;position:absolute;top:75px;width:80px;}.aui-colorpicker-controls .aui-field-content .aui-field-label{margin-top:0;width:20%;}.aui-colorpicker-controls .aui-field-content .aui-field-input-text{width:60%;}.aui-colorpicker-controls .aui-field-content{clear:both;padding:3px 0;}.aui-colorpicker-hue-thumb{cursor:default;height:7px;position:absolute;width:18px;}.aui-colorpicker-hue-thumb-image{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/color_indic.png) no-repeat 0 0;display:block;height:7px;margin-left:-2px;margin-top:-2px;width:18px;}.aui-colorpicker-hue-canvas{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/hue.png) no-repeat -2px -2px;height:186px;left:200px;outline:0 none;position:absolute;top:5px;width:14px;}.aui-colorpicker-mask{left:0;position:absolute;top:0;}.aui-colorpicker-swatch{border:2px solid #727C81;height:60px;left:226px;position:absolute;top:5px;width:60px;}.aui-colorpicker-swatch-current,.aui-colorpicker-swatch-original{background:#fff;height:30px;left:0;position:absolute;top:0;width:100%;}.aui-colorpicker-swatch-original{top:auto;bottom:0;cursor:pointer;}.aui-colorpicker-thumb{cursor:default;position:absolute;}.aui-colorpicker-thumb-image{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/select.png) no-repeat 0 0;display:block;height:11px;width:11px;}.aui-colorpicker-hue-slider-content{display:inline;position:static;}\n");
      out.write(".aui-colorpickergrid{width:340px;}.aui-colorpickergrid .aui-colorpicker-container{height:auto;line-height:0;text-align:center;}.aui-colorpickergrid-item{cursor:pointer;display:inline-block;height:15px;margin:3px;padding:2px;width:15px;}.aui-colorpickergrid-item-content{display:block;height:100%;}.aui-colorpickergrid-item{border:1px solid #ccc;}.aui-colorpickergrid-item:hover{border-color:#333;}.aui-colorpickergrid-item:hover{border-color:#333;}\n");
      out.write(".aui-colorpickergrid{width:350px;}.aui-colorpickergrid .aui-colorpicker-container{height:auto;line-height:0;text-align:center;}.aui-colorpickergrid-item{cursor:pointer;display:inline-block;height:15px;margin:3px;padding:2px;width:15px;}.aui-colorpickergrid-item-content{display:block;height:100%;}.aui-colorpickergrid-item{border:1px solid #ccc;}.aui-colorpickergrid-item:hover{border-color:#333;}.aui-colorpickergrid-item:hover{border-color:#333;}\n");
      out.write(".aui-databrowser{overflow:hidden;}.aui-databrowser-search{overflow-y:auto;}.aui-databrowser-tree{overflow-y:auto;}.aui-databrowser .aui-combobox-content{float:right;}.aui-databrowser .aui-databrowser-search-view-only .aui-combobox-content{float:none;}.aui-databrowser .aui-databrowser-search-list{margin:0;list-style:none;overflow:hidden;clear:left;}.aui-databrowser .aui-databrowser-search-list li{text-align:center;list-style:none;float:left;}.aui-databrowser-search-list-item-icon span{margin:0 auto;}.aui-databrowser{padding:10px;border:solid 1px #999;background-color:#fff;}.aui-databrowser .aui-combobox{margin-bottom:4px;}.aui-databrowser-search{border:solid 1px #ccc;}.aui-databrowser-tree{border:solid 1px #ccc;padding:5px;}.aui-databrowser .aui-databrowser-search-list li{margin:10px;}.aui-databrowser .aui-databrowser-search-list li div{font-size:9px;}.aui-databrowser-search-list-item-icon span{width:16px;height:16px;}\n");
      out.write("\n");
      out.write(".aui-datepicker-display,.aui-datepicker-select-wrapper,.aui-field-date .aui-datepicker-display,.aui-field-date .aui-datepicker-select-wrapper,.aui-datepicker-button-wrapper{float:left;}.aui-datepicker-button-wrapper .aui-buttonitem-content{margin:0;}.aui-datepicker-button-wrapper{margin:0 5px 0 2px;}.aui-datepicker-select-wrapper select{margin-left:2px;}\n");
      out.write(".aui-dialog{position:absolute}.aui-dialog .aui-icon-loading{margin:0 auto}.aui-dd-draggable .aui-dialog-hd{cursor:move}.aui-dialog-bd{overflow-y:auto}.aui-dialog-button{margin-right:2px}.aui-dialog-content{border:1px solid #c8c9ca;padding:5px}\n");
      out.write(".aui-dialog-iframe-node{border-width:0;overflow:auto;width:100%}\n");
      out.write(".aui-editable-content-wrapper{border:1px solid transparent;padding:3px;}.aui-editable{position:absolute;}.aui-editable-editing{visibility:hidden;}.aui-editable-hidden{display:none;}.aui-editable-form-triggers{background-color:transparent;border-width:0;padding:0;position:absolute;right:0;top:0;}.aui-editable-input{width:100%;}.aui-editable-content{padding-right:60px;}\n");
      out.write("\n");
      out.write("\n");
      out.write(".aui-editormenu-content .aui-panel-bd{padding:0;}.aui-editormenu-content-list{list-style:none;margin:0;padding:0;overflow-x:hidden;overflow-y:auto;}.aui-editormenu-content-list li{list-style:none;}.aui-editormenu{background-color:#fff;}.aui-editormenu-content-text{font-size:1.1em;font-weight:bold;background-color:#ddd;padding:3px;margin-bottom:1px;}.aui-editormenu-content-item{border:1px solid #eee;background-color:#eee;margin:1px;padding:3px;cursor:pointer;}.aui-editormenu-content-item:hover{color:#06c;border:1px solid #316ac5;background-color:#dff1ff;}\n");
      out.write(".aui-editormenu-content-list{list-style:none;margin:0;padding:0;}.aui-editormenu-content-list li{list-style:none;}.aui-editortoolbar-content .aui-toolbar-horizontal{margin-right:3px;}.aui-editormenu-hidden{position:absolute;clip:rect(0pt,0pt,0pt,0pt);}.aui-editortoolbar-source-textarea{width:100%;}.aui-editortoolbar-insertimage-content .aui-button-holder,.aui-editortoolbar-insertlink-content .aui-button-holder{text-align:right;}.aui-editortoolbar-insertimage-content .aui-field-content,.aui-editortoolbar-insertlink-content .aui-field-content{clear:both;}.aui-editortoolbar-align-node{position:absolute;}.aui-editortoolbar-insertlink-content{padding:10px;}.aui-editormenu-content{background-color:#fff;}.aui-editortoolbar-content select{margin:3px 3px 0 0;}.aui-editormenu-content-text{font-size:1.1em;font-weight:bold;background-color:#ddd;padding:3px;margin-bottom:1px;}.aui-editormenu-content-item{border:1px solid #eee;background-color:#eee;margin:1px;padding:3px;cursor:pointer;}.aui-editormenu-content-item:hover{color:#06c;border:1px solid #316ac5;background-color:#dff1ff;}.aui-editortoolbar-content .aui-icon-styles{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/styles.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-forecolor{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/forecolor.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-backcolor{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/backcolor.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-bold{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/bold.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-italic{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/italic.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-underline{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/underline.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-strikethrough{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/strike.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-insertimage{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/image.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-insertlink{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/hyperlink.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-justifyleft{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/justify_left.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-justifycenter{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/justify_center.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-justifyright{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/justify_right.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-insertunorderedlist{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/unordered_list.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-insertorderedlist{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/ordered_list.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-indent{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/indent.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-outdent{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/outdent.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-format{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/format.png) no-repeat transparent;}.aui-editortoolbar-content .aui-icon-source{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/source.png) no-repeat transparent;}.aui-editortoolbar-insertimage-content .aui-icon-align-block{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/align_block.png) no-repeat;}.aui-editortoolbar-insertimage-content .aui-icon-align-left{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/align_left.png) no-repeat;}.aui-editortoolbar-insertimage-content .aui-icon-align-inline{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/align_inline.png) no-repeat;}.aui-editortoolbar-insertimage-content .aui-icon-align-right{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/align_right.png) no-repeat;}.aui-editortoolbar-insertimage-content,.aui-editortoolbar-insertlink-content{padding:10px;}.aui-editortoolbar-insertimage-content .aui-field-numeric,.aui-editortoolbar-size-separator{display:inline-block;vertical-align:middle;}.aui-editortoolbar-insertimage-content .aui-field-numeric input,.aui-editortoolbar-size-separator{width:30px;}.aui-editortoolbar-size-separator{text-align:center;}\n");
      out.write(".aui-editortoolbar-content .aui-icon-quote{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/quote.png) no-repeat transparent;}\n");
      out.write(".aui-combobox-content .aui-field-content{float:none;}.aui-combobox-content{white-space:nowrap;vertical-align:middle;}.aui-combobox-content .aui-field-content{display:inline-block;}.aui-combobox-content .aui-field-input{display:block;}\n");
      out.write(".aui-textarea-height-monitor{line-height:normal;left:0;padding:0;position:absolute;top:0;white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;}.ie .aui-textarea-height-monitor{border:1px solid #000;zoom:1;}\n");
      out.write(".aui-image-viewer{z-index:3000}.aui-image-viewer-link{outline:0 none}.aui-image-viewer-content .aui-icon-loading{margin:0 auto}.aui-image-viewer-bd{background:#fff;padding:5px;width:16px}.aui-image-viewer-content .aui-widget-ft{background:#fff;color:#222;padding:0 10px 10px;font-size:larger;font-weight:bold}.aui-image-viewer-loading .aui-image-viewer-content .aui-widget-ft{visibility:hidden}.aui-image-viewer-info{color:#777}.aui-image-viewer-arrow{height:54px;outline:0;position:absolute;width:30px;z-index:3000}.aui-image-viewer-arrow-left{background-image:url(");
      out.print( themeImagesPath );
      out.write("/aui/arrow-left.png);left:100px}.aui-image-viewer-arrow-right{background-image:url(");
      out.print( themeImagesPath );
      out.write("/aui/arrow-right.png);right:100px}.aui-image-viewer-close{background-image:url(");
      out.print( themeImagesPath );
      out.write("/aui/close.png);background-repeat:no-repeat;height:19px;outline:0;position:fixed;right:20px;top:20px;width:19px;z-index:3000}.ie6 .aui-image-viewer-close{position:absolute}\n");
      out.write(".aui-image-gallery-paginator{bottom:0;position:fixed;width:100%;z-index:2000}.ie6 .aui-image-gallery-paginator{position:absolute}.aui-image-gallery-paginator-content{padding:30px 30px 5px}.aui-image-gallery-paginator .aui-paginator-link{color:white;font-size:larger;font-weight:bold;border:0;padding:0}.aui-image-gallery-paginator .aui-image-gallery-paginator-thumb{background:transparent url() no-repeat 50% 50%;border:0;cursor:pointer;display:inline-block;height:55px;overflow:hidden;padding:3px;width:55px;margin:1px}.aui-image-gallery-paginator .aui-paginator-current-page .aui-image-gallery-paginator-thumb{border:3px solid #fff;padding:0}.aui-image-gallery-paginator-links{text-align:center}.aui-image-gallery-player{padding-bottom:10px;text-align:center}\n");
      out.write(".aui-loadingmask-masked{overflow:hidden!important;}.aui-loadingmask-masked-relative{position:relative!important;}.aui-loadingmask{height:100%;width:100%;zoom:1;}.aui-loadingmask-content{background:#ccc;}.aui-loadingmask-message{background:#eee;border:1px solid;border-color:#bbb #999 #999 #bbb;left:50%;padding:2px;position:absolute;top:50%;}.aui-loadingmask-message-content{background:#fff url(");
      out.print( themeImagesPath );
      out.write("/aui/loading_indicator.gif) no-repeat 5px 50%;border:1px solid #ccc;color:#111;line-height:1;padding:5px 10px 5px 25px;}.aui-loadingmask-message{background:#d4d4d4 url(");
      out.print( themeImagesPath );
      out.write("/aui/../../../../../build/aui-skin-classic/images/common/state_default_bg.png) repeat-x 0 0;border:1px solid;border-color:#c8c9ca #9e9e9e #9e9e9e #c8c9ca;}\n");
      out.write(".aui-overlaycontextpanel{position:absolute;z-index:9999;}.aui-overlaycontextpanel-content{border-width:2px;padding:8px;position:relative;}.aui-overlaycontextpanel-hidden{visibility:hidden;top:-10000px!important;left:-10000px!important;}.aui-overlaycontextpanel .aui-overlaycontextpanel-pointer,.aui-overlaycontextpanel .aui-overlaycontextpanel-pointer-inner{position:absolute;width:0;height:0;background:none;}.aui-overlaycontextpanel-arrow-tr .aui-overlaycontextpanel-pointer,.aui-overlaycontextpanel-arrow-tc .aui-overlaycontextpanel-pointer,.aui-overlaycontextpanel-arrow-tl .aui-overlaycontextpanel-pointer{top:-14px;border-top:0;border-bottom-width:14px;}.aui-overlaycontextpanel-arrow-tr .aui-overlaycontextpanel-pointer{border-left:18px dotted transparent;border-right:0;right:10px;}.aui-overlaycontextpanel-arrow-tc .aui-overlaycontextpanel-pointer{border-left:10px dotted transparent;border-right:10px dotted transparent;left:50%;margin-left:-10px;}.aui-overlaycontextpanel-arrow-tl .aui-overlaycontextpanel-pointer{border-left:0;border-right:18px dotted transparent;left:10px;}.aui-overlaycontextpanel-arrow-tr .aui-overlaycontextpanel-pointer-inner,.aui-overlaycontextpanel-arrow-tc .aui-overlaycontextpanel-pointer-inner,.aui-overlaycontextpanel-arrow-tl .aui-overlaycontextpanel-pointer-inner{border-bottom:10px solid #fff;bottom:-14px;}.aui-overlaycontextpanel-arrow-tr .aui-overlaycontextpanel-pointer-inner{border-left:12px dotted transparent;border-right:0;right:2px;}.aui-overlaycontextpanel-arrow-tc .aui-overlaycontextpanel-pointer-inner{border-left:8px dotted transparent;border-right:8px dotted transparent;left:-8px;}.aui-overlaycontextpanel-arrow-tl .aui-overlaycontextpanel-pointer-inner{border-left:0;border-right:12px dotted transparent;left:2px;}.aui-overlaycontextpanel-arrow-rb .aui-overlaycontextpanel-pointer,.aui-overlaycontextpanel-arrow-rc .aui-overlaycontextpanel-pointer,.aui-overlaycontextpanel-arrow-rt .aui-overlaycontextpanel-pointer{right:-14px;border-right:0;border-left-width:14px;}.aui-overlaycontextpanel-arrow-rb .aui-overlaycontextpanel-pointer{border-bottom:0;border-top:18px dotted transparent;bottom:10px;}.aui-overlaycontextpanel-arrow-rc .aui-overlaycontextpanel-pointer{border-bottom:10px dotted transparent;border-top:10px dotted transparent;bottom:50%;margin-bottom:-10px;}.aui-overlaycontextpanel-arrow-rt .aui-overlaycontextpanel-pointer{border-bottom:18px dotted transparent;border-top:0;top:10px;}.aui-overlaycontextpanel-arrow-rb .aui-overlaycontextpanel-pointer-inner,.aui-overlaycontextpanel-arrow-rc .aui-overlaycontextpanel-pointer-inner,.aui-overlaycontextpanel-arrow-rt .aui-overlaycontextpanel-pointer-inner{border-left:10px solid #fff;left:-14px;}.aui-overlaycontextpanel-arrow-rb .aui-overlaycontextpanel-pointer-inner{border-bottom:0;border-top:12px dotted transparent;bottom:2px;}.aui-overlaycontextpanel-arrow-rc .aui-overlaycontextpanel-pointer-inner{border-bottom:8px dotted transparent;border-top:8px dotted transparent;bottom:-8px;}.aui-overlaycontextpanel-arrow-rt .aui-overlaycontextpanel-pointer-inner{border-bottom:12px dotted transparent;border-top:0;top:2px;}.aui-overlaycontextpanel-arrow-br .aui-overlaycontextpanel-pointer,.aui-overlaycontextpanel-arrow-bc .aui-overlaycontextpanel-pointer,.aui-overlaycontextpanel-arrow-bl .aui-overlaycontextpanel-pointer{bottom:-14px;border-bottom:0;border-top-width:14px;}.aui-overlaycontextpanel-arrow-br .aui-overlaycontextpanel-pointer{border-left:18px dotted transparent;border-right:0;right:10px;}.aui-overlaycontextpanel-arrow-bc .aui-overlaycontextpanel-pointer{border-left:10px dotted transparent;border-right:10px dotted transparent;left:50%;margin-left:-10px;}.aui-overlaycontextpanel-arrow-bl .aui-overlaycontextpanel-pointer{border-left:0;border-right:18px dotted transparent;left:10px;}.aui-overlaycontextpanel-arrow-br .aui-overlaycontextpanel-pointer-inner,.aui-overlaycontextpanel-arrow-bc .aui-overlaycontextpanel-pointer-inner,.aui-overlaycontextpanel-arrow-bl .aui-overlaycontextpanel-pointer-inner{border-top:10px solid #fff;top:-14px;}.aui-overlaycontextpanel-arrow-br .aui-overlaycontextpanel-pointer-inner{border-left:12px dotted transparent;border-right:0;right:2px;}.aui-overlaycontextpanel-arrow-bc .aui-overlaycontextpanel-pointer-inner{border-left:8px dotted transparent;border-right:8px dotted transparent;left:-8px;}.aui-overlaycontextpanel-arrow-bl .aui-overlaycontextpanel-pointer-inner{border-left:0;border-right:12px dotted transparent;left:2px;}.aui-overlaycontextpanel-arrow-lb .aui-overlaycontextpanel-pointer,.aui-overlaycontextpanel-arrow-lc .aui-overlaycontextpanel-pointer,.aui-overlaycontextpanel-arrow-lt .aui-overlaycontextpanel-pointer{left:-14px;border-left:0;border-right-width:14px;}.aui-overlaycontextpanel-arrow-lb .aui-overlaycontextpanel-pointer{border-bottom:0;border-top:18px dotted transparent;bottom:10px;}.aui-overlaycontextpanel-arrow-lc .aui-overlaycontextpanel-pointer{border-bottom:10px dotted transparent;border-top:10px dotted transparent;bottom:50%;margin-bottom:-10px;}.aui-overlaycontextpanel-arrow-lt .aui-overlaycontextpanel-pointer{border-bottom:18px dotted transparent;border-top:0;top:10px;}.aui-overlaycontextpanel-arrow-lb .aui-overlaycontextpanel-pointer-inner,.aui-overlaycontextpanel-arrow-lc .aui-overlaycontextpanel-pointer-inner,.aui-overlaycontextpanel-arrow-lt .aui-overlaycontextpanel-pointer-inner{border-right:10px solid #fff;right:-14px;}.aui-overlaycontextpanel-arrow-lb .aui-overlaycontextpanel-pointer-inner{border-bottom:0;border-top:12px dotted transparent;bottom:2px;}.aui-overlaycontextpanel-arrow-lc .aui-overlaycontextpanel-pointer-inner{border-bottom:8px dotted transparent;border-top:8px dotted transparent;bottom:-8px;}.aui-overlaycontextpanel-arrow-lt .aui-overlaycontextpanel-pointer-inner{border-bottom:12px dotted transparent;border-top:0;top:2px;}.aui-overlaycontextpanel-arrow-tl,.aui-overlaycontextpanel-arrow-tc,.aui-overlaycontextpanel-arrow-tr{margin-top:12px;}.aui-overlaycontextpanel-arrow-bl,.aui-overlaycontextpanel-arrow-bc,.aui-overlaycontextpanel-arrow-br{margin-bottom:12px;}\n");
      out.write(".aui-overlaycontextpanel-arrow-lt,.aui-overlaycontextpanel-arrow-lc,.aui-overlaycontextpanel-arrow-lb{margin-left:12px;}.aui-overlaycontextpanel-arrow-rt,.aui-overlaycontextpanel-arrow-rc,.aui-overlaycontextpanel-arrow-rb{margin-right:12px;}\n");
      out.write(".aui-overlaymask-content{background:#000;}\n");
      out.write(".aui-paginator{display:none;}.aui-paginator-container{font-size:14px;}.aui-paginator-link{color:#000;text-decoration:none;outline-style:none;}.aui-paginator-page-link{border:1px solid #ccc;padding:3px 6px;}.aui-paginator-current-page{border:0;font-weight:bold;}.aui-paginator-current-page-report,.aui-paginator-total{font-weight:bold;}.aui-paginator-first-link,.aui-paginator-last-link,.aui-paginator-prev-link,.aui-paginator-next-link,.aui-paginator-page-link{margin:2px;}\n");
      out.write(".aui-panel-content{border:1px solid #999;}.aui-panel-icons{float:right;}.aui-panel-hd{background:#ccc;border-bottom:1px solid #999;font-weight:bold;padding:2px 3px 2px 4px;}.aui-panel-bd{padding:3px;}.aui-panel-ft{background:#eee;border-top:1px solid #ccc;padding:5px 10px;text-align:right;visibility:inherit;}.aui-panel-collapsed .aui-panel-hd{border-bottom-width:0;}.aui-panel-hd-text{float:left;line-height:2;}.aui-panel-hd{background:#c0c2c5 url(");
      out.print( themeImagesPath );
      out.write("/aui/../../../../../build/aui-skin-classic/images/panel/header_bg.png) repeat-x 0 0;border:0 solid;border-color:#C8C9CA #aeb2b8 #aeb2b8 #C8C9CA;border-bottom-width:1px;padding-left:10px;text-shadow:1px 1px #fff;}\n");
      out.write(".aui-portal-layout-drag-indicator{background:#3C56B5;height:6px;position:absolute;width:100px;z-index:900;}.aui-portal-layout-drag-indicator-icon{position:absolute;top:-5px;}.aui-portal-layout-drag-indicator-icon-left{left:-13px;}.aui-portal-layout-drag-indicator-icon-right{right:-13px;}\n");
      out.write(".aui-progress-bar-content{background:white;border:1px solid #949DAD;margin:2px;overflow:hidden;padding:1px;position:relative;}.aui-progress-bar-content{height:100%;}.aui-progress-bar-status{background:#D4E4FF;overflow:hidden;position:relative;}.aui-progress-bar-horizontal .aui-progress-bar-status{height:100%;}.aui-progress-bar-vertical .aui-progress-bar-status{width:100%;}.aui-progress-bar-text{left:0;position:absolute;text-align:center;top:0;width:100%;}\n");
      out.write(".aui-rating-element{background:transparent url(");
      out.print( themeImagesPath );
      out.write("/aui/rating.png) no-repeat scroll 0 0;width:17px;height:16px;display:block;float:left;font-size:0;text-indent:-9999em;}.aui-rating-element-on{background-position:0 -16px;}.aui-rating-element-hover{background-position:0 -32px;}.aui-rating-label-element{display:block;font-size:12px;padding:0 2px;}.aui-thumbrating .aui-rating-element{background:transparent url(");
      out.print( themeImagesPath );
      out.write("/aui/rating_thumbs.png) no-repeat scroll 0 0;height:18px;width:18px;}.aui-thumbrating .aui-rating-thumb-up.aui-rating-element-off{background-position:0 -57px;}.aui-thumbrating .aui-rating-thumb-up.aui-rating-element-hover,.aui-thumbrating .aui-rating-thumb-up.aui-rating-element-on{background-position:0 -38px;}.aui-thumbrating .aui-rating-thumb-down.aui-rating-element-off{background-position:0 -19px;}.aui-thumbrating .aui-rating-thumb-down.aui-rating-element-hover,.aui-thumbrating .aui-rating-thumb-down.aui-rating-element-on{background-position:0 0;}.aui-thumbrating .aui-rating-label-element{padding:0 2px;float:left;}\n");
      out.write(".aui-resize,.aui-resize-wrapper{z-index:0;zoom:1}.aui-resize-handle{position:absolute;display:block;z-index:100;zoom:1}.aui-resize-proxy{position:absolute;border:1px dashed #000;position:absolute;z-index:10000}.aui-resize-hidden-handles .aui-resize-handle{opacity:0;filter:alpha(opacity=0)}.aui-resize-handle-t,.aui-resize-handle-b{width:100%;left:0;height:6px}.aui-resize-handle-l,.aui-resize-handle-r{height:100%;top:0;width:6px}.aui-resize-handle-t{cursor:n-resize;top:0}.aui-resize-handle-b{cursor:s-resize;bottom:0}.aui-resize-handle-l{cursor:w-resize;left:0}.aui-resize-handle-r{cursor:e-resize;right:0}.aui-resize-handle-inner{position:absolute;zoom:1}.aui-resize-handle-inner-t,.aui-resize-handle-inner-b{margin-left:-8px;left:50%}.aui-resize-handle-inner-l,.aui-resize-handle-inner-r{margin-top:-8px;top:50%}.aui-resize-handle-inner-t{top:-5px}.aui-resize-handle-inner-b{bottom:-4px}.aui-resize-handle-inner-l{left:-4px}.aui-resize-handle-inner-r{right:-5px}.aui-resize-handle-tr,.aui-resize-handle-br,.aui-resize-handle-tl,.aui-resize-handle-bl{height:16px;width:16px;z-index:200}.aui-resize-handle-tr{cursor:ne-resize;top:0;right:0}.aui-resize-handle-tl{cursor:nw-resize;top:0;left:0}.aui-resize-handle-br{cursor:se-resize;bottom:0;right:0}.aui-resize-handle-bl{cursor:sw-resize;bottom:0;left:0}\n");
      out.write(".aui-scheduler-base-content{border-color:#bcf;border-style:solid;border-width:0 3px 3px;overflow:hidden}.aui-scheduler-base-nav{width:61px;padding:5px}.aui-scheduler-base-nav .aui-scheduler-base-icon-next,.aui-scheduler-base-nav .aui-scheduler-base-icon-prev{float:left}.aui-scheduler-base-nav .aui-scheduler-base-icon-next{margin-left:3px}.aui-scheduler-base-hd{background:#bcf}.aui-scheduler-base-controls{float:left}.aui-scheduler-base-views{float:right}.aui-scheduler-base-controls{line-height:27px;width:350px}.aui-scheduler-base-views{line-height:27px;width:160px}.aui-scheduler-base-controls .aui-scheduler-base-current-date,.aui-scheduler-base-controls .aui-scheduler-base-nav,.aui-scheduler-base-controls .aui-scheduler-base-today{float:left}.aui-scheduler-base-views .aui-scheduler-base-view{color:#fff;float:left;margin-right:10px}.aui-scheduler-base-controls .aui-scheduler-base-today{color:#fff;margin:0 5px 0 10px}.aui-scheduler-base-controls .aui-scheduler-base-current-date{font-weight:bold}.aui-scheduler-base-icon-next,.aui-scheduler-base-icon-prev{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/scheduler-arrows.png) no-repeat 0 0;height:17px;width:29px}.aui-scheduler-base-icon-prev{background-position:0 -17px}\n");
      out.write(".aui-scheduler-view-day-hidden,.aui-scheduler-view-week-hidden,.aui-scheduler-view-year-hidden,.aui-scheduler-view-month-hidden{display:none}.aui-scheduler-view-table{table-layout:fixed;width:100%}.aui-scheduler-view-table-col{vertical-align:top;border-left:1px solid #ddd}.aui-scheduler-view-table-col-shim{height:1250px;margin-bottom:-1250px;position:relative}.aui-scheduler-view-table-colblank{width:40px}.aui-scheduler-view-table-coltime{background-color:#e3e9ff;border-right:1px solid #bcf;color:#aaadbc;text-align:right;width:40px;padding-right:2px}.aui-scheduler-view-table-time{height:52px}.aui-scheduler-view-scrollable{position:relative;overflow:scroll;overflow-y:scroll;overflow-x:hidden}.aui-scheduler-view-noscroll{position:relative;overflow:hidden;overflow-y:hidden;overflow-x:hidden}.aui-scheduler-view-content{position:relative}.aui-scheduler-view-colgrid{background:#ddd}.aui-scheduler-view-grid{background:#fff;position:relative}.aui-scheduler-view-day-header-table{width:100%;table-layout:fixed}.aui-scheduler-view-markers{position:absolute;width:100%;z-index:0}.aui-scheduler-view-markercell{height:52px}.aui-scheduler-view-marker-division{height:25px;border-bottom:1px dotted #ddd;border-top:1px solid #ddd}.aui-scheduler-view-day-content .aui-widget-hd{background-color:#e3e9ff;border-bottom:1px solid #bcf;color:#aaadbc}.aui-scheduler-view-day-content .aui-widget-bd{height:600px}.aui-scheduler-view-day-header-day-first{width:40px}.aui-scheduler-view-day-header-day-pad-right{width:15px}.aui-scheduler-view-day-header-day a{font-size:11px;font-weight:normal;text-decoration:none;display:block}.aui-scheduler-view-day-header-day a:hover{text-decoration:underline}.aui-scheduler-view-day-header-day{text-align:center}.aui-scheduler-view-day{background:#fff}\n");
      out.write(".aui-scheduler-event-hidden{display:none}.aui-scheduler-event-content{text-align:left}.aui-scheduler-event-title{font:bold 80% Verdana,sans-serif;overflow:hidden;text-align:left;white-space:nowrap}.aui-scheduler-event.aui-scheduler-event-repeated{opacity:.8;-ms-filter:alpha(opacity=80);filter:alpha(opacity=80)}.aui-dd-proxy .aui-scheduler-event{opacity:.7;filter:alpha(opacity=70)}.aui-scheduler-event.aui-scheduler-event-proxy{position:relative;opacity:.7;filter:alpha(opacity=70);width:99.88%}.aui-scheduler-event{-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;color:#fff;height:70px;left:0;position:absolute;top:0;width:95%;z-index:400;overflow:hidden}.aui-scheduler-today{background:#fff7d7;border:1px solid #fad163;border-width:0 1px}.aui-scheduler-today-hd{background:#fad163;border:1px solid #fad163;border-top:0;border-bottom:0}.aui-scheduler-view-month-header-day{text-align:center}.aui-scheduler-view-month-header-table{width:100%;table-layout:fixed}.aui-scheduler-view-month-content .aui-widget-hd{background-color:#e3e9ff;border-bottom:1px solid #bcf;color:#aaadbc}.aui-scheduler-view-month-header-day div{font-size:11px;font-weight:normal}.aui-scheduler-view-month-container{height:100%;position:relative;white-space:nowrap;line-height:14px}.aui-scheduler-view-month-row-container{background:#fff;bottom:0;left:0;overflow:hidden;position:absolute;top:0;width:100%}.aui-scheduler-view-month-row{left:0;overflow:hidden;position:absolute;width:100%}.aui-scheduler-view-month-table-grid-first{border-top:0}.aui-scheduler-view-month-table-grid{height:100%;left:0;position:absolute;table-layout:fixed;top:0;width:100%}.aui-scheduler-view-month-colgrid{border-left:1px solid #ddd}.aui-scheduler-view-month-colgrid-first{border-left:0 none}.aui-scheduler-view-month-table-data{width:100%;table-layout:fixed;position:relative}.aui-scheduler-view-month-table-data-col{border-left:0 none;padding:1px 2px 0}.aui-scheduler-view-month-table-data-col-title.aui-scheduler-view-month-table-data-col-nomonth{background-color:#fff;color:#aaa}.aui-scheduler-view-month-table-data-first .aui-scheduler-view-month-table-data-col-title{border-top:0}.aui-scheduler-view-month-table-data-col-title{background-color:#f8f9ff;color:#666;line-height:16px;overflow:hidden;padding-right:2px;text-align:right;border-left:1px solid #ddd;border-top:1px solid #ddd}.aui-scheduler-view-month-table-data-col-title-down{border-top:1px solid #fad163}.aui-scheduler-view-month-colgrid-next,.aui-scheduler-view-month-table-data-col-title-first,.aui-scheduler-view-month-table-data-col-title-next{border-left:0}.aui-scheduler-view-month-colgrid-today{background-color:#fff7d7;border-left:1px solid #fad163;border-right:1px solid #fad163}.aui-scheduler-view-month-table-data-col-title-today{background:#fad163;border:1px solid #fad163}.aui-scheduler-view-month-table-data-event.aui-scheduler-view-month-table-data-event-right{padding-right:16px}.aui-scheduler-view-month-table-data-event.aui-scheduler-view-month-table-data-event-left{padding-left:16px}.aui-scheduler-view-month-table-data-event{-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;background-color:#d96666;cursor:pointer;margin-bottom:1px;color:white;overflow:hidden;padding:1px 1px 1px 3px;white-space:nowrap;position:relative}.aui-scheduler-view-month-table-data-event-repeated{background-color:transparent;color:#d96666}.aui-scheduler-view-month-table-data-event .aui-icon-arrowstop-1-l{left:0;position:absolute;top:0}.aui-scheduler-view-month-table-data-event .aui-icon-arrowstop-1-r{position:absolute;right:0;top:0}.aui-scheduler-event.aui-scheduler-event-recorder{width:99.88%;position:relative;opacity:.7;filter:alpha(opacity=70)}.aui-scheduler-event-recorder-form .aui-scheduler-event-recorder-field-hint{color:#777;font-size:.9em}.aui-scheduler-event-recorder-form .aui-scheduler-event-recorder-button-row{margin-top:10px;text-align:center}.aui-scheduler-event-recorder-form .aui-field-content{display:block;clear:both;padding:7px 0}.aui-scheduler-event-recorder-form .aui-fieldset-legend{padding-bottom:1em}.aui-scheduler-event-recorder-form .aui-button-row{padding:5px}.aui-scheduler-event-icons .aui-icon{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/scheduler-notification.png) no-repeat 0 0;height:7px;width:8px}.aui-scheduler-event-icons{position:absolute;right:3px;top:3px;display:none}.aui-scheduler-event-icons .aui-icon{float:left;display:none}.aui-scheduler-event-repeated .aui-scheduler-event-icons,.aui-scheduler-event-repeated .aui-scheduler-event-icon-repeated{display:block}.aui-scheduler-event-repeated .aui-scheduler-event-icons .aui-scheduler-event-icon-repeated{background-position:-9px 0}.aui-scheduler-event-repeater .aui-scheduler-event-icons,.aui-scheduler-event-repeater .aui-scheduler-event-icon-repeater,.aui-scheduler-event-repeater .aui-scheduler-event-icon-repeated{display:block}.aui-scheduler-event-repeater .aui-scheduler-event-icons .aui-scheduler-event-icon-repeated{background-position:-9px 0}.aui-scheduler-event-repeater .aui-scheduler-event-icons .aui-scheduler-event-icon-repeater{background-position:0 0}.aui-scheduler-event-disabled .aui-scheduler-event-icons,.aui-scheduler-event-disabled .aui-scheduler-event-icon-disabled{display:block}.aui-scheduler-event-disabled .aui-scheduler-event-icons .aui-scheduler-event-icon-disabled{background-position:0 -28px}\n");
      out.write(".aui-sortable-proxy{background:#ccc;}.aui-sortable-placeholder{padding:0;margin:0;border:1px solid #ccc;background:#eee;}.aui-sortable-dragging{visibility:hidden;}.aui-sortable-handle,.aui-sortable-no-handles{cursor:move;}\n");
      out.write(".aui-tabview-list,.aui-tabview-list-content{border-bottom:1px solid #000;list-style:none;margin:0;padding:0;}.aui-tabview-list:after,.aui-tabview-list-content:after{clear:both;content:\"\";display:block;height:0;}.aui-tabview-list,.aui-tabview-list-content{zoom:1;}.aui-tabview-list,.aui-tabview-list-content{margin-bottom:1em;}.aui-tab{border-bottom-width:0;float:left;margin:0 .2em -1px 0;position:relative;}.aui-tab-content{float:left;padding:.5em 1em;}.aui-tab-active{padding-bottom:1px;}.aui-tab-disabled{cursor:text;opacity:.6;filter:alpha(opacity=60);}.aui-tab-disabled a{cursor:text;}.aui-tabview-list,.aui-tabview-list-content{border:1px solid #c8c9ca;padding:4px;}.ie7 .aui-tabview-list,.ie7 .aui-tabview-list-content{overflow:hidden;}.aui-tab{background:transparent;border-width:0;font-weight:bold;}.aui-tab-content{padding:3px 10px;}.aui-tab-active{background:#333;padding:0;position:relative;z-index:10;}.aui-tab-active,.aui-tab-active a{color:#fff;text-decoration:none;text-shadow:-1px -1px #000;}.aui-tab-active:after{border:10px solid;border-bottom-width:0;border-color:#333 transparent transparent;bottom:-7px;content:'-';display:block;height:0;left:50%;margin-left:-10px;position:absolute;text-indent:-9999px;width:0;z-index:20;}\n");
      out.write(".aui-tabview-wrapper{position:relative;}.aui-tabview-wrapper .aui-tabview-list{border-width:0;margin:0;padding:0;position:relative;width:9999px;}.aui-tabview-wrapper{overflow:hidden;}.aui-tabviewmenu-trigger{position:absolute;top:0;z-index:10;}.aui-tabviewmenu-list-content{list-style:none;margin:0;}.aui-tabviewmenu-item{margin:0;}.aui-tabview-list-content{border-width:0;margin-bottom:0;}.aui-tabviewmenu-trigger{height:100%;right:0;width:20px;}.aui-tabview-wrapper{border:1px solid #C8C9CA;margin-bottom:1em;}.aui-tabview-wrapper .aui-tab-active:after{border:10px solid;border-top-width:0;border-color:transparent transparent #fff;bottom:-5px;content:'-';display:block;height:0;left:50%;margin-left:-10px;position:absolute;text-indent:-9999px;width:0;z-index:20;}.aui-tabviewmenu-trigger{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/menu_trigger_bg.png) no-repeat 0 50%;}.aui-tabviewmenu-item{border-bottom:1px solid #DEDEDE;padding:2px;}.aui-tabviewmenu-item a{padding:2px 5px;text-decoration:none;color:#369;}.aui-tabviewmenu-item a:hover{background:#ccc;}.aui-tabviewmenu-list{position:absolute;width:auto;}.aui-tabviewmenu-list-content{background:#FFF;min-width:150px;}.aui-tabviewmenu-list-content{background:#CCC9C9 url(");
      out.print( themeImagesPath );
      out.write("/aui/../../../../../build/aui-skin-classic/images/menu/bg.png) repeat-x 0 0;border:1px solid;border-color:#AEAFB1 #777879 #777879 #AEAFB1;padding:2px 0;}.aui-tabviewmenu-list-content li{border-bottom:1px solid #BCBBBB;border-top:1px solid #EEE;display:block;list-style:none;margin:0;padding:0;}.ie7 .aui-tabviewmenu-list-content li{z-index:10;}.aui-tabviewmenu-list-content li.first{border-top-width:0;}.aui-tabviewmenu-list-content li.last{border-bottom-width:0;}.aui-tabviewmenu-list-content li a{color:#2C2F34;display:block;padding:4px 5px;text-decoration:none;}.aui-tabviewmenu-list-content li a:hover{background-color:#5B677D;color:#FFF;text-shadow:-1px -1px #2C2F34;}.aui-tabviewmenu-list-content a{font-weight:bold;text-shadow:1px 1px #FFF;}\n");
      out.write(".aui-textboxlist-content{border:1px solid #999;overflow:hidden;padding:4px 5px 0;}.aui-textboxlist-content .aui-field-input-text{border-width:0;outline-width:0;padding:3px 0 2px;}.aui-textboxlistentry-holder{list-style:none;margin:0;}.aui-textboxlistentry-holder li{float:left;line-height:1;margin:0 5px 4px 0;}.aui-textboxlistentry{background-color:#dee7f8;border:1px solid #cad8f3;padding:5px 25px 5px 11px;position:relative;}.aui-textboxlistentry-focused{background-color:#598bec;border-color:#224fa8;color:#fff;outline-width:0;}.aui-textboxlistentry-close{cursor:pointer;position:absolute;right:3px;top:3px;}.aui-textboxlistentry-close-hover{background-color:#cad8f3;}.aui-textboxlistentry-focused .aui-textboxlistentry-close{background-color:#fff;}\n");
      out.write(".aui-toolbar{white-space:nowrap;}.aui-toolbar-content{float:none;display:inline-block;vertical-align:top;}.aui-toolbar-item{margin:0;display:inline-block;vertical-align:top;}.aui-toolbar-vertical .aui-toolbar-item{display:block;width:100%;}.aui-toolbar-vertical .aui-buttonitem-icon-only{text-align:center;width:auto;}.aui-toolbar-vertical .aui-buttonitem-icon-label{text-align:left;}.aui-toolbar-vertical .aui-buttonitem-icon-only .aui-buttonitem-icon{float:none;}.aui-toolbar-horizontal .aui-toolbarspacer{width:4px;display:inline-block;}.aui-toolbar-vertical .aui-toolbarspacer{height:4px;display:block;}\n");
      out.write(".aui-tooltip{max-width:600px;}.aui-tooltip-content{border-width:2px;padding:8px;position:relative;}\n");
      out.write(".aui-tree-view{overflow:auto;}.aui-tree-view-content{margin:0;}.aui-tree-view ul{list-style:none;}.aui-tree-container{margin:0 0 0 20px;}.aui-tree-node-content .aui-tree-hitarea{height:18px;width:16px;cursor:pointer;}.aui-tree-node .yui3-dd-draggable,.aui-tree-node .yui3-dd-draggable .aui-tree-label{cursor:pointer;}.aui-tree-collapsed .aui-tree-hitarea{background-image:url(");
      out.print( themeImagesPath );
      out.write("/aui/arrows.png);background-repeat:no-repeat;}.aui-tree-expanded .aui-tree-hitarea{background-image:url(");
      out.print( themeImagesPath );
      out.write("/aui/arrows.png);background-repeat:no-repeat;background-position:-16px 50%;}.aui-tree-collapsed.aui-tree-node-over .aui-tree-hitarea{background-position:-32px 50%;}.aui-tree-expanded.aui-tree-node-over .aui-tree-hitarea{background-position:-48px 50%;}.aui-tree-hitarea,.aui-tree-label,.aui-tree-icon{float:left;}.aui-tree-label{cursor:default;line-height:19px;margin-left:2px;}.aui-tree-node-leaf{margin-left:15px;}.aui-tree-hidden{display:none;}.aui-tree-node-selected .aui-tree-label{background:#eee;}.aui-tree-file .aui-tree-icon{height:18px;width:18px;}.aui-tree-node-leaf .aui-tree-icon{background:transparent url(");
      out.print( themeImagesPath );
      out.write("/aui/file.png) no-repeat 50% 50%;}.aui-tree-file .aui-tree-expanded .aui-tree-icon{background:transparent url(");
      out.print( themeImagesPath );
      out.write("/aui/folder_opened.png) no-repeat 50% 50%;}.aui-tree-file .aui-tree-collapsed .aui-tree-icon{background:transparent url(");
      out.print( themeImagesPath );
      out.write("/aui/folder_closed.png) no-repeat 50% 50%;}.aui-tree-collapsed .aui-tree-node-hidden-hitarea,.aui-tree-expanded .aui-tree-node-hidden-hitarea{background-image:none;}.aui-tree-view-content .aui-tree-node-io-loading .aui-tree-icon{background:url(");
      out.print( themeImagesPath );
      out.write("/aui/loading_indicator.gif) no-repeat 50% 50%!important;}.aui-tree-node-checkbox-container{float:left;background:transparent url(");
      out.print( themeImagesPath );
      out.write("/aui/checks.png) no-repeat 0 50%;height:18px;width:16px;}.aui-tree-node-checked .aui-tree-node-checkbox-container{background-position:-18px 50%;}.aui-tree-node-child-unchecked .aui-tree-node-checkbox-container{background-position:-36px 50%;}.aui-tree-drag-insert-above{border-top:1px dotted #555;}.aui-tree-drag-insert-below{border-bottom:1px dotted #555;}.aui-tree-drag-insert-append .aui-tree-label{background:#eee;border:1px dotted #555;}.aui-tree-drag-helper{min-width:100px;line-height:18px;position:absolute;}.aui-tree-drag-helper-content{background:#fff;border:1px solid #aaa;margin:20px 0 0 60px;opacity:.9;padding:2px 10px 0 6px;filter:alpha(opacity=90);}.aui-tree-drag-helper-content .aui-icon{background-position:-32px -192px;float:left;}.aui-tree-drag-state-append .aui-icon{background-position:0 -192px;}.aui-tree-drag-state-insert-above .aui-icon,.aui-tree-drag-state-insert-below .aui-icon{background-position:-64px -64px;}.aui-tree-drag-helper-label{float:left;font-size:12px;padding-left:2px;}.aui-tree-node-paginator{margin-left:18px;}\n");
      out.write('\n');
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write("td.lfr-center {\n");
      out.write("\ttext-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write("td.lfr-top {\n");
      out.write("\tvertical-align: top;\n");
      out.write("}\n");
      out.write("\n");
      out.write("td.lfr-middle {\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".definition-of-terms {\n");
      out.write("\tmargin-bottom: 2em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".definition-of-terms dd {\n");
      out.write("\tmargin-left: 20em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".definition-of-terms dl {\n");
      out.write("\tmargin-left: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".definition-of-terms dt {\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-grid {\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-component, .lfr-component ul, .lfr-component li, .lfr-component dl, .lfr-component dt, .lfr-component dd {\n");
      out.write("\tmargin: 0;\n");
      out.write("\tpadding: 0;\n");
      out.write("\tlist-style: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-component li img, img.icon {\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#layout-grid.dragging .lfr-portlet-column.empty {\n");
      out.write("\tpadding: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-js-required {\n");
      out.write("\tclip: rect(0 0 0 0);\n");
      out.write("\tposition: absolute;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .lfr-js-required {\n");
      out.write("\tclip: auto;\n");
      out.write("\tposition: static;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".popup-alert-notice, .popup-alert-warning {\n");
      out.write("\tbackground: #ffc url() no-repeat 5px 50%;\n");
      out.write("\tborder-bottom: 1px solid;\n");
      out.write("\tfont-size: 1.1em;\n");
      out.write("\tleft: 0;\n");
      out.write("\tpadding: 10px;\n");
      out.write("\tpadding-left: 25px;\n");
      out.write("\tposition: fixed;\n");
      out.write("\ttop: 0;\n");
      out.write("\twidth: 100%;\n");
      out.write("\tz-index: 10000;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".popup-alert-notice {\n");
      out.write("\tbackground-color: #ffc;\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/messages/alert.png);\n");
      out.write("\tborder-bottom-color: #fc0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".popup-alert-warning {\n");
      out.write("\tbackground-color: #fcc;\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/messages/error.png);\n");
      out.write("\tborder-bottom-color: #f00;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .popup-alert-notice, .ie6 .popup-alert-warning {\n");
      out.write("\tbottom: auto;\n");
      out.write("\tleft: expression( ( LFR_IGNORE_ME2 = document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft ) + 'px' );\n");
      out.write("\tposition: absolute;\n");
      out.write("\tright: auto;\n");
      out.write("\ttop: expression( ( LFR_IGNORE_ME = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop ) + 'px' );\n");
      out.write("}\n");
      out.write("\n");
      out.write(".popup-alert-notice .countdown-timer {\n");
      out.write("\tfont-size: 1.1em;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".popup-alert-notice input, .popup-alert-warning input {\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".portal-add-content-search {\n");
      out.write("\tmargin-bottom: 8px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portal-add-content .lfr-portlet-used {\n");
      out.write("\tcolor: #ccc;\n");
      out.write("\tcursor: default;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portal-add-content .portlet-msg-info {\n");
      out.write("\tcolor: #333;\n");
      out.write("\tmargin-bottom: 0;\n");
      out.write("\tmargin-top: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portal-add-content .lfr-portlet-used a {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-add-content {\n");
      out.write("\tmargin-bottom: 0.5em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-add-content.collapsed {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-add-content.expanded {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-add-content h2 {\n");
      out.write("\tcursor: pointer;\n");
      out.write("\tfont-size: 1.1em;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tmargin: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-add-content.collapsed h2, .lfr-add-content .lfr-add-content.collapsed h2 {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/arrows/01_plus.png) no-repeat 100% 50%;\n");
      out.write("\tborder: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-add-content.expanded h2, .lfr-add-content .lfr-add-content.expanded h2 {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/arrows/01_minus.png) no-repeat 100% 50%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-add-content h2 span {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/add_content/portlet_category.png) no-repeat 0 50%;\n");
      out.write("\tpadding-left: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-install-more {\n");
      out.write("\tborder-top: 1px solid #ccc;\n");
      out.write("\tmargin: 10px 0 0;\n");
      out.write("\tpadding-top: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-install-more a {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/install_more.png) no-repeat 0 50%;\n");
      out.write("\tdisplay: block;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tpadding-left: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-content-category {\n");
      out.write("\tpadding-left: 10px;\n");
      out.write("\tpadding-top: 3px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-content-category.hidden {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-content-category.visible {\n");
      out.write("\tborder-bottom: 1px solid #ddd;\n");
      out.write("\tborder-top: 1px solid #ddd;\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .lfr-content-category.visible {\n");
      out.write("\theight: 1%;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-flyout ul {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js li.lfr-flyout {\n");
      out.write("\tdisplay: block;\n");
      out.write("\tposition: relative;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .lfr-flyout-has-children {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/arrows/04_left.png) no-repeat 5px 50%;\n");
      out.write("\tpadding-left: 12px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .lfr-flyout-has-children.send-right {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/arrows/04_right.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .lfr-flyout-has-children ul {\n");
      out.write("\tmin-width: 150px;\n");
      out.write("\tposition: absolute;\n");
      out.write("\tright: 100%;\n");
      out.write("\ttop: -1px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .lfr-flyout-has-children.send-right ul {\n");
      out.write("\tleft: 100%;\n");
      out.write("\tright: auto;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-panel.lfr-extended, .lfr-panel-container, .lfr-floating-container {\n");
      out.write("\tborder: 1px solid;\n");
      out.write("\tborder-color: #DEDEDE #BFBFBF #BFBFBF #DEDEDE;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-container, .lfr-floating-container {\n");
      out.write("\tbackground: #fff;\n");
      out.write("\tclear: both;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-floating-container {\n");
      out.write("\tposition: relative;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-container .lfr-panel {\n");
      out.write("\tborder: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel .lfr-panel-titlebar {\n");
      out.write("\tmargin-bottom: 0.5em;\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel.lfr-collapsible .lfr-panel-titlebar {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/arrows/05_down.png) no-repeat 2px 50%;\n");
      out.write("\tcursor: pointer;\n");
      out.write("\tpadding-left: 15px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-titlebar .lfr-panel-title {\n");
      out.write("\tfloat: left;\n");
      out.write("\tfont-size: 1.2em;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tmargin: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-titlebar .lfr-panel-button {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/application/panel_header_toggler_close.png) no-repeat 0 0;\n");
      out.write("\tdisplay: none;\n");
      out.write("\tfloat: right;\n");
      out.write("\theight: 22px;\n");
      out.write("\twidth: 19px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-extended.lfr-collapsible .lfr-panel-button {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel.lfr-collapsed .lfr-panel-titlebar .lfr-panel-button {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/application/panel_header_toggler_open.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-titlebar .lfr-panel-button:hover {\n");
      out.write("\tbackground-position: 0 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel.lfr-collapsed .lfr-panel-titlebar {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/arrows/05_right.png);\n");
      out.write("\tmargin-bottom: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel.lfr-extended .lfr-panel-titlebar {\n");
      out.write("\tbackground: #d6d6d6 url(");
      out.print( themeImagesPath );
      out.write("/application/panel_header.png) repeat-x 0 0;\n");
      out.write("\tborder-bottom: 1px solid #cdcdcd;\n");
      out.write("\tline-height: 1.6;\n");
      out.write("\tpadding: 2px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .lfr-extended.lfr-collapsible .lfr-panel-titlebar {\n");
      out.write("\tzoom: 1;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-extended.lfr-collapsed .lfr-panel-titlebar {\n");
      out.write("\tborder-bottom: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-container .lfr-extended.lfr-collapsible .lfr-panel-titlebar {\n");
      out.write("\tborder-top: 1px solid #cecece;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel.lfr-collapsed .lfr-panel-content {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .lfr-floating-container {\n");
      out.write("\tposition: absolute;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .lfr-floating-trigger {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/arrows/05_down.png);\n");
      out.write("\tbackground-position: 100% 50%;\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("\tpadding: 3px;\n");
      out.write("\tpadding-right: 15px;\n");
      out.write("\ttext-decoration: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .lfr-trigger-selected {\n");
      out.write("\tbackground-color: #069;\n");
      out.write("\tcolor: #fff;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-floating-container .col {\n");
      out.write("\tfloat: left;\n");
      out.write("\tmargin-right: 10px;\n");
      out.write("\twidth: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-floating-container .lfr-form-row {\n");
      out.write("\tborder: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel .lfr-panel-content .undo-queue {\n");
      out.write("\tborder-left: none;\n");
      out.write("\tborder-right: none;\n");
      out.write("\tborder-top: none;\n");
      out.write("\tmargin: -0.5em 0 10px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-panel-content .aui-paginator-container .aui-paginator-page-container,\n");
      out.write(".lfr-panel-content .aui-paginator-container .lfr-paginator-next,\n");
      out.write(".lfr-panel-content .aui-paginator-container .lfr-paginator-prev {\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-content .aui-paginator-container .lfr-paginator-next,\n");
      out.write(".lfr-panel-content .aui-paginator-container .lfr-paginator-prev {\n");
      out.write("\tpadding: 8px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-content .aui-paginator-container {\n");
      out.write("\tbackground: #cfd2d5;\n");
      out.write("\tborder-bottom: 1px solid #dcdee0;\n");
      out.write("\tborder-top: 1px solid #dcdee0;\n");
      out.write("\ttext-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-content .aui-paginator-page-container .aui-paginator-page-link {\n");
      out.write("\tbackground: none;\n");
      out.write("\tborder: 1px solid #CFD2D5;\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("\tfloat: none;\n");
      out.write("\tpadding: 5px 10px;\n");
      out.write("\ttext-align: center;\n");
      out.write("\twidth: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-content .aui-paginator-page-container .aui-paginator-page-link.aui-paginator-current-page:hover {\n");
      out.write("\tbackground: #5094d7 url(");
      out.print( themeImagesPath );
      out.write("/application/current_page_bg.png) repeat-x 0 0;\n");
      out.write("\tborder-color: #31659c #396ea8 #4c8ccb;\n");
      out.write("\tcolor: #fff;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-content .aui-paginator-page-container .aui-paginator-page-link.aui-paginator-current-page {\n");
      out.write("\tbackground: #99a7b3 url(");
      out.print( themeImagesPath );
      out.write("/application/current_page_hover_bg.png) repeat-x 0 0;\n");
      out.write("\tborder-color: #6b7785 #7c8994 #919fa9;\n");
      out.write("\tcolor: #fff;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-content .aui-paginator-container {\n");
      out.write("\toverflow: hidden;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-position-helper {\n");
      out.write("\tposition: absolute;\n");
      out.write("\tz-index: 1000;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-form-row {\n");
      out.write("\tborder-bottom: 1px solid #CCC;\n");
      out.write("\tmargin-bottom: 10px;\n");
      out.write("\tmargin-top: 10px;\n");
      out.write("\toverflow: hidden;\n");
      out.write("\tpadding: 5px;\n");
      out.write("\tpadding-top: 1px;\n");
      out.write("\tposition: relative;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .lfr-form-row {\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-form-row:hover {\n");
      out.write("\tbackground-color: #DFFCCB;\n");
      out.write("\tborder: solid #B2FF3A;\n");
      out.write("\tborder-width: 1px 0;\n");
      out.write("\tpadding-top: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-form-row .aui-field {\n");
      out.write("\tclear: none;\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-form-row-inline .aui-field {\n");
      out.write("\tmargin-right: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-form-row legend .field-label{\n");
      out.write("\tfloat:left;\n");
      out.write("\tmargin-right: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-form-row .handle-sort-vertical {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/application/handle_sort_vertical.png) no-repeat 0 50%;\n");
      out.write("\tcursor: move;\n");
      out.write("\tpadding-left: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-form-row fieldset {\n");
      out.write("\tborder: none;\n");
      out.write("\tmargin: 0;\n");
      out.write("\tpadding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-autorow-controls.aui-toolbar {\n");
      out.write("\tpadding: 1px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-autorow-controls {\n");
      out.write("\tbottom: 5px;\n");
      out.write("\tposition: absolute;\n");
      out.write("\tright: 5px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".aui-undomanager .lfr-undo-queue {\n");
      out.write("\tmargin: 10px auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-action-undo {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-action-clear {\n");
      out.write("\tfloat: right;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-undomanager .lfr-queue-empty, .lfr-queue-single .lfr-action-clear {\n");
      out.write("\tdisplay: none;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-panel-page .portal-add-content {\n");
      out.write("\tpadding: 0;\n");
      out.write("\tpadding-left: 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-page .panel-page-content {\n");
      out.write("\tborder-left: 1px solid #ccc;\n");
      out.write("\tpadding-left: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-page .lfr-add-content h2 {\n");
      out.write("\tborder: 1px solid #ccc;\n");
      out.write("\tborder-right: none;\n");
      out.write("\tpadding: 1px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-page .lfr-add-content h2 span {\n");
      out.write("\tbackground: #efefef;\n");
      out.write("\tdisplay: block;\n");
      out.write("\tpadding: 2px;\n");
      out.write("\tpadding-left: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-page .lfr-add-content .lfr-content-category h2 {\n");
      out.write("\tborder: none;\n");
      out.write("\tborder-bottom: 1px solid #ccc;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-page .lfr-add-content .lfr-content-category h2 span {\n");
      out.write("\tbackground: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-panel-page.panel-page-frontpage .panel-page-content h2 {\n");
      out.write("\tmargin-top: 0;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-template {\n");
      out.write("\tdisplay: none;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-layout-template {\n");
      out.write("\tmargin: 0 0 1em;\n");
      out.write("\ttext-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-layout-template-column-content {\n");
      out.write("\tpadding: 0 2em 0 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-page-layouts-content {\n");
      out.write("\tpadding-left: 1em;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-portlet-item {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/add_content/portlet_item.png) no-repeat 0 50%;\n");
      out.write("\tborder: 1px solid #fff;\n");
      out.write("\tcursor: move;\n");
      out.write("\tfont-size: 1.1em;\n");
      out.write("\tmargin-bottom: 3px;\n");
      out.write("\tpadding: 0 5px 0 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-item.lfr-instanceable {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/add_content/portlet_item_instanceable.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-item:hover, .lfr-portlet-item.over {\n");
      out.write("\tbackground-color: #ffc;\n");
      out.write("\tborder-color: #fc0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .lfr-portlet-item {\n");
      out.write("\theight: 1%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-item p {\n");
      out.write("\tfont-size: 1em;\n");
      out.write("\tmargin: 0;\n");
      out.write("\tpadding-right: 30px;\n");
      out.write("\tposition: relative;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-item p a {\n");
      out.write("\tcursor: pointer;\n");
      out.write("\tfont-size: 0.9em;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tposition: absolute;\n");
      out.write("\tright: 0;\n");
      out.write("\ttop: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .lfr-portlet-item p a {\n");
      out.write("\ttop: -2px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .lfr-portlet-item p a {\n");
      out.write("\tright: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#layout_configuration_content {\n");
      out.write("\twidth: 95%;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write("#portlet-set-properties .aui-form {\n");
      out.write("\tclear: both;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties fieldset {\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties fieldset fieldset {\n");
      out.write("\tmargin-top: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties fieldset fieldset legend {\n");
      out.write("\tfont-size: 1.1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .aui-form .text-input {\n");
      out.write("\tmargin-right: 5px;\n");
      out.write("\twidth: 80px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .aui-form fieldset.col {\n");
      out.write("\twidth: 29%;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .common {\n");
      out.write("\twidth: 27%;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .extra {\n");
      out.write("\twidth: 20%;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .use-for-all-column .aui-field-row .aui-field-label {\n");
      out.write("\twidth: 50px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .aui-form #custom-css {\n");
      out.write("\theight: 300px;\n");
      out.write("\twidth: 400px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .form-hint {\n");
      out.write("\tfloat: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .lfr-bg-image-properties {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties #border-note {\n");
      out.write("\tmargin-top: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .aui-form {\n");
      out.write("\tclear: both;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .aui-legend {\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write("#portlet-set-properties .aui-colorpicker-trigger {\n");
      out.write("\tmargin-top: 0;\n");
      out.write("\tvertical-align: top;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-portlet-toolbar {\n");
      out.write("\tbackground: #F6F8FB;\n");
      out.write("\tmargin-bottom: 5px;\n");
      out.write("\toverflow: hidden;\n");
      out.write("\tpadding: 2px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .lfr-portlet-toolbar {\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-toolbar .lfr-toolbar-button a {\n");
      out.write("\tbackground: url() no-repeat 5px 50%;\n");
      out.write("\tborder: 1px solid #F6F8FB;\n");
      out.write("\tcolor: #9EA8AD;\n");
      out.write("\tdisplay: block;\n");
      out.write("\tfloat: left;\n");
      out.write("\tfont-size: 12px;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tpadding: 4px 14px 4px 29px;\n");
      out.write("\ttext-decoration: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-toolbar .lfr-toolbar-button a:hover {\n");
      out.write("\tbackground-color: #DFF4FF;\n");
      out.write("\tborder: 1px solid #A7CEDF;\n");
      out.write("\tcolor: #346799;\n");
      out.write("\tpadding: 4px 14px 4px 29px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .lfr-portlet-toolbar .lfr-toolbar-button a {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-toolbar .lfr-toolbar-button.current a {\n");
      out.write("\tbackground-color: #CFD5D7;\n");
      out.write("\tborder: 1px solid #CFD5D7;\n");
      out.write("\tcolor: #000;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-toolbar .lfr-toolbar-button.current a:hover {\n");
      out.write("\tbackground-color: #D6DCDD;\n");
      out.write("\tborder-color: #B7C0C2;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-toolbar .lfr-toolbar-button.add-button a {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/common/add.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-toolbar .lfr-toolbar-button.custom-attributes-button a {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/common/attributes.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-toolbar .lfr-toolbar-button.view-button a {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/common/view_users.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-portlet-toolbar .lfr-toolbar-button.export-button a {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/common/export.png);\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-toolbar {\n");
      out.write("\tbackground-color: #e5e4e8;\n");
      out.write("\tpadding-top: 3px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-toolbar:after {\n");
      out.write("\tclear: both;\n");
      out.write("\tcontent: \".\";\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 0;\n");
      out.write("\tvisibility: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .lfr-toolbar {\n");
      out.write("\theight: 1%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .lfr-toolbar {\n");
      out.write("\twidth: 750px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-toolbar .lfr-button, .lfr-emoticon-container .lfr-button {\n");
      out.write("\tborder: 1px solid #e5e4e8;\n");
      out.write("\tcursor: pointer;\n");
      out.write("\tmargin: 0 2px;\n");
      out.write("\tpadding: 2px 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-toolbar .lfr-button img {\n");
      out.write("\tvertical-align: text-top;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-toolbar .lfr-button:focus {\n");
      out.write("\tborder: 1px solid #000;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-toolbar .lfr-button:hover {\n");
      out.write("\tborder-bottom-color: #777;\n");
      out.write("\tborder-right-color: #777;\n");
      out.write("\tborder: 1px solid #ccc;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-toolbar .lfr-separator {\n");
      out.write("\tborder-left: 1px solid #fff;\n");
      out.write("\tborder-right: 1px solid #ccc;\n");
      out.write("\tfont-size: 12px;\n");
      out.write("\theight: 25px;\n");
      out.write("\tmargin: 0 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-toolbar select, .lfr-toolbar .lfr-button, .lfr-toolbar .lfr-separator, .lfr-emoticon-container .lfr-button {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-toolbar select {\n");
      out.write("\tmargin: 0 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .lfr-toolbar select {\n");
      out.write("\tmargin-top: 3px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-emoticon-container {\n");
      out.write("\tbackground: #e5e4e8;\n");
      out.write("\tborder: 1px solid #ccc;\n");
      out.write("\tpadding-top: 5px;\n");
      out.write("\toverflow: hidden;\n");
      out.write("\twidth: 180px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-emoticon-container .lfr-button {\n");
      out.write("\tmargin: 0;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-tree {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tree .expand-image {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tree a {\n");
      out.write("\ttext-decoration: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tree li {\n");
      out.write("\tmargin-bottom: 2px;\n");
      out.write("\tpadding-left: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tree li ul {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tree li ul li, .lfr-tree li.tree-item {\n");
      out.write("\tpadding-left: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tree li.tree-item {\n");
      out.write("\tpadding-left: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tree li.tree-item li {\n");
      out.write("\tpadding-left: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tree li.tree-item ul {\n");
      out.write("\tmargin-left: 0;\n");
      out.write("\tmargin-top: 5px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-upload-container {\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("\twidth: 450px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .upload-target a {\n");
      out.write("\tfloat: left;\n");
      out.write("\tmargin-right: 15px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container a.browse-button {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/dock/add_content.png);\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container a.upload-button {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/common/top.png);\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container a.clear-uploads {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/portlet/refresh.png);\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container a.cancel-uploads {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/common/close.png);\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("\tfloat: right;\n");
      out.write("\tmargin-right: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .upload-file {\n");
      out.write("\tbackground: #f0faf0 url(");
      out.print( themeImagesPath );
      out.write("/file_system/small/jpg.png) no-repeat 5px 50%;\n");
      out.write("\tborder-bottom: 1px solid #ccc;\n");
      out.write("\tdisplay: block;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tmargin-bottom: 1px;\n");
      out.write("\tpadding: 5px;\n");
      out.write("\tpadding-left: 25px;\n");
      out.write("\tposition: relative;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .upload-list-info {\n");
      out.write("\tmargin: 5px 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .upload-list-info h4 {\n");
      out.write("\tfont-size: 1.3em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .cancel-button {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/common/delete.png);\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("\tposition: absolute;\n");
      out.write("\tright: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .upload-complete {\n");
      out.write("\tbackground-color: #E8EEF7;\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/dock/my_place_current.png);\n");
      out.write("\tfont-weight: normal;\n");
      out.write("\topacity: 0.6;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .upload-error {\n");
      out.write("\tbackground-color: #FDD;\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/messages/error.png);\n");
      out.write("\tfont-weight: normal;\n");
      out.write("\topacity: 0.6;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .upload-error .error-message {\n");
      out.write("\tposition: absolute;\n");
      out.write("\tright: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .upload-complete .cancel-button {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .file-uploading {\n");
      out.write("\tbackground-color: #ffc;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .file-uploading .cancel-button {\n");
      out.write("\ttop: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .progress-bar {\n");
      out.write("\tbackground: #fff;\n");
      out.write("\tborder: 1px solid #83a8d9;\n");
      out.write("\tdisplay: none;\n");
      out.write("\theight: 15px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .progress {\n");
      out.write("\tbackground: #8db2e3;\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 15px;\n");
      out.write("\twidth: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-upload-container .file-uploading .progress-bar {\n");
      out.write("\tdisplay: block;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".page-extra-settings .lfr-panel-titlebar .lfr-panel-title {\n");
      out.write("\tfont-size: 1em;\n");
      out.write("\tpadding: 0.5em 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".page-extra-settings .lfr-extended .lfr-panel-titlebar {\n");
      out.write("\tline-height: 1;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".page-extra-settings .lfr-extended .lfr-panel-titlebar {\n");
      out.write("\tline-height: 1;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".page-extra-settings .lfr-extended .lfr-panel-content {\n");
      out.write("\tpadding: 5px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".popup-alert-notice .notice-date {\n");
      out.write("\tmargin: 0 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".popup-alert-notice .current-user-language {\n");
      out.write("\tborder: 1px solid #ccc;\n");
      out.write("\tborder-bottom: none;\n");
      out.write("\tborder-top: none;\n");
      out.write("\tdisplay: inline;\n");
      out.write("\tmargin: 0 10px;\n");
      out.write("\tpadding: 0 15px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-asset-category-container {\n");
      out.write("\theight: 260px;\n");
      out.write("\tmargin-bottom: 0.5em;\n");
      out.write("\toverflow: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-container fieldset {\n");
      out.write("\tmargin: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-container legend {\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tmargin-left: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-container label {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-container label input {\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-container .lfr-asset-category-message {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-search-container {\n");
      out.write("\tbackground-color: #D3DADD;\n");
      out.write("\tborder-bottom: 1px solid #AEB8BC;\n");
      out.write("\tmargin: 3px 0;\n");
      out.write("\tpadding: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-search-input {\n");
      out.write("\twidth: 250px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-container .no-matches {\n");
      out.write("\tborder-bottom: none;\n");
      out.write("\tborder-left: none;\n");
      out.write("\tborder-right: none;\n");
      out.write("\tcolor: #999;\n");
      out.write("\tmargin-bottom: 0;\n");
      out.write("\tmargin-left: 5px;\n");
      out.write("\tmargin-top: 0;\n");
      out.write("\tpadding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-vocabulary-container.no-matches legend {\n");
      out.write("\tpadding-left: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write(".lfr-asset-vocabulary-container .lfr-asset-category-message, .lfr-asset-vocabulary-container.no-matches .lfr-asset-category-list {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-vocabulary-container.no-matches .lfr-asset-category-message {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-list-container {\n");
      out.write("\tcursor: pointer;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-list ul {\n");
      out.write("\tlist-style: none;\n");
      out.write("\tmargin-top: 4px;\n");
      out.write("\tpadding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-list li {\n");
      out.write("\tlist-style-type: none;\n");
      out.write("\tmargin: 0;\n");
      out.write("\tpadding: 0 0 3px 16px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-list a.selected {\n");
      out.write("\tbackground-color: #EEE;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-category-list span {\n");
      out.write("\tpadding-left: 3px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-categories-selector-list .aui-tree-node-paginator {\n");
      out.write("\tmargin-left: 29px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-asset-column-content {\n");
      out.write("\tmargin-right: 1em;\n");
      out.write("\tpadding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-actions-content {\n");
      out.write("\tbackground-color: #D7F1FF;\n");
      out.write("\toverflow: visible;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-actions .lfr-asset-summary {\n");
      out.write("\tmargin-bottom: 2em;\n");
      out.write("\toverflow: hidden;\n");
      out.write("\ttext-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-actions .lfr-asset-avatar img {\n");
      out.write("\tmargin: 0 auto;\n");
      out.write("\tpadding-right: 2em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-actions .lfr-asset-column-actions-content {\n");
      out.write("\tborder: 1px solid #88C5D9;\n");
      out.write("\tpadding: 0.7em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-categories .lfr-asset-category {\n");
      out.write("\tcolor: #555;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-categories {\n");
      out.write("\tcolor: #7D7D7D;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-description {\n");
      out.write("\tcolor: #444;\n");
      out.write("\tfont-style: italic;\n");
      out.write("\tmargin: 0.5em auto 2em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-field, .lfr-asset-column-actions .lfr-asset-field {\n");
      out.write("\tclear: left;\n");
      out.write("\tmargin: 1em auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-icon {\n");
      out.write("\tborder-right: 1px solid #999;\n");
      out.write("\tcolor: #999;\n");
      out.write("\tfloat: left;\n");
      out.write("\tmargin-right: 10px;\n");
      out.write("\tpadding-left: 25px;\n");
      out.write("\tpadding-right: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-icon.last {\n");
      out.write("\tborder-width: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-author {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/portlet/edit_guest.png) no-repeat 0 50%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-date {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/date.png) no-repeat 0 50%;\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-subfolders {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/folder.png) no-repeat 0 50%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-items {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/page.png) no-repeat 0 50%;\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-downloads {\n");
      out.write("\tpadding-left: 0px;\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-metadata {\n");
      out.write("\tclear: both;\n");
      out.write("\tmargin-bottom: 1.5em;\n");
      out.write("\tpadding-bottom: 1.5em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-ratings {\n");
      out.write("\tmargin: 2em 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-tags .tag {\n");
      out.write("\tcolor: #555;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-asset-tags {\n");
      out.write("\tcolor: #7D7D7D;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .taglib-custom-attributes-list label, .lfr-asset-column-details .lfr-asset-field label {\n");
      out.write("\tdisplay: block;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .taglib-custom-attributes-list {\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-panels {\n");
      out.write("\tclear: both;\n");
      out.write("\tpadding-top: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-panels .lfr-panel, .lfr-asset-column-details .lfr-panel {\n");
      out.write("\tclear: both;\n");
      out.write("\tmargin-bottom: 1.5em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-panels .lfr-panel.lfr-extended, .lfr-asset-column-details .lfr-panel.lfr-extended {\n");
      out.write("\tmargin-bottom: 0;\n");
      out.write("\tborder: 1px solid #CCC;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-asset-column-details .lfr-panel-container, .lfr-asset-panels .lfr-panel-container {\n");
      out.write("\tborder-width:0;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-tags-selector-content {\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-input {\n");
      out.write("\twidth: 300px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup {\n");
      out.write("\twidth: 250px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tags-selector-list {\n");
      out.write("\tborder: solid #ccc;\n");
      out.write("\tborder-width: 0 1px 1px;\n");
      out.write("\theight: 265px;\n");
      out.write("\tmargin-bottom: 0.5em;\n");
      out.write("\toverflow: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup fieldset {\n");
      out.write("\tborder-width: 0;\n");
      out.write("\tmargin: 0;\n");
      out.write("\tpadding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup label {\n");
      out.write("\tborder-bottom: 1px solid #ccc;\n");
      out.write("\tcursor: pointer;\n");
      out.write("\tdisplay: block;\n");
      out.write("\tpadding: 2px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup ul {\n");
      out.write("\tmargin: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup li li {\n");
      out.write("\tlist-style: none;\n");
      out.write("\tmargin-left: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup label input {\n");
      out.write("\tmargin-right: 5px;\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup .lfr-tag-message {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup .aui-textfield-content {\n");
      out.write("\tbackground-color: #D3DADD;\n");
      out.write("\tborder-bottom: 1px solid #AEB8BC;\n");
      out.write("\tpadding: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup .aui-field-input-text {\n");
      out.write("\twidth: 99%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup .no-matches {\n");
      out.write("\tcolor: #999;\n");
      out.write("\tpadding: 3px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-tag-selector-popup .no-matches .lfr-tag-message {\n");
      out.write("\tdisplay: block;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".breadcrumbs {\n");
      out.write("\tmargin-bottom: 10px;\n");
      out.write("\tmargin-left: 0px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-button {\n");
      out.write("\tbackground: url() no-repeat 0 50%;\n");
      out.write("\tpadding: 2px 0 2px 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .controls-hidden .lfr-meta-actions, .js .controls-hidden .portlet-borderless-bar, .js .controls-hidden .portlet-topper-toolbar, .js .controls-hidden .lfr-configurator-visibility {\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .page-maximized .portlet-topper-toolbar {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".configuration-frame {\n");
      out.write("\tborder-width: 0;\n");
      out.write("\toverflow: auto;\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".float-container:after {\n");
      out.write("\tclear: both;\n");
      out.write("\tcontent: \".\";\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 0;\n");
      out.write("\tvisibility: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .float-container {\n");
      out.write("\theight: 1%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-meta-actions {\n");
      out.write("\tpadding-top: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-table {\n");
      out.write("\tborder-collapse: collapse;\n");
      out.write("\tclear: both;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-table tr td, .lfr-table tr th {\n");
      out.write("\tpadding: 0 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-table tr th {\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-table tr td:first-child, .lfr-table tr td.first-child, .lfr-table tr th:first-child, .lfr-table tr th.first-child {\n");
      out.write("\tpadding-left: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-table tr td:last-child, .lfr-table tr td.last-child, .lfr-table tr th:last-child, .lfr-table tr th.last-child {\n");
      out.write("\tpadding-right: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-control-links a {\n");
      out.write("\tmargin-right: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-toolbar {\n");
      out.write("\tmargin-bottom: 1.5em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-checkbox-preselected {\n");
      out.write("\topacity: 0.5;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .lfr-checkbox-preselected {\n");
      out.write("\tfilter: alpha(opacity=50);\n");
      out.write("\tzoom: 1;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-configurator-visibility {\n");
      out.write("\topacity: 0.5;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .lfr-configurator-visibility, .ie .lfr-configurator-visibility .portlet-topper {\n");
      out.write("\tfilter: alpha(opacity=50);\n");
      out.write("\tzoom: 1;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-input-time {\n");
      out.write("\tfloat: left;\n");
      out.write("\tclear: right;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".gecko .lfr-input-time, .ie .lfr-input-time {\n");
      out.write("\tpadding-left: 2px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".aui-field-wrapper {\n");
      out.write("\tclear: both;\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-form-button.aui-input-disabled input, .aui-form-button.aui-input-disabled button {\n");
      out.write("\tbackground: #F5F5F5 url(../images/forms/button.png) repeat-x 0 0;\n");
      out.write("\tcolor: #ccc;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-form-button.aui-input-disabled input:hover, .aui-form-button.aui-input-disabled button:hover {\n");
      out.write("\tbackground: #F5F5F5 url(../images/forms/button.png) repeat-x 0 0;\n");
      out.write("\tborder-color: #DEDEDE #BFBFBF #BFBFBF #DEDEDE;\n");
      out.write("\tcolor: #ccc;\n");
      out.write("\tcursor: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".aui-form-validator-error-container {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/messages/error.png) no-repeat scroll 6px 50% #FDD;\n");
      out.write("\tborder: 1px solid #F00;\n");
      out.write("\tdisplay: block;\n");
      out.write("\tfont-weight: normal;\n");
      out.write("\tmargin: 3px 0;\n");
      out.write("\tpadding: 6px;\n");
      out.write("\tpadding-left: 30px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-input-text, .aui-form .aui-fieldset .aui-field.lfr-input-text-container input {\n");
      out.write("\twidth: ");
      out.print( ModelHintsConstants.TEXT_DISPLAY_WIDTH );
      out.write("px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-input-text.flexible, .lfr-input-text-container.flexible input {\n");
      out.write("\twidth: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-textarea, .aui-form .aui-fieldset .aui-field.lfr-textarea-container textarea {\n");
      out.write("\theight: ");
      out.print( ModelHintsConstants.TEXTAREA_DISPLAY_HEIGHT );
      out.write("px;\n");
      out.write("\twidth: ");
      out.print( ModelHintsConstants.TEXTAREA_DISPLAY_WIDTH );
      out.write("px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("fieldset, .aui-form fieldset {\n");
      out.write("\tmargin-bottom: 2em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("fieldset:last-child, .aui-form fieldset:last-child {\n");
      out.write("\tmargin-bottom: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-display-terms-search {\n");
      out.write("\tdisplay: block;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".separator {\n");
      out.write("\tmargin: 15px auto;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".portlet-boundary .top-links-container {\n");
      out.write("\tclear: both;\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-boundary .top-links {\n");
      out.write("\tfloat: right;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-boundary .top-links .top-link {\n");
      out.write("\tborder-right: 1px solid #bbb;\n");
      out.write("\tmargin-right: 0.5em;\n");
      out.write("\tpadding-right: 0.5em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-boundary .top-links .top-link.last {\n");
      out.write("\tborder-width: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-boundary .top-links .top-links-navigation {\n");
      out.write("\tfloat: left;\n");
      out.write("}");
      out.write('\n');
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-asset-categories-summary .asset-category {\n");
      out.write("\tmargin-bottom: 3px;\n");
      out.write("\tmargin-right: 3px;\n");
      out.write("\tpadding-right: 5px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-calendar {\n");
      out.write("\twidth: 190px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar table {\n");
      out.write("\tborder: 1px solid #999;\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar tr th, .taglib-calendar tr td {\n");
      out.write("\theight: 25px;\n");
      out.write("\ttext-align: center;\n");
      out.write("\tborder-bottom: 1px solid #999;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar tr.portlet-section-header th, .taglib-calendar tr td {\n");
      out.write("\tborder: 1px solid #999;\n");
      out.write("\tborder-bottom: none;\n");
      out.write("\tborder-top: none;\n");
      out.write("\tpadding: 0;\n");
      out.write("\twidth: 26px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar tr.portlet-section-header th.first, .taglib-calendar tr td.first {\n");
      out.write("\tborder-left: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar tr.portlet-section-header th.last, .taglib-calendar tr td.last {\n");
      out.write("\tborder-right: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar tr td a {\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 15px;\n");
      out.write("\tpadding: 5px 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar a:hover, .taglib-calendar a:focus {\n");
      out.write("\tbackground-color: #ccc;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar .calendar-inactive {\n");
      out.write("\tcolor: #999;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar .calendar-current-day {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar .calendar-current-day a {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar .has-events a span {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/calendar/event_indicator.png) no-repeat 50% 95%;\n");
      out.write("\tpadding-bottom: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-calendar .has-events.calendar-current-day a span {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/calendar/event_indicator_current.png);\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-captcha {\n");
      out.write("\tmargin: 1em 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-captcha .captcha {\n");
      out.write("\tdisplay: block;\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-diff-addedline, #taglib-diff-results ins {\n");
      out.write("\tbackground: #E6FFE6;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-context {\n");
      out.write("\tbackground: #EEEEEE;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-deletedline, #taglib-diff-results del {\n");
      out.write("\tbackground: #FFE6E6;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-table {\n");
      out.write("\tborder-collapse: separate;\n");
      out.write("\tborder-spacing: 5pt;\n");
      out.write("\twidth: 100%;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-diff-html div.diff-removed-image {\n");
      out.write("\tbackground: #fdc6c6 url(");
      out.print( themeImagesPath );
      out.write("/diff/minus.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-html div.diff-added-image {\n");
      out.write("\tbackground: #cfc url(");
      out.print( themeImagesPath );
      out.write("/diff/plus.png) no-repeat;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-html div.diff-removed-image, div.diff-added-image {\n");
      out.write("\theight: 300px;\n");
      out.write("\tmargin: 2px;\n");
      out.write("\topacity: 0.55;\n");
      out.write("\tposition: absolute;\n");
      out.write("\twidth: 200px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".gecko .taglib-diff-html div.diff-removed-image, .gecko div.diff-added-image {\n");
      out.write("\t-moz-opacity: 0.55;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .taglib-diff-html div.diff-removed-image, .ie div.diff-added-image {\n");
      out.write("\tfilter: alpha(opacity=55);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-html span.diff-html-added img {\n");
      out.write("\tborder: 2px solid #cfc;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-html span.diff-html-added {\n");
      out.write("\tbackground-color: #cfc;\n");
      out.write("\tfont-size: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-html span.diff-html-changed img {\n");
      out.write("\tborder: 2px dotted #009;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-html span.diff-html-changed {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/diff/underline.png) bottom repeat-x;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-html span.diff-html-removed img {\n");
      out.write("\tborder: 2px solid #fdc6c6;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-diff-html span.diff-html-removed {\n");
      out.write("\tbackground-color: #fdc6c6;\n");
      out.write("\tfont-size: 1em;\n");
      out.write("\ttext-decoration: line-through;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-discussion table.add-comment {\n");
      out.write("\tborder-width: 0;\n");
      out.write("\tborder-collapse: collapse;\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-discussion table.add-comment td {\n");
      out.write("\tpadding: 0\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-discussion table.tree-walker {\n");
      out.write("\tborder-width: 0;\n");
      out.write("\tborder-collapse: collapse;\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-discussion table.tree-walker td {\n");
      out.write("\tpadding: 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-discussion td img {\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-flags {\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-flags span a {\n");
      out.write("\tmargin-right: 2px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-header .header-title {\n");
      out.write("\tmargin: 0.1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-header .header-back-to a {\n");
      out.write("\tfloat: right;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tpadding: 1em 0.2em;\n");
      out.write("\ttext-decoration: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-header {\n");
      out.write("\tborder-bottom: 1px solid #C8C9CA;\n");
      out.write("\tcolor: #555;\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-icon {\n");
      out.write("\ttext-decoration:none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-icon .taglib-text {\n");
      out.write("\ttext-decoration: underline;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-icon .taglib-text:hover {\n");
      out.write("\ttext-decoration: none;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-icon-help img {\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-icon-list li {\n");
      out.write("\tfloat: left;\n");
      out.write("\tmargin-right: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-icon-list:after {\n");
      out.write("\tclear: both;\n");
      out.write("\tcontent: \".\";\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 0;\n");
      out.write("\tvisibility: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .taglib-icon-list {\n");
      out.write("\theight: 1%;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-actions {\n");
      out.write("\tfloat: right;\n");
      out.write("\ttext-align: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions.left {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions ul {\n");
      out.write("\tdisplay: none;\n");
      out.write("\tbottom: 0%;\n");
      out.write("\tposition: absolute;\n");
      out.write("\tright: 100%;\n");
      out.write("\tz-index: 99999;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions.visible ul {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions.left ul {\n");
      out.write("\tright: auto;\n");
      out.write("\tleft: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions .lfr-trigger, .lfr-actions .lfr-trigger strong {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/button_bg.png) no-repeat;\n");
      out.write("\tfont-weight: normal;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions .lfr-trigger {\n");
      out.write("\tbackground-position: 100% -42px;\n");
      out.write("\tcursor: pointer;\n");
      out.write("\tpadding-right: 3px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .lfr-actions {\n");
      out.write("\theight: 15px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions .lfr-trigger strong {\n");
      out.write("\tdisplay: block;\n");
      out.write("\tmin-width: 70px;\n");
      out.write("\tpadding: 2px 0 3px;\n");
      out.write("\ttext-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .lfr-actions .lfr-trigger strong {\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions.visible .lfr-trigger {\n");
      out.write("\tbackground-position: 100% 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions.visible .lfr-trigger strong {\n");
      out.write("\tbackground-position: 0 -21px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions .lfr-trigger strong a {\n");
      out.write("\ttext-decoration: none;\n");
      out.write("\tpadding: 2px 5px 2px 15px;\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions.show-arrow .lfr-trigger strong a {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/arrows/04_left.png) no-repeat 5px 50%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions.left .lfr-trigger strong a {\n");
      out.write("\tbackground-position: 98% 50%;\n");
      out.write("\tpadding: 2px 10px 2px 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-actions.left.show-arrow .lfr-trigger strong a {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/arrows/04_right.png);\n");
      out.write("\tpadding-right: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-menu-list ul {\n");
      out.write("\tbackground: #fff url(");
      out.print( themeImagesPath );
      out.write("/forms/button.png) repeat-x 0 100%;\n");
      out.write("\tborder: 1px solid;\n");
      out.write("\tborder-color: #DEDEDE #BFBFBF #BFBFBF #DEDEDE;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-menu-list li {\n");
      out.write("\tbackground: transparent;\n");
      out.write("\tborder-bottom: 1px solid #DEDEDE;\n");
      out.write("\tpadding: 2px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-menu-list li nobr {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-menu-list li a {\n");
      out.write("\tpadding: 5px 10px 5px 0;\n");
      out.write("\tdisplay: block;\n");
      out.write("\tfont-weight: normal;\n");
      out.write("\ttext-decoration: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-menu-list li a img {\n");
      out.write("\tpadding: 0 3px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".lfr-menu-list li.last {\n");
      out.write("\tborder-bottom: none;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".lfr-language-selector input, .lfr-language-selector textarea {\n");
      out.write("\twidth: 200px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-move-boxes {\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-move-boxes .arrow-button img {\n");
      out.write("\tborder: 0;\n");
      out.write("\theight: 16px;\n");
      out.write("\twidth: 16px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-move-boxes .choice-selector select {\n");
      out.write("\tmin-width: 150px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .taglib-move-boxes .choice-selector select {\n");
      out.write("\twidth: expression(this.currentStyle.getAttribute('minWidth') || 150 + 'px');\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-move-boxes .choice-selector label {\n");
      out.write("\tbackground: #ebf1f9;\n");
      out.write("\tborder-bottom: 1px solid #8db2f3;\n");
      out.write("\tdisplay: block;\n");
      out.write("\tmargin-bottom: 5px;\n");
      out.write("\tpadding: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-move-boxes .choice-selector .aui-field-content {\n");
      out.write("\tmargin-bottom: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-move-boxes .move-arrow-buttons {\n");
      out.write("\tmargin-top: 5em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-move-boxes .move-arrow-buttons .arrow-button {\n");
      out.write("\tdisplay: block;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-input-repeat .end-date-field {\n");
      out.write("\tclear: both;\n");
      out.write("\tpadding-left: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-input-repeat .weekdays {\n");
      out.write("\tclear: both;\n");
      out.write("\tpadding-top: 1em;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-language-option {\n");
      out.write("\tbackground-position: 0 50%;\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("\tpadding-left: 26px;\n");
      out.write("\tmargin: 1px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-language-list-text {\n");
      out.write("\tborder-right: 1px solid #CCC;\n");
      out.write("\tpadding: 0 0.2em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-language-list-text.last {\n");
      out.write("\tborder-width: 0;\n");
      out.write("}");
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write(".taglib-portlet-preview.show-borders {\n");
      out.write("\tborder: 1px solid #828F95;\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("\tpadding: 3px 3px 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-portlet-preview.show-borders .title {\n");
      out.write("\tbackground-color: #D3DADD;\n");
      out.write("\tfont-size: 1.4em;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tpadding: 0.5em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-portlet-preview .preview {\n");
      out.write("\tmargin: 1em;\n");
      out.write("\tmin-height: 90px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-ratings.stars {\n");
      out.write("\toverflow: hidden;\n");
      out.write("\tpadding-top: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-ratings.stars .liferay-rating-score {\n");
      out.write("\tpadding-left: 15px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-ratings .aui-rating-content {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-ratings.stars .liferay-rating-vote, .taglib-ratings.stars .liferay-rating-score {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-ratings.score {\n");
      out.write("\twhite-space: nowrap;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-ratings.thumbs .aui-thumbrating {\n");
      out.write("\theight: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-ratings.thumbs .aui-thumbrating .aui-rating-label-element {\n");
      out.write("\tpadding: 0 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-ratings.thumbs {\n");
      out.write("\tfloat: left;\n");
      out.write("\tmargin: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .taglib-ratings .liferay-rating-vote .aui-field {\n");
      out.write("\tdisplay: none;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-search-iterator {\n");
      out.write("\tborder-collapse: collapse;\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-iterator td {\n");
      out.write("\tpadding: 4px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-iterator td.align-center {\n");
      out.write("\ttext-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-iterator td.align-left {\n");
      out.write("\ttext-align: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-iterator td.align-right {\n");
      out.write("\ttext-align: right;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-iterator td.valign-bottom {\n");
      out.write("\tvertical-align: bottom;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-iterator td.valign-middle {\n");
      out.write("\tvertical-align: middle;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-iterator td.valign-top {\n");
      out.write("\tvertical-align: top;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-iterator-highlighted {\n");
      out.write("\tfont-weight: bold;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-iterator-page-iterator-top {\n");
      out.write("\tpadding-bottom: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-iterator-page-iterator-bottom {\n");
      out.write("\tpadding-top: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator {\n");
      out.write("\tclear: both;\n");
      out.write("\theight: auto;\n");
      out.write("\toverflow: hidden;\n");
      out.write("\twidth: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-results {\n");
      out.write("\tcolor: #555;\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages {\n");
      out.write("\tfloat: right;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-links a, .taglib-page-iterator .search-pages .page-links span {\n");
      out.write("\tbackground: url() no-repeat 0 0;\n");
      out.write("\tborder-left: 1px solid #ccc;\n");
      out.write("\tdisplay: inline-block;\n");
      out.write("\tline-height: 1;\n");
      out.write("\tpadding: 2px 15px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-links span {\n");
      out.write("\tbackground-position: 0 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-links a.first, .taglib-page-iterator .search-pages .page-links span.first {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/arrows/paging_first.png);\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("\tborder-left: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-links a.previous, .taglib-page-iterator .search-pages .page-links span.previous {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/arrows/paging_previous.png);\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-links a.next, .taglib-page-iterator .search-pages .page-links span.next {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/arrows/paging_next.png);\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-links a.last, .taglib-page-iterator .search-pages .page-links span.last {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/arrows/paging_last.png);\n");
      out.write("\tbackground-repeat: no-repeat;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-links a.first, .taglib-page-iterator .search-pages .page-links span.first, .taglib-page-iterator .search-pages .page-links a.previous, .taglib-page-iterator .search-pages .page-links span.previous {\n");
      out.write("\tpadding-right: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-links a.next, .taglib-page-iterator .search-pages .page-links span.next, .taglib-page-iterator .search-pages .page-links a.last, .taglib-page-iterator .search-pages .page-links span.last {\n");
      out.write("\tbackground-position: 100% 0;\n");
      out.write("\tpadding-left: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-links span.first, .taglib-page-iterator .search-pages .page-links span.previous {\n");
      out.write("\tbackground-position: 0 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-links span.next, .taglib-page-iterator .search-pages .page-links span.last {\n");
      out.write("\tbackground-position: 100% 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .delta-selector, .taglib-page-iterator .search-pages .page-selector, .taglib-page-iterator .search-pages .page-links {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-page-iterator .search-pages .page-selector {\n");
      out.write("\tmargin-left: 10px;\n");
      out.write("\tmargin-right: 10px;\n");
      out.write("\tpadding-left: 10px;\n");
      out.write("\tpadding-right: 10px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-search-toggle .inline-control {\n");
      out.write("\tfloat: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-search-toggle .toggle-link {\n");
      out.write("\tclear: both;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-social-activities table {\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-activities td {\n");
      out.write("\tpadding: 5px;\n");
      out.write("\tvertical-align: top;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-activities .day-separator {\n");
      out.write("\tborder-bottom: 1px dotted #CCC;\n");
      out.write("\tmargin-bottom: 0px;\n");
      out.write("\tmargin-top: 10px;\n");
      out.write("\tpadding-bottom: 2px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-activities .portlet-icon {\n");
      out.write("\twidth: 16px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".js .taglib-social-bookmarks.visible {\n");
      out.write("\tbackground: #F0F5F7;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .taglib-social-bookmarks .show-bookmarks {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .taglib-social-bookmarks.visible .show-bookmarks {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .taglib-social-bookmarks ul {\n");
      out.write("\tborder: 1px solid #828F95;\n");
      out.write("\tborder-left: none;\n");
      out.write("\tborder-right: none;\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .taglib-social-bookmarks.visible ul {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks li {\n");
      out.write("\tfloat: left;\n");
      out.write("\tmargin-right: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks ul:after {\n");
      out.write("\tclear: both;\n");
      out.write("\tcontent: \".\";\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 0;\n");
      out.write("\tvisibility: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .taglib-social-bookmarks ul {\n");
      out.write("\theight: 1%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks li a {\n");
      out.write("\tbackground: url() no-repeat 0 50%;\n");
      out.write("\tpadding: 3px 2px 3px 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks a.taglib-social-bookmark-blinklist {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/social_bookmarks/blinklist.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks a.taglib-social-bookmark-delicious {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/social_bookmarks/delicious.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks a.taglib-social-bookmark-digg {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/social_bookmarks/digg.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks a.taglib-social-bookmark-facebook {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/social_bookmarks/facebook.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks a.taglib-social-bookmark-furl {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/social_bookmarks/furl.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks a.taglib-social-bookmark-newsvine {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/social_bookmarks/newsvine.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks a.taglib-social-bookmark-reddit {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/social_bookmarks/reddit.png);\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-social-bookmarks a.taglib-social-bookmark-technorati {\n");
      out.write("\tbackground-image: url(");
      out.print( themeImagesPath );
      out.write("/social_bookmarks/technorati.png);\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-asset-tags-summary .tag {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/tag.png) no-repeat 0 50%;\n");
      out.write("\tmargin-bottom: 3px;\n");
      out.write("\tmargin-right: 3px;\n");
      out.write("\tpadding: 2px 5px 2px 20px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-user-display .user-details .user-name {\n");
      out.write("\tclear: both;\n");
      out.write("\tdisplay: block;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".js .taglib-webdav.visible {\n");
      out.write("\tbackground: #F0F5F7;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .taglib-webdav .show-webdav {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .taglib-webdav.visible .show-webdav {\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .taglib-webdav table {\n");
      out.write("\tborder: 1px solid #828F95;\n");
      out.write("\tborder-left: none;\n");
      out.write("\tborder-right: none;\n");
      out.write("\tdisplay: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".js .taglib-webdav.visible table {\n");
      out.write("\tdisplay: block;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-webdav table:after {\n");
      out.write("\tclear: both;\n");
      out.write("\tcontent: \".\";\n");
      out.write("\tdisplay: block;\n");
      out.write("\theight: 0;\n");
      out.write("\tvisibility: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .taglib-webdav table {\n");
      out.write("\theight: 1%;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".taglib-workflow-status .workflow-id {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/history.png) no-repeat 0 50%;\n");
      out.write("\tcolor: #999;\n");
      out.write("\tpadding: 2px 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-workflow-status .workflow-version {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/pages.png) no-repeat 0 50%;\n");
      out.write("\tcolor: #999;\n");
      out.write("\tmargin-right: 5px;\n");
      out.write("\tpadding: 2px 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-workflow-status .workflow-status {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/recent_changes.png) no-repeat 0 50%;\n");
      out.write("\tcolor: #999;\n");
      out.write("\tmargin-right: 5px;\n");
      out.write("\tpadding: 2px 0 2px 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-workflow-status .workflow-status .workflow-status-approved {\n");
      out.write("\tcolor: green;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-workflow-status .workflow-status .workflow-status-draft {\n");
      out.write("\tcolor: blue;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-workflow-status .workflow-status .workflow-status-expired {\n");
      out.write("\tcolor: red;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".taglib-workflow-status .workflow-status .workflow-status-pending {\n");
      out.write("\tcolor: orange;\n");
      out.write("}");
      out.write('\n');
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write(".openid-login input[type=\"text\"] {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/openid.gif) no-repeat;\n");
      out.write("\tbackground-color: #fff;\n");
      out.write("\tbackground-position: 0 50%;\n");
      out.write("\tcolor: #000;\n");
      out.write("\tpadding-left: 18px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie6 .openid-login input.text {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/openid.gif) no-repeat;\n");
      out.write("\tbackground-color: #fff;\n");
      out.write("\tbackground-position: 0 50%;\n");
      out.write("\tcolor: #000;\n");
      out.write("\tpadding-left: 18px;\n");
      out.write("}");
      out.write('\n');

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

      out.write("\n");
      out.write("\n");
      out.write("img.label-icon {\n");
      out.write("\tborder: 0px;\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write("img.icon {\n");
      out.write("\tborder: 0px;\n");
      out.write("\tfloat: absmiddle;\n");
      out.write("}\n");
      out.write("\n");
      out.write("img.avatar {\n");
      out.write("\tborder: 0px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("label.hidden-label {\n");
      out.write("\tclip: rect(0 0 0 0);\n");
      out.write("\tposition: absolute;\n");
      out.write("}\n");
      out.write("\n");
      out.write("td.stretch {\n");
      out.write("\twidth: 99%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".nobr {\n");
      out.write("\twhite-space: nowrap;\n");
      out.write("}");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
