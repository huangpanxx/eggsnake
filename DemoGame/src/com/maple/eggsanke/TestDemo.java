package com.maple.eggsanke;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class TestDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new JoglApplication(new DemoGame(), "Egg Snake", 480, 320, false);
	}	
}
