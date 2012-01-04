/* 
 * Description	: desktop GameStarter
 * Author		: 黄攀
 * Created		: 2012-1-2
 */

package com.maple.eggsnake.desktop;

import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.maple.eggsnake.application.EggSnakeManager;
import com.maple.eggsnake.logger.ConsoleLogger;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;

public class DesktopGameStarter {
	/**
	 * 桌面程序启动器
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String args[]) throws Exception {
		// 设置日志方式
		DefaultLogger.setInnerLogger(new ConsoleLogger());
		DefaultLogger.setEnable(true);
		
		// 标记启动
		Loggable logger = DefaultLogger.getDefaultLogger();
		logger.log("Enter from desktop");
		
		// 获得管理器
		EggSnakeManager manager = EggSnakeManager.getInstance();
		// 启动管理器
		new JoglApplication(manager, "EggSnake", 480, 320, true);
		
	}
}
