/*
 * @(#)MD5.java 2017年10月24日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.util;

import java.security.MessageDigest;

/**
 *
 * @author zhaomy07
 * @date 2017年10月24日 下午1:44:05
 * @version V1.0.0 description：
 * 
 */
public class MD5 {
	
	// 公盐
	private static final String PUBLIC_SALT = "salt";
	
	// 十六进制下数字到字符的映射数组
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f" };

	/**
	 * 用户密码加密，盐值为 ：私盐+公盐
	 * 
	 * @param password 密码
	 * @param salt 私盐
	 * @return MD5加密字符串
	 */
	public static String encryptPassword(String password, String salt) {
		return encodeByMD5(PUBLIC_SALT + password + salt);
	}

	/**
	 * md5加密算法
	 * 
	 * @param originString
	 * @return
	 */
	public static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换字节数组为十六进制字符串
	 * 
	 * @param 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	public static void main(String[] args) {
		
		//1515430034-0-0-c69c4d6bfa67362721948b534ef34801
		/*Long time = 1444435200l;
		System.out.println("time="+time);
		String key = "aliyuncdnexp1234";
		System.out.println(encodeByMD5(key + time).toLowerCase());*/
		
		String p = "&client_key=56c3713c"
				+ "&country_code=cn"
				+ "&data=655454786_5256263723135106705_p99"
				+ "&downs="
				+ "&exp_tag=1_i/1603266382048571395_p0"
				+ "&exp_tag0="
				+ "&language=zh-Hans-CN;q=1"
				+ "&photoinfo="
				+ "&token=81066f8718bd4eb48374d9da833b6649-721695143";
		
		
		String a = "app=0"
				+ "appver=4.49.0.2116"
				+ "c=VIVO"
				+ "client_key=3c2cd3f3"
				+ "country_code=CN"
				+ "data=3xsew89k8xusa2u_3x24h3w8xsv9bwy_p10"
				+ "did=ANDROID_3a5fadf1b59f988a"
				+ "downs="
				+ "language=zh-cn"
				+ "lat=NaN"
				+ "lon=NaN"
				+ "mod=vivo(vivo X6Plus D)"
				+ "net=WIFI"
				+ "oc=VIVO"
				+ "os=android"
				+ "sys=ANDROID_5.1"
				+ "token=d41d8cd98f00b204e9800998ecf8427e-6134061"
				+ "ud=96052"
				+ "ver=4.49";

		String test = "app=0"
				+ "appver=4.49.0.2116"
				+ "c=VIVO"
				+ "client_key=3c2cd3f3"
				+ "country_code=CN"
				+ "data=3xsew89k8xusa2u_3x24h3w8xsv9bwy_p10"
				+ "did=ANDROID_3a5fadf1b59f988a"
				+ "downs="
				+ "language=zh-cn"
				+ "lat=NaN"
				+ "lon=NaN"
				+ "mod=vivo(vivo X6Plus D)"
				+ "net=WIFI"
				+ "oc=VIVO"
				+ "os=android"
				+ "sys=ANDROID_5.1"
				+ "token=d41d8cd98f00b204e9800998ecf8427e-6134061"
				+ "ud=96052"
				+ "ver=4.49"
				+ "382700b563f4";
		
		System.out.println(encodeByMD5(a).toLowerCase());
		System.out.println(encodeByMD5(test).toLowerCase());
		
		
		
		
		
		
	}
	
}
