/** 
 * @description	: log for debug console 
 * @author		: 黄攀
 * @created		: 2012-1-3
 */

package com.maple.eggsnake.logger;

import com.sun.opengl.impl.Debug;

public class DebugLogger implements Loggable {

	@Override
	public void log(String patten, Object... args) {
		log(0, patten, args);
	}

	@Override
	public void log(int level, String patten, Object... args) {
		String msg = String.format(patten, args);
		String out = String.format("Level %1$d\t:%2$s", level, msg);
		Debug.debug(out);
	}
}
