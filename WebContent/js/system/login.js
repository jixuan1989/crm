function updateImg(){
	//验证码请求必须要加个随机数，不然不会刷新的。
	Ext.getCmp('randomImg').getEl().update('<img title="点击更换验证码" src='+Ext.contextPath+'/PrintRandomImg?r='+Math.random()+'" onClick="updateImg();">');
}
Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	
	var loginForm = Ext.create('Ext.form.Panel',{
		autoShow: true,
		border : false,
		renderTo :'login_div',
		labelAlign:'right',
		baseCls: '',
		defaults :{
			labelWidth : 60,
			padding : '0 0 0 35'
		},
		items: [{
			fieldLabel: '操作员',
			xtype: 'textfield',
			allowBlank: false,
			blankText: '请输入操作员编号',
			name:'oprid',
			id: 'oprid'
		},{
			inputType: 'password',
			fieldLabel: '密　码',
			xtype: 'textfield',
			allowBlank: false,				
			blankText: '请输入密码',
			name:'password',
			id: 'password'
		},{
			xtype : 'panel',
			layout : 'column',			
			labelAlign:'right',
			baseCls: '',
			border : false,
			defaults : {
				labelWidth : 60
			},
			items : [{
				name:'random',
				id : 'random',
				xtype:'textfield',
				fieldLabel:'验证码',
				columnWidth : .5,
				margin:'0 5 0 0',
				listeners : {  
					specialkey: function(field, e){
						if (e.getKey() == e.ENTER) {
		                    login();
		                }
					 },
					 focus: function(this_, the, eOpts ){
						// Ext.getCmp('randomImg').getEl().update('<img title="点击更换验证码" src='+Ext.contextPath+'/PrintRandomImg?r='+Math.random()+'" onClick="updateImg();">');
					 }
				}
			},{
				id:'randomImg',
				columnWidth : .2,
				minHeight : 22,
		        html:'<img title="点击更换验证码" src='+Ext.contextPath+'/PrintRandomImg?r='+Math.random()+' onclick="javascript:updateImg();">'
			}]
		},{
			xtype : 'panel',
			border : true,
			margin : '0 0 0 40',
			baseCls : '',
			layout : 'column',
			defaults : {
				margin : '10 10 10 10',
				columnWidth : .35,
			},
			items : [{
				xtype : 'button',
				text : '登　陆',
				handler: login,
			},{
				xtype : 'button',
				text : '重　置',
				handler: function() {
					loginForm.getForm().reset();
				}
			}]
		}],
	});
	Ext.define('my.ui.msgWindow', {
		extend : 'Ext.window.Window',
		title : '短信验证信息',
		layout : 'fit',
		autoHeight : true,
		iconCls : 'logo',
		autoShow : true,
		modal : true,
		buttonAlign : 'center',
		resizable : false,
		closable : true,
		initComponent : function() {
			var me = this;
			Ext.apply(me,{
				items : [{
					xtype : 'form',
					items : [{
						xtype: 'textfield',
						margin : '10 10 10 10',
						fieldLabel: '短信验证码',
						name: 'random',
					}]
				}],
				buttons : [{
					text : '提交',
					handler : function (){msgVal(me.down('form').getForm());}
				},{
					text : '重置',
					handler : function (){me.down('form').getForm().reset();}
				}]
			});
			me.callParent();
		}
	});
	/**
	 * 系统登录
	 */
	function login() {
		if(loginForm.getForm().isValid()) {
			if(Ext.getCmp('random').getValue().length != 4){
				Ext.getCmp('random').focus();
				Ext.getCmp('random').setValue('');
				return;
			}
			loginForm.getForm().submit({
				url: 'login.asp',
				success: function(form, action) {
					window.location.href = 'redirect.asp';
				},
				failure: function(form, action) {
					if(action.result.code != undefined && action.result.code == '00') {// 重置密码
						showConfirm('密码已过期，是否进行修改？', form, function(bt) {
							if (bt == "yes") {
								resetPwdWin.show();
								resetPwdForm.getForm().reset();
								resetPwdForm.getForm().findField('resetOprId').setValue(form.findField('oprid').getValue());
							}
						});
					}else if(action.result.code != undefined && action.result.code == '01') {//短信验证
						Ext.create('my.ui.msgWindow');
					}else {
						showErrorMsg(action.result.msg,resetPwdWin);
						Ext.getCmp('randomImg').getEl().update('<img title="点击更换验证码" src='+Ext.contextPath+'/PrintRandomImg?r='+Math.random()+'" onClick="updateImg();">');
						Ext.getCmp('random').setValue('');
					}
				},
				waitMsg: '登录中......'
			});
		}
	}
	/**
	 * 短信验证
	 */
	function msgVal(form) {
		if(form.isValid()) {
			form.submit({
				url: 'msgValidate.asp',
				waitMsg : '登录中，请稍候......',
				success: function(form, action) {
					window.location.href = 'redirect.asp';
				},
				failure: function(form, action) {
					if(action.result.code != undefined && action.result.code == '01') {//短信验证
						form.reset();
						showErrorMsg(action.result.msg,this);
					}
				}
			});
		}
	}
	/**
	 * 重置密码表单
	 */
	var resetPwdForm = Ext.create('Ext.form.Panel',{
		width: 300,
		autoHeight: true,
		waitMsgTarget: true,
		items: [{
			xtype: 'textfield',
			fieldLabel: '操作员编号',
			id: 'resetOprId',
			name: 'resetOprId',
			readOnly: true
		},{
			xtype: 'textfield',
			fieldLabel: '原密码',
			inputType: 'password',
			id: 'resetPassword',
			name: 'resetPassword',
			allowBlank: false,
			blankText: '原密码不能为空'
		},{
			xtype: 'textfield',
			fieldLabel: '新密码',
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
		buttons: [{
			text: '提交',
			handler: function() {
				if(!resetPwdForm.getForm().isValid()) {
					return;
				}
				if(resetPwdForm.getForm().findField('resetPassword').getValue() == resetPwdForm.getForm().findField('resetPassword1').getValue()) {
					showAlertMsg('新密码不能和原始密码一致，请重新输入',resetPwdForm,function() {
						resetPwdForm.getForm().findField('resetPassword1').setValue('');
						resetPwdForm.getForm().findField('resetPassword2').setValue('');
					});
					return;
				}
				if(resetPwdForm.getForm().findField('resetPassword1').getValue() != resetPwdForm.getForm().findField('resetPassword2').getValue()) {
					showAlertMsg('两次输入的新密码不一致，请重新输入',resetPwdForm,function() {
						resetPwdForm.getForm().findField('resetPassword1').setValue('');
						resetPwdForm.getForm().findField('resetPassword2').setValue('');
					});
					return;
				}
				resetPwdForm.getForm().submit({
					url: 'resetPwd.asp',
					waitMsg: '正在提交，请稍后......',
					success: function(form, action) {
						showSuccessMsg(action.result.msg, resetPwdWin);
						loginForm.getForm().findField('password').setValue('');
						resetPwdWin.close();
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
				resetPwdForm.get('resetOprId').setValue(loginForm.get('oprid').getValue());
			}
		}]
	});
});