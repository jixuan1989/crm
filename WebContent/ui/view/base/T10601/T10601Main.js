Ext.Loader.setConfig({
	enabled : true,
	disableCaching : false
});

Ext.define('zft.view.base.T10601.T10601Main', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.t10601Main',
	layout : 'fit',
	autoDestroy : true,
	initComponent : function() {
		var me = this;
		DWREngine.setAsync(false);
		// 职位	
		var PositionStore = new Ext.data.JsonStore({
			fields: ['value','name']
		});
		SelectOptionsDWR.getComboData('POSITIONINFO',function(ret){
			PositionStore.loadData(eval(ret));
		});
		
		// 部门
		var stepmentStore = new Ext.data.Store({
			fields: ['value','name']
		});
		SelectOptionsDWR.getComboData('STEPMENT',function(ret){
			stepmentStore.loadData(eval(ret));
		});
		
		//人员信息
		var allEmpStore = new Ext.data.JsonStore({
			fields: ['value','name']
		});
		SelectOptionsDWR.getComboData('ALL_EMPLOYEE',function(ret){
			allEmpStore.loadData(eval(ret));
		});
		DWREngine.setAsync(true);
		var columns = [{xtype: 'rownumberer',header: '序号',align :'center',width : 50},
		        {header: '员工号',dataIndex: 'employeeId',width: 80,align: 'center', field: {
			       		xtype: 'textfield',
		        		maxLength: 15,
		        		allowBlank: false,
		        		regex:/^[a-zA-Z0-9]{5}$/,
		   				regexText:'工号必须是5位数字和字母组成，请重新输入',
		        		vtype: 'isOverMax'
		        }},
		   		{header: '姓名',dataIndex: 'name',width: 100,align: 'center', field: {
	       				xtype: 'textfield',
		        		maxLength: 15,
		        		allowBlank: false,
		        		vtype: 'isOverMax'
		        }},
		        {header: '上级',dataIndex: 'parentId',width: 100,align: 'center', 
		        	editor: {
		        		xtype : 'combo',
				   		displayField: 'name',
			   			valueField: 'value',
		   			    store: allEmpStore,
		   			    queryMode: 'local',
		   				editable: true,
		   				name: 'parentId',
		   				width: 85
		        	},
		        	renderer:function(data){
		        		if(null == data) return '';
		   			    var record = allEmpStore.getAt(allEmpStore.find('value',data));
		   			    if(null != record){
		   			    	return record.data.name;
		   			    }else{
		   			    	return data;
		   			    }
		        	}},
		   		{header: '人员部门',dataIndex: 'stepment',width: 150,align: 'center',
		   	   		editor: {
			   	   		xtype : 'combo',
				   	   	displayField: 'name',
			   			valueField: 'value',
		   		        store: stepmentStore,
		   		        queryMode: 'local',
		   				name: 'stepment',
		   				width: 85
		   			},
		   		    renderer:function(val){
		   		    	var name=""
		   		    	stepmentStore.each(function(data){
							var key=data.get('value');
							var value=data.get('name');
							if(val == key){
								name = value;
							}
						},this);
		   		    	return name;
		   		    }
		   		},
		   		{header: '性别',dataIndex: 'sex',width: 80,align: 'center',renderer: function(val){
		   			if(val == '0')
		   				return '男';
		   			else if(val == '1')
		   				return '女';
		   		},
		   		editor:{
				   		xtype : 'combo',
			   			store: Ext.create('Ext.data.Store',{
			   				fields: ['value','name'],
			   				data: [{'value':'0','name':'男'},
			   				       {'value':'1','name':'女'}]
			   			}),
			   			displayField: 'name',
			   			valueField: 'value',
			   			emptyText: '请选择性别',
			   			queryMode: 'local',
			   			triggerAction: 'all',
			   			forceSelection: true,
			   			typeAhead: true,
			   			selectOnFocus: true,
			   			editable: false
		   		}},
		   		{header: '职位',dataIndex: 'job',width: 120,align: 'center',
		   			editor: {
		   				xtype : 'combo',
		   				displayField: 'name',
			   			valueField: 'value',
		   		        store: PositionStore,
		   		        queryMode: 'local',
		   				name: 'job',
		   				width: 85
		   			},
		   			renderer:function(val){
		   		    	var name=""
		   		    		PositionStore.each(function(data){
							var key=data.get('value');
							var value=data.get('name');
							if(val == key){
								name = value;
							}
						},this);
		   		    	return name;
		   		    }
		   		},
		   		{header: '级别',dataIndex: 'levels',width: 100,align: 'levels',renderer:function(val){
		   			if("0"==val){
		   				return "主管";
		   			}else if("1"==val){
		   				return "一级";
		   			}else if("2"==val){
		   				return "二级";
		   			}else if("3"==val){
		   				return "三级";
		   			}else if("4"==val){
		   				return "四级";
		   			}else if("5"==val){
		   				return "五级";
		   			}else if("6"==val){
		   				return "见习";
		   			}else if("7"==val){
		   				return "实习生";
		   			}else if("8"==val){
		   				return "正式员工";
		   			}
		   		},
		   		editor: {
		   			xtype : 'combo',
		   			store: Ext.create('Ext.data.Store',{
		   				fields: ['value','name'],
		   				data: [{'value':'0','name':'主管'},{'value':'1','name':'一级'},{'value':'2','name':'二级'},
		   					       {'value':'3','name':'三级'},{'value':'4','name':'四级'},{'value':'5','name':'五级'},
		   					       {'value':'6','name':'见习'},{'value':'7','name':'实习生'},{'value':'8','name':'正式员工'}]
		   			}),
		   			displayField: 'name',
		   			valueField: 'value',
		   			emptyText: '请选择级别',
		   			queryMode: 'local',
		   			triggerAction: 'all',
		   			forceSelection: true,
		   			typeAhead: true,
		   			selectOnFocus: true,
		   			editable: false
		   		}},
		   		{header: '学历',dataIndex: 'edution',width: 80,align: 'edution',renderer:function(val){
		   			if("0"==val){
		   				return "博士";
		   			}else if("1"==val){
		   				return "研究生";
		   			}else if("2"==val){
		   				return "本科";
		   			}else if("3"==val){
		   				return "大专";
		   			}else if("4"==val){
		   				return "中专";
		   			}else if("5"==val){
		   				return "初中";
		   			}
		   		},
		   		editor: {
		   			xtype : 'combo',
		   			store: Ext.create('Ext.data.Store',{
		   				fields: ['value','name'],
		   				data: [{'value':'0','name':'博士'},{'value':'1','name':'研究生'},{'value':'2','name':'本科'},
		   				       {'value':'3','name':'大专'},{'value':'4','name':'中专'},{'value':'5','name':'初中'}]
		   			}),
		   			displayField: 'name',
		   			valueField: 'value',
		   			emptyText: '请选择学历',
		   			queryMode: 'local',
		   			triggerAction: 'all',
		   			forceSelection: true,
		   			typeAhead: true,
		   			selectOnFocus: true,
		   			editable: false
		   		}},
		   		{header: '入职时间',dataIndex: 'entryTime',width: 100,align: 'entryTime',renderer: formatDt},
		   		{header: '邮箱',dataIndex: 'email',align: 'center',width: 120,
		   		field: {
	       				xtype: 'textfield',
		        		maxLength: 40,
		        		vtype: 'isOverMax',
		        		vtype: 'email'
		        }},
		   		{header: '联系电话',dataIndex: 'telephone',align: 'center',
		        field: {
	       			xtype: 'textfield',
		        	maxLength: 20,
		        	vtype: 'isOverMax'
		        }}
		];
		
		Ext.apply(me,{
			store : 'base.T10601GridStore',
			columns : columns,
			autoScroll : true,
			columnLines: true,
			plugins : [ Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit : 2
			})],
			dockedItems : [ {
				xtype : 'pagingtoolbar',
				store : 'base.T10601GridStore',
				dock : 'bottom',
				displayInfo : true
			}]
		});
		me.callParent(arguments);
	}
});
