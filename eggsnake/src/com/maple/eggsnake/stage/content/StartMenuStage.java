/** 
 * @description	: StartMenuStage继承自BaseStage
 * @author		: 王志伟
 * @created		: 2012-1-6
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class StartMenuStage extends BaseStage implements ActorLoader {

	private Texture titileTexture;// 标题纹理
	private Texture wheelSnakeTexture;// 旋转轮子上的蛇图纹理
	private Texture wheelMouseTexture;// 旋转轮子上的小老鼠纹理
	private Texture quitTexture;// quit纹理
	private Texture wheelTexture;// 旋转轮子纹理
	private Texture newGameTexture;// new game文字纹理
	private Texture settingTexture;// setting文字纹理
	private Texture aboutUsTexture;// aboutUs文字纹理
	private Texture startEggTexture;//
	private Texture oneEyeMouseTexture;
	private Texture twoEyeMouseTexture;

	private ContentScreen contentScreen;// 中间层的Screen

	private float generalTextureWidth; // 保存任意纹理宽度
	@SuppressWarnings("unused")
	private float generalTextureHeight; // 保存任意纹理高度
	@SuppressWarnings("unused")
	private TextureRegion generalTextureRegion;// 保存纹理的部分区域

	public StartMenuStage(ContentScreen screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		this.initContent(screen);
	}

	/**
	 * 
	 */
	@Override
	public void initContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	/**
	 * @description 初始化相关纹理
	 */
	@Override
	public void loadTextures() {
		titileTexture = ResourceLoader.loadTexture("fonteggsnake_512_128.png");
		wheelTexture = ResourceLoader.loadTexture("wheel_128_128.png");
		wheelSnakeTexture = ResourceLoader
				.loadTexture("halfeggsnake_128_128.png");
		wheelMouseTexture = ResourceLoader
				.loadTexture("mousequeue_128_128.png");
		quitTexture = ResourceLoader.loadTexture("quitbutton_128_64.png");
		newGameTexture = ResourceLoader.loadTexture("newgamebtn256_64.png");
		settingTexture = ResourceLoader.loadTexture("settingsbtn256_64.png");
		aboutUsTexture = ResourceLoader.loadTexture("aboutusbtn256_64.png");
		startEggTexture = ResourceLoader.loadTexture("startegg_64_64.png");
		oneEyeMouseTexture = ResourceLoader.loadTexture("oneeyemouse_64_64.png");
		twoEyeMouseTexture = ResourceLoader.loadTexture("twoeyesmouse_64_64.png");
	}

	/**
	 * 
	 */
	@Override
	public void load() {
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
		this.load();
	}

	@Override
	public void dispose() {
		this.newGameTexture.dispose();
		this.settingTexture.dispose();
		this.aboutUsTexture.dispose();
		this.wheelMouseTexture.dispose();
		this.wheelSnakeTexture.dispose();
		this.wheelTexture.dispose();
		super.dispose();
	}

	/**
	 * @description 加载标题"EggSnake"
	 */
	private void loadTitleImage() {
		generalTextureHeight = this.titileTexture.getHeight();
		ActorRegister.singleRegister(this, titileTexture, 0f,
				0.68f * this.height());//待重构
	}

	/**
	 * @description 加载左边旋转轮子特效
	 */
	private void loadLSnakeWheelImage() {
		ActorRegister.combineRegister(this, this.wheelTexture,
				this.wheelSnakeTexture, 0f, 96f, true);
	}

	/**
	 * @description 加载右边旋转轮子
	 */
	private void loadRMouseWheelImage() {
		ActorRegister.combineRegister(this, this.wheelTexture,
				this.wheelMouseTexture, 352f, 100f, false);
	}

	/**
	 * @description 加载退出按钮
	 */
	private void loadQuitImage() {
		generalTextureWidth = this.quitTexture.getWidth();
		ActorRegister.navigateRegister(contentScreen, this,
				EnumStage.DISPOSESTAGE, quitTexture, this.width
						- generalTextureWidth + 10f, 0);
	}

	/**
	 * @description 加载new game
	 */
	private void loadNewGameImage() {
		ActorRegister.navigateRegister(contentScreen, this,
				EnumStage.GAMESTAGE, newGameTexture, 105f, 180f);
	}

	/**
	 * @description 加载Setting
	 */
	private void loadSettingImage() {
		ActorRegister.navigateRegister(contentScreen, this,
				EnumStage.SETTINGSTAGE, settingTexture, 112f, 128f);
	}

	/**
	 * @description 加载aboutus
	 */
	private void loadAboutUsImage() {
		ActorRegister.navigateRegister(contentScreen, this,
				EnumStage.ABOUTUSSTAGE, aboutUsTexture, 112f, 73f);
	}
	
	/**
	 * @description 加载StartEgg
	 */
	private void loadStartEggImage(){
		ActorRegister.singleRegister(this, this.startEggTexture, 0f, 0f);
	}
	
	/**
	 * 
	 * 
	 */
	private void loadOneEyeMouseImage(){
		ActorRegister.singleRegister(this, this.oneEyeMouseTexture, 128f, 0f);
	}
	
	/**
	 * 
	 * 
	 */
	private void loadTwoEyeMouseImage(){
		ActorRegister.singleRegister(this, this.twoEyeMouseTexture, 180f, -10f);
	}

}
