/* 
 * Description	: loggers' interface
 * Author		: 黄攀
 * Created		: 2012-1-3
 */

package com.maple.eggsnake.logger;

public interface Loggable {

	void log(String patten, Object... args);

	void log(int level, String patten, Object... args);
}
