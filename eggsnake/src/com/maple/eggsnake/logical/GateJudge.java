package com.maple.eggsnake.logical;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.service.ResourceLoader;

public class GateJudge {

	private int crossGateTimer = 10;

	private Sound crossGateSound = null;
	private Sound killSound = null;

	int mouseCounter = 0;
	LogicalGameListener listener = null;

	private boolean isCrossed = false;

	Loggable logger;

	public GateJudge(World world) {
		this.initialize(world);
	}

	public void tick() {
		if (this.isCrossed() && this.getCrossGateTimer() > 0) {
			--this.crossGateTimer;
			if (this.getCrossGateTimer() <= 0) {
				if (this.listener != null) {
					this.listener.onAllMouseKilled();
				}
			}
		}

	}

	public GateJudge(World world, LogicalGameListener listener) {
		this.initialize(world);
		this.setListener(listener);
	}

	public void initialize(World world) {
		this.logger = DefaultLogger.getDefaultLogger();
		this.setCrossed(false);
		this.setCrossGateTimer(10);
		Iterator<Body> it = world.getBodies();
		while (it.hasNext()) {
			Body body = it.next();
			ArrayList<Fixture> fixtures = body.getFixtureList();
			for (Fixture fixture : fixtures) {
				int index = fixture.getFilterData().groupIndex;
				if (index == BodyGroup.MOUSES) {
					mouseCounter++;
					break;
				}
			}
		}
		try {
			this.killSound = ResourceLoader.loadSound("kill_sound.ogg");
			this.crossGateSound = ResourceLoader
					.loadSound("cross_gate_sound.ogg");
		} catch (Exception e) {
			logger.logWithSignature(this, "加载声音出错:%1$s", e.getMessage());
		}
	}

	void playKillSound() {
		if (this.killSound != null)
			this.killSound.play();
	}

	void playCrossGateSound() {
		if (this.crossGateSound != null)
			this.crossGateSound.play();
	}

	public void setListener(LogicalGameListener listener) {
		this.listener = listener;
	}

	public LogicalGameListener getListener() {
		return this.listener;
	}

	public void killOne() {
		this.mouseCounter--;
		this.playKillSound();
		if (this.mouseCounter == 0 && this.listener != null) {
			this.setCrossed(true);
		}
	}

	public boolean isCrossed() {
		return isCrossed;
	}

	public void setCrossed(boolean isCrossed) {
		this.isCrossed = isCrossed;
		this.playCrossGateSound();
	}

	public int getCrossGateTimer() {
		return crossGateTimer;
	}

	public void setCrossGateTimer(int crossGateTimer) {
		this.crossGateTimer = crossGateTimer;
	}
}
