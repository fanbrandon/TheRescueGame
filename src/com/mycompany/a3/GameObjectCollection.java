package com.mycompany.a3;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection {
	private ArrayList<GameObject> gameObjects;
	
	// constructor for GameObjectCollection
	public GameObjectCollection() {
		gameObjects = new ArrayList<GameObject>();
	}

	@Override
	public void add(GameObject object) {
		gameObjects.add(object);
		
	}
	// 
	@Override
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	// Iterator and its has next and get next method, included a remove method as well
	private class GameObjectIterator implements IIterator {
		private int currElementIndex;
		
		public GameObjectIterator() {
			currElementIndex = -1;
		}
		
		@Override
		public boolean hasNext() {
			if (gameObjects.size() <=0) {
				return false;
			}
			if (currElementIndex == gameObjects.size() - 1) {
				return false;
			}
			return true;
		}
		
		@Override
		public GameObject getNext() {
			currElementIndex++;
			return gameObjects.get(currElementIndex);
		}

		@Override
		public void remove(GameObject gameobjects) {
			gameObjects.remove(gameobjects);
			
		}

		
	}
	// more methods to get, size, and remove the arraylist
	public int size() {
		 return gameObjects.size();
	}

	public GameObject get(int i) {
		return gameObjects.get(i);
	}

	public void remove(int i) {
		gameObjects.remove(i);
		
	}

	

	
}
