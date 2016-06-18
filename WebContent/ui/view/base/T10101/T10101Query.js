Ext.define('zft.view.base.T10101.T10101Query', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10101Query',
	
	initComponent : function() {
		var me = this;
		//机构信息
		var brhStore = new Ext.data.JsonStore({
			fields: ['value','name'],
			root: 'data'
		});
		SelectOptionsDWR.getComboData('OPR_BELOW_BRH',function(ret){
			brhStore.loadData(eval(ret));
		});	
		Ext.apply(me,{
			title : '查询条件',
			layout : 'fit',
			width : 400,
			autoHeight : true,
			closeAction : 'hide',
			autoShow : true,
			buttonAlign : 'center',
			resizable : false,
			closable : true,
			items : [{
				xtype : 'form',
				width : 380,
				autoHeight : true,
				lablewidth : 10,
				layout : 'column',
				defaults :{
					margin : '10 10 0 10',
					columnWidth : 1,
				},
				items : [{
					xtype : 'combo',
					displayField: 'name',
					valueField: 'value',
					emptyText: '请选择机构',
					queryMode: 'local',
					editable: false,
					store : brhStore,
					allowBlank: false,
					blankText: '请选择一个机构',
					fieldLabel: '机构编号',
					name: 'brhIdEdit'
				},{
					xtype : 'textfield',
					name: 'searchBrhName',
					maxLength: 40,
					fieldLabel: '机构名称',
					margin : '10 10 10 10',
				}]
			}],
			buttons : [ {
				text : '查询',
				name : 'query'
			}, {
				text : '清除查询条件',
				name : 'reset'
			}]
		});
		this.callParent(arguments);
	}
});
