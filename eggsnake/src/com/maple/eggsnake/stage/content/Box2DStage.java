package com.maple.eggsnake.stage.content;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.maple.eggsnake.actor.game.ActorFactory;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.logical.LogicalGameListener;
import com.maple.eggsnake.logical.WorldController;
import com.maple.eggsnake.physics.B2Const;
import com.maple.eggsnake.stage.BaseStage;

public class Box2DStage extends BaseStage implements LogicalGameListener {

	Loggable logger;
	WorldController controller;
	private int gate = 0;

	Box2DDebugRenderer render = new Box2DDebugRenderer();
	public OrthographicCamera debugCamera = new OrthographicCamera();

	private HashMap<Body, Actor> Map;

	public Box2DStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		logger = DefaultLogger.getDefaultLogger();
		this.gate = 0;
		this.Map = new HashMap<Body, Actor>();
		this.initController();
	}

	private void initController() {
		try {
			this.controller = new WorldController(this.gate, this);
			this.initActors(controller.getWorld());
		} catch (Exception e) {
			logger.logWithSignature(this, "加载地图失失败:%1$s",
					e.getLocalizedMessage());
		}
	}

	private void initActors(World world) {
		this.Map.clear();
		this.clear();
		Iterator<Body> it = world.getBodies();
		while (it.hasNext()) {
			Body body = it.next();
			Actor actor = ActorFactory.createActor(body, this);
			if (actor != null) {
				this.addActor(actor);
				Map.put(body, actor);
			}
		}

	}

	private void gotoGate(int index) throws Exception {
		this.controller.reloadWorld(index);
		this.initActors(this.controller.getWorld());
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {

		logger.logWithSignature(this, "show");
		this.debugCamera.viewportWidth = 480 / B2Const.CONVERTRATIO;
		this.debugCamera.viewportHeight = 320 / B2Const.CONVERTRATIO;
		this.debugCamera.position.set(new Vector3(
				this.debugCamera.viewportWidth / 2,
				this.debugCamera.viewportHeight / 2, 1));
	}

	@Override
	public void draw() {
		super.draw();
		float dt = Gdx.graphics.getDeltaTime();
		if (controller.getWorld() != null) {
			controller.update(dt);
			this.debugCamera.update();
			render.render(controller.getWorld(), this.debugCamera.combined);
			this.debugCamera.apply(Gdx.gl10);
		}
	}

	@Override
	public void dispose() {
		this.controller.dispose();
		super.dispose();
	}

	private Vector2 convertToWorld(int x, int y) {
		Vector3 point = new Vector3(x, y, 0);
		this.debugCamera.unproject(point);
		return new Vector2(point.x, point.y);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		Vector2 hitPoint = convertToWorld(x, y);
		return controller.touchDown(hitPoint);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		Vector2 hitPoint = convertToWorld(x, y);
		return controller.touchDragged(hitPoint);
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		Vector2 hitPoint = convertToWorld(x, y);
		controller.touchUp(hitPoint);
		return false;
	}

	@Override
	public void onAllMouseKilled() {
		logger.logWithSignature(this, "过关");
		try {
			this.gotoGate(++this.gate);
		} catch (Exception e) {
			logger.logWithSignature(this, e.getMessage());
		}
	}

	@Override
	public void onCrossGate() {
		try {
			this.gotoGate(++this.gate);
		} catch (Exception e) {
			logger.logWithSignature(this, e.getMessage());
		}
	}

	@Override
	public void onShotTimeChanged(int hitTime) {

	}

	@Override
	public void onMouseKilled(Body body) {
		Actor actor = this.Map.get(body);
		this.removeActor(actor);

	}
}
