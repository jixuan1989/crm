package com.huateng.system.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huateng.common.SysParamConstants;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * Title:打印JPG图片
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2010-9-5
 * 
 * Company: Shanghai Huateng Software Systems Co., Ltd.
 * 
 * @author PanShuang
 * 
 * @version 1.0
 */
public class PrintImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedOutputStream outputStream =null;
		InputStream fileInputStream = null;
		try{
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
			response.setDateHeader("Expires", 0);
			
			System.out.println("PrintImage invoked");
			byte[] fileName = request.getParameter("fileName").getBytes("ISO-8859-1");
			String name = new String(fileName,"GBK");
			String fileType = request.getParameter("fileType");
			String fileAddr = SysParamUtil.getParam(SysParamConstants.FILE_UPLOAD_DISK)+fileType+File.separator;
			String tmp = fileAddr;
			fileAddr += name;
			File file = new File(fileAddr);		
			
			if(!file.exists()){
				name = new String(fileName,"utf-8");
				fileAddr = tmp+name;
				file = new File(fileAddr);
			}
			
			if(!file.exists()) {
				fileInputStream = request.getSession().getServletContext().getResourceAsStream("/ext-4.2/resources/images/nopic.gif");
			} else {
				fileInputStream = new FileInputStream(file);
			}
			
			BufferedImage bufferedImage = ImageIO.read(fileInputStream);
			outputStream = new BufferedOutputStream(response.getOutputStream());
			JPEGImageEncoder imageEncoder = JPEGCodec.createJPEGEncoder(outputStream);
			JPEGEncodeParam encodeParam = JPEGCodec.getDefaultJPEGEncodeParam(bufferedImage);
			encodeParam.setQuality(1f, true);
			imageEncoder.encode(bufferedImage, encodeParam);
			
			byte[] data = new byte[8192];
			
			int len = -1;
			
			while((len = fileInputStream.read(data, 0, 8192)) != -1) {
				outputStream.write(data, 0, len);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(outputStream !=null){
				outputStream.flush();
				outputStream.close();
				outputStream=null;
			}
			if(fileInputStream != null){
				fileInputStream.close();
				fileInputStream=null;
			}			
		}		
	}
}
