package com.mycompany.a3;

import com.codename1.charts.models.*;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public abstract class GameObject implements IDrawable {
	private int size;
	private int color;
	private Point location;
	
	/**
	 * Construct a game objects and its size, color and position
	 * in the world
	 * @param size
	 * @param color
	 */
	public GameObject (int size, int color) {
		this.size = size;
		this.color = color;
		Random rand = new Random();
		float x = rand.nextFloat() * 1000;
		float y = rand.nextFloat() * 1000;
		this.location = new Point(x,y);
		
	}
	
	/**
	 * Set the x coordinate of an object
	 * @param x
	 */
	public void setX(float x) {
		location.setX(x);
	}
	
	/**
	 * Get an x coordinate of an object
	 * @return
	 */
	public float getX() {
		return location.getX();
	}
	
	/**
	 * Set the y coordinate of an object
	 * @param y
	 */
	public void setY(float y) {
		location.setY(y);
	}
	
	/**
	 * Get the y coordinate of an object
	 * @return
	 */
	public float getY() {
		return location.getY();
	}
	
	/**
	 * Set color of an object
	 * @param color
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
	/**
	 * Get color of an object
	 * @return
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * Set size of an object
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Get size of an object
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Set location of object in (x,y) form
	 * @param x
	 * @param y
	 */
	public void setLocation(float x, float y) {
		this.location = new Point(x,y);
	}
	
	/**
	 * Get the (x,y) coordinates of an object
	 * @return
	 */
	public Point getLocation () {
		return location;
	}
	
	/**
	 * Get the rgb values of an object's color
	 * @return
	 */
	public String getColortoString() {
		return "["+ ColorUtil.red(this.color) + "," + ColorUtil.green(this.color) + "," + ColorUtil.blue(this.color)+ "]";
	}
	
	/**
	 * Abstract method to display object's info in string
	 */
	public abstract String toString();
}
