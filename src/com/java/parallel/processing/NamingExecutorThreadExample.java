package com.java.parallel.processing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.java.parallel.processing.threads.ThreadPool4;
import com.java.parallel.processing.utils.NameThreadFactory;

/**
 * @author nsajekar
 *
 * default naming of executor is [pool-n-thread-m]
 * JVM will automatically increment pool sequence number n when new executor pool is created
 * JVM will automatically increment thread sequence number m when new thread is created
 * 
 */
public class NamingExecutorThreadExample {
	public static void main(String[] args) {
		/**
		 * Default name
		 */
//		String threadName = Thread.currentThread().getName();
//		System.out.println("main thread start with name : " + threadName);
//		
//		ExecutorService executorService = Executors.newCachedThreadPool();
//		for (int i = 0; i < 3; i++) {
//			executorService.execute(new ThreadPool4());
//		}
//		executorService.shutdown();
//		System.out.println("main thread end with name : " + threadName);

		/**
		 * Custom name
		 */
		String threadName1 = Thread.currentThread().getName();
		System.out.println("main thread start with name : " + threadName1);
		/**
		 * ways to initiate thread factory name
		 */
		ExecutorService executorService1 = Executors.newCachedThreadPool(new NameThreadFactory());
//		ExecutorService executorService1 = Executors.newCachedThreadPool(runnable -> new Thread(runnable,"nitesh-thread-pool"));

		for (int i = 0; i < 3; i++) {
			executorService1.execute(new ThreadPool4());
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 5; i++) {
			executorService1.execute(new ThreadPool4());
		}
		executorService1.shutdown();
		System.out.println("main thread end with name : " + threadName1);
	}
	}
	
	
