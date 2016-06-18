Ext.define('SelectedMenuModel', {
	extend : 'Ext.data.Model',
	fields : [ 'value', 'name', 'ro', 'op','tpl' ]
});

function renderDelete(val) {
	return '<img src="' + Ext.contextPath
			+ '/ext-4.2/resources/images/recycle.png" '
			+ 'title="删除此菜单" onclick=deleteEditMenu(' + val
			+ ') onmouseover="this.style.cursor=\'pointer\'"/>';
};

function deleteEditMenu(val) {
	var grid = Ext.getCmp('editSelectedMenuGrid');
	var store = grid.getStore();
	var records = grid.getSelectionModel().getSelection();
	var rec = records[0];
	store.remove(rec);
	var menuArray = new Array();
	store.each(function(rec){
		var tpl = rec.get('tpl');
		console.log(tpl);
		var boxes =  document.getElementsByName(rec.get('value')+ "_check");
		for(var i =1;i<boxes.length + 1;i++){
			if(boxes[i -1].checked){
				console.log(rec.get('value') + "-------" + tpl.substr(tpl.substr(0,tpl.indexOf("value='" + i + "'"))));
				tpl = tpl.substr(0,tpl.indexOf("value='" + i + "'")) + " checked='true' " + tpl.substr(tpl.indexOf("value='" + i + "'"),tpl.length);
			}
			
		}
		console.log(tpl);
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

Ext.define('zft.view.base.T10301.T10301Edit', {
	extend : 'Ext.window.Window',
	alias : 'widget.t10301Edit',
	requires : [ 'Ext.window.Window', 'Ext.form.Panel' ],
	title : '角色编辑',
	layout : 'fit',
	width : 1000,
	autoHeight : true,
	autoShow : false,
	buttonAlign : 'center',
	resizable : false,
	closable : true,
	modal: true,
	id : 't10301EditWin',
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
						labelWidth : 90,
						padding : 10
					},
					items : [ {
						fieldLabel : '角色名称*',
						id : 'editRoleName',
						disabled: true
					}, {
						xtype : 'combo',
						mode : 'local',
						id : 'editRoleType',
						disabled: true,
						triggerAction : 'all',
						forceSelection : true,
						editable : false,
						displayField : 'name',
						valueField : 'value',
						queryMode : 'local',
						forceSelection : true,
						store : roleLevelStore
					}, {
						fieldLabel : '角色描述',
						id : 'editDescription',
						disabled: true
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
								Ext.getCmp('editMenuLvl2Grid').getStore().removeAll();
								SelectOptionsDWR.getComboDataWithParameter('MENU_LEVEL',
										record.raw.value, function(ret) {
									editMenuLvl2Store.loadData(eval(ret));
										});
							}
						}
					}, {
						xtype : 'gridpanel',
						store : editMenuLvl2Store,
						columnLines : true,
						stripeRows : true,
						height : 300,
						width : 150,
						id : 'editMenuLvl2Grid',
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
								Ext.getCmp('editMenuLvl3Grid').getStore().removeAll();
								SelectOptionsDWR.getComboDataWithParameter('MENU_LEVEL',
										record.raw.value, function(ret) {
									editMenuLvl3Store.loadData(eval(ret));
										});
							}
						}
					}, {
						xtype : 'gridpanel',
						store : editMenuLvl3Store,
						columnLines : true,
						stripeRows : true,
						height : 300,
						width : 150,
						id : 'editMenuLvl3Grid',
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
								//Ext.getCmp('editSelectedMenuStore').getStore().removeAll();
								var recordValue = record.raw.value;
								var recordName = record.raw.name;
								var itemIndex = editSelectedMenuStore.findExact("name",
										record.raw.name);
								MenuDwr.getRole(recordValue,function(ret){
									var tpl = ret;
	                               	if(ret!=null){
	                               		if (itemIndex == -1) {
	    									var selectedRec = Ext.create('SelectedMenuModel', {
	    										name : recordName,
	    										value : recordValue,
	    										tpl : tpl
	    									});
	    									editSelectedMenuStore.add(selectedRec);
	    								}
	                               	}
	                            });
							}
						}
					},{
						xtype : 'gridpanel',
						store : editSelectedMenuStore,
						columnLines : true,
						stripeRows : true,
						height : 300,
						width : 500,
						id : 'editSelectedMenuGrid',
						plugins : [ {
							ptype : 'rowexpander',
							expandOnEnter : true,
							rowBodyTpl : ['{tpl}']
						} ],
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
							//width : 30,
							flex : 1,
							renderer : renderDelete
						} ]
					} ]
				} ]
	} ],

	buttons : [ {
		text : '确定',
	},{
		text : '关闭',
	} ],
	initComponent : function() {
		this.callParent(arguments);
	}
});