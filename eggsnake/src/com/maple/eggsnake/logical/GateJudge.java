package com.maple.eggsnake.logical;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.service.SoundManager;

public class GateJudge {

	private int crossGateTimer = 10;
	private boolean isCrossed = false;
	private boolean crossGateTimerAwake = false;

	int mouseCounter = 0;
	private int shotCounter = 0;
	LogicalGameListener listener = null;

	Loggable logger;

	public GateJudge(World world) {
		this.initialize(world);
	}

	public void tick() {
		if (this.isCrossGateTimerAwake()) {
			this.setCrossGateTimer(this.getCrossGateTimer() - 1);
		}

	}

	public void shot() {
		if (!this.isCrossed()) {
			this.shotCounter++;
			if (this.listener != null)
				this.listener.onShotTimeChanged(this.shotCounter);
			logger.logWithSignature(this, "发射次数:%1$d", this.shotCounter);
		}
	}

	public GateJudge(World world, LogicalGameListener listener) {
		this.initialize(world);
		this.setListener(listener);
	}

	public void initialize(World world) {
		this.logger = DefaultLogger.getDefaultLogger();
		this.shotCounter = 0;
		this.setCrossed(false);
		this.setCrossGateTimerAwake(false);
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
	}

	void playKillSound() {
		SoundManager.playKillSound();
	}

	void playCrossGateSound() {
		SoundManager.playCrossGateSound();
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
		if (isCrossed) {
			if (this.listener != null) {
				this.playCrossGateSound();
				this.listener.onAllMouseKilled();
			}
			this.setCrossGateTimer(10);
		}
	}

	public int getCrossGateTimer() {
		return crossGateTimer;
	}

	public void setCrossGateTimer(int crossGateTimer) {
		this.crossGateTimer = crossGateTimer;
		if (this.crossGateTimer <= 0) {
			this.setCrossGateTimerAwake(false);
			this.crossGateTimer = 0;
			if (this.listener != null) {
				this.listener.onCrossGate();
			}
		}
	}

	public boolean isCrossGateTimerAwake() {
		return crossGateTimerAwake;
	}

	public void setCrossGateTimerAwake(boolean crossGateTimerAwake) {
		this.crossGateTimerAwake = crossGateTimerAwake;
	}
}
