package org.apache.jsp.css;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/css_init.jsp");
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

      out.write("\r\n");
      out.write("\r\n");

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("html body {\r\n");
      out.write("\theight: auto;\r\n");
      out.write("\tpadding-bottom: 24px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* ---------- Bottom Dock ---------- */\r\n");
      out.write("\r\n");
      out.write(".chat-bar {\r\n");
      out.write("\tbackground: url(");
      out.print( request.getContextPath() );
      out.write("/images/btm-dock_bg.png);\r\n");
      out.write("\tborder: 1px 1px 1px 0 solid #b5b5b5;\r\n");
      out.write("\tbottom: 0;\r\n");
      out.write("\theight: 24px;\r\n");
      out.write("\tleft: 15px;\r\n");
      out.write("\tposition: fixed;\r\n");
      out.write("\tright: 15px;\r\n");
      out.write("\tz-index: 1000;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".ie6 .chat-bar {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".ie6 .lfr-shim {\r\n");
      out.write("\tbottom: 0;\r\n");
      out.write("\tborder: 0;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tz-index: 100;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-sound {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-status {\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-tabs-container {\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* ---------- Bottom Dock Tabs ---------- */\r\n");
      out.write("\r\n");
      out.write(".chat-bar div ul, .chat-bar div ul li {\r\n");
      out.write("\tlist-style-type: none;\r\n");
      out.write("\tmargin: 0;\r\n");
      out.write("\tpadding: 0;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-bar .chat-tabs li {\r\n");
      out.write("\tfloat: right;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-bar div ul li .panel-trigger {\r\n");
      out.write("\tcursor: pointer;\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\tborder-left: 1px solid #b5b5b5;\r\n");
      out.write("\tborder-right: 1px solid #e0e0e0;\r\n");
      out.write("\tcolor: #111;\r\n");
      out.write("\tfont: bold 11px/24px Tahoma, Geneva, sans-serif;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\tpadding: 0 6px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-bar div ul li .panel-trigger:hover {\r\n");
      out.write("\tbackground: #fff;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-bar div ul li.selected .panel-trigger {\r\n");
      out.write("\tbackground: #fff;\r\n");
      out.write("\tborder: 0 1px 1px solid #262626;\r\n");
      out.write("\tborder-radius: 0 4px;\r\n");
      out.write("\t-moz-border-radius: 0 4px;\r\n");
      out.write("\t-o-border-radius: 0 4px;\r\n");
      out.write("\t-webkit-border-radius: 0 4px;\r\n");
      out.write("\tline-height: 27px;\r\n");
      out.write("\theight: 25px;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("\tz-index: 30;\r\n");
      out.write("\tmargin: -2px 0 -1px;\r\n");
      out.write("}\r\n");
      out.write("/*\r\n");
      out.write(".chat-bar div ul li.buddy-list .panel-trigger {\r\n");
      out.write("\tborder-right: 0;\r\n");
      out.write("}*/\r\n");
      out.write("\r\n");
      out.write("/* ---------- Unread Messages ---------- */\r\n");
      out.write("\r\n");
      out.write(".chat-bar div ul li.waiting .panel-trigger {\r\n");
      out.write("\tbackground-image: url(");
      out.print( request.getContextPath() );
      out.write("/images/message_waiting_indicator.gif);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-bar div ul li .panel-trigger .unread {\r\n");
      out.write("\tbackground: url(");
      out.print( request.getContextPath() );
      out.write("/images/unread-messages.png);\r\n");
      out.write("\tcolor: #fff;\r\n");
      out.write("\tdisplay: none;\r\n");
      out.write("\tfont: bold 10px/16px Arial, Helvetica, sans-serif;\r\n");
      out.write("\theight: 16px;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tright: -4px;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\ttop: -6px;\r\n");
      out.write("\twidth: 16px;\r\n");
      out.write("\tz-index: 30;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-bar div ul li.selected .panel-trigger .unread {\r\n");
      out.write("\tright: -5px;\r\n");
      out.write("\ttop: -4px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* ---------- Typing status ---------- */\r\n");
      out.write("\r\n");
      out.write(".typing-status {\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\twidth: 16px;\r\n");
      out.write("\theight: 16px;\r\n");
      out.write("\tbackground: url(");
      out.print( request.getContextPath() );
      out.write("/images/typing.png) no-repeat 0 0;\r\n");
      out.write("\tdisplay: none;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\ttop: 5px;\r\n");
      out.write("\tleft: 4px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".selected .typing-status {\r\n");
      out.write("\ttop: 6px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".typing .typing-status {\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-bar div ul li.typing .panel-trigger {\r\n");
      out.write("\tpadding-left: 24px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* ---------- Bottom Dock Popups ---------- */\r\n");
      out.write("\r\n");
      out.write(".chat-bar .chat-panel {\r\n");
      out.write("\tbottom: 24px;\r\n");
      out.write("\tdisplay: none;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tright: 0;\r\n");
      out.write("\tz-index: 20;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-bar .selected .chat-panel {\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* ---------- Online Users ---------- */\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-content {\r\n");
      out.write("\tlist-style-type: none;\r\n");
      out.write("\tmargin: 0;\r\n");
      out.write("\tpadding: 2px 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-content .online-users li {\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\tmargin: 0 2px;\r\n");
      out.write("\tpadding: 2px;\r\n");
      out.write("\theight: 24px;\r\n");
      out.write("\tlist-style-type: none;\r\n");
      out.write("\tcursor: pointer;\r\n");
      out.write("\tborder: 1px solid #fff;\r\n");
      out.write("\twidth: 212px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-content .online-users li {\r\n");
      out.write("\tmargin: 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-content li.idle {\r\n");
      out.write("\theight: 14px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-content li:hover {\r\n");
      out.write("\tbackground: #f3f3f3;\r\n");
      out.write("\tborder-color: #ddd;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-content div {\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\tfont: 12px/24px Arial, Helvetica, sans-serif;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-content .idle div {\r\n");
      out.write("\tline-height: 14px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-content img {\r\n");
      out.write("\tbackground: #333;\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\theight: 24px;\r\n");
      out.write("\tmargin: 0 6px 0 0;\r\n");
      out.write("\twidth: 24px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-content .idle img {\r\n");
      out.write("\tdisplay: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* ---------- Popup Window ---------- */\r\n");
      out.write("\r\n");
      out.write(".chat-portlet .chat-settings .settings {\r\n");
      out.write("\tfloat: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-portlet .chat-settings li {\r\n");
      out.write("\tborder-bottom: 1px solid #999;\r\n");
      out.write("\tclear: both;\r\n");
      out.write("\tfloat: none;\r\n");
      out.write("\tpadding: 10px;\r\n");
      out.write("\tposition: static;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-settings label {\r\n");
      out.write("\tfont-weight: bold;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-settings input {\r\n");
      out.write("\tvertical-align: middle;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-settings .ctrl-holder {\r\n");
      out.write("\tpadding: 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-settings.saved .panel-trigger .trigger-name {\r\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/messages/success.png) no-repeat 0 0;\r\n");
      out.write("\tpadding-left: 20px;\r\n");
      out.write("\tpadding-bottom: 2px;\r\n");
      out.write("\tpadding-top: 2px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-status .status-message {\r\n");
      out.write("\tmargin: 5px;\r\n");
      out.write("\tpadding-left: 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-window {\r\n");
      out.write("\tborder-radius: 4px 4px 0 4px;\r\n");
      out.write("\t-moz-border-radius: 4px 4px 0 4px;\r\n");
      out.write("\t-o-border-radius: 4px 4px 0 4px;\r\n");
      out.write("\t-webkit-border-radius: 4px 4px 0 4px;\r\n");
      out.write("\tbackground: #fff;\r\n");
      out.write("\tborder: 1px solid #262626;\r\n");
      out.write("\tfont: 11px Tahoma, Geneva, sans-serif;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("\twidth: 226px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-icon {\r\n");
      out.write("\tbackground: #333;\r\n");
      out.write("\tborder: 1px solid #000;\r\n");
      out.write("\theight: 48px;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\ttop: 4px;\r\n");
      out.write("\tleft: 6px;\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\twidth: 48px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-title {\r\n");
      out.write("\tbackground: #20272b;\r\n");
      out.write("\tcolor: #f2f2f2;\r\n");
      out.write("\tfont-weight: bold;\r\n");
      out.write("\theight: 24px;\r\n");
      out.write("\tline-height: 24px;\r\n");
      out.write("\ttext-indent: 60px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-title, .chat-settings .panel-title {\r\n");
      out.write("\ttext-indent: 6px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-button {\r\n");
      out.write("\tcursor: pointer;\r\n");
      out.write("\theight: 12px;\r\n");
      out.write("\twidth: 12px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-button.close {\r\n");
      out.write("\tbackground: url(");
      out.print( request.getContextPath() );
      out.write("/images/chat-close_button.png) left bottom;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tright: 4px;\r\n");
      out.write("\ttop: 6px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-button.close:hover {\r\n");
      out.write("\tbackground-position: left top;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-button.minimize {\r\n");
      out.write("\tbackground: url(");
      out.print( request.getContextPath() );
      out.write("/images/chat-minimize_button.png) left bottom;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tright: 20px;\r\n");
      out.write("\ttop: 6px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".buddy-list .panel-button.minimize, .chat-settings .panel-button.minimize {\r\n");
      out.write("\tright: 4px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-button.minimize:hover {\r\n");
      out.write("\tbackground-position: left top;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-profile {\r\n");
      out.write("\tbackground: #eee;\r\n");
      out.write("\tborder-bottom: 1px solid #ccc;\r\n");
      out.write("\theight: 27px;\r\n");
      out.write("\tpadding-top: 5px;\r\n");
      out.write("\ttext-indent: 60px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-output {\r\n");
      out.write("\tborder-bottom: 1px solid #000;\r\n");
      out.write("\theight: 192px;\r\n");
      out.write("\toverflow-y: scroll;\r\n");
      out.write("\tpadding: 2px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".ie .panel-output {\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-output .blurb {\r\n");
      out.write("\tmargin: 0;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-output .name {\r\n");
      out.write("\tbackground: #828F95;\r\n");
      out.write("\tcolor: #fff;\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\tline-height: 11px;\r\n");
      out.write("\tpadding: 2px 4px 3px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-output .incoming .name {\r\n");
      out.write("\tbackground: #D3DADD;\r\n");
      out.write("\tcolor: #555;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-output .date {\r\n");
      out.write("\tcolor: #eee;\r\n");
      out.write("\tfont-size: 10px;\r\n");
      out.write("\tfont-style: normal;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tright: 8px;\r\n");
      out.write("\ttop: 2px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-output .incoming .date {\r\n");
      out.write("\tcolor: #555;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-output .text {\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\tline-height: 14px;\r\n");
      out.write("\tpadding: 4px 8px 8px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-input {\r\n");
      out.write("\tborder-top: 1px solid #369;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("\theight: 19px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-input textarea {\r\n");
      out.write("\tbackground: none;\r\n");
      out.write("\tborder: 0;\r\n");
      out.write("\tbottom: 0;\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\theight: 14px;\r\n");
      out.write("\toverflow: hidden;\r\n");
      out.write("\tpadding: 2px;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\twidth: 222px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-input textarea, .chat-height-monitor {\r\n");
      out.write("\tfont: 11px/14px Tahoma, Geneva, sans-serif;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".panel-input textarea.focus, .ie6 .panel-input textarea.focus {\r\n");
      out.write("\tbackground: #fff;\r\n");
      out.write("\tborder-width: 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".chat-height-monitor {\r\n");
      out.write("\tleft: -10000em;\r\n");
      out.write("\tpadding: 0;\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\ttop: -10000em;\r\n");
      out.write("\twhite-space: pre-wrap;\r\n");
      out.write("\twhite-space: -moz-pre-wrap;\r\n");
      out.write("\twhite-space: -pre-wrap;\r\n");
      out.write("\twhite-space: -o-pre-wrap;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".ie .chat-height-monitor {\r\n");
      out.write("\tword-wrap: break-word;\r\n");
      out.write("\tzoom: 1;\r\n");
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
