package com.java.parallel.processing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.java.parallel.processing.threads.ThreadPool1;

/**
 * @author nsajekar
 *  1.create task definition class
 *  2.provide task to executor service
 *  
 *  1.uses thread pool
 *  2.allocates heavy weight threads upfront
 *  3.decouples task submission from thread creation and managment
 *  4.each thread in pool execute task one by one
 *  5.task queue holds the task
 *  
 *  Executor(interface) -> void execute(Runnable task);
 *  ExecutorService (interface) -> <T>Future<T> submit (Callable<T>task)
 *                                                      Future<T> submit (Runnable task)
 *                                                      void shutdown();
 *                                                      List<Runnable> shutdownNow();
 *                                                      boolean isShutdown();
 *  Executors(Class) ->  ExecutorService newFixedThreadPool(in noOfThreads)
 *                                     ExecutorService newCacheThreadPool()
 *                                     ExecutorService newSingleThreadExecutor()
 *                                     ExecutorService newSingleThreadScheduledExecutor()
 * Future (Interface) -> V get() throws InterreptedExeception,ExecutionException 
 *                                    boolean isDone();
 *                                
 *                                    Executor(Interface)
 *                                        /\
 *                                          |  implements
 *                              ExecutorService (Interface)
 *                                        /\
 *                                          |  implements
 *                             AbstractExecutorService(Abstract Class)
 *                                        /\
 *                                          |  Extends
  *                             ThreadPoolExecutor (Class)
 */
public class ExecutorServiceExample {
	public static void main(String[] args) {
		/**
		 * 1st technique for creating and running thread
		 * note : it will create threads specify in method @param newFixedThreadPool(int noOfThread);
		 */
		System.out.println("STARTED :: 1st technique ");
		ExecutorService executorService = Executors.newFixedThreadPool(6);
		for (int i = 0; i < 6; i++) {
			executorService.execute(new ThreadPool1());
		}
		executorService.shutdown();
		System.out.println("ENDED :: 1st technique");
		
		/**
		 * 2nd technique for creating and running thread
		 * note : it will create as many as thread as tasks and reuse them
		 */
		System.out.println("STARTED :: 2nd technique ");
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 6; i++) {
			cachedThreadPool.execute(new ThreadPool1());
		}
		cachedThreadPool.shutdown();
		System.out.println("ENDED :: 2nd technique");
		
		/**
		 * 3rd technique for creating and running thread
		 * note : It will execute tasks sequentially but When object is shared then use synchronization
		 */
		System.out.println("STARTED :: 3nd technique ");
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 6; i++) {
			singleThreadPool.execute(new ThreadPool1());
		}
		singleThreadPool.shutdown();
		System.out.println("ENDED :: 3nd technique");
		
	}
}
