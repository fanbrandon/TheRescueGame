package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Astronaut extends Opponents implements IMoving, ISelectable {
	private int health;
	private int pointValue = 10;
	private boolean selected;
	
	Random random = new Random();
	
	/**
	 * Construct astronaut objects, set color to red
	 * size is betwwen 20 and 50 and direction from 0-359
	 * health is 5 and speed is health *1
	 */
	public Astronaut() {
		super(0,ColorUtil.rgb(255, 0, 0));
		final int MIN_SIZE = 100;
		final int MAX_SIZE = 150;
		super.setSize(new Random().nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE);
		super.setDirection(random.nextInt(359));
		this.health = 5;
		this.setSpeed((health/2 * 1) +1);
	}
	/**
	 * Get points
	 * @return
	 */
	public int tempObject() {
		return pointValue;
	}

	/**
	 * set points
	 * @param pointValue
	 */
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	
	/**
	 * Set health
	 * @param health
	 */
	public void setHealth (int health) {
		this.health = health;
	}
	
	/**
	 * Get health
	 * @return
	 */
	public int getHealth () {
		return health;
	}
	
	/**
	 * Method that when called will lower astronauts health and point value
	 * by 1 and adjust its color, if the chosen astronaut has no health
	 * display a message
	 */
	public void astronautCollision() {
		if (this.getHealth() > 0) {
	        setHealth(getHealth() - 1); // Decrease health
	        setPointValue(getPointValue() - 1); // Decrease points
	        this.setSpeed((health / 2 * 1) + 1); // Adjust speed based on health

	        // Map health to a color
	        int red = (int) ((getHealth() / 5.0) * 255); // Assuming max health is 10
	        int green = (int) ((1 - (getHealth() / 5.0)) * 255); // Green increases as health decreases
	        int blue = (int) ((2- (getHealth() / 5.0)) * 255); // No blue, or you could add it for a more complex effect

	        // Set the color
	        this.setColor(ColorUtil.rgb(red, green, blue)); // Adjust color to reflect health

	        System.out.println("An alien and an astronaut fought! The astronaut's health decreased to " + health);
	    } else {
	        System.out.println("This astronaut has no health!");
	    }
	}
	
	
	public void healHealth() {
		this.setHealth(5);
		this.setColor(ColorUtil.rgb(255, 0, 0));
		this.setSpeed((health / 2 * 1) + 1);
	}
	/**
	 * Override the move method if a chosen astronaut has no health
	 * this astronaut cannot move
	 */
	@Override
	public void move() {
		if (this.getHealth() <= 0) {
			System.out.println("An astronaut has no health and cannot move!");
			return;
		}
		super.move();
	}
	
	/**
	 * Return string values of astronaut
	 */
	@Override
	public String toString() {
		return "Astronaut: loc = " + Math.round(this.getX()*10.0)/10.0 + ", " + Math.round(this.getY()*10.0) / 10.0
				+ " color = " + this.getColortoString()
				+ " size = " + this.getSize()
				+ " speed = " + this.getSpeed()
				+ " dir = " + this.getDirection()
				+ " health = " + this.getHealth();
	}
	// point value getter
	public int getPointValue() {
		return pointValue;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		int objectSize = this.getSize();
		
		int objectX = (int) (this.getX() + pCmpRelPrnt.getX() - objectSize/2);
		int objectY = (int) (this.getY() + pCmpRelPrnt.getY() - objectSize/2);
		
		int[] xPoints = {objectX, objectX + objectSize, objectX + objectSize/2};
		int[] yPoints = {objectY, objectY, objectY + objectSize};	
		
		g.setColor(super.getColor());
		
		if(selected) {
			g.drawPolygon(xPoints, yPoints, 3);
		} else {
			g.fillPolygon(xPoints, yPoints, 3);
		}
		
		
		
	}
	@Override
	public void setSelected(boolean y) {
		selected = y;
		
	}
	@Override
	public boolean isSelected() {
		return selected;
	}
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		// Capture the size of the object that we will be checking against
				int objectSize = getSize();
				
				// Capture the pointer's location relative to the parent's origin
				float pointerX = pPtrRelPrnt.getX();
				float pointerY = pPtrRelPrnt.getY();
				
				// Capture the shape location relative to parent's origin
				float objectX = this.getX() + pCmpRelPrnt.getX() - objectSize/2;
				float objectY = this.getY() + pCmpRelPrnt.getY() - objectSize/2;
				
				
				// pointerX >= objectX:  This checks to see if the pointer location (x) is greater than or equal to that of the object (left side)
				boolean leftSide = pointerX >= objectX;
				
				// pointerX <= objectX + objectSize:  This checks to see if the pointer location (x) is less than or equal to that of the object (right side)
				boolean rightSide = pointerX <= objectX + objectSize;
				
				// pointerY >= objectY:  This checks to see if the pointer location (y) is greater than or equal to that of the object (top side)
				boolean topSide = pointerY >= objectY;
				
				// pointerY <= objectY + objectSize:  This checks to see if the pointer location (y) is less than or equal to that of the object (bottom side)
				boolean bottomSize = pointerY <= objectY + objectSize;
				
				
				// Here we combine all of the booleans to see if the pointer was between all of those sides
				if (leftSide && rightSide && topSide && bottomSize) {
					
					// If so then we return true (the object contains the pointer)
					return true;
					
				} else {
					
					// Otherwise we return false (the object does not contain the pointer)
					return false;
					
				}
	}
	

}
