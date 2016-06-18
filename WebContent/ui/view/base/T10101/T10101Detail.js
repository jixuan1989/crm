
Ext.define('zft.view.base.T10101.T10101Detail', {
	extend : 'Ext.window.Window',
	requires:['Ext.window.Window'],
	alias : 'widget.t10101Detail',
	config : {
		brhId : ''
	},
	initComponent : function() {
		var me = this;
		/*****************************************操作员信息*****************************************/	
		var oprGridStore = Ext.create('Ext.data.Store',{
			fields : [
			    {name: 'oprId',mapping: 'oprId'},
				{name: 'brhId',mapping: 'brhId'},
				{name: 'oprName',mapping: 'oprName'},
				{name: 'oprGender',mapping: 'oprGender'},
				{name: 'registerDt',mapping: 'registerDt'},
				{name: 'oprTel',mapping: 'oprTel'},
				{name: 'oprMobile',mapping: 'oprMobile'}
			],
			proxy : {
				type : 'ajax',
				url: 'gridPanelStoreAction.asp?storeId=oprInfoWithBrh',
				actionMethods: {   
		            read: 'POST'  //默认为get提交，更改为post解决url中文乱码问题
		        }, 
				reader : {
					type : 'json',
					root : 'data',
					totalProperty : 'totalCount',
					idProperty : 'oprId'
				}
			}
		});
		oprGridStore.on('beforeload', function(){
			Ext.apply(oprGridStore.proxy.extraParams, {
				brhId: me.brhId
			});
		});
		
		oprGridStore.load({
			params : {
				start : 0,
			}
		});
		
		var oprGridPanel = Ext.create('Ext.grid.Panel', {
			store: oprGridStore,
			columns : [{header: '操作员编号',dataIndex: 'oprId',align: 'center',sortable: true, flex: 1 },
			         {header: '所在机构编号',align: 'center',dataIndex: 'brhId' ,fixed : true, flex: 1},
			         {header: '操作员姓名',align: 'center',dataIndex: 'oprName', flex: 1},
			         {header: '操作员性别',align: 'center',dataIndex: 'oprGender', flex: 1},
			         {header: '注册日期',align: 'center',dataIndex: 'registerDt', flex: 1},
			         {header: '操作员联系电话',align: 'center',dataIndex: 'oprTel', flex: 1},
			         {header: '操作员移动电话',align: 'center',dataIndex: 'oprMobile', flex: 1}],
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : oprGridStore,
				dock : 'bottom',
				displayInfo : true
			}]
		});
		
		/******************************************商户信息***********************************************/
		var mchntGridStore = Ext.create('Ext.data.Store',{
			fields : [
			    {name: 'mchtNo',mapping: 'mchtNo'},
				{name: 'mcc',mapping: 'mcc'},
				{name: 'applyDate',mapping: 'applyDate'},
				{name: 'mchtStatus',mapping: 'mchtStatus'},
				{name: 'mchtNm',mapping: 'mchtNm'},
				{name: 'mchtCnAbbr',mapping: 'mchtCnAbbr'},
				{name: 'licenceNo',mapping: 'licenceNo'},
				{name: 'addr',mapping: 'addr'},
				{name: 'contact',mapping: 'contact'},
				{name: 'commTel',mapping: 'commTel'},
				{name: 'manager',mapping: 'manager'}
			],
			proxy : {
				type : 'ajax',
				url: 'gridPanelStoreAction.asp?storeId=mchntInfoQuery',
				actionMethods: {   
		            read: 'POST'  //默认为get提交，更改为post解决url中文乱码问题
		        }, 
				reader : {
					type : 'json',
					root : 'data',
					totalProperty : 'totalCount',
					idProperty : 'mchntId'
				}
			}
		});
		mchntGridStore.on('beforeload', function(){
			Ext.apply(mchntGridStore.proxy.extraParams, {
				brhId: me.brhId
			});
		});
		
		mchntGridStore.load({
			params: {
				start: 0
			}
		});
		
		// 商户状态转译
		function mchntSt(val) {
			if(val == '0') {
				return '<font color="green">可用</font>';
			} else if(val == '1') {
				return '<font color="red">不可用</font>';
			}
			return val;
		};
		
		var mchntGridPanel = Ext.create('Ext.grid.Panel', {
			store: mchntGridStore,
			columns : [{header: '商户编号',dataIndex: 'mchtNo',align: 'center',sortable: true},
			         {header: '商户MCC',align: 'center',dataIndex: 'mcc'},
			         {header: '商户注册日期',align: 'center',dataIndex: 'applyDate'},
			         {header: '商户状态',align: 'center',dataIndex: 'mchtStatus', renderer: mchntSt},
			         {header: '商户中文名称',align: 'center',dataIndex: 'mchtNm'},
			         {header: '商户对外名称',align: 'center',dataIndex: 'mchtCnAbbr'},
			         {header: '营业执照号码',align: 'center',dataIndex: 'licenceNo'},
			         {header: '商户地址',align: 'center',dataIndex: 'addr'},
			         {header: '法人姓名',align: 'center',dataIndex: 'manager'},
			         {header: '联系人姓名',align: 'center',dataIndex: 'contact'},
			         {header: '联系电话',align: 'center',dataIndex: 'commTel'}],
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : mchntGridStore,
				dock : 'bottom',
				displayInfo : true
			}]
		});
		/*******************************************终端信息**********************************************/
		var termStore = Ext.create('Ext.data.Store',{
			fields : [
			    {name: 'serialNo',mapping: 'serialNo'},
			    {name: 'termId',mapping: 'termId'},
				{name: 'mchtSerialNo',mapping: 'mchtSerialNo'},
				{name: 'mchtCd',mapping: 'mchtCd'},
				{name: 'mchtNm',mapping: 'mchtNm'},
				{name: 'state',mapping: 'state'},
				{name: 'termSta',mapping: 'termSta'},
				{name: 'signStep',mapping: 'signStep'},
				{name: 'signEmp',mapping: 'signEmp'},
				{name: 'shopNm',mapping: 'shopNm'},
				{name: 'shopAddr',mapping: 'shopAddr'},
				{name: 'shopTel',mapping: 'shopTel'},
				{name: 'contactTel',mapping: 'contactTel'}
			],
			autoLoad : false,
			proxy : {
				type : 'ajax',
				url: 'gridPanelStoreAction.asp?storeId=termInfoAll',
				actionMethods: {   
		            read: 'POST'  //默认为get提交，更改为post解决url中文乱码问题
		        }, 
				reader : {
					type : 'json',
					root : 'data',
					totalProperty : 'totalCount'
				}
			}
		});
		termStore.on('beforeload', function(){
			Ext.apply(termStore.proxy.extraParams, {
				brhId: me.brhId
			});
		});
		
		termStore.load({
			params: {
				start: 0
			}
		});
		
		var termTypeMap = new DataMap('TERM_TYPE');
		function termType(val) {
			return termTypeMap[val];
		};
		// 终端状态
		function termSt(val) {
			if(val == '0') {
				return '<font color="green">正常</font>';
			} else if(val == '1') {
				return '<font color="gray">添加待审核</font>';
			} else if(val == '2') {
				return '<font color="gray">添加审核退回</font>';
			} else if(val == '3') {
				return '<font color="gray">修改待审核</font>';
			} else if(val == '4') {
				return '<font color="gray">修改审核退回</font>';
			} else if(val == '5') {
				return '<font color="gray">停用待审核</font>';
			} else if(val == '6') {
				return '<font color="red">停用</font>';
			} else if(val == '7') {
				return '<font color="gray">恢复待审核</font>';
			}
			return val;
		}
		function isSuppIc(val) {
			if(val == '1') 
				return '是';
			else if(val == '0')
				return '否';
			return val;
		};
		function isSuppCredit(val) {
			if(val == '1') 
				return '是';
			else if(val == '0')
				return '否';
			return val;
		};
		function callType(val) {
			if(val == '00') 
				return 'GPRS拨入';
			else if(val == '01')
				return 'CDMA拨入';
			else if(val == '02') 
				return '其他拨入';
			
			return val;
		};
		function encType(val) {
			if(val == '1') 
				return '软件加密';
			else if(val == '0')
				return '加密机加密';
			return val;
		}
		var termGridPanel = Ext.create('Ext.grid.Panel', {
			store: termStore,
			columns : [
			           {id: 'serialNo',text: '终端内部编号',dataIndex: 'serialNo',align: 'center',hidden:true},
			    		{text: '终端编号',dataIndex: 'termId',flex: 0.8,align: 'center'},
			    		{text: '商户编号',dataIndex: 'mchtCd',flex: 1,align: 'center'},
			    		//{text: '商户内部编号',dataIndex: 'mchtNo',width: 150,algin: 'center'},
			    		{text: '商户名称',dataIndex: 'mchtNm',flex: 1.5,align: 'center'},
			    		{text: '终端状态',dataIndex: 'state',flex: 1,align: 'center',renderer:termStatus},
			    		{text: '签约部门',dataIndex: 'signStep',flex: 1,align: 'center'},
			    		{text: '签约人员',dataIndex: 'signEmp',flex: .5,align: 'center'},
			      		{text: '联系电话',dataIndex: 'contactTel',flex: .5,align: 'center'}
			],
			
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : termStore,
				dock : 'bottom',
				displayInfo : true
			}]
		});
		
		var tabs = Ext.create('Ext.tab.Panel', {
		    activeTab: 0,
		    bodyPadding: 0,
		    width: '100%',
		    height : '100%',
		    layout: 'fit',
		    defaults : {
		    	padding : '20 20 20 20',
		    	width: '100%',
			    height : '100%',
		    	xtype :'form',
		    	layout: 'column',
		    },
		    items: [{
		    	title: '操作员信息',
		    	id : 'tab1',
		    	layout : 'fit',
		    	padding : '0 0 0 0',
		    	margin : '0 0 0 0',
		    	items : [oprGridPanel]
		    },{
		    	title: '商户信息',
		    	id : 'tab2',
		    	layout : 'fit',
		    	padding : '0 0 0 0',
		    	margin : '0 0 0 0',
		    	items : [mchntGridPanel]
		    },{
		    	title: '终端信息',
		    	id : 'tab3',
		    	layout : 'fit',
		    	padding : '0 0 0 0',
		    	margin : '0 0 0 0',
		    	items : [termGridPanel]
		    }]
		});
		
		Ext.apply(me,{
			title : '机构关联信息',
			autoShow : true,
			iconCls: 'term',
			width:'65%',
			authWidth : true,
			autoHeight : true,
			modal : true,
			layout : 'fit',
			height:'80%',
			frame : true,
			items: [tabs]
		});
		this.callParent(arguments);
	}
});