package com.maple.eggsnake.screen;

import com.maple.eggsnake.stage.foreground.ForegroundStage;

public class ForegroundScreen extends NavigateScreen{
	public ForegroundScreen(){
		this.setLayer(ScreenLayer.FOREGROUND);
		this.Navigate(new ForegroundStage(getWidth(), getHeight(), false));
	}

}
