package com.maple.eggsnake;

public class Display {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Display().show(Test.EAST);
	}

	public void show(Test test){
		switch(test){
		case SOUTH:
		{
			System.out.println("SOUTH!");
			break;
		}
		case EAST:
		{
			System.out.println("EAST!");
			break;
		}
			default:
			{
				System.out.println("DEFAULT!");
				break;
			}
		}
	}
}
