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
import com.maple.eggsnake.service.MusicManager;

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
		logger.logWithSignature(this, "语言测试:%1$s",
				Language.translate("eggsnake"));
	}

	@Override
	public void create() {
		logger.logWithSignature(this, "create");
		MusicManager.setBackgroundMusic("background.ogg");
		MusicManager.play(true);
		this.navigate(new LayeredScreen(this));
	}

	@Override
	public void dispose() {
		logger.logWithSignature(this, "dispose");
		if (screen != null)
			this.screen.dispose();
	}

	@Override
	public void pause() {
		logger.logWithSignature(this, "pause");

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
		logger.logWithSignature(this, "resize(%1$d,%2$d)",
				width, height);
		if (screen != null)
			this.screen.resize(width, height);
	}

	@Override
	public void resume() {
		logger.logWithSignature(this, "resume");
		if (screen != null)
			this.screen.resume();
	}

	@Override
	public void navigate(ProcessableScreen screen) {
		logger.logWithSignature(this, "navigate(%1$s)", screen
				.getClass().getSimpleName());
		this.screen = screen;
		Gdx.input.setInputProcessor(screen);
		if (screen != null)
			screen.show();
	}
}
