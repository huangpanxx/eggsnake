/* 
 * Description	: Android Game Starter
 * Author		: 黄攀
 * Created		: 2012-1-2
 */

package com.maple.eggsnake.android;

import android.content.pm.ActivityInfo;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
//import com.maple.eggsnake.android.logger.AndroidFileLogger;
import com.maple.eggsnake.application.EggSnakeManager;
import com.maple.eggsnake.logger.ConsoleLogger;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.service.ApplicationService;

public class AndroidEggSnakeStarter extends AndroidApplication {
	public void onCreate(android.os.Bundle savedInstance) {
		// 父类初始化
		super.onCreate(savedInstance);

		// 设置横屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		// 设置日志方式
		DefaultLogger.setInnerLogger(new ConsoleLogger());
		DefaultLogger.setEnable(true);

		// 开始启动
		Loggable logger = DefaultLogger.getDefaultLogger();
		logger.log("Enter from Android");
		
		// 配置环境
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = false;
		//设置Application
		com.maple.eggsnake.service.Application app =  new com.maple.eggsnake.android.service.AndroidApplication(this);
		ApplicationService.setApplication(app);

		// 获得管理器
		EggSnakeManager manager = EggSnakeManager.getInstance();
		logger.log("启动管理器");
		// 启动管理器
		initialize(manager, config);
	}
}
