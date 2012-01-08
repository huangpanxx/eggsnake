package com.maple.eggsnake.physics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import com.badlogic.gdx.physics.box2d.joints.GearJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;

public class B2WorldFactory {

	private B2WorldFactory() {

	}

	public static World loadWorld(String path) throws Exception {

		Loggable logger = DefaultLogger.getDefaultLogger();

		// 读入json地图
		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer();
		String s = new String();
		while ((s = br.readLine()) != null) {
			sb.append(s);
			sb.append('\n');
		}
		String text = sb.toString();

		// 规范化命名
		String jsonWorld = text.replace("massData-mass", "massData_mass")
				.replace("massData-center", "massData_center")
				.replace("massData-I", "massData_I")
				.replace("filter-categoryBits", "filter_categoryBits")
				.replace("filter-maskBits", "filter_maskBits")
				.replace("filter-groupIndex", "filter_groupIndex");

		fr.close();

		// 反序列化
		StringReader reader = new StringReader(jsonWorld);
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(B2Vector.class, new B2VectorSerializer())
				.registerTypeAdapter(B2HexFloat.class,
						new B2HexFloatSerializer()).create();

		B2World w = gson.fromJson(reader, B2World.class);

		// 构造物理世界
		Vector2 gravity = w.gravity.toVector2();
		logger.logWithSignature("B2WorldFactory", "物理世界重力:%1$f,%2$f",
				gravity.x, gravity.y);
		World world = new World(gravity, false);
		world.setAutoClearForces(w.autoClearForces);
		world.setWarmStarting(w.warmStarting);
		world.setContinuousPhysics(w.continuousPhysics);

		// 容器
		ArrayList<Body> bodys = new ArrayList<Body>();
		ArrayList<Joint> joints = new ArrayList<Joint>();

		// 添加Body
		for (B2Body b : w.body) {
			BodyDef bd = new BodyDef();
			bd.position.set(b.position.toVector2());
			if (b.angle != null)
				bd.angle = b.angle.toFloat();
			if (b.linearVelocity != null)
				bd.linearVelocity.set(b.linearVelocity.toVector2());
			if (b.angularVelocity != null)
				bd.angularVelocity = b.angularVelocity.toFloat();
			if (b.linearDamping != null)
				bd.linearDamping = b.linearDamping.toFloat();
			if (b.gravityScale != null)
				bd.gravityScale = b.gravityScale.toFloat();
			bd.allowSleep = b.allowSleep;
			bd.awake = b.awake;
			bd.fixedRotation = b.fixedRotation;
			bd.bullet = b.bullet;
			bd.active = b.active;
			if (b.type == 1) {
				bd.type = BodyDef.BodyType.StaticBody;
			} else if (b.type == 2) {
				bd.type = BodyDef.BodyType.DynamicBody;
			} else if (b.type == 3) {
				bd.type = BodyDef.BodyType.KinematicBody;
			}
			Body body = world.createBody(bd);
			bodys.add(body);
			if (b.name != null)
				body.setUserData(b.name);
			if (b.fixture != null) {
				// 添加Fixture
				for (B2Fixture fd : b.fixture) {
					FixtureDef fDef = new FixtureDef();
					if (fd.restitution != null)
						fDef.restitution = fd.restitution.toFloat();
					if (fd.friction != null)
						fDef.friction = fd.friction.toFloat();
					if (fd.density != null)
						fDef.density = fd.density.toFloat();
					fDef.isSensor = fd.sensor;
					fDef.filter.maskBits = (short) fd.filter_maskBits;
					fDef.filter.categoryBits = (short) fd.filter_categoryBits;
					fDef.filter.groupIndex = (short) fd.filter_groupIndex;
					Shape shape = null;
					if (fd.circle != null) {
						CircleShape circle = new CircleShape();
						shape = circle;
						circle.setRadius(fd.circle.radius.toFloat());
						circle.setPosition(fd.circle.center.toVector2());
					} else if (fd.edge != null) {
						EdgeShape edge = new EdgeShape();
						shape = edge;
						edge.set(fd.edge.vertex1.toVector2(),
								fd.edge.vertex2.toVector2());
						if (fd.edge.hasVertex0) {
							// 没有对应属性 源码657行
						}
						if (fd.edge.hasVertex3) {

						}
					} else if (fd.loop != null) {
						// 跳过
					} else if (fd.chain != null) {
						ChainShape chain = new ChainShape();
						shape = chain;
						int size = fd.chain.vertices.x.size();
						Vector2 vertices[] = new Vector2[size];
						for (int i = 0; i < size; ++i) {
							vertices[i] = new Vector2(fd.chain.vertices.x
									.get(i).toFloat(), fd.chain.vertices.y.get(
									i).toFloat());
						}
						chain.createChain(vertices);
						if (fd.chain.hasPrevVertex)
							chain.setPrevVertex(fd.chain.prevVertex.toVector2());
						if (fd.chain.hasNextVertex)
							chain.setNextVertex(fd.chain.nextVertex.toVector2());
					} else if (fd.polygon != null) {
						PolygonShape polygon = new PolygonShape();
						shape = polygon;
						int size = fd.polygon.vertices.x.size();
						Vector2 vertices[] = new Vector2[size];
						for (int i = 0; i < size; ++i) {
							vertices[i] = new Vector2(fd.polygon.vertices.x
									.get(i).toFloat(), fd.polygon.vertices.y
									.get(i).toFloat());
						}
						polygon.set(vertices);
					}
					if (shape != null) {
						fDef.shape = shape;
						Fixture fixture = body.createFixture(fDef);
						if (fd.name != null)
							fixture.setUserData(fd.name);
					} else {
						logger.logWithSignature("B2WorldFactory",
								"shape is null");
					}

				}
			}
		}

		// 添加 Joints
		if (w.body != null && w.joint != null) {
			for (B2Joint def : w.joint) {
				if (def.type != "gear")
					createJoint(def, bodys, joints, world);
			}
			for (B2Joint def : w.joint) {
				if (def.type == "gear")
					createJoint(def, bodys, joints, world);
			}
		}
		return world;

	}

	static Joint createJoint(B2Joint jd, ArrayList<Body> bodys,
			ArrayList<Joint> joints, World world) {
		int bodyIndexA = jd.bodyA;
		int bodyIndexB = jd.bodyB;
		int bodySize = bodys.size();
		if (bodyIndexA > bodySize || bodyIndexB > bodySize) {
			return null;
		}

		JointDef jointDef = null;

		if (jd.type.equals("revolute")) {
			RevoluteJointDef revoluteDef = new RevoluteJointDef();
			revoluteDef.localAnchorA.set(jd.anchorA.toVector2());
			revoluteDef.localAnchorB.set(jd.anchorB.toVector2());
			revoluteDef.referenceAngle = jd.refAngle.toFloat();
			revoluteDef.enableLimit = jd.enableLimit;
			revoluteDef.lowerAngle = jd.lowerLimit.toFloat();
			revoluteDef.upperAngle = jd.upperLimit.toFloat();
			revoluteDef.enableMotor = jd.enableMotor;
			revoluteDef.motorSpeed = jd.motorSpeed.toFloat();
			revoluteDef.maxMotorTorque = jd.maxMotorTorque.toFloat();
			jointDef = revoluteDef;
		} else if (jd.type.equals("prismatic")) {
			PrismaticJointDef prismaticDef = new PrismaticJointDef();
			prismaticDef.localAnchorA.set(jd.anchorA.toVector2());
			prismaticDef.localAnchorB.set(jd.anchorB.toVector2());
			if (jd.localAxisA != null)
				prismaticDef.localAxisA.set(jd.localAxisA.toVector2());
			else
				prismaticDef.localAxisA.set(jd.localAxis1.toVector2());
			prismaticDef.referenceAngle = jd.refAngle.toFloat();
			prismaticDef.enableLimit = jd.enableLimit;
			prismaticDef.lowerTranslation = jd.lowerLimit.toFloat();
			prismaticDef.upperTranslation = jd.upperLimit.toFloat();
			prismaticDef.enableMotor = jd.enableMotor;
			prismaticDef.motorSpeed = jd.motorSpeed.toFloat();
			prismaticDef.maxMotorForce = jd.maxMotorForce.toFloat();
			jointDef = prismaticDef;
		} else if (jd.type.equals("distance")) {
			DistanceJointDef distanceDef = new DistanceJointDef();
			distanceDef.localAnchorA.set(jd.anchorA.toVector2());
			distanceDef.localAnchorB.set(jd.anchorB.toVector2());
			distanceDef.length = jd.length.toFloat();
			distanceDef.frequencyHz = jd.frequency.toFloat();
			distanceDef.dampingRatio = jd.dampingRatio.toFloat();
			jointDef = distanceDef;
		} else if (jd.type.equals("pulley")) {
			PulleyJointDef pulleyDef = new PulleyJointDef();
			pulleyDef.groundAnchorA.set(jd.groundAnchorA.toVector2());
			pulleyDef.groundAnchorB.set(jd.groundAnchorB.toVector2());
			pulleyDef.localAnchorA.set(jd.anchorA.toVector2());
			pulleyDef.localAnchorB.set(jd.anchorB.toVector2());
			pulleyDef.lengthA = jd.lengthA.toFloat();
			pulleyDef.lengthB = jd.lengthB.toFloat();
			pulleyDef.ratio = jd.ratio.toFloat();
			jointDef = pulleyDef;
		} else if (jd.type.equals("mouse")) {
			MouseJointDef mouseDef = new MouseJointDef();
			mouseDef.target.set(jd.target.toVector2());
			mouseDef.maxForce = jd.maxForce.toFloat();
			mouseDef.frequencyHz = jd.frequency.toFloat();
			mouseDef.dampingRatio = jd.dampingRatio.toFloat();
			jointDef = mouseDef;
		} else if (jd.type.equals("gear")) {
			GearJointDef gearDef = new GearJointDef();
			int jointIndex1 = jd.joint1;
			int jointIndex2 = jd.joint2;
			gearDef.joint1 = joints.get(jointIndex1);
			gearDef.joint2 = joints.get(jointIndex2);
			gearDef.ratio = jd.ratio.toFloat();
		} else if (jd.type.equals("wheel")) {
			WheelJointDef wheelDef = new WheelJointDef();
			wheelDef.localAnchorA.set(jd.anchorA.toVector2());
			wheelDef.localAnchorB.set(jd.anchorB.toVector2());
			wheelDef.localAxisA.set(jd.localAxisA.toVector2());
			wheelDef.enableMotor = jd.enableMotor;
			wheelDef.motorSpeed = jd.motorSpeed.toFloat();
			wheelDef.maxMotorTorque = jd.maxMotorTorque.toFloat();
			wheelDef.frequencyHz = jd.springFrequency.toFloat();
			wheelDef.dampingRatio = jd.springDampingRatio.toFloat();
			jointDef = wheelDef;
		} else if (jd.type.equals("weld")) {
			WeldJointDef weldDef = new WeldJointDef();
			weldDef.localAnchorA.set(jd.anchorA.toVector2());
			weldDef.localAnchorB.set(jd.anchorB.toVector2());
			weldDef.referenceAngle = 0;
			jointDef = weldDef;
		} else if (jd.type.equals("friction")) {
			FrictionJointDef frictionDef = new FrictionJointDef();
			frictionDef.localAnchorA.set(jd.anchorA.toVector2());
			frictionDef.localAnchorB.set(jd.anchorB.toVector2());
			frictionDef.maxForce = jd.maxForce.toFloat();
			frictionDef.maxTorque = jd.maxTorque.toFloat();
			jointDef = frictionDef;
		} else if (jd.type.equals("rope")) {
			RopeJointDef ropeDef = new RopeJointDef();
			ropeDef.localAnchorA.set(jd.anchorA.toVector2());
			ropeDef.localAnchorB.set(jd.anchorB.toVector2());
			ropeDef.maxLength = jd.maxLength.toFloat();
			jointDef = ropeDef;
		}

		Joint joint = null;
		if (jointDef != null) {

			jointDef.bodyA = bodys.get(bodyIndexA);
			jointDef.bodyB = bodys.get(bodyIndexB);
			jointDef.collideConnected = jd.collideConnected;

			joint = world.createJoint(jointDef);
			joints.add(joint);
			if (jd.type.equals("mouse")) {
				((MouseJoint) joint).setTarget(jd.target.toVector2());
			}
			if (jd.name != null) {
				// null
			}

		}
		return joint;
	}
}
