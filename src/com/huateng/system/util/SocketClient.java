package com.huateng.system.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
	
	private Socket socket;
	private InputStream in;
	private OutputStream out;	
	private BufferedReader reader;
	public SocketClient(String host,int port) throws IOException{
		socket = new Socket();  
        socket.setSoTimeout(20000);
        socket.setSoLinger(true, 100);
        socket.connect(new InetSocketAddress(host, port));
        in = socket.getInputStream();  
        out = socket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(in));
	}
	
	/**
	 * 中间业务平台备付金查询
	 * @throws IOException
	 */
	public void writeMsg() throws IOException{
		String s = "00436000078209603100313131002406000020000000C00000000014383632393938323338363235313031343930303530303233337C4531323935313130317C3531313031";
		byte [] b = CommonFunction.str2Bcd(s);
		out.write(b);
		out.flush();
	}
	
	
	/**
	 * 中间业务平台备付金查询
	 * @param len 跳过多少个字节读取
	 * @return 
	 * @throws IOException
	 */
	public String readMsg() throws IOException{
		byte [] tmp = new byte[2];
		in.read(tmp);
		int size = Integer.parseInt(CommonFunction.bytesToHexString(tmp),16);
		in.read(new byte[59]);
		byte[] b = new byte[size - 59];
		in.read(b);
		return new String(b,"gb18030");
	}
	
	public String readMsg(int len,int t) throws IOException{
		byte [] tmp = new byte[len];
		in.read(tmp);
		int size = Integer.parseInt(CommonFunction.bytesToHexString(tmp),16);
		in.read(new byte[t]);
		byte[] b = new byte[size-t];
		in.read(b);
		return new String(b,"gb18030");
	}
	
	public String readMsg(int len) throws IOException{
		byte [] tmp = new byte[len];
		in.read(tmp);
		byte[] b = new byte[Integer.parseInt(new String(tmp))];
		in.read(b);
		return new String(b,"gb18030");
	}
	
	public void writeMsg(String msg) throws IOException{
		out.write(msg.getBytes());
		out.flush();
	}
	public void writeMsg(byte [] msg) throws IOException{
		out.write(msg);
		out.flush();
	}
	
	public void close() throws IOException {
		if(!socket.isClosed()) {
			socket.shutdownInput();
			socket.shutdownOutput();
			socket.close();
		}
	}
	
	/**
	 * 发送数据
	 * @return OutputStream
	 */
	public OutputStream getOut() {
		return out;
	}
	
	/**
	 * 读取数据
	 * @return InputStream
	 */
	public InputStream getIn() {
		return in;
	}
	public String readString() throws IOException{
		boolean isOver = false;

		BufferedReader bf = new BufferedReader(new InputStreamReader(in));
		String str = null;
		while(!isOver)
		{
			str = bf.readLine();
			if(str != null)
				isOver = true;
		}
		return str;
	}
	public SocketClient(String host,int port,int timeOut) throws IOException{
		socket = new Socket();  
        socket.setSoTimeout(timeOut);
        socket.setSoLinger(true, 100);
        socket.connect(new InetSocketAddress(host, port));
        in = socket.getInputStream();  
        out = socket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(in));
	}
}
