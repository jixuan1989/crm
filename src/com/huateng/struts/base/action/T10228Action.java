 
package com.huateng.struts.base.action;

 
import java.util.HashMap;

import com.huateng.bo.base.T10228BO;
import com.huateng.struts.system.action.BaseAction;
import com.huateng.system.util.ContextUtil;

/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2014-5-5
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author ning.tan
 * 
 * @version 1.0
 */
public class T10228Action extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private T10228BO t10228BO = (T10228BO) ContextUtil.getBean("T10228BO");
	@Override
	protected String subExecute() throws Exception {
		//拆分
		if("split".equals(method)) {
			HashMap<String,String> params = new HashMap<String,String> ();
			params.put("salTpGrp", salTpGrp);
			params.put("descr", descr);
			params.put("newSalTpGrp", newSalTpGrp);
			params.put("newDescr", newDescr);
			params.put("minTpGrp", minTpGrp);
			params.put("newMcc", newMcc);
			rspCode = t10228BO.split(params,operator);
		} else if("group".equals(method)) { //合并
			HashMap<String,String> params = new HashMap<String,String> ();
			params.put("salTpGrp", salTpGrp);
			params.put("salTpGrp2", salTpGrp2);
			params.put("newDescr", newDescr);
			params.put("minTpGrp", minTpGrp);
			rspCode = t10228BO.group(params,operator);
		}
		return rspCode;
	}	
	private String salTpGrp;
	private String descr;
	private String salTpGrp2;
	private String descr2;
	private String newSalTpGrp;
	private String newDescr;
	private String minTpGrp;
	private String newMcc;
	
	public String getSalTpGrp() {
		return salTpGrp;
	}
	public void setSalTpGrp(String salTpGrp) {
		this.salTpGrp = salTpGrp;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getNewDescr() {
		return newDescr;
	}
	public void setNewDescr(String newDescr) {
		this.newDescr = newDescr;
	}
	public String getMinTpGrp() {
		return minTpGrp;
	}
	public void setMinTpGrp(String minTpGrp) {
		this.minTpGrp = minTpGrp;
	}
	public String getNewMcc() {
		return newMcc;
	}
	public void setNewMcc(String newMcc) {
		this.newMcc = newMcc;
	}
	public String getNewSalTpGrp() {
		return newSalTpGrp;
	}
	public void setNewSalTpGrp(String newSalTpGrp) {
		this.newSalTpGrp = newSalTpGrp;
	}
	public String getSalTpGrp2() {
		return salTpGrp2;
	}
	public void setSalTpGrp2(String salTpGrp2) {
		this.salTpGrp2 = salTpGrp2;
	}
	public String getDescr2() {
		return descr2;
	}
	public void setDescr2(String descr2) {
		this.descr2 = descr2;
	}
	
}
