/**
 * 发卡行
 */
Ext.define('zft.store.system.CardBankStore', {
	extend : 'Ext.data.Store',
	fields : [ 'valueField', 'displayField' ],
	proxy : {
		type : 'ajax',
		url : 'baseSelect.asp?ID=INS_ID_CD',
		reader : {
			type : 'json',
			root : 'data'
		}
	},
	autoLoad : true
});
