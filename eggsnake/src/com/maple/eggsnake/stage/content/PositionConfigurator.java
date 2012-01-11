package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.math.Vector2;

public class PositionConfigurator {

	public static Vector2 configStagePosition(float xOffset, float yOffset){
		Vector2 position;
		try{
			position =  new Vector2(GravityPosision.gravity.x + xOffset,
				GravityPosision.gravity.y + yOffset);
		}catch(Exception e){
			e.printStackTrace();
			position = new Vector2(0f, 0f);
		}
		return position;
	}
}
