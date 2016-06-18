Ext.define('zft.view.system.UnDealPanel', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.unDealPanel',
	initComponent : function() {
		var me = this;
		var unDealGridStore = Ext.create('zft.store.system.UnDealGridStore');
		var columns = [
		       	    {xtype: 'rownumberer',text : '序号',width : '30',align :'center'},
		    	    {header: '待办事项名称',dataIndex: 'unDealName',sortable: true ,align :'center',width : '230',
		    	    	renderer:function(val) {
			    	    		return '<a href="">' + val + '</a>';
			    	    }
		       	    },
		    	    {header: '待办事项发起人',dataIndex: 'unDealCreater',sortable: true ,align :'center',width : '100'},
		    	    {header: '待办事项发起时间',dataIndex: 'unDealTime',sortable: true ,align :'center',width : '150'},
		    	    {header: '待办事项状态',dataIndex: 'unDealState',sortable: true ,align :'center',width : '100',
		    	    	renderer:function(val) {
		    	    		switch(val) {
		    	    			case '0' : return '<font color="green">待办</font>';
		    	    			case '1' : return '<font color="red">已处理</font>';
		    	    		}
		    	    	}
		    	    }
		    	];
		Ext.apply(me,{
			store : unDealGridStore,
			columns : columns,
			autoScroll : true,
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : unDealGridStore,
				dock : 'bottom',
				displayInfo : true
			}]
		});
		me.callParent(arguments);
	}
})