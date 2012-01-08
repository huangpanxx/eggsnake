package com.maple.eggsnake.service;

import com.badlogic.gdx.physics.box2d.World;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.physics.B2WorldFactory;
import com.maple.eggsnake.util.PathHelper;

public class WorldLoader {

	String basePath = null;

	public WorldLoader(String dirPath) {
		this.basePath = dirPath;
	}

	public World loadWorld(String mapPath) {
		try {
			String absPath = PathHelper.combine(basePath, mapPath);
			return B2WorldFactory.loadWorld(absPath);
		} catch (Exception e) {
			DefaultLogger.getDefaultLogger().logWithSignature(this,
					"加载地图%1$s失败:%2$s", mapPath, e.getMessage());
			return null;

		}
	}
}
