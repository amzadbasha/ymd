package org.apache.jsp.html.portlet.asset_005fpublisher.css;

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
    _jspx_dependants.add("/html/portlet/css_init.jsp");
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
      out.write(".portlet-asset-publisher .add-asset-selector {\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("\toverflow: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-abstract .asset-content p {\n");
      out.write("\tmargin-bottom: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-abstract .asset-description {\n");
      out.write("\tclear: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-back-to {\n");
      out.write("\ttext-align: right;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-content .asset-description {\n");
      out.write("\tclear: left;\n");
      out.write("\tfont-style: italic;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-content {\n");
      out.write("\tclear: right;\n");
      out.write("\tmargin-bottom: 10px;\n");
      out.write("\tmargin-left: 25px;\n");
      out.write("\tmargin-right: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-flag {\n");
      out.write("\tmargin-top: 1.8em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-full-content .asset-content {\n");
      out.write("\tmargin-right: 25px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-full-content.show-asset-title .asset-content {\n");
      out.write("\tmargin-right: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-full-content.no-title .asset-user-actions{\n");
      out.write("\tpadding-bottom: 2em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-edit {\n");
      out.write("\tfloat: right;\n");
      out.write("\tmargin-left: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-metadata span {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-metadata .metadata-categories span {\n");
      out.write("\tfloat: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-metadata {\n");
      out.write("\tclear: both;\n");
      out.write("\tmargin-left: 25px;\n");
      out.write("\toverflow: hidden;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".ie .portlet-asset-publisher .asset-metadata {\n");
      out.write("\theight: 1%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-more {\n");
      out.write("\tclear: left;\n");
      out.write("\t/*Amzad*/\n");
      out.write("\tcolor: #5B677D;\n");
      out.write("\tfont-size: 10px;\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-entries-group-label {\n");
      out.write("\tbackground-color: #DDD;\n");
      out.write("\tclear: both;\n");
      out.write("\tpadding: 3px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-ratings {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-small-image {\n");
      out.write("\tfloat: right;\n");
      out.write("\tpadding-left: 0.5em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-title .asset-actions img {\n");
      out.write("\tmargin-left: 5px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-actions {\n");
      out.write("\tfloat: right;\n");
      out.write("\tfont-size: 11px;\n");
      out.write("\tfont-weight: normal;\n");
      out.write("\tmargin-bottom: 3px;\n");
      out.write("\tmargin-top: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-title a {\n");
      out.write("\ttext-decoration: none;\n");
      out.write("\tmargin-left: 10px;\n");
      out.write("\t\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-title a:hover {\n");
      out.write("\ttext-decoration: underline;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-title {\n");
      out.write("\tborder-bottom: 1px solid #DDD;\n");
      out.write("\tmargin-bottom: 0.7em;\n");
      out.write("\tmargin-right: 8px;\n");
      out.write("\tmargin-top: 2em;\n");
      out.write("\tpadding-bottom: 1px;\n");
      out.write("\tfont-size: 12px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-user-actions .export-actions, .portlet-asset-publisher .asset-user-actions .print-action, .portlet-asset-publisher .asset-user-actions .locale-actions {\n");
      out.write("\tfloat: right;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-user-actions .locale-separator {\n");
      out.write("\tborder-right: 1px solid #CCC;\n");
      out.write("\tfloat: right;\n");
      out.write("\tmargin-right: 1em;\n");
      out.write("\tpadding: 0.8em 0.5em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .asset-user-actions .print-action {\n");
      out.write("\tmargin: 0 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .edit-controls {\n");
      out.write("\tmargin-bottom: 20px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .final-separator {\n");
      out.write("\tborder: 0;\n");
      out.write("\tmargin-bottom: 30px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .lfr-meta-actions {\n");
      out.write("\tmargin-right: 1em;\n");
      out.write("\tpadding-top: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .metadata-author {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/portlet/edit_guest.png) no-repeat 0 0;\n");
      out.write("\tfloat: left;\n");
      out.write("\tfont-weight: bold;\n");
      out.write("\tmargin-right: 10px;\n");
      out.write("\tpadding-left: 25px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .metadata-entry {\n");
      out.write("\tcolor: #999;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .metadata-modified-date, .portlet-asset-publisher .metadata-create-date, .portlet-asset-publisher .metadata-publish-date, .portlet-asset-publisher .metadata-expiration-date {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/date.png) no-repeat 0 0;\n");
      out.write("\tcolor: #999;\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("\tpadding-left: 25px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .metadata-priority {\n");
      out.write("\tbackground: url(");
      out.print( themeImagesPath );
      out.write("/common/top.png) no-repeat 0 20%;\n");
      out.write("\tmargin-right: 10px;\n");
      out.write("\tpadding-left: 25px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .metadata-view-count {\n");
      out.write("\tmargin-right: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .separator {\n");
      out.write("\tborder-right: 1px solid #999;\n");
      out.write("\tclear: both;\n");
      out.write("\tmargin: 25px 25px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .taglib-asset-categories-summary {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .taglib-asset-tags-summary {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .title-list .asset-actions {\n");
      out.write("\tleft: 10px;\n");
      out.write("\tposition: relative;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .title-list .asset-metadata {\n");
      out.write("\tpadding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .title-list a {\n");
      out.write("\tfloat: left;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher .vertical-separator {\n");
      out.write("\tborder-right: 1px solid #999;\n");
      out.write("\tfloat: left;\n");
      out.write("\tmargin: 0 10px;\n");
      out.write("\tpadding: 7px 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher li.title-list {\n");
      out.write("\tclear: both;\n");
      out.write("\tlist-style: none;\n");
      out.write("\tmargin-bottom: 0.15em;\n");
      out.write("\tmargin-right: 8px;\n");
      out.write("\tpadding-bottom: 1px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-asset-publisher ul.title-list {\n");
      out.write("\tmargin-left: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .aui-form .queryRules fieldset {\n");
      out.write("\tpadding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .aui-form .queryRules fieldset legend {\n");
      out.write("\tfont-size: 1em;\n");
      out.write("\tfont-weight: normal;\n");
      out.write("\tpadding: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .aui-form .lfr-form-row {\n");
      out.write("\tbackground-color: #F3F3F3;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .aui-form .lfr-form-row:hover {\n");
      out.write("\tbackground-color: #DFFCCB;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .aui-form .lfr-form-row .aui-field {\n");
      out.write("\tline-height: 2;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .aui-form .lfr-form-row .aui-field.tags-selector, .aui-form .lfr-form-row .aui-field.categories-selector{\n");
      out.write("\tclear: both;\n");
      out.write("\tline-height: 1.5;\n");
      out.write("\tmargin-top: 5px;\n");
      out.write("\twidth: 100%;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .lfr-panel .lfr-panel-titlebar {\n");
      out.write("\tmargin-bottom: 0;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .lfr-panel-content {\n");
      out.write("\tbackground-color: #F8F8F8;\n");
      out.write("\tpadding: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .aui-form .general-display-settings {\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .dl-file-icon {\n");
      out.write("\tborder-width: 0;\n");
      out.write("\tmargin-right: 0.3em;\n");
      out.write("\tposition: relative;\n");
      out.write("\ttext-align: left;\n");
      out.write("\ttop: 0.4em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .add-asset-selector {\n");
      out.write("\toverflow: auto;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .asset-search {\n");
      out.write("\tmargin-bottom: 1em;\n");
      out.write("}\n");
      out.write("\n");
      out.write(".portlet-configuration .lfr-actions.select-existing-selector {\n");
      out.write("\tmargin: 0 0  1em 1em;\n");
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
