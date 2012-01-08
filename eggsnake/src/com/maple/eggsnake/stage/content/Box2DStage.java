package com.maple.eggsnake.stage.content;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.maple.eggsnake.stage.BaseStage;

public class Box2DStage extends BaseStage {

	World world;
	Box2DDebugRenderer render;

	public Box2DStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		render = new Box2DDebugRenderer();
		world = new World(new Vector2(0,-9.8f), true);
		createBody();
	}

	private void createBody() {
		
        BodyDef bd = new BodyDef(); //声明物体定义 
        bd.position.set(24f, 16f); 
        bd.type=BodyType.DynamicBody; 
        Body b = world.createBody(bd); //通过world创建一个物体 
        CircleShape c = new CircleShape(); //创建一个形状（圆） 
        c.setRadius(1f); //设置半径 
        FixtureDef fd = new FixtureDef();
        fd.shape = c;
        fd.density = 1.0f;
        fd.friction = 1.0f;
        fd.restitution = 1.0f;
        Fixture f = b.createFixture(fd); //将形状和密度赋给物体 
        b.setUserData("Ball");
        f.setUserData("BallFixture");
        
        BodyDef rectbd = new BodyDef();
        rectbd.position.set(0,0);
        rectbd.type = BodyType.StaticBody;
        Body rectb = world.createBody(rectbd);
        EdgeShape rectShape = new EdgeShape();
        rectShape.set(0, 0, 48, 0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectShape;
        fixtureDef.restitution = 1.0f;
        fixtureDef.friction = 1.0f;
        fixtureDef.density = 1.0f;
        rectb.createFixture(fixtureDef);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		super.draw();
		world.step(Gdx.graphics.getDeltaTime(), 10, 10);
		this.camera.viewportHeight = 32;
		this.camera.viewportWidth = 48;
		this.camera.position.set(24f,16f,1);
		render.render(world, this.camera.combined);

	}
	
	@Override
	public void dispose(){
		super.dispose();
		world.dispose();
	}
}
