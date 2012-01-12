/** 
 * @description	: StartMenuStage继承自BaseStage
 * @author		: 王志伟
 * @created		: 2012-1-8
 */

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
	public static BaseStage getDestnationStage(ContentScreen screen,
			EnumDestStage stage) {
		BaseStage destStage = null;
		switch (stage) {
		case STARTMENUSTAGE: {
			destStage = new StartMenuStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case ABOUTUSSTAGE: {
			destStage = new AboutUsStage(screen, screen.getWidth(),
					screen.getHeight(), false);
			break;
		}
		case SETTINGSTAGE: {
			destStage = new SettingStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case SELECTLEVELSTAGE: {
			destStage = new SelectLevelStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case REPLAYSTAGE: {
			try {
				destStage = Box2DStage
						.getInstance(screen, CurrentLevel.current);
			} catch (Exception e) {

			}
			break;
		}
		case NEXTLEVELSTAGE: {
			int next = CurrentLevel.current + 1;
			if (5 < next)
				next = next % 6;
			CurrentLevel.current = next;
			destStage = Box2DStage.getInstance(screen, next);
			break;
		}
		case LEVELONESTAGE: {
			destStage = Box2DStage.getInstance(screen, 0);
			CurrentLevel.current = 0;
			break;
		}
		case LEVELTWOSTAGE: {
			destStage = Box2DStage.getInstance(screen, 1);
			CurrentLevel.current = 1;
			break;
		}
		case LEVELTHREESTAGE: {
			destStage = Box2DStage.getInstance(screen, 2);
			CurrentLevel.current = 2;
			break;
		}
		case LEVELFOURSTAGE: {
			destStage = Box2DStage.getInstance(screen, 3);
			CurrentLevel.current = 3;
			break;
		}
		case LEVELFIVESTAGE: {
			destStage = Box2DStage.getInstance(screen, 4);
			CurrentLevel.current = 4;
			break;
		}
		case LEVELSIXSTAGE: {
			destStage = Box2DStage.getInstance(screen, 5);
			CurrentLevel.current = 5;
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
