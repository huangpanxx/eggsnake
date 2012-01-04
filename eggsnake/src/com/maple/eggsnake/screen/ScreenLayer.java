/** 
 * @description	: one of the layer of a screen
 * @author		: 黄攀
 * @created		: 2012-1-4
 */

package com.maple.eggsnake.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;

public class ScreenLayer extends InputProxyScreen {

	protected Stage stage;
	protected Loggable logger;
	protected Camera camera;

	public ScreenLayer() {
		logger = DefaultLogger.getDefaultLogger();
		camera = new OrthographicCamera(getWidth(), getHeight());
	}

	public void Navigate(Stage _stage) {
		logger.log("%1$s:Navigate", this.getClass().getName());
		this.stage = _stage;
		this.stage.setCamera(camera);
		this.setProcessor(this.stage);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		if (stage != null)
			stage.dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float dt) {
		if (stage != null) {
			stage.act(dt);
			stage.draw();
		}
	}

	@Override
	public void resize(int width, int height) {
		if (camera == null)
			camera = new OrthographicCamera(width, height);
		else {
			camera.viewportHeight = height;
			camera.viewportWidth = width;
		}
		camera.position.set(width / 2, height / 2, 1);
		logger.log("StartScreen: resize:%1$d,%2$d", width, height);
	}

	@Override
	public void resume() {
	}

}