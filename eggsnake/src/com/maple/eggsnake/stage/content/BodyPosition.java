/** 
 * @description	: BodyPosition
 * @author		: 王志伟
 * @created		: 2012-1-9
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.math.Vector2;


public class BodyPosition {
	
	private Vector2 position;
	private static BodyPosition uniqueInstance;
	
	private BodyPosition(){
			this.position = new Vector2(0, 0);
	}
	
	public static BodyPosition getInstance(){
		if(null == uniqueInstance){
			uniqueInstance = new BodyPosition();
		}
		return uniqueInstance;
	}
	
	public void setPosition(float x, float y){
		this.position.x = x;
		this.position.y = y;
	}
	
	public Vector2 getPosition(){
		return this.position;
	}
}
