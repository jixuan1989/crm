
Ext.define('zft.controller.base.T10500Controller', {
	extend : 'Ext.app.Controller',
	alias : 'widget.t10500Controller',
	models : ['base.T10500GridModel'],
	stores : ['base.T10500GridStore'],
	views : [ 'base.T10500.T10500Main',
	          'base.T10500.T10500Add'],
	refs: [
			{ref: 't10500Main', selector: 't10500Main'},
			{ref: 't10500Add', selector: 't10500Add'},
			{ref: 't10500AddForm', selector: 't10500Add > form'},
			{ref: 't10500QueryForm', selector: 't10500Query > form'}
	],
	init : function() {
		var store = this.getStore('base.T10500GridStore');
		var addPanel = null;
		var queryPanel = null;
		this.control({
			't10500Main' : {
				select : function() {
					//Ext.getCmp('delete').setDisabled(0);
					//Ext.getCmp('reload').setDisabled(0);
				},
				deselect : function() {
					//Ext.getCmp('delete').setDisabled(1);
					//Ext.getCmp('reload').setDisabled(1);
				}
			},
			't10500Main button[id=add]' : {
				click : function(){
					addPanel = this.getView('base.T10500.T10500Add').create();
				}
			},
			't10500Main button[action=query]' : {
				click : function(){
					if(queryPanel == null)
						queryPanel = Ext.create('zft.view.base.T10500.T10500Query');
					else 
						queryPanel.show();
				}
			},
			't10500Query button[action=query]' : {
				click : function(){
					var me = this;
					var form = me.getT10500QueryForm().getForm();
					store.on('beforeload', function(){
						Ext.apply(store.proxy.extraParams, {
							depName:form.findField('depName').getValue(),
						});
					});
					
					store.load({
						params: {
							start: 0
						}
					});
				}
			},
			't10500Main button[id=delete]' : {
				click : function(){
					var me = this;
					var grid = me.getT10500Main();
					var records = grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec = records[0];
						showConfirm('确定要删除该部门信息吗？', grid, function(bt) {
							if (bt == "yes") {
								Ext.Ajax.request({
									url: 'T10500Action.asp?method=delete',
									success : function(rsp, opt) {
										var rspObj = Ext.decode(rsp.responseText);
										if (rspObj.success) {
											showSuccessMsg(rspObj.msg, grid);
											store.reload();
										} else {
											showErrorMsg(rspObj.msg, grid);
										}
									},
									params: {
										id : rec.get('id'),
										txnId: '10500',
										subTxnId: '02'
									}
								});
							}
						});
					}
				}
			},
			't10500Main button[id=reload]' : {
				click : function(){
					var records = store.getUpdatedRecords();// 获取修改的行的数据，无法获取幻影数据
					if (records.length == 0) {
						showMsg("没有任何数据被修改过!", this);
						Ext.getCmp('reload').setDisabled(1);
						return;
					};
					showMask('正在保存地区代码信息，请稍后......');
					// 存放要修改的机构信息
					var array = new Array();
					for ( var index = 0; index < records.length; index++) {
						var record = records[index];
						var data = {
								id: record.get('id'),
								name: record.get('name'),
								brhId:record.get('brhId')
						};
						array.push(data);
					}
					Ext.Ajax.request({
						url: 'T10500Action.asp?method=update',
						method : 'post',
						params: {
							tblCompanyStepmentList : Ext.encode(array),
							txnId: '10500',
							subTxnId: '03'
						},
						success : function(rsp, opt) {
							var rspObj = Ext.decode(rsp.responseText);
							store.sync();
							if (rspObj.success) {
								showSuccessMsg(rspObj.msg, this);
							} else {
								showErrorMsg(rspObj.msg, this);
							}
							Ext.getCmp('reload').setDisabled(1);
							store.reload();
							hideMask();
						}
					});
				}
			},
//================================增加面板事件==================================
			't10500Add button[name=submit]' : {
				click : function(){
					var me=this;
					var form = this.getT10500AddForm().getForm();
					if (form.isValid()) {
						form.submit({
							url: 'T10500Action.asp?method=add',
							waitMsg : '正在提交，请稍后......',
							success : function(form, action) {
								showSuccessMsg(action.result.msg, form);
								form.reset();
								store.reload();
								// 添加成功后关闭窗口
								me.getT10500Add().close();
							},
							failure : function(form, action) {
								showErrorMsg(action.result.msg, form);
							},
							params: {
								txnId: '10500',
								subTxnId: '01'
							}
						});
					}
				}
			},
			't10500Add button[name=reset]' : {
				click : function(){
					this.getT10500AddForm().getForm().reset();
				}
			}
		});
	}
});