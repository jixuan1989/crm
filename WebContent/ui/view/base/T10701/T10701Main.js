Ext.Loader.setConfig({
	enabled : true
});
/**
 * 交易状态转译
 */
function txnSta(val) {
	if(val == '0') {
		return '<font color="green">成功</font>';
	} else if(val == '1') {
		return '<font color="red">失败</font>';
	}
	return val;
}
Ext.define('zft.view.base.T10701.T10701Main', {
	extend : 'zft.view.system.BaseGridPanel',
	alias : 'widget.t10701Main',
	initComponent : function() {
		var tbar = [ {
			xtype : 'button',
			text : '查询',
			width : 85,
			id : 'query',
			iconCls : 'query'
		} ];
		var columns = [ 
		{text: '操作员编号',dataIndex: 'oprId',align: 'center',flex: 1,name: 'oprId'},
		{text: '操作日期',dataIndex: 'txnDate',align: 'center',flex: 2},
		{text: '操作时间',dataIndex: 'txnTime',align: 'center',renderer: formatTs,flex: 2},
		{text: '交易名称',dataIndex: 'txnName',align: 'center',flex: 3,name:'txnName'},
		{text: '交易状态',dataIndex: 'txnSta',align: 'center',renderer: txnSta,flex: 1} ];
		Ext.apply(this, {
			id : 't10701Main',
			width : 800,
			store : 'base.T10701GridStore',
			columns : columns,
			layout : 'fit',
			tbar : tbar,
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : 'base.T10701GridStore',
				dock : 'bottom',
				displayInfo : true
			} ]
		});
		this.callParent(arguments);
	}
});
