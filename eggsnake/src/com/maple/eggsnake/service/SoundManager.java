package com.maple.eggsnake.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private static Sound flySound = null;
	private static Sound contactSound = null;
	private static Sound crossGateSound = null;
	private static Sound killSound = null;
	private static Sound dragSound = null;

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
		//contactSound.play();
	}

	public static void playCrossGateSound() {
		if (crossGateSound == null)
			crossGateSound = ResourceLoader.loadSound("cross_gate_sound.ogg");
		crossGateSound.play();
	}

	public static void playKillSound() {
		if (killSound == null)
			killSound = ResourceLoader.loadSound("kill_sound.ogg");
		killSound.play();
	}

	public static void playDragSound() {
		if (dragSound == null) {
			dragSound = ResourceLoader.loadSound("drag_sound.ogg");
		}
		dragSound.play();
	}
}
