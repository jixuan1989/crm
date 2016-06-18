Ext.define('zft.store.base.T10601GridStore', {
	extend : 'zft.store.system.BaseStore',
	alias : 'widget.t10601GridStore',
	model : 'zft.model.base.T10601GridModel',
	proxy : {
		type : 'ajax',
		url: 'gridPanelStoreAction.asp?storeId=companyEmployee',
		actionMethods: {   
            read: 'POST'  //默认为get提交，更改为post解决url中文乱码问题
        }, 
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount',
			idProperty: 'oprId'
		}
	}
});
