Ext.define('zft.store.system.MenuTreeStore', {
	extend : 'Ext.data.TreeStore',
	storeId : 'menuTreeStore',
	alias : 'widget.menuTreeStore',
	requires : 'zft.model.system.MenuTreeModel',
	model : 'zft.model.system.MenuTreeModel',
	autoLoad : false,
	proxy : {
		type : 'ajax',
		url : 'tree.asp',
		extraParams : {
			param:''
        },
		reader : {
			type : 'json',
			successProperty : 'success'
		}
	}
	
});