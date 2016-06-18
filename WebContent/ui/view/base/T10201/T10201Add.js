Ext.define('zft.view.base.T10201.T10201Add', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10201Add',
	title : '地区码添加',
	layout : 'fit',
	width : 350,
	autoHeight : true,
	iconCls : 'logo',
	autoShow : true,
	modal : true,
	buttonAlign : 'center',
	resizable : false,
	closable : true,
	initComponent : function() {
		var me = this;
		
		Ext.apply(me,{
			items : [ {
				xtype : 'form',
				autoHeight : true,
				width : 350,
				defaultType : 'textfield',
				labelWidth : 90,
				layout : 'column',
				defaults :{
					margin : '10 10 0 10',
					columnWidth : 1,
				},
				waitMsgTarget : true,
				items: [{
					fieldLabel: '银联地区代码*',
					name: 'cupCityCode',
					allowBlank: false,
					maskRe:/^[0-9]$/,
					regex:/^[0-9]{4}$/,
					regexText:'银联地区代码为4个数字',
					emptyText: '该输入项只能包含数字'
				},{
					fieldLabel: '商户地区代码*',
					name: 'mchtCityCode',
					allowBlank: false,
					maskRe:/^[0-9]$/,
					regex:/^[0-9]{4}$/,
					regexText:'商户地区代码为4个数字',
					emptyText: '该输入项只能包含数字'
				},{
					fieldLabel: '地区名称*',
					name: 'cityName',
					width: 300,
					maxLength: 20,
					allowBlank: false,
					blankText: '地区名称不能为空',
					emptyText: '请输入地区名称',
					margin : '10 10 10 10',
					vtype: 'isOverMax'
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