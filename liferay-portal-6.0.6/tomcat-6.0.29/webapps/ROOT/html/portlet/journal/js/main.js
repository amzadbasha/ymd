AUI().add(
	'liferay-portlet-journal',
	function(A) {
		var D = A.DataType;
		var Lang = A.Lang;

		var generateInstanceId = function() {
			var instanceId = '';

			var key = Liferay.Portlet.Journal.PROXY.instanceIdKey;

			for (var i = 0; i < 8; i++) {
				var pos = Math.floor(Math.random() * key.length);

				instanceId += key.substring(pos, pos + 1);
			}

			return instanceId;
		};

		var getUID = function() {
			return (++ A.Env._uidx);
		};

		var TPL_FIELD_CONTAINER = '<div><li class="structure-field">' +
				'<span class="journal-article-close"></span>' +
				'<span class="folder">' +
					'<div class="field-container">' +
						'<input class="journal-article-localized" type="hidden" value="false" />' +
						'<div class="journal-article-move-handler"></div>' +
						'<label for="" class="journal-article-field-label"><span>{fieldLabel}</span></label>' +
						'<div class="journal-article-component-container"></div>' +
						'<span class="aui-field aui-field-choice journal-article-localized-checkbox">' +
							'<span class="aui-field-content">' +
								'<span class="aui-field-element aui-field-label-right">' +
									'<input type="hidden" value="false" name="{portletNamespace}{instanceId}localized-checkbox">' +
									'<input type="checkbox" onclick="Liferay.Util.updateCheckboxValue(this); " name="{portletNamespace}{instanceId}localized-checkboxCheckbox" id="{portletNamespace}{instanceId}localized-checkboxCheckbox" class="aui-field-input aui-field-input-choice"> </span>' +
									'<label for="{portletNamespace}{instanceId}localized-checkboxCheckbox" class="aui-field-label">{localizedLabelLanguage}</label>' +
								'</span>'+
							'</span>' +
						'<div class="journal-article-required-message portlet-msg-error">{requiredFieldLanguage}</div>' +
						'<div class="journal-article-buttons {articleButtonsRowCSSClass}">' +
							'<span class="aui-field aui-field-inline aui-field-text journal-article-variable-name">' +
								'<span class="aui-field-content">' +
									'<label for="{portletNamespace}{instanceId}variableName" class="aui-field-label">{variableNameLanguage}</label>' +
									'<span class="aui-field-element ">' +
										'<input type="text" size="25" value="{variableName}" name="{portletNamespace}variableName" id="{portletNamespace}{instanceId}variableName" class="aui-field-input aui-field-input-text">' +
									'</span>' +
								'</span>' +
							'</span>' +
							'{editBtnTemplateHTML}' +
							'{repeatableBtnTemplateHTML}' +
						'</div>' +
					'</div>' +
					'<ul class="folder-droppable"></ul>' +
				'</span>' +
			'</li></div>';

		var TPL_HELPER = '<div id="{0}" class="journal-article-helper not-intersecting">' +
			'<div class="journal-component"></div>' +
			'<div class="forbidden-action"></div>' +
		'</div>';

		var TPL_IFRAME = '<iframe frameborder="0" height="460" id="{name}" name="{name}" scrolling="no" src="{url}" width="500"></iframe>';

		var TPL_INSTRUCTIONS_CONTAINER = '<div class="journal-article-instructions-container journal-article-instructions-message portlet-msg-info"></div>';

		var TPL_OPTION = '<option></option>';

		var TPL_PLACEHOLDER = '<div class="aui-tree-placeholder aui-tree-sub-placeholder"></div>';

		var TPL_STRUCTURE_FIELD_INPUT = '<input class="aui-field-input lfr-input-text" type="text" value="" size="40"/>';

		var TPL_TOOLTIP_IMAGE = '<img align="top" class="journal-article-instructions-container" src="' + themeDisplay.getPathThemeImages() + '/portlet/help.png" />';

		var fieldsDataSet = new A.DataSet();

		var Journal = function(portletNamespace, articleId) {
			var instance = this;

			instance.articleId = articleId;
			instance.timers = {};
			instance.portletNamespace = portletNamespace;

			var structureTreeId = instance._getNamespacedId('#structureTree');
			var structureTree = A.one(structureTreeId);

			instance._helperId = instance._getNamespacedId('journalArticleHelper', instance.portletNamespace, '');

			var helperHTML = A.substitute(TPL_HELPER, [instance._helperId]);

			instance._helper = A.Node.create(helperHTML);

			instance._helper.appendTo(document.body);

			instance.acceptChildren = true;

			var placeholder = A.Node.create(TPL_PLACEHOLDER);

			var fields = A.all(structureTreeId + ' li.structure-field');

			instance.nestedListOptions = {
				dd: {
					handles: ['.journal-article-move-handler']
				},
				dropCondition: function(event) {
					var dropNode = event.drop.get('node');

					return instance.canDrop(dropNode);
				},
				dropOn: 'span.folder > ul.folder-droppable',
				helper: instance._helper,
				placeholder: placeholder,
				sortCondition: function(event) {
					var dropNode = event.drop.get('node');

					return dropNode.ancestor(structureTreeId);
				},
				sortOn: structureTreeId
			};

			instance.nestedListEvents = {
				'drag:start': function(event) {
					var helper = instance._helper;

					helper.setStyle('height', '100px');
					helper.setStyle('width', '450px');

					instance.updateTextAreaVisibility('hidden');
				},

				'drag:end': function(event) {
					instance._dropField();

					instance.updateTextAreaVisibility('visible');
				},

				'drag:out': function(event) {
					if (!instance.acceptChildren) {
						instance.helperIntersecting();
						instance.acceptChildren = true;
					}
				},

				'drag:over': function(event) {
					var dropNode = event.drop.get('node');

					instance.acceptChildren = instance.canDrop(dropNode);

					if (instance.acceptChildren) {
						instance.helperIntersecting();
					}
					else {
						instance.helperNotIntersecting();
					}
				}
			};

			instance.createNestedList(
				fields.filter(':not(.repeated-field)').filter(':not(.parent-structure-field)'),
				instance.nestedListOptions,
				instance.nestedListEvents
			);

			var journalComponentListId = instance._getNamespacedId('#journalComponentList');
			var componentFields = A.all(journalComponentListId + ' .component-group .journal-component');

			instance.componentFieldsOptions = {
				dropCondition: function(event) {
					var dropNode = event.drop.get('node');

					return instance.canDrop(dropNode);
				},
				dropOn: 'span.folder > ul.folder-droppable',
				helper: instance._helper,
				placeholder: placeholder,
				sortCondition: function(event) {
					var dropNode = event.drop.get('node');

					return dropNode.ancestor(structureTreeId);
				}
			};

			instance.componentFieldsEvents = {
				'drag:start': function(event) {
					var drag = event.target;
					var proxy = drag.get('dragNode');
					var source = drag.get('node');
					var languageName = source.text();
					var componentType = instance.getComponentType(source);
					var className = 'journal-component-' + instance._stripComponentType(componentType);
					var helper = instance._helper;
					var helperComponentIcon = instance._helper.all('div.journal-component');

					helper.setStyle('height', '25px');
					helper.setStyle('width', '200px');

					if (helperComponentIcon) {
						helperComponentIcon.addClass(className).html(languageName);
					}

					proxy.addClass('component-dragging');

					instance.updateTextAreaVisibility('hidden');

					instance.clonedSource = source.clone();

					source.placeBefore(instance.clonedSource);

					instance.clonedSource.attr('id', '');
					instance.clonedSource.guid();

					instance.clonedSource.show().setStyle('visibility', 'visible');
					instance.clonedSource.removeClass('aui-helper-hidden');
					instance.clonedSource.addClass('dragging');

					instance.createNestedList(
						instance.clonedSource,
						instance.componentFieldsOptions,
						instance.componentFieldsEvents,
						true
					);
				},

				'drag:end': function(event) {
					var drag = event.target;
					var source = drag.get('node');
					var proxy = drag.get('dragNode');

					var componentType = instance.getComponentType(source);
					var className = 'journal-component-' + instance._stripComponentType(componentType);
					var helperComponentIcon = instance._helper.all('div.journal-component');

					proxy.removeClass('component-dragging');

					if (helperComponentIcon) {
						helperComponentIcon.removeClass(className).empty();
					}

					var addedComponent = structureTree.one('div.journal-component');

					if (addedComponent) {
						addedComponent.hide();

						var fieldInstance = instance._fieldInstanceFactory(componentType);

						if (fieldInstance.get('fieldType') == 'text_area') {
							var html = instance.buildHTMLEditor(fieldInstance);

							fieldInstance.set('innerHTML', html);
						}

						var htmlTemplate = instance._createFieldHTMLTemplate(fieldInstance);
						var newComponent = A.Node.create(htmlTemplate);

						addedComponent.placeBefore(newComponent);
						addedComponent.remove();

						var variableName = newComponent.attr('dataName');
						var randomInstanceId = newComponent.attr('dataInstanceId');

						fieldInstance.set('source', newComponent);
						fieldInstance.set('variableName', variableName);
						fieldInstance.set('instanceId', randomInstanceId);

						instance.createNestedList(
							newComponent,
							instance.nestedListOptions,
							instance.nestedListEvents
						);

						instance._attachEvents();

						var id = newComponent.get('id');

						fieldsDataSet.add(id, fieldInstance);

						instance.repositionEditFieldOptions();

						var variableNameInput = instance.getById(randomInstanceId + 'variableName');

						if (variableNameInput) {
							Liferay.Util.focusFormField(variableNameInput);
							variableNameInput.select();
						}
					}
					else {
						source.remove();
					}

					instance.updateTextAreaVisibility('visible');

					if (instance.clonedSource) {
						var journalComponentList = instance.getById('#journalComponentList');

						instance.clonedSource.removeClass('dragging');

						if (journalComponentList.contains(source[0]) &&
							journalComponentList.contains(instance.clonedSource[0])) {

							source.remove();
						}
					}
				},

				'drag:out': instance.nestedListEvents['drag:out'],

				'drag:over': instance.nestedListEvents['drag:over']
			};

			instance.createNestedList(
				componentFields,
				instance.componentFieldsOptions,
				instance.componentFieldsEvents,
				true
			);

			var fieldLabel = instance.getById('fieldLabel');
			var editContainerWrapper = instance.getById('#journalArticleEditFieldWrapper');

			editContainerWrapper.show();

			instance.editContainerContextPanel = new A.OverlayContextPanel(
				{
					after: {
						hide: A.bind(instance.closeEditFieldOptions, instance),
						show: function() {
							A.later(
								0,
								instance,
								function() {
									Liferay.Util.focusFormField(fieldLabel);
								}
							);
						}
					},
					align: {
						points: ['lc', 'rc']
					},
					bodyContent: editContainerWrapper,
					trigger: '.edit-button .aui-button-input'
				}
			).render();

			A.OverlayContextManager.remove(instance.editContainerContextPanel);

			instance._initializeTagsSuggestionContent();
			instance._initializePageLoadFieldInstances();
			instance._attachEvents();
			instance._attachEditContainerEvents();
			instance._attachDelegatedEvents();

			instance._updateOriginalStructureXSD();
		};

		Journal.prototype = {
			addStructure: function(structureId, autoStructureId, name, description, xsd, callback) {
				var instance = this;

				var groupId = instance.getGroupId();
				var addCommunityPermissions = true;
				var addGuestPermissions = true;
				var parentStructureId = '';

				var serviceParameterTypes = [
					'long',
					'java.lang.String',
					'boolean',
					'java.lang.String',
					'java.lang.String',
					'java.lang.String',
					'java.lang.String',
					'com.liferay.portal.service.ServiceContext'
				];

				Liferay.Service.Journal.JournalStructure.addStructure(
					{
						groupId: groupId,
						structureId: structureId,
						autoStructureId: autoStructureId,
						parentStructureId: parentStructureId,
						name: name,
						description: description,
						xsd: xsd,
						serviceContext: A.JSON.stringify(
							{
								addCommunityPermissions: addCommunityPermissions,
								addGuestPermissions: addGuestPermissions,
								scopeGroupId: groupId
							}
						),
						serviceParameterTypes: A.JSON.stringify(serviceParameterTypes)
					},
					function(message) {
						if (Lang.isFunction(callback)) {
							callback(message);
						}
					}
				);
			},

			articleChanged: function() {
				var instance = this;

				return window[instance.portletNamespace + 'contentChangedFlag'];
			},

			buildHTMLEditor: function(fieldInstance) {
				var instance = this;

				var instanceId = fieldInstance.get('instanceId');
				var name = instance.portletNamespace + 'structure_el_' + instanceId + '_content';
				var url = instance.buildHTMLEditorURL(fieldInstance);

				var iframeHTML = A.substitute(
					TPL_IFRAME,
					{
						name: name,
						url: url
					}
				);

				return iframeHTML;
			},

			buildHTMLEditorURL: function(fieldInstance) {
				var instance = this;

				var url = [];
				var initMethod = instance.portletNamespace + 'initEditor' + fieldInstance.get('variableName');
				var onChangeMethod = instance.portletNamespace + 'editorContentChanged';

				window[initMethod] = instance._emptyFunction;

				url.push(themeDisplay.getPathContext());
				url.push('/html/js/editor/editor.jsp?p_l_id=');
				url.push(themeDisplay.getPlid());
				url.push('&p_main_path=');
				url.push(encodeURIComponent(themeDisplay.getPathMain()));
				url.push('&doAsUserId=');
				url.push(Journal.PROXY.doAsUserId);
				url.push('&editorImpl=');
				url.push(Journal.PROXY.editorImpl);
				url.push('&toolbarSet=liferay-article');
				url.push('&initMethod=');
				url.push(initMethod);
				url.push('&onChangeMethod=');
				url.push(onChangeMethod);
				url.push('&cssPath=');
				url.push(Journal.PROXY.pathThemeCss);
				url.push('&cssClasses=portlet ');

				return url.join('');
			},

			canDrop: function(source) {
				var instance = this;

				var componentType = instance.getComponentType(source);

				var canDrop = true;

				if ((componentType == 'multi-list') || (componentType == 'list')) {
					canDrop = false;
				}
				else if (source.hasClass('repeated-field') || source.hasClass('parent-structure-field')) {
					canDrop = false;
				}

				return canDrop;
			},

			changeLanguageView: function() {
				var instance = this;

				var form = instance.getPrincipalForm();

				var articleContent = instance.getArticleContentXML();
				var cmdInput = instance.getByName(form, 'cmd');
				var contentInput = instance.getByName(form, 'content');
				var languageIdInput = instance.getByName(form, 'languageId');
				var lastLanguageIdInput = instance.getByName(form, 'lastLanguageId');
				var redirectInput = instance.getByName(form, 'redirect');

				cmdInput.val('');

				if (instance.articleChanged()) {
					if (confirm(Liferay.Language.get('would-you-like-to-save-the-changes-made-to-this-language'))) {
						cmdInput.val('update');

						contentInput.val(articleContent);
					}
					else {
						if (!confirm(Liferay.Language.get('are-you-sure-you-want-to-switch-the-languages-view'))) {
							languageIdInput.val(lastLanguageIdInput.val());

							return;
						}
						else {
							contentInput.val('');
						}
					}
				}
				else {
					contentInput.val('');
				}

				var workflowActionInput = instance.getByName(form, 'workflowAction');

				workflowActionInput.val(Liferay.Workflow.STATUS_ANY);

				var languageId = languageIdInput.val();
				var getLanguageViewURL = window[instance.portletNamespace + 'getLanguageViewURL'];
				var languageViewURL = getLanguageViewURL(languageId);

				redirectInput.val(languageViewURL);

				form.submit();
			},

			clearMessage: function(selector) {
				var instance = this;

				var journalMessage = A.one(selector);

				var timer = instance.timers[selector];

				if (timer) {
					timer.cancel();
				}

				journalMessage.hide();
			},

			closeEditFieldOptions: function() {
				var instance = this;

				instance.editContainerContextPanel.hide();

				instance.unselectFields();
			},

			closeField: function(source) {
				var instance = this;

				var fields = instance.getFields();

				if (fields && fields.size() > 1) {
					if (confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-this-field-and-all-its-children'))) {
						instance.closeRepeatedSiblings(source);
						instance.closeEditFieldOptions();

						if (source.inDoc()) {
							source.remove();
						}
					}
				}
			},

			closeRepeatedSiblings: function(source) {
				var instance = this;

				var fieldInstance = instance.getFieldInstance(source);

				if (!fieldInstance.get('repeated')) {
					var repeatedFields = instance.getRepeatedSiblings(fieldInstance);

					if (repeatedFields) {
						repeatedFields.remove();
					}
				}
			},

			createNestedList: function(nodes, options, events, components) {
				var instance = this;

				var applyEvents = function(nestedList) {
					A.each(
						events,
						function(item, index, collection) {
							if (index && Lang.isFunction(item)) {
								nestedList.on(index, item);
							}
						}
					);
				};

				var defaults = {
					dropOn: '.folder'
				};

				options = A.merge(defaults, options);

				if (!instance._nestedList) {
					instance._nestedList = new A.NestedList(options);

					applyEvents(instance._nestedList);
				}

				if (components && !instance._nestedListComponents) {
					instance._nestedListComponents = new A.NestedList(options);

					applyEvents(instance._nestedListComponents);
				}

				nodes.each(
					function(item, index, collection) {
						var nestedList = instance._nestedList;

						if (components) {
							nestedList = instance._nestedListComponents;
						}

						nestedList.add(item);
					}
				);
			},

			disableEditMode: function() {
				var instance = this;

				A.getBody().removeClass('portlet-journal-edit-mode');

				var editStructureBtn = instance.getById('editStructureBtn');
				var journalComponentList = instance.getById('#journalComponentList');
				var saveStructureBtn = instance.getById('saveStructureBtn');

				instance.closeEditFieldOptions();

				saveStructureBtn.ancestor('.aui-button').hide();
				journalComponentList.hide();

				var structureBtnText = Liferay.Language.get('edit');

				editStructureBtn.val(structureBtnText);

				A.all('input.journal-list-label').attr('disabled', 'disabled');

				if (instance.structureChange()) {
					var structureMessage = instance.getById('structureMessage');

					instance.showMessage(
						structureMessage,
						'alert',
						null,
						30000
					);
				}
			},

			disableFields: function() {
				var instance = this;

				var fieldsContainer = instance.getById('#journalArticleContainer');

				fieldsContainer.all('input:not(:button)').attr('disabled', 'disabled');
				fieldsContainer.all('textarea, select').attr('disabled', 'disabled');
			},

			downloadArticleContent: function() {
				var instance = this;

				var downloadAction = themeDisplay.getPathMain() + '/journal/get_article_content';
				var auxForm = instance.getPrincipalForm('fm2');

				var articleContent = instance.getArticleContentXML();
				var xmlInput = instance.getByName(auxForm, 'xml', true);

				if (instance.structureChange()) {
					if (confirm(Liferay.Language.get('you-should-save-the-structure-first'))) {
						instance.openSaveStructureDialog();
					}
				}
				else {
					auxForm.attr('action', downloadAction);
					auxForm.attr('target', '_self');

					xmlInput.val(articleContent);

					auxForm.submit();
				}
			},

			editContainerNormalMode: function() {
				var instance = this;

				var editContainerWrapper = instance.getById('#journalArticleEditFieldWrapper');

				editContainerWrapper.removeClass('save-mode');
				instance.editContainerModified = false;
			},

			editContainerSaveMode: function() {
				var instance = this;

				var editContainerWrapper = instance.getById('#journalArticleEditFieldWrapper');

				editContainerWrapper.addClass('save-mode');
				instance.editContainerModified = true;
			},

			enableEditMode: function() {
				var instance = this;

				A.getBody().addClass('portlet-journal-edit-mode');

				var editStructureBtn = instance.getById('editStructureBtn');
				var journalComponentList = instance.getById('#journalComponentList');
				var saveStructureBtn = instance.getById('saveStructureBtn');

				instance.editContainerNormalMode();

				saveStructureBtn.ancestor('.aui-button').show();

				journalComponentList.show();

				var structureTree = instance.getById('#structureTree');
				var structureBtnText = Liferay.Language.get('stop-editing');

				editStructureBtn.val(structureBtnText);

				structureTree.all('.journal-list-label').attr('disabled', '');

				var structureMessage = instance.getById('structureMessage');

				instance.clearMessage(structureMessage);
			},

			enableFields: function() {
				var instance = this;

				var fieldsContainer = instance.getById('#journalArticleContainer');

				fieldsContainer.all('input:not(:button)').attr('disabled', '');
				fieldsContainer.all('textarea, select').attr('disabled', '');
			},

			getArticleContentXML: function() {
				var instance = this;

				var buffer = [];
				var structureTreeId = instance._getNamespacedId('#structureTree');
				var sourceRoots = A.all(structureTreeId + ' > li');
				var hasStructure = instance.hasStructure();

				var content;

				if (!hasStructure) {
					var item = sourceRoots.item(0);

					if (item) {
						var fieldInstance = instance.getFieldInstance(item);

						content = fieldInstance.getContent(item);
					}
				}
				else {
					var attributes = null;
					var availableLocales = [];
					var stillLocalized = false;
					var availableLocalesElements = A.all('[name=' + instance.portletNamespace + 'available_locales]');
					var defaultLocale = instance.getById('defaultLocale').val();

					instance.getFields().each(
						function(item, index, collection) {
							var fieldInstance = instance.getFieldInstance(item);
							var isLocalized = fieldInstance.get('localized');

							if (isLocalized) {
								stillLocalized = true;
							}
						}
					);

					if (stillLocalized) {
						availableLocalesElements.each(
							function(item, index, collection) {
								var locale = item.val();

								if (locale) {
									availableLocales.push(locale);
								}
							}
						);

						attributes = {
							'available-locales': availableLocales.join(','),
							'default-locale': defaultLocale
						};
					}

					var root = instance._createDynamicNode('root', attributes);

					buffer.push(root.openTag);

					sourceRoots.each(
						function(item, index, collection) {
							instance._appendStructureTypeElementAndMetaData(item, buffer, true);
						}
					);

					buffer.push(root.closeTag);

					content = buffer.join('');
				}

				return content;
			},

			getById: function(id, namespace) {
				var instance = this;

				return A.one(
					instance._getNamespacedId(id, namespace)
				);
			},

			getByName: function(currentForm, name, withoutNamespace) {
				var instance = this;

				var inputName = withoutNamespace ? name : instance.portletNamespace + name;

				return A.one(currentForm).one('[name=' + inputName + ']');
			},

			getCloseButtons: function() {
				var instance = this;

				return A.all('span.journal-article-close');
			},

			getComponentType: function(source) {
				return source.attr('dataType');
			},

			getDefaultLocale: function() {
				var instance = this;

				return instance.getById('defaultLocale').val();
			},

			getEditButton: function(source) {
				var instance = this;

				return source.one('.edit-button .aui-button-input');
			},

			getEditButtons: function() {
				var instance = this;

				var structureTreeId = instance._getNamespacedId('#structureTree');

				return A.all(structureTreeId + ' div.journal-article-buttons .edit-button .aui-button-input');
			},

			getFieldInstance: function(source) {
				var instance = this;

				var id = source.get('id');

				return fieldsDataSet.item(id);
			},

			getFields: function() {
				var instance = this;

				var structureTreeId = instance._getNamespacedId('#structureTree');

				return A.all(structureTreeId + ' li');
			},

			getGroupId: function() {
				var instance = this;

				var groupId = themeDisplay.getScopeGroupId();

				if (instance.articleId) {
					var form = instance.getPrincipalForm();
					var inputGroupId = instance.getByName(form, 'groupId');
					var inputGroupIdVal = inputGroupId.val();

					if (inputGroupIdVal) {
						groupId = inputGroupIdVal;
					}
				}

				return groupId;
			},

			getParentStructureId: function() {
				var instance = this;

				return instance.getById('parentStructureId').val();
			},

			getRepeatableButtons: function() {
				var instance = this;

				var structureTreeId = instance._getNamespacedId('#structureTree');

				return A.all(structureTreeId + ' div.journal-article-buttons .repeatable-button .aui-button-input');
			},

			getRepeatedSiblings: function(fieldInstance) {
				var instance = this;

				var structureTreeId = instance._getNamespacedId('#structureTree');
				var selector = structureTreeId + ' li[dataName=' + fieldInstance.get('variableName') + '].repeated-field';

				return A.all(selector);
			},

			getSaveDialog: function(openCallback) {
				var instance = this;

				if (!instance._saveDialog) {
					var saveStructureTemplateDialog = instance.getById('saveStructureTemplateDialog');
					var htmlTemplate = saveStructureTemplateDialog.html();
					var title = Liferay.Language.get('editing-structure-details');

					var form = instance.getPrincipalForm();

					var structureIdInput = instance.getByName(form, 'structureId');
					var structureNameInput = instance.getByName(form, 'structureName');
					var structureDescriptionInput = instance.getByName(form, 'structureDescription');
					var storedStructureXSD = instance.getByName(form, 'structureXSD');

					var saveCallback = function() {
						var dialogFields = instance._saveDialog.fields;

						instance.showMessage(
							dialogFields.messageElement,
							'info',
							Liferay.Language.get('waiting-for-an-answer')
						);

						var form = instance.getPrincipalForm();
						var structureIdInput = instance.getByName(form, 'structureId');
						var structureId = structureIdInput.val();

						if (!structureId) {
							var autoGenerateId = dialogFields.saveStructureAutogenerateIdCheckbox.get('checked');

							instance.addStructure(
								dialogFields.dialogStructureId.val(),
								autoGenerateId,
								dialogFields.dialogStructureName.val(),
								dialogFields.dialogDescription.val(),
								dialogFields.contentXSD,
								serviceCallback
							);
						}
						else {
							instance.updateStructure(
								instance.getParentStructureId(),
								dialogFields.dialogStructureId.val(),
								dialogFields.dialogStructureName.val(),
								dialogFields.dialogDescription.val(),
								dialogFields.contentXSD,
								serviceCallback
							);
						}
					};

					instance._saveDialog = new A.Dialog(
						{
							bodyContent: htmlTemplate,
							buttons: [
								{
									handler: saveCallback,
									text: Liferay.Language.get('save')
								},
								{
									handler: function() {
										this.close();
									},
									text: Liferay.Language.get('cancel')
								}
							],
							centered: true,
							modal: true,
							title: title,
							width: 550
						}
					).render();

					instance._saveDialog.fields = {
						autoGenerateIdMessage: Liferay.Language.get('autogenerate-id'),
						contentXSD: '',
						dialogDescription: instance.getById('saveStructureStructureDescription'),
						dialogStructureId: instance.getById('saveStructureStructureId'),
						dialogStructureName: instance.getById('saveStructureStructureName'),
						idInput: instance.getById('saveStructureStructureId'),
						loadDefaultStructure: instance.getById('loadDefaultStructure'),
						messageElement: instance.getById('saveStructureMessage'),
						saveStructureAutogenerateId: instance.getById('saveStructureAutogenerateId'),
						saveStructureAutogenerateIdCheckbox: instance.getById('saveStructureAutogenerateIdCheckbox'),
						showStructureIdContainer: instance.getById('showStructureIdContainer'),
						structureIdContainer: instance.getById('structureIdContainer'),
						structureNameLabel: instance.getById('structureNameLabel')
					};

					var dialogFields = instance._saveDialog.fields;

					var serviceCallback = function(message) {
						var exception = message.exception;

						if (!exception) {
							structureDescriptionInput.val(message.description);
							structureIdInput.val(message.structureId);
							structureNameInput.val(message.name);
							storedStructureXSD.val(encodeURIComponent(dialogFields.contentXSD));

							dialogFields.dialogStructureId.val(message.structureId);
							dialogFields.dialogStructureName.val(message.name);
							dialogFields.dialogDescription.val(message.description);
							dialogFields.structureNameLabel.html(message.name);
							dialogFields.saveStructureAutogenerateIdCheckbox.hide();

							if (dialogFields.loadDefaultStructure) {
								dialogFields.loadDefaultStructure.show();
							}

							dialogFields.dialogStructureId.attr('disabled', 'disabled');

							instance.showMessage(
								dialogFields.messageElement,
								'success',
								Liferay.Language.get('your-request-processed-successfully')
							);

							var structureMessage = instance.getById('structureMessage');

							structureMessage.hide();
						}
						else {
							var errorMessage = instance._translateErrorMessage(exception);

							instance.showMessage(
								dialogFields.messageElement,
								'error',
								errorMessage
							);
						}
					};

					dialogFields.saveStructureAutogenerateIdCheckbox.on(
						'click',
						function(event) {
							var checkbox = event.target;
							var value = checkbox.get('checked');

							dialogFields.saveStructureAutogenerateId.val(value);

							if (value) {
								dialogFields.dialogStructureId.attr('disabled', 'disabled').val(dialogFields.autoGenerateIdMessage);
							}
							else {
								dialogFields.dialogStructureId.attr('disabled', '').val('');
							}
						}
					);

					dialogFields.showStructureIdContainer.on(
						'click',
						function(event) {
							dialogFields.structureIdContainer.toggle();

							event.halt();
						}
					);

					dialogFields.dialogStructureName.focus();
				}
				else {
					instance._saveDialog.show();
				}

				if (openCallback) {
					openCallback.apply(instance, [instance._saveDialog]);
				}
			},

			getSelectedField: function() {
				var instance = this;

				var selected = null;
				var fields = instance.getFields();

				if (fields) {
					selected = fields.filter('.selected');
				}

				return selected ? selected.item(0) : null;
			},

			getSourceByNode: function(node) {
				var instance = this;

				return node.ancestor('li', true);
			},

			getStructureXSD: function() {
				var instance = this;

				var buffer = [];
				var structureTreeId = instance._getNamespacedId('#structureTree');
				var sourceRoots = A.all(structureTreeId + ' > li.structure-field:not(.repeated-field)').filter(':not(.parent-structure-field)');

				var root = instance._createDynamicNode('root');

				buffer.push(root.openTag);

				A.each(
					sourceRoots,
					function(item, index, collection) {
						instance._appendStructureTypeElementAndMetaData(item, buffer);
					}
				);

				buffer.push(root.closeTag);

				return buffer.join('');
			},

			getTextAreaFields: function() {
				var instance = this;

				var structureTreeId = instance._getNamespacedId('#structureTree');

				return A.all(structureTreeId + ' li[dataType=text_area] div.journal-article-component-container');
			},

			getPrincipalFieldElement: function(source) {
				var instance = this;

				var componentContainer = source.one('div.journal-article-component-container');

				return componentContainer.one('.aui-field-input');
			},

			getPrincipalForm: function(formName) {
				var instance = this;

				return A.one('form[name=' + instance.portletNamespace + (formName || 'fm1') + ']');
			},

			getNodeTypeContent: function() {
				var instance = this;

				return instance.hasStructure() ? 'dynamic-content' : 'static-content';
			},

			hasStructure: function() {
				var instance = this;

				return !!instance.getById('structureId').val();
			},

			hasTemplate: function() {
				var instance = this;
				var form = instance.getPrincipalForm();

				return !!instance.getByName(form, 'templateId').val();
			},

			helperIntersecting: function() {
				var instance = this;

				instance._helper.removeClass('not-intersecting');
			},

			helperNotIntersecting: function(helper) {
				var instance = this;

				instance._helper.addClass('not-intersecting');
			},

			hideEditContainerMessage: function() {
				var instance = this;

				var selector = instance._getNamespacedId('journalMessage');

				A.one(selector).hide();
			},

			loadDefaultStructure: function() {
				var instance = this;

				var form = instance.getPrincipalForm();

				var structureIdInput = instance.getByName(form, 'structureId');
				var templateIdInput = instance.getByName(form, 'templateId');
				var contentInput = instance.getByName(form, 'content');

				structureIdInput.val('');
				templateIdInput.val('');
				contentInput.val('');

				form.submit();
			},

			loadEditFieldOptions: function(source) {
				var instance = this;

				var fieldInstance = instance.getFieldInstance(source);

				var check = function(hiddenField, checked) {
					var id = hiddenField.get('id');
					var checkbox = A.one('#' + id + 'Checkbox');

					var value = A.DataType.Boolean.parse(checked);

					hiddenField.val(value);

					if (checkbox) {
						checkbox.set('checked', value);
					}
				};

				if (fieldInstance) {
					var editContainerWrapper = instance.getById('#journalArticleEditFieldWrapper');
					var displayAsTooltip = instance.getById('displayAsTooltip');
					var repeatable = instance.getById('repeatable');
					var fieldType = instance.getById('fieldType');
					var indexType = instance.getById('indexType');
					var instructions = instance.getById('instructions');
					var predefinedValue = instance.getById('predefinedValue');
					var required = instance.getById('required');
					var fieldLabel = instance.getById('fieldLabel');

					fieldType.val(fieldInstance.get('fieldType'));
					indexType.val(fieldInstance.get('indexType'));

					check(displayAsTooltip, fieldInstance.get('displayAsTooltip'));
					check(repeatable, fieldInstance.get('repeatable'));
					check(required, fieldInstance.get('required'));

					fieldLabel.val(fieldInstance.get('fieldLabel'));
					instructions.val(fieldInstance.get('instructions'));
					predefinedValue.val(fieldInstance.get('predefinedValue'));

					var elements = editContainerWrapper.all('input[type=text], select:not([name$=fieldType]), textarea, input[type=checkbox]');

					if (fieldInstance.get('repeated') || fieldInstance.get('parentStructureId')) {
						elements.attr('disabled', 'disabled');
					}
					else {
						elements.attr('disabled', '');
					}
				}
			},

			normalizeValue: function(value) {
				var instance = this;

				if (Lang.isUndefined(value)) {
					value = '';
				}

				return value;
			},

			openPopupWindow: function(url, title) {
				var popup = window.open(url, title, 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680');

				popup.focus();
			},

			openSaveStructureDialog: function() {
				var instance = this;

				var form = instance.getPrincipalForm();

				var structureIdInput = instance.getByName(form, 'structureId');
				var structureNameInput = instance.getByName(form, 'structureName');
				var structureDescriptionInput = instance.getByName(form, 'structureDescription');

				var structureId = structureIdInput.val();

				instance.getSaveDialog(
					function(dialog) {
						var dialogFields = dialog.fields;

						dialogFields.contentXSD = instance.getStructureXSD();

						dialogFields.dialogStructureName.val(structureNameInput.val());
						dialogFields.dialogDescription.val(structureDescriptionInput.val());
						dialogFields.dialogStructureId.attr('disabled', 'disabled').val(dialogFields.autoGenerateIdMessage);

						if (structureId) {
							dialogFields.saveStructureAutogenerateId.hide();
							dialogFields.dialogStructureId.val(structureIdInput.val());
						}

						dialog.show();

						dialog._setAlignCenter(true);
					}
				);
			},

			previewArticle: function() {
				var instance = this;

				var form = instance.getPrincipalForm();
				var auxForm = instance.getPrincipalForm('fm2');
				var articleContent = instance.getArticleContentXML();

				if (instance.structureChange()) {
					if (confirm(Liferay.Language.get('you-should-save-the-structure-first'))) {
						instance.openSaveStructureDialog();
					}
				}
				else {
					var languageIdInput = instance.getByName(form, 'languageId');
					var typeInput = instance.getByName(form, 'type');
					var versionInput = instance.getByName(form, 'version');
					var structureIdInput = instance.getByName(form, 'structureId');
					var templateIdInput = instance.getByName(form, 'templateId');

					var previewURL = themeDisplay.getPathMain() + '/journal/view_article_content?cmd=preview&groupId=' + instance.getGroupId() + '&articleId=' + instance.articleId + '&version=' + versionInput.val() + '&languageId=' + languageIdInput.val() + '&type=' + typeInput.val() + '&structureId=' + structureIdInput.val() + '&templateId=' + templateIdInput.val();

					auxForm.attr('action', previewURL);
					auxForm.attr('target', '_blank');

					var titleInput = instance.getByName(form, 'title');
					var titleAuxFormInput = instance.getByName(auxForm, 'title', true);
					var xmlAuxFormInput = instance.getByName(auxForm, 'xml', true);

					titleAuxFormInput.val(titleInput.val());
					xmlAuxFormInput.val(articleContent);

					auxForm.submit();
				}
			},

			renderEditFieldOptions: function(source) {
				var instance = this;

				var editButton = instance.getEditButton(source);
				var fields = instance.getFields();

				instance.editContainerNormalMode();

				fields.removeClass('selected');
				source.addClass('selected');

				if (instance._lastEditContainerTrigger != editButton) {
					instance.editContainerContextPanel.set('trigger', editButton);
					instance.editContainerContextPanel.show();

					instance._lastEditContainerTrigger = editButton;
				}

				instance.hideEditContainerMessage();
				instance.loadEditFieldOptions(source);

				instance.editContainerContextPanel.refreshAlign();
			},

			repeatField: function(source) {
				var instance = this;

				var _cloneFieldInstance = function(originalSource, newSource) {
					var fieldInstance = instance.getFieldInstance(originalSource).clone();
					var instanceId = generateInstanceId();

					newSource.addClass('repeated-field');
					newSource.removeClass('yui-dd-drop yui-dd-draggable');

					var newId = newSource.resetId().get('id');

					fieldsDataSet.add(newId, fieldInstance);
					fieldInstance.set('source', newSource);
					fieldInstance.set('instanceId', instanceId);

					var fieldType = fieldInstance.get('fieldType');

					if (fieldType == 'text_area') {
						var html = instance.buildHTMLEditor(fieldInstance);

						fieldInstance.set('innerHTML', html);

						var componentContainer = newSource.one('.journal-article-component-container');

						componentContainer.html(html);
					}
					else if (fieldType == 'image') {
						newSource.all('.journal-image-show-hide,.journal-image-preview').remove();
					}

					return fieldInstance;
				};

				var newSource = source.clone();

				source.placeAfter(newSource);

				_cloneFieldInstance(source, newSource);

				var children = newSource.all('.structure-field');

				children.each(
					function(item) {
						var fieldInstance = _cloneFieldInstance(item, item);
					}
				);

				instance._attachEvents();
			},

			repositionEditFieldOptions: function() {
				var instance = this;

				var editContainerWrapper = instance.getById('#journalArticleEditFieldWrapper');

				var isVisible = !editContainerWrapper.ancestor('.aui-overlaycontextpanel-hidden');

				if (isVisible) {
					setTimeout(
						function() {
							var lastSelectedField = instance.getSelectedField();
							instance.renderEditFieldOptions(lastSelectedField);
						},
						200
					);
				}
			},

			saveArticle: function(cmd) {
				var instance = this;

				var form = instance.getPrincipalForm();

				if (instance.structureChange()) {
					if (confirm(Liferay.Language.get('you-should-save-the-structure-first'))) {
						instance.openSaveStructureDialog();
					}
				}
				else if (instance.hasStructure() && !instance.hasTemplate()) {
					var templateMessage = Liferay.Language.get('please-add-a-template-to-render-this-structure');

					alert(templateMessage);

					instance.showMessage(
						'#selectTemplateMessage',
						'info',
						templateMessage,
						30000
					);

					instance.getById('selectTemplateBtn').focus();
				}
				else {
					if (!cmd) {
						cmd = instance.articleId ? 'update' : 'add';
					}

					var cmdInput = instance.getByName(form, 'cmd');
					var newArticleIdInput = instance.getByName(form, 'newArticleId');
					var articleIdInput = instance.getByName(form, 'articleId');
					var contentInput = instance.getByName(form, 'content');
					var workflowActionInput = instance.getByName(form, 'workflowAction');

					var canSubmit = instance.validateRequiredFields();

					if (canSubmit) {
						if (cmd == 'publish') {
							workflowActionInput.val(Liferay.Workflow.ACTION_PUBLISH);

							cmd = instance.articleId ? 'update' : 'add';
						}

						cmdInput.val(cmd);

						if (!instance.articleId) {
							articleIdInput.val(newArticleIdInput.val());
						}

						instance._updateLocaleCheckboxes();

						var content = instance.getArticleContentXML();

						contentInput.val(content);

						form.submit();
					}
				}
			},

			saveEditFieldOptions: function(source) {
				var instance = this;

				var fieldInstance = instance.getFieldInstance(source);

				if (fieldInstance) {
					var displayAsTooltip = instance.getById('displayAsTooltip');
					var displayAsTooltipCheckbox = instance.getById('displayAsTooltipCheckbox');
					var repeatable = instance.getById('repeatable');
					var repeatableCheckbox = instance.getById('repeatableCheckbox');
					var fieldType = instance.getById('fieldType');
					var indexType = instance.getById('indexType');
					var instructions = instance.getById('instructions');
					var predefinedValue = instance.getById('predefinedValue');
					var required = instance.getById('required');
					var requiredCheckbox = instance.getById('requiredCheckbox');
					var fieldLabel = instance.getById('fieldLabel');
					var localized = source.one('.journal-article-localized');

					var localizedValue = '';

					if (localized) {
						localizedValue = localized.val();
					}

					var journalMessage = instance.getById('journalMessage');

					A.each(
						{
							displayAsTooltip: displayAsTooltipCheckbox.attr('checked'),
							fieldType: fieldType.val(),
							indexType: indexType.val(),
							instructions: instructions.val(),
							localizedValue: localizedValue,
							predefinedValue: predefinedValue.val(),
							repeatable: repeatableCheckbox.attr('checked'),
							required: requiredCheckbox.attr('checked')
						},
						function(item, index, collection) {
							fieldInstance.set(index, item);
						}
					);

					instance.updateFieldLabelName(fieldInstance, fieldLabel.val());

					instance.showMessage(
						journalMessage,
						'success',
						Liferay.Language.get('your-request-processed-successfully')
					);

					instance.editContainerNormalMode();
				}
			},

			showMessage: function(selector, type, message, delay) {
				var instance = this;

				var journalMessage = A.one(selector);
				var className = 'save-structure-message portlet-msg-' + (type || 'success');

				journalMessage.attr('className', className);
				journalMessage.show();

				instance.editContainerContextPanel.refreshAlign();

				if (message) {
					journalMessage.html(message);
				}

				instance.timers[selector] = A.later(
					delay || 5000,
					instance,
					function() {
						journalMessage.hide();

						instance.editContainerContextPanel.refreshAlign();
					}
				);
			},

			structureChange: function(attribute) {
				var instance = this;

				var form = instance.getPrincipalForm();
				var storedStructureXSD = instance.getByName(form, 'structureXSD').val();

				var hasChanged = (storedStructureXSD != encodeURIComponent(instance.getStructureXSD()));

				return hasChanged;
			},

			unselectFields: function() {
				var instance = this;

				var selected = instance.getSelectedField();

				if (selected) {
					selected.removeClass('selected');
				}
			},

			updateFieldLabelName: function(fieldInstance, fieldLabel) {
				var instance = this;

				var repeatedSiblings = instance.getRepeatedSiblings(fieldInstance);

				repeatedSiblings.each(
					function(item, index, collection) {
						var repeatedFieldInstance = instance.getFieldInstance(item);

						repeatedFieldInstance.set('fieldLabel', fieldLabel);
					}
				);

				fieldInstance.set('fieldLabel', fieldLabel);
			},

			updateFieldVariableName: function(fieldInstance, variableName) {
				var instance = this;

				var repeatedSiblings = instance.getRepeatedSiblings(fieldInstance);

				repeatedSiblings.each(
					function(item, index, collection) {
						var repeatedFieldInstance = instance.getFieldInstance(item);

						repeatedFieldInstance.set('variableName', variableName);
					}
				);

				fieldInstance.set('variableName', variableName);
			},

			updateStructure: function(parentStructureId, structureId, name, description, xsd, callback) {
				var instance = this;

				var groupId = instance.getGroupId();

				var serviceParameterTypes = [
					'long',
					'java.lang.String',
					'java.lang.String',
					'java.lang.String',
					'java.lang.String',
					'java.lang.String',
					'com.liferay.portal.service.ServiceContext'
				];

				Liferay.Service.Journal.JournalStructure.updateStructure(
					{
						groupId: groupId,
						structureId: structureId,
						parentStructureId: parentStructureId || '',
						name: name,
						description: description,
						xsd: xsd,
						serviceContext: A.JSON.stringify(
							{
								scopeGroupId: groupId
							}
						),
						serviceParameterTypes: A.JSON.stringify(serviceParameterTypes)
					},
					function(message) {
						if (Lang.isFunction(callback)) {
							callback(message);
						}
					}
				);
			},

			updateTextAreaVisibility: function(visibility) {
				var instance = this;

				var textAreaFields = instance.getTextAreaFields();

				if (textAreaFields) {
					textAreaFields.setStyle('visibility', visibility);
				}
			},

			validateRequiredFields: function() {
				var instance = this;

				var canSubmit = true;
				var structureTreeId = instance._getNamespacedId('#structureTree');
				var fields = A.all(structureTreeId + ' li');
				var requiredFields = fields.filter('[dataRequired=true]');
				var fieldsConatainer = A.all(structureTreeId + ' li .field-container');
				var firstEmptyField = null;

				fieldsConatainer.removeClass('required-field');

				A.each(
					requiredFields,
					function(item, index, collection) {
						var fieldInstance = instance.getFieldInstance(item);
						var content = fieldInstance.getContent(item);

						if (!content) {
							var fieldConatainer = item.one('.field-container');

							fieldConatainer.addClass('required-field');

							if (canSubmit) {
								firstEmptyField = instance.getPrincipalFieldElement(item);
							}

							canSubmit = false;
						}
					}
				);

				if (firstEmptyField) {
					firstEmptyField.focus();
				}

				return canSubmit;
			},

			_appendStructureChildren: function(source, buffer, generateArticleContent) {
				var instance = this;

				var selector = '> span.folder > ul > li';

				if (!generateArticleContent) {
					selector += '.structure-field:not(.repeated-field)';
				}

				var children = source.all(selector).filter(':not(.parent-structure-field)');

				A.each(
					children,
					function(item, index, collection) {
						instance._appendStructureTypeElementAndMetaData(item, buffer, generateArticleContent);
					}
				);
			},

			_appendStructureTypeElementAndMetaData: function(source, buffer, generateArticleContent) {
				var instance = this;

				var fieldInstance = instance.getFieldInstance(source);

				if (fieldInstance) {
					var typeElement;
					var type = fieldInstance.get('fieldType');
					var indexType = fieldInstance.get('indexType');

					if (generateArticleContent) {
						var instanceId = fieldInstance.get('instanceId');

						if (!instanceId) {
							instanceId = generateInstanceId();
							fieldInstance.set('instanceId', instanceId);
						}

						typeElement = instance._createDynamicNode(
							'dynamic-element',
							{
								'instance-id': instanceId,
								name: encodeURI(fieldInstance.get('variableName')),
								type: type,
								'index-type': indexType
							}
						);
					}
					else {
						typeElement = instance._createDynamicNode(
							'dynamic-element',
							{
								name: encodeURI(fieldInstance.get('variableName')),
								type: type,
								'index-type': indexType,
								repeatable: fieldInstance.get('repeatable')
							}
						);
					}

					var dynConAttributes = null;

					if (fieldInstance.get('localized')) {
						dynConAttributes = {
							'language-id': fieldInstance.get('localizedValue')
						};
					}

					var nodeTypeContent = instance.getNodeTypeContent();
					var typeContent = instance._createDynamicNode(nodeTypeContent, dynConAttributes);
					var metadata = instance._createDynamicNode('meta-data');

					var entryInstructions = instance._createDynamicNode(
						'entry',
						{
							name: 'instructions'
						}
					);

					var entryRequired = instance._createDynamicNode(
						'entry',
						{
							name: 'required'
						}
					);

					var displayAsTooltip = instance._createDynamicNode(
						'entry',
						{
							name: 'displayAsTooltip'
						}
					);

					var label = instance._createDynamicNode(
						'entry',
						{
							name: 'label'
						}
					);

					var predefinedValue = instance._createDynamicNode(
						'entry',
						{
							name: 'predefinedValue'
						}
					);

					buffer.push(typeElement.openTag);

					if (!generateArticleContent) {
						instance._appendStructureFieldOptionsBuffer(source, buffer);
					}

					instance._appendStructureChildren(source, buffer, generateArticleContent);

					if (!generateArticleContent) {
						buffer.push(metadata.openTag);
							var displayAsTooltipVal = instance.normalizeValue(
								fieldInstance.get('displayAsTooltip')
							);
							buffer.push(displayAsTooltip.openTag);
							buffer.push('<![CDATA[' + displayAsTooltipVal + ']]>');
							buffer.push(displayAsTooltip.closeTag);

							var requiredVal = instance.normalizeValue(
								fieldInstance.get('required')
							);
							buffer.push(entryRequired.openTag);
							buffer.push('<![CDATA[' + requiredVal + ']]>');
							buffer.push(entryRequired.closeTag);

							var instructionsVal = instance.normalizeValue(
								fieldInstance.get('instructions')
							);
							buffer.push(entryInstructions.openTag);
							buffer.push('<![CDATA[' + instructionsVal + ']]>');
							buffer.push(entryInstructions.closeTag);

							var fieldLabelVal = instance.normalizeValue(
								fieldInstance.get('fieldLabel')
							);
							buffer.push(label.openTag);
							buffer.push('<![CDATA[' + fieldLabelVal + ']]>');
							buffer.push(label.closeTag);

							var predefinedValueVal = instance.normalizeValue(
								fieldInstance.get('predefinedValue')
							);
							buffer.push(predefinedValue.openTag);
							buffer.push('<![CDATA[' + predefinedValueVal + ']]>');
							buffer.push(predefinedValue.closeTag);
						buffer.push(metadata.closeTag);
					}
					else if (generateArticleContent) {
						buffer.push(typeContent.openTag);

						var appendOptions = (type == 'multi-list' || type == 'list');

						if (appendOptions) {
							instance._appendStructureFieldOptionsBuffer(source, buffer, generateArticleContent);
						}
						else {
							var content = fieldInstance.getContent(source) || '';

							buffer.push('<![CDATA[' + content + ']]>');
						}

						buffer.push(typeContent.closeTag);
					}

					buffer.push(typeElement.closeTag);
				}
			},

			_appendStructureFieldOptionsBuffer: function(source, buffer, generateArticleContent) {
				var instance = this;

				var fieldInstance = instance.getFieldInstance(source);
				var type = fieldInstance.get('fieldType');
				var optionsList = source.all('> .folder > .field-container > .journal-article-component-container > .journal-list-subfield option');

				if (optionsList) {
					A.each(
						optionsList,
						function(item, index, collection) {
							var optionKey = instance._formatOptionsKey(item.text());
							var optionValue = item.val();

							if (!generateArticleContent) {
								var typeElementOption = instance._createDynamicNode(
									'dynamic-element',
									{
										name: optionKey,
										type: optionValue,
										'repeatable': fieldInstance.get('repeatable')
									}
								);

								buffer.push(typeElementOption.openTag + typeElementOption.closeTag);
							}
							else {
								if (item.get('selected')) {
									var multiList = (type == 'multi-list');
									var option = instance._createDynamicNode('option');

									if (multiList) {
										buffer.push(option.openTag);
									}

									buffer.push('<![CDATA[' + optionValue + ']]>');

									if (multiList) {
										buffer.push(option.closeTag);
									}
								}
							}
						}
					);
				}
			},

			_attachDelegatedEvents: function() {
				var instance = this;

				var journalArticleContainerId = instance._getNamespacedId('#journalArticleContainer');

				var addListItem = function(event) {
					var icon = event.currentTarget;
					var iconParent = icon.get('parentNode');
					var select = iconParent.get('parentNode').one('select');
					var keyInput = iconParent.one('input.journal-list-key');
					var key = instance._formatOptionsKey(keyInput.val());
					var valueInput = iconParent.one('input.journal-list-value');
					var value = valueInput.val();

					if (key && value) {
						var options = select.all('option');

						options.each(
							function(item, index, collection) {
								var keyText = Lang.trim(key);
								var itemText = Lang.trim(item.text());

								if (itemText.toLowerCase() == keyText.toLowerCase()) {
									item.remove();
								}
							}
						);

						var option = A.Node.create(TPL_OPTION).val(value).text(key);

						select.append(option);
						option.attr('selected', 'selected');
						keyInput.val('').focus();
						valueInput.val('value');
					}
					else {
						keyInput.focus();
					}
				};

				var keyPressAddItem = function(event) {
					var btnScope = event.currentTarget.get('parentNode').one('span.journal-add-field');

					if (event.keyCode == 13) {
						event.currentTarget = btnScope;

						addListItem.apply(event.currentTarget, arguments);
					}
				};

				var removeListItem = function(event) {
					var icon = event.currentTarget;
					var select = icon.get('parentNode').one('select').focus();
					var options = select.all('option');

					options.each(
						function(item, index, collection) {
							if (item.attr('selected')) {
								item.remove();
							}
						}
					);
				};

				var container = A.one(journalArticleContainerId);

				container.delegate(
					'click',
					function(event) {
						var checkbox = event.currentTarget;
						var source = instance.getSourceByNode(checkbox);

						instance._updateLocaleState(source, checkbox);
					},
					'.journal-article-localized-checkbox .aui-field-input-choice'
				);

				container.delegate(
					'click',
					function(event) {
						var source = instance.getSourceByNode(event.currentTarget);

						instance.repeatField(source);
					},
					'.repeatable-field-add'
				);

				container.delegate(
					'click',
					function(event) {
						var source = instance.getSourceByNode(event.currentTarget);

						instance.closeField(source);
					},
					'.repeatable-field-delete'
				);

				container.delegate(
					'mouseenter',
					function(event) {
						var source = instance.getSourceByNode(event.currentTarget);

						source.addClass('repeatable-border');
					},
					'.repeatable-field-image'
				);

				container.delegate(
					'mouseleave',
					function(event) {
						var source = instance.getSourceByNode(event.currentTarget);

						source.removeClass('repeatable-border');
					},
					'.repeatable-field-image'
				);

				container.delegate('keypress', keyPressAddItem, '.journal-list-key');
				container.delegate('keypress', keyPressAddItem, '.journal-list-value');
				container.delegate('click', addListItem, '.journal-add-field');
				container.delegate('click', removeListItem, '.journal-delete-field');

				container.delegate(
					'click',
					function(event) {
						var button = event.currentTarget;
						var buttonValue = null;
						var imagePreview = button.ancestor('.journal-image-preview');
						var imageWrapper = imagePreview.one('.journal-image-wrapper');
						var imageDelete = instance.getByName(imagePreview, 'journalImageDelete');

						if (imageDelete.val() == '') {
							imageDelete.val('delete');
							imageWrapper.hide();

							buttonValue = Liferay.Language.get('cancel');
						}
						else {
							imageDelete.val('');
							imageWrapper.show();

							buttonValue = Liferay.Language.get('delete');
						}

						button.val(buttonValue);
					},
					'[name="' + instance.portletNamespace + 'journalImageDeleteButton"]'
				);

				container.delegate(
					'click',
					function(event) {
						var link = event.currentTarget;
						var imagePreviewDiv = link.get('parentNode').get('parentNode').one('.journal-image-preview');

						var showLabel = link.one('.show-label').show();
						var hideLabel = link.one('.hide-label').show();

						var visible = imagePreviewDiv.hasClass('aui-helper-hidden');

						if (visible) {
							showLabel.hide();
							hideLabel.show();
						}
						else {
							showLabel.show();
							hideLabel.hide();
						}

						imagePreviewDiv.toggle();
					},
					'.journal-image-link'
				);

				var _attachButtonInputSelector = function(id, title, handlerName) {
					var buttonId = '.journal-' + id + '-button .aui-button-input';

					container.delegate(
						'click',
						function(event) {
							var button = event.currentTarget;
							var input = button.ancestor('.journal-article-component-container').one('.aui-field-input');
							var selectUrl = button.attr('data-' + id + 'Url');

							window[instance.portletNamespace + handlerName] = function(url) {
								input.val(url);
							};

							instance.openPopupWindow(selectUrl, title);
						},
						buttonId
					);
				};

				_attachButtonInputSelector('documentlibrary', 'DocumentLibrary', 'selectDocumentLibrary');
				_attachButtonInputSelector('imagegallery', 'ImageGallery', 'selectImageGallery');

				container.delegate(
					'mouseover',
					function(event) {
						var image = event.currentTarget;
						var source = instance.getSourceByNode(image);
						var fieldInstance = instance.getFieldInstance(source);

						if (fieldInstance) {
							var instructions = fieldInstance.get('instructions');

							Liferay.Portal.ToolTip.show(this, instructions);
						}
					},
					'img.journal-article-instructions-container'
				);

				var variableNameSelector = '[name="' + instance.portletNamespace + 'variableName"]';

				container.delegate('keypress', A.bind(instance._onKeypressVariableName, instance), variableNameSelector);
				container.delegate('keyup', A.bind(instance._onKeyupVariableName, instance), variableNameSelector);
			},

			_attachEditContainerEvents: function(attribute) {
				var instance = this;

				var editContainerWrapper = instance.getById('#journalArticleEditFieldWrapper');
				var editContainerCheckboxes = editContainerWrapper.all('input[type=checkbox]');
				var editContainerInputs = editContainerWrapper.all('input[type=text],select');
				var editContainerTextareas = editContainerWrapper.all('textarea');
				var editFieldCancelButton = editContainerWrapper.one('.cancel-button .aui-button-input');
				var editFieldCloseButton = editContainerWrapper.one('.close-button .aui-button-input');
				var editFieldSaveButton = editContainerWrapper.one('.save-button .aui-button-input');
				var enableLocale = instance.getById('enableLocalizationCheckbox');
				var languageIdSelect = instance.getById('languageIdSelect');
				var wrapper = instance.getById('journalArticleWrapper');

				editContainerCheckboxes.detach('click');
				editContainerInputs.detach('change');
				editContainerInputs.detach('keypress');
				editContainerTextareas.detach('change');
				editContainerTextareas.detach('keypress');
				editFieldCancelButton.detach('click');
				editFieldCloseButton.detach('click');
				editFieldSaveButton.detach('click');

				var editContainerSaveMode = instance.editContainerSaveMode;

				editContainerCheckboxes.on('click', editContainerSaveMode, instance);
				editContainerInputs.on('change', editContainerSaveMode, instance);
				editContainerInputs.on('keypress', editContainerSaveMode, instance);
				editContainerTextareas.on('change', editContainerSaveMode, instance);
				editContainerTextareas.on('keypress', editContainerSaveMode, instance);

				editFieldSaveButton.on(
					'click',
					function() {
						var source = instance.getSelectedField();

						instance.saveEditFieldOptions(source);
					}
				);

				var closeEditField = instance.closeEditFieldOptions;

				editFieldCancelButton.on('click', closeEditField, instance);
				editFieldCloseButton.on('click', closeEditField, instance);

				enableLocale.on(
					'click',
					function(event) {
						var checked = enableLocale.get('checked');

						wrapper.toggleClass('localization-disabled', !checked);

						A.io.request(
							themeDisplay.getPathMain() + '/portal/session_click',
							{
								data: {
									'liferay_journal_localization': checked
								}
							}
						);
					}
				);
			},

			_attachEvents: function() {
				var instance = this;

				var closeButtons = instance.getCloseButtons();
				var editButtons = instance.getEditButtons();
				var repeatableButtons = instance.getRepeatableButtons();
				var defaultLanguageIdSelect = instance.getById('defaultLanguageIdSelect');
				var downloadArticleContentBtn = instance.getById('downloadArticleContentBtn');
				var fieldsContainer = instance.getById('journalArticleContainer');
				var languageIdSelect = instance.getById('languageIdSelect');
				var previewArticleBtn = instance.getById('previewArticleBtn');
				var publishButton = instance.getById('publishButton');
				var saveButton = instance.getById('saveButton');

				var containerInputs = fieldsContainer.all('.journal-article-component-container .aui-field-input');

				closeButtons.detach('click');
				containerInputs.detach('change');
				defaultLanguageIdSelect.detach('change');
				editButtons.detach('click');
				languageIdSelect.detach('change');
				repeatableButtons.detach('click');

				if (publishButton) {
					publishButton.detach('click');
				}

				if (saveButton) {
					saveButton.detach('click');
				}

				editButtons.on(
					'click',
					function(event) {
						var source = instance.getSourceByNode(event.currentTarget);

						instance.renderEditFieldOptions(source);
					}
				);

				repeatableButtons.on(
					'click',
					function(event) {
						var source = instance.getSourceByNode(event.currentTarget);

						instance.repeatField(source);
					}
				);

				closeButtons.on(
					'click',
					function(event) {
						var source = instance.getSourceByNode(event.currentTarget);

						instance.closeField(source);
					}
				);

				if (saveButton) {
					saveButton.on(
						'click',
						function() {
							instance.saveArticle();
						}
					);
				}

				if (publishButton) {
					publishButton.on(
						'click',
						function() {
							instance.saveArticle('publish');
						}
					);
				}

				if (downloadArticleContentBtn) {
					downloadArticleContentBtn.detach('click');

					downloadArticleContentBtn.on(
						'click',
						function() {
							instance.downloadArticleContent();
						}
					);
				}

				if (previewArticleBtn) {
					previewArticleBtn.detach('click');

					previewArticleBtn.on(
						'click',
						function() {
							instance.previewArticle();
						}
					);
				}

				languageIdSelect.on(
					'change',
					function() {
						instance.changeLanguageView();
					}
				);

				defaultLanguageIdSelect.on(
					'change',
					function() {
						instance.changeLanguageView();
					}
				);

				var changeStructureBtn = instance.getById('changeStructureBtn');
				var changeTemplateBtn = instance.getById('changeTemplateBtn');
				var editStructureBtn = instance.getById('editStructureBtn');
				var loadDefaultStructureBtn = instance.getById('loadDefaultStructure');
				var saveStructureBtn = instance.getById('saveStructureBtn');
				var saveStructureTriggers = A.one('.journal-save-structure-trigger');

				changeStructureBtn.detach('click');
				editStructureBtn.detach('click');
				saveStructureBtn.detach('click');
				saveStructureTriggers.detach('click');

				changeStructureBtn.on(
					'click',
					function(event) {
						if (confirm(Liferay.Language.get('selecting-a-new-structure-will-change-the-available-input-fields-and-available-templates'))) {
							var url = event.target.attr('dataChangeStructureUrl');
							instance.openPopupWindow(url, 'ChangeStructure');
						}
					}
				);

				if (changeTemplateBtn) {
					changeTemplateBtn.detach('click');

					changeTemplateBtn.on(
						'click',
						function(event) {
							if (confirm(Liferay.Language.get('selecting-a-template-will-change-the-structure,-available-input-fields,-and-available-templates'))) {
								var url = event.target.attr('dataChangeTemplateUrl');
								instance.openPopupWindow(url, 'ChangeTemplate');
							}
						}
					);
				}

				if (loadDefaultStructureBtn) {
					loadDefaultStructureBtn.detach('click');

					loadDefaultStructureBtn.on(
						'click',
						function() {
							instance.loadDefaultStructure();
						}
					);
				}

				saveStructureBtn.on(
					'click',
					function() {
						instance.openSaveStructureDialog();
					}
				);

				saveStructureTriggers.on(
					'click',
					function(event) {
						event.preventDefault();

						saveStructureBtn.simulate('click');
					}
				);

				var body = A.getBody();

				editStructureBtn.on(
					'click',
					function(event) {
						if (body.hasClass('portlet-journal-edit-mode')) {
							instance.disableEditMode();
						}
						else {
							instance.enableEditMode();
						}
					}
				);

				containerInputs.on(
					'change',
					function() {
						window[instance.portletNamespace + 'contentChanged']();
					}
				);
			},

			_createDynamicNode: function(nodeName, attributeMap) {
				var instance = this;

				var attrs = [];
				var typeElement = [];

				if (!nodeName) {
					nodeName = 'dynamic-element';
				}

				var typeElementModel = ['<', nodeName, (attributeMap ? ' ' : ''), , '>', ,'</', nodeName, '>'];

				A.each(
					attributeMap || {},
					function(item, index, collection) {
						if (item !== undefined) {
							attrs.push([index, '="', item, '" '].join(''));
						}
					}
				);

				typeElementModel[3] = attrs.join('').replace(/[\s]+$/g, '');
				typeElement = typeElementModel.join('').replace(/></, '>><<').replace(/ +>/, '>').split(/></);

				return {
					closeTag: typeElement[1],
					openTag: typeElement[0]
				};
			},

			_createFieldHTMLTemplate: function(field) {
				var instance = this;

				var fieldContainer = field.getFieldContainer();
				var fieldElementContainer = field.getFieldElementContainer();
				var innerHTML = field.get('innerHTML');
				var type = field.get('fieldType');

				fieldElementContainer.html(innerHTML);

				return fieldContainer.html();
			},

			_dropField: function() {
				var instance = this;

				instance.repositionEditFieldOptions();
			},

			_emptyFunction: function() {
				return '';
			},

			_fieldInstanceFactory: function(options) {
				var instance = this;

				var type;

				if (Lang.isString(options)) {
					type = options;
					options = null;
				}
				else {
					type = options.fieldType;
				}

				options = options || {};

				var model = {
					'boolean': Journal.FieldModel.Boolean,
					'document_library': Journal.FieldModel.DocumentLibrary,
					'image': Journal.FieldModel.Image,
					'image_gallery': Journal.FieldModel.ImageGallery,
					'link_to_layout': Journal.FieldModel.LinkToPage,
					'list': Journal.FieldModel.List,
					'multi-list': Journal.FieldModel.MultiList,
					'selection_break': Journal.FieldModel.SelectionBreak,
					'text': Journal.FieldModel.Text,
					'text_area': Journal.FieldModel.TextArea,
					'text_box': Journal.FieldModel.TextBox
				};

				options = A.merge(model[type], options);

				var fieldInstance = new Journal.StructureField(
					options,
					instance.portletNamespace
				).render();

				fieldInstance.get('fieldLabel');

				return fieldInstance;
			},

			_formatOptionsKey: function(s) {
				return s.replace(/\W+/g, ' ').replace(/^\W+|\W+$/g, '').replace(/ /g, '_');
			},

			_getNamespacedId: function(id, namespace, prefix) {
				var instance = this;

				if (!Lang.isString(namespace)) {
					namespace = instance.portletNamespace;
				}

				if (!Lang.isString(prefix)) {
					prefix = '#';
				}

				id = id.replace(/^#/, '');

				return prefix + namespace + id;
			},

			_initializePageLoadFieldInstances: function() {
				var instance = this;

				var fields = instance.getFields();

				fields.each(
					function(item, index, collection) {
						var fieldInstance = instance.getFieldInstance(item);

						if (!fieldInstance) {
							var componentName = item.attr('dataName');
							var componentType = item.attr('dataType');
							var displayAsTooltip = item.attr('dataDisplayAsTooltip');
							var fieldLabel = item.attr('dataLabel');
							var indexType = item.attr('dataIndexType');
							var instanceId = item.attr('dataInstanceId');
							var instructions = item.attr('dataInstructions');
							var localized = item.one('.journal-article-localized');
							var parentValue = item.attr('dataParentStructureId');
							var predefinedValue = item.attr('dataPredefinedValue');
							var repeatable = item.attr('dataRepeatable');
							var required = item.attr('dataRequired');

							if (localized) {
								var localizedValue = localized.val();
							}

							var isLocalized = (String(localizedValue) != 'false');

							fieldInstance = instance._fieldInstanceFactory(
								{
									displayAsTooltip: displayAsTooltip,
									fieldLabel: fieldLabel,
									instanceId: instanceId,
									instructions: instructions,
									localized: isLocalized,
									localizedValue: localizedValue,
									parentStructureId: parentValue,
									predefinedValue: predefinedValue,
									repeatable: repeatable,
									required: required,
									source: item,
									fieldType: componentType,
									indexType: indexType,
									variableName: componentName
								}
							);
						}

						var id = item.get('id');

						fieldsDataSet.add(id, fieldInstance);
					}
				);
			},

			_initializeTagsSuggestionContent: function() {
				var instance = this;

				window[instance.portletNamespace + 'getSuggestionsContent'] = function() {
					var content = [];

					instance.getFields().each(
						function(item, index, collection) {
							var fieldInstance = instance.getFieldInstance(item);
							var fieldContent = fieldInstance.getContent(item);

							content.push(fieldContent);
						}
					);

					return content.join(' ');
				};
			},

			_onKeyupVariableName: function(event) {
				var instance = this;

				var variableNameInput = event.currentTarget;
				var source = instance.getSourceByNode(variableNameInput);
				var fieldInstance = instance.getFieldInstance(source);

				if (fieldInstance) {
					var variableNameValue = variableNameInput.val();

					instance.updateFieldVariableName(fieldInstance, variableNameValue);
				}
			},

			_onKeypressVariableName: function(event) {
				var instance = this;

				var allowedKeyCodes = [8, 37, 39, 46];
				var charCode = event.charCode;
				var keyCode = event.keyCode;

				if ((A.Array.indexOf(allowedKeyCodes, keyCode) != -1)) {
					return;
				}

				var regex = /^[\w_-]$/;
				var typed = String.fromCharCode(keyCode);

				if (!regex.test(typed)) {
					event.halt();
				}
			},

			_stripComponentType: function(type) {
				return type.toLowerCase().replace(/[^a-z]+/g, '');
			},

			_translateErrorMessage: function(exception) {
				var errorText = '';

				if (exception.indexOf('StructureXsdException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-xsd');
				}
				else if (exception.indexOf('DuplicateStructureIdException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-unique-id');
				}
				else if (exception.indexOf('StructureDescriptionException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-description');
				}
				else if (exception.indexOf('StructureIdException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-id');
				}
				else if (exception.indexOf('StructureInheritanceException') > -1) {
					errorText = Liferay.Language.get('this-structure-is-already-within-the-inheritance-path-of-the-selected-parent-please-select-another-parent-structure');
				}
				else if (exception.indexOf('StructureNameException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-name');
				}
				else if (exception.indexOf('NoSuchStructureException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-id');
				}
				else if (exception.indexOf('ArticleContentException') > -1) {
					errorText = Liferay.Language.get('please-enter-valid-content');
				}
				else if (exception.indexOf('ArticleIdException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-id');
				}
				else if (exception.indexOf('ArticleTitleException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-valid-name');
				}
				else if (exception.indexOf('DuplicateArticleIdException') > -1) {
					errorText = Liferay.Language.get('please-enter-a-unique-id');
				}

				return errorText;
			},

			_updateLocaleCheckboxes: function() {
				var instance = this;

				var fields = instance.getFields();

				fields.each(
					function(item, index, collection) {
						var checkbox = item.one('.journal-article-localized-checkbox .aui-field-input-choice');

						if (checkbox && checkbox.get('checked')) {
							instance._updateLocaleState(item, checkbox);
						}
					}
				);
			},

			_updateLocaleState: function(source, checkbox) {
				var instance = this;

				var isLocalized = checkbox.get('checked');
				var fieldInstance = instance.getFieldInstance(source);
				var languageIdSelect = instance.getById('languageIdSelect');
				var defaultLocale = instance.getDefaultLocale();
				var localizedValue = source.one('.journal-article-localized');

				var selectedLocale = defaultLocale;

				if (languageIdSelect) {
					selectedLocale = languageIdSelect.val();
				}

				var setLocalizedValue = function(value) {
					if (localizedValue) {
						localizedValue.val(value);
					}
				};

				if (isLocalized) {
					setLocalizedValue(selectedLocale);
				}
				else if (!confirm(Liferay.Language.get('unchecking-this-field-will-remove-localized-data-for-languages-not-shown-in-this-view'))) {
					checkbox.attr('checked', true);

					setLocalizedValue(selectedLocale);
				}
				else {
					setLocalizedValue(false);
				}

				var fieldInstance = instance.getFieldInstance(source);

				fieldInstance.set('localized', checkbox.get('checked'));
			},

			_updateOriginalStructureXSD: function() {
				var instance = this;

				var form = instance.getPrincipalForm();

				var currentXSD = encodeURIComponent(instance.getStructureXSD());
				var structureXSDInput = instance.getByName(form, 'structureXSD');

				structureXSDInput.val(currentXSD);
			}
		};

		var StructureField = A.Component.create(
			{
				ATTRS: {
					content: {
						validator: Lang.isString,
						value: ''
					},

					displayAsTooltip: {
						setter: function(v) {
							var instance = this;

							return instance.setAttribute('displayAsTooltip', D.Boolean.parse(v));
						},
						valueFn: function() {
							var instance = this;

							return instance.getAttribute('displayAsTooltip', true);
						}
					},

					fieldLabel: {
						setter: function(v) {
							var instance = this;

							return instance.setFieldLabel(v);
						},
						valueFn: function() {
							var instance = this;

							return instance.getAttribute('fieldLabel', '');
						}
					},

					fieldType: {
						setter: function(v) {
							var instance = this;

							return instance.setAttribute('fieldType', v);
						},
						validator: Lang.isString,
						value: ''
					},

					localized: {
						valueFn: function() {
							var instance = this;

							var localizedValue = instance.getLocalizedValue();

							return (String(localizedValue) == 'true');
						}
					},

					localizedValue: {
						getter: function() {
							var instance = this;

							return instance.getLocalizedValue();
						}
					},

					indexType: {
						setter: function(v) {
							var instance = this;

							return instance.setAttribute('IndexType', v);
						},
						valueFn: function() {
							var instance = this;

							return instance.getAttribute('IndexType', '');
						}
					},

					innerHTML: {
						validator: Lang.isString,
						value: TPL_STRUCTURE_FIELD_INPUT
					},

					instructions: {
						setter: function(v) {
							var instance = this;

							return instance.setInstructions(v);
						},
						valueFn: function() {
							var instance = this;

							return instance.getAttribute('instructions', '');
						}
					},

					instanceId: {
						setter: function(v) {
							var instance = this;

							return instance.setInstanceId(v);
						},
						valueFn: function() {
							var instance = this;

							var randomInstanceId = generateInstanceId();

							return instance.getAttribute('instanceId', randomInstanceId);
						}
					},

					optionsEditable: {
						validator: Lang.isBoolean,
						value: true
					},

					parentStructureId: {
						setter: function(v) {
							var instance = this;

							return instance.setAttribute('parentStructureId', v);
						},
						valueFn: function() {
							var instance = this;

							return instance.getAttribute('parentStructureId', '');
						}
					},

					predefinedValue: {
						setter: function(v) {
							var instance = this;

							return instance.setAttribute('predefinedValue', v);
						},
						valueFn: function() {
							var instance = this;

							return instance.getAttribute('predefinedValue', '');
						}
					},

					repeatable: {
						setter: function(v) {
							var instance = this;

							return instance.setRepeatable(D.Boolean.parse(v));
						},
						valueFn: function() {
							var instance = this;

							return instance.getAttribute('repeatable', false);
						}
					},

					repeated: {
						getter: function() {
							var instance = this;

							return instance.get('source').hasClass('repeated-field');
						}
					},

					required: {
						setter: function(v) {
							var instance = this;

							return instance.setAttribute('required', D.Boolean.parse(v));
						},
						valueFn: function() {
							var instance = this;

							return instance.getAttribute('required', false);
						}
					},

					source: {
						value: null
					},

					variableName: {
						setter: function(v) {
							var instance = this;

							return instance.setVariableName(v);
						},
						validator: Lang.isString,
						valueFn: function() {
							var instance = this;

							return instance.getAttribute('name');
						}
					}
				},

				EXTENDS: A.Widget,

				NAME: 'structurefield',

				constructor: function(config, portletNamespace) {
					var instance = this;

					instance._lazyAddAttrs = false;

					instance.portletNamespace = portletNamespace;

					StructureField.superclass.constructor.apply(this, arguments);
				},

				UI_ATTRS: ['optionsEditable'],

				prototype: {
					cloneableAttrs: [
						'displayAsTooltip',
						'fieldLabel',
						'fieldType',
						'indexType',
						'innerHTML',
						'instructions',
						'localized',
						'localizedValue',
						'predefinedValue',
						'repeatable',
						'required',
						'variableName'
					],

					initializer: function() {
						var instance = this;

						var propagateAttr = instance.propagateAttr;

						A.each(
							instance.cloneableAttrs,
							function(item, index, collection) {
								instance.after(item + 'Change', propagateAttr);
							}
						);
					},

					canDrop: function() {
						var instance = this;

						return Journal.prototype.canDrop.apply(instance, arguments);
					},

					clone: function() {
						var instance = this;

						var options = {};
						var portletNamespace = instance.portletNamespace;

						A.each(
							instance.cloneableAttrs,
							function(item, index, collection) {
								options[item] = instance.get(item);
							}
						);

						options.source = null;

						return new StructureField(options, portletNamespace);
					},

					createInstructionsContainer: function(value) {
						return A.Node.create(TPL_INSTRUCTIONS_CONTAINER).html(value);
					},

					createTooltipImage: function() {
						return A.Node.create(TPL_TOOLTIP_IMAGE);
					},

					getAttribute: function(key, defaultValue) {
						var instance = this;

						var value = undefined;
						var source = instance.get('source');

						if (source) {
							value = source.attr('data' + key);
						}

						if (Lang.isUndefined(value) && !Lang.isUndefined(defaultValue)) {
							value = defaultValue;
						}

						return value;
					},

					getByName: function() {
						var instance = this;

						return Journal.prototype.getByName.apply(instance, arguments);
					},

					getComponentType: function() {
						var instance = this;

						return Journal.prototype.getComponentType.apply(instance, arguments);
					},

					getContent: function(source) {
						var instance = this;

						var content;
						var type = instance.get('fieldType');
						var componentContainer = source.one('div.journal-article-component-container');

						var principalElement = componentContainer.one('.aui-field-input');

						if (type == 'boolean') {
							content = principalElement.attr('checked');
						}
						else if (type == 'text_area') {
							var editorName = source.one('iframe').attr('name');
							var editorReference = window[editorName];

							if (editorReference && Lang.isFunction(editorReference.getHTML)) {
								content = editorReference.getHTML();
							}
						}
						else if (type == 'multi-list') {
							var output = [];
							var options = principalElement.all('option');

							options.each(
								function(item, index, collection) {
									if (item.get('selected')) {
										var value = item.val();

										output.push(value);
									}
								}
							);

							content = output.join(',');
						}
						else if (type == 'image') {
							var imageContent = componentContainer.one('.journal-image-content');
							var imageDelete = instance.getByName(componentContainer, 'journalImageDelete');

							if (imageDelete && imageDelete.val() == 'delete') {
								content = 'delete';
							}
							else {
								if (imageContent) {
									content = imageContent.val() || principalElement.val();
								}
							}
						}
						else {
							if (principalElement) {
								content = principalElement.val();
							}
						}

						instance.set('content', content);

						return content;
					},

					getFieldContainer: function() {
						var instance = this;

						if (!instance.fieldContainer) {
							var htmlTemplate = [];
							var fieldLabel = Liferay.Language.get('field');
							var localizedLabelLanguage = Liferay.Language.get('localized');
							var requiredFieldLanguage = Liferay.Language.get('this-field-is-required');
							var variableNameLanguage = Liferay.Language.get('variable-name');

							var optionsEditable = instance.get('optionsEditable');

							var editBtnTemplate = instance.getById('editBtnTemplate');
							var editBtnTemplateHTML = '';

							if (editBtnTemplate) {
								editBtnTemplateHTML = editBtnTemplate.html();
							}

							var articleButtonsRowCSSClass = '';

							if (!optionsEditable) {
								articleButtonsRowCSSClass = 'aui-helper-hidden';
							}

							var repeatableBtnTemplate = instance.getById('repeatableBtnTemplate');
							var repeatableBtnTemplateHTML = '';

							if (repeatableBtnTemplate) {
								repeatableBtnTemplateHTML = repeatableBtnTemplate.html();
							}

							var fieldType = instance.get('fieldType');
							var required = instance.get('required');
							var variableName = instance.get('variableName') + getUID();
							var randomInstanceId = generateInstanceId();

							htmlTemplate = A.substitute(
								TPL_FIELD_CONTAINER,
								{
									articleButtonsRowCSSClass: articleButtonsRowCSSClass,
									editBtnTemplateHTML: editBtnTemplateHTML,
									fieldLabel: fieldLabel,
									localizedLabelLanguage: localizedLabelLanguage,
									instanceId: randomInstanceId,
									portletNamespace: instance.portletNamespace,
									repeatableBtnTemplateHTML: repeatableBtnTemplateHTML,
									requiredFieldLanguage: requiredFieldLanguage,
									variableName: variableName,
									variableNameLanguage: variableNameLanguage
								}
							);

							instance.fieldContainer = A.Node.create(htmlTemplate);

							var source = instance.fieldContainer.one('li');

							source.setAttribute('dataName', variableName);
							source.setAttribute('dataRequired', required);
							source.setAttribute('dataType', fieldType);
							source.setAttribute('dataInstanceId', randomInstanceId);

							if (!instance.canDrop(source)) {
								instance.fieldContainer.one('.folder-droppable').remove();
							}
						}

						return instance.fieldContainer;
					},

					getFieldElementContainer: function() {
						var instance = this;

						if (!instance.fieldElementContainer) {
							instance.fieldElementContainer = instance.getFieldContainer().one('div.journal-article-component-container');
						}

						return instance.fieldElementContainer;
					},

					getFieldInstance: function() {
						var instance = this;

						return Journal.prototype.getFieldInstance.apply(instance, arguments);
					},

					getFieldLabelElement: function() {
						var instance = this;

						var source = instance.get('source');

						if (!source) {
							source = instance.getFieldContainer().one('li');
						}

						return source.one('> .folder > .field-container .journal-article-field-label');
					},

					getLocalizedValue: function() {
						var instance = this;

						var source = instance.get('source');

						if (source) {
							var input = source.one('.journal-article-localized');
						}

						return input ? input.val() : 'false';
					},

					getRepeatedSiblings: function() {
						var instance = this;

						return Journal.prototype.getRepeatedSiblings.apply(instance, [instance]);
					},

					propagateAttr: function(event) {
						var instance = this;

						var siblings = instance.getRepeatedSiblings();

						if (siblings) {
							siblings.each(
								function(item, index, collection) {
									var fieldInstance = instance.getFieldInstance(item);

									if (fieldInstance) {
										fieldInstance.set(event.attrName, event.newVal);
									}
								}
							);
						}
					},

					setFieldLabel: function(value) {
						var instance = this;

						var fieldLabel = instance.getFieldLabelElement();

						if (!value) {
							value = instance.get('variableName');
						}

						fieldLabel.one('span').html(value);

						instance.setAttribute('fieldLabel', value);

						return value;
					},

					setInstanceId: function(value) {
						var instance = this;

						instance.setAttribute('instanceId', value);

						var type = instance.get('fieldType');
						var source = instance.get('source');

						if ((type == 'image') && source) {
							var isLocalized = instance.get('localized');
							var inputFileName = 'structure_image_' + value + '_' + instance.get('variableName');
							var inputFile = source.one('.journal-article-component-container [type=file]');

							if (isLocalized) {
								inputFileName += '_' + instance.get('localizedValue');
							}

							inputFile.attr('name', inputFileName);
						}

						return value;
					},

					setInstructions: function(value) {
						var instance = this;

						var source = instance.get('source');

						if (source) {
							var fieldInstance = instance.getFieldInstance(source);

							instance.setAttribute('instructions', value);

							if (fieldInstance) {
								var fieldContainer = source.one('> .folder > .field-container');
								var label = fieldInstance.getFieldLabelElement();
								var tooltipIcon = label.one('.journal-article-instructions-container');
								var journalInstructionsMessage = fieldContainer.one('.journal-article-instructions-message');
								var displayAsTooltip = fieldInstance.get('displayAsTooltip');

								if (tooltipIcon) {
									tooltipIcon.remove();
								}

								if (journalInstructionsMessage) {
									journalInstructionsMessage.remove();
								}

								if (value) {
									if (!displayAsTooltip) {
										var instructionsMessage = fieldInstance.createInstructionsContainer(value);
										var requiredMessage = fieldContainer.one('.journal-article-required-message');

										requiredMessage.placeAfter(instructionsMessage);
									}
									else {
										label.append(fieldInstance.createTooltipImage());
									}
								}
							}
						}

						return value;
					},

					setRepeatable: function(value) {
						var instance = this;

						var source = instance.get('source');

						instance.setAttribute('repeatable', value);

						if (source) {
							var fieldInstance = instance.getFieldInstance(source);
							var fieldContainer = source.one('> .folder > .field-container');
							var repeatableFieldImage = fieldContainer.one('.repeatable-field-image');
							var repeatableAddIcon = source.one('.journal-article-buttons .repeatable-button');

							if (repeatableFieldImage) {
								repeatableFieldImage.remove();
							}

							var parentStructureId = instance.get('parentStructureId');

							if (value && !parentStructureId) {
								var repeatableFieldImageModel = A.Node.create(
									A.one('#repeatable-field-image-model').html()
								);

								fieldContainer.append(repeatableFieldImageModel);

								if (repeatableAddIcon) {
									repeatableAddIcon.show();
								}
							}
							else {
								if (repeatableAddIcon) {
									repeatableAddIcon.hide();
								}
							}
						}

						return value;
					},

					setVariableName: function(value) {
						var instance = this;

						var fieldLabel = instance.getFieldLabelElement();
						var input = fieldLabel.get('parentNode').one('.journal-article-component-container .aui-field-input');

						if (input) {
							input.attr('id', value);

							fieldLabel.setAttribute('for', value);
						}

						instance.setAttribute('name', value);

						return value;
					},

					setAttribute: function(key, value) {
						var instance = this;

						var source = instance.get('source');

						if (Lang.isArray(value)) {
							value = value[0];
						}

						if (source) {
							source.setAttribute('data' + key, value);
						}

						return value;
					},

					_uiSetOptionsEditable: function(val) {
						var instance = this;

						var source = instance.get('source');

						if (source) {
							var journalArticleButtons = source.one('.journal-article-buttons');

							if (journalArticleButtons) {
								if (val) {
									journalArticleButtons.show();
								}
								else {
									journalArticleButtons.hide();
								}
							}
						}
					},

					_getNamespacedId: Journal.prototype._getNamespacedId,

					getById: Journal.prototype.getById
				}
			}
		);

		Journal.StructureField = StructureField;

		Journal.FieldModel = {};

		var fieldModel = Journal.FieldModel;

		var registerFieldModel = function(namespace, type, variableName, optionsEditable) {
			var instance = this;

			var typeEl = A.one('#journalFieldModelContainer div[dataType="'+ type +'"]');

			if (typeEl) {
				var innerHTML = typeEl.html();
			}

			fieldModel[namespace] = {
				fieldLabel: variableName,
				fieldType: type,
				innerHTML: innerHTML,
				optionsEditable: optionsEditable,
				variableName: variableName
			};
		};

		registerFieldModel('Text', 'text', 'TextField', true);
		registerFieldModel('TextArea', 'text_area', 'TextAreaField', true);
		registerFieldModel('TextBox', 'text_box', 'TextBoxField', true);
		registerFieldModel('Image', 'image', 'ImageField', true);
		registerFieldModel('ImageGallery', 'image_gallery', 'ImageGalleryField', true);
		registerFieldModel('DocumentLibrary', 'document_library', 'DocumentLibraryField', true);
		registerFieldModel('Boolean', 'boolean', 'BooleanField', true);
		registerFieldModel('List', 'list', 'ListField', true);
		registerFieldModel('MultiList', 'multi-list', 'MultiListField', true);
		registerFieldModel('LinkToPage', 'link_to_layout', 'LinkToPageField', true);
		registerFieldModel('SelectionBreak', 'selection_break', 'SelectionBreakField', false);

		Liferay.Portlet.Journal = Journal;
	},
	'',
	{
		requires: ['aui-base', 'aui-data-set', 'aui-datatype', 'aui-dialog', 'aui-nested-list', 'aui-overlay-context-panel', 'json', 'substitute']
	}
);