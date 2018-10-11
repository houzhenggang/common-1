package com.common.transfer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhaomingyang9
 * @date 2018年10月11日 下午3:39:59
 * @version V1.0.0
 * description：
 * 
 */
public class CommonTransferUtil {

	public static final String comma = ",";
	
	public static List<String> StringToList(String str){
		List<String> list = new ArrayList<String>();
		if (str != null && !"".equals(str.trim())) {
			String[] strs = str.split(comma);
			for (String s : strs) {
				list.add(s);
			}
		} 
		return list;
	}
	
	public static void main(String[] args) {
		System.out.println(StringToList("123,hha,hhe,h,"));
		System.out.println(StringToList(""));
		System.out.println(StringToList(" "));
	}
	
}
