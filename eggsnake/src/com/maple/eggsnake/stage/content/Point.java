package com.maple.eggsnake.stage.content;

public class Point {
	
	private float x;
	private float y;
	
	public Point(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public void set(float x, float y){
		this.x = x;
		this.y = y;
	}
}
