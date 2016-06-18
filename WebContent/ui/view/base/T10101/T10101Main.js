Ext.Loader.setConfig({
	enabled : true,
	disableCaching : false
});

Ext.define('zft.view.base.T10101.T10101Main', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.t10101Main',
	layout : 'fit',
	autoDestroy : true,
	config : {
		upBrhStore : ''
	},
	initComponent : function() {
		var me = this;
		// 机构信息
		var brhStore = new Ext.data.JsonStore({
			autoDestroy: true,
			fields: ['value','name'],
			root: 'data'
		});
		SelectOptionsDWR.getComboData('OPR_BELOW_BRH',function(ret){
			brhStore.loadData(eval(ret));
		});	
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
		me.upBrhStore = new Ext.data.JsonStore({
			autoDestroy: true,
			fields: ['value','name'],
			root: 'data'
		});
		SelectOptionsDWR.getComboData('UP_BRH_ID',function(ret){
			me.upBrhStore.loadData(eval(ret));
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
		
		var columns = [{xtype: 'rownumberer',header: '序号',align :'center',width : 50},
		            {header: '机构编号',dataIndex: 'brhId',sortable: true ,align :'center'},
		       		{header: '机构级别',dataIndex: 'brhLvl',renderer: brhLvlRender,align :'center',
		       		 editor: {
		       			xtype: 'combo',
		       			queryMode: 'local',
		       		 	store: brhLvlStore,
		       			displayField: 'name',
		       			valueField: 'value',
		       			triggerAction: 'all',
		       			forceSelection: true,
		       			typeAhead: true,
		       			selectOnFocus: true,
		       			editable: true,
		       			lazyRender: true
		       		 }},
		       		{header: '机构类型',dataIndex: 'brhType',renderer: brhTypeRender,align :'center',
		       		 editor: {
		       			xtype: 'combo',
		       			queryMode: 'local',
		       		 	store: brhTypeStore,
		       		 	displayField: 'name',
		       		 	valueField: 'value',
		       			triggerAction: 'all',
		       			forceSelection: true,
		       			typeAhead: true,
		       			selectOnFocus: true,
		       			editable: true,
		       			lazyRender: true
		       		 }},
		       		{header: '上级机构编号',dataIndex: 'upBrhId',width : 180,align :'center',
		       		 editor: {
		       			xtype: 'combo',
		       			queryMode: 'local',
		       		 	store: me.upBrhStore,
		       			displayField: 'name',
		       			valueField: 'value',
		       			triggerAction: 'all',
		       			forceSelection: true,
		       			typeAhead: true,
		       			selectOnFocus: true,
		       			editable: true
		       		 }},
		       		{header: '机构名称',dataIndex: 'brhName',width : 180,align :'center',
		       		field: {
		       			xtype: 'textfield',
		       		 	allowBlank: false,
		       			blankText: '机构名称不能为空',
		       			emptyText: '请输入机构名称',
		       			maxLength: 40,
		       			maxLengthText: '机构名称最多可以输入40个汉字'
		       		 }},
		       		{header: '机构地址',dataIndex: 'brhAddr',width : 180,align :'center',
		       			field: {
			       		xtype: 'textfield',
		       		 	allowBlank: false,
		       			blankText: '机构地址不能为空',
		       			emptyText: '请输入机构地址',
		       			maxLength: 40,
		       			maxLengthText: '机构地址最多可以输入40个汉字'
		       		 }},
		       		{header: '机构联系电话',dataIndex: 'brhTelNo',align :'center',
			       	field: {
					    xtype: 'textfield',
		       		 	allowBlank: false,
		       			blankText: '机构联系电话不能为空',
		       			emptyText: '请输入机构联系电话',
		       			maxLength: 20,
		       			maxLengthText: '机构联系电话最多可以输入20个数字',
		       			maskRe:/^[0-9]$/,
		       			blankText: '该输入项只能包含数字'
		       		 }},
		       		{header: '邮政编码',dataIndex: 'postCode',align :'center',
		       		field: {
						xtype: 'textfield',
		       		 	allowBlank: false,
		       			blankText: '机构邮政编码不能为空',
		       			emptyText: '请输入机构邮政编码',
		       			minLength: 6,
		       			minLengthText: '机构邮政编码必须是6个数字',
		       			maxLength: 6,
		       			maxLengthText: '机构邮政编码必须是6个数字',
		       			maskRe:/^[0-9]$/,
		       			blankText: '该输入项只能包含数字'
		       		 }},
		       		{header: '机构联系人',dataIndex: 'brhContName',align :'center',
		       		field: {
						xtype: 'textfield',
		       		 	allowBlank: false,
		       			emptyText: '请输入负责人姓名',
		       			maxLength: 20,
		       			maxLengthText: '负责人姓名最多可以输入20个汉字'
		       		 }},
		       		 {header: '所在地区码',dataIndex: 'resv1',align :'center',
		       			 field: {
						xtype: 'textfield',
		       		 	allowBlank: false,
		       			emptyText: '请输入所在地区码',
		       			maxLength: 4,
		       			maxLengthText: '地区国标码最多可以输入4个数字',
		       			maskRe:/^[0-9]$/,
		       			blankText: '该输入项只能包含数字'
		       		 }},
		       		{header: '银联机构号',dataIndex: 'cupBrhId',align :'center',
		       		field: {
						xtype: 'textfield',
		       		 	allowBlank: false,
		       			emptyText: '请输入银联机构号',
		       			minLength: 8,
		       			minLengthText: '银联机构号必须是8个数字',
		       			maxLength: 8,
		       			maxLengthText: '银联机构号必须是8个数字',
		       			maskRe:/^[0-9]$/,
		       			blankText: '该输入项只能包含数字'
		       		 }}
		       	];
		
		Ext.apply(me,{
			store : 'base.T10101GridStore',
			columns : columns,
			autoScroll : true,
			columnLines: true,
			/*plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit : 2
			})],*/
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : 'base.T10101GridStore',
				dock : 'bottom',
				displayInfo : true
			}]
		});
		me.callParent(arguments);
	}
});
