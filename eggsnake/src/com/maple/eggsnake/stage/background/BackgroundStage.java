package com.maple.eggsnake.stage.background;

import com.badlogic.gdx.graphics.Texture;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.screen.NavigateScreen;
import com.maple.eggsnake.service.MusicManager;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class BackgroundStage extends BaseStage {

	final Loggable logger;
	NavigateScreen screen;

	public BackgroundStage(NavigateScreen _screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		logger = DefaultLogger.getDefaultLogger();
		loadBackground();
		MusicManager.setBackgroundMusic("background.ogg");//aganzo_boss.ogg")
		MusicManager.play(true);
	}

	private void loadBackground() {
		Texture texture = ResourceLoader.loadTexture("background_512_512.png");
		ActorRegister.singleRegister(this, texture, 0, 0);
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

	}

}
