/*
 * @(#)CommonUtil.java 2017年10月24日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;

/**
 *
 * @author zhaomy07
 * @date 2017年10月24日 下午1:52:48
 * @version V1.0.0
 * description：
 * 
 */
public class CommonUtil {

	/**
	 * 通过URL读取json数据
	 * 
	 * @param url
	 * @return
	 */
	public static String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL path = new URL(url);
			URLConnection yc = path.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "utf-8"));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求 (XML)
	 * @param url
	 * @param param
	 * @return
	 */
	public static String sendXmlPost(String url, String param){
		String result = "";
		try {  
            URL realUrl = new URL(url);  
            URLConnection con = realUrl.openConnection();  
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
			con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            con.setRequestProperty("Content-Type", "text/xml");  
  
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());      
            out.write(new String(param.getBytes("UTF-8")));  
            out.flush();  
            out.close();  
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));  
            String line = "";  
            for (line = br.readLine(); line != null; line = br.readLine()) {  
            	result += line;
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		return result;
	}

	
	
	/**
	 * 把字符串转成列表
	 * @param words
	 * @return
	 */
	public static List<String> getJSONArrayList(String words){
		try {
			if (words == null || "".equals(words.trim())) {
				return null;
			}
			if (words.startsWith("[")) {
				return JSONArray.parseArray(words, String.class);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 字符串反转
	 * @param str
	 * @return
	 */
	public static String reverse(String str){  
		if (str == null || "".equals(str.trim())) {
			return null;
		}
        return new StringBuilder(str).reverse().toString();  
    } 

	/**
	 * 生成随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for (int i=0;i<length;i++) {
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }

	public static String getSixNum(int s){
		DecimalFormat df = new DecimalFormat("000000");
		return df.format(s);
	}
	
	 /**
     * 验证手机号码
     * 
     * 移动号码段:139、138、137、136、135、134、150、151、152、157、158、159、182、183、187、188、147
     * 联通号码段:130、131、132、136、185、186、145
     * 电信号码段:133、153、146、148、166、180、189、198、199
     * 
     * @param cellphone
     * @return
     */
    public static boolean checkPhone(String phone) {
    	String regex = "^((13[0-9])|(14[5-8])|166|(15([0-3]|[5-9]))|(18([0,2-3]|[5-9]))|(19[8-9]))\\d{8}$"; 
    	Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 有些手机号是不能全部显示的，中间四位一般需要替换成*  [183****5678]
     * @param phone
     * @return
     */
    public static String replacePhone(String phone){
    	if (phone == null || "".equals(phone)) {
    		return "";
    	}
    	return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }
    
	/**
	 * 验证用户名 （由字母数字下划线组成 6-16位）
	 * @param userName
	 * @return
	 */
    public static boolean checkName(String userName){
    	Pattern p = Pattern.compile("[a-zA-Z0-9_]{6,16}");
        Matcher m = p.matcher(userName);
        return m.matches();
    }
	
    /**
     * 验证密码 匹配数字和字母  8-16位
     * @param password
     * @return
     */
    public static boolean checkPwd(String password){
    	String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";	
    	Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }
    
    public static boolean checkPayPwd(String password){
    	String regex = "^\\d{6}$";	
    	Pattern p = Pattern.compile(regex);
    	Matcher m = p.matcher(password);
    	return m.matches();
    }
    
    

    /**
     * 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X 
     * @param idCard
     * @return
     */
    public static boolean checkIdCard(String idCard){
    	String regex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
    	Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(idCard);
        return m.matches();
    }
    
    public static void main(String[] args) {
    	/*System.out.println(getRandomString(32));
		String password = "412801199001281";
		System.out.println(checkIdCard(password));
		System.out.println(replacePhone("18312501959"));*/
		/*System.out.println(checkPayPwd(""));
		System.out.println(getSixNum(1));*/
    	System.out.println(sendPost("http://api.gifshow.com/rest/n/clc/click?mod=vivo(vivo%20X6Plus%20D)&lon=NaN&country_code=CN&did=ANDROID_e556f1fea65c6e74&app=0&net=WIFI&oc=VIVO&ud=96052&c=VIVO&sys=ANDROID_5.1&appver=4.49.0.2116&language=zh-cn&lat=NaN&ver=4.49",
    			"downs="
    			+ "&token=d41d8cd98f00b204e9800998ecf8427e-423142"
    			+ "&data=3xsew89k8xusa2u_3xa5mgtyqjg5g3i_p10"
    			+ "&client_key=3c2cd3f3"
    			+ "&os=android"
    			+ "&sig=1713e82de6908b91defdf3aa0ea5a90d"));
    	/*for (int i = 0; i < 20; i++) {
    	}*/
	}
	
}
