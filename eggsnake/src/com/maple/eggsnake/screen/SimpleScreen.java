package com.maple.eggsnake.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public abstract class SimpleScreen implements Screen {

	int getWidth() {
		return Gdx.graphics.getWidth();
	}

	int getHeight() {
		return Gdx.graphics.getHeight();
	}

	public SimpleScreen() {
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
