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

public class CircleMouseActor extends BodyAttachedActor {

	Loggable logger = null;
	CircleShape shape = null;
	Body body = null;
	Texture texture;
	SpriteBatch batch;
	Sprite sprite;

	public static boolean isCircleMouse(Body body) {
		String name = (String) body.getUserData();
		Fixture fixture = null;
		if (name != null) {
			if (name.startsWith("CircleMouse_")) {
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
			if (name.startsWith("CircleMouse_")) {
				for (Fixture f : body.getFixtureList()) {
					if (f.getShape() instanceof CircleShape) {
						return (CircleShape) f.getShape();
					}
				}
			}
		}
		return null;
	}

	public CircleMouseActor(Box2DStage stage, Body body) {
		super(stage);
		logger = DefaultLogger.getDefaultLogger();
		if (!CircleMouseActor.isCircleMouse(body)) {
			logger.logWithSignature(this, "不是老鼠");
		} else {
			this.batch = new SpriteBatch();

			this.body = body;
			this.shape = this.getCircleShape(body);
			String m = (String) body.getUserData();
			m = m.substring("CircleMouse_".length());
			this.texture = ResourceLoader.loadTexture(m);
			this.sprite = new Sprite(texture);
			this.sprite.setRegionX(4);
			this.sprite.setRegionY(-20);
		}
	}

	@Override
	public void draw(SpriteBatch spriteBacth, float dt) {

		try {
			this.batch.setProjectionMatrix(this.stage.getCamera().combined);
			float r = this.shape.getRadius();
			Vector3 pos = new Vector3(body.getPosition().x - r,
					body.getPosition().y - r, 0);

			this.batch.begin();
			this.sprite.rotate(this.body.getAngle());

			this.batch.draw(this.sprite, pos.x * B2Const.CONVERTRATIO, pos.y
					* B2Const.CONVERTRATIO-40);

			this.batch.end();

		} catch (Exception e) {
			this.stage.removeActor(this);
		}
	}

}
