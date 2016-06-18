var t10210Add=null;
Ext.define('zft.controller.base.T10210Controller', {
	extend : 'Ext.app.Controller',
	alias : 'widget.t10210Controller',
	models : ['base.T10210GridModel'],
	stores : ['base.T10210GridStore'],
	views : [ 'base.T10210.T10210Main' ],
	refs: [
			{ref: 't10210Main', selector: 't10210Main'},
			{ref:"t10210Add",selector:"t10210Add"},
			{ref: 't10210AddForm', selector: 't10210Add > form'},
			{ref: 't10210QueryForm', selector: 't10210Query > form'},
	],
	init : function() {
		var store=this.getStore('base.T10210GridStore');
		var queryPanel = null;
		
		this.control({
			't10210Main' : {
				select : function() {
					/*Ext.getCmp('delete').setDisabled(0);
					var records=this.getT10210Main().getSelectionModel().getSelection();
					if(records[0].get('type') == '00'){
						Ext.getCmp('reload').setDisabled(0);
					}*/
				},
				deselect : function() {
					/*Ext.getCmp('delete').setDisabled(1);
					Ext.getCmp('reload').setDisabled(1);*/
				}
			},
			't10210Main button[action=query]' : {
				click : function(){
					if(queryPanel == null)
						queryPanel = Ext.create('zft.view.base.T10210.T10210Query');
					else 
						queryPanel.show();
				}
			},
			't10210Query button[action=query]' : {
				click : function(){
					var me = this;
					var form = me.getT10210QueryForm().getForm();
					store.on('beforeload', function(){
						Ext.apply(store.proxy.extraParams, {
							owner:form.findField('owner').getValue(),
							value:form.findField('value').getValue(),
						});
					});
					
					store.load({
						params: {
							start: 0
						}
					});
				}
			},
			't10210Main button[id=delete]' : {
				click : function() {
					var grid=this.getT10210Main();
					var records=grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec=records[0];
						showConfirm('确定要删除该条记录吗？', grid, function(bt) {
							if (bt == "yes") {
								Ext.Ajax.request({
									url: 'T10202Action.asp?method=delete',
									success : function(rsp, opt) {
										var rspObj=Ext.decode(rsp.responseText);
										if (rspObj.success) {
											showSuccessMsg(rspObj.msg, grid);
											store.reload();
										} else {
											showErrorMsg(rspObj.msg, grid);
										}
									},
									params: { 
										owner: rec.get('owner'),
										key: rec.get('key'),
										txnId: '10202',
										subTxnId: '02'
									}
								});
							}
						});
					}
				}
			},
			't10210Main button[id=reload]' : {
				click : function() {
					var records=store.getUpdatedRecords();// 获取修改的行的数据，无法获取幻影数据
					if (records.length == 0) {
						showMsg("没有任何数据被修改过!", this);
						Ext.getCmp('reload').setDisabled(1);
						return;
					};
					showMask('正在保存卡表信息，请稍后......');
					// 存放要修改的机构信息
					var array=new Array();
					for ( var index=0; index < records.length; index++) {
						var record=records[index];
						if(record.get('type') == '00'){
							var data={
									owner : record.get('owner'),
									key: record.get('key'),
									type: '00',                 //能维护的参数信息默认类型为00
									value: record.get('value'),
									descr: record.get('descr'),
									reserve: record.get('reserve')
							};
							array.push(data);
						}
					}
					Ext.Ajax.request({
						url: 'T10202Action.asp?method=update',
						method: 'post',
						params: {
							parameterList : Ext.encode(array),
							txnId: '10202',
							subTxnId: '03'
						},
						success : function(rsp, opt) {
							var rspObj=Ext.decode(rsp.responseText);
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
			't10210Main button[id=add]' : {
				click : function() {
					t10210Add=Ext.create('zft.view.base.T10210.T10210Add');
				}
			},
			't10210Add button[id=oprAdd]' : {
				click : function() {
					var me = this;
					var addWin = me.getT10210Add();
					var form = me.getT10210AddForm().getForm();
					if (form.isValid()) {
						form.submit({
							url: 'T10202Action.asp?method=add',
							waitMsg : '正在提交，请稍后......',
							success : function(form, action) {
								showSuccessMsg(action.result.msg, form);
								form.reset();
								store.reload();
								addWin.close();
							},
							failure : function(form, action) {
								showErrorMsg(action.result.msg, form);
							},
							params: {
								txnId: '10202',
								subTxnId: '01'
							}
						});
					}
				}
			},
			't10210Add button[id=reset]' : {
				click : function() {
					this.getT10210AddForm().getForm().reset();
				}
			}
		});
	},
	initComponent : function() {
		this.callParent(arguments);
	}
});
