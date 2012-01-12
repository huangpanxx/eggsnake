package com.maple.eggsnake.actor.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maple.eggsnake.service.SoundManager;

public class SoundImage extends FlatImage {

	private final int MAX = 100;// 设为该值时，音效开启
	private final int MIN = 0;// 设为该值时，音效关闭
	

	public SoundImage(TextureRegion region, float x, float y, Stage stage) {
		super(region, x, y, stage);
	}

	public SoundImage(Texture texture, float x, float y, Stage stage) {
		super(texture, x, y, stage);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer){
		if(SettingState.soundOpened){
			SoundManager.setVolume(MIN);
			SettingState.soundOpened = false;
		}
		else{
			SoundManager.setVolume(MAX);
			SettingState.soundOpened = true;
		}
		return touchable;
	}
}
