package com.maple.eggsnake.actor.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.physics.B2Const;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.content.Box2DStage;

public class CircleSnakeActor extends BodyAttachedActor {

	Loggable logger = null;
	CircleShape shape = null;
	Body body = null;
	Texture texture;
	SpriteBatch batch;
	Sprite sprite;

	public static boolean isCircleSnake(Body body) {
		String name = (String) body.getUserData();
		Fixture fixture = null;
		if (name != null) {
			if (name.startsWith("CircleSnake_")) {
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

	public CircleShape getCircleShape(Body body) {
		String name = (String) body.getUserData();
		if (name != null) {
			if (name.startsWith("CircleSnake_")) {
				for (Fixture f : body.getFixtureList()) {
					if (f.getShape() instanceof CircleShape) {
						return (CircleShape) f.getShape();
					}
				}
			}
		}
		return null;
	}

	public CircleSnakeActor(Box2DStage stage, Body body) {
		super(stage);
		logger = DefaultLogger.getDefaultLogger();
		if (!CircleSnakeActor.isCircleSnake(body)) {
			logger.logWithSignature(this, "不是蛇");
		} else {
			this.batch = new SpriteBatch();

			this.body = body;
			this.shape = this.getCircleShape(body);
			String m = (String) body.getUserData();
			m = m.substring("CircleSnake_".length());
			this.texture = ResourceLoader.loadTexture(m);
			this.sprite = new Sprite(texture);
		}
	}

	@Override
	public void draw(SpriteBatch spriteBacth, float dt) {

		this.batch.begin();
		try {

			float r = this.shape.getRadius();
			Vector3 pos = new Vector3(body.getPosition().x - r,
					body.getPosition().y - r, 0);
			float angle = this.body.getAngle();
			this.sprite.setRotation((float) (angle * 180 / 3.14));
			this.sprite.setPosition(pos.x * B2Const.CONVERTRATIO - 5, pos.y
					* B2Const.CONVERTRATIO-28);
			this.sprite.draw(batch);

		} catch (Exception e) {
			this.stage.removeActor(this);
		}

		this.batch.end();
	}

}
