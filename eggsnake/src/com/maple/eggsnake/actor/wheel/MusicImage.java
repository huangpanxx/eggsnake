package com.maple.eggsnake.actor.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maple.eggsnake.service.MusicManager;

public class MusicImage extends FlatImage {
	
	private TextureRegion openedTextureRegion;
	private TextureRegion closedTextureRegion;
	
	public MusicImage(TextureRegion openedRegion, TextureRegion closedRegion, 
			float x, float y, Stage stage) {
		super(openedRegion, x, y, stage);
		this.openedTextureRegion = new TextureRegion(openedRegion);
		this.closedTextureRegion = new TextureRegion(closedRegion);
	}

	public MusicImage(Texture texture, float x, float y, Stage stage) {
		super(texture, x, y, stage);
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer) {
		if (SettingState.musicOpened) {
			MusicManager.stop();
			SettingState.musicOpened = false;
			this.region = closedTextureRegion;
		} else {
			MusicManager.play(true);
			SettingState.musicOpened = true;
			this.region = openedTextureRegion;
		}
		this.x += 10;
		this.y += 10;
		return touchable;
	}
	
	@Override
	public void touchUp(float x, float y, int pointer){
		this.x -= 10;
		this.y -= 10;
	}
	
}
