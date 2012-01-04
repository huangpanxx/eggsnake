/** 
 * @description	: simplify the operator for proxy processor
 * @author		: 黄攀
 * @created		: 2012-1-4
 */

package com.maple.eggsnake.screen;

import com.badlogic.gdx.InputProcessor;

abstract class InputProxyScreen extends SimpleScreen implements InputProcessor{

	private InputProcessor processor;
	
	public InputProxyScreen(){
		
	}
	
	@Override
	public boolean keyDown(int keyCode) {
		if (getProcessor() != null)
			return getProcessor().keyDown(keyCode);
		else
			return false;
	}

	@Override
	public boolean keyTyped(char keyCode) {
		if (getProcessor() != null)
			return getProcessor().keyTyped(keyCode);
		else
			return false;
	}

	@Override
	public boolean keyUp(int keyCode) {
		if (getProcessor() != null)
			return getProcessor().keyUp(keyCode);
		else
			return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (getProcessor() != null)
			return getProcessor().scrolled(amount);
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (getProcessor() != null)
			return getProcessor().touchDown(x, y, pointer, button);
		else
			return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		if (getProcessor() != null)
			return getProcessor().touchDragged(arg0, arg1, arg2);
		else
			return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {

		if (getProcessor() != null)
			return getProcessor().touchMoved(x, y);
		else
			return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {

		if (getProcessor() != null)
			return getProcessor().touchUp(x, y, pointer, button);
		return false;
	}
	
	public InputProcessor getProcessor() {
		return processor;
	}
	
	public void setProcessor(InputProcessor processor) {
		this.processor = processor;
	}

}
