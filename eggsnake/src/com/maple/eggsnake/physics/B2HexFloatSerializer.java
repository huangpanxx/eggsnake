package com.maple.eggsnake.physics;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class B2HexFloatSerializer implements JsonDeserializer<B2HexFloat> {

	@Override
	public B2HexFloat deserialize(JsonElement context, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		String val = context.toString();
		if (val.contains("\"")) {
			val = val.replace("\"", "").replace("/", "");
			return new B2HexFloat(this.parseFromHex(val));
		} else {
			return new B2HexFloat(Float.parseFloat(val));
		}
	}

	private float parseFromHex(String hex) {
		Long i = Long.parseLong(hex, 16);
		Float f = Float.intBitsToFloat(i.intValue());
		return f;
	}
}
