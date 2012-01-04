/** 
 * @description	: the start screen
 * @author		: 黄攀
 * @created		: 2012-1-3
 */

package com.maple.eggsnake.screen;

import com.maple.eggsnake.application.ScreenManageable;
import com.maple.eggsnake.stage.StartMenuStage;

public class MainScreen extends ScreenBase {

	public MainScreen(ScreenManageable manager) {
		stage = new StartMenuStage(this, this.getWidth(), this.getHeight(),
				true);
		this.Navigate(stage);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}
}
