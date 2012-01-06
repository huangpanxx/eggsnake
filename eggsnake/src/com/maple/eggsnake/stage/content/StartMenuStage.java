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
	private ContentScreen contentScreen;// 中间层的Screen

	private float generalTextureWidth; // 作为临时变量，保存任意纹理宽度
	private float generalTextureHeight; // 作为临时变量，保存任意纹理高度
	private TextureRegion generalTextureRegion;// 保存纹理的部分区域

	public StartMenuStage(ContentScreen screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		this.contentScreen = screen;
		this.initTextures();
		this.loadTitleImage();
		this.loadRMouseWheelImage();
		this.loadLSnakeWheelImage();
		this.loadQuitImage();
	}

	/**
	 * @description 加载标题"EggSnake"
	 */
	private void loadTitleImage() {
		generalTextureWidth = this.titileTexture.getWidth();
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
		ActorRegister.singleRegister(this, quitTexture, this.width
				- generalTextureWidth + 10f, 0);
	}

	/**
	 * @description 初始化相关纹理
	 */
	private void initTextures() {
		titileTexture = new Texture(
				Gdx.files.internal("data/images/fonteggsnake_512_128.png"));
		wheelTexture = new Texture(
				Gdx.files.internal("data/images/wheel_128_128.png"));
		wheelSnakeTexture = new Texture(
				Gdx.files.internal("data/images/halfeggsnake_128_128.png"));
		wheelMouseTexture = new Texture(
				Gdx.files.internal("data/images/oneeyemouse_64_64.png"));
		quitTexture = new Texture(
				Gdx.files.internal("data/images/quitbutton_128_64.png"));
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
	 * @return the titileTexture
	 */
	public Texture getTitileTexture() {
		return titileTexture;
	}

	/**
	 * @param titileTexture
	 *            the titileTexture to set
	 */
	public void setTitileTexture(Texture titileTexture) {
		this.titileTexture = titileTexture;
	}

	/**
	 * @return the wheelSnakeTexture
	 */
	public Texture getWheelSnakeTexture() {
		return wheelSnakeTexture;
	}

	/**
	 * @param wheelSnakeTexture
	 *            the wheelSnakeTexture to set
	 */
	public void setWheelSnakeTexture(Texture wheelSnakeTexture) {
		this.wheelSnakeTexture = wheelSnakeTexture;
	}

	/**
	 * @return the wheelMouseTexture
	 */
	public Texture getWheelMouseTexture() {
		return wheelMouseTexture;
	}

	/**
	 * @param wheelMouseTexture
	 *            the wheelMouseTexture to set
	 */
	public void setWheelMouseTexture(Texture wheelMouseTexture) {
		this.wheelMouseTexture = wheelMouseTexture;
	}

	/**
	 * @return the quitTexture
	 */
	public Texture getQuitTexture() {
		return quitTexture;
	}

	/**
	 * @param quitTexture
	 *            the quitTexture to set
	 */
	public void setQuitTexture(Texture quitTexture) {
		this.quitTexture = quitTexture;
	}

	/**
	 * @return the wheelTexture
	 */
	public Texture getWheelTexture() {
		return wheelTexture;
	}

	/**
	 * @param wheelTexture
	 *            the wheelTexture to set
	 */
	public void setWheelTexture(Texture wheelTexture) {
		this.wheelTexture = wheelTexture;
	}
}
