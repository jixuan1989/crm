Ext.define('zft.store.base.T10101GridStore', {
	extend : 'zft.store.system.BaseStore',
	alias : 'widget.t10101GridStore',
	model : 'zft.model.base.T10101GridModel',
	proxy : {
		type : 'ajax',
		url: 'gridPanelStoreAction.asp?storeId=brhInfo',
		actionMethods: {   
            read: 'POST'  //默认为get提交，更改为post解决url中文乱码问题
        }, 
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount',
			idProperty : 'brhId'
		}
	}
});
