Ext.define('zft.store.system.BaseComboStore', {
	extend : 'Ext.data.Store',
	fields: [{
		name : 'valueField'
	}, {
		name : 'displayField'
	}],
	
	proxy : {
		type : 'ajax',
		url: 'baseSelect.asp',
		actionMethods: {
            read: 'POST' 
        },
		reader : {
			type : 'json',
			root: 'data'
		}
	},
});
