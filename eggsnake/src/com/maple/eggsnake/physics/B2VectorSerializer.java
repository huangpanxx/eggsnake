package com.maple.eggsnake.physics;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class B2VectorSerializer implements JsonDeserializer<B2Vector> {

	@Override
	public B2Vector deserialize(JsonElement element, Type type,
			JsonDeserializationContext context) throws JsonParseException {
		String val = element.toString();

		if (val.matches("\\{.*\\}")) {
			String[] strs = val.split(",");
			return new B2Vector(this.toFloat(strs[0]), this.toFloat(strs[1]));
		} else if (val.matches("\\d*")) {
			return new B2Vector(Float.parseFloat(val), 0);
		}
		System.out.println("解析Vector失败:" + val);
		return new B2Vector(0, 0);
	}

	private float toFloat(String s) {
		s = s.split(":")[1].trim();
		s = s.replace("{", "").replace("}", "");
		if (s.contains("\"")) {
			s = s.replace("\"", "");
			return parseFromHex(s);
		} else {
			return Float.parseFloat(s);
		}
	}

	private float parseFromHex(String hex) {
		Long i = Long.parseLong(hex, 16);
		Float f = Float.intBitsToFloat(i.intValue());
		return f;
	}
}
