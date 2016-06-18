/**
 * 操作员维护
 */
// 所属部门数据集
var stepStore=new Ext.data.Store({
	fields : [ 'name', 'value' ],
	autoLoad : true
});
// 查询部门下的人员
var stepEmployeeStore=new Ext.data.Store({
	fields : [ 'name', 'value' ],
	autoLoad : true
});	
// 权限数据集
var roleStore=new Ext.data.Store({
	fields : [ 'name', 'value' ],
	autoLoad : true
});
var roleForLook = new Ext.data.Store({
	fields : ['name','value'],
	autoLoad : true
});
//SelectOptionsDWR.getComboData('ROLE_FOR_LOOK',
//		function(ret) {
//			roleForLook.loadData(eval(ret));
//	});
Ext.define('zft.controller.base.T10401Controller', {
	extend : 'Ext.app.Controller',
	alias : 'widget.t10401Controller',
	models : ['base.T10401GridModel'],
	stores : ['base.T10401GridStore'],
	views : [ 'base.T10401.T10401Main','base.T10401.T10401Add','base.T10401.T10401Edit', 'base.T10401.T10401Query'],
	refs: [
			{ref: 't10401Main', selector: 't10401Main'},
			{ref: 't10401Add', selector: 't10401Add'},
			{ref: 't10401Edit', selector: 't10401Edit'},
			{ref: 't10401Query', selector: 't10401Query'},
			{ref: 'queryForm', selector: 't10401Query > form'},
			{ref: 'addForm', selector: 't10401Add > form'},
			{ref: 'EditForm', selector: 't10401Edit > form'}
	],
	init : function() {
		queryPanel=null; 
		this.getStore('base.T10401GridStore');
		this.control({
			't10401Main button[text=查询]' : {
				click : function() {
					if(queryPanel == null)
						queryPanel=this.getView('base.T10401.T10401Query').create();
					else 
						queryPanel.show();
				}
			},
			't10401Main button[text=新增]' : {
				click : function() {
					this.getView('base.T10401.T10401Add').create();
				}
			},
			't10401Query button[text=查询]' : {
				click : function() {
					var me=this;
					var store=me.getStore('base.T10401GridStore');
					var form=me.getQueryForm().getForm();
					store.on('beforeload', function(store, operation, options){
						Ext.apply(store.proxy.extraParams, {
							oprId: form.findField('oprId').getValue(),
							brhId: form.findField('searchBrh').getValue(),
							oprName: form.findField('oprNameID').getValue(),
							oprDegree: form.findField('oprDegree').getValue(),
							oprStep: form.findField('oprStep').getValue()
						});
					});
					store.load({
						params : {
							start: 0
						}
					});
				}
			},
			't10401Add button[text=确定]' : {
				click : function() {
					var me=this;
					var addForm=me.getAddForm().getForm();
					if (addForm.isValid()) {
						addForm.submit({
							url : 'T10401Action.asp?method=add',
							waitMsg : '正在提交，请稍后......',
							success : function(form, action) {
								showSuccessMsg(action.result.msg, addForm);
								addForm.reset();
								me.getT10401Add().close();
								SelectOptionsDWR.getComboData('ROLE_FOR_LOOK',
					    				function(ret) {
					    					roleForLook.loadData(eval(ret));
					    			});
								me.getStore('base.T10401GridStore').reload();
								// 关闭窗口
								me.getT10401Add().close();
							},
							failure : function(form, action) {
								showErrorMsg(action.result.msg, form);
							},
							params : {
								txnId : '10401',
								subTxnId : '01'
							}
						});
					}
					
				}
			},
			't10401Main button[text=删除]' : {
				click : function() {
					var me=this;
					var grid=me.getT10401Main();
					var records=grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec=records[0];
						var oprId=rec.get('oprId');
						showConfirm('确定要删除该操作员吗？操作员编号：' + oprId, grid, function(
								bt) {
							if (bt == "yes") {
								Ext.Ajax.request({
									url : 'T10401Action.asp?method=delete',
									success : function(rsp, opt) {
										var rspObj=Ext
												.decode(rsp.responseText);
										if (rspObj.success) {
											showSuccessMsg(rspObj.msg, grid);
											me.getStore('base.T10401GridStore').reload();
										} else {
											showErrorMsg(rspObj.msg, grid);
										}
									},
									params : {
										oprId : oprId,
										txnId : '10401',
										subTxnId : '02'
									}
								});
							}
						});
					}
				}
			},
			't10401Main button[text=解锁重置]' : {
				click : function() {
					var me=this;
					var grid=me.getT10401Main();
					var records=grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec=records[0];
						var oprId=rec.get('oprId');
						showConfirm('确定要解锁并重置该操作员吗？', grid, function(
								bt) {
							if (bt == "yes") {
								Ext.Ajax.request({
									url : 'T10401Action.asp?method=reset',
									success : function(rsp, opt) {
										var rspObj=Ext
												.decode(rsp.responseText);
										if (rspObj.success) {
											showSuccessMsg(rspObj.msg, grid);
											me.getStore('base.T10401GridStore').reload();
										} else {
											showErrorMsg(rspObj.msg, grid);
										}
									},
									params : {
										oprId : oprId,
										txnId : '10401',
										subTxnId : '05'
									}
								});
							}
						});
					}
				}
			},
			't10401Main button[text=编辑用户]' : {
				click : function(){
					var me=this;
					var grid=me.getT10401Main();
					me.getView('base.T10401.T10401Edit').create();
					var records=grid.getSelectionModel().getSelection();
					var rec=records[0];
					var oprDegree=rec.get('oprDegree').replaceAll(' ','');
					var brhId = rec.get('brhId');
					var arr=oprDegree.split(',');
					SelectOptionsDWR.getComboDataWithParameter(
					'ROLE_BY_BRH', brhId,
					function(ret) {
						roleForLook.removeAll();
						roleForLook.loadData(eval(ret));
					});
					Ext.getCmp('editDegree').setValue(arr); 
					//Ext.getCmp('editBrh').setValue(brhId); 
				}
			},
			't10401Main' : {
				select : function() {
					Ext.getCmp('delete').setDisabled(0);
					Ext.getCmp('edit').setDisabled(0);
					Ext.getCmp('reset').setDisabled(0);
				},
				deselect : function() {
					Ext.getCmp('delete').setDisabled(1);
					Ext.getCmp('edit').setDisabled(1);
					Ext.getCmp('reset').setDisabled(1);
				}
			},
			't10401Edit' : {
				beforerender : function() {
					var me=this;
					var grid=me.getT10401Main();
					var records=grid.getSelectionModel().getSelection();
					var rec=records[0];
					var brhId=rec.get('brhId');
					Ext.getCmp('editBrh').setValue(brhId);
				}
			},
			't10401Edit button[text=确定]' : {
				click : function(){
					var me=this;
					var grid=me.getT10401Main();
					var records=grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec=records[0];
						var oprId=rec.get('oprId');
						Ext.Ajax.request({
							url : 'T10401Action.asp?method=edit',
							success : function(rsp, opt) {
								var rspObj=Ext.decode(rsp.responseText);
									if (rspObj.success) {
										showSuccessMsg(rspObj.msg, grid);
										me.getT10401Edit().close();
										me.getStore('base.T10401GridStore').reload();
									} else {
										showErrorMsg(rspObj.msg, grid);
									}
							},
							params : {
								oprId : oprId,
								brhId : Ext.getCmp('editBrh').getValue(),
								oprDegree : Ext.getCmp('editDegree').getValue(),
								txnId : '10401',
								subTxnId : '04'
							}
						});
						grid.getSelectionModel().deselectAll();
					}
				}
			}
	})
},
	initComponent : function() {
		this.callParent(arguments);
	}
});
