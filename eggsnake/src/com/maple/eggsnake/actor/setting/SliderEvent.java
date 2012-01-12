package com.maple.eggsnake.actor.setting;

import java.util.EventObject;


public class SliderEvent extends EventObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Object obj;
	
	public SliderEvent(Object source) {
		super(source);
		this.obj = source;
	}
	
	@Override
	public Object getSource(){
		return obj;
	}

}
