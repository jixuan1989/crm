/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-7-17       first release
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
package com.huateng.struts.base.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.huateng.bo.base.T10201BO;
import com.huateng.common.Constants;
import com.huateng.po.TblCityCode;
import com.huateng.struts.system.action.BaseAction;
import com.huateng.system.util.BeanUtils;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.ContextUtil;

/**
 * Title:地区码维护
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-7-17
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class T10201Action extends BaseAction {
	
	
	T10201BO t10201BO = (T10201BO) ContextUtil.getBean("T10201BO");
	
	/* (non-Javadoc)
	 * @see com.huateng.struts.system.action.BaseAction#subExecute()
	 */
	@Override
	protected String subExecute() throws Exception {
		
		if("add".equals(method)) {
			log("新增地区码信息。操作员编号：" + operator.getOprId());
			rspCode = add();
		} else if("delete".equals(method)) {
			log("删除地区码信息。操作员编号：" + operator.getOprId());
			rspCode = delete();
		} else if("update".equals(method)) {
			log("同步地区码信息。操作员编号：" + operator.getOprId());
			rspCode = update();
		}
		
		return rspCode;
	}
	
	/**
	 * add city code
	 * @return
	 */
	private String add() {
		
		if(t10201BO.get(cityName) != null) {
			return "您所添加的地区码已经被使用";
		}
		
//		TblCityCode tblCityCode = new TblCityCode(cupCityCode,cityName);
		TblCityCode TblCityCode = getCstCityCode();
		
		t10201BO.add(TblCityCode);
		
		return Constants.SUCCESS_CODE;
	}
	
	/**
	 * delete city code
	 * @return
	 */
	private String delete() {
		
		if(t10201BO.get(cupCityCode) == null) {
			return "没有找到要删除的地区码信息";
		}
		
		t10201BO.delete(cupCityCode);
		
		return Constants.SUCCESS_CODE;
	}
	
	/**
	 * update city code
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private String update() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		jsonBean.parseJSONArrayData(getCityCodeList());
		
		int len = jsonBean.getArray().size();
		
		TblCityCode tblCityCode = null;
		
		List<TblCityCode> cityCodeInfoList = new ArrayList<TblCityCode>(len);
		
		for(int i = 0; i < len; i++) {			
			tblCityCode = new TblCityCode();			
			jsonBean.setObject(jsonBean.getJSONDataAt(i));			
			BeanUtils.setObjectWithPropertiesValue(tblCityCode, jsonBean, false);
			TblCityCode tblCityCodeOld = t10201BO.get(tblCityCode.getCupCityCode());			
			String sysTime = CommonFunction.getCurrentDate()+CommonFunction.getCurrentTime();
			String INIT_OPR_ID = "".equals(operator.getOprId()) ? "-" : operator.getOprId().trim();		
			tblCityCode.setCityFlag("0");
			tblCityCode.setMchtAddr("-");
			tblCityCode.setInitOprId(tblCityCodeOld.getInitOprId());
			tblCityCode.setModiOprId(INIT_OPR_ID);
			tblCityCode.setInitTime(tblCityCodeOld.getInitTime());
			tblCityCode.setModiTime(sysTime);
			
			cityCodeInfoList.add(tblCityCode);
		}
		
		t10201BO.update(cityCodeInfoList);
		
		return Constants.SUCCESS_CODE;
	}
	
	private String cupCityCode;
	
	private String mchtCityCode;
	
	private String cityName;
	
	private String cityCodeList;	

	
	/**
	 * @return the cupCityCode
	 */
	public String getCupCityCode() {
		return cupCityCode;
	}

	/**
	 * @param cupCityCode the cupCityCode to set
	 */
	public void setCupCityCode(String cupCityCode) {
		this.cupCityCode = cupCityCode;
	}

	/**
	 * @return the mchtCityCode
	 */
	public String getMchtCityCode() {
		return mchtCityCode;
	}

	/**
	 * @param mchtCityCode the mchtCityCode to set
	 */
	public void setMchtCityCode(String mchtCityCode) {
		this.mchtCityCode = mchtCityCode;
	}

	public T10201BO getT10201BO() {
		return t10201BO;
	}

	public void setT10201BO(T10201BO t10201bo) {
		t10201BO = t10201bo;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the cityCodeList
	 */
	public String getCityCodeList() {
		return cityCodeList;
	}

	/**
	 * @param cityCodeList the cityCodeList to set
	 */
	public void setCityCodeList(String cityCodeList) {
		this.cityCodeList = cityCodeList;
	}
	
	
	private TblCityCode getCstCityCode(){
		String sysTime = CommonFunction.getCurrentDate()+CommonFunction.getCurrentTime();
		TblCityCode cstCityCode = new TblCityCode();
		String CITY_CODE_NEW = "".equals(cupCityCode) ? "-" : cupCityCode.trim();
		String CITY_CODE_OLD = "".equals(mchtCityCode) ? "-" : mchtCityCode.trim();		
		String CITY_NAME = "".equals(cityName) ? "-" : cityName.trim();
		String INIT_OPR_ID = "".equals(operator.getOprId()) ? "-" : operator.getOprId().trim();		
		cstCityCode.setCupCityCode(CITY_CODE_NEW);
		cstCityCode.setMchtCityCode(CITY_CODE_OLD);
		cstCityCode.setCityFlag("0");
		cstCityCode.setMchtAddr("-");
		cstCityCode.setCityName(CITY_NAME);
		cstCityCode.setInitOprId(INIT_OPR_ID);
		cstCityCode.setModiOprId(INIT_OPR_ID);
		cstCityCode.setInitTime(sysTime);
		cstCityCode.setModiTime(sysTime);
		return cstCityCode;
	}
	
}
