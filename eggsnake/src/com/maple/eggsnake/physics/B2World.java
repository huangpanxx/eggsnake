package com.maple.eggsnake.physics;
import java.util.ArrayList;


public class B2World {
	public boolean allowSleep;
	public boolean autoClearForces;
	public boolean continuousPhysics;
	public B2Vector gravity;
	public boolean subStepping;
	public boolean warmStarting;
	ArrayList<B2Body> body;
	ArrayList<B2Joint> joint;
}
