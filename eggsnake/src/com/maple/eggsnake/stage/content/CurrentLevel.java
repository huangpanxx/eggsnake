package com.maple.eggsnake.stage.content;


public class CurrentLevel {
	private int level;
	
	private static CurrentLevel uniqueInstance;
	
	private CurrentLevel(){
		this.level = 1;
	}
	
	public static CurrentLevel getInstance(){
		if(null == uniqueInstance)
			uniqueInstance = new CurrentLevel();
		return uniqueInstance;
	}
	
	/**
	 * 
	 * @param level
	 */
	public void setLevel(int level){
		if((1 <= level) && (level <= 3))
			this.level = level;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getLevel(){
		return this.level;
	}
}
