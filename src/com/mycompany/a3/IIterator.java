package com.mycompany.a3;

public interface IIterator {
	
	boolean hasNext();
	
	GameObject getNext();
	

	void remove(GameObject gameobjects);
}
