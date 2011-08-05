AUI().add(
	'liferay-asset-tags-selector',
	function(A) {
		var Lang = A.Lang;

		var	getClassName = A.ClassNameManager.getClassName;

		var NAME = 'tagselector';

		var CSS_INPUT_NODE = 'lfr-tag-selector-input';

		var CSS_NO_MATCHES = 'no-matches';

		var CSS_POPUP = 'lfr-tag-selector-popup';

		var CSS_TAGS_LIST = 'lfr-tags-selector-list';

		var TPL_CHECKED = ' checked="checked" ';

		var TPL_INPUT = '<label title="{name}"><input type="checkbox" value="{name}" {checked} />{name}</label>';

		var TPL_LOADING = '<div class="loading-animation" />';

		var TPL_MESSAGE = '<div class="lfr-tag-message">{0}</div>';

		var TPL_URL_SUGGESTIONS = 'http://search.yahooapis.com/ContentAnalysisService/V1/termExtraction?appid=YahooDemo&output=json&context={context}';

		var TPL_TAGS_CONTAINER = '<div class="' + CSS_TAGS_LIST + '"></div>';

		/**
		 * OPTIONS
		 *
		 * Required
		 * curEntries (string): The current tags.
		 * instanceVar {string}: The instance variable for this class.
		 * hiddenInput {string}: The hidden input used to pass in the current tags.
		 * textInput {string}: The text input for users to add tags.
		 * summarySpan {string}: The summary span to show the current tags.
		 *
		 * Optional
		 * focus {boolean}: Whether the text input should be focused.
		 *
		 * Callbacks
		 * contentCallback {function}: Called to get suggested tags.
		 */

		var AssetTagsSelector = A.Component.create(
			{
				ATTRS: {
					allowAnyEntry: {
						value: true
					},
					allowSuggestions: {
						value: false
					},
					contentCallback: {
						value: null
					},
					curEntries: {
						setter: function(value) {
							var instance = this;

							if (Lang.isString(value)) {
								value = value.split(',');
							}

							return value;
						},
						value: ''
					},
					dataSource: {
						valueFn: function() {
							var instance = this;

							return instance._getTagsDataSource();
						}
					},
					guid: {
						value: ''
					},
					instanceVar: {
						value: ''
					},
					hiddenInput: {
						setter: function(value) {
							var instance = this;

							return A.one(value + instance.get('guid'));
						}
					},
					matchKey: {
						value: 'value'
					},
					schema: {
						value: {
							resultFields: ['text', 'value']
						}
					}
				},

				EXTENDS: A.TextboxList,

				NAME: NAME,

				prototype: {
					renderUI: function() {
						var instance = this;

						AssetTagsSelector.superclass.renderUI.apply(instance, arguments);

						instance._renderIcons();

						instance.inputNode.addClass(CSS_INPUT_NODE);

						instance._overlayAlign.node = instance.entryHolder;
					},

					bindUI: function() {
						var instance = this;

						AssetTagsSelector.superclass.bindUI.apply(instance, arguments);

						instance._bindTagsSelector();

						instance.entries.after('add', instance._updateHiddenInput, instance);
						instance.entries.after('remove', instance._updateHiddenInput, instance);
					},

					addEntries: function() {
						var instance = this;

						instance._onAddEntryClick();
					},

					syncUI: function() {
						var instance = this;

						AssetTagsSelector.superclass.syncUI.apply(instance, arguments);

						var curEntries = instance.get('curEntries');

						A.each(curEntries, instance.add, instance);
					},

					_bindTagsSelector: function() {
						var instance = this;

						instance._submitFormListener = A.Do.before(instance._onAddEntryClick, window, 'submitForm', instance);

						A.on(
							'key',
							instance._onTagsSelectorCommaPress,
							instance.get('boundingBox'),
							'down:188',
							instance
						);
					},

					_formatEntry: function(item) {
						var instance = this;

						var input = A.substitute(TPL_INPUT, item);

						instance._buffer.push(input);
					},

					_getPopup: function() {
						var instance = this;

						if (!instance._popup) {
							var popup = new A.Dialog(
								{
									bodyContent: TPL_LOADING,
									constrain: true,
									draggable: true,
									hideClass: 'aui-helper-hidden-accessible',
									preventOverlap: true,
									stack: true,
									title: '',
									width: 320,
									zIndex: 1000
								}
							).render();

							popup.get('boundingBox').addClass(CSS_POPUP);

							var bodyNode = popup.bodyNode;

							bodyNode.html('');

							var searchField = new A.Textfield(
								{
									defaultValue: Liferay.Language.get('search'),
									labelText: false
								}
							).render(bodyNode);

							var entriesNode = A.Node.create(TPL_TAGS_CONTAINER);

							bodyNode.appendChild(entriesNode);

							popup.searchField = searchField;
							popup.entriesNode = entriesNode;

							instance._popup = popup;

							instance._initSearch();

							var onCheckboxClick = A.bind(instance._onCheckboxClick, instance);

							entriesNode.delegate('click', onCheckboxClick, 'input[type=checkbox]');
						}

						return instance._popup;
					},

					_getProxyData: function() {
						var instance = this;

						var context = '';

						var contentCallback = instance.get('contentCallback');

						if (contentCallback) {
							context = contentCallback();
						}

						var suggestionsURL = A.substitute(
							TPL_URL_SUGGESTIONS,
							{
								context: encodeURIComponent(context)
							}
						);

						var proxyData = {
							url: suggestionsURL
						};

						return proxyData;
					},

					_getEntries: function(callback) {
						var instance = this;

						Liferay.Service.Asset.AssetTag.getGroupTags(
							{
								groupId: themeDisplay.getParentGroupId()
							},
							callback
						);
					},

					_getTagsDataSource: function() {
						var instance = this;

						var AssetTagSearch = Liferay.Service.Asset.AssetTag.search;

						AssetTagSearch._serviceQueryCache = {};

						var serviceQueryCache = AssetTagSearch._serviceQueryCache;

						var dataSource = new Liferay.Service.DataSource(
							{
								on: {
									request: function(event) {
										var term = event.request;
										var key = term;

										if (term == '*') {
											term = '';
										}

										var serviceQueryObj = serviceQueryCache[key];

										if (!serviceQueryObj) {
											serviceQueryObj = {
												groupId: themeDisplay.getParentGroupId(),
												name: '%' + term + '%',
												properties: '',
												begin: 0,
												end: 20
											};

											serviceQueryCache[key] = serviceQueryObj;
										}

										event.request = serviceQueryObj;
									}
								},
								source: AssetTagSearch
							}
						).plug(
							A.Plugin.DataSourceCache,
							{
								max: 500
							}
						);

						return dataSource;
					},

					_initSearch: function() {
						var instance = this;

						var popup = instance._popup;

						popup.liveSearch = new A.LiveSearch(
							{
								after: {
									search: function() {
										var fieldsets = popup.entriesNode.all('fieldset');

										fieldsets.each(
											function(item, index, collection) {
												var visibleEntries = item.one('label:not(.aui-helper-hidden)');

												var action = 'addClass';

												if (visibleEntries) {
													action = 'removeClass';
												}

												item[action](CSS_NO_MATCHES);
											}
										);
									}
								},
								data: function(node) {
									var value = node.attr('title');

									return value.toLowerCase();
								},
								input: popup.searchField.get('node'),
								nodes: '.' + CSS_TAGS_LIST + ' label'
							}
						);
					},

					_namespace: function(name) {
						var instance = this;

						return instance.get('instanceVar') + name + instance.get('guid');
					},

					_onAddEntryClick: function(event) {
						var instance = this;

						var text = instance.inputNode.val();

						if (text) {
							if(text.indexOf(',') > -1) {
								var items = text.split(',');

								A.each(
									items,
									function(item, index, collection) {
										instance.entries.add(item, {});
									}
								);
							} else {
								instance.entries.add(text, {});
							}
						}

						Liferay.Util.focusFormField(instance.inputNode);
					},

					_onCheckboxClick: function(event) {
						var instance = this;

						var checkbox = event.currentTarget;
						var checked = checkbox.get('checked');
						var value = checkbox.val();

						var action = 'remove';

						if (checked) {
							action = 'add';
						}

						instance[action](value);
					},

					_onTagsSelectorCommaPress: function(event) {
						var instance = this;

						instance._onAddEntryClick();

						event.preventDefault();
					},

					_renderIcons: function() {
						var instance = this;

						var contentBox = instance.get('contentBox');

						var toolbar = [
							{
								handler: {
									context: instance,
									fn: instance._onAddEntryClick
								},
								icon: 'plus',
								id: 'add',
								label: Liferay.Language.get('add')
							},
							{
								handler: {
									context: instance,
									fn: instance._showSelectPopup
								},
								icon: 'search',
								id: 'select',
								label: Liferay.Language.get('select')
							}
						];

						if (instance.get('contentCallback')) {
							toolbar.push(
								{
									handler: {
										context: instance,
										fn: instance._showSuggestionsPopup
									},
									icon: 'comment',
									id: 'suggest',
									label: Liferay.Language.get('suggestions')
								}
							);
						}

						instance.icons = new A.Toolbar(
							{
								children: toolbar
							}
						).render(contentBox);

						var iconsBoundingBox = instance.icons.get('boundingBox');

						instance.entryHolder.placeAfter(iconsBoundingBox);
					},

					_showPopup: function(event) {
						var instance = this;

						var popup = instance._getPopup();

						if (event && event.currentTarget) {
							var toolItem = event.currentTarget.get('boundingBox');

							popup.align(toolItem, ['bl', 'tl']);
						}

						popup.entriesNode.html(TPL_LOADING);

						popup.show();
					},

					_showSelectPopup: function(event) {
						var instance = this;

						instance._showPopup(event);

						instance._popup.set('title', Liferay.Language.get('tags'));

						instance._getEntries(
							function(entries) {
								instance._updateSelectList(entries, instance._entriesIterator);
							}
						);
					},

					_showSuggestionsPopup: function(event) {
						var instance = this;

						instance._showPopup(event);

						instance._popup.set('title', Liferay.Language.get('suggestions'));

						A.io.request(
							themeDisplay.getPathMain() + '/portal/rest_proxy',
							{
								data: instance._getProxyData(),
								dataType: 'json',
								on: {
									success: function(event, id, obj) {
										var results = this.get('responseData');

										if (results && results.ResultSet) {
											instance._updateSelectList(results.ResultSet.Result, instance._suggestionsIterator);
										}
									}
								}
							}
						);
					},

					_suggestionsIterator: function(item, index, collection) {
						var instance = this;

						var checked = instance.entries.indexOfKey(item) > -1 ? TPL_CHECKED : '';

						var tag = {
							checked: checked,
							name: item
						};

						instance._formatEntry(tag);
					},

					_entriesIterator: function(item, index, collection) {
						var instance = this;

						item.checked = instance.entries.indexOfKey(item.name) > -1 ? TPL_CHECKED : '';

						instance._formatEntry(item);
					},

					_updateHiddenInput: function(event) {
						var instance = this;

						var hiddenInput = instance.get('hiddenInput');

						hiddenInput.val(instance.entries.keys.join());

						var popup = instance._popup;

						if (popup && popup.get('visible')) {
							var checkbox = popup.bodyNode.one('input[value=' + event.attrName + ']');

							if (checkbox) {
								var checked = false;

								if (event.type == 'dataset:add') {
									checked = true;
								}

								checkbox.set('checked', checked);
							}
						}
					},

					_updateSelectList: function(data, iterator) {
						var instance = this;

						var popup = instance._popup;

						popup.searchField.resetValue();

						instance._buffer = ['<fieldset class="' + (!data || !data.length ? CSS_NO_MATCHES : '') + '">'];

						A.each(data, iterator, instance);

						var buffer = instance._buffer;

						var message = A.substitute(TPL_MESSAGE, [Liferay.Language.get('no-tags-found')]);

						buffer.push(message);
						buffer.push('</fieldset>');

						popup.entriesNode.html(buffer.join(''));

						popup.liveSearch.get('nodes').refresh();
						popup.liveSearch.refreshIndex();
					},

					_buffer: []
				}
			}
		);

		Liferay.AssetTagsSelector = AssetTagsSelector;
	},
	'',
	{
		requires: ['aui-autocomplete', 'aui-dialog', 'aui-io-request', 'aui-live-search', 'aui-textboxlist', 'aui-form-textfield', 'datasource-cache', 'liferay-service-datasource', 'substitute']
	}
);