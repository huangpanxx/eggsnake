package com.maple.eggsnake.actor.lightlabel;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actors.Label;
import com.maple.eggsnake.actor.wheel.Dynamicable;

public class LightLabel extends Label implements Dynamicable{

	public LightLabel(String name, BitmapFont font, String text, Action action) {
		super(name, font, text);
		this.loadAction(action);
	}

	public LightLabel(String name, BitmapFont font, Action action) {
		super(name, font);
		this.loadAction(action);
	}

	public LightLabel(String name, BitmapFont font, String text) {
		super(name, font, text);
	}

	public LightLabel(String name, BitmapFont font) {
		super(name, font);
	}
	
	@Override
	public void loadAction(Action action) {
		this.action(action);
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
