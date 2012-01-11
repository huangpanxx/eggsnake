package com.maple.eggsnake.actor.wheel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.RotateTo;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ApplicationService;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.DestinationStage;
import com.maple.eggsnake.stage.content.common.EnumDestStage;

public class NavigatorImage extends Image {

	private ContentScreen contentScreen; // 中间层屏幕
	
	private EnumDestStage destStage; // 跳转的目标Stage枚举
	
	@SuppressWarnings("unused")
	private BaseStage sourceStage;

	private final float scaleSize = 10f;// 缩放幅度
	private final float clockwiseAngles = 360f;// 顺时针旋转360度
	private final float duration = 2f;// 间隔

	public NavigatorImage(ContentScreen screen, EnumDestStage destStage, String name,
			TextureRegion region) {
		super(name, region);
		this.contentScreen = screen;
		this.destStage = destStage;
		this.touchable = true;
	}
	

	public NavigatorImage(String name, TextureRegion region) {
		super(name, region);
	}

	public NavigatorImage(ContentScreen screen, EnumDestStage destStage, String name,
			Texture texture) {
		super(name, texture);
		this.contentScreen = screen;
		this.destStage = destStage;
		this.touchable = true;
	}
	

	public NavigatorImage(ContentScreen screen, BaseStage source,
			EnumDestStage destStage, String name, Texture texture) {
		super(name, texture);
		this.contentScreen = screen;
		this.sourceStage = source;
		this.destStage = destStage;
		this.touchable = true;
	}
	

	public NavigatorImage(String name, Texture texture) {
		super(name, texture);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		this.action(RotateTo.$(this.clockwiseAngles, this.duration));
		this.width += scaleSize;
		this.height += scaleSize;
		return touchable;
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		this.width -= scaleSize;
		this.height -= scaleSize;
	}

	@Override
	public void touchDragged(float x, float y, int pointer) {
		this.navigate(destStage);
	}
	
	private void navigate(EnumDestStage destStage){
		if (!(EnumDestStage.DISPOSESTAGE == this.destStage))
			this.contentScreen.navigate(DestinationStage.getDestnationStage(
					contentScreen, destStage));
		else
			ApplicationService.getInstance().exitGame();
	}	
}
