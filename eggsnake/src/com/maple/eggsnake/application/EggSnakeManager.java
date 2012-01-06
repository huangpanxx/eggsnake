/** 
 * @description	: Game Manager, Singleton mode
 * @author		: 黄攀
 * @created		: 2012-1-2
 */

package com.maple.eggsnake.application;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.maple.eggsnake.localization.Language;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.screen.LayeredScreen;
import com.maple.eggsnake.screen.ProcessableScreen;

public class EggSnakeManager implements ApplicationListener, ScreenManageable {

	// The only instance
	private static EggSnakeManager instance = null;

	private ProcessableScreen screen = null;

	public static EggSnakeManager getInstance() {
		if (instance == null)
			instance = new EggSnakeManager();
		return instance;
	}

	Loggable logger;

	// Private constructor
	private EggSnakeManager() {
		// Here for local test!
		logger = DefaultLogger.getDefaultLogger();
		logger.log(1, "语言测试:" + Language.translate("eggsnake"));
	}

	@Override
	public void create() {
		logger.log("EggSnakeManager:create");
		this.navigate(new LayeredScreen(this));
	}

	@Override
	public void dispose() {
		logger.log("EggSnakeManager:dispose");
		if (screen != null)
			this.screen.dispose();
	}

	@Override
	public void pause() {
		logger.log("EggSnakeManager: pause");

		if (screen != null)
			this.screen.pause();
	}

	@Override
	public void render() {

		if (screen != null) {
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			float dt = Gdx.graphics.getDeltaTime();
			this.screen.render(dt);
		}
	}

	@Override
	public void resize(int width, int height) {

		if (screen != null)
			this.screen.resize(width, height);
	}

	@Override
	public void resume() {

		if (screen != null)
			this.screen.resume();
	}

	@Override
	public void navigate(ProcessableScreen screen) {
		this.screen = screen;
		Gdx.input.setInputProcessor(screen);
		if (screen != null)
			screen.show();
	}
}
