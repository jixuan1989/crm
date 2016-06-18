Ext.define('zft.store.base.T10701GridStore', {
	extend : 'zft.store.system.BaseStore',
	alias : 'widget.t10402GridStore',
	model : 'zft.model.base.T10701GridModel',
	proxy : {
		type : 'ajax',
		url : 'gridPanelStoreAction.asp?storeId=txnInfo',
		actionMethods: {   
            read: 'POST' 
        }, 
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount'
		}
	}
});
