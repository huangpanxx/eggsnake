package com.maple.eggsnake.actor.setting;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class MusicSlider extends Slider{

	public MusicSlider(float min, float max, float steps, SliderStyle style,
			String name) {
		super(min, max, steps, style, name);
	}

	public MusicSlider(float min, float max, float steps, SliderStyle style) {
		super(min, max, steps, style);
		// TODO Auto-generated constructor stub
	}

	public MusicSlider(float min, float max, float steps, Skin skin) {
		super(min, max, steps, skin);
		// TODO Auto-generated constructor stub
	}
}
