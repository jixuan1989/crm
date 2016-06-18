Ext.define('zft.view.system.Upload', {
	extend : 'Ext.window.Window',
	alias : 'widget.upload',
	modal: true,
	config : {
		filePostName : 'xlsFile',
		file_types : '*.xls',
		file_types_description : '微软Excel文件(1997-2003)',
		upload_url : 'T40201Action.asp?method=uploadFile',
		post_params : {
			txnId : '40201',
			subTxnId : '01'
		}
	},
	requires : [ 'Ext.window.Window', 'Ext.form.Panel','zft.view.system.UploadPanel' ],
	initComponent : function() {
		var me = this;
		var uploadpanel = Ext.create('zft.view.system.UploadPanel', {
			filePostName : me.filePostName,
			upload_url : me.upload_url,
			file_types_description : me.file_types_description,
			file_types : me.file_types,
			post_params : me.post_params
		});
		
		Ext.apply(this, {
			title : '文件批量上传窗口',
			layout : 'fit',
			width : 550,
			autoHeight : true,
			autoShow : true,
			buttonAlign : 'center',
			resizable : false,
			closable : true,
			listeners : {
				beforeclose : function(){
					uploadpanel.getStore().removeAll();
				}
			},
			items : [ uploadpanel ]
		});
		this.callParent(arguments);
	}
});