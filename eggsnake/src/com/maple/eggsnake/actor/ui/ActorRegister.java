/** 
 * @description	: 负责向Stage中添加Actor
 * @author		: 王志伟
 * @created		: 2012-1-3
 */

package com.maple.eggsnake.actor.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.eggenum.EnumStage;
import com.maple.eggsnake.eggenum.EnumRotateDirection;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.stage.BaseStage;

public class ActorRegister{
	
	private static RotateAnimateImage firstImage;
	private static Image lastImage;
	private static Image generalImage;
	
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
	
	public static void singleRegister(BaseStage stage, Texture texture, 
			float x, float y){
		generalImage = new Image("Quit", texture);
		generalImage.x = x;
		generalImage.y = y;
		stage.addActor(generalImage);
	}	
	
	public static void singleRegister(BaseStage stage, TextureRegion region,
			float x, float y){
		generalImage = new Image("Quit", region);
		generalImage.x = x;
		generalImage.y = y;
		stage.addActor(generalImage);
	}
	

	public static void navigateRegister(ContentScreen screen, BaseStage sourceStage,
			EnumStage destStage, Texture texture, float x, float y){
		generalImage = new ImageButtonActor(screen, destStage ,"default", texture);
		generalImage.x = x;
		generalImage.y = y;
		sourceStage.addActor(generalImage);
	} 
}
