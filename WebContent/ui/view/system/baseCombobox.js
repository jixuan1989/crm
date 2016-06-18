Ext.define('zft.view.system.baseCombobox', {
	extend : 'Ext.form.ComboBox',
	alias : 'widget.basecomboselect',
	config : {
		baseParams : ''
	},
	
	initComponent : function() {
		me = this;
		var store = Ext.create('Ext.data.Store', {
			fields : [ {
				name : 'valueField'
			}, {
				name : 'displayField'
			}],
			proxy : {
				type : 'ajax',
				url : 'baseSelect.asp',
				actionMethods : {
					read : 'POST'
				},
				extraParams : {
					ID : me.baseParams
				},
				reader : {
					type : 'json',
					root : 'data'
				}
			},
			autoLoad : true
		});
		
		Ext.apply(me,{
			store: store,
		    queryMode: 'local',
		    typeAhead: true,
		    minChars:'local',
		    triggerAction : 'all',
		    autoSelect : true,
		    editable: true,
		    displayField: 'displayField',
		    valueField: 'valueField'
		});
		
		me.callParent(arguments);
	}
});