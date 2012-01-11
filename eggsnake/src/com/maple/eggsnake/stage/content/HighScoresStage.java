/** 
 * @description	: HighScoresStage高分榜界面
 * @author		: 王志伟
 * @created		: 2012-1-9
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.maple.eggsnake.actor.lightlabel.ScoreLightLabel;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.common.EnumDestStage;

public class HighScoresStage extends BaseStage implements ActorLoader {

	private Texture menuTexture; // 菜单纹理
	private Texture replayTexture; // 重新开始纹理
	private Texture nextTexture; // 下一关纹理
	private Texture wheelTexture; // 旋转轮子纹理
	private Texture successMouseTexture; // 过关成功老鼠纹理()
	private Texture dottedLineTexture; // 虚线纹理
	private Texture levelOneTitleTexture;// 第一关高分榜数字纹理
	private Texture levelInformationTexure;

	@SuppressWarnings("unused")
	private int currentLevel; // 当前所处关卡
	@SuppressWarnings("unused")
	private int scroes;// 撞击次数
	private ContentScreen contentScreen;// 中间层的Screen
	
	private ScoreLightLabel scorelightLabel;

	public HighScoresStage(ContentScreen screen, float width, float height,
			boolean stretch, int hitTimes, int level) {
		super(width, height, stretch);
		this.loadContent(screen);
		this.loadScroes(hitTimes);
		this.loadCurrentLevel(level);
		this.loadScorelightLabel(hitTimes);
		this.load();
	}

	@Override
	public void loadContent(ContentScreen screen) {
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
		this.dottedLineTexture = ResourceLoader
				.loadTexture("dottedline_512_8.png");
		this.levelOneTitleTexture = ResourceLoader
				.loadTexture("1-1highscores_128_64.png");
		this.levelInformationTexure = 
				ResourceLoader.loadTexture("levelsucceed_256_128.png");
	}

	private void loadMenuImage() {
		ActorRegister.navigateRegister(contentScreen, this,
				EnumDestStage.STARTMENUSTAGE, menuTexture, 75f, 20f);
	}

	private void loadReplayImage() {
		ActorRegister.navigateRegister(contentScreen, this,
				EnumDestStage.REPLAYSTAGE, replayTexture, 195f, 20f);
	}

	private void loadNextImage() {
			ActorRegister.navigateRegister(contentScreen, this,
					EnumDestStage.NEXTLEVELSTAGE, nextTexture, 315f, 20f);
	}

	private void loadDottedLineImage() {
		ActorRegister.singleRegister(this, new TextureRegion(dottedLineTexture,
				0, 0, 430, 8), 10, 80);
		ActorRegister.singleRegister(this, new TextureRegion(dottedLineTexture,
				0, 0, 430, 8), 10, 250);
	}

	private void loadScoreWheelImage() {
		ActorRegister.combineRegister(this, this.wheelTexture,
				this.successMouseTexture, 312f, 110f, 30f, 30f);
	}

	private void loadLevelOneTitleImage() {
		ActorRegister.singleRegister(this, levelOneTitleTexture, 96, 256);
	}
	
    private void loadLevelInformationImage(){
		ActorRegister.singleRegister(this, levelInformationTexure, 55, 120);
	}
	
	private void loadScroes(int hitTimes){
		this.scroes = hitTimes;
	}

	private void loadCurrentLevel(int level){
		this.currentLevel = level;
	}
	
	private void loadScorelightLabel(int scores){
		
		
		
		this.scorelightLabel = new ScoreLightLabel("score", new BitmapFont(),
				scores);
		this.scorelightLabel.x = 195f;
		this.scorelightLabel.y = 160f;
		this.addActor(scorelightLabel);
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
		this.loadLevelInformationImage();
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
