/**
 * 
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

	private void loadSelectTiltleImage() {
		Texture selectTitleTexture = ResourceLoader
				.loadTexture("selectleveltitle_512_64.png");
		this.selectTitleImage = new FlatImage(selectTitleTexture, 0f, 256f,
				this);
	}

	private void loadLevelOneImage() {
		Texture levelOneTexture = ResourceLoader
				.loadTexture("level1_128_256.png");
		this.levelOneImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.LEVELONESTAGE, levelOneTexture, 64f, 32f);
	}

	private void loadLevelTwoImage() {
		Texture levelTwoTexture = ResourceLoader.loadTexture(""
				+ "level2_128_256.png");
		this.levelTwoImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.LEVELTWOSTAGE, levelTwoTexture, 180f, 32f);
	}

	private void loadLevelThreeImage() {
		Texture levelThreeTexture = ResourceLoader
				.loadTexture("level3_128_256.png");
		this.levelThreeImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.LEVELTHREESTAGE, levelThreeTexture, 296f, 32f);
	}

	private void loadQuitImage() {
		Texture quitTexture = ResourceLoader
				.loadTexture("quitbutton_128_64.png");
		this.quitImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.STARTMENUSTAGE, quitTexture, 366f, 0f);
	}

	@Override
	public void load() {
		this.loadSelectTiltleImage();
		this.loadLevelOneImage();
		this.loadLevelTwoImage();
		this.loadLevelThreeImage();
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
