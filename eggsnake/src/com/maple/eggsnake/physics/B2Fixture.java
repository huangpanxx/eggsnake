package com.maple.eggsnake.physics;

public class B2Fixture {
	public B2HexFloat restitution;
	public B2HexFloat friction;
	public B2HexFloat density;
	public boolean sensor;
	public int filter_categoryBits=0x01;
	public int filter_maskBits = 0xffff;
	public int filter_groupIndex = 0;
	public B2CircleShape circle;
	public B2EdgeShape edge;
	public B2LoopShape loop;
	public B2ChainShape chain;
	public B2PolygonShape polygon;
	public String name;
	
}
