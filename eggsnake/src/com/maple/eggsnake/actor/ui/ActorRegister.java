/** 
 * @description	: 负责向Stage中添加Actor
 * @author		: 王志伟
 * @created		: 2012-1-3
 */

package com.maple.eggsnake.actor.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.actor.wheel.NavigatorImage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.common.EnumDestStage;
import com.maple.eggsnake.stage.content.common.EnumRotateDirection;

public class ActorRegister{
	
	private static RotateAnimateImage firstImage;
	private static Image lastImage;
	private static Image generalImage;
	
	/**
	 * 
	 * @param stage
	 * @param first
	 * @param last
	 * @param x
	 * @param y
	 * @param isSnake
	 */
	public static void combineRegister(BaseStage stage, Texture first, Texture last,
			float x, float y, boolean isSnake){
		firstImage = new RotateAnimateImage("first", first, 4f, EnumRotateDirection.CLOCKWISE);
		firstImage.x = x;
		firstImage.y = y;
		lastImage = new Image("last", last);
		if(!isSnake){
			lastImage.x = x + 5f;
			lastImage.y = y;
		}
		else{
			lastImage.x = x + 10f;
			lastImage.y = y;
		}
		stage.addActor(firstImage);
		stage.addActor(lastImage);
	}
	
	/**
	 * 
	 * @param stage
	 * @param first
	 * @param last
	 * @param x
	 * @param y
	 */
	public static void combineRegister(BaseStage stage, Texture first, Texture last,
			float x, float y){
		firstImage = new RotateAnimateImage("first", first);
		firstImage.x = x;
		firstImage.y = y;
		lastImage = new Image("last", last);
		lastImage.x = x - 46f;
		lastImage.y = y + 20f;
		stage.addActor(firstImage);
		stage.addActor(lastImage);
	}
	
	
	/**
	 * 
	 * @param stage
	 * @param first
	 * @param last
	 * @param x
	 * @param y
	 * @param displacementX
	 * @param displacementY
	 */
	public static void combineRegister(BaseStage stage, Texture first, Texture last,
			float x, float y, float displacementX, float displacementY){
		firstImage = new RotateAnimateImage("first", first, 
				4f, EnumRotateDirection.CLOCKWISE);
		firstImage.x = x;
		firstImage.y = y;
		lastImage = new Image("last", last);
		lastImage.x = x + displacementX;
		lastImage.y = y + displacementY;
		stage.addActor(firstImage);
		stage.addActor(lastImage);
	}
	
	
	/**
	 * 
	 * @param stage
	 * @param texture
	 * @param x
	 * @param y
	 */
	public static void singleRegister(BaseStage stage, Texture texture, 
			float x, float y){
		generalImage = new Image("Quit", texture);
		generalImage.x = x;
		generalImage.y = y;
		stage.addActor(generalImage);
	}	
	
	
	/**
	 * 
	 * @param stage
	 * @param region
	 * @param x
	 * @param y
	 */
	public static void singleRegister(BaseStage stage, TextureRegion region,
			float x, float y){
		generalImage = new Image("Quit", region);
		generalImage.x = x;
		generalImage.y = y;
		stage.addActor(generalImage);
	}
	

	/**
	 * 
	 * @param screen
	 * @param sourceStage
	 * @param destStage
	 * @param texture
	 * @param x
	 * @param y
	 */
	public static void navigateRegister(ContentScreen screen, BaseStage sourceStage,
			EnumDestStage destStage, Texture texture, float x, float y){
		generalImage = new NavigatorImage(screen, destStage ,"default", texture);
		generalImage.x = x;
		generalImage.y = y;
		sourceStage.addActor(generalImage);
	} 
}
