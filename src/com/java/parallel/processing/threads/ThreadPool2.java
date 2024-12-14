package com.java.parallel.processing.threads;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author nsajekar
 */
public class ThreadPool2 implements Runnable {
	static int count = 0;
	int id;

	@Override
	public void run() {
		//naming thread
		Thread.currentThread().setName("Nitesh Thread - " + id);
		String threadName = Thread.currentThread().getName();
		System.out.println("#### [" +threadName+ "] < - task " + id + "->#### STARTED");
		for (int i = 0; i < 10; i++) {
			System.out.println("thread id :: " + id + "Thread :: " + i + " time :: " + LocalDateTime.now());
			try {
				TimeUnit.MILLISECONDS.sleep((long)Math.random()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("#### [" +threadName+ "] < - task " + id + "->#### ENDED");
	}

	public ThreadPool2() {
		this.id = ++count;
	}
}
