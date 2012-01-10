package com.maple.eggsnake.actor.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.maple.eggsnake.stage.content.Box2DStage;

public abstract class BodyAttachedActor extends Actor {

	protected Box2DStage stage = null;

	public BodyAttachedActor(Box2DStage stage) {
		this.stage = stage;
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean touchDown(float x, float y, int key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void touchDragged(float x, float y, int key) {
		// TODO Auto-generated method stub
	}

	@Override
	public void touchUp(float x, float y, int key) {
		// TODO Auto-generated method stub
	}

}
