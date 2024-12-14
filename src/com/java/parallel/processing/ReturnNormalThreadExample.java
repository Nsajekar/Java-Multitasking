package com.java.parallel.processing;

import com.java.parallel.processing.threads.ValueReturningTaskB;
import com.java.parallel.processing.utils.SumObserver;

/**
 * @author nsajekar 
 * Note :
 * 1.Values Are return from tasks not threads 
 * 2.there is not a direct way for returning values from tasks in java but we can return it by this technique
 */
public class ReturnNormalThreadExample {
	public static void main(String[] args) {
		/**
		 * Returning values from tasks 1st technique
		 */
//		String currentThread = Thread.currentThread().getName();
//		System.out.println("[ main thread starts here -: " + currentThread + " ]");
//		ValueReturningTaskA taskA = new ValueReturningTaskA(1, 2, 100);
//		Thread t1 = new Thread(taskA, "worker-1");
//		ValueReturningTaskA taskB = new ValueReturningTaskA(1, 2, 1000);
//		Thread t2 = new Thread(taskB, "worker-2");
//		ValueReturningTaskA taskC = new ValueReturningTaskA(1, 2, 500);
//		Thread t3 = new Thread(taskC, "worker-3");
//		t1.start();
//		t2.start();
//		t3.start();
//		try {
//			System.out.println("result1 = " + taskA.getSum());
//			System.out.println("result2 = " + taskB.getSum());
//			System.out.println("result3 = " + taskC.getSum());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("[ main thread ends here -: " + currentThread + " ]");
		
		/**
		 * Returning values from tasks 2nd technique
		 */
		String currentThread2 = Thread.currentThread().getName();
		System.out.println("[ main thread starts here -: " + currentThread2 + " ]");
		ValueReturningTaskB taskA2 = new ValueReturningTaskB(1, 2, 100,new SumObserver("task-1"));
		Thread t12 = new Thread(taskA2, "worker-1");
		ValueReturningTaskB taskB2 = new ValueReturningTaskB(1, 2, 1000,new SumObserver("task-2"));
		Thread t22 = new Thread(taskB2, "worker-2");
		//USING JAVA 8
		ValueReturningTaskB taskC2 = new ValueReturningTaskB(1, 2, 500,result -> System.out.println("result for task-3 = "  + result));
		Thread t32 = new Thread(taskC2, "worker-3");
		t12.start();
		t22.start();
		t32.start();
		
		System.out.println("[ main thread ends here -: " + currentThread2 + " ]");
	}
}
