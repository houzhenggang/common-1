package com.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

/**
 * 将图片转换为Base64
 * 将base64编码字符串解码成img图片
 * 
 * @创建时间 2017-11-25 13:50
 *
 */
public class Base64Util {

	/**
	 * 将图片转换成Base64编码
	 * @param imgFile 待处理图片
	 * @return
	 */
	public static String getImgStr(String imgFile) {
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64(data));
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * @param imgStr 图片数据
	 * @param imgFilePath 保存图片全路径地址
	 * @return
	 */
	public static String generateImage(String imgStr, String userType) {
		if (imgStr == null || "".equals(imgStr.trim())) // 图像数据为空
			return "";

		if (imgStr.startsWith("http")) {
			return imgStr;
		}
		
		try {
			// Base64解码
			byte[] b = org.apache.commons.codec.binary.Base64.decodeBase64(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			String childFolder = getDate(new Date(), "yyyy");
            String childChildFolder = getDate(new Date(), "MMdd");
            String tempPath = UploadHelper.getAttachSavePath();
            String filePath = userType + File.separator + childFolder +
            		File.separator + childChildFolder + File.separator;
            // 建立目录
            String newFilePath = tempPath + filePath;
            File f1 = new File(newFilePath);
            if (!f1.exists()) {
                f1.setWritable(true);
                f1.mkdirs();
            }
			// 生成jpeg图片
            String ranNum = String.valueOf(1 + (int) (Math.random() * 1000));
            String fileName = getDate(new Date(), "yyyyMMddHHmmss") + ranNum + ".JPEG";
            String imgFilePath = newFilePath + fileName;
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return FileAddress.UPLOAD_URL + filePath + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private static String getDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat(format);
        return sdFormat.format(date);
    }
	
	public static void main(String[] args) {
		String imgFile = "d:\\Desert.jpg";// 待处理的图片
		String imgbese = getImgStr(imgFile);
		System.out.println(imgbese.length());
		System.out.println(imgbese);
		String imgFilePath = "data";// 新生成的图片
		String s = generateImage(imgbese, imgFilePath);
		System.out.println(s);
	}
}
