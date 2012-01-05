package com.maple.eggsnake.stage.foreground;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.stage.BaseStage;

public class ForegroundStage extends BaseStage {

	SpriteBatch spriteBatch;
	ParticleEffect particle;
	Loggable logger;

	float w;
	float h;

	public ForegroundStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.w = width;
		this.h = height;
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
		this.particle.setPosition(x * w / width(), h - y * h / height());
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
		this.particle.setPosition(arg0 * w / width(), h - arg1 * h / height());
		this.particle.start();
		return false;
	}

	@Override
	public void draw() {
		spriteBatch.begin();
		particle.draw(spriteBatch, Gdx.graphics.getDeltaTime());
		spriteBatch.end();

		super.draw();
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
		this.height = height;
		this.width = width;

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
