package com.maple.eggsnake.task;

public interface Task<T,V> {
	public T doWork(V arg); 
}
