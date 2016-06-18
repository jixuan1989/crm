Ext.define('zft.view.base.T10500.T10500Add', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10500Add',
	title : '部门增加',
	layout : 'fit',
	width : 350,
	autoHeight : true,
	iconCls : 'logo',
	autoShow : true,
	buttonAlign : 'center',
	modal : true,
	resizable : false,
	closable : true,
	initComponent : function() {
		var me = this;
		//获取机构信息
		var BrhStore = Ext.create('Ext.data.Store',{
	        fields: ['value','name']
	    });
		SelectOptionsDWR.getComboData('BRH_BELOW_BRANCH',function(ret){
			BrhStore.loadData(eval(ret));
		});
		Ext.apply(me,{
			items : [ {
				xtype : 'form',
				autoHeight : true,
				width : 350,
				labelWidth : 90,
				layout : 'column',
				waitMsgTarget : true,
				defaults :{
					xtype:"textfield",
					margin : '10 10 0 10',
					columnWidth : 0.99,
					allowBlank: false
				},
				items: [{
					fieldLabel: '部门编号*',
					name: 'id',
					regex:/^[0-9a-zA-Z_]{1,15}$/,
					regexText:'部门编号只能为数字,字母或下划线组成',
					vtype: 'isOverMax',
					
					blankText: '部门编号不能为空',
					maxLength: 15
				},{
					fieldLabel: '部门名称*',
					name: 'name',
					vtype: 'isOverMax',
					blankText: '部门名称不能为空',
					maxLength: 20
				},{
					xtype: 'combo',
					fieldLabel: '部门所属机构*',
					store: BrhStore,
					name: 'brhId',
					displayField: 'name',
					valueField: 'value',
					queryMode: 'local',
					editable : false,
					blankText: '部门所属机构不能为空',
					margin : '10 10 10 10'
				}]
			}],
			buttons : [ {
				text : '确定',
				name : 'submit'
			}, {
				text : '重置',
				name : 'reset'
			}],
		});
		me.callParent(arguments);
	}
});