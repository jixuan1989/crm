Ext.Loader.setConfig({
	enabled : true,
	disableCaching : false
});

Ext.define('zft.view.base.T10201.T10201Main', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.t10201Main',
	layout : 'fit',
	autoDestroy : true,
	initComponent : function() {
		var me = this;
		var columns = [{xtype: 'rownumberer',header: '序号',align :'center',flex : 1},
		            {header: '银联地区代码',dataIndex: 'cupCityCode',sortable: true,flex : 1},
		       		{header: '商户地区代码',dataIndex: 'mchtCityCode',sortable: true,flex : 1},
		       		{header: '地区名称',dataIndex: 'cityName',id:'cityName',flex : 1,
		       			field: {
							xtype: 'textfield',
			       		 	allowBlank: false,
			       			maxLength: 20,
			       			vtype: 'isOverMax'
			       		 }},
		       		{header: '创建时间',dataIndex: 'initTime',align: 'center',renderer: formatTs,flex : 1},
		       		{header: '最近修改时间',dataIndex: 'modiTime',align: 'center',renderer: formatTs,flex : 1}
		       	];
		
		Ext.apply(me,{
			store : 'base.T10201GridStore',
			columns : columns,
			autoScroll : true,
			columnLines: true,
			plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit : 2
			})],
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : 'base.T10201GridStore',
				dock : 'bottom',
				displayInfo : true
			}]
		});
		me.callParent(arguments);
	}
});
