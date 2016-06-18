/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-8-31       first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2010 Huateng Software, Inc. All rights reserved.
 *
 *       This software is the confidential and proprietary information of
 *       Shanghai HUATENG Software Co., Ltd. ("Confidential Information").
 *       You shall not disclose such Confidential Information and shall use it
 *       only in accordance with the terms of the license agreement you entered
 *       into with Huateng.
 *
 * Warning:
 * =============================================================================
 *
 */
package com.huateng.struts.system.action;

import javax.servlet.RequestDispatcher;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.huateng.common.Constants;
import com.huateng.common.Operator;
import com.huateng.dao.iface.base.TblOprInfoDAO;
import com.huateng.po.TblOprInfo;
import com.huateng.system.util.ContextUtil;

/**
 * Title: 操作员退出
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-8-31
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LogoutAction extends BaseAction {

	/* (non-Javadoc)
	 * @see com.huateng.struts.system.action.BaseAction#subExecute()
	 */
	@Override
	protected String subExecute() throws Exception {
        //管理员退出，将冻结状态修改为正常
        //TODO 该处为应急处理，逻辑未做严格处理，后期应该优化
         setOperStatus();

		// 删除操作员信息
		removeSessionAttribute(Constants.OPERATOR_INFO);
		// 删除菜单树信息
		removeSessionAttribute(Constants.TREE_MENU_MAP);
		// 删除工具栏信息
		removeSessionAttribute(Constants.TOOL_BAR_STR);
		
		return SUCCESS;
	}

    /**
     * 新增逻辑
     * 在用户登录后，将用户标示值为冻结，所以该处在用户退出时，应将用户解冻
     */
    private void setOperStatus() {
       TblOprInfoDAO tblOprInfoDAO = (TblOprInfoDAO) ContextUtil.getBean("OprInfoDAO");
       Operator operator = (Operator)super.getSessionAttribute(Constants.OPERATOR_INFO);
       if(operator != null && StringUtils.isNotBlank(operator.getOprId())) {
           TblOprInfo tblOprInfo = tblOprInfoDAO.get(operator.getOprId());
           if(tblOprInfo != null)
               tblOprInfo.setOprSta("0");

           tblOprInfoDAO.update(tblOprInfo);
       }
    }

}
