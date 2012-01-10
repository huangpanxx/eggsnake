/** 
 * @description	: BodyPosition
 * @author		: 王志伟
 * @created		: 2012-1-9
 */

package com.maple.eggsnake.stage.content;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;


public class BodyPosition {
	
	private Map<String, Vector2> positionMap;
	private static BodyPosition uniqueInstance;
	
	private BodyPosition(){
		this.positionMap = new HashMap<String, Vector2>();
	}
	
	public static BodyPosition getInstance(){
		if(null == uniqueInstance){
			uniqueInstance = new BodyPosition();
		}
		return uniqueInstance;
	}
	
	public void setPosition(String name, Vector2 position){
		if(this.positionMap.containsKey(name)){
			this.positionMap.get(name).set(position);
		}
		else{
			this.positionMap.put(name, position);
		}
	}
	
	public Vector2 getPosition(String name){
		return this.positionMap.get(name);
	}
}
