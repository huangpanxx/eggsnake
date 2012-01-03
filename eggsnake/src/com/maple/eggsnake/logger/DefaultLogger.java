/* 
 * Description	: default logger getter and setter, thread not safety!
 * Author		: 黄攀
 * Created		: 2012-1-3
 */

package com.maple.eggsnake.logger;

public class DefaultLogger implements Loggable {

	private static DefaultLogger defaultLogger;

	public static Loggable getDefaultLogger() {
		if (defaultLogger == null)
			defaultLogger = new DefaultLogger();
		return defaultLogger;
	}

	private static Loggable innerLogger;

	public static Loggable getInnerLogger() {
		return innerLogger;
	}

	public static void setInnerLogger(Loggable logger) {
		innerLogger = logger;
	}

	private DefaultLogger() {

	}

	@Override
	public void log(String patten, Object... args) {
		Loggable logger = DefaultLogger.getInnerLogger();
		if (logger != null)
			logger.log(patten, args);
	}

	@Override
	public void log(int level, String patten, Object... args) {
		Loggable logger = DefaultLogger.getInnerLogger();
		if (logger != null)
			logger.log(level, patten, args);
	}

	public void log(String msg) {
		Loggable logger = DefaultLogger.getInnerLogger();
		if (logger != null)
			logger.log(msg);
	}
}
