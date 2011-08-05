Liferay.Address = {
	getCountries: function(callback) {
		Liferay.Service.Portal.Country.getCountries(
			{
				active: true
			},
			callback
		);
	},

	getRegions: function(callback, selectKey) {
		Liferay.Service.Portal.Region.getRegions(
			{
				countryId: Number(selectKey),
				active: true
			},
			callback
		);
	}
};