/**
 * @author zhiwei.wang
 * @version 0.0
 * @created 03-一月-2012 17:34:18
 */
package com.maple.eggsnake.mytest;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.maple.eggsnake.actor.ui.AnimateImageListener;
import com.maple.eggsnake.sound.SoundLoader;
import com.maple.eggsnake.stage.content.AboutUsStage;



public class TestAboutUs extends AnimateImageListener implements ApplicationListener{
	
	AboutUsStage stage;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		Music bgSound = SoundLoader.loadMusic("learn.mp3");
		bgSound.play();
		stage = new AboutUsStage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}







