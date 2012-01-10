/** 
 * @description	: 动态Actor事件
 * @author		: 王志伟
 * @created		: 2012-1-3
 */

package com.maple.eggsnake.actor.event;

import java.util.EventObject;

public class AnimateImageEvent extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -490127094220892085L;
	Object obj;
	
	public AnimateImageEvent(Object source) {
		super(source);
		this.obj = source;
	}

	@Override
	public Object getSource(){
		return this.obj;
	}
}
