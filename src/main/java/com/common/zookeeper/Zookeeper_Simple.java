/*
 * @(#)Zookeeper_Simple.java 2018年9月1日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 *
 * @author Administrator
 * @date 2018年9月1日 下午8:27:53
 * @version V1.0.0
 * description：
 * 
 */
public class Zookeeper_Simple implements Watcher{

	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper Zookeeper = new ZooKeeper("39.106.56.98:2181,39.106.56.98:2182,39.106.56.98:2183",
				5000, new Zookeeper_Simple());
		System.out.println(Zookeeper.getState());
		String path = Zookeeper.create("/zk-test/child1", "123".getBytes(), 
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(path);
		
		try {
			connectedSemaphore.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void process(WatchedEvent event) {
		System.out.println("Recevie watched Event" + event);
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}
	}

}
