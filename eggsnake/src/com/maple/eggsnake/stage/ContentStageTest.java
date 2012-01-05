/** 
 * @description	: This contains the start menu logical and ui
 * @author		: 黄攀
 * @created		: 2012-1-4
 */

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

public class ContentStageTest extends BaseStage {
	final Loggable logger;
	AnimateImage btnStart;
	NavigateScreen screen;

	public ContentStageTest(Screen _screen, float width, float height,
			boolean stretch) {
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
		logger.log("Content Stage called");
		this.addActor(btnStart);
		
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
}
