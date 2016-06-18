Ext.define('zft.view.system.winLogin', {
	extend : 'Ext.window.Window',
	layout : 'fit',
	title : '系统登陆',
	autoShow : true,
	buttonAlign : 'center',
	autoHeight : true,
	width : 300,
	autoDestroy : true,
	initComponent : function() {
		var me = this;
		var form = Ext.create('Ext.form.Panel',{
			layout : 'column',
			margin : '0 0 10 0',
			border : false,
			defaults : {
				columnWidth : 1,
				margin : '10 10 0 10',
				xtype : 'textfield',
				allowBlank : false,
			},
			items : [{
				name : 'loginType',
				value : '11',
				hidden : true
			},{
				fieldLabel : '操作员',
				allowBlank : false,
				readOnly : true,
				blankText : '请输入操作员编号',
				value : opr,
				name : 'oprid',
			}, {
				inputType : 'password',
				fieldLabel : '密　码',
				blankText : '请输入密码',
				name : 'password',
			} ],
		});
		Ext.apply(me, {
			items : [form]
		});
		me.callParent(arguments);
	},
	buttons : [{
		text : '登　陆',
		action : 'submit',
	},{
		text : '重　置',
		action : 'reset',
	}]
});