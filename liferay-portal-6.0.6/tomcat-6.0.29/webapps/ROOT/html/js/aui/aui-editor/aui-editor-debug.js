AUI.add('aui-editor-base', function(A) {
var Lang = A.Lang,

	NAME = 'editor';

var Editor = A.Component.create(
	{
		NAME: NAME,

		EXTENDS: A.EditorBase,

		ATTRS: {
			toolbarConfig: {
				value: null
			}
		},

		prototype: {
			initializer: function() {
				var instance = this;

				instance.publish(
					'toolbar:ready',
					{
						fireOnce: true
					}
				);

				instance.after(
					'ready',
					function() {
						instance.plug(A.Plugin.EditorToolbar, instance.get('toolbarConfig'));

						instance.fire('toolbar:ready');

						instance.focus();
					}
				);
			},

			addCss: function(url) {
				var instance = this;

				instance.on(
					'toolbar:ready',
					function() {
						var frame = instance.getInstance();

						frame.Get.css(url);
					}
				);
			},

			addGroup: function(group) {
				var instance = this;

				instance.on(
					'toolbar:ready',
					function() {
						instance.toolbar.addGroup(group);
					}
				);
			},

			addGroupType: function(type, data) {
				var instance = this;

				instance.on(
					'toolbar:ready',
					function() {
						instance.toolbar.addGroupType(type, data);
					}
				);
			}			
		}
	}
);

A.Editor = Editor;

}, '1.0.1' ,{requires:['aui-base','editor-base','aui-editor-toolbar-plugin']});
AUI.add('aui-editor-tools-plugin', function(A) {
var Lang = A.Lang,
	UA = A.UA,

	JUSTIFY = 'justify',

	BLOCK_TAGS = {
		div: true,
		h1: true,
		h2: true,
		h3: true,
		h4: true,
		h5: true,
		h6: true,
		p: true
	},

	IGNORE_TAGS = {
		br: true
	},

	ITEM_TAGS = {
		li: true
	},

	TPL_JUSTIFY = '<div style="text-align: {0};">{1}</div>';

function findInsert(item) {
	var found = null;

	var childNodes = item.get('childNodes');

	childNodes.some(
		function(item, index, collection) {
			if (item.get('innerHTML') == '{0}') {
				item.html('');

				found = item;

				return true;
			}

			return findInsert(item);
		}
	);

	return found;
}

function addWrapper(parent, item, html) {
	var wrapper = A.Node.create(html);

	parent.insert(wrapper, item);

	if (wrapper.html() != '') {
		if (wrapper.html() == '{0}') {
			wrapper.html('');
		}
		else {
			var insert = findInsert(wrapper);

			if (insert) {
				wrapper = insert;
			}
		}
	}

	return wrapper;
}

function compareTextContent(innerItem, outerItem) {
	var attr = (UA.ie ? 'innerText' : 'textContent');

	return (innerItem.get(attr) == outerItem.get(attr));
}

var EditorTools = {};

A.mix(
	A.Plugin.ExecCommand.COMMANDS,
	{
		justify: function(cmd, val) {
			var instance = this;

			var host = instance.get('host');
			var frame = host.getInstance();

			var selection = new frame.Selection();
			var items = selection.getSelected();
			var insertHtml = false;

			if (selection.isCollapsed || !items.size()) {
				var anchorTextNode = selection.anchorTextNode;

				items = [anchorTextNode];

				insertHtml = true;
			}

			A.each(
				items,
				function(item, index, collection) {
					var tagName = item.get('tagName');

					if (tagName) {
						tagName = tagName.toLowerCase();
					}

					if (IGNORE_TAGS[tagName]) {
						return;
					}

					if (tagName == 'font') {
						var tempNode = item.get('parentNode');

						if (!tempNode.test('body')) {
							item = tempNode;

							tagName = item.get('tagName').toLowerCase();
						}
					}

					if (!item.test('body') && item.getComputedStyle('textAlign') == val) {
						return;
					}

					var parent = item.get('parentNode');
					var wrapper;

					if (BLOCK_TAGS[tagName] || item.getComputedStyle('display') == 'block') {
						wrapper = item;
					}
					else if (!parent.get('childNodes').item(1) || ITEM_TAGS[tagName]) {
						tagName = parent.get('tagName').toLowerCase();

						if (BLOCK_TAGS[tagName] || parent.getComputedStyle('display') == 'block') {
							wrapper = parent;
						}
					}
					else {
						if (insertHtml) {
							host.execCommand('inserthtml', Lang.sub(TPL_JUSTIFY, [val, frame.Selection.CURSOR]));

							selection.focusCursor(true, true);

							return;
						}
						else {
							wrapper = A.Node.create(Lang.sub(TPL_JUSTIFY, [val, '']));

							parent.insert(wrapper, item);

							wrapper.append(item);
						}
					}

					if (wrapper) {
						wrapper.setStyle('textAlign', val);
					}
				}
			);
		},

		justifycenter: function() {
			var instance = this;

			return instance.get('host').execCommand(JUSTIFY, 'center');
		},

		justifyleft: function() {
			var instance = this;

			return instance.get('host').execCommand(JUSTIFY, 'left');
		},

		justifyright: function() {
			var instance = this;

			return instance.get('host').execCommand(JUSTIFY, 'right');
		},

		subscript: function() {
			var instance = this;

			return instance.get('host').execCommand('wrap', 'sub');
		},

		superscript: function() {
			var instance = this;

			return instance.get('host').execCommand('wrap', 'sup');
		},

		wraphtml: function(cmd, val) {
			var instance = this;

			var host = instance.get('host');
			var frame = host.getInstance();

			var selection = new frame.Selection();
			var items = selection.getSelected();

			if (!selection.isCollapsed && items.size()) {
				var firstInner;
				var firstOuter;
				var lastInner;
				var lastOuter;
				var outerNodes;

				if (items.size() > 1) {
					var itemIndex;
					var itemTotal;

					items.each(
						function(item, index, collection) {
							var parent = item;
							var total = 0;
							var previous;

							while ((parent = parent.ancestor()) && !parent.test('body')) {
								total++;

								previous = parent;
							}

							if (itemTotal == null || total < itemTotal) {
								itemIndex = index;
								itemTotal = total;
							}
						}
					);

					var item = items.item(itemIndex);
					var parent = (item.test('font') ? item.ancestor().ancestor() : item.ancestor());
					var outerIndex = [];

					outerNodes = parent.get('childNodes');

					items.each(
						function(item, index, collection) {
							var parent = item;
							var foundIndex = -1;

							if (item.ancestor().test('body')) {
								foundIndex = index;
							}
							else {
								while ((parent = parent.ancestor()) != null) {
									outerNodes.some(
										function(outerItem, outerIndex, outerCollection) {
											if (outerItem == parent) {
												foundIndex = outerIndex;

												return true;
											}
										}
									);

									if (foundIndex != -1) {
										break;
									}
								}
							}

							outerIndex[index] = foundIndex;
						}
					);

					if (outerIndex.length > 1) {
						var firstIndex;
						var lastIndex;

						for (var i = 0; i < outerIndex.length; i++) {
							if (outerIndex[i] != -1) {
								if (outerIndex[i] < firstIndex || firstIndex == null) {
									firstIndex = outerIndex[i];
									firstInner = items.item(i);
									firstOuter = outerNodes.item(firstIndex);
								}

								if (outerIndex[i] > lastIndex || lastIndex == null) {
									lastIndex = outerIndex[i];
									lastInner = items.item(i);
									lastOuter = outerNodes.item(lastIndex);
								}
							}
							else {
								firstOuter = null;

								break;
							}
						}
					}
				}

				if (firstOuter != null && ((firstOuter == lastOuter) || (compareTextContent(firstInner, firstOuter) && compareTextContent(lastInner, lastOuter)))) {
					var parent = firstOuter.ancestor();

					var wrapper = addWrapper(parent, firstOuter, val);

					for (var i = firstIndex; i <= lastIndex; i++) {
						wrapper.append(outerNodes.item(i));
					}
				}
				else {
					items.each(
						function(item, index, collection) {
							var tagName = item.get('tagName').toLowerCase();

							if (!IGNORE_TAGS[tagName]) {
								var parent = item.ancestor();
	
								var wrapper = addWrapper(parent, item, val);
	
								wrapper.append(item);
							}
						}
					);
				}
			}
			else {
				host.execCommand('inserthtml', Lang.sub(val, [frame.Selection.CURSOR]));

				if (val.indexOf('{0}') != -1) {
					selection.focusCursor(true, true);
				}
			}
		}
	}
);

A.Plugin.EditorTools = EditorTools;

}, '1.0.1' ,{requires:['aui-base','editor-base']});
AUI.add('aui-editor-menu-plugin', function(A) {
var Lang = A.Lang,
	isString = Lang.isString,

	getClassName = A.ClassNameManager.getClassName,

	NAME = 'editormenu',
	NAME_PLUGIN = 'editormenuplugin',

	NS_EDITORMENU = 'menu',

	CONTENT = 'content',

	CSS_MENU_CONTENT_LIST = getClassName(NAME, CONTENT, 'list'),
	CSS_MENU_CONTENT_TEXT = getClassName(NAME, CONTENT, 'text'),
	CSS_MENU_CONTENT_ITEM = getClassName(NAME, CONTENT, 'item'),

	TPL_TAG_EMPTY = '<{1}{2}>{0}</{1}>',

	TPL_MENU_CONTENT_ITEM = '<li class="' + CSS_MENU_CONTENT_ITEM + '">' + TPL_TAG_EMPTY + '</li>',
	TPL_MENU_CONTENT_LIST = '<ul class="' + CSS_MENU_CONTENT_LIST + '"></ul>',
	TPL_MENU_CONTENT_TEXT = '<li class="' + CSS_MENU_CONTENT_TEXT + '"><span>{0}</span></li>';

var EditorMenu = A.Component.create(
	{
		NAME: NAME,

		EXTENDS: A.OverlayContext,

		ATTRS: {
			headerContent: {
				value: '',
				setter: function(value) {
					var instance = this;

					instance._headerContent = value;

					return '';
				}
			},

			host: {
				value: false
			},

			items: {
				value: null
			}
		},

		prototype: {
			renderUI: function() {
				var instance = this;

				EditorMenu.superclass.renderUI.apply(instance, arguments);

				var host = instance.get('host');

				var	contentBox = instance.get('contentBox');
				var headerContent = instance._headerContent;
				var items = instance.get('items');

				var menuList = A.Node.create(TPL_MENU_CONTENT_LIST);

				A.each(
					items,
					function(item, index, collection) {
						var output = '';

						if (isString(item)) {
							output += Lang.sub(TPL_MENU_CONTENT_TEXT, [item]);
						}
						else {
							var attr = instance._generateTagAttr(item);

							output += Lang.sub(TPL_MENU_CONTENT_ITEM, [item.label, item.tag, attr]);
						}

						var node = A.Node.create(output);

						node.setData(NAME, item);

						menuList.append(node);
					}
				);

				var panel = new A.Panel(
					{
						collapsible: false,
						headerContent: headerContent,
						icons: [
							{
								icon: 'close',
								handler: {
									fn: instance.hide,
									context: instance
								}
							}
						]
					}
				).render(contentBox);

				panel.bodyNode.append(menuList);

				instance._menuList = menuList;
			},

			bindUI: function() {
				var instance = this;

				EditorMenu.superclass.bindUI.apply(instance, arguments);

				instance._menuList.delegate('click', instance._onMenuListItemClick, '.' + CSS_MENU_CONTENT_ITEM, instance);
			},

			_generateTagAttr: function(obj) {
				var instance = this;

				var buffer = [];

				var output = '';
				var attributes = obj.attributes;
				var styles = obj.styles;

				if (attributes) {
					for (var i in attributes) {
						buffer.push(' ' + i + '="' + attributes[i] + '"');
					}
				}

				if (styles) {
					buffer = [' style="'];

					for (var i in styles) {
						buffer.push(i + ': ' + styles[i] + ';');
					}

					buffer.push('"');
				}

				return buffer.join('');
			},

			_onMenuListItemClick: function(event) {
				var instance = this;

				var listItem = event.currentTarget;
				var	config = listItem.getData(NAME);

				var attr = instance._generateTagAttr(config);
				var output = Lang.sub(TPL_TAG_EMPTY, ['{0}', config.tag, attr]);

				var host = instance.get('host');

				host.execCommand('wraphtml', output);
				host.focus();

				instance.hide();
			},

			_uiSetHeight: function(val) {
				var instance = this;

				var	boundingBox = instance.get('boundingBox');
				var menuList = instance._menuList;

				boundingBox.setStyle('height', 'auto');

				menuList.setStyle('height', val);
			},

			_uiSetWidth: function(val) {
				var instance = this;

				var	boundingBox = instance.get('boundingBox');
				var menuList = instance._menuList;

				boundingBox.setStyle('width', 'auto');

				menuList.setStyle('width', val);
			}
		}
	}
);

var EditorMenuPlugin = A.Component.create(
	{
		EXTENDS: A.Plugin.Base,

		NAME: NAME_PLUGIN,

		NS: NS_EDITORMENU,

		prototype: {
			add: function(config) {
				var instance = this;

				var host = instance.get('host');

				return new EditorMenu(
					A.mix(
						{
							host: host
						},
						config
					)
				).render();
			}
		}
	}
);

A.namespace('Plugin').EditorMenu = EditorMenuPlugin;

}, '1.0.1' ,{requires:['aui-base','editor-base','aui-overlay-context','aui-panel','aui-editor-tools-plugin']});
AUI.add('aui-editor-toolbar-plugin', function(A) {
var Lang = A.Lang,
	isArray = Lang.isArray,
	isFunction = Lang.isFunction,

	getClassName = A.ClassNameManager.getClassName,

	NAME = 'editortoolbar',
	TOOLBAR_PLUGIN = 'toolbar',

	ALIGNMENT = 'alignment',
	ALIGN_LEFT = 'align-left',
	ALIGN_INLINE = 'align-inline',
	ALIGN_BLOCK = 'align-block',
	ALIGN_RIGHT = 'align-right',
	COLOR = 'color',
	CONTENT = 'content',
	FONT = 'font',
	INDENT = 'indent',
	INPUT = 'input',
	INSERT = 'insert',
	INSERTIMAGE = 'insertimage',
	INSERTLINK = 'insertlink',
	LIST = 'list',
	SELECT = 'select',
	SOURCE = 'source',
	STYLES = 'styles',
	SUBSCRIPT = 'subscript',
	TEXT = 'text',

	ICON_CHECK = 'circle-check',
	ICON_CLOSE = 'close',

	CMD_IGNORE = {
		backcolor: true,
		forecolor: true,
		format: true,
		insertimage: true,
		insertlink: true,
		source: true,
		styles: true
	},

	CMD_FORMAT = [
		'b',
		'big',
		'font',
		'em',
		'i',
		'small',
		's',
		'strike',
		'strong',
		'sub',
		'sup',
		'u'
	],

	CSS_BUTTON_HOLDER = getClassName('button', 'holder'),
	CSS_FIELD_INPUT = getClassName('field', INPUT),
	CSS_FIELD_INPUT_TEXT = getClassName('field', INPUT, 'text'),
	CSS_FIELD_LABEL = getClassName('field', 'label'),
	CSS_FIELD_NUMERIC = getClassName('field', 'numeric'),
	CSS_ALIGN_NODE = getClassName(NAME, 'align', 'node'),
	CSS_INSERTIMAGE = getClassName(NAME, INSERTIMAGE),
	CSS_INSERTLINK = getClassName(NAME, INSERTLINK),
	CSS_SELECT_FONTNAME = getClassName(NAME, SELECT, 'fontname'),
	CSS_SELECT_FONTSIZE = getClassName(NAME, SELECT, 'fontsize'),
	CSS_SIZE_SEPARATOR = getClassName(NAME, 'size', 'separator'),
	CSS_SOURCE_TEXTAREA = getClassName(NAME, SOURCE, 'textarea'),
	CSS_STATE_ACTIVE = getClassName('state', 'active'),
	CSS_TOOLBAR = getClassName(NAME),
	CSS_TOOLBAR_CONTENT = getClassName(NAME, CONTENT),

	TPL_ALIGN_NODE = '<div class="' + CSS_ALIGN_NODE + '"></div>',
	TPL_INSERTIMAGE_HREF = '<a></a>',
	TPL_INSERTIMAGE_IMG = '<img />',
	TPL_INSERTLINK = '<a href="{0}"{2}>{1}</a>',

	TPL_SOURCE_TEXTAREA = '<textarea class="' + CSS_SOURCE_TEXTAREA + '"></textarea>',

	TPL_TOOLBAR = '<div class="' + CSS_TOOLBAR + '"><div class="' + CSS_TOOLBAR_CONTENT + '"></div></div>',

	TPL_TOOLBAR_BUTTON_HOLDER = '<div class="' + CSS_BUTTON_HOLDER + '"></div>',

	TPL_TOOLBAR_FONTNAME = '<select class="' + CSS_SELECT_FONTNAME + '">{0}</select>',

	TPL_TOOLBAR_FONTNAME_OPTION = '<option selected="selected"></option>' +
								'<option>Arial</option>' +
								'<option>Arial Black</option>' +
								'<option>Comic Sans MS</option>' +
								'<option>Courier New</option>' +
								'<option>Lucida Console</option>' +
								'<option>Tahoma</option>' +
								'<option>Times New Roman</option>' +
								'<option>Trebuchet MS</option>' +
								'<option>Verdana</option>',

	TPL_TOOLBAR_FONTSIZE = '<select class="' + CSS_SELECT_FONTSIZE + '">{0}</select>',

	TPL_TOOLBAR_FONTSIZE_OPTION = '<option selected="selected"></option>' +
								'<option value="1">10</option>' +
								'<option value="2">13</option>' +
								'<option value="3">16</option>' +
								'<option value="4">18</option>' +
								'<option value="5">24</option>' +
								'<option value="6">32</option>' +
								'<option value="7">48</option>',

	TPL_TOOLBAR_SIZE_SEPARATOR = '<span class="' + CSS_SIZE_SEPARATOR + '">x</span>';

var EditorToolbar = A.Component.create(
	{
		NAME: NAME,

		NS: TOOLBAR_PLUGIN,

		EXTENDS: A.Plugin.Base,

		ATTRS: {
			append: {
				value: null
			},
			groups: {
				value: [
					{
						type: FONT
					},
					{
						type: TEXT
					},
					{
						type: COLOR
					},
					{
						type: ALIGNMENT
					},
					{
						type: INDENT
					},
					{
						type: LIST
					},
					{
						type: INSERT
					},
					{
						type: SOURCE
					}
				]
			}
		},

		prototype: {
			initializer: function() {
				var instance = this;

				var host = instance.get('host');
				var container = host.frame.get('container');
				var append = instance.get('append');
				var groups = instance.get('groups');

				var boundingBox = A.Node.create(TPL_TOOLBAR);
				var contentBox = boundingBox.one('.' + CSS_TOOLBAR_CONTENT);

				container.placeBefore(boundingBox);

				instance._boundingBox = boundingBox;
				instance._contentBox = contentBox;

				var toolbars = [];

				if (append != null && isArray(append)) {
					var appendLength = append.length;

					for (var i = 0; i < appendLength; i++) {
						var item = append[i];

						if (item.index != null) {
							var index = instance._isGroupIncluded('type', groups, item.type);

							if (index != -1) {
								groups.splice(index, 1);
							}

							groups.splice(Math.min(item.index, groups.length), 0, item);
						}
						else {
							groups.push(item);
						}
					}
				}

				for (var i = 0; i < groups.length; i++) {
					var group = groups[i];
					var groupType = GROUPS[group.type] || group;
					var buttons = [];

					if (isArray(group.include)) {
						var groupLength = group.include.length;

						for (var j = 0; j < groupLength; j++) {
							var index = instance._isGroupIncluded('icon', groupType.children, group.include[j]);

							if (index != -1) {
								buttons.push(groupType.children[index]);
							}
						}
					}
					else {
						buttons = groupType.children;
					}

					var toolbar = instance._addGroup(group, groupType, buttons);

					if (toolbar) {
						toolbars.push(toolbar);
					}
				}

				instance._toolbars = toolbars;

				contentBox.delegate(
					'click',
					function(event) {
						var instance = this;

						var node = A.Widget.getByNode(event.currentTarget);

						if (node) {
							var cmds = node.get('icon').split('-');

							if (!CMD_IGNORE[cmds[0]]) {
								instance.execCommand(cmds[0], (cmds[1] ? cmds[1] : ''));
								instance.focus();
							}
						}
					},
					'button',
					host
				);
			},

			addGroup: function(group) {
				var instance = this;

				var groupType = GROUPS[group.type] || group;

				instance._addGroup(group, groupType);
			},

			addGroupType: function(type, data) {
				var instance = this;

				if (!GROUPS[type]) {
					GROUPS[type] = data;
				}
			},

			_addGroup: function(group, groupType, buttons) {
				var instance = this;

				var insert = (buttons == null && group.index != null);
				buttons = buttons || groupType.children;

				if (isArray(buttons)) {
					var host = instance.get('host');
					var contentBox = instance._contentBox;
					var children = [];
					var toolbar;

					var attrs = {
						boundingBox: instance._boundingBox,
						contentBox: contentBox
					};

					for (var i = 0; i < buttons.length; i++) {
						var button = buttons[i];

						if (!button.select) {
							var title = YUI.AUI.defaults.EditorToolbar.STRINGS[button._titleKey];

							button.title = (title != null ? title : EditorToolbarStrings[button._titleKey]);

							children.push(button);
						}
					}

					if (children.length) {
						toolbar = new A.Toolbar(
							A.merge(
								groupType.config,
								group.toolbar,
								{
									children: children
								}
							)
						).render(contentBox);
					}

					var generate = groupType.generate;

					if (generate && isFunction(generate.init)) {
						generate.init.call(instance, host, attrs);
					}

					children = (children.length ? children : buttons);

					for (var i = 0; i < children.length; i++) {
						var item = children[i];
						var icon = item.icon;

						if (generate && isFunction(generate[icon])) {
							var config = (group.config ? group.config[icon] : null);

							attrs.button = (item.select || !toolbar ? null : toolbar.item(i));

							generate[icon].call(instance, host, attrs, config);
						}
					}

					if (insert) {
						var nodes = contentBox.get('childNodes');
						var nodesLength = nodes.size();
						var index = group.index;

						if (index < nodesLength - 1) {
							contentBox.insert(nodes.item(nodesLength - 1), nodes.item(index));
						}
					}

					return toolbar;
				}
			},

			_isGroupIncluded: function(name, children, type) {
				var instance = this;

				for (var i = 0; i < children.length; i++) {
					if (children[i][name] == type) {
						return i;
					}
				}

				return -1;
			},

			_updateToolbar: function(event, attrs) {
				var instance = this;

				if (event.changedNode) {
					var cmds = event.commands;
					var toolbars = instance.toolbars;

					var toolbarIterator = function(item, index, collection) {
						var state = !!(cmds[item.get('icon')]);

						item.StateInteraction.set('active', state);
					};

					if (toolbars) {
						for (var i = 0; i < toolbars.length; i++) {
							toolbars[i].each(toolbarIterator);
						}
					}

					var fontName = event.fontFamily;
					var fontNameOptions = attrs._fontNameOptions;
					var fontSize = event.fontSize;
					var fontSizeOptions = attrs._fontSizeOptions;

					if (fontNameOptions) {
						fontNameOptions.item(0).set('selected', true);

						fontNameOptions.each(
							function(item, index, collection) {
								var val = item.get('value').toLowerCase();

								if (val === fontName.toLowerCase()) {
									item.set('selected', true);
								}
							}
						);
					}

					if (fontSizeOptions) {
						fontSize = fontSize.replace('px', '');

						fontSizeOptions.item(0).set('selected', true);

						fontSizeOptions.each(
							function(item, index, collection) {
								var val = item.get('value').toLowerCase();
								var txt = item.get('text');

								if (txt === fontSize) {
									item.set('selected', true);
								}
							}
						);
					}
				}
			}
		}
	}
);

function selectFontCommand(event) {
	var instance = this;

	var target = event.currentTarget;
	var css = target.get('className');
	var cmd = css.substring(css.lastIndexOf('-') + 1);
	var val = target.get('value');

	instance.execCommand(cmd, val);
	instance.focus();
}

EditorToolbar.generateOverlay = function(trigger, config, panel) {
	var overlay = new A['OverlayContext' + (panel ? 'Panel' : '')] (
		A.merge(
			{
				align: {
					node: trigger,
					points: [ 'tl', 'bl' ]
				},
				hideOn: 'click',
				showOn: 'click',
				trigger: trigger
			},
			config
		)
	).render();

	return overlay;
};

EditorToolbar.generateColorPicker = function(editor, attrs, config, cmd) {
	var button = attrs.button;
	var boundingBox = button.get('boundingBox');

	var colorPicker = new A.ColorPicker(
		A.merge(
			{
				align: {
					node: boundingBox,
					points: ['tl', 'bl']
				},
				trigger: boundingBox
			},
			config
		)
	);

	if (config && config.plugins) {
		for (var i = 0; i < config.plugins.length; i++) {
			colorPicker.plug(config.plugins[i], config);
		}
	}

	colorPicker.render(A.getBody());

	colorPicker.on(
		'colorChange',
		function(event) {
			var instance = this;

			var rgb = colorPicker.get('rgb');

			editor.execCommand(cmd, rgb.hex);
			editor.focus();
		}
	);
};

EditorToolbar.openOverlayToAlignNode = function(overlay, alignNode, iframe, iframeNode) {
	var xy = iframe.getXY();
	var xyNode = iframeNode.getXY();

	xy = [xy[0] + xyNode[0], xy[1] + xyNode[1]];

	alignNode.setStyle('width', iframeNode.get('offsetWidth'));
	alignNode.setStyle('height', iframeNode.get('offsetHeight'));
	alignNode.setXY(xy);

	alignNode.show();

	overlay.set(
		'align',
		{
			node: alignNode,
			points: [ 'tl', 'bc' ]
		}
	);

	overlay.show();
};

var EditorToolbarStrings = {
	ALIGN: 'Align',
	ALIGN_BLOCK: 'Block',
	ALIGN_LEFT: 'Left',
	ALIGN_INLINE: 'Inline',
	ALIGN_RIGHT: 'Right',
	BACKCOLOR: 'Background Color',
	BOLD: 'Bold',
	BORDER: 'Border',
	DESCRIPTION: 'Description',
	EDIT_IMAGE: 'Edit Image',
	EDIT_LINK: 'Edit Link',
	FORECOLOR: 'Foreground Color',
	IMAGE_URL: 'Image URL',
	INDENT: 'Indent',
	INSERT: 'Insert',
	INSERT_IMAGE: 'Insert Image',
	INSERT_LINK: 'Insert Link',
	INSERT_ORDERED_LIST: 'Insert Numbered List',
	INSERT_UNORDERED_LIST: 'Insert Bulleted List',
	ITALIC: 'Italic',
	JUSTIFY_LEFT: 'Justify Left',
	JUSTIFY_CENTER: 'Justify Center',
	JUSTIFY_RIGHT: 'Justify Right',
	LINE_THROUGH: 'Line Through',
	LINK_URL: 'Link URL',
	OPEN_IN_NEW_WINDOW: 'Open in new window',
	OUTDENT: 'Outdent',
	PADDING: 'Padding',
	REMOVE_FORMAT: 'Format Source',
	SAVE: 'Save',
	SIZE: 'Size',
	SOURCE: 'Source',
	SUBSCRIPT: 'Subscript',
	SUPERSCRIPT: 'Superscript',
	STYLES: 'Styles',
	UNDERLINE: 'Underline'
};

if (!YUI.AUI.defaults.EditorToolbar) {
	YUI.AUI.defaults.EditorToolbar = {
		STRINGS: {}
	};
}

A.mix(YUI.AUI.defaults.EditorToolbar.STRINGS, EditorToolbarStrings);

var GROUPS = {};

GROUPS[ALIGNMENT] = {
	children: [
		{
			icon: 'justifyleft',
			_titleKey: 'JUSTIFY_LEFT'
		},
		{
			icon: 'justifycenter',
			_titleKey: 'JUSTIFY_CENTER'
		},
		{
			icon: 'justifyright',
			_titleKey: 'JUSTIFY_RIGHT'
		}
	]
};

GROUPS[COLOR] = {
	children: [
		{
			icon: 'forecolor',
			_titleKey: 'FORECOLOR'
		},
		{
			icon: 'backcolor',
			_titleKey: 'BACKCOLOR'
		}
	],
	generate: {
		forecolor: function(editor, attrs, config) {
			var instance = this;

			EditorToolbar.generateColorPicker(editor, attrs, config, 'forecolor');
		},

		backcolor: function(editor, attrs, config) {
			var instance = this;

			EditorToolbar.generateColorPicker(editor, attrs, config, 'backcolor');
		}
	}
};

GROUPS[FONT] = {
	children: [
		{
			icon: 'fontname',
			select: true
		},
		{
			icon: 'fontsize',
			select: true
		}
	],

	generate: {
		init: function(editor, attrs) {
			var instance = this;

			var contentBox = attrs.contentBox;

			editor.after(
				'nodeChange',
				function(event) {
					var instance = this;

					switch (event.changedType) {
						case 'keyup':
						case 'mousedown':
							instance._updateToolbar(event, attrs);
						break;
					}
				},
				instance
			);
		},

		fontname: function(editor, attrs, config) {
			var instance = this;

			var contentBox = attrs.contentBox;

			var node;
			var data = [TPL_TOOLBAR_FONTNAME_OPTION];

			if (config && config.optionHtml) {
				data[0] = config.optionHtml;
			}

			node = A.Node.create(Lang.sub(TPL_TOOLBAR_FONTNAME, data));

			contentBox.append(node);

			node.on(
				'change',
				selectFontCommand,
				editor
			);

			var options = contentBox.all('.' + CSS_SELECT_FONTNAME + ' option');

			attrs._fontNameOptions = options;

			return node;
		},

		fontsize: function(editor, attrs, config) {
			var instance = this;

			var contentBox = attrs.contentBox;

			var node;
			var data = [TPL_TOOLBAR_FONTSIZE_OPTION];

			if (config && config.optionHtml) {
				data[0] = config.optionHtml;
			}

			node = A.Node.create(Lang.sub(TPL_TOOLBAR_FONTSIZE, data));

			contentBox.append(node);

			node.on(
				'change',
				selectFontCommand,
				editor
			);

			var options = contentBox.all('.' + CSS_SELECT_FONTSIZE + ' option');

			attrs._fontSizeOptions = options;

			return node;
		}
	}
};

GROUPS[INDENT] = {
	children: [
		{
			icon: 'indent',
			_titleKey: 'INDENT'
		},
		{
			icon: 'outdent',
			_titleKey: 'OUTDENT'
		}
	]
};

GROUPS[INSERT] = {
	children: [
		{
			icon: 'insertimage',
			_titleKey: 'INSERT_IMAGE'
		},
		{
			icon: 'insertlink',
			_titleKey: 'INSERT_LINK'
		}
	],
	generate: {
		insertimage: function(editor, attrs, config) {
			var instance = this;

			var button = attrs.button;
			var boundingBox = button.get('boundingBox');

			var overlay = EditorToolbar.generateOverlay(boundingBox, config, true);

			var contextBox = overlay.get('contentBox');

			var panel = new A.Panel(
				{
					collapsible: false,
					title: YUI.AUI.defaults.EditorToolbar.STRINGS.INSERT_IMAGE,
					icons: [
						{
							icon: ICON_CLOSE,
							handler: {
								fn: overlay.hide,
								context: overlay
							}
						}
					]
				}
			).render(contextBox);

			contextBox = panel.bodyNode;

			if (config && config.dataBrowser) {
				config.dataBrowser.render(contextBox);
			}
			else {
				var editNode;
				var selection;

				var imageForm = new A.Form(
					{
						cssClass: CSS_INSERTIMAGE,
						labelAlign: 'left'
					}
				).render(contextBox);

				var borderOptions = [
					{
						labelText: 'none',
						value: 'none'
					}
				];

				for (var i = 1; i < 6; i++) {
					borderOptions.push(
						{
							labelText: i + 'px',
							value: i + 'px solid'
						}
					);
				}

				imageForm.add(
					[
						{
							id: 'imageURL',
							labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.IMAGE_URL
						},
						{
							id: 'size',
							labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.SIZE,
							type: 'hidden'
						},
						{
							id: 'width',
							labelText: false,
							cssClass: CSS_FIELD_NUMERIC
						},
						{
							id: 'height',
							labelText: false,
							cssClass: CSS_FIELD_NUMERIC
						},
						{
							id: 'padding',
							labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.PADDING
						},
						new A.Select(
							{
								id: 'border',
								labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.BORDER,
								options: borderOptions
							}
						),
						{
							id: 'align',
							labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.ALIGN,
							type: 'hidden'
						},
						{
							id: 'description',
							labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.DESCRIPTION
						},
						{
							id: 'linkURL',
							labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.LINK_URL
						},
						{
							id: 'openInNewWindow',
							labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.OPEN_IN_NEW_WINDOW,
							type: 'checkbox'
						}
					],
					true
				);

				imageForm.getField('width').get('boundingBox').placeAfter(TPL_TOOLBAR_SIZE_SEPARATOR);

				var imageFormContentBox = imageForm.get('contentBox');
				var buttonRow = A.Node.create(TPL_TOOLBAR_BUTTON_HOLDER);

				var hrefTarget = imageForm.getField('openInNewWindow');

				var insertButton = new A.ButtonItem(
					{
						icon: ICON_CHECK,
						label: YUI.AUI.defaults.EditorToolbar.STRINGS.INSERT
					}
				).render(buttonRow);

				insertButton.on(
					'click',
					function(event) {
						var instance = this;

						var href;
						var img;
						var parent;

						if (editNode) {
							img = editNode;
							parent = editNode.get('parentNode');

							if (parent.get('tagName').toLowerCase() == 'a') {
								href = parent;
							}
						}
						else {
							img = A.Node.create(TPL_INSERTIMAGE_IMG);
						}

						var fieldValues = imageForm.get('fieldValues');

						var description = fieldValues.description;

						var imgAttrs = {
							src: fieldValues.imageURL,
							title: description,
							alt: description
						};

						var imgStyles = {
							border: fieldValues.border
						};

						var height = parseInt(fieldValues.height, 10);
						var width = parseInt(fieldValues.width, 10);

						if (!isNaN(height)) {
							imgAttrs.height = height;
						}

						if (!isNaN(width)) {
							imgAttrs.width = width;
						}

						var padding = parseInt(fieldValues.padding, 10);

						if (!isNaN(padding)) {
							imgStyles.padding = padding;
						}
 
						toolbarAlign.some(
							function(item, index, collection) {
								var instance = this;

								var active = item.StateInteraction.get('active');

								if (active) {
									imgStyles.display = '';

									switch(index) {
										case 0:
											imgAttrs.align = 'left';
										break;

										case 1:
											imgAttrs.align = '';
										break;

										case 2:
											imgAttrs.align = 'center';
											imgStyles.display = 'block';
										break;

										case 3:
											imgAttrs.align = 'right';
										break;
									}

									return true;
								}
							}
						);

						img.setAttrs(imgAttrs);
						img.setStyles(imgStyles);

						var linkURL = fieldValues.linkURL;

						if (linkURL) {
							if (!href) {
								href = A.Node.create(TPL_INSERTIMAGE_HREF);

								if (editNode) {
									parent.insert(href, editNode);
								}

								href.append(img);
							}

							href.setAttribute('href', linkURL);
							href.setAttribute('target', (hrefTarget.get('node').get('checked') ? '_blank' : ''));

							img = href;
						}
						else {
							if (editNode && href) {
								parent.insert(editNode, href);

								href.remove(true);
							}
						}

						if (!editNode && selection && selection.anchorNode) {
							selection.anchorNode.append(img);
						}

						overlay.hide();
					}
				);
				
				var imgSizeDetection = A.Node.create(TPL_INSERTIMAGE_IMG);

				var heightField = imageForm.getField('height');
				var widthField = imageForm.getField('width');

				imgSizeDetection.on(
					'load',
					function(event) {
						var img = event.currentTarget;

						if (!heightField.get('value') || !widthField.get('value')) {
							imageForm.set(
								'values',
								{
									height: img.get('height'),
									width: img.get('width')
								}
							);
						}
					}
				);

				imageForm.getField('imageURL').get('node').on(
					'blur',
					function(event) {
						imgSizeDetection.set('src', this.val());
					}
				);

				imageFormContentBox.append(buttonRow);

				var toolbarAlign = new A.Toolbar(
					{
						activeState: true,
						children: [
							{
								icon: ALIGN_LEFT,
								title: YUI.AUI.defaults.EditorToolbar.STRINGS.ALIGN_LEFT
							},
							{
								icon: ALIGN_INLINE,
								title: YUI.AUI.defaults.EditorToolbar.STRINGS.ALIGN_INLINE
							},
							{
								icon: ALIGN_BLOCK,
								title: YUI.AUI.defaults.EditorToolbar.STRINGS.ALIGN_BLOCK
							},
							{
								icon: ALIGN_RIGHT,
								title: YUI.AUI.defaults.EditorToolbar.STRINGS.ALIGN_RIGHT
							}
						]
					}
				);

				toolbarAlign.after(
					'buttonitem:click',
					function(event) {
						var button = event.target;

						toolbarAlign.each(
							function(item, index, collection) {
								if (item != button) {
									item.StateInteraction.set('active', false);
								}
							}
						);
					}
				);

				toolbarAlign.render(imageForm.getField('align').get('contentBox'));

				overlay.on(
					'show',
					function(event) {
						if (!selection || !selection.anchorNode) {
							var frame = editor.getInstance();

							editor.focus();

							selection = new frame.Selection();
						}
					}
				);

				overlay.after(
					'hide',
					function(event) {
						imageForm.resetValues();

						toolbarAlign.each(
							function(item, index, collection) {
								item.StateInteraction.set('active', false);
							}
						);

						hrefTarget.get('node').set('checked', false);

						panel.set('title', YUI.AUI.defaults.EditorToolbar.STRINGS.INSERT_IMAGE);
						insertButton.set('label', YUI.AUI.defaults.EditorToolbar.STRINGS.INSERT);

						alignNode.hide();

						overlay.set(
							'align',
							{
								node: boundingBox,
								points: [ 'tl', 'bl' ]
							}
						);

						editNode = null;
					}
				);

				var alignNode = A.Node.create(TPL_ALIGN_NODE);

				alignNode.hide();

				A.getBody().append(alignNode);

				editor.on(
					'toolbar:ready',
					function() {
						var iframe = editor.frame._iframe;
						var frame = editor.getInstance();

						iframe.on(
							'mouseout',
							function(event) {
								selection = new frame.Selection();
							}
						);

						frame.one('body').delegate(
							'click',
							function(event) {
								var instance = this;

								if (editNode != event.currentTarget) {
									var img = event.currentTarget;

									var parent = img.get('parentNode');
									var borderWidth = img.getStyle('borderWidth');
									var padding = img.getStyle('padding');
									var linkTag = (parent.get('tagName').toLowerCase() == 'a');

									imageForm.set(
										'values',
										{
											border: (borderWidth ? borderWidth + ' solid' : ''),
											description: img.get('alt'),
											height: img.get('height'),
											imageURL: img.get('src'),
											linkURL: (linkTag ? parent.get('href') : ''),
											width: img.get('width'),
											padding: (padding ? parseInt(padding) : '')
										}
									);

									var index = 1;

									switch (img.getAttribute('align')) {
										case 'left':
											index = 0;
										break;
										case 'center':
											index = 2;
										break;
										case 'right':
											index = 3;
										break;
									}

									toolbarAlign.item(index).StateInteraction.set('active', true);

									hrefTarget.get('node').attr('checked', (linkTag && parent.getAttribute('target') == '_blank'));

									panel.set('title', YUI.AUI.defaults.EditorToolbar.STRINGS.EDIT_IMAGE);
									insertButton.set('label', YUI.AUI.defaults.EditorToolbar.STRINGS.SAVE);

									editNode = img;

									EditorToolbar.openOverlayToAlignNode(overlay, alignNode, iframe, img);
								}
							},
							'img'
						);
					}
				);
			}
		},

		insertlink: function(editor, attrs, config) {
			var instance = this;

			var button = attrs.button;
			var boundingBox = button.get('boundingBox');

			var overlay = EditorToolbar.generateOverlay(boundingBox, config, true);
			var contextBox = overlay.get('contentBox');

			var panel = new A.Panel(
				{
					collapsible: false,
					title: YUI.AUI.defaults.EditorToolbar.STRINGS.INSERT_LINK,
					icons: [
						{
							icon: ICON_CLOSE,
							handler: {
								fn: overlay.hide,
								context: overlay
							}
						}
					]
				}
			).render(contextBox);

			contextBox = panel.bodyNode;

			var iframe = editor.frame._iframe;
			var editNode;
			var selection;

			var linkForm = new A.Form(
				{
					cssClass: CSS_INSERTLINK,
					labelAlign: 'left'
				}
			).render(contextBox);

			linkForm.add(
				[
					{
						id: 'description',
						labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.DESCRIPTION
					},
					{
						id: 'linkURL',
						labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.LINK_URL
					},
					{
						id: 'openInNewWindow',
						labelText: YUI.AUI.defaults.EditorToolbar.STRINGS.OPEN_IN_NEW_WINDOW,
						type: 'checkbox'
					}
				],
				true
			);

			var linkFormContentBox = linkForm.get('contentBox');
			var buttonRow = A.Node.create(TPL_TOOLBAR_BUTTON_HOLDER);

			var hrefTarget = linkForm.getField('openInNewWindow');

			var insertButton = new A.ButtonItem(
				{
					icon: ICON_CHECK,
					label: YUI.AUI.defaults.EditorToolbar.STRINGS.INSERT
				}
			).render(buttonRow);

			insertButton.on(
				'click',
				function(event) {
					var instance = this;

					var fieldValues = linkForm.get('fieldValues');

					if (editNode) {
						editNode.setAttribute('href', fieldValues.linkURL);
						editNode.set('innerHTML', fieldValues.description);

						if (hrefTarget.get('node').get('checked')) {
							editNode.setAttribute('target', '_blank');
						}
						else {
							editNode.setAttribute('target', '');
						}
					}
					else {
						editor.execCommand('inserthtml', Lang.sub(TPL_INSERTLINK, [fieldValues.linkURL, fieldValues.description, (hrefTarget.get('node').get('checked') ? ' target="_blank"' : '')]));
					}

					overlay.hide();
				}
			);

			linkFormContentBox.append(buttonRow);

			overlay.after(
				'hide',
				function(event) {
					linkForm.resetValues();

					hrefTarget.get('node').set('checked', false);

					panel.set('title', YUI.AUI.defaults.EditorToolbar.STRINGS.CREATE_LINK);
					insertButton.set('label', YUI.AUI.defaults.EditorToolbar.STRINGS.INSERT);

					alignNode.hide();

					overlay.set(
						'align',
						{
							node: boundingBox,
							points: [ 'tl', 'bl' ]
						}
					);

					editNode = null;
				}
			);

			var alignNode = A.Node.create(TPL_ALIGN_NODE);

			alignNode.hide();

			A.getBody().append(alignNode);

			editor.on(
				'toolbar:ready',
				function() {
					var frame = editor.getInstance();

					frame.one('body').delegate(
						'click',
						function(event) {
							var instance = this;

							if (editNode != event.currentTarget) {
								var link = event.currentTarget;

								if (!link.one('img')) {
									var parent = link.get('parentNode');

									linkForm.set(
										'values',
										{
											description: link.get('innerHTML'),
											linkURL: link.getAttribute('href'),
										}
									);

									hrefTarget.get('node').attr('checked', (link.getAttribute('target') == '_blank'));

									panel.set('title', YUI.AUI.defaults.EditorToolbar.STRINGS.EDIT_LINK);
									insertButton.set('label', YUI.AUI.defaults.EditorToolbar.STRINGS.SAVE);

									editNode = link;

									EditorToolbar.openOverlayToAlignNode(overlay, alignNode, iframe, link);
								}
							}
						},
						'a'
					);
				}
			);
		}
	}
};

GROUPS[LIST] = {
	children: [
		{
			icon: 'insertunorderedlist',
			_titleKey: 'INSERT_UNORDERED_LIST'
		},
		{
			icon: 'insertorderedlist',
			_titleKey: 'INSERT_ORDERED_LIST'
		}
	],
	generate: {
		init: function(editor) {
			var instance = this;

			editor.plug(A.Plugin.EditorLists);
		}
	}
};

GROUPS[SOURCE] = {
	children: [
		{
			icon: 'format',
			_titleKey: 'REMOVE_FORMAT'
		},
		{
			icon: 'source',
			_titleKey: 'SOURCE'
		}
	],
	generate: {
		format: function(editor, attrs, config) {
			var instance = this;

			var button = attrs.button;

			button.on(
				'click',
				function(event) {
					var instance = this;

					var frame = instance.getInstance();

					var selection = new frame.Selection();
					var items = selection.getSelected();

					if (!selection.isCollapsed && items.size()) {
						items.each(
							function(item, index, collection) {
								var instance = this;

								item.removeAttribute('style');

								var html = item.get('innerHTML');

								html = html.replace(/<([a-zA-Z0-9]*)\b[^>]*>/g, '<$1>');

								for (var i = 0; i < CMD_FORMAT.length; i++) {
									var regExp = new RegExp('(<' + CMD_FORMAT[i] + '>|<\\/' + CMD_FORMAT[i] + '>)', 'ig');

									html = html.replace(regExp, '');
								}

								item.set('innerHTML', html);

								var parent = item.get('parentNode');

								if (!parent.test('body')) {
									parent.removeAttribute('style');
								}
							}
						);
					}
				},
				editor
			);
		},

		source: function(editor, attrs, config) {
			var instance = this;

			var contentBox = attrs.contentBox;
			var button = attrs.button;
			var frame;

			var textarea = A.Node.create(TPL_SOURCE_TEXTAREA);

			editor.on(
				'toolbar:ready',
				function() {
					var instance = this;

					var frame = editor.frame;
					var container = frame.get('container');

					textarea.hide();

					container.append(textarea);
				}
			);

			button._visible = false;

			button.on(
				'click',
				function(event) {
					var instance = this;

					var frame = editor.frame;
					var buttonVisible = button._visible;

					if (buttonVisible) {
						instance.set('content', textarea.val());

						textarea.hide();
						textarea.val('');

						frame.show();
					}
					else {
						var iframe = frame._iframe;

						textarea.val(instance.getContent());

						var offsetHeight = iframe.get('offsetHeight') - textarea.getPadding('tb');

						textarea.setStyle('height', offsetHeight);

						frame.hide();

						textarea.show();
					}

					buttonVisible = !buttonVisible;

					button._visible = buttonVisible;

					contentBox.all('select').attr('disabled', buttonVisible);
					contentBox.all('button').attr('disabled', buttonVisible);

					button.get('contentBox').attr('disabled', false);
				},
				editor
			);
		}
	}
};

GROUPS[STYLES] = {
	children: [
		{
			icon: 'styles',
			_titleKey: 'STYLES'
		}
	],
	generate: {
		styles: function(editor, attrs, config) {
			var instance = this;

			var button = attrs.button;
			var boundingBox = button.get('boundingBox');

			editor.plug(A.Plugin.EditorMenu);

			editor.menu.add(
				A.merge(
					{
						align: {
							node: boundingBox,
							points: ['tl', 'bl']
						},
						hideOn: 'click',
						showOn: 'click',
						trigger: boundingBox
					},
					config
				)
			);
		}
	}
};

GROUPS[SUBSCRIPT] = {
	children: [
		{
			icon: 'subscript',
			_titleKey: 'SUBSCRIPT'
		},
		{
			icon: 'superscript',
			_titleKey: 'SUPERSCRIPT'
		}
	]
};

GROUPS[TEXT] = {
	children: [
		{
			icon: 'bold',
			_titleKey: 'BOLD'
		},
		{
			icon: 'italic',
			_titleKey: 'ITALIC'
		},
		{
			icon: 'underline',
			_titleKey: 'UNDERLINE'
		},
		{
			icon: 'strikethrough',
			_titleKey: 'LINE_THROUGH'
		}
	]
};

A.namespace('Plugin').EditorToolbar = EditorToolbar;

}, '1.0.1' ,{requires:['aui-base','aui-button-item','aui-color-picker','aui-editor-menu-plugin','aui-editor-tools-plugin','aui-form-select','aui-overlay-context-panel','aui-panel','aui-toolbar','createlink-base','editor-lists','editor-base','plugin']});
AUI.add('aui-editor-bbcode-plugin', function(A) {
var Lang = A.Lang,
	isArray = Lang.isArray,
	isString = Lang.isString,

	getClassName = A.ClassNameManager.getClassName,

	NAME = 'bbcodeplugin',
	BBCODE_PLUGIN = 'bbcode',

	QUOTE = 'quote',

	CSS_QUOTE = QUOTE,
	CSS_QUOTE_CONTENT = CSS_QUOTE + '-content',
	CSS_QUOTE_TITLE = CSS_QUOTE + '-title',

	TPL_BBCODE_ATTRIBUTE = '\\[(({0})=([^\\]]*))\\]([\\s\\S]*?)\\[\\/{0}\\]',
	TPL_BBCODE_GENERIC = '\\[({0})\\]([\\s\\S]*?)\\[\\/{0}\\]',
	TPL_HTML_GENERIC = '<{0}(>|\\b[^>]*>)([\\s\\S]*?)</{0}>',
	TPL_HTML_STYLE =  '<(([a-z0-9]+)\\b[^>]*?style=("|\').*?{0}\\s*:\\s*([^;"\']+);?[^>]*)>([\\s\\S]*?)<(/\\2)>',
	TPL_HTML_TAGS = '(<[a-z0-9]+[^>]*>|</[a-z0-9]+>)',
	TPL_QUOTE_CONTENT = '<div class="' + CSS_QUOTE + '"><div class="' + CSS_QUOTE_CONTENT + '">',
	TPL_QUOTE_CLOSING_TAG = '</div></div>',
	TPL_QUOTE_TITLE_CONTENT = '<div class="' + CSS_QUOTE_TITLE + '">$1</div>' + TPL_QUOTE_CONTENT,

	REGEX_HTML_TAGS = new RegExp(TPL_HTML_TAGS, 'gi'),

	HTML_BBCODE = [
		{
			convert: [
				['br']
			],
			regExp: '<{0}[^>]*>',
			output: '\n'
		},
		{
			convert: [
				['&nbsp;']
			],
			regExp: '{0}',
			output: ' '
		},
		{
			convert: [
				{
					tags: ['font-family'],
					source: ['font']
				},
				{
					tags: ['font-size'],
					source: ['size']
				},
				{
					tags: ['[^a-z-]*color'],
					source: ['color']
				}
			],
			regExp: TPL_HTML_STYLE,
			output: '<$1>[{0}=$4]$5[/{0}]<$6>'
		},
		{
			convert: [
				{
					tags: ['font-style'],
					source: ['i']
				},
				{
					tags: ['font-weight'],
					source: ['b']
				}
			],
			regExp: TPL_HTML_STYLE,
			output: '<$1>[{0}]$5[/{0}]<$6>'
		},
		{
			convert: [
				['text-decoration']
			],
			regExp: TPL_HTML_STYLE,
			output: function() {
				var tags = '';

				var attr = arguments[4].toLowerCase();

				if (attr.indexOf('underline') != -1) {
					tags += '[u]';
				}
				else if (attr.indexOf('line-through') != -1) {
					tags += '[s]';
				}

				if (tags != '') {
					return '<' + arguments[1] + '>' + tags + arguments[5] + tags.replace('[', '[/') + '<' + arguments[6] + '>';
				}

				return arguments[0];
			}
		},
		{
			convert: [
				['margin-left']
			],
			regExp: TPL_HTML_STYLE,
			output: function() {
				var html = '';
				var width = parseInt(arguments[3], 10);

				if (!isNaN(width)) {
					var total = Math.floor(width / 40);

					for (var i = 0; i < total; i++) {
						html += '[indent]';
					}
				}

				html = html + arguments[5] + html.replace(/\[/g, '[/');

				return '<' + arguments[1] + '>' + html + '<' + arguments[6] + '>';
			}
		},
		{
			convert: [
				{
					tags: ['font', 'size'],
					source: ['size']
				},
				{
					tags: ['font', 'face'],
					source: ['font']
				}
			],
			regExp: '(<{0}\\b[^>]*{1}=("|\')([^"\']+)("|\')[^>]*>)([\\s\\S]*?)(</{0}>)',
			output: '$1[{0}=$3]$5[/{0}]$6'
		},
		{
			convert: [
				['text-align']
			],
			regExp: TPL_HTML_STYLE,
			output: '<$1>[$4]$5[/$4]<$6>'
		},
   		{
			convert: [
				['quote']
			],
			regExp: '<div\\b[^>]*class=("|\')([^"\']*?)_' + CSS_QUOTE + '[^"\']*("|\')[^>]*>([\\s\\S]*?)</div>',
			output: '$4'
		},
		{
			convert: [
				['span']
			],
			regExp: TPL_HTML_GENERIC,
			output: '$2'
		},
		{
			convert: [
				['blockquote']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[indent]$2[/indent]'
		},
		{
			convert: [
				['b'],
				['strong']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[b]$2[/b]'
		},
		{
			convert: [
				['i'],
				['em']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[i]$2[/i]'
		},
		{
			convert: [
				['u']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[u]$2[/u]'
		},
		{
			convert: [
				['s'],
				['strike']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[s]$2[/s]'
		},
		{
			convert: [
				['img']
			],
			regExp: '(<a[^>]*>)?<{0}\\b[^>]*src=("|\')([^"\']+)("|\')[^>]*>(</a>)?',
			output: '[img]$3[/img]'
		},
		{
			convert: [
				['a']
			],
			regExp: '<{0}\\b[^>]*href=("|\')mailto:([^"\']+)("|\')[^>]*>([\\s\\S]*?)</{0}>',
			output: '[email=$2]$4[/email]'
		},
		{
			convert: [
				['a']
			],
			regExp: '<{0}\\b[^>]*href=("|\')([^"\']+)("|\')[^>]*>([\\s\\S]*?)</{0}>',
			output: '[url=$2]$4[/url]'
		},
		{
			convert: [
				['center']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[center]$2[/center]'
		},
		{
			convert: [
				['ul']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[list]$2[/list]'
		},
		{
			convert: [
				['ol']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[list=1]$2[/list]'
		},
		{
			convert: [
				['li']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[*]$2'
		},
		{
			convert: [
				['code']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[code]$2[/code]'
		},
		{
			convert: [
				['div']
			],
			regExp: TPL_HTML_GENERIC,
			output: '$2\n'
		},
		{
			convert: [
				['h1'],
				['h2'],
				['h3'],
				['h4'],
				['h5'],
				['h6']
			],
			regExp: TPL_HTML_GENERIC,
			output: '[b]$2[/b]\n'
		},
		{
			convert: [
				['p']
			],
			regExp: TPL_HTML_GENERIC,
			output: '\n$2\n'
		},
		{
			convert: [
				{
					tags: ['list', 'left|center|right'],
					source: ['list']
				}
			],
			regExp: '(\\[{0}[^\\]]*\\])\\s*\\[({1})\\]([\\s\\S]*?)\\[/\\2\\]\\s*\\[/{0}\\]',
			output: '[$2]$1$3[/{0}][/$2]'
		}
	],

	BBCODE_HTML = [
		{
			convert: [
				{
					tags: ['b'],
					source: ['b']
				},
				{
					tags: ['i'],
					source: ['i']
				},
				{
					tags: ['u'],
					source: ['u']
				},
				{
					tags: ['s'],
					source: ['s']
				},
				{
					tags: ['code'],
					source: ['code']
				}
			],
			regExp: TPL_BBCODE_GENERIC,
			output: '<{0}>$2</{0}>'
		},
		{
			convert: [
				{
					tags: ['color'],
					source: ['color']
				}
			],
			regExp: TPL_BBCODE_ATTRIBUTE,
			output: '<span style="{0}: $3;">$4</span>'
		},
		{
			convert: [
				{
					tags: ['font'],
					source: ['face']
				},
				{
					tags: ['size'],
					source: ['size']
				}
			],
			regExp: TPL_BBCODE_ATTRIBUTE,
			output: '<font {0}="$3">$4</font>'
		},
		{
			convert: [
				['img']
			],
			regExp: TPL_BBCODE_GENERIC,
			output: '<img src="$2" alt="" />'
		},
		{
			convert: [
				{
					tags: ['email'],
					source: ['mailto:']
				},
				{
					tags: ['url'],
					source: ['']
				}
			],
			regExp: TPL_BBCODE_ATTRIBUTE,
			output: '<a href="{0}$3">$4</a>'
		},
		{
			convert: [
				['list']
			],
			regExp: '\\[({0}(=1)?)]([\\s\\S]*?)\\[\\/{0}\\]',
			output: function() {
				var html = '';

				if (arguments[1] == 'list=1') {
					html += '<ol>';
				}
				else {
					html += '<ul>';
				}

				var li = Lang.trim(arguments[3]).split('[*]');

				for (var i = 1; i < li.length; i++) {
					html += '<li>' + li[i] + '</li>';
				}

				if (arguments[1] == 'list=1') {
					html += '</ol>';
				}
				else {
					html += '</ul>';
				}

				return html;
			}
		},
		{
			convert: [
				{
					tags: ['indent'],
					source: ['blockquote']
				}
			],
			regExp: TPL_BBCODE_GENERIC,
			output: '<{0}>$2</{0}>'
		},
		{
			convert: [
				['left'],
				['center'],
				['right']
			],
			regExp: TPL_BBCODE_GENERIC + '\n?',
			output: '<div style="text-align: $1;">$2</div>'
		},
		{
			convert: [
				['\n']
			],
			regExp: '{0}',
			output: '<br />'
		}
	];

var GROUPS = {};

GROUPS[QUOTE] = {
	children: [
		{
			icon: 'quote',
			_titleKey: 'QUOTE'
		}
	]
};

A.mix(
	A.Plugin.ExecCommand.COMMANDS,
	{
		quote: function(cmd, val) {
			var instance = this;

			var host = instance.get('host');

			var output = TPL_QUOTE_CONTENT + '{0}' + TPL_QUOTE_CLOSING_TAG;

			host.execCommand('wraphtml', output);
			host.focus();
		}
	}
);

if (!YUI.AUI.defaults.EditorToolbar) {
	YUI.AUI.defaults.EditorToolbar = {
		STRINGS: {}
	};
}

A.mix(
	YUI.AUI.defaults.EditorToolbar.STRINGS,
	{
		QUOTE: 'Quote'
	}
);

var EditorBBCode = A.Component.create(
	{
		NAME: NAME,

		NS: BBCODE_PLUGIN,

		EXTENDS: A.Plugin.Base,

		ATTRS: {
			host: {
				value: false
			}
		},

		prototype: {
			initializer: function() {
				var instance = this;

				var host = instance.get('host');

				host.addGroupType(QUOTE, GROUPS[QUOTE]);

				instance.afterHostMethod('getContent', instance.getBBCode, instance);
				host.on('contentChange', instance._contentChange, instance);
			},

			getBBCode: function() {
				var instance = this;

				var host = instance.get('host');
				var frame = host.getInstance();

				var wrapper = frame.one('body').cloneNode(true);
				var quote;

				var quoteIterator = function(item, index, collection) {
					var content;
					var temp = item;

					do {
						if (temp) {
							content = temp;
						}

						temp = temp.one('div.' + CSS_QUOTE_CONTENT);
					}
					while (temp);

					var parent = content.get('parentNode');

					if (parent.hasClass(QUOTE)) {
						var title = parent.previous();

						var bbcode = '[' + QUOTE;

						if (title && title.hasClass(CSS_QUOTE_TITLE)) {
							var titleHtml = title.get('innerHTML');

							titleHtml = titleHtml.replace(REGEX_HTML_TAGS, '');

							bbcode += '=' + (titleHtml.charAt(titleHtml.length - 1) == ':' ? titleHtml.substring(0, titleHtml.length - 1) : title.get('innerHTML'));

							title.remove(true);
						}

						bbcode += ']' +  content.get('innerHTML') + '[/' + QUOTE + ']\n';

						parent.set('innerHTML', bbcode);

						parent.removeClass(QUOTE);
						parent.addClass('_' + QUOTE);
					}
				};

				while (quote = wrapper.all('div.' + CSS_QUOTE)) {
					if (!quote.size()) {
						break;
					}

					quote.each(quoteIterator);
				}

				html = wrapper.get('innerHTML');

				html =  instance._parseTagExpressions(HTML_BBCODE, html);

				html = html.replace(REGEX_HTML_TAGS, '');

				return new A.Do.AlterReturn(null, html);
			},

			getContentAsHtml: function() {
				var instance = this;

				var host = instance.get('host');

				return host.constructor.prototype.getContent.apply(host, arguments);
			},

			setContentAsBBCode: function(bbcode) {
				var instance = this;

				var host = instance.get('host');

				host.set('content', bbcode);
			},

			_contentChange: function(event) {
				var instance = this;

				event.newVal = instance._parseBBCode(event.newVal);

				event.stopImmediatePropagation();
			},

			_parseBBCode: function(bbcode) {
				var instance = this;

				var html = bbcode;

				html = html.replace(/\[quote=([^\]]*)\]/gi, TPL_QUOTE_TITLE_CONTENT);
				html = html.replace(/\[quote\]/gi, TPL_QUOTE_CONTENT);
				html = html.replace(/\[\/quote\]\n?/gi, TPL_QUOTE_CLOSING_TAG);

				html = instance._parseTagExpressions(BBCODE_HTML, html);

				return html;
			},

			_parseTagExpressions: function(options, html) {
				var instance = this;

				var option;
				var convert;
				var convertItem;
				var convertLength;
				var tags;

				for (var i = 0; i < options.length; i++) {
					option = options[i];
					convert = option.convert;
					convertLength = convert.length;

					for (var j = 0; j < convertLength; j++) {
						var output = option.output;

						convertItem = convert[j];

						if (isArray(convertItem)) {
							tags = convertItem;
						}
						else {
							tags = convertItem.tags;

							if (isString(output)) {
								output = Lang.sub(output, convertItem.source);
							}
						}

						var regExp = Lang.sub(option.regExp, tags);

						html = html.replace(new RegExp(regExp, 'gi'), output);
					}
				}

				return html;
			}
		}
	}
);

A.namespace('Plugin').EditorBBCode = EditorBBCode;

}, '1.0.1' ,{requires:['aui-base','editor-base']});


AUI.add('aui-editor', function(A){}, '1.0.1' ,{use:['aui-editor-base','aui-editor-tools-plugin','aui-editor-menu-plugin','aui-editor-toolbar-plugin','aui-editor-bbcode-plugin'], skinnable:true});

