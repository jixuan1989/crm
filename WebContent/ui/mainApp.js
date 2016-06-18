Ext.Loader.setConfig({
	enabled : true,
	paths:{zft:"ui"}
});
//卡类型
var cardTypeStore = Ext.create('zft.store.system.CardTypeStore');
SelectOptionsDWR.getComboDataWithParameter('CODE_LIBRARY','CardType', function(ret) {
	cardTypeStore.loadData(Ext.decode(ret));
});
//机构信息
var brhStore = Ext.create('zft.store.system.BrhStore');
SelectOptionsDWR.getComboData('OPR_BELOW_BRH', function(ret) {
	brhStore.loadData(eval(ret));
});
//发卡行
var cardBank = Ext.create('zft.store.system.CardBankStore');

//上级营业网点编号(机构)
var suBrhStore =Ext.create('zft.store.system.SuBrhStore');
SelectOptionsDWR.getComboData('BRH_ID',function(ret){
	suBrhStore.loadData(Ext.decode(ret));
});
//权限级别
var roleLevelStore = Ext.create('zft.store.system.RoleLevelStore');

// 权限级别
SelectOptionsDWR.getComboData('BRH_LVL',function(ret){
	roleLevelStore.loadData(Ext.decode(ret));
});
//角色信息
var roleForLook = new Ext.data.Store({
	fields : [ 'name', 'value' ],
	autoLoad : true
});
SelectOptionsDWR.getComboData('ROLE_FOR_LOOK',
		function(ret) {
			roleForLook.loadData(eval(ret));
	});
var application = new Ext.app.Application({
	name: 'zft',
	appFolder: 'ui',
	
	launch: function() {
		Ext.create("Ext.container.Viewport", {
			layout : 'border',
			items : [ {
				xtype : 'center',
				region : 'center'
			}, {
				xtype : 'mainTree',
				region : 'west',
				width : 270,
				split : true,
				collapsible : true
			}]
		});
	},
	controllers: ['system.MainController']
});