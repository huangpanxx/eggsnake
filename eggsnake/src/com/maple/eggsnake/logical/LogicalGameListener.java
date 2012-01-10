package com.maple.eggsnake.logical;

import com.badlogic.gdx.physics.box2d.Body;

public interface LogicalGameListener {
	public void onAllMouseKilled();
	public void onCrossGate();
	public void onShotTimeChanged(int hitTime);
	public void onMouseKilled(Body body);
}
