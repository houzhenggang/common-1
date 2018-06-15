/*
 * @(#)HttpClientUtil.java 2018年6月14日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Administrator
 * @date 2018年6月14日 下午8:44:58
 * @version V1.0.0 description：
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {
	
	public static String doPost(String url, Map<String, String> map, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
			httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			httpPost.setHeader("Connection", "keep-alive");
			httpPost.setHeader("Content-Type", "text/plain;charset=UTF-8");
			httpPost.setHeader("Host", "live.kuaishou.com");
			httpPost.setHeader("Origin", "https://live.kuaishou.com");
			httpPost.setHeader("Cookie", "clientid=3; did=web_46e1c518abacbc4de11b90abe4df6e40; client_key=65890b29; _ga=GA1.2.1896530350.1522473369; didv=2; live_deviceid=web_46e1c518abacbc4de11b90abe4df6e40; userId=721695143; Hm_lvt_86a27b7db2c5c0ae37fee4a8a35033ee=1528910632,1528976004; kuaishou.live.web_st=ChRrdWFpc2hvdS5saXZlLndlYi5zdBKgAdsm/NTja07SgwaP+HcgcgyFZcqyzgUvcXq1v6M0dgmjC0gncnWO5/DMImuMwyc1WhrSvsI+05hcgUIlY97tV7VIZtF4tq/Mx1cva8PMWEhJNQR0NpZFDluvXSq/EWUhFK7FadxbExooe0uIfIgZzCQUMruaF3PAGH9B5QZ/MfbA9inG2KxPQ04LqyRQJTN+aWuOleFP1+iOYtXqV1/NfrkaEqtYXSf19kp4pfaTtPC7dufEBiIgRqELQisrc42enR/YtDRWiLQ50f5E4CeWyqm8q3iLpjsoBTAB; kuaishou.live.web_ph=5c3c11b19455e9e4fc88125641b55a7bf9f0");
			httpPost.setHeader("Referer", "https://live.kuaishou.com/u/3xsew89k8xusa2u/3xpwknptgykmdn4");
			httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
			
			HttpResponse response = httpClient.execute(httpPost);
			/*response.setHeader("Access-Control-Allow-Credentials","true");
			response.setHeader("Access-Control-Allow-Origin","https://live.kuaishou.com");
			response.setHeader("Connection", "keep-alive");
			response.setHeader("Content-Encoding","gzip");
			response.setHeader("Content-Type","application/json;charset=UTF-8");
			response.setHeader("Transfer-Encoding","chunked");*/
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) throws InterruptedException {
		String url0 = "https://live.kuaishou.com/shortVideo/commentList";
		String url = "https://live.kuaishou.com/history/list";
		String url1 = "https://live.kuaishou.com/pay/key";
		String url2 = "https://live.kuaishou.com/user/login";
		String url3 = "https://live.kuaishou.com/rest/wd/live/web/log";
		String url4 = "https://live.kuaishou.com/u/3xsew89k8xusa2u/3x24h3w8xsv9bwy";
		String charset = "utf-8";
		Map<String,String> createMap = new HashMap<String,String>();  
	    /*System.out.println(doPost(url, createMap, charset));
	    System.out.println(doPost(url1, createMap, charset));*/
		long time = System.currentTimeMillis();
		/*createMap.put("base","{'session_id':'C8W7P0VspqkHbv_T',"
	    		+ "'page_id':'IDHEDKLy8IKb_Bfs_1528989307174','refer_page_id':'p0r9EgwExRsSB2WZ_1528989301246',"
	    		+ "'refer_show_id':'','refer_url':'https://live.kuaishou.com/u/3xsew89k8xusa2u/3x24h3w8xsv9bwy?cc=copylink&fid=721695143&timestamp=1528978779212&type=2&et=1_i%2F1603250440607404032_f81&userId=3xsew89k8xusa2u&photoId=3x24h3w8xsv9bwy',"
	    		+ "'page_live_stream_id':'','url':'https://live.kuaishou.com/u/3xsew89k8xusa2u/3x24h3w8xsv9bwy',"
	    		+ "'screen':'1920*1080','platform':'Win32','log_time':'"+ time +"'}");  
	    createMap.put("page","1");  
	    createMap.put("count","20");  
	    createMap.put("events",""); */
	    /*System.out.println(doPost(url4, createMap, charset));*/
	    //System.out.println(doPost(url3, createMap, charset));
	    //System.out.println(doPost(url0, createMap, charset));
	    /*createMap.put("authToken","ChRrdWFpc2hvdS5saXZlLndlYi5hdBKgAb2R17gvCoTp0aT56S5G8zq0MIsFVzP3fkrQbWsTJl6vR5KHCDcIRHYYLDf7tMwId8aA9TYxtXkkXsXgKmyQGFG9XPymBZYtUf3Dtr1Q3d17QH7UwKqFhA54Y5LoBRBytX0TQ5rDQZ7XwiiwTUICUWaDryJrNHq7+ajQBUhALT5tFP0EtTnVTCRCWxwul4zITDflEey/Hjyv7XUw9Y3Ya7gaErnk7K3szEIAj49h66SvNbb+kSIgjtgdM64aHVfk9QSvu4+JnWXvNotc6QIE2VWWEfrRfk4oBTAB");  
	    createMap.put("sid","kuaishou.live.web");  
	    System.out.println(doPost(url2, createMap, charset));*/
	    String newUrl = "http://api.gifshow.com/rest/n/clc/click?mod=vivo(vivo%20X6Plus%20D)&lon=NaN&country_code=CN&did=ANDROID_9d53e66a6e0d94bc&app=0&net=WIFI&oc=VIVO&ud=96052&c=VIVO&sys=ANDROID_5.1&appver=4.49.0.2116&language=zh-cn&lat=NaN&ver=4.49";
	    createMap.put("downs", "");
	    createMap.put("token", "d41d8cd98f00b204e9800998ecf8427e-4631196");
	    createMap.put("data", "3xsew89k8xusa2u_3xjexn9ra2h2ahc_p10");
	    createMap.put("client_key", "3c2cd3f3");
	    createMap.put("os", "android");
	    createMap.put("sig", "81971bbedf68604fba0242e22a6ffbde");
	    System.out.println(doPost(newUrl, createMap, charset));
	    
	}
	
}