package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.Input;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.stage.BaseStage;

public class LevelTwoStage extends BaseStage implements ActorLoader {

	private ContentScreen contentScreen;
	
	public LevelTwoStage(ContentScreen screen, float width, float height, 
			boolean stretch) {
		super(width, height, stretch);
		this.initContent(screen);
		CurrentLevel.getInstance().setLevel(2);
	}

	@Override
	public void initContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

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
	
	@Override
	public boolean keyDown(int keycode){
		if(keycode == Input.Keys.W){
			this.contentScreen.navigate(new SelectLevelStage(contentScreen,
					contentScreen.getWidth(), contentScreen.getHeight(), true));
		}
		return true;
	}

}
