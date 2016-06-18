/**
	 * 操作员状态
	 */
	function oprState(val) {
		if(val == '0') {
			return '<img src="' + Ext.contextPath + '/ext/resources/images/active_16.png" title="可用"/>正常';
		} else if(val == '1') {
			return '<img src="' + Ext.contextPath + '/ext/resources/images/stop_16.png" title="锁定"/>锁定';
		} else if(val == '2') {
			return '添加待审查';
		}else if(val == '3') {
			return '修改待审核';
		}else if(val == '4') {
			return '审核不通过';
		}
		return '状态未知';
	}
	//1-押金 2-服务费 3-赔偿金 4-销售款
	
	function receiptType(val){
		if(val == "1")
			return "押金";
		else if(val == "2")
			return "服务费";
		else if(val == "3")
			return "赔偿金";
		else if(val == "4")
			return "销售款";
	}
	
	
/**
	 * 操作员性别
	 */
	
	function gender(val) {
		if(val == '0') {
			return '<img src="' + Ext.contextPath + '/ext/resources/images/male.png" />';
		} else if(val == '1') {
			return '<img src="' + Ext.contextPath + '/ext/resources/images/female.png" />';
		}
		return val;
	}
	//function 
	/** 操作类型 **/
	var ACTION_TYPE = [['1','增加'],["2","修改"],["3","停用"],["4","恢复"],["5","临时保存"],["0","正常"],["6","注销"],["7","锁定"]];
	/** 审核级别 **/
	var AUDIT_RANK = [["1","分公司"],["2","总公司"],["0","正常"]];
	/** 审核结果：通过 **/
	var AUDIT_RESULT = [["3","通过"],["1","待审核"],["2","退回"],["0","正常"],["4","待补录"],["5","补录完成待审核"],["6","补录复核退回"]];
	var status = "";
	/**
	 * 商户状态组合
	 */
	function getMchntSt(type){
		if(status != "")
			return Ext.decode(status);
		
		for(var i=0; i<ACTION_TYPE.length; i++){
			var actionType = ACTION_TYPE[i];
			var unType = actionType[0];
			if(unType == type){
				continue;
			}
			
			for(var j=0; j<AUDIT_RANK.length; j++){
				var auditRank = AUDIT_RANK[j];
				if(auditRank[0] == 0)
					continue;
				
				for(var k=0; k<AUDIT_RESULT.length; k++){
					var auditResult = AUDIT_RESULT[k];
					if(auditResult[0] == 0)
						continue;
					
					if(unType == 3 || unType ==4){
						if(auditResult[0]== 4 || auditResult[0] == 5 || auditResult[0] == 6){
							continue;
						}
					}
					if(auditResult[0] == 3)//通过状态
						continue;
					
					if((auditResult[0] == 3 && auditRank[0] ==2)//通过（总公司）
						|| (auditResult[0] == 5 && auditRank[0] ==2) //补录完成待审核（总公司）
						|| (auditResult[0] == 6 && auditRank[0] ==2))//补录复核退回（总公司）
						continue;
					
					var value = "";
					var name = "";
					if(unType == 1 || unType == 2 || unType == 3 || unType == 4){
						if( unType == 3 || unType == 4){
							if(auditResult[0] != 1 && auditResult[0] != 2){
								continue;
							}
						}
						value = actionType[0]+ auditRank[0]+auditResult[0];
						name = actionType[1]+ auditResult[1] + "（"+ auditRank[1]+ "）";
					}else {
						if(status.indexOf(actionType[1]) == -1){
							value = actionType[0]+ AUDIT_RANK[2][0]+AUDIT_RESULT[3][0];
							name = actionType[1];
						}
					}
					if(value != "" && name != "")
						status += "{'value':'"+value+"', 'name':'"+name+"'},";
				}
			}
		}
		status = "["+status.substring(0,status.length -1)+"]";
		return Ext.decode(status);
	}
	
	/**
	 * 商户状态
	 * @param val
	 * @returns {String}
	 */
	function mchtStatus(val){
			if(val == 1){
   				return "<font color=red>未启用</font>";
   			}else if(val == 0) {
   				return "<font color=green>正常</font>";
   			} else if(val == 2){
   				return "<font color=red>停用</font>";
   			}
   	}
	 /**
	  * 商户状态转译
	 */
	function mchntSt(val) {
		var v1 = val.substring(0,1);
		var v2 = val.substring(1,2);
		var v3 = val.substring(2,3);
		var v = "";
		if("0" == v1){
			return "<font color='green'>完成</font>";
		}else if("3" == v1 && "0" == v2 && "0" == v3){
			return "<font color='red'>完成</font>";//审核结束审核状态为正常，商户状态为停用
		}else if("6" == v1 && "0" == v2 && "0" == v3){
			return "<font color='red'>完成</font>";////审核结束审核状态为正常，商户状态为注销
		}else if("5" == v1 && "0" == v2 && "0" == v3){
			return "<font color='red'>临时保存</font>";
		}
		for(var i =0 ; i< ACTION_TYPE.length ; i++){
			var v0 = ACTION_TYPE[i][0];
			if(v0 == v1){
				v += ACTION_TYPE[i][1];
				break;
			}
		}
		
		for(var i =0 ; i< AUDIT_RESULT.length ; i++){
			var v0 = AUDIT_RESULT[i][0];
			if(v0 == v3){
				v += AUDIT_RESULT[i][1];
				break;
			}
		}
		
		for(var i =0 ; i< AUDIT_RANK.length ; i++){
			var v0 = AUDIT_RANK[i][0];
			if(v0 == v2){
				v += "（"+AUDIT_RANK[i][1]+"）";
				break;
			}
		}
		return v;
	} 
	
	/**
	  * 机构级别转换
	*/
	 function brhLvlRender(val) {
		 switch(val) {
			case '0': return '总公司';
			case '1': return '分公司';
			case '2': return '网点';
			default : return '未知';
		}
	 }
	 /**
	  * 机构类型转换
	*/
	 function brhTypeRender(val) {
		 switch(val) {
			case '0': return '我方收单机构';
			case '1': return '银行收单机构';
			default : return '未知';
		}
	 }
	 
	 	/**
	     * 翻译是或者否
		 */
	 function yesOrNo(val) {
		 switch(val) {
			case '0': return '是';
			case '1': return '否';
			default : return '未知';
		}
	 }
	     
     /**
	     * 银行转换
		 */
	 function groupType(val) {
		 switch(val) {
			case '1': return '全国性集团';
			case '2': return '地方性集团（省内）';
			default : return '未知';
		}
	 }
	     
     /**
	     * 交易渠道转换
		 */
	 function channelType(val) {
		 switch(val) {
			case '0': return '间联POS';
			case '1': return '否';
			default : return '未知';
		}
	 }
     /**
	     * 卡类型转换
		 */
	 function cardType(val) {
		 switch(val) {
			case '01': return '借记卡';
			case '00': return '贷记卡';
//			case '00': return '他行银联卡';
//			case '01': return '本行借记卡';
//			case '03': return '本行一帐通';
//			case '04': return '本行贷记卡';
			default : return '未知卡';
		}
	 }
	     
     /**
	     * 交易名称转换
		 */
	 function funType(val) {
		 switch(val) {
			case '1101': return   '消费';
			case '1161': return   '查询';
			case '5151': return   '退货';
			case '2101': return   '消费冲正';
			case '3101': return   '消费撤消';
			case '4101': return   '撤消冲正';
			case '1011': return   '预授权';
			case '2011': return   '预授权冲正';
			case '3011': return   '预授权撤消';
			case '4011': return   '预授权撤消冲正';
			case '1091': return   '联机预授权完成';
			case '2091': return   '联机预授权完成冲正';
			case '3091': return   '联机预授权完成撤消';
			case '4091': return   '联机预授权完成撤消冲正';
			case '1111': return   '分期付款';
			case '2111': return   '分期付款冲正';
			case '1171': return   '积分查询';
			case '1121': return   '积分消费';
			case '2121': return   '积分消费冲正';
			case '3121': return   '积分撤消';
			case '4121': return   '积分撤消冲正';
			case '5161': return   '离线预授权完成';
			case '1131': return   '财务转账';
			case '3131': return   '转账撤销';
			case '1001': return   '明细查询';
			case '1000': return   '余额查询';
			default : return '未知交易类型';
		}
	 }
	     
     /**
	     * 服务等级转换
		 */
	 function svrLvlType(val) {
		 switch(val) {
			case '0': return   'VIP';
			case '1': return   '重点';
			case '2': return   '普通';
			default : return '未知卡';
		}
	 }
	 	
	 //终端库存状态
	 function translateState(val) {
//			if(val == '0')
//				return "已入库";
			if(val == '1')
				return "已作废";
//			if(val == '2')
//				return "下发未审核";
//			if(val == '3')
//				return "下发未审核";
			if(val == '4')
				return "已入库";
			if(val == '5')
				return "已出库";
			return val;
		}
	// 终端类型
	function termType(val) {
			if(val == 'P')
				return "POS";
			if(val == 'E')
				return "EPOS";
			if(val == 'A')
				return "ATM";
			return val;
		}
    // 终端状态
    function termSta(val) {
            if(val == '0')
                return "<font color='gray'>新增未审核</font>";
            if(val == '1')
                return "<font color='green'>启用</font>";
            if(val == '2')
                return "<font color='gray'>修改未审核</font>";
            if(val == '3')
                return "<font color='gray'>停用未审核</font>";
            if(val == '4')
                return "<font color='red'>停用</font>";
            if(val == '5')
                return "<font color='gray'>恢复未审核</font>";
            if(val == '6')
                return "<font color='gray'>注销未审核</font>";
            if(val == '7')
                return "<font color='red'>注销</font>";
            if(val == '8')
                return "<font color='blue'>新增审核拒绝</font>";
            if(val == '9')
                return "<font color='blue'>修改审核拒绝</font>";
            if(val == 'A')
                return "<font color='blue'>停用审核拒绝</font>";
            if(val == 'B')
                return "<font color='blue'>恢复审核拒绝</font>";
            if(val == 'C')
                return "<font color='blue'>注销审核拒绝</font>";
            if(val == 'D')
                return "复制未修改";
            return val;
        }
    // 终端签到状态
	function termSignSta(val) {
			if(val == '0')
				return "未签到";
			if(val == '1')
				return "已签到";
			if(val == '')
				return "ATM";
			return val;
		}
	function mechineBackSta(val) {
		if(val == '0')
			return "<font color='green'>机具正常</font>";
		if(val == '1')
			return "<font color='blue'>退机未退费</font>";
		if(val == '2')
			return "<font color='gray'>退机已退费</font>";
		if(val == '3')
			return "<font color='red'>尚未装机</font>";
		return val;
	}
    function termState(val) {
        if(val == '0')
            return "已申请";
        if(val == '1')
            return "已审核";
        return val;
    }    
    function clcAction(val) {
        if(val == '0')
            return "<font color='green'>关注</font>";
        if(val == '2')
            return "<font color='red''>拒绝</font>";
        if(val == '3')
            return "<font color='blue'>预警</font>";
        return val;
    }
    
    function BHPayAction(val) {
        if(val == '0')
            return "<font color='green'>未处理</font>";
        if(val == '1')
            return "未审核";
        if(val == '2')
            return "<font color='blue'>处理完成</font>";
        if(val == '3')
            return "<font color='red'>审核退回</font>";
        if(val == '4')
            return "<font color='gray'>申请撤销</font>";
        return val;
    }
    
    function payStatusFor(val){
    	  if(val == '0')
              return "<font color='red'>未发送</font>";
          if(val == '1')
              return "<font color='green''>已发送</font>";
    }
    
    function payStatus(val) {
        if(val == '0')
            return "<font color='green'>成功</font>";
        if(val == '1')
            return "<font color='red''>失败</font>";
        if(val == '9')
            return "<font color='blue'>状态未明</font>";
        return val;
    }
    
    function settleFlag(val) {
        if(val == '0')
            return "未处理";
        if(val == '1')
            return "<font color='red'>划拨失败</font>";
        if(val == '2')
            return "<font color='green'>划拨成功</font>";
        return val;
    }
    function taskType(val){
    	switch(val) {
			case '0': return   '装机任务';
			case '1': return   '换机任务';
			case '2': return   '维护任务';
			case '3': return   '配送耗材';
			case '4': return   '调单任务';
			case '5': return   '风险排查任务';
			case '6': return   '上门回访任务';
			case '7': return   '电话回访任务';
			case '8': return   '送修任务';
		}
    }
    function taskState(val){
    	switch(val) {
			case '1': return   "<font color='green'>新建</font>";
			case '2': return   "<font color='blue'>等待调机</font>";
			case '3': return   "<font color='#BBCC00'>等待派工</font>";
			case '4': return   "<font color='ff00AA'>等待处理</font>";
			case '5': return   "<font color='red'>完成</font>";
			case '6': return   "<font color='gray'>结束</font>";
			case '7': return   "<font color='red'>已删除</font>";
		}
    }
    function termStatus(val){
    	switch(val) {
		case '0': return   "<font color='red'>未启用</font>";
		case '1': return   "<font color='green'>正常</font>";
		case '4': return   "<font color='red'>停用</font>";
		case '7': return   "<font color='red'>注销</font>";
    	}
    }
    
    function checkAccount(val){
    	switch(val) {
		case '0': return   "对账初始化";
		case '1': return   "对账成功";
		case '2': return   "渤海有,POSP无";
		case '3': return   "渤海有, posp有, 收款账户不符";
		case '4': return   "渤海有, posp有, 交易金额不符";
		case '8': return   "失败交易, 不参与对账";
		case '9': return   "对账交易状态未明";
    	}
    }
    
    function settleDataSorurce(val){
    	switch(val) {
			case '0': return   "POSP系统";
			case '1': return   "互联网系统";
    	}
    }
    
    
    function BHPayType(val) {
        if(val == '1')
            return "渤海银行超级网银";
        if(val == '2')
            return "渤海银行大额支付";
        if(val == '8')
        	return "民生代付";
        return val;
    }
    
    function BHT0Flag(val) {
        if(val == '0')
            return "否";
        if(val == '1')
            return "是";
        return val;
    }
    
    
    function BHUseType(val) {
        if(val == '00')
            return "正常清结算打款";
        if(val == '01')
            return "头寸调拨";
        if(val == '02')
            return "结转手续费";
        if(val == '03')
            return "结转风险准备金";
        if(val == '04')
            return "结转利息收入";
        if(val == '05')
            return "自有资金提出";
        if(val == '06')
            return "退票";
        return val;
    }