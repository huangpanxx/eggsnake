package com.maple.eggsnake.stage.content;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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

	Vector3 pos_camera = new Vector3(0, 0, 0);
	Box2DDebugRenderer render = new Box2DDebugRenderer();

	float viewportWidth = 0;
	float viewportHeight = 0;

	public Box2DStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		logger = DefaultLogger.getDefaultLogger();
		this.gate = 0;
		this.initController();
	}

	private void initController() {
		try {
			this.controller = new WorldController(this.gate,this);
		} catch (Exception e) {
			logger.logWithSignature(this, "加载地图失失败:%1$s",
					e.getLocalizedMessage());
		}
	}
	
	private void gotoGate(int index) throws Exception{
		this.controller.reloadWorld(index);
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
		viewportWidth = this.camera.viewportWidth;
		viewportHeight = this.camera.viewportHeight;
		this.pos_camera = new Vector3(this.camera.position);

		this.camera.viewportWidth = 480 / B2Const.CONVERTRATIO;
		this.camera.viewportHeight = 320 / B2Const.CONVERTRATIO;
		this.camera.position.set(0, this.camera.viewportHeight / 2, 1);
	}

	@Override
	public void draw() {
		super.draw();
		float dt = Gdx.graphics.getDeltaTime();
		if (controller.getWorld() != null) {
			controller.update(dt);
			render.render(controller.getWorld(), this.camera.combined);
		}
	}

	@Override
	public void dispose() {
		this.controller.dispose();
		super.dispose();
	}

	private Vector2 convertToWorld(int x, int y) {
		Vector3 point = new Vector3(x, y, 0);
		this.camera.unproject(point);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShotTimeChanged(int hitTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseKilled(String mouseName) {
		// TODO Auto-generated method stub
		
	}
}
