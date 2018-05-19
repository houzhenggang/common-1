/*
 * @(#)FaceIdentification2cpp.java 2018年5月15日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.cpp;

/**
 *
 * @author Administrator
 * @date 2018年5月15日 下午11:28:21
 * @version V1.0.0
 * description：
 * 
 */
public class FaceIdentification2cpp {

	static{
		System.loadLibrary("FaceVerification");
	}
	
	public native float detector(String imgPath1, String imgPath2, 
			String detectionModelPath, String alignmentModelPath, String identificationModelPath);
	
	public static void main(String[] args) {
		String imgPath1 = "D:/seeta/b01.jpg";
		String imgPath2 = "D:/seeta/b03.jpg";
		String detectionModelPath = "F:/SeetaFaceEngine-master/FaceDetection/model/seeta_fd_frontal_v1.0.bin";
		String alignmentModelPath = "F:/SeetaFaceEngine-master/FaceAlignment/model/seeta_fa_v1.1.bin";
		String identificationModelPath = "F:/SeetaFaceEngine-master/FaceIdentification/model/seeta_fr_v1.0.bin";
		
		FaceIdentification2cpp cpp = new FaceIdentification2cpp();
		float sum = cpp.detector(imgPath1, imgPath2, detectionModelPath, alignmentModelPath, identificationModelPath);
		
		
		System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
		System.out.println("匹配率小于0.6可认为不是同一个人");
		
		if (sum < 0.6f) {
			System.out.println("FAIL----->人脸检测匹配不成功，匹配率为：" + sum);
		} else {
			System.out.println("SUCCESS-->人脸检测匹配成功，匹配率为：" + sum);
		}
		
	}
	
}
