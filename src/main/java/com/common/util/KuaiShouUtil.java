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

	private static String gifshowClick() {
		String deviceId = "ANDROID_" + getSuffixDeviceId(16);
		//deviceId = "ANDROID_e556f1fea65c6e74";
		String token = getTokenRandom();
		//token = "d41d8cd98f00b204e9800998ecf8427e-423142";
		String userId = "655454786";
		String photoId = "5235997525079428439";
		String url = "http://api.gifshow.com/rest/n/clc/click?"
				+ "mod=vivo"
				+ "&lon=NaN"
				+ "&country_code=CN"
				+ "&did=" + deviceId
				+ "&app=0"
				+ "&net=WIFI"
				+ "&oc=VIVO"
				+ "&ud=721695143"
				+ "&c=VIVO"
				+ "&sys=ANDROID_5.1"
				+ "&appver=5.7.4.6246"
				+ "&language=zh-cn"
				+ "&lat=NaN"
				+ "&ver=5.7";
		
		String sigParam = "app=0"
				+ "appver=5.7.4.6246"
				+ "c=VIVO"
				+ "client_key=3c2cd3f3"
				+ "country_code=CN"
				+ "data=" + userId + "_" + photoId + "_p5"
				+ "did=" + deviceId
				+ "downs="
				+ "language=zh-cn"
				+ "lat=NaN"
				+ "lon=NaN"
				+ "mod=vivo"
				+ "net=WIFI"
				+ "oc=VIVO"
				+ "os=android"
				+ "sys=ANDROID_5.1"
				+ "token=" + token
				+ "ud=721695143"
				+ "ver=5.7"
				+ "382700b563f4";
		
		String sig = MD5.encodeByMD5(sigParam).toLowerCase();
		
		String postParam = "downs="
    			+ "&token=" + token
    			+ "&data=" + userId + "_" + photoId + "_p5"
    			+ "&client_key=3c2cd3f3"
    			+ "&os=android"
    			+ "&sig=" + sig;
		
		String result = CommonUtil.sendPost(url, postParam);
		return result;
	}

	public static void getGifshowClickNew(){
		
		String url = "http://api.gifshow.com/rest/n/clc/click?"
				+ "appver=5.6.2.412"
				+ "&did=CF8C80C3-1ECC-46E3-B06F-2DDD4F1CEBD4"
				+ "&c=a"
				+ "&ver=5.6"
				+ "&ud=721695143"
				+ "&lon=113.9693722599014"
				+ "&priorityType=1"
				+ "&lat=22.54654462827511"
				+ "&sys=ios10.3.2"
				+ "&mod=iPhone7%2C2"
				+ "&net=%E4%B8%AD%E5%9B%BD%E7%A7%BB%E5%8A%A8_5";
		
		
		String a = "__NStokensig=a6c418ad16f5546344e6e11c6e0d77e8bbaa8dbbb8af71e85c45af3b53426145"
				+ "&client_key=56c3713c"
				+ "&country_code=cn"
				+ "&data=655454786_5233745724099940968_p6"
				+ "&downs="
				+ "&exp_tag=1_i%2F1603350107535974402_f81"
				+ "&exp_tag0="
				+ "&language=zh-Hans-CN%3Bq%3D1"
				+ "&photoinfo="
				+ "&sig=c22d8f9b4182f0cc52de681b257c4aa8"
				+ "&token=ba78f147720b4117be25745c2858d0a2-721695143";
		
		String sigParam = "appver=5.6.2.412"
				+ "c=a"
				+ "client_key=56c3713c"
				+ "country_code=cn"
				+ "data=655454786_5233745724099940968_p6"
				+ "did=CF8C80C3-1ECC-46E3-B06F-2DDD4F1CEBD4"
				+ "downs="
				+ "exp_tag=1_i/1603350107535974402_f81"
				+ "exp_tag0="
				+ "language=zh-Hans-CN;q=1"
				+ "lat=22.54654462827511"
				+ "lon=113.9693722599014"
				+ "mod=iPhone7,2"
				+ "net=中国移动_5"
				+ "photoinfo="
				+ "priorityType=1"
				+ "sys=ios10.3.2"
				+ "token=ba78f147720b4117be25745c2858d0a2-721695143"
				+ "ud=721695143"
				+ "ver=5.6"
				+ "";
		
		String sig = MD5.encodeByMD5(sigParam).toLowerCase();
		System.out.println("sig="+sig);
		
	}

	
	public static void getGifshowClickNew2(){
		
		String url = "http://api.gifshow.com/rest/n/clc/click?"
				+ "app=0"
				+ "&lon=0"
				+ "&did_gt=1529138968750"
				+ "&priorityType=1"
				+ "&c=MYAPP%2C1"
				+ "&sys=ANDROID_4.4.2"
				+ "&mod=Tencent%28virtual%20machine%202%29"
				+ "&did=ANDROID_5522a09bb500d82f"
				+ "&ver=5.7"
				+ "&net=WIFI"
				+ "&country_code=CN"
				+ "&iuid="
				+ "&appver=5.7.4.6246"
				+ "&max_memory=128"
				+ "&oc=MYAPP%2C1"
				+ "&ftt=K-T-T"
				+ "&ud=721695143"
				+ "&language=zh-cn"
				+ "&lat=0";
		
		String a = "data=655454786_5235997525079428439_p5"
				+ "&exptag0="
				+ "&exptag=1_i%2F1603418798930878475_p0"
				+ "&downs="
				+ "&photoinfo=655454786%2F_"
				+ "&os=android"
				+ "&client_key=3c2cd3f3"
				+ "&token=51619cc4051949a58cbb16adf98d19dc-721695143"
				+ "&__NStokensig=ce84a8b02b486460f666eb628f71f737140b1e761f368486855cbb837ce84bd6"
				+ "&sig=a1e0ddd64062f6d110685e572952909c";
		
		String sigParam = "app=0"
				+ "appver=5.7.4.6246"
				+ "c=MYAPP,1"
				+ "client_key=3c2cd3f3"
				+ "country_code=CN"
				+ "data=655454786_5235997525079428439_p5"
				+ "did=ANDROID_5522a09bb500d82f"
				+ "did_gt=1529138968750"
				+ "downs="
				+ "exptag=1_i/1603418798930878475_p0"
				+ "exptag0="
				+ "ftt=K-T-T"
				+ "iuid="
				+ "language=zh-cn"
				+ "lat=0"
				+ "lon=0"
				+ "max_memory=128"
				+ "mod=Tencent(virtual machine 2)"
				+ "net=WIFI"
				+ "oc=MYAPP,1"
				+ "os=android"
				+ "photoinfo=655454786/_"
				+ "priorityType=1"
				+ "sys=sys=ANDROID_4.4.2"
				+ "token=51619cc4051949a58cbb16adf98d19dc-721695143"
				+ "ud=721695143"
				+ "ver=5.7"
				+ "382700b563f4";
		
		String sig = MD5.encodeByMD5(sigParam).toLowerCase();
		System.out.println("sig="+sig);
		
	}	

	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		/*for (int i=0; i<1000; i++) {
			String result = gifshowClick();
			System.out.println(result);
		}*/
		while (true) {
			System.out.println(gifshowClick());
			Thread.sleep(13000);
		}
		//getGifshowClickNew2();
	}
	
}
