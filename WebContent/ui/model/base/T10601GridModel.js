Ext.define('zft.model.base.T10601GridModel', {
	extend : 'Ext.data.Model',
	alias : 'widget.t10601GridModel',
	fields : [{name: 'employeeNum',mapping: 'employeeNum'},
				{name: 'employeeId',mapping: 'employeeId'},
				{name: 'name',mapping: 'name'},
				{name: 'sex',mapping: 'sex'},
				{name: 'stepment',mapping: 'stepment'},
				{name: 'job',mapping: 'job'},
				{name: 'levels',mapping: 'levels'},
				{name: 'edution',mapping: 'edution'},
				{name: 'email',mapping: 'email'},
				{name: 'entryTime',mapping: 'entryTime'},
				{name: 'telephone',mapping: 'telephone'},
				{name: 'parentId',mapping: 'parentId'}]
});
