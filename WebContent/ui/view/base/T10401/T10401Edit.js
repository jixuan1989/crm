Ext.define('zft.view.base.T10401.T10401Edit', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10401Edit',
	requires : [ 'zft.view.system.MultiComboBox' ],
	title : '操作员编辑',
	layout : 'fit',
	width : 350,
	autoHeight : true,
	autoShow : true,
	buttonAlign : 'center',
	resizable : false,
	closable : true,
	modal: true,
	id : 'editWin',
	animateTarget : 'add',
	items : [ {
		xtype : 'form',
		autoHeight : true,
		width : 350,
		defaultType : 'textfield',
		labelWidth : 90,
		waitMsgTarget : true,
		id : 'editForm',
		defaults : {
		    margin : '10 0 10 20'
		},
		items : [
				{
					xtype : 'combo',
					editable : false,
					emptyText : '请选择机构',
					fieldLabel : '所属机构号*',
					id : 'editBrh',
					name : 'editBrh',
					displayField : 'name',
					valueField : 'value',
					queryMode : 'local',
					width : 300,
					allowBlank : false,
					readOnly : true,
					hidden : true,
					store : new Ext.data.ArrayStore({
						fields: ['value','name'],
						data: [['0000','支付通总公司'],['1900','支付通四川分公司']],
						reader: new Ext.data.ArrayReader()
					}),
					listeners : {
						scope : this,
						select : function(com, records, options) {
							SelectOptionsDWR.getComboDataWithParameter(
									'ROLE_BY_BRH', com.getValue(),
									function(ret) {
										roleForLook.removeAll();
										roleForLook.loadData(eval(ret));
									});
						}
					}
				},
				{
					xtype : 'multicombobox',
					editable : false,
					emptyText : '请选择角色',
					fieldLabel : '操作员角色',
					id : 'editDegree',
					name : 'editDegree',
					displayField : 'name',
					valueField : 'value',
					multiSelect:true,
					queryMode : 'local',
					width : 300,
					forceSelection : true,
					allowBlank : true,
					store : roleForLook
				}]
	} ],

	buttons : [ {
		text : '确定',
	}, {
		text : '关闭',
		handler : function() {
			Ext.getCmp('editForm').getForm().reset();
			Ext.getCmp('editWin').close();
		}
	} ]
});