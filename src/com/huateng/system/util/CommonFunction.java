package com.huateng.system.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.huateng.bo.base.T10211BO;
import com.huateng.common.Operator;
import com.huateng.common.StringUtil;
import com.huateng.commquery.dao.ICommQueryDAO;
import com.huateng.po.TblOprLog;

/**
 * Title:通用方法
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-3-9
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author 
 * 
 * @version 1.0
 */
public class CommonFunction {
	
	private static SimpleDateFormat showDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat sysDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static SimpleDateFormat specialDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	
	private static SimpleDateFormat sysTimeFormat = new SimpleDateFormat("HHmmss");
	
	
	private static SimpleDateFormat sysDateFormat8 = new SimpleDateFormat("yyyyMMdd");
	
	private static SimpleDateFormat showDateFormatZHCN = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	
	private static ICommQueryDAO commQueryDAO = (ICommQueryDAO) ContextUtil.getBean("CommQueryDAO");
	
	/**
	 * 控制台打印数据(后期取消)
	 * @param str
	 */
	public static void console(String str) {
		System.err.println("你打印的数据 : "+str);
	}
	
	/**
	 * 获得系统当前日期时间（yyyyMMddHHmmss）
	 * @return
	 */
	public static String getCurrentDateTime() {
		return sysDateFormat.format(new Date());
	}
	
	/**
	 * 获得系统当前时间（yyyyMMddHHmmss）
	 * @return
	 */
	public static String getCurrentTime() {
		return sysTimeFormat.format(new Date());
	}
	
	
	/**
	 * 获得系统当前日期（yyyyMMdd）
	 * @return
	 */
	public static String getCurrentDate() {
		return sysDateFormat8.format(new Date());
	}
	
	/**
	 * 获得向前系统时间用于显示（yyyy-MM-dd HH:mm:ss）
	 * @return
	 */
	public static String getCurrentDateTimeForShow() {
		return showDateFormat.format(new Date());
	}
	/**
	 * 获得中文系统时间（yyyy年MM月dd日 HH时mm分ss秒）
	 * @return
	 */
	public static String getCurrentDateTimeZHCN() {
		return showDateFormatZHCN.format(new Date());
	}
	
	/**
	 * 获取特殊格式的系统时间（yyyyMMdd HH:mm:ss:）
	 * @return
	 */
	public static String getSpecialDateTime(Date p_date) {
		return specialDateFormat.format(p_date);
	}
	/**
	 * 通过传递日期参数获得系统当前时间（yyyyMMddHHmmss）
	 * @return
	 */
	public static String getCurrentTimeForPara(Date p_date) {
		return sysDateFormat.format(p_date);
	}
	
	
	
	/**
	 * 获得给定时间的前一天
	 * @param date 格式为yyMMdd exp 20120301
	 * @return String yyMMdd exp 20120229
	 */
	public static String getDateYestoday(String date){
		Calendar cal=Calendar.getInstance();
		date=date.trim();
		int year=Integer.parseInt(date.substring(0,4));
		int month=Integer.parseInt(date.substring(4,6));
		int day=Integer.parseInt(date.substring(6,date.length()));
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, day);
		//日期的前一天
		cal.add(Calendar.DATE,-1);
		
		String mon=String.valueOf(cal.get(Calendar.MONTH)+1);
		if(mon.length()==1){
			mon="0"+mon;
		}
		String d=String.valueOf(cal.get(Calendar.DATE));
		if(d.length()==1){
			d="0"+d;
		}
		String yestoday=String.valueOf(cal.get(Calendar.YEAR))+mon+d;
		return yestoday.trim();	
	}
	/**
	 * 获得给定时间的前十天
	 * @param date 格式为yyMMdd exp 20120301
	 * @return String yyMMdd exp 20120229
	 */
	public static String getDateTen(String date){
		Calendar cal=Calendar.getInstance();
		date=date.trim();
		int year=Integer.parseInt(date.substring(0,4));
		int month=Integer.parseInt(date.substring(4,6));
		int day=Integer.parseInt(date.substring(6,date.length()));
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, day);
		//日期的前十天
		cal.add(Calendar.DATE,-10);
		
		String mon=String.valueOf(cal.get(Calendar.MONTH)+1);
		if(mon.length()==1){
			mon="0"+mon;
		}
		String d=String.valueOf(cal.get(Calendar.DATE));
		if(d.length()==1){
			d="0"+d;
		}
		String yestoday=String.valueOf(cal.get(Calendar.YEAR))+mon+d;
		return yestoday.trim();	
	}
	/**
	 * 获得给定时间的前iDays天
	 * @param date 格式为yyMMdd exp 20120301
	 * @return String yyMMdd exp 20120229
	 */
	public static String getDateBefore(String date,int iDays){
		Calendar cal=Calendar.getInstance();
		date=date.trim();
		int year=Integer.parseInt(date.substring(0,4));
		int month=Integer.parseInt(date.substring(4,6));
		int day=Integer.parseInt(date.substring(6,date.length()));
		cal.set(Calendar.YEAR,year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, day);
		//日期的前十天
		cal.add(Calendar.DATE,-iDays);
		
		String mon=String.valueOf(cal.get(Calendar.MONTH)+1);
		if(mon.length()==1){
			mon="0"+mon;
		}
		String d=String.valueOf(cal.get(Calendar.DATE));
		if(d.length()==1){
			d="0"+d;
		}
		String yestoday=String.valueOf(cal.get(Calendar.YEAR))+mon+d;
		return yestoday.trim();	
	}
	/**
	 * 填补字符串
	 * @param str
	 * @param fill
	 * @param len
	 * @param isEnd
	 * @return
	 */
	public static String fillString(String str,char fill,int len,boolean isEnd) {
		if(str != null){
			int fillLen = len - str.getBytes().length;
			if(len <= 0) {
				return str;
			}
			for(int i = 0; i < fillLen; i++) {
				if(isEnd) {
					str += fill;
				} else {
					str = fill + str;
				}
			}
		}else{
			str = new String();
			for(int i = 0; i < len; i++) {
					str = fill + str;
			}
		}
		return str;
	}
	
	/**
	 * 填补字符串(中文字符扩充)
	 * @param str
	 * @param fill
	 * @param len
	 * @param isEnd
	 * @return
	 */
	public static String fillStringForChinese(String str,char fill,int len,boolean isEnd) {
        int num = 0;
        Pattern p = Pattern.compile("[^\\x00-\\xff]"); 
        for(int i=0;i<str.length();i++)
        {
        	Matcher m = p.matcher(str.substring(i, i+1));
            if(m.find()) {
                num++;
             }
        }
        int fillLen = len - (str.length()+num);
        if(len <= 0) {
                return str;
        }
        for(int i = 0; i < fillLen; i++) {
                if(isEnd) {
                        str += fill;
                } else {
                        str = fill + str;
                }
        }
        return str;
	}
	
	/**
	 * 获得本行及一下机构MAP
	 * @param brhId
	 * @param brhMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> getBelowBrhMap(String brhId,Map<String, String> brhMap) {
		String sql = "SELECT BRH_ID,BRH_NAME FROM  TBL_BRH_INFO START WITH BRH_ID='"+brhId+"' CONNECT BY PRIOR BRH_ID=UP_BRH_ID ";
		List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
		for(Object[] brhInfo : dataList) { 
			String tempBrhId = brhInfo[0].toString();
			String tempBrhName = brhInfo[1].toString();
			brhMap.put(tempBrhId, tempBrhName);
		}
		return brhMap;
	}
	/**
	 * 获得本行及一下机构MAP
	 * @param brhId
	 * @param brhMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> getBelowBrhMap(Map<String, String> brhMap) {
		String sql = "SELECT BRH_ID,BRH_NAME,CUP_BRH_ID FROM TBL_BRH_INFO WHERE BRH_ID in " + getBelowBrhInfo(brhMap) + " or UP_BRH_ID in " + getBelowBrhInfo(brhMap) + " ";
		List<Object[]> dataList = commQueryDAO.findBySQLQuery(sql);
		if (brhMap.size() == dataList.size()) {
			return brhMap;
		}
		for(Object[] brhInfo : dataList) {
			String tempBrhId = brhInfo[0].toString();
			String tempBrhName = brhInfo[1].toString();
			brhMap.put(tempBrhId, tempBrhName);
		}
		brhMap = getBelowBrhMap(brhMap);
		return brhMap;
	}

	/**
	 * 获得本行及下属机构编号信息
	 * @param brhMap
	 * @return
	 */
	public static String getBelowBrhInfo(Map<String, String> brhMap) {
		String belowBrhInfo = "(";
		Iterator<String> iter = brhMap.keySet().iterator();
		while(iter.hasNext()) {
			String brhId = iter.next();
			belowBrhInfo += "'" + brhId + "'";
			if(iter.hasNext()) {
				belowBrhInfo += ",";
			}
		}
		belowBrhInfo += ")";
		return belowBrhInfo;
	}
	
	/**
	 * 获得指定日期的偏移日期
	 * @param refDate 参照日期
	 * @param offSize 偏移日期
	 * @return
	 */
	public static String getOffSizeDate(String refDate,String offSize) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Integer.parseInt(refDate.substring(0, 4)), 
							  Integer.parseInt(refDate.substring(4, 6)) - 1, 
							  Integer.parseInt(refDate.substring(6, 8)));
		calendar.add(Calendar.DATE, Integer.parseInt(offSize));
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String retDate = String.valueOf(calendar.get(Calendar.DATE));
		if(Integer.parseInt(month) < 10) {
			month = "0" + month;
		}
		if(Integer.parseInt(retDate) < 10) {
			retDate = "0" + retDate;
		}
		return year + month + retDate;
	}
	
	
	/**
	 * 将金额元转分
	 * @param str
	 * @return
	 */
	public static String transYuanToFen(String str) {
		if(str == null || "".equals(str.trim()))
			return "";
		BigDecimal bigDecimal = new BigDecimal(str.trim());
		return bigDecimal.movePointRight(2).toString();
	}
	
	/**
	 * 将金额分转元
	 * @param str
	 * @return
	 */
	public static String transFenToYuan(String str) {
		if(str == null || "".equals(str.trim()))
			return "";
		BigDecimal bigDecimal = new BigDecimal(str.trim());
		return bigDecimal.movePointLeft(2).toString();
	}
	
	/**
	 * 获得指定个数的随机数组合
	 * @param len
	 * @return
	 * 2010-8-19上午10:51:15
	 */
	public static String getRandomNum(int len) {
		String ran = "";
		Random random = new Random();
		for(int i = 0; i < len; i++) {
			ran += String.valueOf(random.nextInt(10));
		}
		return ran;
	}
	
	/**
	 * 判断字符串是否全部由数字组成
	 * @param str
	 * @return
	 * 2010-8-26下午02:20:28
	 */
	public static boolean isMoney(String str) {	
		
		for(int i = 0; i < str.length(); i++) {
			if(!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}
	
	/**
	 * 判断字符串是否全部由数字组成
	 * @param str
	 * @return
	 * 2010-8-26下午02:20:28
	 */
	public static boolean isAllDigit(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}
	
	public static Date getCurrentTs()
	{
		Date now = new Date();
		return new Timestamp(now.getTime());
	}
	
	public static ICommQueryDAO getCommQueryDAO() {
		return commQueryDAO;
	}

	public static void setCommQueryDAO(ICommQueryDAO commQueryDAO) {
		CommonFunction.commQueryDAO = commQueryDAO;
	}
	
	/**
	 * trim给定对象的field
	 * 仅对private field String有效(不含static)
	 * 
	 * @param obj
	 * @return
	 * 2011-6-22下午03:46:12
	 */
	public static Object trimObject(Object obj){
		
		try {
			Method[] methods = obj.getClass().getMethods();
			for (Method m: methods) {
				if (m.getName().startsWith("get")) {
					//允许个别字段转换失败
					try {
						if (String.class == m.getReturnType()) {
							String value = (String) m.invoke(obj, new Object[] {});
							if (!StringUtil.isNull(value)) {
								obj.getClass()
									.getMethod("s" + m.getName().substring(1),
												String.class)
									.invoke(obj, value.trim());
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	/**
	 * 获得指定个数转换为Double
	 * @param len
	 * @return
	 * 2010-8-19上午10:51:15
	 */
	
	public static Double getDValue(String value, Double _default) {
		if (StringUtil.isNotEmpty(value)) {
			return Double.valueOf(value);
		}
		return _default;
	}
	
	/**
	 * 获得指定个数转换为BigDecimal
	 * @param len
	 * @return
	 * 2010-8-19上午10:51:15
	 */
	public static BigDecimal getBValue(String value, BigDecimal _default) {
		if (StringUtil.isNotEmpty(value)) {
			try {
				return new BigDecimal(value.trim());
			} catch (Exception ex) {
				return _default;
			}
		} else
			return _default;
	}
	
	/**
	 * 获得指定个数转换为int
	 * @param len
	 * @return
	 * 2010-8-19上午10:51:15
	 */
	public static Integer getInt(String value, int _default) {
		if (StringUtil.isNotEmpty(value)) {
			try {
				return Integer.parseInt(value.trim());
			} catch (Exception ex) {
				return _default;
			}
		} else
			return _default;
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String formate8Date(String str) {
		if(str.length() == 8) {
			return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + 
			str.substring(6, 8);
		}
		return str;
	}
	
	public static String getCurrDate(String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(new Date());
	}
	/**
	 * 16进制
	 * @param c
	 * @return
	 * 2011-7-27上午11:50:28
	 */
	private static byte toByte(char c) {
	    byte b = (byte) "0123456789abcdef".indexOf(c);
	    return b;
	}
	/**
	 * 16进制转BCD
	 * @param hex
	 * @return
	 * 2011-7-27上午11:49:20
	 */
	public static byte[] hexStringToByte(String hex) {
	    int len = (hex.length() / 2);
	    byte[] result = new byte[len];
	    char[] achar = hex.toCharArray();
	    for (int i = 0; i < len; i++) {
	     int pos = i * 2;
	     result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
	    }
	    return result;
	}
	
	public static String transMoney(double n){
		try {
			String[] fraction = {"角", "分"};
			String[] digit = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
			String[][] unit = {{"元", "万", "亿"}, {"", "拾", "佰", "仟"}};
			
			String head = n < 0 ? "负" : "";
			n = Math.abs(n);
			
			String s = "";
			
			for (int i = 0; i < fraction.length; i++) {
				s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+","");
			}
			if (s.length() < 1) {
				s = "整";
			}
			int integerPart = (int)Math.floor(n);
			
			for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
				String p = "";
				for (int j = 0; j < unit[1].length && n > 0;j++) {
					p = digit[integerPart % 10] + unit[1][j] + p;
					integerPart = integerPart/10;
				}
				s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
			}
			return head + s.replaceAll("(零.)*零元", "元").replaceAll("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String insertString(String src, String fill){
		String tmp = "";
		for (int i = 0; i < src.length(); i++) {
			tmp += fill;
			tmp += src.substring(i, i + 1);
		}
		return tmp;
	}
	/**
	 * 记录业务操作类型
	 * @param opr 操作员
	 * @param sOprType 操作类型
	 */
	public static void addOprLog(Operator opr,String sOprType){
		T10211BO t10211BO = (T10211BO) ContextUtil.getBean("T10211BO");
		TblOprLog tblOprLog = new TblOprLog();
		tblOprLog.setId(GenerateNextId.getNextSequence_datePlusSeq("TBL_OPR_LOG"));
		tblOprLog.setOprId(opr.getOprId());
		tblOprLog.setOprType(sOprType);
		tblOprLog.setOprDate(getCurrentDate());
		t10211BO.add(tblOprLog);
	}
	

	/**
	 * 注：将参数二对象的值复制到参数三对象中（复制对象类型要相同）。
	 * 注：但此方法无法将参数对象二为空的属性复制给参数对象三
	 * @param cls 反射对象类型
	 * @param cp1参数对象二（人员修改过后的对象）“界面”
	 * @param cp2 参数对象三（最终复制完成得到的结果的对象）“数据库”
	 * @param s 指定强制复制的参数名称
	 */
	public static void copyEditPOJO(Class cls ,Object cp1, Object cp2,String ... s){
		try {
			List<String> list = Arrays.asList(s);
			Method [] methods = cls.getDeclaredMethods();
			for(Method method : methods){
				String metName = method.getName();
				if(metName.startsWith("set")){
					String invoke = "get"+metName.substring(3);
					String name = metName.substring(3,4).toLowerCase()+metName.substring(4);
					Object val = cls.getMethod(invoke).invoke(cp1);
					
					if(list.indexOf(name) != -1 || (val != null && !"".equals(val))){
						method.invoke(cp2, val);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param date
	 */
	public static String to8Date(String dateStr) {
		if(dateStr.length() == 8) {
			return dateStr;
		}
		if(dateStr.length() > 8) {
			if(dateStr.indexOf("-") > -1){
				return dateStr.substring(0, 4) + dateStr.substring(5, 7) + dateStr.substring(8,10);
			} else {
				return dateStr.substring(0, 8);
			}
		}
		return dateStr;
	}
	/**
	 * 
	 * @param list
	 * @return
	 */
	public static boolean checkSamList(List list){
		for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )   { 
			for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )   { 
				if  (list.get(j).equals(list.get(i)))   { 
			       return true;
			    } 
			} 
		}
		return false; 
	}
	
	/**
	 * BCD转成16进制
	 * @param bArray
	 * @return
	 * 2011-7-27上午11:47:56
	 */
	public static String bytesToHexString(byte[] bArray) {
	    StringBuffer sb = new StringBuffer(bArray.length);
	    String sTemp;
	    for (int i = 0; i < bArray.length; i++) {
	     sTemp = Integer.toHexString(0xFF & bArray[i]);
	     if (sTemp.length() < 2)
	      sb.append(0);
	     sb.append(sTemp.toUpperCase());
	    }
	    return sb.toString();
	}
	
	/**
	 * @功能: 10进制串转为BCD码
	 * @参数: 10进制串
	 * @结果: BCD码
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}
		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}
	public static String getSysParam(String owner,String key) {
		String sql = "SELECT VALUE FROM CST_SYS_PARAM WHERE OWNER = '" + owner + "' and key='" + key + "'";
		List<String> resultList = CommonFunction.getCommQueryDAO()
				.findBySQLQuery(sql);
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		}
		return null;
	}
	
	/**
	 * 字符串转16进制
	 * @param s
	 * @return
	 */
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
}
