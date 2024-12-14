package com.java.parallel.processing.threads;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskA implements Runnable {

	private int a ;
	private  int b;
	private long sleepTime;
	private int sum;
	
	private volatile boolean done = false;
	
	private static int count = 0;
	private int instance;
	private String taskId;
	
	public ValueReturningTaskA(int a, int b, long sleepTime) {
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		this.instance = ++count;
		this.taskId = "valuereturningtaskA" +instance;
	}

	@Override
	public void run() {
		String currentThread = Thread.currentThread().getName();
		System.out.println("#### [" + currentThread + "]  <" + taskId + "> Started ####");
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sum = a + b ;
		System.out.println("#### [" + currentThread + "]  <" + taskId + "> Ended ####");
		done = true;
		synchronized (this) {
			System.out.println("#### [" + Thread.currentThread().getName() + "]  <" + taskId + "> Notifying ####");
			this.notify();
		}
	}
	
	public int getSum() throws InterruptedException {
		synchronized (this) {
			System.out.println("#### [" + Thread.currentThread().getName() + "]  <" + taskId + "> Waiting ####");
			if(!done) {
				this.wait();
			}
		}
		return sum;
	}

}
