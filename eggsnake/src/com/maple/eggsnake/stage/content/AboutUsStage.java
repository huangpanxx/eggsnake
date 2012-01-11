/** 
 * @description	: AboutUsStage继承自BaseStage
 * @author		: 王志伟
 * @created		: 2012-1-6
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

public class AboutUsStage extends BaseStage implements ActorLoader {

	@SuppressWarnings("unused")
	private Image titleImage;
	@SuppressWarnings("unused")
	private Image snakeImage;
	@SuppressWarnings("unused")
	private Image snakeFontImage;
	@SuppressWarnings("unused")
	private Image aboutUsInformationImage;
	@SuppressWarnings("unused")
	private Image quitImage;
	@SuppressWarnings("unused")
	private Image upMaskImage;
	@SuppressWarnings("unused")
	private Image downMaskImage;

	private ContentScreen contentScreen;

	public AboutUsStage(ContentScreen screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		this.loadContent(screen);
		this.load();
	}

	public void loadContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	public void loadTextures() {

	}

	public void load() {
		this.loadUpMaskImage();
		this.loadDownMaskImage();
		this.loadTextures();
		this.loadTitleImage();
		this.loadSnakeImage();
		this.loadSnakeFontImage();
		this.loadAboutUsInforImage();
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
	}

	private void loadUpMaskImage() {
		Texture maskTexture = ResourceLoader.loadTexture("mask_512_128.png");
		this.upMaskImage = new FlatImage(maskTexture, -16f, 212f, this);
	}

	private void loadDownMaskImage() {
		Texture maskTexture = ResourceLoader.loadTexture("mask_512_128.png");
		this.downMaskImage = new FlatImage(maskTexture, -16f, -60f, this);
	}
	
	private void loadTitleImage() {
		Texture titileTexture = ResourceLoader
				.loadTexture("aboutusstage_256_64.png");
		this.titleImage = new FlatImage(titileTexture, 102f, 246f, this);
	}

	private void loadSnakeImage() {
		Texture snakeImageTexture = ResourceLoader
				.loadTexture("wholesnake_128_128.png");
		this.snakeImage = new FlatImage(snakeImageTexture, 372f, 182f, this);
	}

	private void loadSnakeFontImage() {
		Texture snakeFontTexture = ResourceLoader
				.loadTexture("titleeggsnake_128_32.png");
		this.snakeFontImage = new FlatImage(snakeFontTexture, 106f, 10f, this);
	}

	private void loadAboutUsInforImage() {
		Texture aboutUsInfor = ResourceLoader
				.loadTexture("aboutusinfo_512_256.png");
		this.aboutUsInformationImage = new FlatImage(aboutUsInfor, -30f, 10f,
				this);
	}

	private void loadQuitImage() {
		Texture quitTexture = ResourceLoader
				.loadTexture("quitbutton_128_64.png");
		this.quitImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.STARTMENUSTAGE, quitTexture, 380f, 0f);
	}

}