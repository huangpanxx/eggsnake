package com.maple.eggsnake.actor.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.stage.content.common.EnumRotateDirection;

public class CombinedWheel extends Wheel implements Dynamicable{

    private Image frontImage;
    
	public CombinedWheel(String wheelName, Texture wheelTexture, 
			String frontName, Texture frontTexture, Action action){
		super(wheelName, wheelTexture, action);
		this.loadFrontImage(frontName, frontTexture);
	}
	
	public CombinedWheel(String wheelName, TextureRegion wheelRegion, 
			String frontName, TextureRegion frontRegion, 
			Action action){
		super(wheelName, wheelRegion, action);
		this.loadFrontImage(frontName, frontRegion);
	}
	
	public CombinedWheel(String wheelName, Texture wheelTexture, 
			String frontName, Texture frontTexture, 
			EnumRotateDirection direction) {
		super(wheelName, wheelTexture, direction);
		this.loadFrontImage(frontName, frontTexture);
	}
	
	public CombinedWheel(String wheelName, TextureRegion wheelRegion, 
			String frontName, TextureRegion frontRegion, 
			EnumRotateDirection direction){
		super(wheelName, wheelRegion, direction);
		this.loadFrontImage(frontName, frontRegion);
	}

	private void loadFrontImage(String frontName, Texture frontTexture){
		this.frontImage = new Image(name, frontTexture);
	}
	
	private void loadFrontImage(String frontName, TextureRegion frontRegion){
		this.frontImage = new Image(frontName, frontRegion);
	}
	
	public void setWheelPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setFrontImagePosition(){
		this.frontImage.x  = this.x + (this.width - this.frontImage.width) / 2f;
		this.frontImage.y = this.y + (this.height - this.frontImage.height) / 2f;
	}

	public void loadActor(Stage stage){
		stage.addActor(this);
		stage.addActor(frontImage);
	}
	/**
	 * @return the frontImage
	 */
	public Image getFrontImage() {
		return frontImage;
	}

	/**
	 * @param frontImage the frontImage to set
	 */
	public void setFrontImage(Image frontImage) {
		this.frontImage = frontImage;
	}

}
