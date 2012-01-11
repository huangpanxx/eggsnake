package com.maple.eggsnake.stage.content;

import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.common.EnumDestStage;

public class DestinationStage {

	/**
	 * @description 获取导航需要的目标Stage
	 * @param screen
	 * @param stage
	 * @return
	 */
	public static BaseStage getDestnationStage(ContentScreen screen,EnumDestStage stage) 
	{
		BaseStage destStage = null;
		switch (stage) {
		case STARTMENUSTAGE: {
			destStage = new StartMenuStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case ABOUTUSSTAGE: {
			destStage = Box2DStage.getInstance(screen, 1);
			break;
		}
		case SETTINGSTAGE: {
			destStage = new SettingStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case GAMESTAGE: {
			destStage = Box2DStage.getInstance(screen, 1);
			break;
		}
		case SELECTLEVELSTAGE:{
			destStage = new SelectLevelStage(screen, screen.getWidth(), 
					screen.getHeight(), true);
			break;
		}
		case LEVELONESTAGE: {
			destStage = new LevelOneStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case LEVELTWOSTAGE: {
			destStage = new LevelTwoStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case LEVELTHREESTAGE: {
			destStage = new LevelThreeStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}

		case SUCCESSSTAGE: {
			destStage = new HighScoresStage(screen, true, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case FAILEDSTAGE: {
			destStage = new HighScoresStage(screen, true, screen.getWidth(),
					screen.getHeight(), false);
			break;
		}
		default: {
			destStage = new StartMenuStage(screen, screen.getWidth(),
					screen.getHeight(), true);
		}
	}
		return destStage;
	}
}
