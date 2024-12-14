package com.java.parallel.processing;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.java.parallel.processing.threads.CalculationTaskB;
import com.java.parallel.processing.threads.Thread1;
import com.java.parallel.processing.utils.TaskResult;

/**
 * @author nsajekar
 * Note:
 * 1.Runnable<T> -> (I)Runnable Task didn't return anything & also did not throw any checked exception
 *                                (II)Run Using ExecutorService.execute(Runnable task)
 * 2.Callable<T>   -> (I)THIS CONTAINS METHOD public T call() throws Exception WHICH RETURNS T
 *                                (II)Run Using ExecutorService.submit(Callable<T> task)
 *                                (III)call to submit(Callable<T>) method returns Future<T>
 *                                (IV) Task Result Can be retrieved using "T Future.get()" method
 * 3.we can also run runnable using submit method but it will return null only after execution is completed
 * 4.CompletionService<V> -> Future<V> submit(Callable <V> task)
 *                                                Future<V>submit(Runnable task,V result)
 *                                                Future<V>take() -> It Blocks the thread for execution &
 *                                                                                  Retrieves and removes the Future representing the next completed task, waiting if none are yet present.
 *                                                Future<V>poll() -> it does not blocks the thread for execution & 
 *                                                                                 Retrieves and removes the Future representing the next completed task, or null if none are present.
 * 5.ExecutorImplementationService<V> -> It Implements CompletionService<V>
 *  
 *  2ND TECHNIQUE -> PROCESSING RESULT BASED ON ORDER OF COMPLETETION
 */
public class ReturnValuesUsingExecutors_2ndTechniquExample {
	
	public static void main(String[] args) {
		String currentThread = Thread.currentThread().getName();
		System.out.println("[ main thread starts here -: " + currentThread + " ]");
		
		//USING JAVA 8
		Callable<TaskResult<String, Integer>> task = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("#### [" + threadName + "]  <" + 4 + "> Started ####");
			TimeUnit.MILLISECONDS.sleep(200);
			System.out.println("#### [" + threadName + "]  <" + 4 + "> Ended ####");
			return new TaskResult<>("4", 43+78);
		};
		// using java 8
		ExecutorService executorService = Executors.newCachedThreadPool(runnable -> new Thread(runnable, "nitesh-cached-thread-pool"));
		CompletionService<TaskResult<String, Integer>> executorComplitionService = new ExecutorCompletionService<>(executorService);
		executorComplitionService.submit(new CalculationTaskB(1, 4, 1000));
		executorComplitionService.submit(new CalculationTaskB(1, 9, 700));
		executorComplitionService.submit(new CalculationTaskB(1, 11, 500));
		executorComplitionService.submit(task);
		executorComplitionService.submit(new Thread1(), new TaskResult<>("Loop task - 1 ", 21));
		executorService.shutdown();
		for (int i = 0; i < 4; i++) {
			try {
				System.out.println("Result : - " + executorComplitionService.take().get() );
			} catch (InterruptedException  | ExecutionException e) {
				e.printStackTrace();
			} 
		}
		System.out.println("[ main thread ends here -: " + currentThread + " ]");
	}
}