package com.maple.eggsnake.actor.setting;

import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class MusicSlider extends Slider {

	/**
	 * 
	 * @param min
	 * @param max
	 * @param steps
	 * @param style
	 * @param name
	 */
	public MusicSlider(float min, float max, float steps, SliderStyle style,
			String name) {
		super(min, max, steps, style, name);
	}

	/**
	 * 
	 * @param min
	 * @param max
	 * @param steps
	 * @param style
	 */
	public MusicSlider(float min, float max, float steps, SliderStyle style) {
		super(min, max, steps, style);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param min
	 * @param max
	 * @param steps
	 * @param skin
	 */
	public MusicSlider(float min, float max, float steps, Skin skin) {
		super(min, max, steps, skin);
		// TODO Auto-generated constructor stub
	}
	
	/*@Override
	public void touchDragged(float x, float y, int pointer){
		this.action(MoveTo.$(x, y, 2f));
	}*/
}
