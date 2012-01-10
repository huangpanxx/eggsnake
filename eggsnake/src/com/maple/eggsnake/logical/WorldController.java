package com.maple.eggsnake.logical;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.task.Task;
import com.maple.eggsnake.task.TaskContainer;
import com.maple.eggsnake.task.TaskQueueContainer;

public class WorldController {

	private class DeleteBodyTask implements Task<Object, World> {

		Body body;

		public DeleteBodyTask(Body body) {
			this.body = body;
		}

		@Override
		public Object doWork(World world) {
			if (world != null)
				world.destroyBody(body);
			if (judge != null)
				judge.killOne();
			return null;
		}

	}

	private class ContactHander implements ContactListener {

		@Override
		public void beginContact(Contact contact) {

		}

		@Override
		public void endContact(Contact contact) {
		}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
			Fixture fixtureA = contact.getFixtureA();
			Fixture fixtureB = contact.getFixtureB();
			if (fixtureA != null && fixtureB != null) {
				Body bodyA = fixtureA.getBody();
				Body bodyB = fixtureB.getBody();
				if (bodyA != null && bodyB != null) {
					String nameA = (String) bodyA.getUserData();
					String nameB = (String) bodyB.getUserData();
					double f_x = impulse.getTangentImpulses()[0];
					double f_y = impulse.getTangentImpulses()[1];

					double f = Math.sqrt(f_x * f_x + f_y * f_y);
					logger.logWithSignature(this, "%1$f", f);
					if (f > 0.05f) {
						if ("mouse".equals(nameA)) {
							addTask(new DeleteBodyTask(bodyA));

						} else if ("mouse".equals(nameB)) {
							addTask(new DeleteBodyTask(bodyB));

						}
					}

					// if (nameA != null && nameB != null) {
					// if (nameB.equals("mouse") && nameA.equals("snake")) {
					// addTask(new DeleteBodyTask(bodyB));
					// } else if (nameA.equals("mouse")
					// && nameB.equals("snake")) {
					// addTask(new DeleteBodyTask(bodyA));
					// }
					// }
				}
			}
		}

		@Override
		public void preSolve(Contact contact, Manifold manifold) {
		}

	}

	/**
	 * here start the controller
	 */

	World world;
	Body groundBody;
	Body hitBody;
	Vector2 hitPoint;
	TaskContainer<Task<Object, World>> tasks;
	MouseJoint mouseJoint = null;
	Loggable logger;
	GateJudge judge = null;
	boolean pause = false;

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

	public WorldController(World world, LogicalGameListener listener)
			throws Exception {
		this.initialize(world, listener);
	}

	public WorldController(String world, LogicalGameListener listener)
			throws Exception {
		this.initialize(loadWorld(world), listener);
	}

	public WorldController(String map) throws Exception {
		this.initialize(loadWorld(map), null);
	}

	public WorldController(World world) throws Exception {
		this.initialize(world, null);

	}

	public WorldController(int gate, LogicalGameListener listener)
			throws Exception {
		World world = this.loadWorld(gate);
		this.initialize(world, listener);
	}

	public void reloadWorld(String map) throws Exception {
		World world = loadWorld(map);
		this.initialize(world, this.judge.getListener());
	}

	public void reloadWorld(int gate) throws Exception {
		World world = ResourceLoader.loadGate(gate);
		this.initialize(world, this.judge.getListener());
	}

	public void reloadWorld(World world) throws Exception {
		this.initialize(world, this.judge.getListener());
	}

	public void addTask(Task<Object, World> task) {
		this.tasks.push(task);
	}

	public void pause() {
		this.pause = true;
	}

	public void resume() {
		this.pause = false;
	}

	public boolean isPause() {
		return this.pause;
	}

	private void initialize(World world, LogicalGameListener listener)
			throws Exception {
		if (world == null) {
			throw new Exception("world is null");
		}
		if (tasks != null) {
			tasks.clear();
		} else {
			tasks = new TaskQueueContainer<Task<Object, World>>();
		}
		if (this.world != null) {
			this.world.dispose();
			this.world = null;
		}
		logger = DefaultLogger.getDefaultLogger();
		hitPoint = new Vector2(0, 0);
		try {
			this.setWorld(world);
			this.loadJugde();
		} catch (Exception e) {
			this.world = null;
			throw e;
		}

		this.setGameLogicalListener(listener);
	}

	private void loadJugde() {
		this.judge = new GateJudge(world);
	}

	public void setGameLogicalListener(LogicalGameListener listener) {
		this.judge.setListener(listener);
	}

	private World loadWorld(String map) throws Exception {
		World world = null;
		try {
			world = ResourceLoader.loadWorld(map);
		} catch (Exception e) {
			String error = String
					.format("加载世界%1$s失败:%2$s", map, e.getMessage());
			logger.logWithSignature(this, error);
			throw new Exception(error);
		}
		return world;
	}

	private World loadWorld(int gate) throws Exception {
		World world = null;
		try {
			world = ResourceLoader.loadGate(gate);
		} catch (Exception e) {
			String error = String.format("加载关卡世界%1$s失败:%2$s", gate,
					e.getMessage());
			logger.logWithSignature(this, error);
			throw new Exception(error);
		}
		return world;
	}

	private void setWorld(World world) {
		this.world = world;
		this.groundBody = this.createTinyGround();
		world.setContactListener(new ContactHander());
		World.setVelocityThreshold(15f);
	}

	public World getWorld() {
		return this.world;
	}

	public void update(float dt) {
		if (!pause && world != null) {
			world.step(dt, 10, 10);
			while (!tasks.isEmpty()) {
				Task<Object, World> task = tasks.pop();
				task.doWork(world);
			}
		}
	}

	private Body createTinyGround() {
		BodyDef bd = new BodyDef();
		bd.type = BodyDef.BodyType.StaticBody;
		Body body = world.createBody(bd);
		return body;
	}

	public void dispose() {
		world.dispose();
	}

	public boolean touchDown(Vector2 pos) {
		return this.touchDown(pos.x, pos.y);
	}

	public boolean touchDown(float x, float y) {
		this.hitBody = null;
		this.hitPoint.set(x, y);
		world.QueryAABB(callback, this.hitPoint.x - 0.01f,
				this.hitPoint.y - 0.01f, this.hitPoint.x + 0.01f,
				this.hitPoint.y + 0.01f);
		if (this.hitBody != null && hitBody.getType() == BodyType.DynamicBody
				&& this.groundBody != null) {
			String name = (String) this.hitBody.getUserData();
			if (name != null && name.equals("snake")) {
				Vector2 speed = hitBody.getLinearVelocity();
				logger.logWithSignature(this, "hitBody speed:%1$f,%2$s",
						this.hitBody.getLinearVelocity().x,
						this.hitBody.getLinearVelocity().y);
				if (true) {// speed.x < 0.01f && speed.y < 0.01f) {
					MouseJointDef def = new MouseJointDef();
					def.bodyA = this.groundBody;
					def.bodyB = hitBody;
					def.collideConnected = true;
					def.target.set(this.hitBody.getPosition().x,
							this.hitBody.getPosition().y);
					def.maxForce = 1000.f * hitBody.getMass();
					mouseJoint = (MouseJoint) world.createJoint(def);
					hitBody.setAwake(true);
				}
			}
		}
		return false;

	}

	public boolean touchDragged(Vector2 pos) {
		return this.touchDragged(pos.x, pos.y);
	}

	public boolean touchDragged(float x, float y) {
		return false;
	}

	public boolean touchUp(Vector2 pos) {
		return this.touchUp(pos.x, pos.y);
	}

	public boolean touchUp(float x, float y) {
		if (mouseJoint != null) {
			world.destroyJoint(mouseJoint);
			mouseJoint = null;

			Vector2 mousePos = new Vector2(x, y);
			Vector2 bodyPos = this.hitPoint;
			;
			Vector2 v = new Vector2(mousePos.x - bodyPos.x, mousePos.y
					- bodyPos.y);
			float mass = 4;
			this.hitBody.setLinearVelocity(v.x * mass, v.y * mass);
		}
		return false;
	}
}
