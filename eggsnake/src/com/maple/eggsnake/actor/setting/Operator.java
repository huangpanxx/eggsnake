package com.maple.eggsnake.actor.setting;

import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.maple.eggsnake.service.MusicManager;

public class Operator implements Slider.ValueChangedListener{

	@Override
	public void changed(Slider arg0, float volume) {
		// TODO Auto-generated method stub
		MusicManager.setVolume(volume);
	}

}
