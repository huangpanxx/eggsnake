/** 
 * @description	: PositionConfigurator
 * @author		: 王志伟
 * @created		: 2012-1-10
 */

package com.maple.eggsnake.stage.content.attachtexture;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.maple.eggsnake.logical.WorldController;
import com.maple.eggsnake.physics.B2Const;

public class PositionConfigurator {

	private static float radius;
	private static String name;
	private static Iterator<Body> iterator;
	private static Body body;
	private static Vector2 gravity = new Vector2();
	private static Vector2 lowerLeft = new Vector2();
	private static Vector2 topRight = new Vector2();
	private static ArrayList<Fixture> fixtures;

	/**
	 * 
	 * @param controller
	 */
	public static void configure(WorldController controller) {
		setIterator(controller);
		while (iterator.hasNext()) {
			setBody();
			setBodyName();
			setFixtures();
			setGravityCenter();
			setPosition();
		}
	}

	/**
	 * 
	 * @param controller
	 */
	private static void setIterator(WorldController controller) {
		iterator = controller.getWorld().getBodies();
		System.out.println("iterator: " + iterator.toString());
	}

	private static void setBody() {
		body = iterator.next();
		System.out.println("body: " + body.toString());
	}

	private static void setBodyName() {
		name = (String) body.getUserData();
		System.out.println("name: " + name);
	}

	private static void setFixtures() {
		fixtures = body.getFixtureList();
	    System.out.println("fixtures: " + fixtures.toString());
	}

	private static void setGravityCenter() {
		gravity = new Vector2(body.getPosition().x, body.getPosition().y);
		body.getWorldPoint(gravity);
		gravity.x *= B2Const.CONVERTRATIO;
		gravity.y *= B2Const.CONVERTRATIO;
	}

	private static void setPosition() {
		if (!fixtures.isEmpty()) {
			if (fixtures.get(0).getShape().getType() == Shape.Type.Circle)
				configureCircleBody();
			if (fixtures.get(0).getShape().getType() == Shape.Type.Polygon)
				configurePolygon();
		}
	}

	private static void configureCircleBody() {
		radius = fixtures.get(0).getShape().getRadius();
		CircleBodyPosition.getInstance().setCircleBodyPosition(name, gravity.x,
				gravity.y, radius * B2Const.CONVERTRATIO);
	}

	private static void configurePolygon() {
		PolygonShape polygon = (PolygonShape) fixtures.get(0).getShape();
		polygon.getVertex(0, lowerLeft);
		body.getWorldPoint(lowerLeft);
		lowerLeft.x = lowerLeft.x * B2Const.CONVERTRATIO + gravity.x;
		lowerLeft.y = lowerLeft.y * B2Const.CONVERTRATIO + gravity.y;
		polygon.getVertex(2, topRight);
		body.getWorldPoint(topRight);
		topRight.x = topRight.x * B2Const.CONVERTRATIO + gravity.x;
		topRight.y = topRight.y * B2Const.CONVERTRATIO + gravity.y;
		RectangleBodyPosition.getInstance().setRectangleBodyPosition(name,
				lowerLeft, topRight);
	}
}
