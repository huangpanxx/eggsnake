package com.maple.eggsnake.sound;

import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private float volume;
	
	public SoundManager(){
		volume = 1f;
	}
	public void setVolume(float v){
		volume = v;
	}
	
	public float getVolume(){
		return volume;
	}
	
	public void play(Sound sound){
		sound.play(volume);
	}
	public void stop(Sound sound){
		sound.stop();
	}
}
