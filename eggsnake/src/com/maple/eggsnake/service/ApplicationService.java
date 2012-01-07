package com.maple.eggsnake.service;

import com.badlogic.gdx.Gdx;
import com.maple.eggsnake.logger.DefaultLogger;

public class ApplicationService {
	public static void exitGame() {
		DefaultLogger.getDefaultLogger().log("ApplicationService:exit");
		Gdx.app.exit();
	}
}
