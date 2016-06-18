 Ext.define('zft.model.base.T10210GridModel', {
     extend: 'Ext.data.Model',
     alias: 'widget.t10210Model',
     fields: [
        {name: 'owner',type: 'string'},	
		{name: 'key',type: 'string'},	
		{name: 'type',type: 'string'},
		{name: 'value',type: 'string'},
		{name: 'descr',type: 'string'},
		{name: 'reserve',type: 'string'}
     ]
 });
 
