package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Alien extends Opponents implements IMoving {
	private final int MIN_SIZE = 100;
	private final int MAX_SIZE = 150;
	private boolean newAlienFlag = false;
    private int collisionTimer = 0;  // Counter to track the number of moves made
    private ArrayList<Alien> collisionVector;
	
	Random random = new Random();
	
	/**
	 * Construct alien object, set color blue
	 * size is 20-50, direction 0-359
	 * speed is 5 
	 */
	public Alien() {
		super(0, ColorUtil.rgb(0, 255, 0));
		super.setSize(random.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE);
		super.setDirection(random.nextInt(359));
		super.setSpeed(3);
		collisionVector = new ArrayList<>();
	}
	// color setter
	public void setColor() {
		this.setColor();
	}
	
	public boolean isNewAlien() {
        return newAlienFlag;
    }
	
	public void setNewAlienFlag(boolean newAlienFlag) {
        this.newAlienFlag = newAlienFlag;
    }

	public void incrementCollisionTimer() {
	    collisionTimer++;
	    if (collisionTimer >= 100) {  // Example: Reset after 100 updates
	        clearCollisionVector();
	        collisionTimer = 0;  // Reset the timer
	    }
	}
	
	public void addToCollisionVector(Alien alien) {
        if (!collisionVector.contains(alien)) {
            collisionVector.add(alien);
        }
    }

    // Check if the alien has already collided with another alien
    public boolean hasCollidedWith(Alien alien) {
        return collisionVector.contains(alien);
    }

    public void clearCollisionVector() {
        collisionVector.clear();
    }
	
	/**
	 * Return string values of alien
	 */
	@Override
	public String toString() {
		return "Alien: loc = " + Math.round(this.getX()*10.0)/10.0 + ", " + Math.round(this.getY()*10.0) / 10.0
				+ " color = " + this.getColortoString()
				+ " size = " + this.getSize()
				+ " speed = " + this.getSpeed()
				+ " dir = " + this.getDirection();			
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int objectSize = this.getSize();
		
		int objectX = (int) (this.getX() + pCmpRelPrnt.getX() - objectSize/2);
		int objectY = (int) (this.getY() + pCmpRelPrnt.getY() - objectSize/2);
		
		g.setColor(super.getColor());
		
		g.fillRect(objectX, objectY, objectSize, objectSize);
		
	}
	
	
	
}
