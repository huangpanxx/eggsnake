package com.maple.eggsnake.actor.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

public class FlatImage extends Image {

	/**
	 * 
	 * @param texture
	 * @param x
	 * @param y
	 * @param stage
	 */
	public FlatImage(Texture texture, float x, float y, Stage stage) {
		super("default", texture);
		this.setPosition(x, y);
		stage.addActor(this);
	}

	/**
	 * 
	 * @param region
	 * @param x
	 * @param y
	 * @param stage
	 */
	public FlatImage(TextureRegion region, float x, float y, Stage stage) {
		super("default", region);
		this.setPosition(x, y);
		stage.addActor(this);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	private void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
