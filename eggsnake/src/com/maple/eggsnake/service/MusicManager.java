package com.maple.eggsnake.service;

import com.badlogic.gdx.audio.Music;

public class MusicManager {

	static Music backgroundMusic;
	private static float volume = 100;

	public static void setBackgroundMusic(String name) {
		backgroundMusic = ResourceLoader.loadMusic(name);
	}

	public static void play(boolean loop) {
		backgroundMusic.play();
		backgroundMusic.setLooping(loop);
	}

	public static void stop() {
		backgroundMusic.stop();
	}

	public static void setVolume(float volume) {
		MusicManager.backgroundMusic.setVolume(volume);
		MusicManager.volume = volume;
	}

	public static float getVolume() {
		return MusicManager.volume;
	}

}
