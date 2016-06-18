Ext.define('zft.view.system.loginMsgWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.loginMsgWindow',
	title : '短信验证信息',
	layout : 'fit',
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
			items : [{
				xtype : 'form',
				items : [{
					xtype: 'textfield',
					fieldLabel: '短信验证码',
					name: 'msg',
				}]
			}],
			buttons : [{
				text : '提交',
				action : 'submit'
			},{
				text : '重置',
				scope : this,
				handler : function (){this.getForm().reset();}
			}]
		});
		me.callParent();
	}
});