package com.java.parallel.processing;

import java.util.concurrent.TimeUnit;

import com.java.parallel.processing.threads.ThreadPool2;
import com.java.parallel.processing.threads.ThreadPool3;

/**
 * @author nsajekar
 */
public class NamingThreadExample {
	public static void main(String[] args) {
		/**
		 * 1st technique Naming threads within task 
		 */
		String threadName = Thread.currentThread().getName();
		System.out.println("main thread start with name : " + threadName);
		for (int i = 0; i < 2; i++) {
			new Thread(new ThreadPool2()).start();
		}
		System.out.println("main thread end with name : " + threadName);
		
		/**
		 * 2nd technique Naming threads
		 */
		String threadName2 = Thread.currentThread().getName();
		System.out.println("main thread start with name : " + threadName2);
		new Thread(new ThreadPool3(),"WORKER-1").start();
		Thread T2 = new Thread(new ThreadPool3());
		T2.start();
		try {
			TimeUnit.MICROSECONDS.sleep(100);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("renaming thread -2");
		T2.setName("WORKER-2");
		System.out.println("main thread end with name : " + threadName2);
	}
}
