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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.actor.ui.AnimateImageListener;
import com.maple.eggsnake.sound.SoundLoader;



public class TestImageGame extends AnimateImageListener implements ApplicationListener{
	ActorRegister snake;
	Texture bgTexture;
	Texture fontTexture;
	TextureRegion bgRegion;
	TextureRegion fontRegion;
	Stage stage;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		Music bgSound = SoundLoader.loadMusic("learn.mp3");
		bgSound.play();
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		bgTexture = new Texture(Gdx.files.internal("data/images/muban_512_512.png"));
		fontTexture = new Texture(Gdx.files.internal("data/images/fontEggSnake_512_128.png"));
		bgRegion = new TextureRegion(bgTexture, 0, 0, 480, 320);
		fontRegion = new TextureRegion(fontTexture, 16, 0, 480, 128);
		this.initAnimateIamges();
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
	
	/**
	 * @Description 初始化四个齿轮按钮
	 * 
	 */
	private void initAnimateIamges(){
		ActorRegister.singleRegister(stage, bgRegion, 0, 0);
		ActorRegister.singleRegister(stage, new TextureRegion(new Texture(Gdx.files.internal("data/images/black_512_128.png")), 
				16, 0, 480, 100), 0, 220);
		ActorRegister.singleRegister(stage, fontRegion, 0, 212);
		ActorRegister.combineRegister(stage, new Texture(Gdx.files.internal("data/images/wheel_128_128.png")), 
				new Texture(Gdx.files.internal("data/images/halfEggSnake_128_128.png")), 0f, 96f, true);
		ActorRegister.combineRegister(stage, new Texture(Gdx.files.internal("data/images/wheel_128_128.png")), 
				new Texture(Gdx.files.internal("data/images/oneEyeMice_64_64.png")), 352f, 100f, false);
		ActorRegister.combineRegister(stage, new Texture(Gdx.files.internal("data/images/quitBtn_64_64.png")), 
				new Texture(Gdx.files.internal("data/images/quitFont_128_64.png")), 416, 0);		
		ActorRegister.singleRegister(stage, new Texture(Gdx.files.internal("data/images/shineWoodBtn_256_64.png")), 
				112, 183);
		ActorRegister.singleRegister(stage, new Texture(Gdx.files.internal("data/images/newGame_128_32.png")),
				168, 199);
		ActorRegister.singleRegister(stage, new Texture(Gdx.files.internal("data/images/shineWoodBtn_256_64.png")), 
				112, 128);	
		ActorRegister.singleRegister(stage, new Texture(Gdx.files.internal("data/images/setting_128_32.png")),
				168, 144);
		ActorRegister.singleRegister(stage, new Texture(Gdx.files.internal("data/images/shineWoodBtn_256_64.png")), 
				112, 73);
		ActorRegister.singleRegister(stage, new Texture(Gdx.files.internal("data/images/aboutUs_128_32.png")),
				168, 89);
	}
}







