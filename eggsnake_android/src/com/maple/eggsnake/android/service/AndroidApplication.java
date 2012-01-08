package com.maple.eggsnake.android.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;

import com.badlogic.gdx.Gdx;
import com.maple.eggsnake.service.Application;

public class AndroidApplication implements Application {

	private Activity activity = null;

	public AndroidApplication(Activity activity) {
		this.activity = activity;
	}

	public void exit() {
		Gdx.app.exit();
		this.activity.finish();
	}

	public FileOutputStream openFileOutput(String path, boolean append)
			throws Exception {
		if (append) {
			return this.activity.openFileOutput(path, Activity.MODE_APPEND);
		} else {
			return this.activity.openFileOutput(path,
					Activity.MODE_WORLD_WRITEABLE);
		}
	}

	public FileInputStream openFileInput(String path) throws Exception {
		return this.activity.openFileInput(path);
	}
}
