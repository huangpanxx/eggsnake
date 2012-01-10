package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.eggenum.EnumDestStage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class LevelOneStage extends BaseStage implements ActorLoader {

	private ContentScreen contentScreen;
	
	private Texture quitTexture;// quit纹理
	@SuppressWarnings("unused")
	private float generalTextureWidth; // 作为临时变量，保存任意纹理宽度
	@SuppressWarnings("unused")
	private float generalTextureHeight; // 作为临时变量，保存任意纹理高度
	
	public LevelOneStage(ContentScreen screen, float width, float height, 
			boolean stretch) {
		super(width, height, stretch);
		this.initContent(screen);
		CurrentLevel.getInstance().setLevel(2);
		this.load();
	}

	@Override
	public void initContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	@Override
	public void loadTextures() {
		quitTexture = ResourceLoader.loadTexture("quitbutton_128_64.png");
	}

	/**
	 * @author zhiwei.wang
	 */
	private void loadQuitImage() {
		generalTextureWidth = this.quitTexture.getWidth();
		ActorRegister.navigateRegister(contentScreen, this, EnumDestStage.SELECTLEVELSTAGE,
				quitTexture, 380, 0);
	}
	
	@Override
	public void load() {
		// TODO Auto-generated method stub
		this.loadTextures();
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
	
	@Override
	public boolean keyDown(int keycode){
		if(keycode == Input.Keys.W){
			this.contentScreen.navigate(new SelectLevelStage(contentScreen,
					contentScreen.getWidth(), contentScreen.getHeight(), true));
		}
		return true;
	}

}
