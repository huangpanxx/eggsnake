package com.maple.eggsnake.actor.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.physics.B2Const;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.content.Box2DStage;

public class CircleWoodActor extends BodyAttachedActor {

	Loggable logger = null;
	CircleShape shape = null;
	Body body = null;
	Texture texture;
	SpriteBatch batch;
	Sprite sprite;

	public static boolean isCircleWood(Body body) {
		String name = (String) body.getUserData();
		Fixture fixture = null;
		if (name != null) {
			if (name.startsWith("CircleWood_")) {
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
			if (name.startsWith("CircleWood_")) {
				for (Fixture f : body.getFixtureList()) {
					if (f.getShape() instanceof CircleShape) {
						return (CircleShape) f.getShape();
					}
				}
			}
		}
		return null;
	}

	public CircleWoodActor(Box2DStage stage, Body body) {
		super(stage);
		logger = DefaultLogger.getDefaultLogger();
		if (!CircleWoodActor.isCircleWood(body)) {
			logger.logWithSignature(this, "不是圆型木头");

		} else {
			this.batch = new SpriteBatch();
			this.body = body;
			this.shape = this.getCircleShape(body);
			String m = (String) body.getUserData();
			m = m.substring("CircleWood_".length());
			this.texture = ResourceLoader.loadTexture(m);
			this.sprite = new Sprite(texture);
		}
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float dt) {

		batch.begin();
		try {
			this.batch.setProjectionMatrix(this.stage.getCamera().combined);
			float r = this.shape.getRadius();
			
			Vector3 pos = new Vector3(body.getPosition().x  - 2*r,
					body.getPosition().y - 2*r , 0);
			sprite.setPosition(pos.x * B2Const.CONVERTRATIO, pos.y
					* B2Const.CONVERTRATIO);
			sprite.setRotation((float) (body.getAngle() * 180 / 3.14));
			sprite.draw(this.batch);

		} catch (Exception e) {
			this.stage.removeActor(this);
		}
		batch.end();
	}

}
