package com.mycompany.a3;

public abstract class Rescuers extends GameObject implements IGuided {
	/**
	 * Construct rescuers objects
	 * @param color
	 * @param size
	 */
	public Rescuers (int color, int size) {
			super(color, size);
	}
	
	public void moveLeft() {
		setLocation(this.getX() - 10,this.getY());
		
	}

	
	public void moveRight() {
		setLocation(this.getX() + 10,this.getY());
		
	}

	
	public void moveUp() {
		setLocation(this.getX(),this.getY() + 10);
		
	}

	
	public void moveDown() {
		setLocation(this.getX(),this.getY() - 10);
		
	}

	
	public void JumpToLocation() {
		System.out.println("Spaceship has moved to random");
		
	}
}
