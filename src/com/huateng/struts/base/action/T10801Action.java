package com.huateng.struts.base.action;

import com.huateng.common.Constants;
import com.huateng.struts.system.action.BaseAction;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.SocketClient;
import com.huateng.system.util.SysParamUtil;

/**
 * 银联商户签到管理
 * @author sr
 *
 */
public class T10801Action extends BaseAction {
	private String method;
	private String oprId;
	
	public static String IP = SysParamUtil.getParam("BRHQD_IP");
	public static Integer PORT = Integer.parseInt(SysParamUtil.getParam("BRHQD_PORT"));
	
	@Override
	protected String subExecute(){
		String zftBrhId = (String) CommonFunction.getCommQueryDAO().
				findBySQLQuery("select KEY from cst_sys_param t where t.owner='ZftBrhId' ").get(0);
		try {
			if("all".equals(method)) {
				SocketClient client = new SocketClient(IP, PORT);
				String msg = "";
				if("A001".equals(oprId)){
					msg = "0020611100108"+zftBrhId+"   ";
				} else if("A002".equals(oprId)){
					msg = "0020612100208"+zftBrhId+"   ";
				} else if("A003".equals(oprId)){
					msg = "0020613130108"+zftBrhId+"   ";
				} else if("A004".equals(oprId)){
					msg = "0020614100208"+zftBrhId+"   ";
				} else if("A005".equals(oprId)){
					msg = "0020614100108"+zftBrhId+"   ";
				}
				client.writeMsg(msg);
				String retMsg = client.readMsg(4);
				if("00".equals(retMsg.substring(4))){
					return Constants.SUCCESS_CODE_CUSTOMIZE+"成功";
				} else {
					return "失败";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log("操作员编号：" + operator.getOprId()+ "，对机构的维护操作" + getMethod() + "失败，失败原因为："+e.getMessage());
		}
		return "失败";
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public void setOprId(String oprId) {
		this.oprId = oprId;
	}
	
	
}
