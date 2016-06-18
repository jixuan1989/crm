Ext.define('zft.store.base.T10210GridStore', {
	extend : 'zft.store.system.BaseStore',
	model : 'zft.model.base.T10210GridModel',
	alias : 'widget.t10210GridStore',
	proxy : {
		type : 'ajax',
		url: 'gridPanelStoreAction.asp?storeId=sysParamInfo',
		actionMethods: {   
            read: 'POST' 
        },
		reader : {
			type : 'json',
			root: 'data',
			totalProperty: 'totalCount'
		}
	},
});
