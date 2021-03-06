/**
 * 判断是否包含空格
 * 
 * @param str 要判断的字符
 */
function hasBlank(string) {
	//return string.indexOf(str) == 0 || string.length == (string.indexOf(str)+1)?true:false;
	return string.indexOf(" ") >= 0 ? true : false;
};

function mchtType(val){
	if('0' == val){
		return "收单商户";
	}else if('1' == val){
		return "专业化服务商户";
	}
}

//操作员信息
var operator = {};
//系统信息定义
var System = {};
//操作员编号
OPR_ID = 'ope_id';
//操作员名称
OPR_NAME = 'opr_name';
//操作员所在机构名称
OPR_BRH_NAME = 'opr_brh_name';
//信息列表默认显示记录数
QUERY_RECORD_COUNT = 'query_record_count';
QUERY_SELECT_COUNT = 'query_select_count';
/**    
 * 对Date的扩展，将 Date 转化为指定格式的String    
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符    
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)    
 * eg:    
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423    
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04    
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04    
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04    
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18    
 */     
Date.prototype.pattern=function(fmt) {
    var o = {
    "M+" : this.getMonth()+1, //月份
    "d+" : this.getDate(), //日
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //12小时制小时
    "H+" : this.getHours(), //24小时制小时
    "m+" : this.getMinutes(), //分
    "s+" : this.getSeconds(), //秒
    "q+" : Math.floor((this.getMonth()+3)/3), //季度
    "S" : this.getMilliseconds() //毫秒
    };
    var week = {
    "0" : "\u65e5",
    "1" : "\u4e00",
    "2" : "\u4e8c",
    "3" : "\u4e09",
    "4" : "\u56db",
    "5" : "\u4e94",
    "6" : "\u516d"     
    };
    if(/(y+)/.test(fmt)){
 		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    if(/(E+)/.test(fmt)){
 		fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);
    }
    for(var k in o){
		if(new RegExp("("+ k +")").test(fmt)){
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		}
    }
    return fmt;
};

/**
 * 显示错误信息
 * @param  msg
 * @param  animEl
 * @param  fn
 */
function showErrorMsg(msg,animEl) {
	Ext.Msg.show({
	    title: '错误提示',
	    msg: msg,
	    buttons: Ext.MessageBox.OK,
	    icon: Ext.MessageBox.WARNING
	});
	/*setTimeout(function () {
        Ext.MessageBox.hide();
    }, 3000);*/
}
/**
 * 显示警告信息
 * @param  msg
 * @param  animEl
 * @param  fn
 */
function showAlertMsg(msg,animEl,fn) {
	Ext.Msg.show({
	    title: '警告',
	    msg: msg,
	    buttons: Ext.MessageBox.OK,
	    icon: Ext.MessageBox.WARNING,
	    /*listeners: {
	        'render': function(cmp, opts) {
	            Ext.TaskManager.start({
	                run: function(){
	                    cmp.hide();
	                },
	                duration: 3000 //单位毫秒
	            });
	        }
	    }*/
	});
}
/**
 * 显示提示信息
 * @param  msg
 * @param  animEl
 * @param  fn
 */
function showMsg(msg,animEl) {
	Ext.Msg.show({
	    title: '提示',
	    msg: msg,
	    buttons: Ext.MessageBox.OK,
	    icon: Ext.MessageBox.WARNING,
	    /*listeners: {
	        'render': function(cmp, opts) {
	            Ext.TaskManager.start({
	                run: function(){
	                    cmp.hide();
	                },
	                duration: 3000 //单位毫秒
	            });
	        }
	    }*/
	});
}
/**
 * 显示成功信息
 * @param  msg
 * @param  animEl
 */
function showSuccessMsg(msg,animEl) {
	Ext.Msg.show({
	    title: '成功',
	    msg: msg,
	    buttons: Ext.MessageBox.OK,
	    icon: Ext.MessageBox.INFO
	});
	/*setTimeout(function () {
        Ext.MessageBox.hide();
    }, 3000);*/
}

/**
 * 确认提示框
 * @param  msg
 * @param  animEl
 * @param  fn
 */
function showConfirm(msg,animEl,fn) {
	Ext.MessageBox.show({
		msg: msg,
		title: '确认提示',
		//cls:'color:#FFFFFF;',
		//animEl: Ext.get(animEl.getEl()),
		buttons: Ext.Msg.YESNO,
		icon: Ext.MessageBox.QUESTION,
		modal: false,
		width: 250,
		fn: fn
	});
}
 /**
  * 终端审核确认提示框
  * @param  msg
  * @param  animEl
  * @param  fn
  */
 function showCon(msg,animEl,fn) {
 	Ext.MessageBox.show({
 		msg: '<b>'+msg+'</b>',
 		title: '确认提示',
 		animEl: Ext.get(animEl.getEl()),
 		buttons: Ext.MessageBox.YESNOCANCEL,
 		icon: Ext.MessageBox.QUESTION,
 		modal: true,
 		width: 280,
 		fn: fn
 	});
 }

/**
 * 显示程序处理中通用遮罩
 * @param  msg
 */
var mask;
function showMask(msg) {
	mask = new Ext.LoadMask(Ext.getBody(),{   
        msg:msg,   
        removeMask : true                      
    });   
	mask.show(); 
}
/**
 * 隐藏程序处理通用遮罩
 */
function hideMask() {
	mask.hide();
}







/**
 * 打印日志
 * FF专用
 */
function log(info) {
	if(typeof(console) != 'undefined')
		console.log(info);
	else
		alert(info);
}
/**
 * 输入内容校验
 */
Ext.apply(Ext.form.field.VTypes,{
	isOverMax: function(value,f) {
		var str = new String(value);
		var len = 0;
		for(var i = 0; i < str.length; i++) {
			if(escape(str.charAt(i)).length > 1) {
				len += 2;
			} else {
				len += 1;
			}
		}
		return (f.maxLength >= len);
	},
	isOverMaxText: '该输入项内容超出最大限制',
	isFixLength: function(value,f){
		var str = new String(value);
		var len = 0;
		for(var i = 0; i < str.length; i++) {
			if(escape(str.charAt(i)).length > 1) {
				len += 2;
			} else {
				len += 1;
			}
		}
		return (f.maxLength == len);
	},
	isFixLengthText: '该输入项内容不满足其固定长度的限制',
	isMoney: function(value,f) {
		var str = new String(value);
		var reg = new RegExp(/^(([1-9]\d*)|0)(\.\d{1,2})?$/);
		return reg.test(str);
	},
	isMoneyText: '请输入正确格式的金额',
	isRate: function(value,f) {
		var str = new String(value);
		var reg = new RegExp(/^(([1-9]\d*)|0)(\.\d{1,2})?$/);
		return reg.test(str);
	},
	isRateText: '请输入正确格式的比例',
    isAlphanum: function(value,f){
        var str = new String(value);
        var reg = new RegExp(/^[a-zA-Z0-9_]+$/);
        return reg.test(str);
    },
    isAlphanumText: '请输入正确格式',
    isNumber: function(value,f) {
    	var str = new String(value);
        var reg = new RegExp("^\\d+$");
        return reg.test(str);
    },
    isNumberText: '该输入项只能包含数字'
});

/**
 * 格式化日期时间
 * @param  val
 * @return
 */
function formatTs(val) {
	if(val.length == 14) {
		return val.substring(0,4) + '-' + 
		       val.substring(4,6) + '-' + 
		       val.substring(6,8) + ' ' + 
		       val.substring(8,10) + ':' + 
		       val.substring(10,12) + ':' + 
		       val.substring(12,14);
	} else if(val.length == 10) {
		return val.substring(0,2) + '-' + 
		       val.substring(2,4) + ' ' + 
		       val.substring(4,6) + ':' + 
		       val.substring(6,8) + ':' + 
		       val.substring(8,10);
	} else if (val.length ==  8){
		return val.substring(0,4)+ '-' +
		       val.substring(4,6)+ '-' +
		       val.substring(6,8);
	}else if (val.length == 6){
		return val.substring(0,4) + '-' +
		       val.substring(4,6);
	}else {
		return val;
    }
}
/**
 * 交易金额保留两位小数
 */
function formatAmt(val) {
	if(val.substring(val.length-3,val.length-2) !='.') {
		return val.length + '.00';
	} else {
		return val;
	}
}

function formatHour(val){
	if(val.length >= 6 ){
		return val.substring(0,2)+':'+
		       val.substring(2,4)+':'+
		       val.substring(4,6);
		
	}else if (val.length==4){
		return val.substring(0,2)+':'+ val.substring(2,4);
	}
}
/**
 * 格式化8位日期时间
 * @param  val
 * @return
 */
function formatDt(val) {
	if(val.length == 8) {
		return val.substring(0,4) + '-' + 
	       val.substring(4,6) + '-' + 
	       val.substring(6,8);
	} else if(val.length == 14){
		return val.substring(0,4) + '-' + 
	       val.substring(4,6) + '-' + 
	       val.substring(6,8) + ' '+
	       val.substring(8,10) + ':'+
	       val.substring(10,12) + ':'+
	       val.substring(12,14);
	}
}
/**
 * 格式化14位日期
 * @param val
 * @return
 */
//function formatTs(val) {
//	alert(val.length);
//	if(val.length == 8){
//		return val.substring(0,4) + '-' +
//			   val.substring(4,6) + '-' +
//			   val.substring(6,8);
//	}else if(val.length == 14){
//		return val.substring(0,4) + '-' +
//			   val.substring(4,6) + '-' +
//			   val.substring(6,8) + ' ' +
//			   val.substring(8,10)+ ':' +
//			   val.substring(10,12)+ ':'+
//			   val.substring(12,14);
//	}else{
//		return val;
//	}
//}
//数组扩展
/**
 * 添加元素
 * @param {} x
 */
Array.prototype.add = function(x) {
	this.push(x);
};
//删除元素
Array.prototype.remove = function(x) {
	var len = this.length;
	for(var i = 0; i < len; i++) {
		if(x == this[i]) {
			this.splice(i,1);
			i--;
		}
	}
};
/**
 * 删除指定位置的元素
 * @param {} x
 */
Array.prototype.removeAt = function(x) {
	this.splice(x,1);
};
/**
 * 转换为字符串
 * @return {}
 */
Array.prototype.toString = function() {
	return this.join(",");
};
/**
 * 获得指定元素的索引
 * @param {} x
 * @return {}
 */
Array.prototype.getIndex = function(x) {
	var len = this.length;
	for(var i = 0; i < len; i++) {
		if(x == this[i]) {
			return i;
		}
	}
	return -1;
};
/**
 * 字符串替换
 * @param {} s1
 * @param {} s2
 * @return {}
 */
String.prototype.replaceAll = function(s1,s2) {
	return this.replace(new RegExp(s1,'mg') ,s2);
};

/**
 * 将SelectOptionsDWR返回的json数据转换为ArrayStore
 * 可读的Array数据
 * @param {} jsonData
 * @return {}
 */
function decodeJsonToArray(jsonData) {
	return Ext.decode(jsonData.replaceAll('{"data":','').replaceAll('displayField','').replaceAll('valueField','').replaceAll('{','[').replaceAll('}',']').replaceAll(']]]',']]').replaceAll('"":','').replaceAll('"','\''));
}
/**
 * 获得内容长度
 * @param {} obj
 * @return {}
 */
function getLength(obj) {
	var str = new String(obj);
	var len = 0;
	for(var i = 0; i < str.length; i++) {
		if(escape(str.charAt(i)).length > 1) {
			len += 2;
		} else {
			len += 1;
		}
	}
	return len;
}
/**
 * 根据编号x获得后台数据集
 * @param {} x
 * @return {}
 */
function DataMap(x) {
	if(typeof(x) == 'string') {
		var arr = new Array();
			SelectOptionsDWR.getDataMap(x,function(ret) {
			for(var key in ret) {
				arr[key] = ret[key];
			}
		});
		return arr;
	} else
		return null;
}

/**
 * Form增加重置所有表单项为空的方法
 */
Ext.form.BasicForm.prototype.resetAll = function() {
	this.items.each(function(f){
          	f.setValue("");
    });
    this.clearInvalid();
};

/**
 * Form增加disable所有表单项为空的方法
 */
Ext.form.BasicForm.prototype.disableAll = function() {
  	this.items.each(function(f){
  		try{
          	f.disable();
  		}catch(e){}
    });
    this.clearInvalid();
};


/**
 * Form增加enable所有表单项为空的方法
 */
Ext.form.BasicForm.prototype.enableAll = function() {
  	this.items.each(function(f){
  		try{
          	f.enable();
  		}catch(e){}
    });
    this.clearInvalid();
};


/**
 * VTypes daterange
 * 定义开始日期和截止日期的关联
 * 
 * 
 * 例
 * {
 *  xtype: 'datefield',
 *  id: 'startDate',
 * 	vtype: 'daterange',
 *  endDateField: 'endDate'
 * }
 * 
 */
Ext.apply(Ext.form.VTypes, {
  daterange : function(val, field) {
      var date = field.parseDate(val);

      if(!date){
          return false;
      }
      if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
          var start = Ext.getCmp(field.startDateField);
          start.setMaxValue(date);
          start.validate();
          this.dateRangeMax = date;
      }
      else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
          var end = Ext.getCmp(field.endDateField);
          end.setMinValue(date);
          end.validate();
          this.dateRangeMin = date;
      }
      return true;
  }
});
function GetData(x){
	var data;
	if(typeof(x) == 'string') {
			SelectOptionsDWR.getComboData(x,function(ret) {
			data = ret;
			return data;
		});
		
	} else
		return null;
	
};
//数据转换公用方法
function storeDataTs (data,dataStore){
	if(null ==data){
	   return '';
	}
	var index = dataStore.find("value",data);
	var record = dataStore.getAt(index);
	if(null !=record){
	    return record.data.name;
	}else{
	    return data;
	}
};
//按钮控制方法
function setMenuDisabled(menuName,flag){
	var menu = Ext.getCmp(menuName);
	if(menu){
		menu.setDisabled(flag);
	}
}
/*//复选下拉组件
Ext.define('Ext.ux.MultiComboBox', {
    extend: 'Ext.form.ComboBox',
    alias: 'widget.multicombobox',
    xtype: 'multicombobox',
    initComponent: function(){
    	this.multiSelect = true;
    	this.listConfig = {
	    	  itemTpl : Ext.create('Ext.XTemplate',
	  	    	    '<input type=checkbox>{name}'),
	  	      onItemSelect: function(record) {    
	  	    	  var node = this.getNode(record);
    	          if (node) {
    	             Ext.fly(node).addCls(this.selectedItemCls);
    	             
    	             var checkboxs = node.getElementsByTagName("input");
    	             if(checkboxs!=null)
    	             {
    	            	 var checkbox = checkboxs[0];
  	    				 checkbox.checked = true;
    	             }
    	          }
	  	      },
	  	      listeners:{
  	    		  itemclick:function(view, record, item, index, e, eOpts ){
  	    			  var isSelected = view.isSelected(item);
  	    			  var checkboxs = item.getElementsByTagName("input");
  	    			  if(checkboxs!=null)
  	    			  {
  	    				  var checkbox = checkboxs[0];
  	    				  if(!isSelected)
  	    				  {
  	    					  checkbox.checked = true;
  	    				  }else{
  	    					  checkbox.checked = false;
  	    				  }
  	    			  }
  	    		  }
	  	      }    	  
	  	}   	
    	this.callParent();
    }
});*/