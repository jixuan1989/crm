
Ext.define('zft.controller.base.T10101Controller', {
	extend : 'Ext.app.Controller',
	alias : 'widget.t10101Controller',
	models : ['base.T10101GridModel'],
	stores : ['base.T10101GridStore'],
	views : [ 'base.T10101.T10101Main',
	          'base.T10101.T10101Add',
	          'base.T10101.T10101Detail'],
	refs: [
			{ref: 't10101Main', selector: 't10101Main'},
			{ref: 't10101AddForm', selector: 't10101Add > form'},
			{ref: 't10101QueryForm', selector: 't10101Query > form'},
			
	],
	init : function() {
		var store = this.getStore('base.T10101GridStore');
		var addPanel = null;
		var queryPanel = null;
		this.control({
			't10101Main' : {
				select : function() {
					Ext.getCmp('detail').setDisabled(0);
				},
				deselect : function() {
					Ext.getCmp('detail').setDisabled(1);
				}
			},
			't10101Main button[id=add]' : {
				click : function(){
					addPanel = this.getView('base.T10101.T10101Add').create();
				}
			},
			't10101Main button[id=delete]' : {
				click : function(){
					var me = this;
					var grid = me.getT10101Main();
					var records = grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec = records[0];
						var brhId = rec.get('brhId');
						showConfirm('确定要删除条信息吗？', grid, function(bt) {
							if (bt == "yes") {
								Ext.Ajax.request({
									url: 'T10101Action.asp?method=delete',
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
										brhId: brhId,
										txnId: '10101',
										subTxnId: '02'
									}
								});
							}
						});
					}
				}
			},
			't10101Main button[id=reload]' : {
				click : function(){
					var records = store.getUpdatedRecords();// 获取修改的行的数据，无法获取幻影数据
					if (records.length == 0) {
						showMsg("没有任何数据被修改过!", this);
						Ext.getCmp('reload').setDisabled(1);
						return;
					};
					showMask('正在保存机构信息，请稍后......');
					// 存放要修改的机构信息
					var array = new Array();
					for ( var index = 0; index < records.length; index++) {
						var record = records[index];
						var data = {
								id : record.get('brhId'),
								brhLevel: record.get('brhLvl'),
								brhType: record.get('brhType'),
								upBrhId: record.get('upBrhId'),
								brhName: record.get('brhName'),
								brhAddr: record.get('brhAddr'),
								brhTelNo: record.get('brhTelNo'),
								postCd: record.get('postCode'),
								brhContName: record.get('brhContName'),
								cupBrhId: record.get('cupBrhId'),
								resv1:record.get('resv1')
						};
						array.push(data);
					}
					Ext.Ajax.request({
						url: 'T10101Action.asp?method=update',
						method : 'post',
						params: {
							brhDataList : Ext.encode(array),
							txnId: '10101',
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
			't10101Main button[id=query]' : {
				click : function(){
					if(queryPanel == null)
						queryPanel = Ext.create('zft.view.base.T10101.T10101Query')
					else 
						queryPanel.show();
				}
			},
			't10101Main button[id=detail]' : {
				click : function(){
					var me = this;
					var grid = me.getT10101Main();
					var records = grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec = records[0];
						var brhId = rec.get('brhId');
						var detail = this.getView('base.T10101.T10101Detail').create({
							brhId : brhId
						});
					
						detail.down('tabpanel').on('tabchange',
							function(tabPanel,newCard,oldCard,eOpts){
								newCard.down('grid').getStore().load({
									params : {
										start : 0,
										brhId : brhId
									}
								});
							});
					}
				}
			},
//================================增加面板事件==================================
			't10101Add button[name=submit]' : {
				click : function(){
					var me=this;
					var form = this.getT10101AddForm().getForm();
					if (form.isValid()) {
						form.submit({
							url: 'T10101Action.asp?method=add',
							waitMsg : '正在提交，请稍后......',
							success : function(form, action) {
								showSuccessMsg(action.result.msg, form);
								form.reset();
								//重新加载机构列表
								store.reload();
								//重新加载上级机构信息
								SelectOptionsDWR.getComboData('UP_BRH_ID',function(ret){
									me.getT10101Main().getUpBrhStore().loadData(eval(ret));
								});
								addPanel.close();
							},
							failure : function(form, action) {
								showErrorMsg(action.result.msg, form);
								addPanel.close();
							},
							params: {
								txnId: '10101',
								subTxnId: '01'
							}
						});
					}
				}
			},
			't10101Add button[name=reset]' : {
				click : function(){
					this.getT10101AddForm().getForm().reset();
				}
			},
//======================查询事件处理===========================================
			't10101Query button[name=query]' : {
				click : function(){
					var me = this;
					var form = me.getT10101QueryForm().getForm();
					store.on('beforeload', function(){
						Ext.apply(store.proxy.extraParams, {
							brhId: form.findField('brhIdEdit').getValue(),
							brhName: form.findField('searchBrhName').getValue()
						});
					});
					
					store.load({
						params: {
							start: 0
						}
					});
				}
			},
			't10101Query button[name=reset]' : {
				click : function(){
					this.getT10101QueryForm().getForm().reset();
				}
			},
		});
	}
});