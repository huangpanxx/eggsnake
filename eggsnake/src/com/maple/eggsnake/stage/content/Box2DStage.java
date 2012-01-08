package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.physics.B2WorldFactory;
import com.maple.eggsnake.stage.BaseStage;

public class Box2DStage extends BaseStage {

	World world;
	Box2DDebugRenderer render;
	Camera debugCamera;
	Loggable logger = null;

	float viewportWidth;
	float viewportHeight;
	float position_x;
	float position_y;

	public Box2DStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.logger = DefaultLogger.getDefaultLogger();



		render = new Box2DDebugRenderer();

		try {
			world = B2WorldFactory.loadWorld("data/maps/snapshot.json");
			logger.log("%1$d",world.getBodyCount());
			logger.log("%1$d",world.getJointCount());
		} catch (Exception e) {
			logger.logWithSignature(this, "加载世界失败(%1$s)", e.getMessage());
		}
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

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		viewportWidth = this.camera.viewportWidth;
		viewportHeight = this.camera.viewportHeight;
		this.position_x = this.camera.position.x;
		this.position_y = this.camera.position.y;

		this.camera.viewportWidth = 48 * 4;
		this.camera.viewportHeight = 32 * 4;
		this.camera.position.set(0, 24 * 4, 1);
	}

	@Override
	public void draw() {
		super.draw();
		if (world != null) {
			world.step(Gdx.graphics.getDeltaTime(), 10, 10);
			render.render(world, this.camera.combined);
		}
	}

	@Override
	public void dispose() {
		this.camera.viewportHeight = this.viewportHeight;
		this.camera.viewportWidth = this.viewportWidth;
		this.camera.position.set(this.position_x, this.position_y,
				this.camera.position.z);
		if(world!=null)
			world.dispose();
		super.dispose();
	}
}
