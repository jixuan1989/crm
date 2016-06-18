package com.huateng.struts.base.action;

import java.util.ArrayList;
import java.util.List;

import com.huateng.bo.base.T10243BO;
import com.huateng.po.base.TblDestReleInfN;
import com.huateng.po.base.TblDestReleInfPK;
import com.huateng.struts.system.action.BaseAction;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.ContextUtil;
import com.huateng.system.util.JSONBean;

/**
 * Title:新通道池维护
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2011-06-16
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author Ning.tan
 * 
 * @version 1.0
 */
@SuppressWarnings("serial")
public class T10243Action extends BaseAction {
	
	private T10243BO t10243BO = (T10243BO) ContextUtil.getBean("T10243BO");


	@Override
	protected String subExecute() throws Exception {
		//新增
		if("add".equals(method)) {
			List<TblDestReleInfN> releList =convertJson();
			if(CommonFunction.checkSamList(releList)){
				return "通道重复，请删除后提交！";
			}
			rspCode = t10243BO.add(descr, releList, operator);
		} else if("edit".equals(method)) { //修改	
			List<TblDestReleInfN> releList =convertJson();	
			if(CommonFunction.checkSamList(releList)){
				return "通道重复，请删除后提交！";
			}
			rspCode = t10243BO.edit(destIdGrpId,descr, releList, operator);
		}else if("delete".equals(method)){
			rspCode = t10243BO.delete(destIdGrpId, operator);
		}
		return rspCode;
	}
	
	public List<TblDestReleInfN> convertJson(){
		List<TblDestReleInfN> releList = new ArrayList<TblDestReleInfN>();
		JSONBean jsonBean = new JSONBean();
		jsonBean.parseJSONArrayData(data);
		int len = jsonBean.getArray().size();

		for(int i = 0; i < len; i++) {
			jsonBean.setObject(jsonBean.getJSONDataAt(i));
			
			//destId
			String destId = jsonBean.getJSONDataAt(i).getString("destId");
			//顺序号
			String sequeuceNo = jsonBean.getJSONDataAt(i).getString("sequeuceNo");
			//更新人
			String updUsrId = jsonBean.getJSONDataAt(i).getString("updUsrId");
			//更新时间
			String recUpdTs = jsonBean.getJSONDataAt(i).getString("recUpdTs");		
			
			
			TblDestReleInfN tblDestReleInf = new TblDestReleInfN();
			TblDestReleInfPK key = new TblDestReleInfPK();
			key.setDestIdGrpId(destIdGrpId);
			key.setDestId(destId);
			tblDestReleInf.setId(key);
			tblDestReleInf.setSequeuceNo(sequeuceNo);
			tblDestReleInf.setLastOperIn("A");
			tblDestReleInf.setUpdUsrId(updUsrId);
			tblDestReleInf.setRecUpdTs(recUpdTs);
			tblDestReleInf.setRecCrtTs(recUpdTs);			
			releList.add(tblDestReleInf);
		}
		return releList;
	}
	

	//池编号
	private String destIdGrpId;
	//池名称
	private String descr;
	
	private String data;


	public String getDestIdGrpId() {
		return destIdGrpId;
	}

	public void setDestIdGrpId(String destIdGrpId) {
		this.destIdGrpId = destIdGrpId;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


	
}
