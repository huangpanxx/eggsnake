package com.maple.eggsnake.actor.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.maple.eggsnake.stage.content.Box2DStage;

public class ActorFactory {

	public static BodyAttachedActor createActor(Body body, Box2DStage stage) {
		// 这里暂时使用代码绑定，后期使用反射实现
		// Loggable logger = DefaultLogger.getDefaultLogger();
		// BodyAttachedActor actor = null;
		if (CircleSnakeActor.isCircleSnake(body)) {
			return new CircleSnakeActor(stage, body);
		}

		if (CircleMouseActor.isCircleMouse(body)) {
			return new CircleMouseActor(stage, body);
		}
		if (CircleWoodActor.isCircleWood(body)) {
			return new CircleWoodActor(stage, body);
		}
		if(SqureWoodActor.isSqureWood(body)){
			return new SqureWoodActor(stage, body);
		}
		return null;
	}
}
