/** 
 * @description	: AboutUsStage继承自BaseStage
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

public class AboutUsStage extends BaseStage {

	private Texture titileTexture;// 标题纹理
	private Texture snakeImageTexture;// 蛇图纹理
	private Texture snakeFontTexture;// 蛋蛋蛇问字纹理
	private Texture quitTexture;// quit纹理

	private ContentScreen contentScreen;// 中间层的Screen
	private int contentScreenWidth;// 中间层的Screen的宽度
	private int contentScreenHeight;// 中间层的Screen的高度

	private float generalTextureWidth; // 作为临时变量，保存任意纹理宽度
	private float generalTextureHeight; // 作为临时变量，保存任意纹理高度
	private BaseStage generalDestStage;// 保存目标stage

	public AboutUsStage(ContentScreen screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		this.initContent(screen);
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
		this.loadSnakeImage();
		this.loadSnakeFontImage();
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
		this.load();
	}

	/**
	 * @author zhiwei.wang
	 */
	private void loadTitleImage() {
		generalTextureWidth = this.titileTexture.getWidth();
		generalTextureHeight = this.titileTexture.getHeight();
		ActorRegister.singleRegister(this, new TextureRegion(titileTexture),
				(this.width - generalTextureWidth) / 2 - 10f, this.height
						- generalTextureHeight);
	}

	/**
	 * @author zhiwei.wang
	 */
	private void loadSnakeImage() {
		generalTextureWidth = this.snakeImageTexture.getWidth();
		generalTextureHeight = this.snakeImageTexture.getHeight();
		ActorRegister.singleRegister(this,
				new TextureRegion(snakeImageTexture), this.width
						- generalTextureWidth, this.height
						- generalTextureHeight - 50);
	}

	/**
	 * @author zhiwei.wang
	 * @description “egg snake”与“about us”左对齐
	 */
	private void loadSnakeFontImage() {
		generalTextureWidth = this.snakeFontTexture.getWidth();
		generalTextureHeight = this.snakeFontTexture.getHeight();
		ActorRegister.singleRegister(this, new TextureRegion(snakeFontTexture),
				(this.width - this.titileTexture.getWidth()) / 2 - 10, 0);
	}

	/**
	 * @author zhiwei.wang
	 */
	private void loadQuitImage() {
		generalTextureWidth = this.quitTexture.getWidth();
		generalDestStage = new StartMenuStage(contentScreen,
				contentScreenWidth, contentScreenHeight, true);
		ActorRegister.navigateRegister(contentScreen, this, generalDestStage,
				quitTexture, this.width - generalTextureWidth, 0);
	}

	/**
	 * @author zhiwei.wang
	 */
	private void loadTextures() {
		titileTexture = new Texture(
				Gdx.files.internal("data/images/aboutusstage_256_64.png"));
		snakeImageTexture = new Texture(
				Gdx.files.internal("data/images/wholesnake_128_128.png"));
		snakeFontTexture = new Texture(
				Gdx.files.internal("data/images/titleeggsnake_128_32.png"));
		quitTexture = new Texture(
				Gdx.files.internal("data/images/aboutquitbutton_128_64.png"));
	}

}