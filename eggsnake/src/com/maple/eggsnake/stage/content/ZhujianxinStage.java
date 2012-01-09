package com.maple.eggsnake.stage.content;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.maple.eggsnake.logger.DefaultLogger;
import com.maple.eggsnake.logger.Loggable;
import com.maple.eggsnake.physics.B2Const;
import com.maple.eggsnake.service.ResourceLoader;
import com.maple.eggsnake.stage.BaseStage;

public class ZhujianxinStage extends BaseStage {

	World world;
	Box2DDebugRenderer render;
	Camera debugCamera;
	Loggable logger = null;
	MouseJoint mouseJoint = null;

	float viewportWidth;
	float viewportHeight;
	float position_x;
	float position_y;

	Vector3 hitPoint = new Vector3();
	Vector2 target = new Vector2();
	Body hitBody = null;
	Body ground = null;
	
	
	/////////////////////////////////////////////////////////////////////
	/*private static final int FRAME_COLS = 2;
    private static final int FRAME_ROWS = 2;
	
    Animation                       walkAnimation;          // #3
    Texture                         walkSheet;              // #4
    TextureRegion[]                 walkFrames;             // #5
    SpriteBatch                     spriteBatch;            // #6
    TextureRegion                   currentFrame;           // #7
    float stateTime;                                        // #8*/
	/////////////////////////////////////////////////////////////////////

	QueryCallback callback = new QueryCallback() {
		@Override
		public boolean reportFixture(Fixture fixture) {
			if (fixture.testPoint(hitPoint.x, hitPoint.y)) {
				hitBody = fixture.getBody();
				return true;
			}
			return false;
		}
	};

	public ZhujianxinStage(float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.logger = DefaultLogger.getDefaultLogger();

		render = new Box2DDebugRenderer();

		try {
			world = ResourceLoader.loadWorld("map111.json");
			logger.logWithSignature(this, "Body:%1$d", world.getBodyCount());
			logger.logWithSignature(this, "Joint:%1$d", world.getJointCount());
			Iterator<Body> it = world.getBodies();
			while (it.hasNext()) {
				Body body = it.next();
				String name = (String) body.getUserData();
				if (name != null && "snake".equals(name)) {
					this.ground = body;
					break;
				}
			}
			//this.initTexture();
		} catch (Exception e) {
			logger.logWithSignature(this, "加载世界失败(%1$s)", e.getMessage());
		}
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
		viewportWidth = this.camera.viewportWidth;
		viewportHeight = this.camera.viewportHeight;
		this.position_x = this.camera.position.x;
		this.position_y = this.camera.position.y;
		this.camera.viewportWidth = 480 / B2Const.CONVERTRATIO;																
		this.camera.viewportHeight = 320 / B2Const.CONVERTRATIO;																														
		this.camera.position.set(0, 0, 1);
	}

	@Override
	public void draw() {
		super.draw();		
		this.configCamera();
		world.step(Gdx.graphics.getDeltaTime(), 10, 10);
		render.render(world, this.camera.combined);
		//this.configPosition(world);
		//this.attachTexture();
		
	}

	@Override
	public void dispose() {
		this.camera.viewportHeight = this.viewportHeight;
		this.camera.viewportWidth = this.viewportWidth;
		this.camera.position.set(this.position_x, this.position_y,
				this.camera.position.z);
		if (world != null)
			world.dispose();
		super.dispose();
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		camera.unproject(hitPoint.set(x, y, 0));
		this.hitBody = null;
		world.QueryAABB(callback, this.hitPoint.x - 0.0001f,
				this.hitPoint.y - 0.0001f, this.hitPoint.x + 0.0001f,
				this.hitPoint.y + 0.0001f);

		if (this.hitBody != null && hitBody.getType() == BodyType.DynamicBody
				&& ground != null) {
			MouseJointDef def = new MouseJointDef();
			def.bodyA = ground;// groundBody
			def.bodyB = hitBody;
			def.collideConnected = true;
			def.target.set(this.hitPoint.x, this.hitPoint.y);
			def.maxForce = 1000.f * hitBody.getMass();
			mouseJoint = (MouseJoint) world.createJoint(def);
			hitBody.setAwake(true);
		}
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (mouseJoint != null) {
			logger.logWithSignature(this, "%1$f,%2$f",
					this.hitBody.getPosition().x, this.hitBody.getPosition().y);
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (mouseJoint != null) {
			world.destroyJoint(mouseJoint);
			mouseJoint = null;
			Vector3 mousePos = new Vector3(x, y, 0);
			camera.unproject(mousePos);
			Vector3 bodyPos = this.hitPoint;
			Vector2 v = new Vector2(mousePos.x - bodyPos.x, mousePos.y
					- bodyPos.y);
			float mass = this.hitBody.getMass() / 100;
			this.hitBody.setLinearVelocity(v.x * mass, v.y * mass);
		}
		return false;
	}
	
	private void configPosition(World world){
		Iterator<Body> iterator;
		Vector3 bodyVector3;
		Body body;
		iterator = world.getBodies();
		String userData;
		while(iterator.hasNext()){
			body = iterator.next();
			userData = (String)body.getUserData();
			if("snake".equals(userData)){
				bodyVector3 = new Vector3(body.getPosition().x, 
						body.getPosition().y, 0f);
				camera.project(bodyVector3);
				BodyPosition.getBodyPositionInstance().setPosition(bodyVector3.x,
						bodyVector3.y);
				System.out.println("X: " + BodyPosition.getBodyPositionInstance().getPosition().x + 
						" y: " + BodyPosition.getBodyPositionInstance().getPosition().y);
			}
		}
	}
	
	/**
	 * @description 配置照相机
	 */
	private void configCamera(){
		this.camera.viewportWidth = 48;
		this.camera.viewportHeight = 32;
		this.camera.position.set(0,20,1);
	}
	
	/**
	 * @description 根据body的位置给body贴纹理
	 */
	private void attachTexture(){
        //stateTime += Gdx.graphics.getDeltaTime();                       // #15
        //float temp = stateTime;
        //currentFrame = walkAnimation.getKeyFrame(temp, true);      // #16
        /*spriteBatch.begin();
        spriteBatch.draw(currentFrame, BodyPosition.getBodyPositionInstance().getPosition().x, 
        		BodyPosition.getBodyPositionInstance().getPosition().y);                         // #17
        spriteBatch.end();*/
		/*Image image = new Image("test", currentFrame);
		image.x = BodyPosition.getBodyPositionInstance().getPosition().x;
		image.y = BodyPosition.getBodyPositionInstance().getPosition().y;
		if(null == this.findActor("test"))
			this.addActor(image);
		else
			this.removeActor(this.findActor("test"));*/
	}
	
	/**
	 * @description 初始化纹理
	 */
	private void initTexture(){
		//walkSheet = new Texture(Gdx.files.internal("data/images/test.png"));     // #9
        /*TextureRegion[][] tmp = TextureRegion.split(walkSheet, 
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
        walkAnimation = new Animation(0.025f, walkFrames);*/              // #11
		//currentFrame = new TextureRegion(walkSheet, 0, 0, 32, 32);
       // spriteBatch = new SpriteBatch();                                // #12
//        stateTime = 0f; 
	}
}
