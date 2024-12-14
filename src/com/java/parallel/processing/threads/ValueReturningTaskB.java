package com.java.parallel.processing.threads;

import java.util.concurrent.TimeUnit;

import com.java.parallel.processing.utils.ResultListener;

public class ValueReturningTaskB implements Runnable {

	private int a ;
	private  int b;
	private long sleepTime;
	private int sum;
	
	private ResultListener<Integer> listener;
	
	private static int count = 0;
	private int instance;
	private String taskId;
	
	public ValueReturningTaskB(int a, int b, long sleepTime,ResultListener<Integer> listener) {
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		this.instance = ++count;
		this.taskId = "valuereturningtaskA" +instance;
		this.listener = listener;
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
		
		listener.notifyResult(sum);
	}
	
}
