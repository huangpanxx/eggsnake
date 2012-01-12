package com.maple.eggsnake.actor.setting;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

public class SliderImage extends Image {

	private float originX;
	private float originY;
	private float endX;
	private float endY;
	private float offsetX;
	private float offsetY;
	
	public SliderImage(Texture texture, float x, float y, Stage stage) {
		super("default", texture);
		this.x = x;
		this.y = y;
		stage.addActor(this);
	}
	
	public SliderImage(TextureRegion region, float x, float y, Stage stage){
		super("default", region);
		this.x = x;
		this.y = y;
		stage.addActor(this);
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer){
		if((x <= 180) && (0 <= x))
				this.action(MoveTo.$(x, 60, 1f));
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer){
		this.originX = x;
		this.originY = y;
		return touchable;
	}
	
	@Override
	public void touchUp(float x, float y, int pointer){
		this.endX = x;
		this.endY = y;
		this.offsetX = this.endX - this.offsetX;
		this.offsetY = this.endY - this.originY;
	}

}
