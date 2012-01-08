package com.maple.eggsnake.physics;

public class B2Joint {
	public int bodyA;
	public int bodyB;
	public String type;
	//revolute
	public B2Vector anchorA;
	public B2Vector anchorB;
	public B2HexFloat refAngle;
	public boolean enableLimit;
	public B2HexFloat lowerLimit;
	public B2HexFloat upperLimit;
	public boolean enableMotor;
	public B2HexFloat motorSpeed;
	public B2HexFloat maxMotorTorque;
	
	//prismatic
	public B2Vector localAxisA;
	public B2Vector localAxis1;
	
	//distance
	public B2HexFloat length;
	public B2HexFloat frequency;
	public B2HexFloat dampingRatio;
	
	//pulley
	public B2Vector groundAnchorA;
	public B2Vector groundAnchorB;
	public B2HexFloat lengthA;
	public B2HexFloat lengthB;
	public Float ratio;
	
	//mouse
	public B2HexFloat maxForce;
	
	
	//gear
	public int joint1;
	public int joint2;
	
	//wheel
	public B2HexFloat springFrequency;
	public B2HexFloat springDampingRatio;
	
	//weld
	public B2HexFloat referenceAngle = new B2HexFloat(0);
	
	//friction
	public B2HexFloat maxTorque;
	
	//rope
	public B2HexFloat maxLength;
	
	//
	public boolean collideConnected = false;
	public String name;
}
