Ext.define('zft.store.base.T10500GridStore', {
	extend : 'zft.store.system.BaseStore',
	alias : 'widget.t10500GridStore',
	model : 'zft.model.base.T10500GridModel',
	proxy : {
		type : 'ajax',
		url: 'gridPanelStoreAction.asp?storeId=companyInfo',
		actionMethods: {   
            read: 'POST'  //默认为get提交，更改为post解决url中文乱码问题
        }, 
		reader : {
			type : 'json',
			root : 'data',
			totalProperty : 'totalCount',
		}
	}
});
