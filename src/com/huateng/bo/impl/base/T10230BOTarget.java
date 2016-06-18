package com.huateng.bo.impl.base;

import java.util.ArrayList;
import java.util.List;

import com.huateng.bo.base.T10230BO;
import com.huateng.common.Constants;
import com.huateng.common.Operator;
import com.huateng.common.StringUtil;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.dao.iface.base.TblDestGrpInfDAO;
import com.huateng.dao.iface.base.TblDestReleInfDAO;
import com.huateng.po.base.TblDestGrpInf;
import com.huateng.po.base.TblDestReleInf;
import com.huateng.system.util.CommonFunction;

/**
 * Title:通道池维护
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-8-9
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author 
 * 
 * @version 1.0
 */
public class T10230BOTarget implements T10230BO {
	
	private TblDestGrpInfDAO tblDestGrpInfDAO;
	private TblDestReleInfDAO tblDestReleInfDAO;
	private ICommQueryDAO commQueryDAO;	

	@Override
	public String add(String descr,
			List<TblDestReleInf> destReleList, Operator operator) {
		// TODO Auto-generated method stub
		//获取ID
		String idSql ="SELECT min(DEST_ID_GRP_ID+1) from TBL_DEST_GRP_INF WHERE (DEST_ID_GRP_ID+1) NOT IN (SELECT TO_NUMBER(DEST_ID_GRP_ID) FROM TBL_DEST_GRP_INF) ";
		List idList =commQueryDAO.findBySQLQuery(idSql);
		int id=1;
		if(idList !=null&&!idList.isEmpty()&& !StringUtil.isNull(idList.get(0))){
			id = Integer.parseInt(idList.get(0).toString());
		}
		if(id>9999){
			return "通道池数量已满，请删除无用池以后重新新增！";
		}
		TblDestGrpInf tblDestGrpInf = new TblDestGrpInf();
		tblDestGrpInf.setDestIdGrpId(CommonFunction.fillString(""+id, '0', 4, false));
		tblDestGrpInf.setDescr(descr);
		tblDestGrpInf.setSequeuceNo("1");
		tblDestGrpInf.setLastOperIn("A");
		tblDestGrpInf.setRecCrtTs(CommonFunction.getCurrentDateTime());
		tblDestGrpInf.setRecUpdTs(CommonFunction.getCurrentDateTime());
		tblDestGrpInf.setRecSt("1");
		tblDestGrpInf.setUpdUsrId(operator.getOprId());
		
		tblDestGrpInfDAO.save(tblDestGrpInf);
		
		for(int i=0;i<destReleList.size();i++){
			destReleList.get(i).getId().setDestIdGrpId(tblDestGrpInf.getDestIdGrpId());
			destReleList.get(i).setSequeuceNo(""+(i+1));
			tblDestReleInfDAO.save(destReleList.get(i));
		}
		return Constants.SUCCESS_CODE;
	}

	@Override
	public String edit(String destIdGrpId,String descr,
			List<TblDestReleInf> newReleList, Operator operator) {
		// TODO Auto-generated method stub
		TblDestGrpInf tblDestGrpInf =tblDestGrpInfDAO.get(destIdGrpId);
		if(tblDestGrpInf == null){
			return "编辑的通道池不存在，请刷新界面！";
		}
	
		String destReleHql ="from com.huateng.po.base.TblDestReleInf t where t.Id.destIdGrpId='"+tblDestGrpInf.getDestIdGrpId()+"'";
		List<TblDestReleInf> oldReleList =commQueryDAO.findByHQLQuery(destReleHql);
		List<TblDestReleInf> addReleList = new ArrayList<TblDestReleInf>(); //需要插入的结果集
		List<TblDestReleInf> deleReleList = new ArrayList<TblDestReleInf>();//需要删除的结果集
		//过滤需要插入的数据
		int maxSequnce =1;
		for(TblDestReleInf newRele:newReleList){
			boolean exists = false;
			for(TblDestReleInf oldRele:oldReleList){			
				//已经在老的里面存在的 不做更新
				if(oldRele.equals(newRele)){
					exists=true;
					break;
				}
			}
			if(!exists){
				addReleList.add(newRele);
			}
			String sequnceNo=newRele.getSequeuceNo();
			if(sequnceNo!=null&&!"".equals(sequnceNo)){
				if(Integer.parseInt(sequnceNo)>maxSequnce){
					maxSequnce =Integer.parseInt(sequnceNo);
				}
			}
		}
		//过滤需要删除的数据
		for(TblDestReleInf oldRele:oldReleList){		
			boolean exists = false;
			for(TblDestReleInf newRele:newReleList){
				if(oldRele.equals(newRele)){
					exists=true;
					break;
				}
			}
			//如果老的不在新的结果集里面则删除
			if(!exists){
				deleReleList.add(oldRele);
			}
		}
		tblDestGrpInf.setDescr(descr);
		tblDestGrpInf.setUpdUsrId(operator.getOprId());
		tblDestGrpInf.setRecUpdTs(CommonFunction.getCurrentDateTime());
		tblDestGrpInfDAO.update(tblDestGrpInf);
		for(TblDestReleInf deleMccRele:deleReleList){
			tblDestReleInfDAO.delete(deleMccRele);
		}
		for(TblDestReleInf addMccRele:addReleList){
			addMccRele.setSequeuceNo(""+(maxSequnce+1));
			maxSequnce++;
			tblDestReleInfDAO.save(addMccRele);
		}
		return Constants.SUCCESS_CODE;
	}

	@Override
	public String delete(String destIdGrpId, Operator operator) {
		// TODO Auto-generated method stub
		if(tblDestGrpInfDAO.get(destIdGrpId)==null){
			return "要删除的MCC池不存在,请刷新界面后重新操作！";
		}
		String destReleHql ="from com.huateng.po.base.TblDestReleInf t where t.Id.destIdGrpId='"+destIdGrpId+"'";
		List<TblDestReleInf> deleReleList =commQueryDAO.findByHQLQuery(destReleHql);
		for(TblDestReleInf deleRele:deleReleList){
			tblDestReleInfDAO.delete(deleRele);
		}
		tblDestGrpInfDAO.delete(destIdGrpId);
		return Constants.SUCCESS_CODE;
	}

	public TblDestGrpInfDAO getTblDestGrpInfDAO() {
		return tblDestGrpInfDAO;
	}

	public void setTblDestGrpInfDAO(TblDestGrpInfDAO tblDestGrpInfDAO) {
		this.tblDestGrpInfDAO = tblDestGrpInfDAO;
	}

	public TblDestReleInfDAO getTblDestReleInfDAO() {
		return tblDestReleInfDAO;
	}

	public void setTblDestReleInfDAO(TblDestReleInfDAO tblDestReleInfDAO) {
		this.tblDestReleInfDAO = tblDestReleInfDAO;
	}

	public ICommQueryDAO getCommQueryDAO() {
		return commQueryDAO;
	}

	public void setCommQueryDAO(ICommQueryDAO commQueryDAO) {
		this.commQueryDAO = commQueryDAO;
	}
	
}
