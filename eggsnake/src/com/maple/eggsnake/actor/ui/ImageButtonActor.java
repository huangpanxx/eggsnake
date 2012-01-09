package com.maple.eggsnake.actor.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.RotateTo;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.eggenum.EnumStage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ApplicationService;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.DestinationStage;

public class ImageButtonActor extends Image {

	private ContentScreen contentScreen; // 中间层屏幕
	private EnumStage destSatge; // 跳转的目标Stage枚举
	private BaseStage sourceStage;

	private final float scaleSize = 10f;// 缩放幅度
	private final float clockwiseAngles = 360f;// 顺时针旋转360度
	private final float duration = 2f;// 间隔

	public ImageButtonActor(ContentScreen screen, EnumStage destStage, String name,
			TextureRegion region) {
		super(name, region);
		this.contentScreen = screen;
		this.destSatge = destStage;
		this.touchable = true;
	}

	public ImageButtonActor(String name, TextureRegion region) {
		super(name, region);
	}

	public ImageButtonActor(ContentScreen screen, EnumStage destStage, String name,
			Texture texture) {
		super(name, texture);
		this.contentScreen = screen;
		this.destSatge = destStage;
		this.touchable = true;
	}

	public ImageButtonActor(ContentScreen screen, BaseStage source,
			EnumStage destStage, String name, Texture texture) {
		super(name, texture);
		this.contentScreen = screen;
		this.sourceStage = source;
		this.destSatge = destStage;
		this.touchable = true;
	}

	public ImageButtonActor(String name, Texture texture) {
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
		if (!(EnumStage.DISPOSESTAGE == this.destSatge))
			this.contentScreen.navigate(DestinationStage.getDestnationStage(
					contentScreen, destSatge));
		else
		{
			ApplicationService.getInstance().exitGame();
		}
	}
	
}
