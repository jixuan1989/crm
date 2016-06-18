/* @(#)
 *
 * Project:NEBMis
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   PanShuang      2010-3-9       first release
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
package com.huateng.common;

/**
 * Title:静态全局变量宏定义
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-3-9
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class Constants {
	
	/**系统一级菜单*/
	public static final String MENU_LVL_1 = "1";
	/**系统二级菜单*/
	public static final String MENU_LVL_2 = "2";
	/**系统三级菜单*/
	public static final String MENU_LVL_3 = "0";
	/**菜单编号*/
	public static final String MENU_ID = "id";
	/**父菜单编号*/
	public static final String MENU_PARENT_ID = "parent_id";
	/**菜单名称*/
	public static final String MENU_TEXT = "text";
	/**菜单链接*/
	public static final String MENU_URL = "url";
	/**菜单权限*/
	public static final String ROLE_FLAG = "roleFlag";
	/**菜单叶子节点标识*/
	public static final String MENU_LEAF = "leaf";
	/**树节点样式*/
	public static final String MENU_CLS = "cls";
	/**叶子节点样式*/
	public static final String MENU_FILE = "file";
	/**父节点样式*/
	public static final String MENU_FOLDER = "folder";
	/**子菜单标识*/
	public static final String MENU_CHILDREN = "children";
	/**系统成功提示*/
	public static final String SUCCESS_HEADER = "success";
	/**系统成功提示*/
	public static final String PROMPT_MSG = "msg";
	/**系统提示代码*/
	public static final String PROMPT_CODE = "code";
	/**生产模式标识*/
	public static final String PRODUCTION_MODE_FLG = "PRODUCTION_MODE";
	/**操作员信息*/
	public static final String OPERATOR_INFO = "operator";
	/**菜单事件*/
	public static final String MENU_HANDLER = "handler";
	/**方法名称*/
	public static final String MENU_LVL1_FUNC = "function(){new changeTree(this)}";
	/**JSON格式的方法名称*/
	public static final String MENU_LVL1_JSON_FUNC = "\"changeTree\"";
	/**方法名称*/
	public static final String MENU_LVL3_FUNC = "function(){new changeTxn(this)}";
	/**JSON格式的方法名称*/
	public static final String MENU_LVL3_JSON_FUNC = "\"changeTxn\"";
	/**树形菜单SESSION变量*/
	public static final String TREE_MENU_MAP = "tree_menu_map";
	/**工具栏SESSION变量*/
	public static final String TOOL_BAR_STR = "tbar";
	/**字菜单标识*/
	public static final String TOOL_BAR_CHILDREN = "menu";
	/**工具栏类型*/
	public static final String TOOL_BAR_TYPE = "xtype";
	/**按钮类型*/
	public static final String TOOL_BAR_BUTTON = "splitbutton";
	/**菜单图标*/
	public static final String TOOLBAR_ICON = "iconCls";
	/**一级菜单图标样式*/
	public static final String TOOLBAR_ICON_MENU = "splitMenu";
	/**菜单项图标样式*/
	public static final String TOOLBAR_ICON_MENUITEM = "splitButtonItem";
	/**菜单数初始化变量*/
	public static final String TREE_INIT_FLG = "init";
	/**系统日志编号识别类型*/
	public static final String NEXT_ID_LOG_FLG = "TXN_LOG";
	/**角色编号识别类型*/
	public static final String NEXT_ID_ROLE_FLG = "ROLE_ID";
	/**存于SESSION中的日志信息*/
	public static final String TXN_LOG_SESSION = "TXN_LOG_SESSION";
	/**菜单是否展开*/
	public static final String IS_EXPAND = "expanded";
	/**菜单面板*/
	public static final String TREE_MENU_PANEL = "treeMenuPanel";
	
	
    // SysDic 表
	public final static String TABLE_ROOT = "results";
    public final static String TABLE_ROW = "row";
    public final static String SYS_DIC_TBL_NM = "TBL_NM";
    public final static String SYS_DIC_FLD_NM = "FLD_NM";
    public final static String SYS_DIC_FLD_VAL = "FLD_VAL";
    public final static String SYS_DIC_FLD_DESC = "FLD_DESC";
    
    //SelectOptions
    public final static String SLT_TXN_ID = "TXN_ID";
    public final static String SLT_XML_ID = "id";
    public final static String SLT_EXTRACT_TYPE = "EXTRACT_TYPE";
    public final static String SLT_ATTR_VALUE = "value";
    public final static String SLT_MODE_STATIC = "STATIC_MODE";
    public final static String SLT_MODE_SQL = "SQL_MODE";
    public final static String SLT_MODE_SQL_SQL = "SQL";
    public final static String SLT_MODE_QUERYDAO = "QUERY_DAO";
    public final static String SLT_MODE_DYNAMIC = "DYNAMIC_MODE";
    public final static String SLT_MODE_DYNAMIC_METHOD = "METHOD";
    public final static String SLT_MODE_DYNAMIC_ATTR_NAME = "name";
    public final static String SLT_MODE_SQL_VALUE = "SQL";
    public final static String SLT_MODE_STATIC_VALUE = "STATIC";
    public final static String SLT_MODE_DYNAMIC_VALUE = "DYNAMIC";
    
    /**SysDic路径*/
    public final static String SYSDIC_CONTEXTPATH = "/WEB-INF/xml/SysDic.xml";
    /**SelectionOptions路径*/
    public final static String SELECTOPTION_CONTEXTPATH = "/WEB-INF/xml/SelectOptions.xml";
    /**信息列表路径*/
    public static final String GRID_CONFIG_CONTEXTPATH = "/WEB-INF/xml/GridConfig.xml";
    /**菜单列表路径*/
    public static final String MENU_CONFIG_CONTEXTPATH = "/WEB-INF/xml/MenuConfig.xml";
    /**JSON数据头信息*/
    public static final String JSON_HEANDER_DATA = "data";
    /**JSON数据总数*/
    public static final String JSON_HEANDER_TOTALCOUNT = "totalCount";
    /**查询显示的记录数*/
    public static final int QUERY_RECORD_COUNT = 15;
    /**下拉列表记录数*/
    public static final int QUERY_SELECT_COUNT = 50;
    /**下拉列表隐藏值*/
    public static final String SELECT_VALUE_FIELD = "valueField";
    /**下拉列表显示值*/
    public static final String SELECT_DISPLAY_FIELD = "displayField";
    /**上下文路径*/
    public static String CONTEXTPATH = null;
    /**成功代码*/
    public static final String SUCCESS_CODE = "00";
    /**自定义代码*/
    public static final String SUCCESS_CODE_CUSTOMIZE = "$C";
    /**商户参数上传时信息需要再次校验时提示自定义代码*/
    public static final String ERROR_CODE_CUSTOMIZE = "$E";
    /**商户参数上传时信息需要再次校验时提示自定义代码,此时是商户号一样，商户名不一样*/
    public static final String ERROR_CODE_CUSTOMIZE_NUMBERREPEAT_NAMENOTREAPEAT = "$S";
    /**数据存储失败*/
    public static final String DATA_OPR_FAIL = "数据存储失败，请重试";
    /**失败代码*/
    public static final String FAILURE_CODE = "-1";
    /**PDF格式报表*/
    public static final String REPORT_PDFMODE = "PDF";
    /**RTF格式报表*/
    public static final String REPORT_RTFMODE = "RTF";
    /**HTML格式报表*/
    public static final String REPORT_HTMLMODE = "HTML";
    /**TXT格式报表*/
    public static final String REPORT_TXTMODE = "TXT";
    /**XLS格式报表*/
    public static final String REPORT_EXCELMODE = "EXCEL";
    
    public final static String ADD = "A";
    public final static String MODIFY = "M";
    public final static String DELETE = "D";
    public final static String VALID = "1";
    public final static String INVALID = "0";
    
    public final static String DEFAULT = "-";
    
    public final static String ERR_ATTRIBUTE = "没有正确获得页面传递参数，请重试！";
    
    //任务类型
    //装机任务
    public final static String TASK_TYPE_INSTALL = "0";
    //换机任务
    public final static String TASK_TYPE_CHANGE  = "1";
    //维护任务
    public final static String TASK_TYPE_REPAIR = "2";
    //配送耗材
    public final static String TASK_TYPE_DISTRIBUTION = "3";
    //调单任务
    public final static String TASK_TYPE_ACHIEVE = "4";
    //风险排查任务
    public final static String TASK_TYPE_RISK = "5";
    //上门回访
    public final static String TASK_TYPE_VISIT_DROPIN = "6";
    //电话回访
    public final static String TASK_TYPE_VISIT_CALL = "7";
    //送修
    public final static String TASK_TYPE_SEND_REPAIR = "8";
    
    //任务状态
    //新建
    public final static String TASK_STATE_NEW = "1";
    //等待调机
    public final static String TASK_STATE_WARITDEBUG = "2";
    //等待派工
    public final static String TASK_STATE_PAI = "3"; 
    //等待处理（待完成）
    public final static String TASK_STATE_DEAL = "4";
    //已完成
    public final static String TASK_STATE_COMPLETE = "5";
    //结束
    public final static String TASK_STATE_END = "6";
    //回访任务类型的删除任务
    public final static String TASK_DELETE = "7";
    
    //   机具操作类型
    /**
     *  入库
     */
    public final static String OPR_TYPE_IN = "0";
    /**
     *  出库
     */
    public final static String OPR_TYPE_OUT = "1";
    /**
     *  状态变更
     */
    public final static String OPR_TYPE_CHANGESTATUS = "2";
    
    //机具流转状态
    /**
     * 正常
     */
    public final static String POS_STATE_NORMALLY = "0";
    /**
     * 待领机
     */
    public final static String POS_STATE_WARITPULLDOWN = "1";
    /**
     * 已安装，出库到商户
     */
    public final static String POS_STATE_OUT = "2";
    /**
     * 待入库
     */
    public final static String POS_STATE_WAITIN = "3";
    /**
     * 小库房返银行
     */
    public final static String POS_STATE_BACKTOBANK = "5";
    /**
     * 大库房返厂商，小库房返还给大库房
     */
    public final static String POS_STATE_BACKTOFACTORY = "6";
    /**
     *  作废
     */
    public final static String POS_STATE_DELETE= "8" ;
    /**
     *  待安装
     */
    public final static String POS_STATE_WAITOUT = "9" ;
    
    //机具流转原因
    /**
     * 新机入库
     */
    public final static String POS_TRANSFER_REASON_NEWIN = "1";
    /**
     * 厂家返回
     */
    public final static String POS_TRANSFER_REASON_FACTORYBACK = "2";
    /**
     * 返回厂家
     */
    public final static String POS_TRANSFER_REASON_BACKTOFACOTRY = "3";
    /**
     * 分公司领用
     */
    public final static String POS_TRANSFER_REASON_BRANCHCOMPANYGET = "4";
    /**
     * 分公司返还
     */
    public final static String POS_TRANSFER_REASONBACKFROMCOMPANY = "5";
    /**
     * 机具调试
     */
    public final static String POS_TRANSFER_REASON_ADJUST = "6";
    /**
     * 机具领用
     */
    public final static String POS_TRANSFER_REASON_GET = "7";
    /**
     * 银行导入
     */
    public final static String POS_TRANSFER_REASON_BANKIMPORT = "8";
    /**
     * 返回银行
     */
    public final static String POS_TRANSFER_REASON_BACKTOBANK = "9";
    /**
     * 新装失败退回
     */
    public final static String POS_TRANSFER_REASON_NEWBACK = "10";
    /**
     * 换机成功换回
     */
    public final static String POS_TRANSFER_REASON_CHANGESUCCESS_BACK = "11";
    /**
     * 换机失败退回
     */
    public final static String POS_TRANSFER_REASON_CHANGEFAIL_BACK = "12";
    /**
     * 已安装
     */
    public final static String POS_TRANSFER_REASON_OUT = "13";
    /**
     * 撤机
     */
    public final static String POS_TRANSFER_REASON_BACK = "14";
    /**
     * 撤参
     */
    public final static String POS_TRANSFER_REASON_BACKTOSMALLHOUSE = "15";
    /**
     * 作废机具
     */
    public final static String POS_TRANSFER_REASON_DELETE = "16";
    /**
     * 取消调机
     */
    public final static String POS_TRANSFER_CANCELDEBUG = "17";
    
    //调机类型
    //键盘
    public final static String ADJUST_MACHINE_TYPE_KEY = "0" ;
    //主机
    public final static String ADJUST_MACHINE_TYPE_MAIN = "1";
    //电源
    public final static String ADJUST_MACHINE_TYPE_POWER = "2";
    //主机和键盘
    public final static String ADJUST_MACHINE_TYPE_MAINKEY = "3";
    //主机电源
    public final static String ADJUST_MACHINE_TYPE_MAINPOWER = "4" ;
    //键盘电源
    public final static String ADJUST_MACHINE_TYPE_KEYPOWER = "5" ;
    //调试主机、密码键盘、电源
    public final static String ADJUST_MACHINE_TYPE_ALL = "6" ;
    //不调试设备
    public final static String ADJUST_MACHINE_TYPE_NO = "7" ;
    
    //收据状态
    public final static String RECEIPT_DAIKAI = "0";// 待开收据
    public final static String RECEIPT_DAILING = "1";//待领收据
    public final static String RECEIPT_DAISHOU = "2";//待收押金
    public final static String RECEIPT_YISHOU = "3";//已收押金
    
    //收据显示任务状态
    public final static String RECEIPT_TASK_UNDO = "0";//未完成
    public final static String RECEIPT_TASK_DEL = "1";//已删除
    public final static String RECEIPT_TASK_DONE = "2";//已完成
    public final static String RECEIPT_TASK_LOSE = "3";//已失败
    
    
    //押金表中状态
    public final static String DEPOSIT_STA_UNDO = "2";//未收
    public final static String DEPOSIT_STA_DONE = "0";//已收
    public final static String DEPOSIT_STA_CANE = "1";//已退
    public final static String DEPOSIT_STA_CHAN = "3";//已换签
    
    //是否是重新上点
    /**
     * 是/正确/机具可用/机具和商户关联且任务已完成/不存在密码键盘
     */
    public final static String FLAG_YES = "1";//是
    /**
     * 否/错误/机具不可用/机具已和商户关联但任务未完成/存在密码键盘
     */
    public final static String FLAG_NO = "0";//否
    
    //门店装机类型
    public final static String MACHINE_NEW = "0";//新装机
    public final static String MACHINE_ADD = "1";//增机
    public final static String MACHINE_CHANGE = "2";//换签
    public final static String MACHINE_AGAIN = "3";//重新上点
    //创建装机任务时判断是否要调机
    public final static String IS_DEBUG_YES ="0";
    public final static String IS_DEBUG_NO = "1";
    public final static String IS_DEBUG_NORMAL = "2";//正常类型的装机
    //是否正常完成装机任务
    public final static String IS_REBACK_YES="0";//正常任务
    public final static String IS_REBACK_NO="1";//下次重装
    
    //小库入库原因
    public final static String INREASON_FROM_BIGHOURSE = "0" ;//大库转入
    public final static String INREASON_INSTALL_TASK_FAITH = "1" ;//装机失败入库
    public final static String INREASON_VINDICATE_TASK_FAITH = "2" ;//维护失败入库
    public final static String INREASON_BORROW_BACK = "3" ;//借出返回入库
    public final static String INREASON_VINDICATE = "4" ;//维护入库
    public final static String INREASON_FIXED_RETURN = "5" ;//返修入库
    public final static String INREASON_BANKPOS = "6" ;//银行机具入库
    public final static String INREASON_MCHTRETURN = "7" ;//商户退机
    public final static String INREASON_COMPENSATE = "27" ;//报赔入库
    public final static String INREASON_OTHER = "90" ;//其他入库
    
    //大库入库原因
    //['1','厂家新购'],['2','厂家借用'],['3','厂家测试机'],['4','小库返还'],['5','特殊入库'],['6','商户退机']
    public final static String INREASON_BIG_NEWBUY = "8" ;//厂家新购
    public final static String INREASON_BIG_FACTROYLEND = "9" ;//厂家借用
    public final static String INREASON_BIG_FACTROYDEBUG = "10" ;//厂家测试机
    public final static String INREASON_BIG_SMALLHOURSERETURN = "11" ;//小库返还
    public final static String INREASON_BIG_SPRECIALIN = "12" ;//特殊入库
    public final static String INREASON_BIG_FIXBACK = "13" ;//银行返修入库
    public final static String INREASON_BIG_OTHER = "91" ;//其他入库
    
    
    //大库出库原因
    public final static String OUTREASON_BIG_TO_SMALLHOURSE = "14" ;//小库领用
    public final static String OUTREASON_BIG_BAD_CHANGE_GOOD = "15" ;//小库以坏换好
    public final static String OUTREASON_BIG_FIXED_CHANGE_GOOD = "16" ;//小库已修换好
    public final static String OUTREASON_BIG_TO_FACTORY_FIX = "17" ;//返厂维修
    public final static String OUTREASON_BIG_TO_FACTORY_CHANGE = "18" ;//返厂更换
    public final static String OUTREASON_BIG_BACK_TO_FACTORY = "19" ;//退回厂家
    public final static String OUTREASON_BIG_NATURAL_DAMAGE = "20" ;//自然损坏
    public final static String OUTREASON_BIG_TO_BRANCH = "21" ;//分公司领用
    public final static String OUTREASON_BIG_OTHER = "92" ;//其他方式出库
    
  //小库出库原因
    //public final static String OUTREASON_TO_BIGHOURSE = "22" ;//返大库
    public final static String OUTREASON_TO_BANK = "23";
    public final static String OUTREASON_TO_BIGHOURSE = "22" ;//返大库
    public final static String OUTREASON_INSTALL = "23" ;//装机出库
    public final static String OUTREASON_VINDICATE = "24" ;//维护出库
    public final static String OUTREASON_BORROW = "25" ;//借出出库
    public final static String OUTREASON_REPORTFIX = "26" ;//银行报修出库
    public final static String OUTREASON_OTHER = "93" ;//其他方式出库
 
    
    //菜单按钮
    public final static String MENU_CONFIG_ID = "menu_id";//菜单id
    public final static String BUTTON_CONFIG = "button";//按钮
    public final static String BUTTON_CONFIG_TEXT = "text";
    public final static String BUTTON_CONFIG_ID = "id";
    public final static String BUTTON_CONFIG_VALUE = "value";
    public final static String BUTTON_CONFIG_ACTION = "action";
    public final static String BUTTON_CONFIG_CLS = "iconCls";
    public final static String BUTTON_CONFIG_DISABLED = "disabled";
    public final static String BUTTON_CONFIG_WIDTH = "width";
    public final static String BUTTON_CONFIG_MARGIN = "margin";
    public final static String BUTTON_CONFIG_MARGIN_VALUE = "0 20 0 0";
    /**风险案例状态0-待指派**/
    public final static String CASE_ASSIGN_FLAG_WDIRECT="0";
    /**风险案例状态1-待完成**/
    public final static String CASE_ASSIGN_FLAG_WCOMP="1";
    /**风险案例状态2-待审核**/
    public final static String CASE_ASSIGN_FLAG_WSIGN="2";
    /**风险案例状态3-审核退回**/
    public final static String CASE_ASSIGN_FLAG_BACK="3";
    /**风险案例状态4-审核通过**/
    public final static String CASE_ASSIGN_FLAG_OK="4";
    /**案例结案状态0-未结案**/
    public final static String CASE_HANDLE_FLAG_NO="0";
    /**案例结案状态1-已结案**/
    public final static String CASE_HANDLE_FLAG_YES="1";
    
   
    /**渤海银行审核状态0-未发起审核**/
    public final static String BHBANK_VERIFY_NOTDEAL = "0" ;
    /**渤海银行审核状态1-未审核**/
    public final static String BHBANK_WATING_VERIFY = "1" ;
    /**渤海银行审核状态2-审核通过**/
    public final static String BHBANK_VERIFY_PASS = "2" ;
    /**渤海银行审核状态3-审核退回**/
    public final static String BHBANK_VERIFY_RETURN = "3" ;
    /**渤海银行审核状态4-申请撤销**/
    public final static String BHBANK_BACK_OUT = "4" ;
    
    /**互联网系统编号**/
    public final static String ONLINE_SYSTEM_CODE = "ONLINE" ;
    /**系统编号**/
    public final static String SYSTEM_CODE = "ACT" ;
    /**渤海银行单笔交易交易码**/
    public final static String SINGLE_TXN_CODE = "PAY001" ;
    /**渤海银行交易状态查询交易码**/
    public final static String CHECKSTATUS_TXN_CODE = "PAY002" ;
    /**渤海银行大额支付交易码**/
    public final static String SINGLE_LARGE_TXN_CODE = "PAY006" ;
    /**民生单笔代付交易码**/
    public final static String MS_PAY_TXN_CODE = "1002" ;
    /**渤海银行代付返回码 成功**/
    public final static String BH_SUCCESS="0" ;
    /**渤海银行代付返回码 失败**/
    public final static String BH_FAIL="1" ;
    /**渤海银行代付返回码 未知状态**/
    public final static String BH_NOTKNOWN="9" ;
    
    
    /**互联网系统返回码 成功**/
    public final static String ONLINE_SUCCESS="0" ;
    /**互联网系统返回码 失败**/
    public final static String ONLINE_FAIL="1" ;
    /**互联网系统返回码 未知状态**/
    public final static String ONLINE_NOTKNOWN="2" ;
    
    
    /**系统自动更新某条记录时，系统自身的操作员号**/
    public final static String SYSTEM_DEFAUT_OPR = "0000000000" ;
    
    /**需要渤海银行清算的数据来源  POSP**/
    public final static String SETTLE_POSP="10" ;
    /**需要渤海银行清算的数据来源  POSP+民生**/
    public final static String SETTLE_POSP_MS="11" ;
    /**需要渤海银行清算的数据来源  民生**/
    public final static String SETTLE_MS="01" ;
    
    /**渤海银行超级网银支付**/
    public final static String BH_PAY_TYPE_SUPERBANK="1" ;
    /**渤海银行大额支付**/
    public final static String BH_PAY_TYPE_LARGEAMOUNT="2" ;
    
    public final static String MS_PAY_TYPE ="8";
 
    /**日间报警间隔**/
    public final static String DAY_INTERVAL="c" ;
    /**夜间报警间隔**/
    public final static String NIGHT_INTERVAL="b" ;
    /**交易失败率**/
    public final static String FAIL_PERCENT="d" ;
    /**交易周期**/
    public final static String TRANS_INTERVAL="a" ;
    
    
    /**
     * 渤海打款的用途说明
     * 00 - 正常清结算打款 01-头寸调拨 02-结转手续费 03-结转风险准备金 04-结转利息收入 05-自有资金提出 06-退票
     */
    public final static String BH_USE_TYPE_00 = "00" ;
    public final static String BH_USE_TYPE_01 = "01" ;
    public final static String BH_USE_TYPE_02 = "02" ;
    public final static String BH_USE_TYPE_03 = "03" ;
    public final static String BH_USE_TYPE_04 = "04" ;
    public final static String BH_USE_TYPE_05 = "05" ;
    public final static String BH_USE_TYPE_06 = "06" ;
    
    /**action下载文件标识*/
    public final static String DOWNLOAD_FILE_CODE="$F";
}