/*
 * @(#)MyThread.java 2018年6月17日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.Thread;

import com.common.util.CommonUtil;

/**
 *
 * @author Administrator
 * @date 2018年6月17日 下午7:39:57
 * @version V1.0.0
 * description：
 * 
 */
public class MyThread extends Thread{
	
	public void run(){
		for (int i=0;i<10000;i++) {
			String url = "http://livenew.gifshow.com/rest/n/live/like?"
					+ "appver=5.6.2.412&did=CF8C80C3-1ECC-46E3-B06F-2DDD4F1CEBD4&c=a&ver=5.6&ud=721695143&lon=113.944628644051&lat=22.54795870255951&sys=ios10.3.2&mod=iPhone7%2C2&net=%E4%B8%AD%E5%9B%BD%E7%A7%BB%E5%8A%A8_5";
			
			String a = "__NStokensig=2a53b949e091363fffc9964fd9f6cd17cc5591063b18b19fb26684812f555e5e&client_key=56c3713c&count=5&country_code=cn&language=zh-Hans-CN%3Bq%3D1&liveStreamId=NYAnLp4ykDw&sig=4fbf5db5ecbeff5cf9ccb329b318e258&token=734cb5872be94bf2bdca8e3d2a374d5f-721695143";
			
			System.out.println(CommonUtil.sendPost(url, a));
		}
    }

	
	public static void main(String[] args) {
		for (int i = 0; i<10;i++) {
			new MyThread().start();
		}
	}
	
}
