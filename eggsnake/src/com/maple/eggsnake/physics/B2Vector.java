package com.maple.eggsnake.physics;
import com.badlogic.gdx.math.Vector2;

public class B2Vector {
	public B2Vector() {
		x = 0;
		y = 0;
	}

	public B2Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float x;
	public float y;

	public Vector2 toVector2() {
		return new Vector2(x, y);
	}

}
