package com.maple.eggsnake.service;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;

public class MusicLoader {

	private String dirPath;
	Map<String, Music> map;
	Loggable logger;
	Music defaultMusic;

	public MusicLoader(String dirPath, String defaultMusic) {
		int ch = dirPath.lastIndexOf(0);
		if (ch != '/' && ch != '\\') {
			this.dirPath = dirPath + '/';
		} else {
			this.dirPath = dirPath;
		}
		this.map = new HashMap<String, Music>();
		logger = DefaultLogger.getDefaultLogger();
		this.defaultMusic = Gdx.audio.newMusic(Gdx.files.internal(dirPath
				+ defaultMusic));
	}

	public Music loadMusic(String musicPath) {
		Music music = null;
		try {
			if (map.containsKey(musicPath)) {
				music = map.get(musicPath);
			} else {
				String absPath = this.dirPath + musicPath;
				FileHandle file = Gdx.files.internal(absPath);
				music = Gdx.audio.newMusic(file);
				map.put(musicPath, music);
			}
		} catch (Exception ex) {
			logger.log("MusicManager loadMusic:加载:%1$s失败", musicPath);
			music = this.defaultMusic;
		}
		return music;

	}

}
