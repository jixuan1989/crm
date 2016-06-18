Ext.define('zft.model.base.T10701GridModel', {
	extend : 'Ext.data.Model',
	alias : 'widget.t10701GridModel',
	fields : [ {
		name : 'oprId',
		mapping : 'oprId'
	}, {
		name : 'txnDate',
		mapping : 'txnDate'
	}, {
		name : 'txnTime',
		mapping : 'txnTime'
	}, {
		name : 'txnName',
		mapping : 'txnName'
	}, {
		name : 'txnSta',
		mapping : 'txnSta'
	}, {
		name : 'errMsg',
		mapping : 'errMsg'
	} ]
});
