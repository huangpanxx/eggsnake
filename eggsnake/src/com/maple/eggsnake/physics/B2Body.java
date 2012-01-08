package com.maple.eggsnake.physics;
import java.util.ArrayList;


public class B2Body {
	public int type;
	public B2Vector position;
	public B2HexFloat angle;
	public B2Vector linearVelocity;
	public B2HexFloat angularVelocity;
	public B2HexFloat linearDamping = new B2HexFloat(0);
	public B2HexFloat angularDamping = new B2HexFloat(0);
	public B2HexFloat gravityScale = new B2HexFloat(1);
	public boolean allowSleep = true;
	public boolean awake = false;
	public boolean fixedRotation = false;
	public boolean bullet = false;
	public boolean active = true;
	public String name;
	public ArrayList<B2Fixture> fixture;
	public B2HexFloat massData_mass;
	public B2Vector massData_center;
	public B2HexFloat massData_I;
	
}
