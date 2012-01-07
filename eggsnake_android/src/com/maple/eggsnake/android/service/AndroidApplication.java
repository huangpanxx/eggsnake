package com.maple.eggsnake.android.service;

import android.app.Activity;

import com.badlogic.gdx.Gdx;
import com.maple.eggsnake.service.Application;

public class AndroidApplication implements Application{
	
	private Activity activity = null;
	public AndroidApplication(Activity activity){
		this.activity = activity;
	}

	public void exit() {
		Gdx.app.exit();
		this.activity.finish();
	}

}
