package com.maple.eggsnake.physics;

public class B2HexFloat {

	public B2HexFloat() {
		this.value = 0;
	}

	public B2HexFloat(float parseFromHex) {
		this.value = parseFromHex;
	}

	public float value;

	public float toFloat() {
		return value;
	}
}