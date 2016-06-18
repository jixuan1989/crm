//工具栏对象
var toolBarStr;
//菜单树对象
var menuTree;
//var initMask = new Ext.LoadMask(Ext.getBody(),{});
//父工具栏调用方法
function changeTree(button) {
	Ext.require(button,function(){
        var controller = application.getController('zft.controller.system.TreeController');
        controller.init(application,button);
    },self);
}

//子工具栏调用方法
function changeTxn(button) {
	var arr = button.url.split('.');
	var role = button.roleFlag;
	var con = application.getController('zft.controller.system.MainController');
	var center = con.getCenter();
	var conStr = 'zft.controller.' + button.url + 'Controller';
	var keys = application.controllers.keys + ':';
	Ext.require(conStr,function(){
		if(-1 == keys.indexOf(conStr)){
			controller = application.getController(conStr);
			controller.init();
		}
		center.removeAll(true);
		var mainStr = 'zft.view.' + button.url + '.' + arr[1] + 'Main';
		var panel = Ext.create(mainStr);
		var add = Ext.getCmp('add');//增加
		var upload = Ext.getCmp('upload');//上传
		var del = Ext.getCmp('delete');//删除
		var reload = Ext.getCmp('reload');//保存
		var edit = Ext.getCmp('edit');//修改
		var reset = Ext.getCmp('reset');
		if(-1 != role.indexOf("2")){
			if(add){
				add.hidden = false;
			}
			if(upload){
				upload.hidden = false;
			}
		};
		if(-1 != role.indexOf("3")){
			if(del){
				del.hidden = false;
			}
		};
		if(-1 != role.indexOf("4")){
			if(edit){
				edit.hidden = false;
			}
			if(reload){
				reload.hidden = false;
			}
			if(reset){
				reset.hidden = false;
			}
		};
		center.add(panel);
		center.setActive(true, panel);
    },self);
}
//隐藏加载图层
//function hideToolInitMask() {
//	initMask.hide();
//}