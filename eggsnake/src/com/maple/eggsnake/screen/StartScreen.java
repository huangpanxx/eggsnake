/* 
 * Description	: the start screen
 * Author		: 黄攀
 * Created		: 2012-1-3
 */

package com.maple.eggsnake.screen;

import com.maple.eggsnake.application.ScreenManageable;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;

public class StartScreen extends SimpleScreen{

	private ScreenManageable manager;
	Loggable logger;

	public StartScreen(ScreenManageable manager) {
		logger = DefaultLogger.getDefaultLogger();
		logger.log("StartScreen 构造函数");
		this.manager = manager;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
