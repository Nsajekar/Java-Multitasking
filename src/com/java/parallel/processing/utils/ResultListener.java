package com.java.parallel.processing.utils;

public interface ResultListener<T> {
	void notifyResult(T result);
}
