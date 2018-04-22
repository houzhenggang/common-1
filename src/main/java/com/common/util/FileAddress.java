package com.common.util;

import java.util.Properties;

public class FileAddress {

	public static final String UPLOAD_URL;//文件、图片上传地址

	static {
		// 文件、图片上传地址
		Properties props = PropertiesUtils.loadProperties("config");
		UPLOAD_URL = props.getProperty("upload_url");
	}
	
}
