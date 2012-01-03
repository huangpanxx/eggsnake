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

	// Private constructor
	private EggSnakeManager() {
	}

	@Override
	public void create() {
		this.navigate(new StartScreen(this));
	}

	@Override
	public void dispose() {
		this.screen.dispose();
	}

	@Override
	public void pause() {
		this.screen.pause();
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		float dt = Gdx.graphics.getDeltaTime();
		this.screen.render(dt);
	}

	@Override
	public void resize(int width, int height) {
		this.screen.resize(width, height);
	}

	@Override
	public void resume() {
		this.screen.resume();
	}

	@Override
	public void navigate(Screen screen) {
		this.screen = screen;
		screen.show();
	}
}
