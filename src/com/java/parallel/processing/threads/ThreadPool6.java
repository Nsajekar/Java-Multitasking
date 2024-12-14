package com.java.parallel.processing.threads;

import java.util.concurrent.TimeUnit;

/**
 * @author nsajekar
 */
public class ThreadPool6 implements Runnable {
	static int count = 0;
	int id;

	@Override
	public void run() {
		System.out.println("#### [" +Thread.currentThread().getName()+ "] < - task " + id + "->#### STARTED");
		for (int i = 0; i < 10; i++) {
			System.out.println("TASK id :: " + i + " Thread :: " + Thread.currentThread().getName());
			try {
				TimeUnit.MILLISECONDS.sleep((long)Math.random()*500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("#### [" +Thread.currentThread().getName()+ "] < - task " + id + "->#### ENDED");
	}

	public ThreadPool6() {
		this.id = ++count;
	}
}
