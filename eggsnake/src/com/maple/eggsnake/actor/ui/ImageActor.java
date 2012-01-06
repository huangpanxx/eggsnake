package com.maple.eggsnake.actor.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.stage.BaseStage;

public class ImageActor extends Image {

	private ContentScreen contentScreen; // 中间层屏幕
	private BaseStage destSatge; // 跳转的目标Stage

	public ImageActor(ContentScreen screen, BaseStage destStage, String name,
			TextureRegion region) {
		super(name, region);
		this.contentScreen = screen;
		this.destSatge = destStage;
		this.touchable = true;
	}

	public ImageActor(ContentScreen screen, BaseStage destStage,
			String name, Texture texture) {
		super(name, texture);
		this.contentScreen = screen;
		this.destSatge = destStage;
		this.touchable = true;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		this.width += 10f;
		this.height += 10f;
		this.y -= 5f;
 		return touchable;
	}

	public void touchUp(float x, float y, int pointer) {
		this.width -= 5f;
		this.height -= 5f;
		this.y -= 5f;
		this.contentScreen.navigate(this.destSatge);
	}
}
