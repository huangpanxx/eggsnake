/* 
 * Description	: the base class for screen
 * Author		: 黄攀
 * Created		: 2012-1-3
 */


package com.maple.eggsnake.screen;

import com.badlogic.gdx.Screen;
import com.maple.eggsnake.application.ScreenManageable;

public abstract class ScreenBase implements Screen {
	public abstract void requestEnter(ScreenManageable manager);
	public abstract void requestExit(ScreenManageable manager);
	
}
