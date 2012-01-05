/** 
 * @description	: the base screen to implements some common operations
 * @author		: 黄攀
 * @created		: 2012-1-4
 */

package com.maple.eggsnake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.stage.TestStage;

public class NavigateScreen extends SimpleScreen implements ProcessableScreen, Layerable {

	private int layer;

	public NavigateScreen() {
		logger = DefaultLogger.getDefaultLogger();
		camera = new OrthographicCamera(getWidth(), getHeight());
		this.Navigate(new TestStage(this, getWidth(), getHeight(), false));

	}

	public int getWidth() {
		return Gdx.graphics.getWidth();
	}

	public int getHeight() {
		return Gdx.graphics.getHeight();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	protected Stage stage;
	protected Loggable logger;
	protected Camera camera;

	public void Navigate(Stage _stage) {
		logger.log("%1$s:Navigate", this.getClass().getName());
		this.layer = 1;
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

	private InputProcessor processor;

	@Override
	public boolean keyDown(int keyCode) {
		if (getProcessor() != null)
			return getProcessor().keyDown(keyCode);
		else
			return false;
	}

	@Override
	public boolean keyTyped(char keyCode) {
		if (getProcessor() != null)
			return getProcessor().keyTyped(keyCode);
		else
			return false;
	}

	@Override
	public boolean keyUp(int keyCode) {
		if (getProcessor() != null)
			return getProcessor().keyUp(keyCode);
		else
			return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (getProcessor() != null)
			return getProcessor().scrolled(amount);
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (getProcessor() != null)
			return getProcessor().touchDown(x, y, pointer, button);
		else
			return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		if (getProcessor() != null)
			return getProcessor().touchDragged(arg0, arg1, arg2);
		else
			return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {

		if (getProcessor() != null)
			return getProcessor().touchMoved(x, y);
		else
			return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {

		if (getProcessor() != null)
			return getProcessor().touchUp(x, y, pointer, button);
		return false;
	}

	public InputProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(InputProcessor processor) {
		this.processor = processor;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}
}
