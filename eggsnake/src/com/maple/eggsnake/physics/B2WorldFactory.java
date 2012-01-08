package com.maple.eggsnake.physics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;

public class B2WorldFactory {
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
		logger.log("物理世界重力:%1$f,%2$f", gravity.x, gravity.y);
		World world = new World(gravity, false);
		world.setAutoClearForces(w.autoClearForces);
		world.setWarmStarting(w.warmStarting);
		world.setContinuousPhysics(w.continuousPhysics);
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
		
		//添加 Joints
		
		return world;
	}
}
