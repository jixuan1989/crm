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
package com.huateng.system.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.huateng.log.Log;

/**
 * Title:加密
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
public class Encryption {
	/**
	 * 3des 密钥共24字节，48位
	 */
	private static final String DESEDEKEY= "1234567890" +
    		                              "1234567890" +
    		                              "1234567890" +
    		                              "1234567890" +
    		                              "12345678";
    
    private static final String Algorithm = "DESede";
    
    private static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			log(e1.getMessage());
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
			log(e2.getMessage());
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
			log(e3.getMessage());
		}
		return null;
	}
    /**
     * 加密
     * @param src
     * @return
     * @throws Exception
     */
    public static String encrypt(String src) throws Exception{
	    byte[] encoded = encryptMode(hex2byte(DESEDEKEY), src.getBytes());
	    return byte2hex(encoded);
	}
    
    /**
     * 转换成十六进制字符串
     * @param b
     * @return
     */
	private static String byte2hex(byte[] b) {

		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {

			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

			if (stmp.length() == 1)
				hs = hs + "0" + stmp;

			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}
	
	/**
	 * 16进制表示的字符串转换成字节数组 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	private static byte[] hex2byte( String s ) throws Exception {
		char c,c1;
		int x;
		if( s.length() %2 != 0 )
			throw new Exception( "密钥格式不正确" );
		byte [] ret=new byte[s.length()/2];
		
		for( int i=0;i<s.length();i++ ){
			c=s.charAt(i);c1=s.charAt(++i);
			if( !(c>='0' && c<='9' || c>='A' && c<='F' || c>='a' && c<='f' ))
				throw new Exception( "密钥格式不正确" );
			if( !(c1>='0' && c1<='9' || c1>='A' && c1<='F' || c1>='a' && c1<='f' ))
				throw new Exception( "密钥格式不正确" );
			x = Integer.decode( "0x"+c+c1 ).intValue();
			if( x>127 ){
				ret[i/2]=(byte)(x|0xffffff00);
			}else{
				ret[i/2]=(byte)(x);
			}
		}
		return ret;
	}
	
    /**
     * 记录系统日志
     * @param info
     */
    private static void log(String info) {
    	Log.log(info);
    }
}
