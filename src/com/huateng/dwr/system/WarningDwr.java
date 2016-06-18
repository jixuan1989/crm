package com.huateng.dwr.system;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huateng.common.Constants;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.dao.iface.base.TblWarningParamDAO;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.ContextUtil;
import com.huateng.system.util.JSONBean;

public class WarningDwr {
	
	private ICommQueryDAO commQueryDAO = (ICommQueryDAO)ContextUtil.getBean("CommQueryDAO");
	private TblWarningParamDAO tblWarningParamDAO = (TblWarningParamDAO) ContextUtil.getBean("tblWarningParamDAO");

	
	//实时交易预警
	public String warningTask(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		LinkedList<Object> jsonDataList = new LinkedList<Object>();
		String jsonData = "[{'value':'1','name':'没有找到可选内容'}]";
		String interval = "";
		String nowTime = CommonFunction.getCurrentDateTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");

		int hour = Integer.parseInt(nowTime.substring(8, 10));
		if(hour < 8){
			interval = tblWarningParamDAO.get(Constants.NIGHT_INTERVAL).getParamValue();
		}else{
			interval = tblWarningParamDAO.get(Constants.DAY_INTERVAL).getParamValue();
		}
        Date date=format.parse(format.format(new Date()));
        date.setMinutes(date.getMinutes()-Integer.parseInt(interval));
        String beginTime = format.format(date);
        String hm = nowTime.substring(8,12);
        String sql = "select getcolname('productId',c.product_id), count(a.inst_date) from TBL_WARNING_RULE c left join tbl_mcht_base_inf b on c.product_id = b.product_id  and b.conn_type != 'X' "
				+ "left join tbl_n_txn a on A.CARD_ACCP_ID=B.MCHT_NO AND a.inst_date>= '" + beginTime + "' and a.inst_date<= '" + nowTime
				+ "' where c.start_time<= '" + hm + "' and c.end_time>='" + hm + "' and c.status='0' group by c.product_id having count(a.inst_date) = 0";
		List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
		Iterator<Object[]> iterator = dataList.iterator();
		String value = "";
		while (iterator.hasNext()) {
			Object[] obj = iterator.next();
            value = value + obj[0].toString().trim() + "|";
            
		}
		if(dataList.size() > 0 ){
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("value", value);
            map.put("name", value + "类商户规定时间内无交易");
			jsonDataList.add(map);
			jsonData = JSONBean.genListToJSON(jsonDataList);
		}
		return jsonData;
	}
	//实时交易失败率预警
	public String failTask(HttpServletRequest request,HttpServletResponse response){
		LinkedList<Object> jsonDataList = new LinkedList<Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String jsonData = "[{'value':'1','name':'没有找到可选内容'}]";
		String interval = tblWarningParamDAO.get(Constants.TRANS_INTERVAL).getParamValue();
		String failPercent = tblWarningParamDAO.get(Constants.FAIL_PERCENT).getParamValue();
		Calendar end = Calendar.getInstance();
		Calendar begin = Calendar.getInstance();
		begin.add(Calendar.MINUTE, - Integer.parseInt(interval));
		Date endDate = new Date(end.getTimeInMillis());
		Date beginDate = new Date(begin.getTimeInMillis());
		String sql = "select distinct tmp.flag,round(count(*)over(partition by tmp.flag )/count(*)over()*100,2) bfb from "
				+ "( select a.*, case when a.resp_code='00' then '00' else '01' end flag from TBL_N_TXN a where a.inst_date>= '" + format.format(beginDate) + "' and a.inst_date<= '" + format.format(endDate) + "') tmp order by tmp.flag desc";
		List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
		Iterator<Object[]> iterator = dataList.iterator();
		String value = "";
		if(dataList.size() > 0 ){
			Map<String, String> map = new LinkedHashMap<String, String>();
			for(int i = 0;i<dataList.size();i++){
				if("01".equals(dataList.get(i)[0].toString())){
					String per = dataList.get(i)[1].toString();
					if(new BigDecimal(per).compareTo(new BigDecimal(failPercent)) == 1){
						map.put("value", "2");
			            map.put("name", "单位时间内交易失败率大于百分之" + failPercent);
						jsonDataList.add(map);
						jsonData = JSONBean.genListToJSON(jsonDataList);
					}
				}
			}
			
		}
		return jsonData;
	}
}
