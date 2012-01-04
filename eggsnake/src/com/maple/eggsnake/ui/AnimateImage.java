package com.maple.eggsnake.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.RotateTo;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

/**
 * @author zhiwei.wang
 * @version 0.0
 * @deprecated 这是一个旋转控件（可以理解为按钮）
 * @created 03-一月-2012 17:34:18
 */

public class AnimateImage extends Image {

	private Texture rotateTexture; // 旋转控件的图片
	private float rotateDuration = 4f; // 旋转间隔
	private RotateDirection rotateDirection;// 旋转方向
	// private MyGame myGame; //游戏主控
	private boolean EnabledSwitchSreen; // 是否允许切换场景

	private float startX; // 玩家触控起的始点横坐标
	private float startY; // 玩家触控的起始点纵坐标

	public AnimateImage(String name, Texture texture, float duration,
			RotateDirection direction) {
		super(name, texture);
		// TODO Auto-generated constructor stub
		this.rotateTexture = texture;
		this.touchable = true;
		this.rotateDuration = duration;
		this.rotateDirection = direction;
		this.initActions();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		this.startX = x;
		this.startY = y;
		System.out.println("startX: " + x + " startY: " + y + " pointer: "
				+ pointer);
		return touchable;
	}

	@Override
	public void touchDragged(float x, float y, int pointer) {
		System.out.println("This image is being dragged!");
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		System.out.println("upX: " + x + " upY: " + y + " pointer:" + pointer);
		if ((10f <= (x - startX)) && (10f <= (y - startY)))
			;
	}

	/* 初始化该控件的旋转动作 */
	private void initActions() {
		Action rotateAction;
		if (RotateDirection.CLOCKWISE == this.rotateDirection)
			rotateAction = RotateTo.$(360, this.rotateDuration);
		else
			rotateAction = RotateTo.$(-360, this.rotateDuration);
		this.action(rotateAction);
	}

	/**
	 * @return the rotateTexture
	 */
	public Texture getRotateTexture() {
		return rotateTexture;
	}

	/**
	 * @param rotateTexture
	 *            the rotateTexture to set
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
	 * @param rotateDuration
	 *            the rotateDuration to set
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
	 * @param rotateDirection
	 *            the rotateDirection to set
	 */
	public void setRotateDirection(RotateDirection rotateDirection) {
		this.rotateDirection = rotateDirection;
	}

	/**
	 * @return the enabledSwitchSreen
	 */
	public boolean isEnabledSwitchSreen() {
		return EnabledSwitchSreen;
	}

	/**
	 * @param enabledSwitchSreen the enabledSwitchSreen to set
	 */
	public void setEnabledSwitchSreen(boolean enabledSwitchSreen) {
		EnabledSwitchSreen = enabledSwitchSreen;
	}
}
