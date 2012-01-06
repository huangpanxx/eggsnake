package com.maple.eggsnake.screen;

import com.maple.eggsnake.stage.background.BackgroundStage;

public class BackgroundScreen extends NavigateScreen {
	public BackgroundScreen() {
		this.setLayer(ScreenLayer.BACKGROUDN);
		this.navigate(new BackgroundStage(this, getWidth(), getHeight(),
				false));
	}
}
