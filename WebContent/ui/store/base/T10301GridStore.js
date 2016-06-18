Ext.define('zft.store.base.T10301GridStore', {
	extend : 'zft.store.system.BaseStore',
	alias : 'widget.t10301GridStore',
	model : 'zft.model.base.T10301GridModel',
	proxy : {
		type : 'ajax',
		url : 'gridPanelStoreAction.asp?storeId=roleInfo',
		actionMethods: {   
            read: 'POST'  //默认为get提交，更改为post解决url中文乱码问题
        }, 
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount',
			idProperty : 'roleId'
		}
	}
});
