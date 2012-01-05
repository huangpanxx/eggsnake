/** 
 * @description	: the content layer of the screen
 * @author		: 黄攀
 * @created		: 2012-1-4
 */



package com.maple.eggsnake.screen;

import com.maple.eggsnake.stage.ContentStageTest;

public class ContentScreen extends NavigateScreen{

	public ContentScreen(){
		this.setLayer(ScreenLayer.CONTENT);
		this.Navigate(new ContentStageTest(this, getWidth(), getHeight(), false));
	}
}
