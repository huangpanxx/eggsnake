package com.maple.eggsnake.stage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.ui.AnimateImage;
import com.maple.eggsnake.ui.RotateDirection;

public class StartScreenStage extends Stage {
	AnimateImage btnStart;
	final Loggable logger;

	public StartScreenStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		logger = DefaultLogger.getDefaultLogger();
		createButtons();
	}

	private void createButtons() {
		btnStart = new AnimateImage("Start", new Texture(
				"data/images/bottle.png"), 1f, RotateDirection.ANTICLOCKWISE);
		this.addActor(btnStart);
	}
}
