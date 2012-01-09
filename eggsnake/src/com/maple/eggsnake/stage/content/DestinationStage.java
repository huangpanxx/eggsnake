package com.maple.eggsnake.stage.content;

import com.maple.eggsnake.eggenum.EnumStage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.stage.BaseStage;

public class DestinationStage {

	public static BaseStage getDestnationStage(ContentScreen screen,
			EnumStage stage) {
		BaseStage destStage;
		switch (stage) {
		case STARTMENUSTAGE: {
			destStage = new StartMenuStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case ABOUTUSSTAGE: {
			destStage = new AboutUsStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case SETTINGSTAGE: {
			destStage = new SettingStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case GAMESTAGE: {
			destStage = new LoveYouStage( screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		default: {
			destStage = new StartMenuStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		}
		return destStage;
	}	
}
