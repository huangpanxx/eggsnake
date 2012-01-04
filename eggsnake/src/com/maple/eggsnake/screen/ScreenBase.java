/** 
 * @description	: the base screen to implement some common functions
 * @author		: 黄攀
 * @created		: 2012-1-4
 */

package com.maple.eggsnake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;

public abstract class ScreenBase implements Screen {

	private float width = 0;
	private float height = 0;
	protected Stage stage;
	protected Loggable logger;
	Camera camera;

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public ScreenBase() {
		logger = DefaultLogger.getDefaultLogger();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}

	public void Navigate(Stage _stage) {
		logger.log("%1$s:Navigate", this.getClass().getName());
		this.stage = _stage;
		Gdx.input.setInputProcessor(stage);
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
		stage.dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float dt) {
		if (stage != null)
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
	}

}
