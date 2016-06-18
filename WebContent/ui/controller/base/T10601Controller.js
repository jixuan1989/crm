
Ext.define('zft.controller.base.T10601Controller', {
	extend : 'Ext.app.Controller',
	alias : 'widget.t10601Controller',
	models : ['base.T10601GridModel'],
	stores : ['base.T10601GridStore'],
	views : [ 'base.T10601.T10601Main',
	          'base.T10601.T10601Add',
	          'base.T10601.T10601Query'],
	refs: [
			{ref: 't10601Main', selector: 't10601Main'},
			{ref: 't10601Add', selector: 't10601Add'},
			{ref: 't10601AddForm', selector: 't10601Add > form'},
			{ref: 't10601QueryForm', selector: 't10601Query > form'},
			
	],
	init : function() {
		var store = this.getStore('base.T10601GridStore');
		var addPanel = null;
		var queryPanel = null;
		this.control({
			/*'t10601Main' : {
				select : function() {
					Ext.getCmp('delete').setDisabled(0);
					Ext.getCmp('reload').setDisabled(0);
				},
				deselect : function() {
					Ext.getCmp('delete').setDisabled(1);
					Ext.getCmp('reload').setDisabled(1);
				}
			},*/
			't10601Main button[id=add]' : {
				click : function(){
					addPanel = this.getView('base.T10601.T10601Add').create();
				}
			},
			't10601Main button[id=delete]' : {
				click : function(){
					var me = this;
					var grid = me.getT10601Main();
					var records = grid.getSelectionModel().getSelection();
					if (records.length == 1) {
						var rec = records[0];
						showConfirm('确定要删除条信息吗？', grid, function(bt) {
							if (bt == "yes") {
								Ext.Ajax.request({
									url: 'T10601Action.asp?method=delete',
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
										id : rec.get('employeeNum'),
										txnId: '10601',
										subTxnId: '02'
									},
								});
							}
						});
					}
				}
			},
			't10601Main button[id=reload]' : {
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
								id: record.get('employeeNum'),
								employeeId: record.get('employeeId'),
								name: record.get('name'),
								sex: record.get('sex'),
								stepment:record.get('stepment'),
								job: record.get('job'),
								levels: record.get('levels'),
								edution: record.get('edution'),
								graduate: record.get('graduate'),
								professional: record.get('professional'),
								entryTime: record.get('entryTime'),
								telephone: record.get('telephone'),
								email: record.get('email'),
								parentId:record.get('parentId')
						};
						array.push(data);
					}
					Ext.Ajax.request({
						url: 'T10601Action.asp?method=update',
						method : 'post',
						params: {
							tblCompanyEmployeeList : Ext.encode(array),
							txnId: '10601',
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
			't10601Main button[id=query]' : {
				click : function(){
					if(queryPanel == null)
						queryPanel = Ext.create('zft.view.base.T10601.T10601Query')
					else 
						queryPanel.show();
				}
			},
//================================增加面板事件==================================
			't10601Add button[name=submit]' : {
				click : function(){
					var me=this;
					var form = this.getT10601AddForm().getForm();
					if (form.isValid()) {
						form.submit({
							url: 'T10601Action.asp?method=add',
							waitMsg : '正在提交，请稍后......',
							success : function(form, action) {
								showSuccessMsg(action.result.msg, form);
								form.reset();
								//重新加载机构列表
								store.reload();
								me.getT10601Add().close();
							},
							failure : function(form, action) {
								showErrorMsg(action.result.msg, form);
							},
							params: {
								txnId: '10601',
								subTxnId: '01'
							}
						});
					}
				}
			},
			't10601Add button[name=reset]' : {
				click : function(){
					this.getT10601AddForm().getForm().reset();
				}
			},
//======================查询事件处理===========================================
			't10601Query button[name=query]' : {
				click : function(){
					var me = this;
					var form = me.getT10601QueryForm().getForm();
					store.on('beforeload', function(){
						Ext.apply(store.proxy.extraParams, {
							employeeNum:form.findField('employeeNew').getValue(),
							name:form.findField('employeeName').getValue(),
							stepment:form.findField('stepment').getValue(),
							job: form.findField('job').getValue(),
							levels:form.findField('levels').getValue()
						});
					});
					
					store.load({
						params: {
							start: 0
						}
					});
				}
			},
			't10601Query button[name=reset]' : {
				click : function(){
					this.getT10601QueryForm().getForm().reset();
				}
			},
		});
	}
});