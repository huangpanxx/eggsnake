package com.maple.eggsnake.util;

public class Path {
	public static String combine(String base, String relative) {
		if (base.endsWith("/") || base.endsWith("\\")) {
		} else {
			base = base + "/";
		}
		return base + relative;
	}
}
