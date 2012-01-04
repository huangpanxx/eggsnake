package com.maple.eggsnake.sound;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

public class SoundLoad {
	public static Sound test;

	public static void load() {
		test = loadSound("data/sounds/test.ogg");
	}

	// load one music
	public static Sound loadSound(String name) {
		return Gdx.audio.newSound(Gdx.files.internal(name));
	}

	// load musics for a directory
	public static Sound[] loadSounds(String dir) {
		FileHandle fh = Gdx.files.internal("data/sounds" + dir);
		FileHandle[] fhs = fh.list();
		ArrayList<Sound> sounds = new ArrayList<Sound>();
		for (int i = 0; i < fhs.length; i++) {
			String name = fhs[i].name();
			if (name.endsWith(".ogg")) {
				sounds.add(loadSound(dir + "/" + name));
			}
		}
		Sound[] result = new Sound[0];
		return sounds.toArray(result);
	}
}
