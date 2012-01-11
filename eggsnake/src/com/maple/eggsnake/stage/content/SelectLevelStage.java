/**
 * 
 */
package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.common.EnumDestStage;

/**
 * @author Administrator
 *
 */
public class SelectLevelStage extends BaseStage implements ActorLoader {
	
	private Texture selectTitleTexture;
	private Texture levelOneTexture;
	private Texture levelTwoTexture;
	private Texture levelThreeTexture;
	private Texture quitTexture;// quit纹理
	
	private ContentScreen contentScreen;

	public SelectLevelStage(ContentScreen screen, float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.loadContent(screen);
		this.load();
	}


	@Override
	public void loadContent(ContentScreen screen) {
		this.contentScreen = screen;
	}


	@Override
	public void loadTextures() {
		this.selectTitleTexture = ResourceLoader.loadTexture("selectleveltitle_512_64.png");
		this.levelOneTexture = ResourceLoader.loadTexture("level1_128_256.png");
		this.levelTwoTexture = ResourceLoader.loadTexture("level2_128_256.png");
		this.levelThreeTexture = ResourceLoader.loadTexture("level3_128_256.png");
		this.quitTexture = ResourceLoader.loadTexture("quitbutton_128_64.png");
	}


	private void loadSelectTiltleImage(){
		ActorRegister.singleRegister(this, 
				new TextureRegion(selectTitleTexture, 16, 0, 480, 64), 0, 256);
	}
	
	
	private void loadLevelOneImage(){
		ActorRegister.navigateRegister(contentScreen, this,
				EnumDestStage.LEVELONESTAGE, levelOneTexture, 64, 32);
	}
	
	private void loadLevelTwoImage(){
		ActorRegister.navigateRegister(contentScreen, this,
				EnumDestStage.LEVELTWOSTAGE, levelTwoTexture, 180, 32);
	}
	
	private void loadLevelThreeImage(){
		ActorRegister.navigateRegister(contentScreen, this,
				EnumDestStage.LEVELTHREESTAGE, levelThreeTexture, 296, 32);
	}
	
	private void loadQuitImage() {
		ActorRegister.navigateRegister(contentScreen, this, EnumDestStage.STARTMENUSTAGE,
				quitTexture, 366, 0);
	}
	
	@Override
	public void load() {
		this.loadTextures();
		this.loadSelectTiltleImage();
		this.loadLevelOneImage();
		this.loadLevelTwoImage();
		this.loadLevelThreeImage();
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
}
