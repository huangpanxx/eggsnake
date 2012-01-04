/* 
 * Description	: the start screen
 * Author		: 黄攀
 * Created		: 2012-1-3
 */

package com.maple.eggsnake.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.maple.eggsnake.application.ScreenManageable;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.stage.StartScreenStage;

public class StartScreen extends SimpleScreen {

	StartScreenStage stage;
	Camera camera;
	Loggable logger;

	public StartScreen(ScreenManageable manager) {
		logger = DefaultLogger.getDefaultLogger();
		stage = new StartScreenStage(this.getWidth(), this.getHeight(), false);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float dt) {
		stage.act(dt);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		if (camera == null)
			camera = new OrthographicCamera(width, height);
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.position.set(width / 2, height / 2, 1);
		stage.setCamera(camera);
		logger.log("StartScreen: resize:%1$d,%2$d", width, height);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}
}
