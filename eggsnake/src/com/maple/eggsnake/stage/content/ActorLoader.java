package com.maple.eggsnake.stage.content;

import com.maple.eggsnake.screen.ContentScreen;

public interface ActorLoader {
	public void initContent(ContentScreen screen);
	public void loadTextures();
	public void load();
}
