/** 
 * @description	: BodyPosition
 * @author		: 王志伟
 * @created		: 2012-1-9
 */

package com.maple.eggsnake.stage.content;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;


public class RectangleBodyPosition {
	
	private Map<String, RectangleBody> rectangleBodyMap = 
			new HashMap<String, RectangleBody>();
	
	private static RectangleBodyPosition uniqueInstance;
	
	public static RectangleBodyPosition getInstance(){
		if(null == uniqueInstance){
			uniqueInstance = new RectangleBodyPosition();
		}
		return uniqueInstance;
	} 
	
	public void setRectangleBodyPosition(String name, float x, float y, 
			float u,float v){
		if(this.rectangleBodyMap.containsKey(name)){
			this.rectangleBodyMap.get(name).setPosition(x, y, u, v);
		}
		else{
			this.rectangleBodyMap.put(name,
					new RectangleBody(x, y, u, v));
		}
	}
	
	public void setRectangleBodyPosition(String name, Vector2 lowerLeft, 
			Vector2 topRight){
		if(this.rectangleBodyMap.containsKey(name)){
			this.rectangleBodyMap.get(name).setLowerLeft(lowerLeft);
			this.rectangleBodyMap.get(name).setTopRight(topRight);
		}
		else{
			this.rectangleBodyMap.put(name, new RectangleBody(lowerLeft,
					topRight));
		}
	}
	
	public Vector2 getLowerLeft(String name){
		
		return this.rectangleBodyMap.get(name).getLowerLeft();
	}
	
	public Vector2 getTopRight(String name){
		return this.rectangleBodyMap.get(name).getTopRight();
	}
}
