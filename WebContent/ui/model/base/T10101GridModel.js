Ext.define('zft.model.base.T10101GridModel', {
	extend : 'Ext.data.Model',
	alias : 'widget.t10101GridModel',
	fields : [{name: 'brhId',mapping: 'brhId'},
				{name: 'brhLvl',mapping: 'brhLvl'},
				{name: 'brhType',mapping: 'brhType'},
				{name: 'upBrhId',mapping: 'upBrhId'},
				{name: 'brhName',mapping: 'brhName'},
				{name: 'brhAddr',mapping: 'brhAddr'},
				{name: 'brhTelNo',mapping: 'brhTelNo'},
				{name: 'postCode',mapping: 'postCode'},
				{name: 'brhContName',mapping: 'brhContName'},
				{name: 'cupBrhId',mapping: 'cupBrhId'},
				{name: 'resv1',mapping: 'resv1'}]
});
