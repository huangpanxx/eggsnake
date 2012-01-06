/** 
 * @description	: a generic event argument class
 * @author		: 黄攀
 * @created		: 2012-1-5
 */

package com.maple.eggsnake.event;

public class EventArg<T> {
	private T arg;

	public EventArg(T arg) {
		this.arg = arg;

	}

	public T getArg() {
		return arg;
	}
}
