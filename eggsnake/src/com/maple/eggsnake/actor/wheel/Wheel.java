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
	
	private final float duration = 4f;
	
	@SuppressWarnings("unused")
	private EnumRotateDirection direction;
	
	public Wheel(String name, TextureRegion region, Action action) {
		super(name, region);
		this.loadAction(action);
	}

	public Wheel(String name, Texture texture, Action action) {
		super(name, texture);
		this.loadAction(action);
	}
	
	public Wheel(String name, TextureRegion region, EnumRotateDirection direction) {
		super(name, region);
		this.loadDefaultAction(direction);
	}

	public Wheel(String name, Texture texture, EnumRotateDirection direction) {
		super(name, texture);
		this.loadDefaultAction(direction);
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
