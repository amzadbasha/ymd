AUI().add(
	'liferay-panel-floating',
	function(A) {

		/**
		 * OPTIONS
		 *
		 * Also inherits all configuration options from Liferay.Panel
		 *
		 * Optional
		 * trigger {string|object}: A selector of the element that triggers the opening of the floating panel.
		 * paging {boolean}: Whether or not to add pagination to the panel.
		 * pagingElements {string}: A selector of the elements that make up each "page".
		 * resultsPerPage {number}: The number of results to show per page.
		 * width {number}: The width of the panel.
		 *
		 */

		var PanelFloating = A.Component.create(
			{
				EXTENDS: Liferay.Panel,

				constructor: function(options) {
					var instance = this;

					var defaults = {
						trigger: '.lfr-trigger',
						paging: false,
						pagingElements: 'ul',
						resultsPerPage: 1,
						width: 300
					};

					options = A.merge(defaults, options);

					instance._paging = options.paging;
					instance._pagingElements = options.pagingElements;
					instance._trigger = A.one(options.trigger);
					instance._containerWidth = options.width;

					PanelFloating.superclass.constructor.apply(instance, arguments);

					if (!instance._inContainer) {
						instance._container = A.Node.create('<div class="lfr-floating-container aui-helper-hidden"></div>');

						instance._panel.item(0).placeBefore(instance._container);
						instance._container.append(instance._panel);

						instance._inContainer = true;
					}

					instance._positionHelper = A.Node.create('<div class="lfr-position-helper"></div>');
					instance._positionHelper.append(instance._container);

					A.getBody().prepend(instance._positionHelper);

					instance._positionHelper.hide();

					instance.paginate(instance._container.all('.lfr-panel-content'));

					instance._trigger.addClass('lfr-floating-trigger');

					instance._hideAllPanels = function(event) {
						var target = event.target;

						if (!target.hasClass('lfr-panel') && !target.ancestor('.lfr-position-helper')) {
							instance.onOuterClick(target);

							A.getDoc().detach('click', instance._hideAllPanels);
						}

						event.stopPropagation();
					};

					instance._trigger.on(
						'click',
						function(event) {
							instance.onTriggerClick(event.target);

							A.getDoc().on('click', instance._hideAllPanels);

							event.stopPropagation();
						}
					);

					instance.set('trigger', instance._trigger);
				},

				prototype: {
					hide: function() {
						var instance = this;

						instance._positionHelper.hide();

						instance._trigger.removeClass('lfr-trigger-selected');

						instance.fire('hide');
					},

					onOuterClick: function() {
						var instance = this;

						instance.hide();

						instance.fire('outerClick');
					},

					onTitleClick: function(el) {
						var instance = this;

						PanelFloating.superclass.onTitleClick.apply(instance, arguments);

						var currentContainer = A.one(el).ancestor('.lfr-panel');
						var sets = currentContainer.all('ul');

						if (sets.size() && !sets.all('.current-set').size()) {
							sets.item(0).addClass('current-set');
						}
					},

					onTriggerClick: function(trigger) {
						var instance = this;

						var panelHidden = instance._positionHelper.hasClass('aui-helper-hidden');

						if (panelHidden) {
							instance.show(trigger);
						}
						else {
							instance.hide(trigger);
						}

						instance.fire('triggerClick');
					},

					paginate: function(currentPanelContent) {
						var instance = this;

						if (instance._paging) {
							currentPanelContent.each(
								function(item, index, collection) {
									var pages = item.all('ul');
									var totalPages = pages.size();

									if (totalPages > 1) {
										var paginatorContainer = A.Node.create('<div class="paginator-container"></div>');

										item.append(paginatorContainer);

										var paginatorInstance = new A.Paginator(
											{
												containers: paginatorContainer,
												on: {
													changeRequest: function(newState) {
														var page = newState.state.page;
														var showPage = Math.max(0, page - 1);

														pages.hide();
														pages.item(showPage).show();

														this.setState(newState);
													}
												},
												pageContainerTemplate: '<span></span>',
												template: '<span class="lfr-paginator-prev">{PrevPageLink}</span>{PageLinks}<span class="lfr-paginator-next">{NextPageLink}</span>',
												total: totalPages
											}
										).render();

										instance._container.addClass('lfr-panel-paging');
									}
								}
							);
						}
					},

					position: function(trigger) {
						var instance = this;

						trigger = A.one(trigger);

						var container = instance._container;
						var positionHelper = instance._positionHelper;

						var triggerHeight = trigger.get('offsetHeight');
						var triggerWidth = trigger.get('offsetWidth');

						var triggerOffset = trigger.getXY();

						positionHelper.setStyles(
							{
								height: triggerHeight + 'px',
								width: triggerWidth + 'px'
							}
						);

						positionHelper.show();
						container.show();

						positionHelper.setStyles(
							{
								left: triggerOffset[0] + 'px',
								top: triggerOffset[1] + triggerHeight + 'px'
							}
						);
					},

					show: function(trigger) {
						var instance = this;

						instance._container.setStyle('width', instance._containerWidth + 'px');

						instance.position(trigger);

						instance._trigger.addClass('lfr-trigger-selected');

						if (instance._paging) {
							instance._setMaxPageHeight();
						}

						instance.fire('show');
					},

					_setMaxPageHeight: function() {
						var instance = this;

						var sets = instance._container.all('.lfr-panel:not(.lfr-collapsed)');

						var maxHeight = 0;

						var panelContent = sets.all('.lfr-panel-content');
						var pages = panelContent.all('>' + instance._pagingElements);

						pages.each(
							function(item, index, collection) {
								var setHeight = item.get('offsetHeight');

								if (setHeight > maxHeight) {
									maxHeight = setHeight;
								}
							}
						);

						pages.setStyle('height', maxHeight + 'px');
					}
				}
			}
		);

		Liferay.PanelFloating = PanelFloating;
	},
	'',
	{
		requires: ['aui-paginator', 'liferay-panel', 'selector-css3']
	}
);