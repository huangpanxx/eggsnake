/** 
 * @description	: StartMenuStage继承自BaseStage
 * @author		: 王志伟
 * @created		: 2012-1-6
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.actor.wheel.FlatImage;
import com.maple.eggsnake.actor.wheel.MusicImage;
import com.maple.eggsnake.actor.wheel.NavigatorImage;
import com.maple.eggsnake.actor.wheel.SoundImage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.common.EnumDestStage;

public class SettingStage extends BaseStage implements ActorLoader {

	@SuppressWarnings("unused")
	private Image quitImage;
	@SuppressWarnings("unused")
	private Image titileImage;
	@SuppressWarnings("unused")
	private Image fontEggSnakeImage;
	@SuppressWarnings("unused")
	private Image upMaskImage;
	@SuppressWarnings("unused")
	private Image downMaskImage;
	@SuppressWarnings("unused")
	private Image twoEyeMouseImage;

	@SuppressWarnings("unused")
	private Image musicImage;
	@SuppressWarnings("unused")
	private Image soundImage;

	private ContentScreen contentScreen;

	public SettingStage(ContentScreen screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		this.loadContent(screen);
		this.load();

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

	@Override
	public void loadContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	@Override
	public void load() {
		this.loadUpMaskImage();
		this.loadDownMaskImage();
		this.loadTitleImage();
		this.loadFontEggSnakeImage();
		this.loadMusicImage();
		this.loadSoundImage();
		this.loadTwoEyeMouseImage();
		this.loadQuitImage();
	}

	private void loadUpMaskImage() {
		Texture maskTexture = ResourceLoader.loadTexture("mask_512_128.png");
		this.upMaskImage = new FlatImage(maskTexture, -16f, 212f, this);
	}

	private void loadDownMaskImage() {
		Texture maskTexture = ResourceLoader.loadTexture("mask_512_128.png");
		this.downMaskImage = new FlatImage(maskTexture, -16f, -60f, this);
	}

	/**
	 * @description 加载setting标题
	 */
	private void loadTitleImage() {
		Texture titleTexture = ResourceLoader
				.loadTexture("titlesettings_256_64.png");
		this.titileImage = new FlatImage(titleTexture, 92f, 246f, this);
	}

	private void loadMusicImage(){
		TextureRegion openedRegion = new TextureRegion(
				ResourceLoader.loadTexture("musicopened_128_128.png"));
		TextureRegion closedRegion = new TextureRegion(
				ResourceLoader.loadTexture("musicclosed_128_128.png"));
		this.musicImage = new MusicImage(openedRegion, closedRegion,
				250f, 100f, this);
	}
	
	private void loadSoundImage(){
		TextureRegion openedRegion = new TextureRegion(
				ResourceLoader.loadTexture("soundopened_128_128.png"));
		TextureRegion closedRegion = new TextureRegion(
				ResourceLoader.loadTexture("soundclosed_128_128.png"));
		this.soundImage = new SoundImage(openedRegion, closedRegion,
				30f, 100f, this);
	}
	
	/**
	 * @description 加载退出按钮
	 */
	private void loadQuitImage() {
		Texture quitTexture = ResourceLoader
				.loadTexture("quitbutton_128_64.png");
		this.quitImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.STARTMENUSTAGE, quitTexture, 372f, 0f);
	}

	private void loadFontEggSnakeImage() {
		Texture fontEggSnakeTexture = ResourceLoader
				.loadTexture("titleeggsnake_128_32.png");
		this.fontEggSnakeImage = new FlatImage(fontEggSnakeTexture, 112f, 24f,
				this);
	}

	private void loadTwoEyeMouseImage() {
		Texture twoEyeMouseTexture = ResourceLoader
				.loadTexture("twoeyesmouse_64_64.png");
		this.twoEyeMouseImage = new FlatImage(twoEyeMouseTexture, 400f, 190f,
				this);
	}

}
