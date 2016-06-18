Ext.define('zft.view.base.T10210.T10210Query', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10210Query',
	title : '查询条件',
	layout : 'fit',
	width : '25%',
	autoHeight : true,
	closeAction : 'hide',
	autoShow : true,
	buttonAlign : 'center',
	resizable : false,
	closable : true,
	initComponent : function() {
		var me = this;
		Ext.apply(me,{
			items : [{
				xtype : 'form',
				layout : 'column',
				margin : '0 0 10 0',
				border : false,
				defaults :{
					margin : '10 10 0 10',
					xtype : 'textfield',
					columnWidth : 1,
				},
				items : [{
					name: 'owner',
			        fieldLabel: '参数属主',
				},{
					name: 'value',
			        fieldLabel: '显示名称',
				}]
			}],
			buttons : [ {
				text : '查询',
				action : 'query'
			}, {
				text : '清除查询条件',
				scope : this,
				handler : function(){
					this.down('form').getForm().reset();
				}
			}]
		});
		this.callParent(arguments);
	}
});
