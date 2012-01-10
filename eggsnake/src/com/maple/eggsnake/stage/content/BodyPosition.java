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
	
	private Map<String, Vector2> lowerLeftMap;
	private Map<String, Radius> radiusMap;
	private Map<String, Vector2> topRightMap;
	
	private static BodyPosition uniqueInstance;
	
	private BodyPosition(){
		this.lowerLeftMap = new HashMap<String, Vector2>();
		this.radiusMap = new HashMap<String, Radius>();
		this.topRightMap = new HashMap<String, Vector2>();
	}
	
	public static BodyPosition getInstance(){
		if(null == uniqueInstance){
			uniqueInstance = new BodyPosition();
		}
		return uniqueInstance;
	}
	
	/**
	 * @description 添加圆形的body的左下角坐标和半径
	 * @param name
	 * @param lowerLeft
	 * @param radius
	 */
	public void setPosition(String name, Vector2 lowerLeft, float radius){
		if(this.lowerLeftMap.containsKey(name) && this.radiusMap.containsKey(name)){
			this.lowerLeftMap.get(name).set(lowerLeft);
			this.radiusMap.get(name).setRadius(radius);
		}
		else{
			if(!this.lowerLeftMap.containsKey(name))
				this.lowerLeftMap.put(name, lowerLeft);
			if(!this.radiusMap.containsKey(name))
				this.radiusMap.put(name, new Radius(radius));
		}
	}
	
	/**
	 * @description 添加矩形左下角和右上角坐标
	 * @param name
	 * @param lowerLeft
	 * @param topRight
	 */
	public void setPosition(String name, Vector2 lowerLeft, Vector2 topRight){
		if(this.lowerLeftMap.containsKey(name) && this.topRightMap.containsKey(name)){
			this.lowerLeftMap.get(name).set(lowerLeft);
			this.topRightMap.get(name).set(topRight);
		}
		else{
			if(!this.lowerLeftMap.containsKey(name))
				this.lowerLeftMap.put(name, lowerLeft);
			if(!this.topRightMap.containsKey(name))
				this.topRightMap.put(name, topRight);
		}
	}
	
	/**
	 * @description 根据名字获取左下角坐标
	 * @param name
	 * @return
	 */
	public Vector2 getLowerLeft(String name){
		if(this.lowerLeftMap.containsKey(name))
			return this.lowerLeftMap.get(name);
		else
			return null;
	}
	
	/**
	 * @description 根据名字获取半径
	 * @param name
	 * @return
	 */
	public float getRadius(String name){
		if(this.radiusMap.containsKey(name))
			return this.radiusMap.get(name).getRadius();
		else
			return 0.0f;
	}
	
	public Vector2 getTopRight(String name){
		if(this.topRightMap.containsKey(name))
			return this.topRightMap.get(name);
		else
			return null;
	}
}
