package com.huateng.bo.base;

import com.huateng.po.base.TblKqTermInf;
import com.huateng.po.base.TblKqTermInfPK;
import com.huateng.po.base.TblKqTermKey;

/**
 * Title:快钱终端管理
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-8-10
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author 
 * 
 * @version 1.0
 */
public interface T10233BO {

	public String add(TblKqTermInf tblKqTermInf,TblKqTermKey tblKqTermKey);

	public String update(TblKqTermInf tblKqTermInf);

	public String delete(TblKqTermInfPK tblKqTermInfPK);

}
