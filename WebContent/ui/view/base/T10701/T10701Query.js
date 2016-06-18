Ext.define('zft.view.base.T10701.T10701Query', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10701Query',
	
	initComponent : function() {
		var me = this;
		var brhStore = Ext.create('zft.store.system.BaseComboStore');
		var conTxnStore = Ext.create('zft.store.system.BaseComboStore');
		
		brhStore.load({params: { ID: 'BRH_BELOW' } });
		conTxnStore.load({params: { ID: 'CON_TXN' } });
		Ext.apply(me,{
			title : '查询条件',
			layout : 'fit',
			width : 400,
			autoHeight : true,
			closeAction : 'hide',
			autoShow : true,
			buttonAlign : 'center',
			resizable : false,
			modal: true,
			closable : true,
			items : [{
				xtype : 'form',
				width : 380,
				autoHeight : true,
				lablewidth : 10,
				defaults : {
					width : 360,
					lablewidth : 10,
					margin : '10 0 10 20'
				},
				items : [{
					xtype : 'textfield',
					name : 'oprNo',
					fieldLabel : '操作员编号'
				},{
					xtype : 'combo',
					mode : 'local',
					triggerAction : 'all',
					forceSelection : true,
					editable : false,
					emptyText : '请选择所属机构',
					fieldLabel : '所属机构',
					name : 'brhId',
					displayField : 'displayField',
					valueField : 'valueField',
					queryMode : 'local',
					forceSelection : true,
					allowBlank : false,
					store : brhStore
				},{
					xtype : 'combo',
					mode : 'local',
					triggerAction : 'all',
					forceSelection : true,
					editable : false,
					emptyText : '请选择交易名称',
					fieldLabel : '交易名称',
					name : 'conTxn',
					displayField : 'displayField',
					valueField : 'valueField',
					queryMode : 'local',
					forceSelection : true,
					allowBlank : true,
					store : conTxnStore
				},{
					xtype: 'datefield',
					name: 'startDate',
					format: 'Y-m-d',
					maxValue: new Date(),
					emptyText: '请选择交易开始日期',
					fieldLabel: '交易开始日期',
					listeners: {  
						'select': function () {  
							var form = me.down('form').getForm();
							var start = form.findField('startDate').getValue(); 
							var max = Ext.Date.add(start, Ext.Date.DAY, 31);
							if(max < new Date()){
								form.findField('endDate').setMaxValue(max);
							}
							form.findField('endDate').setMinValue(start);  
							var endDate = form.findField('endDate').getValue();  
							if (start > endDate) {  
								form.findField('endDate').setValue(start);  
							} 
						}   
					} 
				},{
					xtype: 'datefield',
					name: 'endDate',
					format: 'Y-m-d',
					maxValue: new Date(),
					emptyText: '请选择交易结束日期',
					fieldLabel: '交易结束日期',
					listeners: {  
						'select': function () { 
							var form = me.down('form').getForm();
							var start = form.findField('startDate').getValue();
							var end = form.findField('endDate').getValue();
							var min = Ext.Date.add(end, Ext.Date.DAY, -31);
							form.findField('startDate').setMinValue(min);
							form.findField('startDate').setMaxValue(end);
							if(start == null){
								showMsg("请先选择交易起始日期!", this);
								form.findField('endDate').setValue('');
							}
						}   
					}
				}]
			}],
			buttons : [ {
				text : '查询',
			}, {
				text : '清除查询条件',
				handler : function() {
					var form = me.down('form').getForm();
					form.findField('startDate').setMinValue(''); 
					form.findField('startDate').setMaxValue(new Date());
					form.reset();
				}
			} ]
		});
		this.callParent(arguments);
	}
});
