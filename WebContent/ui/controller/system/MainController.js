Ext.Loader.setConfig({
	enabled:true,
	paths:{zft:"ui"}
});
Ext.define('zft.controller.system.MainController', {
	extend : 'Ext.app.Controller',
	alias : 'widget.mainController',
	views : [ 'system.Center', 'system.MainTree' ],
	refs : [ {
		ref : 'center',
		selector : 'center'
	},{
		ref : 'mainTree',
		selector : 'mainTree'
	}],
	init: function() {
		this.control({
		});
	},
	initComponent : function() {
		this.callParent(arguments);
	}
});