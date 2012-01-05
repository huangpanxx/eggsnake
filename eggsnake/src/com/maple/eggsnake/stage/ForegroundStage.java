package com.maple.eggsnake.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;

public class ForegroundStage extends BaseStage {

	SpriteBatch spriteBatch;
	ParticleEffect particle;
	Loggable logger;

	public ForegroundStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		initialize();
	}

	private void initialize() {
		logger = DefaultLogger.getDefaultLogger();
		spriteBatch = new SpriteBatch();
		particle = new ParticleEffect();
		FileHandle description = Gdx.files
				.internal("data/particles/particle.p");
		FileHandle textureDir = Gdx.files.internal("data/images/");
		particle.load(description, textureDir);
		particle.setPosition(-100, -100);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		this.particle.setPosition(x, Gdx.graphics.getHeight() - y);
		this.particle.start();
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		this.particle.setPosition(-100, -100);
		this.particle.start();
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		this.particle.setPosition(arg0, Gdx.graphics.getHeight() - arg1);
		this.particle.start();
		return false;
	}

	@Override
	public void draw() {
		super.draw();
		spriteBatch.begin();
		spriteBatch.setColor(Color.WHITE);
		particle.draw(spriteBatch, Gdx.graphics.getDeltaTime());
		spriteBatch.end();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
}
