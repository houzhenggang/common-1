/*
 * @(#)ImageLoginServlet.java 2018年5月19日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 * @date 2018年5月19日 下午11:26:21
 * @version V1.0.0
 * description：
 * 
 */
public class ImageLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	// 生成数字和字母的验证码
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedImage img = new BufferedImage(102, 32,
				BufferedImage.TYPE_INT_RGB);
		// 得到该图片的绘图对象
		Graphics g = img.getGraphics();
		Random r = new Random();
		Color c = new Color(255,255,255,255);
		g.setColor(c);
		// 填充整个图片的颜色
		g.fillRect(0, 0, 102, 32);
		
		StringBuffer sb = new StringBuffer();
		
		//备选字体  
	    //String[] fontTypes ={ "\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66" };  
	    //int fontTypesLength = fontTypes.length;
		
		// 向图片中输出数字和字母
		String letterBase = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numberBase = "01234567890123456789";
	    
	    //String totalBase = letterBase + chineseBase + numberBase;
	    String totalBase = letterBase + numberBase;
		char[] ch = totalBase.toCharArray();
		Random random = new Random();  
		int index, len = ch.length;
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			g.setFont(new Font("Arial", Font.ITALIC, 26));// 输出的字体和大小
			//g.setFont(new Font(fontTypes[random.nextInt(fontTypesLength)], Font.ITALIC, 26));// 输出的字体和大小
			g.drawString("" + ch[index], (i * 24) + 3, 28);// 写什么数字，在图片的什么位置画
			sb.append(ch[index]);
		}
		//产生随即干扰点  
        for (int i = 0; i < 10; i++) {  
            int x1 = random.nextInt(72);  
            int y1 = random.nextInt(26);  
            g.drawOval(x1, y1, 2, 2);  
        }  
        for(int i = 0; i < 6; i++) {  
            int x = random.nextInt(72);  
            int y = random.nextInt(26);  
            int x2 = random.nextInt(72);  
            int y2 = random.nextInt(26);  
            g.drawLine(x, y, x + x2, y + y2);  
        }
        g.setColor(c); // 将画笔的颜色再设置回去  
        g.dispose();
		request.getSession().setAttribute("picLogincode", sb.toString().toUpperCase());
		ImageIO.write(img, "JPG", response.getOutputStream());
	}
}