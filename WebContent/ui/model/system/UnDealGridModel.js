 Ext.define('zft.model.system.UnDealGridModel', {
     extend: 'Ext.data.Model',
	 alias : 'widget.unDealGridModel',
     fields: [
        {name: 'unDealName',type: 'string'},
        {name: 'unDealCreater',type: 'string'},
		{name: 'unDealTime',type: 'string'},
		{name: 'unDealState',type: 'string'},
		{name: 'unDealUrl',type: 'string'},
		{name: 'dealOper',type: 'string'},
		{name: 'dealDegree',type: 'string'}
     ]
 });
