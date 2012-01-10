package com.maple.eggsnake.util;

public class MathHelper {
	public static float getDistance(float x1, float y1, float x2, float y2) {
		return getDistance(x1 - x2, y1 - y2);
	}

	public static float getDistance(float x, float y) {
		return (float) Math.sqrt((double) (x * x) + (float) (y * y));
	}
}
