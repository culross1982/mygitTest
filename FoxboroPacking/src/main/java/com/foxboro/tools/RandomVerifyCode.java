package com.foxboro.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成验证码
 * @author Administrator
 *
 */
public class RandomVerifyCode {
	private int width=100;//设置验证码框大小
	private int height=30;
	private String data="ABCDEFGHIJKLMNOPQRSTUVWXYZ";//定义字符串集
	private Random random=new Random();
	private String ranStr="";
	
	public void VerifyCode(HttpServletResponse response,HttpServletRequest request){
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		Graphics graphics=image.getGraphics();//取得此图片的绘图对象
		graphics.setColor(Color.LIGHT_GRAY);//设为灰色
		graphics.fillRect(0, 0, width, height);
		graphics.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
		graphics.setColor(Color.getHSBColor(21, 12, 22));
		for(int i=0;i<4;i++){
			int position=random.nextInt(data.length());//获取随机位置
			String randomStr=data.substring(position, position+1);//截取字符串集里的单个字符
			graphics.drawString(randomStr, width/5*(i+1), 20);//放置字符位置
			ranStr+=randomStr;
		}
		request.getSession().removeAttribute("RANDOMSTR");//清空session中的验证码
		request.getSession().setAttribute("RANDOMSTR", ranStr);//保存验证码
		graphics.dispose();
		try {
			ImageIO.write(image, "jpg",response.getOutputStream());//将图片格式化到输出流中
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
