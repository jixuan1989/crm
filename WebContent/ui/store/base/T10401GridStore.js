Ext.define('zft.store.base.T10401GridStore', {
	extend : 'zft.store.system.BaseStore',
	alias : 'widget.t10401GridStore',
	model : 'zft.model.base.T10401GridModel',
	proxy : {
		type : 'ajax',
		url : 'gridPanelStoreAction.asp?storeId=oprInfo',
		actionMethods: {   
            read: 'POST'  //默认为get提交，更改为post解决url中文乱码问题
        }, 
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount',
			idProperty : 'oprId'
		}
	}
});
