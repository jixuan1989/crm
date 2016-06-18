/**
 * 角色维护
 */
Ext.define('SelectedMenuModel', {
	extend : 'Ext.data.Model',
	fields : [ 'value', 'name', 'ro', 'op' ]
});
//一级菜单数据集
var menuLvl1Store=new Ext.data.Store({
	fields : [ 'value', 'name' ],
	autoLoad : true
});
// 二级菜单数据集
var menuLvl2Store=new Ext.data.Store({
	fields : [ 'value', 'name' ],
	autoLoad : true
});
// 三级菜单数据集
var menuLvl3Store=new Ext.data.Store({
	fields : [ 'value', 'name' ],
	autoLoad : true
});
// 已选菜单数据集
var selectedMenuStore=new Ext.data.Store({
	fields : [ 'value', 'name' ],
	autoLoad : true
});

//二级菜单数据集
var editMenuLvl2Store=new Ext.data.Store({
	fields : [ 'value', 'name' ],
	autoLoad : true
});
// 三级菜单数据集
var editMenuLvl3Store=new Ext.data.Store({
	fields : [ 'value', 'name' ],
	autoLoad : true
});
// 已选菜单数据集
var editSelectedMenuStore=new Ext.data.Store({
	fields : [ 'value', 'name' ],
	autoLoad : true
});
var renderStore=new Ext.data.Store({
	fields : [ 'value', 'name' ],
	autoLoad : true
});

Ext.define('zft.controller.base.T10301Controller', {
	extend : 'Ext.app.Controller',
	alias : 'widget.t10301Controller',
	models : ['base.T10301GridModel'],
	stores : ['base.T10301GridStore'],
	views : [ 'base.T10301.T10301Main','base.T10301.T10301Add','base.T10301.T10301Edit' ],
	refs: [
			{ref: 't10301Main', selector: 't10301Main'},
			{ref: 't10301Add', selector: 't10301Add'},
			{ref: 't10301Edit', selector: 't10301Edit'},
			{ref: 'addForm', selector: 't10301Add > form'},
			{ref: 'EditForm', selector: 't10301Edit > form'}
	],
	init : function() {
		this.getStore('base.T10301GridStore');
		this.control({
			't10301Main' : {
				select : function() {
					Ext.getCmp('delete').setDisabled(0);
					Ext.getCmp('edit').setDisabled(0);
					Ext.getCmp('reload').setDisabled(0);
				},
				deselect : function() {
					Ext.getCmp('delete').setDisabled(1);
					Ext.getCmp('edit').setDisabled(1);
					Ext.getCmp('reload').setDisabled(1);
				}
			},
			't10301Main button[id=reload]' : {
				click : function() {
					var me=this;
					var grid=me.getT10301Main();
					var store=me.getStore('base.T10301GridStore');
					Ext.getCmp('delete').setDisabled(1);
					Ext.getCmp('reload').setDisabled(1);
					Ext.getCmp('edit').setDisabled(1);
					var records=store.getUpdatedRecords();
					if (records.length == 0) {
						showMsg("没有任何数据被修改过!", this);
						return;
					};
					var array=new Array();
					for ( var index=0; index < records.length; index++) {
						var record=records[index];
						var data={
							roleId : record.get('roleId'),
							roleName : record.get('roleName'),
							roleType: record.get('roleType'),
							description: record.get('description'),
							recCrtTs: record.get('recCrtTs')
						};
						array.push(data);
					}
					Ext.Ajax.request({
						url : 'T10301Action.asp?method=update',
						method : 'post',
						params : {
							roleInfoList : Ext.encode(array),
							txnId : '10301',
							subTxnId : '03'
						},
						success : function(rsp, opt) {
							var rspObj=Ext.decode(rsp.responseText);
							if (rspObj.success) {
								store.sync();
								showSuccessMsg(rspObj.msg, this);
								grid.getSelectionModel().deselectAll();
							} else {
								store.sync();
								showErrorMsg(rspObj.msg, this);
							}
							store.reload();
						}
					});
				}
			},
			't10301Main button[id=add]' : {
				click : function() {
					this.getView('base.T10301.T10301Add').create();
					SelectOptionsDWR.getComboData('MENU_LEVEL_1', function(ret) {
						menuLvl1Store.loadData(eval(ret));
					});
				}
			},
			't10301Main button[id=delete]' : {
				click : function() {
					var me=this;
					var grid=me.getT10301Main();
					var records=grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec=records[0];
						var roleId=rec.get('roleId');
						showConfirm('确定要删除该角色吗？', grid, function(
								bt) {
							if (bt == "yes") {
								Ext.Ajax.request({
									url : 'T10301Action.asp?method=delete',
									success : function(rsp, opt) {
										var rspObj=Ext
												.decode(rsp.responseText);
										if (rspObj.success) {
											showSuccessMsg(rspObj.msg, grid);
											me.getStore('base.T10301GridStore').reload();
											grid.getSelectionModel().deselectAll();
										} else {
											showErrorMsg(rspObj.msg, grid);
											grid.getSelectionModel().deselectAll();
										}
									},
									params : {
										roleId : roleId,
										txnId : '10301',
										subTxnId : '02'
									}
								});
							}
						});
					}
				}
			},
			't10301Main button[id=edit]' : {
				click : function() {
					var me=this;
					var editWin = me.getView('base.T10301.T10301Edit').create();
					editSelectedMenuStore.removeAll();
					SelectOptionsDWR.getComboData('MENU_LEVEL_1', function(ret) {
						menuLvl1Store.loadData(eval(ret));
					});
					var grid=me.getT10301Main();
					var records=grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec=records[0];
						var roleId=rec.get('roleId');
						SelectOptionsDWR.getComboDataWithParameter('ROLE_MENU',roleId,function(ret) {
							renderStore.loadData(eval(ret));
							renderStore.each(function(data){
								var value=data.get('value').split("_");
								MenuDwr.getRoleWithDegree(value[0],roleId,function(ret){
									if('81229' != value[0] && '81234' != value[0]){
										var tpl = ret;
		                               	if(ret!=null){
		    								var selectedRec = Ext.create('SelectedMenuModel', {
		    									name : data.get('name'),
		    									value : value[0],
		    									tpl : tpl
		    								});
		    								editSelectedMenuStore.add(selectedRec);
		                               	}
									}
	                            });
							},me);
						});
						Ext.getCmp('editRoleName').setValue(rec.get('roleName'));
						Ext.getCmp('editRoleType').setValue(rec.get('roleType'));
						Ext.getCmp('editDescription').setValue(rec.get('description'));
					}
					editWin.show();
				}
			},
			't10301Add button[text=确定]' : {
				click : function(){
					var me=this;
					var selectedMenuGrid=Ext.getCmp('selectedMenuGrid');
					selectedMenuStore=selectedMenuGrid.getStore();
					var flag = false;
					var menu = '';
					if(selectedMenuStore.getCount() == 0) {
						showMsg('您没有为该角色选择任何菜单信息',this);
						return;
					} else {
						var menuArray=new Array();
						selectedMenuStore.each(function(rec){
							var boxes=document.getElementsByName(rec.get('value') + "_check");
							var groupIds="";
						    for (var j=0; j < boxes.length; j++)
						    {
						        if (boxes[j].checked)
						        {
						        	groupIds += boxes[j].value;
						        }
						    }
						    if(groupIds == ''){
						    	flag = true;
						    	menu = rec.get('name');
						    }
						    var data={
									//将操作权限拼接到操作代码后
									valueId: rec.get('value') + "_" + groupIds
							};
							menuArray.push(data);
						},me);
						if(flag){
							showMsg('您没有为' + menu + '功能选择任何权限',this);
							return;
						}
						if(hasBlank(Ext.getCmp("roleName").getValue())) {
							showMsg("你输入的角色名称包含空格");
							return;
						}
//						if(hasBlank(rec.findField("description"))) {
//							showMsg("你输入的角色描述包含空格");
//							return;
//						}
						var addForm=me.getAddForm().getForm();
						if (addForm.isValid()) {
							addForm.submit({
								url : 'T10301Action.asp?method=add',
								waitMsg : '正在提交，请稍后......',
								success : function(form, action) {
									showSuccessMsg(action.result.msg, addForm);
									addForm.reset();
									me.getT10301Add().close();
									menuLvl2Store.removeAll();
									menuLvl3Store.removeAll();
									selectedMenuStore.removeAll();
									me.getStore('base.T10301GridStore').reload();
								},
								failure : function(form, action) {
									showErrorMsg(action.result.msg, form);
								},
								params : {
									txnId : '10301',
									subTxnId : '01',
									menuList: Ext.encode(menuArray),
									roleName: Ext.getCmp("roleName").getValue(),
									roleType: Ext.getCmp("roleType").getValue(),
									description: Ext.getCmp("description").getValue()
								}
							});
						}
					}
				}
			},
			't10301Add button[text=关闭]' : {
				click : function(){
					menuLvl2Store.removeAll();
					menuLvl3Store.removeAll();
					selectedMenuStore.removeAll();
					this.getT10301Add().close();
				}
			},
			't10301Add button[text=重置]' : {
				click : function(){
					var me = this;
					var form=me.getAddForm().getForm();
					menuLvl2Store.removeAll();
					menuLvl3Store.removeAll();
					selectedMenuStore.removeAll();
					form.reset();
				}
			},
			't10301Edit button[text=确定]' : {
				click : function(){
					var me=this;
					var selectedMenuGrid=Ext.getCmp('editSelectedMenuGrid');
					selectedMenuStore=selectedMenuGrid.getStore();
					var flag = false;
					if(selectedMenuStore.getCount() == 0) {
						showMsg('您没有为该角色选择任何菜单信息',this);
					} else {
						var menuArray=new Array();
						selectedMenuStore.each(function(rec){
							var boxes=document.getElementsByName(rec.get('value') + "_check");
							var groupIds="";
						    for (var j=0; j < boxes.length; j++)
						    {
						        if (boxes[j].checked)
						        {
						        	groupIds += boxes[j].value;
						        }
						    }
						    if(groupIds == ''){
						    	flag = true;
						    	menu = rec.get('name');
						    }
						    var data={
									//将操作权限拼接到操作代码后
									valueId: rec.get('value').split('_')[0] + "_" + groupIds
							};
							menuArray.push(data);
						},me);
						if(flag){
							showMsg('您没有为' + menu + '功能选择任何权限',this);
							return;
						}
						var editForm=me.getEditForm().getForm();
						if (editForm.isValid()) {
							editForm.submit({
								url : 'T10301Action.asp?method=edit',
								waitMsg : '正在提交，请稍后......',
								success : function(form, action) {
									showSuccessMsg(action.result.msg, editForm);
									editForm.reset();
									me.getT10301Edit().close();
									editMenuLvl2Store.removeAll();
									editMenuLvl3Store.removeAll();
									editSelectedMenuStore.removeAll();
									me.getStore('base.T10301GridStore').reload();
								},
								failure : function(form, action) {
									showErrorMsg(action.result.msg, form);
								},
								params : {
									txnId : '10301',
									subTxnId : '04',
									menuList: Ext.encode(menuArray),
									roleId : Ext.getCmp('t10301Main').getSelectionModel().getSelection()[0].get('roleId')
								}
							});
						}
					}
				}
			},
			't10301Edit button[text=关闭]' : {
				click : function(){
					editMenuLvl2Store.removeAll();
					editMenuLvl3Store.removeAll();
					this.getT10301Edit().close();
				}
			}
		});
	}
});