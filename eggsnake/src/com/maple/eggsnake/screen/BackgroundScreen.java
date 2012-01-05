package com.maple.eggsnake.screen;

import com.maple.eggsnake.stage.BackgroundStageTest;

public class BackgroundScreen extends NavigateScreen {
	public BackgroundScreen() {
		this.setLayer(ScreenLayer.BACKGROUDN);
		this.Navigate(new BackgroundStageTest(this, getWidth(), getHeight(),
				false));
	}
}
