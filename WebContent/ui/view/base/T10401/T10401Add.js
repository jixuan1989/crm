Ext.define('zft.view.base.T10401.T10401Add', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10401Add',
	requires : [ 'zft.view.system.MultiComboBox' ],
	title : '操作员添加',
	layout : 'fit',
	width : 350,
	autoHeight : true,
	autoShow : true,
	buttonAlign : 'center',
	resizable : false,
	closable : true,
	modal: true,
	id : 'addWin',
	animateTarget : 'add',
	items : [ {
		xtype : 'form',
		layout: 'column',
		width : 350,
		autoHeight : true,
		waitMsgTarget : true,
		id : 'addForm',
		defaults : {
		    margin : '10 10 0 10',
		    xtype:"combo",
		    columnWidth: .99,
		    editable: false,
		    allowBlank: false,
		    displayField : 'name',
			valueField : 'value',
			queryMode : 'local'
		},
		items : [{
					xtype:"textfield",
					fieldLabel : '操作员编号*',
					blankText : '操作员编号不能为空',
					emptyText : '请输入操作员编号',
					name : 'oprId',
					regex : /^[a-zA-Z0-9]{8}$/,
					regexText : '操作员编号由8位的数字或字母组成',
					maxLength : 8,
					vtype : 'isOverMax'
				},
				{
					emptyText : '请选择机构',
					fieldLabel : '所属机构号*',
					name : 'brhId',
					store : brhStore,
					listeners : {
						scope : this,
						select : function(com, records, options) {
							SelectOptionsDWR.getComboDataWithParameter(
									'ROLE_BY_BRH', com.getValue(),
									function(ret) {
										roleForLook.removeAll();
										roleForLook.loadData(eval(ret));
									});
							SelectOptionsDWR.getComboDataWithParameter(
									'STEPMENT_BY_BRH', com.getValue(),
									function(ret) {
										stepStore.removeAll();
										stepStore.loadData(eval(ret));
									})
						}
					}
				},
				{
					xtype : 'multicombobox',
					emptyText : '请选择角色',
					fieldLabel : '操作员角色*',
					name : 'oprDegree',
					store : roleForLook
				},
				{
					emptyText : '请选择所属部门',
					fieldLabel : '所属部门*',
					name : 'stepmentId',
					store : stepStore,
					listeners : {
						'select' : function(com, records, options) {
							SelectOptionsDWR.getComboDataWithParameter(
									'STEP_EMPLOYEE', com.getValue(), function(
											ret) {
										stepEmployeeStore.loadData(eval(ret));
									});
						}
					}
				},
				{
					emptyText : '请选择操作员',
					fieldLabel : '操作员*',
					name : 'oprName',
					displayField : 'name',
					valueField : 'value',
					store : stepEmployeeStore,
					listeners : {
						'select' : function() {
							var me = this;
							T10401.getEmployeeInfo(this.getValue(), function(
									ret) {
								if (ret != null) {
									var employeeInfo = Ext.decode(ret
											.substring(1, ret.length - 1));
									var form = me.up('form').getForm();
									form.findField('oprGender').setValue(
											employeeInfo.sex);
									form.findField('oprTel').setValue(
											employeeInfo.mobiephone);
									form.findField('oprEmail').setValue(
											employeeInfo.email);
								}
							});
						}
					}
				}, {
					emptyText : '请选择性别',
					fieldLabel : '性别*',
					name : 'oprGender',
					readOnly : true,
					allowBlank: true,
					store : new Ext.data.ArrayStore({
						fields: ['value','name'],
						data: [['0','男'],['1','女']]
					}),
				}, {
					xtype:"textfield",
					inputType: 'password',
					fieldLabel : '密码*',
					emptyText : '请输入密码',
					name : 'oprPwd',
					vtype: 'isOverMax',
					regex: /^[a-zA-Z0-9]{6}$/,
					regexText: '密码必须由6位数字或字母组成',
					blankText : '操作员密码不能为空'
				}, {
					xtype:"textfield",
					fieldLabel : '操作员手机',
					name : 'oprTel',
					allowBlank: true,
					readOnly : true,
					maxLength:12
				}, {
					xtype:"textfield",
					fieldLabel : '操作员邮箱',
					readOnly : true,
					allowBlank: true,
					name : 'oprEmail'
				}, {
					fieldLabel: '是否短信验证*',
					name: 'isValMsg',
					margin:"10 10 10 10",
					store : new Ext.data.ArrayStore({
						fields: ['value','name'],
						data: [['0','是'],['1','否']],
						reader: new Ext.data.ArrayReader()
					})
				}]
	} ],

	buttons : [ {
		text : '确定',
	}, {
		text : '重置',
		handler : function() {
			Ext.getCmp('addForm').getForm().reset();
		}
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});