package com.maple.eggsnake.sound;

import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	float volume;
	
	public void setVolume(float v){
		volume = v;
	}
	
	public float getVolume(){
		return volume;
	}
	
	public void play(Sound sound){
		sound.play(volume);
	}
	
}
