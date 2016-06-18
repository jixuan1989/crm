Ext.define('zft.model.base.T10500GridModel', {
	extend : 'Ext.data.Model',
	alias : 'widget.t10500GridModel',
	fields : [{name: 'id',mapping: 'id'},
			   {name: 'name',mapping: 'name'},
			   {name: 'stepId',mapping: 'stepId'},
			   {name: 'brhId',mapping: 'brhId'}]
});
