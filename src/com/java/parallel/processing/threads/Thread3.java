package com.java.parallel.processing.threads;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author nsajekar
 */
public class Thread3 implements Runnable {
	static int count = 0;
	int id;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("thread id :: "+ id +"Thread :: " + i + " time :: " + LocalDateTime.now());
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Thread3() {
		this.id = ++count;
	}
}
