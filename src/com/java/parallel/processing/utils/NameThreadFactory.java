package com.java.parallel.processing.utils;

import java.util.concurrent.ThreadFactory;

public class NameThreadFactory implements ThreadFactory{
	
	public static int count = 0;
	public static String name ="worker-";

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r,name + ++count);
		return thread;
	}

}
