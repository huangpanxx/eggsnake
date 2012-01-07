package com.maple.eggsnake.service;

import com.badlogic.gdx.Gdx;

public class DesktopApplication implements Application{

	@Override
	public void exit() {
		Gdx.app.exit();
	}

}
