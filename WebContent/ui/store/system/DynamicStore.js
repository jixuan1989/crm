/*
 * 动态加载的COMBO
 */
Ext.define('zft.store.system.DynamicStore',{
	extend : 'Ext.data.Store',
	alias : 'widget.dynamicStore',
	fields: ['valueField', 'displayField'],
	proxy : {
		type : 'ajax',
		url : 'dynamicSelect.asp',
		actionMethods: {   
            read: 'POST'
        }, 
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : false
});