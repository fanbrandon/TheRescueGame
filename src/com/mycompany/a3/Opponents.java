package com.mycompany.a3;

import java.lang.Math;
import com.codename1.charts.models.Point;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public abstract class Opponents extends GameObject implements IMoving, ICollider {
	private int speed;
	private int direction;
	private Vector<GameObject> collisionVector = new Vector<>();

    public Vector<GameObject> getCollisionVector() {
        return collisionVector;
    }
	
	Random random = new Random();
	
	
	
	/**
	 * Construct an opponent object
	 * @param speed
	 * @param direction
	 */
	public Opponents(int speed, int direction) {
		super(speed, direction);
	}
	
	/**
	 * Set speed value of opponent object
	 * @param speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * Get speed value of opponent object
	 * @return
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Set direction of opponent object
	 * @param direction
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	/**
	 * Get direction of opponent object
	 * @return
	 */
	public int getDirection() {
		return direction;
	}
	
	public void setSize() {
		this.setSize();
	}
	
	/**
	 * Move the opponent objects
	 */
	public void move() {
		int adjustDirection = random.nextInt(11) -5; // random number to adjust direction
		direction = (getDirection()+ adjustDirection);
		checkBoundary(); // check if object is at boundary
		float deltaX = (float)Math.cos(Math.toRadians(90-direction)) * getSpeed(); // create new x and y
		float deltaY = (float)Math.sin(Math.toRadians(90-direction)) * getSpeed();
		this.setLocation(deltaX + getX(), deltaY +getY()); // update location
	}
	
	/**
	 * Check if opponent is outside the game world's boundary
	 * Reverse its direction so it goes back into the game 
	 * world boundary
	 * 
	 */
	public void checkBoundary() {
		Point location = getLocation();
		
		if(location.getX() <= 0) { // check if it is left or right
			direction = getDirection() -180;
			direction = (direction % 360 + 360) % 360; // reverse the direction
			System.out.println("Hit left world boundary, reversing direction");
		}else if (location.getX() >= 1000) {//check if it is top or bottom
			direction = getDirection() - 180;
			direction = (direction % 360 + 360) % 360; // reverse the direction
			System.out.println("Hit right world boundary, reversing direction");
		}
		
		if(location.getY() <= 0) { // check if it is top or bottom
			direction = getDirection() - 180;
			direction = (direction % 360 + 360) % 360; // reverse the direction
			System.out.println("Hit bottom of the world boundary, reversing direction");
		}else if (location.getY() >= 1000) {//check if it is top or bottom
			direction = getDirection() -180 ;
			direction = (direction % 360 + 360) % 360; // reverse the direction
			System.out.println("Hit top of the world boundary, reversing direction");
		}
		
	}
	
	@Override
	public boolean collidesWith(GameObject otherObject) {
		double thisCenterX = this.getX();
		double thisCenterY = this.getY();
		double otherCenterX = otherObject.getX();
		double otherCenterY = otherObject.getY();
		
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		double distBetweenCentersSqr = (dx*dx + dy*dy);
		
		int thisRadius = this.getSize()/2;
		int otherRadius = otherObject.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius+otherRadius*otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) {
			return true;
		}
		return false;
	}
	
	@Override
	public void handleCollision(GameObject otherObject, GameWorld gw) {
		if (this instanceof Astronaut && otherObject instanceof Alien) {
			gw.astronautCollision();
		} else if (this instanceof Alien && otherObject instanceof Alien) {
			gw.alienCollision();
		}
		
	}
}
