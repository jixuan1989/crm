Ext.define('zft.model.base.T10301GridModel', {
	extend : 'Ext.data.Model',
	alias : 'widget.t10301GridModel',
	fields : [ {
		name : 'roleId',
		type: 'string'
	}, {
		name : 'roleName',
		type: 'string'
	}, {
		name : 'roleType',
		type: 'string'
	}, {
		name : 'description',
		type: 'string'
	}, {
		name : 'recUpdOpr',
		type: 'string'
	}, {
		name : 'recCrtTs',
		type: 'string'
	}, {
		name : 'recUpdTs',
		type: 'string'
	} ]
});
