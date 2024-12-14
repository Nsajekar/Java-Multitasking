package com.java.parallel.processing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.java.parallel.processing.threads.ThreadPool5;
import com.java.parallel.processing.utils.NameDemonThreadFactory;

/**
 * @author nsajekar
 * Note
 * 1.These Threads are intended to run background task that support user thread 
 * 2.Life Cycle -> The JVM Does not wait for deamon thread to complete before exiting if all user
 *                          Threads finish,the JVM terminates stopping all deamon threads abruptly 
 * 3.example -> directory watcher thread,socket reader thread,long calculation thread,automatic save thread
 * 4.Thread Class -> void Thread.setDaemon(boolean on) -> also used in executor API
 * 5.In Executor Service  ->  
 *    I) Same Thread.setDeamon(on boolean) is used in executor
 *    II)For Executors , implement ThreadFactory and manipulate thread there to make it Daemon 
 */
public class DaemonThreadExample {
	
	public static int count = 0;
	
	public static void main(String[] args) {
		String threadName = Thread.currentThread().getName();
		System.out.println("[ main thread starts here -: " + threadName + " ]");
//		createDeamonThreadUsingThreadAPI();
		createDeamonThreadUsingExecutorAPI();
		System.out.println("[ main thread ends here -: " + threadName + " ]");
	}

	private static void createDeamonThreadUsingThreadAPI() {
		Thread t1 = new Thread(new ThreadPool5(200),"task-1");
		Thread t2 = new Thread(new ThreadPool5(500),"task-2");
		t2.setDaemon(true);
		t1.start();
		t2.start();
	}

	private static void createDeamonThreadUsingExecutorAPI() {
		ExecutorService executorService = Executors.newCachedThreadPool(new NameDemonThreadFactory());
            //JAVA 8	
//		ExecutorService executorService = Executors.newCachedThreadPool(runnable -> { 
//			Thread t = new Thread(runnable,"thread-" + ++count);
//			if(++count  % 2 == 0) {
//				t.setDaemon(true);
//			}
//			return t;
//		});
		executorService.execute(new ThreadPool5(100));
		executorService.execute(new ThreadPool5(200));
		executorService.execute(new ThreadPool5(300));
		executorService.execute(new ThreadPool5(400));
		executorService.shutdown();
	}

}
