
Ext.define('zft.controller.base.T10201Controller', {
	extend : 'Ext.app.Controller',
	alias : 'widget.t10201Controller',
	models : ['base.T10201GridModel'],
	stores : ['base.T10201GridStore'],
	views : [ 'base.T10201.T10201Main',
	          'base.T10201.T10201Add'],
	refs: [
			{ref: 't10201Main', selector: 't10201Main'},
			{ref: 't10201Add', selector: 't10201Add'},
			{ref: 't10201AddForm', selector: 't10201Add > form'},
			{ref: 't10201QueryForm', selector: 't10201Query > form'},
	],
	init : function() {
		var store = this.getStore('base.T10201GridStore');
		var addPanel = null;
		var queryPanel = null;
		this.control({
			't10201Main' : {
				select : function() {
					//Ext.getCmp('delete').setDisabled(0);
					//Ext.getCmp('reload').setDisabled(0);
				},
				deselect : function() {
					//Ext.getCmp('delete').setDisabled(1);
					//Ext.getCmp('reload').setDisabled(1);
				}
			},
			't10201Main button[id=add]' : {
				click : function(){
					addPanel = this.getView('base.T10201.T10201Add').create();
				}
			},
			't10201Main button[action=query]' : {
				click : function(){
					if(queryPanel == null)
						queryPanel = Ext.create('zft.view.base.T10201.T10201Query');
					else 
						queryPanel.show();
				}
			},
			't10201Query button[action=query]' : {
				click : function(){
					var me = this;
					var form = me.getT10201QueryForm().getForm();
					store.on('beforeload', function(){
						Ext.apply(store.proxy.extraParams, {
							cityName:form.findField('name').getValue(),
						});
					});
					
					store.load({
						params: {
							start: 0
						}
					});
				}
			},
			't10201Main button[id=delete]' : {
				click : function(){
					var me = this;
					var grid = me.getT10201Main();
					var records = grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec = records[0];
						showConfirm('确定要删除该条地区信息吗？', grid, function(bt) {
							if (bt == "yes") {
								Ext.Ajax.request({
									url: 'T10201Action.asp?method=delete',
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
										cupCityCode: rec.get('cupCityCode'),
										txnId: '10201',
										subTxnId: '02'
									}
								});
							}
						});
					}
				}
			},
			't10201Main button[id=reload]' : {
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
								cupCityCode : record.get('cupCityCode'),
								mchtCityCode : record.get('mchtCityCode'),
								cityName: record.get('cityName')
						};
						array.push(data);
					}
					Ext.Ajax.request({
						url: 'T10201Action.asp?method=update',
						method : 'post',
						timeout: '10000',
						params: {
							cityCodeList: Ext.encode(array),
							txnId: '10201',
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
			't10201Add button[name=submit]' : {
				click : function(){
					var me=this;
					var form = this.getT10201AddForm().getForm();
					if (form.isValid()) {
						form.submit({
							url: 'T10201Action.asp?method=add',
							waitMsg : '正在提交，请稍后......',
							success : function(form, action) {
								showSuccessMsg(action.result.msg, form);
								store.reload();
								me.getT10201Add().close();
							},
							failure : function(form, action) {
								showErrorMsg(action.result.msg, form);
							},
							params: {
								txnId: '10201',
								subTxnId: '01'
							}
						});
					}
				}
			},
			't10201Add button[name=reset]' : {
				click : function(){
					this.getT10201AddForm().getForm().reset();
				}
			}
		});
	}
});