Ext.Loader.setConfig({
	enabled : true,
	disableCaching : false
});

/**
 * 操作员性别
 */
function gender(val) {
	if(val == '0') {
		return '<img src="' + Ext.contextPath + '/ext-4.2/resources/images/male.png" />';
	} else if(val == '1') {
		return '<img src="' + Ext.contextPath + '/ext-4.2/resources/images/female.png" />';
	}
	return val;
}
/**
 * 操作员状态
 */
function oprState(val) {
	if(val == '0') {
		return '<img src="' + Ext.contextPath + '/ext-4.2/resources/images/active_16.png" title="可用"/>';
	} else if(val == '1') {
		return '<img src="' + Ext.contextPath + '/ext-4.2/resources/images/stop_16.png" title="锁定"/>';
	}
	return val;
}
var columns = [
    {
    	text: '操作员编号',
    	dataIndex: 'oprId',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	flex: 1
    },{
    	text: '所属机构号',
    	dataIndex: 'brhId',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	flex: 1
    },{
    	text: '角色',
    	dataIndex: 'oprDegree',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	renderer:function(data){
    		DWREngine.setAsync(false);
    		SelectOptionsDWR.getComboData('ROLE_FOR_LOOK',
    				function(ret) {
    					roleForLook.loadData(eval(ret));
    			});
    		var arr = data.split(',');
    		var index;
    		var record;
    		var name = '';
    		if(null == arr[0]) 
    			return '';
    		for(var i = 0;i<arr.length;i++){
    			index = roleForLook.find("value",arr[i].trim());
        		record = roleForLook.getAt(index);
        		if(null != record){
        			if(i < arr.length - 1){
            			name = name + record.data.name + ',';
            		}else{
            			name = name + record.data.name;
            		}
        		}
    		}
    		if(null != name){
    			return name
    		}else{
    			return '';
    		}
    		DWREngine.setAsync(true);
    	},
    	flex: 1
    },{
    	text: '姓名',
    	dataIndex: 'oprName',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	flex: 1
    },{
    	text: '性别',
    	dataIndex: 'oprGender',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	renderer: gender,
    	flex: 1
    },{
    	text: '注册日期',
    	dataIndex: 'registerDt',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	flex: 1
    },{
    	text: '联系电话',
    	dataIndex: 'oprTel',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	flex: 1
    },{
    	text: '手机',
    	dataIndex: 'oprMobile',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	flex: 1
    },{
    	text: '密码有效期',
    	dataIndex: 'pwdOutDate',
    	sortable: true,
    	hideable: false,
    	align: 'center',
    	flex: 1
    },{
    	text: '状态',
    	dataIndex: 'oprSta',
    	sortable: true,
    	hideable: false,
    	renderer: oprState,
    	align: 'center',
    	flex: 1
    }
];
Ext.define('zft.view.base.T10401.T10401Main', {
	extend : 'zft.view.system.BaseGridPanel',
	alias : 'widget.t10401Main',
	id : 't10401Main',
	store : 'base.T10401GridStore',
	columns :columns,
	width : 400,
	layout : 'fit',
	columnLines: true,
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'base.T10401GridStore',
		dock : 'bottom',
		displayInfo : true
	} ]
});
