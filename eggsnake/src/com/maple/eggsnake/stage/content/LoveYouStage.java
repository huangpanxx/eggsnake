package com.maple.eggsnake.stage.content;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.logical.LogicalGameListener;
import com.maple.eggsnake.logical.WorldController;
import com.maple.eggsnake.physics.B2Const;
import com.maple.eggsnake.stage.BaseStage;

public class LoveYouStage extends BaseStage implements LogicalGameListener{

	Loggable logger;
	WorldController controller;

	Vector3 pos_camera = new Vector3(0, 0, 0);
	Box2DDebugRenderer render = new Box2DDebugRenderer();

	float viewportWidth = 0;
	float viewportHeight = 0;
	
	//////////////////////////////////////////////////////////////////
	private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 5;
	
    Animation                       walkAnimation;          // #3
    Texture                         walkSheet;              // #4
    TextureRegion[]                 walkFrames;             // #5
    SpriteBatch                     spriteBatch;            // #6
    TextureRegion                   currentFrame;           // #7
    float stateTime;                                        // #8
	///////////////////////////////////////////////////////////////////

	public LoveYouStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		logger = DefaultLogger.getDefaultLogger();
		this.loadWorld();
		this.initTexture();
	}

	private void loadWorld() {
		try {
			this.controller = new WorldController("levelthree.json", this);
		} catch (Exception e) {
			logger.logWithSignature(this, "加载地图失失败:%1$s",
					e.getLocalizedMessage());
		}
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
		viewportWidth = this.camera.viewportWidth;
		viewportHeight = this.camera.viewportHeight;
		this.pos_camera = new Vector3(this.camera.position);

		this.camera.viewportWidth = 480 / B2Const.CONVERTRATIO;
		this.camera.viewportHeight = 320 / B2Const.CONVERTRATIO;
		this.camera.position.set(this.camera.viewportWidth / 2, this.camera.viewportHeight / 2, 1);
	}

	@Override
	public void draw() {
		super.draw();
		float dt = Gdx.graphics.getDeltaTime();
		if (controller.getWorld() != null) {
			controller.update(dt);
			render.render(controller.getWorld(), this.camera.combined);
		}
		this.configPosition();
		this.attachTexture();
	}

	@Override
	public void dispose() {
		this.controller.dispose();
		super.dispose();
	}

	private Vector2 convertToWorld(int x, int y) {
		Vector3 point = new Vector3(x, y, 0);
		this.camera.unproject(point);
		return new Vector2(point.x, point.y);
	}
	
	/**
	 * @description 配置单例位置信息
	 */
	private void configPosition(){
		Iterator<Body> iterator;
		Vector3 bodyVector3;
		Body body;
		iterator = controller.getWorld().getBodies();
		//ArrayList<Fixture> fixtures;
		String userData;
		
		while(iterator.hasNext()){
			body = iterator.next();
			//Vector2 vector2 = null;
			//body.getLocalPoint(vector2);
			userData = (String)body.getUserData();
			//fixtures = body.getFixtureList();
			if("snake".equals(userData)){
			//	float radius = fixtures.get(0).getShape().getRadius();
				
				bodyVector3 = new Vector3(body.getPosition().x, 
						body.getPosition().y, 0);
				camera.project(bodyVector3);
				BodyPosition.getInstance().setPosition(bodyVector3.x,
						bodyVector3.y);
				System.out.println("X: " + BodyPosition.getInstance().getPosition().x + 
						" y: " + BodyPosition.getInstance().getPosition().y);
			}
		}
	}
	
	/**
	 * @description 初始化纹理
	 */
	private void initTexture(){
		walkSheet = new Texture(Gdx.files.internal("data/images/sprite_animation_frames_1.png"));     // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, 
        		walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                	tmp[i][j].setRegionWidth(tmp[i][j].getRegionWidth());
                	tmp[i][j].setRegionHeight(tmp[i][j].getRegionHeight());
                        walkFrames[index++] = tmp[i][j];
                }
        }
        walkAnimation = new Animation(0.025f, walkFrames);              // #11
		currentFrame = new TextureRegion(walkSheet, 0, 0, 32, 32);
        spriteBatch = new SpriteBatch();                                // #12
        stateTime = 0f; 
	}
	
	
	/**
	 * @description 根据body的位置给body贴纹理
	 */
	private void attachTexture(){
		stateTime += Gdx.graphics.getDeltaTime();                       // #15
        float temp = stateTime;
        currentFrame = walkAnimation.getKeyFrame(temp, true);      // #16
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, BodyPosition.getInstance().getPosition().x, 
        		BodyPosition.getInstance().getPosition().y);                         // #17
        spriteBatch.end();
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
		logger.logWithSignature(this, "过关了");
	}
}
