/**
 * @author zhiwei.wang
 * @version 0.0
 * @created 03-一月-2012 17:34:18
 */

package com.maple.eggsnake.actor.ui;

import java.util.EventObject;

public class AnimateImageEvent extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
