/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-5-21       first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2008 Huateng Software, Inc. All rights reserved.
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

import org.apache.struts2.ServletActionContext;

import com.huateng.common.SysCodeConstants;
import com.huateng.system.util.SysCodeUtil;

/**
 * Title:系统登录
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-5-21
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class MsgValidateAction extends BaseAction {

	private static final long serialVersionUID = -915297506148396706L;

	/**验证码*/
	private String random;
	

	/* (non-Javadoc)
	 * @see com.huateng.struts.system.action.BaseAction#subExecute()
	 */
	@Override
	protected String subExecute() throws Exception {
		
		String msgVal = ServletActionContext.getRequest().getSession().getAttribute(SysCodeConstants.MSG_VALIDATE).toString();
		if(random.equals(msgVal)){
			writeSuccessMsg("验证成功");
			return SUCCESS;
		}else{
			writeAlertMsg("短信验证失败", SysCodeUtil.getSysCode(SysCodeConstants.MSG_VALIDATE));
			return SUCCESS;
		}
	}
	
	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

}
