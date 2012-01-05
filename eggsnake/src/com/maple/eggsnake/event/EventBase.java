/** 
 * @description	: all events should derived from this class
 * @author		: 黄攀
 * @created		: 2012-1-5
 */



package com.maple.eggsnake.event;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author snail
 *
 * @param <T>  T is the class of argument
 */
public class EventBase<T> {
	
	List<EventListener<T>> listeners;
	EventAggregator aggregator;

	public EventBase(EventAggregator _aggregator) {
		this.aggregator = _aggregator;
		listeners = new ArrayList<EventListener<T>>();
	}

	public void subscribe(EventListener<T> listener){
		this.listeners.add(listener);
	}
	public void unSubscribe(EventListener<T> listener){
		this.listeners.remove(listener);
	}
	public void publish(T arg){
		for(EventListener<T> listener:listeners){
			listener.onEventReceived(arg);
		}
	}
}
