package com.mycompany.a3.Commands;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command {
	
	
		private GameWorld gw;
		
		
		Toolbar toolBar;
		
		
		
		
	    public SoundCommand(GameWorld gw, Toolbar toolBar) {
	    	
	    	
	        super("Sound");
	        
	        
	        this.gw = gw;
	        
	        
	        this.toolBar = toolBar;
	        
	    }
	    
	    
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	
	    	
	    	if (((CheckBox) e.getComponent()).isSelected()) {
	    		
	    		
	    		gw.setSound(true);
	    		
	    	} else {
	    		
	    		
	    		gw.setSound(false);
	    		
	    	}
	    	
	    	// Close the toolbar
	    	toolBar.closeSideMenu();
	        
	    }
}
