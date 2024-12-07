package com.mycompany.a3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

import com.codename1.ui.Display;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public class GameWorld extends Observable {
	private GameObjectCollection gameObjects;
	private int clock;
	private int score;
	private int astronautCount;
	private int alienCount;
	private int maxAliens = 10;
	private boolean sound = false;
	private int width;
	private int height;
	private int alienSneak;
	private int astronautRescued;
	private Sound alienSound;
	private Sound astroSound;
	private Sound doorSound;
	private BGSound bgSound;
	private boolean paused = false;
	
	Random random = new Random();
	
	/**
	 * Initiate game world, clock, score, game objects and counts
	 */
	public void init() {
		gameObjects = new GameObjectCollection();
		Spaceship spaceship = Spaceship.getSpaceship();
		gameObjects.add(spaceship);
		for(int i = 0; i < 4; i++) {
			gameObjects.add(new Astronaut());
			astronautCount++;
		}
		for(int i = 0; i < 3; i++) {
			gameObjects.add(new Alien());
			alienCount++;
		}
	}
	
	// All methods now use iterator design pattern

	/**
	 * The move methods for any guided object. First finds an instance
	 * of the spaceship object, then calls appropriate method
	 */

	public void moveRight() {
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			GameObject tempObject = iterator.getNext();
			
			if(tempObject instanceof Spaceship) {
				((Spaceship)tempObject).moveRight();
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	public void moveLeft() {
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			GameObject tempObject = iterator.getNext();
			
			if(tempObject instanceof Spaceship) {
				((Spaceship)tempObject).moveLeft();
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	public void moveUp() {
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			GameObject tempObject = iterator.getNext();
			
			if(tempObject instanceof Spaceship) {
				((Spaceship)tempObject).moveUp();
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	public void moveDown() {
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			GameObject tempObject = iterator.getNext();
			
			if(tempObject instanceof Spaceship) {
				((Spaceship)tempObject).moveDown();
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	/**
	 * expands size of the ship's door by 10
	 */
	public void expand() {
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			GameObject tempObject = iterator.getNext();
			
			if(tempObject instanceof Spaceship) {
				((Spaceship)tempObject).expand();
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * shrinks size of the ship's door by 10
	 */
	public void contract() {
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			GameObject tempObject = iterator.getNext();
			
			if(tempObject instanceof Spaceship) {
				((Spaceship)tempObject).contract();
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * Increment game clock by 1, all objects with IMoving interface move accordingly
	 */
	public void tick() {
		System.out.println("-------------------The game has ticked---------------------------------");
		System.out.println("-------------------Aliens and Astronauts have moved--------------------");
		clock++;

	    // Move all movable objects
	    IIterator iterator = gameObjects.getIterator();
	    while (iterator.hasNext()) {
	        GameObject tempObject = iterator.getNext();
	        if (tempObject instanceof IMoving) {
	            ((IMoving) tempObject).move();
	        }
	    }

	    // Collision detection
	    IIterator outerIterator = gameObjects.getIterator();
	    while (outerIterator.hasNext()) {
	        GameObject currentObject = outerIterator.getNext();

	        if (currentObject instanceof ICollider) {
	            ICollider collider = (ICollider) currentObject;

	            IIterator innerIterator = gameObjects.getIterator();
	            while (innerIterator.hasNext()) {
	                GameObject otherObject = innerIterator.getNext();

	                if (currentObject != otherObject && otherObject instanceof ICollider) {
	                    ICollider otherCollider = (ICollider) otherObject;

	                    // Check if objects are already in each other's collision vector
	                    boolean alreadyColliding =
	                        ((Opponents) collider).getCollisionVector().contains(otherObject) ||
	                        ((Opponents) otherCollider).getCollisionVector().contains(currentObject);

	                    if (collider.collidesWith(otherObject)) {
	                        if (!alreadyColliding) {
	                            // Handle collision and update collision vectors
	                            collider.handleCollision(otherObject, this);
	                            ((Opponents) collider).getCollisionVector().add(otherObject);
	                            ((Opponents) otherCollider).getCollisionVector().add(currentObject);
	                        }
	                    } else {
	                        // Remove from collision vectors if no longer colliding
	                        ((Opponents) collider).getCollisionVector().remove(otherObject);
	                        ((Opponents) otherCollider).getCollisionVector().remove(currentObject);
	                    }
	                }
	            }
	        }
	    }
	    
	    if (getSound()) {
	    	bgSound.play();
	    }else {
	    	bgSound.pause();
	    }

	    this.setChanged();
	    this.notifyObservers(this);
	}
	/**
	 * Opens the door of the spaceship if any opponents are with the boundary,
	 * the score will be adjusted accordingly
	 */
	public void score() {
		gameEnd();

	    // Find the spaceship object using the iterator
	    IIterator iterator = gameObjects.getIterator();
	    Spaceship spaceship = null;

	    while (iterator.hasNext()) {
	        GameObject tempObject = iterator.getNext();
	        if (tempObject instanceof Spaceship) {
	            spaceship = (Spaceship) tempObject;
	            break; // We found the spaceship, no need to keep iterating
	        }
	    }

	    // Ensure spaceship was found before accessing its methods
	    if (spaceship != null) {
	        spaceship.openDoor(gameObjects);
	    	alienSneak = spaceship.getAlienSnuck();
	        astronautRescued = spaceship.getAstronautRescued();
	        score = spaceship.getPoints();
	        if(getSound()) {
	        	doorSound.play();
	        }
	        gameEnd();
	    } else {
	        System.out.println("Spaceship not found.");
	    }
	    this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * Replace the spaceship's location coordinates with a random alien's,
	 * will print error message if there are no aliens in the game
	 */
	public void toAlien() {
		if (getAlienCount() == 0) {
	        System.out.println("There are no aliens in the world.");
	    } else {
	        int alienIndex = random.nextInt(alienCount);
	        int currentIndex = 0;

	        // Use iterator to find the alien at the random index
	        IIterator iterator = gameObjects.getIterator();
	        while (iterator.hasNext()) {
	            GameObject tempObject = iterator.getNext();
	            if (tempObject instanceof Alien) {
	                if (currentIndex == alienIndex) {
	                    Alien alien = (Alien) tempObject;
	                    Spaceship spaceship = findSpaceship();
	                    if (spaceship != null) {
	                        spaceship.setLocation(alien.getX(), alien.getY());
	                        spaceship.setOpponentLocation(" alien.");
	                        spaceship.JumpToLocation();
	                    }
	                    this.setChanged();
	    	    		this.notifyObservers(this);
	                    return;
	                }
	                currentIndex++;
	            }
	        }
	        this.setChanged();
			this.notifyObservers(this);
	    }
		
	}
	
	private Spaceship findSpaceship() {
		IIterator iterator = gameObjects.getIterator();
	    while (iterator.hasNext()) {
	        GameObject tempObject = iterator.getNext();
	        if (tempObject instanceof Spaceship) {
	            return (Spaceship) tempObject;
	        }
	    }
	    System.out.println("Spaceship not found.");
	    return null;
	}

	/**
	 * Replace the spaceship's location coordinates with a random astronaut's,
	 * will print error message if there are no astronauts
	 */
	public void toAstronaut() {
		if (getAstronautCount() ==0) {
	        System.out.println("There are no astronauts in the world.");
	    } else {
	        int astronautIndex = random.nextInt(astronautCount);
	        int currentIndex = 0;

	        // Use iterator to find the astronaut at the random index
	        IIterator iterator = gameObjects.getIterator();
	        while (iterator.hasNext()) {
	            GameObject tempObject = iterator.getNext();
	            if (tempObject instanceof Astronaut) {
	                if (currentIndex == astronautIndex) {
	                    Astronaut astronaut = (Astronaut) tempObject;
	                    Spaceship spaceship = findSpaceship();
	                    if (spaceship != null) {
	                        spaceship.setLocation(astronaut.getX(), astronaut.getY());
	                        spaceship.setOpponentLocation(" astronaut.");
	                        spaceship.JumpToLocation();
	                    }
	                    this.setChanged();
	    	    		this.notifyObservers(this);
	                    return;
	                }
	                currentIndex++;
	            }
	        }}
	        
		
	}

	/**
	 * Finds a random alien and creates a new instance of the alien object
	 * near the random alien's location, will print error message if there
	 * are less than two aliens
	 */
	public void alienCollision() {
	    if (getAlienCount() >= 2 && getAlienCount() < maxAliens) {
	        IIterator iterator = gameObjects.getIterator();
	        boolean newAlienCreated = false; // Flag to track if a new alien is created

	        while (iterator.hasNext()) {
	            GameObject tempObject = iterator.getNext();

	            if (tempObject instanceof Alien) {
	                Alien alien = (Alien) tempObject;

	                // Skip collision checks for newly created aliens
	                if (alien.isNewAlien()) {
	                    continue;
	                }

	                IIterator objectIterator = gameObjects.getIterator();
	                while (objectIterator.hasNext()) {
	                    GameObject otherObject = objectIterator.getNext();

	                    if (otherObject instanceof Alien) {
	                        Alien alien2 = (Alien) otherObject;

	                        // Skip collision checks if the other alien is newly created or already collided
	                        if (alien2.isNewAlien() || alien.hasCollidedWith(alien2)) {
	                            continue;
	                        }

	                        // Check for collision between two aliens
	                        if (alien.collidesWith(alien2)) {
	                            if (!newAlienCreated) {
	                                // Create a new alien
	                                Alien newAlien = new Alien();
	                                int offsetX = random.nextInt(250);
	                                int offsetY = random.nextInt(250);

	                                // Ensure the new location stays within bounds
	                                float newX = Math.min(Math.max(alien.getX() + offsetX, 0), 1000);
	                                float newY = Math.min(Math.max(alien.getY() + offsetY, 0), 1000);

	                                newAlien.setLocation(newX, newY);
	                                newAlien.setNewAlienFlag(true);
	                                gameObjects.add(newAlien);
	                                if (getSound()) {
	                                	alienSound.play();
	                                }
	                                alienCount++;

	                                // Add the parents and the new alien to each other's collision vectors
	                                alien.addToCollisionVector(alien2);
	                                alien2.addToCollisionVector(alien);
	                                newAlien.addToCollisionVector(alien);
	                                newAlien.addToCollisionVector(alien2);

	                                alien.addToCollisionVector(newAlien);
	                                alien2.addToCollisionVector(newAlien);

	                                newAlienCreated = true;
	                                System.out.println("Two aliens have collided! A new alien has been created.");
	                            }

	                            break; // No need to check further for collisions in this cycle
	                        }
	                    }
	                }

	                if (newAlienCreated) {
	                    break; // Exit the outer loop if a new alien has been created
	                }
	            }
	        }

	        // Notify observers only if a new alien was created
	        if (newAlienCreated) {
	            this.setChanged();
	            this.notifyObservers(this);
	        }
	    } else if (getAlienCount() == maxAliens) {
	        System.out.println("Max Aliens in the world reached.");
	    } else {
	        System.out.println("Not enough Aliens to reproduce a new one.");
	    }
	}

	/**
	 * Finds a random astronaut and lowers its health attribute and color
	 * attribute, if there are no aliens or astronauts print an error message
	 */
	public void astronautCollision() {
	    // Ensure we have aliens and astronauts to collide
	    if (getAlienCount() != 0 && getAstronautCount() > 0) {
	        IIterator objectIterator = gameObjects.getIterator();
	        
	        // Iterate through all game objects and check for collisions
	        while (objectIterator.hasNext()) {
	            GameObject tempObject = objectIterator.getNext();
	            
	            // Process only astronauts (to check for collision with aliens)
	            if (tempObject instanceof Astronaut) {
	                Astronaut astronaut = (Astronaut) tempObject;

	                // Now iterate through all game objects again to check for alien collisions
	                IIterator gameObjectIterator = gameObjects.getIterator();
	                while (gameObjectIterator.hasNext()) {
	                    GameObject otherObject = gameObjectIterator.getNext();
	                    
	                    // If the object is an alien, check for a collision with the astronaut
	                    if (otherObject instanceof Alien) {
	                        Alien alien = (Alien) otherObject;
	                        
	                        // Check for collision between astronaut and alien
	                        if (astronaut.collidesWith(alien)) {
	                            astronaut.astronautCollision(); // Handle collision logic (remove astronaut, score, etc.)

	                            // Ensure sound plays every time a collision occurs
	                            if (getSound() && astronaut.getHealth() > 0) {
	                                astroSound.play();  // Play sound for each collision
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        
	        // Notify observers that the collision handling is complete
	        this.setChanged();
	        this.notifyObservers(this);
	    } else {
	        System.out.println("Not enough aliens or astronauts for a collision.");
	    }
	}

	public void heal() {
	    IIterator objectIterator = gameObjects.getIterator();

	    while (objectIterator.hasNext()) {
	        GameObject tempObject = objectIterator.getNext();

	        // Check if the object is an Astronaut and selected
	        if (tempObject instanceof Astronaut && tempObject instanceof ISelectable) {
	            ISelectable selectable = (ISelectable) tempObject;
	            if (selectable.isSelected()) {
	                Astronaut astronaut = (Astronaut) tempObject;
	                astronaut.healHealth();
	                selectable.setSelected(false); // Deselect astronaut after healing
	                break; // Heal only the first selected astronaut
	            }
	        }
	    }

	    this.setChanged();
	    this.notifyObservers(this);
	}
	

	/**
	 * Print map that shows objects location, size, health, direction, speed
	 */
	public void map() {
        System.out.println("-------------------Map is Printed--------------------------------------");
        IIterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject tempObject = iterator.getNext();
            System.out.println(tempObject.toString());
        }
    }

	
	/**
	 * Check if all astronauts are rescued and exit if they are
	 */
	public void gameEnd() {
		if(getAstronautCount() == 0) {
			System.out.println("You have rescued all astronauts");
			Display.getInstance().exitApplication();
		}
	}
	// sound setter
	public void setSound(boolean sound) {
		this.sound = sound;
		
		this.setChanged();
		this.notifyObservers(this);
		
	}
	// display map while using an iterator
	public void displayCurrentMap() {
		// Display a message header
				System.out.println("-----------------------------------------------------");
				System.out.println("The current map of the game has been displayed below.");
				System.out.println("-----------------------------------------------------");
				
				// Create an iterator to keep track of the objects
				IIterator iterator = gameObjects.getIterator();
				
				// Here we check to see if the iterator that we are using has a next object
				while(iterator.hasNext()) {
					
					// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
					GameObject tempObject = iterator.getNext();
					
					// Using the .toString() method that we overwrote in each class, we can print the details of the game objects
					System.out.println(tempObject.toString());
					
				}
	}
	
	// Pause the background sound
	public void pauseSound() {
			
			// Check to see if the sound is on
		if(sound) {
				
				// If so then we will pause the background music
			bgSound.pause();
				
			}
			
		}
		
		// Pause the background sound
	public void playSound() {
			
			// Check to see if the sound is on
		if (sound) {
				
				// If so then we will play the background music
				bgSound.play();
				
			}
			
		}
		
	
	
	public void createSounds() {
		alienSound = new Sound("Alien.wav");
		
		astroSound = new Sound("Astro.wav");
		
		doorSound = new Sound("Door.wav");
		
		bgSound = new BGSound("Background.wav");
	}
	
	
	// getters and setters for private variables
	
	public IIterator getIterator() {
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		return iterator;
		
	}
	
	public boolean getPaused() {
		return paused;
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getClock() {
		return clock;
	}
	
	public void setClock(int clock) {
		this.clock = clock;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean getSound() {
		return sound;
	}
	
	public int getAstronautCount() {
		return astronautCount - astronautRescued;
	}
	
	public void setAstronautCount(int astronautCount) {
		this.astronautCount = astronautCount;
	}
	
	public int getAlienCount() {
		return alienCount - alienSneak;
	}
	
	public void setAlienCount(int alienCount) {
		this.alienCount = alienCount;
	}
	
	public void setAlienSneak (int alienSneak) {
		this.alienSneak = alienSneak;
	}
	
	public int getAlienSneak() {
		return alienSneak;
	}
	
	public void setAstroRescued(int astronautRescued) {
		this.astronautRescued = astronautRescued;
	}
	
	public int getAstroRescued() {
		return astronautRescued;
	}

	
}
