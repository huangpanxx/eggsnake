/** 
 * @description	: StartMenuStage继承自BaseStage
 * @author		: 王志伟
 * @created		: 2012-1-6
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.actor.wheel.CombinedWheel;
import com.maple.eggsnake.actor.wheel.FlatImage;
import com.maple.eggsnake.actor.wheel.NavigatorImage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.common.EnumDestStage;
import com.maple.eggsnake.stage.content.common.EnumRotateDirection;

public class StartMenuStage extends BaseStage implements ActorLoader {
	@SuppressWarnings("unused")
	private Image quitImage;
	@SuppressWarnings("unused")
	private Image newGameImage;
	@SuppressWarnings("unused")
	private Image settingImage;
	@SuppressWarnings("unused")
	private Image aboutUsImage;
	@SuppressWarnings("unused")
	private Image titleImage;
	@SuppressWarnings("unused")
	private Image startEggImage;
	@SuppressWarnings("unused")
	private Image oneEyeMouseImage;
	@SuppressWarnings("unused")
	private Image twoEyeMouseImage;
	@SuppressWarnings("unused")
	private Image upMaskImage;
	@SuppressWarnings("unused")
	private Image downMaskImage;

	@SuppressWarnings("unused")
	private CombinedWheel combineRMouseWheel;
	@SuppressWarnings("unused")
	private CombinedWheel combineLSnakeWheel;

	private ContentScreen contentScreen;

	public StartMenuStage(ContentScreen screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		this.loadContent(screen);
		this.load();
	}

	/**
	 * 
	 */
	@Override
	public void loadContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	/**
	 * @description 初始化相关纹理
	 */
	@Override
	public void loadTextures() {
	}

	/**
	 * 
	 */
	@Override
	public void load() {
		this.loadUpMaskImage();
		this.loadDownMaskImage();
		this.loadTextures();
		this.loadTitleImage();
		this.loadRMouseWheelImage();
		this.loadLSnakeWheelImage();
		this.loadNewGameImage();
		this.loadSettingImage();
		this.loadAboutUsImage();
		this.loadStartEggImage();
		this.loadOneEyeMouseImage();
		this.loadTwoEyeMouseImage();
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

	@Override
	public void dispose() {
		super.dispose();
	}

	private void loadUpMaskImage() {
		Texture maskTexture = ResourceLoader.loadTexture("mask_512_128.png");
		this.upMaskImage = new FlatImage(maskTexture, -16f, 242f, this);
	}

	private void loadDownMaskImage() {
		Texture maskTexture = ResourceLoader.loadTexture("mask_512_128.png");
		this.downMaskImage = new FlatImage(maskTexture, -16f, -50f, this);
	}

	/**
	 * @description 加载标题"EggSnake"
	 */
	private void loadTitleImage() {
		Texture titileTexture = ResourceLoader
				.loadTexture("fonteggsnake_512_128.png");
		this.titleImage = new FlatImage(titileTexture, 0, 212f, this);
	}

	/**
	 * @description 加载左边旋转轮子特效
	 */
	private void loadLSnakeWheelImage() {
		Texture wheelTexture = ResourceLoader.loadTexture("wheel_128_128.png");
		Texture wheelSnakeTexture = ResourceLoader
				.loadTexture("halfeggsnake_128_128.png");
		this.combineLSnakeWheel = new CombinedWheel(wheelTexture,
				wheelSnakeTexture, 0f, 96f, EnumRotateDirection.ANTICLOCKWISE,
				this);
	}

	/**
	 * @description 加载右边旋转轮子
	 */
	private void loadRMouseWheelImage() {
		Texture wheelTexture = ResourceLoader.loadTexture("wheel_128_128.png");
		Texture wheelMouseTexture = ResourceLoader
				.loadTexture("mousequeue_128_128.png");
		this.combineRMouseWheel = new CombinedWheel(wheelTexture,
				wheelMouseTexture, 352f, 100f,
				EnumRotateDirection.ANTICLOCKWISE, this);
	}

	/**
	 * @description 加载退出按钮
	 */
	private void loadQuitImage() {
		Texture quitTexture = ResourceLoader
				.loadTexture("quitbutton_128_64.png");
		this.quitImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.DISPOSESTAGE, quitTexture, 380f, 0f);
	}

	/**
	 * @description 加载new game
	 */
	private void loadNewGameImage() {
		Texture newGameTexture = ResourceLoader
				.loadTexture("newgamebtn_256_64.png");
		this.newGameImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.SELECTLEVELSTAGE, newGameTexture, 105f, 180f);
	}

	/**
	 * @description 加载Setting
	 */
	private void loadSettingImage() {
		Texture settingTexture = ResourceLoader
				.loadTexture("settingsbtn_256_64.png");
		this.settingImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.SETTINGSTAGE, settingTexture, 112f, 128f);
	}

	/**
	 * @description 加载aboutus
	 */
	private void loadAboutUsImage() {
		Texture aboutUsTexture = ResourceLoader
				.loadTexture("aboutusbtn_256_64.png");
		this.aboutUsImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.ABOUTUSSTAGE, aboutUsTexture, 112f, 73f);
	}

	/**
	 * @description 加载StartEgg
	 */
	private void loadStartEggImage() {
		Texture startEggTexture = ResourceLoader
				.loadTexture("startegg_64_64.png");
		this.startEggImage = new FlatImage(startEggTexture, 0f, 0f, this);
	}

	private void loadOneEyeMouseImage() {
		Texture oneEyeMouseTexture = ResourceLoader
				.loadTexture("oneeyemouse_64_64.png");
		this.oneEyeMouseImage = new FlatImage(oneEyeMouseTexture, 128f, 0f,
				this);
	}

	private void loadTwoEyeMouseImage() {
		Texture twoEyeMouseTexture = ResourceLoader
				.loadTexture("twoeyesmouse_64_64.png");
		this.twoEyeMouseImage = new FlatImage(twoEyeMouseTexture, 180f, 0f,
				this);
	}
}
