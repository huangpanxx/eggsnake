/** 
 * @description	: RectangleBody
 * @author		: 王志伟
 * @created		: 2012-1-10
 */

package com.maple.eggsnake.stage.content.attachtexture;

import com.badlogic.gdx.math.Vector2;

public class RectangleBody {
	
	private Vector2 lowerLeft;
	private Vector2 topRight;
	
	public RectangleBody(Vector2 lowerLeft, Vector2 topRight){
		this.lowerLeft = new Vector2(0, 0);
		this.topRight = new Vector2(0, 0);
		this.lowerLeft.set(lowerLeft);
		this.topRight.set(topRight);
	}
	
	public RectangleBody(float x, float y, float u, float v){
		this.lowerLeft = new Vector2(0, 0);
		this.topRight = new Vector2(0, 0);
		if(!(null == this.lowerLeft)){
			this.lowerLeft.x = x;
			this.lowerLeft.y = y;
		}
		if(!(null == topRight)){
			this.topRight.x = u;
			this.topRight.y = v;
		}
	}

	public void setPosition(float x, float y, float u, float v){
		if(!(null == this.lowerLeft)){
			this.lowerLeft.x = x;
			this.lowerLeft.y = y;
		}
		if(!(null == topRight)){
			this.topRight.x = u;
			this.topRight.y = v;
		}
	} 
	
	/**
	 * @return the lowerLeft
	 */
	public Vector2 getLowerLeft() {
		return lowerLeft;
	}

	/**
	 * @param lowerLeft the lowerLeft to set
	 */
	public void setLowerLeft(Vector2 lowerLeft) {
		this.lowerLeft = lowerLeft;
	}

	/**
	 * @return the topRight
	 */
	public Vector2 getTopRight() {
		return topRight;
	}

	/**
	 * @param topRight the topRight to set
	 */
	public void setTopRight(Vector2 topRight) {
		this.topRight = topRight;
	}
	
}
