
Ext.define('zft.controller.base.T10801Controller', {
	extend : 'Ext.app.Controller',
	alias : 'widget.t10801Controller',
	views : [ 'base.T10801.T10801Main'],
	refs: [
			{ref: 't10801Main', selector: 't10801Main'},
	],
	init : function() {
		this.control({
		});
	}
});