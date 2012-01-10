package com.maple.eggsnake.util;

import com.badlogic.gdx.math.Vector2;

public class MathHelper {
	public static float getDistance(float x1, float y1, float x2, float y2) {
		return getDistance(x1 - x2, y1 - y2);
	}

	public static float getDistance(float x, float y) {
		return (float) Math.sqrt((double) (x * x) + (float) (y * y));
	}

	public static float getDistance(Vector2 v1, Vector2 v2) {
		return getDistance(v1.x - v2.x, v1.y - v2.y);
	}
}
