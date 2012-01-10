package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.math.Vector2;

public class CircleBody {
	
	private Vector2 lowerLeft;
	private float radius;
	
	public CircleBody(float x, float y, float radius){
		this.lowerLeft = new Vector2(x, y);
		this.radius = radius;
	}
	
	public CircleBody(Vector2 lowerLeft, float radius){
		this.lowerLeft = new Vector2(lowerLeft);
		this.radius = radius;
	}
	
	public float getX(){
		return this.lowerLeft.x;
	}
	
	public float getY(){
		return this.lowerLeft.y;
	}
	
	public float getRadius(){
		return this.radius;
	}
	
	public void setPosition(float x, float y, float radius){
		this.lowerLeft.x = x;
		this.lowerLeft.y = y;
		this.radius = radius;
	}
	
	public Vector2 getLowerLeft(){
		return this.lowerLeft;
	}
}
