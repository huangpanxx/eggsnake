package com.maple.eggsnake.actor.lightlabel;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.Action;

public class ScoreLightLabel extends LightLabel{

	private int scores;//需要显示的玩家加当前关的得到的分数
	
	public ScoreLightLabel(String name, BitmapFont font, String text,
			Action action, int scores) {
		super(name, font, text, action);
		this.setDisplayInfomation(scores);
		this.loadScores(scores);
	}

	public ScoreLightLabel(String name, BitmapFont font, 
			String text, int scores) {
		super(name, font, text);
		this.setDisplayInfomation(scores);
		this.loadScores(scores);
	}

	public ScoreLightLabel(String name, BitmapFont font, 
			Action action, int scores) {
		super(name, font, action);
		this.setDisplayInfomation(scores);
		this.loadScores(scores);
	}

	public ScoreLightLabel(String name, BitmapFont font, int scores) {
		super(name, font);
		this.setDisplayInfomation(scores);
		this.loadScores(scores);
	}
	
	private void setDisplayInfomation(int scores){
		this.setWrappedText("" + scores, 
				HAlignment.LEFT);
	}
	
	private void loadScores(int scores){
		this.scores = scores;
	}
}
