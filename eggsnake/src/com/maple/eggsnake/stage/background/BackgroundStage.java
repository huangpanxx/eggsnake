package com.maple.eggsnake.stage.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.event.EventAggregator;
import com.maple.eggsnake.event.RequestChangeBackgroundEvent;
import com.maple.eggsnake.event.EventListener;
import com.maple.eggsnake.event.RequstChangeBackgroundEventArg;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.screen.NavigateScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class BackgroundStage extends BaseStage implements
		EventListener<RequstChangeBackgroundEventArg> {

	Loggable logger = null;
	NavigateScreen screen = null;
	Image backgroundImage = null;
	String backgroundImageName = null;

	public BackgroundStage(NavigateScreen _screen, float width, float height,
			boolean stretch) {
		super(width, height, stretch);
		logger = DefaultLogger.getDefaultLogger();
		loadBackground("background_512_512.png");
		try {
			RequestChangeBackgroundEvent requestChangeBackgroundEvent = EventAggregator
					.getInstance().getEvent(RequestChangeBackgroundEvent.class);
			requestChangeBackgroundEvent.subscribe(this);
		} catch (Exception e) {
			logger.logWithSignature(this, "订阅RequestBackgroundEvent失败:%1$s",
					e.getMessage());
		}
	}

	private void loadBackground(String name) {
		//检测是否重复设置
		if (this.backgroundImage != null) {
			if (this.backgroundImageName.equals(name)) {
				return;
			}
		}
		//加载
		Texture texture = ResourceLoader.loadTexture(name);
		
		//若成功加载纹理则移除旧背景
		if (backgroundImage != null) {
			this.removeActor(backgroundImage);
			backgroundImage = null;
		}
		
		//加载成功后更新最近背景名字
		this.backgroundImageName = name;
		
		//创建新的背景
		backgroundImage = new Image("Quit", texture);
		backgroundImage.x = 0;
		backgroundImage.y = 0;
		this.addActor(backgroundImage);
	}

	public void hide() {
		// TODO Auto-generated method stub

	}

	public void pause() {
		// TODO Auto-generated method stub

	}

	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	public void resume() {
		// TODO Auto-generated method stub
	}

	public void show() {

	}

	@Override
	public void onEventReceived(RequstChangeBackgroundEventArg arg) {
		// TODO Auto-generated method stub
		try {
			this.loadBackground(arg.path);
		} catch (Exception e) {
			logger.logWithSignature(this, "背景切换请求失败:%1$s", arg.path);
		}

	}

}
