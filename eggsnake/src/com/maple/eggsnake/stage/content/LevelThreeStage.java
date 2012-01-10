package com.maple.eggsnake.stage.content;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.logical.LogicalGameListener;
import com.maple.eggsnake.physics.B2Const;
import com.maple.eggsnake.screen.ContentScreen;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.logical.WorldController;
import com.maple.eggsnake.stage.BaseStage;

public class LevelThreeStage extends BaseStage implements ActorLoader, LogicalGameListener{
	
	private ContentScreen contentScreen;
	
	Loggable logger;
	WorldController controller;

	Vector3 pos_camera = new Vector3(0, 0, 0);
	Box2DDebugRenderer render = new Box2DDebugRenderer();

	float viewportWidth = 0;
	float viewportHeight = 0;
	
	private Texture snake;
	private Texture wood2;
	private Texture wood3;
	
	private SpriteBatch spriteBach;
	
	private float radius;

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
		this.snake = ResourceLoader.loadTexture("eggSnake_64_128.png");
		this.wood2 = ResourceLoader.loadTexture("lefttwowood_32_256.png");
		this.wood3 = ResourceLoader.loadTexture("wood3.png");
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
		PositionConfigurator.configure(controller);
		spriteBach.begin();
		CircleBody temp = CircleBodyPosition.getInstance().getCircleBody("snake");
		Vector2 wood3 = RectangleBodyPosition.getInstance().getLowerLeft("wood3");
		spriteBach.draw(this.snake, temp.getX() - temp.getRadius(), 
				temp.getY() - temp.getRadius() * 2f);
		spriteBach.draw(this.wood3, wood3.x - 25f, wood3.y - 28f);
		Vector2 wood2 = RectangleBodyPosition.getInstance().getLowerLeft("wood2");
		spriteBach.draw(this.wood2, wood2.x - 23, 320 - 236);		
		spriteBach.end();
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
		this.wood2.dispose();
		this.wood3.dispose();
		this.snake.dispose();
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
	}

