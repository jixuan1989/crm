Ext.define('zft.view.base.T10210.T10210Add', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10210Add',
	title : '参数信息增加',
	layout : 'fit',
	width : 350,
	autoHeight : true,
	autoShow : true,
	modal : true,
	buttonAlign : 'center',
	resizable : false,
	closable : true,
	items : [ {
		xtype : 'form',
		autoHeight : true,
		width : 350,
		defaultType : 'textfield',
		labelWidth : 90,
		waitMsgTarget : true,
		url: 'T10202Action.asp?method=add',
		layout : 'column',
		defaults :{
			margin : '10 10 0 10',
			columnWidth : 1,
		},
		items : [{
					fieldLabel : '系统参数属主*',
					name : 'owner',
					maxLength: 20,
					allowBlank: false,
					blankText: '系统参数属主不能为空',
					emptyText: '请输入系统参数属主',
					vtype: 'isOverMax'
				},
				{
					fieldLabel: '参数键值*',
					name: 'key',
					maxLength: 23,
					allowBlank: false,
					blankText: '参数键值不能为空',
					emptyText: '请输入参数键值',
					vtype: 'isOverMax'
				},{
					fieldLabel: '参数类型*',
					xtype: 'combo',
					store: new Ext.data.ArrayStore({
						fields: ['valueField','displayField'],
						data: [['00','可维护'],['01','不可维护']],
						reader: new Ext.data.ArrayReader()
					}),
					value:'00',
					displayField: 'displayField',
					valueField: 'valueField',
					hiddenName: 'type',
					name:'type',
					emptyText: '请选择参数类型',
					fieldLabel: '参数类型*',
					mode: 'local',
					triggerAction: 'all',
					forceSelection: true,
					typeAhead: true,
					selectOnFocus: true,
					editable: true,
					allowBlank: false,
					blankText: '请选择一个参数类型'
				},{
					fieldLabel: '界面显示名称*',
					id: 'value',
					name: 'value',
					maxLength: 200,
					allowBlank: false,
					blankText: '界面显示名称不能为空',
					emptyText: '请输入界面显示名称',
					vtype: 'isOverMax'
				},{
					fieldLabel: '参数描述',
					allowBlank: true,
					blankText: '参数描述不能为空',
					emptyText: '请输入参数描述',
					name: 'descr',
					maxLength: 60,
					vtype: 'isOverMax'
				},{
					fieldLabel: '备注信息',
					allowBlank: true,
					id: 'reserve',
					name: 'reserve',
					maxLength: 64,
					margin : '10 10 10 10',
					vtype: 'isOverMax'
				}]
	} ],

	buttons : [ {
		text : '确定',
		id : 'oprAdd'
	}, {
		text : '重置',
		id:'reset'
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});