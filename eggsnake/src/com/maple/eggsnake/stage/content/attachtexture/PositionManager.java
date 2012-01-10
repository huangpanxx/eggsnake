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

public class PositionManager {
	
	private static float radius;

	public static void setPosition(WorldController controller){
			Iterator<Body> iterator;
			Body body;
			iterator = controller.getWorld().getBodies();
			String userData;
			while(iterator.hasNext()){
				body = iterator.next();
				userData = (String)body.getUserData();
				Vector2 center = new Vector2(body.getPosition().x, 
						body.getPosition().y);
				ArrayList<Fixture> fixtures = body.getFixtureList();
				body.getWorldPoint(center);
				
				center.x *= B2Const.CONVERTRATIO;
				center.y *= B2Const.CONVERTRATIO;	
				Vector2 lowerLeft = new Vector2();
				Vector2 topRight = new Vector2();
				if(!fixtures.isEmpty()){
					if(fixtures.get(0).getShape().getType() == Shape.Type.Circle){
						radius = fixtures.get(0).getShape().getRadius();
						CircleBodyPosition.getInstance().setCircleBodyPosition(userData,
								center.x, center.y, radius * B2Const.CONVERTRATIO);
					}
					if(fixtures.get(0).getShape().getType() == Shape.Type.Polygon){
						PolygonShape polygon = (PolygonShape)fixtures.get(0).getShape();
						polygon.getVertex(0, lowerLeft);
						body.getWorldPoint(lowerLeft);
						lowerLeft.x = lowerLeft.x * B2Const.CONVERTRATIO + center.x;
						lowerLeft.y = lowerLeft.y * B2Const.CONVERTRATIO + center.y;
						polygon.getVertex(2, topRight);
						body.getWorldPoint(topRight);
						topRight.x = topRight.x * B2Const.CONVERTRATIO + center.x;
						topRight.y = topRight.y * B2Const.CONVERTRATIO + center.y;
						RectangleBodyPosition.getInstance().setRectangleBodyPosition(userData,
								lowerLeft, topRight);
					}
				}
			}		
	}
}
