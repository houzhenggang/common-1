package com.common.util;

/**
*
* @author zhaomy07
* @date 2017年12月17日 上午10:58:37
* @version V1.0.0
* description：
* 
*/
public class QrcodeUtil {

	public static final String URL = "http://qr.liantu.com/api.php?&w=300&text=";
	
	/**
	 * 二维码生成图片
	 * @param text
	 * @return
	 */
	public static String createCode(String text){
		if (text == null || "".equals(text)) {
			return null;
		}
		return URL + text;
	}
	
}
