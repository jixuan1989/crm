Ext.define('zft.store.system.UnDealGridStore', {
	extend : 'zft.store.system.BaseStore',
	alias : 'widget.unDealGridStore',
	model : 'zft.model.system.UnDealGridModel',
	autoLoad : true,
	proxy : {
		type : 'ajax',
		url: 'gridPanelStoreAction.asp?storeId=unDeal',
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
