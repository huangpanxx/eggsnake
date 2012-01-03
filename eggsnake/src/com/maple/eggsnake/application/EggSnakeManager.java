/* 
 * Description	: Game Manager, Singleton mode
 * Author		: 黄攀
 * Created		: 2012-1-2
 */

package com.maple.eggsnake.application;

import java.util.Stack;

import com.badlogic.gdx.ApplicationListener;
import com.maple.eggsnake.screen.ScreenBase;
import com.maple.eggsnake.screen.StartScreen;

public class EggSnakeManager implements ApplicationListener, ScreenManageable {

	// The only instance
	private static EggSnakeManager instance = null;

	public static EggSnakeManager getInstance() {
		if (instance == null)
			instance = new EggSnakeManager();
		return instance;
	}

	private ScreenBase currentScreen = null;
	private Stack<ScreenBase> Screens = new Stack<ScreenBase>();

	// Singleton Mode
	private EggSnakeManager() {

	}

	@Override
	public void create() {
		this.requestNavigate(new StartScreen());
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/**
	 * 场景进入完毕
	 */
	@Override
	public void enterScreenFinished(ScreenBase screen) {
		// TODO Auto-generated method stub
	}

	/**
	 * 场景退出完毕回调函数
	 */
	@Override
	public void exitScreenFinished(ScreenBase screen) {
		// 是否有等待进入场景
		if (this.Screens.empty()) {
			this.currentScreen = null;
		} else {
			// 存在则请求进入
			requestEnterScreen(this.Screens.pop());
		}
	}

	/**
	 * 用于切换场景,如果存在场景正在运行,则通知其退出,然后切换新场景
	 * 
	 * @see com.maple.eggsnake.application.ScreenManageable#requestNavigate(com.maple
	 *      .eggsnake.screen.ScreenBase)
	 */
	@Override
	public void requestNavigate(ScreenBase screen) {
		if (this.currentScreen != null) {
			// 若存在场景,保存正在请求场景并通知当前场景退出
			addScreenToWaitingStack(screen);
			requestExitScreen(this.currentScreen);
		} else {
			// 直接请求进入
			requestEnterScreen(screen);
		}
	}

	/**
	 * 将场景加入等待栈中
	 * 
	 * @param screen
	 */
	private void addScreenToWaitingStack(ScreenBase screen) {
		Screens.push(screen);
	}

	/**
	 * 向场景发送通知,通知其进入
	 * 
	 * @param screen
	 */
	private void requestEnterScreen(ScreenBase screen) {
		this.currentScreen = screen;
		screen.requestEnter(this);
	}

	/**
	 * 向场景发送通知,通知其退出
	 * 
	 * @param screen
	 */
	private void requestExitScreen(ScreenBase screen) {
		screen.requestExit(this);
	}
}
