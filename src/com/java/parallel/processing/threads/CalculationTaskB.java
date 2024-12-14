package com.java.parallel.processing.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.java.parallel.processing.utils.TaskResult;

public class CalculationTaskB implements Callable<TaskResult<String, Integer>> {

	private int a ;
	private  int b;
	private long sleepTime;
	
	private static int count = 0;
	private int instance;
	private String taskId;
	
	public CalculationTaskB(int a, int b, long sleepTime) {
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		this.instance = ++count;
		this.taskId = "calculationTaskB" +instance;
	}

	@Override
	public TaskResult<String, Integer> call() throws Exception {
		String currentThread = Thread.currentThread().getName();
		System.out.println("#### [" + currentThread + "]  <" + taskId + "> Started ####");
		TimeUnit.MILLISECONDS.sleep(sleepTime);
		System.out.println("#### [" + currentThread + "]  <" + taskId + "> Ended ####");
		return new TaskResult<>(taskId, a+b);
	}
	
}
