/* 
 * Description	: ApplicationListener witch is used to manage events and resources
 * Author		: 黄攀
 * Created		: 2012-1-2
 */


package com.maple.eggsnake;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameListener implements ApplicationListener {

	SpriteBatch batch;
	Texture texture;
	@Override
	public void create() {
		batch = new SpriteBatch();
		FileHandle handle = Gdx.files.internal("images/test01.png");
		texture = new Texture(handle);
		
		// TODO Auto-generated method stub
		
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
		batch.begin();
		batch.draw(texture,0f,0f);
		batch.end();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
