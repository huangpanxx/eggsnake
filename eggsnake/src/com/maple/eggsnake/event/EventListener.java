/** 
 * @description	: listener for a events
 * @author		: 黄攀
 * @created		: 2012-1-5
 */

package com.maple.eggsnake.event;

/**
 * 
 * @author snail
 * 
 * @param <T>
 *            T is the type of argument
 */

public interface EventListener<T> {
	public void onEventReceived(T arg);

}
