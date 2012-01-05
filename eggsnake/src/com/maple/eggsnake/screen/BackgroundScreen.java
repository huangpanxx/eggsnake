package com.maple.eggsnake.screen;

import com.maple.eggsnake.stage.background.BackgroundStage;

public class BackgroundScreen extends NavigateScreen {
	public BackgroundScreen() {
		this.setLayer(ScreenLayer.BACKGROUDN);
		this.Navigate(new BackgroundStage(this, getWidth(), getHeight(),
				false));
	}
}
