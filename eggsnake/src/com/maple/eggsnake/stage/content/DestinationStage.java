package com.maple.eggsnake.stage.content;

import com.maple.eggsnake.eggenum.EnumLevelStage;
import com.maple.eggsnake.eggenum.EnumMainStage;
import com.maple.eggsnake.eggenum.EnumPassStage;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.stage.BaseStage;

public class DestinationStage {

	/**
	 * @description 获取导航需要的目标主Stage
	 * @param screen
	 * @param stage
	 * @return
	 */
	public static BaseStage getDestnationStage(ContentScreen screen,
			EnumMainStage stage) {
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
	
	/**
	 * @description 重载函数，获得对应关卡的Stage。
	 * @param screen
	 * @param stage
	 * @return
	 */
	public static BaseStage getDestnationStage(ContentScreen screen,
			EnumLevelStage stage) {
		BaseStage destStage;
		switch (stage) {
		case LEVELONE:
		{
			destStage = new LevelOneStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case LEVELTWO:
		{
			destStage = new LevelTwoStage(screen, screen.getWidth(),
					screen.getHeight(), true);
			break;
		}
		case LEVELTHREE:
		{
			destStage = new LevelThreeStage(screen, screen.getWidth(),
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
	
	/**
	 * description 重载函数，获取显示过关成功与否的Stage
	 * @param screen
	 * @param stage
	 * @return
	 */
	public static BaseStage getDestnationStage(ContentScreen screen,
			EnumPassStage stage) {
		BaseStage destStage;
		switch (stage) {
		case SUCCESS:
		{
			destStage = new HighScoresStage(screen, true, screen.getWidth(), 
					screen.getHeight(), true);
			break;
		}
		case FAILED:
		{
			destStage = new HighScoresStage(screen, true, screen.getWidth(),
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
