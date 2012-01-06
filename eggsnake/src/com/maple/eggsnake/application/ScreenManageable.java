/** 
 * @description	: Game Manager interface
 * @author		: 黄攀
 * @created		: 2012-1-3
 */

package com.maple.eggsnake.application;

import com.maple.eggsnake.screen.ProcessableScreen;

public interface ScreenManageable {

	void navigate(ProcessableScreen screen);
}
