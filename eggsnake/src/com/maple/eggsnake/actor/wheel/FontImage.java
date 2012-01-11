package com.maple.eggsnake.actor.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

public class FontImage extends Image{

	public FontImage(Texture texture, float x, float y) {
		super("default", texture);
	}

	public FontImage(TextureRegion region, float x, float y) {
		super("default", region);
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
}
