package com.huateng.system.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

public class MsgSendUtil {
	public static void sendMsg(String ip,String port,String str,String sysId,String tel,String key){
		SocketClient socket;
		try {
			socket = new SocketClient(ip,Integer.parseInt(port),90000);
			String sendStr = tel + str + sysId + key;
			String md5 = getMD5(sendStr);
			String msg = "<Root code=\"ESB001\">" ;
			msg =  msg+ "<mobileNo>" + tel + "</mobileNo>" ;
			msg =  msg+ "<msgInfo>"+ str + "</msgInfo>" ;
			msg =  msg+ "<sysId>" + sysId + "</sysId>" ;
			msg =  msg+ "<md5Code>"+md5+"</md5Code>" ;
			msg =  msg+ "</Root>\n" ;
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOut(),Charset.forName("utf-8"))) ;
			bw.write(msg) ;
			bw.flush() ;
			byte[] b = new byte[2048] ;
			socket.getIn().read(b) ;
			System.out.println(new String(b));
			socket.close() ;
			bw.close() ;
		} catch (NumberFormatException | IOException e1) {
			e1.printStackTrace();
		}
	}
//	短信验证MD5加密
	public static String getMD5(String data) {
		byte[] source = data.getBytes();
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };				// 用来将字节转换成16进制表示的字符
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();						// MD5 的计算结果是一个 128 位的长整数，
			char str[] = new char[16 * 2];					// 每个字节用 16 进制表示的话，使用两个字符， 所以表示成16
			int k = 0;										// 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) {					// 从第一个字节开始，对 MD5 的每一个字节转换成 16
				byte byte0 = tmp[i];						// 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];	// 取字节中高 4 位的数字转换,
				str[k++] = hexDigits[byte0 & 0xf];			// 取字节中低 4 位的数字转换
			}
			s = new String(str);							// 换后的结果转换为字符串

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return s;
	} 
}
