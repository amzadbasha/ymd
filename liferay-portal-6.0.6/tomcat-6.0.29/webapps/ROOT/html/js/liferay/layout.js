AUI().add(
	'liferay-layout',
	function(A) {
		var DDM = A.DD.DDM;

		var CSS_DRAGGABLE = A.ClassNameManager.getClassName('dd', 'draggable');

		var getTitle = A.cached(
			function(id) {
				var portletBoundary = A.one('#' + id);

				var portletTitle = portletBoundary.one('.portlet-title');

				if (!portletTitle) {
					portletTitle = Liferay.Layout.PROXY_NODE_ITEM.one('.portlet-title');

					var title = portletBoundary.one('.portlet-title-default');

					var titleText = '';

					if (title) {
						titleText = title.html();
					}

					portletTitle.html(titleText);
				}

				return portletTitle.outerHTML();
			}
		);

		var Layout = {
			EMPTY_COLUMNS: {},

			OVER_NESTED_PORTLET: false,

			PROXY_NODE: A.Node.create('<div class="lfr-portlet-proxy aui-portal-layout-proxy"></div>'),

			PROXY_NODE_ITEM: A.Node.create(
				'<div class="lfr-portlet-proxy aui-portal-layout-proxy">' +
					'<div class="portlet-topper">' +
						'<span class="portlet-title"></span>' +
					'</div>' +
				'</div>'
			),

			PORTLET_TOPPER: A.Node.create('<div class="portlet-topper"></div>'),

			init: function(options) {
				Layout.options = options;
				Layout.isFreeForm = options.freeForm;

				Layout.PROXY_NODE.append(Layout.PORTLET_TOPPER);

				Layout.DEFAULT_LAYOUT_OPTIONS = {
					delegateConfig: {
						dragConfig: {
							clickPixelThresh: 0,
							clickTimeThresh: 250,
							plugins: [
								{
									cfg: {
										horizontal: false,
										scrollDelay: 30
									},
									fn: A.Plugin.DDWinScroll
								}
							]
						},
						handles: options.handles,
						invalid: options.invalid
					},
					dragNodes: options.dragNodes,
					dropContainer: function(dropNode) {
						return dropNode.one(options.dropContainer);
					},
					dropNodes: options.dropNodes,
					lazyStart: true,
					on: {
						'drop:enter': function(event) {
							Liferay.Layout.updateOverNestedPortletInfo();
						},

						'drop:exit': function(event) {
							Liferay.Layout.updateOverNestedPortletInfo();
						},
						placeholderAlign: function(event) {
							var portalLayout = event.currentTarget;
							var activeDrop = portalLayout.activeDrop;
							var lastActiveDrop = portalLayout.lastActiveDrop;

							if (lastActiveDrop) {
								var activeDropNode = activeDrop.get('node');
								var lastActiveDropNode = lastActiveDrop.get('node');

								var quadrant = portalLayout.quadrant;
								var isStatic = activeDropNode.isStatic;

								if (isStatic) {
									var start = (isStatic == 'start');
									var siblingPos = (start ? 'nextSibling' : 'previousSibling');

									var siblingPortlet = Liferay.Layout.findSiblingPortlet(activeDropNode, siblingPos);
									var staticSibling = (siblingPortlet && (siblingPortlet.isStatic == isStatic));

									if (staticSibling ||
										(start && (quadrant <= 2)) ||
										(!start && (quadrant >= 3))) {

										event.halt();
									}
								}

								var isOverColumn = !activeDropNode.drop;

								if (!Layout.OVER_NESTED_PORTLET && isOverColumn) {
									var activeDropNodeId = activeDropNode.get('id');
									var emptyColumn = Layout.EMPTY_COLUMNS[activeDropNodeId];

									if (!emptyColumn) {
										if (activeDropNode != lastActiveDropNode) {
											var referencePortlet = Liferay.Layout.getLastPortletNode(activeDropNode);

											if (referencePortlet.isStatic) {
												var options = Liferay.Layout.options;
												var dropColumn = activeDropNode.one(options.dropContainer);
												var foundReferencePortlet = Liferay.Layout.findReferencePortlet(dropColumn);

												if (foundReferencePortlet) {
													referencePortlet = foundReferencePortlet;
												}
											}

											var drop = DDM.getDrop(referencePortlet);

											if (drop) {
												portalLayout.quadrant = 4;
												portalLayout.activeDrop = drop;
												portalLayout.lastAlignDrop = drop;
											}

											portalLayout._syncPlaceholderUI();
										}

										event.halt();
									}
								}

								if (Layout.OVER_NESTED_PORTLET && (activeDropNode == lastActiveDropNode)) {
									event.halt();
								}
							}
						}
					},
					proxy: {
						resizeFrame: false
					}
				};

				if (options.freeForm) {
					Layout.initFreeFormLayoutHandler();
				}
				else {
					Layout.initColumnLayoutHandler();
				}

				Layout.bindDragDropListeners();

				Layout.updateEmptyColumnsInfo();

				Liferay.on('closePortlet', Layout._onPortletClose);
			},

			bindDragDropListeners: function() {
				var layoutHandler = Layout.layoutHandler;

				layoutHandler.on('drag:end', A.bind(Layout._onPortletDragEnd, Layout));
				layoutHandler.on('drag:start', A.bind(Layout._onPortletDragStart, Layout));
			},

			getLastPortletNode: function(column) {
				var instance = this;

				var portlets = column.all(Liferay.Layout.options.portletBoundary);
				var lastIndex = portlets.size() - 1;

				return portlets.item(lastIndex);
			},

			findIndex: function(node) {
				var options = Layout.options;
				var parentNode = node.get('parentNode');

				return parentNode.all('> ' + options.portletBoundary).indexOf(node);
			},

			findReferencePortlet: function(dropColumn) {
				var portletBoundary = Liferay.Layout.options.portletBoundary;
				var portlets = dropColumn.all('>' + portletBoundary);
				var firstPortlet = portlets.item(0);

				if (firstPortlet) {
					var lastStatic = null;
					var referencePortlet = null;
					var firstPortletStatic = firstPortlet.isStatic;

					if (!firstPortletStatic || (firstPortletStatic == 'end')) {
						referencePortlet = firstPortlet;
					}
					else {
						portlets.each(
							function(item) {
								var isStatic = item.isStatic;

								if (!isStatic ||
									(lastStatic && isStatic && (isStatic != lastStatic))) {
									referencePortlet = item;
								}

								lastStatic = isStatic;
							}
						);
					}
				}

				return referencePortlet;
			},

			findSiblingPortlet: function(portletNode, siblingPos) {
				var dragNodes = Liferay.Layout.options.dragNodes;
				var sibling = portletNode.get(siblingPos);

				while (sibling && !sibling.test(dragNodes)) {
					sibling = sibling.get(siblingPos);
				}

				return sibling;
			},

			fire: function() {
				var layoutHandler = Layout.layoutHandler;

				if (layoutHandler) {
					return layoutHandler.fire.apply(layoutHandler, arguments);
				}
			},

			getPortlets: function() {
				var options = Layout.options;

				return A.all(options.dragNodes);
			},

			hasMoved: function(dragNode) {
				var moved = false;
				var curPortletInfo = Layout.curPortletInfo;

				if (curPortletInfo) {
					var currentIndex = Layout.findIndex(dragNode);
					var currentParent = dragNode.get('parentNode');

					if ((curPortletInfo.originalParent != currentParent) ||
						(curPortletInfo.originalIndex != currentIndex)) {
						moved = true;
					}
				}

				return moved;
			},

			hasPortlets: function(columnNode) {
				var options = Layout.options;

				return !!columnNode.one(options.portletBoundary);
			},

			initColumnLayoutHandler: function() {
				var columnLayoutDefaults = A.merge(
					Layout.DEFAULT_LAYOUT_OPTIONS,
					{
						after: {
							'drag:start': function(event) {
								var node = DDM.activeDrag.get('node');
								var nodeId = node.get('id');

								Layout.PORTLET_TOPPER.html(getTitle(nodeId));
							}
						}
					}
				);

				Layout.layoutHandler = new Layout.ColumnLayout(columnLayoutDefaults);

				Layout.syncDraggableClassUI();
			},

			initFreeFormLayoutHandler: function() {
				var freeformLayoutDefaults = A.merge(
					Layout.DEFAULT_LAYOUT_OPTIONS,
					{
						after: {
							'drag:start': function(event) {
								var instance = this;

								var proxyNode = instance.get('proxyNode');
								var node = DDM.activeDrag.get('node');
								var nodeId = node.get('id');

								proxyNode.one('.portlet-topper').html(getTitle(nodeId));
							}
						},
						delegateConfig: {
							dragConfig: {
								startCentered: false
							}
						},
						lazyStart: false,
						on: {}
					}
				);

				Layout.layoutHandler = new Layout.FreeFormLayout(freeformLayoutDefaults);
			},

			on: function() {
				var layoutHandler = Layout.layoutHandler;

				if (layoutHandler) {
					return layoutHandler.on.apply(layoutHandler, arguments);
				}
			},

			refresh: function(portlet) {
				var layoutHandler = Layout.layoutHandler;

				portlet = A.one(portlet);

				if (portlet) {
					layoutHandler.delegate.syncTargets();

					Liferay.Layout.updatePortletDropZones(portlet);
				}
			},

			saveIndex: function(portletNode, columnNode) {
				var currentColumnId = Liferay.Util.getColumnId(columnNode.get('id'));
				var portletId = Liferay.Util.getPortletId(portletNode.get('id'));
				var position = Layout.findIndex(portletNode);

				if (Layout.hasMoved(portletNode)) {
					Layout.saveLayout(
						{
							cmd: 'move',
							p_p_col_id: currentColumnId,
							p_p_col_pos: position,
							p_p_id: portletId
						}
					);
				}
			},

			saveLayout: function(options) {
				var data = {
					doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
					p_l_id: themeDisplay.getPlid()
				};

				A.mix(data, options);

				A.io.request(
					themeDisplay.getPathMain() + '/portal/update_layout',
					{
						data: data
					}
				);
			},

			syncDraggableClassUI: function() {
				var options = Layout.options;

				if (options.dragNodes) {
					var dragNodes = A.all(options.dragNodes);

					if (options.invalid) {
						dragNodes = dragNodes.filter(':not(' + options.invalid + ')');
					}

					dragNodes.addClass(CSS_DRAGGABLE);
				}
			},

			syncEmptyColumnClassUI: function(columnNode) {
				var options = Layout.options;
				var curPortletInfo = Layout.curPortletInfo;

				if (curPortletInfo) {
					var emptyColumnClass = options.emptyColumnClass;
					var originalParent = curPortletInfo.originalParent;
					var columnHasPortlets = Layout.hasPortlets(columnNode);
					var originalColumnHasPortlets = Layout.hasPortlets(originalParent);

					var dropZoneId = columnNode.ancestor(Layout.options.dropNodes).get('id');
					var originalDropZoneId = originalParent.ancestor(Layout.options.dropNodes).get('id');

					Layout.EMPTY_COLUMNS[dropZoneId] = !columnHasPortlets;
					Layout.EMPTY_COLUMNS[originalDropZoneId] = !originalColumnHasPortlets;

					columnNode.toggleClass(emptyColumnClass, !columnHasPortlets);
					originalParent.toggleClass(emptyColumnClass, !originalColumnHasPortlets);
				}
			},

			updateCurrentPortletInfo: function(dragNode) {
				var options = Layout.options;

				Layout.curPortletInfo = {
					node: dragNode,
					originalIndex: Layout.findIndex(dragNode),
					originalParent: dragNode.get('parentNode'),
					siblingsPortlets: dragNode.siblings(options.portletBoundary)
				};
			},

			updateEmptyColumnsInfo: function() {
				var options = Layout.options;

				A.all(options.dropNodes).each(
					function(item) {
						var columnId = item.get('id');

						Layout.EMPTY_COLUMNS[columnId] = !Layout.hasPortlets(item);
					}
				);
			},

			updateOverNestedPortletInfo: function() {
				var activeDrop = DDM.activeDrop;
				var nestedPortletId = Layout.options.nestedPortletId;

				if (activeDrop) {
					var activeDropNode = activeDrop.get('node');
					var activeDropNodeId = activeDropNode.get('id');

					Layout.OVER_NESTED_PORTLET = (activeDropNodeId.indexOf(nestedPortletId) > -1);
				}
			},

			updatePortletDropZones: function(portletBoundary) {
				var options = Layout.options;
				var portletDropNodes = portletBoundary.all(options.dropNodes);

				portletDropNodes.each(
					function(item) {
						Layout.layoutHandler.addDropNode(item);
					}
				);
			},

			_onPortletClose: function(event) {
				var portlet = event.portlet;
				var portletId = portlet.portletId;
				var column = portlet.ancestor(Layout.options.dropContainer);

				Layout.updateCurrentPortletInfo(portlet);

				if (column) {
					Layout.syncEmptyColumnClassUI(column);
				}
			},

			_onPortletDragEnd: function(event) {
				var dragNode = event.target.get('node');

				var columnNode = dragNode.get('parentNode');

				Layout.saveIndex(dragNode, columnNode);

				Layout.syncEmptyColumnClassUI(columnNode);
			},

			_onPortletDragStart: function(event) {
				var dragNode = event.target.get('node');

				Layout.updateCurrentPortletInfo(dragNode);
			}
		};

		var ColumnLayout = A.Component.create(
			{
				ATTRS: {
					proxyNode: {
						value: Layout.PROXY_NODE
					}
				},

				NAME: 'ColumnLayout',

				EXTENDS: A.PortalLayout,

				prototype: {
					dragItem: 0,

					_positionNode: function(event) {
						var instance = this;

						var portalLayout = event.currentTarget;
						var activeDrop = portalLayout.lastAlignDrop || portalLayout.activeDrop;

						if (activeDrop) {
							var dropNode = activeDrop.get('node');
							var isStatic = dropNode.isStatic;

							if (isStatic) {
								var start = (isStatic == 'start');

								portalLayout.quadrant = (start ? 4 : 1);
							}

							ColumnLayout.superclass._positionNode.apply(this, arguments);
						}
					},

					_syncProxyNodeSize: function() {
						var instance = this;

						var dragNode = DDM.activeDrag.get('dragNode');
						var proxyNode = instance.get('proxyNode');

						if (proxyNode && dragNode) {
							dragNode.set('offsetHeight', 30);
							dragNode.set('offsetWidth', 200);

							proxyNode.set('offsetHeight', 30);
							proxyNode.set('offsetWidth', 200);
						}
					}
				}
			}
		);

		var FreeFormLayout = A.Component.create(
			{
				ATTRS: {
					proxyNode: {
						value: Liferay.Template.PORTLET
					}
				},

				EXTENDS: ColumnLayout,

				NAME: 'FreeFormLayout',

				prototype: {
					portletZIndex: 100,

					initializer: function() {
						var instance = this;

						var placeholder = instance.get('placeholder');

						if (placeholder) {
							placeholder.addClass(Layout.options.freeformPlaceholderClass);
						}

						Layout.getPortlets().each(
							function(item, index, collection) {
								instance._setupNodeResize(item);
								instance._setupNodeStack(item);
							}
						);
					},

					alignPortlet: function(portletNode, referenceNode) {
						var instance = this;

						portletNode.setXY(referenceNode.getXY());

						instance.savePosition(portletNode);
					},

					savePosition: function(portletNode) {
						var portletId = Liferay.Util.getPortletId(portletNode.get('id'));

						Layout.saveLayout(
							{
								cmd: 'drag',
								height: portletNode.getStyle('height'),
								left: portletNode.getStyle('left'),
								p_p_id: portletId,
								top: portletNode.getStyle('top'),
								width: portletNode.getStyle('width')
							}
						);
					},

					_onPortletMouseDown: function(event) {
						var instance = this;

						var portlet = event.currentTarget;

						portlet.setStyle('zIndex', instance.portletZIndex++);
					},

					_positionNode: function(event) {
						var instance = this;

						var activeDrag = DDM.activeDrag;
						var dragNode = activeDrag.get('dragNode');
						var portletNode = activeDrag.get('node');

						var activeDrop = instance.activeDrop;

						if (activeDrop) {
							FreeFormLayout.superclass._positionNode.apply(this, arguments);
						}

						dragNode.setStyle('display', 'block');

						instance.alignPortlet(portletNode, dragNode);

						dragNode.setStyle('display', 'none');
					},

					_setupNodeResize: function(node) {
						var instance = this;

						var resizable = node.hasClass('aui-resize');

						if (!resizable) {
							var resize = new A.Resize(
								{
									after: {
										end: function(event) {
											var info = event.info;

											var portletNode = this.get('node');
											var internalNode = portletNode.one('.portlet');

											if (internalNode) {
												internalNode.set('offsetHeight', info.height);
											}

											instance.savePosition(portletNode);
										}
									},
									handles: 'r,br,b',
									node: node,
									proxy: true
								}
							);
						}
					},

					_setupNodeStack: function(node) {
						var instance = this;

						node.on('mousedown', A.bind(instance._onPortletMouseDown, instance));
					},

					_syncProxyNodeSize: function() {
						var instance = this;

						var node = DDM.activeDrag.get('node');
						var proxyNode = instance.get('proxyNode');

						if (proxyNode) {
							var offsetHeight = node.get('offsetHeight');
							var offsetWidth = node.get('offsetWidth');

							proxyNode.set('offsetHeight', offsetHeight);
							proxyNode.set('offsetWidth', offsetWidth);
						}
					}
				}
			}
		);

		Layout.ColumnLayout = ColumnLayout;
		Layout.FreeFormLayout = FreeFormLayout;
		Liferay.Layout = Layout;
	},
	'',
	{
		requires: ['aui-io-request', 'aui-portal-layout', 'aui-resize', 'dd'],
		use: []
	}
);