Ext.define('zft.view.base.T10601.T10601Add', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10601Add',
	title : '人员信息添加',
	layout : 'fit',
	width : '60%',
	autoHeight : true,
	iconCls : 'logo',
	autoShow : true,
	modal : true,
	buttonAlign : 'center',
	resizable : false,
	closable : true,
	initComponent : function() {
		var me = this;

		// 部门
		var stepmentStore = new Ext.data.JsonStore({
			fields: ['value','name']
		});
		SelectOptionsDWR.getComboData('STEPMENT',function(ret){
			stepmentStore.loadData(eval(ret));
		});
		// 职位	
		var PositionStore = new Ext.data.JsonStore({
			fields: ['value','name']
		});
		SelectOptionsDWR.getComboData('POSITIONINFO',function(ret){
			PositionStore.loadData(eval(ret));
		});
		//人员信息
		var allEmpStore = new Ext.data.JsonStore({
			fields: ['value','name']
		});
		SelectOptionsDWR.getComboData('ALL_EMPLOYEE',function(ret){
			allEmpStore.loadData(eval(ret));
		});
		Ext.apply(me,{
			items : [ {
				xtype : 'form',
				autoHeight : true,
				width : '60%',
				defaultType : 'textfield',
				labelWidth : 90,
				waitMsgTarget : true,
				layout : 'column',
				defaults :{
					margin : '10 10 0 10',
					columnWidth : .5,
				},
				items : [{
					fieldLabel: '员工号*',
					name: 'tblCompanyEmployee.employeeId',
					vtype: 'isOverMax',
					maxLength: 15,
					regex:/^[a-zA-Z0-9]{5}$/,
					regexText:'工号必须是5位数字和字母组成，请重新输入',
					allowBlank: false,
					blankText: '工号不能为空',
				},{
					fieldLabel: '人员姓名*',
					name: 'tblCompanyEmployee.name',
					vtype: 'isOverMax',
					maxLength: 15,
					allowBlank: false,
					blankText: '姓名不能为空',
				},{
					xtype: 'combo',
					displayField: 'name',
		   			valueField: 'value',
					emptyText: '请选择性别',
					name: 'tblCompanyEmployee.sex',
					queryMode: 'local',
					editable: false,
					blankText: '性别不能为空',
					fieldLabel: '性别*',
					store: Ext.create('Ext.data.Store',{
		   				fields: ['value','name'],
		   				data: [{'value':'0','name':'男'},
		   				       {'value':'1','name':'女'}]
		   			})
		   			
				},{
					xtype: 'combo',
					displayField: 'name',
					valueField: 'value',
					emptyText: '请选择人员部门',
					queryMode: 'local',
					blankText: '人员部门不能为空',
					fieldLabel: '人员部门*',
					editable: false,
					name: 'tblCompanyEmployee.stepment',
					store: stepmentStore,
				},{
					xtype: 'combo',
					store: PositionStore,
					displayField: 'name',
					valueField: 'value',
					emptyText: '请选择职位',
					name: 'tblCompanyEmployee.job',
					queryMode: 'local',
					editable: false,
					allowBlank: false,
					blankText: '职位不能为空',
					fieldLabel: '职位*',
				},{
					xtype: 'combo',
					store: Ext.create('Ext.data.Store',{
		   				fields: ['value','name'],
		   				data: [{'value':'0','name':'主管'},{'value':'1','name':'一级'},{'value':'2','name':'二级'},
		   					       {'value':'3','name':'三级'},{'value':'4','name':'四级'},{'value':'5','name':'五级'},
		   					       {'value':'6','name':'见习'},{'value':'7','name':'实习生'},{'value':'8','name':'正式员工'}]
		   			}),
		   			displayField: 'name',
		   			valueField: 'value',
		   			name: 'tblCompanyEmployee.levels',
		   			queryMode: 'local',
		   			editable: false,
					allowBlank: true,
					fieldLabel: '级别'
				},{
					xtype: 'combo',
					store: allEmpStore,
					displayField: 'name',
					valueField: 'value',
					emptyText: '请选择上级',
					name: 'tblCompanyEmployee.parentId',
					queryMode: 'local',
					editable: true,
					allowBlank: false,
					blankText: '上级不能为空',
					fieldLabel: '上级*',
				},{
					xtype : 'combo',
		   			store: Ext.create('Ext.data.Store',{
		   				fields: ['value','name'],
		   				data: [{'value':'0','name':'博士'},{'value':'1','name':'研究生'},{'value':'2','name':'本科'},
		   				       {'value':'3','name':'大专'},{'value':'4','name':'中专'},{'value':'5','name':'初中'}]
		   			}),
		   			fieldLabel: '学历',
		   			displayField: 'name',
		   			valueField: 'value',
		   			emptyText: '请选择学历',
		   			name: 'tblCompanyEmployee.edution',
		   			queryMode: 'local',
		   			editable: false
				},{
					fieldLabel: '毕业院校',
					name: 'tblCompanyEmployee.graduate',
					vtype: 'isOverMax',
					maxLength: 15
				},{
					fieldLabel: '专业',
					name: 'tblCompanyEmployee.professional',
					vtype: 'isOverMax'
				},{
					xtype: 'datefield',
					name: 'tblCompanyEmployee.entryTime',
					format: 'Ymd',
					editable:false,
					fieldLabel: '入职时间',
					blankText: '请选择入职时间',
				},{
					xtype: 'datefield',
					name: 'tblCompanyEmployee.rotationTime',
					format: 'Ymd',
					editable:false,
					fieldLabel: '转正时间',
					blankText: '请选择转正时间',
				},{
					fieldLabel: '联系电话',
					maxLength: 20,
					vtype: 'isOverMax',
					name: 'tblCompanyEmployee.telephone'
				},{
					fieldLabel: '手机*',
					allowBlank: false,
					maxLength: 20,
					name: 'tblCompanyEmployee.mobiephone',
					vtype: 'isOverMax'
				},{
					fieldLabel: '短号',
					allowBlank: true,
					maxLength: 20,
					vtype: 'isOverMax',
					name: 'tblCompanyEmployee.fax',
					regex:/^[0-9]{1,20}$/,
					regexText:'短号只能为数字，请重新输入'
				},{
					fieldLabel: '邮箱',
					maxLength: 40,
					vtype: 'isOverMax',
					name: 'tblCompanyEmployee.email',
					vtype: 'email',
					regex:/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
					regexText:'邮箱格式输入有误，请重新输入'
				},{
					xtype: 'datefield',
					name: 'tblCompanyEmployee.birthday',
					format: 'Ymd',
					editable:false,
					fieldLabel: '生日',
					blankText: '请选择生日日期',
				},{
					fieldLabel: '现住地址',
					name: 'tblCompanyEmployee.address',
					vtype: 'isOverMax',
					maxLength: 80,
				},{
					fieldLabel: '户籍地址',
					name: 'tblCompanyEmployee.permanentAddress',
					vtype: 'isOverMax',
					maxLength: 80,
				},{
					fieldLabel: '身份证号码',
					name: 'tblCompanyEmployee.identityNum',
					regex:/^[0-9a-zA-Z]{1,20}$/,
					regexText:'身份证号码输入有误，请重新输入',
					vtype: 'isOverMax',
					maxLength: 20,
				},{
					fieldLabel: '紧急联系人',
					name: 'tblCompanyEmployee.contact',
					vtype: 'isOverMax',
					maxLength: 15,
				},{
					fieldLabel: '紧急联系电话',
					name: 'tblCompanyEmployee.contactTelephone',
					regex:/^[0-9]{1,20}$/,
					vtype: 'isOverMax',
					maxLength: 20,
				},{
					xtype: 'datefield',
					fieldLabel: '离职时间',
					name: 'tblCompanyEmployee.overtime',
					editable:false,
					format : 'Ymd',
					vtype: 'isOverMax',
					margin : '10 10 10 10',
					maxLength: 8,
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