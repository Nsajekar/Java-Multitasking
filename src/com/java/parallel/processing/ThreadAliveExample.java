package com.java.parallel.processing;

import java.util.concurrent.TimeUnit;

import com.java.parallel.processing.threads.ThreadPool6;

/**
 * @author nsajekar
* Note :
* 1. boolean Thread.isAlive() -> return true if running
* 2.return false if I) If Thread has not been started yet
*                           II) if Thread Has Terminated irrespective of whether it has successfully finished executing its task or not
 */
public class ThreadAliveExample {
	public static void main(String[] args) throws InterruptedException {
		String threadName = Thread.currentThread().getName();
		System.out.println("[ main thread starts here -: " + threadName + " ]");
		checkNormalAliveThread();
		checkExecutorAliveThread();
		System.out.println("[ main thread ends here -: " + threadName + " ]");
	}

	private static void checkNormalAliveThread() throws InterruptedException {
		String tName = Thread.currentThread().getName();
		Thread t1 = new Thread(new ThreadPool6(),"task-1");
		Thread t2 = new Thread(new ThreadPool6(),"task-2");
		boolean isT1Alive = t1.isAlive();
		boolean isT2Alive = t1.isAlive();
		System.out.println("[ "+tName + " ] BEFORE STARTING... T1 IS "+ t1.getName() +" ALIVE = " + isT1Alive);
		System.out.println("[ "+tName + " ] BEFORE STARTING... T2 IS "+ t2.getName() +" ALIVE = " + isT2Alive);
		t1.start();
		t2.start();
		
		while (true) {
			TimeUnit.MILLISECONDS.sleep(600);
			isT1Alive = t1.isAlive();
			isT2Alive = t1.isAlive();
			System.out.println("[ " + tName + " ] AFTER STARTING... T1 IS " + t1.getName() + " ALIVE = " + isT1Alive);
			System.out.println("[ " + tName + " ] AFTER STARTING... T2 IS " + t2.getName() + " ALIVE = " + isT2Alive);
			
			if(!t1.isAlive() && !t2.isAlive()) {
				break;
			}
		}
	}

	private static void checkExecutorAliveThread() {
		// TODO Auto-generated method stub
		
	}

}
