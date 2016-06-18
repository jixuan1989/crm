Ext.define('SelectedMenuModel', {
	extend : 'Ext.data.Model',
	fields : [ 'value', 'name', 'ro', 'op','tpl' ]
});
function renderRole(val) {
	var boxes = document.getElementsByName(val + "_check");
	if (boxes.length == 4) {
		var returnStr = '<input type="checkbox" value="1" name=' + val
				+ '_check checked="checked" disabled="true"/>查询   ';
		if (boxes[1].checked) {
			returnStr = returnStr
					+ '<input type="checkbox"  checked="checked" value="2" name='
					+ val + '_check />增加    ';
		} else {
			returnStr = returnStr + '<input type="checkbox"  value="2" name='
					+ val + '_check />增加    ';
		}
		if (boxes[2].checked) {
			returnStr = returnStr
					+ '<input type="checkbox"  checked="checked" value="3" name='
					+ val + '_check />删除    ';
		} else {
			returnStr = returnStr + '<input type="checkbox"  value="3" name='
					+ val + '_check />删除   ';
		}
		if (boxes[3].checked) {
			returnStr = returnStr
					+ '<input type="checkbox"  checked="checked" value="4" name='
					+ val + '_check />修改    ';
		} else {
			returnStr = returnStr + '<input type="checkbox"  value="4" name='
					+ val + '_check />修改    ';
		}
		return returnStr;
	} else {
		var returnStr = '<input type="checkbox" value="1" name=' + val
				+ '_check checked="checked" disabled="true"/>查询   ';
		returnStr = returnStr + '<input type="checkbox" value="2" name=' + val
				+ '_check />增加    ';
		returnStr = returnStr + '<input type="checkbox" value="3" name=' + val
				+ '_check />删除   ';
		returnStr = returnStr + '<input type="checkbox" value="4" name=' + val
				+ '_check />修改    ';
		return returnStr;
	}

};

function renderDelete(val) {
	return '<img src="' + Ext.contextPath
			+ '/ext-4.2/resources/images/recycle.png" '
			+ 'title="删除此菜单" onclick=deleteMenu(' + val
			+ ') onmouseover="this.style.cursor=\'pointer\'"/>';
};

function deleteMenu(val) {
	//var me = this;
	var grid = Ext.getCmp('selectedMenuGrid');
	var store = grid.getStore();
	var records = grid.getSelectionModel().getSelection();
	var rec = records[0];
	store.remove(rec);
	var menuArray = new Array();
	store.each(function(rec){
		var tpl = rec.get('tpl');
		var boxes =  document.getElementsByName(rec.get('value')+ "_check");
		for(var i =1;i<boxes.length + 1;i++){
			if(boxes[i -1].checked){
				//alert(tpl.substr(tpl.indexOf("value='" + i + "'"),tpl.length));
				tpl = tpl.substr(0,tpl.indexOf("value='" + i + "'")) + " checked='true' " + tpl.substr(tpl.indexOf("value='" + i + "'"),tpl.length);
			}
		}
		var selectedRec = Ext.create('SelectedMenuModel', {
			name : rec.get('name'),
			value : rec.get('value'),
			tpl : tpl
		});
		menuArray.push(selectedRec);
	},this);
	store.removeAll();
	for(var i = 0;i<menuArray.length;i++){
		store.add(menuArray[i]);
	}
};

Ext.define('zft.view.base.T10301.T10301Add', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10301Add',
	requires : [ 'Ext.window.Window', 'Ext.form.Panel' ],
	title : '角色添加',
	layout : 'fit',
	width : 1000,
	autoHeight : true,
	autoShow : true,
	buttonAlign : 'center',
	resizable : false,
	closable : true,
	modal: true,
	id : 't10301AddWin',
	animateTarget : 'add',
	items : [ {
		xtype : 'form',
		autoHeight : true,
		width : 1000,
		labelWidth : 10,
		waitMsgTarget : true,
		defaults : {
		    margin : '10 20 10 20'
		},
		items : [
				{
					xtype : 'panel',
					layout : 'table',
					defaultType : 'textfield',
					autoHeight : true,
					defaults : {
						flex : 1,
						labelWidth : 70,
						padding : 10
					},
					items : [ {
						fieldLabel : '角色名称*',
						allowBlank : false,
						blankText : '角色名称不能为空',
						emptyText : '请输入角色名称',
						name : 'roleName',
						id : 'roleName',
						maxLength : 64,
						vtype : 'isOverMax'
					}, {
						xtype : 'combo',
						mode : 'local',
						triggerAction : 'all',
						forceSelection : true,
						editable : false,
						emptyText : '请选择角色级别',
						fieldLabel : '角色级别*',
						id : 'roleType',
						name : 'roleType',
						displayField : 'name',
						valueField : 'value',
						queryMode : 'local',
						forceSelection : true,
						allowBlank : false,
						store : roleLevelStore
					}, {
						fieldLabel : '角色描述',
						allowBlank : true,
						emptyText : '请输入角色描述',
						id : 'description',
						name : 'description',
						maxLength : 1024,
						vtype : 'isOverMax'
					} ]

				},
				{
					xtype : 'panel',
					title : '<center>请为该角色选择权限</center>',
					layout : 'table',
					items : [ {
						xtype : 'gridpanel',
						store : menuLvl1Store,
						columnLines : true,
						stripeRows : true,
						height : 300,
						width : 150,
						columns : [ {
							text : '菜单编号',
							dataIndex : 'value',
							hidden : true,
							align : 'center',
							flex : 1
						}, {
							text : '<center>一级菜单</center>',
							dataIndex : 'name',
							align : 'center',
							menuDisabled : true,
							resizable : false,
							flex : 1
						} ],
						listeners : {
							itemclick : function(view, record, item, index, e) {
								Ext.getCmp('menuLvl2Grid').getStore().removeAll();
								SelectOptionsDWR.getComboDataWithParameter('MENU_LEVEL',
										record.raw.value, function(ret) {
											menuLvl2Store.loadData(eval(ret));
										});
							}
						}
					}, {
						xtype : 'gridpanel',
						store : menuLvl2Store,
						columnLines : true,
						stripeRows : true,
						height : 300,
						width : 150,
						id : 'menuLvl2Grid',
						columns : [ {
							text : '菜单编号',
							dataIndex : 'value',
							hidden : true,
							align : 'center',
							flex : 1
						}, {
							text : '<center>二级菜单</center>',
							dataIndex : 'name',
							align : 'center',
							menuDisabled : true,
							resizable : false,
							flex : 1
						} ],
						listeners : {
							itemclick : function(view, record, item, index, e) {
								Ext.getCmp('menuLvl3Grid').getStore().removeAll();
								SelectOptionsDWR.getComboDataWithParameter('MENU_LEVEL',
										record.raw.value, function(ret) {
											menuLvl3Store.loadData(eval(ret));
										});
							}
						}
					}, {
						xtype : 'gridpanel',
						store : menuLvl3Store,
						columnLines : true,
						stripeRows : true,
						height : 300,
						width : 150,
						id : 'menuLvl3Grid',
						columns : [ {
							text : '菜单编号',
							dataIndex : 'value',
							hidden : true,
							align : 'center',
							flex : 1
						}, {
							text : '<center>三级菜单</center>',
							dataIndex : 'name',
							align : 'center',
							menuDisabled : true,
							resizable : false,
							flex : 1
						} ],
						listeners : {
							itemclick : function(view, record, item, index, e) {
								var recordValue = record.raw.value;
								var recordName = record.raw.name;
								var itemIndex = selectedMenuStore.findExact("value",
										record.raw.value);
								MenuDwr.getRole(recordValue,function(ret){
									var tpl = ret;
	                               	if(ret!=null){
	                               		if (itemIndex == -1) {
	    									var selectedRec = Ext.create('SelectedMenuModel', {
	    										name : recordName,
	    										value : recordValue,
	    										tpl : tpl
	    									});
	    									selectedMenuStore.add(selectedRec);
	    								}
	                               	}
	                            });
								//var tpl = '<table width="85%" style="word-break:break-all; word-wrap:break-all;"><tr><td><input type="checkbox" name={value}_check value="2" />增加    </td><td><input type="checkbox" name={value}_check value="3" />修改    </td><td><input type="checkbox" name={value}_check value="4" />删除    </td><tr></table>';
							}
						}
					},{
						xtype : 'gridpanel',
						plugins : [ {
							ptype : 'rowexpander',
							//expandOnEnter : true,
							//pluginId : '1',
							//rowBodyTpl : ['{tpl}']
							rowBodyTpl : new Ext.XTemplate('{tpl}')
						} ],
						store : selectedMenuStore,
						columnLines : true,
						stripeRows : true,
						height : 300,
						width : 500,
						id : 'selectedMenuGrid',
						columns : [ {
							text : '菜单编号',
							dataIndex : 'value',
							hidden : true,
							align : 'center',
							flex : 1
						}, {
							text : '<center>已选菜单</center>',
							dataIndex : 'name',
							align : 'center',
							width : 430,
							menuDisabled : true,
							resizable : false
						}, {
							menuDisabled : true,
							resizable : false,
							flex : 1,
							renderer : renderDelete
						} ]
					} ]
				} ]
	} ],

	buttons : [ {
		text : '确定',
	}, {
		text : '重置',
	}, {
		text : '关闭',
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});