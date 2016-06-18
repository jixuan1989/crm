package com.huateng.common.select;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.huateng.common.Operator;
import com.huateng.common.StringUtil;
import com.huateng.common.TxnInfo;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.system.cache.CacheFactory;
import com.huateng.system.cache.OSCache;
import com.huateng.system.util.CommonFunction;
import com.huateng.system.util.ContextUtil;

/**
 * 
 * Title: SelectOption接口方法
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2009-12-27
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class SelectMethod {

	static ICommQueryDAO commQueryDAO = (ICommQueryDAO) ContextUtil
			.getBean("CommQueryDAO");
    private static LinkedHashMap<String, String> amtMap = new LinkedHashMap<String, String>();
	/**
	 * 根据当前操作员级别获得机构级别信息， 只有总行返回
	 * 
	 * @param params
	 * @return
	 */
	public static LinkedHashMap<String, String> getBrhLvlByOprInfo(
			Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();

		Operator operator = (Operator) params[0];

		// 总行
		if ("0".endsWith(operator.getOprBrhLvl())) {
			dataMap.put("0", "总公司");
			dataMap.put("1", "分公司");
			dataMap.put("2", "网点");
			// dataMap.put("3", "网点");
		} else {
			dataMap.put("0", "总公司");
			dataMap.put("1", "分公司");
			dataMap.put("2", "网点");
		}
		return dataMap;
	}

	/**
	 * 根据操作员所在机构返回机构信息
	 * 
	 * @param params
	 * @return
	 */
	public static LinkedHashMap<String, String> getUpBrhIdByOprInfo(
			Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		Operator operator = (Operator) params[0];
		Map<String, String> brhInfoMap = operator.getBrhBelowMap();
		Iterator<String> iterator = brhInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			dataMap.put(key, key + " - " + brhInfoMap.get(key));
		}
		return dataMap;
	}

	/**
	 * 根据上级菜单编号查询菜单信息
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getLevelMenu(Object[] params) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MENU_LEVEL_" + params[1].toString();
		try {
			//cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select func_id,func_name from tbl_func_inf where func_parent_id = ";
					if("8".equals(params[1].toString())){
						sql = sql + "'8' or func_id in('81229','81234') ";
					}else if("802".equals(params[1].toString())){
						sql = sql + "'802' and func_id not in('81229','81234') ";
					}else{
						sql = sql+ params[1].toString();
					}
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				hashMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//putInCache(key, hashMap);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashMap;
	}

	/**
	 * 根据角色编号查找角色菜单信息
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getRoleMenu(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "ROLE_MENU_" + params[1].toString();
		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select value_id,func_name,role_flag from tbl_role_func_map,tbl_func_inf where value_id = func_id and func_id not in ('50905','50906','50907') and key_id = "
					+ params[1].toString();
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim() + "_" + obj[2].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获得当前机构的下属机构
	 * 
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getBrhInfoBelow(Object[] params) {
		Map<String, String> dataMap = new HashMap<String, String>();
		Operator operator = (Operator) params[0];

		dataMap = operator.getBrhBelowMap();

		return dataMap;
	}

	/**
	 * 获得当前机构的下属机构(总分行)
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getBrhInfoBelowBranch(
			Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		Operator operator = (Operator) params[0];
		try {
			dataMap = new LinkedHashMap<String, String>();
			String sql = "select BRH_ID,BRH_ID||'-'||BRH_NAME FROM TBL_BRH_INFO WHERE BRH_LEVEL IN ('0','1') AND trim(BRH_ID) IN "
					+ operator.getBrhBelowId();
			sql += " ORDER BY BRH_LEVEL,BRH_ID";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getBrhInfoBank(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		Operator operator = (Operator) params[0];
		//String key = "BRH_BELOW_BRANCH_" + operator.getOprBrhId();
		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			dataMap = (LinkedHashMap<String, String>) operator
					.getBrhBelowMap();

			Iterator<String> it = dataMap.keySet().iterator();
			StringBuffer sb = new StringBuffer("(");
			while (it.hasNext()) {
				sb.append("'").append(it.next().trim()).append("'")
				.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(")");

			dataMap = new LinkedHashMap<String, String>();
			String sql = "select BRH_ID,BRH_NAME FROM TBL_BRH_INFO WHERE BRH_TYPE=1 AND BRH_LEVEL=2 AND trim(BRH_ID) IN "
					+ sb.toString();
			sql += " ORDER BY BRH_LEVEL,BRH_ID";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获取机构ID-机构名称
	 * 
	 * @param params
	 * @return 2012-2-13下午05:02:38
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getBrhIdBrhName(Object[] params) {
		// Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "BRH_ID_BRH_NAME_";
		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select brh_id,brh_name from TBL_BRH_INFO ";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获取当前机构的下属机构ID-机构名称
	 * 
	 * @param params
	 * @return 2012-2-13下午05:02:38
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getBranchBellow(Object[] params) {
		Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		try {
			String sql = "select brh_id,brh_name from TBL_BRH_INFO where brh_id in "
					+ operator.getBrhBelowId()
					+ " and brh_id!='"
					+ operator.getOprBrhId() + "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获得当前机构的下属机构(-)
	 * 
	 * @param params
	 * @return
	 */
	public static LinkedHashMap<String, String> getBrhInfoBelowShowId(
			Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		Operator operator = (Operator) params[0];

		Iterator<String> it = operator.getBrhBelowMap().keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			dataMap.put(key, key + "-" + operator.getBrhBelowMap().get(key));
		}

		return dataMap;
	}

	/**
	 * 根据机构级别获得角色信息
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getRoleInfoByBrh(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "ROLE_BY_BRH_" + params[1].toString().trim();
		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select role_id,role_name from tbl_role_inf where role_type = "
					+ "(select brh_level from tbl_brh_info where BRH_ID = '"
					+ params[1].toString().trim() + "')";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}

	/**
	 * 根据机构号获取机构下的部门
	 * 
	 * @param params
	 * @return 2012-12-26上午11:03:55
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getStepmentByBrh(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "STEPMENT_BY_BRH_" + params[1].toString().trim();
		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select id,name from TBL_COMPANY_STEPMENT where brh_id='"
					+ params[1].toString().trim() + "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}
	/**
	 * 根据操作员获取操作员所在机构下的部门
	 *  
	 * @param params 参数
	 * @return
	 */
	public static LinkedHashMap<String, String> getStepmentByOpr(Object[] params) {

		Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "STEPMENT_BY_BRH_" + params[1].toString().trim();
		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select id,name from TBL_COMPANY_STEPMENT where brh_id='"
					+ operator.getOprBrhId() + "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}
	/**
	 * 查询部门下的人员
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getStepEmployee(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "STEP_EMPLOYEE_" + params[1].toString().trim();
		String sql = "";
		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			if(params[1] != null && !"".equals(params[1])){
				sql = " select EMPLOYEE_NUM,NAME FROM TBL_COMPANY_EMPLOYEE  WHERE STEPMENT = '"
						+ params[1].toString().trim() + "'";
			}else{
				sql = " select EMPLOYEE_NUM,NAME FROM TBL_COMPANY_EMPLOYEE";
			}

			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}
	/**
	 * 查询人员所属部门
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getEmployeeStep(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "EMPLOYEE_STEP_" + params[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = " select a.id,a.NAME FROM TBL_COMPANY_STEPMENT a  "
					+ " LEFT JOIN TBL_COMPANY_EMPLOYEE b on b.stepment=a.id "
					+ " WHERE b.employee_NUM = '"
					+ params[1].toString().trim() + "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 查询门店下的人员
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作，优化原查询SQL
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getStepShopEmployee(
			Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "STEP_SHOP_EMPLOYEE_" + params[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			// 合并SQL
			String sql = "select EMPLOYEE_NUM,NAME FROM TBL_COMPANY_EMPLOYEE  WHERE STEPMENT = (select ID FROM TBL_COMPANY_STEPMENT  WHERE NAME = '"
					+ params[1].toString().trim() + "')";
			// 获取门店ID
			// String sql2 =
			// " select ID FROM TBL_COMPANY_STEPMENT  WHERE NAME = '"
			// +params[1].toString().trim()+ "'" ;
			// String shopId = commQueryDAO.findCountBySQLQuery(sql2);
			// String sql =
			// " select EMPLOYEE_NUM,NAME FROM TBL_COMPANY_EMPLOYEE  WHERE STEPMENT = '"
			// +shopId+ "'" ;
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 查询省下市
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getAreaDataStore(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "AREA_DATA_STORE_" + params[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			// 获取门店ID
			String sql = " select ID,AREA_NAME FROM TBL_AREA_INFO  WHERE PARENT_ID = '"
					+ params[1].toString().trim() + "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 查询所有人员
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getAllEmployee(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "ALL_EMPLOYEE_";

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = " select EMPLOYEE_NUM,NAME FROM TBL_COMPANY_EMPLOYEE ";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}
	/**
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */

	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getBrhEmployee(Object[] params) {
		Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "BRH_EMPLOYEE_" + operator.getOprBrhId().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = " select EMPLOYEE_NUM,NAME FROM TBL_COMPANY_EMPLOYEE WHERE STEPMENT IN (SELECT ID FROM TBL_COMPANY_STEPMENT WHERE BRH_ID IN "
					+ operator.getBrhBelowId() + " ) ";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 根据机构获得机构下部门
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getEmpByBrh(Object[] params) {
		Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "STEPMENT_" + operator.getOprBrhId().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = " SELECT ID,NAME  FROM TBL_COMPANY_STEPMENT  where brh_id in "
					+ operator.getBrhBelowId() + " order by NAME";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}

			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 根据机构获得机构下部门
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getPositionInf(Object[] params) {
		// Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "POSITIONINFO_";

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			//String sql = " SELECT ID,POSITION_NAME  FROM TBL_POSITION_INF   order by sort_no";
			String sql = "select key,value from CST_SYS_PARAM where owner = 'POSITION_INF'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}

			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 根据商户组别获得商户编号
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getMchntTpByGrp(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MCHNT_TP_" + params[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String val = "";
			String whereSql = " ";
			if(params[1] != null && !"".equals(params[1])){
				val = params[1].toString().trim();
				whereSql = " and mchnt_tp_grp = '"+val+"'";
			}
			String sql = "select mchnt_tp,descr from tbl_inf_mchnt_tp where 1=1 "
					+ whereSql
					+ " order by mchnt_tp";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[0].toString()
						.trim() + "-" + obj[1].toString().trim());
			}

			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 查询商户分店列表
	 * 
	 * @param params
	 * @return 2010-8-17下午03:13:55
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getMchtBranInf(Object[] params) {
		// Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "TBL_MCHT_BRAN_INF_";

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select  a.MCHT_CD,a.BRANCH_CD,a.BRANCH_NM,a.rec_upd_ts from TBL_MCHT_BRAN_INF a order by a.rec_upd_ts";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}

			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 查询商户限额查询
	 * 
	 * @param params
	 * @return 2010-8-17下午03:13:55
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getCstMchtFee(Object[] params) {
		// Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "CST_MCHT_FEE_INF_";

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select a.mcht_cd,a.txn_num, a.card_type, a.channel, a.day_num, a.day_amt, "
					+ "a.day_single, a.mon_num, a.mon_amt from cst_mcht_fee_inf a";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 查询真实表商户编号
	 * 
	 * @param params
	 * @return 2010-8-17下午03:13:55
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getMchntNo(Object[] params) {
		Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MCHNT_NO_" + operator.getOprBrhId();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select MCHT_NO, MCHT_NO ||' - '|| MCHT_NM as MCHT_NM from TBL_MCHT_BASE_INF where ACQ_INST_ID in "
					+ operator.getBrhBelowId() + " and MCHT_STATUS = '0'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}
	/**
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getMchntUploadNo(Object[] params) {
		Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MCHNT_UPLOAD_NO_" + operator.getOprBrhId().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select V_6 as MCHT_NO, V_6 ||' - '|| V_7 as MCHT_NM from TBL_UPLOAD_TMP_INF  where REC_CRT_ORG IN "
					+ operator.getBrhBelowId();
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 风险模型类型
	 * 
	 * @param params
	 * @return 2010-8-17下午03:13:55
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getKind(Object[] params) {

		LinkedHashMap dataMap = new LinkedHashMap();
		dataMap.put("C1", "3日内，同一卡号在同一商户内交易限制");
		dataMap.put("C2", "3日内，同一卡号在同一受理行内交易限制");
		dataMap.put("C3", "3日内，同一卡号交易限制");
		dataMap.put("M1", "同一商户当日某笔授权回应为\"查询发卡方\"后，继续进行同金额同卡号交易");
		dataMap.put("M2", "同一商户当日内发生的授权回应在受控范围内");
		dataMap.put("M3", "同一商户当日同一卡号交易限制");
		dataMap.put("M4", "同一商户当日交易金额限制");
		dataMap.put("M5", "同一商户当日有超过一笔同金额的限制");
		return dataMap;
	}


	/**
	 * 获取厂商型号
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getFactoryType(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "FACTORY_TYPE_" + params[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select TYPE_NO,TYPE_NAME from TBL_FACTORY_TYPE where FACTORY_NO = '"
					+ params[1].toString().trim()
					+ "'"
					+ " order by TYPE_NO";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获取所有型号
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getFactoryTypeNoParam(
			Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();

		try {
			String sql = "select TYPE_NO,TYPE_NAME from TBL_FACTORY_TYPE  order by TYPE_NO";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获取商户下的终端号
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getMchtTermType(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MCHT_TERM_TYPE_" + params[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = " select MCHT_CD ||' - '|| TERM_ID as MCHT_CD,TERM_ID FROM TBL_TERM_INF WHERE MCHT_CD = '"
					+ params[1].toString().trim() + "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获取商户下的终端号取VALUE值
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getMchtTermValue(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();

		try {
			String sql = " select TERM_ID,TERM_ID FROM TBL_TERM_INF WHERE MCHT_CD = '"
					+ params[1].toString().trim() + "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}
	/**
	 * 获取商户下的终端号取VALUE值
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getMchtSerialNoTermValue(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		try {
			String sql = " select SERIAL_NO,TERM_ID FROM TBL_TERM_INF WHERE MCHT_SERIALNO = '"
					+ params[1].toString().trim() + "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	/**
	 * 获取专业化服务商户下的终端号取VALUE值
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getBankMchtSerialNoTermValue(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();

		try {
			String sql = " select SERIAL_NO,TERM_ID FROM TBL_BANK_TERM_INF WHERE MCHT_SERIALNO = '"
					+ params[1].toString().trim() + "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}
	/**
	 * 查询商户下的终端号VALUE值(新建任务)
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getTaskTermValue(Object[] object) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		String[] params = object[1].toString().trim().split("\\|");
		String mchtNo = params[0].toString().trim();
		String taskType = params[1].toString().trim();
		String sql = " select a.TERM_ID,a.TERM_ID FROM TBL_BANK_TERM_INF a WHERE MCHT_SERIALNO = '"
				+ mchtNo + "' ";
		try {
			if("0".equals(taskType)){
				sql = sql +  " and a.SERIAL_NO not in (select b.term_no from TBL_POS_MAPPING b where b.mcht_no = '" + mchtNo + "')";
			}else{
				sql = sql +  " and a.SERIAL_NO in (select b.term_no from TBL_POS_MAPPING b where b.mcht_no = '" + mchtNo + "'  and is_normal ='1')";
			}
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获取商户下的门店取VALUE值
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getMchtShopValue(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MCHT_SHOP_VALUE_" + params[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = " select ID,SHOP_NAME FROM TBL_MCHT_SHOPPING_REAL WHERE MCHT_NO = '"
					+ params[1].toString().trim() + "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获取对应公司下的所有库房
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getHourseType(Object[] params) {
		Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "HOURSE_TYPE_" + operator.getOprBrhId().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "SELECT HOURSE_NO,HOURSE_NAME FROM TBL_HOURSE_MANAGEMENT WHERE HOURSE_TYPE ='1' and branch_Id in "
					+ operator.getBrhBelowId();
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获取所有大库房
	 * 
	 * @param params
	 * @return 2011-6-8下午02:46:17
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getBigHourseType(Object[] params) {
		Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MENU_LEVEL_" + operator.getOprBrhId().trim();

		try {
			String sql = "SELECT HOURSE_NO,HOURSE_NAME FROM TBL_HOURSE_MANAGEMENT WHERE HOURSE_TYPE ='0' and branch_Id in "
					+ operator.getBrhBelowId();
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 获取终端库存编号
	 * 
	 * @param params
	 * @return 2011-6-21下午03:14:37
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	public static LinkedHashMap<String, String> getTermIdId(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "TERMIDID_";

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			StringBuffer sql = new StringBuffer(
					"select term_no,product_cd from tbl_term_management where STATE ='4' ");
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql
					.toString());
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 查询POS交易类型
	 * 
	 * @param params
	 * @return 2010-8-27上午11:21:12
	 */
	public static LinkedHashMap<String, String> getPosTxnNum(Object[] params) {
		return (LinkedHashMap<String, String>) TxnInfo.txnNameMap;
	}

	/**
	 * 查询商户地区代码
	 * 
	 * @param params
	 * @return 2010-8-27上午11:21:12
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	public static LinkedHashMap<String, String> getCityCode(Object[] args) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "CITY_CODE_";

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select MCHT_CITY_CODE,MCHT_CITY_CODE ||' - '|| CITY_NAME AS CITY_NAME From CST_CITY_CODE";
			List<Object[]> dates = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> itor = dates.iterator();
			String docType, descTx;
			while (itor.hasNext()) {
				Object[] obj = itor.next();
				docType = obj[0].toString().trim();
				descTx = obj[1].toString().trim();
				dataMap.put(docType, descTx);
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;

	}

	/**
	 * 查询商户地区代码
	 * 
	 * @param params
	 * @return 2010-8-27上午11:21:12
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	public static LinkedHashMap<String, String> getCupCode(Object[] args) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		String key ="CUP_CODE_";
		try {
			cacheMap = getFromCacheByKey(key);
			if (cacheMap != null) {
				System.out.println("get from cache! the key is " + key);
				return cacheMap;
			} else {
				String sql = "select CUP_CITY_CODE,CUP_CITY_CODE ||' - '|| CITY_NAME AS CITY_NAME From CST_CITY_CODE";
				List<Object[]> dates = commQueryDAO.findBySQLQuery(sql);
				Iterator<Object[]> itor = dates.iterator();
				Object[] obj;
				String docType, descTx;
				while (itor.hasNext()) {
					obj = itor.next();
					docType = obj[0].toString().trim();
					descTx = obj[1].toString().trim();
					dataMap.put(docType, descTx);
				}
				putInCache(key, dataMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;

	}

	/**
	 * 查询机构地区代码
	 * 
	 * @param params
	 * @return 2010-8-27上午11:21:12
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	public static LinkedHashMap<String, String> getCityCodeOld(Object[] args) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "CITY_CODE_OLD_";

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select DISTINCT(MCHT_CITY_CODE),MCHT_CITY_CODE　 From CST_CITY_CODE";
			List<Object[]> dates = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> itor = dates.iterator();
			Object[] obj;
			String docType, descTx;
			while (itor.hasNext()) {
				obj = itor.next();
				docType = obj[0].toString().trim();
				descTx = obj[1].toString().trim();
				dataMap.put(docType, descTx);
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;

	}

	/**
	 * 根据商户编号查询该商户已经选择的交易权限码
	 * 
	 * @param params
	 * @return 2010-8-27上午11:21:12
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	public static LinkedHashMap<String, String> getMchtRoleFuncByMerId(
			Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MCHT_FUNC_ROLE_" + args[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			if (args.length >= 2) {
				// 查找所有交易码
				String sql = "select TXN_NUM,TXN_NAME from TBL_TXN_NAME where DC_FLAG = '0' order by TXN_NUM";
				;
				List<Object[]> funcAll = commQueryDAO.findBySQLQuery(sql);

				Long merId = Long.valueOf(args[1].toString().toString());
				// 查找选择商户所在 Mcc
				List<Object[]> roleIds = commQueryDAO
						.findBySQLQuery("select MCC,SIGN_INST_ID from TBL_MCHT_BASE_INF where MCHT_NO = "
								+ merId);
				String roleId = roleIds.get(0)[0].toString();
				// 相关二级商户具有的交易权限
				sql = "select VALUE_ID from TBL_MER_ROLE_FUNC where KEY_ID = "
						+ roleId + " order by VALUE_ID";
				List<Object> funcRole = commQueryDAO.findBySQLQuery(sql);

				// 该商户所在机构所拥有的权限集

				List<Object[]> rightFuncLIst = new ArrayList<Object[]>();
				// 筛选权限信息
				for (int i = 0; i < funcAll.size(); i++) {
					Long funcId = Long.valueOf(funcAll.get(i)[0].toString()
							.trim());
					for (int j = 0; j < funcRole.size(); j++) {
						Long valueId = Long.valueOf(String.valueOf(funcRole
								.get(j)));
						if (valueId.longValue() == funcId.longValue()) {
							Object[] funcInfo = new Object[2];
							funcInfo[0] = funcId;
							funcInfo[1] = funcAll.get(i)[1].toString();
							rightFuncLIst.add(funcInfo);
							funcAll.remove(i--);
							break;
						}
					}

				}

				Iterator itor = rightFuncLIst.iterator();
				Object[] obj;
				String docType, descTx;
				while (itor.hasNext()) {
					obj = (Object[]) itor.next();
					docType = obj[0].toString().trim();
					descTx = obj[1].toString().trim();
					hashMap.put(docType, descTx);
				}
			}
			//				putInCache(key, hashMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashMap;

	}

	/**
	 * 根据商户类型编号查询商户组别已经选择的交易权限码
	 * 
	 * @param params
	 * @return 2010-8-27上午11:21:12
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	public static LinkedHashMap<String, String> getMerRoleFunc(Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MER_FUNC_ROLE_" + args[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			if (args.length >= 2) {
				Long roleId = Long.valueOf(args[1].toString().toString());
				String sql = "select TXN_NUM,TXN_NAME from TBL_TXN_NAME where DC_FLAG = '0' order by TXN_NUM";
				;
				List<Object[]> funcAll = commQueryDAO.findBySQLQuery(sql);
				sql = "select VALUE_ID from TBL_MER_ROLE_FUNC where KEY_ID = "
						+ roleId + " order by VALUE_ID";
				List<Object> funcRole = commQueryDAO.findBySQLQuery(sql);
				// 右侧权限集
				List<Object[]> rightFuncLIst = new ArrayList<Object[]>();
				// 筛选权限信息
				for (int i = 0; i < funcAll.size(); i++) {
					Long funcId = Long.valueOf(funcAll.get(i)[0].toString()
							.trim());
					for (int j = 0; j < funcRole.size(); j++) {
						Long valueId = Long.valueOf(String.valueOf(funcRole
								.get(j)));
						if (valueId.longValue() == funcId.longValue()) {
							Object[] funcInfo = new Object[2];
							funcInfo[0] = funcId;
							funcInfo[1] = funcAll.get(i)[1].toString();
							rightFuncLIst.add(funcInfo);
							funcAll.remove(i--);
							break;
						}
					}

				}

				Iterator itor = rightFuncLIst.iterator();
				Object[] obj;
				String docType, descTx;
				while (itor.hasNext()) {
					obj = (Object[]) itor.next();
					docType = obj[0].toString().trim();
					descTx = obj[1].toString().trim();
					hashMap.put(docType, descTx);
				}
			}
			//				putInCache(key, hashMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashMap;

	}

	/**
	 * 根据商户类型编号查询商户组别已经选择的权限码
	 * 
	 * @param params
	 * @return 2010-8-27上午11:21:12
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	public static LinkedHashMap<String, String> getMerMerFuncAll(Object[] args) {

		// String sql =
		// "select FUNC_ID,FUNC_NAME from TBL_MER_FUNC_INF where FUNC_TYPE = '0' order by FUNC_ID";

		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MER_FUNC_ALL_" + args[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select TXN_NUM,TXN_NAME from TBL_TXN_NAME where DC_FLAG = '0' order by TXN_NUM";
			List<Object[]> funcAll = commQueryDAO.findBySQLQuery(sql);
			Iterator itor = funcAll.iterator();
			Object[] obj;
			String docType, descTx;
			while (itor.hasNext()) {
				obj = (Object[]) itor.next();
				docType = obj[0].toString().trim();
				descTx = obj[1].toString().trim();
				hashMap.put(docType, descTx);
			}
			//				putInCache(key, hashMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashMap;

	}

	/**
	 * 商户种类
	 * 
	 * @param args
	 * @return
	 */
	public static LinkedHashMap<String, String> getMchtGroupFlagShow(
			Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("0", "普通商户");
		hashMap.put("1", "集团商户");
		hashMap.put("2", "二级商户");
		hashMap.put("3", "网上支付商户");
		return hashMap;
	}

	/**
	 * 获得商户服务等级
	 * 
	 * @param args
	 * @return
	 */
	public static LinkedHashMap<String, String> getBranchSvrLvl(Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("0", "VIP");
		hashMap.put("1", "重点");
		hashMap.put("2", "普通");
		return hashMap;
	}

	/**
	 * 获得商户资质等级
	 * 
	 * @param args
	 * @return
	 */
	public static LinkedHashMap<String, String> getMchtCreLvl(Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("1", "一级");
		hashMap.put("2", "二级");
		hashMap.put("3", "三级");
		hashMap.put("9", "未分级");
		return hashMap;
	}

	/**
	 * 获得商户拓展方式
	 * 
	 * @param args
	 * @return
	 */
	public static LinkedHashMap<String, String> getMchtMoreType(Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("A", "自主拓展");
		hashMap.put("B", "存量接收");
		hashMap.put("C", "独立营销");
		hashMap.put("D", "换签");
		hashMap.put("E", "联合营销");
		hashMap.put("F", "银行推荐");
		hashMap.put("G", "重新上点");
		hashMap.put("H", "变更换签");
		hashMap.put("N", "其他");
		return hashMap;
	}

	/**
	 * getYesOrNotSelect
	 * 
	 * @param args
	 * @return
	 */
	public static LinkedHashMap<String, String> getYesOrNotSelect(Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("0", "是");
		hashMap.put("1", "否");
		return hashMap;
	}

	/**
	 * getAOrPSelect
	 * 
	 * @param args
	 * @return
	 */
	public static LinkedHashMap<String, String> getAOrPSelect(Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("0", "A");
		hashMap.put("1", "P");
		return hashMap;
	}

	/**
	 * getTxnNum 回佣类型
	 * 
	 * @param args
	 * @return
	 */
	public static LinkedHashMap<String, String> getDiscAlgoFlag(Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("0", "按比率");
		hashMap.put("1", "按金额");
		return hashMap;
	}

	/**
	 * getTxnNum 交易类型
	 * 
	 * @param args
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	public static LinkedHashMap<String, String> getTxnNum(Object[] args) {
		// LinkedHashMap<String, String> hashMap = new LinkedHashMap<String,
		// String>();
		// hashMap.put("1101", "信用卡消费");
		// hashMap.put("1091", "信用卡预授权完成");
		// hashMap.put("1701", "T+0收款");
		// return hashMap;
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "TXN_NUM_";

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select TXN_NUM,TXN_NAME from TBL_TXN_NAME where DC_FLAG = '0' order by TXN_NUM";
			;
			List<Object[]> dates = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> itor = dates.iterator();
			Object[] obj;
			String docType, descTx;
			while (itor.hasNext()) {
				obj = itor.next();
				docType = obj[0].toString().trim();
				descTx = obj[1].toString().trim();
				hashMap.put(docType, descTx);
			}
			//				putInCache(key, hashMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashMap;

	}

	/**
	 * getCardType 卡类型
	 * 
	 * @param args
	 * @return
	 */
	public static LinkedHashMap<String, String> getCardType(Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("03", "借记卡");
		hashMap.put("04", "贷记卡");
		// hashMap.put("00", "他行银联卡");
		// hashMap.put("01", "本行借记卡");
		// hashMap.put("02", "本行一帐通");
		// hashMap.put("03", "本行贷记卡");
		return hashMap;
	}

	/**
	 * getChannel 交易渠道CHANNEL
	 * 
	 * @param args
	 * @return
	 */
	public static LinkedHashMap<String, String> getChannel(Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("0", "间联POS");
		hashMap.put("1", "否");
		return hashMap;
	}

	/**
	 * 获得币种信息
	 * 
	 * @param args
	 * @return
	 */
	public static LinkedHashMap<String, String> getCurType(Object[] args) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		hashMap.put("0", "人民币");
		hashMap.put("1", "港币");
		hashMap.put("2", "美元");
		hashMap.put("3", "日元");
		hashMap.put("4", "马克");
		hashMap.put("5", "英镑");
		hashMap.put("6", "法郎");
		hashMap.put("7", "台币");
		return hashMap;
	}

	/**
	 * 授权管理的一级菜单获取
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getAuthorMenu(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "AUTHOR_LEVEL_1_";

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			List<Object[]> list = null;

			String sql = "select FUNC_ID,FUNC_NAME from tbl_func_inf where func_id in "
					+ "(select distinct func_parent_id from TBL_FUNC_INF where func_id in "
					+ "(select distinct func_parent_id from TBL_FUNC_INF where trim(misc1) in ('1','2')))";

			list = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 根据上级菜单编号查询菜单信息(授权管理)
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getAuthorNextMenu(
			Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "AUTHOR_LEVEL_" + params[1].toString().trim();
		if (params.length == 1) {
			return dataMap;
		}
		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select FUNC_TYPE from tbl_func_inf where func_id = '"
					+ params[1].toString().trim() + "'";
			List type = commQueryDAO.findBySQLQuery(sql);
			if (null != type && !type.isEmpty()) {
				if (!StringUtil.isNull(type.get(0))) {
					if ("1".equals(type.get(0).toString())) {
						sql = "select func_id,func_name from tbl_func_inf where func_parent_id = '"
								+ params[1].toString().trim()
								+ "' and func_id in (select distinct func_parent_id from TBL_FUNC_INF where trim(misc1) in ('1','2')) ";
					} else {
						sql = "select func_id,func_name from tbl_func_inf where func_parent_id = '"
								+ params[1].toString().trim()
								+ "' and trim(misc1) in ('1','2')";
					}
				}
				List<Object[]> list = commQueryDAO.findBySQLQuery(sql);
				Iterator<Object[]> iterator = list.iterator();
				while (iterator.hasNext()) {
					Object[] obj = iterator.next();
					dataMap.put(obj[0].toString().trim(), obj[1].toString()
							.trim());
				}
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 授权管理的已列为需授权的交易获取
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getAuthorisedMenu(
			Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "AUTHOR_MENU_";

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			List<Object[]> list = null;

			String sql = "select FUNC_ID,FUNC_NAME from tbl_func_inf where trim(misc1) = '1' and FUNC_TYPE = '0'";

			list = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 当前机构和下属机构的32域
	 * 
	 * @param params
	 * @return 2010-8-17下午03:13:55
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getCupBrhInfoBelow(
			Object[] params) {
		Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "CUP_BRH_BELOW_" + operator.getOprBrhId();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select CUP_BRH_ID, CUP_BRH_ID ||' - '|| BRH_NAME from TBL_BRH_INFO where BRH_ID in "
					+ operator.getBrhBelowId();
			sql += " ORDER BY BRH_ID";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 根据代码类型查询代码值和解释
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getSelectedCode(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "CODE_LIBRARY_" + params[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "select key,value from cst_sys_param where owner = '"
					+ params[1].toString() + "' order by sortno ";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 根据机构级别查询机构值和解释
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getBrhWithLevel(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "BRH_INFO_LEVEL_" + params[1].toString().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "SELECT BRH_ID,BRH_ID||'-'||BRH_NAME FROM TBL_BRH_INFO WHERE BRH_LEVEL='"
					+ params[1].toString() + "' ORDER BY BRH_ID ";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 查询操作员当前机构及下级机构
	 * 
	 * @param params
	 * @return
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getBrhByOpr(Object[] params) {
		Operator operator = (Operator) params[0];
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();
		//String key = "MENU_LEVEL_" + operator.getOprBrhId().trim();

		try {
			//			cacheMap = getFromCacheByKey(key);
			//			if (cacheMap != null) {
			//				System.out.println("get from cache! the key is " + key);
			//				return cacheMap;
			//			} else {
			String sql = "SELECT BRH_ID,BRH_ID||'-'||BRH_NAME FROM TBL_BRH_INFO WHERE BRH_ID IN "
					+ operator.getBrhBelowId() + " ORDER BY BRH_ID ";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
			//				putInCache(key, dataMap);
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}

	/**
	 * 根据key从缓存中取相对应的值
	 * @param key
	 * @return
	 * @author lvcg 20130820
	 */
	public static LinkedHashMap<String, String> getFromCacheByKey(String key) {
		OSCache cache = CacheFactory.getInstance();
		return (LinkedHashMap<String, String>) cache.get(key);
	}

	/**
	 * 将数据添加到缓存中
	 * @param key
	 * @param obj
	 * @author lvcg 20130820
	 */
	public static void putInCache(String key, Object obj) {
		OSCache cache = CacheFactory.getInstance();
		cache.put(key, obj);
	}
	
	
	public static LinkedHashMap<String, String> getDiscCode(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		
		try {
			String val = "";
			if(params[1] != null && !"".equals(params[1])){
				val = params[1].toString().trim();
			}
			
			String sql = "SELECT DISC_CD,DISC_NM FROM TBL_INF_DISC_CD WHERE DISC_CD= '"
					+ val
					+ "'";
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[0].toString()
						.trim() + "-" + obj[1].toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}
	/**
	 * 查询本年度历史交易金额
	 * 
	 * @param params
	 * @return 2010-8-27上午11:21:12
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	public static LinkedHashMap<String, String> getTransAmtHis(Object[] args) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();

		try {
			String sql = "select * from report_trans_amt_his order by trans_month"; 
					//" where substr(trans_month,0,4)=to_char(sysdate, 'yyyy') order by trans_month";
			List<Object[]> dates = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> itor = dates.iterator();
			String docType, descTx;
			while (itor.hasNext()) {
				Object[] obj = itor.next();
				docType = obj[0].toString().trim();
				descTx = obj[1].toString().trim();
				dataMap.put(descTx,docType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;

	}
	/**
	 * 查询本年度历史交易金额
	 * 
	 * @param params
	 * @return 2010-8-27上午11:21:12
	 * update by lvcg 20130820 增加缓存读取操作
	 */
	public static LinkedHashMap<String, String> getTransNum(Object[] args) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();

		try {
			String nowTime = CommonFunction.getCurrentDate();
			String sql = "select count(*) from TBL_N_TXN t where substr(inst_date,0,12)= substr(TO_CHAR(sysdate - 2 / 24 / 60, 'yyyymmddhh24miss'),0,12)";
			//List dates = commQueryDAO.findBySQLQuery(sql);
			String count = CommonFunction.getCommQueryDAO().findCountBySQLQuery(
					sql);
			dataMap.put(count,count);
//			for(int i=0;i<dates.size();i++){
//				Object[] big = dates.get(i);
//			}
//			Iterator<BigDecimal[]> itor = dates.iterator();
//			String num;
//			while (itor.hasNext()) {
//				BigDecimal[] big = itor.next();
//				num = big[0].toString().trim();
//				dataMap.put(num,num);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;

	}
	public static LinkedHashMap<BigDecimal, String> getTransAmt(Object[] args) {
		LinkedHashMap<BigDecimal, String> dataMap = new LinkedHashMap<BigDecimal, String>();
		try {
			String nowTime = CommonFunction.getCurrentDate();
			String sql = "select * from REPORT_TRANS_AMT t where substr(trans_time,0,8)= TO_CHAR(sysdate,'yyyymmdd') order by substr(trans_time,0,12)";
			//List dates = commQueryDAO.findBySQLQuery(sql);
			List<Object[]> dates = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> itor = dates.iterator();
			String time;
			BigDecimal amt;
			while (itor.hasNext()) {
				Object[] big = itor.next();
				time = big[0].toString().trim().substring(8,12);
				amt = new BigDecimal(big[1].toString().trim());
				dataMap.put(amt,time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;

	}
	public static LinkedHashMap<String, String> getSalTpMcc(Object[] params) {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();
		try {
			String sql = "SELECT MCHNT_TP,DESCR FROM  TBL_INF_MCHNT_TP WHERE SAL_TP_GRP ='"+params[1]+"'";
			List<Object[]> funcAll = commQueryDAO.findBySQLQuery(sql);
			Iterator itor = funcAll.iterator();
			Object[] obj;
			String docType, descTx;
			while (itor.hasNext()) {
				obj = (Object[]) itor.next();
				docType = obj[0].toString().trim();
				descTx = obj[1].toString().trim();
				hashMap.put(docType, descTx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashMap;

	}	
	/**
	 * 获取支付方式
	 * @param params
	 * @return
	 */
	public static LinkedHashMap<String, String> getPayTypeParam(
			Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();

		try {
			List<Object[]> list = null;

			String sql = "select key,VALUE from cst_sys_param t WHERE t.owner='settlePayType'";

			list = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}
	/**
	 * 特殊计费级别
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> getSpeFeeLevel(Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		try {
			String sql="";
			if(params[1]==null||"null".equals(params[1])){
				sql = "select key,key||'-'||value from cst_sys_param where owner like 'SpeFeeLevel%' order by sortno ";
			}else{
				sql = "select key,key||'-'||value from cst_sys_param where owner = 'SpeFeeLevel"+ params[1].toString() + "' order by sortno ";
			}
			List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	
	/**
	 * 获取T+0支付批次
	 * @param params
	 * @return
	 */
	public static LinkedHashMap<String, String> getT0Param(
			Object[] params) {
		LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> cacheMap = new LinkedHashMap<String, String>();

		try {
			List<Object[]> list = null;

			String sql = "select key,VALUE from cst_sys_param t WHERE t.owner='SettleTs'";

			list = commQueryDAO.findBySQLQuery(sql);
			Iterator<Object[]> iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] obj = iterator.next();
				dataMap.put(obj[0].toString().trim(), obj[1].toString()
						.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataMap;
	}
}
