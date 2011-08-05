AUI().add(
	'liferay-ratings',
	function(A) {
		var Lang = A.Lang;

		var EMPTY_FN = Lang.emptyFn;

		var Ratings = A.Component.create(
			{
				ATTRS: {
					averageScore: {},
					className: {},
					classPK: {},
					namespace: {},
					size: {},
					totalEntries: {},
					totalScore: {},
					type: {},
					uri: {},
					yourScore: {}
				},

				EXTENDS: A.Base,

				prototype: {
					initializer: function() {
						var instance = this;

						instance._renderRatings();
					},

					_bindRatings: function() {
						var instance = this;

						instance.ratings.after('itemSelect', instance._itemSelect, instance);
					},

					_getLabel: function(desc, totalEntries, ratingScore) {
						var instance = this;

						var labelScoreTpl = '{desc} ({totalEntries} {voteLabel}) {ratingScoreLabel}';

						var ratingScoreLabel = '';

						if (ratingScore || ratingScore == 0) {
							ratingScoreLabelMessage = A.substitute(
								Liferay.Language.get('the-average-rating-is-x-stars-out-of-x'),
								[ratingScore, instance.get('size')]
							);

							ratingScoreLabel = '<span class="aui-helper-hidden-accessible">' + ratingScoreLabelMessage + '</span>';
						}

						var voteLabel = '';

						if (totalEntries == 1) {
							voteLabel = Liferay.Language.get('vote');
						}
						else {
							voteLabel = Liferay.Language.get('votes');
						}

						return A.substitute(
							labelScoreTpl,
							{
								desc: desc,
								ratingScoreLabel: ratingScoreLabel,
								totalEntries: totalEntries,
								voteLabel: voteLabel
							}
						);
					},

					_itemSelect: EMPTY_FN,

					_renderRatings: EMPTY_FN,

					_sendVoteRequest: function(url, score, callback) {
						var instance = this;

						A.io.request(
							url,
							{
								data: {
									className: instance.get('className'),
									classPK: instance.get('classPK'),
									p_l_id: themeDisplay.getPlid(),
									score: score
								},
								dataType: 'json',
								on: {
									success: A.bind(callback, instance)
								}
							}
						);
					},

					_showScoreTooltip: function(event) {
						var instance = this;

						var ratingScore = instance.ratingScore;

						var stars = ratingScore.get('selectedIndex') + 1;

						if (stars == 1) {
							message = Liferay.Language.get('star');
						}
						else {
							message = Liferay.Language.get('stars');
						}

						message = ' ' + message;

						var el = event.currentTarget.getDOM();

						Liferay.Portal.ToolTip.show(el, stars + message);
					},

					_fixScore: function(score) {
						var instance = this;

						return (score > 0) ? ('+' + score) : (score + '');
					},

					_convertToIndex: function(score) {
						var instance = this;

						var scoreindex = -1;

						if (score == 1.0) {
							scoreindex = 0;
						}
						else if (score == -1.0) {
							scoreindex = 1;
						}

						return scoreindex;
					}
				},

				register: function(config) {
					var instance = this;

					var ratings = Liferay.Ratings.StarRating;

					if (config.type != 'stars') {
						ratings = Liferay.Ratings.ThumbRating;
					}

					var ratingInstance = new ratings(config);

					instance._INSTANCES[config.id || config.namespace] = ratingInstance;

					return ratingInstance;
				},

				_INSTANCES: {},

				_thumbScoreMap: {
					'-1': 0,
					'down': -1,
					'up': 1
				}
			}
		);

		var StarRating = A.Component.create(
			{
				EXTENDS: Ratings,

				prototype: {
					_renderRatings: function() {
						var instance = this;

						var namespace = instance.get('namespace');

						if (themeDisplay.isSignedIn()) {
							var yourScore = instance.get('yourScore');

							instance.ratings = new A.StarRating(
								{
									boundingBox: '#' + namespace + 'ratingStar',
									canReset: false,
									defaultSelected: yourScore,
									srcNode: '#' + namespace + 'ratingStarContent'
								}
							).render();

							instance._bindRatings();
						}

						var description = Liferay.Language.get('average');
						var totalEntries = instance.get('totalEntries');
						var averageScore = instance.get('averageScore');
						var size = instance.get('size');

						var label = instance._getLabel(description, totalEntries, averageScore);

						var ratingScore = new A.StarRating(
							{
								boundingBox: '#' + namespace + 'ratingScore',
								canReset: false,
								defaultSelected: averageScore,
								disabled: true,
								label: label,
								size: size,
								srcNode: '#' + namespace + 'ratingScoreContent'
							}
						);

						ratingScore.get('boundingBox').on('mouseenter', instance._showScoreTooltip, instance);

						instance.ratingScore = ratingScore.render();
					},

					_itemSelect: function(event) {
						var instance = this;

						var uri = instance.get('uri');
						var score = instance.ratings.get('selectedIndex') + 1;

						instance._sendVoteRequest(uri, score, instance._saveCallback);
					},

					_saveCallback: function(event, id, obj) {
						var instance = this;

						var xhr = event.currentTarget;

						var json = xhr.get('responseData');
						var description = Liferay.Language.get('average');

						var label = instance._getLabel(description, json.totalEntries, json.averageScore);

						var averageIndex = json.averageScore - 1;

						var ratingScore = instance.ratingScore;

						ratingScore.set('label', label);
						ratingScore.select(averageIndex);
					}
				}
			}
		);

		var ThumbRating = A.Component.create(
			{
				EXTENDS: Ratings,

				prototype: {
					_renderRatings: function() {
						var instance = this;

						if (themeDisplay.isSignedIn()) {
							var description = instance._fixScore(instance.get('totalScore'));
							var totalEntries = instance.get('totalEntries');
							var averageScore = instance.get('averageScore');
							var size = instance.get('size');
							var yourScore = instance.get('yourScore');

							var label = instance._getLabel(description, totalEntries, averageScore);

							var yourScoreIndex = instance._convertToIndex(yourScore);

							var namespace = instance.get('namespace');

							instance.ratings = new A.ThumbRating(
								{
									boundingBox: '#' + namespace + 'ratingThumb',
									label: label,
									srcNode: '#' + namespace + 'ratingThumbContent'
								}
							).render();

							instance._bindRatings();

							instance.ratings.select(yourScoreIndex);
						}
					},

					_itemSelect: function(event) {
						var instance = this;

						var uri = instance.get('uri');
						var value = instance.ratings.get('value');

						var score = Liferay.Ratings._thumbScoreMap[value];

						instance._sendVoteRequest(uri, score, instance._saveCallback);
					},

					_saveCallback: function(event, id, obj) {
						var instance = this;

						var xhr = event.currentTarget;

						var json = xhr.get('responseData');
						var score = Math.round(json.totalEntries * json.averageScore);

						var description = instance._fixScore(score);
						var label = instance._getLabel(description, json.totalEntries);

						instance.ratings.set('label', label);
					}
				}
			}
		);

		Ratings.StarRating = StarRating;
		Ratings.ThumbRating = ThumbRating;

		Liferay.Ratings = Ratings;
	},
	'',
	{
		requires: ['aui-io-request', 'aui-rating', 'substitute']
	}
);