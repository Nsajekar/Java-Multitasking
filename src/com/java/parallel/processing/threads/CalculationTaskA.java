package com.java.parallel.processing.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CalculationTaskA implements Callable<Integer> {

	private int a ;
	private  int b;
	private long sleepTime;
	
	private static int count = 0;
	private int instance;
	private String taskId;
	
	public CalculationTaskA(int a, int b, long sleepTime) {
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		this.instance = ++count;
		this.taskId = "calculationtaskA" +instance;
	}

	@Override
	public Integer call() throws Exception {
		String currentThread = Thread.currentThread().getName();
		System.out.println("#### [" + currentThread + "]  <" + taskId + "> Started ####");
		TimeUnit.MILLISECONDS.sleep(sleepTime);
		System.out.println("#### [" + currentThread + "]  <" + taskId + "> Ended ####");
		return a+b;
	}
	
}
