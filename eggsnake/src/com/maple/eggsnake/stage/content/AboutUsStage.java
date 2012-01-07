/** 
 * @description	: AboutUsStage继承自BaseStage
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

public class AboutUsStage extends BaseStage implements ActorLoader{

	private Texture titileTexture;// 标题纹理
	private Texture snakeImageTexture;// 蛇图纹理
	private Texture snakeFontTexture;// 蛋蛋蛇问字纹理
	private Texture quitTexture;// quit纹理
	private Texture aboutUsInfor;//aboutus信息

	private ContentScreen contentScreen;// 中间层的Screen

	private float generalTextureWidth; // 作为临时变量，保存任意纹理宽度
	private float generalTextureHeight; // 作为临时变量，保存任意纹理高度
	@SuppressWarnings("unused")
	private BaseStage generalDestStage;// 保存目标stage

	public AboutUsStage(ContentScreen screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		this.initContent(screen);
	}

	/**
	 * 
	 */
	public void initContent(ContentScreen screen) {
		this.contentScreen = screen;
	}
	
	/**
	 * @author zhiwei.wang
	 */
	public void loadTextures() {
		titileTexture = ResourceLoader.loadTexture("aboutusstage_256_64.png");
		snakeImageTexture = ResourceLoader.loadTexture("wholesnake_128_128.png");
		snakeFontTexture = ResourceLoader.loadTexture("titleeggsnake_128_32.png");
		aboutUsInfor = ResourceLoader.loadTexture("aboutUS_info_512_256.png");
		quitTexture = ResourceLoader.loadTexture("quitbutton_128_64.png");
	}
	
	/**
	 * 
	 */
	public void load() {
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
						- generalTextureHeight - 10f);
	}

	/**
	 * @author zhiwei.wang
	 */
	private void loadSnakeImage() {
		generalTextureWidth = this.snakeImageTexture.getWidth();
		generalTextureHeight = this.snakeImageTexture.getHeight();
		ActorRegister.singleRegister(this,
				new TextureRegion(snakeImageTexture), this.width
						- generalTextureWidth + 20f, this.height
						- generalTextureHeight - 10f);
	}

	/**
	 * @author zhiwei.wang
	 * @description “egg snake”与“about us”左对齐
	 */
	private void loadSnakeFontImage() {
		generalTextureWidth = this.snakeFontTexture.getWidth();
		generalTextureHeight = this.snakeFontTexture.getHeight();
		ActorRegister.singleRegister(this, new TextureRegion(snakeFontTexture),
				(this.width - this.titileTexture.getWidth()) / 2 - 10f, 10f);
	}
	
	/**
	 * @description 
	 */
	private void loadAboutUsInforImage(){
		ActorRegister.singleRegister(this, new TextureRegion(this.aboutUsInfor),
				-30f, 10f);
	}

	/**
	 * @author zhiwei.wang
	 */
	private void loadQuitImage() {
		generalTextureWidth = this.quitTexture.getWidth();
		ActorRegister.navigateRegister(contentScreen, this, EnumStage.STARTMENUSTAGE,
				quitTexture, this.width - generalTextureWidth, 0);
	}

}