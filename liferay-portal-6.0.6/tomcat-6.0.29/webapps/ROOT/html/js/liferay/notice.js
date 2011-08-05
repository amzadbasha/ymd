AUI().add(
	'liferay-notice',
	function(A) {

		/**
		 * OPTIONS
		 *
		 * Required
		 * content {string}: The content of the toolbar.
		 *
		 * Optional
		 * closeText {string}: Use for the "close" button. Set to false to not have a close button.
		 * toggleText {object}: The text to use for the "hide" and "show" button. Set to false to not have a hide button.
		 * noticeClass {string}: A class to add to the notice toolbar.
		 * type {string}: Either 'notice' or 'warning', depending on the type of the toolbar. Defaults to notice.
		 *
		 * Callbacks
		 * onClose {function}: Called when the toolbar is closed.
		 */

		var Notice = function(options) {
			var instance = this;
			options = options || {};
			instance._noticeType = options.type || 'notice';
			instance._noticeClass = 'popup-alert-notice';
			instance._useCloseButton = true;
			instance._onClose = options.onClose;
			instance._closeText = options.closeText;
			instance._body = A.getBody();

			instance._useToggleButton = false;
			instance._hideText = '';
			instance._showText = '';

			if (options.toggleText !== false) {
				instance.toggleText = A.mix(
					options.toggleText,
					{
						hide: null,
						show: null
					}
				);

				instance._useToggleButton = true;
			}

			if (instance._noticeType == 'warning') {
				instance._noticeClass = 'popup-alert-warning';
			}

			if (options.noticeClass) {
				instance._noticeClass += ' ' + options.noticeClass;
			}

			instance._content = options.content || '';

			instance._createHTML();

			return instance._notice;
		};

		Notice.prototype = {
			setClosing: function() {
				var instance = this;

				var alerts = A.all('.popup-alert-notice, .popup-alert-warning');

				if (alerts.size()) {
					instance._useCloseButton = true;

					if (!instance._body) {
						instance._body = A.getBody();
					}

					instance._body.addClass('has-alerts');

					alerts.each(instance._addCloseButton, instance);
				}
			},

			_createHTML: function() {
				var instance = this;

				var notice = A.Node.create('<div class="' + instance._noticeClass + '" dynamic="true"><div class="popup-alert-content"></div></div>');

				notice.html(instance._content);

				instance._addCloseButton(notice);
				instance._addToggleButton(notice);

				instance._body.append(notice);
				instance._body.addClass('has-alerts');

				instance._notice = notice;
			},

			_addCloseButton: function(notice) {
				var instance = this;

				if (instance._closeText !== false) {
					instance._closeText = instance._closeText || Liferay.Language.get('close');
				}
				else {
					instance._useCloseButton = false;
					instance._closeText = '';
				}

				if (instance._useCloseButton) {
					var html = '<input class="submit popup-alert-close" type="submit" value="' + instance._closeText + '" />';

					notice.append(html);

					var closeButton = notice.one('.popup-alert-close');

					closeButton.on(
						'click',
						function() {
							notice.hide();
							notice.remove(true);
							instance._body.removeClass('has-alerts');

							if (instance._onClose) {
								instance._onClose();
							}
						}
					);
				}
			},

			_addToggleButton: function(notice) {
				var instance = this;

				if (instance._useToggleButton) {
					instance._hideText = instance._toggleText.hide || Liferay.Language.get('hide');
					instance._showText = instance._toggleText.show || Liferay.Language.get('show');

					var toggleButton = A.Node.create('<a class="toggle-button" href="javascript:;"><span>' + instance._hideText + '</span></a>');
					var toggleSpan = toggleButton.one('span');
					var height = 0;

					var visible = 0;

					var showText = instance._showText;
					var hideText = instance._hideText;

					toggleButton.on(
						'click',
						function(event) {
							var text = showText;

							if (visible == 0) {
								text = hideText;

								visible = 1;
							}
							else {
								visible = 0;
							}

							notice.toggle();
							toggleSpan.text(text);
						}
					);

					notice.append(toggleButton);
				}
			}
		};

		Liferay.Notice = Notice;
	},
	'',
	{
		requires: ['aui-base']
	}
);