package com.java.parallel.processing.threads;

import java.util.concurrent.TimeUnit;

/**
 * @author nsajekar
 */
public class ThreadPool5 implements Runnable {
	static int count = 0;
	int id;
	String  taskId ;
	long sleep;

	@Override
	public void run() {
		boolean isRunningDeamonThread = Thread.currentThread().isDaemon();
		String type = isRunningDeamonThread ? "DEAMON" : "NORMAL";
		System.out.println("#### [" +Thread.currentThread().getName()+ "] "+ "TYPE = " +type+ "< " + taskId + " >#### STARTED");
		for (int i = 0; i < 2; i++) {
			System.out.println("thread id :: " + taskId + " [ Thread name :: " + Thread.currentThread().getName() + "] "+ "TYPE = <" +type +" >");
			try {
				TimeUnit.MILLISECONDS.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("#### [" +Thread.currentThread().getName()+ "] "+ "TYPE = " +type+ "< " + taskId + " >#### ENDED");
	}

	public ThreadPool5(long sleep) {
		this.id = ++count;
		this.taskId = "ThreadPool5- " +id;
		this.sleep = sleep;
	}
}
