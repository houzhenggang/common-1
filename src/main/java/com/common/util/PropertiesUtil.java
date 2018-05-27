package com.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author zhaomy07
 * @date 2017年11月6日 下午2:26:47
 * @version V1.0.0
 * description：
 */
public class PropertiesUtil {
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);

	public static Properties loadProperties(String fileName) {
		Properties props = null;
		try {
			props = new Properties();
			InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName + ".properties");
			props.load(is);
			is.close();
			return props;
		} catch (IOException e) {
			logger.error("加载" + fileName + ".properties文件出错！", e);
			return null;
		}
	}

	public static void main(String[] args) {
		Properties props = PropertiesUtil.loadProperties("cros");
		System.out.println(props.getProperty("ALLOW.ORIGIN.LIMIT"));
	}

}
