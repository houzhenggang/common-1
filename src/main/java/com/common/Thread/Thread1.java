/*
 * @(#)Thread1.java 2018年8月1日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.Thread;

/**
 *
 * @author Administrator
 * @date 2018年8月1日 下午7:21:04
 * @version V1.0.0 description：
 * 
 */
public class Thread1 extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "正在执行....");
	}
	
	
	public static void main(String[] args) {
		for (int i = 0; i<20; i++) {
			new Thread1().start();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
