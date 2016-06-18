Ext.define('zft.view.system.West', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.west',
	title : '系统菜单',
	width : 200,
	height : 150,
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore')
});