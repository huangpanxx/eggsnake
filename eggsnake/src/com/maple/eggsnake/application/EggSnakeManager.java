/* 
 * Description	: Game Manager, Singleton mode
 * Author		: 黄攀
 * Created		: 2012-1-2
 */

package com.maple.eggsnake.application;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.maple.eggsnake.localization.Language;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.screen.StartScreen;

public class EggSnakeManager implements ApplicationListener, ScreenManageable {

	// The only instance
	private static EggSnakeManager instance = null;

	private Screen screen = null;

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
		logger.log(-1, Language.translate("eggsnake"));
	}

	@Override
	public void create() {
		this.navigate(new StartScreen(this));
	}

	@Override
	public void dispose() {
		if (screen != null)
			this.screen.dispose();
	}

	@Override
	public void pause() {

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
	public void navigate(Screen screen) {
		this.screen = screen;
		if (screen != null)
			screen.show();
	}
}
