/**
 * @author zhiwei.wang
 * @version 0.0
 * @created 03-一月-2012 17:34:18
 */

package com.maple.eggsnake.actor.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

public class ActorRegister{
	
	private static RotateAnimateImage firstImage;
	private static Image lastImage;
	private static Image generalImage;
	
	public static void combineRegister(Stage stage, Texture first, Texture last,
			float x, float y, boolean isSnake){
		firstImage = new RotateAnimateImage("first", first, 4f, RotateDirection.CLOCKWISE);
		firstImage.x = x;
		firstImage.y = y;
		lastImage = new Image("last", last);
		if(!isSnake){
			lastImage.x = x + lastImage.width / 2;
			lastImage.y = y + lastImage.height / 2;
		}
		else{
			lastImage.x = x + 10f;
			lastImage.y = y + 10f;
		}
		stage.addActor(firstImage);
		stage.addActor(lastImage);
	}
	
	public static void combineRegister(Stage stage, Texture first, Texture last,
			float x, float y){
		firstImage = new RotateAnimateImage("first", first);
		firstImage.x = x;
		firstImage.y = y;
		lastImage = new Image("last", last);
		lastImage.x = x - 46;
		lastImage.y = y + 20;
		stage.addActor(firstImage);
		stage.addActor(lastImage);
	}
	
	public static void singleRegister(Stage stage, Texture texture, 
			float x, float y){
		generalImage = new Image("Quit", texture);
		generalImage.x = x;
		generalImage.y = y;
		stage.addActor(generalImage);
	}	
	
	public static void singleRegister(Stage stage, TextureRegion region,
			float x, float y){
		generalImage = new Image("Quit", region);
		generalImage.x = x;
		generalImage.y = y;
		stage.addActor(generalImage);
	}
}