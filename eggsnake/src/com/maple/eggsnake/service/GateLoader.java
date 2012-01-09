package com.maple.eggsnake.service;

import com.badlogic.gdx.physics.box2d.World;
import com.google.gson.Gson;
import com.maple.eggsnake.util.FileHelper;
import com.maple.eggsnake.util.PathHelper;

public class GateLoader {
	
	String baseDir = null;
	MapDictionary mapInfo = null;
	
	public GateLoader(String baseDir,String dictionaryFile) throws Exception{
		this.baseDir = baseDir;
		String text = FileHelper.readAll(PathHelper.combine(baseDir, dictionaryFile));
		Gson gson = new Gson();
		mapInfo = gson.fromJson(text, MapDictionary.class);
	}
	
	public World loadGate(int gateIndex){
		String path = mapInfo.Maps.get(gateIndex);
//		String absPath = PathHelper.combine(baseDir, path);
		return ResourceLoader.loadWorld(path);
	}
}
