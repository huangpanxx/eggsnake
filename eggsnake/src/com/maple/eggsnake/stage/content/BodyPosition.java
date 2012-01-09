package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.math.Vector3;

public class BodyPosition {
	
	private Vector3 position;
	private static BodyPosition uniqueBodyPositionInstance;
	
	private BodyPosition(){
			this.position = new Vector3(0, 0, 0);
	}
	
	public static BodyPosition getBodyPositionInstance(){
		if(null == uniqueBodyPositionInstance){
			uniqueBodyPositionInstance = new BodyPosition();
		}
		return uniqueBodyPositionInstance;
	}
	
	public void setPosition(float x, float y){
		this.position.x = x;
		this.position.y = y;
	}
	
	public Vector3 getPosition(){
		return this.position;
	}

}
