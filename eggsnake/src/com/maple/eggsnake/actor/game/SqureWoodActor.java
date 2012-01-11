package com.maple.eggsnake.actor.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.physics.B2Const;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.content.Box2DStage;
import com.maple.eggsnake.util.MathHelper;

public class SqureWoodActor extends BodyAttachedActor {

	public static boolean isSqureWood(Body body) {
		String name = (String) body.getUserData();
		Fixture fixture = null;
		if (name != null) {
			if (name.startsWith("SqureWood_")) {
				for (Fixture f : body.getFixtureList()) {
					if (f.getShape() instanceof PolygonShape) {
						fixture = f;
						break;
					}
				}
			}
		}
		return fixture != null;
	}

	private PolygonShape getPolygonShape(Body body) {
		String name = (String) body.getUserData();
		if (name != null) {
			if (name.startsWith("SqureWood_")) {
				for (Fixture f : body.getFixtureList()) {
					if (f.getShape() instanceof PolygonShape) {
						return (PolygonShape) f.getShape();
					}
				}
			}
		}
		return null;
	}

	PolygonShape shape = null;
	Body body = null;
	Loggable logger = null;
	Texture texture;
	SpriteBatch batch;
	float width = 0;
	float height = 0;
	float angle = 0;
	Sprite sprite;

	public SqureWoodActor(Box2DStage stage, Body body) {
		super(stage);
		logger = DefaultLogger.getDefaultLogger();
		this.shape = this.getPolygonShape(body);
		if (shape == null) {
			logger.logWithSignature(this, "不是SqureShape");
		} else {
			this.batch = new SpriteBatch();
			this.body = body;

			Vector2 v0 = new Vector2();
			shape.getVertex(0, v0);
			Vector2 v1 = new Vector2();
			shape.getVertex(1, v1);
			Vector2 v2 = new Vector2();
			shape.getVertex(2, v2);
			Vector2 v3 = new Vector2();
			shape.getVertex(3, v3);

			width = MathHelper.getDistance(v1, v2) * B2Const.CONVERTRATIO;
			height = MathHelper.getDistance(v1, v0) * B2Const.CONVERTRATIO;
			String m = (String) body.getUserData();
					
			m = m.substring("SqureWood_".length());
			this.texture= ResourceLoader.loadTexture(m);//, 0, 0,
//					width, height);
			TextureRegion region = new TextureRegion(texture,0,0,(int)(width),(int)(height));//width,height);
			this.sprite = new Sprite(region);
//			this.sprite.setRegionWidth();//(int) width);
//			this.sprite.setRegionHeight(10);//(int) height);
			this.angle = (float) (MathUtils.atan2(v2.y - v1.y,v2.x - v1.x) * 180 / 3.14);

		}
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float dt) {
		this.batch.begin();
		try {
			float r = this.shape.getRadius();
			Vector3 pos = new Vector3(body.getPosition().x - r,
					body.getPosition().y - r, 0);
			float a = (float) (this.body.getAngle() * 180 / 3.14 + angle);
			
			sprite.setPosition(pos.x * B2Const.CONVERTRATIO - width
					/ 2, pos.y * B2Const.CONVERTRATIO - height / 2);
			
			sprite.setRotation(a);
			
			sprite.draw(this.batch);
			

		} catch (Exception e) {
			this.stage.removeActor(this);
		}
		this.batch.end();

	}

}
