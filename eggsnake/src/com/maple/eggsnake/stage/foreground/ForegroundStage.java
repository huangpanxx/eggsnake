package com.maple.eggsnake.stage.foreground;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class ForegroundStage extends BaseStage {

	SpriteBatch spriteBatch;
	ParticleEffect particle;
	Loggable logger;
	Sound slipSound;
	Sound clickSound;
	float soundDelta;
	float w;
	float h;
	final float minSoundDelta = 0.5f;
	final float minDistance = 50;
	private int startX = 0;
	private int startY = 0;
	TextureRegion texCourse;
	TextureRegion texClickDown;
	TextureRegion texClickNormal;
	TextureRegion texDrag;

	private Vector2 coursePos = new Vector2(50, 50);

	public ForegroundStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.w = width;
		this.h = height;
		this.soundDelta = 0;
		initialize();
	}

	private void playSlipSound() {
		if (this.soundDelta > this.minSoundDelta) {
//			this.slipSound.play();
			this.soundDelta = 0;
		}
	}
	private void playClickSound(){
		this.clickSound.play();
	}

	private void initialize() {
		logger = DefaultLogger.getDefaultLogger();
		spriteBatch = new SpriteBatch();
		particle = ResourceLoader.loadParticle("default.p", "");
		particle.setPosition(-100, -100);
		this.slipSound = ResourceLoader.loadSound("slip.ogg");
		Texture course = ResourceLoader.loadTexture("hand256_64.png");
		this.texClickNormal = new TextureRegion(course, 0, 0, 41, 56);
		this.texClickDown = new TextureRegion(course, 82, 0, 41, 56);
		this.texDrag = new TextureRegion(course, 126, 0, 41, 56);
		this.texCourse = this.texClickNormal;
		this.clickSound = ResourceLoader.loadSound("click_sound.ogg");
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		this.startX = x;
		this.startY = y;
		this.soundDelta = this.minSoundDelta;
		float screen_x = x * w / width();
		float screen_y = h - y * h / height();
		this.particle.setPosition(x * w / width(), h - y * h / height());
		this.particle.start();
		this.coursePos.set(screen_x, screen_y);
		this.texCourse = this.texClickDown;
		this.playClickSound();
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		this.particle.setPosition(-100, -100);
		this.particle.start();
		this.texCourse = this.texClickNormal;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int keyType) {
		float screen_x = x * w / width();
		float screen_y = h - y * h / height();
		this.particle.setPosition(x * w / width(), h - y * h / height());
		this.particle.start();
		this.coursePos.set(screen_x, screen_y);
		if (isMoved(x, y)) {
			this.playSlipSound();
			this.startX = x;
			this.startY = y;
		}
		this.texCourse = this.texDrag;
		return false;
	}

	private boolean isMoved(int x, int y) {
		return this.getDistance(this.startX, this.startY, x, y) > this.minDistance;
	}

	private float getDistance(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	@Override
	public void draw() {
		float dt = Gdx.graphics.getDeltaTime();
		spriteBatch.begin();
		this.spriteBatch.draw(texCourse, this.coursePos.x-2,
				this.coursePos.y - 36);
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
		this.coursePos.set(this.width() *2 /3, this.height() / 2);

	}
}
