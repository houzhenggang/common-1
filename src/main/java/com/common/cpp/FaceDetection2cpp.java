/*
 * @(#)FaceDetection2cpp.java 2018年5月15日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.cpp;

/**
 *
 * @author Administrator
 * @date 2018年5月15日 下午8:30:24
 * @version V1.0.0
 * description：
 * 
 */
public class FaceDetection2cpp {

	static{
		System.loadLibrary("FaceDetection_simple");
	}
	
	public native void detector(String imgPath, String modelPath);
	
	public static void main(String[] args) {
		FaceDetection2cpp cpp = new FaceDetection2cpp();
		cpp.detector("D:/seeta/0202.jpg", "F:/SeetaFaceEngine-master/FaceDetection/model/seeta_fd_frontal_v1.0.bin");
		System.out.println("success");
	}
	
}
