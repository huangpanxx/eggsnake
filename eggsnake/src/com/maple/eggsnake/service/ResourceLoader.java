package com.maple.eggsnake.service;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class ResourceLoader {

	public final String TEXTURE_DIR = "data/images";
	public final String DEFAULT_TEXTURE_NAME = "default.png";

	public final String SOUND_DIR = "data/sounds";
	public final String DEFAULT_SOUND_NAME = "default.ogg";

	public final String MUSIC_DIR = "data/musics";
	public final String DEFAULT_MUSIC_NAME = "default.ogg";

	private static TextureLoader textureLoader = null;
	private static SoundLoader soundLoader = null;
	private static MusicLoader musicLoader = null;

	public Texture loadTexture(String relativePath) {
		if (ResourceLoader.textureLoader == null) {
			ResourceLoader.textureLoader = new TextureLoader(TEXTURE_DIR,
					DEFAULT_TEXTURE_NAME);
		}
		return ResourceLoader.textureLoader.loadTexture(relativePath);
	}

	public Sound loadSound(String relativePath) {
		if (ResourceLoader.soundLoader == null) {
			ResourceLoader.soundLoader = new SoundLoader(SOUND_DIR,
					DEFAULT_SOUND_NAME);
		}
		return ResourceLoader.soundLoader.loadSound(relativePath);
	}

	public Music loadMusic(String relativePath) {
		if (ResourceLoader.musicLoader == null) {
			ResourceLoader.musicLoader = new MusicLoader(SOUND_DIR,
					DEFAULT_SOUND_NAME);
		}
		return ResourceLoader.musicLoader.loadMusic(relativePath);
	}
}
