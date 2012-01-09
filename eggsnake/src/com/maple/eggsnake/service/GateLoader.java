package com.maple.eggsnake.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.google.gson.Gson;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.util.PathHelper;

public class GateLoader {

	String baseDir = null;
	MapDictionary mapInfo = null;
	Loggable logger;

	public GateLoader(String baseDir, String dictionaryFile) throws Exception {
		logger = DefaultLogger.getDefaultLogger();
		this.baseDir = baseDir;

		logger.logWithSignature(this, "加载关卡文件 ：%1$s",
				PathHelper.combine(baseDir, dictionaryFile));
		String absPath = PathHelper.combine(this.baseDir, dictionaryFile);
		String text = Gdx.files.internal(absPath).readString();
		logger.logWithSignature(this, "关卡Json:", text);
		Gson gson = new Gson();
		mapInfo = gson.fromJson(text, MapDictionary.class);
	}

	public World loadGate(int gateIndex) {
		logger.logWithSignature(this, "加载地图号:%1$s", gateIndex);
		String path = mapInfo.Maps.get(gateIndex);
		logger.logWithSignature(this, "加载地图%1$s", path);
		return ResourceLoader.loadWorld(path);
	}
}
