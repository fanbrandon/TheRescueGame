package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.util.ArrayList;
import java.lang.Math;

public class Spaceship extends Rescuers implements IGuided {
	private static Spaceship theSpaceship;
	private int doorSize;
	private String opponentLocation;
	private boolean doorOpen = false;
	private int points = 0;
	private int astronautRescued;
	private int alienSnuck;
	private int alienCount = 3;
	private int astronautCount = 4;
	
	/**
	 * Construct spaceship object with a size of 100 
	 * and blue color
	 */
	private Spaceship() {
		super(100, ColorUtil.rgb(0, 0, 255));
		this.doorSize = super.getSize();
	}
	
	public static Spaceship getSpaceship() {
		if (theSpaceship == null) {
			theSpaceship = new Spaceship();
		}
		return theSpaceship;
	}
	
	public void setColor() {
		this.setColor();
	}
	
	/**
	 * Get value of astronauts rescued
	 * @return
	 */
	public int getAstronautRescued() {
		return astronautRescued;
	}

	/**
	 * Set number of astronauts rescued
	 * @param astronautRescued
	 */
	public void setAstronautRescued(int astronautRescued) {
		this.astronautRescued = astronautRescued;
	}

	/**
	 * Get number of aliens on spaceship
	 * @return
	 */
	public int getAlienSnuck() {
		return alienSnuck;
	}

	/**
	 * Set number of aliens on spaceship
	 * @param alienSnuck
	 */
	public void setAlienSnuck(int alienSnuck) {
		this.alienSnuck = alienSnuck;
	}

	/**
	 * Get alien count in the world
	 * @return
	 */
	public int getAlienCount() {
		return alienCount;
	}

	/**
	 * Set alien count in the world
	 * @param alienCount
	 */
	public void setAlienCount(int alienCount) {
		this.alienCount = alienCount;
	}

	/**
	 * Get astronaut count in the world
	 * @return
	 */
	public int getAstronautCount() {
		return astronautCount;
	}

	/**
	 * Set astronaut count in the world
	 * @param astronautCount
	 */
	public void setAstronautCount(int astronautCount) {
		this.astronautCount = astronautCount;
	}
	
	/**
	 * Set the opponent location in location
	 * @param location
	 */
	public void setOpponentLocation(String location) {
		this.opponentLocation = location;
	}
	
	/**
	 * Set points for game score
	 * @param points
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * Get the points for game score
	 * @return
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Move method, move in any direction by 10 
	 * or jump to the location of an opponent
	 */
	@Override
	public void moveLeft() {
		setLocation(this.getX() - 10,this.getY());
		
	}

	@Override
	public void moveRight() {
		setLocation(this.getX() + 10,this.getY());
		
	}

	@Override
	public void moveUp() {
		setLocation(this.getX(),this.getY() + 10);
		
	}

	@Override
	public void moveDown() {
		setLocation(this.getX(),this.getY() - 10);
		
	}

	@Override
	public void JumpToLocation() {
		System.out.println("Spaceship has moved to random" + opponentLocation);
		
	}
	
	/**
	 * Method to open spaceship's door and update score
	 * by taking astronauts or aliens in ship
	 * @param gameObjects
	 */
	public void openDoor(GameObjectCollection gameObjects) { // pass gameObjects
		if (!doorOpen) { 
	        doorOpen = true; 
	        System.out.println("Spaceship door is now open.");

	        float doorX = this.getX();
	        float doorY = this.getY();

	        // Use a for loop to iterate over the ArrayList
	        for (int i = 0; i < gameObjects.size(); i++) {
	            GameObject obj = gameObjects.get(i);
	            if (obj instanceof Opponents) {
	                float opponentX = obj.getX();
	                float opponentY = obj.getY();

	                // Calculate the distance between the spaceship and the opponent
	                float distance = (float) Math.sqrt((opponentX - doorX) * (opponentX - doorX)
	                                                  + (opponentY - doorY) * (opponentY - doorY));

	                // If the opponent is within the door's range (half the door's size)
	                if (distance <= doorSize / 2.0f) {
	                    if (obj instanceof Astronaut) { 
	                        Astronaut astronaut = (Astronaut) obj;
	                        this.points += astronaut.getPointValue();
	                        this.astronautRescued++;
	                        this.astronautCount--;
	                        System.out.println("Astronaut rescued! Points gained: " + astronaut.getPointValue());
	                    } else if (obj instanceof Alien) { 
	                        this.points -= 10;
	                        this.alienSnuck++;
	                        this.alienCount--;
	                        System.out.println("Alien encountered! Points lost: 10");
	                    }

	                    // Remove the object from the ArrayList
	                    gameObjects.remove(i);
	                    i--; // Decrement the index after removal to avoid skipping the next element
	                }
	            }
	        }

	        doorOpen = false;
	        System.out.println("Spaceship door is now closed.");
	    } else {
	        System.out.println("The door is already open!");
	    }
    }
	
	/**
	 * Expand door by 10 if it is less than 1000
	 */
	public void expand() {
		if(doorSize < 1000) {
			doorSize += 10;
			this.setSize(doorSize);
			System.out.println("Spaceship door has been expanded");
		}
		else {
			System.out.println("Spaceship door cannot expand any further");
		}
	}
	
	/**
	 * Shrink door by 10 if it is more than 50
	 */
	public void contract() {
		if(doorSize > 50) {
			doorSize -= 10;
			this.setSize(doorSize);
			System.out.println("Spaceship door has been contracted");
		}
		else {
			System.out.println("Spaceship door cannot contract any further");
		}
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		int objectSize = this.getSize();
		
		int objectX = (int) (this.getX() + pCmpRelPrnt.getX() - objectSize/2);
		int objectY = (int) (this.getY() + pCmpRelPrnt.getY() - objectSize/2);
		
		g.setColor(super.getColor());
		
		g.fillArc(objectX, objectY, objectSize, objectSize, 0, 360);
	}

	/**
	 * Return string values of spaceship
	 */
	@Override
	public String toString() {
		return "Spaceship: loc = " + Math.round(this.getX()*10.0)/10.0 + ", " + Math.round(this.getY()*10.0) / 10.0
				+ " color = " + this.getColortoString()
				+ " size = " + this.getSize();
	}

}
