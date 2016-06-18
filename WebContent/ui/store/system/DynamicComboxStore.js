Ext.define('zft.store.system.DynamicComboxStore', {
	extend : 'Ext.data.Store',
	fields: [{
		name : 'valueField'
	}, {
		name : 'displayField'
	}],
	proxy : {
		type : 'ajax',
		url: 'dynamicSelect.asp',
		actionMethods: {
            read: 'POST' 
        },
        extraParams :　{
        	methodName: 'getMchtMapping'
        },
		reader : {
			type : 'json',
			totalProperty: 'totalCount',
			root: 'data'
		}
	}
});