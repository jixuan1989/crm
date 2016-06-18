Ext.define('zft.view.base.T10401.T10401Query', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10401Query',
	title : '查询条件',
	layout : 'fit',
	width : 350,
	autoHeight : true,
	closeAction : 'hide',
	autoShow : true,
	buttonAlign : 'center',
	resizable : false,
	closable : true,
	animateTarget : 'query',
	items : [ {
		xtype : 'form',
		width : 350,
		autoHeight : true,
		defaults : {
		    margin : '10 0 10 20'
		},
		items : [ {
			xtype : 'textfield',
			name : 'oprId',
			width : 300,
			vtype: 'alphanum',
			fieldLabel : '操作员编号'
		},{
			width : 300,
			xtype : 'combo',
			mode : 'local',
			triggerAction : 'all',
			forceSelection : true,
			editable : false,
			fieldLabel : '机构',
			name : 'searchBrh',
			displayField : 'name',
			valueField : 'value',
			queryMode : 'local',
			emptyText: '请选择一个机构',
			store : brhStore,
			listeners : {
				scope : this,
				select : function(com,records,options) {
					SelectOptionsDWR.getComboDataWithParameter('ROLE_BY_BRH',com.getValue(),function(ret){
						roleForLook.removeAll();
						roleForLook.loadData(eval(ret));
					});
					SelectOptionsDWR.getComboDataWithParameter('STEPMENT_BY_BRH',com.getValue(),function(ret){
						stepStore.removeAll();
						stepStore.loadData(eval(ret));
					})
				}
			}
		},{
			xtype : 'textfield',
			name : 'oprNameID',
			width : 300,
			fieldLabel : '操作员姓名'
		},{
			width : 300,
			xtype : 'combo',
			mode : 'local',
			triggerAction : 'all',
			forceSelection : true,
			editable : false,
			fieldLabel : '操作员角色',
			name : 'oprDegree',
			displayField : 'name',
			valueField : 'value',
			emptyText: '请选择操作员角色',
			queryMode : 'local',
			store : roleForLook
		},{
			width : 300,
			xtype : 'combo',
			mode : 'local',
			triggerAction : 'all',
			forceSelection : true,
			editable : false,
			fieldLabel : '操作员部门',
			name : 'oprStep',
			displayField : 'name',
			valueField : 'value',
			emptyText: '请选择操作员所属部门',
			queryMode : 'local',
			store : stepStore
		}  ]
	} ],
	initComponent : function() {
		var me = this;
		Ext.apply(me,{
			buttons : [ {
				text : '查询',
			}, {
				text : '清除查询条件',
				handler : function() {
					brhId = '';
					me.down('form').getForm().reset();
				}
			} ],
		});
		me.callParent(arguments);
	}
});
