package com.maple.eggsnake.actor.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maple.eggsnake.service.SoundManager;

public class SoundImage extends FlatImage {

	private final int MAX = 100;// 设为该值时，音效开启
	private final int MIN = 0;// 设为该值时，音效关闭
	
	private TextureRegion openedTextureRegion;
	private TextureRegion closedTextureRegion;
	
	public SoundImage(TextureRegion openedRegion, TextureRegion closedRegion, 
			float x, float y, Stage stage) {
		super(openedRegion, x, y, stage);
		this.openedTextureRegion = new TextureRegion(openedRegion);
		this.closedTextureRegion = new TextureRegion(closedRegion);
	}

	public SoundImage(Texture texture, float x, float y, Stage stage) {
		super(texture, x, y, stage);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer){
		if(SettingState.soundOpened){
			SoundManager.setVolume(MIN);
			SettingState.soundOpened = false;
			this.region = closedTextureRegion;
		}
		else{
			SoundManager.setVolume(MAX);
			SettingState.soundOpened = true;
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
