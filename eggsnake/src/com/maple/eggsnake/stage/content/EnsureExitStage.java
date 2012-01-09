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

	/* (non-Javadoc)
	 * @see com.maple.eggsnake.stage.content.ActorLoader#initContent(com.maple.eggsnake.screen.ContentScreen)
	 */
	@Override
	public void initContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	/* (non-Javadoc)
	 * @see com.maple.eggsnake.stage.content.ActorLoader#loadTextures()
	 */
	@Override
	public void loadTextures() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.maple.eggsnake.stage.content.ActorLoader#load()
	 */
	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.maple.eggsnake.stage.BaseStage#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.maple.eggsnake.stage.BaseStage#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.maple.eggsnake.stage.BaseStage#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.maple.eggsnake.stage.BaseStage#resume()
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.maple.eggsnake.stage.BaseStage#show()
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
