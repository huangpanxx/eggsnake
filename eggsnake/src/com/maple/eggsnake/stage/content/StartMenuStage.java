/** 
 * @description	: StartMenuStage继承自BaseStage
 * @author		: 王志伟
 * @created		: 2012-1-6
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.stage.BaseStage;

public class StartMenuStage extends BaseStage {

	private Texture titileTexture;// 标题纹理
	private Texture wheelSnakeTexture;// 旋转轮子上的蛇图纹理
	private Texture wheelMouseTexture;// 旋转轮子上的小老鼠纹理
	private Texture quitTexture;// quit纹理
	private Texture wheelTexture;// 旋转轮子纹理
	private Texture newGameTexture;// new game文字纹理
	private Texture settingTexture;// setting文字纹理
	private Texture aboutUsTexture;// aboutUs文字纹理

	private ContentScreen contentScreen;// 中间层的Screen
	private int contentScreenWidth;// 中间层的Screen的宽度
	private int contentScreenHeight;// 中间层的Screen的高度

	private float generalTextureWidth; // 保存任意纹理宽度
	private float generalTextureHeight; // 保存任意纹理高度
	private TextureRegion generalTextureRegion;// 保存纹理的部分区域
	private BaseStage generalDestStage;// 保存目标BaseStage

	public StartMenuStage(ContentScreen screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		this.initContent(screen);
		this.load();
	}

	/**
	 * 
	 */
	private void initContent(ContentScreen screen) {
		this.contentScreen = screen;
		this.contentScreenWidth = this.contentScreen.getWidth();
		this.contentScreenHeight = this.contentScreen.getHeight();
	}
	
	/**
	 * 
	 */
	private void load() {
		this.loadTextures();
		this.loadTitleImage();
		this.loadRMouseWheelImage();
		this.loadLSnakeWheelImage();
		this.loadNewGameImage();
		this.loadSettingImage();
		this.loadAboutUsImage();
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
	
	/**
	 * @description 加载标题"EggSnake"
	 */
	private void loadTitleImage() {
		generalTextureHeight = this.titileTexture.getHeight();
		generalTextureRegion = new TextureRegion(this.titileTexture, 16f, 0f,
				480f, 128f);
		ActorRegister.singleRegister(this, generalTextureRegion, 0f,
				this.height - generalTextureHeight);
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
		/*
		 * generalDestStage = new AboutUsStage(this.contentScreen,
		 * this.contentScreenWidth, this.contentScreenHeight, true);
		 * ActorRegister.navigateRegister(contentScreen, this, generalDestStage,
		 * quitTexture, this.width - generalTextureWidth + 10f, 0);
		 */
	}

	/**
	 * @description 加载new game
	 */
	private void loadNewGameImage() {
		generalDestStage = new GameStage(contentScreen,
				this.contentScreenWidth, this.contentScreenHeight, true);
		ActorRegister.navigateRegister(contentScreen, this, generalDestStage,
				newGameTexture, 112f, 183f);
	}

	/**
	 * @description 加载Setting
	 */
	private void loadSettingImage() {
		generalDestStage = new SettingStage(this.contentScreen,
				this.contentScreenWidth, this.contentScreenHeight, true);
		ActorRegister.navigateRegister(contentScreen, this, generalDestStage,
				settingTexture, 112f, 128f);
	}

	/**
	 * @description 加载aboutus
	 */
	private void loadAboutUsImage() {
		generalDestStage = new AboutUsStage(contentScreen,
				this.contentScreenWidth, this.contentScreenHeight, true);
		ActorRegister.navigateRegister(contentScreen, this, generalDestStage,
				aboutUsTexture, 112f, 73f);
	}

	/**
	 * @description 初始化相关纹理
	 */
	private void loadTextures() {
		titileTexture = new Texture(
				Gdx.files.internal("data/images/fonteggsnake_512_128.png"));
		wheelTexture = new Texture(
				Gdx.files.internal("data/images/wheel_128_128.png"));
		wheelSnakeTexture = new Texture(
				Gdx.files.internal("data/images/halfeggsnake_128_128.png"));
		wheelMouseTexture = new Texture(
				Gdx.files.internal("data/images/mousequeue_128_128.png"));
		quitTexture = new Texture(
				Gdx.files.internal("data/images/quitbutton_128_64.png"));
		newGameTexture = new Texture(
				Gdx.files.internal("data/images/newgamebtn256_64.png"));
		settingTexture = new Texture(
				Gdx.files.internal("data/images/settingsbtn256_64.png"));
		aboutUsTexture = new Texture(
				Gdx.files.internal("data/images/aboutusbtn256_64.png"));
	}

}
