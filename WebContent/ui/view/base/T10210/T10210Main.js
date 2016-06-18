Ext.Loader.setConfig({
	enabled : true,
	disableCaching : false
});

//名配置项
var columns =  [ 
{ text: '参数属主',dataIndex: 'owner', sortable: true, hideable: false, flex: 1},
{ text: '参数键值',dataIndex: 'key', sortable: true, hideable: false, flex: 1},
{ text: '类型',dataIndex: 'type', sortable: true,renderer:typeRenderer, hideable: false, flex: 1},
{ text: '显示名称',dataIndex: 'value', sortable: true, hideable: false, flex: 1,
	field: {
		xtype: 'textfield',
		maxLength: 200,
		allowBlank: false,
		vtype: 'isOverMax'
	}
},
{ text: '参数描述',dataIndex: 'descr', sortable: true, hideable: false, flex: 1,
	field: {
		xtype: 'textfield',
		maxLength: 60,
		allowBlank: false,
		vtype: 'isOverMax'
	}
},
{ text: '备注',dataIndex: 'reserve', sortable: true, hideable: false, flex: 1,
	field: {
		xtype: 'textfield',
		maxLength: 64,
		allowBlank: true,
		vtype: 'isOverMax'
	}
}];

Ext.define('zft.view.base.T10210.T10210Main', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.t10210Main',
	region : 'center',
	layout : 'fit',
	autoDestroy : true,
	store : 'base.T10210GridStore',
	columns : columns,
	columnLines: true,
	plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {
		clicksToEdit : 2
	}) ],
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'base.T10210GridStore',//..子类中定义
		dock : 'bottom',
		displayInfo : true
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});
function typeRenderer(val) {
	if(val == "00") {
		return "可维护";
	} else {
		return "不可维护";
	}
}