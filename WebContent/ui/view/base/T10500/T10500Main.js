Ext.Loader.setConfig({
	enabled : true,
	disableCaching : false
});

Ext.define('zft.view.base.T10500.T10500Main', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.t10500Main',
	layout : 'fit',
	autoDestroy : true,
	initComponent : function() {
		var me = this;
		//获取机构信息
		var BrhStore = Ext.create('Ext.data.Store',{
	        fields: ['value','name']
	    });
		SelectOptionsDWR.getComboData('BRH_BELOW_BRANCH',function(ret){
			BrhStore.loadData(eval(ret));
		});/*
		function brhRend(val){
			var index = BrhStore.find('value',val);
			alert(index);
			var record = BrhStore.getAt(index);
			if(null != record && undefined != record){
				return record.name;
			}else{
				return val;
			}
		};*/
		var columns = [{xtype: 'rownumberer',header: '序号',align :'center',width : 50},
		               {header: '部门编号',dataIndex: 'stepId',sortable: true,width: 200},
		       		{header: '部门名称',dataIndex: 'name',width: 300,
		            field: {
						xtype: 'textfield',
		       			allowBlank: false,
		       			blankText: '部门名称不能为空',
		       			maxLength: 20,
		       			vtype: 'isOverMax'
		       		}},
		       		{header: '部门所属机构',dataIndex: 'brhId',width: 170,align: 'center',/*renderer: brhRend,*/
		       			editor: {
			       			xtype: 'combo',
			       			queryMode: 'local',
			       		 	store: BrhStore,
			       			displayField: 'name',
			       			valueField: 'value',
			       			triggerAction: 'all',
			       			forceSelection: true,
			       			typeAhead: true,
			       			selectOnFocus: true,
			       			editable: true,
			       			lazyRender: true
			       		 }}
		       	];
		var tbar = [ {
			text : '添加',
			width : 85,
			id : 'add',
			margin : '0 20 0 0',
			iconCls : 'add'
		},{
			text : '保存',
			width : 85,
			id : 'reload',
			disabled: true,
			margin : '0 20 0 0',
			iconCls : 'reload'
		}];
		
		Ext.apply(me,{
			store : 'base.T10500GridStore',
			columns : columns,
			autoScroll : true,
			columnLines: true,
			/*plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit : 2
			})],*/
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : 'base.T10500GridStore',
				dock : 'bottom',
				displayInfo : true
			}]
		});
		me.callParent(arguments);
	}
});
