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

package com.liferay.portal.workflow.kaleo.definition;

import com.liferay.portal.kernel.util.Validator;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public abstract class Node {

	public Node(NodeType nodeType, String name, String description) {
		_nodeType = nodeType;
		_name = name;
		_description = description;
	}

	public void addTransition(Transition transition) {
		_transitions.put(transition.getName(), transition);
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Node)) {
			return false;
		}

		Node node = (Node)obj;

		if (Validator.equals(_name, node._name)) {
			return true;
		}

		return true;
	}

	public Set<Action> getActions() {
		if (_actions == null) {
			return Collections.EMPTY_SET;
		}

		return _actions;
	}

	public String getDescription() {
		return _description;
	}

	public String getName() {
		return _name;
	}

	public NodeType getNodeType() {
		return _nodeType;
	}

	public Set<Notification> getNotifications() {
		if (_notifications == null) {
			return Collections.EMPTY_SET;
		}

		return _notifications;
	}

	public Map<String, Transition> getTransitions() {
		return _transitions;
	}

	public Collection<Transition> getTransitionsEntries() {
		return Collections.unmodifiableCollection(_transitions.values());
	}

	public int hashCode() {
		return _name.hashCode();
	}

	public void setActions(Set<Action> actions) {
		_actions = actions;
	}

	public void setNotifications(Set<Notification> notifications) {
		_notifications = notifications;
	}

	private Set<Action> _actions;
	private String _description;
	private String _name;
	private NodeType _nodeType;
	private Set<Notification> _notifications;
	private Map<String, Transition> _transitions =
		new HashMap<String, Transition>();

}