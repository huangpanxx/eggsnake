package com.maple.eggsnake.actor.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maple.eggsnake.service.MusicManager;

public class MusicImage extends FlatImage {
	
	public MusicImage(TextureRegion region, float x, float y, Stage stage) {
		super(region, x, y, stage);
	}

	public MusicImage(Texture texture, float x, float y, Stage stage) {
		super(texture, x, y, stage);
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer) {
		if (SettingState.musicOpened) {
			MusicManager.stop();
			SettingState.musicOpened = false;
		} else {
			MusicManager.play(true);
			SettingState.musicOpened = true;
		}
		return touchable;
	}
	
}
