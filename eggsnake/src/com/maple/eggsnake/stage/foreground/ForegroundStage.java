package com.maple.eggsnake.stage.foreground;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class ForegroundStage extends BaseStage {

	SpriteBatch spriteBatch;
	ParticleEffect particle;
	Loggable logger;
	Sound slipSound;
	float soundDelta;
	float w;
	float h;
	final float minSoundDelta = 0.3f;

	public ForegroundStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.w = width;
		this.h = height;
		this.soundDelta = 0;
		initialize();
	}

	private void playSlipSound(){
		if (this.soundDelta > this.minSoundDelta) {
			this.slipSound.play();
			this.soundDelta = 0;
		}
	}
	private void initialize() {
		logger = DefaultLogger.getDefaultLogger();
		spriteBatch = new SpriteBatch();
		particle = ResourceLoader.loadParticle("default.p", "");
		particle.setPosition(-100, -100);
		this.slipSound = ResourceLoader.loadSound("slip.ogg");
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		this.soundDelta = this.minSoundDelta;
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
		this.playSlipSound();
		return false;
	}

	@Override
	public void draw() {
		float dt = Gdx.graphics.getDeltaTime();
		spriteBatch.begin();
		particle.draw(spriteBatch, dt);
		spriteBatch.end();
		this.soundDelta += dt;
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
