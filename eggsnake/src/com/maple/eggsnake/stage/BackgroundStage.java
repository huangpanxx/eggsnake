package com.maple.eggsnake.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.screen.NavigateScreen;

public class BackgroundStage extends BaseStage {

	final Loggable logger;
	NavigateScreen screen;

	public BackgroundStage(NavigateScreen _screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		logger = DefaultLogger.getDefaultLogger();
		loadBackground();
	}

	private void loadBackground() {
		FileHandle fileHandle = Gdx.files.internal("data/images/background.png");
		ActorRegister.singleRegister(this, new Texture(fileHandle), 0, 0);
	}

	public void hide() {
		// TODO Auto-generated method stub

	}

	public void pause() {
		// TODO Auto-generated method stub

	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void resume() {
		// TODO Auto-generated method stub
	}

	public void show() {
		// TODO Auto-generated method stub

	}

}
