package com.maple.eggsnake.actor.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Forever;
import com.badlogic.gdx.scenes.scene2d.actions.RotateBy;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.stage.content.common.EnumRotateDirection;

public class Wheel extends Image implements Dynamicable{

	private final float clockWiseAngles = 360f;
	private final float anclockWiseAngles = -360f;
	
	private final float duration = 8f;
	
	@SuppressWarnings("unused")
	private EnumRotateDirection direction;
	
	public Wheel(TextureRegion region, float x, float y, Action action) {
		super("wheel", region);
		this.loadAction(action);
		this.setWheelPosition(x, y);
	}

	public Wheel(Texture texture, float x, float y, Action action) {
		super("wheel", texture);
		this.loadAction(action);
		this.setWheelPosition(x, y);
	}
	
	public Wheel(TextureRegion region, float x, float y, 
			EnumRotateDirection direction) {
		super("wheel", region);
		this.loadDefaultAction(direction);
		this.setWheelPosition(x, y);
	}

	public Wheel(Texture texture, float x, float y, 
			EnumRotateDirection direction) {
		super("wheel", texture);
		this.loadDefaultAction(direction);
		this.setWheelPosition(x, y);
	}

	@Override
	public void loadAction(Action action) {
		this.action(action);
	}

	private void loadDefaultAction(EnumRotateDirection direction){
		this.direction = direction;
		Action rotateAction;
		if(EnumRotateDirection.CLOCKWISE == direction)
			rotateAction = RotateBy.$(this.clockWiseAngles, this.duration);
		else
			rotateAction = RotateBy.$(this.anclockWiseAngles, this.duration);
		this.action(Forever.$(rotateAction));
	}
	
	public void setWheelPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer){
		return super.touchDown(x, y, pointer);
	}
	
	@Override
	public void touchUp(float x, float y, int pointer){
		 super.touchUp(x, y, pointer);
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer){
		super.touchDragged(x, y, pointer);
	}
}
