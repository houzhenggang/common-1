/*
 * @(#)KuaishouUtil.java 2018年6月15日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.util;

import java.util.Random;

/**
 *
 * @author Administrator
 * @date 2018年6月15日 下午4:47:56
 * @version V1.0.0 description：
 * 
 */
public class KuaiShouUtil {

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getSuffixDeviceId(int length) {
		String str = "0123456789abcdef";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(16);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	public static String getTokenRandom() {
		Double random = Math.random() * 19999998 + 1;
		String tokenRandom = random.intValue() + "";
		return MD5.encodeByMD5(tokenRandom) + "-" + tokenRandom;
	}
	
	public static void main(String[] args) {
		String deviceId = "ANDROID_" + getSuffixDeviceId(16);
		String token = getTokenRandom();
		String userId = "3xsew89k8xusa2u";
		String photoId = "3x24h3w8xsv9bwy";
		String url = "http://api.gifshow.com/rest/n/clc/click?mod=vivo(vivo%20X6Plus%20D)&lon=NaN&country_code=CN"
				+ "&did=" + deviceId
				+ "&app=0&net=WIFI&oc=VIVO&ud=96052&c=VIVO"
				+ "&sys=ANDROID_5.1&appver=4.49.0.2116"
				+ "&language=zh-cn&lat=NaN&ver=4.49";
		
		String sigParam = "app=0"
				+ "appver=4.49.0.2116"
				+ "c=VIVO"
				+ "client_key=3c2cd3f3"
				+ "country_code=CN"
				+ "data=" + userId + "_" + photoId + "_p10"
				+ "did=" + deviceId
				+ "downs="
				+ "language=zh-cn"
				+ "lat=NaN"
				+ "lon=NaN"
				+ "mod=vivo(vivo X6Plus D)"
				+ "net=WIFI"
				+ "oc=VIVO"
				+ "os=android"
				+ "sys=ANDROID_5.1"
				+ "token=" + token
				+ "ud=96052"
				+ "ver=4.49"
				+ "382700b563f4";;
		
		String sig = MD5.encodeByMD5(sigParam);
		
		String postParam = "downs="
    			+ "&token=" + token
    			+ "&data=" + userId + "_" + photoId + "_p10"
    			+ "&client_key=3c2cd3f3"
    			+ "&os=android"
    			+ "&sig=" + sig;
		
		String result = CommonUtil.sendPost(url, postParam);
		
		System.out.println(result);
		
	}
	
}
