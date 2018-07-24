/*
 * @(#)RedisThreadA.java 2018年7月17日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.lock.redis;

/**
 *
 * @author Administrator
 * @date 2018年7月17日 下午3:15:25
 * @version V1.0.0
 * description：
 * 
 */
public class RedisThreadA extends Thread{

	private RedisService service;

    public RedisThreadA(RedisService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
	
    public static void main(String[] args) {
    	RedisService service = new RedisService();
        for (int i = 0; i < 50; i++) {
        	RedisThreadA threadA = new RedisThreadA(service);
            threadA.start();
        }
	}
    
}
