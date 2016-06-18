Ext.Loader.setConfig({
	enabled : true
});

Ext.apply(Ext.form.VTypes,{
	isNumber: function(value,f) {
		var reg = new RegExp("^\\d+$");
		return reg.test(value);
	},
	isNumberText: '该输入项只能包含数字'
});

/**
 * 角色级别
 */
function roleLevel(val) {
	switch(val) {
		case '0' : return '总公司';
		case '1' : return '分公司';
	}
}
var columns = [
    {
    	text: '角色编号',
    	dataIndex: 'roleId',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	flex: 1
    },{
    	text: '角色名称',
    	dataIndex: 'roleName',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	field:{
			xtype: 'textfield',
			allowBlank: false,
			maxLength: 64,
			vtype: 'isOverMax',
			blankText: '角色名称不能为空',
			maxLengthText: '角色名称最多可以输入32个汉字'
		},
    	flex: 1
    },{
    	text: '角色级别',
    	dataIndex: 'roleType',
    	renderer:roleLevel,
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	flex: 1
    },{
    	text: '角色描述',
    	dataIndex: 'description',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	field:{
			xtype: 'textfield',
			allowBlank: false,
			maxLength: 1024,
			vtype: 'isOverMax'
		},
    	flex: 1
    },{
    	text: '最后更新操作员',
    	dataIndex: 'recUpdOpr',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	flex: 1
    },{
    	text: '创建时间',
    	dataIndex: 'recCrtTs',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	renderer:formatTs,
    	flex: 1
    },{
    	text: '最后更新时间',
    	dataIndex: 'recUpdTs',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	renderer:formatTs,
    	flex: 1
    }
];
Ext.define('zft.view.base.T10301.T10301Main', {
	extend : 'zft.view.system.BaseGridPanel',
	alias : 'widget.t10301Main',
	id : 't10301Main',
	store : 'base.T10301GridStore',
	columns :columns,
	columnLines: true,
	layout : 'fit',
	autoDestroy : true,
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'base.T10301GridStore',
		dock : 'bottom',
		displayInfo : true
	} ]
});
