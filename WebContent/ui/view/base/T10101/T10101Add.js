Ext.define('zft.view.base.T10101.T10101Add', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10101Add',
	title : '机构增加',
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
		// 机构级别
		var brhLvlStore = new Ext.data.JsonStore({
			autoDestroy: true,
			fields: ['value','name'],
			root: 'data'
		});
		SelectOptionsDWR.getComboDataWithParameter('CODE_LIBRARY','BrhLevel',function(ret) {
			brhLvlStore.loadData(eval(ret));
		});
		// 机构类型
		var brhTypeStore = new Ext.data.JsonStore({
			autoDestroy: true,
			fields: ['value','name'],
			root: 'data'
		});
		SelectOptionsDWR.getComboDataWithParameter('CODE_LIBRARY','BrhType',function(ret) {
			brhTypeStore.loadData(eval(ret));
		});
		// 上级机构信息
		var upBrhStore = new Ext.data.JsonStore({
			autoDestroy: true,
			fields: ['value','name'],
			root: 'data'
		});
		SelectOptionsDWR.getComboData('UP_BRH_ID',function(ret){
			upBrhStore.loadData(eval(ret));
		});
		// 所在地区数据集
		var cityStore = new Ext.data.JsonStore({
			autoDestroy: true,
			fields: ['value','name'],
			root: 'data'
		});
		SelectOptionsDWR.getComboData('CUP_CODE',function(ret){
			cityStore.loadData(eval(ret));
		});
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
					editable : true
				},
				waitMsgTarget : true,
				items : [{
					fieldLabel : '行内机构编码*',
					allowBlank : false,
					emptyText : '请输入行内机构编号',
					name : 'brhId',
					maxLength : 10,
					maxLengthText : '行内机构编号最多可以输入10个数字',
					regex:/^[0-9]+$/,
					blankText : '该输入项只能包含数字'
				}, {
					xtype : 'combo',
					store : brhLvlStore,
					displayField : 'name',
					valueField : 'value',
					name : 'brhLvl',
					emptyText : '请选择机构级别',
					fieldLabel : '机构级别*',
					queryMode : 'local',
					triggerAction : 'all',
					forceSelection : true,
					typeAhead : true,
					selectOnFocus : true,
					editable : false,
					allowBlank : false,
					blankText : '请选择一个机构级别'
				}, {
					xtype : 'combo',
					store : brhTypeStore,
					displayField : 'name',
					valueField : 'value',
					name : 'brhType',
					emptyText : '请选择机构级别',
					fieldLabel : '机构类型*',
					queryMode : 'local',
					triggerAction : 'all',
					editable : false,
					forceSelection : true,
					typeAhead : true,
					selectOnFocus : true,
					allowBlank : false,
					blankText : '请选择一个机构类型'
				}, {
					xtype : 'combo',
					store : upBrhStore,
					displayField : 'name',
					valueField : 'value',
					name : 'upBrhId',
					emptyText : '请选择上级机构编码',
					fieldLabel : '上级机构编码*',
					queryMode : 'local',
					triggerAction : 'all',
					forceSelection : true,
					typeAhead : true,
					editable : false,
					selectOnFocus : true,
					allowBlank : false,
					blankText : '请选择一个上级机构编码'
				}, {
					xtype : 'combo',
					fieldLabel : '所在地区码*',
					store : cityStore,
					displayField : 'name',
					valueField : 'value',
					queryMode : 'local',
					triggerAction : 'all',
					forceSelection : true,
					typeAhead : true,
					selectOnFocus : true,
					allowBlank : true,
					editable : false,
					emptyText : '请选择分店所在区域',
					name : 'resv1'
				},{
					fieldLabel : '机构名称*',
					allowBlank : false,
					blankText : '机构名称不能为空',
					emptyText : '请输入机构名称',
					name : 'brhName',
					maxLength : 40,
					maxLengthText : '机构名称最多可以输入40个汉字'
				},{
					fieldLabel : '机构地址*',
					allowBlank : false,
					blankText : '机构地址不能为空',
					emptyText : '请输入机构地址',
					name : 'brhAddr',
					maxLength : 40,
					maxLengthText : '机构地址最多可以输入40个汉字'
				}, {
					fieldLabel : '机构联系电话*',
					allowBlank : false,
					emptyText : '请输入机构联系电话',
					name : 'brhTelNo',
					maxLength : 20,
					maxLengthText : '机构联系电话最多可以输入20个数字',
					regex:/^[0-9]+$/,
					blankText : '该输入项只能包含数字'
				}, {
					fieldLabel : '邮政编码*',
					allowBlank : false,
					emptyText : '请输入机构邮政编码',
					name : 'postCode',
					minLength : 6,
					minLengthText : '机构邮政编码必须是6个数字',
					maxLength : 6,
					maxLengthText : '机构邮政编码必须是6个数字',
					regex:/^[0-9]+$/,
					blankText : '该输入项只能包含数字'
				}, {
					fieldLabel : '机构联系人',
					emptyText : '请输入机构联系人',
					name : 'brhContName',
					maxLength : 20,
					maxLengthText : '负责人姓名最多可以输入20个汉字'
				}, {
					fieldLabel : '银联机构号*',
					emptyText : '请输入银联机构号',
					allowBlank : false,
					minLength : 8,
					minLengthText : '银联机构号必须是8个数字',
					maxLength : 8,
					maxLengthText : '银联机构号必须是8个数字',
					name : 'cupBrhId',
					regex:/^[0-9]+$/,
					blankText : '该输入项只能包含数字',
					margin : '10 10 10 10',
				} ]
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