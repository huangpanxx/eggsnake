/** 
 * @description	: 自定义Actor,继承自Image
 * @author		: 王志伟
 * @created		: 2012-1-3
 */

package com.maple.eggsnake.actor.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.RotateBy;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

public class RotateAnimateImage extends Image{
	
    private float clockWiseAngles = 180f; //顺时针180度
    private float anclockWiseAngles = -180f; //逆时针180度
    private float rotateDuration = 4f;      //旋转间隔,默认旋转间隔为4f
    private RotateDirection rotateDirection;//旋转方向
    
	private Texture rotateTexture;			//控件的图片	
	private AnimateImageListener listener;  //添加监听器

	public RotateAnimateImage(String name, Texture texture, float duration,
			RotateDirection direction) {
		super(name, texture);
		this.rotateTexture = texture;
		this.touchable = true;
		this.rotateDuration = duration;
		this.rotateDirection = direction;
		this.listener = new AnimateImageListener();
		this.initActions();
	}
	
	public RotateAnimateImage(String name, Texture texture){
		super(name, texture);
		this.rotateTexture = texture;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer) {
		this.listener.onTouchDown(new AnimateImageEvent(this));	
		return touchable;	
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer){
		this.listener.onTouchDragged(new AnimateImageEvent(this));
	}
	
	@Override
	public void touchUp(float x, float y, int pointer){
		this.listener.onTouchUp(new AnimateImageEvent(this));
	}

	/**
	 * @description  初始化旋转动作
	 */
	private void initActions(){
		Action rotateAction;
		if(RotateDirection.CLOCKWISE == this.rotateDirection)
			rotateAction = RotateBy.$(this.clockWiseAngles, this.rotateDuration);
		else
			rotateAction = RotateBy.$(this.anclockWiseAngles, this.rotateDuration);
		this.action(Forever.$(rotateAction));	
	}
	
	/**
	 * @Description 添加监听器
	 * @param listener
	 */
	public void addActionListener(AnimateImageListener listener){
		this.listener = listener;
	}
	
	/**
	 * @return the rotateTexture
	 */
	public Texture getRotateTexture() {
		return rotateTexture;
	}

	/**
	 * @param rotateTexture the rotateTexture to set
	 */
	public void setRotateTexture(Texture rotateTexture) {
		this.rotateTexture = rotateTexture;
	}

	/**
	 * @return the rotateDuration
	 */
	public float getRotateDuration() {
		return rotateDuration;
	}

	/**
	 * @param rotateDuration the rotateDuration to set
	 */
	public void setRotateDuration(float rotateDuration) {
		this.rotateDuration = rotateDuration;
	}

	/**
	 * @return the rotateDirection
	 */
	public RotateDirection getRotateDirection() {
		return rotateDirection;
	}

	/**
	 * @param rotateDirection the rotateDirection to set
	 */
	public void setRotateDirection(RotateDirection rotateDirection) {
		this.rotateDirection = rotateDirection;
	}

	/**
	 * @return the clockWiseAngles
	 */
	public float getClockWiseAngles() {
		return clockWiseAngles;
	}

	/**
	 * @param clockWiseAngles the clockWiseAngles to set
	 */
	public void setClockWiseAngles(float clockWiseAngles) {
		this.clockWiseAngles = clockWiseAngles;
	}

	/**
	 * @return the anClockWiseAngles
	 */
	public float getAnClockWiseAngles() {
		return anclockWiseAngles;
	}

	/**
	 * @param anClockWiseAngles the anClockWiseAngles to set
	 */
	public void setAnClockWiseAngles(float anClockWiseAngles) {
		this.anclockWiseAngles = anClockWiseAngles;
	}
}
