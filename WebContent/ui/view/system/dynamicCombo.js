Ext.define('zft.view.system.dynamicCombo', {
	extend : 'Ext.form.ComboBox',
	alias : 'widget.dynamicCombo',
	config : {
		methodName : '',
		inputValue : ''
	},
	initComponent : function() {
		me = this;
		var store = Ext.create('Ext.data.Store', {
			fields : [ {
				name : 'valueField'
			}, {
				name : 'displayField'
			}],
			autoLoad : false,
			proxy : {
				type : 'ajax',
				url : 'dynamicSelect.asp',
				actionMethods : {
					read : 'POST'
				},
				extraParams : {
					methodName : me.methodName,
					inputValue : me.inputValue
				},
				reader : {
					type : 'json',
					root : 'data',
					totalProperty : 'totalCount'
				}
			},
		});
		Ext.apply(me,{
			store: store,
			pageSize : 25,
			editable : true,
			typeAhead: true,
			triggerAction : 'all',
		    autoSelect : true,
		    minChars:'local',
			queryMode : 'remote',
		    displayField: 'displayField',
		    valueField: 'valueField',
		});
		var flag = true;
		var inputValue = me.inputValue;
		
		//值发生改变时执行此函数
		me.on('change',function( this_, newValue, oldValue, eOpts ){
			me.forceSelection=true;
    		if(inputValue == "" || inputValue == undefined){
    			if(newValue != ""){
					store.on('beforeload', function(){
						Ext.apply(store.proxy.extraParams, {
							inputValue : newValue
						});
					});
	    		}
    		}
    		//第一次加载完成后跟据value 查看出数据（控件自动调用load事件）仿止重复加载。
    		if(flag){
    			store.load();	
    			flag = false;
    		}
    		
		});
		me.callParent(arguments);
	}
});