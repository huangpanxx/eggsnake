package com.maple.eggsnake.actor.ui;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.RotateTo;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.DestinationStage;
import com.maple.eggsnake.stage.content.EnumStage;

public class ImageActor extends Image {

	private ContentScreen contentScreen; // 中间层屏幕
	private EnumStage destSatge; // 跳转的目标Stage枚举

	public ImageActor(ContentScreen screen, EnumStage destStage, String name,
			TextureRegion region) {
		super(name, region);
		this.contentScreen = screen;
		this.destSatge = destStage;
		this.touchable = true;
	}
	
	public ImageActor(String name,TextureRegion region){
		super(name, region);
	}

	public ImageActor(ContentScreen screen, EnumStage destStage,
			String name, Texture texture) {
		super(name, texture);
		this.contentScreen = screen;
		this.destSatge = destStage;
		this.touchable = true;
	}
	
	public ImageActor(String name,Texture texture){
		super(name, texture);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		this.action(RotateTo.$(360, 2f));
		this.width += 10f;
		this.height += 10f;
 		return touchable;
	}

	@Override
	public void touchUp(float x, float y, int pointer) {
		this.width -= 10f;
		this.height -= 10f;
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer){
		@SuppressWarnings("unused")
		BaseStage temp = DestinationStage.getDestnationStage(contentScreen,
				destSatge);
		this.contentScreen.navigate(DestinationStage.getDestnationStage(contentScreen,
				destSatge));
	}
}
