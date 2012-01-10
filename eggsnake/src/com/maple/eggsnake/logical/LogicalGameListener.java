package com.maple.eggsnake.logical;

public interface LogicalGameListener {
	public void onAllMouseKilled();
	public void onCrossGate();
	public void onShotTimeChanged(int hitTime);
}
