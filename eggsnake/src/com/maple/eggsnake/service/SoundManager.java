package com.maple.eggsnake.service;

import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private static Sound flySound;
	private static Sound contactSound;

	public static void playFlySound() {
		if (flySound == null) {
			flySound = ResourceLoader.loadSound("fly_sound.ogg");
		}
		flySound.play();
	}

	public static void playContactSound() {
		if (contactSound == null) {
			contactSound = ResourceLoader.loadSound("contact_sound.ogg");
		}
		contactSound.play();
	}
}
