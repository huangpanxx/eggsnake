package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DRender {
	
	private World gameWorld;
	public Box2DRender(World world){
		this.gameWorld = world;
	}

	private void addTextures(){
		while(this.gameWorld.getBodies().hasNext()){
			this.doAddTexture(this.gameWorld.getBodies().next());
		}
	}
	
	private void doAddTexture(Body body){
		
	}
}
