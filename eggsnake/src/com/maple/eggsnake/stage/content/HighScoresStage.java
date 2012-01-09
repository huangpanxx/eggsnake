/** 
 * @description	: HighScoresStage高分榜界面
 * @author		: 王志伟
 * @created		: 2012-1-9
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.graphics.Texture;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.eggenum.EnumStage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class HighScoresStage extends BaseStage implements ActorLoader{
	
	private Texture menuTexture; //菜单纹理
	private Texture replayTexture; //重新开始纹理
	private Texture nextTexture; //下一关纹理
	private Texture wheelTexture; //旋转轮子纹理
	private Texture mouseTexture; //老鼠纹理
	
	private ContentScreen contentScreen;// 中间层的Screen

	private float generalTextureWidth; // 作为临时变量，保存任意纹理宽度
	private float generalTextureHeight; // 作为临时变量，保存任意纹理高度

	public HighScoresStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
	}

	@Override
	public void initContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	@Override
	public void loadTextures() {
		this.menuTexture = ResourceLoader.loadTexture("menu_128_64.png");
		this.replayTexture = ResourceLoader.loadTexture("replay_128_64.png");
		this.nextTexture = ResourceLoader.loadTexture("next_128_64.png");
	}
	
	private void loadMenuImage(){
		ActorRegister.navigateRegister(contentScreen, this, EnumStage.STARTMENUSTAGE,
				menuTexture, 75f, 20f);
	}
	
	private void loadReplayImage(){
		ActorRegister.navigateRegister(contentScreen, this, EnumStage.GAMESTAGE,
				replayTexture, 155f, 10f);
	}
	
	private void loadNextImage(){
		//ActorRegister.navigateRegister(contentScreen, this, EnumStage., texture, x, y)
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
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
