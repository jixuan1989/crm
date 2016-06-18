Ext.Loader.setConfig({
	enabled : true
});

Ext.define('zft.view.base.T10801.T10801Main', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.t10801Main',
	width : 500,
	initComponent : function() {		
		me = this;
		var columns = [{text: '操作编号',dataIndex: 'oprId', hidden : true,align: 'center',flex: 1},
		       		{text: '操作名称',dataIndex: 'oprName',align: 'center',width : 200},
		       		{
		                xtype:'actioncolumn',
		                width:80,
		                align: 'center',
		                text : '操作',
		                items: [{
		                	iconCls: 'edit',
		                	tooltip: '点击',
		                    handler: function(grid, rowIndex, colIndex) {
		                        var rec = grid.getStore().getAt(rowIndex);
		                        var oprId = rec.get('oprId');
		                        
		                        showConfirm('确认要'+rec.get('oprName')+"？", grid, function(bt) {
									if (bt == "yes") {
										showMask('请稍候......');
										Ext.Ajax.request({
											url: 'T10801Action.asp?method=all',
											waitMsg : '请稍候......',
											params: {
												txnId: '10801',
												subTxnId: '01',
												oprId : oprId,
											},
											success : function(rsp, opt) {
												hideMask();
												var rspObj=Ext.decode(rsp.responseText);
												if (rspObj.success) {
													rec.set('retCode',"<font color='blue'>"+rspObj.msg+"</font>");
												} else {
													rec.set('retCode',"<font color='red'>"+rspObj.msg+"</font>");
												}
											},
											failure: function(){
												hideMask();
											}
										});
									}
								});
		                    }
		                }]
		            },{text: '操作结果',dataIndex: 'retCode',align: 'center',width : 220},];
		var tbar = [];
		Ext.apply(me,{
			tbar : tbar,
			store : Ext.create('Ext.data.Store', {
				fields: [{name: 'oprId', type: 'string'},
				         {name: 'oprName', type: 'string'},
				         {name: 'retCode', type: 'string'}],
			     data : [{oprId: 'A001', oprName: '机构签到',retCode:''},
			         {oprId: 'A002', oprName: '机构签退',retCode:''},
			         {oprId: 'A003', oprName: '线路测试',retCode:''},
			         {oprId: 'A004', oprName: '重置MAC',retCode:''},
			         {oprId: 'A005', oprName: '重置PIN',retCode:''}]
			}),
			columns : columns,
			layout : 'fit',
		});
		this.callParent();
	}
});
