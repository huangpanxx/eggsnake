/** 
 * @description	: EnsureExitStage确认是否退出
 * @author		: 王志伟
 * @created		: 2012-1-9
 */
package com.maple.eggsnake.stage.content;

import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.stage.BaseStage;

/**
 * @author Administrator
 *
 */
public class EnsureExitStage extends BaseStage implements ActorLoader {

	@SuppressWarnings("unused")
	private ContentScreen contentScreen;
	
	/**
	 * @param width
	 * @param height
	 * @param stretch
	 */
	public EnsureExitStage(ContentScreen screen, float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.initContent(screen);
	}

	@Override
	public void initContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

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
