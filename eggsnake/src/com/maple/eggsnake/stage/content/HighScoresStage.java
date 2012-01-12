/** 
 * @description	: HighScoresStage高分榜界面
 * @author		: 王志伟
 * @created		: 2012-1-9
 */

package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.badlogic.gdx.scenes.scene2d.actors.Label;
import com.maple.eggsnake.actor.lightlabel.ScoreLightLabel;
import com.maple.eggsnake.actor.wheel.CombinedWheel;
import com.maple.eggsnake.actor.wheel.FlatImage;
import com.maple.eggsnake.actor.wheel.NavigatorImage;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.logical.GateRecord;
import com.maple.eggsnake.logical.GateRecordItem;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.common.EnumDestStage;
import com.maple.eggsnake.stage.content.common.EnumRotateDirection;

public class HighScoresStage extends BaseStage implements ActorLoader {

	private int currentLevel; // 当前所处关卡
	private int scroes;// 撞击次数
	private ContentScreen contentScreen;// 中间层的Screen

	private Label currentScoreLabel;
	private Label historyScoreLabel;
	private BitmapFont bitmapFontForLabel;

	private Loggable logger;

	public HighScoresStage(ContentScreen screen, float width, float height,
			boolean stretch, int hitTimes, int level) {
		super(width, height, stretch);
		this.loadContent(screen);
		this.loadScroes(hitTimes);
		this.loadCurrentLevel(level);
		this.changeStartState();
		this.load();
		this.loadCurrentScoreLabel(hitTimes);
		this.logger = DefaultLogger.getDefaultLogger();
		this.loadHistoryHighestScoreLabel();
	}

	@Override
	public void loadContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	@SuppressWarnings("unused")
	private Image scoreMaskImage;

	private void loadScoreMaskImage() {
		Texture maskTextue = ResourceLoader
				.loadTexture("scoremask_512_256.png");
		this.scoreMaskImage = new FlatImage(maskTextue, 0f, 20f, this);
	}

	@SuppressWarnings("unused")
	private Image menuImage;

	private void loadMenuImage() {
		Texture menuTexture = ResourceLoader.loadTexture("menu_128_64.png");
		this.menuImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.STARTMENUSTAGE, menuTexture, 75f, 20f);
	}

	@SuppressWarnings("unused")
	private Image replayImage;

	private void loadReplayImage() {
		Texture replayTexture = ResourceLoader.loadTexture("replay_128_64.png");
		this.replayImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.REPLAYSTAGE, replayTexture, 195f, 20f);
	}

	@SuppressWarnings("unused")
	private Image nextImage;

	private void loadNextImage() {
		Texture nextTexture = ResourceLoader.loadTexture("next_128_64.png");
		this.nextImage = new NavigatorImage(contentScreen, this,
				EnumDestStage.NEXTLEVELSTAGE, nextTexture, 315f, 20f);
	}

	@SuppressWarnings("unused")
	private Image dottedUpLineImage;
	@SuppressWarnings("unused")
	private Image dottedDownLineImage;

	private void loadDottedLineImage() {
		Texture dottedLineTexture = ResourceLoader
				.loadTexture("dottedline_512_8.png");
		this.dottedUpLineImage = new FlatImage(new TextureRegion(
				dottedLineTexture, 0f, 0f, 430f, 8f), -5f, 200f, this);
		this.dottedDownLineImage = new FlatImage(new TextureRegion(
				dottedLineTexture, 0f, 0f, 430f, 8f), -5f, 30f, this);
	}

	@SuppressWarnings("unused")
	private Image scoreWheelImage;

	private void loadScoreWheelImage() {
		Texture wheelTexture = ResourceLoader.loadTexture("wheel_128_128.png");
		Texture successMouseTexture = ResourceLoader
				.loadTexture("successmouse_64_64.png");
		this.scoreWheelImage = new CombinedWheel(wheelTexture,
				successMouseTexture, 312f, 110f,
				EnumRotateDirection.ANTICLOCKWISE, this);
	}

	@SuppressWarnings("unused")
	private Image levelOneTitleImage;

	private void loadLevelOneTitleImage() {
		Texture levelOneTitleTexture = ResourceLoader
				.loadTexture("1-1highscores_128_64.png");
		this.levelOneTitleImage = new FlatImage(levelOneTitleTexture, 96f,
				256f, this);
	}

	@SuppressWarnings("unused")
	private Image levelInformationImage;

	private void loadLevelInformationImage() {

		Texture levelInformationTexure = ResourceLoader
				.loadTexture("levelsucceed_256_128.png");
		this.levelInformationImage = new FlatImage(levelInformationTexure, 55f,
				120f, this);
	}

	private void loadScroes(int hitTimes) {
		this.scroes = hitTimes;
	}

	private void loadCurrentLevel(int level) {
		this.currentLevel = level;
	}

	private void loadCurrentScoreLabel(int scores) {
		this.saveCurrentScore(scores);
		bitmapFontForLabel = ResourceLoader.loadFont("bright.fnt",
				"bright1.png");
		this.currentScoreLabel = new ScoreLightLabel("score",
				bitmapFontForLabel, scores);
		this.currentScoreLabel.x = 230f;
		this.currentScoreLabel.y = 120f;
		this.addActor(currentScoreLabel);
	}

	@SuppressWarnings("unused")
	private int historyHightScore;

	private void loadHistoryHighestScoreLabel() {
		this.historyScoreLabel = new ScoreLightLabel("history",
				bitmapFontForLabel, this.getHistoryScore());
		this.historyScoreLabel.x = 400f;
		this.historyScoreLabel.y = 256f;
		this.addActor(historyScoreLabel);
	}

	private void changeStartState() {
		if (GameStartUp.isFirstStarted) {
			GameStartUp.isFirstStarted = false;
			GateRecord.fetchGateRecord(currentLevel).addRecordItem(
					new GateRecordItem(scroes));
		}
	}

	private int getHistoryScore() {
		int history = -1;
		GateRecord record = GateRecord.fetchGateRecord(currentLevel);
		if (0 != record.getSize())
			history = record.getRecordItem(record.getSize() - 1).score;
		logger.logWithSignature(this, "loadHistoryHighestScoreLabel: %1$d",
				history);
		return history;
	}

	private void saveCurrentScore(int scores) {
		GateRecord record = GateRecord.fetchGateRecord(currentLevel);
		record.addRecordItem(new GateRecordItem(scores));
		try {
			record.save();
		} catch (Exception e) {
			logger.logWithSignature(this, "loadCurrentScoreLabel:%1$s",
					e.getMessage());
		}
	}

	@Override
	public void load() {
		this.loadScoreMaskImage();
		this.loadDottedLineImage();
		this.loadScoreWheelImage();
		this.loadLevelOneTitleImage();
		this.loadLevelInformationImage();
		this.loadMenuImage();
		this.loadReplayImage();
		this.loadNextImage();
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
