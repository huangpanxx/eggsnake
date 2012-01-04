/**
 * @author zhiwei.wang
 * @version 0.0
 * @created 03-一月-2012 17:34:18
 */

package com.badlogic.gdx.helloworld;

import java.util.EventObject;

public class AnimateImageEvent extends EventObject{

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
