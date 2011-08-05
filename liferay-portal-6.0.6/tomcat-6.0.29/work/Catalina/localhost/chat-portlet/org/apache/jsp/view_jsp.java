package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.liferay.chat.model.Status;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.chat.util.ChatUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ContactConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import java.util.List;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(5);
    _jspx_dependants.add("/init.jsp");
    _jspx_dependants.add("/WEB-INF/tld/c.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-theme.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-ui.tld");
    _jspx_dependants.add("/WEB-INF/tld/liferay-util.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fliferay_002dtheme_005fdefineObjects_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dtop;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dbottom;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fliferay_002dtheme_005fdefineObjects_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dtop = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dbottom = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fliferay_002dtheme_005fdefineObjects_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dtop.release();
    _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dbottom.release();
    _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.release();
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      //  liferay-theme:defineObjects
      com.liferay.taglib.theme.DefineObjectsTag _jspx_th_liferay_002dtheme_005fdefineObjects_005f0 = (com.liferay.taglib.theme.DefineObjectsTag) _005fjspx_005ftagPool_005fliferay_002dtheme_005fdefineObjects_005fnobody.get(com.liferay.taglib.theme.DefineObjectsTag.class);
      _jspx_th_liferay_002dtheme_005fdefineObjects_005f0.setPageContext(_jspx_page_context);
      _jspx_th_liferay_002dtheme_005fdefineObjects_005f0.setParent(null);
      int _jspx_eval_liferay_002dtheme_005fdefineObjects_005f0 = _jspx_th_liferay_002dtheme_005fdefineObjects_005f0.doStartTag();
      if (_jspx_th_liferay_002dtheme_005fdefineObjects_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fliferay_002dtheme_005fdefineObjects_005fnobody.reuse(_jspx_th_liferay_002dtheme_005fdefineObjects_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fliferay_002dtheme_005fdefineObjects_005fnobody.reuse(_jspx_th_liferay_002dtheme_005fdefineObjects_005f0);
      com.liferay.portal.theme.ThemeDisplay themeDisplay = null;
      com.liferay.portal.model.Company company = null;
      com.liferay.portal.model.Account account = null;
      com.liferay.portal.model.User user = null;
      com.liferay.portal.model.User realUser = null;
      com.liferay.portal.model.Contact contact = null;
      com.liferay.portal.model.Layout layout = null;
      java.util.List layouts = null;
      java.lang.Long plid = null;
      com.liferay.portal.model.LayoutTypePortlet layoutTypePortlet = null;
      java.lang.Long scopeGroupId = null;
      com.liferay.portal.security.permission.PermissionChecker permissionChecker = null;
      java.util.Locale locale = null;
      java.util.TimeZone timeZone = null;
      com.liferay.portal.model.Theme theme = null;
      com.liferay.portal.model.ColorScheme colorScheme = null;
      com.liferay.portal.theme.PortletDisplay portletDisplay = null;
      java.lang.Long portletGroupId = null;
      themeDisplay = (com.liferay.portal.theme.ThemeDisplay) _jspx_page_context.findAttribute("themeDisplay");
      company = (com.liferay.portal.model.Company) _jspx_page_context.findAttribute("company");
      account = (com.liferay.portal.model.Account) _jspx_page_context.findAttribute("account");
      user = (com.liferay.portal.model.User) _jspx_page_context.findAttribute("user");
      realUser = (com.liferay.portal.model.User) _jspx_page_context.findAttribute("realUser");
      contact = (com.liferay.portal.model.Contact) _jspx_page_context.findAttribute("contact");
      layout = (com.liferay.portal.model.Layout) _jspx_page_context.findAttribute("layout");
      layouts = (java.util.List) _jspx_page_context.findAttribute("layouts");
      plid = (java.lang.Long) _jspx_page_context.findAttribute("plid");
      layoutTypePortlet = (com.liferay.portal.model.LayoutTypePortlet) _jspx_page_context.findAttribute("layoutTypePortlet");
      scopeGroupId = (java.lang.Long) _jspx_page_context.findAttribute("scopeGroupId");
      permissionChecker = (com.liferay.portal.security.permission.PermissionChecker) _jspx_page_context.findAttribute("permissionChecker");
      locale = (java.util.Locale) _jspx_page_context.findAttribute("locale");
      timeZone = (java.util.TimeZone) _jspx_page_context.findAttribute("timeZone");
      theme = (com.liferay.portal.model.Theme) _jspx_page_context.findAttribute("theme");
      colorScheme = (com.liferay.portal.model.ColorScheme) _jspx_page_context.findAttribute("colorScheme");
      portletDisplay = (com.liferay.portal.theme.PortletDisplay) _jspx_page_context.findAttribute("portletDisplay");
      portletGroupId = (java.lang.Long) _jspx_page_context.findAttribute("portletGroupId");
      out.write("\r\n");
      out.write("\r\n");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(null);
      // /view.jsp(19,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest( themeDisplay.isSignedIn() && !(BrowserSnifferUtil.isIe(request) && (BrowserSnifferUtil.getMajorVersion(request) < 7)) );
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\r\n");
          out.write("\t");

	Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
	
          out.write("\r\n");
          out.write("\r\n");
          out.write("\t");
          //  liferay-util:html-top
          com.liferay.taglib.util.HtmlTopTag _jspx_th_liferay_002dutil_005fhtml_002dtop_005f0 = (com.liferay.taglib.util.HtmlTopTag) _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dtop.get(com.liferay.taglib.util.HtmlTopTag.class);
          _jspx_th_liferay_002dutil_005fhtml_002dtop_005f0.setPageContext(_jspx_page_context);
          _jspx_th_liferay_002dutil_005fhtml_002dtop_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
          int _jspx_eval_liferay_002dutil_005fhtml_002dtop_005f0 = _jspx_th_liferay_002dutil_005fhtml_002dtop_005f0.doStartTag();
          if (_jspx_eval_liferay_002dutil_005fhtml_002dtop_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_liferay_002dutil_005fhtml_002dtop_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay_002dutil_005fhtml_002dtop_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay_002dutil_005fhtml_002dtop_005f0.doInitBody();
            }
            do {
              out.write("\r\n");
              out.write("\t\t<link href=\"");
              out.print( PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/css/main.jsp", portlet.getTimestamp()) );
              out.write("\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
              out.write("\t");
              int evalDoAfterBody = _jspx_th_liferay_002dutil_005fhtml_002dtop_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay_002dutil_005fhtml_002dtop_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.popBody();
            }
          }
          if (_jspx_th_liferay_002dutil_005fhtml_002dtop_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dtop.reuse(_jspx_th_liferay_002dutil_005fhtml_002dtop_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dtop.reuse(_jspx_th_liferay_002dutil_005fhtml_002dtop_005f0);
          out.write("\r\n");
          out.write("\r\n");
          out.write("\t");
          //  liferay-util:html-bottom
          com.liferay.taglib.util.HtmlBottomTag _jspx_th_liferay_002dutil_005fhtml_002dbottom_005f0 = (com.liferay.taglib.util.HtmlBottomTag) _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dbottom.get(com.liferay.taglib.util.HtmlBottomTag.class);
          _jspx_th_liferay_002dutil_005fhtml_002dbottom_005f0.setPageContext(_jspx_page_context);
          _jspx_th_liferay_002dutil_005fhtml_002dbottom_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
          int _jspx_eval_liferay_002dutil_005fhtml_002dbottom_005f0 = _jspx_th_liferay_002dutil_005fhtml_002dbottom_005f0.doStartTag();
          if (_jspx_eval_liferay_002dutil_005fhtml_002dbottom_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            if (_jspx_eval_liferay_002dutil_005fhtml_002dbottom_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.pushBody();
              _jspx_th_liferay_002dutil_005fhtml_002dbottom_005f0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
              _jspx_th_liferay_002dutil_005fhtml_002dbottom_005f0.doInitBody();
            }
            do {
              out.write("\r\n");
              out.write("\t\t<script src=\"");
              out.print( PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/js/main.js", portlet.getTimestamp()) );
              out.write("\" type=\"text/javascript\"></script>\r\n");
              out.write("\t");
              int evalDoAfterBody = _jspx_th_liferay_002dutil_005fhtml_002dbottom_005f0.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_liferay_002dutil_005fhtml_002dbottom_005f0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              out = _jspx_page_context.popBody();
            }
          }
          if (_jspx_th_liferay_002dutil_005fhtml_002dbottom_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dbottom.reuse(_jspx_th_liferay_002dutil_005fhtml_002dbottom_005f0);
            return;
          }
          _005fjspx_005ftagPool_005fliferay_002dutil_005fhtml_002dbottom.reuse(_jspx_th_liferay_002dutil_005fhtml_002dbottom_005f0);
          out.write("\r\n");
          out.write("\r\n");
          out.write("\t");

	Status status = StatusLocalServiceUtil.getUserStatus(themeDisplay.getUserId());

	boolean online = status.getOnline();
	String activePanelId = status.getActivePanelId();
	String statusMessage = HtmlUtil.escape(status.getMessage());
	boolean playSound = status.getPlaySound();

	List<Object[]> buddies = ChatUtil.getBuddies(themeDisplay.getCompanyId(), themeDisplay.getUserId());

	int buddiesCount = buddies.size();
	
          out.write("\r\n");
          out.write("\r\n");
          out.write("\t<div class=\"portlet-chat\" id=\"chatBar\">\r\n");
          out.write("\t\t<div class=\"chat-bar\">\r\n");
          out.write("\t\t\t<div class=\"chat-sound\"></div>\r\n");
          out.write("\r\n");
          out.write("\t\t\t<div class=\"chat-status\">\r\n");
          out.write("\t\t\t\t<div class=\"status-message\">\r\n");
          out.write("\t\t\t\t\t");
          //  c:if
          org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
          _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
          _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
          // /view.jsp(52,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
          _jspx_th_c_005fif_005f1.setTest( Validator.isNotNull(statusMessage) );
          int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
          if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            do {
              out.write("\r\n");
              out.write("\t\t\t\t\t\t");
              out.print( LanguageUtil.format(pageContext, "you-are-x", "<strong>" + statusMessage + "</strong>", false) );
              out.write("\r\n");
              out.write("\t\t\t\t\t");
              int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
          }
          if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
            _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
            return;
          }
          _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
          out.write("\r\n");
          out.write("\t\t\t\t</div>\r\n");
          out.write("\t\t\t</div>\r\n");
          out.write("\r\n");
          out.write("\t\t\t<div class=\"chat-tabs-container\">\r\n");
          out.write("\t\t\t\t<ul class=\"chat-tabs\">\r\n");
          out.write("\t\t\t\t\t<li class=\"buddy-list ");
          out.print( activePanelId.equals("buddylist") ? "selected" : "" );
          out.write("\">\r\n");
          out.write("\t\t\t\t\t\t<div class=\"panel-trigger\" panelId=\"buddylist\">\r\n");
          out.write("\t\t\t\t\t\t\t<span class=\"trigger-name\">");
          out.print( LanguageUtil.format(pageContext, "online-friends-x", "(" + buddiesCount + ")", false) );
          out.write("</span>\r\n");
          out.write("\t\t\t\t\t\t</div>\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t<div class=\"chat-panel\">\r\n");
          out.write("\t\t\t\t\t\t\t<div class=\"panel-window\">\r\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"panel-button minimize\"></div>\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"panel-title\">");
          out.print( LanguageUtil.format(pageContext, "online-friends-x", "(" + buddiesCount + ")", false) );
          out.write("</div>\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"panel-content\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<ul class=\"lfr-component online-users\">\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t");

										for (Object[] buddy : buddies) {
											long userId = (Long)buddy[0];
											String firstName = (String)buddy[1];
											String middleName = (String)buddy[2];
											String lastName = (String)buddy[3];
											long portraitId = (Long)buddy[4];
											boolean awake = (Boolean)buddy[5];

											String fullName = ContactConstants.getFullName(firstName, middleName, lastName);
										
          out.write("\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t<li class=\"active\" userId=\"");
          out.print( userId );
          out.write("\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t<img alt=\"\" src=\"");
          out.print( themeDisplay.getPathImage() );
          out.write("/user_portrait?img_id=");
          out.print( portraitId );
          out.write("\">\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"name\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t");
          out.print( fullName );
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t</li>\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t");

										}
										
          out.write("\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t</ul>\r\n");
          out.write("\t\t\t\t\t\t\t\t</div>\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t<div style=\"clear: both;\"></div>\r\n");
          out.write("\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t</li>\r\n");
          out.write("\t\t\t\t\t<li class=\"chat-settings ");
          out.print( activePanelId.equals("settings") ? "selected" : "" );
          out.write("\">\r\n");
          out.write("\t\t\t\t\t\t<div class=\"panel-trigger\" panelId=\"settings\">\r\n");
          out.write("\t\t\t\t\t\t\t<span class=\"trigger-name\">");
          if (_jspx_meth_liferay_002dui_005fmessage_005f0(_jspx_th_c_005fif_005f0, _jspx_page_context))
            return;
          out.write("</span>\r\n");
          out.write("\t\t\t\t\t\t</div>\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t<div class=\"chat-panel\">\r\n");
          out.write("\t\t\t\t\t\t\t<div class=\"panel-window\">\r\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"panel-button minimize\"></div>\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"panel-title\">");
          if (_jspx_meth_liferay_002dui_005fmessage_005f1(_jspx_th_c_005fif_005f0, _jspx_page_context))
            return;
          out.write("</div>\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t<ul class=\"lfr-component settings\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<li>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t<label for=\"statusMessage\">");
          out.print( LanguageUtil.format(pageContext, "x-is", user.getFullName(), false) );
          out.write("</label>\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t<input id=\"statusMessage\" type=\"text\" value=\"");
          out.print( statusMessage );
          out.write("\" />\r\n");
          out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<li>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t<label for=\"onlineStatus\"><input ");
          out.print( online ? "checked=\"checked\"" : "" );
          out.write(" id=\"onlineStatus\" type=\"checkbox\" /> ");
          if (_jspx_meth_liferay_002dui_005fmessage_005f2(_jspx_th_c_005fif_005f0, _jspx_page_context))
            return;
          out.write("</label>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<li>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t\t<label for=\"playSound\"><input ");
          out.print( playSound ? "checked=\"checked\"" : "" );
          out.write(" id=\"playSound\" type=\"checkbox\" /> ");
          if (_jspx_meth_liferay_002dui_005fmessage_005f3(_jspx_th_c_005fif_005f0, _jspx_page_context))
            return;
          out.write(" </label>\r\n");
          out.write("\t\t\t\t\t\t\t\t\t</li>\r\n");
          out.write("\t\t\t\t\t\t\t\t</ul>\r\n");
          out.write("\r\n");
          out.write("\t\t\t\t\t\t\t\t<div class=\"ctrl-holder\">\r\n");
          out.write("\t\t\t\t\t\t\t\t\t<input id=\"saveSettings\" type=\"submit\" value=\"");
          if (_jspx_meth_liferay_002dui_005fmessage_005f4(_jspx_th_c_005fif_005f0, _jspx_page_context))
            return;
          out.write("\">\r\n");
          out.write("\t\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t\t</li>\r\n");
          out.write("\t\t\t\t</ul>\r\n");
          out.write("\t\t\t</div>\r\n");
          out.write("\t\t</div>\r\n");
          out.write("\r\n");
          out.write("\t\t<input id=\"activePanelId\" type=\"hidden\" value=\"");
          out.print( activePanelId );
          out.write("\" />\r\n");
          out.write("\t\t<input id=\"chatPortletId\" type=\"hidden\" value=\"");
          out.print( portletDisplay.getId() );
          out.write("\" />\r\n");
          out.write("\t</div>\r\n");
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
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

  private boolean _jspx_meth_liferay_002dui_005fmessage_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay_002dui_005fmessage_005f0 = (com.liferay.taglib.ui.MessageTag) _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay_002dui_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_liferay_002dui_005fmessage_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /view.jsp(107,34) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_liferay_002dui_005fmessage_005f0.setKey("settings");
    int _jspx_eval_liferay_002dui_005fmessage_005f0 = _jspx_th_liferay_002dui_005fmessage_005f0.doStartTag();
    if (_jspx_th_liferay_002dui_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_liferay_002dui_005fmessage_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_liferay_002dui_005fmessage_005f0);
    return false;
  }

  private boolean _jspx_meth_liferay_002dui_005fmessage_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay_002dui_005fmessage_005f1 = (com.liferay.taglib.ui.MessageTag) _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay_002dui_005fmessage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_liferay_002dui_005fmessage_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /view.jsp(114,33) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_liferay_002dui_005fmessage_005f1.setKey("settings");
    int _jspx_eval_liferay_002dui_005fmessage_005f1 = _jspx_th_liferay_002dui_005fmessage_005f1.doStartTag();
    if (_jspx_th_liferay_002dui_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_liferay_002dui_005fmessage_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_liferay_002dui_005fmessage_005f1);
    return false;
  }

  private boolean _jspx_meth_liferay_002dui_005fmessage_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay_002dui_005fmessage_005f2 = (com.liferay.taglib.ui.MessageTag) _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay_002dui_005fmessage_005f2.setPageContext(_jspx_page_context);
    _jspx_th_liferay_002dui_005fmessage_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /view.jsp(123,123) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_liferay_002dui_005fmessage_005f2.setKey("show-me-as-online");
    int _jspx_eval_liferay_002dui_005fmessage_005f2 = _jspx_th_liferay_002dui_005fmessage_005f2.doStartTag();
    if (_jspx_th_liferay_002dui_005fmessage_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_liferay_002dui_005fmessage_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_liferay_002dui_005fmessage_005f2);
    return false;
  }

  private boolean _jspx_meth_liferay_002dui_005fmessage_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay_002dui_005fmessage_005f3 = (com.liferay.taglib.ui.MessageTag) _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay_002dui_005fmessage_005f3.setPageContext(_jspx_page_context);
    _jspx_th_liferay_002dui_005fmessage_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /view.jsp(126,120) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_liferay_002dui_005fmessage_005f3.setKey("play-a-sound-when-i-receive-a-new-message-in-a-hidden-window");
    int _jspx_eval_liferay_002dui_005fmessage_005f3 = _jspx_th_liferay_002dui_005fmessage_005f3.doStartTag();
    if (_jspx_th_liferay_002dui_005fmessage_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_liferay_002dui_005fmessage_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_liferay_002dui_005fmessage_005f3);
    return false;
  }

  private boolean _jspx_meth_liferay_002dui_005fmessage_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  liferay-ui:message
    com.liferay.taglib.ui.MessageTag _jspx_th_liferay_002dui_005fmessage_005f4 = (com.liferay.taglib.ui.MessageTag) _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.get(com.liferay.taglib.ui.MessageTag.class);
    _jspx_th_liferay_002dui_005fmessage_005f4.setPageContext(_jspx_page_context);
    _jspx_th_liferay_002dui_005fmessage_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /view.jsp(131,55) name = key type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_liferay_002dui_005fmessage_005f4.setKey("save");
    int _jspx_eval_liferay_002dui_005fmessage_005f4 = _jspx_th_liferay_002dui_005fmessage_005f4.doStartTag();
    if (_jspx_th_liferay_002dui_005fmessage_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_liferay_002dui_005fmessage_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fliferay_002dui_005fmessage_0026_005fkey_005fnobody.reuse(_jspx_th_liferay_002dui_005fmessage_005f4);
    return false;
  }
}
