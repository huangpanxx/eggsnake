/** 
 * @description	: one of the layer of a screen
 * @author		: 黄攀
 * @created		: 2012-1-4
 */

package com.maple.eggsnake.screen;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import com.badlogic.gdx.InputMultiplexer;
import com.maple.eggsnake.application.ScreenManageable;
import com.maple.eggsnake.stage.TestStage;

public class LayeredScreen extends SimpleScreen implements ProcessableScreen {

	ScreenManageable manager;
	PriorityQueue<NavigateScreen> screens;
	InputMultiplexer processPlexer;
	
	public LayeredScreen(ScreenManageable _manager) {
		this.manager = _manager;
		this.processPlexer = new InputMultiplexer();
		// 使用字典模拟附加属性,进行排序
		screens = new PriorityQueue<NavigateScreen>(3,
				new Comparator<NavigateScreen>() {
					@Override
					public int compare(NavigateScreen o1, NavigateScreen o2) {
						return o1.getLayer() - o2.getLayer();
					}
				});
		NavigateScreen screen = new NavigateScreen();
		screen.Navigate(new TestStage(screen, getWidth(), getHeight(),
				false));
		this.addScreen(screen);
	}

	public void addScreen(NavigateScreen screen) {
		this.screens.add(screen);
		this.processPlexer.addProcessor(screen);
	}

	public void removeScreen(NavigateScreen screen) {
		this.screens.remove(screen);
		this.processPlexer.removeProcessor(screen);
	}

	@Override
	public boolean keyDown(int arg0) {
		return processPlexer.keyDown(arg0);
	}

	@Override
	public boolean keyTyped(char arg0) {
		return processPlexer.keyTyped(arg0);
	}

	@Override
	public boolean keyUp(int arg0) {
		return processPlexer.keyUp(arg0);
	}

	@Override
	public boolean scrolled(int arg0) {
		return processPlexer.scrolled(arg0);
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		return processPlexer.touchDown(arg0, arg1, arg2, arg3);
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return processPlexer.touchDragged(arg0,arg1,arg2);
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		return processPlexer.touchMoved(arg0, arg1);
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return processPlexer.touchUp(arg0, arg1, arg2, arg3);
	}

	@Override
	public void dispose() {
		Iterator<NavigateScreen> it = screens.iterator();
		while (it.hasNext()) {
			NavigateScreen screen = it.next();
			screen.dispose();
		}
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Iterator<NavigateScreen> it = screens.iterator();
		while (it.hasNext()) {
			NavigateScreen screen = it.next();
			screen.hide();
		}
	}

	@Override
	public void pause() {
		Iterator<NavigateScreen> it = screens.iterator();
		while (it.hasNext()) {
			NavigateScreen screen = it.next();
			screen.pause();
		}
	}

	@Override
	public void render(float dt) {
		Iterator<NavigateScreen> it = screens.iterator();
		while (it.hasNext()) {
			NavigateScreen screen = it.next();
			screen.render(dt);
		}
	}

	@Override
	public void resize(int width, int height) {
		Iterator<NavigateScreen> it = screens.iterator();
		while (it.hasNext()) {
			NavigateScreen screen = it.next();
			screen.resize(width, height);
		}
	}

	@Override
	public void resume() {
		Iterator<NavigateScreen> it = screens.iterator();
		while (it.hasNext()) {
			NavigateScreen screen = it.next();
			screen.resume();
		}
	}

	@Override
	public void show() {
		Iterator<NavigateScreen> it = screens.iterator();
		while (it.hasNext()) {
			NavigateScreen screen = it.next();
			screen.show();
		}
	}

}