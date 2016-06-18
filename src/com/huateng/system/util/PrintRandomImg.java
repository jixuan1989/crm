package com.huateng.system.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * Title:打印验证码图片
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
public class PrintRandomImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintRandomImg() {
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
			//生成4位随机数
			String random =createRandom();
			//将随机数放入session
			request.getSession().setAttribute("randomCode", random);
			//生成图片
			BufferedImage bufferedImage = creatImage(random);
			//将图片以流形式返回
			ServletOutputStream out = response.getOutputStream();
		    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		    encoder.encode(bufferedImage);
		    out.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(outputStream !=null){
				outputStream.flush();
				outputStream.close();
			}
			if(fileInputStream != null){
				fileInputStream.close();
			}			
		}		
	}
	/**
	 * 生成4位的随机数字
	 * @return
	 */
	private  String createRandom(){
	    String random = "";
	    for (int i = 0; i < 4; ++i) {
	      random = random + (int)(Math.random() * 10.0D);
	    }
	    return random;
	}
	/**
	 * 生成图片buffer
	 * @param img
	 * @return
	 */
	public BufferedImage creatImage(String img){
	    int width = 60; int height = 22;
	    BufferedImage image = new BufferedImage(width, height,1);

	    Graphics g = image.getGraphics();

	    Random random = new Random();

	    g.setColor(Color.white);
	    g.fillRect(0, 0, width, height);

	    g.setFont(new Font("Times New Roman", 1, 16));

	    g.setColor(Color.black);
	    for (int i = 0; i < 50; ++i) {
	      int x = random.nextInt(width);
	      int y = random.nextInt(height);

	      g.drawLine(x, y, x, y);
	    }

	    g.setColor(Color.GREEN);
	    g.drawString(img, 10, 17);

	    g.dispose();
	    return image;
	}
}
