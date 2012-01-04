/** 
 * @description	: the base screen to implement some common functions
 * @author		: 黄攀
 * @created		: 2012-1-4
 */

package com.maple.eggsnake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public abstract class SimpleScreen implements Screen {

	private float width = 0;
	private float height = 0;

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public SimpleScreen() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

}
