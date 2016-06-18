Ext.define('zft.model.base.T10201GridModel', {
	extend : 'Ext.data.Model',
	alias : 'widget.t10201GridModel',
	fields : [{name: 'cupCityCode',mapping: 'cupCityCode'},
				{name: 'mchtCityCode',mapping: 'mchtCityCode'},
				{name: 'cityName',mapping: 'cityName'},
				{name: 'initTime',mapping: 'initTime'},
				{name: 'modiTime',mapping: 'modiTime'}]
});
