package com.maple.eggsnake.stage.content;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.actor.ui.ActorRegister;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.physics.B2Const;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class ZhujianxinStage extends BaseStage {

	World world;
	Box2DDebugRenderer render;
	Camera debugCamera;
	Loggable logger = null;
	MouseJoint mouseJoint = null;

	float viewportWidth;
	float viewportHeight;
	float position_x;
	float position_y;

	Vector3 hitPoint = new Vector3();
	Vector2 target = new Vector2();
	Body hitBody = null;
	Body ground = null;
	
	TextureRegion region = new TextureRegion(ResourceLoader.loadTexture("me.png"), 
			0, 0, 32, 32);
	Image rat = new Image("rat", region);

	QueryCallback callback = new QueryCallback() {
		@Override
		public boolean reportFixture(Fixture fixture) {
			if (fixture.testPoint(hitPoint.x, hitPoint.y)) {
				hitBody = fixture.getBody();
				return true;
			}
			return false;
		}
	};

	public ZhujianxinStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.logger = DefaultLogger.getDefaultLogger();

		render = new Box2DDebugRenderer();

		try {
			world = ResourceLoader.loadWorld("maptest.json");
			logger.logWithSignature(this, "Body:%1$d", world.getBodyCount());
			logger.logWithSignature(this, "Joint:%1$d", world.getJointCount());
			Iterator<Body> it = world.getBodies();
			while (it.hasNext()) {
				Body body = it.next();
				String name = (String) body.getUserData();
				if (name != null && "snake".equals(name)) {
					Vector3 vector3 = new Vector3(body.getPosition().x, 
							body.getPosition().y, 0);
					camera.project(vector3);
					rat.x = vector3.x;
					rat.y = vector3.y / 20f;
					System.out.println("constructor x: " + rat.x + 
							" construtor y: " + rat.y);
					this.addActor(rat);
					this.ground = body;
					break;
				}
			}
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
		viewportWidth = this.camera.viewportWidth;
		viewportHeight = this.camera.viewportHeight;
		this.position_x = this.camera.position.x;
		this.position_y = this.camera.position.y;
		this.camera.viewportWidth = 480 / B2Const.CONVERTRATIO;// this.width() /
																// B2Const.CONVERTRATIO;
		this.camera.viewportHeight = 320 / B2Const.CONVERTRATIO;// this.height()
																// /
																// B2Const.CONVERTRATIO;
		this.camera.position.set(0, 0, 1);
	}

	@Override
	public void draw() {
		super.draw();
		this.camera.viewportWidth = 48;
		this.camera.viewportHeight = 32;
		this.camera.position.set(0,20,1);
		world.step(Gdx.graphics.getDeltaTime(), 10, 10);
		render.render(world, this.camera.combined);
		this.attachTexture(world);
	}

	@Override
	public void dispose() {
		this.camera.viewportHeight = this.viewportHeight;
		this.camera.viewportWidth = this.viewportWidth;
		this.camera.position.set(this.position_x, this.position_y,
				this.camera.position.z);
		if (world != null)
			world.dispose();
		super.dispose();
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		camera.unproject(hitPoint.set(x, y, 0));
		this.hitBody = null;
		world.QueryAABB(callback, this.hitPoint.x - 0.0001f,
				this.hitPoint.y - 0.0001f, this.hitPoint.x + 0.0001f,
				this.hitPoint.y + 0.0001f);

		if (this.hitBody != null && hitBody.getType() == BodyType.DynamicBody
				&& ground != null) {
			MouseJointDef def = new MouseJointDef();
			def.bodyA = ground;// groundBody
			def.bodyB = hitBody;
			def.collideConnected = true;
			def.target.set(this.hitPoint.x, this.hitPoint.y);
			def.maxForce = 1000.f * hitBody.getMass();
			mouseJoint = (MouseJoint) world.createJoint(def);
			hitBody.setAwake(true);
		}
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (mouseJoint != null) {
			logger.logWithSignature(this, "%1$f,%2$f",
					this.hitBody.getPosition().x, this.hitBody.getPosition().y);
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (mouseJoint != null) {
			world.destroyJoint(mouseJoint);
			mouseJoint = null;
			Vector3 mousePos = new Vector3(x, y, 0);
			camera.unproject(mousePos);
			Vector3 bodyPos = this.hitPoint;
			Vector2 v = new Vector2(mousePos.x - bodyPos.x, mousePos.y
					- bodyPos.y);
			float mass = this.hitBody.getMass() / 100;
			this.hitBody.setLinearVelocity(v.x * mass, v.y * mass);
		}
		return false;
	}
	
	private void attachTexture(World world){
		Iterator<Body> iterator;
		Vector3 bodyVector3;
		Body body;
		iterator = world.getBodies();
		String userData;
		while(iterator.hasNext()){
			body = iterator.next();
			userData = (String)body.getUserData();
			if("snake".equals(userData)){
				bodyVector3 = new Vector3(body.getPosition().x, body.getPosition().y, 0f);
				camera.project(bodyVector3);
				System.out.println("X: " + bodyVector3.x + " Y:" + 
				bodyVector3.y);
				rat.x = bodyVector3.x;
				rat.y = bodyVector3.y;
				this.addActor(rat);
			}
		}
	}
}
