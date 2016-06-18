var timeToolItem = new Ext.toolbar.TextItem('');
//修改操作员密码
var resetPwdMenu = {
	text: '修改密码',
	id: 'key',
	iconCls: 'key',
	handler: function() {
		resetPwdWin.show();
		resetPwdForm.getForm().reset();
		resetPwdForm.getComponent('resetOprId').setValue(operator[OPR_ID]);
	}
};

var lockScreenMenu = {
	text: '锁屏',
	id: 'lock',
	iconCls: 'lock',
	handler: function() {
		lockWin.show();
		lockForm.getForm().reset();
		lockForm.getComponent('lockOprId').setValue(operator[OPR_ID]);
	}
};

var quitMenu = {
	text: '安全退出',
	iconCls: 'quit',
	handler: function() {
	showConfirm('确定要退出吗？',this,function(bt) {
		if(bt == 'yes') {
				window.location.href = 'logout.asp';
		}
	});
	}
};
/**
 * 重置密码表单
 */
var resetPwdForm = new Ext.form.Panel({
	width: 300,
	autoHeight: true,
	waitMsgTarget: true,
	margin : '0 0 10 0',
	border : false,
	defaults : {
		margin : '10 10 0 10'
	},
	items: [{
		xtype: 'textfield',
		fieldLabel: '操作员编号',
		id: 'resetOprId',
		name: 'resetOprId',
		readOnly: true
	},{
		xtype: 'textfield',
		fieldLabel: '原密码',
		margin: '10 10 10 10',
		inputType: 'password',
		id: 'resetPassword',
		name: 'resetPassword',
		allowBlank: false,
		blankText: '原密码不能为空'
	},{
		xtype: 'textfield',
		fieldLabel: '新密码',
		margin: '10 10 10 10',
		inputType: 'password',
		regex: /^(?=^.{8,}$)(?=(?:.*?\d){1})(?=(?:.*?[a-zA-Z]){1})(?=(?:.*?[!@#$%*()_+^&}{:;?.]){1})(?!.*\s)[0-9a-zA-Z!@#$%*()_+^&]*$/,
		regexText: '密码必须包含数字、字母和符号,长度为8位以上',
		id: 'resetPassword1',
		name: 'resetPassword1',
		allowBlank: false,
		blankText: '新密码不能为空'
	},{
		xtype: 'textfield',
		fieldLabel: '重复密码',
		margin: '10 10 10 10',
		inputType: 'password',
		regex: /^(?=^.{8,}$)(?=(?:.*?\d){1})(?=(?:.*?[a-zA-Z]){1})(?=(?:.*?[!@#$%*()_+^&}{:;?.]){1})(?!.*\s)[0-9a-zA-Z!@#$%*()_+^&]*$/,
		regexText: '密码必须包含数字、字母和符号,长度为8位以上',
		id: 'resetPassword2',
		name: 'resetPassword2',
		allowBlank: false,
		blankText: '重复密码不能为空'
	}]
});
/**
 * 修改密码窗口
 */
var resetPwdWin = new Ext.Window({
	title: '密码修改',
	width: 300,
	layout: 'fit',
	iconCls: 'key',
	items: [resetPwdForm],
	resizable: false,
	closable: true,
	closeAction: 'hide',
	buttonAlign: 'center',
	initHiddenL: true,
	modal: true,
	draggable: false,
	animateTarget: 'key',
	buttons: [{
		text: '提交',
		handler: function() {
			if(!resetPwdForm.getForm().isValid()) {
				return;
			}
			if(resetPwdForm.getComponent('resetPassword').getValue() == resetPwdForm.getComponent('resetPassword1').getValue()) {
				showAlertMsg('新密码不能和原始密码一致，请重新输入',resetPwdForm,function() {
					resetPwdForm.getComponent('resetPassword1').setValue('');
					resetPwdForm.getComponent('resetPassword2').setValue('');
				});
				return;
			}
			if(resetPwdForm.getComponent('resetPassword1').getValue() != resetPwdForm.getComponent('resetPassword2').getValue()) {
				showAlertMsg('两次输入的新密码不一致，请重新输入',resetPwdForm,function() {
					resetPwdForm.getComponent('resetPassword1').setValue('');
					resetPwdForm.getComponent('resetPassword2').setValue('');
				});
				return;
			}
			resetPwdForm.getForm().submit({
				url: 'resetPwd.asp',
				waitMsg: '正在提交，请稍后......',
				success: function(form, action) {
					showMsg(action.result.msg,resetPwdWin,function() {
						resetPwdWin.hide();
					});
				},
				failure: function(form, action) {
					showErrorMsg(action.result.msg,resetPwdWin);
				}
			});
		}
	},{
		text: '清空',
		handler: function() {
			resetPwdForm.getForm().reset();
			resetPwdForm.getComponent('resetOprId').setValue(operator[OPR_ID]);
		}
	}]
});
// 锁屏表单
var lockForm = new Ext.form.FormPanel({
	width: 300,
	autoHeight: true,
	waitMsgTarget: true,
	items: [{
		xtype: 'textfield',
		fieldLabel: '操作员编号',
		id: 'lockOprId',
		name: 'lockOprId',
		readOnly: true
	},{
		xtype: 'textfield',
		fieldLabel: '密码',
		inputType: 'password',
		regex: /^[0-9a-zA-Z]{6}$/,
		regexText: '密码必须是6位数字或字母',
		id: 'lockPassword',
		name: 'lockPassword',
		allowBlank: false,
		blankText: '请输入密码'
	}]
});
// 锁屏对话框
var lockWin = new Ext.Window({
	title: '屏幕锁定',
	width: 300,
	layout: 'fit',
	iconCls: 'lock',
	items: [lockForm],
	resizable: false,
	closable: false,
	closeAction: 'hide',
	buttonAlign: 'center',
	initHiddenL: true,
	modal: true,
	draggable: false,
	animateTarget: 'lock',
	buttons: [{
		text: '解锁',
		iconCls: 'key',
		tooltip: '解锁',
		handler: function() {
			if(!lockForm.getForm().isValid()) {
				return;
			}
			lockForm.getForm().submit({
				url: 'unlockScreen.asp',
				waitMsg: '正在验证密码，请稍后......',
				success: function(form, action) {
					lockWin.hide();
					
				},
				failure: function(form, action) {
					showErrorMsg(action.result.msg,lockWin);
				}
			});
		}
	}]
});

Ext.define('zft.view.system.Center', {
	extend: 'Ext.panel.Panel',
	alias:'widget.center',
	bodyCls: 'bgimage',
	initComponent : function() {
		var me = this;
		//var unDealPanel = Ext.create('zft.view.system.UnDealPanel');
		var str = 'All rights reserved©2011.Chengdu PayExpress New Information Technology Services Ltd.Co  成都支付通新信息技术服务有限公司' + '|' + lastTime;
		Ext.apply(me,{
			layout : 'fit',
			title : '首页',
			html: '<iframe id="mainUI" name="mainUI" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe>',
			bbar: [
                    "<MARQUEE scrolldelay='180'>" + str + "</MARQUEE>"
			       //	'All rights reserved©2011.Chengdu PayExpress New Information Technology Services Ltd.Co  成都支付通新信息技术服务有限公司',
					//'-',
					//lastTime,
					//'-',
					//'当前时间：',
					//timeToolItem,
			],
			tools: [{
				margin : '0 10 0 10',
				tooltip: '首页',
				baseCls: 'home',
				handler: function() {
					//var con = application.getController('zft.controller.system.MainController');
					//var center = con.getCenter();
					//center.setTitle('我的待办事项');
					//var mainPanel = Ext.create('zft.view.system.UnDealPanel');
					//center.removeAll(true);
					//center.add(mainPanel);
					//center.setActive(true, mainPanel);
				}
			},{
				margin : '0 10 0 10',
				tooltip: '修改密码',
				baseCls : 'key',
			    handler: function() {
			    	resetPwdWin.show();
			        resetPwdForm.getForm().reset();
			        resetPwdForm.getComponent('resetOprId').setValue(operator[OPR_ID]);
			    }
			},{
				margin : '0 10 0 10',
				tooltip: '退出',
				baseCls: 'quit',
				handler: function() {
					showConfirm('确定要退出吗？',this,function(bt) {
						if(bt == 'yes') {
							window.location.href = 'logout.asp';
						}
					});
				}
			}],
			listeners: {
				render: function() {
					Ext.TaskManager.start({
						run: function() {
							Ext.fly(timeToolItem.getEl()).update( new Date().pattern('yyyy-MM-dd HH:mm:ss'));
						},
						interval: 1000
					});
				}
			}
		});
		me.callParent(arguments);
	}
});

function lackScreenSubmit(obj,options){
	
	var form = new Ext.form.FormPanel({
		frame: true,
		border: true,
		width: 300,
		autoHeight: true,
		items: [{
			xtype: 'textfield',
			id: 'username',
			name: 'username',
			fieldLabel: '柜员号',
			maskRe: /^[0-9]+$/,
			allowBlank: false,
			blankText: '请输入柜员号'
		},{
			xtype: 'textfield',
			inputType: 'password',
			id: 'pass',
			name: 'pass',
			fieldLabel: '密码',
			allowBlank: false,
			blankText: '请输入柜员密码'
		}]
	});
	
	
	var win = new Ext.Window({
		title: '支付通统一授权系统',
		frame: true,
		width: 300,
		layout: 'fit',
		iconCls: 'logo',
		items: [form],
		resizable: false,
		closable: true,
		buttonAlign: 'center',
		initHiddenL: true,
		modal: true,
		draggable: true,
		animateTarget: 'lock',
		buttons: [{
			text:'确定',
			handler: function(bt){
				var frm = form.getForm();
				if(frm.isValid()) {
					frm.submit({
						url: 'AuthoriseAction.asp',
						waitTitle : '请稍候',
						waitMsg : '正在验证授权信息,请稍候...',
						success : function(form, action) {
							frm.reset();
							win.close();
							obj.submit(options);
						},
						failure : function(form,action) {
							frm.reset();
							showErrorMsg(action.result.msg,obj);
						},
						params: {
							txnCode: txnCode
						}
					});
				}
			}
		}]
	});
	win.show();
}


function lackScreenRequest(obj,options){
		var form = new Ext.form.FormPanel({
		border: true,
		width: 300,
		autoHeight: true,
		items: [{
			xtype: 'textfield',
			id: 'username',
			name: 'username',
			fieldLabel: '柜员号',
			maskRe: /^[0-9]+$/,
			allowBlank: false,
			blankText: '请输入柜员号'
		},{
			xtype: 'textfield',
			inputType: 'password',
			id: 'pass',
			name: 'pass',
			fieldLabel: '密码',
			allowBlank: false,
			blankText: '请输入柜员密码'
		}]
	});
	
	
	var win = new Ext.Window({
		title: '支付通统一授权系统',
		width: 300,
		layout: 'fit',
		iconCls: 'logo',
		items: [form],
		resizable: false,
		closable: true,
		buttonAlign: 'center',
		initHiddenL: true,
		modal: true,
		draggable: true,
		animateTarget: 'lock',
		buttons: [{
			text:'确定',
			handler: function(bt){
				var frm = form.getForm();
				if(frm.isValid()) {
					frm.submit({
						url: 'AuthoriseAction.asp',
						waitTitle : '请稍候',
						waitMsg : '正在验证授权信息,请稍候...',
						success : function(form, action) {
							frm.reset();
							win.close();
							obj.request(options);
						},
						failure : function(form,action) {
							frm.reset();
							Ext.MessageBox.show({
								msg: action.result.msg,
								title: '错误提示',
								animEl: Ext.getBody(),
								buttons: Ext.MessageBox.OK,
								icon: Ext.MessageBox.ERROR,
								modal: true,
								width: 250
							});
						},
						params: {
							txnCode: txnCode
						}
					});
				}
			}
		}]
	});
	win.show();
}