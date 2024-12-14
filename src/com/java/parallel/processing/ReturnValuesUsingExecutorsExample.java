package com.java.parallel.processing;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.java.parallel.processing.threads.CalculationTaskA;
import com.java.parallel.processing.threads.Thread1;

/**
 * @author nsajekar
 * Note:
 * 1.Runnable<T> -> (I)Runnable Task didn't return anything & also did not throw any checked exception
 *                                (II)Run Using ExecutorService.execute(Runnable task)
 * 2.Callable<T>   -> (I)THIS CONTAINS METHOD public T call() throws Exception WHICH RETURNS T
 *                                (II)Run Using ExecutorService.submit(Callable<T> task)
 *                                (III)call to submit(Callable<T>) method returns Future<T>
 *                                (IV) Task Result Can be retrieved using "T Future.get()" method
 *                                
 * 3.we can also run runnable using submit method but it will return null only after execution is completed
 *  
 *  1ST TECHNIQUE -> PROCESSING RESULT BASED ON ORDER OF RETRIEVAL
 */
public class ReturnValuesUsingExecutorsExample {
	public static void main(String[] args) {
		String currentThread = Thread.currentThread().getName();
		System.out.println("[ main thread starts here -: " + currentThread + " ]");
		
		//USING JAVA 8
		Callable<Integer> task = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("#### [" + threadName + "]  <" + 4 + "> Started ####");
			TimeUnit.MILLISECONDS.sleep(50);
			System.out.println("#### [" + threadName + "]  <" + 4 + "> Ended ####");
			return 56+67;
		};
		Runnable task1 = () -> System.out.println("addition is :-" +12+13);
		// using java 8
		ExecutorService executorService = Executors.newCachedThreadPool(runnable -> new Thread(runnable, "nitesh-cached-thread-pool"));
		
		Future<Integer> result1 = executorService.submit(new CalculationTaskA(1, 4, 1000));
		Future<Integer> result2 = executorService.submit(new CalculationTaskA(1, 9, 500));
		Future<Integer> result3 = executorService.submit(new CalculationTaskA(1, 11, 100));
		Future<Integer> result4 = executorService.submit(task);
		Future<?> result5 = executorService.submit(task1);
		Future<Double> result6 = executorService.submit(new Thread1(),999.000);
		
		try {
			System.out.println("result 1 : " +result1.get());
			System.out.println("result 2 : " +result2.get());
			System.out.println("result 3 : " +result3.get());
			System.out.println("result 4 : " +result4.get());
			System.out.println("result 5 : " +result5.get());
			System.out.println("result 6 : " +result6.get());
		} catch (Exception e) {
			e.printStackTrace();
		}   
		executorService.shutdown();
		System.out.println("[ main thread ends here -: " + currentThread + " ]");
	}
}