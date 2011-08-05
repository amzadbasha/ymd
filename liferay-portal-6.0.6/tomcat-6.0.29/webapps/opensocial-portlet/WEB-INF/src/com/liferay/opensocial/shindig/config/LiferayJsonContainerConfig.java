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

package com.liferay.opensocial.shindig.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import javax.annotation.Nullable;

import org.apache.shindig.config.ContainerConfigException;
import org.apache.shindig.config.JsonContainerConfig;
import org.apache.shindig.expressions.Expressions;

/**
 * @author Michael Young
 */
@Singleton
public class LiferayJsonContainerConfig extends JsonContainerConfig {

	@Inject
	public LiferayJsonContainerConfig(
			@Named("shindig.containers.default") String containers,
			@Nullable @Named("shindig.port") String port,
			Expressions expressions)
		throws ContainerConfigException {

		super(containers, port, expressions);
	}

	public String getString(String container, String property) {
		String value = super.getString(container, property);

		if (Validator.isNotNull(value)) {
			value = value.replaceAll("%host%", _host.get());
		}

		return value;
	}

	public void setHost(String host) {
		_host.set(host);
	}

	private static AutoResetThreadLocal<String> _host =
		new AutoResetThreadLocal<String>(
			LiferayJsonContainerConfig.class + "._host", StringPool.BLANK);

}