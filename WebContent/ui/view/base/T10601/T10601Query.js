Ext.define('zft.view.base.T10601.T10601Query', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10601Query',
	
	initComponent : function() {
		var me = this;
		// 职位	
		var PositionStore = new Ext.data.JsonStore({
			fields: ['value','name']
		});
		SelectOptionsDWR.getComboData('POSITIONINFO',function(ret){
			PositionStore.loadData(eval(ret));
		});
		// 部门
		var stepmentStore = new Ext.data.JsonStore({
			fields: ['value','name']
		});
		SelectOptionsDWR.getComboData('STEPMENT',function(ret){
			stepmentStore.loadData(eval(ret));
		});
		Ext.apply(me,{
			title : '查询条件',
			layout : 'fit',
			width : 400,
			autoHeight : true,
			closeAction : 'hide',
			autoShow : true,
			buttonAlign : 'center',
			resizable : false,
			closable : true,
			items : [{
				xtype : 'form',
				width : 380,
				autoHeight : true,
				lablewidth : 10,
				layout : 'column',
				defaults :{
					xtype: 'textfield',
					margin : '10 10 0 10',
					columnWidth : .9,
				},
				items : [{
					name: 'employeeNew',
					vtype: 'alphanum',
					fieldLabel: '员工号'
				},{
					xtype: 'textfield',
					name: 'employeeName',
					fieldLabel: '人员姓名'
				},{
					xtype:'combo',
					store: stepmentStore,
					displayField: 'name',
					valueField: 'value',
					emptyText: '请选择所属部门',
					queryMode: 'local',
					editable: false,
					editable: false,
					blankText: '请选择一个部门',
					fieldLabel: '人员部门',
					name: 'stepment'
				},{
					xtype: 'combo',
					store:PositionStore,
					displayField: 'name',
					valueField: 'value',
					emptyText: '请选择职位',
					name: 'job',
					queryMode: 'local',
					editable: false,
					fieldLabel: '职位'
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
					emptyText: '请选择级别',
					name: 'levels',
					queryMode: 'local',
					editable: false,
					margin : '10 10 10 10',
					fieldLabel: '级别'
				}]
			}],
			buttons : [ {
				text : '查询',
				name : 'query'
			}, {
				text : '清除查询条件',
				name : 'reset'
			}]
		});
		this.callParent(arguments);
	}
});
