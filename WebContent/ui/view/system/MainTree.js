var baseTree = Ext.create('Ext.tree.Panel', {
	title : '基本信息',
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore'),
	listeners : {
		beforerender : function(){
			var me = this;
			var store = me.getStore();
			store.on('beforeload', function(){
				Ext.apply(store.proxy.extraParams, {
					param : 'init'
				});
			});
			store.load();
		},
		itemclick : treeClick
	}
});
var mchtTree = Ext.create('Ext.tree.Panel', {
	title : '商户管理',
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore'),
	listeners : {
		beforerender  : function(){
			var me = this;
			var store = me.getStore();
			store.on('beforeload', function(){
				Ext.apply(store.proxy.extraParams, {
					param : '2'
				});
			});
			store.load();
		},
		itemclick : treeClick
	}
});
var termTree = Ext.create('Ext.tree.Panel', {
	title : '终端管理',
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore'),
	listeners : {
		beforerender  : function(){
			var me = this;
			var store = me.getStore();
			store.on('beforeload', function(){
				Ext.apply(store.proxy.extraParams, {
					param : '3'
				});
			});
			store.load();
		},
		itemclick : treeClick
	}
});
var onLinePayTree = Ext.create('Ext.tree.Panel', {
	title : '代付',
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore'),
	listeners : {
		beforerender  : function(){
			var me = this;
			var store = me.getStore();
			store.on('beforeload', function(){
				Ext.apply(store.proxy.extraParams, {
					param : '9'
				});
			});
			store.load();
		},
		itemclick : treeClick
	}
});
var queryTree = Ext.create('Ext.tree.Panel', {
	title : '查询统计',
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore'),
	listeners : {
		beforerender  : function(){
			var me = this;
			var store = me.getStore();
			store.on('beforeload', function(){
				Ext.apply(store.proxy.extraParams, {
					param : '5'
				});
			});
			store.load();
		},
		itemclick : treeClick
	}
});
var taskTree = Ext.create('Ext.tree.Panel', {
	title : '任务管理',
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore'),
	listeners : {
		beforerender  : function(){
			var me = this;
			var store = me.getStore();
			store.on('beforeload', function(){
				Ext.apply(store.proxy.extraParams, {
					param : '10'
				});
			});
			store.load();
			if(store.getCount() == 0){
				me.hide();
			}
		},
		itemclick : treeClick
	}
});
var riskTree = Ext.create('Ext.tree.Panel', {
	title : '风险控制',
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore'),
	listeners : {
		beforerender  : function(){
			var me = this;
			var store = me.getStore();
			store.on('beforeload', function(){
				Ext.apply(store.proxy.extraParams, {
					param : '4'
				});
			});
			store.load();
			if(store.getCount() == 0){
				me.hide();
			}
		},
		itemclick : treeClick
	}
});
var clearTree = Ext.create('Ext.tree.Panel', {
	title : '清算对账',
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore'),
	listeners : {
		beforerender  : function(){
			var me = this;
			var store = me.getStore();
			store.on('beforeload', function(){
				Ext.apply(store.proxy.extraParams, {
					param : '8'
				});
			});
			store.load();
			if(store.getCount() == 0){
				me.hide();
			}
		},
		itemclick : treeClick
	}
});
var provisionsTree = Ext.create('Ext.tree.Panel', {
	title : '备付金管理',
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore'),
	listeners : {
		beforerender  : function(){
			var me = this;
			var store = me.getStore();
			store.on('beforeload', function(){
				Ext.apply(store.proxy.extraParams, {
					param : '11'
				});
			});
			store.load();
			if(store.getCount() == 0){
				me.hide();
			}
		},
		itemclick : treeClick
	}
});
var errorTree = Ext.create('Ext.tree.Panel', {
	title : '差错处理',
	expanded : true,
	useArrows:true,
	collapsible: false,
	rootVisible : false,
	displayField : 'text',
	animate : true, 
	store : Ext.create('zft.store.system.MenuTreeStore'),
	listeners : {
		beforerender  : function(){
			var me = this;
			var store = me.getStore();
			store.on('beforeload', function(){
				Ext.apply(store.proxy.extraParams, {
					param : '12'
				});
			});
			store.load();
			if(store.getCount() == 0){
				me.hide();
			}
		},
		itemclick : treeClick
	}
});
/*var systemPanel = Ext.create('Ext.panel.Panel',{
	title : '系统菜单',
	bodyCls : 'sysimage',
	defaults : {
		width : '30%'
	},
	buttons : [{
	   	    	text: '修改密码',
	   	    	iconCls: 'key',
	   	    	width : '30%',
	   	    	handler: function() {
	   	    		resetPwdWin.show();
	   	    		resetPwdForm.getForm().reset();
	   	    		resetPwdForm.getComponent('resetOprId').setValue(operator[OPR_ID]);
	   	    	}
	   	    },{
	   	    	text: '锁屏',
	   	    	iconCls: 'lock',
	   	    	width : '25%',
	   	    	handler: function() {
	   	    		lockWin.show();
	   	    		lockForm.getForm().reset();
	   	    		lockForm.getComponent('lockOprId').setValue(operator[OPR_ID]);
	   	    	}
	   	    },{
	   	    	text: '安全退出',
	   	    	iconCls: 'quit',
	   	    	width : '30%',
	   	    	handler: function() {
	   	    		showConfirm('确定要退出吗？',this,function(bt) {
	   	    			if(bt == 'yes') {
	   	    				window.location.href = 'logout.asp';
	   	    			}
	   	    		});
	   	    	}
	   	    }
	   	]
});*/
function treeClick(tree, record, item, index, e, options){
	
	if(record.data.id.length != 3){
		var con = application.getController('zft.controller.system.MainController');
		var center = con.getCenter();
		center.setTitle(record.data.text);
		var arr = record.data.url.split('.');
		var tbar = Ext.decode(record.data.roleFlag,false);
		var conStr = 'zft.controller.' + record.data.url + 'Controller';
		//alert(conStr);
		var keys = application.controllers.keys + ':';
		Ext.require(conStr,function(){
			if(-1 == keys.indexOf(conStr)){
				controller = application.getController(conStr);
			}
			center.removeAll(true);
			var mainStr = 'zft.view.' + record.data.url + '.' + arr[1] + 'Main';
			var panel = Ext.create(mainStr,{
				tbar : tbar
			});
			panel.getStore().load();
			center.add(panel);
			center.setActive(true, panel);
        },self);
	}
}
Ext.define('zft.view.system.MainTree', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.mainTree',
	layout : "accordion",
	title : '系统菜单',
	width : 500,
	collapsible : false,
	defaults: {
		bodyStyle: 'headerbackground:#ffc;'
    },
    listeners : {
    	beforerender : function(this_,eOpts){
    		var me = this;
    		if(treeMenuPanel.indexOf('baseTree') != -1){
    			me.add(baseTree);
    		}
    		if(treeMenuPanel.indexOf('mchtTree') != -1){
    			me.add(mchtTree);
    		}
    		if(treeMenuPanel.indexOf('termTree') != -1){
    			me.add(termTree);
    		}
    		if(treeMenuPanel.indexOf('onLinePayTree') != -1){
    			me.add(onLinePayTree);
    		}
    		if(treeMenuPanel.indexOf('queryTree') != -1){
    			me.add(queryTree);
    		}
    		if(treeMenuPanel.indexOf('taskTree') != -1){
    			me.add(taskTree);
    		}
    		if(treeMenuPanel.indexOf('clearTree') != -1){
    			me.add(clearTree);
    		}
    		if(treeMenuPanel.indexOf('riskTree') != -1){
    			me.add(riskTree);
    		}
    		if(treeMenuPanel.indexOf('provisionsTree') != -1){
    			me.add(provisionsTree);
    		}
    		if(treeMenuPanel.indexOf('errorTree') != -1){
    			me.add(errorTree);
    		}
    	}
    }
	//items : [ baseTree,mchtTree,termTree,hourseTree,queryTree,taskTree ]
});