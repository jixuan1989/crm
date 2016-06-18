Ext.define('zft.view.system.BaseGridPanel', {
	extend : 'Ext.grid.Panel',
	region : 'center',
	layout : 'border',
	autoDestroy : true,
	plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {
		clicksToEdit : 2
	}) ],
	initComponent : function() {
		this.callParent(arguments);
	}
});