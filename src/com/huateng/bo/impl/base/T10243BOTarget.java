package com.huateng.bo.impl.base;

import java.util.ArrayList;
import java.util.List;

import com.huateng.bo.base.T10243BO;
import com.huateng.common.Constants;
import com.huateng.common.Operator;
import com.huateng.common.StringUtil;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.dao.iface.base.TblDestGrpInfNDAO;
import com.huateng.dao.iface.base.TblDestReleInfNDAO;
import com.huateng.po.base.TblDestGrpInfN;
import com.huateng.po.base.TblDestReleInfN;
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
public class T10243BOTarget implements T10243BO {
	
	private TblDestGrpInfNDAO tblDestGrpInfNDAO;
	private TblDestReleInfNDAO tblDestReleInfNDAO;
	private ICommQueryDAO commQueryDAO;	

	@Override
	public String add(String descr,
			List<TblDestReleInfN> destReleList, Operator operator) {
		// TODO Auto-generated method stub
		//获取ID
		String idSql ="SELECT min(DEST_ID_GRP_ID+1) from TBL_DEST_GRP_INF_N WHERE (DEST_ID_GRP_ID+1) NOT IN (SELECT TO_NUMBER(DEST_ID_GRP_ID) FROM TBL_DEST_GRP_INF_N) ";
		List idList =commQueryDAO.findBySQLQuery(idSql);
		int id=1;
		if(idList !=null&&!idList.isEmpty()&& !StringUtil.isNull(idList.get(0))){
			id = Integer.parseInt(idList.get(0).toString());
		}
		if(id>9999){
			return "通道池数量已满，请删除无用池以后重新新增！";
		}
		TblDestGrpInfN tblDestGrpInf = new TblDestGrpInfN();
		tblDestGrpInf.setDestIdGrpId(CommonFunction.fillString(""+id, '0', 4, false));
		tblDestGrpInf.setDescr(descr);
		tblDestGrpInf.setSequeuceNo("1");
		tblDestGrpInf.setLastOperIn("A");
		tblDestGrpInf.setRecCrtTs(CommonFunction.getCurrentDateTime());
		tblDestGrpInf.setRecUpdTs(CommonFunction.getCurrentDateTime());
		tblDestGrpInf.setRecSt("1");
		tblDestGrpInf.setUpdUsrId(operator.getOprId());
		
		tblDestGrpInfNDAO.save(tblDestGrpInf);
		
		for(int i=0;i<destReleList.size();i++){
			destReleList.get(i).getId().setDestIdGrpId(tblDestGrpInf.getDestIdGrpId());
			destReleList.get(i).setSequeuceNo(""+(i+1));
			tblDestReleInfNDAO.save(destReleList.get(i));
		}
		return Constants.SUCCESS_CODE;
	}

	@Override
	public String edit(String destIdGrpId,String descr,
			List<TblDestReleInfN> newReleList, Operator operator) {
		// TODO Auto-generated method stub
		TblDestGrpInfN tblDestGrpInf =tblDestGrpInfNDAO.get(destIdGrpId);
		if(tblDestGrpInf == null){
			return "编辑的通道池不存在，请刷新界面！";
		}
	
		String destReleHql ="from com.huateng.po.base.TblDestReleInfN t where t.Id.destIdGrpId='"+tblDestGrpInf.getDestIdGrpId()+"'";
		List<TblDestReleInfN> oldReleList =commQueryDAO.findByHQLQuery(destReleHql);
		List<TblDestReleInfN> addReleList = new ArrayList<TblDestReleInfN>(); //需要插入的结果集
		List<TblDestReleInfN> deleReleList = new ArrayList<TblDestReleInfN>();//需要删除的结果集
		//过滤需要插入的数据
		int maxSequnce =0;
		for(TblDestReleInfN newRele:newReleList){
			boolean exists = false;
			for(TblDestReleInfN oldRele:oldReleList){			
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
		for(TblDestReleInfN oldRele:oldReleList){		
			boolean exists = false;
			for(TblDestReleInfN newRele:newReleList){
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
		tblDestGrpInfNDAO.update(tblDestGrpInf);
		for(TblDestReleInfN deleMccRele:deleReleList){
			tblDestReleInfNDAO.delete(deleMccRele);
		}
		for(TblDestReleInfN addMccRele:addReleList){
			addMccRele.setSequeuceNo(""+(maxSequnce+1));
			maxSequnce++;
			tblDestReleInfNDAO.save(addMccRele);
		}
		return Constants.SUCCESS_CODE;
	}

	@Override
	public String delete(String destIdGrpId, Operator operator) {
		// TODO Auto-generated method stub
		if(tblDestGrpInfNDAO.get(destIdGrpId)==null){
			return "要删除的MCC池不存在,请刷新界面后重新操作！";
		}
		String destReleHql ="from com.huateng.po.base.TblDestReleInfN t where t.Id.destIdGrpId='"+destIdGrpId+"'";
		List<TblDestReleInfN> deleReleList =commQueryDAO.findByHQLQuery(destReleHql);
		for(TblDestReleInfN deleRele:deleReleList){
			tblDestReleInfNDAO.delete(deleRele);
		}
		tblDestGrpInfNDAO.delete(destIdGrpId);
		return Constants.SUCCESS_CODE;
	}

	public TblDestGrpInfNDAO getTblDestGrpInfNDAO() {
		return tblDestGrpInfNDAO;
	}

	public void setTblDestGrpInfNDAO(TblDestGrpInfNDAO tblDestGrpInfNDAO) {
		this.tblDestGrpInfNDAO = tblDestGrpInfNDAO;
	}

	public TblDestReleInfNDAO getTblDestReleInfNDAO() {
		return tblDestReleInfNDAO;
	}

	public void setTblDestReleInfNDAO(TblDestReleInfNDAO tblDestReleInfNDAO) {
		this.tblDestReleInfNDAO = tblDestReleInfNDAO;
	}

	public ICommQueryDAO getCommQueryDAO() {
		return commQueryDAO;
	}

	public void setCommQueryDAO(ICommQueryDAO commQueryDAO) {
		this.commQueryDAO = commQueryDAO;
	}
	
}
