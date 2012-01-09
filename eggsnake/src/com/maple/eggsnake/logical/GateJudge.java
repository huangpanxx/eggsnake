package com.maple.eggsnake.logical;

import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class GateJudge {

	int mouseCounter = 0;
	LogicalGameListener listener = null;

	public GateJudge(World world) {
		this.initialize(world);
	}

	public GateJudge(World world, LogicalGameListener listener) {
		this.initialize(world);
		this.setListener(listener);
	}

	public void initialize(World world) {
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

	public void setListener(LogicalGameListener listener) {
		this.listener = listener;
	}
	
	public LogicalGameListener getListener(){
		return this.listener;
	}

	public void killOne() {
		this.mouseCounter--;
		if (this.mouseCounter == 0 && this.listener != null) {
			this.listener.onAllMouseKilled();
		}
	}
}
