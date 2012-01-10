package com.maple.eggsnake.stage.content;

import java.util.HashMap;
import java.util.Map;

public class CircleBodyPosition {
	
	Map<String, CircleBody> circleBodyMap = new HashMap<String, CircleBody>();
	
	private static CircleBodyPosition uniqueInstance;
	
	public static CircleBodyPosition getInstance(){
		if(null == uniqueInstance)
			uniqueInstance = new CircleBodyPosition();
		return uniqueInstance;
	}
	
	private CircleBodyPosition(){		
	}
	
	public void setCircleBodyPosition(String name, float x, float y, float radius){
		if(this.circleBodyMap.containsKey(name)){
			this.circleBodyMap.get(name).setPosition(x, y, radius);
		}
		else{
			this.circleBodyMap.put(name, new CircleBody(x, y, radius));
		}
	}
	
	public CircleBody getCircleBody(String name){
		return this.circleBodyMap.get(name);
	}
}
