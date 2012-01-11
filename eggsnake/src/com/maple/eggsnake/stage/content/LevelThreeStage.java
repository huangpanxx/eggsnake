package com.maple.eggsnake.stage.content;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.logical.LogicalGameListener;
import com.maple.eggsnake.physics.B2Const;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.logical.WorldController;
import com.maple.eggsnake.stage.BaseStage;
import com.maple.eggsnake.stage.content.attachtexture.CircleBody;
import com.maple.eggsnake.stage.content.attachtexture.CircleBodyPosition;
import com.maple.eggsnake.stage.content.attachtexture.RectangleBodyPosition;
import com.maple.eggsnake.stage.content.attachtexture.PositionManager;

public class LevelThreeStage extends BaseStage implements ActorLoader, 
LogicalGameListener{
	
	private ContentScreen contentScreen;
	
	Loggable logger;
	WorldController controller;

	Vector3 pos_camera = new Vector3(0, 0, 0);
	Box2DDebugRenderer render = new Box2DDebugRenderer();

	float viewportWidth = 0;
	float viewportHeight = 0;
	
	private Texture snakeTexture;
	private Texture woodOneTexture;
	private Texture woodTwoTexture;
	private Texture woodThreeTexture;
	private Texture woodMouseTexture;
	
	private SpriteBatch spriteBach;
	

	public LevelThreeStage(ContentScreen screen, float width, float height, 
			boolean stretch) {
		super(width, height, stretch);
		this.initContent(screen);
		CurrentLevel.getInstance().setLevel(3);
		this.loadWorld();
		this.spriteBach = new SpriteBatch();
		this.load();
	}

	@Override
	public void initContent(ContentScreen screen) {
		this.contentScreen = screen;
	}

	@Override
	public void loadTextures() {
		this.snakeTexture = ResourceLoader.loadTexture("eggSnake_64_128.png");
		this.woodOneTexture = ResourceLoader.loadTexture("wood1_32_256.png");
		this.woodTwoTexture = ResourceLoader.loadTexture("wood2_32_256.png");
		this.woodThreeTexture = ResourceLoader.loadTexture("wood3_32_256.png");
		this.woodMouseTexture = ResourceLoader.loadTexture("woodmouse_64_128");
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		this.loadTextures();
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
	public void draw() {
		super.draw();
		float dt = Gdx.graphics.getDeltaTime();
		if (controller.getWorld() != null) {
			controller.update(dt);
			render.render(controller.getWorld(), this.camera.combined);
		}
		this.attachTexture();
	}
	
	@Override
	public void show() {
		viewportWidth = this.camera.viewportWidth;
		viewportHeight = this.camera.viewportHeight;
		this.pos_camera = new Vector3(this.camera.position);

		this.camera.viewportWidth = 480 / B2Const.CONVERTRATIO;
		this.camera.viewportHeight = 320 / B2Const.CONVERTRATIO;
		this.camera.position.set(this.camera.viewportWidth / 2, 
				this.camera.viewportHeight / 2, 1);
	}
	
	@Override
	public void dispose() {
		this.controller.dispose();
		this.woodTwoTexture.dispose();
		this.woodThreeTexture.dispose();
		this.woodMouseTexture.dispose();
		this.snakeTexture.dispose();
		super.dispose();
	}
	
	private void loadWorld() {
		try {
			this.controller = new WorldController(1, this);
		} catch (Exception e) {
			logger.logWithSignature(this, "加载地图失失败:%1$s",
					e.getLocalizedMessage());
		}
	}
	
	private void attachTexture(){
		PositionManager.setPosition(controller);
		spriteBach.begin();
		this.attachCircle();
		this.attachWoodOne();
		this.attachWoodTwo();
		this.attachWoodThree();
		this.attachMouse();
		spriteBach.end();
	}
	
	private void attachCircle(){
		CircleBody circle = CircleBodyPosition.getInstance().getCircleBody("snake");
		if(circle != null){
			spriteBach.draw(this.snakeTexture, circle.getX() - circle.getRadius(), 
					circle.getY() - circle.getRadius() * 2f);
		}	
	}
	
	private void attachWoodOne(){
		Vector2 woodOne = RectangleBodyPosition.getInstance().getLowerLeft("wood1");
		if(woodOne != null){
			spriteBach.draw(this.woodOneTexture, woodOne.x, woodOne.y);
		}
	}
	
	private void attachWoodTwo(){
		Vector2 woodTwo = RectangleBodyPosition.getInstance().getLowerLeft("wood2");
		if(woodTwo != null){
			spriteBach.draw(this.woodTwoTexture, woodTwo.x - 23, 320 - 236);
		}
	}
	
	private void attachWoodThree(){
		Vector2 woodThree = RectangleBodyPosition.getInstance().getLowerLeft("wood3");
		if(woodThree != null){
			spriteBach.draw(this.woodThreeTexture, woodThree.x - 25f, woodThree.y - 28f);
		}
	}
	
	private void attachMouse(){
		Vector2 woodMouse = RectangleBodyPosition.getInstance().getLowerLeft("mouse");
		if(woodMouse != null){
			spriteBach.draw(woodMouseTexture, woodMouse.x, woodMouse.y);
		}
	}
	
	@Override
	public boolean keyDown(int keycode){
		if(keycode == Input.Keys.W){
			this.contentScreen.navigate(new SelectLevelStage(contentScreen,
					contentScreen.getWidth(), contentScreen.getHeight(), true));
		}
		return true;
	}
	
	private Vector2 convertToWorld(int x, int y) {
		Vector3 point = new Vector3(x, y, 0);
		this.camera.unproject(point);
		return new Vector2(point.x, point.y);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		Vector2 hitPoint = convertToWorld(x, y);
		return controller.touchDown(hitPoint);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		Vector2 hitPoint = convertToWorld(x, y);
		return controller.touchDragged(hitPoint);
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		Vector2 hitPoint = convertToWorld(x, y);
		controller.touchUp(hitPoint);
		return false;
	}

	@Override
	public void onAllMouseKilled() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onCrossGate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShotTimeChanged(int hitTime) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMouseKilled(Body body) {
		// TODO Auto-generated method stub
		
	}
	}

