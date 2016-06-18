package com.huateng.bo.impl.base;

import java.util.List;

import com.huateng.common.Constants;
import com.huateng.dao.iface.base.TblOprLogDAO;
import com.huateng.po.TblOprLog;


/**
 * Title:通道信息BO实现
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-3-11
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author 
 * 
 * @version 1.0
 */
public class T10211BOTarget implements com.huateng.bo.base.T10211BO {
	
	private TblOprLogDAO tblOprLogDAO;
	
	/* (non-Javadoc)
	 * @see com.huateng.bo.T10401BO#add(com.huateng.po.TblOprInfo)
	 */
	public String add(TblOprLog tblOprLog) {
		tblOprLogDAO.save(tblOprLog);
		return Constants.SUCCESS_CODE;
	}

	/* (non-Javadoc)
	 * @see com.huateng.bo.T10401BO#delete(java.lang.String)
	 */
	public String delete(String id) {
		tblOprLogDAO.delete(id);
		return Constants.SUCCESS_CODE;
	}

	/* (non-Javadoc)
	 * @see com.huateng.bo.T10401BO#get(java.lang.String)
	 */
	public TblOprLog get(String id) {
		return tblOprLogDAO.get(id);
	}

	/* (non-Javadoc)
	 * @see com.huateng.bo.T10401BO#update(java.util.List)
	 */
	public String update(List<TblOprLog> tblOprLogList) {
		for(TblOprLog tblOprLog : tblOprLogList) {
			update(tblOprLog);
		}
		return Constants.SUCCESS_CODE;
	}

	
	/**
	 * @return the tblOprLogDAO
	 */
	public TblOprLogDAO getTblOprLogDAO() {
		return tblOprLogDAO;
	}

	/**
	 * @param tblOprLogDAO the tblOprLogDAO to set
	 */
	public void setTblOprLogDAO(TblOprLogDAO tblOprLogDAO) {
		this.tblOprLogDAO = tblOprLogDAO;
	}

	/* (non-Javadoc)
	 * @see com.huateng.bo.T10401BO#update(com.huateng.po.TblOprInfo)
	 */
	public String update(TblOprLog tblOprLog) {
		this.tblOprLogDAO.update(tblOprLog);
		return Constants.SUCCESS_CODE;
	}
	
}
