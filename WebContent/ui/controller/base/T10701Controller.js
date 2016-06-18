var termStore=new Ext.data.Store({
	fields : [ 'value', 'name' ],
	autoLoad : true
});
var cardTypeStore=new Ext.data.Store({
	fields : [ 'value', 'name' ],
	autoLoad : true
});
Ext.define('zft.controller.base.T10701Controller', {
	requires:['Ext.app.*'],
	extend : 'Ext.app.Controller',
	alias : 'widget.t10701Controller',
	models : ['base.T10701GridModel'],
	stores : ['base.T10701GridStore'],
	views : [ 'base.T10701.T10701Main','base.T10701.T10701Query'],
	refs: [
			{ref: 't10701Main', selector: 't10701Main'},
			{ref: 't10701Query', selector: 't10701Query'},
			{ref: 'queryForm', selector: 't10701Query > form'}
	],
	init : function() {
		var queryPanel=null;
		this.getStore('base.T10701GridStore').load();
		this.control({
			't10701Main button[text=查询]' : {
				click : function(){
					if(queryPanel == null)
						queryPanel=this.getView('base.T10701.T10701Query').create();
					else 
						queryPanel.show();
				}
			},
			't10701Query button[text=查询]' : {
				click : function(){
					var me=this;
					var store=this.getStore('base.T10701GridStore');
					var form=me.getQueryForm().getForm();
					store.on('beforeload', function(store, operation, options){
						Ext.apply(store.proxy.extraParams, {
							oprNo: form.findField('oprNo').getValue(),
							startDate : typeof(form.findField('startDate').getValue()) == 'string' ? '' : Ext.util.Format.date(form.findField('startDate').getValue(),'Ymd'),
							endDate : typeof(form.findField('endDate').getValue()) == 'string' ? '' : Ext.util.Format.date(form.findField('endDate').getValue(),'Ymd'),
							brhId: form.findField('brhId').getValue(),
							conTxn: form.findField('conTxn').getValue()
						});
					});
					store.load({
						start : 0
					});
				}
			},
			't10701Query button[text=清除查询条件]' : {
				click : function() {
					this.getQueryForm().getForm().reset();
				}
			}
		});
	},
	initComponent : function() {
		this.callParent(arguments);
	}
});