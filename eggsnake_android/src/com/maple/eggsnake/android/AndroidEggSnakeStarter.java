/* 
 * Description	: Android Game Starter
 * Author		: 黄攀
 * Created		: 2012-1-2
 */

package com.maple.eggsnake.android;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.maple.eggsnake.application.EggSnakeManager;
import com.maple.eggsnake.logger.DebugLogger;
import com.maple.eggsnake.logger.DefaultLogger;

public class AndroidEggSnakeStarter extends AndroidApplication {
	public void onCreate(android.os.Bundle savedInstance) {
		// 父类初始化
		super.onCreate(savedInstance);

		// 设置日志方式
		DefaultLogger.setInnerLogger(new DebugLogger());

		// 开始启动
		DefaultLogger.getDefaultLogger().log("Enter from Android");

		// 配置环境
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = true;

		// 获得管理器
		EggSnakeManager manager = EggSnakeManager.getInstance();

		// 启动管理器
		initialize(manager, config);
	}
}
