package com.maple.eggsnake.stage;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.maple.eggsnake.actor.ui.AnimateImage;
import com.maple.eggsnake.actor.ui.AnimateImageEvent;
import com.maple.eggsnake.actor.ui.AnimateImageListener;
import com.maple.eggsnake.actor.ui.RotateDirection;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.screen.NavigateScreen;

public class BackgroundStage extends BaseStage {

	final Loggable logger;
	AnimateImage btnStart;
	NavigateScreen screen;

	public BackgroundStage(Screen _screen, float width,
			float height, boolean stretch) {
		super(width, height, stretch);
		logger = DefaultLogger.getDefaultLogger();
		createButtons();
	}

	private void createButtons() {
		btnStart = new AnimateImage("Start", new Texture(
				"data/images/bottle.png"), 1f, RotateDirection.ANTICLOCKWISE);
		btnStart.addActionListener(new AnimateImageListener() {
			@Override
			public void onTouchDragged(AnimateImageEvent e) {
				logger.log("Btn dragged");
			}
		});
		btnStart.x = 30;
		this.addActor(btnStart);
		logger.log("Background Stage called");
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
