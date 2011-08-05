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

package com.liferay.wsrp.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.util.servlet.ServletResponseUtil;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class WSDLServlet extends HttpServlet {

	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		try {
			String content = getContent(request);

			response.setContentType(ContentTypes.TEXT_XML);

			ServletResponseUtil.write(response, content);
		}
		catch (Exception e) {
			_log.error(e, e);

			response.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	protected String getContent(HttpServletRequest request) throws Exception {
		String path = GetterUtil.getString(request.getPathInfo());

		ServletContext servletContext = getServletContext();

		for (String curPath : _PATHS) {
			if (path.equals(curPath)) {
				String content = StringUtil.read(
					servletContext.getResourceAsStream("/WEB-INF/wsdl" + path));

				return replaceLocations(request, content);
			}
		}

		double version = ParamUtil.getDouble(
			request, "version", _DEFAULT_VERSION);

		String content = StringUtil.read(
			servletContext.getResourceAsStream(
				"/WEB-INF/wsdl/wsrp-" + version + "-service.wsdl"));

		content = replaceLocations(request, content);

		String url = request.getRequestURL().toString();

		int pos = url.lastIndexOf(StringPool.SLASH);

		long wsrpProducerId = GetterUtil.getLong(url.substring(pos));

		return StringUtil.replace(
			content,
			new String[] {
				"http://my.service:8080",
				"${wsrpProducerId}"
			},
			new String[] {
				getURL(request),
				String.valueOf(wsrpProducerId)
			});
	}

	protected String getURL(HttpServletRequest request) {
		String url = request.getRequestURL().toString();

		int pos = url.lastIndexOf("/wsdl");

		return url.substring(0, pos);
	}

	protected String replaceLocations(
		HttpServletRequest request, String content) {

		String url = getURL(request) + "/wsdl";

		return StringUtil.replace(
			content,
			new String[] {
				"location=\"wsrp-",
				"schemaLocation=\"wsrp-"
			},
			new String[] {
				"location=\"" + url + "/wsrp-",
				"schemaLocation=\"" + url + "/wsrp-"
			});
	}

	private static final double _DEFAULT_VERSION = 2.0;

	private static final String[] _PATHS = {
		"/wsrp-1.0-bindings.wsdl", "/wsrp-1.0-interfaces.wsdl",
		"/wsrp-1.0-service.wsdl", "/wsrp-1.0-types.xsd",
		"/wsrp-2.0-bindings.wsdl", "/wsrp-2.0-extra.xsd",
		"/wsrp-2.0-interfaces.wsdl", "/wsrp-2.0-service.wsdl",
		"/wsrp-2.0-types.xsd"
	};

	private static Log _log = LogFactoryUtil.getLog(WSDLServlet.class);

}