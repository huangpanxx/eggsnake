package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.graphics.Texture;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.eggenum.EnumDestStage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class SettingStage extends BaseStage implements ActorLoader{

	private ContentScreen contentScreen;
	private Texture quitTexture;// quit纹理
	@SuppressWarnings("unused")
	private Texture titleTexture;//title纹理
	
	private float generalTextureWidth; // 保存任意纹理宽度
	@SuppressWarnings("unused")
	private float generalTextureHeight; // 保存任意纹理高度
//	private Texture titileTexture;

	public SettingStage(ContentScreen screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		this.initContent(screen);
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
	public void initContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	@Override
	public void loadTextures() {
//		titleTexture = ResourceLoader.loadTexture("titlesettings_256_64.png");
		quitTexture = ResourceLoader.loadTexture("quitbutton_128_64.png");
	}

	@Override
	public void load() {
		this.loadTextures();
//		this.loadTitleImage();
		this.loadQuitImage();
	}
	
	/**
	 * @description 加载setting标题
	 */
	/*private void loadTitleImage(){
		ActorRegister.singleRegister(this, titileTexture, 112f,
				64f);
	}*/
	
	
	/**
	 * @description 加载退出按钮
	 */
	private void loadQuitImage() {
		generalTextureWidth = this.quitTexture.getWidth();
		ActorRegister.navigateRegister(contentScreen, this, EnumDestStage.STARTMENUSTAGE,
				quitTexture, this.width - generalTextureWidth + 10f, 0);
	}

}
