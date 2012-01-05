/**
 * @author zhiwei.wang
 * @version 0.0
 * @created 05-一月-2012 16:14:18
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.stage.BaseStage;

public class AboutUsStage extends BaseStage {

	private Texture titileTexture;// 标题纹理
	private Texture snakeImageTexture;// 蛇图纹理
	private Texture snakeFontTexture;// 蛋蛋蛇问字纹理
	private Texture quitTexture;// quit纹理
	private float generalTextureWidth; // 作为临时变量，保存任意纹理宽度
	private float generalTextureHeight; // 作为临时变量，保存任意纹理高度

	public AboutUsStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.initTextures();
		this.loadTitleImage();
		this.loadSnakeImage();
		this.loadSnakeFontImage();
		this.loadQuitImage();
	}

	/**
	 * @author zhiwei.wang
	 */
	private void loadTitleImage() {
		generalTextureWidth = this.titileTexture.getWidth();
		generalTextureHeight = this.titileTexture.getHeight();
		ActorRegister.singleRegister(this, new TextureRegion(titileTexture),
				(this.width - generalTextureWidth) / 2 - 10, this.height
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
		generalTextureHeight = this.quitTexture.getHeight();
		ActorRegister.singleRegister(this, quitTexture, this.width
				- generalTextureWidth, 0);
	}

	/**
	 * @author zhiwei.wang
	 */
	private void initTextures() {
		titileTexture = new Texture(
				Gdx.files.internal("data/images/aboutUsStage_256_64.png"));
		snakeImageTexture = new Texture(
				Gdx.files.internal("data/images/picSnake_128_128.png"));
		snakeFontTexture = new Texture(
				Gdx.files.internal("data/images/titleEggSnake_128_32.png"));
		quitTexture = new Texture(
				Gdx.files.internal("data/images/quitBtn_128_64.png"));
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
	 * @param titileTexture the titileTexture to set
	 */
	public void setTitileTexture(Texture titileTexture) {
		this.titileTexture = titileTexture;
	}

	/**
	 * @return the snakeImageTexture
	 */
	public Texture getSnakeImageTexture() {
		return snakeImageTexture;
	}

	/**
	 * @param snakeImageTexture the snakeImageTexture to set
	 */
	public void setSnakeImageTexture(Texture snakeImageTexture) {
		this.snakeImageTexture = snakeImageTexture;
	}

	/**
	 * @return the snakeFontTexture
	 */
	public Texture getSnakeFontTexture() {
		return snakeFontTexture;
	}

	/**
	 * @param snakeFontTexture the snakeFontTexture to set
	 */
	public void setSnakeFontTexture(Texture snakeFontTexture) {
		this.snakeFontTexture = snakeFontTexture;
	}

	/**
	 * @return the quitTexture
	 */
	public Texture getQuitTexture() {
		return quitTexture;
	}

	/**
	 * @param quitTexture the quitTexture to set
	 */
	public void setQuitTexture(Texture quitTexture) {
		this.quitTexture = quitTexture;
	}
}
