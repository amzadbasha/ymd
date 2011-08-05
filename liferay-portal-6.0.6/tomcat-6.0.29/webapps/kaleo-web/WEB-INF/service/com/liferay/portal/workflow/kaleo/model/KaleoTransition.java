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

package com.liferay.portal.workflow.kaleo.model;

/**
 * The model interface for the KaleoTransition service. Represents a row in the &quot;KaleoTransition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Never modify this interface directly. Add methods to {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. All methods that expect a kaleo transition model instance should use the {@link KaleoTransition} interface instead.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTransitionModel
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionImpl
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoTransitionModelImpl
 * @generated
 */
public interface KaleoTransition extends KaleoTransitionModel {
	public com.liferay.portal.workflow.kaleo.model.KaleoNode getSourceKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getTargetKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}