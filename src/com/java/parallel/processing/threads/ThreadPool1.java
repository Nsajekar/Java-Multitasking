package com.java.parallel.processing.threads;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author nsajekar
 */
public class ThreadPool1 implements Runnable {
	static int count = 0;
	int id;

	@Override
	public void run() {
		System.out.println("#### < - task " + id + "->#### STARTED");
		for (int i = 0; i < 10; i++) {
			System.out.println("thread id :: " + id + "Thread :: " + i + " time :: " + LocalDateTime.now());
			try {
				TimeUnit.MILLISECONDS.sleep((long)Math.random()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("#### < - task " + id + "->#### ENDED");
	}

	public ThreadPool1() {
		this.id = ++count;
	}
}
