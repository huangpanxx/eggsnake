/** 
 * @description	: HighScoresStage高分榜界面
 * @author		: 王志伟
 * @created		: 2012-1-9
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.eggenum.EnumDestStage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class HighScoresStage extends BaseStage implements ActorLoader {

	private Texture menuTexture; // 菜单纹理
	private Texture replayTexture; // 重新开始纹理
	private Texture nextTexture; // 下一关纹理
	private Texture wheelTexture; // 旋转轮子纹理
	private Texture successMouseTexture; // 过关成功老鼠纹理()
	private Texture failedMouseTexture; // 过关失败纹理
	private Texture dottedLineTexture; // 虚线纹理
	private Texture levelOneTitleTexture;// 第一关高分榜数字纹理

	private ContentScreen contentScreen;// 中间层的Screen

	private boolean isPassed;

	public HighScoresStage(ContentScreen screen, boolean isPassed, float width,
			float height, boolean stretch) {
		super(width, height, stretch);
		this.initContent(screen);
		this.isPassed = isPassed;
		this.load();
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
		this.wheelTexture = ResourceLoader.loadTexture("wheel_128_128.png");
		this.successMouseTexture = ResourceLoader
				.loadTexture("successmouse_64_64.png");
		this.failedMouseTexture = ResourceLoader.loadTexture("failedmouse_64_64.png");
		this.dottedLineTexture = ResourceLoader
				.loadTexture("dottedline_512_8.png");
		this.levelOneTitleTexture = ResourceLoader
				.loadTexture("1-1highscores_128_64.png");
	}

	private void loadMenuImage() {
		ActorRegister.navigateRegister(contentScreen, this,
				EnumDestStage.STARTMENUSTAGE, menuTexture, 75f, 20f);
	}

	private void loadReplayImage() {
		ActorRegister.navigateRegister(contentScreen, this,
				EnumDestStage.GAMESTAGE, replayTexture, 195f, 20f);
	}

	private void loadNextImage() {
		if (1 == CurrentLevel.getInstance().getLevel()) {
			ActorRegister.navigateRegister(contentScreen, this,
					EnumDestStage.LEVELTWOSTAGE, nextTexture, 315f, 20f);
		} else if (2 == CurrentLevel.getInstance().getLevel()) {
			ActorRegister.navigateRegister(contentScreen, this,
					EnumDestStage.LEVELTHREESTAGE, nextTexture, 315f, 20f);
		} else {
			ActorRegister.navigateRegister(contentScreen, this,
					EnumDestStage.LEVELONESTAGE, nextTexture, 315f, 20f);
		}
	}

	private void loadDottedLineImage() {
		ActorRegister.singleRegister(this, new TextureRegion(dottedLineTexture,
				0, 0, 430, 8), 10, 80);
		ActorRegister.singleRegister(this, new TextureRegion(dottedLineTexture,
				0, 0, 430, 8), 10, 250);
	}

	private void loadScoreWheelImage() {
		if(this.isPassed){
			ActorRegister.combineRegister(this, this.wheelTexture,
					this.successMouseTexture, 312f, 110f, 30f, 30f);
		}
		else
		{
			ActorRegister.combineRegister(this, this.wheelTexture,
					this.failedMouseTexture, 312f, 110f, 30f, 30f);
		}
	}

	private void loadLevelOneTitleImage() {
		ActorRegister.singleRegister(this, levelOneTitleTexture, 96, 256);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		this.loadTextures();
		this.loadMenuImage();
		this.loadReplayImage();
		this.loadNextImage();
		this.loadDottedLineImage();
		this.loadScoreWheelImage();
		this.loadLevelOneTitleImage();
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
