/*
 * System Support
 * 
 */

// 商户MCC数据集
var mchntMccStore = new Ext.data.JsonStore({
	fields: ['valueField','displayField'],
	root: 'data'
});
//var initDate = new Date('1/1/2008');
// 报表类型
var reportType = new Ext.form.ComboBox({
	fieldLabel: '报表类型',
	store: new Ext.data.ArrayStore({
		fields: ['valueField','displayField'],
		data: [['EXCEL','Excel 格式'],['PDF','PDF 格式']]}),
	reader: new Ext.data.ArrayReader(),
	hiddenName: 'reportType',
	allowBlank: false,
	blankText: '请选择报表格式',
	value: 'EXCEL'
});
/*
 * 时间选择TimeFiled类，继承自ComboBox xtype: 'timefieldsp'
 */
// Ext.form.TimeFieldSP = Ext.extend(Ext.form.ComboBox, {
Ext.form.TimeFieldSP = Ext.create('Ext.form.field.ComboBox', {
//    extend: 'Ext.form.ComboBox',
    alias : 'timefieldsp',
// update by lvcg for Extjs4
	emptyText: '请选择', 
	minValue : null,
	maxValue : null,
	minText : "输入的时间需小于或等于 {0}",
	maxText : "输入的时间需大于或等于 {0}",
	invalidText : "{0} 不是一个有效的时间",
	format : "g:i A",
	altFormats : "g:ia|g:iA|g:i a|g:i A|h:i|g:i|H:i|ga|ha|gA|h a|g a|g A|gi|hi|gia|hia|g|H",
	increment: 15,
	mode: 'local',
	triggerAction: 'all',
	typeAhead: false,
	initDate: '1/1/2008',
	initDateFormat: 'j/n/Y',
	
	initComponent : function(){
		if(typeof this.minValue == "string"){
			this.minValue = this.parseDate(this.minValue);
		}
		if(typeof this.maxValue == "string"){
			this.maxValue = this.parseDate(this.maxValue);
		}
		if(!this.store){
			var min = this.parseDate(this.minValue) || Ext.Date.clearTime(new Date(this.initDate),false);
			var max = this.parseDate(this.maxValue) || Ext.Date.add(Ext.Date.clearTime(new Date(this.initDate),false),Ext.Date.HOUR,23);
			var times = [];
//			while(min.dateFormat(this.format) <= max.dateFormat(this.format) 
//				&& min.dateFormat(this.format) < min.add('mi', this.increment).dateFormat(this.format)){
			while(Ext.Date.format(min,this.format) <= Ext.Date.format(max,this.format)
					&& Ext.Date.format(min,this.format) < Ext.Date.format(Ext.Date.add(min,this.increment),this.format)){
				times.push(min.dateFormat(this.format));
				min = min.add('mi', this.increment);
			}
			this.store = times;
		}
		//Ext.form.TimeFieldSP.superclass.initComponent.call(this,arguments);
		this.callParent().initComponent;
		
	},
	 getValue : function(){
	 	var v = Ext.form.TimeFieldSP.superclass.getValue.call(this);
	 	alert(v);
	 	return this.formatDate(this.parseDate(v)) || '';
	 },
	 setValue : function(value){
		 alert(value);
	 	return Ext.form.TimeFieldSP.superclass.setValue.call(this, this.formatDate(this.parseDate(value)));
	 },
	 validateValue : Ext.form.DateField.prototype.validateValue,
	 parseDate : Ext.form.DateField.prototype.parseDate,
	 formatDate : Ext.form.DateField.prototype.formatDate,
	 beforeBlur : function(){
	 	var v = this.parseDate(this.getRawValue());
	 	if(v){
	 		this.setValue(v.dateFormat(this.format));
	 	}
	 	Ext.form.TimeFieldSP.superclass.beforeBlur.call(this);
	}
});
// Ext.reg('timefieldsp', Ext.form.TimeFieldSP);



/*
 * 定义Store组件 通用combo组件
 * 
 * xtype: 'basecomboselect'
 */
//Ext.namespace('Ext.ux2'); 
//// Ext.ux2.baseComboStore = Ext.extend(Ext.data.Store,{
//Ext.define('Ext.ux2.baseComboStore', {
//	extend: 'Ext.data.Store',
//	alias : 'widget.basecomboselect',
//	fields: ['valueField', 'displayField'],
//	proxy : {
//		type : 'ajax',
//		url : 'baseSelect.asp',
//		actionMethods: {   
//            read: 'POST'
//        }, 
//		reader : {
//			type : 'json',
//			root : 'data'
//		}
//	}
//	// update by lvcg for Extjs4
//	autoLoad : true
//});
/*
Ext.ux2.baseCombo = Ext.extend(Ext.form.ComboBox,{
	initComponent:function(){
		if(!this.store){
			var combo = this;
			var basePare = this.baseParams;
			this.store = new Ext.ux2.baseComboStore({
				baseParams: {ID: basePare},
				listeners: {
					load: function(){
						if(combo.getValue()!=''){
							combo.setValue(combo.getValue());
						}
					}
				}
			});
		}
		if(null != this.hiddenName && null == this.id){
			this.id = 'id' + this.hiddenName;
		}
		if(!this.allowBlank){
			var label = this.fieldLabel;
			if(label.indexOf("*") != -1){
				label = label.substring(0, label.length - 1);
			}
			this.blankText = '请选择' + label;
		}
		Ext.ux2.baseCombo.superclass.initComponent.call(this);
	},
	displayField: 'displayField',
	valueField: 'valueField',
	mode: 'local',
	emptyText: '请选择',
	triggerAction: 'all',
	forceSelection: true,
	selectOnFocus: true,
	editable: false,
	lazyRender: false,
	typeAhead: false,
	store:null,
	listeners:{
		// 修正第一次有可能获取不到数据的问题
		'focus':function(){
			if(this.store.getTotalCount() == 0){
				this.store.reload();
			}
		}
	}
});*/
// Ext.reg('basecomboselect', Ext.ux2.baseCombo);



/*
 * 通用textfield非空组件
 * 
 * xtype: 'basecomboselect'
 */
// Ext.ux2.textfieldNotNull = Ext.extend(Ext.form.TextField,{
Ext.define('Ext.ux2.textfieldNotNull', {
	extend: 'Ext.form.TextField',
	alias : 'textnotnull',
		// update by lvcg for Extjs4
	initComponent:function(){
		if(null != this.id && null == this.name){
			this.name = this.id;
		}
		if(!this.allowBlank){
			var label = this.fieldLabel;
			if(label.indexOf("*") != -1){
				label = label.substring(0, label.length - 1);
			}
			this.blankText = '请输入' + label;
		}
		Ext.ux2.textfieldNotNull.superclass.initComponent.call(this); 
	},
	allowBlank: false,
	id: null
});
// Ext.reg('textnotnull', Ext.ux2.textfieldNotNull);


// 返回radio value
function getRadioChecked(name){
	var checks = Ext.getDom(Ext.getDoc()).getElementsByName(name);
	for(var i=0;i<checks.length;i++){
		if(checks[i].checked){
			return checks[i].value
		}
	}
}


/*
 * 可用于远程转译字段的工具
 * 
 * 使用： getRemoteTrans(value,id) 具体执行方法见RemoteTransMethod.java,其中方法名=ID
 * 
 */
// var TransRecord = new Ext.data.Record.create([
// {name: 'index', type: 'string'},
// {name: 'trans', type: 'string'}
// ]);
//update by lvcg for Extjs4
Ext.regModel('TransRecord', {
	fields: [{
		name: 'index',
		type: 'string'
		}, {
			name: 'trans',
			type: 'string'
		}]
});
var TransData = [
	['basic','basic']
];
//var TransStore = new Ext.data.Store({
//	model: 'TransRecord',
//	proxy: new Ext.data.MemoryProxy(TransData),
//	reader: new Ext.data.ArrayReader({
//		idProperty: 'index'
//	}, TransRecord)
//});
//
//update by lvcg for Extjs4
var TransStore = Ext.create('Ext.data.Store',{
	model: 'TransRecord',
	proxy: {
		type: 'memory',
		reader: {
			type: 'array',
			root : 'index'
		}
	}
});
TransStore.load();
function getRemoteTrans(val,id){
	if(TransStore.getCount() > 0){
		if(TransStore.find('index', id + val) = -1){
			dwr.engine.setAsync(false);
			RemoteTransDWR.getTrans(id,val,RemoteBack)
			dwr.engine.setAsync(true);
		}
		return TransStore.getAt(TransStore.find('index',id + val)).get('trans');
	}
}
function RemoteBack(ret){
	for(key in ret){
		TransStore.add(new TransRecord({
			index: key,
			trans: ret[key]
		}));
	}
}

/*
 * 通过Store获取内容后显示
 */
// Ext.form.ComboForDispaly = Ext.extend(Ext.form.Field, {
Ext.define('Ext.form.ComboForDispaly', {
	extend: 'Ext.form.Field',
	alias : 'combofordispaly',
	// update by lvcg for Extjs4
    validationEvent : false,
    validateOnBlur : false,
    defaultAutoCreate : {tag: "div"},
    fieldClass : "x-form-display-field",
    htmlEncode: false,
    baseParams: null,
    initEvents : Ext.emptyFn,
    store:null,
    hiddenName: null,
    initComponent:function(){
		this.name = this.hiddenName;
		Ext.form.ComboForDispaly.superclass.initComponent.call(this); 
	},
    isValid : function(){
        return true;
    },
    validate : function(){
        return true;
    },
    getRawValue : function(){
        var v = this.rendered ? this.el.dom.innerHTML : Ext.value(this.value, '');
        if(v === this.emptyText){
            v = '';
        }
        if(this.htmlEncode){
            v = Ext.util.Format.htmlDecode(v);
        }
        return v;
    },
    getValue : function(){
        return this.getRawValue();
    },
    getName: function() {
        return this.name;
    },
    setRawValue : function(v){
        if(this.htmlEncode){
            v = Ext.util.Format.htmlEncode(v);
        }
        return this.rendered ? (this.el.dom.innerHTML = (Ext.isEmpty(v) ? '' : v)) : (this.value = v);
    },
    setValue : function(v){
    	if(v == ''){
    		return;
    	}
    	if(!this.store && this.baseParams){
			var field = this;
			var basePare = this.baseParams;
			this.store = new Ext.ux2.baseComboStore({
				baseParams: {ID: basePare},
				listeners: {
					load: function(){
						var record;
        				if(field.store.getCount() > 0){
            				field.store.each(function(r){
                				if(r.get('valueField') == v){
                    				field.setRawValue(r.get('displayField'));
                    				return field;
                				}
            				});
        				}
					}
				}
			});
		}else if(this.store){
			var field = this
			var record;
        	if(field.store.getCount() > 0){
            	field.store.each(function(r){
            		if(r.get('valueField') == v){
            			field.setRawValue(r.get('displayField'));
            			return field;
            		}
            	});
        	}
		}else{
	    	this.setRawValue(v);
	    	return this;
		}
    }
});
// Ext.reg('combofordispaly', Ext.form.ComboForDispaly);


/*
 * 动态加载的COMBO 该组空间暂时不支持修改时使用
 */
Ext.ux2.dynamicStore = Ext.extend(Ext.data.Store,{
	proxy: new Ext.data.HttpProxy({
		url: 'dynamicSelect.asp'
	}),
	reader : new Ext.data.JsonReader({
		root : 'data',
		totalProperty: 'totalCount'
		}, [{
			name : 'valueField'
		}, {
			name : 'displayField'
	}]),
	autoLoad : true
});
// Ext.ux2.dynamicCombo = Ext.extend(Ext.form.ComboBox,{
Ext.define('Ext.ux2.dynamicCombo', {
	extend: 'Ext.form.ComboBox',
	alias : 'dynamicCombo',
		// update by lvcg for Extjs4
	initComponent:function(){
		if(!this.store){
			var combo = this;
			var method = this.methodName;
			this.store = new Ext.ux2.dynamicStore({
				baseParams: {methodName: method},
				listeners: {
					load: function(){
						
						// 如果无法找到数据就将查询条件重置，但不适用于combo的reload
						if(this.getTotalCount() == 1 && this.getAt(0).data.valueField==''){
							combo.inputValue == '';
						}
					},
					beforeload: function(){
						Ext.apply(this.baseParams, {
							inputValue: combo.inputValue
						});
						switchs = false;
					}
				}
			});
		}
		if(null != this.hiddenName && null == this.id){
			this.id = 'id' + this.hiddenName;
		}
		if(!this.allowBlank){
			var label = this.fieldLabel;
			if(label.indexOf("*") != -1){
				label = label.substring(0, label.length - 1);
			}
			this.blankText = '请选择' + label;
		}
		this.callParent(arguments);
		//Ext.ux2.dynamicCombo.superclass.initComponent.call(this);
	},
	displayField: 'displayField',
	valueField: 'valueField',
	mode: 'local',
	emptyText: '请选择',
	triggerAction: 'all',
	forceSelection: true,
	selectOnFocus: true,
	editable: false,
	lazyRender: false,
	typeAhead: false,
	minChars: 1,
	pageSize: 50,
	lastValue: '',
	store:null,
	inputValue: '',
	switchs: false,
	isFirstTime: true,
	listeners:{
		'focus':function(){
			if(this.store.getTotalCount() == 0){
				this.store.reload();
			}
		},
		'keyup':function(c,d){
			var combo = this;
			var p = d.keyCode;
			// 判断按键的有效性,暂时只考虑这些
			if(Number(p)==13||Number(p)==37||Number(p)==38||Number(p)==39||Number(p)==40){
				return;
			}
			// 如果输入的字符没有变化，则不做处理
			if(combo.inputValue == combo.getRawValue()){
				return;
			}
			// 只有输入的字符才做为匹配的条件
			combo.inputValue = combo.getRawValue();
			// 请求数据开关
			if(!switchs){
				switchs = true;
				setTimeout(function(){combo.getStore().load();},500);
			}
		}
	}
});
// Ext.reg('dynamicCombo', Ext.ux2.dynamicCombo);

/** **********查询商户下终端方法********** */
// Ext.ux2.dynamicTermCombo = Ext.extend(Ext.form.ComboBox,{
Ext.define('Ext.ux2.dynamicTermCombo', {
	extend: 'Ext.form.ComboBox',
	alias : 'dynamicTermCombo',
		// update by lvcg for Extjs4
	initComponent:function(){
		if(!this.store){
			var combo = this;
			var method = this.methodName;
			this.store = new Ext.ux2.dynamicStore({
				baseParams: {methodName: method},
				listeners: {
					load: function(){
						
						// 如果无法找到数据就将查询条件重置，但不适用于combo的reload
						if(this.getTotalCount() == 1 && this.getAt(0).data.valueField==''){
							combo.inputValue == '';
						}
					},
					beforeload: function(){
						Ext.apply(this.baseParams, {
							inputValue: combo.inputValue
						});
						switchs = false;
					}
				}
			});
		}
		if(null != this.hiddenName && null == this.id){
			this.id = 'id' + this.hiddenName;
		}
		if(!this.allowBlank){
			var label = this.fieldLabel;
			if(label.indexOf("*") != -1){
				label = label.substring(0, label.length - 1);
			}
			this.blankText = '请选择' + label;
		}
		Ext.ux2.dynamicTermCombo.superclass.initComponent.call(this);
	},
	displayField: 'displayField',
	valueField: 'valueField',
	mode: 'local',
	emptyText: '请选择',
	triggerAction: 'all',
	forceSelection: true,
	selectOnFocus: true,
	editable: false,
	lazyRender: false,
	typeAhead: false,
	minChars: 1,
	pageSize: 50,
	lastValue: '',
	store:null,
	inputValue: '',
	switchs: false,
	isFirstTime: true
});
// Ext.reg('dynamicTermCombo', Ext.ux2.dynamicTermCombo);


