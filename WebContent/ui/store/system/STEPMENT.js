Ext.define('zft.store.system.STEPMENT', {
	extend : 'Ext.data.Store',
	fields: [{name : 'value'}, 
	         {name : 'name'}
	],
	idProperty:"value"
});
