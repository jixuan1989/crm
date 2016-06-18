Ext.define('zft.model.system.MenuTreeModel', {
        extend: 'Ext.data.Model',
        fields: [
                 'id','text','cls','handler','xtype','iconCls','parent_id','leaf','url','roleFlag'
             ],
        idProperty: 'id'
    });