/** 
 * @description	: SelectLevelStage
 * @author		: 王志伟
 * @created		: 2012-1-8
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.actor.wheel.FlatImage;
import com.maple.eggsnake.actor.wheel.NavigatorImage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.common.EnumDestStage;

public class SelectLevelStage extends BaseStage implements ActorLoader {

	@SuppressWarnings("unused")
	private Image selectTitleImage;
	@SuppressWarnings("unused")
	private Image levelOneImage;
	@SuppressWarnings("unused")
	private Image levelTwoImage;
	@SuppressWarnings("unused")
	private Image levelThreeImage;
	@SuppressWarnings("unused")
	private Image levelFourImage;
	@SuppressWarnings("unused")
	private Image levelFiveImage;
	@SuppressWarnings("unused")
	private Image levelSixImage;
	@SuppressWarnings("unused")
	private Image quitImage;

	private ContentScreen contentScreen;

	public SelectLevelStage(ContentScreen screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		this.loadContent(screen);
		this.load();
	}

	@Override
	public void loadContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	@SuppressWarnings("unused")
	private Image selectMaskImage;
	
	private void loadSelectMaskImage() {
		Texture maskTextue = ResourceLoader
				.loadTexture("selectmask_512_256.png");
		this.selectTitleImage = new FlatImage(maskTextue, 0f, 35f, this);
	}
	
	private void loadSelectTiltleImage() {
		Texture selectTitleTexture = ResourceLoader
				.loadTexture("selectleveltitle_512_64.png");
		this.selectTitleImage = new FlatImage(selectTitleTexture, 5f, 256f,
				this);
	}

	private void loadLevelOneImage() {
		Texture levelOneTexture = ResourceLoader
				.loadTexture("onepink_128_128.png");
		this.levelOneImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.LEVELONESTAGE, levelOneTexture, 64f, 150f);
	}

	private void loadLevelTwoImage() {
		Texture levelTwoTexture = ResourceLoader.loadTexture(""
				+ "twoyellow_128_128.png");
		this.levelTwoImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.LEVELTWOSTAGE, levelTwoTexture, 180f, 150f);
	}

	private void loadLevelThreeImage() {
		Texture levelThreeTexture = ResourceLoader
				.loadTexture("threeblue_128_128.png");
		this.levelThreeImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.LEVELTHREESTAGE, levelThreeTexture, 296f, 150f);
	}
	
	private void loadLevelFourImage(){
		Texture texture = ResourceLoader
				.loadTexture("fourpink_128_128.png");
		this.levelThreeImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.LEVELFOURSTAGE, texture, 64f, 32f);
	}
	
	private void loadLevelFiveImage(){
		Texture texture = ResourceLoader
				.loadTexture("fiveyellow_128_128.png");
		this.levelThreeImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.LEVELFIVESTAGE, texture, 180f, 32f);
	}
	
	private void loadLevelSixImage(){
		Texture texture = ResourceLoader
				.loadTexture("sixblue_128_128.png");
		this.levelThreeImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.LEVELSIXSTAGE, texture, 296f, 35f);
	}

	private void loadQuitImage() {
		Texture quitTexture = ResourceLoader
				.loadTexture("quitbutton_128_64.png");
		this.quitImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.STARTMENUSTAGE, quitTexture, 366f, 0f);
	}

	@Override
	public void load() {
		this.loadSelectMaskImage();
		this.loadSelectTiltleImage();
		this.loadLevelOneImage();
		this.loadLevelTwoImage();
		this.loadLevelThreeImage();
		this.loadLevelFourImage();
		this.loadLevelFiveImage();
		this.loadLevelSixImage();
		this.loadQuitImage();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
}
