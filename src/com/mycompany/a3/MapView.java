package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.models.Point;

public class MapView extends Container implements Observer {
	
	private GameWorld gw;
	
	public MapView(GameWorld gw) {
		
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255, 0, 0))); // red border around center container
		
		this.gw = gw;
	}

	@Override
	public void update(Observable observable, Object data) {
		
		gw.displayCurrentMap();
		
		this.revalidate();
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		Point pCmpRelPrnt = new Point(getX(),getY());
		
		IIterator iterator = gw.getIterator();
		
		while(iterator.hasNext()) {
			GameObject tempObject = iterator.getNext();
			
			if (tempObject instanceof IDrawable) {
				((IDrawable)tempObject).draw(g,pCmpRelPrnt);
			}
		}
	}
	
	@Override
	public void pointerPressed(int x, int y) {
	    // Adjust the pointer coordinates to MapView's parent's origin
	    x = x - getParent().getAbsoluteX();
	    y = y - getParent().getAbsoluteY();

	    boolean objectSelected = false;
	    
	    Point pPtrRelPrnt = new Point(x,y);
	    Point pCmpRelPrnt = new Point(getX(), getY());

	    // Get the IIterator instance from the GameWorld
	    IIterator iterator = gw.getIterator();

	    // Traverse the game objects
	    while (iterator.hasNext()) {
	        GameObject obj = (GameObject) iterator.getNext();
	        if (obj instanceof ISelectable) {
	            ISelectable selectable = (ISelectable) obj;
	            if (selectable.contains(pPtrRelPrnt, pCmpRelPrnt)) {
	                // Select this object
	                selectable.setSelected(true);
	                objectSelected = true;
	            } else {
	                // Unselect all other objects
	                selectable.setSelected(false);
	            }
	        }
	    }

	    // If no object is clicked, clear selection
	    if (!objectSelected) {
	        iterator = gw.getIterator(); // Reset the iterator
	        while (iterator.hasNext()) {
	            GameObject obj = (GameObject) iterator.getNext();
	            if (obj instanceof ISelectable) {
	                ((ISelectable) obj).setSelected(false);
	            }
	        }
	    }

	    repaint(); // Redraw to show selection changes
	}
}
