package com.huateng.bo.impl.base;

import com.huateng.bo.base.T10233BO;
import com.huateng.common.Constants;
import com.huateng.dao.iface.base.TblKqTermInfDAO;
import com.huateng.dao.iface.base.TblKqTermKeyDAO;
import com.huateng.po.base.TblKqTermInf;
import com.huateng.po.base.TblKqTermInfPK;
import com.huateng.po.base.TblKqTermKey;


/**
 * Title:快钱终端管理
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-3-11
 * 
 * 
 * @author 
 * 
 * @version 1.0
 */
public class T10233BOTarget implements T10233BO {
	
	private TblKqTermInfDAO tblKqTermInfDAO;
	private TblKqTermKeyDAO tblKqTermKeyDAO;
	
	public String add(TblKqTermInf tblKqTermInf,TblKqTermKey tblKqTermKey) {
		if(tblKqTermInfDAO.get(tblKqTermInf.getId())!=null){
			return "输入的商户号终端号已经存在!";
		}
		tblKqTermInfDAO.save(tblKqTermInf);
		tblKqTermKeyDAO.save(tblKqTermKey);
		return Constants.SUCCESS_CODE;
	}

	public String delete(TblKqTermInfPK tblKqTermInfPK) {
		if(tblKqTermInfDAO.get(tblKqTermInfPK)==null){
			return "删除的终端已经不存在,请刷新页面!";
		}
		tblKqTermInfDAO.delete(tblKqTermInfPK);
		tblKqTermKeyDAO.delete(tblKqTermInfPK);
		return Constants.SUCCESS_CODE;
	}

	public String update(TblKqTermInf tblKqTermInf) {
		TblKqTermInf oldTerm = tblKqTermInfDAO.get(tblKqTermInf.getId());
		if(oldTerm==null){
			return "更新的终端已经不存在,请刷新页面!";
		}
		oldTerm.setUsageFlag(tblKqTermInf.getUsageFlag());
		oldTerm.setRecUpdOpr(tblKqTermInf.getRecUpdOpr());
		oldTerm.setRecUpdTime(tblKqTermInf.getRecUpdTime());
		tblKqTermInfDAO.update(oldTerm);
		return Constants.SUCCESS_CODE;
	}

	public TblKqTermInfDAO getTblKqTermInfDAO() {
		return tblKqTermInfDAO;
	}

	public void setTblKqTermInfDAO(TblKqTermInfDAO tblKqTermInfDAO) {
		this.tblKqTermInfDAO = tblKqTermInfDAO;
	}

	public TblKqTermKeyDAO getTblKqTermKeyDAO() {
		return tblKqTermKeyDAO;
	}

	public void setTblKqTermKeyDAO(TblKqTermKeyDAO tblKqTermKeyDAO) {
		this.tblKqTermKeyDAO = tblKqTermKeyDAO;
	}
	
}
