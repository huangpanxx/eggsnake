package com.maple.eggsnake.stage.content;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.physics.B2Const;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.task.Task;
import com.maple.eggsnake.task.TaskContainer;
import com.maple.eggsnake.task.TaskQueueContainer;

public class Box2DStage extends BaseStage {

	private class ContactHandleTask implements Task<Object, World> {

		Contact contact;
		@SuppressWarnings("unused")
		ContactImpulse impluse;

		public ContactHandleTask(Contact _contact, ContactImpulse _impluse) {
			this.contact = _contact;
			this.impluse = _impluse;
		}

		@Override
		public Object doWork(World world) {
			if (contact.isTouching()) {
				Fixture fixtureA = contact.getFixtureA();
				Fixture fixtureB = contact.getFixtureB();
				Body bodyA = fixtureA.getBody();
				Body bodyB = fixtureB.getBody();

				DefaultLogger.getDefaultLogger().logWithSignature(this,
						"碰撞:%1$s,%2$s", bodyA.getType().toString(),
						bodyB.getType().toString());
				if (fixtureA.getFilterData().groupIndex == 1)
					world.destroyBody(bodyA);
				if (bodyB.getType() == BodyType.DynamicBody) {
					Vector2 bodyPos = bodyB.getPosition();
					Vector3 pos = new Vector3(bodyPos.x, bodyPos.y, 0);
					camera.project(pos);
					particleLife = PARTICLELIFE;
					particle.setPosition(pos.x, pos.y);
					particle.start();

					logger.logWithSignature(this, "碰撞坐标：%1$s,%2$s", pos.x,
							pos.y);

				}
			}
			return null;
		}

	}

	World world;
	Box2DDebugRenderer render;
	Camera debugCamera;
	Loggable logger = null;
	MouseJoint mouseJoint = null;
	SpriteBatch spriteBatch;
	ParticleEffect particle;

	final TaskContainer<Task<Object, World>> tasks;

	float viewportWidth;
	float viewportHeight;
	float position_x;
	float position_y;

	final float PARTICLELIFE = 0.2f;
	float particleLife = PARTICLELIFE;

	Vector3 hitPoint = new Vector3();
	Vector2 target = new Vector2();
	Body hitBody = null;
	Body ground = null;

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

	public Box2DStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.logger = DefaultLogger.getDefaultLogger();

		particle = ResourceLoader.loadParticle("collision.p", "");
		particle.setDuration(0);

		this.particleLife = this.PARTICLELIFE;

		spriteBatch = new SpriteBatch();
		render = new Box2DDebugRenderer();
		tasks = new TaskQueueContainer<Task<Object, World>>();

		try {
			world = ResourceLoader.loadWorld("hp.json");
			logger.logWithSignature(this, "Body:%1$d", world.getBodyCount());
			logger.logWithSignature(this, "Joint:%1$d", world.getJointCount());
			this.ground = createTinyGround();
			World.setVelocityThreshold(15f);

			world.setContactListener(new ContactListener() {

				@Override
				public void preSolve(Contact arg0, Manifold arg1) {
					// TODO Auto-generated method stub
				}

				@Override
				public void postSolve(Contact contact, ContactImpulse impluse) {
					// TODO Auto-generated method stub
					tasks.push(new ContactHandleTask(contact, impluse));
				}

				@Override
				public void endContact(Contact arg0) {
					// TODO Auto-generated method stub
				}

				@Override
				public void beginContact(Contact contact) {
				}
			});
		} catch (Exception e) {
			logger.logWithSignature(this, "加载世界失败(%1$s)", e.getMessage());
		}
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	private Body createTinyGround() {
		BodyDef bd = new BodyDef();
		bd.type = BodyDef.BodyType.StaticBody;
		Body body = world.createBody(bd);
		return body;
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
		if (world != null) {
			world.step(Gdx.graphics.getDeltaTime() / 1.8f, 10, 10);
			while (!tasks.isEmpty()) {
				Task<Object, World> task = tasks.pop();
				task.doWork(world);
			}
			render.render(world, this.camera.combined);
		}
		if (particle != null) {
			spriteBatch.begin();
			particle.draw(spriteBatch, Gdx.graphics.getDeltaTime());
			spriteBatch.end();
			if (this.particleLife > 0) {
				this.particleLife -= Gdx.graphics.getDeltaTime();
				if (this.particleLife <= 0) {
					this.particle.setPosition(-100, -100);
				}
			}
		}
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

			logger.logWithSignature(this, "hitBody speed:%1$f,%2$s",
					this.hitBody.getLinearVelocity().x,
					this.hitBody.getLinearVelocity().y);

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
		if (mouseJoint != null && this.hitBody != null) {
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
			float mass = 4;
			this.hitBody.setLinearVelocity(v.x * mass, v.y * mass);
		}
		return false;
	}
}
