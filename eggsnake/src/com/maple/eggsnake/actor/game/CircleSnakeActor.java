package com.maple.eggsnake.actor.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.content.Box2DStage;
import com.maple.eggsnake.stage.content.attachtexture.CircleBody;

public class CircleSnakeActor extends BodyAttachedActor {

	Loggable logger = null;
	CircleBody body = null;
	Texture texture;

	public static boolean IsCircleSnake(Body body) {
		String name = (String) body.getUserData();
		Fixture fixture = null;
		if (name != null) {
			if ("CircleSnake".equals(name)) {
				for (Fixture f : body.getFixtureList()) {
					if (f.getShape() instanceof CircleShape) {
						fixture = f;
						break;
					}
				}
			}
		}
		return fixture != null;
	}

	public CircleSnakeActor(Box2DStage stage, Body body) {
		super(stage);
		logger = DefaultLogger.getDefaultLogger();
		if (!CircleSnakeActor.IsCircleSnake(body)) {
			logger.logWithSignature(this, "不是蛇");
		} else {
			this.body = (CircleBody) this.body;
			this.texture = ResourceLoader.loadTexture("eggSnake_64_128.png");
		}
	}

	@Override
	public void draw(SpriteBatch spriteBacth, float dt) {
		try {
			spriteBacth.begin();
			float r = body.getRadius();
			Vector3 pos = new Vector3(body.getX() - r, body.getY() - r, 0);
			this.stage.getCamera().project(pos);
			spriteBacth.draw(this.texture, pos.x, pos.y);
			spriteBacth.end();
		} catch (Exception e) {
			this.stage.removeActor(this);
		}
	}

}
