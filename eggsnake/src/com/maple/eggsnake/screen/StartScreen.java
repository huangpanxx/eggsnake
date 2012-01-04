/* 
 * Description	: the start screen
 * Author		: 黄攀
 * Created		: 2012-1-3
 */

package com.maple.eggsnake.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.maple.eggsnake.application.ScreenManageable;

public class StartScreen extends SimpleScreen {
	
	Stage stage;
	Camera camera;
	public StartScreen(ScreenManageable manager) {
		camera = new OrthographicCamera(this.getWidth(),this.getHeight());
		stage = new Stage(this.getWidth(),this.getHeight(),false);
		stage.setCamera(camera);
//		Actor actor = new Button(Button.ButtonStyle.);//Label(Language.translate("eggsnake"), new BitmapFont());
//		stage.addActor(actor);
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
	public void render(float dt) {
		stage.act(dt);
		stage.draw();
	}

	@Override
	public void resize(int width, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}
