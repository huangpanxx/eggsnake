package com.maple.eggsnake.actor.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.stage.content.common.EnumRotateDirection;

public class CombinedWheel extends Wheel implements Dynamicable{

    private Image frontImage;
    
    /**
     * 
     * @param wheelTexture
     * @param frontTexture
     * @param x
     * @param y
     * @param action
     * @param stage
     */
	public CombinedWheel(Texture wheelTexture, Texture frontTexture, 
			float x, float y,  Action action, Stage stage){
		super(wheelTexture, x, y, action);
		this.loadFrontImage("front", frontTexture);
		this.setFrontImagePosition();
		this.loadActor(stage);
	}
	
	/**
	 * 
	 * @param wheelRegion
	 * @param frontRegion
	 * @param x
	 * @param y
	 * @param action
	 * @param stage
	 */
	public CombinedWheel(TextureRegion wheelRegion, TextureRegion frontRegion, 
			float x, float y, Action action, Stage stage){
		super(wheelRegion, x, y, action);
		this.loadFrontImage("front", frontRegion);
		this.setFrontImagePosition();
		this.loadActor(stage);
	}
	
	/**
	 * 
	 * @param wheelTexture
	 * @param frontTexture
	 * @param x
	 * @param y
	 * @param direction
	 * @param stage
	 */
	public CombinedWheel(Texture wheelTexture, Texture frontTexture, 
			float x, float y, EnumRotateDirection direction, Stage stage) {
		super(wheelTexture, x, y, direction);
		this.loadFrontImage("front", frontTexture);
		this.setFrontImagePosition();
		this.loadActor(stage);
	}
	
	/**
	 * 
	 * @param wheelRegion
	 * @param frontRegion
	 * @param x
	 * @param y
	 * @param direction
	 * @param stage
	 */
	public CombinedWheel(TextureRegion wheelRegion, TextureRegion frontRegion, 
			float x, float y, EnumRotateDirection direction, Stage stage){
		super(wheelRegion, x, y, direction);
		this.loadFrontImage("front", frontRegion);
		this.setFrontImagePosition();
		this.loadActor(stage);
	}

	/**
	 * 
	 * @param frontName
	 * @param frontTexture
	 */
	private void loadFrontImage(String frontName, Texture frontTexture){
		this.frontImage = new Image(name, frontTexture);
	}
	
	/**
	 * 
	 * @param frontName
	 * @param frontRegion
	 */
	private void loadFrontImage(String frontName, TextureRegion frontRegion){
		this.frontImage = new Image(frontName, frontRegion);
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
