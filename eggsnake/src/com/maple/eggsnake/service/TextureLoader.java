package com.maple.eggsnake.service;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;

class TextureLoader {
	private String dirPath;
	Map<String, Texture> map;
	Loggable logger;
	Texture defaultTexture;

	public TextureLoader(String dirPath, String defaultTexture) {
		int ch = dirPath.lastIndexOf(0);
		if (ch != '/' && ch != '\\') {
			this.dirPath = dirPath + '/';
		} else {
			this.dirPath = dirPath;
		}
		this.map = new HashMap<String, Texture>();
		logger = DefaultLogger.getDefaultLogger();
		this.defaultTexture = new Texture(this.dirPath + defaultTexture);
	}

	public Texture loadTexture(String texturePath) {
		Texture texture = null;
		try {
			if (map.containsKey(texturePath)) {
				logger.logWithSignature(this, "命中资源%1$s", texturePath);
				texture = map.get(texturePath);
			} else {
				String absPath = this.dirPath + texturePath;
				texture = new Texture(absPath);
				logger.logWithSignature(this, "加载资源%1$s", texturePath);
				map.put(texturePath, texture);
			}
		} catch (Exception ex) {
			logger.log("TextureManager loadTexture:加载:%1$s失败", texturePath);
			texture = this.defaultTexture;
		}
		return texture;

	}

}
