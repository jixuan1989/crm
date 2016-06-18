Ext.onReady(function(){
	//Ext.MessageBox.alert("系统提示","系统超时，请点击安全退出，重新登录");
	
	Ext.Msg.show({
		title:'系统提示',
		msg: '会话已经超时或您无权限操作，请点击右上角安全退出，重新登录',
	//	buttons: Ext.Msg.OK,
	//	fn: doSave,
		modal: true,
		width: 420,
		autoHeight:true,
		resizable : false,
		closable : false,
		modal: true,
		icon: Ext.MessageBox.WARNING
		});
		
	function doSave(button,text){
		if(button=="ok"){
			window.location.href = 'logout.asp';
		}
	}
});